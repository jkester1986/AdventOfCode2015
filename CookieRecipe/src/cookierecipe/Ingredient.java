/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cookierecipe;

/**
 *
 * @author jkester
 */
public class Ingredient {
    public int capacity;
    public int durability;
    public int flavor;
    public int texture;
    public int calories;
    
    public Ingredient(int cap, int dur, int flav, int text, int cal){
        capacity = cap;
        durability = dur;
        flavor = flav;
        texture = text;
        calories = cal;
    }
}