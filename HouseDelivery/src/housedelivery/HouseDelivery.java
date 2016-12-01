/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package housedelivery;
import java.util.*;
/**
 *
 * @author jkester
 */
public class HouseDelivery {

    /**
     * @param args the command line arguments
     */
    
    public int countHouses(String direction){
        ArrayList<ArrayList<Integer>> count = new ArrayList<ArrayList<Integer>>();
        
        //this is the part I originally forgot
        ArrayList<Integer> origin = new ArrayList<Integer>();
        origin.add(0);
        origin.add(0);
        count.add(origin);
        
        int x = 0;
        int y = 0;
        int x2 = 0;
        int y2 = 0;
        boolean svr = true;
        
        for (int i = 0; i < direction.length(); i++){
            ArrayList<Integer> grid = new ArrayList<Integer>();
            ArrayList<Integer> roboGrid = new ArrayList<Integer>();
            //System.out.println(svr);
            if(direction.charAt(i) == '>'){
                if(svr)x ++;
                else x2 ++;
            }
            else if(direction.charAt(i) == '<'){
                if(svr) x --;
                else x2 --;
            }
            else if(direction.charAt(i) == '^'){
                if(svr) y ++;
                else y2 ++;
            }
            else if(direction.charAt(i) == 'v'){
                if(svr) y --;
                else y2 --;
            }
            else System.out.println("There's a problem, no character being read in.");
            
            grid.add(x);
            grid.add(y);
            roboGrid.add(x2);
            roboGrid.add(y2);
            if(svr){
                if(!count.contains(grid)) count.add(grid);
            }
            else{
                if(!count.contains(roboGrid)) count.add(roboGrid);
            }
            svr = !svr;
        }
        return count.size();
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        HouseDelivery route = new HouseDelivery();
        Scanner user_input = new Scanner( System.in );
        System.out.println("Where is Santa supposed to travel?");
        String myInput;
        myInput = user_input.next();
        System.out.println(route.countHouses(myInput) + " different houses were visited");
    }
}
