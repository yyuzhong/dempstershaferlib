/**
 * 
 */
package exception;

/**
 * @author Elisa Costante
 * 
 */
public class DempsterTotalConflictException extends Exception {

	private static final long serialVersionUID = 2163197023908545966L;

	public DempsterTotalConflictException() {
		super();
	}

	/**
	 * @param string
	 */
	public DempsterTotalConflictException(String string) {
		super(string);
	}

}
