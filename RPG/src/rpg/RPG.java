/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpg;

import java.util.*;
/**
 *
 * @author jkester
 */
public class RPG {

    /**
     * @param args the command line arguments
     */
    Item[] weapons = new Item[5];
    Item[] armor = new Item[5];
    Item[] rings = new Item[6];
    
    ArrayList<Player> players = new ArrayList<>();
    
    ArrayList<Player> winningPlayers = new ArrayList<>();
    ArrayList<Player> losingPlayers = new ArrayList<>();
    
    
    public void addWeapons(Scanner sc){
        int index = 0;
        while(sc.hasNext()){
            String line = sc.next();
            if(line.equals("exit")){
                break;
            }
            else{
                Item item;
                item = new Item(line, 
                        Integer.parseInt(sc.next()),
                        Integer.parseInt(sc.next()), 
                        Integer.parseInt(sc.next()));
                weapons[index] = item;
                index++;
            }
        }
        /*
        for(Item weapon : weapons){
            System.out.println(weapon.name + " " + weapon.cost + " " + weapon.damage + " " + weapon.armor);
        }
        */
    }
    
    public void addArmor(Scanner sc){
        int index = 0;
        while(sc.hasNext()){
            String line = sc.next();
            if(line.equals("exit")){
                break;
            }
            else{
                Item item;
                item = new Item(line, 
                        Integer.parseInt(sc.next()),
                        Integer.parseInt(sc.next()), 
                        Integer.parseInt(sc.next()));
                armor[index] = item;
                index++;
            }
        }
        /*
        for(Item armor : armor){
            System.out.println(armor.name + " " + armor.cost + " " + armor.damage + " " + armor.armor);
        }
                */
    }
    
    public void addRings(Scanner sc){
        int index = 0;
        while(sc.hasNext()){
            String line = sc.next();
            if(line.equals("exit")){
                break;
            }
            else{
                Item item;
                item = new Item(line + sc.next(), 
                        Integer.parseInt(sc.next()),
                        Integer.parseInt(sc.next()), 
                        Integer.parseInt(sc.next()));
                rings[index] = item;
                index++;
            }
        }
        /*
        for(Item ring : rings){
            System.out.println(ring.name + " " + ring.cost + " " + ring.damage + " " + ring.armor);
        }*/
    }
    
    public void getGearCombos(){
        for(Item weapon : weapons){//base case, one weapon
            Player player = new Player(weapon);
            players.add(player);
        }
        
        for(Item weapon : weapons){//one weapon, all combos of armor
            for(Item armor : armor){
                Player player = new Player(weapon);
                player.addItem(armor);
                players.add(player);
            }
        }
        for(Item weapon : weapons){//one weapon, all combos of 1 ring
            for(Item ring : rings){
                Player player = new Player(weapon);
                player.addItem(ring);
                players.add(player);
            }
        }
        for(Item weapon : weapons){//one weapon, all combos of 1 ring + 1 armor
            for(Item ring : rings){
                for(Item armor : armor){
                    Player player = new Player(weapon);
                    player.addItem(ring);
                    player.addItem(armor);
                    players.add(player);
                } 
            }
        }
        
        for(Item weapon : weapons){//one weapon, all combos of 2 rings
            for(Item ring : rings){
                for(Item ring2 : rings){
                    if(ring != ring2){
                        Player player = new Player(weapon);
                        player.addItem(ring);
                        player.addItem(ring2);
                        players.add(player);
                    }  
                }
            }
        }
        
        for(Item weapon: weapons){
            for(Item armor : armor){
                for(Item ring : rings){
                    for(Item ring2 : rings){
                        if(ring != ring2){
                            Player player = new Player(weapon);
                            player.addItem(ring);
                            player.addItem(ring2);
                            player.addItem(armor);
                            players.add(player);
                        }  
                    }
                }
            }
        }
                
        /*
        for(Player player : players){
            System.out.println("Player damage: " + player.damage 
                    + " Player armor: " + player.armor 
                    + " Player cost: " + player.cost
                    + " Player items: " + player.itemNames);
        }
                */
    }
    
    public void fightMonster(){
        Player monster = new Player();
        monster.health = 104;
        monster.damage = 8;
        monster.armor = 1;
        
        for(Player player : players){
            boolean winner = false;
            boolean monsterTurn = false;
            
            while(!winner){
                if(!monsterTurn){//your turn
                    monster.health -= (player.damage - monster.armor);
                    //System.out.println("monster health = " + monster.health);
                    if(monster.health <= 0){//if monster dies
                        winningPlayers.add(player);
                        winner = true;
                        monster.health = 104;
                    }
                    monsterTurn = true;
                }
                else{//monster's turn
                    player.health -= (monster.damage - player.armor);
                    //System.out.println("Your health is " + player.health);
                    if(player.health <= 0){//if you die
                        winner = true;
                        monster.health = 104;
                        losingPlayers.add(player);
                    }
                    monsterTurn = false;
                }
            }
            
        }
    }
    
    public Player getLowestWinningCostPlayer(){
        int lowestCost = 0;
        Player winningPlayer = new Player();
        for(Player player : winningPlayers){
            if(lowestCost == 0){
                lowestCost = player.cost;
                winningPlayer = player;
            }
            else{
                if(player.cost < lowestCost){
                    lowestCost = player.cost;
                    winningPlayer = player;
                }
            }
        }
        System.out.println("The lowest cost is " + lowestCost);
        return winningPlayer;
    }
    
    public Player getHighestCostLosingPlayer(){
        int highestCost = 0;
        Player losingPlayer = new Player();
        for(Player player : losingPlayers){
            
            if(player.cost > highestCost){
                highestCost = player.cost;
                losingPlayer = player;
            }
            
        }
        System.out.println("The lowest cost is " + highestCost);
        return losingPlayer;
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        RPG rpg = new RPG();
        Scanner sc = new Scanner(System.in);
        System.out.println("What weapons are in the store?");
        rpg.addWeapons(sc);
        sc = new Scanner(System.in);
        System.out.println("What armor is in the store?");
        rpg.addArmor(sc);
        sc = new Scanner(System.in);
        System.out.println("What rings are in the store?");
        rpg.addRings(sc);
        sc.close();
        
        rpg.getGearCombos();
        rpg.fightMonster();
        Player winningPlayer = rpg.getLowestWinningCostPlayer();
        System.out.println("The cheapest winning player had these items: " + winningPlayer.itemNames);
        Player losingPlayer = rpg.getHighestCostLosingPlayer();
        System.out.println("The most expensive losing player had these items: " + losingPlayer.itemNames);
    }
    
}
