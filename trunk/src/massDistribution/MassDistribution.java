package massDistribution;

import java.util.ArrayList;

import utilities.DoubleUtility;
import core.FocalElement;
import core.FrameOfDiscernment;

public class MassDistribution implements Cloneable {

	protected ArrayList<FocalElement> bodyOfEvidence;
	private FrameOfDiscernment frameOfDiscernment;

	public MassDistribution(ArrayList<FocalElement> mass) {
		super();
		this.bodyOfEvidence = mass;
	}

	public MassDistribution() {

	}

	public ArrayList<FocalElement> getBodyOfEvidence() {
		return bodyOfEvidence;
	}

	public void setElements(ArrayList<FocalElement> focalElements) {
		this.bodyOfEvidence = focalElements;
	}

	public void addElement(FocalElement element) {
		if (bodyOfEvidence == null)
			bodyOfEvidence = new ArrayList<FocalElement>();
		bodyOfEvidence.add(element);
	}

	/**
	 * Verify if the bodyOfEvidence distribution is valid, hat means the sum of
	 * all bodyOfEvidence it's equal to one.
	 * 
	 * @return true if the bodyOfEvidence distribution is valid, false
	 *         otherwise.
	 */
	public boolean isValid() {
		double sum = getTotalBpa();
		if (DoubleUtility.areEqualsDouble(sum, 1, DoubleUtility.EPSILON))
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
		String massTOString = "MassDistribution [bodyOfEvidence="
				+ bodyOfEvidence;
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
				+ ((bodyOfEvidence == null) ? 0 : bodyOfEvidence.hashCode());
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

		if (other.getBodyOfEvidence().size() == bodyOfEvidence.size()
				&& other.getBodyOfEvidence().containsAll(bodyOfEvidence)) {
			boolean equal = true;
			for (int i = 0; i < other.getBodyOfEvidence().size(); i++) {
				FocalElement el1 = other.getBodyOfEvidence().get(i);
				FocalElement el2 = MassDistribution.findFocalElement(
						bodyOfEvidence, el1);

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
			for (int i = 0; i < bodyOfEvidence.size(); i++) {
				FocalElement el = (FocalElement) bodyOfEvidence.get(i).clone();
				elementsClone.add(el);
			}
			MassDistribution clone = new MassDistribution(elementsClone);
			return clone;
		} catch (CloneNotSupportedException e) {
			throw new InternalError(e.toString());
		}
	}

	public static MassDistribution order(MassDistribution mass) {
		ArrayList<FocalElement> orderedElements = new ArrayList<FocalElement>();

		for (FocalElement focalElement1 : mass.getBodyOfEvidence()) {

			FocalElement min = focalElement1;
			for (int j = 0; j < mass.getBodyOfEvidence().size(); j++) {
				FocalElement element2 = mass.getBodyOfEvidence().get(j);

				if (element2.getElement().compareTo(focalElement1.getElement()) < 0
						&& !orderedElements.contains(element2)) {
					min = element2;
				}

			}
			orderedElements.add(min);
			// mass.getFocalElements().remove(focalElement1);
		}

		MassDistribution orderedMass = new MassDistribution(orderedElements);

		return orderedMass;

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
	public static FocalElement findFocalElement(
			ArrayList<FocalElement> elementsList, FocalElement element) {
		int index = elementsList.indexOf(element);
		FocalElement found = null;
		if (index >= 0)
			found = elementsList.get(index);
		return found;
	}

	/**
	 * Set the BodyOfEvidence for each {@link FocalElement} belong to the
	 * {@link MassDistribution}
	 * 
	 * @param jointDistribution
	 */
	public static void setBodyOfEvidence(MassDistribution massDistribution) {
		for (FocalElement focalElement : massDistribution.getBodyOfEvidence()) {
			focalElement
					.setBodyOfEvidence(massDistribution.getBodyOfEvidence());

		}

	}

	public double getTotalBpa() {
		double sum = 0;
		for (FocalElement focalElement : bodyOfEvidence) {
			sum = sum + focalElement.getBpa();

		}
		return sum;
	}

	/**
	 * @return the frameOfDiscernment
	 */
	public FrameOfDiscernment getFrameOfDiscernment() {
		return this.frameOfDiscernment;
	}

	/**
	 * @param frameOfDiscernment
	 *            the frameOfDiscernment to set
	 */
	public void setFrameOfDiscernment(FrameOfDiscernment frameOfDiscernment) {
		this.frameOfDiscernment = frameOfDiscernment;
	}

}
