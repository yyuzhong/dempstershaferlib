package interfaces;

import java.util.ArrayList;

import massDistribution.ClassAttributeMap;
import massDistribution.MassDistribution;
import massDistribution.MeasuredAttribute;
import core.FrameOfDiscernment;
import core.Hypothesis;

public interface ISource {

	/**
	 * Return the {@link MassDistribution} of the source based on the
	 * ClassificationDB <br>
	 * ref. [url: http://www.micro.dibe.unige.it/maurizio_valle/
	 * Elettronica_Industriale_2/Dempster_shafer.pdf]
	 * 
	 * @param frameOfDiscernment
	 *            : the frameOfDiscernment about one wants to have the
	 *            {@link MassDistribution}
	 * @param classificationDB
	 *            : the mapping beetween attribute values and {@link Hypothesis}
	 *            of the {@link FrameOfDiscernment}.
	 * @return the {@link MassDistribution} of the source.
	 */

	public MassDistribution getMassDistribution(
			FrameOfDiscernment frameOfDiscernment,
			ClassAttributeMap classificationDB);

	/**
	 * Read the attribute collected and measured from the source.
	 * 
	 * @return a list of {@link MeasuredAttribute}.
	 */
	public ArrayList<MeasuredAttribute> readMeasuredAttributes();

}
