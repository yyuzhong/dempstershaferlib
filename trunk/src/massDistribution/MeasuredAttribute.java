package massDistribution;

import interfaces.IAttribute;

/**
 * This class
 * 
 * @author Elisa Costante
 * 
 */
public class MeasuredAttribute extends IAttribute {

	private IMeasure measure;

	@Override
	public IMeasure getMeasure() {
		return measure;
	}

}
