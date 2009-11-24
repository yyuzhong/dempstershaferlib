package core;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.TreeSet;

/**
 * A Focal Element is an Element with an evaluated focalElements.
 * 
 * @author Elisa Costante
 * 
 */
public class FocalElement implements Cloneable, Comparable<FocalElement> {
	private final int PRECISION = 4;
	private BigDecimal bpa;
	private Element element;

	private Double belief;
	private Double plausability;

	public FocalElement(Element element, double bpa) {

		this.element = element;
		setBpa(bpa);
	}

	/**
	 * Set the Value of bpa. It mantains only 5 decimal digit
	 * 
	 * @param bpa
	 */
	public void setBpa(Double bpa) {
		BigDecimal bpaBigDecimal = new BigDecimal(bpa).setScale(PRECISION,
				BigDecimal.ROUND_HALF_UP);
		this.bpa = bpaBigDecimal;

	}

	/**
	 * @return the bpa
	 */
	public Double getBpa() {
		return bpa.doubleValue();
	}

	public Double getBelief() {
		return belief;
	}

	public void setBelief(Double belief) {
		BigDecimal bigDecimal = new BigDecimal(belief, new MathContext(5));
		belief = bigDecimal.doubleValue();
		this.belief = belief;
	}

	public Double getPlausability() {
		return plausability;
	}

	public void setPlausability(Double plausability) {
		BigDecimal bigDecimal = new BigDecimal(plausability, new MathContext(5));
		plausability = bigDecimal.doubleValue();
		this.plausability = plausability;
	}

	/**
	 * @return the element
	 */
	public Element getElement() {
		return element;
	}

	/**
	 * @param element
	 *            the element to set
	 */
	public void setElement(Element element) {
		this.element = element;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "[" + element + " - " + bpa + "]";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((belief == null) ? 0 : belief.hashCode());
		result = prime * result + ((bpa == null) ? 0 : bpa.hashCode());
		result = prime * result + ((element == null) ? 0 : element.hashCode());
		result = prime * result
				+ ((plausability == null) ? 0 : plausability.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		FocalElement other = (FocalElement) obj;

		if (other.getElement().equals(element)
				&& compareSamePrecision(other.getBpa().doubleValue(), bpa
						.doubleValue(), PRECISION))
			return true;
		else
			return false;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		Element cloneElement = (Element) element.clone();
		FocalElement clone = new FocalElement(cloneElement, bpa.doubleValue());
		// clone.setBelief(belief);
		// clone.setPlausability(plausability);
		return clone;
	}

	/**
	 * Look up for the <code>element</code> in the <code>elementList</code>.
	 * 
	 * @param elementsList
	 *            : the list of focalElements
	 * @param element
	 *            : the element one want to find in the list.
	 * @return The <code>element</code> if it is in the list, <code>null</code>
	 *         otherwise.
	 */
	public static FocalElement findElement(ArrayList<FocalElement> focalList,
			Element element) {
		for (FocalElement focalElement : focalList) {
			if (focalElement.getElement().equals(element))
				return focalElement;
		}
		return null;
	}

	@Override
	public int compareTo(FocalElement o) {
		return this.getElement().compareTo(o.getElement());
	}

	/**
	 * Return the union of two focalElements list.
	 * 
	 * @param el1
	 * @param el2
	 * @return the union of two focalElements list.
	 */
	public static ArrayList<FocalElement> getMassUnionElement(
			ArrayList<FocalElement> elementList1,
			ArrayList<FocalElement> elementList2) {

		ArrayList<FocalElement> newElementList1 = new ArrayList<FocalElement>();

		for (FocalElement element : elementList1) {
			newElementList1.add(new FocalElement(element.getElement(), 0));
		}

		ArrayList<FocalElement> newElementList2 = new ArrayList<FocalElement>();

		for (FocalElement element : elementList2) {
			newElementList2.add(new FocalElement(element.getElement(), 0));
		}

		TreeSet<FocalElement> union = new TreeSet<FocalElement>(newElementList1);

		union.addAll(new TreeSet<FocalElement>(newElementList2));

		return new ArrayList<FocalElement>(union);
	}

	public static ArrayList<FocalElement> getMassUnionElement(
			ArrayList<MassDistribution> masses) {

		ArrayList<FocalElement> union = null;

		if (masses.size() >= 2) {
			ArrayList<FocalElement> m1Elements = masses.get(0)
					.getFocalElements();
			ArrayList<FocalElement> m2Elements = masses.get(1)
					.getFocalElements();

			union = FocalElement.getMassUnionElement(m1Elements, m2Elements);
			for (int i = 2; i < masses.size(); i++) {
				union = FocalElement.getMassUnionElement(union, masses.get(i)
						.getFocalElements());
			}

		}
		return union;
	}

	/**
	 * Compare the two double values using the same precision.
	 * 
	 * @param a
	 *            : the first value
	 * @param b
	 *            : the second value
	 * @param precision
	 *            : number of decimal digit
	 * @return true if the two number are equal, false otherwise
	 */
	public static boolean compareSamePrecision(double a, double b, int precision) {

		BigDecimal aBigDecimal = new BigDecimal(a).setScale(precision,
				BigDecimal.ROUND_HALF_UP);

		BigDecimal bBigDecimal = new BigDecimal(b).setScale(precision,
				BigDecimal.ROUND_HALF_UP);

		return (aBigDecimal.compareTo(bBigDecimal) == 0);
	}

	public double getUncertainity() {
		return plausability - belief;
	}

}
