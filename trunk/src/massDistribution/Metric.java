/**
 * 
 */
package massDistribution;

import interfaces.IMeasure;
import interfaces.IRange;

import java.util.ArrayList;

/**
 * @author Elisa Costante
 * 
 */
public class Metric {

	private String name;
	private MetricType type;
	private IMeasure measure;
	private IMeasure bestCase;
	private IMeasure worstCase;
	private ArrayList<IRange> validRanges;

	public Metric(String name, MetricType type) {
		super();
		this.name = name;
		this.type = type;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the type
	 */
	public MetricType getType() {
		return this.type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(MetricType type) {
		this.type = type;
	}

	/**
	 * @return the measure
	 */
	public IMeasure getMeasure() {
		return this.measure;
	}

	/**
	 * @param measure
	 *            the measure to set
	 */
	public void setMeasure(IMeasure measure) {
		this.measure = measure;
	}

	/**
	 * @return the validRanges
	 */
	public ArrayList<IRange> getValidRanges() {
		return this.validRanges;
	}

	/**
	 * @param validRanges
	 *            the validRanges to set
	 */
	public void setValidRanges(ArrayList<IRange> validRanges) {
		this.validRanges = validRanges;
	}

	public void addRange(IRange range) {
		if (validRanges == null)
			validRanges = new ArrayList<IRange>();
		validRanges.add(range);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return " metric=" + this.name + " - " + this.type + " - "
				+ this.validRanges + "]";
	}

	/**
	 * @return the bestCase
	 */
	public IMeasure getBestCase() {
		return this.bestCase;
	}

	/**
	 * @param bestCase
	 *            the bestCase to set
	 */
	public void setBestCase(IMeasure bestCase) {
		this.bestCase = bestCase;
	}

	/**
	 * @return the worstCase
	 */
	public IMeasure getWorstCase() {
		return this.worstCase;
	}

	/**
	 * @param worstCase
	 *            the worstCase to set
	 */
	public void setWorstCase(IMeasure worstCase) {
		this.worstCase = worstCase;
	}

}
