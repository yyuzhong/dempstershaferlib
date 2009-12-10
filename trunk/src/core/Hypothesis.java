package core;

/**
 * An Hypothesis is an element of the Frame of Discernment in Dempster-Shafer
 * Theory. It rapresent an event.
 * 
 * @author Elisa Costante
 * 
 */
public class Hypothesis implements Comparable, Cloneable {

	protected String identifier;
	protected Object value;

	public Hypothesis(String identifier, Object value) {
		super();
		this.identifier = identifier;
		this.value = value;
	}

	public Hypothesis(String identifier) {
		this.identifier = identifier;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 * 
	 * Two Hypothesis are equals if they have the sae identifier
	 */
	@Override
	public boolean equals(Object obj) {
		Hypothesis other = (Hypothesis) obj;
		if (other.getIdentifier().equals(this.getIdentifier()))
			return true;
		else
			return false;
	}

	@Override
	public String toString() {
		String objToString = "{" + identifier + "}";
		return objToString;
	}

	@Override
	public int compareTo(Object o) {
		Hypothesis hypothesis = (Hypothesis) o;
		String compare1 = hypothesis.getIdentifier();
		String compare2 = this.identifier;

		return compare1.compareTo(compare2);
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		String nameClone = new String(identifier);
		Object valueClone = value;
		Hypothesis clone = new Hypothesis(nameClone, valueClone);
		return clone;
	}

}
