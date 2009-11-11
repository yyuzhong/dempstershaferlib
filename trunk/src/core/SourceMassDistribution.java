package core;

import java.util.ArrayList;

public class SourceMassDistribution extends MassDistribution {

	protected Source source;

	public SourceMassDistribution(ArrayList<Element> mass) {
		super(mass);
	}

	public Source getSource() {
		return source;
	}

	public void setSource(Source source) {
		this.source = source;
	}

}
