package joint;

import java.util.ArrayList;

import utilities.DoubleWrapper;
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

				JointMassDistribution dempster = dempster(jointDistribution,
						jointDistribution);

				for (int j = 0; j < masses.size() - 2; j++) {
					jointDistribution = dempster(dempster, jointDistribution);
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
			DoubleWrapper[][] similarityMatrix = getSimilarityMatrix(masses);

			DoubleWrapper[] supportDegree = getSupportDegree(similarityMatrix);

			DoubleWrapper[] credibility = getCredibility(supportDegree);

			ArrayList<Element> jointElements = Element
					.getMassUnionElement(masses);

			for (int i = 0; i < jointElements.size(); i++) {

				Element jointElement = jointElements.get(i);

				DoubleWrapper newBpa = new DoubleWrapper(0);
				for (int j = 0; j < masses.size(); j++) {
					MassDistribution m = masses.get(j);
					DoubleWrapper cred = credibility[j];
					DoubleWrapper oldBpa = new DoubleWrapper(0);

					int index = m.getElements().indexOf(jointElement);
					if (index >= 0)
						oldBpa = m.getElements().get(index).getBpa();
					newBpa
							.setDoubleValue(newBpa.getDoubleValue()
									+ (cred.getDoubleValue() * oldBpa
											.getDoubleValue()));
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
	private static DoubleWrapper getScalarProduct(MassDistribution m1,
			MassDistribution m2) {

		DoubleWrapper scalarProduct = new DoubleWrapper(0);

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
				scalarProduct
						.setDoubleValue(scalarProduct.getDoubleValue()
								+ ((el1.getBpa().getDoubleValue() * el2
										.getBpa().getDoubleValue()) * (intersectionSize / unionSize)));
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
	private static DoubleWrapper getDistance(MassDistribution m1,
			MassDistribution m2) {
		DoubleWrapper normM1 = getScalarProduct(m1, m1); // ||m1||^2
		DoubleWrapper normM2 = getScalarProduct(m2, m2);// ||m2||^2
		DoubleWrapper scalarProduct = getScalarProduct(m1, m2);// <m1,m2>
		DoubleWrapper distance = new DoubleWrapper(
				Math
						.sqrt((normM1.getDoubleValue()
								+ normM2.getDoubleValue() - 2 * (scalarProduct
								.getDoubleValue())) / 2));
		return distance;

	}

	/**
	 * Computes the similarity between two mass distributions.
	 * 
	 * @param m1
	 * @param m2
	 * @return
	 */
	private static DoubleWrapper getSimilarity(MassDistribution m1,
			MassDistribution m2) {

		DoubleWrapper distance = getDistance(m1, m2);
		DoubleWrapper sim = new DoubleWrapper((Math.cos(distance
				.getDoubleValue()
				* Math.PI) + 1) / 2);
		return sim;

	}

	private static DoubleWrapper[] getSupportDegree(
			DoubleWrapper[][] similarityMatrix) {
		DoubleWrapper[] supportDegree = new DoubleWrapper[similarityMatrix.length];

		for (int i = 0; i < similarityMatrix.length; i++) {
			supportDegree[i] = new DoubleWrapper(0);
			for (int j = 0; j < similarityMatrix.length; j++) {
				if (i != j)
					supportDegree[i] = new DoubleWrapper(supportDegree[i]
							.getDoubleValue()
							+ similarityMatrix[i][j].getDoubleValue());
			}
		}

		return supportDegree;
	}

	private static DoubleWrapper[] getCredibility(DoubleWrapper[] supportDegree) {
		DoubleWrapper[] credibility = new DoubleWrapper[supportDegree.length];
		DoubleWrapper summation = new DoubleWrapper(0);

		for (int i = 0; i < supportDegree.length; i++) {

			summation = new DoubleWrapper(summation.getDoubleValue()
					+ supportDegree[i].getDoubleValue());

		}

		for (int i = 0; i < supportDegree.length; i++) {

			// credibility(m1)= Sup(mi) / Summation(Sup(mi)) for each mass i
			credibility[i] = new DoubleWrapper(supportDegree[i]
					.getDoubleValue()
					/ summation.getDoubleValue());

		}

		return credibility;
	}

	/**
	 * Compute the Similarity MAtrix of mass distributions.
	 * 
	 * @param masses
	 * @return the similarity MAtrix or null if there are no mass.
	 */
	private static DoubleWrapper[][] getSimilarityMatrix(
			ArrayList<MassDistribution> masses) {
		if (masses != null && masses.size() > 0) {
			int n = masses.size();
			DoubleWrapper[][] similarityMatrix = new DoubleWrapper[n][n];

			for (int i = 0; i < similarityMatrix.length; i++) {
				for (int j = i; j < similarityMatrix.length; j++) {
					if (i == j) {
						similarityMatrix[i][j] = new DoubleWrapper(1);
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

		DoubleWrapper conflict = getConflict(m1Elements, m2Elements);

		ArrayList<Element> jointElements = Element.getMassUnionElement(
				m1Elements, m2Elements);

		for (int i = 0; i < jointElements.size(); i++) {
			Element jointElement = jointElements.get(i);
			DoubleWrapper bpa = new DoubleWrapper(0);
			for (int k = 0; k < m1Elements.size(); k++) {
				Element el1 = m1Elements.get(k);

				for (int j = 0; j < m2Elements.size(); j++) {
					Element el2 = m2Elements.get(j);

					Element intersection = Element.getIntersection(el1, el2);

					if (intersection != null
							&& intersection.equals(jointElement)) {
						bpa.setDoubleValue(bpa.getDoubleValue()
								+ (el1.getBpa().getDoubleValue() * el2.getBpa()
										.getDoubleValue()));
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

		DoubleWrapper conflict = getConflict(m1Elements, m2Elements);

		ArrayList<Element> jointElements = Element.getMassUnionElement(
				m1Elements, m2Elements);

		for (int i = 0; i < jointElements.size(); i++) {
			Element jointElement = jointElements.get(i);
			DoubleWrapper bpa = new DoubleWrapper(0);
			for (int k = 0; k < m1Elements.size(); k++) {
				Element el1 = m1Elements.get(k);

				for (int j = 0; j < m2Elements.size(); j++) {
					Element el2 = m2Elements.get(j);

					Element intersection = Element.getIntersection(el1, el2);

					if (intersection != null
							&& intersection.equals(jointElement)) {
						bpa.setDoubleValue(bpa.getDoubleValue()
								+ (el1.getBpa().getDoubleValue() * el2.getBpa()
										.getDoubleValue()));
					}
				}

			}
			bpa.setDoubleValue(bpa.getDoubleValue()
					/ (1 - conflict.getDoubleValue()));
			jointElement.setBpa(bpa);
		}

		JointMassDistribution jointMass = new JointMassDistribution(
				jointElements);
		if (jointMass.isValid()) {
			return jointMass;
		} else
			return null;
	}

	private static DoubleWrapper getConflict(ArrayList<Element> m1Elements,
			ArrayList<Element> m2Elements) {
		DoubleWrapper conflict = new DoubleWrapper(0);
		for (int i = 0; i < m1Elements.size(); i++) {
			Element el1 = m1Elements.get(i);

			for (int j = 0; j < m2Elements.size(); j++) {
				Element el2 = m2Elements.get(j);

				if (Element.getIntersection(el1, el2) == null) {
					conflict.setDoubleValue(conflict.getDoubleValue()
							+ (el1.getBpa().getDoubleValue() * el2.getBpa()
									.getDoubleValue()));
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
				jointElement.setBpa(new DoubleWrapper(m2Element.getBpa()
						.getDoubleValue() / 2));
			} else if (m2Element == null) {
				jointElement.setBpa(new DoubleWrapper(m1Element.getBpa()
						.getDoubleValue() / 2));

			} else if (Element.getIntersection(m1Element, m2Element) != null) {
				DoubleWrapper intersectBpa = new DoubleWrapper((m1Element
						.getBpa().getDoubleValue() + m2Element.getBpa()
						.getDoubleValue()) / 2);
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
