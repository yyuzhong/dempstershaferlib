package core;

import java.util.Collection;

/**
 * A FrameOfDIscernemt is a set of Hypothesis. Different Sources must have the
 * same FrameOfDiscernemt to be aggregate.
 * 
 * @author Elisa Costante
 * 
 */
public class FrameOfDiscernment {

	protected Collection<Hypothesis> hipothesies;

	public FrameOfDiscernment(Collection<Hypothesis> hipothesies) {
		super();
		this.hipothesies = hipothesies;
	}

	public Collection<Hypothesis> getHipothesies() {
		return hipothesies;
	}

	public void setHipothesies(Collection<Hypothesis> hipothesies) {
		this.hipothesies = hipothesies;
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

}
