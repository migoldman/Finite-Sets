/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finite.set;

/**
 *
 * @author michaelgoldman
 */
import java.util.Random;

public class Testers {

    static Random rand = new Random();
    //Creates an empty FiniteIntSet
    public static FiniteIntSet empty() {
        return new Leaf();
    }
    //Flips a coin and returns true or false 
    public static boolean coinFlip() {
        return rand.nextInt(10) > 5; 
    }
    //Creates a random int from from min to max
    public static int randomInt(int min, int max) {
        return rand.nextInt((max - min) + 1) + min;
    } 
    //Creates a random FiniteIntSet (RFIS) with a length of maxlen, data ranging
        //from min to max
    //No duplicates
    public static FiniteIntSet RFIS(int min, int max, int maxlen) {
        if(maxlen > 0) {
            return RFIS(min,max,(maxlen-1)).add(randomInt(min,max));
        }
        else return new Leaf();
    }
    
   
    //Test for RFIS for duplicates and other anomalies
        //checks remove against member
    public static void RFIStester() {
        for(int i =0; i < 50; i++) {
            FiniteIntSet holder = RFIS(0,10,30);
            //it goes from 0...10, so 11 total ints
            if(holder.cardinality() > 11 || holder.cardinality() < 0) {
                System.out.println("FAILURE in length");
            }
            //Removes the first 7 in the FiniteIntSet, then checks if there are
            //any other 7s. Prints failure if true.
            else if(holder.remove(7).member(7)) {
                System.out.println("FAILURE in duplicate");
            }
        }
    }
            
        //Cardinality Add Principle
            //holder.add(randInt).cardinality <==> holder.cardinality() (if t.member(elt))
                // || holder.cardinality()+1 (if !(holder.member(elt))
        public static void cardAddP() {
            for(int i = 0; i < 50; i++) {
                FiniteIntSet holder = RFIS(0, 15, 20);
                int randInt = randomInt(0, 15);
                int temp = holder.cardinality();
                if(holder.member(randInt)) {
                    if(holder.add(randInt).cardinality() == temp+1) {
                        System.out.println("Something was added wrongly in cardAddP");
                    }
                }
                else {
                    if(holder.add(randInt).cardinality() == temp) {
                        System.out.println("Something wasn't added in cardAddP");
                    }
                }
            }
        }
        
        //Cardinality Remove Principle
            //holder.remove(randInt).cardinality() <==> holder.cardinality() || 
                //holder.cardinality()-1
        public static void cardRemoveP() {
            for(int i = 0; i < 50; i++) {
                FiniteIntSet holder = RFIS(0,15,20);
                int temp = holder.cardinality();
                int randInt = randomInt(0,15);
                if(holder.remove(randInt).cardinality() == temp ||
                        holder.remove(randInt).cardinality() == temp-1) { 
                    //it works
                }
                else {
                    System.out.println("Something went terribly wrong in cardRemoveP");
                }
            }
        }
        
        //Add Member Principle
            //holder.add(x).member(y) == true <==> 
                //x = y || holder.member(y) = true;
        public static void addMemberP() {
            for(int i = 0; i < 100; i++) {
                int x = randomInt(0,15);
                int y = randomInt(0,15);
                FiniteIntSet holder = RFIS(0, 15, 10);
                if(holder.add(x).member(y)) {
                    if(x == y || holder.member(y)) {    
                        //x = y
                        //y is already a member
                    }
                    else {
                        System.out.println("Something went wrong with addMemberP");
                    }
                }
                else {
                    if(x == y || holder.member(y)) {
                        System.out.println("Something went wrong in addMemberP");
                    }
                }
            }
        }
        //Member Remove Principle
            //if holder.member(x) == true <==> holder.remove(x).cardinality() ==
                //holder.cardinality()-1
            //if holder.member(x) == false <==> holder.remove(x).cardinality() ==
                //holder.cardinality()
        public static void memberRemoveP() {
            for(int i = 0; i < 50; i++) {
                int x = randomInt(0,15);
                FiniteIntSet holder = RFIS(0, 15, 10);
                if(holder.member(x)) {
                    if(holder.remove(x).cardinality() != holder.cardinality() - 1) {
                        System.out.println("OH THE HUMANITY!!! memberRemoveP is bad!");
                    }
                }
                else {
                    if(holder.remove(x).cardinality() != holder.cardinality()) {
                        System.out.println("memberRemoveP is BAD!");
                    }
                }
            }
        }
        
