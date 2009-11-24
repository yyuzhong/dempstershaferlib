package core;

import java.util.ArrayList;

/**
 * A FrameOfDIscernemt is a set of Hypothesis. Different Sources must have the
 * same FrameOfDiscernemt to be aggregate.
 * 
 * @author Elisa Costante
 * 
 */
public class FrameOfDiscernment {

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
	 * Compute and returns the power set of the specific FrameOfDiscernment
	 * 
	 * @return the <code>powerSet</code> derived from the FrameOfDiscernment
	 */

	// TODO
	public PowerSet getPowerSet() {

		return null;

	}

	public Element getUniversalSet() {
		return new Element(allHypothesis);
	}

}
