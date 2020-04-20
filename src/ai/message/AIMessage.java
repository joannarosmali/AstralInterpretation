package ai.message;

import java.util.ResourceBundle;


public class AIMessage {

	private static final String BUNDLE_QNAME = "ai.message.i18n.Messages";

	private static ResourceBundle bundle;

	//private static Locale locale = null;

	static{

		//locale = Locale.getDefault();
		bundle = ResourceBundle.getBundle(BUNDLE_QNAME);	
	}

	public static ResourceBundle getMessage(){
		return bundle;
	}
}