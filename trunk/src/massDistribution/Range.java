package massDistribution;

public interface Range {

	public String getType();

	public boolean contains(IMeasure measuredValue);

}
