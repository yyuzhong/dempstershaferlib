package massDistribution;

import interfaces.IMeasure;

import java.util.ArrayList;

public class DiscreteRange implements Range {

	private ArrayList<Object> rangeElements = new ArrayList<Object>();

	private static final String type = "CONTINUE";

	/**
	 * @return "the type of the range. "CONTINUE" in this case.
	 */
	public String getType() {
		return type;
	}

	public void addElement(DiscreteMeasure discreteMeasure) {
		rangeElements.add(discreteMeasure.getValue());
	}

	@Override
	public boolean containsMeasure(IMeasure measuredValue) {
		if (rangeElements != null) {
			return rangeElements.contains(measuredValue.getValue());

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
