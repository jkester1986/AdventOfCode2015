/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpg2;

import org.paukov.combinatorics.*;
import java.util.*;

/**
 *
 * @author jkester
 */
public class RPG2 {

    /**
     * @param args the command line arguments
     */
    Player you = new Player();
    //String[] spells = {"Poison", "Magic Missile","Recharge", "Drain", "Shield" };
    
    Spell magMissile = new Spell("Magic Missile", 53, 0, 4, 0, 0);
    Spell drain = new Spell("Drain", 73, 0, 2, 2, 0);
    Spell shield = new Spell("Shield", 113, 6, 0, 7, 0);
    Spell poison = new Spell("Poison", 173, 6, 3, 0, 0);
    Spell recharge = new Spell("Recharge", 229, 5, 0, 0, 101);
    
    Spell[] spells = {shield, recharge, drain, poison, magMissile};
    
    int bossHealth = 58;
    int bossAttack = 9;
    
    ArrayList<Player> winners = new ArrayList<>();
    ArrayList<Player> losers = new ArrayList<>();
    
    Player winner = new Player();
    int manaCost = 0;
    
    public boolean fightBoss(boolean hasWinner){
       if(hasWinner){
           return true;
       }
       else{
           for(Spell spell : spells){
                boolean aWinner = false;
                int originalArmor = you.armor;
                int originalHealth = you.health;
                int bossOriginalHealth = bossHealth;
                int originalMana = you.mana;
                int originalManaSpent = you.manaSpent;
                ArrayList<Spell> originalAllAttacks = new ArrayList<>();
                
                for(Spell attack : you.getAttacks()){
                    originalAllAttacks.add(new Spell(attack));
                }
                
                ArrayList<Spell> originalEffects = new ArrayList<>();
                for(Spell effect : you.getEffects()){
                    originalEffects.add(new Spell(effect));
                }

                /*-----------All the stuff happens----------------*/
                you.health -= 1;
                applyEffects();
                removeTimedOutEffects();

                if (you.health <=0){
                    //System.out.println("You DIED from the boss");
                    Player thisLoser = new Player();
                    thisLoser.manaSpent = you.manaSpent;
                    thisLoser.allAttacks = you.allAttacks;
                    losers.add(thisLoser);
                    //System.out.println("");
                    //System.out.println("");
                    //noWinner = false;
                }
                else if(didBossDie()){//boss died
                    //don't do anything else
                }
                
                else{//boss didn't die from effects
                    if(isInUse(spell)){//trying to cast a spell that's already in use
                        //do nothing so we move on to the next spell
                    }
                    else if((you.manaSpent + spell.cost) > manaCost && manaCost != 0){//cost is going to be higher than the current lowest cost
                        //do nothing so we can move on to next spell
                    }
                    else{
                        aWinner = yourTurn(spell);//take your turn

                        if(!aWinner){//if no winner, boss gets to go
                            you.health -= 1;
                            applyEffects();
                            removeTimedOutEffects();
                            if (you.health <=0){
                                //System.out.println("You DIED from the boss");
                                Player thisLoser = new Player();
                                thisLoser.manaSpent = you.manaSpent;
                                thisLoser.allAttacks = you.allAttacks;
                                losers.add(thisLoser);
                                //System.out.println("");
                                //System.out.println("");
                                //noWinner = false;
                            }
                            else if(didBossDie()){//boss died from an effect you cast
                                //don't do anything else
                            }
                            else{//otherwise, boss gets his turn
                                aWinner = bossTurn(); 
                                fightBoss(aWinner);
                            }
                        }
                        else{
                            //you won, nothing else happens
                        }
                    }
                }


                /******* REVERSE EVERYTHING AFTER THIS ***************/
                
                you.armor = originalArmor;
                you.health = originalHealth;
                bossHealth = bossOriginalHealth;
                you.mana = originalMana;
                you.manaSpent = originalManaSpent;
                you.allAttacks = new ArrayList<>();
                for(Spell attack : originalAllAttacks){
                    you.allAttacks.add(new Spell(attack));
                }
                you.spellsInEffect = new ArrayList<>();
                for(Spell effect : originalEffects){
                    you.spellsInEffect.add(new Spell(effect));
                }

            }
       }
       return true;//made it through all combinations of spells to win/lose
    }
    
    
    public boolean isInUse(Spell spell){
        boolean inUse = false;
        for(Spell spellInList : you.spellsInEffect){
            if(spellInList.name.equals(spell.name)){
                //System.out.println("trying to use a spell with an effect already in play: " + spell.name);
                
                inUse = true;
                break;
            }
        }
        return inUse;
    }
    
    public boolean yourTurn(Spell spell){
        //System.out.println("");
        //System.out.println("");
        //System.out.println("Your turn!");
        int cost = spell.cost;
        
        if(cost <= you.mana){//if you have enough mana to cast the spell
            you.mana -= cost;
            you.manaSpent += cost;
            you.addAttack(spell);
            switch (spell.name){
                case "Shield":
                    you.armor = 7;
                    //System.out.println("You now have armor");
                    you.spellsInEffect.add(new Spell("Shield", 113, 6, 0, 7, 0));
                    break;
                case "Magic Missile":
                    //System.out.println("You launched a missile and hit the boss for 4 damage");
                    bossHealth -= 4;
                    //System.out.println("Boss's health is now: " + bossHealth);
                    break;
                case "Drain":
                    bossHealth -= 2;
                    //System.out.println("You cast Drain. You healed yourself for 2 and attacked for 2. Boss health is " + bossHealth);
                    you.health += 2;
                    //System.out.println("Your health is: " + you.health);
                    //System.out.println("Boss's health is " + bossHealth);
                    break;
                case "Recharge":
                    //System.out.println("You cast recharge. The timer on it is : " + spell.timer);
                    you.spellsInEffect.add(new Spell("Recharge", 229, 5, 0, 0, 101));
                    break;
                case "Poison":
                    //System.out.println("You cast Poison. The timer on it is: " + spell.timer);
                    you.spellsInEffect.add(new Spell("Poison", 173, 6, 3, 0, 0));
                    break;
            }
            return didBossDie(); //boss died
        }
        else{//you don't have enough money
            
            return true;
        }
    }
    
