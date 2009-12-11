package massDistribution;

import interfaces.IMeasure;

public interface Range {

	/**
	 * Return a {@link String} which represents the type of the Range.
	 * 
	 * @return the type of the Range.
	 */
	public String getType();

	/**
	 * Returns true if the range contains the <code>measuredValue</code>, false
	 * otherwise. This method must be implemented correctly depending on the
	 * type of the range (ContinueRange or DiscreteRange).
	 * 
	 * @param measuredValue
	 *            : the value one wants to test if it is present in the Range.
	 * @return true if the range contains the <code>measuredValue</code>, false
	 *         otherwise.
	 */
	public boolean containsMeasure(IMeasure measuredValue);

}
