package core;

import java.util.ArrayList;

public class JointMassDistribution extends MassDistribution {

	protected String operator;

	public JointMassDistribution(ArrayList<Element> mass) {
		super(mass);
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

}
