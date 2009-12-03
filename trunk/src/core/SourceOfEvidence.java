package core;

import interfaces.ISource;

import java.util.ArrayList;

import massDistribution.ClassAttributeMap;
import massDistribution.MassDistribution;
import massDistribution.MeasuredAttribute;

/**
 * This class represents a source of evidence. Different source must have the
 * same {@link FrameOfDiscernment} to aggregate their {@link MassDistribution}.
 * 
 * @author Elisa Costante
 * 
 */
public abstract class SourceOfEvidence implements ISource {

	private FrameOfDiscernment frameOfDiscernment;

	private String url;

	private String name;

	public SourceOfEvidence() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public FrameOfDiscernment getFrameOfDiscernment() {
		return frameOfDiscernment;
	}

	public void setFrameOfDiscernment(FrameOfDiscernment frameOfDiscernment) {
		this.frameOfDiscernment = frameOfDiscernment;
	}

	public MassDistribution getMassDistribution(
			ClassAttributeMap classAttributeMap) {
		ArrayList<MeasuredAttribute> measureAttributesList = readMeasureAttribute();

		for (MeasuredAttribute measuredAttribute : measureAttributesList) {

		}
		return null;
	}

	/**
	 * This method must be implemented in order to read the attribute collected
	 * and measured from the source.
	 * 
	 * @return a list of {@link MeasuredAttribute}.
	 */
	public abstract ArrayList<MeasuredAttribute> readMeasureAttribute();

}
