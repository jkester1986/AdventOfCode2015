/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package balancingpackages;

import java.util.*;
import org.paukov.combinatorics.*;

/**
 *
 * @author jkester
 */
public class BalancingPackages {

    /**
     * @param args the command line arguments
     */
    ArrayList<Integer> numbers = new ArrayList<>();
    
    ArrayList<ArrayList<Integer>> combinations = new ArrayList<>();
    ArrayList<Integer> tempList = new ArrayList<>();
    
    int balanceGoal = 0;
    
    int total = 0;
    
   ArrayList<ArrayList<Integer>> smallCombos = new ArrayList<>();
    
    public int totDivideByThree(Scanner sc){
        int third = 0;
        int index = 0;
        while(sc.hasNext()){
            String next = sc.next();
            if(next.equals("exit")){
                break;
            }
            else{
                int theNum = Integer.parseInt(next);
                third += theNum;
                //if(third >= 516) System.out.println("Max size of list is " + index);
                numbers.add(theNum);
                index++;
            }
        }
        System.out.println(third);
        third = third/4;
        return third;
    }
    
    ArrayList<Integer> temp = new ArrayList<>();
    
    boolean foundWinner = false;
    ArrayList<Integer> winner;
    
    public void getCombos(ArrayList<Integer> list){
        //System.out.println(list.size() + " = the size of the list");
        if(list.size() == 0){
            return;
        }
        if(foundWinner){
            return;
        }
        else{
            for(int num = 0; num < list.size(); num++){
                
                ArrayList<Integer> oldTemp = new ArrayList(temp);
                temp.add(list.get(num));
                int revertTot = total;
                total += list.get(num);
                //System.out.println("Number being added : " + list.get(num));
                //System.out.println(total);
                if(total == balanceGoal){
                    if(!foundWinner){
                        winner = new ArrayList(temp);
                        foundWinner = true;
                    }
                    
                    //System.out.println("winning combination is: " + temp);
                    //return;//if we have made it here, any other numbers will be too high
                }
                else if(total > balanceGoal){
                    //return;
                }
                
                else{
                    for(int index = num+1; index < list.size(); index++){
                        ArrayList<Integer> newList = new ArrayList<Integer>(list.subList(index, list.size()));
                        //System.out.println("new list: " + newList);
                        getCombos(newList);
                        //System.out.println("The value about to be removed is " + list.get(index-1));
                        int difference = total - list.get(index-1);
                        temp.remove(list.get(index-1));
                        total -= difference;
                        revertTot = total;
                    }
                }
                
                //System.out.println("Number being removed : " + list.get(num));
                total = revertTot;
                //System.out.println(total);
                temp.remove(list.get(num));
            }
        }
    }
    
    public void getSmallCombos(ArrayList<Integer> list){
        if(list.size() == 0){
            return;
        }
        else{
            for(int num = 0; num < list.size(); num++){
                temp.add(list.get(num));
                /*
                if(!smallCombos.contains(temp)){
                    smallCombos.add(new ArrayList(temp));
                }
                */
                int total = 0;
                for(int i : temp){
                    total += i;
                    
                }
                if(!smallCombos.contains(temp) && total == balanceGoal){
                    smallCombos.add(new ArrayList(temp));
                    System.out.println(temp);
                    temp.remove(list.get(num));
                    return;
                }
                
                else if(total > balanceGoal || temp.size() > 5){

                    temp.remove(list.get(num));
                    return;
                }
                
                for(int index = num+1; index < list.size(); index++){
                    ArrayList<Integer> newList = new ArrayList<Integer>(list.subList(index, list.size()));
                    getSmallCombos(new ArrayList(newList));
                    temp.remove(list.get(index-1));
                }
                
                temp.remove(list.get(num));
            }
        }
    }
    
    
    
    public void reverseArray(){
        ArrayList<Integer> list = new ArrayList();
        
        for(int index = 0; index < numbers.size(); index++){
            //System.out.println("index is: " + index);
            list.add(numbers.get(numbers.size()-index-1));
        }
        
        numbers = new ArrayList(list);
        System.out.println(numbers);
    }
    
    public long entanglement(ArrayList<Integer> list){
        long entTotal = 1;
        for(Integer integer : list){
            System.out.println(integer);
            entTotal = integer.intValue()*entTotal;
            System.out.println("new total: " + entTotal);
        }
        
        return entTotal;
    }
    
    public void reduceWinners(){
        smallCombos.sort(Comparator.comparing(ArrayList::size));
        int size = smallCombos.get(0).size();
        
        ArrayList<ArrayList<Integer>> reduced = new ArrayList();
        
        for(ArrayList<Integer> combo : smallCombos){
            if(combo.size()== size){
                reduced.add(combo);
            }
            else break;
        }
        
        smallCombos = new ArrayList(reduced);
    }
    
    
    public static void main(String[] args) {
        // TODO code application logic here
        BalancingPackages bp = new BalancingPackages();
        Scanner sc = new Scanner(System.in);
        System.out.println("What are the sizes of the packages?");
        bp.balanceGoal = bp.totDivideByThree(sc);
        System.out.println("Each group should be divided into a size of " + bp.balanceGoal);
        
        System.out.println(bp.numbers);
        //bp.reverseArray();
        
        
        ArrayList<Integer> smallNums = new ArrayList<>();
        smallNums.add(1);
        smallNums.add(2);
        smallNums.add(3);
        smallNums.add(4);
        smallNums.add(5);
        
        
        bp.getSmallCombos(bp.numbers);
        /*
        for(ArrayList<Integer> combo : bp.smallCombos){
            System.out.println(combo);
        }
        */
        
       
        /*
        bp.getCombos(bp.numbers);
        System.out.println("Winning combo is: " + bp.winner);
        bp.foundWinner = false;
        bp.combinations.add(bp.winner);
        
        int midway = bp.numbers.get(13);
        
        while(bp.numbers.get(0) != midway){
            Integer toShift = bp.numbers.get(0);
            bp.numbers.remove(toShift);
            bp.numbers.add(toShift);
            
            bp.getCombos(bp.numbers);
            System.out.println("Winning combo is: " + bp.winner);
            bp.foundWinner = false;
            bp.combinations.add(bp.winner);
        }
        
        */
        
        bp.reduceWinners();
        System.out.println("reduced winners");
        
        for(ArrayList combo : bp.smallCombos){
            System.out.println(combo);
            System.out.println("The entanglement is " + bp.entanglement(combo));
        }
        
        //
        //System.out.println("There are " + bp.combinations.size() + " combinations");
        
        /*
        for(ArrayList<Integer> combo : bp.combinations){
            System.out.println(combo);
        }
        */
        
        
        
        /*
        ICombinatoricsVector<ArrayList> originalVector = Factory.createVector(bp.listToArray());
        Generator<ArrayList> gen = Factory.createSimpleCombinationGenerator(originalVector, 3);
        
        for (ICombinatoricsVector<ArrayList> combo : gen)
            System.out.println(combo);
*/
    }
    
}