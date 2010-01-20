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

	protected FrameOfDiscernment frameOfDiscernment;

	protected String name;

	protected ArrayList<MeasuredAttribute> measuredAttributes;

	public SourceOfEvidence(String name) {
		this.name = name;
	}

	public String getIdentifierName() {
		return name;
	}

	public void setIdentifierName(String name) {
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
		// La massa si calcola per gli attributi presenti nella
		// classAttributeMap
		for (ClassificationAttribute classificationAttribute : classAttributeMap
				.getAllAttributes()) {

			if (measuredAttributes.contains(classificationAttribute)) {
				int indexOfAttribute = measuredAttributes
						.indexOf(classificationAttribute);
				MeasuredAttribute measuredFromSourceAttribute = measuredAttributes
						.get(indexOfAttribute);

				IMeasure measuredValue = measuredFromSourceAttribute
						.getMetric().getMeasure();

				if (measuredValue.hasMeasuredValue()) {
					Element element = computeElement(classificationAttribute,
							measuredValue);

					FocalElement focalElement = new FocalElement(element,
							classificationAttribute.getWeight());

					focalEvidence.add(focalElement);
				}

			}

		}

		return computeMass(focalEvidence);
	}

	/**
	 * Return the {@link MassDistribution} for the body of evidence
	 * <code>focalEvidence</code>.
	 * 
	 * @param focalEvidence
	 * @return
	 */
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
			// add the unknown mass to the universal set
			FocalElement universalSet = new FocalElement(frameOfDiscernment
					.getUniversalSet(), (double) (1.0 - mass.getTotalBpa()));
			mass.addElement(universalSet);
		}
		MassDistribution.setBodyOfEvidence(mass);

		return mass;
	}

	/**
	 * Given a measured value for a classattribute returns the element with the
	 * relatives Hypothesis. If no Hypothesis match with the measured value an
	 * Element with the empty set as hyphotesis is returned Returns the
	 * {@link Element} or null
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
					if (iRange.containsValue(measuredValue)) {
						element.addHypothesis(hypothesis);
					}
				}
			}
		}// TODO vedere se è corretto far ritornare l'elemento con l'insieme
		// vuoto delle ipotesi.
		return element;
	}

	/**
	 * @return the measuredAttributes
	 */
	public ArrayList<MeasuredAttribute> getMeasuredAttributes() {
		return this.measuredAttributes;
	}

	/**
	 * @param measuredAttributes
	 *            the measuredAttributes to set
	 */
	public void setMeasuredAttributes(
			ArrayList<MeasuredAttribute> measuredAttributes) {
		this.measuredAttributes = measuredAttributes;
	}

	public void addMeasuredAttribute(MeasuredAttribute qAttribute) {
		if (measuredAttributes == null)
			measuredAttributes = new ArrayList<MeasuredAttribute>();
		measuredAttributes.add(qAttribute);

	}
}
