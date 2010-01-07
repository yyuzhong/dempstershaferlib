/**
 * 
 */
package massDistribution;

/**
 * @author Elisa Costante
 * 
 */
public enum MetricType {
	INTEGER("INTEGER", 1), REAL("REAL", 2), DISCRETE("DISCRETE", 3);

	private String name;
	private int value;

	private MetricType(String name, int value) {
		this.setName(name);
		this.setValue(value);
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(int value) {
		this.value = value;
	}

	/**
	 * @return the value
	 */
	public int getValue() {
		return value;
	}
}
