/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cookierecipe;

import org.paukov.combinatorics.*;

/**
 *
 * @author jkester
 */
public class CookieRecipe {

    /**
     * @param args the command line arguments
     */
    Ingredient sprinkles = new Ingredient(2, 0, -2, 0, 3);
    Ingredient butterscotch = new Ingredient(0, 5, -3, 0, 3);
    Ingredient chocolate = new Ingredient(0, 0, 5, -1, 8);
    Ingredient candy = new Ingredient(0, -1, 0, 5, 8);
    
    int biggestScore = 0;
    
    public int cookieScore(Generator<String> gen){
        // Print all possible combinations
        for (ICombinatoricsVector<String> combination : gen) {
           
            int capacity = 0;
            int durability = 0;
            int flavor = 0;
            int texture = 0;
            int calories = 0;
            
            //for every ingredient in the combination
            for(String ingredient : combination){
                switch (ingredient){
                    case "sprinkles":
                        capacity += sprinkles.capacity;
                        durability += sprinkles.durability;
                        flavor += sprinkles.flavor;
                        texture += sprinkles.texture;
                        calories += sprinkles.calories;
                        break;
                    case "butterscotch":
                        capacity += butterscotch.capacity;
                        durability += butterscotch.durability;
                        flavor += butterscotch.flavor;
                        texture += butterscotch.texture;
                        calories += butterscotch.calories;
                        break;
                    case "chocolate":
                        capacity += chocolate.capacity;
                        durability += chocolate.durability;
                        flavor += chocolate.flavor;
                        texture += chocolate.texture;
                        calories += chocolate.calories;
                        break;    
                    case "candy":
                        capacity += candy.capacity;
                        durability += candy.durability;
                        flavor += candy.flavor;
                        texture += candy.texture;
                        calories += candy.calories;
                        break;    
                    default:
                        break;
                }  
            }
            //System.out.println("total capacity: " + capacity);
            if(capacity < 0) capacity = 0;
            if(durability < 0) durability = 0;
            if(flavor < 0) flavor = 0;
            if(texture < 0) texture = 0;
            if(calories < 0) calories = 0;
            
            int totalScore = capacity*durability*flavor*texture;
            if(totalScore > biggestScore && calories == 500) biggestScore = totalScore;
        }
        return biggestScore;
    }
    
    
    
    public static void main(String[] args) {
        // TODO code application logic here
        CookieRecipe recipe = new CookieRecipe();
        
        //Ingredient[] ingredients = {sprinkles, butterscotch, chocolate, candy};
        // Create the initial vector of (apple, orange)
        ICombinatoricsVector<String> initialVector = Factory.createVector(
           new String[] { "candy", "sprinkles", "chocolate", "butterscotch" } );

        // Create a multi-combination generator to generate 3-combinations of
        // the initial vector
        Generator<String> gen = Factory.createMultiCombinationGenerator(initialVector, 100);

        System.out.println(recipe.cookieScore(gen));
    }
    
}
