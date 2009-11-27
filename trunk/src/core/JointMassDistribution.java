package core;

import java.util.ArrayList;

public class JointMassDistribution extends MassDistribution {

	protected String operator;

	public JointMassDistribution(ArrayList<FocalElement> elements) {
		super(elements);
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	@Override
	public String toString() {
		return "Elements [" + bodyOfEvidence + "]; [operator=" + operator + "]";
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

}
