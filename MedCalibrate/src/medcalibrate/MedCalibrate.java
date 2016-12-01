/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medcalibrate;

import java.util.*;
import org.paukov.combinatorics.*;

/**
 *
 * @author jkester
 */



public class MedCalibrate {

    ArrayList<Medicine> medicines = new ArrayList<Medicine>();
    ArrayList<String> allCombinations = new ArrayList<String>();
    /**
     * @param args the command line arguments
     */
    public void setMedicines(Scanner sc){
        while(sc.hasNextLine()){
            String next = sc.nextLine();
            if(!next.equals("exit")){
                String[] splitLine = next.split(" ");
                    if(medicines.size() == 0){
                        System.out.println(splitLine[0] + " " + splitLine[2]);
                        System.out.println("initial add");
                        medicines.add(new Medicine(splitLine[0], splitLine[2]));
                    }
                    else{
                        System.out.println(splitLine[0] + " " + splitLine[2]);
                        boolean set = false;
                        
                        for(int j = 0; j < medicines.size(); j++){
                            //System.out.println("Medicine name at this index is: " + medicines.get(j).name);
                            if(medicines.get(j).name.equals(splitLine[0])){
                                //System.out.println("adding new conversion");
                                Medicine thisMed = medicines.get(j);
                                thisMed.addConversion(splitLine[2]);
                                medicines.set(j, thisMed);
                                set = true;
                            }
                            
                        }
                        if(set){
                            
                        }
                        else{
                            //System.out.println("adding new medicine");
                            medicines.add(new Medicine(splitLine[0], splitLine[2]));
                        }
                    }
            }
            else break;
            
        }
        System.out.println("The medicines are: ");
        for(Medicine medicine : medicines){
            System.out.println(medicine.name + medicine.conversions);
        }
    }
    
    public String eliminateExtra(String string){
        StringBuilder newString = new StringBuilder();
        for(int i = 0; i < string.length(); i++){
            switch (string.charAt(i)){
                case 'A':
                    if(i+1 != string.length()){
                        if(string.charAt(i+1) == 'l'){
                            newString.append("Al ");
                            i++;
                        }
                    }
                    break;
                case 'B':
                    newString.append("B ");
                    break;
                case 'C':
                    if(i+1 != string.length()){
                        if(string.charAt(i+1) == 'a'){
                            newString.append("Ca ");
                            i++;
                        }
                    }
                    break;
                case 'F':
                    newString.append("F ");
                    break;
                case 'H':
                    newString.append("H ");
                    break;
                case 'M':
                    if(i+1 != string.length()){
                        if(string.charAt(i+1) == 'g'){
                            newString.append("Mg ");
                            i++;
                        }
                    }
                    break;
                case 'N':
                    newString.append("N ");
                    break;
                case 'O':
                    newString.append("O ");
                    break;
                case 'P':
                    newString.append("P ");
                    break;
                case 'S':
                    if(i+1 != string.length()){
                        if(string.charAt(i+1) == 'i'){
                            newString.append("Si ");
                            i++;
                        }
                    }
                    break;
                case 'T':
                    if(i+1 != string.length()){
                        if(string.charAt(i+1) == 'h'){
                            newString.append("Th ");
                            i++;
                        }
                        else if(string.charAt(i+1) == 'i'){
                            newString.append("Ti ");
                            i++;
                        }
                    }
                    break;
                case 'e':
                    newString.append("e ");
                    break;
                default:
                    break;
            }
        }
        return newString.toString();
    }
    
    public String[] splitString(String string){
        String[] moleculeArr = string.split("((?<=Al)|(?=Al))"
                + "|((?<=B)|(?=B))"
                + "|((?<=Ca)|(?=Ca))"
                + "|((?<=F)|(?=F))"
                + "|((?<=H)|(?=H))"
                + "|((?<=Mg)|(?=Mg))"
                + "|((?<=N)|(?=N))"
                + "|((?<=O)|(?=O))"
                + "|((?<=P)|(?=P))"
                + "|((?<=Si)|(?=Si))"
                + "|((?<=Th)|(?=Th))"
                + "|((?<=Ti)|(?=Ti))"
                + "|((?<=e)|(?=e))");
        System.out.println(Arrays.toString(moleculeArr));
        return moleculeArr;
    }
    
