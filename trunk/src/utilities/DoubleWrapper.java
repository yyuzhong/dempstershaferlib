package utilities;

import java.text.DecimalFormat;
import java.text.ParseException;

/**
 * This class is a Wrapper to the {@link Double} class which has only 5
 * precision digit.
 * 
 * @author Elisa Costante
 * 
 */
public class DoubleWrapper {

	private double doubleValue;

	public DoubleWrapper(double doubleValue) {
		setDoubleValue(doubleValue);

	}

	/**
	 * @return the doubleValue
	 */
	public double getDoubleValue() {
		return doubleValue;
	}

	/**
	 * @param doubleValue
	 *            the doubleValue to set
	 */
	public void setDoubleValue(double doubleValue) {
		// Beware DecimalFormat is not thread safe.
		DecimalFormat decimalFormat = new DecimalFormat("0.0000");
		String doubleString = decimalFormat.format(doubleValue);
		try {
			this.doubleValue = decimalFormat.parse(doubleString).doubleValue();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "" + doubleValue;
	}

}