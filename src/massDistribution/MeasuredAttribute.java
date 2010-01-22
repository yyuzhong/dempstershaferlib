/**
 * 
 */
package massDistribution;

import interfaces.Attribute;

/**
 * A {@link MeasuredAttribute} has a Metric (IMeasure) an Identifier and a list
 * of valid iRanges.
 * 
 * @author Elisa Costante
 * 
 */
public class MeasuredAttribute extends Attribute {

	private Metric metric;

	/**
	 * @param identifier
	 */
	public MeasuredAttribute(String identifier) {
		super(identifier);
	}

	/**
	 * @return the metric
	 */
	public Metric getMetric() {
		return this.metric;
	}

	/**
	 * @param metric
	 *            the metric to set
	 */
	public void setMetric(Metric metric) {
		this.metric = metric;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "{" + this.identifier + " - " + this.metric + ", " + "}";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#clone()
	 */
	@Override
	public Object clone() throws CloneNotSupportedException {
		if (this.identifier != null) {
			MeasuredAttribute cloned = new MeasuredAttribute(this.identifier);
			if (this.getMetric() != null) {
				cloned.setMetric((Metric) metric.clone());
			}
			return cloned;
		}
		return null;
	}

}
