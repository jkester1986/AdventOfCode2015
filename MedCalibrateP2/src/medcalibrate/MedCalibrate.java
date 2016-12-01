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
    int count = 0;
    /**
     * @param args the command line arguments
     */
    
    
    public void setMedicines(Scanner sc){
        while(sc.hasNextLine()){
            String next = sc.nextLine();
            if(!next.equals("exit")){
                String[] splitLine = next.split(" ");
                    if(medicines.size() == 0){
                        System.out.println(splitLine[2] + " " + splitLine[0]);
                        System.out.println("initial add");
                        medicines.add(new Medicine(splitLine[2], splitLine[0]));
                    }
                    else{
                        System.out.println(splitLine[2] + " " + splitLine[0]);
                        boolean set = false;
                        
                        for(int j = 0; j < medicines.size(); j++){
                            //System.out.println("Medicine name at this index is: " + medicines.get(j).name);
                            if(medicines.get(j).name.equals(splitLine[2])){
                                //System.out.println("adding new conversion");
                                Medicine thisMed = medicines.get(j);
                                thisMed.addConversion(splitLine[0]);
                                medicines.set(j, thisMed);
                                set = true;
                            }
                            
                        }
                        if(set){
                            
                        }
                        else{
                            //System.out.println("adding new medicine");
                            medicines.add(new Medicine(splitLine[2], splitLine[0]));
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
        String[] moleculeArr = string.split("((?<=ThF)|(?=ThF))"
                + "|((?<=ThRnFAr)|(?=ThRnFAr))"
                + "|((?<=BCa)|(?=BCa))"
                + "|((?<=TiB)|(?=TiB))"
                + "|((?<=TiRnFAr)|(?=TiRnFAr))"
                + "|((?<=CaCa)|(?=CaCa))"
                + "|((?<=PB)|(?=PB))"
                + "|((?<=PRnFAr)|(?=PRnFAr))"
                + "|((?<=SiRnFYFAr)|(?=SiRnFYFAr))"
                + "|((?<=SiRnMgAr)|(?=SiRnMgAr))"
                + "|((?<=SiTh)|(?=SiTh))"
                + "|((?<=CaF)|(?=CaF))"
                + "|((?<=PMg)|(?=PMg))"
                + "|((?<=SiAl)|(?=SiAl))"
                + "|((?<=CRnAlAr)|(?=CRnAlAr))"
                + "|((?<=CRnFYFYFAr)|(?=CRnFYFYFAr))"
                + "|((?<=CRnFYMgAr)|(?=CRnFYMgAr))"
                + "|((?<=CRnMgYFAr)|(?=CRnMgYFAr))"
                + "|((?<=HCa)|(?=HCa))"
                + "|((?<=NRnFYFAr)|(?=NRnFYFAr))"
                + "|((?<=NRnMgAr)|(?=NRnMgAr))"
                + "|((?<=NTh)|(?=NTh))"
                + "|((?<=OB)|(?=OB))"
                + "|((?<=ORnFAr)|(?=ORnFAr))"
                + "|((?<=BF)|(?=BF))"
                + "|((?<=TiMg)|(?=TiMg))"
                + "|((?<=CRnFAr)|(?=CRnFAr))"
                + "|((?<=HSi)|(?=HSi))"
                + "|((?<=CRnFYFAr)|(?=CRnFYFAr))"
                + "|((?<=CRnMgAr)|(?=CRnMgAr))"
                + "|((?<=HP)|(?=HP))"
                + "|((?<=NRnFAr)|(?=NRnFAr))"
                + "|((?<=OTi)|(?=OTi))"
                + "|((?<=CaP)|(?=CaP))"
                + "|((?<=PTi)|(?=PTi))"
                + "|((?<=SiRnFAr)|(?=SiRnFAr))"
                + "|((?<=CaSi)|(?=CaSi))"
                + "|((?<=ThCa)|(?=ThCa))"
                + "|((?<=BP)|(?=BP))"
                + "|((?<=TiTi)|(?=TiTi))"
                + "|((?<=HF)|(?=HF))"
                + "|((?<=NAl)|(?=NAl))"
                + "|((?<=OMg)|(?=OMg))");
        System.out.println(Arrays.toString(moleculeArr));
        return moleculeArr;
    }
    
    
    public void orderMolecules(){
        ArrayList<Medicine> orderedMeds = new ArrayList<>();
        
        for(Medicine medicine : medicines){
            if(orderedMeds.isEmpty()){
                orderedMeds.add(medicine);
            }
            else{
                
                for(int index = 0; index < orderedMeds.size(); index++){
                    if(medicine.name.length() > orderedMeds.get(index).name.length()){
                        orderedMeds.add(index, medicine);
                        break;
                    }
                    else if (index+1 == orderedMeds.size()) {
                        orderedMeds.add(medicine);
                        break;
                    }
                }
            }
            
        }
        medicines = orderedMeds;
        for(Medicine medicine : medicines){
            System.out.println(medicine.name);
        }
    }
    
    public String reduceString(String string){
        for(Medicine medicine : medicines){
            if(string.contains(medicine.name)){
                //String[] split = string.split(medicine.name);
                String[] split = string.split("((?<=" + medicine.name + ")|(?=" + medicine.name +"))");
                System.out.println(Arrays.toString(split));
                for(int index = 0; index < split.length; index++){
                    if(split[index].equals(medicine.name)){
                        split[index] = medicine.conversions.get(0);
                        count++;
                    }
                }
                string = "";
                for(String splitString : split){
                    string += splitString;
                }
                System.out.println("New string is: " + string);
            }
            
        }
       return string;
    }
    
    public void removeDupes(){
        allCombinations = new ArrayList<>(new LinkedHashSet<>(allCombinations));
    }

    public static void main(String[] args) {
        // TODO code application logic here
        MedCalibrate mc = new MedCalibrate();
        Scanner sc = new Scanner(System.in);
        System.out.println("What is your medicine conversion input?");
        mc.setMedicines(sc);
        mc.orderMolecules();
        
        System.out.println(mc.medicines.size());
        String medString = "CRnSiRnCaPTiMgYCaPTiRnFArSiThFArCaSiThSiThPBCaCaSiRnSiRnTiTiMgArPBCaPMgYPTiRnFArFArCaSiRnBPMgArPRnCaPTiRnFArCaSiThCaCaFArPBCaCaPTiTiRnFArCaSiRnSiAlYSiThRnFArArCaSiRnBFArCaCaSiRnSiThCaCaCaFYCaPTiBCaSiThCaSiThPMgArSiRnCaPBFYCaCaFArCaCaCaCaSiThCaSiRnPRnFArPBSiThPRnFArSiRnMgArCaFYFArCaSiRnSiAlArTiTiTiTiTiTiTiRnPMgArPTiTiTiBSiRnSiAlArTiTiRnPMgArCaFYBPBPTiRnSiRnMgArSiThCaFArCaSiThFArPRnFArCaSiRnTiBSiThSiRnSiAlYCaFArPRnFArSiThCaFArCaCaSiThCaCaCaSiRnPRnCaFArFYPMgArCaPBCaPBSiRnFYPBCaFArCaSiAl";
        while(!medString.equals("e")){
            medString = mc.reduceString(medString);
        }
        System.out.println("count is : " + mc.count);
        

    }
    
}
