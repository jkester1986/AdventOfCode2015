/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leftovers;

import java.util.*;
import org.paukov.combinatorics.*;
import org.paukov.combinatorics.permutations.*;

/**
 *
 * @author jkester
 */
public class Leftovers {

    /**
     * @param args the command line arguments
     */
    int workingTot = 0;
    
    boolean hitSmallest = false;
    int smallestSize = 500;
    
    public int[] inputToArray(Scanner sc){
        
        String string ="";
        while(sc.hasNext()){
            String next = sc.next();
            if(next.equals("exit")){
                break;
            }
            
            else string += next + " ";
        }
        System.out.println(string);
        String[] valArray = string.split(" ");
        int[] ints = new int[valArray.length];
        for(int i = 0; i < valArray.length; i++){
            ints[i] = Integer.parseInt(valArray[i]);
        }
        Arrays.sort(ints);
        return ints;
    }
    
    public String[] inputToSArray(Scanner sc){
        String string ="";
        while(sc.hasNext()){
            String next = sc.next();
            if(next.equals("exit")){
                break;
            }
            
            else string += next + " ";
        }
        System.out.println(string);
        String[] valArray = string.split(" ");
        return valArray;
    }
    
    public void setSize(ICombinatoricsVector<String> subSet){
        
        workingTot++;
        smallestSize = subSet.getSize();
        hitSmallest = true;
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        Leftovers leftovers = new Leftovers();
        Scanner sc = new Scanner(System.in);
        ICombinatoricsVector<String> initialSet = Factory.createVector(leftovers.inputToSArray(sc));
        //Generator<String> gen = Factory.createSubSetGenerator(initialSet);
         Generator<String> gen = Factory.createSubSetGenerator(initialSet, false);
        //System.out.println(gen.getNumberOfGeneratedObjects());
        
        for (ICombinatoricsVector<String> subSet : gen) {
            //System.out.println(subSet);
            
            int total = 0;
            for(String combination: subSet){
                total += Integer.parseInt(combination);
            }
            //System.out.println("total is: " + total);
            if(total == 150){
                leftovers.workingTot++;
                if(subSet.getSize()< leftovers.smallestSize){
                    leftovers.smallestSize = subSet.getSize();
                }
            }
            
        }
        System.out.println("Smallest size is: " + leftovers.smallestSize);
        System.out.println("Total working combos is " + leftovers.workingTot);
        leftovers.workingTot = 0;
        
        for (ICombinatoricsVector<String> subSet : gen) {
            //System.out.println(subSet);
            
            int total = 0;
            for(String combination: subSet){
                total += Integer.parseInt(combination);
            }
            //System.out.println("total is: " + total);
            if(total == 150 && subSet.getSize() == 4){
                leftovers.workingTot++;
                
            }
            
        }
        System.out.println("Total working combos is " + leftovers.workingTot);
        /*
        for(Integer i: ints){
            System.out.println(i);
        }
                */
    }
    
}
