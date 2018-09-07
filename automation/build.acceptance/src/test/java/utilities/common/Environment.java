package utilities.common;
import java.util.Properties;

public class Environment {

	private static String PROP_FILE = "env.properties";
	
	public static String getValue(String Key) {

		Properties prop = new Properties();
		String result = "";
		String error = String.format("Unable to load properties file: %s", PROP_FILE);

		try {
			prop.load(Environment.class.getClassLoader().getResourceAsStream(PROP_FILE));

			if (!prop.containsKey(Key)) {
				error = String.format("Unable to load %s from properties file: %s", Key, PROP_FILE);
			} else {
				result = prop.getProperty(Key);
			}

		} catch (Exception e) {
			System.out.println(error);
		}

		return result;
	}
}
