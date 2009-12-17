package massDistribution;

import interfaces.Attribute;
import interfaces.IMeasure;

/**
 * 
 * 
 * @author Elisa Costante
 * 
 */
public class MeasuredAttribute extends Attribute {

	protected IMeasure measure;

	public MeasuredAttribute(String identifier) {
		super(identifier);
	}

	/**
	 * @return the measure.
	 */
	public IMeasure getMeasure() {
		return measure;
	}

	/**
	 * Set the field measure.
	 * 
	 * @param measure
	 *            : The measure to set.
	 */
	public void setMeasure(IMeasure measure) {
		this.measure = measure;
	}

}
