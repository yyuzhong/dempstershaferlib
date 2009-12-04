package massDistribution;

public class ContinueMeasure implements IMeasure, Comparable<IMeasure> {

	protected Comparable value;

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
		return this.compareTo((ContinueMeasure) o);
	}
}
