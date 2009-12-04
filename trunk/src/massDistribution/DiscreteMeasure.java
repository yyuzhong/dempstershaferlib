package massDistribution;

public class DiscreteMeasure implements IMeasure {

	protected Object value;

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
}
