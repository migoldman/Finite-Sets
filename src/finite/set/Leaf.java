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
   }
   
   public boolean isEmptyHuh() {
       return true;
   }
   
   public boolean member(int elt) {
       return false;
   }
   
   public FiniteIntSet add(int elt) {
       return new Branch(this, elt, this);
   }
   
   public FiniteIntSet remove(int elt) {
       return this;
   }
   
   public FiniteIntSet union(FiniteIntSet u) {
       return u;     
   }
   
   public FiniteIntSet inter(FiniteIntSet u) {
       return this;
   }
   
   public FiniteIntSet diff(FiniteIntSet u) {
       return u; 
   }
   
   public boolean equal(FiniteIntSet u) {
       return u.isEmptyHuh();
   }
   
   public boolean subset(FiniteIntSet u) {
       return true;
   }
   
}
