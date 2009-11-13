package joint;

import java.util.ArrayList;

import core.Element;
import core.JointMassDistribution;
import core.MassDistribution;

/**
 * THe class supplies a static method for each different joint operator.
 * 
 * @author Elisa Costante
 * 
 */
public class JointManager {

	/**
	 * Applies Dempster's operator to the list of {@link MassDistribution}.
	 * 
	 * @param masses
	 * @return the result of Dempster's operator to the {@link MassDistribution}
	 *         list
	 */
	public static JointMassDistribution dempsterJoint(
			ArrayList<MassDistribution> masses) {
		if (masses.size() > 1)
			return applyOperator(masses, JointOperatorEnum.DEMPSTER.getValue());
		else
			return null;
	}

	/**
	 * Applies Yager's operator to the list of {@link MassDistribution}.
	 * 
	 * @param masses
	 * @return the result of Yager's operator to the {@link MassDistribution}
	 *         list
	 */
	public static JointMassDistribution yagerJoint(
			ArrayList<MassDistribution> masses) {

		return applyOperator(masses, JointOperatorEnum.YAGER.getValue());
	}

	/**
	 * Applies the Average operator to the list of {@link MassDistribution}.
	 * 
	 * @param masses
	 * @return the result of Average operator to the {@link MassDistribution}
	 *         list
	 */
	public static JointMassDistribution averageJoint(
			ArrayList<MassDistribution> masses) {

		return applyOperator(masses, JointOperatorEnum.AVERAGE.getValue());
	}

	/**
	 * Applies the Distance Evidence operator to the list of
	 * {@link MassDistribution}.
	 * 
	 * @param masses
	 * @return the result of Distance Evidence operator to the
	 *         {@link MassDistribution} list
	 */
	public static JointMassDistribution distanceEvidenceJoint(
			ArrayList<MassDistribution> masses) {
		return applyOperator(masses, JointOperatorEnum.DISTANCE_EVIDENCE
				.getValue());
	}

	public static JointMassDistribution applyOperator(
			ArrayList<MassDistribution> masses, int operator) {

		if (masses.size() > 1) {

			JointMassDistribution jointDistribution = null;
			int i = 0;

			MassDistribution m1 = masses.get(i);
			i++;
			MassDistribution m2 = masses.get(i);
			i++;

			switch (operator) {
			case 1:
				jointDistribution = average(m1, m2);
				for (int j = i; j < masses.size(); j++) {
					jointDistribution = average(jointDistribution, masses
							.get(j));
				}
				jointDistribution.setOperator(JointOperatorEnum.AVERAGE
						.getName());
				break;
			case 2:
				jointDistribution = dempster(m1, m2);
				for (int j = i; j < masses.size(); j++) {
					jointDistribution = dempster(jointDistribution, masses
							.get(j));
				}
				jointDistribution.setOperator(JointOperatorEnum.DEMPSTER
						.getName());
				break;
			case 3:
				jointDistribution = yager(m1, m2);
				for (int j = i; j < masses.size(); j++) {
					jointDistribution = yager(jointDistribution, masses.get(j));
				}
				jointDistribution
						.setOperator(JointOperatorEnum.YAGER.getName());
				break;
			case 4:
				jointDistribution = distance(m1, m2);
				for (int j = i; j < masses.size(); j++) {
					jointDistribution = distance(jointDistribution, masses
							.get(j));
				}
				jointDistribution
						.setOperator(JointOperatorEnum.DISTANCE_EVIDENCE
								.getName());
				break;

			default:
				break;
			}

			if (jointDistribution.isValid())
				return jointDistribution;
			else
				return null;

		} else
			// there is only one massDistribution in the array.
			return null;
	}

	private static JointMassDistribution distance(MassDistribution m1,
			MassDistribution m2) {
		// TODO Auto-generated method stub
		return null;
	}

	private static JointMassDistribution yager(MassDistribution m1,
			MassDistribution m2) {
		// TODO Auto-generated method stub
		return null;
	}

	private static JointMassDistribution dempster(MassDistribution m1,
			MassDistribution m2) {

		ArrayList<Element> m1Elements = m1.getElements();
		ArrayList<Element> m2Elements = m2.getElements();

		double conflict = getConflict(m1Elements, m1Elements);

		ArrayList<Element> jointElements = Element.getMassUnionElement(
				m1Elements, m2Elements);

		for (int i = 0; i < jointElements.size(); i++) {
			Element jointElement = jointElements.get(i);
			double bpa = 0;
			for (int k = 0; k < m1Elements.size(); k++) {
				Element el1 = m1Elements.get(k);

				for (int j = 0; j < m2Elements.size(); j++) {
					Element el2 = m2Elements.get(j);

					Element intersection = Element.getIntersection(el1, el2);

					if (intersection != null
							&& intersection.equals(jointElement)) {
						bpa = bpa + (el1.getBpa() * el2.getBpa());
					}
				}

			}

			bpa = bpa / (1 - conflict);
			jointElement.setBpa(bpa);
		}

		JointMassDistribution jointMass = new JointMassDistribution(
				jointElements);
		if (jointMass.isValid()) {
			return jointMass;
		} else
			return null;
	}

	private static double getConflict(ArrayList<Element> m1Elements,
			ArrayList<Element> m2Elements) {
		double conflict = 0;
		for (int i = 0; i < m1Elements.size(); i++) {
			Element el1 = m1Elements.get(i);

			for (int j = 0; j < m2Elements.size(); j++) {
				Element el2 = m2Elements.get(j);

				if (Element.getIntersection(el1, el2) == null) {
					conflict = conflict + (el1.getBpa() * el2.getBpa());
				}
			}

		}
		return conflict;
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
