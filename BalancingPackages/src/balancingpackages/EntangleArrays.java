/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package balancingpackages;

import java.util.*;
/**
 *
 * @author jkester
 */
public class EntangleArrays {
    ArrayList<int[]> weights = new ArrayList();
    
    long lowestEntanglement = 0;
    
    int[] lowestEntArray = new int[5];
    
    public void splitLines(Scanner sc){
        while(sc.hasNextLine()){
            String next = sc.nextLine();
            System.out.println(next);
            if(next.equals("exit")){
                break;
            }
            else{
                weights.add(fromString(next));
            }
        }
    }
    
    private static int[] fromString(String string) {
        String[] strings = string.replace("[", "").replace("]", "").split(", ");
        int result[] = new int[strings.length];
        for (int i = 0; i < result.length; i++) {
          result[i] = Integer.parseInt(strings[i]);
        }
        return result;
    }
    
    public void lowestEntanglement(){
        for(int[] combo : weights){
            long entanglement = 1;
            for(int num : combo){
                entanglement *= num;
            }
            if(lowestEntanglement == 0 || entanglement < lowestEntanglement){
                lowestEntanglement = entanglement;
                lowestEntArray = combo;
            }
        }
        System.out.println("Lowest entanglement is: " + lowestEntanglement);
        System.out.println("The combo for it was: " + Arrays.toString(lowestEntArray));
    }
    
    public static void main(String[] args){
        EntangleArrays ea = new EntangleArrays();
        Scanner sc = new Scanner(System.in);
        System.out.println("What are the arrays to read in?");
        ea.splitLines(sc);
        sc.close();
        
        ea.lowestEntanglement();
    }
}
