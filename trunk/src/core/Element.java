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

	public Element() {
		super();
	}

	public ArrayList<Hypothesis> getHypothesies() {
		return hypothesies;
	}

	public void setHypothesies(ArrayList<Hypothesis> hypothesies) {
		this.hypothesies = hypothesies;
	}

	public void addHypothesis(Hypothesis hypothesis) {
		if (hypothesies == null) {
			hypothesies = new ArrayList<Hypothesis>();
		}
		hypothesies.add(hypothesis);
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
	 * <code>element2</code> or null if one of the two element is null.<br>
	 * NB: THe intersection between the Empty set and any other set is the Empty
	 * set.
	 * 
	 * @param element1
	 * @param element2
	 * @return the intersection or null if the intersection is empty.
	 */

	public static Element getIntersection(Element element1, Element element2) {

		if (element1 != null && element2 != null) {
			if (element1.isEmptySet() || element2.isEmptySet()) {
				// return the empty set if one of the two elements is empty.
				return new Element(null);
			} else {
				Element intersectionElement = null;

				ArrayList<Hypothesis> h1Array = element1.getHypothesies();
				ArrayList<Hypothesis> h2Array = element2.getHypothesies();
				ArrayList<Hypothesis> intersectionHypothesies = new ArrayList<Hypothesis>();
				if (h1Array != null && h2Array != null) {
					// Searchinf for the common hypothesis
					for (int i = 0; i < h1Array.size(); i++) {
						Hypothesis h1 = h1Array.get(i);
						for (int j = 0; j < h2Array.size(); j++) {
							Hypothesis h2 = h2Array.get(j);
							if (h1.equals(h2))
								intersectionHypothesies.add(h1);
						}

					}
					if (intersectionHypothesies.size() > 0) {
						intersectionElement = new Element(
								intersectionHypothesies);
					} else {
						intersectionElement = new Element(null);
					}
					return intersectionElement;
				}

			}
		}

		return null;
	}

	/**
	 * Returns the Union between <code>element1</code> and <code>element2</code>
	 * . An union of two {@link Element} is an {@link Element} that has all the
	 * hypothesies common to both the elements. An empy {@link Element} is
	 * always common to the other.<br>
	 * The Union between every set A and the Empty set is A.
	 * 
	 * @param element1
	 * @param element2
	 * @return the Union between the two elements or null if one of the element
	 *         is null.
	 */
	public static Element getUnion(Element element1, Element element2) {
		if (element1 == null || element2 == null)
			return null;
		else if (element1.isEmptySet())
			return element2;
		else if (element2.isEmptySet())
			return element1;
		else {
			TreeSet<Hypothesis> union = new TreeSet<Hypothesis>(element1
					.getHypothesies());
			union.addAll(element2.getHypothesies());

			if (union.size() > 0)

				return new Element(new ArrayList<Hypothesis>(union));
			else
				return new Element(null);
		}

	}

	/**
	 * Return true if b is included in a, false otherwise. <br>
	 * The empty set is included in each set A.
	 * 
	 * @param a
	 * @param b
	 * @return true if b is included in a.
	 */
	public static boolean isIncluded(Element a, Element b) {
		// B is included in A if all the element of B belong to A
		// that means the intersection between A and B is equals to B.
		if (b.isEmptySet())
			return true;
		else {
			Element intersection = getIntersection(a, b);
			if (intersection != null && intersection.equals(b))
				return true;

			else
				return false;
		}

	}

	/**
	 * 
	 * Return true if and only if the two object have the same set of
	 * hypothesies or have both empty set of hypothesis.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this != null && obj != null) {
			Element other = (Element) obj;
			ArrayList<Hypothesis> otherHypothesies = other.getHypothesies();
			if ((this.hypothesies == null && other.getHypothesies() == null)
					|| (this.hypothesies != null
							&& other.getHypothesies() != null
							&& other.getHypothesies().size() == hypothesies
									.size() && otherHypothesies
							.containsAll(hypothesies))) {

				return true;
			}

			return false;
		}
		return false;
	}

	/**
	 * Returns the list of the Hypothesis of the element in the format
	 * {hyo1,hyp2,..} or the null set {} if there is any hypothesis
	 * */
	@Override
	public String toString() {
		if (hypothesies != null) {
			String elementToString = "{";
			for (int i = 0; i < hypothesies.size(); i++) {
				Hypothesis hypothesis = (Hypothesis) hypothesies.get(i);
				elementToString = elementToString + hypothesis.getIdentifier();
				if (i != (hypothesies.size() - 1)) {
					elementToString = elementToString + ",";
				}
			}

			elementToString = elementToString + "}";

			return elementToString;
		} else
			return "{}";
	}

	/**
	 * Returns 0 if <code>o</code> is equals to this element, the comparison
	 * between the string rapresentation of the elements otherwise.
	 */
	@Override
	public int compareTo(Object o) {
		// if (o != null) {
		// Element element = (Element) o;
		//
		// ArrayList<Hypothesis> elementHypList = element.getHypothesies();
		// if (elementHypList != null && hypothesies != null) {
		// // String compare1 = element.getHypothesies().toString();
		// String compare1 = "";
		// for (Hypothesis hypothesis : elementHypList) {
		// compare1 = compare1 + hypothesis.getIdentifier();
		// }
		// String compare2 = "";
		//
		// for (Hypothesis hypothesis : hypothesies) {
		// compare2 = compare2 + hypothesis.getIdentifier();
		// }
		// int value = compare2.compareTo(compare1);
		//
		// return value;
		// }
		// return -1;
		// }
		// return -1;
		if (this.equals(o))
			return 0;
		else if (o != null)
			return o.toString().compareTo(this.toString());
		else
			return -1;
	}

	/**
	 * @return the number of the hypothesies of the {@link Element}
	 */
	public int size() {
		if (hypothesies != null)
			return hypothesies.size();
		else
			return 0;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		if (hypothesies != null) {
			ArrayList<Hypothesis> hypClone = new ArrayList<Hypothesis>();
			for (int i = 0; i < hypothesies.size(); i++) {
				Hypothesis hyp = (Hypothesis) hypothesies.get(i).clone();
				hypClone.add(hyp);
			}
			Element clone = new Element(hypClone);
			return clone;
		} else
			return new Element(null);
	}

	/**
	 * @return true if and only if the set of hypothesis is null, false
	 *         otherwise.
	 */
	public boolean isEmptySet() {
		if (hypothesies == null)
			return true;
		else
			return false;
	}

}
