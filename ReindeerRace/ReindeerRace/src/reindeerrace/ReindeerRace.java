/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reindeerrace;

/**
 *
 * @author jkester
 */
public class ReindeerRace {

    /**
     * @param args the command line arguments
     */
    
    
    public int getDistance(int speed, int runTime, int restTime, int time){
        int currentTime = 0;
        int distance = 0;
        while(currentTime < time){
            int timeLeft = time-currentTime;
            //System.out.println("The time left is: " + timeLeft);
            if(timeLeft >= runTime){
                distance += runTime*speed;
                //System.out.println(runTime*speed);
            }
            else{
                distance += timeLeft*speed;
                //System.out.println(timeLeft*speed);
            }
            currentTime += runTime + restTime;
            //System.out.println("The currentTime = " + currentTime);
        }
        return distance;
    }
    
    public void getPoints(Reindeer[] reindeer, int time){
        for(int i = 1; i < time; i++){//every second
            System.out.println("We are on second number " + i);
            int distance = 0;
            int greatestDistanceIndex = 0;
            for(int j = 0; j < reindeer.length; j++){//for every reindeer
                if(reindeer[j].isResting){
                    System.out.println("REST COUNT IS " + reindeer[j].restCount);
                    System.out.println(reindeer[j].name + " is resting");
                    if(reindeer[j].restCount < reindeer[j].restTime){
                        reindeer[j].restCount++; 
                        
                    }
                    else if(reindeer[j].restTime == reindeer[j].restCount){
                        reindeer[j].restCount = 1;
                        System.out.println(reindeer[j].name + " is about to run");
                        reindeer[j].isResting = false;
                    }
                    else{
                        System.out.println("rest count and rest time are not equal");
                    }
                }
                else{
                    System.out.println("run count is " + reindeer[j].runCount);
                    if(reindeer[j].runCount < reindeer[j].runTime){
                        reindeer[j].runCount++;
                        reindeer[j].distance += reindeer[j].speed;
                        System.out.println(reindeer[j].name + " has run " + reindeer[j].distance);
                    }
                    else if(reindeer[j].runCount == reindeer[j].runTime){
                        reindeer[j].runCount = 1;
                        reindeer[j].distance += reindeer[j].speed;
                        reindeer[j].isResting = true;
                        System.out.println(reindeer[j].name + " is about to rest");
                    }
                }
                System.out.println(reindeer[j].distance + " is " + reindeer[j].name + "'s current distance");
                if(reindeer[j].distance > distance){
                    distance = reindeer[j].distance;
                    greatestDistanceIndex = j;
                }
            }
            reindeer[greatestDistanceIndex].points += 1;
            System.out.println(reindeer[greatestDistanceIndex].name + " was awarded a point!");
        }
    }
    
    public int mostPoints(Reindeer[] reindeer){
        int greatestDistance = 0;
        for (int i = 0; i < reindeer.length; i++){
            System.out.println(reindeer[i].name + "'s points are: " + reindeer[i].getPoints());
            if(reindeer[i].getPoints() > greatestDistance){
                
                greatestDistance = reindeer[i].getPoints();
            }
        }
        return greatestDistance;
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        ReindeerRace rr = new ReindeerRace();
        Reindeer rudolph = new Reindeer("Rudolph", 22, 8, 165);
        Reindeer cupid = new Reindeer("Cupid", 8, 17, 114);
        Reindeer prancer = new Reindeer("Prancer", 18, 6, 103);
        Reindeer donner = new Reindeer("Donner", 25, 6, 145);
        Reindeer dasher = new Reindeer("Dasher", 11, 12, 125);
        Reindeer comet = new Reindeer("Comet", 21,6,121);
        Reindeer blitzen = new Reindeer("Blitzen", 18, 3, 50);
        Reindeer vixen = new Reindeer("Vixen", 20, 4, 75);
        Reindeer dancer = new Reindeer("Dancer", 7, 20, 119);
        
        Reindeer[] reindeer = {rudolph, cupid, prancer, donner, dasher, comet, blitzen, vixen, dancer};
        //Reindeer[] reindeer = {cupid};
        rr.getPoints(reindeer, 2503);
        System.out.println(rr.mostPoints(reindeer));
    }
    
}