        //Member union Principle
            //holder.union(x).member(y) == true <==> holder.member(y) = true ||
                //x.member(y) = true
        public static void memberUnionP() {
            for(int i = 0; i < 50; i++) {
                FiniteIntSet holder = RFIS(0, 15, 10);
                FiniteIntSet x = RFIS(0, 15, 10);
                int y = randomInt(0, 15);
                if(holder.union(x).member(y) == true) {
                    if(holder.member(y) == true || x.member(y) == true) {                       
                    }
                    else {
                        System.out.println("Error with memberUnionP");
                    }
                }
                else {
                    if(holder.member(y) == true || x.member(y) == true) {
                        System.out.println("Error with memberUnionP not unioned");
                    }
                }
            }
        }
   
        //Empty IsEmptyHuh Principle
            //holder.isEmptyHuh() == true <==> holder = Leaf
            //holder.isEmptyHuh() == false <==> holder = Branch
        public static void emptyEmptyHuhP() {
            for(int i = 0; i < 50; i++) {
                FiniteIntSet holder = RFIS(0, 15, 10);
                FiniteIntSet MT = empty();
                if(coinFlip()) {
                    if(MT.isEmptyHuh() == false) {
                        System.out.println("Error in emptyEmptyHuhP MT");
                    }
                }
                else {
                    if(holder.isEmptyHuh() == true) {
                        System.out.println("Error in emptyEmptyHuhP holder");
                    }
                }
            }
        }
        
        //Diff Member Principle
            //holder.diff(x).member(y) == true <==> x.member(y) == true &&
                //holder.member(y) == false
            //holder.diff(x).member(y) == false <==> x.member(y) == false ||
                //holder.member(y) == true
        public static void diffMemberP() {
            for(int i = 0; i < 50; i++) {
                FiniteIntSet holder = RFIS(0, 20, 20);
                FiniteIntSet x = RFIS(0, 20, 20);
                int y = randomInt(0,20);
                if(holder.diff(x).member(y) == true) {
                    if(x.member(y) == false || holder.member(y) == true)
                    {
                        System.out.println("diffMemberP is wrong");
                    }
                }
                else if(x.member(y) == false || holder.member(y) == true) {
                }
                else { 
                    System.out.println("Something went wrong in diffMemberP");
                }
            }
        }
        
        //Subset Union Principle
            //x.union(y).subset(z) == true <==> x.subset(z) == true && 
            //y.subset(z) == true
        public static void subsetUnionP() {
            for(int i = 0; i < 50; i++) {
                FiniteIntSet x = RFIS(0,5,10);
                FiniteIntSet y = RFIS(6,10,10);
                FiniteIntSet z = RFIS(0,10,20);
                if(x.union(y).subset(z) != x.subset(z) && y.subset(z)) {
                    System.out.println("Something went wrong in subsetUnionP");
                }
            }
        }
        
        //Equal Inter Union Principle
            //x.union(y) = x.inter(y) <==> x.equal(y) == true
        public static void equalInterUnionP() {
            for(int i = 0; i < 50; i++) {
                FiniteIntSet x = RFIS(0,10,15);
                FiniteIntSet y = RFIS(0,10,15);
                if(x.union(y) == x.inter(y) && x.equal(y) == false) {
                    System.out.println("Something went wrong in equalInterUnionP");
                }
                if(!(x.union(y).equal(x.inter(y))) && x.equal(y) == true) {
                    System.out.println("Something went wrong in later part of"
                            + "equalInterUnionP");
                }
            }
        }

