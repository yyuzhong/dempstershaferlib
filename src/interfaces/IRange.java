package interfaces;

import massDistribution.MeasureType;

public interface IRange {

	/**
	 * Return a {@link MeasureType} which represents the type of the IRange.
	 * 
	 * @return the type of the IRange.
	 */
	public MeasureType getType();

	/**
	 * Returns true if the range contains the <code>value</code>, false
	 * otherwise. This method must be implemented correctly depending on the
	 * type of the range (ContinueRange or DiscreteRange).
	 * 
	 * @param measure
	 *            : the one wants to test if it is present in the IRange.
	 * @return true if the range contains the <code>measuredValue</code>, false
	 *         otherwise.
	 */
	public boolean containsValue(IMeasure measure);

	/**
	 * Return true if and only if this range includes the
	 * <code>otherRange</code>.
	 * 
	 * @param otherRange
	 *            : the range one wants to check if it is included.
	 * @return true if <code>otherRange</code> is included in this range, false
	 *         otherwise.
	 */
	public boolean include(IRange otherRange);

}
