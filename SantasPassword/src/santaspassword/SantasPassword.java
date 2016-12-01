/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package santaspassword;

/**
 *
 * @author jkester
 */
public class SantasPassword {

    /**
     * @param args the command line arguments
     */
    
    public String newPW(String old, int currIndex){
        char currentChar = old.charAt(currIndex);
        int currCharVal = (int)currentChar;
        
        StringBuilder newPass = new StringBuilder(old);
        
        if(currentChar == 'z'){//if the current letter is z
            newPass.setCharAt(currIndex, 'a');//change it to a
            if(currIndex != 0){//as long as the previous letter isn't 0
                if(newPass.charAt(currIndex - 1) == 'z'){//if it is z
                    return newPW(newPass.toString(), currIndex-1);
                }
                else{//if it's not z, just increase
                    char prevChar = newPass.charAt(currIndex-1);
                    prevChar++;
                    newPass.setCharAt(currIndex-1, prevChar);
                    //return newPass.toString();
                }
                //System.out.println(newPass.toString());
            }
            else {
                //System.out.println(newPass.toString());
            }
        }
        else {//current letter is a lower letter than z
            currCharVal++;
            newPass.setCharAt(currIndex, (char)currCharVal);
            //System.out.println(newPass.toString());
        }
        return newPass.toString();
    }
    private Boolean checkCases(String newPass){
        Boolean case1 = hasStraight(newPass.toString());//does it have a straight of 3 letters?
        Boolean case2 = newPass.matches(".*[i|o|l].*");//true if word contains i, o, l
        Boolean case3 = newPass.matches(".*(.)\\1.*(?!\\1)(.)\\2.*");
        
        if(case1 && !case2 && case3){//check if cases fit
            //System.out.println("There is a straight of 3 letters");
            
            return true;
        }
        else {
            /*
            System.out.println(newPass);
            if(!case1){
                System.out.println("Case1 fail");
            }
            if(case2){
                System.out.println("Case2 fail");
            }
            if(!case3){
                System.out.println("Case3 fail");
            }
                    */
            return false;
                   
        }
    }
    
    private Boolean hasStraight(String testCase){
        Boolean case1 = false;
        for(int i = 1; i < testCase.length()-1; i++){
            
            if(testCase.charAt(i-1)+1 == testCase.charAt(i) && testCase.charAt(i) == testCase.charAt(i+1)-1){
                //System.out.println("There is a straight of 3 letters");
                case1 = true;
            }
        }
        
        return case1;
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        SantasPassword sp = new SantasPassword();
        String oldP = "vzbxxyzz";
        //String oldP = "aaaaaa";
        System.out.println(oldP);
        String newPass = sp.newPW(oldP, oldP.length()-1);
        
        //sp.checkCases(newPass);
        while(!sp.checkCases(newPass)){
            newPass = sp.newPW(newPass, newPass.length()-1);
        }
        System.out.println(newPass);
    }
    
}