/**
 * 
 */
package ai.model.singleton;

import java.util.Map;

import ai.model.dao.ComponentDAO;

/**
 * @author Joanna
 *
 */
public class MeaningSingleton {
	
	private static Map<String, Map<String,String>> dicc = null;

	private static MeaningSingleton instance = null;
	
	private MeaningSingleton(){
		dicc = new ComponentDAO().getMeanings();
	}
	
	public static String getMeaning(String energy, String componet){
		String meaning = null;
		if(instance==null){
			instance = new MeaningSingleton();
		}
		meaning = (String) ((Map<String,String>)dicc.get(energy)).get(componet);
		return meaning;
	}
	
	
	
}
