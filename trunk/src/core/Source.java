package core;

/**
 * This class represents a source of evidence. Different source must have the
 * same {@link FrameOfDiscernment} to aggregate their {@link MassDistribution}.
 * 
 * @author Elisa Costante
 * 
 */
public class Source {

	protected FrameOfDiscernment frameOfDiscernment;

	// protected MassDistribution massDistribution;

	protected String name;

	public Source(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Source(FrameOfDiscernment frameOfDiscernment,
			MassDistribution massDistribution) {
		super();
		this.frameOfDiscernment = frameOfDiscernment;
		// this.massDistribution = massDistribution;
	}

	public FrameOfDiscernment getFrameOfDiscernment() {
		return frameOfDiscernment;
	}

	public void setFrameOfDiscernment(FrameOfDiscernment frameOfDiscernment) {
		this.frameOfDiscernment = frameOfDiscernment;
	}

}
