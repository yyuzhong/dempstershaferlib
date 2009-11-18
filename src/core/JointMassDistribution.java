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

	public static JointMassDistribution order(JointMassDistribution mass) {
		ArrayList<Element> orderedElements = new ArrayList<Element>();
		for (int i = 0; i < mass.getElements().size(); i++) {
			Element element1 = mass.getElements().get(i);

			Element min = element1;
			for (int j = i; j < mass.getElements().size(); j++) {
				Element element2 = mass.getElements().get(j);

				if (element2.compareTo(element1) < 0) {
					min = element2;
				}

			}
			orderedElements.add(min);
		}

		JointMassDistribution orderedMass = new JointMassDistribution(
				orderedElements);
		orderedMass.setOperator(mass.getOperator());

		return orderedMass;

	}
}
