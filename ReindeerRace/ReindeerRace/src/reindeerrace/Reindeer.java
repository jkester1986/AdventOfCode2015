/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reindeerrace;

/**
 *
 * @author JKester
 */

public class Reindeer {
    public int speed;
    public int runTime;
    public int restTime;
    
    public String name;
    
    public boolean isResting = false;
    
    int points = 0;
    
    int restCount = 1;
    int runCount = 1;
    
    int distance = 0;
    
    public Reindeer(String reindeerName, int runSpeed, int runtime, int resttime){
        speed = runSpeed;
        runTime = runtime;
        restTime = resttime;
        
        name = reindeerName;
    }
    
    public int speed(){
        return speed;
    }
    
    public int runTime(){
        return runTime;
    }
    
    public int restTime(){
        return restTime;
    }
    
    public boolean isResting(){
        return isResting;
    }
    
    public void setResting(boolean restingState){
        isResting = restingState;
    }
    
    public int getPoints(){
        return points;
    }
    
    public void setPoints(int newpoints){
        points = newpoints;
    }
}
