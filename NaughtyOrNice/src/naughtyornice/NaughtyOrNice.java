/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package naughtyornice;

import java.util.*;
import java.lang.*;

/**
 *
 * @author jkester
 */
public class NaughtyOrNice {

    /**
     * @param args the command line arguments
     */
    int niceCount = 0;
    int naughtCount = 0;
    
    public String checkText(String text){
        boolean hasEnoughVowels = false;//not enough vowels
        boolean hasDoubleLetters = false;//no double letters
        boolean hasPairDoubleLetters = false;//no double letter pairs
        int vowelTotal = 0;
        String result = "This text is not nice...";
        
        //ArrayList<Character> checkVowels = new ArrayList<Character>();//stores all vowels in a word
        //ArrayList<Character> checkDoubles = new ArrayList<Character>();
        
        for (int i = 0; i < text.length(); i++){
            int nextChar = i+1;
            Character currChar = text.charAt(i);
            
            breakSwitch:
            if(vowelTotal < 3){//if there are not enough vowels
                switch(currChar) {
                case 'a':
                case 'e':
                case 'i':
                case 'o':
                case 'u':
                    vowelTotal ++;
                    if(vowelTotal >= 3){
                        hasEnoughVowels = true;
                        //System.out.println("There are enough vowels");
                    }
                    //System.out.println("Vowel is: " + text.charAt(i));
                    break breakSwitch;
                }
            }
            
            if(!hasDoubleLetters && nextChar < text.length()){
                if(currChar == text.charAt(nextChar)){
                    hasDoubleLetters = true;
                }
            }
            
            
            if(hasEnoughVowels  && hasDoubleLetters){
                result = "This text is nice!";
                niceCount ++;
                break;
            }
        
        }
        return result;
    }
        
    
    /**
     *
     * @param textInput
     */
    public void checkLines(Scanner textInput){
        while(textInput.hasNext()) {
            String line = textInput.next();
            if(line.contains("ab") || line.contains("cd") || line.contains("pq") || line.contains("xy")){
            }
            else if(!line.equals("exit")) checkText(line);
            else break;
        }
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        NaughtyOrNice nON = new NaughtyOrNice();
        Scanner sc = new Scanner(System.in);
        System.out.println("What words need to be checked?");
        nON.checkLines(sc);
        System.out.println("There are " + nON.niceCount + " nice words");
        //String text = sc.nextLine();
        //System.out.println(nON.checkText(text));
    }
    
}
