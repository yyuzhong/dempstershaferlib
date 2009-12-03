package interfaces;

import massDistribution.IMeasure;

/**
 * This interface allows the definition of the attributes which allow one to
 * establish the mass probability of an hypothesis.
 * 
 * @author Elisa Costante
 * 
 */
public abstract class IAttribute {

	protected String identifier;

	protected IMeasure measure;

	/**
	 * @return the identifier
	 */
	public String getIdentifier() {
		return identifier;
	}

	/**
	 * @param identifier
	 *            the identifier to set
	 */
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	/**
	 * @param measure
	 *            the value to set
	 */
	public void setMeasure(IMeasure measure) {
		this.measure = measure;
	}

	/**
	 * @return the IMeasure of the attribute.
	 */
	public abstract IMeasure getMeasure();

}
