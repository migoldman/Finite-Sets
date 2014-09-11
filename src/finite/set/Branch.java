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
public class Branch implements FiniteIntSet {
    
    FiniteIntSet left;
    FiniteIntSet right;
    int data;
    
    public int cardinality() {
        return left.cardinality() + right.cardinality() + 1; 
    }
    
    public boolean isEmptyHuh() {
        return false;
    }
    
    public boolean member(int elt) {
        if(data == elt) {
            return true; }
        else if(elt < data) {
            return left.member(elt);             
        }  
        else {
            return right.member(elt);
        }
    }
    
    public 
}
