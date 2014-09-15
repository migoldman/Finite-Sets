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
public class Testers {

    public static FiniteIntSet empty() {
        return new Leaf();
    }
    
    public static void main(String[] args) {


        //brx = a branch containing one element
        //bry = a branch containing one element (different than br)
        //brrand = a branch containing one random element
        //bb = a branch containing three elements
        //bbrand = a branch containing three random elements
        Leaf mt = new Leaf();
        Leaf mt2 = new Leaf();
        Branch brx = new Branch(new Leaf(), 3, new Leaf());
        Branch bry = new Branch(new Leaf(), 5, new Leaf());
        Branch bryy = new Branch(new Leaf(), 2, new Leaf());
        Branch brz = new Branch(new Leaf(), 5, new Leaf());
        Branch bb = new Branch(new Branch(new Leaf(), 2, new Leaf()), 5, new Branch(new Leaf(), 8, new Leaf()));
        
        //Cardinality
        System.out.println("Cardinality of MT is " + mt.cardinality());
        System.out.println("Cardinality of BB is " + bb.cardinality());
        System.out.println("Cardinality of BRY is " + brx.cardinality());
        System.out.println();
        
        //isEmptyHuh
        System.out.println("is mt empty? " + mt.isEmptyHuh());
        System.out.println("is brx empty? " + brx.isEmptyHuh());
        System.out.println();
        
        //member
        System.out.println("is 5 in bb? " + bb.member(5));
        System.out.println("is 4 in bb? " + bb.member(4));
        System.out.println();
        
        //add
        System.out.println("5 is now in empty? card should be 1 " + mt.add(5).cardinality());
        System.out.println("4 is now in bb. Card should be 4 " + bb.add(4).cardinality());
        System.out.println("5 is now in bb. Card should be 3 " + bb.add(5).cardinality());
        System.out.println();
        
        //remove
        System.out.println("Cardinality of BRX U BRZ Remove 5 is " + brx.union(bry).remove(5).cardinality());
        System.out.println("bb (2, 5, 8) remove 7 cardinality is " + bb.remove(7).cardinality());
        System.out.println("bb (2, 5, 8) remove 8 cardinality is " + bb.remove(8).cardinality());
        System.out.println();
        
        //union
            //x.union(y).subset(s) = x.subset(s) && y.subset(s)
        System.out.println("Cardinality of BRX U BRY is " + brx.union(bry).cardinality());
        System.out.println("Cardinality of BB U BRY is " + bb.union(bry).cardinality());
        System.out.println();
        
        //inter
        System.out.println("inter BRX and BRY " + brx.inter(bry).cardinality());
        System.out.println("inter BB and BRY " + bb.inter(bry).cardinality());
        System.out.println("inter BB and BRY U BRYY " + bb.inter(bry.union(bryy)).cardinality());
        System.out.println("inter BB and BRY U BRX " + bb.inter(bry.union(brx)).cardinality());
        System.out.println();
        
        //diff 
        System.out.println("Cardinality of the diff between bryy (2) and bb (2 5 8) " + bryy.diff(bb).cardinality());
        System.out.println("Cardinality of the diff between bb (2 5 8) and bryy (2) " + bb.diff(bryy).cardinality());

        //equal correct if subset is correct
        System.out.println("is empty equal to empty? " + mt.equal(mt2));
        System.out.println("is empty equal to something? " + mt.equal(bb));
        System.out.println("Is brx(l,3,l) == bry(l,5,l) " + brx.equal(bry));
        System.out.println("is bry(l,5,l) == brz(l,5,l) " + bry.equal(brz));
        System.out.println();

        //subset believe this is correct
        System.out.println("is bb a subset of bry? false - " + bb.subset(bry));
        System.out.println("is bry a subset of bb? true - " + bry.subset(bb));
        
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
