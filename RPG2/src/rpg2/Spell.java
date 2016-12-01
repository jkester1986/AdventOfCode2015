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
public class Spell {
    public String name;
    
    public int cost;
    
    public int timer;
    
    public int attack;
    public int armor;
    public int increaseMana;
    
    public Spell(String spellName, int manaCost, int spellTimer, int spellAttack, int spellArmor, int spellIncMana){
        name = spellName;
        cost = manaCost;
        
        timer = spellTimer;
        
        attack = spellAttack;
        armor = spellArmor;
        increaseMana = spellIncMana;
    }
    
    public Spell(Spell spell){
        name = spell.name;
        cost = spell.cost;
        timer = spell.timer;
        attack = spell.attack;
        armor = spell.armor;
        increaseMana = spell.increaseMana;
    }
    
}
