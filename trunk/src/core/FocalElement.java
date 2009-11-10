package core;

import java.util.Collection;

/**
 * A Focal Element is an Element with an evaluated mass.
 * 
 * @author Elisa Costante
 * 
 */
public class FocalElement extends Element {
	private double bpa;

	public FocalElement(Collection<Hypothesis> hypothesies, double bpa) {
		super(hypothesies);
		this.bpa = bpa;
	}

	public double getBpa() {
		return bpa;
	}

	public void setBpa(double bpa) {
		this.bpa = bpa;
	}

}
