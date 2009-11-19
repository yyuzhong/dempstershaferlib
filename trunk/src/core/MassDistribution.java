package core;

import java.util.ArrayList;
import java.util.Iterator;

public class MassDistribution implements Cloneable {

	protected ArrayList<FocalElement> focalElements;
	protected Source source;

	public MassDistribution(ArrayList<FocalElement> mass) {
		super();
		this.focalElements = mass;
	}

	public ArrayList<FocalElement> getFocalElements() {
		return focalElements;
	}

	public void setElements(ArrayList<FocalElement> mass) {
		this.focalElements = mass;
	}

	public void addElement(FocalElement element) {
		if (focalElements == null)
			focalElements = new ArrayList<FocalElement>();
		focalElements.add(element);
	}

	/**
	 * Verify if the focalElements distribution is valid, hat means the sum of
	 * all focalElements it's equal to one.
	 * 
	 * @return true if the focalElements distribution is valid, false otherwise.
	 */
	public boolean isValid() {
		double sum = 0;
		for (FocalElement focalElement : focalElements) {
			sum = sum + focalElement.getBpa();

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
		String massTOString = "MassDistribution [focalElements="
				+ focalElements;
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
				+ ((focalElements == null) ? 0 : focalElements.hashCode());
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

		if (other.getFocalElements().size() == focalElements.size()
				&& other.getFocalElements().containsAll(focalElements)) {
			boolean equal = true;
			for (int i = 0; i < other.getFocalElements().size(); i++) {
				FocalElement el1 = other.getFocalElements().get(i);
				FocalElement el2 = MassDistribution.findFocalElement(
						focalElements, el1);

				if (!el1.equals(el2))
					equal = false;
			}
			return equal;
		} else
			return false;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		try {
			ArrayList<FocalElement> elementsClone = new ArrayList<FocalElement>();
			for (int i = 0; i < focalElements.size(); i++) {
				FocalElement el = (FocalElement) focalElements.get(i).clone();
				elementsClone.add(el);
			}
			MassDistribution clone = new MassDistribution(elementsClone);
			return clone;
		} catch (CloneNotSupportedException e) {
			throw new InternalError(e.toString());
		}
	}

	@SuppressWarnings("unchecked")
	public static MassDistribution order(MassDistribution mass) {
		ArrayList<FocalElement> orderedElements = new ArrayList<FocalElement>();

		for (Iterator iterator = orderedElements.iterator(); iterator.hasNext();) {
			FocalElement element1 = ((FocalElement) iterator.next());

			FocalElement min = element1;
			for (int j = 0; j < mass.getFocalElements().size(); j++) {
				FocalElement element2 = mass.getFocalElements().get(j);

				if (element2.getElement().compareTo(element1.getElement()) <= 0) {
					min = element2;
				}

			}
			orderedElements.add(min);
			mass.getFocalElements().remove(element1);
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
	public static FocalElement findFocalElement(
			ArrayList<FocalElement> elementsList, FocalElement element) {
		int index = elementsList.indexOf(element);
		FocalElement found = null;
		if (index >= 0)
			found = elementsList.get(index);
		return found;
	}

}
