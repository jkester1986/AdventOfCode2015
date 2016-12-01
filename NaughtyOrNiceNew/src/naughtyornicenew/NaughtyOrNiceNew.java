/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package naughtyornicenew;

import java.util.*;
import java.lang.*;

/**
 *
 * @author jkester
 */
public class NaughtyOrNiceNew {

    /**
     * @param args the command line arguments
     */
    
    int niceCount = 0;
    int totalPairs = 0;
    int totalDoubles = 0;
    
    public String checkText(String text){
        boolean hasPairOfPairs = false;//any repeating pair of letters
        boolean hasDoubleLetters = false;//no double letters
        String result = "This text is not nice...";
        String previousString = "";
        String twoBackString = "";
        
        //ArrayList<Character> checkVowels = new ArrayList<Character>();//stores all vowels in a word
        ArrayList<String> checkDoubles = new ArrayList<String>();
        
        for (int i = 0; i < text.length(); i++){
            int prevChar = i-1;
            int nextChar = i+1;
            int thirdChar = i+2;
            Character currChar = text.charAt(i);
            
            if(!hasPairOfPairs){//check for case 2
                //System.out.println("The previous character index is " + prevChar);
                //System.out.println("The current character index is " + i);
                if(prevChar >= 0){//if there is a previous character
                   
                    if(nextChar < text.length() ){
                        StringBuilder sb = new StringBuilder();
                        sb.append(currChar);
                        sb.append(text.charAt(nextChar));
                        String compareString =  sb.toString();
                        //System.out.println("New string = " + compareString);
                        
                        // if the previous set of characters + the current set don't match
                        //and the current set is already in the comparison ArrayList
                        if((checkDoubles.contains(compareString) && !compareString.equals(previousString)) 
                                ||
                                (checkDoubles.contains(compareString) && compareString.equals(twoBackString))){
                            
                            hasPairOfPairs = true;
                            totalPairs ++;
                            //System.out.println("There are pairs");
                        }
                        else {
                            checkDoubles.add(compareString);
                            twoBackString = previousString;//the previous string becomes the string from 2 back
                            previousString = compareString;//the current string becomes the previous string
                        }
                    }
                }
                else{//the first set of characters to compare
                    StringBuilder sb = new StringBuilder();
                    sb.append(currChar);
                    sb.append(text.charAt(nextChar));
                    String firstString =  sb.toString();
                    checkDoubles.add(firstString);
                    previousString = firstString;
                }
            }
            
            //if there are no double letters yet and the third character's index is less than the length of the word
            if(!hasDoubleLetters && thirdChar < text.length()){
                if(currChar.equals(text.charAt(thirdChar))){//if the current character = the character two indexes forward
                    hasDoubleLetters = true;
                    totalDoubles++;
                }
            }
            
            
            
            if(hasPairOfPairs  && hasDoubleLetters){
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
            if(!line.equals("exit")) checkText(line);
            else break;
        }
    }
    public static void main(String[] args) {
        // TODO code application logic here
        NaughtyOrNiceNew nON = new NaughtyOrNiceNew();
        Scanner sc = new Scanner(System.in);
        System.out.println("What words need to be checked?");
        nON.checkLines(sc);
        System.out.println("total words with doubles = " + nON.totalDoubles);
        System.out.println("total words with a pair of pairs = " + nON.totalPairs);
        System.out.println("There are " + nON.niceCount + " nice words");
    }
    
}
