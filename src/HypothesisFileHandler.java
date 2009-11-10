import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

import core.Hypothesis;

public class HypothesisFileHandler {

	public ArrayList<Hypothesis> readHypothesis(String fileName) {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(
					fileName)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}

		ArrayList<Hypothesis> hypothesies = new ArrayList<Hypothesis>();
		try {
			String name;
			String value;
			Hypothesis hypothesis;
			String line = br.readLine();
			StringTokenizer tokenizer = new StringTokenizer(line);

			while (line != null) {
				if (!line.startsWith("#")) {
					name = tokenizer.nextToken("-");
					value = tokenizer.nextToken("-");
					hypothesis = new Hypothesis(name, value);
					hypothesies.add(hypothesis);
				}
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return hypothesies;

	}
}
