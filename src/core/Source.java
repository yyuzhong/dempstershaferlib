package core;

/**
 * 
 * @author Elisa Costante
 * 
 */
public class Source {

	protected FrameOfDiscernment frameOfDiscernment;

	protected MassDistribution massDistribution;

	public Source(FrameOfDiscernment frameOfDiscernment,
			MassDistribution massDistribution) {
		super();
		this.frameOfDiscernment = frameOfDiscernment;
		this.massDistribution = massDistribution;
	}

	public FrameOfDiscernment getFrameOfDiscernment() {
		return frameOfDiscernment;
	}

	public void setFrameOfDiscernment(FrameOfDiscernment frameOfDiscernment) {
		this.frameOfDiscernment = frameOfDiscernment;
	}

	public MassDistribution getMassDistribution() {
		return massDistribution;
	}

	public void setMassDistribution(MassDistribution massDistribution) {
		this.massDistribution = massDistribution;
	}

}
