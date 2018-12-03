package utilities.common;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Browser implements WebDriver {

    public WebDriver driver;

    public ResourceLoader environment = new ResourceLoader("env");
    public String browser = environment.getValue("browser");
    public String baseUrl = environment.getValue("baseURL");
    public String contractUrl = environment.getValue("contractBB_URL");
    public String language = environment.getValue("Language");
    public Long defaultWait = Long.valueOf(environment.getValue("defaultWait"));
    public String buyerUsername = environment.getValue("buyerUsername");
    public String buyerPassword = environment.getValue("buyerPassword");

    public Browser() throws IOException {

        if (driver == null) {

            switch (browser) {

                case "firefox":
                    FirefoxOptions ffoptions = new FirefoxOptions();
                    ffoptions.addArguments("--lang" + language);
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver(ffoptions);
                    break;

                case "chrome":
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--lang=" + language);
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver(options);
                    break;

                case "ie":
                    break;

                default:
                    break;
            }

            System.out.println("Driver value is : " + driver);
            driver.manage().window().maximize();

           // driver.get(baseUrl);
        }
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void waitForElementToBeClickable(WebElement ele, Long... i) {

        if (i.length >= 1) {
            WebDriverWait wait = new WebDriverWait(driver, i[0]);
            wait.until(ExpectedConditions.elementToBeClickable(ele));
        } else {
            WebDriverWait wait = new WebDriverWait(driver, defaultWait);
            wait.until(ExpectedConditions.elementToBeClickable(ele));
        }

    }

    public void waitForPageLoad() {
        ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
            }
        };
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(pageLoadCondition);

    }

    public void switchToFrame(WebElement frame) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frame));
    }
    
    public void selectFromDropDownByVisibleText(WebElement ele, String value) {
		waitForElementToBeClickable(ele);
		new Select(ele).selectByVisibleText(value);
	}

    public void waitForElementToDisappear(By id) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.invisibilityOfElementLocated(id));

    }

    public void WaitTillElementIsPresent(WebElement retainkeyinfo) {
        // TODO Auto-generated method stub

    }

    public void visibilityOfElement(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void visibilityOfListLocated(List<WebElement> ele) {

        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfAllElements(ele));
    }

    public void selectInDropDown(WebElement ele, String itemToSet) {
        waitForElementToBeClickable(ele);
        ele.clear();
        ele.sendKeys(itemToSet);
    }

    public void get(String url) {
        driver.get(url);
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public List<WebElement> findElements(By by) {
        // TODO Auto-generated method stub
        return null;
    }

    public WebElement findElement(By by) {
        // TODO Auto-generated method stub
        return null;
    }

    public String getPageSource() {
        // TODO Auto-generated method stub
        return null;
    }

    public void close() {
        driver.close();
    }

    public void quit() {
        // TODO Auto-generated method stub

    }

    public Set<String> getWindowHandles() {
        // TODO Auto-generated method stub
        return null;
    }

    public String getWindowHandle() {
        // TODO Auto-generated method stub
        return null;
    }

    public TargetLocator switchTo() {
        // TODO Auto-generated method stub
        return null;
    }

    public Navigation navigate() {
        // TODO Auto-generated method stub
        return null;
    }

    public Options manage() {
        // TODO Auto-generated method stub
        return null;
    }
	/*
	 * 	public void selectsupplier(String suppliername) throws Exception {
		PCDriver.waitForElementToBeClickable(vendortextbox);
		vendortextbox.clear();
		vendortextbox.sendKeys(suppliername);
		PCDriver.visibilityOfListLocated(vendorlist);
		Thread.sleep(5000);
		System.out.println(vendorlist.size());
		for (WebElement vendor : vendorlist) {
			if (vendor.getText().contains(ReadExcelData.getInstance("Request").getStringValue("supplierselected"))) {
				// System.out.println(vendor.getText());
				Assert.assertEquals(vendor.getText(),
						ReadExcelData.getInstance("Request").getStringValue("supplierselected"));

				PCDriver.waitForElementToBeClickable(vendor);
				vendor.click();
				// System.out.println(vendortextbox.getAttribute("value"));
			}
		}
    */
}
