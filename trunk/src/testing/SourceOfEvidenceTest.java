package testing;

import java.util.ArrayList;
import java.util.Hashtable;

import junit.framework.TestCase;
import massDistribution.ClassAttributeMap;
import massDistribution.ClassificationAttribute;
import massDistribution.ContinueMeasure;
import massDistribution.ContinueRange;
import massDistribution.DiscreteMeasure;
import massDistribution.DiscreteRange;
import massDistribution.MassDistribution;
import massDistribution.MeasuredAttribute;
import massDistribution.Range;
import core.FrameOfDiscernment;
import core.Hypothesis;
import core.SourceOfEvidence;

public class SourceOfEvidenceTest extends TestCase {

	private FrameOfDiscernment frame;
	private Hypothesis h1 = new Hypothesis("A");
	private Hypothesis h2 = new Hypothesis("B");
	private Hypothesis h3 = new Hypothesis("C");
	private Hypothesis h4 = new Hypothesis("D");
	private SourceOfEvidence source;

	public SourceOfEvidenceTest(String name) {
		super(name);

	}

	protected void setUp() throws Exception {
		super.setUp();
		ArrayList<Hypothesis> allHypothesis = new ArrayList<Hypothesis>();
		allHypothesis.add(h1);
		allHypothesis.add(h2);
		allHypothesis.add(h3);
		allHypothesis.add(h4);
		this.frame = new FrameOfDiscernment(allHypothesis);
		this.source = new MySource(frame, "TestSource");
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testGetMassDistribution() {

		ClassAttributeMap classAttributeMap = setClassAttributeMap();
		MassDistribution testMass = source
				.getMassDistribution(classAttributeMap);

		System.out.println(testMass);
		assertTrue(testMass.isValid());
	}

	private ClassAttributeMap setClassAttributeMap() {
		Hashtable<String, ClassificationAttribute> hashTable = new Hashtable<String, ClassificationAttribute>();

		// Set ClassificationAttribute Accessibility
		double weight = (double) 1 / 7;
		ClassificationAttribute accessibility = new ClassificationAttribute(
				"Accessibility", weight, getAccessibilityRanges());
		hashTable.put(accessibility.getIdentifier(), accessibility);

		// Set ClassificationAttribute Reliability
		ClassificationAttribute reliability = new ClassificationAttribute(
				"Reliability", weight, getReliabilityRanges());
		hashTable.put(reliability.getIdentifier(), reliability);

		// Set ClassificationAttribute Availability
		ClassificationAttribute availability = new ClassificationAttribute(
				"Availability", weight, getAvailabilityRanges());
		hashTable.put(availability.getIdentifier(), availability);

		// Set ClassificationAttribute Criptography
		ClassificationAttribute criptography = new ClassificationAttribute(
				"Criptography", weight, getCriptographyRanges());
		hashTable.put(criptography.getIdentifier(), criptography);

		// Set ClassificationAttribute Restrictions
		weight = (double) 3 / 7;
		ClassificationAttribute restrictions = new ClassificationAttribute(
				"Restrictions", weight, getRestrictionsRanges());
		hashTable.put(restrictions.getIdentifier(), restrictions);

		ClassAttributeMap classAttributeMap = new ClassAttributeMap(hashTable);
		return classAttributeMap;
	}

	private Hashtable<Hypothesis, ArrayList<Range>> getRestrictionsRanges() {
		Hashtable<Hypothesis, ArrayList<Range>> hash = new Hashtable<Hypothesis, ArrayList<Range>>();

		ArrayList<Range> rangeList1 = new ArrayList<Range>();
		DiscreteRange r1 = new DiscreteRange();
		r1.addElement(new DiscreteMeasure(new Boolean("true")));
		rangeList1.add(r1);
		hash.put(h1, rangeList1);

		ArrayList<Range> rangeList2 = new ArrayList<Range>();
		DiscreteRange r2 = new DiscreteRange();
		r2.addElement(new DiscreteMeasure(new Boolean("false")));
		rangeList2.add(r2);
		hash.put(h3, rangeList2);

		return hash;
	}

	private Hashtable<Hypothesis, ArrayList<Range>> getCriptographyRanges() {
		Hashtable<Hypothesis, ArrayList<Range>> hash = new Hashtable<Hypothesis, ArrayList<Range>>();

		ArrayList<Range> rangeList1 = new ArrayList<Range>();
		DiscreteRange r1 = new DiscreteRange();
		r1.addElement(new DiscreteMeasure(new Boolean("true")));
		rangeList1.add(r1);
		hash.put(h1, rangeList1);

		ArrayList<Range> rangeList2 = new ArrayList<Range>();
		DiscreteRange r2 = new DiscreteRange();
		r2.addElement(new DiscreteMeasure(new Boolean("false")));
		rangeList2.add(r2);
		hash.put(h3, rangeList2);

		return hash;
	}

	private Hashtable<Hypothesis, ArrayList<Range>> getAvailabilityRanges() {
		Hashtable<Hypothesis, ArrayList<Range>> hash = new Hashtable<Hypothesis, ArrayList<Range>>();

		ArrayList<Range> rangeListTmp = new ArrayList<Range>();
		// Set the lowerBound
		ContinueMeasure lowerBoundTemp = new ContinueMeasure(new Double(0));
		// Set the upperBound
		ContinueMeasure upperBoundTemp = new ContinueMeasure(new Double(5));
		// [lowerBound,upperBound]
		ContinueRange rangeTemp = new ContinueRange(lowerBoundTemp,
				upperBoundTemp, false, false);
		rangeListTmp.add(rangeTemp);
		hash.put(h1, rangeListTmp);

		rangeListTmp = new ArrayList<Range>();
		// Set the lowerBound
		lowerBoundTemp = new ContinueMeasure(new Double(5));
		// Set the upperBound
		upperBoundTemp = new ContinueMeasure(new Double(25));
		// (lowerBound,upperBound)
		rangeTemp = new ContinueRange(lowerBoundTemp, upperBoundTemp, true,
				true);
		rangeListTmp.add(rangeTemp);
		hash.put(h2, rangeListTmp);

		rangeListTmp = new ArrayList<Range>();
		// Set the lowerBound
		lowerBoundTemp = new ContinueMeasure(new Double(25));
		// Set the upperBound
		upperBoundTemp = new ContinueMeasure(new Double(60));
		// [lowerBound,upperBound]
		rangeTemp = new ContinueRange(lowerBoundTemp, upperBoundTemp, false,
				false);
		rangeListTmp.add(rangeTemp);
		hash.put(h3, rangeListTmp);

		rangeListTmp = new ArrayList<Range>();
		// Set the lowerBound
		lowerBoundTemp = new ContinueMeasure(new Double(60));
		// Set the upperBound
		upperBoundTemp = new ContinueMeasure(Double.POSITIVE_INFINITY);
		// [lowerBound,upperBound]
		rangeTemp = new ContinueRange(lowerBoundTemp, upperBoundTemp, false,
				true);
		rangeListTmp.add(rangeTemp);
		hash.put(h4, rangeListTmp);

		return hash;
	}

	private Hashtable<Hypothesis, ArrayList<Range>> getReliabilityRanges() {
		Hashtable<Hypothesis, ArrayList<Range>> hash = new Hashtable<Hypothesis, ArrayList<Range>>();

		ArrayList<Range> rangeListTmp = new ArrayList<Range>();
		// Set the lowerBound
		ContinueMeasure lowerBoundTemp = new ContinueMeasure(new Double(0));

		// Set the upperBound
		ContinueMeasure upperBoundTemp = new ContinueMeasure(new Double(5));
		// [lowerBound,upperBound)
		ContinueRange rangeTemp = new ContinueRange(lowerBoundTemp,
				upperBoundTemp, false, true);
		rangeListTmp.add(rangeTemp);
		hash.put(h1, rangeListTmp);

		rangeListTmp = new ArrayList<Range>();
		// Set the lowerBound
		lowerBoundTemp = new ContinueMeasure(new Double(5));
		// Set the upperBound
		upperBoundTemp = new ContinueMeasure(new Double(15));
		// [lowerBound,upperBound)
		rangeTemp = new ContinueRange(lowerBoundTemp, upperBoundTemp, false,
				true);
		rangeListTmp.add(rangeTemp);
		hash.put(h2, rangeListTmp);

		rangeListTmp = new ArrayList<Range>();
		// Set the lowerBound
		lowerBoundTemp = new ContinueMeasure(new Double(15));
		// Set the upperBound
		upperBoundTemp = new ContinueMeasure(new Double(25));
		// [lowerBound,upperBound]
		rangeTemp = new ContinueRange(lowerBoundTemp, upperBoundTemp, false,
				false);
		rangeListTmp.add(rangeTemp);
		hash.put(h3, rangeListTmp);

		// >20 & null
		rangeListTmp = new ArrayList<Range>();
		// Set the lowerBound
		lowerBoundTemp = new ContinueMeasure(new Double(20));
		// Set the upperBound
		upperBoundTemp = new ContinueMeasure(Double.POSITIVE_INFINITY);
		// (25,infinite)
		rangeTemp = new ContinueRange(lowerBoundTemp, upperBoundTemp, true,
				true);
		rangeListTmp.add(rangeTemp);
		hash.put(h4, rangeListTmp);

		return hash;
	}

	private Hashtable<Hypothesis, ArrayList<Range>> getAccessibilityRanges() {
		Hashtable<Hypothesis, ArrayList<Range>> hash = new Hashtable<Hypothesis, ArrayList<Range>>();

		ArrayList<Range> rangeListTmp = new ArrayList<Range>();
		// Set the lowerBound
		ContinueMeasure lowerBoundTemp = new ContinueMeasure(new Double(1));
		// Set the upperBound
		ContinueMeasure upperBoundTemp = new ContinueMeasure(new Double(1));
		// [lowerBound,lowerBound]
		ContinueRange rangeTemp = new ContinueRange(lowerBoundTemp,
				upperBoundTemp, false, false);
		rangeListTmp.add(rangeTemp);
		hash.put(h1, rangeListTmp);

		rangeListTmp = new ArrayList<Range>();
		// Set the lowerBound
		lowerBoundTemp = new ContinueMeasure(new Double(0.5));
		// Set the upperBound
		upperBoundTemp = new ContinueMeasure(new Double(1));
		// (lowerBound,upperBound)
		rangeTemp = new ContinueRange(lowerBoundTemp, upperBoundTemp, false,
				true);
		rangeListTmp.add(rangeTemp);
		hash.put(h2, rangeListTmp);

		// [0.5 , 0.3) or null
		rangeListTmp = new ArrayList<Range>();
		rangeTemp = new ContinueRange(ContinueMeasure.NOT_MEASURED,
				ContinueMeasure.NOT_MEASURED, false, false);
		rangeListTmp.add(rangeTemp);

		// Set the lowerBound
		lowerBoundTemp = new ContinueMeasure(new Double(0.3));
		// Set the upperBound
		upperBoundTemp = new ContinueMeasure(new Double(0.5));
		rangeTemp = new ContinueRange(lowerBoundTemp, upperBoundTemp, false,
				true);
		rangeListTmp.add(rangeTemp);
		hash.put(h3, rangeListTmp);

		// [0.3 , 0]
		// Set the lowerBound
		rangeListTmp = new ArrayList<Range>();
		lowerBoundTemp = new ContinueMeasure(new Double(0));
		// Set the upperBound
		upperBoundTemp = new ContinueMeasure(new Double(0.4));
		rangeTemp = new ContinueRange(lowerBoundTemp, upperBoundTemp, false,
				true);
		rangeListTmp.add(rangeTemp);
		hash.put(h4, rangeListTmp);

		return hash;
	}

	private class MySource extends SourceOfEvidence {

		public MySource(FrameOfDiscernment frameOfDiscernment, String name) {
			super(frameOfDiscernment, name);
		}

		@Override
		public ArrayList<MeasuredAttribute> readMeasureAttribute() {

			ArrayList<MeasuredAttribute> allMEasuredAttribute = new ArrayList<MeasuredAttribute>();

			// Set the Accessability Attribute
			MeasuredAttribute attr1 = new MeasuredAttribute("Accessibility");
			Double value1 = new Double(0.35);
			ContinueMeasure measure1 = new ContinueMeasure(value1);
			attr1.setMeasure(measure1);
			allMEasuredAttribute.add(attr1);

			// Set the Reliability Attribute
			MeasuredAttribute attr2 = new MeasuredAttribute("Reliability");
			Double value2 = new Double(21);
			ContinueMeasure measure2 = new ContinueMeasure(value2);
			attr2.setMeasure(measure2);
			allMEasuredAttribute.add(attr2);

			// Set the Availability Attribute
			MeasuredAttribute attr3 = new MeasuredAttribute("Availability");
			Double value3 = new Double(59);
			ContinueMeasure measure3 = new ContinueMeasure(value3);
			attr3.setMeasure(measure3);
			allMEasuredAttribute.add(attr3);

			// Set the Cryptography Attribute
			MeasuredAttribute attr4 = new MeasuredAttribute("Criptography");
			// In Java language a Boolean is false if the string is null or
			// everything different from "true"
			DiscreteMeasure measure4 = DiscreteMeasure.NOT_MEASURED;
			attr4.setMeasure(measure4);
			allMEasuredAttribute.add(attr4);

			// Set the Restrictions Attribute
			MeasuredAttribute attr5 = new MeasuredAttribute("Restrictions");
			Boolean value5 = new Boolean("true");
			DiscreteMeasure measure5 = new DiscreteMeasure(value5);
			attr5.setMeasure(measure5);
			allMEasuredAttribute.add(attr5);

			return allMEasuredAttribute;
		}
	}

}
