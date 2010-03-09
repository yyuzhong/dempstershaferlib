/**
 * 
 */
package interfaces;

import java.util.ArrayList;

import core.FocalElement;

/**
 * @author Elisa Costante
 * 
 */
public interface IMassDistribution extends Cloneable {

	public ArrayList<IFocalElement> getBodyOfEvidence();

	public void setElements(ArrayList<IFocalElement> focalElements);

	public void addElement(FocalElement element);

	/**
	 * Verify if the bodyOfEvidence distribution is valid, hat means the sum of
	 * all bodyOfEvidence it's equal to one.
	 * 
	 * @return true if the bodyOfEvidence distribution is valid, false
	 *         otherwise.
	 */
	public boolean isValid();

	/**
	 * Returns true if and only if all the bpa of the mass is associeted to the
	 * universal set.
	 * 
	 * @return
	 */
	public boolean hasTotalLackOfKnowledge();

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj);

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#clone()
	 */

	public Object clone() throws CloneNotSupportedException;

	/**
	 * @return the sum of the bpa of all the focal element.
	 */
	public double getTotalBpa();

	/**
	 * @return the frameOfDiscernment
	 */
	public IFrameOfDiscernment getFrameOfDiscernment();

	/**
	 * @param frameOfDiscernment
	 *            the frameOfDiscernment to set
	 */
	public void setFrameOfDiscernment(IFrameOfDiscernment frameOfDiscernment);

	/**
	 * Returns the {@link IFocalElement} given a String wich rapresents the
	 * element or null if no match is found.
	 * 
	 * @param element
	 * @return va focal element which match with the element or null if any
	 *         match is found.
	 */
	public IFocalElement getFocalElement(String element);

}
