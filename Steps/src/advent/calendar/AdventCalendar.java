/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advent.calendar;
import java.io.*;
import java.util.Scanner;

/**
 *
 * @author jkester
 */
public class AdventCalendar {

    /**
     * @param args the command line arguments
     */
    int aboveGround = 0;
    int basement = 0;
    
    public int whatFloor(String floors){
        int count = floors.length();
        int level = 0;
        int notCounted = 0;
        
        for (int i = 0; i < count; i++){
            if(floors.charAt(i) == ')'){
                level --;
                if(basement == 0 && level < 0 )basement = i+1;
                
            }
            else if(floors.charAt(i) == '('){
                level ++;
                if(aboveGround == 0 && level > 0) aboveGround = i+1;
            }
            else notCounted ++;
        }
        return level;
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        //Scanner myInput = new Scanner(System.in);
        AdventCalendar calendar = new AdventCalendar();
        Scanner user_input = new Scanner( System.in );
        System.out.println("What is the string you'd like to input?");
        String myInput;
        myInput = user_input.next();
        System.out.println(calendar.whatFloor(myInput));
        System.out.println("The first time Santa goes above the main floor is at position " + calendar.aboveGround);
        System.out.println("The first time Santa goes into the basement is at position" + calendar.basement);
    }
    
}
