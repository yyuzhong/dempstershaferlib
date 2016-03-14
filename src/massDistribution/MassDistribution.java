package massDistribution;

import interfaces.IElement;
import interfaces.IFocalElement;
import interfaces.IFrameOfDiscernment;
import interfaces.IMassDistribution;

import java.util.ArrayList;

import utilities.DoubleUtility;
import core.FocalElement;
import core.FrameOfDiscernment;

public class MassDistribution implements Cloneable, IMassDistribution {

	protected ArrayList<IFocalElement> bodyOfEvidence;
	protected IFrameOfDiscernment frameOfDiscernment;

	public MassDistribution(ArrayList<IFocalElement> mass) {
		super();
		this.bodyOfEvidence = mass;
	}

	public MassDistribution() {

	}

	public ArrayList<IFocalElement> getBodyOfEvidence() {
		return bodyOfEvidence;
	}

	public void setElements(ArrayList<IFocalElement> focalElements) {
		this.bodyOfEvidence = focalElements;
	}

	public void addElement(FocalElement element) {
		if (bodyOfEvidence == null)
			bodyOfEvidence = new ArrayList<IFocalElement>();
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

	/**
	 * Returns true if and only if all the bpa of the mass is associeted to the
	 * universal set.
	 * 
	 * @return
	 */
	public boolean hasTotalLackOfKnowledge() {
		if (frameOfDiscernment != null) {
			IElement universalSet = frameOfDiscernment.getUniversalSet();
			IFocalElement universalFocalElement = getFocalElement(universalSet
					.toString());
			if (universalFocalElement != null) {
				if (DoubleUtility.areEqualsDouble(universalFocalElement
						.getBpa(), 1.0, DoubleUtility.EPSILON))
					return true;
			}

		}
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
				IFocalElement el1 = other.getBodyOfEvidence().get(i);
				IFocalElement el2 = MassDistribution.findFocalElement(
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
		MassDistribution clonedMass = new MassDistribution();
		if (bodyOfEvidence != null) {
			ArrayList<IFocalElement> elementsClone = new ArrayList<IFocalElement>();
			for (int i = 0; i < bodyOfEvidence.size(); i++) {
				FocalElement el = (FocalElement) bodyOfEvidence.get(i).clone();
				elementsClone.add(el);
			}
			clonedMass.setElements(elementsClone);
		}
		if (frameOfDiscernment != null) {
			FrameOfDiscernment clonedFrame = (FrameOfDiscernment) frameOfDiscernment
					.clone();
			clonedMass.setFrameOfDiscernment(clonedFrame);
		}

		return clonedMass;

	}

	public static MassDistribution order(MassDistribution mass) {
		ArrayList<IFocalElement> orderedElements = new ArrayList<IFocalElement>();

		for (IFocalElement focalElement1 : mass.getBodyOfEvidence()) {

			IFocalElement min = focalElement1;
			for (int j = 0; j < mass.getBodyOfEvidence().size(); j++) {
				IFocalElement element2 = mass.getBodyOfEvidence().get(j);

				if (element2.getElement().compareTo(focalElement1.getElement()) < 0
						&& !orderedElements.contains(element2)) {
					min = element2;
				}

			}
			if(!orderedElements.contains(min)) orderedElements.add(min);
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
	public static IFocalElement findFocalElement(
			ArrayList<IFocalElement> elementsList, IFocalElement element) {
		int index = elementsList.indexOf(element);
		IFocalElement found = null;
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
		for (IFocalElement focalElement : massDistribution.getBodyOfEvidence()) {
			focalElement
					.setBodyOfEvidence(massDistribution.getBodyOfEvidence());

		}

	}

	public double getTotalBpa() {
		double sum = 0;
		for (IFocalElement focalElement : bodyOfEvidence) {
			sum = sum + focalElement.getBpa();

		}
		return sum;
	}

	/**
	 * @return the frameOfDiscernment
	 */
	public IFrameOfDiscernment getFrameOfDiscernment() {
		return this.frameOfDiscernment;
	}

	/**
	 * @param frameOfDiscernment
	 *            the frameOfDiscernment to set
	 */
	public void setFrameOfDiscernment(IFrameOfDiscernment frameOfDiscernment) {
		this.frameOfDiscernment = frameOfDiscernment;
	}

	/**
	 * Returns the {@link FocalElement} given a String wich rapresents the
	 * element or null if no match is found.
	 * 
	 * @param element
	 * @return va focal element which match with the element or null if any
	 *         match is found.
	 */
	public IFocalElement getFocalElement(String element) {
		for (int i = 0; i < bodyOfEvidence.size(); i++) {
			IFocalElement focalElement = bodyOfEvidence.get(i);
			IElement thisElement = focalElement.getElement();
			if (thisElement.toString().equalsIgnoreCase(element))
				return focalElement;
		}
		return null;
	}

	/**
	 * Retruns a {@link MassDistribution} where all the knowledge is associated
	 * to the Universal Set.
	 * 
	 * @param mass
	 */
	public static MassDistribution getLackOfKnowledge(
			FrameOfDiscernment frameOfDiscernment) {
		MassDistribution mass = new MassDistribution();
		mass.addElement(new FocalElement(frameOfDiscernment.getUniversalSet(),
				1.0));
		mass.setFrameOfDiscernment(frameOfDiscernment);
		return mass;
	}

}
