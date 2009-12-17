package massDistribution;

import interfaces.IRange;

import java.util.ArrayList;

public class DiscreteRange implements IRange {

	private ArrayList<Object> rangeElements = new ArrayList<Object>();

	private static final MeasureType type = MeasureType.DISCRETE;

	/**
	 * @return "the type of the range. "CONTINUE" in this case.
	 */
	public MeasureType getType() {
		return type;
	}

	public void addElement(DiscreteMeasure discreteMeasure) {
		rangeElements.add(discreteMeasure.getValue());
	}

	@Override
	public boolean containsValue(Object value) {
		if (rangeElements != null) {
			return rangeElements.contains(value);

		} else
			return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DiscreteRange: {rangeElements=" + rangeElements + "}";
	}

}
