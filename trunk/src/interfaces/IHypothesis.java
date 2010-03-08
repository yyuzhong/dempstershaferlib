/**
 * 
 */
package interfaces;


/**
 * @author Elisa Costante
 * 
 */
public interface IHypothesis extends Cloneable {
	public String getIdentifier();

	public void setIdentifier(String identifier);

	public Object getValue();

	public void setValue(Object value);

	public Object clone() throws CloneNotSupportedException;

}
