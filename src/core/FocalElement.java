package core;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * A Focal Element is an Element with an evaluated focalElements.
 * 
 * @author Elisa Costante
 * 
 */
public class FocalElement implements Cloneable {
	private Double bpa;
	private Element element;

	private Double belief;
	private Double plausability;

	public FocalElement(Element element, double bpa) {

		this.element = element;
		this.bpa = bpa;
	}

	/**
	 * Set the Value of bpa. It mantains only 5 decimal digit
	 * 
	 * @param bpa
	 */
	public void setBpa(Double bpa) {
		BigDecimal bigDecimal = new BigDecimal(bpa, new MathContext(5));
		bpa = bigDecimal.doubleValue();
		this.bpa = bpa;

	}

	/**
	 * @return the bpa
	 */
	public Double getBpa() {
		return bpa;
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
		return "FocalElement [bpa=" + bpa + ", element=" + element + "]";
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

		if (other.getElement().equals(element) && other.getBpa().equals(bpa))
			return true;
		else
			return false;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		Element cloneElement = (Element) element.clone();
		FocalElement clone = new FocalElement(cloneElement, bpa);
		clone.setBelief(belief);
		clone.setPlausability(plausability);
		return clone;
	}

}
