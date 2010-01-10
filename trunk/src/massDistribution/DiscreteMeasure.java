package massDistribution;

import interfaces.IMeasure;

public class DiscreteMeasure implements IMeasure {

	/**
	 * The default value assumed if no value is founded for the attribute.
	 */
	public static final DiscreteMeasure NOT_MEASURED = new DiscreteMeasure(
			Double.NaN);

	protected Object value;

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

	@Override
	public boolean equals(Object obj) {
		DiscreteMeasure other = (DiscreteMeasure) obj;
		return value.equals(other.getValue());

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
}
