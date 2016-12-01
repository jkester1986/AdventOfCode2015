/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travelingsanta;

import java.util.*;
/**
 *
 * @author jkester
 */
//essentially a traveling salesman problem
public class TravelingSanta {

    /**
     * @param args the command line arguments
     */
    
    String[][] tristam = {
        {"Tristam", "AlphaCentauri", "34"},
        {"Tristam", "Tambi", "63"},
        {"Tristam", "Snowdin", "100"},
        {"Tristam", "Faerun", "108"},
        {"Tristam", "Norrath", "111"},
        {"Tristam", "Straylight", "89"},
        {"Tristam", "Arbre", "132"}};
    String[][] alpha = {    
        {"AlphaCentauri", "Tristam", "34"},
        {"AlphaCentauri", "Snowdin", "4"},
        {"AlphaCentauri", "Tambi", "79"},
        {"AlphaCentauri", "Faerun", "44"},
        {"AlphaCentauri", "Norrath", "147"},
        {"AlphaCentauri", "Straylight", "133"},
        {"AlphaCentauri", "Arbre", "74"}};
    String[][] snowdin = {    
        {"Snowdin", "Tristam", "100"},
        {"Snowdin", "AlphaCentauri", "4"},
        {"Snowdin", "Tambi", "105"},
        {"Snowdin", "Faerun", "95"},
        {"Snowdin", "Norrath", "48"},
        {"Snowdin", "Straylight", "88"},
        {"Snowdin", "Arbre", "7"}};
    String[][] tambi = {    
        {"Tambi", "Tristam", "63"},
        {"Tambi", "AlphaCentauri", "79"},
        {"Tambi", "Snowdin", "105"},
        {"Tambi", "Faerun", "68"},
        {"Tambi", "Norrath", "134"},
        {"Tambi", "Straylight", "107"},
        {"Tambi", "Arbre", "40"}};
    String[][] faerun = {    
        {"Faerun", "Tristam", "108"},
        {"Faerun", "AlphaCentauri", "44"},
        {"Faerun", "Snowdin", "95"},
        {"Faerun", "Tambi", "68"},
        {"Faerun", "Norrath", "11"},
        {"Faerun", "Straylight", "66"},
        {"Faerun", "Arbre", "144"}};
    String[][] norrath = {
        {"Norrath", "Tristam", "111"},
        {"Norrath", "AlphaCentauri", "147"},
        {"Norrath", "Snowdin", "48"},
        {"Norrath", "Tambi", "134"},
        {"Norrath", "Faerun", "11"},
        {"Norrath", "Straylight", "115"},
        {"Norrath", "Arbre", "135"}};
    String[][] straylight = {    
        {"Straylight", "Tristam", "89"},
        {"Straylight", "AlphaCentauri", "133"},
        {"Straylight", "Snowdin", "88"},
        {"Straylight", "Tambi", "107"},
        {"Straylight", "Faerun", "66"},
        {"Straylight", "Norrath", "115"},
        {"Straylight", "Arbre", "127"}};
    String[][] arbre = {    
        {"Arbre", "Tristam", "132"},
        {"Arbre", "AlphaCentauri", "74"},
        {"Arbre", "Snowdin", "7"},
        {"Arbre", "Tambi", "40"},
        {"Arbre", "Faerun", "144"},
        {"Arbre", "Norrath", "135"},
        {"Arbre", "Straylight", "127"}};
    
    boolean visitedTristam = false;
    boolean visitedAlpha = false;
    boolean visitedSnowdin = false;
    boolean visitedTambi = false;
    boolean visitedFaerun = false;
    boolean visitedNorrath = false;
    boolean visitedStraylight = false;
    boolean visitedArbre = false;
    
