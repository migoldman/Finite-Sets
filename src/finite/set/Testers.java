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
    static FiniteIntSet rfisHelp = empty(); 
    FiniteIntSet RFIS = RFIS(); //rfis = Random FiniteIntSet 
    
    
    public boolean CoinFlip() {
        return rand.nextInt()<.4; //that being said, slightly biased coinflip 
    }
    
    public int randomInt(int min, int max) {
        return rand.nextInt((max - min) + 1) + min;
    }
    
    
    public FiniteIntSet RFIS() {
        int temp = randomInt(0, 20);       
        for(int i = 0; i < 20; i++) {           
            if(CoinFlip() == true && rfisHelp.member(temp) == false) {
                rfisHelp.add(temp);
                    i++;
            }
            else {
                    i++;                      
            }
        }
        return rfisHelp;
    }
    
    public static FiniteIntSet empty() {
        return new Leaf();
    }
    
    public static void main(String[] args) {
        //MT = empty FiniteIntSet
        //FS = FiniteIntSet
        FiniteIntSet MT = empty();
        FiniteIntSet FS = empty().add(5);
        
        //Cardinality
        
        //empty testers
            //empty cardinality
        System.out.println("MT cardinality - " + MT.cardinality());
            //emtpy add
        System.out.println("MT add 5, cardinlaity is - " + MT.add(5).cardinality());
            //empty union
        System.out.println("MT union FS, cardinality is - " + MT.union(FS).cardinality());
        System.out.println("RFIS - " + RFIS.cardinality()); //error line 
        //'Cannot reference a nonstatic to in a static. How should I go about fixing this?
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
