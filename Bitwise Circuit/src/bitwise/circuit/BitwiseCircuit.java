/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bitwise.circuit;

import java.util.*;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;


/**
 *
 * @author jkester
 */
public class BitwiseCircuit {

    /**
     * @param args the command line arguments
     */
    
    Map<String, String> circuitPath = new HashMap<String, String>();
    
    private String setOperand(String operand){
        switch(operand){
            case "->":
                operand = "=";
                break;
            case "AND":
                operand = "&";
                break;
            case "OR":
                operand = "|";
                break;
            case "LSHIFT":
                operand = "<<";
                break;
            case "RSHIFT":
                operand = ">>";
                break;
            case "NOT":
                operand = "~";
                break;
        }
        return operand;
    }
    
    
    public void evaluateCircuitPath(Map<String, String> circuit) throws ScriptException{
        for (Map.Entry<String, String> entry : circuit.entrySet()) {
            //String key = entry.getKey();
            String value = entry.getValue();
            //System.out.println("the original string I'm evaluating is " + value);
            evaluateStrings(value);
            // ...
        }
    }
    
    public String evaluateStrings(String str) throws ScriptException{
        System.out.println("The string currently being evaluated is: " + str);
        String[] stringArr = str.split(" ");
        int inputLength = stringArr.length;
        //System.out.println(Arrays.toString(stringArr));
        
        if(str.equals(null)){
            String newStr= "";
            System.out.println("There was a problem. The array had no size.");
            evaluateCircuitPath(circuitPath);
            return str;
        }
        
        else if(inputLength == 1){//if there is only one item in the array, then it is a number
            System.out.println("String value found is " + stringArr[0]);
            return stringArr[0];
        }
        
        //value being directly inserted
        else if(inputLength == 3){
            
            Scanner checkInt = new Scanner(stringArr[0]);
                if(checkInt.hasNextInt()){
                    System.out.println("Result of insert = " +stringArr[0] + ", being stored at " + stringArr[2]);
                    circuitPath.put(stringArr[2], stringArr[0]);
                    return stringArr[0];
                }
                
                else {
                    //System.out.println("The new string I'm getting is " + circuitPath.get(stringArr[0]));
                    String finalString = evaluateStrings(circuitPath.get(stringArr[0]));
                    System.out.println("Result of insert = " + finalString);
                    circuitPath.put(stringArr[2], finalString);
                    return finalString;
                }  
                //System.out.println(circuitPath.entrySet());
        }
        
        //binary value being inverted (NOT)
        else if(inputLength == 4){
            Scanner checkInt = new Scanner(stringArr[1]);
            
            if(checkInt.hasNextInt()){
                int toBinary = Integer.parseInt(checkInt.next());
                String binaryString = Integer.toBinaryString(toBinary);//convert the value to binary
                //System.out.println(binaryString);
                if(binaryString.length() < 16){
                    int difference = 16 - binaryString.length();
                    String zeros = "";
                    for(int i = 0; i < difference; i++){//add a 0 until the string is 16 characters long
                        zeros += "0";
                    }
                    binaryString = zeros + binaryString;
                    //System.out.println(binaryString);
                }
                StringBuilder flipString = new StringBuilder(binaryString);
                for(int i = 0; i < binaryString.length(); i++){//flip the variables
                    if(binaryString.charAt(i) == '1'){
                        flipString.setCharAt(i, '0');
                    }
                    else flipString.setCharAt(i, '1');
                }
                //System.out.println(flipString);

                int base = 2;
                int result = Integer.parseInt(flipString.toString(), base);
                String finalString = Integer.toString(result);
                System.out.println("result of inversion = " + result);
                circuitPath.put(stringArr[3], finalString);
                return finalString;
            }
            else {//value to invert is a string
                String findingInt = evaluateStrings(circuitPath.get(stringArr[1]));
                int toBinary = Integer.parseInt(findingInt);
                String binaryString = Integer.toBinaryString(toBinary);//convert the value to binary
                //System.out.println(binaryString);
                if(binaryString.length() < 16){
                    int difference = 16 - binaryString.length();
                    String zeros = "";
                    for(int i = 0; i < difference; i++){//add a 0 until the string is 16 characters long
                        zeros += "0";
                    }
                    binaryString = zeros + binaryString;
                    //System.out.println(binaryString);
                }
                StringBuilder flipString = new StringBuilder(binaryString);
                for(int i = 0; i < binaryString.length(); i++){//flip the variables
                    if(binaryString.charAt(i) == '1'){
                        flipString.setCharAt(i, '0');
                    }
                    else flipString.setCharAt(i, '1');
                }
                //System.out.println(flipString);

                int base = 2;
                int result = Integer.parseInt(flipString.toString(), base);
                String finalString = Integer.toString(result);
                System.out.println("result of inversion = " + result);
                circuitPath.put(stringArr[3], finalString);
                return finalString;
            }
            //System.out.println(circuitPath.entrySet());
            
        }
        
        else if (inputLength == 5){
            Scanner checkInt1 = new Scanner(stringArr[0]);
            Scanner checkInt2 = new Scanner(stringArr[2]);
            
            if(checkInt1.hasNextInt() && checkInt2.hasNextInt()){
                int firstInt = checkInt1.nextInt();
                int secondInt = checkInt2.nextInt();
                ScriptEngineManager manager = new ScriptEngineManager();
                ScriptEngine engine = manager.getEngineByName( "js" );
                String finalString = engine.eval(firstInt + stringArr[1] + secondInt).toString();
                System.out.println("Result of " + firstInt + stringArr[1] + secondInt + " is " + finalString + " being stored at " + stringArr[4]);
                circuitPath.put(stringArr[4], finalString);
                return finalString;
                
            }
            else{
                //
                String findFirstInt = "";
                String findSecondInt = "";  
                
                if(checkInt1.hasNextInt()){
                    findFirstInt = checkInt1.next();
                }
                else{
                    System.out.println("Next string1 I'm looking for is at "+ stringArr[0] + " :" + circuitPath.get(stringArr[0]));
                    findFirstInt = evaluateStrings(circuitPath.get(stringArr[0]));
                }
                System.out.println("findFirstInt = " + findFirstInt);
                if(checkInt2.hasNextInt()){
                    findSecondInt = checkInt2.next();
                }
                else{
                    System.out.println("Next string2 I'm looking for is at "+ stringArr[2] + " :" + circuitPath.get(stringArr[2]));
                    findSecondInt = evaluateStrings(circuitPath.get(stringArr[2]));
                }
                System.out.println("findSecondInt = " + findSecondInt);
                
                
                int firstInt = Integer.parseInt(findFirstInt);
                int secondInt = Integer.parseInt(findSecondInt);
                ScriptEngineManager manager = new ScriptEngineManager();
                ScriptEngine engine = manager.getEngineByName( "js" );
                String finalString = engine.eval(firstInt + stringArr[1] + secondInt).toString();
                System.out.println("Result of " + firstInt + stringArr[1] + secondInt + " is " + finalString + " being stored at " + stringArr[4]);
                circuitPath.put(stringArr[4], finalString);
                System.out.println("The value at stringArr[4] is " + circuitPath.get(stringArr[4]));
                return finalString;
            }
            //System.out.println(circuitPath.entrySet());
        }
        
        return str;
    }
    
