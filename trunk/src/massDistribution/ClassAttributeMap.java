package massDistribution;

import java.util.Hashtable;

import core.Hypothesis;

public class ClassAttributeMap {

	/**
	 * The <code>map</code> contains the mapping between {@link Hypothesis} and
	 * {@link RangeValues} of an attribute. This is used as a classification
	 * database that allows one to define from which class(Hypothesis) an
	 * attribute belongs to.
	 */
	private Hashtable<ClassificationAttribute, Hashtable<Hypothesis, RangeValues>> map;

	/**
	 * Returns the range values to which the specified
	 * {@link ClassificationAttribute} is mapped, or null if this map contains
	 * no mapping for the {@link ClassificationAttribute}.
	 * 
	 * @param classificationAttribute
	 * @param hypothesis
	 * @return
	 */
	public RangeValues getRange(
			ClassificationAttribute classificationAttribute,
			Hypothesis hypothesis) {
		Hashtable<Hypothesis, RangeValues> temp = map
				.get(classificationAttribute);
		if (temp != null)
			return temp.get(hypothesis);
		else
			return null;
	}

	public ClassificationAttribute getClassificationAttribute(
			MeasuredAttribute measuredAttribute) {

		return null;
	}
}
