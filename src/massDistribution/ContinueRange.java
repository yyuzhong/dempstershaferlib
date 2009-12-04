package massDistribution;

public class ContinueRange implements Range {

	private final static String type = "DISCRETE";
	private ContinueMeasure lowerBound;
	private ContinueMeasure upperBound;

	/**
	 * @return "the type of the range. "DISCRETE" in this case.
	 */
	public String getType() {
		return type;
	}

	/**
	 * @return the lowerBound
	 */
	public ContinueMeasure getLowerBound() {
		return lowerBound;
	}

	/**
	 * @param lowerBound
	 *            the lowerBound to set
	 */
	public void setLowerBound(ContinueMeasure lowerBound) {
		this.lowerBound = lowerBound;
	}

	/**
	 * @return the upperBound
	 */
	public ContinueMeasure getUpperBound() {
		return upperBound;
	}

	/**
	 * @param upperBound
	 *            the upperBound to set
	 */
	public void setUpperBound(ContinueMeasure upperBound) {
		this.upperBound = upperBound;
	}

	@Override
	public boolean contains(IMeasure measuredValue) {
		ContinueMeasure continueMeasuredValue = (ContinueMeasure) measuredValue;
		if (continueMeasuredValue.compareTo(lowerBound) >= 0
				&& continueMeasuredValue.compareTo(upperBound) <= 0)
			return true;
		else
			return false;
	}
}
