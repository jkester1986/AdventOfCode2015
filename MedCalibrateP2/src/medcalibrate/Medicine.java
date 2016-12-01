/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medcalibrate;

import java.util.*;

/**
 *
 * @author jkester
 */
public class Medicine {
    public String name;
    public ArrayList<String> conversions = new ArrayList<String>();
    
    public Medicine(String Medname, String conversion){
        name = Medname;
        conversions.add(conversion);
    }
    
    public void addConversion(String conversion){
        conversions.add(conversion);
    }
}
