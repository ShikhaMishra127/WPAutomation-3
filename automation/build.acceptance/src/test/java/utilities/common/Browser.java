package utilities.common;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Platform;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Browser implements WebDriver {

	public static WebDriver driver;
	
	public static ResourceLoader environment = new ResourceLoader("env");

	public static String browser = environment.getValue("browser");
	public static String baseUrl = environment.getValue("baseURL");
	public static String language = environment.getValue("Language");
	public static Long defaultWait = Long.valueOf(environment.getValue("defaultWait"));
	public static String buyerUsername = environment.getValue("buyerUsername");
	public static String buyerPassword = environment.getValue("buyerPassword");

	public Browser() throws IOException {

		if (driver == null) {

			switch (browser) {

			case "firefox":
				break;

			case "chrome":
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--lang=" + language);
				driver = new ChromeDriver(options);
				break;

			case "ie":
				break;

			default:
				break;
			}

			System.out.println("Driver value is : " + driver);
			driver.manage().window().setSize(new Dimension(1024, 768));

			driver.get(baseUrl);
		}
	}

	public static WebDriver getDriver() {
		return driver;
	}

	public static void waitForElementToBeClickable(WebElement ele, Long... i) {

		if (i.length >= 1) {
			WebDriverWait wait = new WebDriverWait(driver, i[0]);
			wait.until(ExpectedConditions.elementToBeClickable(ele));
		} else {
			WebDriverWait wait = new WebDriverWait(driver, defaultWait);
			wait.until(ExpectedConditions.elementToBeClickable(ele));
		}

	}

	@Override
	public void get(String url) {
		driver.get(url);
	}

	@Override
	public String getCurrentUrl() {
		return driver.getCurrentUrl();
	}

	@Override
	public String getTitle() {
		return driver.getTitle();
	}

	@Override
	public List<WebElement> findElements(By by) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WebElement findElement(By by) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPageSource() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void close() {
		driver.close();
	}

	@Override
	public void quit() {
		// TODO Auto-generated method stub

	}

	@Override
	public Set<String> getWindowHandles() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getWindowHandle() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TargetLocator switchTo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Navigation navigate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Options manage() {
		// TODO Auto-generated method stub
		return null;
	}

	public static void waitForPageLoad() {
		ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		};
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(pageLoadCondition);

	}

	public static void switchToFrame(WebElement frame) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frame));
	}

	public static void waitForElementToDisappear(By id) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.invisibilityOfElementLocated(id));

	}

	public static void WaitTillElementIsPresent(WebElement retainkeyinfo) {
		// TODO Auto-generated method stub
		
	}
	
	public static void visibilityOfListLocated(List<WebElement> ele) {

		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfAllElements(ele));
	}
	
	public static void selectInDropDown(WebElement ele, String itemToSet) {
		waitForElementToBeClickable(ele);
		ele.clear();
		ele.sendKeys(itemToSet);		
	}


	public static void switchToFrameBasedOnFrameName(String frameName) {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameName));
	}

	public static void switchToDefaultContent() {
		Browser.getDriver().switchTo().defaultContent();
	}
}
