/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conwayslights;

import java.util.*;

/**
 *
 * @author jkester
 */
public class ConwaysLights {

    /**
     * @param args the command line arguments
     */
    boolean[][] lights = new boolean[100][100];
    
    public void setLights(Scanner sc){
        for(int j = 0; j < lights.length; j++){
            String row = sc.next();
            for(int i = 0; i < row.length(); i++){
                if(row.charAt(i) == '.'){
                    lights[j][i] = false;
                }
                else{
                    lights[j][i] = true;
                }
            }
            //System.out.println(Arrays.toString(lights[j]));
        }
    }
    
    public void changeLights(int numSteps){
        
        for(int step = 0; step < numSteps; step++){
            boolean[][] lightsNext = new boolean[100][100];
            int touching = 0;
            
            for(int row = 0; row < lights.length; row++){
                for(int light = 0; light < 100; light++){
                    int lastRow = 0;
                    boolean getLast = false;
                    
                    int nextRow = 0;
                    boolean getNext = false;
                    
                    int prevLight = 0;
                    boolean getPrevLight = false;
                    
                    int nextLight = 0;
                    boolean getNextLight = false;
                    
                    
                    if(row != 0){
                        lastRow = row-1;
                        getLast = true;
                    }
                    if(row != lights.length-1){
                        nextRow = row+1;
                        getNext = true;
                    }
                    if(light !=0){
                        prevLight = light-1;
                        getPrevLight = true;
                    }
                    if(light != lights.length-1){
                        nextLight = light+1;
                        getNextLight = true;
                    }
                    
                    
                    
                    if(getLast){
                        if(getPrevLight){
                            if(lights[lastRow][prevLight]) touching++;//top left
                        }
                        if(lights[lastRow][light]) touching++;//top middle
                        if(getNextLight){
                            if(lights[lastRow][nextLight]) touching++;//top right
                        }
                    }
                    if(getPrevLight){
                        if(lights[row][prevLight]) touching++;//left
                    }
                    if(getNextLight){
                        if(lights[row][nextLight]) touching++;//right
                    }
                    if(getNext){
                        if(getPrevLight){
                            if(lights[nextRow][prevLight]) touching++;//bottom left
                        }
                        if(lights[nextRow][light]) touching++;//bottom middle
                        if(getNextLight){
                            if(lights[nextRow][nextLight])touching++;//bottom right
                        }
                    }
                    
                    if(row == 0 && light == 4) System.out.println(touching);
                    
                    if(lights[row][light]){//the light is on
                        if (touching == 2 || touching == 3){
                            //if touching 2 or 3 lights that are on, stay
                            lightsNext[row][light] = true;
                        }
                        else {
                            lightsNext[row][light] = false;
                            //System.out.println("The light is turning off");
                        }//otherwise turn it off;
                    }
                    else{//the light is off
                        if(touching == 3){
                            lightsNext[row][light] = true;//if touching 3 lights, turn on
                        }
                        else{
                            //otherwise, lights stay off
                            lightsNext[row][light] = false;
                        }
                    }
                    
                    
                    touching = 0;
                }
            }
            lightsNext[0][0]=true;
            lightsNext[0][99]=true;
            lightsNext[99][0]=true;
            lightsNext[99][99]=true;
            lights = lightsNext;
            printLights();
        }
    }
    
    
    public int lightsCount(){
        int count = 0;
        for(int row = 0; row < 100; row++){
            for(int column = 0; column < 100; column++){
                if(lights[row][column]) count++;
            }
        }
        return count;
    }
    
    public void printLights(){
        System.out.println("");
        for(int row = 0; row < 100; row++){
            for(int column = 0; column < 100; column++){
                if(lights[row][column]){
                    System.out.print("#");
                }
                else System.out.print(".");
            }
            System.out.println("");
        }
    }
    
    
    public static void main(String[] args) {
        // TODO code application logic here
        ConwaysLights cs = new ConwaysLights();
        Scanner sc = new Scanner(System.in);
        System.out.println("Please give me the light input");
        cs.setLights(sc);
        cs.changeLights(100);
        
        System.out.println("The total lights on are: " + cs.lightsCount());
    }
    
}
