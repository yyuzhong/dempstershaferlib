/**
 * 
 */
package massDistribution;

import interfaces.IMeasure;
import interfaces.IMetric;
import interfaces.IRange;

import java.util.ArrayList;

/**
 * @author Elisa Costante
 * 
 */
public class Metric implements IMetric {

	private String name;
	private MetricType type;
	private IMeasure measure;
	private IMeasure bestCase;
	private IMeasure worstCase;
	private IMeasure mediumCase;
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
		return this.name;//
		// ": " + this.validRanges;
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

	/**
	 * @return the mediumCase
	 */
	public IMeasure getMediumCase() {
		return this.mediumCase;
	}

	/**
	 * @param mediumCase
	 *            the mediumCase to set
	 */
	public void setMediumCase(IMeasure mediumCase) {
		this.mediumCase = mediumCase;
	}

	/**
	 * Return the number of element which compose this metric for a
	 * discreteType. if no validRanges is defined it returns -1.
	 * 
	 * @return
	 */
	public int getElementsSize() {
		if (this.type != null && this.type.equals(MetricType.DISCRETE)
				&& validRanges != null) {
			int size = 0;
			for (int i = 0; i < validRanges.size(); i++) {
				DiscreteRange range = (DiscreteRange) validRanges.get(i);
				int elementsSize = range.getRangeElements().size();
				size = size + elementsSize;
			}
			return size;
		} else
			return -1;
	}

	/**
	 * Returns true if the {@link IMeasure} is compliant with the Metric, false
	 * otherwise.
	 * 
	 * @param measure
	 * @return true if the <code>measure</code> is compliant with the Metric,
	 *         false otherwise.
	 */
	public boolean isMeasureValidForTheMetric(IMeasure measure) {
		if (validRanges != null && measure != null) {
			if (this.type.equals(MetricType.INTEGER)
					|| this.type.equals(MetricType.REAL))
				measure = (ContinueMeasure) measure;
			else
				measure = (DiscreteMeasure) measure;

			for (int i = 0; i < validRanges.size(); i++) {
				IRange range = validRanges.get(i);
				if (range.containsValue(measure))
					return true;
			}

		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#clone()
	 */
	@Override
	public Object clone() throws CloneNotSupportedException {
		if (this != null) {
			Metric cloned = new Metric(this.name, this.type);
			if (this.bestCase != null)
				cloned.setBestCase((IMeasure) bestCase.clone());
			if (this.mediumCase != null)
				cloned.setMediumCase((IMeasure) mediumCase.clone());
			if (this.worstCase != null)
				cloned.setWorstCase((IMeasure) worstCase.clone());
			if (this.measure != null)
				cloned.setMeasure((IMeasure) measure.clone());
			if (validRanges != null) {
				for (IRange range : validRanges) {
					cloned.addRange((IRange) range.clone());
				}

			}
			return cloned;
		}
		return null;
	}
}
