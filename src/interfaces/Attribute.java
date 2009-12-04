package interfaces;


/**
 * This interface allows the definition of the attributes which allow one to
 * establish the mass probability of an hypothesis.
 * 
 * @author Elisa Costante
 * 
 */
public class Attribute {

	protected String identifier;

	/**
	 * @return the identifier
	 */
	public String getIdentifier() {
		return identifier;
	}

	/**
	 * @param identifier
	 *            the identifier to set
	 */
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	/**
	 * @param measure
	 *            the value to set
	 */

}
