package utilities.common;

import java.util.Enumeration;
import java.util.Locale;
import java.util.ResourceBundle;

public class ResourceLoader extends ResourceBundle {

	static ResourceBundle bundle;

	public ResourceLoader(String resourcename) {
		try {
			bundle = ResourceBundle.getBundle(resourcename);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Error loading resource file: " + resourcename);
		}
	}

	public ResourceLoader(String resourcename, String locale) {

		try {
			Locale loc = Locale.forLanguageTag(locale);
			bundle = ResourceBundle.getBundle(resourcename, loc);
		} catch (Exception e) {
			throw new RuntimeException("Error loading resource file: " + resourcename + " for locale: " + locale);
		}

	}

	public String getValue(String key) {

		try {
			return bundle.getString(key);
		} catch (Exception e) {
			throw new RuntimeException(
					"Could not find key: " + key + " in resource file: " + bundle.getBaseBundleName());
		}
	}

	@Override
	public Enumeration<String> getKeys() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Object handleGetObject(String arg0)
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Locale getLocale() {
		return bundle.getLocale();
	}
}

