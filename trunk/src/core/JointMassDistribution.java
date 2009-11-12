package core;

import java.util.ArrayList;

public class JointMassDistribution extends MassDistribution {

	public static final String AVERAGE = "AVERAGE";
	public static final String DEMPSTER = "AVERAGE";
	public static final String YAGER = "AVERAGE";
	public static final String DISTANCE_EVIDENCE = "DISTANCE";

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

}
