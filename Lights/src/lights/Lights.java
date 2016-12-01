/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lights;
import java.awt.Point;
import java.util.*;

/**
 *
 * @author jkester
 */
public class Lights {

    /**
     * @param args the command line arguments
     */
    ArrayList<ArrayList<Object>> grid = new ArrayList<ArrayList<Object>>(1000000);
    
    public void setupLights(int size){
        for(int x = 0; x < size; x++){
            for(int y = 0; y < size; y++){
                ArrayList<Object> light = new ArrayList<Object>();
                Point location = new Point(x, y);
                boolean on = false;
                light.add(location);
                light.add(on);
                grid.add(light);
            }
        }
    
    }
    
    public void turnOnLights(Point start, Point stop){
        //System.out.println("I'm going to start turning on lights now");
       int xstart = (int)start.getX();
       int xstop = (int)stop.getX();
       
       int ystart = (int)start.getY();
       int ystop = (int)stop.getY();
       
       outerloop:
       for(int x = xstart; x <= xstop; x++){
           for(int y = ystart; y <= ystop; y++){
               int index = x*1000+y;
               ArrayList<Object> gridpoint = grid.get(index);
               Point point = new Point(x,y);
               
               //System.out.println("gridpoint.get(0) = " + gridpoint.get(0) + "and the point = " + point);
               
               if(gridpoint.get(0).equals(point)) {
                   grid.get(index).set(1, true);
               }
               else{
                   System.out.println("The points don't match!");
                   break outerloop;
               }
           }
       }
    }
    
    public void turnOffLights(Point start, Point stop){
       //System.out.println("I'm going to turn off lights now");
       int xstart = (int)start.getX();
       int xstop = (int)stop.getX();
       
       int ystart = (int)start.getY();
       int ystop = (int)stop.getY();
       
       outerloop:
       for(int x = xstart; x <= xstop; x++){
           for(int y = ystart; y <= ystop; y++){
               int index = x*1000+y;
               ArrayList<Object> gridpoint = grid.get(index);
               Point point = new Point(x,y);
               
               if(gridpoint.get(0).equals(point)) {
                   grid.get(index).set(1, false);
               }
               else{
                   System.out.println("The points don't match!");
                   break outerloop;
               }
           }
       }
    }
    
    public void toggleLights(Point start, Point stop){
        //System.out.println("I'm going to toggle lights now");
       int xstart = (int)start.getX();
       int xstop = (int)stop.getX();
       
       int ystart = (int)start.getY();
       int ystop = (int)stop.getY();
       
        outerloop:
            for(int x = xstart; x <= xstop; x++){
               for(int y = ystart; y <= ystop; y++){
               int index = x*1000+y;
               ArrayList<Object> gridpoint = grid.get(index);
               Point point = new Point(x,y);
               
               if(gridpoint.get(0).equals(point)) {
                   if((Boolean)gridpoint.get(1)){
                       //System.out.println("This light is on, turning it off");
                       grid.get(index).set(1, false);
                   }
                   else grid.get(index).set(1, true);
               }
               else{
                   System.out.println("The points don't match!");
                   break outerloop;
               }
           }
       }
    }
    
    private Point getPoint(String points){
        String[] splitPoints = points.split(",");
        int x = Integer.parseInt(splitPoints[0]);
        int y = Integer.parseInt(splitPoints[1]);
        
        Point point = new Point(x,y);
        return point;
    }
    
    public void executeInput(Scanner sc){
        Point start = new Point(0,0);
        Point stop = new Point  (0, 0);
        
        while(sc.hasNext()){
            String next = sc.next();
            
            if(next.equals("exit")) break;
            else {
                if(next.equals("toggle")){
                    start = getPoint(sc.next());
                    sc.next();//skip "through"
                    stop = getPoint(sc.next());
                    toggleLights(start, stop);
                }
                else{
                    String flipped = sc.next();
                    start = getPoint(sc.next());
                    sc.next();//skip "through"
                    stop = getPoint(sc.next());
                    if(flipped.equals("on")) turnOnLights(start, stop);
                    else if (flipped.equals("off")) turnOffLights(start, stop);
                    else System.out.println("I don't understand the directions  ");
                }
            }
        }
    }
    
    public int totalLightsOn(){
        int totalOn = 0;
        for(ArrayList<Object> light : grid){
            boolean on = (Boolean)light.get(1);
            if(on){
                totalOn++;
            }
        }
        return totalOn;
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        Lights lights = new Lights();
        lights.setupLights(1000);
        System.out.println(lights.grid.size());
        /*
        for(ArrayList<Object> light : lights.grid){
            System.out.println(light.size());
                for(Object info : light){
                    System.out.println(info);
                }
            
        }
        */
        System.out.println("What is the input?");
        Scanner sc = new Scanner(System.in);
        lights.executeInput(sc);
        System.out.println("Total lights on are: " + lights.totalLightsOn());
    }
    
}
