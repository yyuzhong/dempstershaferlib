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
import junit.framework.TestCase;
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

		fail("Not yet implemented");
	}

	private JointMassDistribution readDempsterResult() {
		JointMassDistribution dempsterResult = null;
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(
					"result.txt")));
			String readLine = br.readLine();

			while (readLine != null) {
				while (!readLine.startsWith("$Output DEMPSTER")) {
					readLine = br.readLine();
				}
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

		ArrayList<Hypothesis> hypothesiesList = null;

		StringTokenizer elementTokenizer = new StringTokenizer(readLine);

		// readLine=A,B-0.5;C=0.4
		while (elementTokenizer.hasMoreTokens()) {
			String elementString = elementTokenizer.nextToken(";");

			// elementString=A,B-0.5

			StringTokenizer hypTokenizer = new StringTokenizer(elementString);
			String hypothesiesString = elementTokenizer.nextToken("-");
			// hypothesiesString= A,B

			hypothesiesList = new ArrayList<Hypothesis>();

			while (hypTokenizer.hasMoreTokens()) {

				Hypothesis hypothesis = new Hypothesis(hypTokenizer
						.nextToken(","));

				hypothesiesList.add(hypothesis);
			}

		}

		return null;
	}

	/**
	 * Test method for
	 * {@link joint.JointManager#yagerJoint(java.util.ArrayList)}.
	 */
	public void testYagerJoint() {

		// JointMassDistribution yagerDistribution = JointManager
		// .yagerJoint(masses);

		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link joint.JointManager#averageJoint(java.util.ArrayList)}.
	 */
	public void testAverageJoint() {
		JointMassDistribution averageDistribution = JointManager
				.averageJoint(masses);
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link joint.JointManager#distanceEvidenceJoint(java.util.ArrayList)}.
	 */
	public void testDistanceEvidenceJoint() {

		// JointMassDistribution distanceDistribution = JointManager
		// .distanceEvidenceJoint(masses);
		// distanceDistribution.setOperator("Distance of Evidence");
		fail("Not yet implemented");
	}

}
