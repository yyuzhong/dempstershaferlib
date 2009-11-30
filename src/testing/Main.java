package testing;

import java.util.ArrayList;

import joint.JointManager;
import core.FrameOfDiscernment;
import core.JointMassDistribution;
import core.MassDistribution;
import exception.JointNotPossibleException;
import exception.MassDistributionNotValidException;
import gui.NewSwingApp;

public class Main {
	private static String filename = "test4.txt";

	/**
	 * @param args
	 * @throws CloneNotSupportedException
	 * @throws MassDistributionNotValidException
	 * @throws JointNotPossibleException
	 */
	public static void main(String[] args) throws CloneNotSupportedException,
			JointNotPossibleException, MassDistributionNotValidException {

		FrameOfDiscernment frameOfDiscernment = ReadTestUtility
				.readFrameOfDiscernment(filename);

		ArrayList<MassDistribution> masses = new ArrayList<MassDistribution>();

		// Source feedback = new Source("Feedback");
		// Source uddi = new Source("UDDI");
		// Source trustAuthority = new Source("TrustAthority");
		//
		// MassDistribution feedbackMass = feedback
		// .getMassDistribution("feedback.txt");
		// feedbackMass.setSource(feedback);
		//
		// MassDistribution uddiMass = uddi.getMassDistribution("uddi.txt");
		// uddiMass.setSource(uddi);

		// MassDistribution trustAuthorityMass = trustAuthority
		// .getMassDistribution("trustAuthority.txt");
		// trustAuthorityMass.setSource(trustAuthority);
		//
		// masses.add(feedbackMass);
		// masses.add(uddiMass);
		// masses.add(trustAuthorityMass);

		ReadTestUtility.readInput(filename, masses);

		ArrayList<MassDistribution> input = getNewArrayList(masses);

		// DEPMSTER
		JointMassDistribution demDistribution = JointManager.dempsterJoint(
				input, frameOfDiscernment);
		demDistribution = JointMassDistribution.order(demDistribution);
		System.out.println(demDistribution);

		// YAGER
		JointMassDistribution yagerDistribution = JointManager.yagerJoint(
				input, frameOfDiscernment);
		// yagerDistribution = JointMassDistribution.order(yagerDistribution);
		System.out.println(yagerDistribution);

		// Average
		JointMassDistribution averageDistribution = JointManager.averageJoint(
				input, frameOfDiscernment);
		averageDistribution = JointMassDistribution.order(averageDistribution);
		System.out.println(averageDistribution);

		// DISTANCE EVIDENCE
		JointMassDistribution distanceDistribution = JointManager
				.distanceEvidenceJoint(input, frameOfDiscernment);
		distanceDistribution = JointMassDistribution
				.order(distanceDistribution);
		System.out.println(distanceDistribution);

		ArrayList<MassDistribution> results = new ArrayList<MassDistribution>();

		results.add(demDistribution);
		results.add(yagerDistribution);
		results.add(distanceDistribution);
		results.add(averageDistribution);

		showresults(results, getNewArrayList(masses));

	}

	private static void showresults(ArrayList<MassDistribution> results,
			ArrayList<MassDistribution> input) {
		NewSwingApp frame = new NewSwingApp();
		frame.setJointResults(results);
		frame.setInput(input);
		frame.initGUI();

	}

	private static ArrayList<MassDistribution> getNewArrayList(
			ArrayList<MassDistribution> oldArray)
			throws CloneNotSupportedException {
		ArrayList<MassDistribution> newArray = new ArrayList<MassDistribution>();

		for (int i = 0; i < oldArray.size(); i++) {
			MassDistribution m = ((MassDistribution) oldArray.get(i));
			MassDistribution newMAss = (MassDistribution) m.clone();
			newArray.add(newMAss);
		}
		return newArray;
	}
}
