/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crimesceneanalysis;

import java.util.*;

/**
 *
 * @author jkester
 */
public class CrimeSceneAnalysis {

    /**
     * @param args the command line arguments
     */
    Sue[] sues = new Sue[500];
    
    int highestScore = 0;
    int highIndex = 0;
    
    public Sue buildSue(Sue sue, String line){
        for(int j = 2; j < 7; j++){
                String[] elements = line.split(" ");
                StringBuilder numString = new StringBuilder(elements[j+1]);
                if(numString.charAt(numString.length()-1) == ','){
                    numString.deleteCharAt(numString.length()-1);
                }
                //System.out.println("num is " + numString.toString());
                System.out.println("Elements[j] = " + elements[j]);
                        
                switch (elements[j]){
                    case "cars:":
                        sue.cars = Integer.parseInt(numString.toString());
                        System.out.println("Sue car " + sue.cars);
                        break;
                    case "children:":
                        sue.children = Integer.parseInt(numString.toString());
                        System.out.println("Sue children " + sue.children);
                        break;
                    case "cats:":
                        sue.cats = Integer.parseInt(numString.toString());
                        System.out.println("Sue cats " + sue.cats);
                        break;
                    case "samoyeds:":
                        sue.samoyeds =Integer.parseInt(numString.toString());
                        System.out.println("Sue samoyeds " + sue.samoyeds);
                        break;
                    case "pomeranians:":
                        sue.pomeranians = Integer.parseInt(numString.toString());
                        System.out.println("Sue pomeranians " + sue.pomeranians);
                        break;
                    case "akitas:":
                        sue.akitas = Integer.parseInt(numString.toString());
                        System.out.println("sue akitas " + sue.akitas);
                        break;
                    case "vizslas:":
                        sue.vizslas = Integer.parseInt(numString.toString());
                        System.out.println("Sue vizslas " + sue.vizslas);
                        break;
                    case "goldfish:":
                        sue.goldfish = Integer.parseInt(numString.toString());
                        System.out.println("sue goldfish " +  sue.goldfish);
                        break;
                    case "trees:":
                        sue.trees =Integer.parseInt(numString.toString());
                        System.out.println("Sue trees " + sue.trees);
                        break;
                    case "perfumes:":
                        sue.perfumes = Integer.parseInt(numString.toString());
                        System.out.println("sue perfumes " + sue.perfumes);
                        break;
                    default:
                        break;
                }
                j++;
               
            }
        return sue;
    }
    
    public void assignElements(Scanner sc){
        for(int i = 0; i < sues.length; i++){
            String line = sc.nextLine();
            //System.out.println(line);
            Sue sue = new Sue();
           
            sues[i] = buildSue(sue, line);;
        }
        System.out.println(Arrays.toString(sues));
    }
    
    public int findSue(int children, int cats, int samoyeds, int pomeranians, int akitas, int vizslas, int goldfish,
            int trees, int cars, int perfumes){
        for(int i = 0; i < sues.length; i++){
            int score = 0;
            //System.out.println(sues[i].children);
            if(children == sues[i].children && sues[i].children != -1){
                score += 1;
            }
            if(cats < sues[i].cats && sues[i].cats != -1){
                score += 1;
            }
            if(samoyeds == sues[i].samoyeds && sues[i].samoyeds != -1){
                score += 1;
            }
            if(pomeranians > sues[i].pomeranians && sues[i].pomeranians != -1){
                score += 1;
            }
            if(akitas == sues[i].akitas && sues[i].akitas != -1){
                score += 1;
            }
            if(vizslas == sues[i].vizslas && sues[i].vizslas != -1){
                score += 1;
            }
            if(goldfish > sues[i].goldfish && sues[i].goldfish != -1){
                score += 1;
            }
            if(trees < sues[i].trees && sues[i].trees != -1){
                score += 1;
            }
            if(cars == sues[i].cars && sues[i].cars != -1){
                score += 1;
            }
            if(perfumes == sues[i].perfumes && sues[i].perfumes != -1){
                score += 1;
            }
            
            if(score >= highestScore){
                highestScore = score;
                highIndex = i+1;
                System.out.println(highIndex);
            }
        }
        return highIndex;
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        CrimeSceneAnalysis csa = new CrimeSceneAnalysis();
        Scanner sc = new Scanner(System.in);
        System.out.println("What are the sues' info?");
        csa.assignElements(sc);
        System.out.println("Sue # " + csa.findSue(3, 7, 2, 3, 0, 0, 5, 3, 2, 1));
    }
    
}
