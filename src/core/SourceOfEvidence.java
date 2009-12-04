package core;

import interfaces.ISource;

import java.util.ArrayList;

import massDistribution.ClassAttributeMap;
import massDistribution.ClassificationAttribute;
import massDistribution.IMeasure;
import massDistribution.MassDistribution;
import massDistribution.MeasuredAttribute;
import massDistribution.Range;

/**
 * This class represents a source of evidence. Different source must have the
 * same {@link FrameOfDiscernment} to aggregate their {@link MassDistribution}.
 * 
 * @author Elisa Costante
 * 
 */
public abstract class SourceOfEvidence implements ISource {

	private FrameOfDiscernment frameOfDiscernment;

	private String name;

	public SourceOfEvidence(FrameOfDiscernment frameOfDiscernment, String name) {
		this.frameOfDiscernment = frameOfDiscernment;
		this.name = name;
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

		MassDistribution mass = new MassDistribution();

		ArrayList<MeasuredAttribute> measureAttributesList = readMeasureAttribute();

		for (MeasuredAttribute measuredAttribute : measureAttributesList) {

			ClassificationAttribute classAttribute = classAttributeMap
					.getClassificationAttribute(measuredAttribute
							.getIdentifier());
			IMeasure measuredValue = measuredAttribute.getMeasure();

			Element element = computeElement(classAttribute, measuredValue);

			FocalElement focalElement = new FocalElement(element,
					classAttribute.getWeight());

			mass.addElement(focalElement);

		}
		return null;
	}

	private Element computeElement(ClassificationAttribute classAttribute,
			IMeasure measuredValue) {
		ArrayList<Hypothesis> allHypothesis = frameOfDiscernment
				.getHipothesies();

		Element element = new Element();
		for (Hypothesis hypothesis : allHypothesis) {

			ArrayList<Range> allRange = classAttribute.getRanges(hypothesis);

			for (Range range : allRange) {
				if (range.contains(measuredValue)) {
					element.addHypothesis(hypothesis);
				}
			}
		}

		return element;
	}

	/**
	 * This method must be implemented in order to read the attribute collected
	 * and measured from the source.
	 * 
	 * @return a list of {@link MeasuredAttribute}.
	 */
	public abstract ArrayList<MeasuredAttribute> readMeasureAttribute();

}
