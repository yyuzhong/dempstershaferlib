package core;

import java.util.Hashtable;
import java.util.Iterator;

public class MassDistribution {

	protected Hashtable<Element, Double> mass;

	public MassDistribution(Hashtable<Element, Double> mass) {
		super();
		this.mass = mass;
	}

	public Double getBpa(Element element) {
		if (element != null)
			return mass.get(element);
		else
			return null;
	}

	public void addElement(Element element, Double bpa) {
		if (mass == null)
			mass = new Hashtable<Element, Double>();
		mass.put(element, bpa);
	}

	/**
	 * Verify if the mass distribution is valid, hat means the sum of all
	 * elements it's equal to one.
	 * 
	 * @return true if the mass distribution is valid, false otherwise.
	 */
	public boolean isValid() {
		double sum = 0;
		for (Iterator iterator = mass.values().iterator(); iterator.hasNext();) {
			Double bpa = (Double) iterator.next();
			sum = sum + bpa;

		}
		if (sum == 1)
			return true;
		else
			return false;
	}
}
