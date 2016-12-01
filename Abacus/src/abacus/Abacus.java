/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abacus;

import java.util.*;
import java.io.*;
import org.json.*;

/**
 *
 * @author jkester
 */
public class Abacus {

    /**
     * @param args the command line arguments
     */
    
    int addition = 0;
    
    public void scanObject(JSONObject jobject){
        String[] keys = JSONObject.getNames(jobject);
        for(int i = 0; i < keys.length; i++){
            Object object = jobject.get(keys[i]);
            if(object instanceof String){
                String sObject = (String)object;
                if(sObject.equals("red"))return;
            }
        }
        
        for(int i = 0; i < keys.length; i++){
            Object object = jobject.get(keys[i]);
            if(object instanceof JSONArray){
                System.out.println("JSONArray object");
                scanArray((JSONArray)object);
            }
            else if(object instanceof JSONObject){
                System.out.println("JSONObject object");
                scanObject((JSONObject)object);
            }
            else if(object instanceof Integer){
                System.out.println("Integer object");
                addition += (Integer)object;
            }
            else if(object instanceof String){
                System.out.println("String object");
            }
        }
    }
    
    public void scanArray(JSONArray array){
        
        for(int i = 0; i < array.length(); i++){
            Object object = array.get(i);
            if(object instanceof JSONArray){
                System.out.println("JSONArray object");
                scanArray((JSONArray)object);
            }
            else if(object instanceof JSONObject){
                System.out.println("JSONObject object");
                scanObject((JSONObject)object);
            }
            else if(object instanceof Integer){
                System.out.println("Integer object");
                addition += (Integer)object;
            }
            else if(object instanceof String){
                System.out.println("String object");
            }
        }
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        Abacus abacus = new Abacus();
        System.out.println("give me your input");
        Scanner sc = new Scanner(System.in);
        String input = sc.next();
        JSONArray js = new JSONArray(input);
        System.out.println(js.length());
        abacus.scanArray(js);
        System.out.println(abacus.addition);
    }
    
}
