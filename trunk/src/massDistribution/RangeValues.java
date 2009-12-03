package massDistribution;

import java.util.ArrayList;

public class RangeValues {

	private ArrayList<IMeasure> ranges;

	/**
	 * @return the ranges
	 */
	public ArrayList<IMeasure> getRanges() {
		return ranges;
	}

	/**
	 * @param ranges
	 *            the ranges to set
	 */
	public void setRanges(ArrayList<IMeasure> ranges) {
		this.ranges = ranges;
	}

	/**
	 * @return "Continue" if the elements of <code>ranges</code> are instance of
	 *         type of {@link ContinueMeasure}, "Discrete" if they are instance
	 *         of {@link DiscreteMeasure}, null otherwise.
	 */
	public String getType() {
		if (ranges != null && ranges.size() > 0) {
			if (ranges.get(0) instanceof ContinueMeasure)
				return "Continue";
			else if (ranges.get(0) instanceof DiscreteMeasure)
				return "Discrete";
		}
		return null;
	}

}
