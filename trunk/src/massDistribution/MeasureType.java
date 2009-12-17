/**
 * 
 */
package massDistribution;

/**
 * @author Elisa Costante
 * 
 */
public enum MeasureType {
	CONTINUOUS("CONTINUOUS", 1), DISCRETE("DISCRETE", 2);

	private String name;
	private int value;

	private MeasureType(String name, int value) {

		this.name = name;
		this.value = value;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the value
	 */
	public int getValue() {
		return this.value;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(int value) {
		this.value = value;
	}
}
