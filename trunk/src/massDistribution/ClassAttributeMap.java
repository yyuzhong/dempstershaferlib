package massDistribution;

import java.util.Hashtable;

public class ClassAttributeMap {

	/**
	 * Constructs a nex {@link ClassAttributeMap} with the hashtable passed as
	 * argument.
	 * 
	 * @param allClassificationAttribute
	 */
	public ClassAttributeMap(
			Hashtable<String, ClassificationAttribute> allClassificationAttribute) {
		super();
		this.allClassificationAttribute = allClassificationAttribute;
	}

	/**
	 * The key of the map is the identifier of the Attribute, the value is a
	 * ClassificationAttribute
	 */
	private Hashtable<String, ClassificationAttribute> allClassificationAttribute;

	/**
	 *Returns the value to which the specified identifier is mapped, or {@code
	 * null} if this map contains no mapping for the identifier.
	 * 
	 * @param identifier
	 * @return
	 */
	public ClassificationAttribute getClassificationAttribute(String identifier) {
		if (allClassificationAttribute != null) {
			return allClassificationAttribute.get(identifier);
		} else
			return null;

	}
}
