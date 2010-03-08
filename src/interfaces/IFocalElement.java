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
public interface IFocalElement extends Cloneable, Comparable<FocalElement> {

	/**
	 * Set the Value of bpa. It mantains only a specified number of decimal
	 * digit
	 * 
	 * @param bpa
	 */
	public void setBpa(Double bpa);

	/**
	 * @return the bpa
	 */
	public double getBpa();

	/**
	 * Returns the value of the belief of the element or {@link Double}.NaN if
	 * no bpa is set.
	 * 
	 * @return the belief value
	 */
	public Double getBelief();

	/**
	 * Returns the value of the plausability of the element or {@link Double}
	 * .NaN if no bpa is set.
	 * 
	 * @return the belief value
	 */
	public Double getPlausability();

	/**
	 * @return the element
	 */
	public IElement getElement();

	/**
	 * @param element
	 *            the element to set
	 */
	public void setElement(IElement element);

	public double getUncertainty();

	/**
	 * @param massDistribution
	 *            the massDistribution to set
	 */
	public void setBodyOfEvidence(ArrayList<IFocalElement> bodyOfEvidence);

	public Object clone() throws CloneNotSupportedException;

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(FocalElement o);

}
