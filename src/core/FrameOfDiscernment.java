package core;

import java.util.ArrayList;

import utilities.PowerSetIterator;

/**
 * A FrameOfDIscernemt is a set of Hypothesis. Different Sources must have the
 * same FrameOfDiscernemt to be aggregate.
 * 
 * @author Elisa Costante
 * 
 */
public class FrameOfDiscernment implements Cloneable {

	protected ArrayList<Hypothesis> allHypothesis;

	public FrameOfDiscernment(ArrayList<Hypothesis> hypothesies) {
		super();
		this.allHypothesis = hypothesies;
	}

	public ArrayList<Hypothesis> getHipothesies() {
		return allHypothesis;
	}

	public void setHipothesies(ArrayList<Hypothesis> hypothesies) {
		this.allHypothesis = hypothesies;
	}

	/**
	 * Compute and returns the power set of the specific FrameOfDiscernment or
	 * null if there is any element into the FrameOfDIscernment.<br>
	 * 
	 * A power Set is "The set of all the subsets of a set"
	 * 
	 * Example: If we have a set {a,b,c}:
	 * 
	 * Then a subset of it could be {a} or {b}, or {a,c}, and so on, And {a,b,c}
	 * is also a subset of {a,b,c} And the empty set {} is also a subset of
	 * {a,b,c}
	 * 
	 * So all the subsets together make the Power Set:
	 * 
	 * P(S) = { {}, {a}, {b}, {c}, {a, b}, {a, c}, {b, c}, {a, b, c} }
	 * 
	 * @return the <code>powerSet</code> derived from the FrameOfDiscernment
	 */

	@SuppressWarnings("unchecked")
	public PowerSet getPowerSet() {

		int n = allHypothesis.size();
		if (n > 0) {
			PowerSet powerSet = new PowerSet();

			PowerSetIterator powerSetIter = new PowerSetIterator(allHypothesis);
			while (powerSetIter.hasNext()) {
				ArrayList<Hypothesis> object = (ArrayList<Hypothesis>) powerSetIter
						.next();
				powerSet.addElement(new Element(object));

			}
			powerSet.addElement(new Element(null));
			return powerSet;

		} else {
			return null;

		}

	}

	public Element getUniversalSet() {
		return new Element(allHypothesis);
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		if (this != null) {
			if (this.allHypothesis != null) {
				ArrayList<Hypothesis> clonedHypList = new ArrayList<Hypothesis>();
				for (Hypothesis hypothesis : clonedHypList) {
					clonedHypList.add((Hypothesis) hypothesis.clone());
				}
				return new FrameOfDiscernment(clonedHypList);

			}
		}
		return null;
	}

}
