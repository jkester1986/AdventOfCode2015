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
public class Player {
    int armor;
    int damage;
    int cost;
    
    int health;
    
    ArrayList<String> itemNames = new ArrayList<>();
    
    public Player(){
        armor = 0;
        damage = 0;
        cost = 0;
        health = 100;
    }
    
    public Player(Item item){
        armor = item.armor;
        damage = item.damage;
        cost = item.cost;
        health = 100;
        
        itemNames.add(item.name);
    }
    
    public void addItem(Item item){
        armor += item.armor;
        damage += item.damage;
        cost += item.cost;
        
        itemNames.add(item.name);
    }
}
