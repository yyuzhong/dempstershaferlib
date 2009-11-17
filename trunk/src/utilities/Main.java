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
	 */
	public static void main(String[] args) {

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

		ArrayList<MassDistribution> input = new ArrayList<MassDistribution>(
				masses);
		JointMassDistribution demDistribution = JointManager
				.dempsterJoint(input);

		// JointMassDistribution yagerDistribution = JointManager
		// .yagerJoint(masses);

		JointMassDistribution averageDistribution = JointManager
				.averageJoint(new ArrayList<MassDistribution>(masses));

		JointMassDistribution distanceDistribution = JointManager
				.distanceEvidenceJoint(new ArrayList<MassDistribution>(masses));

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
}
