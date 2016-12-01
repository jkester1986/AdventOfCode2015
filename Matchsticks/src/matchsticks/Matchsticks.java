/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matchsticks;



import java.util.*;
import java.util.regex.*;
import java.lang.*;
import java.io.Writer;
//import org.apache.commons.lang.*;

/**
 *
 * @author jkester
 */
public class Matchsticks {

    /**
     * @param args the command line arguments
     */
    int original = 0;
    int shortened = 0;
    int lengthened = 0;
    
    public void shortenStrings(Scanner sc){
        while(sc.hasNext()){
            String printTest = sc.next();
            if(printTest.equals("exit")) break;
            else {
                System.out.println(printTest);
                original += printTest.length();
                System.out.println(printTest.length());
                //printTest = printTest.substring(1, printTest.length()-1);
                printTest = printTest.replaceAll("^\"|\"$", "");
                //System.out.println("The lenght of the split is " + printTest.length);
                System.out.println(printTest);

                for(int i = 0; i < printTest.length(); i++){
                    int iNext = i+1;
                    if(printTest.charAt(i) == '\\' && iNext < printTest.length()){//we found a slash
                        StringBuilder replacementString = new StringBuilder(printTest);
                        if(printTest.charAt(iNext) == '\\'){//double slashes
                            replacementString.deleteCharAt(iNext);
                            printTest = replacementString.toString();
                            System.out.println(printTest);
                        }
                        else if (printTest.charAt(iNext) == 'x'){//got some ascii happening
                            replacementString.delete(iNext, iNext+3);
                            replacementString.setCharAt(i, '.');
                            printTest = replacementString.toString();
                            System.out.println(printTest);
                        }
                        else {
                            replacementString.deleteCharAt(i);
                            printTest = replacementString.toString();
                            System.out.println(printTest);
                            i=i-1;
                        }
                    }
                }
                shortened += printTest.length();
            }
        }
        
        System.out.println("The original size was " + original);
        System.out.println("the shortened size is " + shortened);
        System.out.println("The difference is " + (original-shortened));
    }
    
    public void lengthenStrings(Scanner sc){
        while(sc.hasNext()){
            String thisString = sc.next();
            if(thisString.equals("exit")) break;
            else {
                System.out.println(thisString);
                original += thisString.length();
                for(int i = 0; i < thisString.length(); i++){
                    int iNext = i+1;
                    StringBuilder repStr = new StringBuilder(thisString);
                    if(thisString.charAt(i) == '\\'){//found a slash
                        repStr.insert(i, '\\');
                        thisString = repStr.toString();
                        System.out.println(thisString);
                        i +=1;
                    }    
                    if(thisString.charAt(i) == '\"'){//
                        repStr.insert(i, '\\');
                        thisString = repStr.toString();
                        System.out.println(thisString);
                        i +=1;
                    }
                }
                lengthened += thisString.length()+2;
            }
        }
        System.out.println("The original size was " + original);
        System.out.println("the lengthened size is " + lengthened);
        System.out.println("The difference is " + (lengthened-original));
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner sc = new Scanner(System.in);
        Matchsticks ms = new Matchsticks();
        ms.lengthenStrings(sc);
        
    }
    
}
