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
            return left.diff(u.remove(data)).union(right.diff(u.remove(data)));
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

}
