/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weathermachinecode;

import java.util.*;

/**
 *
 * @author jkester
 */
public class WeatherMachineCode {

    /**
     * @param args the command line arguments
     */
    ArrayList<ArrayList<Integer>> container = new ArrayList();
    
    
    
    public static void main(String[] args) {
        // TODO code application logic here
        WeatherMachineCode wmc = new WeatherMachineCode();
        
        int start = 1;
        boolean notFound = true;
        ArrayList<Integer> first = new ArrayList();
        first.add(20151125);
        wmc.container.add(first);
        
        long prevNum = 20151125;
        
        outerLoop:
        while(notFound){
            ArrayList<Integer> newList = new ArrayList();
            wmc.container.add(newList);
                for(int row = start; row >= 0; row--){
                    //System.out.println(row);
                    long newInt = (prevNum*252533);
                    //System.out.println("multiplied # = " + newInt);
                    newInt = newInt%33554393;
                    //System.out.println("remainder is " + newInt);
                    prevNum = newInt;
                    
                    //System.out.println(newInt);
                    
                    wmc.container.get(row).add((int)(long)newInt);
                    if(row == 2980){
                        if(wmc.container.get(row).size() == 3075){
                            System.out.println(newInt);
                            System.out.println("made it");
                            notFound = true;
                            break outerLoop;
                        }
                        
                        
                    }
                    else{
                        
                    }
                
            }
            start++;
        }
        
    }
    
}
