package utilities.common;

import java.util.Enumeration;
import java.util.Locale;
import java.util.ResourceBundle;

public class ResourceLoader extends ResourceBundle {

	static ResourceBundle bundle;
	
	public ResourceLoader(String resourcename) {
		bundle = ResourceBundle.getBundle(resourcename);
	}
	
	public ResourceLoader(String resourcename, Locale locale) {
		bundle = ResourceBundle.getBundle(resourcename, locale);
	}
	
	public String getValue(String key) {
		return bundle.getString(key);
		}

	@Override
	public Enumeration<String> getKeys() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Object handleGetObject(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Locale getLocale() {
		return bundle.getLocale();
	}
}

