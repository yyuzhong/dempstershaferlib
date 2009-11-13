package joint;

public enum JointOperatorEnum {

	AVERAGE("AVERAGE", 1), DEMPSTER("DEMPSTER", 2), YAGER("YAGER", 3), DISTANCE_EVIDENCE(
			"DISTANCE_EVIDENCE", 4);

	private String name;
	private int value;

	JointOperatorEnum(String name, int value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

}