    public void evaluateCircuit(Scanner sc) throws ScriptException{
        while(sc.hasNext()){
            String line = sc.nextLine();
            if(line.equals("exit")) break;
            else{
                String[] directions = line.split(" ");
                if(directions.length == 3){//length of 3, directly set
                    String operand = setOperand(directions[1]);
                    directions[1] = operand;
                    Scanner checkInt1 = new Scanner(directions[0]);
                    if(checkInt1.hasNextInt()){
                        circuitPath.put(directions[2], directions[0]);
                        System.out.println("The value being stored at " + directions[2] + " is " + circuitPath.get(directions[2]));
                        //System.out.println(circuitPath.get(directions[2]));
                    }
                    else {
                        circuitPath.putIfAbsent(directions[0], "0");
                        circuitPath.put(directions[2], line);
                    }
                    
                }
                else if(directions.length == 4){//cases with NOT
                    String operand1 = setOperand(directions[0]);
                    directions[0] = operand1;
                    String operand2 = setOperand(directions[2]);
                    directions[2] = operand2;
                    
                    Scanner checkInt1 = new Scanner(directions[1]);
                    if(checkInt1.hasNextInt()){
                        int toBinary = Integer.parseInt(checkInt1.next());
                        String binaryString = Integer.toBinaryString(toBinary);//convert the value to binary
                        //System.out.println(binaryString);
                        if(binaryString.length() < 16){
                            int difference = 16 - binaryString.length();
                            String zeros = "";
                            for(int i = 0; i < difference; i++){//add a 0 until the string is 16 characters long
                                zeros += "0";
                            }
                            binaryString = zeros + binaryString;
                            System.out.println(binaryString);
                        }
                        StringBuilder flipString = new StringBuilder(binaryString);
                        for(int i = 0; i < binaryString.length(); i++){//flip the variables
                            if(binaryString.charAt(i) == '1'){
                                flipString.setCharAt(i, '0');
                            }
                            else flipString.setCharAt(i, '1');
                        }
                        System.out.println(flipString);
                        
                        int base = 2;
                        int result = Integer.parseInt(flipString.toString(), base);
                        String finalString = Integer.toString(result);
                        //System.out.println(result);
                        circuitPath.put(directions[3], finalString);
                        
                        //System.out.println(circuitPath.get(directions[2]));
                    }
                    
                    else{
                        String full = "";
                        for(int i = 0; i < directions.length; i++){
                            full += directions[i] + " ";
                        }
                        circuitPath.putIfAbsent(directions[1], "0");
                        circuitPath.put(directions[3], full);
                    }
                }
                
                else if(directions.length == 5){
                    String operand1 = setOperand(directions[1]);
                    directions[1] = operand1;
                    String operand2 = setOperand(directions[3]);
                    directions[3] = operand2;
                    
                    
                    Scanner checkInt1 = new Scanner(directions[0]);
                    Scanner checkInt2 = new Scanner(directions[2]);
                    if(checkInt1.hasNextInt() && checkInt2.hasNextInt()){
                        int value1 = Integer.parseInt(directions[0]);
                        int value2 = Integer.parseInt(directions[2]);
                        
                        ScriptEngineManager manager = new ScriptEngineManager();
                        ScriptEngine engine = manager.getEngineByName( "js" );
                        
                        String finalValue = engine.eval(value1 + operand1 + value2).toString();
                        System.out.println(finalValue);
                        
                        circuitPath.put(directions[4], finalValue);
                        
                        //System.out.println(circuitPath.get(directions[2]));
                    }
                    else{
                        String full = "";
                        for(int i = 0; i < directions.length; i++){
                            full += directions[i] + " ";
                        }
                        
                        circuitPath.putIfAbsent(directions[0], "0");
                        circuitPath.putIfAbsent(directions[2], "0");
                        circuitPath.put(directions[4], full);
                    }
                }
                
            }
        }
    }
    
    public static void main(String[] args) throws ScriptException {
        // TODO code application logic here
        BitwiseCircuit bitCir = new BitwiseCircuit();
        
        Scanner sc = new Scanner(System.in);
        bitCir.evaluateCircuit(sc);
       
        bitCir.evaluateCircuitPath(bitCir.circuitPath);
        System.out.println(bitCir.circuitPath.entrySet());
        
        System.out.println("value at a: " + bitCir.circuitPath.get("a"));
        //System.out.println(~0b0000000001111011);
    }
    
}
