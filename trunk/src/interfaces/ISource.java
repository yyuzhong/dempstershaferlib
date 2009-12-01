package interfaces;

import java.util.ArrayList;

import core.FocalElement;
import core.Hypothesis;
import core.MassDistribution;

public interface ISource {

	/**
	 * Analyze all the attributes, find out the value of it and defines the
	 * value of basic probability assignment for each of it {@link FocalElement}
	 * 
	 * @return the {@link MassDistribution} of the source.
	 */
	public MassDistribution getMassDistribution();

	/**
	 * Find out and set the measured value of the <code>attribute</code>.
	 * 
	 * @param attribute
	 */
	public void setValue(IAttribute attribute);

	/**
	 * @param attribute
	 * @return
	 */
	public ArrayList<Hypothesis> getHypotheses(IAttribute attribute);

}
