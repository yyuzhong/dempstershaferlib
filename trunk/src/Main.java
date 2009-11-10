import core.FrameOfDiscernment;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		String fileName = "Hypothesis.txt";
		FrameOfDiscernment frameOfDiscernment = new FrameOfDiscernment(
				(new HypothesisFileHandler()).readHypothesis(fileName));

		// MassDistribution mass1= new MassDistribution(mass)

	}
}
