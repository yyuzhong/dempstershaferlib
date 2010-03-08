/**
 * 
 */
package interfaces;

import java.util.ArrayList;

/**
 * @author Elisa Costante
 * 
 */
public interface IPowerSet {

	public ArrayList<IElement> getElements();

	public void setElements(ArrayList<IElement> elements);

	public void addElement(IElement element);

	/**
	 * @return the size of power set or -1 if it has null elements.
	 */
	public int size();

}
