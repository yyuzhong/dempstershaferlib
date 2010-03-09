/**
 * 
 */
package interfaces;

import java.util.ArrayList;

import massDistribution.MetricType;

/**
 * @author Elisa Costante
 * 
 */
public interface IMetric extends Cloneable {
	/**
	 * @return the name
	 */
	public String getName();

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name);

	/**
	 * @return the type
	 */
	public MetricType getType();

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(MetricType type);

	/**
	 * @return the measure
	 */
	public IMeasure getMeasure();

	/**
	 * @param measure
	 *            the measure to set
	 */
	public void setMeasure(IMeasure measure);

	/**
	 * @return the validRanges
	 */
	public ArrayList<IRange> getValidRanges();

	/**
	 * @param validRanges
	 *            the validRanges to set
	 */
	public void setValidRanges(ArrayList<IRange> validRanges);

	public void addRange(IRange range);

	/**
	 * @return the bestCase
	 */
	public IMeasure getBestCase();

	/**
	 * @param bestCase
	 *            the bestCase to set
	 */
	public void setBestCase(IMeasure bestCase);

	/**
	 * @return the worstCase
	 */
	public IMeasure getWorstCase();

	/**
	 * @param worstCase
	 *            the worstCase to set
	 */
	public void setWorstCase(IMeasure worstCase);

	/**
	 * @return the mediumCase
	 */
	public IMeasure getMediumCase();

	/**
	 * @param mediumCase
	 *            the mediumCase to set
	 */
	public void setMediumCase(IMeasure mediumCase);

	/**
	 * Return the number of element which compose this metric for a
	 * discreteType. if no validRanges is defined it returns -1.
	 * 
	 * @return
	 */
	public int getElementsSize();

	/**
	 * Returns true if the {@link IMeasure} is compliant with the Metric, false
	 * otherwise.
	 * 
	 * @param measure
	 * @return true if the <code>measure</code> is compliant with the Metric,
	 *         false otherwise.
	 */
	public boolean isMeasureValidForTheMetric(IMeasure measure);

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#clone()
	 */

	public Object clone() throws CloneNotSupportedException;
}
