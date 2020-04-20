/**
 * 
 */
package ai.model.enums;

/**
 * @author Joanna
 *
 */
public enum HouseEnum {

	CASAI	(1, "Casa I"),
	CASAII	(2, "Casa II"),
	CASAIII	(3, "Casa III"),
	CASAIV	(4, "Casa IV"),
	CASAV	(5, "Casa V"),
	CASAVI	(6, "Casa VI"),
	CASAVII	(7, "Casa VII"),
	CASAVIII	(8, "Casa VIII"),
	CASAIX	(9, "Casa IX"),
	CASAX	(10, "Casa X"),
	CASAXI	(11, "Casa XI"),
	CASAXII	(12, "Casa XII");
	
	private final int id;
	private final String name;
	/**
	 * @param id
	 * @param name
	 */
	private HouseEnum(int id, String name) {
		this.id = id;
		this.name = name;
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	
}
