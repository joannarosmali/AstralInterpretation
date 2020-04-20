/**
 * 
 */
package ai.model.dto;

import ai.model.enums.TypeComponentEnum;

/**
 * @author Joanna
 * 
 * Data Transfert Object of Planet, House and Sign
 *
 */
public class Component {
	
	private int id;
	
	private TypeComponentEnum type;
	
	private String name;
	
	private String features;
	
	private String image;

	/**
	 * @param id
	 * @param type
	 * @param name
	 * @param features
	 * @param image
	 */
	public Component(String id, TypeComponentEnum type, String name, String features, String image) {
		super();
		this.id = Integer.valueOf(id);
		this.type = type;
		this.name = name;
		this.features = features;
		this.image = image;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the type
	 */
	public TypeComponentEnum getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(TypeComponentEnum type) {
		this.type = type;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the features
	 */
	public String getFeatures() {
		return features;
	}

	/**
	 * @param features the features to set
	 */
	public void setFeatures(String features) {
		this.features = features;
	}

	/**
	 * @return the image
	 */
	public String getImage() {
		return image;
	}

	/**
	 * @param image the image to set
	 */
	public void setImage(String image) {
		this.image = image;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Component [id=" + id + ", type=" + type.getType() + ", name=" + name + ", features=" + features + ", image="
				+ image + "]";
	}




}
