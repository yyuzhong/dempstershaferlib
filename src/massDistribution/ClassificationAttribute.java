package massDistribution;

import interfaces.IAttribute;

public class ClassificationAttribute extends IAttribute {

	private double weight;

	private RangeValues rangeValues;

	public ClassificationAttribute(double weight, RangeValues rangeValues) {
		this.weight = weight;
		this.rangeValues = rangeValues;
	}

	@Override
	public IMeasure getMeasure() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @return the weight
	 */
	public double getWeight() {
		return weight;
	}

	/**
	 * @param weight
	 *            the weight to set
	 */
	public void setWeight(double weight) {
		this.weight = weight;
	}

	/**
	 * @return the rangeValues
	 */
	public RangeValues getRangeValues() {
		return rangeValues;
	}

	/**
	 * @param rangeValues
	 *            the rangeValues to set
	 */
	public void setRangeValues(RangeValues rangeValues) {
		this.rangeValues = rangeValues;
	}

}
