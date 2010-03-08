package massDistribution;

import interfaces.IFocalElement;

import java.util.ArrayList;

import joint.JointOperator;
import core.FocalElement;
import core.FrameOfDiscernment;

public class JointMassDistribution extends MassDistribution {

	private JointOperator operator;

	public JointMassDistribution(ArrayList<IFocalElement> elements) {
		super(elements);
	}

	public JointOperator getOperator() {
		return operator;
	}

	public void setOperator(JointOperator operator) {
		this.operator = operator;
	}

	@Override
	public String toString() {
		return "[" + bodyOfEvidence + "]; [operator=" + operator + "]";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((operator == null) ? 0 : operator.hashCode());
		return result;
	}

	public static JointMassDistribution order(JointMassDistribution mass) {

		MassDistribution ordered = MassDistribution.order(mass);

		JointMassDistribution orderedMass = new JointMassDistribution(
				ordered.bodyOfEvidence);
		orderedMass.setOperator(mass.getOperator());

		return orderedMass;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see massDistribution.MassDistribution#clone()
	 */
	@Override
	public Object clone() throws CloneNotSupportedException {

		if (this.bodyOfEvidence != null) {
			ArrayList<IFocalElement> clonedBOF = new ArrayList<IFocalElement>();
			for (IFocalElement focalElement : bodyOfEvidence) {
				clonedBOF.add((FocalElement) focalElement.clone());
			}
			JointMassDistribution cloned = new JointMassDistribution(clonedBOF);
			if (this.frameOfDiscernment != null) {
				cloned
						.setFrameOfDiscernment((FrameOfDiscernment) frameOfDiscernment
								.clone());
			}
			if (this.operator != null)
				cloned.setOperator(operator);
			return cloned;
		}

		return null;

	}

	public static JointMassDistribution getEmptySetKnowledge() {
		ArrayList<IFocalElement> bodyOfEvidence = new ArrayList<IFocalElement>();
		FocalElement emptSet = new FocalElement(FrameOfDiscernment
				.getEmptySet(), 1.0);
		bodyOfEvidence.add(emptSet);

		JointMassDistribution mass = new JointMassDistribution(bodyOfEvidence);

		return mass;

	}
}
