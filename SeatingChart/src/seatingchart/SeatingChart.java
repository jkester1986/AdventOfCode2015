/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seatingchart;


/**
 *
 * @author jkester
 */
public class SeatingChart {

    /**"
     * @param args the command line arguments
     */

    String[][] alice = {
        {"Alice", "Bob", "-2"},
        {"Alice", "Carol", "-62"},
        {"Alice", "David", "65"},
        {"Alice", "Eric", "21"},
        {"Alice", "Julia", "0"},
        {"Alice", "Frank", "-81"},
        {"Alice", "George", "-4"},
        {"Alice", "Julia", "0"},
        {"Alice", "Mallory", "-80"}};
    String[][] bob = {    
        {"Bob", "Alice", "93"},
        {"Bob", "David", "5"},
        {"Bob", "Carol", "19"},
        {"Bob", "Eric", "49"},
        {"Bob", "Frank", "68"},
        {"Bob", "George", "23"},
        {"Bob", "Julia", "0"},
        {"Bob", "Mallory", "29"}};
    String[][] david = {    
        {"David", "Alice", "43"},
        {"David", "Bob", "-96"},
        {"David", "Carol", "-53"},
        {"David", "Eric", "-30"},
        {"David", "Frank", "-12"},
        {"David", "George", "75"},
        {"David", "Julia", "0"},
        {"David", "Mallory", "-20"}};
    String[][] carol = {    
        {"Carol", "Alice", "-54"},
        {"Carol", "Bob", "-70"},
        {"Carol", "David", "-37"},
        {"Carol", "Eric", "-46"},
        {"Carol", "Frank", "33"},
        {"Carol", "George", "-35"},
        {"Carol", "Julia", "0"},
        {"Carol", "Mallory", "10"}};
    String[][] eric = {    
        {"Eric", "Alice", "8"},
        {"Eric", "Bob", "-89"},
        {"Eric", "David", "-34"},
        {"Eric", "Carol", "-69"},
        {"Eric", "Frank", "95"},
        {"Eric", "George", "34"},
        {"Eric", "Julia", "0"},
        {"Eric", "Mallory", "-99"}};
    String[][] frank = {
        {"Frank", "Alice", "-97"},
        {"Frank", "Bob", "6"},
        {"Frank", "David", "56"},
        {"Frank", "Carol", "-9"},
        {"Frank", "Eric", "-17"},
        {"Frank", "George", "18"},
        {"Frank", "Julia", "0"},
        {"Frank", "Mallory", "-56"}};
    String[][] george = {    
        {"George", "Alice", "45"},
        {"George", "Bob", "76"},
        {"George", "David", "54"},
        {"George", "Carol", "63"},
        {"George", "Eric", "54"},
        {"George", "Frank", "30"},
        {"George", "Julia", "0"},
        {"George", "Mallory", "7"}};
    String[][] mallory = {    
        {"Mallory", "Alice", "31"},
        {"Mallory", "Bob", "-32"},
        {"Mallory", "David", "91"},
        {"Mallory", "Carol", "95"},
        {"Mallory", "Eric", "-66"},
        {"Mallory", "Frank", "-75"},
        {"Mallory", "Julia", "0"},
        {"Mallory", "George", "-99"}};
    String[][] julia = {
        {"Julia", "Mallory", "0"},
        {"Julia", "Alice", "0"},
        {"Julia", "Bob", "0"},
        {"Julia", "David", "0"},
        {"Julia", "Carol", "0"},
        {"Julia", "Eric", "0"},
        {"Julia", "Frank", "0"},
        {"Julia", "George", "0"}
    };

    boolean seatedAlice = false;
    boolean seatedBob = false;
    boolean seatedDavid = false;
    boolean seatedCarol = false;
    boolean seatedEric = false;
    boolean seatedFrank = false;
    boolean visitedStraylight = false;
    boolean seatedMallory = false;
    boolean seatedJulia = false;

    int greatestHappiness = 0;
    int currentHappiness = 0;

    String lastSeated = "";


