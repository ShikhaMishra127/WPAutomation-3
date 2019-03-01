package utilities.common;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Browser implements WebDriver {

    public WebDriver driver;

    public ResourceLoader environment = new ResourceLoader("env");
    public String browser = environment.getValue("browser");
    public String baseUrl = environment.getValue("baseURL");
    public String contractUrl = environment.getValue("contractBB_URL");
    public String solicitationUrl = environment.getValue("solicitationBB_URL");
    public String language = environment.getValue("Language");
    public Long defaultWait = Long.valueOf(environment.getValue("defaultWait"));
    public String buyerUsername = environment.getValue("buyerUsername");
    public String buyerPassword = environment.getValue("buyerPassword");

    public Browser() {

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

    public boolean elementExists(By id) {
        return (driver.findElements(id).size() > 0);
    }

    public boolean elementExists(WebElement element) {

        try {
            return element.isDisplayed();
        }
        catch (NoSuchElementException ignored) {
            return false;
        }
    }

    public void WaitTillElementIsPresent(WebElement retainkeyinfo) {
        // TODO Auto-generated method stub

    }

    public void waitForElementToAppear(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForElementToAppear(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void clickWhenAvailable(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOf(element));

        element.click();
    }

    public void clickSubElement(WebElement parent, String subelement) {

        WebElement element = parent.findElement(By.xpath(subelement));
        clickWhenAvailable(element);
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
        return driver.switchTo();
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
        Allows us to inject JavaScript into a WebElement to change its properties
        Used primarily for entering data into read-only web elements
     */
    public void InjectJavaScript(String script, WebElement element, String arguments) {

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(script, element, arguments);

    }

    /*
        Takes the current window, waits for it to have children, then
        changes focus to the first child window it finds.
     */
    public void SwitchToPopUp(String parentWindow) {

        // wait until there is a child window to switch to
        WebDriverWait hangAround = new WebDriverWait(driver, 20);
        hangAround.until(ExpectedConditions.numberOfWindowsToBe(2));

        Set<String> handleSet = driver.getWindowHandles();

        Iterator<String> i = handleSet.iterator();

        while (i.hasNext()) {

            String child = i.next();

            if (!parentWindow.equals(child)) {
                driver.switchTo().window(child);
                break;
            }
        }
    }

    public void SwitchToWindow(String handle) {

        // wait until there is a child window to switch to
        int onemore = (driver.getWindowHandles().size());

        WebDriverWait hangAround = new WebDriverWait(driver, 20);
        hangAround.until(ExpectedConditions.numberOfWindowsToBe(onemore));

        driver.switchTo().window(handle);

        // get a list of available windows
        Set<String> handleSet = driver.getWindowHandles();

    }

    public void ClosePopUp(String parentWindow)
    {
        driver.close();
        driver.switchTo().window(parentWindow);
    }

    public void UncheckCheckbox(WebElement element) {
        if (element.isSelected()) {
            element.click();
        }
    }

    public void CheckCheckbox(WebElement element) {
        if (!element.isSelected()) {
            element.click();
        }
    }

    public void ClickWhenClickable(WebElement element) {
        waitForElementToBeClickable(element);
        element.click();
    }
}
