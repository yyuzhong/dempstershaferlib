package core;

import java.util.ArrayList;

/**
 * A Focal Element is an Element with an evaluated elements.
 * 
 * @author Elisa Costante
 * 
 */
public class FocalElement extends Element {
	private double bpa;

	public FocalElement(ArrayList<Hypothesis> hypothesies, double bpa) {
		super(hypothesies, bpa);
		this.bpa = bpa;
	}

	public double getBpa() {
		return bpa;
	}

	public void setBpa(double bpa) {
		this.bpa = bpa;
	}

}
