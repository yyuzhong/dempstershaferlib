package massDistribution;

import java.util.ArrayList;
import java.util.Hashtable;

public class ClassAttributeMap {

	/**
	 * The key of the map is the identifier of the Attribute, the value is a
	 * ClassificationAttribute
	 */
	private Hashtable<String, ClassificationAttribute> map = new Hashtable<String, ClassificationAttribute>();
	private ArrayList<ClassificationAttribute> allAttributes;

	/**
	 * Constructs a new {@link ClassAttributeMap} with the hashtable passed as
	 * argument.
	 * 
	 * @param allClassificationAttribute
	 */
	public ClassAttributeMap(
			ArrayList<ClassificationAttribute> allClassificationAttribute) {
		super();
		this.allAttributes = allClassificationAttribute;
		for (ClassificationAttribute classificationAttribute : allAttributes) {
			map.put(classificationAttribute.getIdentifier(),
					classificationAttribute);
		}
	}

	public ArrayList<ClassificationAttribute> getAllAttributes() {
		return allAttributes;
	}

	/**
	 *Returns the value to which the specified identifier is mapped, or {@code
	 * null} if this map contains no mapping for the identifier.
	 * 
	 * @param identifier
	 * @return
	 */
	public ClassificationAttribute getClassificationAttribute(String identifier) {
		if (map != null) {
			return map.get(identifier);
		} else
			return null;

	}
}
