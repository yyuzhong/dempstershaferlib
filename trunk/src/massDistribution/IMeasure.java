package massDistribution;

public interface IMeasure {

	/**
	 * Returns the value of the measure.
	 * 
	 * @return: the value of the measure.
	 */
	public Object getValue();

	/**
	 * Set the value of the measure to <code>value</code>.
	 * 
	 * @param value
	 */
	public void setValue(Object value);

}
