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

	public ArrayList<MeasuredAttribute> getMeasuredAttributes();

	public void setMeasuredAttributes(
			ArrayList<MeasuredAttribute> attributesList);

	public void addMeasuredAttribute(MeasuredAttribute qAttribute);

	public String getIdentifierName();

	public void setIdentifierName(String name);

}
