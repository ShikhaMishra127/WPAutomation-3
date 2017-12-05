package pageobjects.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ReadConfig {
	private static final ReadConfig instance = new ReadConfig();
	private static Properties properties = new Properties();

	private ReadConfig() {

	}

	public static synchronized ReadConfig getInstance() {
		try {
			File file = new File(System.getProperty("user.dir") + "/Resources/Data/qa.properties");
			FileInputStream fileInput = new FileInputStream(file);
			properties.load(fileInput);
			fileInput.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return instance;
	}

	public String getUserName() {
		return properties.getProperty("username");

	}
	
	public String getVendorUserName() {
		return properties.getProperty("vendor_username");

	}

	public String getPassword() {

		return properties.getProperty("password");
	}
	
	public String getVendorPassword() {

		return properties.getProperty("vendor_password");
	}

	public String getApplicationUrl() {
		return properties.getProperty("url");
	}

	public String getDriverPath() {
		return properties.getProperty("driver_path");
	}

	public static Properties getProperties() {
		return properties;
	}

	public static void setProperties(Properties properties) {
		ReadConfig.properties = properties;
	}
	
	public String  getBrowser() {
		return properties.getProperty("browser");
	}
	
	public String getExcelPath() {
		return properties.getProperty("excel_path");
	}
	
	public String getWhiteLabelUrl() {
		return properties.getProperty("whiteLabelUrl");
	}
}
