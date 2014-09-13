/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finite.set;

import java.util.Random;

/**
 *
 * @author michaelgoldman
 */

public class Branch implements FiniteIntSet {

    FiniteIntSet combination;
    FiniteIntSet left;
    FiniteIntSet right;
    int data;
    static Random rand = new Random();

    public static int randomInt(int min, int max) {
        return rand.nextInt((max - min) + 1) + min;
    }

    Branch(FiniteIntSet left, int data, FiniteIntSet right) {
        this.left = left;
        this.data = data;
        this.right = right;
    }

    public int cardinality() {
        return left.cardinality() + right.cardinality() + 1;
    }

    public boolean isEmptyHuh() {
        return false;
    }

    public boolean member(int elt) {
        if (data == elt) {
            return true;
        } else if (elt < data) {
            return left.member(elt);
        } else {
            return right.member(elt);
        }
    }

    public FiniteIntSet add(int elt) {
        if (data == elt) {
            return this;
        } else if (elt < data) {
            return new Branch(left.add(elt), data, right);
        } else {
            return new Branch(left, data, right.add(elt));
        }
    }

    public FiniteIntSet remove(int elt) {
        if (data == elt) {
            return left.union(right).remove(elt);
        } else if (elt < data) {
            return new Branch(left.remove(elt), data, right);
        } else {
            return new Branch(left, data, right.remove(elt));
        }
    }

    public FiniteIntSet union(FiniteIntSet u) {
        return left.union(right.union(u)).add(data);
    }

    public FiniteIntSet inter(FiniteIntSet u) {
        if (u.member(data)) {
            return new Branch(left.inter(u), data, right.inter(u));
        } 
        return left.inter(u).union(right.inter(u));     
    }

    public FiniteIntSet diff(FiniteIntSet u) {
        if(u.member(data)) {
            return left.union(right.union(u.remove(data)));
        }     
        return right.diff(u).union(left.diff(u));
    }

    public boolean equal(FiniteIntSet u) {
        return this.subset(u) && u.subset(this);
    }

    public boolean subset(FiniteIntSet u) {
        if (!u.member(data)) {
            return false;
        } else if (left.isEmptyHuh() || right.isEmptyHuh()) {
            return true;
        } else {
            return left.union(right).subset(u);
        }
    }

    public static void main(String[] args) {
        //The Law of Demeter
        //You can only talk to your friends, not your friends' friends

        //mt = empty finiteintset (aka a Leaf)
        //brx = a branch containing one element
        //bry = a branch containing one element (different than br)
        //brrand = a branch containing one random element
        //bb = a branch containing three elements
        //bbrand = a branch containing three random elements
        Leaf mt = new Leaf();
        Leaf mt2 = new Leaf();
        Branch brx = new Branch(new Leaf(), 3, new Leaf());
        Branch bry = new Branch(new Leaf(), 5, new Leaf());
        Branch brz = new Branch(new Leaf(), 5, new Leaf());
        Branch bb = new Branch(new Branch(new Leaf(), 2, new Leaf()), 5, new Branch(new Leaf(), 8, new Leaf()));
        System.out.println("Cardinality of MT is " + mt.cardinality());
        System.out.println("Cardinality of BRX U BRY is " + brx.union(bry).cardinality());
        System.out.println("Cardinality of BRX U BRZ Remove 5 is " + brx.union(bry).remove(5).cardinality());
        System.out.println("is empty equal to empty? " + mt.equal(mt2));
        System.out.println("is empty equal to something? " + mt.equal(bb));        
        System.out.println("Is brx(l,3,l) == bry(l,5,l) " + brx.equal(bry));
        System.out.println("is bry(l,5,l) == brz(l,5,l) " + bry.equal(brz));
        System.out.println("is mt empty? " + mt.isEmptyHuh());
        System.out.println("is brx empty? " + brx.isEmptyHuh());
        System.out.println("inter BRX and BRY " + brx.inter(bry).cardinality());
        System.out.println("inter BRX and BRY " + brx.inter(bry).cardinality());
        System.out.println("bb (2, 5, 8) remove 7 cardinality is " + bb.remove(7).cardinality());
        System.out.println("bb (2, 5, 8) remove 8 cardinality is " + bb.remove(8).cardinality());

        //Cardinality
        //isEmptyHuh
        //member
        //add
        //remove
        //union
        //inter
        //diff
        System.out.println("Cardinality of the diff between bry and bb (should be 2) " + bb.diff(bry).cardinality());
        //equal
        //subset
    }
}

//For all l and elt,
//
//Turn the properties into test cases
//Only use properties as test cases
