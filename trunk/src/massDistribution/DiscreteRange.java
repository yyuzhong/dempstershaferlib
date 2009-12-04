package massDistribution;

import java.util.ArrayList;

public class DiscreteRange implements Range {

	private ArrayList<IMeasure> discreteRange = new ArrayList<IMeasure>();

	private static final String type = "CONTINUE";

	/**
	 * @return "the type of the range. "CONTINUE" in this case.
	 */
	public String getType() {
		return type;
	}

	public void addElement(DiscreteMeasure discreteMeasure) {
		discreteRange.add(discreteMeasure);
	}

	@Override
	public boolean contains(IMeasure measuredValue) {
		if (discreteRange != null) {
			return discreteRange.contains(measuredValue);

		} else
			return false;
	}

}
