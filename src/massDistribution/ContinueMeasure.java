package massDistribution;

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
		this.value = (Comparable) value;

	}

	@Override
	public int compareTo(IMeasure o) {
		return value.compareTo((ContinueMeasure) o.getValue());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "" + value;
	}

	@Override
	public boolean hasMeasuredValue() {
		return !value.equals(NOT_MEASURED);
	}

}
