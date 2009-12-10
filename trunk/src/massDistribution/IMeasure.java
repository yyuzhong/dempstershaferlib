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

	/**
	 * Returns true if the {@link IMeasure} has a value.
	 * 
	 * @return true if and only if the <code>value</code> of the measure is
	 *         different from <code>NOT_MEASURED</code>, false otherwise.
	 */
	public boolean hasMeasuredValue();

}
