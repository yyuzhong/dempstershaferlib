/**
 * 
 */
package testing;

import java.util.ArrayList;

import joint.JointManager;
import joint.JointOperator;
import junit.framework.AssertionFailedError;
import junit.framework.TestCase;
import massDistribution.JointMassDistribution;
import massDistribution.MassDistribution;
import core.FrameOfDiscernment;
import exception.JointNotPossibleException;
import exception.MassDistributionNotValidException;

/**
 * @author Elisa Costante
 * 
 */
public class JointManagerTest extends TestCase {

	private ArrayList<MassDistribution> masses;
	private String filename = "test1.txt";
	private FrameOfDiscernment frame;

	/**
	 * @param identifier
	 */
	public JointManagerTest(String name) {
		super(name);

		masses = new ArrayList<MassDistribution>();
		frame = ReadTestUtility.readFrameOfDiscernment(filename);

		ReadTestUtility.readInput(filename, masses);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		masses = null;
		masses = new ArrayList<MassDistribution>();

		ReadTestUtility.readInput(filename, masses);

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
	 * 
	 * @throws MassDistributionNotValidException
	 * @throws JointNotPossibleException
	 */
	public void testDempsterJoint() throws JointNotPossibleException,
			MassDistributionNotValidException {

		JointMassDistribution demDistribution = JointManager.dempsterJoint(
				masses, frame);
		JointMassDistribution dempsterResult = ReadTestUtility
				.readDempsterResult(filename);
		dempsterResult.setOperator(JointOperator.DEMPSTER);

		try {
			assertEquals(demDistribution, dempsterResult);
		} catch (AssertionFailedError e) {
			System.out.println("Oracolo: " + dempsterResult.toString());
			System.out
					.println("Dempster result: " + demDistribution.toString());

		}

		// fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link joint.JointManager#yagerJoint(java.util.ArrayList)}.
	 * 
	 * @throws MassDistributionNotValidException
	 * @throws JointNotPossibleException
	 */
	public void testYagerJoint() throws JointNotPossibleException,
			MassDistributionNotValidException {

		JointMassDistribution yagerDistribution = JointManager.yagerJoint(
				masses, frame);
		JointMassDistribution yagerResult = ReadTestUtility
				.readYagerResult(filename);
		yagerResult.setOperator(JointOperator.YAGER);

		try {
			assertEquals(yagerDistribution, yagerResult);
		} catch (AssertionFailedError e) {
			System.out.println("Oracolo: " + yagerResult.toString());
			System.out.println("Yager result: " + yagerDistribution.toString());

		}

	}

	/**
	 * Test method for
	 * {@link joint.JointManager#averageJoint(java.util.ArrayList)}.
	 * 
	 * @throws MassDistributionNotValidException
	 * @throws JointNotPossibleException
	 */
	public void testAverageJoint() throws JointNotPossibleException,
			MassDistributionNotValidException {
		JointMassDistribution averageDistribution = JointManager.averageJoint(
				masses, frame);
		JointMassDistribution averageResult = ReadTestUtility
				.readAverageResult(filename);
		averageResult.setOperator(JointOperator.AVERAGE);

		try {
			assertEquals(averageDistribution, averageResult);

		} catch (AssertionFailedError e) {
			System.out.println("Oracolo: " + averageResult.toString());
			System.out.println("Average result: "
					+ averageDistribution.toString());

		}

	}

	/**
	 * Test method for
	 * {@link joint.JointManager#distanceEvidenceJoint(java.util.ArrayList)}.
	 * 
	 * @throws MassDistributionNotValidException
	 * @throws JointNotPossibleException
	 */
	public void testDistanceEvidenceJoint() throws JointNotPossibleException,
			MassDistributionNotValidException {

		JointMassDistribution distanceDistribution = JointManager
				.distanceEvidenceJoint(masses, frame);
		JointMassDistribution distanceResult = ReadTestUtility
				.readDistanceResult(filename);
		distanceResult.setOperator(JointOperator.DISTANCE_EVIDENCE);
		try {
			assertEquals(distanceDistribution, distanceResult);
		} catch (AssertionFailedError e) {
			System.out.println("Oracolo: " + distanceResult.toString());
			System.out.println("Evidence result: "
					+ distanceDistribution.toString());

		}

	}

}