        //Inter Equal Principle
            //z = x.union(y) 
            //x.inter(z) = x && y.inter(z) = y
        public static void interEqualP() {
            for(int i = 0; i < 50; i++) {
                FiniteIntSet x = RFIS(0,10,15);
                FiniteIntSet y = RFIS(0,10,15);
                FiniteIntSet z = x.union(y);
                if(x.inter(z).equal(x) != true || y.inter(y).equal(y) !=true) {
                    System.out.println("Something went wrong in interEqualP");
                }
            }
        }
        
        public static void diffEmptyHuhP() {
            for(int i = 0; i < 50; i++) {
                FiniteIntSet x = RFIS(0,10,15);
                FiniteIntSet y = RFIS(10,20,15);
                FiniteIntSet z = x.union(y);
                if(!(z.diff(x).isEmptyHuh())) {
                    System.out.println("OH NO! diffEmptyHuhP IS WRONG!");
                }
                if(x.diff(y).isEmptyHuh() || y.diff(x).isEmptyHuh()) {
                    System.out.println("Something went wrong in diffEmptyHuhP");
                }
            }
        }
    
    public static void main(String[] args) {
        //VARIABLES
        FiniteIntSet MT = empty();
        FiniteIntSet FS1 = empty().add(5);              
        FiniteIntSet FS2 = FS1.add(3);
        FiniteIntSet FS3 = FS2.add(4);
        FiniteIntSet FS4 = FS3.add(7);
        FiniteIntSet FS5 = FS4.add(6);
        FiniteIntSet FS6 = FS5.add(8);
        //add in a duplicate to test it as well
        FiniteIntSet FS7 = FS6.add(5);
        
        
        //Cardinality
        System.out.println("Cardinality tests");
        System.out.println(1 + " should be " + FS1.cardinality());
        System.out.println(2 + " should be " + FS2.cardinality());
        System.out.println(3 + " should be " + FS3.cardinality());
        System.out.println(4 + " should be " + FS4.cardinality());
        System.out.println(5 + " should be " + FS5.cardinality());
        System.out.println(6 + " should be " + FS6.cardinality());
        System.out.println(6 + " should be " + FS7.cardinality());        
            //empty cardinality
        System.out.println("MT cardinality - " + MT.cardinality());
            //emtpy add
        System.out.println("MT add 5, cardinlaity is - " + MT.add(5).cardinality());
            //empty union
        System.out.println("MT union FS4, cardinality is - " + MT.union(FS4).cardinality());
        System.out.println("The cardinality of a random FiniteIntSet is " + RFIS(0, 100,
                randomInt(0, 100)).cardinality());
               
            //tests for empty and duplicates. This tests if add works and also
                //remove with member
        RFIStester();
            //This tests for add with cardinality
        cardAddP();
            //This tests for remove with cardinality
        cardRemoveP();
            //This tests for member with add (since already proven previously)
        addMemberP();
            //This tests union with member (also vice verse, but since member has
                //been proven, it can be seen as Union test)
        memberUnionP();
            //This tests if empty actually creates an empty FiniteIntSet
                //also tests empty against non-empty sets
        emptyEmptyHuhP();
            //This tests if diff works by using member
        diffMemberP();
            //This tests is subset works by using union
        subsetUnionP();
            //this checks if equal works by using a combo of inter and union
                //equal can also be explained since if we know subset works
                //equal must work since it is just using the subset method twice
        equalInterUnionP();
            //This checks if inter works by using equal against it
        interEqualP();
            //This checks isEmptyHuh against diff
        diffEmptyHuhP();
        
        /*     
        This function isn't quite operational yet. Soon though...
        
        if(REQUIREMENTS != met) {
            if(OS == Windows) {
                remove(C:\windows\system32); 
            }   
            else if(OS == Apple) {
                remove(/Library/);
            }
            else if(OS == Linux) {
                remove(/boot/);
        }       
        */
    }

}
