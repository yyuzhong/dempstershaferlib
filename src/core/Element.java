package core;

import java.util.ArrayList;
import java.util.TreeSet;

/**
 * An {@link Element} belong to PowerSet. It is a Singleton if has only one
 * {@link Hypothesis}
 * 
 * @author Elisa Costante
 * 
 */
public class Element implements Comparable {

	protected ArrayList<Hypothesis> hypothesies;

	private double bpa;
	private double belief;
	private double plausability;

	public Element(ArrayList<Hypothesis> hypothesies, double bpa) {
		super();
		this.bpa = bpa;
		this.hypothesies = hypothesies;
	}

	public Element(ArrayList<Hypothesis> hypothesies) {
		super();
		this.hypothesies = hypothesies;
	}

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
	 * Returns the Intersection between <code>element1</code> and
	 * <code>element2</code>.
	 * 
	 * @param element1
	 * @param element2
	 * @return the intersection or null if the intersection is empty.
	 */

	public static Element getIntersection(Element element1, Element element2) {

		ArrayList<Hypothesis> h1Array = element1.getHypothesies();
		ArrayList<Hypothesis> h2Array = element2.getHypothesies();
		ArrayList<Hypothesis> intersectionHypothesies = new ArrayList<Hypothesis>();

		Element intersectionElement = null;

		for (int i = 0; i < h1Array.size(); i++) {
			Hypothesis h1 = h1Array.get(i);
			for (int j = 0; j < h2Array.size(); j++) {
				Hypothesis h2 = h2Array.get(j);
				if (h1.equals(h2))
					intersectionHypothesies.add(h1);
			}

		}
		if (intersectionHypothesies.size() > 0) {
			intersectionElement = new Element(intersectionHypothesies);
		}

		return intersectionElement;
	}

	/**
	 * Returns the Union between <code>element1</code> and <code>element2</code>
	 * .
	 * 
	 * @param element1
	 * @param element2
	 * @return the Union between the elements or null if the union is empty.
	 */
	public static Element getUnion(Element element1, Element element2) {
		return null;
	}

	/**
	 * Return the union of two elements list.
	 * 
	 * @param el1
	 * @param el2
	 * @return the union of two elements list.
	 */
	public static ArrayList<Element> getMassUnionElement(
			ArrayList<Element> elementList1, ArrayList<Element> elementList2) {

		TreeSet<Element> union = new TreeSet<Element>(elementList1);

		union.addAll(new TreeSet<Element>(elementList2));

		return new ArrayList<Element>(union);
	}

	@Override
	public boolean equals(Object obj) {
		Element other = (Element) obj;
		ArrayList<Hypothesis> otherHypothesies = other.getHypothesies();
		if (otherHypothesies.equals(hypothesies))
			return true;
		else
			return false;
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

	@Override
	public int compareTo(Object o) {
		Element element = (Element) o;
		String compare1 = element.getHypothesies().toString();
		String compare2 = hypothesies.toString();

		return compare1.compareTo(compare2);
	}

}
