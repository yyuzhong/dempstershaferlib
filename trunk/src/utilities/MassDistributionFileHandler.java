package utilities;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

import core.Element;
import core.FocalElement;
import core.Hypothesis;
import core.MassDistribution;

public class MassDistributionFileHandler {

	/**
	 * Read a {@link MassDistribution} from the {@link File}
	 * <code>filename</code>.
	 * 
	 * @param filename
	 * @return a Valid {@link MassDistribution} or <code>null</code> otherwise.
	 */
	public MassDistribution getMassDistribution(String filename) {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(
					filename)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}

		ArrayList<FocalElement> elements = new ArrayList<FocalElement>();

		try {
			ArrayList<Hypothesis> hypothesies = new ArrayList<Hypothesis>();

			String line = br.readLine();

			while (line != null) {
				if (!line.startsWith("#")) {

					StringTokenizer tokenizer = new StringTokenizer(line);
					String allHypothesies = tokenizer.nextToken("-");
					hypothesies = readHypothesies(allHypothesies);
					double bpa = Double.parseDouble(tokenizer.nextToken());

					FocalElement element = new FocalElement(new Element(
							hypothesies), new Double(bpa));
					elements.add(element);
				}
				line = br.readLine();
			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		MassDistribution massDistribution = new MassDistribution(elements);

		if (massDistribution.isValid())
			return massDistribution;
		else
			return null;

	}

	private ArrayList<Hypothesis> readHypothesies(String allHypothesies) {
		ArrayList<Hypothesis> hypothesies = new ArrayList<Hypothesis>();

		allHypothesies = allHypothesies.replaceAll("\\{", "");
		allHypothesies = allHypothesies.replaceAll("\\}", "");
		StringTokenizer tokenizer = new StringTokenizer(allHypothesies);

		while (tokenizer.hasMoreTokens()) {
			Hypothesis hypothesis = new Hypothesis(tokenizer.nextToken(","));
			hypothesies.add(hypothesis);
		}

		return hypothesies;
	}
}
