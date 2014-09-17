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
    public static void RFIStester() {
        for(int i =0; i < 50; i++) {
            FiniteIntSet holder = RFIS(0,10,30);
            //it goes from 0...10, so 11 total ints
            if(holder.cardinality() > 11 || holder.cardinality() < 0) {
                System.out.println("FAILURE");
            }
            else if(holder.remove(7).member(7)) {
                System.out.println("FAILURE");
            }
        }
    }
            
        //Cardinality and Add Principle
            //t.add(elt).cardinality <==> t.cardinality() || t.cardinality()++
        public static void cardAddP() {
            for(int i = 0; i < 50; i++) {
                FiniteIntSet holder = RFIS(0, 15, 10);
                int temp = holder.cardinality();
                if(holder.add(14).cardinality() == temp ||
                        holder.cardinality() == temp++)   {                 
                }
                else {
                    System.out.println("Something went wrong with cardAddP");
                }
            }
        }
        
        //Add Member Principle
            //holder.add(x).member(y) == true <==> 
                //x = y || holder.member(y) = true;
        public static void addMemberP() {
            for(int i = 0; i < 50; i++) {
                int x = randomInt(0,15);
                int y = randomInt(0, 15);
                FiniteIntSet holder = RFIS(0, 15, 10);
                if(holder.add(x).member(y)) {
                    if(x == y) {
                        //x = y
                    }
                    else if(holder.member(y)) {    
                        //y is already a member
                    }
                    else {
                        System.out.println("Something went wrong with addMemberP");
                    }
                }
                else {
                    //x!=y and y isn't a member of t"
                }
            }
        }
        
        //Member Remove Principle
            //holder.union(x).member(y) == true <==> holder.member(y) = true ||
                //x.member(y) = true
        public static void memberUnionP() {
            for(int i = 0; i < 50; i++) {
                FiniteIntSet holder = RFIS(0, 15, 10);
                FiniteIntSet x = RFIS(0, 15, 10);
                int y = randomInt(0, 15);
                if(holder.union(x).member(y) == true) {
                    if(holder.member(y) == true) {                 
                    }
                    else if (x.member(y) == true) {                       
                    }
                    else {
                        System.out.println("Error with memberUnionP");
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
    
    public static void main(String[] args) {
        //VARIABLES
        FiniteIntSet MT = empty();
        FiniteIntSet FS1 = empty().add(5);              
        FiniteIntSet FS2 = FS1.add(3);
        FiniteIntSet FS3 = FS2.add(4);
        FiniteIntSet FS4 = FS3.add(7);
        FiniteIntSet FS5 = FS4.add(6);
        FiniteIntSet FS6 = FS5.add(8);
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
       
        System.out.println(RFIS(0 ,10,30).cardinality());
        
        RFIStester();
        cardAddP();
        addMemberP();
        memberUnionP();
        emptyEmptyHuhP();
        
        
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
        
        //test is lessthan root
    }

}
