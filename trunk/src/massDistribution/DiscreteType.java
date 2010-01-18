/**
 * 
 */
package massDistribution;

/**
 * @author Elisa Costante
 * 
 */
public enum DiscreteType {

	ORDERED("ORDERED", 2), NOT_ORDERED("NOT_ORDERED", 2);

	private String name;
	private int value;

	private DiscreteType(String name, int value) {
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
