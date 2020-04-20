/**
 * 
 */
package ai.controller;

import static ai.model.singleton.MeaningSingleton.getMeaning;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import ai.model.dao.ComponentDAO;
import ai.model.dto.AstralChar;
import ai.model.dto.Component;
import ai.model.enums.HouseEnum;
import ai.model.enums.TypeComponentEnum;

/**
 * @author Joanna
 *
 */
public class ComponentController {
	
	private ComponentDAO componentDAO = new ComponentDAO();
	private Map<String, Component> signs;
	private Map<String, Component> houses;
	
	
	/**
	 * 
	 */
	public ComponentController() {
		super();
		signs = componentDAO.getComponent(TypeComponentEnum.SIGN.getType());
		houses = componentDAO.getComponent(TypeComponentEnum.HOUSE.getType());
	}

	/**
	 * Como las casas son fijas se definieron como un enumerado
	 * @return
	 */
	public String[] getHousesName(){
		String[] housesName = new String[12];
		//housesName[0]="Seleccione...";
		int i = 0;
		housesName[i++] = HouseEnum.CASAI.getName();
		housesName[i++] = HouseEnum.CASAII.getName();
		housesName[i++] = HouseEnum.CASAIII.getName();
		housesName[i++] = HouseEnum.CASAIV.getName();
		housesName[i++] = HouseEnum.CASAV.getName();
		housesName[i++] = HouseEnum.CASAVI.getName();
		housesName[i++] = HouseEnum.CASAVII.getName();
		housesName[i++] = HouseEnum.CASAVIII.getName();
		housesName[i++] = HouseEnum.CASAIX.getName();
		housesName[i++] = HouseEnum.CASAX.getName();
		housesName[i++] = HouseEnum.CASAXI.getName();
		housesName[i] = HouseEnum.CASAXII.getName();
		
		return housesName;
	}

	/**
	 * Se consulta la información de los signos por el significado del Asc
	 * @return
	 */
	public String[] getSignsName(){
		String[] signsName = new String[12];
		//signsName[0]="Seleccione...";
		int i = 0;//1;
		for (Iterator<Component> iterator = signs.values().iterator(); iterator.hasNext();) {
			Component component = iterator.next();
			signsName[i++]=component.getName();
		}
		
		return signsName;
	}
	
	public Collection<Component> getComponent(String type){
		if(type.equals(TypeComponentEnum.SIGN.getType()))
			return signs.values();
		else if(type.equals(TypeComponentEnum.HOUSE.getType()))
			return houses.values();
		else 
			return componentDAO.getComponent(type).values();
	}
	
	public AstralChar getAstralChar(String name, List<Component> energy, int[] housesArray, int[] signsArray){
		//System.out.println("entre....... houses "+housesArray + " signs "+signsArray);
		Map<String, Map<String,String>> interpretation = new LinkedHashMap<String, Map<String,String>>();
		for (int i = 0; i < energy.size(); i++) {
			Component c = (Component)energy.get(i);
			//System.out.println("c...." +c.getName() +"... houses "+housesArray[i] + " signs "+signsArray[i]);

			Map<String,String> m = new LinkedHashMap<String,String>();

			if(!c.getName().equals("Asc")){
				Component h = houses.get(String.valueOf(housesArray[i]+1)); //System.out.println(h);
				m.put(h.getName(), getMeaning(c.getName(), String.valueOf(h.getId())));
			}
			Component s = signs.get(String.valueOf(signsArray[i]+1)); //System.out.println(s);
			m.put(s.getName(), getMeaning(c.getName(), s.getName()));
			interpretation.put(c.getName(), m);
		}
		AstralChar astral = new AstralChar(name, interpretation);
		//System.out.println("la carta astral es :: "+astral.toString());
		return astral;
	}
}
