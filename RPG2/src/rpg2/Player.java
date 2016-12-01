/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpg2;

/**
 *
 * @author jkester
 */
import java.util.*;

/**
 *
 * @author jkester
 */
public class Player {
    int armor;
    //int damage;
    int mana;
    
    int health;
    
    int manaSpent;
    
    ArrayList<Spell> spellsInEffect = new ArrayList<>();
    
    ArrayList<Spell> allAttacks = new ArrayList<>();
    
    public Player(){
        armor = 0;
        //damage = 0;
        mana = 500;
        health = 50;
        
        manaSpent = 0;
    }
    
    public Player(Player player){
        armor = player.armor;
        //damage = player.damage;
        mana = player.mana;
        health = player.health;
        
        manaSpent = player.manaSpent;
        allAttacks = new ArrayList(player.allAttacks);
        
    }
    
    public void addEffect(Spell spell){
        spellsInEffect.add(spell);
    }
    
    public void addAttack(Spell spell){
        allAttacks.add(spell);
    }
    
    public ArrayList<Spell> getAttacks(){
        return allAttacks;
    }
    
    public ArrayList<Spell> getEffects(){
        return spellsInEffect;
    }
    
    
}