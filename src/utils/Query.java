package utils;

import java.util.Locale;
import java.util.ResourceBundle;

public class Query {
	
	private static Locale currentLocale = new Locale("es", "");
	private static ResourceBundle bundle = ResourceBundle.getBundle("Bundles", currentLocale);
	
	public static String getPhrase (String input, String lang){
		currentLocale = new Locale(lang, "");
		bundle = ResourceBundle.getBundle("Bundles", currentLocale);
		return bundle.getString (input);
	}
	
	public static synchronized void changeLocale(String loc){
		currentLocale = new Locale (loc, "");
		bundle = ResourceBundle.getBundle("Bundles", currentLocale);
	}

}
