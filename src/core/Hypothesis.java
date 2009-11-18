package core;

/**
 * An Hypothesis is an element of the Frame of Discernment in Dempster-Shafer
 * Theory. It rapresent an event.
 * 
 * @author Elisa Costante
 * 
 */
public class Hypothesis implements Comparable, Cloneable {

	protected String name;
	protected Object value;

	public Hypothesis(String name, Object value) {
		super();
		this.name = name;
		this.value = value;
	}

	public Hypothesis(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	@Override
	public boolean equals(Object obj) {
		Hypothesis other = (Hypothesis) obj;
		if (other.getName().equals(this.getName()))
			return true;
		else
			return false;
	}

	@Override
	public String toString() {
		String objToString = "{" + name + "}";
		return objToString;
	}

	@Override
	public int compareTo(Object o) {
		Hypothesis hypothesis = (Hypothesis) o;
		String compare1 = hypothesis.getName();
		String compare2 = this.name;

		return compare1.compareTo(compare2);
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		String nameClone = new String(name);
		Object valueClone = value;
		Hypothesis clone = new Hypothesis(nameClone, valueClone);
		return clone;
	}

}