    public int assignSeats(String[][] startLoc){


        String start = startLoc[0][0];
        String end = startLoc[0][1];

        //System.out.println("start location is: " + start);

        switch (start){
            case "Alice":
                seatedAlice = true;
                break;
            case "Bob":
                seatedBob = true;
                break;
            case "David":
                seatedDavid = true;
                break;
            case "Carol":
                seatedCarol = true;
                break;
            case "Eric":
                seatedEric = true;
                break;
            case "Frank":
                seatedFrank = true;
                break;
            case "George":
                visitedStraylight = true;
                break;
            case "Mallory":
                seatedMallory = true;
                break;
            case "Julia":
                seatedJulia = true;
                break;
            default: System.out.println("Uh oh, no matching person");
                break;

        }

        if(!(seatedAlice && seatedBob && seatedDavid && seatedCarol && seatedEric && seatedFrank && visitedStraylight && seatedMallory && seatedJulia)){
            for (int i = 0; i < startLoc.length; i++){
                int happinessToRemove = 0;
                //System.out.println("distance to possibly be added: " + startLoc[i][2]);
                switch (startLoc[i][1]){
                    case "Alice":
                        if (seatedAlice){
                            System.out.println(startLoc[i][1] + " has already been seated");

                        }

                        else {

                            System.out.println("Going from  " + startLoc[i][0] + " to " + startLoc[i][1]);
                            System.out.println("distance to be added: " + startLoc[i][2]);
                            int nextHappiness = getHappiness(alice, startLoc[i][0]);
                            happinessToRemove = Integer.parseInt(startLoc[i][2]) + nextHappiness;
                            currentHappiness += happinessToRemove;
                            System.out.println("current distance: " + currentHappiness);
                            assignSeats(alice);
                            currentHappiness -= happinessToRemove;
                            System.out.println("distance to remove: " + happinessToRemove);
                            System.out.println("New distance: " + currentHappiness);
                        }
                        break;
                    case "Bob":
                        if (seatedBob){
                            //System.out.println(startLoc[i][1] + " has already been visited");

                        }

                        else {
                            System.out.println("Going from  " + startLoc[i][0] + " to " + startLoc[i][1]);
                            //System.out.println("distance to be added: " + startLoc[i][2]);
                            //System.out.println("Bob's happiness with Alice is : " + getHappiness(bob, startLoc[i][0]));
                            int nextHappiness = getHappiness(bob, startLoc[i][0]);
                            happinessToRemove = Integer.parseInt(startLoc[i][2]) + nextHappiness;
                            currentHappiness += happinessToRemove;
                            System.out.println("current distance: " + currentHappiness);
                            assignSeats(bob);
                            currentHappiness -= happinessToRemove;
                            System.out.println("distance to remove: " + happinessToRemove);
                            System.out.println("New distance: " + currentHappiness);
                        }
                        break;
                    case "David":
                        if (seatedDavid){
                            //System.out.println(startLoc[i][1] + " has already been visited");

                        }

                        else {
                            System.out.println("Going from  " + startLoc[i][0] + " to " + startLoc[i][1]);
                            System.out.println("distance to be added: " + startLoc[i][2]);
                            int nextHappiness = getHappiness(david, startLoc[i][0]);
                            happinessToRemove = Integer.parseInt(startLoc[i][2]) + nextHappiness;
                            currentHappiness += happinessToRemove;
                            System.out.println("current distance: " + currentHappiness);
                            assignSeats(david);
                            currentHappiness -= happinessToRemove;
                            System.out.println("distance to remove: " + happinessToRemove);
                            System.out.println("New distance: " + currentHappiness);
                        }
                        break;
                    case "Julia":
                        if (seatedJulia){
                            //System.out.println(startLoc[i][1] + " has already been visited");

                        }

                        else {
                            System.out.println("Going from  " + startLoc[i][0] + " to " + startLoc[i][1]);
                            System.out.println("distance to be added: " + startLoc[i][2]);
                            int nextHappiness = getHappiness(julia, startLoc[i][0]);
                            happinessToRemove = Integer.parseInt(startLoc[i][2]) + nextHappiness;
                            currentHappiness += happinessToRemove;
                            System.out.println("current distance: " + currentHappiness);
                            assignSeats(julia);
                            currentHappiness -= happinessToRemove;
                            System.out.println("distance to remove: " + happinessToRemove);
                            System.out.println("New distance: " + currentHappiness);
                        }
                        break;
                    case "Carol":
                        if (seatedCarol){
                            //System.out.println(startLoc[i][1] + " has already been visited");

                        }

                        else {
                            System.out.println("Going from  " + startLoc[i][0] + " to " + startLoc[i][1]);
                            System.out.println("distance to be added: " + startLoc[i][2]);
                            int nextHappiness = getHappiness(carol, startLoc[i][0]);
                            happinessToRemove = Integer.parseInt(startLoc[i][2]) + nextHappiness;
                            currentHappiness += happinessToRemove;
                            System.out.println("current distance: " + currentHappiness);
                            assignSeats(carol);
                            currentHappiness -= happinessToRemove;
                            System.out.println("distance to remove: " + happinessToRemove);
                            System.out.println("New distance: " + currentHappiness);
                        }
                        break;
                    case "Eric":
                        if (seatedEric){
                            System.out.println(startLoc[i][1] + " has already been visited");

                        }

                        else {
                            System.out.println("Going from  " + startLoc[i][0] + " to " + startLoc[i][1]);
                            System.out.println("happiness to be added: " + startLoc[i][2]);
                            int nextHappiness = getHappiness(eric, startLoc[i][0]);
                            happinessToRemove = Integer.parseInt(startLoc[i][2]) + nextHappiness;
                            
                            currentHappiness += happinessToRemove;
                            System.out.println("current distance: " + currentHappiness);
                            assignSeats(eric);
                            currentHappiness -= happinessToRemove;
                            System.out.println("distance to remove: " + happinessToRemove);
                            System.out.println("New distance: " + currentHappiness);
                        }
                        break;
                    case "Frank":
                        if (seatedFrank){
                            //System.out.println(startLoc[i][1] + " has already been visited");

                        }

                        else {

                            System.out.println("Going from  " + startLoc[i][0] + " to " + startLoc[i][1]);
                            System.out.println("distance to be added: " + startLoc[i][2]);
                            int nextHappiness = getHappiness(frank, startLoc[i][0]);
                            //System.out.println("happiness before add : " + currentHappiness);
                            happinessToRemove = Integer.parseInt(startLoc[i][2]) + nextHappiness;
                            //System.out.println("Total happiness to be added " + happinessToRemove);
                            currentHappiness += happinessToRemove;
                            System.out.println("current distance: " + currentHappiness);
                            assignSeats(frank);
                            currentHappiness -= happinessToRemove;
                            System.out.println("distance to remove: " + happinessToRemove);
                            System.out.println("New distance: " + currentHappiness);
                        }
                        break;
                    case "George":
                        if (visitedStraylight){
                            //System.out.println(startLoc[i][1] + " has already been visited");

                        }

                        else {
                            System.out.println("Going from  " + startLoc[i][0] + " to " + startLoc[i][1]);
                            System.out.println("distance to be added: " + startLoc[i][2]);
                            int nextHappiness = getHappiness(george, startLoc[i][0]);
                            happinessToRemove = Integer.parseInt(startLoc[i][2]) + nextHappiness;
                            currentHappiness += happinessToRemove;
                            System.out.println("current distance: " + currentHappiness);
                            assignSeats(george);
                            currentHappiness -= happinessToRemove;
                            System.out.println("distance to remove: " + happinessToRemove);
                            System.out.println("New distance: " + currentHappiness);
                        }
                        break;
                    case "Mallory":
                        if (seatedMallory){
                            //System.out.println(startLoc[i][1] + " has already been visited");

                        }

                        else {

                            System.out.println("Going from  " + startLoc[i][0] + " to " + startLoc[i][1]);
                            System.out.println("distance to be added: " + startLoc[i][2]);
                            int nextHappiness = getHappiness(mallory, startLoc[i][0]);
                            happinessToRemove = Integer.parseInt(startLoc[i][2]) + nextHappiness;
                            currentHappiness += happinessToRemove;
                            System.out.println("current distance: " + currentHappiness);
                            assignSeats(mallory);
                            currentHappiness -= happinessToRemove;
                            System.out.println("distance to remove: " + happinessToRemove);
                            System.out.println("New distance: " + currentHappiness);
                        }
                        break;
                    default: System.out.println("uh oh, no destination");
                        break;
                }
            }

            switch (start){
                    case "Alice":
                        seatedAlice = false;
                        break;
                    case "Bob":
                        seatedBob = false;
                        break;
                    case "David":
                        seatedDavid = false;
                        break;
                    case "Carol":
                        seatedCarol = false;
                        break;
                    case "Eric":
                        seatedEric = false;
                        break;
                    case "Frank":
                        seatedFrank = false;
                        break;
                    case "George":
                        visitedStraylight = false;
                        break;
                    case "Mallory":
                        seatedMallory = false;
                        break;
                    case "Julia":
                        seatedJulia = false;
                        break;
                    default: System.out.println("Uh oh, no matching start location");
                        break;

                }


            return greatestHappiness;

        }
        
        //All people have been seated
        else {
            //System.out.println("The current distance is: " + currentHappiness);
            int lastHappiness = getHappiness(alice, start) + getHappiness(startLoc, "Alice");
           
            System.out.println("Last happiness is " + lastHappiness);
            currentHappiness += lastHappiness;
            if (currentHappiness > greatestHappiness || greatestHappiness == 0){
                greatestHappiness = currentHappiness;
                System.out.println("THE CURRENT GREATEST HAPPINESS IS: " + greatestHappiness + "\n");
                switch (start){
                    case "Alice":
                        seatedAlice = false;
                        break;
                    case "Bob":
                        seatedBob = false;
                        break;
                    case "David":
                        seatedDavid = false;
                        break;
                    case "Carol":
                        seatedCarol = false;
                        break;
                    case "Eric":
                        seatedEric = false;
                        break;
                    case "Frank":
                        seatedFrank = false;
                        break;
                    case "George":
                        visitedStraylight = false;
                        break;
                    case "Mallory":
                        seatedMallory = false;
                        break;
                    case "Julia":
                        seatedJulia = false;
                        break;
                    default: System.out.println("Uh oh, no matching name");
                        break;

                }
            }
            else{
                System.out.println("THIS IS NOT THE BEST HAPPINESS" + "\n");
                switch (start){
                    case "Alice":
                        seatedAlice = false;
                        break;
                    case "Bob":
                        seatedBob = false;
                        break;
                    case "David":
                        seatedDavid = false;
                        break;
                    case "Carol":
                        seatedCarol = false;
                        break;
                    case "Eric":
                        seatedEric = false;
                        break;
                    case "Frank":
                        seatedFrank = false;
                        break;
                    case "George":
                        visitedStraylight = false;
                        break;
                    case "Mallory":
                        seatedMallory = false;
                        break;
                    case "Julia":
                        seatedJulia = false;
                        break;
                    default: System.out.println("Uh oh, no matching start location");
                        break;

                }
                
            }
            currentHappiness -= lastHappiness;
            System.out.println("Removing current happiness. New happiness is: " + currentHappiness);
            
            return greatestHappiness;
        }

    }
    
    public int getHappiness(String[][] person, String name){
        for(int i = 0; i < person.length; i++){
            if(person[i][1].equals(name)){
                System.out.println(person[i][2]);
                return Integer.parseInt(person[i][2]);
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        // TODO code application logic here
        SeatingChart chart = new SeatingChart();
        System.out.println(chart.assignSeats(chart.alice));
        /*
        System.out.println(chart.assignSeats(chart.bob));
        System.out.println(chart.assignSeats(chart.david));
        System.out.println(chart.assignSeats(chart.carol));
        System.out.println(chart.assignSeats(chart.eric));
        System.out.println(chart.assignSeats(chart.frank));
        System.out.println(chart.assignSeats(chart.george));
        System.out.println(chart.assignSeats(chart.mallory));
                */
        System.out.println("The most happiness you can create is: " + chart.greatestHappiness);


    }
}
