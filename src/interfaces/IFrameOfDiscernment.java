/**
 * 
 */
package interfaces;

import java.util.ArrayList;

import core.FrameOfDiscernment;
import core.PowerSet;

/**
 * @author Elisa Costante
 * 
 */
public interface IFrameOfDiscernment extends Cloneable {

	public ArrayList<IHypothesis> getHipothesies();

	public void setHipothesies(ArrayList<IHypothesis> hypothesies);

	/**
	 * Compute and returns the power set of the specific FrameOfDiscernment or
	 * null if there is any element into the FrameOfDIscernment.<br>
	 * 
	 * A power Set is "The set of all the subsets of a set"
	 * 
	 * Example: If we have a set {a,b,c}:
	 * 
	 * Then a subset of it could be {a} or {b}, or {a,c}, and so on, And {a,b,c}
	 * is also a subset of {a,b,c} And the empty set {} is also a subset of
	 * {a,b,c}
	 * 
	 * So all the subsets together make the Power Set:
	 * 
	 * P(S) = { {}, {a}, {b}, {c}, {a, b}, {a, c}, {b, c}, {a, b, c} }
	 * 
	 * @return the <code>powerSet</code> derived from the FrameOfDiscernment
	 */

	public PowerSet getPowerSet();

	/**
	 * Returns the universal set of this {@link FrameOfDiscernment}.
	 * 
	 * @return
	 */
	public IElement getUniversalSet();

	public Object clone() throws CloneNotSupportedException;

}
