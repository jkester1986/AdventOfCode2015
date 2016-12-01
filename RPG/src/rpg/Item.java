/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpg;

/**
 *
 * @author jkester
 */
public class Item {
    public int cost; 
    public int damage;
    public int armor;
    
    String name;
    
    public Item(String itemName, int itemCost, int itemDamage, int itemArmor){
        cost = itemCost;
        damage = itemDamage;
        armor = itemArmor;
        name = itemName;
    }
}
