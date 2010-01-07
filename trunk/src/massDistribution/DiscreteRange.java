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

	public void addElement(Object value) {
		if (rangeElements == null)
			rangeElements = new ArrayList<Object>();
		rangeElements.add(value);
	}

	@Override
	public boolean containsValue(Object value) {
		if (rangeElements != null) {
			return rangeElements.contains(value);

		} else
			return false;
	}

	@Override
	public String toString() {
		if (rangeElements == null || rangeElements.size() == 0) {
			return "{}";
		} else {
			String range = "{";
			for (int i = 0; i < rangeElements.size(); i++) {
				range = range + rangeElements.get(i).toString();
				if (i < rangeElements.size() - 1)
					range = range + ";";
			}
			range = range + "}";
			return range;

		}
	}

	/**
	 * @return the rangeElements
	 */
	public ArrayList<Object> getRangeElements() {
		return this.rangeElements;
	}

	/**
	 * @param rangeElements
	 *            the rangeElements to set
	 */
	public void setRangeElements(ArrayList<Object> rangeElements) {
		this.rangeElements = rangeElements;
	}

	@Override
	public boolean include(IRange otherRange) {
		if (otherRange instanceof DiscreteRange) {
			DiscreteRange other = (DiscreteRange) otherRange;
			ArrayList<Object> elements = other.getRangeElements();
			for (Object element : elements) {
				if (!this.containsValue(element))
					return false;

			}
			return true;
		}
		return false;
	}

}
