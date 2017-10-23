package com.PerfectCommerce.utils;

import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.PerfectCommerce.generic.LoginPage;

public class PCDriver implements WebDriver {

	public static WebDriver driver;
	static log4jClass log = new log4jClass();

	static {
	PCDriver.invokeBrowser(ReadConfig.getInstance().getBrowser());

}
	public static synchronized WebDriver invokeBrowser(String browser) {

		
		switch (browser) {

		case "firefox":
			DesiredCapabilities cap = new DesiredCapabilities();
			cap.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
			System.setProperty("webdriver.gecko.driver",
					ReadConfig.getInstance().getDriverPath().toString() + "geckodriver.exe");
			driver = new FirefoxDriver(cap);
			driver.get(ReadConfig.getInstance().getApplicationUrl());
			log.info("Browser Invoked");
			break;

		case "chrome":
			DesiredCapabilities capChrome=new DesiredCapabilities();
			capChrome.setJavascriptEnabled(true);
			capChrome.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
			System.setProperty("webdriver.chrome.driver",
					ReadConfig.getInstance().getDriverPath().toString() + "chromedriver.exe");
			driver = new ChromeDriver(capChrome);
			driver.manage().window().maximize();
			break;

		case "ie":
			System.setProperty("webdriver.ie.driver",
					ReadConfig.getInstance().getDriverPath().toString() + "IEDriverServer.exe");
			driver = new InternetExplorerDriver();

		case "edge":
			DesiredCapabilities capEdge = DesiredCapabilities.edge();
			capEdge.setPlatform(Platform.WIN10);
			capEdge.acceptInsecureCerts();
			capEdge.setJavascriptEnabled(true);
			EdgeOptions options = new EdgeOptions();
			options.setPageLoadStrategy("eager");
			System.setProperty("webdriver.edge.driver",
					ReadConfig.getInstance().getDriverPath().toString() + "MicrosoftWebDriver.exe");
			driver = new EdgeDriver(options);
		
		case "phantomjs":
			DesiredCapabilities capPhantom=new DesiredCapabilities();
			//capPhantom.setBrowserName("PhantomJs");
			capPhantom.acceptInsecureCerts();
			capPhantom.setJavascriptEnabled(true);
			capPhantom.setCapability(
			        PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
			        ReadConfig.getInstance().getDriverPath().toString() + "phantomjs.exe");
			//System.setProperty("webdriver.phantomjs.driver",
				//	ReadConfig.getInstance().getDriverPath().toString() + "phantomjs.exe");
			driver = new PhantomJSDriver(capPhantom);
			driver.manage().window().maximize();

		}
		
		
		return driver;
	}

	public void close() {
		driver.close();

	}

	public static WebDriver getDriver() {
		return driver;
	}
	
	public static void waitForPageLoad() {
		ExpectedCondition<Boolean> pageLoadCondition = new
                ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
                    }
                };  
                WebDriverWait wait = new WebDriverWait(driver, 30);
                wait.until(pageLoadCondition);
            }
	
	public static void waitForElementToBeClickable(WebElement ele) {
		
		WebDriverWait wait=new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(ele));
	}
	
	public static void switchToFrame(WebElement frame) {
		WebDriverWait wait=new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frame));
	}
	
	
	
public static void executeScript(WebElement ele) {
	JavascriptExecutor executor = (JavascriptExecutor)driver;
	executor.executeScript("arguments[0].click();", ele);
}
	public WebElement findElement(By arg0) {
		return null;
	}
	
	public static void switchToWindow(String strWindowName) {
		driver.switchTo().window(strWindowName);
	}

	public List<WebElement> findElements(By arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public void get(String arg0) {
		// TODO Auto-generated method stub

	}

	public String getCurrentUrl() {
		return driver.getCurrentUrl();
	}

	public String getPageSource() {

		return driver.getPageSource();
	}

	public String getTitle() {

		return driver.getTitle();
	}

	public String getWindowHandle() {
		// TODO Auto-generated method stub
		return null;
	}

	public Set<String> getWindowHandles() {

		return driver.getWindowHandles();
	}

	public Options manage() {
		// TODO Auto-generated method stub
		return null;
	}

	public Navigation navigate() {

		return null;
	}

	public void quit() {
		driver.quit();
	}

	public TargetLocator switchTo() {

		return null;
	}

}
