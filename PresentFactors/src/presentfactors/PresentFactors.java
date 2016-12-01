/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentfactors;

import java.awt.BorderLayout;
import java.util.*;

/**
 *
 * @author jkester
 */
public class PresentFactors {

    /**
     * @param args the command line arguments
     */

   
    public int sumFactors(Integer[] factors){
        int sum = 0;
        for(int factor : factors){
            sum += factor;
        }
        System.out.println("The sum is " + sum);
        return sum;
    }
    
    public void visitHouses(){
        outerloop:
        for(int house = 1; house <= 36000000; house ++){
            int maxD = (int)Math.sqrt(house);
            int sum=0;
            for(int i = 1; i <= maxD; i++)
            {
                if(house % i == 0)
                {
                   if(i*50 >= house) sum += i*11;
                    //System.out.println("sum: " + sum);
                   int d = house/i;
                   if(d!=i && d*50 >= house){
                       //System.out.println("sum: " + sum);
                        sum+=d*11;
                   }
                     
                   
                }
            }
            //System.out.println("house #" + house + " total is " + sum);
            
            if(sum >=  36000000){
                System.out.println("The house number is : " + house);
                break outerloop;
            }
            
        }
        
        
        /*
        int house = 1;
        int[] houses = new int[36000000];
        //boolean foundHouse = false;
        outerloop:
        //house total of 766922 : 12462300
        while(house < 36000000 ){
            for (int elf = 1; elf <= 36000000; elf++){
                if(elf <= house){
                    if(house%elf == 0){
                        //System.out.println("elf " + elf + " is a factor of house " + house);
                        houses[house-1] += elf*10;
                        if(houses[house-1] >= 36000000){
                            System.out.println("lowest house is " + house);
                            System.out.println("lowest elf is " + elf);
                            break outerloop;
                        }
                    }
                }
                else break;
            }
            System.out.println("house total of " + house + " : " + houses[house-1]);
            house++;
        }
                */
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        PresentFactors pf = new PresentFactors();
        pf.visitHouses();
    }
    
}
