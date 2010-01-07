package massDistribution;

import interfaces.Attribute;
import interfaces.IRange;

import java.util.ArrayList;
import java.util.Hashtable;

import core.Hypothesis;

/**
 * A {@link ClassificationAttribute} is an {@link Attribute} with a weight and a
 * map with couples Hypothesis-Ranges. This map for each Hypothesis says the
 * interval that the attribute should have to belongs from that Hypothesis.
 * 
 * @author Elisa Costante
 * 
 */

public class ClassificationAttribute extends MeasuredAttribute {

	private double weight;

	private Hashtable<Hypothesis, ArrayList<IRange>> map;

	public ClassificationAttribute(String identifier, double weight,
			Hashtable<Hypothesis, ArrayList<IRange>> classificationRanges) {
		super(identifier);
		this.weight = weight;
		this.map = classificationRanges;
	}

	/**
	 * @param attributeIdentifier
	 */
	public ClassificationAttribute(String attributeIdentifier) {
		super(attributeIdentifier);
	}

	/**
	 * @return the weight
	 */
	public Double getWeight() {
		return weight;
	}

	/**
	 * @param weight
	 *            the weight to set
	 */
	public void setWeight(double weight) {
		this.weight = weight;
	}

	/**
	 * @return the <code>map</code>
	 */
	public Hashtable<Hypothesis, ArrayList<IRange>> getMap() {
		return map;
	}

	/**
	 * @param map
	 *            : <code>map</code> to set.
	 */
	public void setMap(
			Hashtable<Hypothesis, ArrayList<IRange>> classificationRanges) {
		this.map = classificationRanges;
	}

	public ArrayList<IRange> getRanges(Hypothesis hypothesis) {
		if (map != null) {
			ArrayList<IRange> ranges = map.get(hypothesis);
			return ranges;
		}

		else
			return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return identifier + "; weight: " + weight + "; map: " + "{" + map + "}";
	}

}
