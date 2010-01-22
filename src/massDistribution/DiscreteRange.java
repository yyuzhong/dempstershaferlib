package massDistribution;

import interfaces.IMeasure;
import interfaces.IRange;

import java.util.ArrayList;

public class DiscreteRange implements IRange {

	private ArrayList<DiscreteMeasure> rangeElements = new ArrayList<DiscreteMeasure>();

	private static final MeasureType type = MeasureType.DISCRETE;

	/**
	 * @return "the type of the range. "CONTINUE" in this case.
	 */
	public MeasureType getType() {
		return type;
	}

	public void addElement(DiscreteMeasure value) {
		if (rangeElements == null)
			rangeElements = new ArrayList<DiscreteMeasure>();
		rangeElements.add(value);
	}

	/**
	 * Returns true if this cotains the {@link DiscreteMeasure} passed as
	 * argoument.
	 * 
	 * @see interfaces.IRange#containsValue(java.lang.Object)
	 */
	@Override
	public boolean containsValue(IMeasure measure) {
		if (measure != null && rangeElements != null) {
			DiscreteMeasure discreteMeasure = (DiscreteMeasure) measure;
			return rangeElements.contains(discreteMeasure);

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
	public ArrayList<DiscreteMeasure> getRangeElements() {
		return this.rangeElements;
	}

	/**
	 * @param rangeElements
	 *            the rangeElements to set
	 */
	public void setRangeElements(ArrayList<DiscreteMeasure> rangeElements) {
		this.rangeElements = rangeElements;
	}

	@Override
	public boolean include(IRange otherRange) {
		if (otherRange instanceof DiscreteRange) {
			DiscreteRange other = (DiscreteRange) otherRange;
			ArrayList<DiscreteMeasure> elements = other.getRangeElements();
			for (DiscreteMeasure element : elements) {
				if (!this.containsValue(element))
					return false;

			}
			return true;
		}
		return false;
	}

	@Override
	public Object clone() {
		if (this != null) {
			DiscreteRange cloned = new DiscreteRange();
			if (rangeElements != null) {
				for (DiscreteMeasure measure : rangeElements) {
					try {
						cloned.addElement((DiscreteMeasure) measure.clone());
					} catch (CloneNotSupportedException e) {
						e.printStackTrace();
					}

				}
			}
			return cloned;
		} else
			return null;
	}

}
