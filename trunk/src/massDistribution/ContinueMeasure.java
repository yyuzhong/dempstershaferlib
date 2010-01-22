package massDistribution;

import interfaces.IMeasure;

@SuppressWarnings("unchecked")
public class ContinueMeasure implements IMeasure, Comparable<IMeasure> {

	/**
	 * The default value assumed if no value is founded for the attribute.
	 */
	public static final ContinueMeasure NOT_MEASURED = new ContinueMeasure(
			Double.NaN);

	protected Comparable value;

	public ContinueMeasure(Comparable value) {
		super();
		this.value = value;
	}

	@Override
	public Object getValue() {

		return this.value;
	}

	@Override
	public void setValue(Object value) {
		this.value = (Double) value;

	}

	@Override
	public int compareTo(IMeasure o) {
		return value.compareTo((Double) o.getValue());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		if (hasMeasuredValue())
			return "" + value;
		else
			return "NOT_MEASURED";
	}

	@Override
	public boolean hasMeasuredValue() {
		return !value.equals(NOT_MEASURED);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#clone()
	 */
	@Override
	public Object clone() throws CloneNotSupportedException {
		if (this != null && this.value != null) {
			ContinueMeasure cloned = new ContinueMeasure(this.value);
			return cloned;
		} else
			return this;
	}
}
