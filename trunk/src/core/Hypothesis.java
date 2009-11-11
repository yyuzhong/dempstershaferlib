package core;

/**
 * An Hypothesis is an element of the Frame of Discernment in Dempster-Shafer
 * Theory. It rapresent an event.
 * 
 * @author Elisa Costante
 * 
 */
public class Hypothesis {

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

}
