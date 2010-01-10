package core;

import java.util.ArrayList;

/**
 * A {@link PowerSet} is a collection of all {@link Element} coming form a
 * {@link FrameOfDiscernment}. If the size of the {@link FrameOfDiscernment} is
 * equal to n then we will have a {@link PowerSet} with 2^n {@link Element}.
 * Also the <i>empty set</i> and the <i>universal set</i> belong to the
 * {@link PowerSet}.
 * 
 * @author Elisa Costante
 * 
 */
public class PowerSet {

	private ArrayList<Element> elements;

	public PowerSet() {
		elements = new ArrayList<Element>();
	}

	public PowerSet(ArrayList<Element> elements) {
		this.elements = elements;
	}

	public ArrayList<Element> getElements() {
		return elements;
	}

	public void setElements(ArrayList<Element> elements) {
		this.elements = elements;
	}

	public void addElement(Element element) {
		if (elements == null)
			elements = new ArrayList<Element>();
		elements.add(element);
	}

	/**
	 * @return the size of power set or -1 if it has null elements.
	 */
	public int size() {
		if (elements != null)
			return elements.size();
		else
			return -1;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		if (this.elements == null) {
			return "{}";
		} else {
			String powerSetToString = "{";
			for (int i = 0; i < elements.size(); i++) {
				powerSetToString = powerSetToString + elements.get(i);
			}
			powerSetToString = powerSetToString + "}";
			return powerSetToString;
		}
	}

}