    public void getCombos(String[] moleculeArr){
        
        for(int i = 0; i < moleculeArr.length; i++){//for every molecule in the array
                StringBuilder newString = new StringBuilder();
                ArrayList<String> tempStrings = new ArrayList<String>();
                ArrayList<String> tempStringsV2 = new ArrayList<String>();
                ArrayList<String> conversions = new ArrayList<String>();
                boolean hasConversion = false;
                for(int j = 0; j < medicines.size(); j++){
                    if(medicines.get(j).name.equals(moleculeArr[i])){
                        conversions = medicines.get(j).conversions;
                        hasConversion = true;
                    }
                }
                
                if(hasConversion){
                    //System.out.println("WE ARE ON CONVERSION " + moleculeArr[i]);
                    for(int i2 = 0; i2 < i; i2++){//append any strings before the one we want
                        newString.append(moleculeArr[i2]);
                        //System.out.println("I am appending");
                       // System.out.println(newString);
                    }

                    if(newString.length() != 0){//if the initial length isn't 0, add newString to ArrayList tempStrings
                        tempStrings.add(newString.toString());
                        //System.out.println("Adding new String");
                    }



                    if(tempStrings.isEmpty()){//if tempStrings is = 0 (aka looking at index 0 for conversion)
                        for(String conversion : conversions){
                            tempStringsV2.add(conversion);//add the conversions as their own string
                            //System.out.println("Initial conversion happening");
                            //System.out.println(tempStringsV2);
                        }
                    }
                    else{//otherwise, add every conversion to the end of each string in tempStrings
                        for(String temp : tempStrings){
                            for(String conversion : conversions){//create new strings
                                //System.out.println("conversion is: " + conversion);
                                String thisNewTemp = temp + conversion;
                                //temp = newTemp.toString();
                                tempStringsV2.add(thisNewTemp);
                                //System.out.println("I am converting");
                                //System.out.println(thisNewTemp);
                            }
                            //System.out.println("");
                        }
                    }



                    tempStrings = tempStringsV2;
                    tempStringsV2 = new ArrayList<String>();

                    //System.out.println("WE HAVE ADDED THE CONVERSIONS! current strings are: " + tempStrings);

                    for(int i2 = i+1; i2 < moleculeArr.length; i2++){//append all strings after the index we are looking at
                        //System.out.println("i2 value is: " + moleculeArr[i2]);
                        for(String temp : tempStrings){
                            //System.out.println(temp);

                            StringBuilder newTemp = new StringBuilder(temp + moleculeArr[i2]);
                            temp = newTemp.toString();
                            tempStringsV2.add(temp);


                        }
                        //System.out.println("tempStrings = " + tempStringsV2);
                        //System.out.println("");
                        tempStrings = tempStringsV2;
                        tempStringsV2 = new ArrayList<String>();

                    }
                     //System.out.println("FINISHED WITH CONVERSION " + moleculeArr[i]);

                    //finally should be finished building all strings

                    for(String temp : tempStrings){
                        allCombinations.add(temp);
                    }

                    //System.out.println("here are all the conversions:");
                    for(String conversion : allCombinations){
                        //System.out.println(conversion);
                    }
                }
                
                
                //System.out.println("");
            //}
        }
    }
    
    public void removeDupes(){
        
        allCombinations = new ArrayList<String>(new LinkedHashSet<String>(allCombinations));
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        MedCalibrate mc = new MedCalibrate();
        Scanner sc = new Scanner(System.in);
        System.out.println("What is your medicine conversion input?");
        //System.out.println(mc.medicines.size());
        mc.setMedicines(sc);
        
        System.out.println(mc.medicines.size());
        System.out.println("What is the medicine string you'd like to add?");
        String medString = sc.next();
        //String betterMedString = mc.eliminateExtra(medString);
        
        String[] moleculeArr = mc.splitString(medString);
        
        mc.getCombos(moleculeArr);
        mc.removeDupes();
        System.out.println(mc.allCombinations.size());
        for(String conversions: mc.allCombinations){
            System.out.println(conversions);
        }

    }
    
}
