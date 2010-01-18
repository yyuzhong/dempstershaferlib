package massDistribution;

import interfaces.IMeasure;

public class DiscreteMeasure implements IMeasure {

	/**
	 * The default value assumed if no value is founded for the attribute.
	 */
	public static final DiscreteMeasure NOT_MEASURED = new DiscreteMeasure(
			Double.NaN);

	protected Object value;

	protected DiscreteType discreteType;

	public DiscreteMeasure(Object value) {
		super();
		this.value = value;
	}

	@Override
	public Object getValue() {
		return value;
	}

	@Override
	public void setValue(Object value) {
		this.value = value;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((this.value == null) ? 0 : this.value.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DiscreteMeasure other = (DiscreteMeasure) obj;
		if (this.value == null) {
			if (other.value != null)
				return false;
		} else if (!this.value.equals(other.value))
			return false;
		return true;
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
		if (value.equals(NOT_MEASURED.value))
			return false;
		else
			return true;
	}

	/**
	 * @return the discreteType
	 */
	public DiscreteType getDiscreteType() {
		return this.discreteType;
	}

	/**
	 * @param discreteType
	 *            the discreteType to set
	 */
	public void setDiscreteType(DiscreteType discreteType) {
		this.discreteType = discreteType;
	}
}
