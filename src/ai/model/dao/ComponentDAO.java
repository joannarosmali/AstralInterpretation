/**
 * 
 */
package ai.model.dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import ai.model.dto.Component;
import ai.model.enums.TypeComponentEnum;

/**
 * @author Joanna
 *
 */
public class ComponentDAO {

	private static final String EXT_JSON = ".json";

	public Map<String, Map<String,String>> getMeanings() {

		try {
			JSONArray array = getJSON("meaning");
			
			@SuppressWarnings("unchecked")
			Iterator<JSONObject> i = array.iterator();
			Map<String, Map<String,String>> dicc = new LinkedHashMap<String, Map<String,String>>();

			// take each value from the json array separately
			while (i.hasNext()) {
				JSONObject innerObj = i.next();
				Map<String,String> aux = new LinkedHashMap<String,String>();
				JSONArray houses = (JSONArray) innerObj.get("houses");
				for (int j = 0; j < houses.size(); j++) {
					String house = (String)((JSONObject)houses.get(j)).get("house");
					String energyInHouse = (String)((JSONObject)houses.get(j)).get("energyInHouse");
					aux.put(house, energyInHouse);
				}
				JSONArray signs = (JSONArray) innerObj.get("signs");
				for (int j = 0; j < signs.size(); j++) {
					String sign = (String)((JSONObject)signs.get(j)).get("sign");
					String energyInSign = (String)((JSONObject)signs.get(j)).get("energyInSign");
					aux.put(sign, energyInSign);
				}
				dicc.put((String)innerObj.get("energy"), aux);
			}
			
			return dicc;
		} catch (NullPointerException ex) {
			ex.printStackTrace();
		}
		return null;

	}
	
	public Map<String, Component> getComponent(String type) {
		Map<String, Component> components = null;
		try {
			// get an array from the JSON object
			JSONArray lang= getJSON(type);
			
			@SuppressWarnings("unchecked")
			Iterator<JSONObject> i = lang.iterator();
			components = new LinkedHashMap<String, Component>();

			// take each value from the json array separately
			while (i.hasNext()) {
				JSONObject innerObj = i.next();
				components.put((String)innerObj.get("id"), new Component((String)innerObj.get("id"), 
						type==TypeComponentEnum.HOUSE.getType() ?
								TypeComponentEnum.HOUSE : 
									type==TypeComponentEnum.PLANET.getType() ? TypeComponentEnum.PLANET :TypeComponentEnum.SIGN
						, (String)innerObj.get("name"), 
						(String)innerObj.get("features"), (String)innerObj.get("image")));
			}
			
		} catch (NullPointerException ex) {
			ex.printStackTrace();
		}
		
		return components;

	}
	
	private JSONArray getJSON(String type) {
		JSONArray lang = null;
		try {
			// read the json file
			InputStream ir = this.getClass().getResourceAsStream(type+EXT_JSON);
			BufferedReader br = new BufferedReader(new InputStreamReader(ir));
			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject) jsonParser.parse(br);
			
			// get an array from the JSON object
			lang = (JSONArray) jsonObject.get(type);

		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ParseException ex) {
			ex.printStackTrace();
		} catch (NullPointerException ex) {
			ex.printStackTrace();
		}
		
		return lang;

	}
	
}
