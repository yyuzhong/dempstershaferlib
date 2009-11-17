package joint;

import java.util.ArrayList;

import core.Element;
import core.FrameOfDiscernment;
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
				jointDistribution = distance(masses);

				JointMassDistribution dempsterDistribution = dempster(
						jointDistribution, jointDistribution);

				for (int j = 0; j < masses.size() - 2; j++) {
					jointDistribution = dempster(dempsterDistribution,
							jointDistribution);
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

	/**
	 * Applies the Chen-Shy distance evidence aggregation to the mass
	 * distributions.</br>
	 * 
	 * The algorithm of computation is the following: </br> 1. Computation of
	 * SImilarity Matrix <br>
	 * 2. Computation of the Support degree of each piece of evidence mi<br>
	 * 3. Computation of the Credibility degree of each piece of evidence mi <br>
	 * 4. Computation of the modified average mass <br>
	 * 5. If there are N different sources THe DempsterRule will be apllied N-1
	 * times
	 * 
	 * @param m1
	 * @param m2
	 * @return
	 */
	private static JointMassDistribution distance(
			ArrayList<MassDistribution> masses) {
		JointMassDistribution jointMassDistribution = null;

		if (masses.size() > 1) {
			double[][] similarityMatrix = getSimilarityMatrix(masses);

			double[] supportDegree = getSupportDegree(similarityMatrix);

			double[] credibility = getCredibility(supportDegree);

			ArrayList<Element> jointElements = Element
					.getMassUnionElement(masses);

			for (int i = 0; i < jointElements.size(); i++) {

				Element jointElement = jointElements.get(i);

				double newBpa = 0;
				for (int j = 0; j < masses.size(); j++) {
					MassDistribution m = masses.get(j);
					double cred = credibility[j];
					double oldBpa = 0;

					int index = m.getElements().indexOf(jointElement);
					if (index >= 0)
						oldBpa = m.getElements().get(index).getBpa();
					newBpa = newBpa + (cred * oldBpa);
				}
				jointElement.setBpa(newBpa);

			}

			jointMassDistribution = new JointMassDistribution(jointElements);
		}
		if (jointMassDistribution.isValid())
			return jointMassDistribution;
		else
			return null;
	}

	/**
	 * Computes the scalar product between two mass distributions
	 * 
	 * @param m1
	 *            : {@link MassDistribution}
	 * @param m2
	 *            : {@link MassDistribution}
	 * @return the scalarProduct.
	 */
	private static double getScalarProduct(MassDistribution m1,
			MassDistribution m2) {

		double scalarProduct = 0;

		ArrayList<Element> m1Elements = m1.getElements();
		ArrayList<Element> m2Elements = m2.getElements();

		for (int i = 0; i < m1Elements.size(); i++) {
			Element el1 = m1Elements.get(i);

			for (int j = 0; j < m2Elements.size(); j++) {

				Element el2 = m2Elements.get(j);
				Element intersection = Element.getIntersection(el1, el2);
				Element union = Element.getUnion(el1, el2);

				int unionSize = 0;
				int intersectionSize = 0;

				if (intersection != null)
					intersectionSize = intersection.size();
				if (union != null)
					unionSize = union.size();

				// scalarProduct= Summation [el1*el2] . |intersect(el1,el2)| /
				// |union(el1,el2)|
				scalarProduct = scalarProduct
						+ ((el1.getBpa() * el2.getBpa()) * (intersectionSize / unionSize));
			}
		}

		return scalarProduct;

	}

	/**
	 * Compute the distance between two mass distributions.
	 * 
	 * @param m1
	 * @param m2
	 * @return
	 */
	private static double getDistance(MassDistribution m1, MassDistribution m2) {
		double normM1 = getScalarProduct(m1, m1); // ||m1||^2
		double normM2 = getScalarProduct(m2, m2);// ||m2||^2
		double scalarProduct = getScalarProduct(m1, m2);// <m1,m2>
		double distance = Math
				.sqrt((normM1 + normM2 - 2 * (scalarProduct)) / 2);
		return distance;

	}

	/**
	 * Computes the similarity between two mass distributions.
	 * 
	 * @param m1
	 * @param m2
	 * @return
	 */
	private static double getSimilarity(MassDistribution m1, MassDistribution m2) {

		double distance = getDistance(m1, m2);
		double sim = (Math.cos(distance * Math.PI) + 1) / 2;
		return sim;

	}

	private static double[] getSupportDegree(double[][] similarityMatrix) {
		double[] supportDegree = new double[similarityMatrix.length];

		for (int i = 0; i < similarityMatrix.length; i++) {
			supportDegree[i] = 0;
			for (int j = 0; j < similarityMatrix.length; j++) {
				if (i != j)
					supportDegree[i] = supportDegree[i]
							+ similarityMatrix[i][j];
			}
		}

		return supportDegree;
	}

	private static double[] getCredibility(double[] supportDegree) {
		double[] credibility = new double[supportDegree.length];
		double summation = 0;

		for (int i = 0; i < supportDegree.length; i++) {

			summation = summation + supportDegree[i];

		}

		for (int i = 0; i < supportDegree.length; i++) {

			// credibility(m1)= Sup(mi) / Summation(Sup(mi)) for each mass i
			credibility[i] = supportDegree[i] / summation;

		}

		return credibility;
	}

	/**
	 * Compute the Similarity MAtrix of mass distributions.
	 * 
	 * @param masses
	 * @return the similarity MAtrix or null if there are no mass.
	 */
	private static double[][] getSimilarityMatrix(
			ArrayList<MassDistribution> masses) {
		if (masses != null && masses.size() > 0) {
			int n = masses.size();
			double[][] similarityMatrix = new double[n][n];

			for (int i = 0; i < similarityMatrix.length; i++) {
				for (int j = i; j < similarityMatrix.length; j++) {
					if (i == j) {
						similarityMatrix[i][j] = 1;
					} else {
						similarityMatrix[i][j] = similarityMatrix[j][i] = getSimilarity(
								masses.get(i), masses.get(j));
					}
				}
			}
			return similarityMatrix;
		}
		return null;
	}

	private static JointMassDistribution yager(MassDistribution m1,
			MassDistribution m2) {
		ArrayList<Element> m1Elements = m1.getElements();
		ArrayList<Element> m2Elements = m2.getElements();

		double conflict = getConflict(m1Elements, m2Elements);

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
			jointElement.setBpa(bpa);
		}

		Element universalSet = FrameOfDiscernment.getUniversalSet();

		int index = jointElements.indexOf(universalSet);
		if (index >= 0) {
			// The UNIVERSALSET is already in the list
			jointElements.get(index).setBpa(conflict);

		} else // The UNIVERSALSET must be added to the list

		{
			universalSet.setBpa(conflict);
			jointElements.add(universalSet);

		}
		JointMassDistribution jointMass = new JointMassDistribution(
				jointElements);
		if (jointMass.isValid()) {
			return jointMass;
		} else
			return null;
	}

	private static JointMassDistribution dempster(MassDistribution m1,
			MassDistribution m2) {

		ArrayList<Element> m1Elements = m1.getElements();
		ArrayList<Element> m2Elements = m2.getElements();

		double conflict = getConflict(m1Elements, m2Elements);

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
