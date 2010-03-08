package core;

import interfaces.IElement;
import interfaces.IFocalElement;

import java.util.ArrayList;
import java.util.TreeSet;

import massDistribution.MassDistribution;
import utilities.DoubleUtility;

/**
 * A Focal Element is a couple Element-bpa.
 * 
 * @author Elisa Costante
 * 
 */
public class FocalElement implements IFocalElement {
	private final int PRECISION = 4;
	private Double bpa;
	private IElement element;
	private Double belief;
	private Double plausability;
	private ArrayList<IFocalElement> bodyOfEvidence;

	public FocalElement(IElement element, double bpa) {
		this.element = element;
		setBpa(bpa);
	}

	/**
	 * Set the Value of bpa. It mantains only a specified number of decimal
	 * digit
	 * 
	 * @param bpa
	 */
	public void setBpa(Double bpa) {
		// BigDecimal bpaBigDecimal = new BigDecimal(bpa).setScale(PRECISION,
		// BigDecimal.ROUND_HALF_UP);
		// this.bpa = bpaBigDecimal;
		this.bpa = bpa;

	}

	/**
	 * @return the bpa
	 */
	public double getBpa() {
		// return bpa.doubleValue();

		return bpa;
	}

	/**
	 * Returns the value of the belief of the element or {@link Double}.NaN if
	 * no bpa is set.
	 * 
	 * @return the belief value
	 */
	public Double getBelief() {
		if (bpa != null) {
			if (belief == null && bodyOfEvidence != null) {
				setBelief();
			}
			return belief;

		} else
			return Double.NaN;
	}

	private void setBelief() {
		// Bel(A)= Summation m(B) for each B| (B included A==true)
		double bel = 0;
		for (IFocalElement focalElement : bodyOfEvidence) {

			if (Element
					.isIncluded(this.getElement(), focalElement.getElement())) {
				bel = bel + focalElement.getBpa();
			}
		}
		// BigDecimal beliefBigDecimal = new BigDecimal(bel).setScale(PRECISION,
		// BigDecimal.ROUND_HALF_UP);
		// this.belief = beliefBigDecimal;
		this.belief = bel;

	}

	/**
	 * Returns the value of the plausability of the element or {@link Double}
	 * .NaN if no bpa is set.
	 * 
	 * @return the belief value
	 */
	public Double getPlausability() {
		if (bpa != null) {
			if (plausability == null && bodyOfEvidence != null) {
				setPlausability();
			}
			return plausability;

		} else
			return Double.NaN;
	}

	private void setPlausability() {
		// Pl(A)= Summation m(B) for each B| (B intersect A== empty)
		double pl = 0;
		for (IFocalElement focalElement : bodyOfEvidence) {

			if (Element.getIntersection(focalElement.getElement(),
					this.getElement()).isEmptySet()) {
				pl = pl + focalElement.getBpa();
			}
		}
		// BigDecimal beliefBigDecimal = new BigDecimal(pl).setScale(PRECISION,
		// BigDecimal.ROUND_HALF_UP);
		// this.plausability = beliefBigDecimal;
		this.plausability = pl;

	}

	/**
	 * @return the element
	 */
	public IElement getElement() {
		return element;
	}

	/**
	 * @param element
	 *            the element to set
	 */
	public void setElement(IElement element) {
		if (element != null)
			this.element = (Element) element;
		else
			this.element = null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "[" + element + " - bpa:"
				+ DoubleUtility.doubleToString(bpa, PRECISION)
				// + "; belief:"
				// + DoubleUtility.doubleToString(belief, PRECISION)
				// + "; plausability: "
				// + DoubleUtility.doubleToString(plausability, PRECISION)
				+ "]";
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 * 
	 *      Two Focal element are equals if they have the same element and the
	 *      same bpa.
	 */
	@Override
	public boolean equals(Object obj) {
		FocalElement other = (FocalElement) obj;

		if (other.getElement().equals(element)
				&& (DoubleUtility.areEqualsDouble(other.getBpa(), bpa,
						DoubleUtility.EPSILON)))
			return true;
		else
			return false;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Element cloneElement = (Element) element.clone();
		FocalElement clone = new FocalElement(cloneElement, bpa.doubleValue());
		// clone.setPlausability(plausability);
		return clone;
	}

	/**
	 * Look up for the <code>element</code> in the <code>elementList</code>.
	 * 
	 * @param elementsList
	 *            : the list of bodyOfEvidence
	 * @param element
	 *            : the element one want to find in the list.
	 * @return The <code>element</code> if it is in the list, <code>null</code>
	 *         otherwise.
	 */
	public static IFocalElement findElement(ArrayList<IFocalElement> focalList,
			IElement element) {
		if (focalList != null) {
			for (IFocalElement focalElement : focalList) {
				if (focalElement.getElement().equals(element))
					return focalElement;
			}
		}

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see interfaces.IFocalElement#compareTo(core.FocalElement)
	 */
	@Override
	public int compareTo(FocalElement o) {
		return this.getElement().compareTo(o.getElement());
	}

	/**
	 * Return the union of two bodyOfEvidence list.
	 * 
	 * @param el1
	 * @param el2
	 * @return the union of two bodyOfEvidence list.
	 */
	public static ArrayList<IFocalElement> getMassUnionElement(
			ArrayList<IFocalElement> elementList1,
			ArrayList<IFocalElement> elementList2) {

		ArrayList<IFocalElement> newElementList1 = new ArrayList<IFocalElement>();

		for (IFocalElement element : elementList1) {
			newElementList1.add(new FocalElement(element.getElement(), 0));
		}

		ArrayList<IFocalElement> newElementList2 = new ArrayList<IFocalElement>();

		for (IFocalElement element : elementList2) {
			newElementList2.add(new FocalElement(element.getElement(), 0));
		}

		TreeSet<IFocalElement> union = new TreeSet<IFocalElement>(
				newElementList1);

		union.addAll(new TreeSet<IFocalElement>(newElementList2));

		return new ArrayList<IFocalElement>(union);
	}

	public static ArrayList<IFocalElement> getMassUnionElement(
			ArrayList<MassDistribution> masses) {

		ArrayList<IFocalElement> union = null;

		if (masses.size() >= 2) {
			ArrayList<IFocalElement> m1Elements = masses.get(0)
					.getBodyOfEvidence();
			ArrayList<IFocalElement> m2Elements = masses.get(1)
					.getBodyOfEvidence();

			union = FocalElement.getMassUnionElement(m1Elements, m2Elements);
			for (int i = 2; i < masses.size(); i++) {
				union = FocalElement.getMassUnionElement(union, masses.get(i)
						.getBodyOfEvidence());
			}

		}
		return union;
	}

	public double getUncertainty() {
		// return (belief.subtract(plausability)).abs().doubleValue();
		return Math.abs(plausability - belief);
	}

	/**
	 * @param massDistribution
	 *            the massDistribution to set
	 */
	public void setBodyOfEvidence(ArrayList<IFocalElement> bodyOfEvidence) {
		if (bodyOfEvidence != null) {
			this.bodyOfEvidence = bodyOfEvidence;
			setBelief();
			setPlausability();

		}
	}

	/**
	 * Returns true if the focal element list contains the element, false
	 * otherwise.
	 * 
	 * @param focalElements
	 * @param element
	 * @return
	 */
	public static boolean containsElement(
			ArrayList<IFocalElement> focalElements, IElement element) {
		if (focalElements != null && element != null) {
			for (IFocalElement focalElement : focalElements) {
				if (focalElement.getElement().equals(element))
					return true;
			}
		}
		return false;
	}

}
