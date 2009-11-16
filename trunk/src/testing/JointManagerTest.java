/**
 * 
 */
package testing;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

import joint.JointManager;
import joint.JointOperatorEnum;
import junit.framework.TestCase;
import core.Element;
import core.Hypothesis;
import core.JointMassDistribution;
import core.MassDistribution;
import core.Source;
import core.SourceMassDistribution;

/**
 * @author Elisa Costante
 * 
 */
public class JointManagerTest extends TestCase {

	private ArrayList<MassDistribution> masses;

	/**
	 * @param name
	 */
	public JointManagerTest(String name) {
		super(name);

		masses = new ArrayList<MassDistribution>();

		readInput();
	}

	private void readInput() {

		Source feedback = new Source("Feedback");
		Source uddi = new Source("UDDI");
		Source trustAuthority = new Source("TrustAthority");
		SourceMassDistribution feedbackMass = feedback
				.getMassDistribution("feedback.txt");
		feedbackMass.setSource(feedback);

		SourceMassDistribution uddiMass = uddi.getMassDistribution("uddi.txt");
		uddiMass.setSource(uddi);

		SourceMassDistribution trustAuthorityMass = trustAuthority
				.getMassDistribution("trustAuthority.txt");
		trustAuthorityMass.setSource(trustAuthority);

		masses.add(feedbackMass);
		masses.add(uddiMass);
		masses.add(trustAuthorityMass);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/**
	 * Test method for
	 * {@link joint.JointManager#dempsterJoint(java.util.ArrayList)}.
	 */
	public void testDempsterJoint() {

		JointMassDistribution demDistribution = JointManager
				.dempsterJoint(masses);
		JointMassDistribution dempsterResult = readDempsterResult();
		dempsterResult.setOperator(JointOperatorEnum.DEMPSTER.getName());
		assertEquals(demDistribution, dempsterResult);

		// fail("Not yet implemented");
	}

	private JointMassDistribution readDempsterResult() {
		JointMassDistribution dempsterResult = null;
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(
					"result.txt")));
			String readLine = br.readLine();

			while (!readLine.startsWith("$Output DEMPSTER")) {
				readLine = br.readLine();
			}

			readLine = br.readLine();
			dempsterResult = parseResult(readLine);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return dempsterResult;
	}

	private JointMassDistribution parseResult(String readLine) {
		readLine = readLine.replaceAll("\\{", "");
		readLine = readLine.replaceAll("\\}", "");

		JointMassDistribution results = null;

		ArrayList<Hypothesis> hypothesiesList = null;

		ArrayList<Element> elementList = new ArrayList<Element>();

		StringTokenizer elementTokenizer = new StringTokenizer(readLine);

		// readLine=A,B-0.5;C=0.4
		while (elementTokenizer.hasMoreTokens()) {
			String elementString = elementTokenizer.nextToken(";");

			// elementString=A,B-0.5
			Element el = parseElement(elementString);

			elementList.add(el);
		}
		if (elementList.size() > 0)
			results = new JointMassDistribution(elementList);

		return results;
	}

	private Element parseElement(String elementString) {
		// elementString=A,B-0.5
		ArrayList<Hypothesis> hypothesiesList = new ArrayList<Hypothesis>();
		StringTokenizer elementTokenizer = new StringTokenizer(elementString);
		String hypothesiesString = elementTokenizer.nextToken("-");
		hypothesiesList = parseHypothesies(hypothesiesString);
		Double bpa = Double.parseDouble(elementTokenizer.nextToken("-"));

		Element el = new Element(hypothesiesList, bpa);
		return el;
	}

	private ArrayList<Hypothesis> parseHypothesies(String hypothesiesString) {
		// hypothesiesString=A,B
		ArrayList<Hypothesis> hypothesiesList = new ArrayList<Hypothesis>();
		StringTokenizer hypTokenizer = new StringTokenizer(hypothesiesString);
		while (hypTokenizer.hasMoreTokens()) {

			Hypothesis hypothesis = new Hypothesis(hypTokenizer.nextToken(","));

			hypothesiesList.add(hypothesis);
		}
		return hypothesiesList;
	}

	/**
	 * Test method for
	 * {@link joint.JointManager#yagerJoint(java.util.ArrayList)}.
	 */
	public void testYagerJoint() {

		JointMassDistribution yagerDistribution = JointManager
				.yagerJoint(masses);
		JointMassDistribution yagerResult = readYagerResult();
		yagerResult.setOperator(JointOperatorEnum.YAGER.getName());
		assertEquals(yagerDistribution, yagerResult);
	}

	private JointMassDistribution readYagerResult() {
		JointMassDistribution yagerResult = null;
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(
					"result.txt")));
			String readLine = br.readLine();

			while (!readLine.startsWith("$Output YAGER")) {
				readLine = br.readLine();
			}

			readLine = br.readLine();
			yagerResult = parseResult(readLine);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return yagerResult;
	}

	/**
	 * Test method for
	 * {@link joint.JointManager#averageJoint(java.util.ArrayList)}.
	 */
	public void testAverageJoint() {
		JointMassDistribution averageDistribution = JointManager
				.averageJoint(masses);
		JointMassDistribution averageResult = readAverageResult();
		averageResult.setOperator(JointOperatorEnum.AVERAGE.getName());
		assertEquals(averageDistribution, averageResult);
	}

	private JointMassDistribution readAverageResult() {
		JointMassDistribution averageResult = null;
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(
					"result.txt")));
			String readLine = br.readLine();

			while (!readLine.startsWith("$Output AVERAGE")) {
				readLine = br.readLine();
			}

			readLine = br.readLine();
			averageResult = parseResult(readLine);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return averageResult;
	}

	/**
	 * Test method for
	 * {@link joint.JointManager#distanceEvidenceJoint(java.util.ArrayList)}.
	 */
	public void testDistanceEvidenceJoint() {

		JointMassDistribution distanceDistribution = JointManager
				.distanceEvidenceJoint(masses);
		JointMassDistribution distanceResult = readDistanceResult();
		distanceResult.setOperator(JointOperatorEnum.DISTANCE_EVIDENCE
				.getName());
		assertEquals(distanceDistribution, distanceResult);

		fail("Not yet implemented");
	}

	private JointMassDistribution readDistanceResult() {
		JointMassDistribution distanceResult = null;
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(
					"result.txt")));
			String readLine = br.readLine();

			while (!readLine.startsWith("$Output DISTANCE")) {
				readLine = br.readLine();
			}

			readLine = br.readLine();
			distanceResult = parseResult(readLine);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return distanceResult;
	}

}
