package utilities.common;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;

public class Browser implements WebDriver {

    public WebDriver driver;

    public String browser;
    public String baseUrl;
    public String contractUrl;;
    public String solicitationUrl;
    public String language;
    public Long defaultWait;
    public Long defaultPopupWaitSeconds;
    public String buyerUsername;
    public String buyerPassword;
    public String buyerTimeZone;
    public String supplierUsername;
    public String supplierPassword;
    public String supplierName;
    public String buyerName;
    public String approverUsername;
    public String approverPassword;
    public String approverName;
    public String environment;


    private String reportBuffer = "";


    public Browser(ITestContext context) {

        // set up global variables based on environment
        LoadProperties();

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
                    options.addArguments("window-size=1800x1800");
                    if(getVisible()) {
                        options.addArguments("--start-maximized");
                    } else {
                        options.addArguments("headless");
                        options.addArguments("window-size=1920,1080");
                    }
                    options.addArguments("--lang=" + language);
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver(options);
                    break;
                case "ie":
                    break;

                default:
                    break;
            }
        }
        // tell our listener how to access the browser (for screenshots, etc)
        context.setAttribute("browser" + Thread.currentThread().getId(), this);

    }

    private void LoadProperties() {

        environment = System.getProperty("ENV", "qa");
        ResourceLoader resource = new ResourceLoader(environment);

        browser = resource.getValue("browser");
        baseUrl = resource.getValue("baseURL");
        contractUrl = resource.getValue("contractBB_URL");
        solicitationUrl = resource.getValue("solicitationBB_URL");
        language = resource.getValue("Language");
        defaultWait = Long.valueOf(resource.getValue("defaultWait"));
        defaultPopupWaitSeconds = Long.valueOf(resource.getValue("defaultPopupWaitSeconds"));
        buyerUsername = resource.getValue("buyerUsername");
        buyerPassword = resource.getValue("buyerPassword");
        buyerTimeZone = resource.getValue("buyerTimeZone");
        supplierUsername = resource.getValue("supplierUsername");
        supplierPassword = resource.getValue("supplierPassword");
        supplierName = resource.getValue("supplierName");
        buyerName = resource.getValue("buyerCompanyName");
        approverUsername = resource.getValue("approverUsername");
        approverPassword = resource.getValue("approverPassword");
        approverName = resource.getValue("approverName");
    }


    /**
     * use System property -DVISIBLE to determine if headless or not, defaults to 'true'
     *
     * @return should we use the broweser in headless mode?
     */
    private boolean getVisible() {
        String val = System.getProperty("VISIBLE", "true");
        return Boolean.valueOf(val).booleanValue();
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void Log(String text) {
        reportBuffer += text + "\n";
    }

    public String GetLog(){ return reportBuffer; }

    public void ClearLog() { reportBuffer = ""; }

    public void HideElement(WebElement element) {
        InjectJavaScript("arguments[0].style.visibility='hidden'", element);
    }

    public void HideElement(String xpath) {
        waitForElementToAppear(By.xpath(xpath));
        InjectJavaScript("arguments[0].style.visibility='hidden'", driver.findElement(By.xpath(xpath)));
    }

    public void ScrollElementIntoView(WebElement element) {
        // Chrome-specific issue (https://github.com/angular/protractor/issues/4589)
        // try to get element to scroll into view
        InjectJavaScript("arguments[0].scrollIntoView()", element);
    }

    public void waitForElementToBeClickable(WebElement element) {
        waitForElementToBeClickable(element, defaultWait);
    }

    public void waitForElementToBeClickable(WebElement ele, Long i) {

        WebDriverWait wait = new WebDriverWait(driver, i);
        try {
            wait.until(ExpectedConditions.elementToBeClickable(ele));

            // if overlay is there, wait for it to leave before continuing
            waitForElementToDisappear(By.xpath("//div[contains(@class,'blockOverlay')]"));
            waitForElementToDisappear(By.xpath("//div[contains(@class,'modal-body')]"));
            waitForElementToDisappear(By.xpath("//div[contains(@id,'cboxOverlay')]"));

            // try to get element to scroll into view before we can click
            ScrollElementIntoView(ele);

        } catch (TimeoutException e) { }
    }

    public void highlightElement(WebElement element) {
        InjectJavaScript("arguments[0].setAttribute('style', arguments[1]);",element, "color: red; border: 2px solid red;");
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

    public void switchBackToTopFrame() {
        driver.switchTo().defaultContent();
    }

    public void selectFromDropDownByVisibleText(WebElement ele, String value) {
        waitForElementToBeClickable(ele);
        new Select(ele).selectByVisibleText(value);
    }

    public void waitForElementToDisappear(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.invisibilityOf(element));
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

    public void waitForElementToAppear(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForElementToAppear(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void clickWhenAvailable(WebElement element) {

        waitForElementToBeClickable(element);
        element.click();
    }

    public void clickWhenAvailable(By locator) {
        waitForElementToAppear(locator);

        WebElement element = driver.findElement(locator);

        ScrollElementIntoView(element);
        element.click();
    }

    /**
     * Sets the proper state of a checkbox, based on what the current
     * @param element - checkbox element
     * @param checked - send TRUE if you want box checked, FALSE otherwise
     */
    public void clickSetCheckbox(WebElement element,  Boolean checked) {

        waitForElementToBeClickable(element);

        // if you want it checked and its not, or if you don't want it checked and it is...
        if ( (checked && !element.isSelected()) || (!checked && element.isSelected())) {
            clickWhenAvailable(element);
        }
    }

    /**
     *  Sets the proper state of a checkbox, without requiring an existing WebElement
     * @param locator - xpath of the checkbox element
     * @param checked - send TRUE if you want box checked, FALSE otherwise
     */
    public void clickSetCheckbox(By locator, Boolean checked) {

        waitForElementToAppear(locator);
        WebElement element = driver.findElement(locator);

        clickSetCheckbox(element, checked);
    }

    public void waitForElementToContainText(WebElement element, String text) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.textToBePresentInElement(element, text));
    }


    /**
     * Given an edit box and a list of return elements, will click on the first element that matches the search string
     *
     * @param editbox       - Element where user types in a value
     * @param elementList   - Element where a drop-down list of results is displayed
     * @param search        - String that the user types into the search box
     */
    public void clickTypeAheadDropdownItem(WebElement editbox, List<WebElement> elementList, String search) {

        // type search term into type-ahead edit box
        sendKeysWhenAvailable(editbox, search);

        // come back with a list of elements that match search string
        visibilityOfListLocated(elementList);

        // go through list of returned items and select first one that matches search string
        for (WebElement element : elementList) {
            if (element.getText().contains(search)) {
                waitForElementToBeClickable(element);
                element.click();
            }
        }
    }

    /**
     * @param element       - Element user types text info (usually an edit control)
     * @param keystrokes    - String of text user will type
     */
    public void sendKeysWhenAvailable(WebElement element,  String keystrokes) {

        waitForElementToAppear(element);

        // clear out existing value and type in new value
        element.clear();
        element.sendKeys(Keys.chord(Keys.CONTROL,"a",Keys.DELETE));  // in case clear does not work - select all and delete value
        element.sendKeys(keystrokes);
    }

    public void clickSubElement(WebElement parent, String subelement) {

        WebElement element = parent.findElement(By.xpath(subelement));
        clickWhenAvailable(element);
    }

    public WebElement getSubElement(WebElement parent, String subelement) {

        waitForElementToAppear(parent);
        return parent.findElement(By.xpath(subelement));
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
    public List<WebElement> findElements(By by) { return driver.findElements(by); }
    public WebElement findElement(By by) { return driver.findElement(by); }
    public String getPageSource() { return driver.getPageSource(); }
    public void close() {
        driver.close();
    }
    public void quit() { driver.quit(); }
    public Set<String> getWindowHandles() {
        return this.driver.getWindowHandles();
    }
    public String getWindowHandle() {
        return this.driver.getWindowHandle();
    }
    public TargetLocator switchTo() {
        return driver.switchTo();
    }
    public Navigation navigate() { return driver.navigate(); }
    public Options manage() { return driver.manage(); }

    /*
        Allows us to inject JavaScript into a WebElement to change its properties
        Used primarily for entering data into read-only web elements
     */
    public void InjectJavaScript(String script, WebElement element, String arguments) {

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(script, element, arguments);
    }

    public void InjectJavaScript(String script, WebElement element) {

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(script, element);
    }


    public void ReloadPage() {

        // refresh entire page, then wait for elements to load
        driver.navigate().refresh();
        waitForPageLoad();
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

    public void waitForPopUpToOpen()
    {
        WebDriverWait wait = new WebDriverWait(this.driver, defaultPopupWaitSeconds);
        wait.until((ExpectedCondition<Boolean>) theDriver -> theDriver.getWindowHandles().size() > 1);
    }

    /*
     * Switches to a window by name. To get the name, in the dev tools console,
     * use 'window.name'.
     */
    public void switchToWindow(String strWindowName) {

        driver.switchTo().window(strWindowName);
        waitForPageLoad();
    }

    /*
        waitForElementWithRefresh

        Used while waiting for an xpath element to appear. Will refresh every few seconds until the item appears. Use
        when waiting for solicitations, POs, other items to show up in a list of items.

        String      searchPath              - XPath containing the string we're looking for
        int         refreshIntervalSeconds  - How ofter to click Refresh
        int         totalLimitSeconds       - Total amount of time to wait for item to appear

     */
    public void waitForElementWithRefresh(String searchPath, int refreshIntervalSeconds, int totalLimitSeconds ) {

        int timeout = 0;

        while ( timeout < (totalLimitSeconds / refreshIntervalSeconds)) {
            // if we find what we're looking for, leave
            if (elementExists(By.xpath(searchPath))) {
                System.out.printf("Found.%n");
                break;
            } else {
                // otherwise, sleep for (refreshIntervalSeconds) seconds and click refresh results to try again
                try {
                    // wait for X seconds, then refresh the page
                    Thread.sleep((refreshIntervalSeconds * 1000));
                    driver.navigate().refresh();
                    timeout++;
                    System.out.printf("Waiting for %d sec%n", (timeout * refreshIntervalSeconds));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public ZonedDateTime getDateTimeNowInUsersTimezone() {
        Instant nowUtc = Instant.now();
        ZoneId usersTimeZone = ZoneId.of(buyerTimeZone);
        return ZonedDateTime.ofInstant(nowUtc, usersTimeZone);
    }

    public Map<HTMLTableColumn, WebElement> buildTableMap(String rowXPath, HTMLTableColumn[] columns) {
        return buildTableMap(null, rowXPath, columns);
    }

    public Map<HTMLTableColumn, WebElement> buildTableMap(WebElement baseElement, String rowXPath,
                                                          HTMLTableColumn[] columns) {
        this.waitForElementToAppear(By.xpath(rowXPath));
        HashMap<HTMLTableColumn, WebElement> elements = new HashMap<>();

        // build a list of WebElements that reference each column (name, number, status, etc)
        for (int i = 1; i < columns.length; i++) {
            //build the column xpath
            String columnxpath = rowXPath + "/td[" + String.valueOf(i) + "]";
            this.waitForElementToAppear(By.xpath(columnxpath));
            //Check if a base Element was passed or if we are only searching by xpath.
            if(baseElement != null) {
                elements.put(columns[i], this.findElement(By.xpath(columnxpath)));
            } else {
                elements.put(columns[i], baseElement.findElement(By.xpath(columnxpath)));
            }
        }
        return elements;
    }

    public void HardWait() {
        // a terrible necessity - some times we just need to wait for browser to catch up. Use Sparingly!
        try { Thread.sleep(3000); } catch (InterruptedException e) { e.printStackTrace(); }
    }

    public interface HTMLTableColumn {}

}
