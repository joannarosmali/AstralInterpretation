/**
 * 
 */
package ai.model.dto;

import java.util.Map;

/**
 * @author Joanna
 *
 */
public class AstralChar {

	private String nativeName;
	private Map<String, Map<String,String>> interpretation;
	

	/**
	 * @param id
	 * @param nativeName
	 * @param idASC
	 * @param interpretation
	 */
	public AstralChar(String nativeName, Map<String, Map<String,String>> interpretation) {
		super();
		this.nativeName = nativeName;
		this.interpretation = interpretation;
	}

	/**
	 * @return the nativeName
	 */
	public String getNativeName() {
		return nativeName;
	}

	/**
	 * @param nativeName the nativeName to set
	 */
	public void setNativeName(String nativeName) {
		this.nativeName = nativeName;
	}
	

	/**
	 * @return the interpretation
	 */
	public Map<String, Map<String,String>> getInterpretation() {
		return interpretation;
	}

	/**
	 * @param interpretation the interpretation to set
	 */
	public void setInterpretation(Map<String, Map<String,String>> interpretation) {
		this.interpretation = interpretation;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AstralChar [nativeName=" + nativeName + ", interpretation="
				+ interpretation + "]";
	}

	
	
	
}
