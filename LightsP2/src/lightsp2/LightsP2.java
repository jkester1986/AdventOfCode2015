/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lightsp2;
import java.awt.Point;
import java.util.*;

/**
 *
 * @author jkester
 */
public class LightsP2 {

    /**
     * @param args the command line arguments
     */
    ArrayList<ArrayList<Object>> grid = new ArrayList<ArrayList<Object>>(1000000);
    
    public void setupLights(int size){
        for(int x = 0; x < size; x++){
            for(int y = 0; y < size; y++){
                ArrayList<Object> light = new ArrayList<Object>();
                Point location = new Point(x, y);
                int brightness = 0;
                light.add(location);
                light.add(brightness);
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
               
               int brightness = (int)gridpoint.get(1);
               
               //System.out.println("gridpoint.get(0) = " + gridpoint.get(0) + "and the point = " + point);
               
               if(gridpoint.get(0).equals(point)) {
                   grid.get(index).set(1, brightness+1);
               }
               else{
                   System.out.println("Brightening: The points don't match!");
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
               //System.out.println("The index is: " + index);
               ArrayList<Object> gridpoint = grid.get(index);
               Point point = new Point(x,y);
               
               
               int brightness = (int)gridpoint.get(1);
               //System.out.println("current brightness of this light is: " + brightness);
               
               if(gridpoint.get(0).equals(point) && brightness > 0) {
                   grid.get(index).set(1, brightness-1);
               }
               else if(gridpoint.get(0).equals(point) && brightness <= 0) {
                   //System.out.println("The brightness is already 0");
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
               
               int brightness = (int)gridpoint.get(1);
               
               //System.out.println("gridpoint.get(0) = " + gridpoint.get(0) + "and the point = " + point);
               
               if(gridpoint.get(0).equals(point)) {
                   grid.get(index).set(1, brightness+2);
               }
               else{
                   System.out.println("Toggling: The points don't match!");
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
        Point stop = new Point(0,0);
        
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
    
    public int totalBrightness(){
        int totBrightness = 0;
        for(ArrayList<Object> light : grid){
            int brightness = (int)light.get(1);
            totBrightness += brightness;
        }
        return totBrightness;
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        LightsP2 lights = new LightsP2();
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
        System.out.println("Total lights on are: " + lights.totalBrightness());
    }
    
}
