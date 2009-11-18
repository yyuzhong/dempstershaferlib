package core;

import java.util.ArrayList;
import java.util.Iterator;

public class MassDistribution implements Cloneable {

	protected ArrayList<Element> elements;
	protected Source source;

	public MassDistribution(ArrayList<Element> mass) {
		super();
		this.elements = mass;
	}

	public ArrayList<Element> getElements() {
		return elements;
	}

	public void setElements(ArrayList<Element> mass) {
		this.elements = mass;
	}

	public void addElement(Element element) {
		if (elements == null)
			elements = new ArrayList<Element>();
		elements.add(element);
	}

	/**
	 * Verify if the elements distribution is valid, hat means the sum of all
	 * elements it's equal to one.
	 * 
	 * @return true if the elements distribution is valid, false otherwise.
	 */
	public boolean isValid() {
		double sum = 0;
		for (Iterator iterator = elements.iterator(); iterator.hasNext();) {
			Element element = (Element) iterator.next();
			sum = sum + element.getBpa();

		}
		if (Math.round(sum) == 1)
			return true;
		else
			return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String massTOString = "MassDistribution [elements=" + elements;
		if (source != null)
			massTOString = massTOString + " " + source.getName() + "]";
		else
			massTOString = massTOString + "]";

		return massTOString;
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
		result = prime * result
				+ ((elements == null) ? 0 : elements.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {

		MassDistribution other = (MassDistribution) obj;

		if (other.getElements().size() == elements.size()
				&& other.getElements().containsAll(elements)) {
			boolean equal = true;
			for (int i = 0; i < other.getElements().size(); i++) {
				Element el1 = other.getElements().get(i);
				int index = elements.indexOf(el1);

				Element el2 = elements.get(index);

				if (el1.getBpa().doubleValue() != el2.getBpa().doubleValue())
					equal = false;
			}
			return equal;
		} else
			return false;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		try {
			ArrayList<Element> elementsClone = new ArrayList<Element>();
			for (int i = 0; i < elements.size(); i++) {
				Element el = (Element) elements.get(i).clone();
				elementsClone.add(el);
			}
			MassDistribution clone = new MassDistribution(elementsClone);
			return clone;
		} catch (CloneNotSupportedException e) {
			throw new InternalError(e.toString());
		}
	}

	public static MassDistribution order(MassDistribution mass) {
		ArrayList<Element> orderedElements = new ArrayList<Element>();
		for (Iterator iterator = orderedElements.iterator(); iterator.hasNext();) {
			Element element1 = (Element) iterator.next();

			Element min = element1;
			for (int j = 0; j < mass.getElements().size(); j++) {
				Element element2 = mass.getElements().get(j);

				if (element2.compareTo(element1) <= 0) {
					min = element2;
				}

			}
			orderedElements.add(min);
			mass.getElements().remove(element1);
		}

		MassDistribution orderedMass = new MassDistribution(orderedElements);

		return orderedMass;

	}

	/**
	 * @return the source
	 */
	public Source getSource() {
		return source;
	}

	/**
	 * @param source
	 *            the source to set
	 */
	public void setSource(Source source) {
		this.source = source;
	}

}