    public boolean bossTurn(){
        
            int actualBossAttack = bossAttack - you.armor;
            if(actualBossAttack <= 0){
                actualBossAttack = 1;
            }
            //System.out.println("The boss just attacked you for " + actualBossAttack);
            you.health -= actualBossAttack;
            //System.out.println("your health is " + you.health);
            if(you.health <= 0){//you died
                //System.out.println("You DIED from the boss");
                Player thisLoser = new Player();
                thisLoser.manaSpent = you.manaSpent;
                thisLoser.allAttacks = you.allAttacks;
                losers.add(thisLoser);
                //System.out.println("");
                //System.out.println("");
                //noWinner = false;
                return true;
            }
            else return false;
    }
    
    public void applyEffects(){
        for(Spell effect : you.spellsInEffect){
            //System.out.println(spellInList.name);
            switch (effect.name){
                case "Shield":
                    //System.out.println("Your shield is still in use");
                    break;
                case "Recharge":
                    you.mana += 101;
                    //System.out.println("your mana increases by 101 to " + you.mana);
                    break;
                case "Poison":
                    bossHealth -= 3;
                    //System.out.println("The boss is poisoned by 3. Boss health: " + bossHealth);
                    break;
                default: break;
            }
            effect.timer--;
            //System.out.println("The timer of " + spellInList.name + " is " + spellInList.timer);
        }
    }
    
    public void removeTimedOutEffects(){
        if(!you.spellsInEffect.isEmpty()){//if there are effects in place
            ArrayList<Integer> indexRemove = new ArrayList<>();
            for(int effectIndex = 0; effectIndex < you.spellsInEffect.size(); effectIndex++){//check to see if the timer has run out on any of them
                
                if(you.spellsInEffect.get(effectIndex).timer <= 0){
                    indexRemove.add(effectIndex);
                }
                
                else{
                }
            }
            for(int remove = indexRemove.size()-1;remove >= 0; remove--){
                Integer indexToRemove = indexRemove.get(remove);
                
                if(you.spellsInEffect.get(indexToRemove).name.equals("Shield")){
                    you.armor = 0;//shield timer is up, reset armor to nothing
                }
                you.spellsInEffect.remove(you.spellsInEffect.get(indexToRemove));
            }
        }
    }
    
    public boolean didBossDie(){
        if(bossHealth <= 0){//boss died
            //System.out.println("YOU WIN!!");
            Player thisWinner = new Player();
            thisWinner.manaSpent = you.manaSpent;
            thisWinner.allAttacks = new ArrayList(you.getAttacks());
            winners.add(new Player(thisWinner));
            if(you.manaSpent < manaCost || manaCost == 0){
                //System.out.println("You are the new winner");
                winner = new Player(thisWinner);
                manaCost = you.manaSpent;
                //System.out.println("Mana cost was" + manaCost);
                /*
                System.out.println("Spells used: ");
                for(Spell spell : you.allAttacks){
                    System.out.println(spell.name);
                }
                System.out.println("");
*/
                       
            }
            return true;
            //return true;
        }
        else{
            return false;
        }
    }
    
    public Player getLowestWinningCostPlayer(){
        int lowestCost = 0;
        Player winningPlayer = new Player();
        for(Player player : winners){
            if(lowestCost == 0){
                lowestCost = player.manaSpent;
                winningPlayer = player;
            }
            else{
                if(player.manaSpent < lowestCost){
                    lowestCost = player.manaSpent;
                    winningPlayer = player;
                }
            }
        }
        System.out.println("The lowest cost is " + lowestCost);
        System.out.println("The attacks used were: ");
        for(Spell spell : winningPlayer.allAttacks){
            System.out.println(spell.name);
        }
        return winningPlayer;
    }
    
    public Player getHighestCostLosingPlayer(){
        int highestCost = 0;
        Player losingPlayer = new Player();
        for(Player player : losers){
            //System.out.println("Losing player manaSpent = " + player.manaSpent);
            if(player.manaSpent > highestCost){
                highestCost = player.manaSpent;
                losingPlayer = player;
            }
            
        }
        System.out.println("The highest cost is " + highestCost);
        return losingPlayer;
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        RPG2 rpg = new RPG2();
        rpg.fightBoss(false);
        
        System.out.println("There are " + rpg.winners.size() + " winners");
        System.out.println("Lowest cost winner = " + rpg.getLowestWinningCostPlayer());
        //System.out.println("There are " + rpg.losers.size() + " losers");
        //System.out.println("Highest cost loser = " + rpg.getHighestCostLosingPlayer().manaSpent);
        
        System.out.println("Mana cost: " + rpg.manaCost);
        System.out.println("attacks: " + rpg.winner.allAttacks);
    }
    
}

