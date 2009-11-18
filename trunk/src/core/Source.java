package core;

import utilities.MassDistributionFileHandler;

/**
 * 
 * @author Elisa Costante
 * 
 */
public class Source {

	protected FrameOfDiscernment frameOfDiscernment;

	protected MassDistribution massDistribution;

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
		this.massDistribution = massDistribution;
	}

	public FrameOfDiscernment getFrameOfDiscernment() {
		return frameOfDiscernment;
	}

	public void setFrameOfDiscernment(FrameOfDiscernment frameOfDiscernment) {
		this.frameOfDiscernment = frameOfDiscernment;
	}

	public MassDistribution getMassDistribution(String filename) {

		if (massDistribution == null) {
			MassDistributionFileHandler fileHandler = new MassDistributionFileHandler();
			massDistribution = fileHandler.getMassDistribution(filename);
		}

		return massDistribution;
	}

	public void setMassDistribution(MassDistribution massDistribution) {
		this.massDistribution = massDistribution;
	}

}
