/**
 * 
 */
package ai.model.enums;

/**
 * @author Joanna
 *
 */
public enum TypeComponentEnum {

	PLANET	("planet"),
	POINT	("point"),
	ASTER	("asteroid"),
	SIGN	("sign"),
	HOUSE	("house");
	
	private final String type;

	/**
	 * @param type
	 */
	private TypeComponentEnum(String type) {
		this.type = type;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	
	
}
