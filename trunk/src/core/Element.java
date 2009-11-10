package core;

import java.util.Collection;

/**
 * An {@link Element} belong to PowerSet. It is a Singleton if has only one
 * {@link Hypothesis}
 * 
 * @author Elisa Costante
 * 
 */
public class Element {

	protected Collection<Hypothesis> hypothesies;

	public Element(Collection<Hypothesis> hypothesies) {
		super();
		this.hypothesies = hypothesies;
	}

	public Collection<Hypothesis> getHypothesies() {
		return hypothesies;
	}

	public void setHypothesies(Collection<Hypothesis> hypothesies) {
		this.hypothesies = hypothesies;
	}

	/**
	 * @return true if and only if the element has just one {@link Hypothesis}.
	 */
	public boolean isSingleton() {
		if (hypothesies.size() == 1)
			return true;
		else
			return false;

	}

}
