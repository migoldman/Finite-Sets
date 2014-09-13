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
public class Leaf implements FiniteIntSet {
    
   public int cardinality() {
       return 0;
       //The cardinality of a leaf is always 0
   }
   
   public boolean isEmptyHuh() {
       return true;
       //A leaf is always empty
   }
   
   public boolean member(int elt) {
       return false;
       //A leaf cannot be a member of something, so it must return false
   }
   
   public FiniteIntSet add(int elt) {
       return new Branch(this, elt, this);
       //A leaf does not have anything, so we create a branch and put elt as the root
   }
   
   public FiniteIntSet remove(int elt) {
       return this;
       //There is nothing to remove so we return this
   }
   
   public FiniteIntSet union(FiniteIntSet u) {
       return u;     
       //There is nothing to union with t and u, so we just return u
   }
   
   public FiniteIntSet inter(FiniteIntSet u) {
       return this;
       //There is nothing to intersect with t and u, so we will return this
   }
   
   public FiniteIntSet diff(FiniteIntSet u) {
       return u; 
       //There is nothing in t to take the diff of in u, so return u
   }
   
   public boolean equal(FiniteIntSet u) {
       return u.isEmptyHuh();
       //The only way u will be equal to t is if u is empty
   }
   
   public boolean subset(FiniteIntSet u) {
       return true;
       //The empty set is always a subset of any other set
   }
   
}
