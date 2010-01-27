package joint;

import java.util.ArrayList;

import massDistribution.JointMassDistribution;
import massDistribution.MassDistribution;
import utilities.DoubleUtility;
import core.Element;
import core.FocalElement;
import core.FrameOfDiscernment;
import exception.DempsterTotalConflictException;
import exception.JointNotPossibleException;
import exception.MassDistributionNotValidException;

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
	 * @throws JointNotPossibleException
	 * @throws MassDistributionNotValidException
	 * @throws DempsterTotalConflictException
	 */
	public static JointMassDistribution dempsterJoint(
			ArrayList<MassDistribution> masses, FrameOfDiscernment frame)
			throws JointNotPossibleException,
			MassDistributionNotValidException, DempsterTotalConflictException {
		if (masses.size() > 1)
			return applyOperator(masses, JointOperator.DEMPSTER, frame);
		else
			throw new JointNotPossibleException(
					"It's not possible do a joint with just one MassDistribution");
	}

	/**
	 * Applies Yager's operator to the list of {@link MassDistribution}.
	 * 
	 * @param masses
	 * @return the result of Yager's operator to the {@link MassDistribution}
	 *         list
	 * @throws JointNotPossibleException
	 * @throws MassDistributionNotValidException
	 * @throws DempsterTotalConflictException
	 */
	public static JointMassDistribution yagerJoint(
			ArrayList<MassDistribution> masses, FrameOfDiscernment frame)
			throws JointNotPossibleException,
			MassDistributionNotValidException, DempsterTotalConflictException {
		if (masses.size() > 1)
			return applyOperator(masses, JointOperator.YAGER, frame);
		else
			throw new JointNotPossibleException(
					"It's not possible do a joint with just one MassDistribution");
	}

	/**
	 * Applies the Average operator to the list of {@link MassDistribution}.
	 * 
	 * @param masses
	 * @return the result of Average operator to the {@link MassDistribution}
	 *         list
	 * @throws JointNotPossibleException
	 * @throws MassDistributionNotValidException
	 * @throws DempsterTotalConflictException
	 */
	public static JointMassDistribution averageJoint(
			ArrayList<MassDistribution> masses, FrameOfDiscernment frame)
			throws JointNotPossibleException,
			MassDistributionNotValidException, DempsterTotalConflictException {
		if (masses.size() > 1)

			return applyOperator(masses, JointOperator.AVERAGE, frame);
		else
			throw new JointNotPossibleException(
					"It's not possible do a joint with just one MassDistribution");

	}

	/**
	 * Applies the Distance Evidence operator to the list of
	 * {@link MassDistribution}.
	 * 
	 * @param masses
	 * @return the result of Distance Evidence operator to the
	 *         {@link MassDistribution} list
	 * @throws JointNotPossibleException
	 * @throws MassDistributionNotValidException
	 * @throws DempsterTotalConflictException
	 */
	public static JointMassDistribution distanceEvidenceJoint(
			ArrayList<MassDistribution> masses, FrameOfDiscernment frame)
			throws JointNotPossibleException,
			MassDistributionNotValidException, DempsterTotalConflictException {
		if (masses.size() > 1)

			return applyOperator(masses, JointOperator.DISTANCE, frame);

		else
			throw new JointNotPossibleException(
					"It's not possible do a joint with just one MassDistribution");

	}

	public static JointMassDistribution applyOperator(
			ArrayList<MassDistribution> masses, JointOperator operator,
			FrameOfDiscernment frame) throws MassDistributionNotValidException,
			JointNotPossibleException, DempsterTotalConflictException {
		if (masses.size() > 1) {

			JointMassDistribution jointDistribution = null;
			int i = 0;

			MassDistribution m1 = masses.get(i);
			i++;
			MassDistribution m2 = masses.get(i);
			i++;

			switch (operator.getValue()) {
			case 1:
				jointDistribution = average(m1, m2, masses);
				jointDistribution.setOperator(JointOperator.AVERAGE);
				break;
			case 2:
				jointDistribution = dempster(m1, m2);
				for (int j = i; j < masses.size(); j++) {
					jointDistribution = dempster(jointDistribution, masses
							.get(j));
				}
				jointDistribution.setOperator(JointOperator.DEMPSTER);
				break;
			case 3:
				// Yager
				// m(A)= m1(B)*m2(C) if B.intersect(c)=A
				Double conflict = getConflict(m1.getBodyOfEvidence(), m2
						.getBodyOfEvidence());
				jointDistribution = yager(m1, m2, conflict, frame);
				for (int j = i; j < masses.size(); j++) {
					conflict = conflict
							+ getConflict(
									jointDistribution.getBodyOfEvidence(),
									masses.get(j).getBodyOfEvidence());

					jointDistribution = yager(jointDistribution, masses.get(j),
							conflict, frame);
				}

				Element universalSet = frame.getUniversalSet();

				jointDistribution.getBodyOfEvidence().add(
						new FocalElement(universalSet, conflict));

				jointDistribution.setOperator(JointOperator.YAGER);
				break;
			case 4:
				jointDistribution = distance(masses);

				JointMassDistribution dempsterDistribution = dempster(
						jointDistribution, jointDistribution);

				for (int j = 0; j < masses.size() - 2; j++) {
					jointDistribution = dempster(dempsterDistribution,
							jointDistribution);
				}
				jointDistribution.setOperator(JointOperator.DISTANCE);
				break;

			default:
				break;
			}

			if (jointDistribution.isValid()) {
				MassDistribution.setBodyOfEvidence(jointDistribution);
				jointDistribution.setFrameOfDiscernment(frame);
				return jointDistribution;
			} else
				throw new MassDistributionNotValidException("MassDistribution"
						+ jointDistribution.toString() + " is not valid!");
		} else
			throw new JointNotPossibleException(
					"It's not possible do a joint with just one MassDistribution");
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
	 * @throws MassDistributionNotValidException
	 */
	private static JointMassDistribution distance(
			ArrayList<MassDistribution> masses)
			throws MassDistributionNotValidException {
		JointMassDistribution jointMassDistribution = null;

		if (masses.size() > 1) {
			double[][] similarityMatrix = getSimilarityMatrix(masses);

			double[] supportDegree = getSupportDegree(similarityMatrix);

			double[] credibility = getCredibility(supportDegree);

			ArrayList<FocalElement> jointElements = FocalElement
					.getMassUnionElement(masses);

			for (int i = 0; i < jointElements.size(); i++) {

				FocalElement jointElement = jointElements.get(i);

				double newBpa = 0;
				for (int j = 0; j < masses.size(); j++) {
					MassDistribution m = masses.get(j);
					double cred = credibility[j];
					double oldBpa = 0;

					FocalElement focalElement = FocalElement.findElement(m
							.getBodyOfEvidence(), jointElement.getElement());

					if (focalElement != null)
						oldBpa = focalElement.getBpa();
					newBpa = newBpa + (cred * oldBpa);
				}
				jointElement.setBpa(newBpa);

			}

			jointMassDistribution = new JointMassDistribution(jointElements);
		}
		if (jointMassDistribution.isValid())
			return jointMassDistribution;
		else
			throw new MassDistributionNotValidException("MassDistribution"
					+ jointMassDistribution.toString() + " is not valid!");
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

		ArrayList<FocalElement> m1Elements = m1.getBodyOfEvidence();
		ArrayList<FocalElement> m2Elements = m2.getBodyOfEvidence();

		for (int i = 0; i < m1Elements.size(); i++) {
			FocalElement el1 = m1Elements.get(i);

			for (int j = 0; j < m2Elements.size(); j++) {

				FocalElement el2 = m2Elements.get(j);
				// compute the intersection
				Element intersection = Element.getIntersection(
						el1.getElement(), el2.getElement());
				// compute the union
				Element union = Element.getUnion(el1.getElement(), el2
						.getElement());

				double unionSize = 0;
				double intersectionSize = 0;

				if (intersection != null)
					intersectionSize = (double) intersection.size();
				if (union != null)
					unionSize = (double) union.size();

				if (unionSize > 0) {

					// TODO controllare che sia corretto

					// scalarProduct= Summation [el1*el2] . |intersect(el1,el2)|
					// /
					// |union(el1,el2)|
					scalarProduct = scalarProduct
							+ ((el1.getBpa() * el2.getBpa()) * ((double) (intersectionSize / unionSize)));

				}
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
			MassDistribution m2, Double conflictTransfer,
			FrameOfDiscernment frame) throws MassDistributionNotValidException {
		ArrayList<FocalElement> m1Elements = m1.getBodyOfEvidence();
		ArrayList<FocalElement> m2Elements = m2.getBodyOfEvidence();

		// double conflict = getConflict(m1Elements, m2Elements);
		// conflictTransfer = new Double(conflictTransfer.doubleValue() +
		// conflict);

		ArrayList<FocalElement> jointElements = FocalElement
				.getMassUnionElement(m1Elements, m2Elements);

		for (int i = 0; i < jointElements.size(); i++) {
			FocalElement jointElement = jointElements.get(i);
			double bpa = 0;
			for (int k = 0; k < m1Elements.size(); k++) {
				FocalElement el1 = m1Elements.get(k);

				for (int j = 0; j < m2Elements.size(); j++) {
					FocalElement el2 = m2Elements.get(j);

					Element intersection = Element.getIntersection(el1
							.getElement(), el2.getElement());

					if (!intersection.isEmptySet()
							&& intersection.equals(jointElement.getElement())) {
						bpa = bpa + (el1.getBpa() * el2.getBpa());
					}
				}

			}
			jointElement.setBpa(bpa);
		}

		JointMassDistribution jointMass = new JointMassDistribution(
				jointElements);
		return jointMass;
		// if (jointMass.isValid()) {
		// return jointMass;
		// } else
		// throw new MassDistributionNotValidException("MassDistribution"
		// + jointMass.toString() + " is not valid!");

	}

	private static JointMassDistribution dempster(MassDistribution m1,
			MassDistribution m2) throws MassDistributionNotValidException,
			DempsterTotalConflictException {

		ArrayList<FocalElement> m1Elements = m1.getBodyOfEvidence();
		ArrayList<FocalElement> m2Elements = m2.getBodyOfEvidence();

		double conflict = getConflict(m1Elements, m2Elements);
		if (!DoubleUtility
				.areEqualsDouble(conflict, 1.0, DoubleUtility.EPSILON)) {
			ArrayList<FocalElement> jointElements = FocalElement
					.getMassUnionElement(m1Elements, m2Elements);

			for (int i = 0; i < jointElements.size(); i++) {
				FocalElement jointElement = jointElements.get(i);
				double bpa = 0;
				for (int k = 0; k < m1Elements.size(); k++) {
					FocalElement el1 = m1Elements.get(k);

					for (int j = 0; j < m2Elements.size(); j++) {
						FocalElement el2 = m2Elements.get(j);

						Element intersection = Element.getIntersection(el1
								.getElement(), el2.getElement());

						if (!intersection.isEmptySet()
								&& intersection.equals(jointElement
										.getElement())) {
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
				throw new MassDistributionNotValidException("MassDistribution"
						+ jointMass.toString() + " is not valid!");
		} else {
			// When the conflict is total (conflict==1.0) the Dempster rule
			// cannot be applied
			// throw new DempsterTotalConflictException(
			// "The dempster aggregation is not applicable");
			return JointMassDistribution.getEmptySetKnowledge();
		}

	}

	private static double getConflict(ArrayList<FocalElement> m1Elements,
			ArrayList<FocalElement> m2Elements) {
		double conflict = 0;
		for (int i = 0; i < m1Elements.size(); i++) {
			FocalElement el1 = m1Elements.get(i);

			for (int j = 0; j < m2Elements.size(); j++) {
				FocalElement el2 = m2Elements.get(j);

				if (Element.getIntersection(el1.getElement(), el2.getElement())
						.isEmptySet()) {
					conflict = conflict + (el1.getBpa() * el2.getBpa());
				}
			}

		}
		return conflict;
	}

	private static JointMassDistribution average(MassDistribution m1,
			MassDistribution m2, ArrayList<MassDistribution> masses)
			throws MassDistributionNotValidException {

		ArrayList<FocalElement> m1Elements = m1.getBodyOfEvidence();
		ArrayList<FocalElement> m2Elements = m2.getBodyOfEvidence();

		ArrayList<FocalElement> jointElements = FocalElement
				.getMassUnionElement(m1Elements, m2Elements);
		for (int i = 2; i < masses.size(); i++) {

			MassDistribution m3 = masses.get(i);
			ArrayList<FocalElement> el3 = m3.getBodyOfEvidence();
			jointElements = FocalElement
					.getMassUnionElement(jointElements, el3);
		}

		for (int i = 0; i < jointElements.size(); i++) {

			double bpa = 0;
			FocalElement jointElement = jointElements.get(i);

			ArrayList<FocalElement> sameElements = new ArrayList<FocalElement>();

			for (int j = 0; j < masses.size(); j++) {
				FocalElement same = FocalElement.findElement(masses.get(j)
						.getBodyOfEvidence(), jointElement.getElement());
				if (same != null) {
					sameElements.add(same);
				}
			}

			for (FocalElement element : sameElements) {
				bpa = bpa + element.getBpa();
			}

			jointElement.setBpa(bpa / masses.size());

		}

		JointMassDistribution jointMass = new JointMassDistribution(
				jointElements);
		if (jointMass.isValid()) {
			return jointMass;
		} else
			throw new MassDistributionNotValidException("MassDistribution"
					+ jointMass.toString() + " is not valid!");
	}
}
