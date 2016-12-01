/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package naughtyornice;

import java.util.Scanner;
import java.lang.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author jkester
 */
public class NaughtyOrNiceRegex {
    
    /**
     * @param args the command line arguments
     */
    int niceCount = 0;
    int naughtyCount = 0;
    
 
    
    /**
     *
     * @param textInput
     */
    public void checkLines(Scanner textInput){
        String pattern1 = ".*(\\w\\w).*\\1.*";//pair that repeats twice
        String pattern2 = ".*(\\w)\\w\\1.*";//letter that repeats with exactly one letter between
        
        Pattern p1 = Pattern.compile(pattern1);
        Pattern p2 = Pattern.compile(pattern2);
        
        while(textInput.hasNext()) {
            
            String line = textInput.next();
            if(!line.equals("exit")) {
                if(p1.matcher(line).matches() && p2.matcher(line).matches()){
                //if(p1.matcher(line).matches()){
                    System.out.println("word matches");
                    niceCount ++;
                }
                else {
                    System.out.println("word doesn't match");
                    naughtyCount ++;
                }
            }
            else break;//stop checking lines, we're done
        }
    }
    
    public void checkLinesOld(Scanner textInput){
        String pattern1 = "(.*[aeiou].*){3}";//look for at least 3 vowels
        String pattern2 = ".*(\\w)\\1.*";//look for double letter pattern
        String pattern3 = "^(?!.*?(?:ab|cd|pq|xy)).*$";//entures that ab, cd, pq, xy pairs are not anywhere in word
        
        Pattern p1 = Pattern.compile(pattern1);
        Pattern p2 = Pattern.compile(pattern2);
        Pattern p3 = Pattern.compile(pattern3);
        
        while(textInput.hasNext()) {
            
            String line = textInput.next();
            if(!line.equals("exit")) {
                if(p1.matcher(line).matches() && p2.matcher(line).matches() && p3.matcher(line).matches()){
                //if(p3.matcher(line).matches()){
                    System.out.println("word matches");
                    niceCount ++;
                }
                else {
                    System.out.println("word doesn't match");
                    naughtyCount ++;
                }
            }
            else break;//stop checking lines, we're done
        }
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        NaughtyOrNiceRegex nON = new NaughtyOrNiceRegex();
        Scanner sc = new Scanner(System.in);
        System.out.println("Let's check some words:");
        nON.checkLines(sc);
        System.out.println("There are " + nON.niceCount + " nice words");
        //String text = sc.nextLine();
        //System.out.println(nON.checkText(text));
    }
}
