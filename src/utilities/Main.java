package utilities;

import gui.NewSwingApp;

import java.util.ArrayList;

import joint.JointManager;
import core.JointMassDistribution;
import core.MassDistribution;
import core.Source;
import core.SourceMassDistribution;

public class Main {

	/**
	 * @param args
	 * @throws CloneNotSupportedException
	 */
	public static void main(String[] args) throws CloneNotSupportedException {

		// String fileName = "Hypothesis.txt";
		// FrameOfDiscernment frameOfDiscernment = new FrameOfDiscernment(
		// (new HypothesisFileHandler()).readHypothesis(fileName));

		ArrayList<MassDistribution> masses = new ArrayList<MassDistribution>();

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

		ArrayList<MassDistribution> input = getNewArrayList(masses);
		// ArrayList<MassDistribution> input = (ArrayList<MassDistribution>)
		// masses
		// .clone();

		JointMassDistribution demDistribution = JointManager
				.dempsterJoint(input);

		// JointMassDistribution yagerDistribution = JointManager
		// .yagerJoint(input);

		JointMassDistribution averageDistribution = JointManager
				.averageJoint(input);

		JointMassDistribution distanceDistribution = JointManager
				.distanceEvidenceJoint(input);

		ArrayList<MassDistribution> results = new ArrayList<MassDistribution>();
		results.add(demDistribution);
		// results.add(yagerDistribution);

		results.add(distanceDistribution);
		results.add(averageDistribution);

		showresults(results);

	}

	private static void showresults(ArrayList<MassDistribution> results) {
		NewSwingApp frame = new NewSwingApp();
		frame.setJointResults(results);
		frame.initGUI();

	}

	private static ArrayList<MassDistribution> getNewArrayList(
			ArrayList<MassDistribution> oldArray)
			throws CloneNotSupportedException {
		ArrayList<MassDistribution> newArray = (ArrayList<MassDistribution>) oldArray
				.clone();

		for (int i = 0; i < oldArray.size(); i++) {
			MassDistribution m = ((MassDistribution) oldArray.get(i));
			MassDistribution newMAss = (MassDistribution) m.clone();
			newArray.add(newMAss);
		}
		return newArray;
	}
}