    int shortestRoute = 0;
    int currentDistance = 0;
    
    
    
    
    public int checkRoute(String[][] startLoc){
        
        
        String start = startLoc[0][0];
        String end = startLoc[0][1];
        
        //System.out.println("start location is: " + start);
        
        switch (start){
            case "Tristam":
                visitedTristam = true;
                break;
            case "AlphaCentauri":
                visitedAlpha = true;
                break;
            case "Snowdin":
                visitedSnowdin = true;
                break;
            case "Tambi":
                visitedTambi = true;
                break;
            case "Faerun":
                visitedFaerun = true;
                break;
            case "Norrath":
                visitedNorrath = true;
                break;
            case "Straylight":
                visitedStraylight = true;
                break;
            case "Arbre":
                visitedArbre = true;
                break;
            default: System.out.println("Uh oh, no matching start location");
                break;
            
        }
        
        if(!(visitedTristam && visitedAlpha && visitedSnowdin && visitedTambi && visitedFaerun && visitedNorrath && visitedStraylight && visitedArbre)){
            for (int i = 0; i < startLoc.length; i++){
                int distanceToRemove = 0;
                //System.out.println("distance to possibly be added: " + startLoc[i][2]);
                switch (startLoc[i][1]){
                    case "Tristam":
                        if (visitedTristam){
                            //System.out.println(startLoc[i][1] + " has already been visited");
                            
                        }
                        
                        else {
                            
                            System.out.println("Going from  " + startLoc[i][0] + " to " + startLoc[i][1]);
                            System.out.println("distance to be added: " + startLoc[i][2]);
                            distanceToRemove = Integer.parseInt(startLoc[i][2]);
                            currentDistance += distanceToRemove;
                            System.out.println("current distance: " + currentDistance);
                            checkRoute(tristam);
                            currentDistance -= distanceToRemove;
                            System.out.println("distance to remove: " + distanceToRemove);
                            System.out.println("New distance: " + currentDistance);
                        }
                        break;
                    case "AlphaCentauri":
                        if (visitedAlpha){
                            //System.out.println(startLoc[i][1] + " has already been visited");
                            
                        }
                        
                        else {
                            System.out.println("Going from  " + startLoc[i][0] + " to " + startLoc[i][1]);
                            System.out.println("distance to be added: " + startLoc[i][2]);
                            distanceToRemove = Integer.parseInt(startLoc[i][2]);
                            currentDistance += distanceToRemove;
                            System.out.println("current distance: " + currentDistance);
                            checkRoute(alpha);
                            currentDistance -= distanceToRemove;
                            System.out.println("distance to remove: " + distanceToRemove);
                            System.out.println("New distance: " + currentDistance);
                        }
                        break;
                    case "Snowdin":
                        if (visitedSnowdin){
                            //System.out.println(startLoc[i][1] + " has already been visited");
                            
                        }
                        
                        else {
                            System.out.println("Going from  " + startLoc[i][0] + " to " + startLoc[i][1]);
                            System.out.println("distance to be added: " + startLoc[i][2]);
                            distanceToRemove = Integer.parseInt(startLoc[i][2]);
                            currentDistance += distanceToRemove;
                            System.out.println("current distance: " + currentDistance);
                            checkRoute(snowdin);
                            currentDistance -= distanceToRemove;
                            System.out.println("distance to remove: " + distanceToRemove);
                            System.out.println("New distance: " + currentDistance);
                        }
                        break;
                    case "Tambi":
                        if (visitedTambi){
                            //System.out.println(startLoc[i][1] + " has already been visited");
                            
                        }
                        
                        else {
                            System.out.println("Going from  " + startLoc[i][0] + " to " + startLoc[i][1]);
                            System.out.println("distance to be added: " + startLoc[i][2]);
                            distanceToRemove = Integer.parseInt(startLoc[i][2]);
                            currentDistance += distanceToRemove;
                            System.out.println("current distance: " + currentDistance);
                            checkRoute(tambi);
                            currentDistance -= distanceToRemove;
                            System.out.println("distance to remove: " + distanceToRemove);
                            System.out.println("New distance: " + currentDistance);
                        }
                        break;
                    case "Faerun":
                        if (visitedFaerun){
                            System.out.println(startLoc[i][1] + " has already been visited");
                            
                        }
                        
                        else {
                            System.out.println("Going from  " + startLoc[i][0] + " to " + startLoc[i][1]);
                            System.out.println("distance to be added: " + startLoc[i][2]);
                            distanceToRemove = Integer.parseInt(startLoc[i][2]);
                            currentDistance += distanceToRemove;
                            System.out.println("current distance: " + currentDistance);
                            checkRoute(faerun);
                            currentDistance -= distanceToRemove;
                            System.out.println("distance to remove: " + distanceToRemove);
                            System.out.println("New distance: " + currentDistance);
                        }
                        break;
                    case "Norrath":
                        if (visitedNorrath){
                            //System.out.println(startLoc[i][1] + " has already been visited");
                            
                        }
                        
                        else {
                            
                            System.out.println("Going from  " + startLoc[i][0] + " to " + startLoc[i][1]);
                            System.out.println("distance to be added: " + startLoc[i][2]);
                            distanceToRemove = Integer.parseInt(startLoc[i][2]);
                            currentDistance += Integer.parseInt(startLoc[i][2]);
                            System.out.println("current distance: " + currentDistance);
                            checkRoute(norrath);
                            currentDistance -= distanceToRemove;
                            System.out.println("distance to remove: " + distanceToRemove);
                            System.out.println("New distance: " + currentDistance);
                        }
                        break;
                    case "Straylight":
                        if (visitedStraylight){
                            //System.out.println(startLoc[i][1] + " has already been visited");
                            
                        }
                        
                        else {
                            System.out.println("Going from  " + startLoc[i][0] + " to " + startLoc[i][1]);
                            System.out.println("distance to be added: " + startLoc[i][2]);
                            distanceToRemove = Integer.parseInt(startLoc[i][2]);
                            currentDistance += distanceToRemove;
                            System.out.println("current distance: " + currentDistance);
                            checkRoute(straylight);
                            currentDistance -= distanceToRemove;
                            System.out.println("distance to remove: " + distanceToRemove);
                            System.out.println("New distance: " + currentDistance);
                        }
                        break;
                    case "Arbre":
                        if (visitedArbre){
                            //System.out.println(startLoc[i][1] + " has already been visited");
                            
                        }
                        
                        else {
                            
                            System.out.println("Going from  " + startLoc[i][0] + " to " + startLoc[i][1]);
                            System.out.println("distance to be added: " + startLoc[i][2]);
                            distanceToRemove = Integer.parseInt(startLoc[i][2]);
                            currentDistance += distanceToRemove;
                            System.out.println("current distance: " + currentDistance);
                            checkRoute(arbre);
                            currentDistance -= distanceToRemove;
                            System.out.println("distance to remove: " + distanceToRemove);
                            System.out.println("New distance: " + currentDistance);
                        }
                        break;
                    default: System.out.println("uh oh, no destination");
                        break;
                }
            }
            
            switch (start){
                    case "Tristam":
                        visitedTristam = false;
                        break;
                    case "AlphaCentauri":
                        visitedAlpha = false;
                        break;
                    case "Snowdin":
                        visitedSnowdin = false;
                        break;
                    case "Tambi":
                        visitedTambi = false;
                        break;
                    case "Faerun":
                        visitedFaerun = false;
                        break;
                    case "Norrath":
                        visitedNorrath = false;
                        break;
                    case "Straylight":
                        visitedStraylight = false;
                        break;
                    case "Arbre":
                        visitedArbre = false;
                        break;
                    default: System.out.println("Uh oh, no matching start location");
                        break;

                }
                
            
            return shortestRoute;
            
        }
        else {
            //System.out.println("The current distance is: " + currentDistance);
            
            if (currentDistance < shortestRoute || shortestRoute == 0){
                shortestRoute = currentDistance;
                System.out.println("THE CURRENT SHORTEST ROUTE IS: " + shortestRoute + "\n");
                switch (start){
                    case "Tristam":
                        visitedTristam = false;
                        break;
                    case "AlphaCentauri":
                        visitedAlpha = false;
                        break;
                    case "Snowdin":
                        visitedSnowdin = false;
                        break;
                    case "Tambi":
                        visitedTambi = false;
                        break;
                    case "Faerun":
                        visitedFaerun = false;
                        break;
                    case "Norrath":
                        visitedNorrath = false;
                        break;
                    case "Straylight":
                        visitedStraylight = false;
                        break;
                    case "Arbre":
                        visitedArbre = false;
                        break;
                    default: System.out.println("Uh oh, no matching start location");
                        break;

                }
            }
            else{
                System.out.println("THIS IS NOT A SHORTER ROUTE" + "\n");
                switch (start){
                    case "Tristam":
                        visitedTristam = false;
                        break;
                    case "AlphaCentauri":
                        visitedAlpha = false;
                        break;
                    case "Snowdin":
                        visitedSnowdin = false;
                        break;
                    case "Tambi":
                        visitedTambi = false;
                        break;
                    case "Faerun":
                        visitedFaerun = false;
                        break;
                    case "Norrath":
                        visitedNorrath = false;
                        break;
                    case "Straylight":
                        visitedStraylight = false;
                        break;
                    case "Arbre":
                        visitedArbre = false;
                        break;
                    default: System.out.println("Uh oh, no matching start location");
                        break;

                }
            }
            
            
           

          
            return shortestRoute;
        }
    
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        TravelingSanta santa = new TravelingSanta();
        System.out.println(santa.checkRoute(santa.tristam));
        System.out.println(santa.checkRoute(santa.alpha));
        System.out.println(santa.checkRoute(santa.snowdin));
        System.out.println(santa.checkRoute(santa.tambi));
        System.out.println(santa.checkRoute(santa.faerun));
        System.out.println(santa.checkRoute(santa.norrath));
        System.out.println(santa.checkRoute(santa.straylight));
        System.out.println(santa.checkRoute(santa.arbre));
        System.out.println("Shortest route is: " + santa.shortestRoute);
    }
    
}
