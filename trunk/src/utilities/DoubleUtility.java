package utilities;

import java.math.BigDecimal;
import java.text.NumberFormat;

/**
 * This class is a Wrapper to the {@link Double} class which has only 5
 * precision digit.
 * 
 * @author Elisa Costante
 * 
 */
public class DoubleUtility {

	public static final double EPSILON = 0.001;

	/**
	 * Returns true if (b - epsilon) <= a <= (b + epsilon), false otherwise
	 * 
	 * @param a
	 *            : the first double to compare.
	 * @param b
	 *            : the second double to compare.
	 * @param epsilon
	 *            : the tolerance.
	 * @return
	 */
	public static boolean areEqualsDouble(double a, double b, double epsilon) {
		if (Math.abs(b - epsilon) <= a && a <= Math.abs(b + epsilon))
			return true;
		else
			return false;
	}

	public static String doubleToString(Double a, int numberOfDigit) {
		if (a != null) {
			BigDecimal bigDecimal = new BigDecimal(new String("" + a))
					.setScale(numberOfDigit, BigDecimal.ROUND_HALF_UP);
			return bigDecimal.toString();

		} else
			return null;
	}

	public String doubleToString(int numberOfDigit, double number) {
		NumberFormat formatter = NumberFormat.getNumberInstance();

		// si imposta il numero massimo di cifre decimali
		// (2 in questo esempio)
		formatter.setMaximumFractionDigits(numberOfDigit);
		return formatter.format(number);

	}
}