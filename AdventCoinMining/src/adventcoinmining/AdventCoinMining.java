/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adventcoinmining;
import java.util.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author jkester
 */
public class AdventCoinMining {

    /**
     * @param args the command line arguments
     */
    public String lowestNumber(String input) throws NoSuchAlgorithmException{
        String sKey = input;
        String hashString = "";
        boolean tooLow = true;
        long lowNumber = 0;
        String combined = "";
        
        
       while(tooLow){
            combined = input + lowNumber;
            
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(combined.getBytes(),0,combined.length());
            byte[] mdbytes = md.digest();
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < mdbytes.length; i++) {
              sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            hashString = sb.toString();
            if(hashString.charAt(0) == '0' && 
                    hashString.charAt(1) == '0' &&
                    hashString.charAt(2) == '0' &&
                    hashString.charAt(3) == '0' &&
                    hashString.charAt(4) == '0' &&
                    hashString.charAt(5) == '0'){
                System.out.println(combined);
                tooLow = false;
            }
            else lowNumber ++;
        }
        return hashString;
    }
    
    public static void main(String[] args) throws NoSuchAlgorithmException {
        // TODO code application logic here
        AdventCoinMining aCM = new AdventCoinMining();
        String testString = "bgvyzdsv";
        System.out.println(aCM.lowestNumber(testString));
    }
    
}
