package interfaces;

import java.util.ArrayList;

import core.Hypothesis;

/**
 * This interface allows the definition of the attributes which allow one to
 * establish the mass probability of an hypothesis.
 * 
 * @author Elisa Costante
 * 
 */
public interface IAttribute {

	public ArrayList<Hypothesis> getHypotheses();

	public String getName();

	public double getWeight();

	public Object getValue();

}
