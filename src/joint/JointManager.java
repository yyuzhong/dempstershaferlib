package joint;

import java.util.ArrayList;

import core.Element;
import core.JointMassDistribution;
import core.MassDistribution;

public class JointManager {

	public static JointMassDistribution dempsterJoint(
			ArrayList<MassDistribution> masses) {
		return null;
	}

	public static JointMassDistribution yagerJoint(
			ArrayList<MassDistribution> masses) {

		return null;
	}

	public static JointMassDistribution averageJoint(
			ArrayList<MassDistribution> masses) {

		if (masses.size() > 1) {

			JointMassDistribution jointDistribution;
			int i = 0;

			MassDistribution m1 = masses.get(i);
			i++;
			MassDistribution m2 = masses.get(i);
			i++;

			jointDistribution = average(m1, m2);
			for (int j = i; j < masses.size(); j++) {
				jointDistribution = average(jointDistribution, masses.get(j));
			}
			jointDistribution.setOperator(JointMassDistribution.AVERAGE);

			if (jointDistribution.isValid())
				return jointDistribution;
			else
				return null;

		} else
			return null;
	}

	public static JointMassDistribution distanceEvidenceJoint(
			ArrayList<MassDistribution> masses) {
		return null;
	}

	private static JointMassDistribution average(MassDistribution m1,
			MassDistribution m2) {

		ArrayList<Element> m1Elements = m1.getElements();
		ArrayList<Element> m2Elements = m2.getElements();

		ArrayList<Element> jointElements = Element.getMassUnionElement(
				m1Elements, m2Elements);

		for (int i = 0; i < jointElements.size(); i++) {
			Element jointElement = jointElements.get(i);

			int m1Index = m1Elements.indexOf(jointElement);
			Element m1Element = null;
			if (m1Index > (-1))
				m1Element = m1Elements.get(m1Index);

			int m2Index = m2Elements.indexOf(jointElement);
			Element m2Element = null;
			if (m2Index > (-1))

				m2Element = m2Elements.get(m2Index);

			if (m1Element == null) {
				jointElement.setBpa(m2Element.getBpa() / 2);
			} else if (m2Element == null) {
				jointElement.setBpa(m1Element.getBpa() / 2);

			} else if (Element.getIntersection(m1Element, m2Element) != null) {
				double intersectBpa = (m1Element.getBpa() + m2Element.getBpa()) / 2;
				jointElement.setBpa(intersectBpa);
			}

		}

		JointMassDistribution jointMass = new JointMassDistribution(
				jointElements);
		if (jointMass.isValid()) {
			return jointMass;
		} else
			return null;
	}
}
