package core;

import interfaces.IAttribute;

import java.util.ArrayList;

public class Attribute implements IAttribute {

	private String name;
	private Object value;
	private ArrayList<Hypothesis> hypotheses;
	private double weight;

	@Override
	public ArrayList<Hypothesis> getHypotheses() {

		return this.hypotheses;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.name;
	}

	@Override
	public Object getValue() {
		// TODO Auto-generated method stub
		return this.value;
	}

	@Override
	public double getWeight() {
		// TODO Auto-generated method stub
		return this.weight;
	}

}
