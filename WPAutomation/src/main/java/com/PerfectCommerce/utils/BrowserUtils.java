package com.PerfectCommerce.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class BrowserUtils {

	WebDriver driver;
	log4jClass log=new log4jClass();
	//ExtentReport extent=new ExtentReport(this.getClass().getName());
	@SuppressWarnings({ "null", "static-access" })
	
	public void init(String browser) {
		 
		switch (browser) {
		case "firefox":

			System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir")+"/Resources/Drivers/geckodriver");
			driver=new FirefoxDriver();
			DesiredCapabilities cap =new DesiredCapabilities();
			//cap.firefox();
			driver.get("https://internalwpqa.perfect.com/login.do");
			cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);

			log.info("Browser Invoked");
			//extent.logger.log(LogStatus.INFO, "Browser Invoked");
			break;

		case "chrome":
			driver=new ChromeDriver();
			break;

	
		}
		//extent.report.endTest(extent.logger);
		//extent.report.flush();
		
	}
	
	
	
	
	
}
