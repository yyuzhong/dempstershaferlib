package core;

import java.util.ArrayList;
import java.util.TreeSet;

/**
 * An {@link Element} is a collection of Hypothesis. It is a Singleton if has
 * only one {@link Hypothesis}.
 * 
 * @author Elisa Costante
 * 
 */
public class Element implements Comparable, Cloneable {

	protected ArrayList<Hypothesis> hypothesies;

	public Element(ArrayList<Hypothesis> hypothesies) {
		super();
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
	 * . An union of two {@link Element} is an {@link Element} that has all the
	 * hypothesies common to both the elemnts <code>element1</code> and
	 * <code>element2</code>.
	 * 
	 * @param element1
	 * @param element2
	 * @return the Union between the bodyOfEvidence or null if the union is
	 *         empty.
	 */
	public static Element getUnion(Element element1, Element element2) {

		TreeSet<Hypothesis> union = new TreeSet<Hypothesis>(element1
				.getHypothesies());
		union.addAll(element2.getHypothesies());

		if (union.size() > 0)

			return new Element(new ArrayList<Hypothesis>(union));
		else
			return null;
	}

	/**
	 * Return true if b is included in a, false otherwise.
	 * 
	 * @param a
	 * @param b
	 * @return true if b is included in a.
	 */
	public static boolean isIncluded(Element a, Element b) {
		// B is included in A if all the element of B belong to A
		// that means the intersection between A and B is equals to B.

		Element intersection = getIntersection(a, b);
		if (intersection != null && intersection.equals(b))
			return true;

		else
			return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 * 
	 * Return true if and only if the two object have the same set of
	 * hypothesies.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this != null && obj != null) {
			Element other = (Element) obj;
			ArrayList<Hypothesis> otherHypothesies = other.getHypothesies();
			if (other.getHypothesies().size() == hypothesies.size()
					&& otherHypothesies.containsAll(hypothesies))
				return true;
			else
				return false;
		}
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

		ArrayList<Hypothesis> elementHypList = element.getHypothesies();
		// String compare1 = element.getHypothesies().toString();
		String compare1 = "";
		for (Hypothesis hypothesis : elementHypList) {
			compare1 = compare1 + hypothesis.getName();
		}
		String compare2 = "";

		for (Hypothesis hypothesis : hypothesies) {
			compare2 = compare2 + hypothesis.getName();
		}
		int value = compare2.compareTo(compare1);

		return value;
	}

	/**
	 * @return the number of the hypothesies of the element
	 */
	public int size() {
		if (hypothesies != null)
			return hypothesies.size();
		else
			return 0;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		ArrayList<Hypothesis> hypClone = new ArrayList<Hypothesis>();
		for (int i = 0; i < hypothesies.size(); i++) {
			Hypothesis hyp = (Hypothesis) hypothesies.get(i).clone();
			hypClone.add(hyp);
		}
		Element clone = new Element(hypClone);
		return clone;
	}

}
