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
public interface FiniteIntSet {
    int cardinality();
    boolean isEmptyHuh();
    boolean member(int elt);
    FiniteIntSet add(int elt);
    FiniteIntSet remove(int elt);
    FiniteIntSet union(FiniteIntSet u);
    FiniteIntSet inter(FiniteIntSet u);
    FiniteIntSet diff(FiniteIntSet u);
    boolean equal(FiniteIntSet u);
    boolean subset(FiniteIntSet u);
}
