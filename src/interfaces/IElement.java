/**
 * 
 */
package interfaces;

import java.util.ArrayList;

import core.Element;
import core.Hypothesis;

/**
 * @author Elisa Costante
 * 
 */
public interface IElement extends Cloneable, Comparable<IElement> {

	public ArrayList<IHypothesis> getHypothesies();

	public void setHypothesies(ArrayList<IHypothesis> hypothesies);

	public void addHypothesis(IHypothesis hypothesis);

	/**
	 * @return true if and only if the element has just one {@link Hypothesis}.
	 */
	public boolean isSingleton();

	/**
	 * @return the number of the hypothesies of the {@link Element}
	 */
	public int size();

	/**
	 * @return true if and only if the set of hypothesis is null, false
	 *         otherwise.
	 */
	public boolean isEmptySet();

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#clone()
	 */
	public Object clone() throws CloneNotSupportedException;

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(IElement o);

}
