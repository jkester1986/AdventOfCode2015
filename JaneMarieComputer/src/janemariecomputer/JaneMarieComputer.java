/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package janemariecomputer;

import java.util.*;
/**
 *
 * @author jkester
 */
public class JaneMarieComputer {

    /**
     * @param args the command line arguments
     */
    int a = 1; 
    int b = 0;
    
    public ArrayList<String[]> getDirections(Scanner sc){
        ArrayList<String[]> directions = new ArrayList<>();
        while(sc.hasNextLine()){
            String line = sc.nextLine();
            if(line.equals("exit")){
                sc.close();
                break;
            }
            else{
                line = line.replace(",", "");
                line = line.replace(" ", ",");
                line = line.replace("+", "");
                
                //System.out.println(line);
                //System.out.println("I  am going through");
                String[] directionArr = line.split(",");
                directions.add(directionArr);
            }
        }
        return directions;
    } 
    
    public void followDirections(ArrayList<String[]> directions){
        for(int index = 0; index < directions.size(); index++){
            String[] direction = directions.get(index);
            switch (direction[0]){
                case "hlf":
                    if(direction[1].equals("a")){
                        a = a/2;
                    }
                    else {
                        b = b/2;
                    }
                    break;
                    
                case "tpl":
                    if(direction[1].equals("a")){
                        a = a*3;
                    }
                    else {
                        b = b*3;
                    }
                    break;
                    
                case "inc":
                    if(direction[1].equals("a")){
                        a++;
                    }
                    else {
                        b++;
                    }
                    break;
                    
                case "jmp":
                    index += (Integer.parseInt(direction[1])-1);
                    break;
                    
                case "jie":
                    if(direction[1].equals("a")){
                        if(a%2 == 0){
                            index += (Integer.parseInt(direction[2])-1);
                        }
                    }
                    else {
                        if(b%2 == 0){
                            index += (Integer.parseInt(direction[2])-1);
                        }
                    }
                    break;
                    
                case "jio":
                    if(direction[1].equals("a")){
                        if(a == 1){
                            index += (Integer.parseInt(direction[2])-1);
                        }
                    }
                    else {
                        if(a%2 == 1){
                            index += (Integer.parseInt(direction[2])-1);
                        }
                    }
                    break;
                    
                default: break;
            }
        }
        System.out.println("b = " + b);
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        JaneMarieComputer jmc = new JaneMarieComputer();
        Scanner sc = new Scanner(System.in);
        System.out.println("What directions are you giving?");
        
        ArrayList<String[]> directions = jmc.getDirections(sc);
        
        jmc.followDirections(directions);
    }
}
