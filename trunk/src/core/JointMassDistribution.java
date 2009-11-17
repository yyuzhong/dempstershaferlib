package core;

import java.util.ArrayList;

public class JointMassDistribution extends MassDistribution {

	protected String operator;

	public JointMassDistribution(ArrayList<Element> elements) {
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
		return "Elements [" + elements + "]; [operator=" + operator + "]";
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

	// /*
	// * (non-Javadoc)
	// *
	// * @see java.lang.Object#equals(java.lang.Object)
	// */
	// @Override
	// public boolean equals(Object obj) {
	// JointMassDistribution other = (JointMassDistribution) obj;
	// if (other.getElements().size() == elements.size()
	// && other.getElements().containsAll(this.elements)
	// && other.getOperator().equals(operator))
	// return true;
	// else
	// return false;
	//
	// }

}
