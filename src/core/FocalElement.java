package core;

import java.util.ArrayList;

/**
 * A Focal Element is an Element with an evaluated elements.
 * 
 * @author Elisa Costante
 * 
 */
public class FocalElement extends Element {
	private Double bpa;

	public FocalElement(ArrayList<Hypothesis> hypothesies, double bpa) {
		super(hypothesies, bpa);
		this.bpa = bpa;
	}

	public Double getBpa() {
		return bpa;
	}

	public void setBpa(Double bpa) {
		this.bpa = bpa;
	}

}
