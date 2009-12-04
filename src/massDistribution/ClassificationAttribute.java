package massDistribution;

import interfaces.Attribute;

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

public class ClassificationAttribute extends Attribute {

	private double weight;

	private Hashtable<Hypothesis, ArrayList<Range>> map;

	public ClassificationAttribute(double weight,
			Hashtable<Hypothesis, ArrayList<Range>> classificationRanges) {
		this.weight = weight;
		this.map = classificationRanges;
	}

	/**
	 * @return the weight
	 */
	public double getWeight() {
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
	public Hashtable<Hypothesis, ArrayList<Range>> getMAp() {
		return map;
	}

	/**
	 * @param map
	 *            : <code>map</code> to set.
	 */
	public void setMap(
			Hashtable<Hypothesis, ArrayList<Range>> classificationRanges) {
		this.map = classificationRanges;
	}

	public ArrayList<Range> getRanges(Hypothesis hypothesis) {
		if (map != null)
			return map.get(hypothesis);
		else
			return null;
	}

}
