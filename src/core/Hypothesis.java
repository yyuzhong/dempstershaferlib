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
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((this.identifier == null) ? 0 : this.identifier.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Hypothesis other = (Hypothesis) obj;
		if (this.identifier == null) {
			if (other.identifier != null)
				return false;
		} else if (!this.identifier.equals(other.identifier))
			return false;
		return true;
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
