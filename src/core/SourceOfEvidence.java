package core;

import interfaces.IMeasure;
import interfaces.IRange;
import interfaces.ISource;

import java.util.ArrayList;

import massDistribution.ClassAttributeMap;
import massDistribution.ClassificationAttribute;
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

	private String name;

	public SourceOfEvidence(String name) {
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

	@Override
	public MassDistribution getMassDistribution(
			FrameOfDiscernment frameOfDiscernment,
			ClassAttributeMap classAttributeMap) {

		this.frameOfDiscernment = frameOfDiscernment;
		ArrayList<FocalElement> focalEvidence = new ArrayList<FocalElement>();

		ArrayList<MeasuredAttribute> measureAttributesList = readMeasuredAttributes();

		for (MeasuredAttribute measuredAttribute : measureAttributesList) {

			ClassificationAttribute classAttribute = classAttributeMap
					.getClassificationAttribute(measuredAttribute
							.getIdentifier());
			IMeasure measuredValue = measuredAttribute.getMeasure();

			if (measuredValue.hasMeasuredValue()) {
				Element element = computeElement(classAttribute, measuredValue);

				FocalElement focalElement = new FocalElement(element,
						classAttribute.getWeight());

				focalEvidence.add(focalElement);
			}

		}

		return computeMass(focalEvidence);
	}

	private MassDistribution computeMass(ArrayList<FocalElement> focalEvidence) {
		MassDistribution mass = new MassDistribution();

		ArrayList<FocalElement> bodyOfEvidence = new ArrayList<FocalElement>();

		for (int i = 0; i < focalEvidence.size(); i++) {
			FocalElement focalElement = focalEvidence.get(i);

			double bpa = focalElement.getBpa();

			for (int j = i + 1; j < focalEvidence.size(); j++) {
				FocalElement same = focalEvidence.get(j);
				if (focalElement.getElement().equals(same.getElement())) {
					bpa = bpa + same.getBpa();
				}
			}
			if (FocalElement.findElement(bodyOfEvidence, focalElement
					.getElement()) == null)
				bodyOfEvidence.add(new FocalElement(focalElement.getElement(),
						bpa));
		}

		mass = new MassDistribution(bodyOfEvidence);

		if (!mass.isValid()) {
			FocalElement universalSet = new FocalElement(new Element(
					frameOfDiscernment.getHipothesies()), (double) (1.0 - mass
					.getTotalBpa()));
			mass.addElement(universalSet);
		}
		MassDistribution.setBodyOfEvidence(mass);

		return mass;
	}

	/**
	 * Returns the {@link Element} or null
	 * 
	 * @param classAttribute
	 * @param measuredValue
	 * @return
	 */
	private Element computeElement(ClassificationAttribute classAttribute,
			IMeasure measuredValue) {
		ArrayList<Hypothesis> allHypothesis = frameOfDiscernment
				.getHipothesies();

		Element element = new Element();
		for (Hypothesis hypothesis : allHypothesis) {

			ArrayList<IRange> allRange = classAttribute.getRanges(hypothesis);

			if (allRange != null) {
				for (IRange iRange : allRange) {
					if (iRange.containsValue((Comparable) measuredValue
							.getValue())) {
						element.addHypothesis(hypothesis);
					}
				}
			}
		}

		if (element.getHypothesies() == null)
			System.out.println(classAttribute);
		return element;
	}

	@Override
	public abstract ArrayList<MeasuredAttribute> readMeasuredAttributes();

}
