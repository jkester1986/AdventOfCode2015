/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elvessay;

/**
 *
 * @author jkester
 */
public class ElvesSay {

    /**
     * @param args the command line arguments
     */
    public int countLength(String text){
        int length = 0;
        int numCount = 0;
        char prevLetter = 'a';
        StringBuilder builder = new StringBuilder();
        
        int j = 0;
        while (j < 50){
            for (int i = 0; i < text.length(); i++){
                if(i == 0){
                    numCount ++;
                    prevLetter = text.charAt(i);
                }
                else if (text.charAt(i) == prevLetter){
                    numCount++;
                }
                else if (i == text.length()-1){
                    if (text.charAt(i) == prevLetter){
                        numCount++;
                        builder.append(Integer.toString(numCount) + prevLetter);
                        prevLetter = 'a';
                        numCount = 0;
                    }
                    else{
                        builder.append(Integer.toString(numCount) + prevLetter);
                        builder.append(Integer.toString(1) + text.charAt(i));
                        prevLetter = 'a';
                        numCount = 0;
                    }
                }
                else{
                    
                    builder.append(Integer.toString(numCount) + prevLetter);
                    prevLetter = text.charAt(i);
                    numCount = 1;
                    
                }
                
            }
            //System.out.println(builder);
            text = builder.toString();
            builder = new StringBuilder();
            j++;
            System.out.println("total iterations: " + j);
        }
        length = text.length();
        
        return length;
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        ElvesSay elves = new ElvesSay();
        System.out.println(elves.countLength("1113222113"));
    }
    
}
