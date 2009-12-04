package massDistribution;

import java.util.Hashtable;

public class ClassAttributeMap {

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
