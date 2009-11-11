package core;

import java.util.ArrayList;

/**
 * An {@link Element} belong to PowerSet. It is a Singleton if has only one
 * {@link Hypothesis}
 * 
 * @author Elisa Costante
 * 
 */
public class Element {

	protected ArrayList<Hypothesis> hypothesies;

	private double bpa;
	private double belief;
	private double plausability;

	public double getBpa() {
		return bpa;
	}

	public void setBpa(double bpa) {
		this.bpa = bpa;
	}

	public double getBelief() {
		return belief;
	}

	public void setBelief(double belief) {
		this.belief = belief;
	}

	public double getPlausability() {
		return plausability;
	}

	public void setPlausability(double plausability) {
		this.plausability = plausability;
	}

	public Element(ArrayList<Hypothesis> hypothesies, double bpa) {
		super();
		this.bpa = bpa;
		this.hypothesies = hypothesies;
	}

	public ArrayList<Hypothesis> getHypothesies() {
		return hypothesies;
	}

	public void setHypothesies(ArrayList<Hypothesis> hypothesies) {
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

	/**
	 * Returns the Intersection between <code>element</code> and
	 * <code>this</code>.
	 * 
	 * @param element
	 * @return the intersection or null if the intersection is null.
	 */
	public Element getIntersection(Element element) {
		return null;
	}

	/**
	 * Returns the Union between <code>element</code> and <code>this</code>.
	 * 
	 * @param element
	 * @return the Union between the elements or null if the union is empty.
	 */
	public Element getUnion(Element element) {
		return null;
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}

	@Override
	public String toString() {
		String elementToString = "{";
		for (int i = 0; i < hypothesies.size(); i++) {
			Hypothesis hypothesis = (Hypothesis) hypothesies.get(i);
			elementToString = elementToString + hypothesis.getName();
			if (i != (hypothesies.size() - 1)) {
				elementToString = elementToString + ",";
			}
		}
		elementToString = elementToString + "}";

		return elementToString;
	}

}
