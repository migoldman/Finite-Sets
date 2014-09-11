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
   
}
