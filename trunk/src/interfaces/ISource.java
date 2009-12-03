package interfaces;

import massDistribution.ClassAttributeMap;
import massDistribution.MassDistribution;

public interface ISource {

	/**
	 * Return the {@link MassDistribution} of the source based on the
	 * ClassificationDB <br>
	 * ref. [url: http://www.micro.dibe.unige.it/maurizio_valle/
	 * Elettronica_Industriale_2/Dempster_shafer.pdf]
	 * 
	 * @return the {@link MassDistribution} of the source.
	 */
	public MassDistribution getMassDistribution(
			ClassAttributeMap classificationDB);

}
