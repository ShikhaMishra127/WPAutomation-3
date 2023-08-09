//package commonutils.pageobjects.utils;// BaseClass.java
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.testng.annotations.AfterClass;
//import org.testng.annotations.BeforeClass;

package commonutils.pageobjects.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.time.LocalDate;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class BaseClass {
    public WebDriver driver;
    private String username = "your_username";
    private String password = "your_password";
    protected ExtentReports reports;
    protected ExtentTest test;
    ExtentSparkReporter htmlReporter;

    @BeforeClass
    public void setUp() {
        // Set the system property for the ChromeDriver executable
        System.setProperty("webdriver.chrome.driver", "D:\\chromedriver-win64\\chromedriver.exe");

        // Initialize the ChromeDriver instance
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


    }


    public WebDriver Buyer_login() throws InterruptedException {

        driver.manage().timeouts().implicitlyWait(12, TimeUnit.SECONDS);

        // Navigate to the login page of your application
        driver.get("https://webprocure-stage.proactiscloud.com/Login");// Stage
//        driver.get("https://webprocure.proactiscloud.com/Login");

        driver.findElement(By.id("visibleUname")).sendKeys("smperfect");
        driver.findElement(By.xpath("//input[@id='visiblePass']")).sendKeys("Welcome@2");

//        driver.findElement(By.id("visibleUname")).sendKeys("smperfect");
//        driver.findElement(By.xpath("//input[@id='visiblePass']")).sendKeys("Welcome@5");

        Thread.sleep(4000);
        driver.findElement(By.id("login-submit")).click();
        Thread.sleep(3000);

        // Switch to the pop-up window
        String mainWindowHandle = driver.getWindowHandle();
        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(mainWindowHandle)) {
                driver.switchTo().window(handle);
                break;
            }
        }

        // Find and click the "Ignore" button
        WebElement ignoreButton = driver.findElement(By.xpath("//*[@id=\"session_info_display_modal\"]/div[2]/div/div[3]/button[1]"));
        ignoreButton.click();

        // Switch back to the main window
        driver.switchTo().window(mainWindowHandle);

        Thread.sleep(5000);

//        test.info("Logging in with credentials: " + username + "/" + password);

        return driver;
    }


    public void logout() {
        // Perform logout actions
//        driver.findElement(By.id("logout-button")).click();

        driver.findElement(By.xpath("//*[@id=\"userMenu\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"userMenuList\"]/li[4]/a")).click();

        driver.switchTo().alert().accept();

//        test.info("Logging out");
    }

    public void vendors_login() throws InterruptedException {

        driver.get("https://webprocure-stage.proactiscloud.com/Login");

        // Perform login
        WebElement usernameField = driver.findElement(By.id("visibleUname"));
        WebElement passwordField = driver.findElement(By.id("visiblePass"));
        WebElement loginButton = driver.findElement(By.id("login-submit"));

        usernameField.sendKeys("shikhav2");
        passwordField.sendKeys("Welcome@1");
        loginButton.click();
        Thread.sleep(4000);

    }

    public void vendor_logout() {
        driver.findElement(By.xpath("//*[@id=\"userMenu\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"userMenuList\"]/li[5]/a")).click();

        driver.switchTo().alert().accept();

    }


    public void create_request() throws InterruptedException {

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        // Create a request
        WebElement Request_tab = driver.findElement(By.xpath("//a[@title=\"Request\"]"));
        Request_tab.click();
        WebElement Create_new = driver.findElement(By.xpath("//a[@title=\"Create new\"]"));
        Create_new.click();

        driver.switchTo().frame("C1ReqMain");


        driver.findElement(By.xpath("//*[@id=\"wrapper\"]/section[1]/ul/li[4]/a")).click();


        driver.switchTo().frame("C1ReqMain");
        driver.findElement(By.xpath("//*[@id=\"OrderQty\"]")).sendKeys("100");
        driver.findElement(By.xpath("//*[@id='UnitPrice']")).sendKeys("50");
        driver.findElement(By.xpath("//input[@id='SupplierPartNum']")).sendKeys("64836493");

        driver.findElement(By.xpath("//input[@id='input_SupplierName']")).sendKeys("Shikha Stage Vendor");
        driver.findElement(By.xpath("//*[@id=\"ui-id-1\"]/li[1]")).click();

        driver.findElement(By.xpath("//button[text()='No']")).click();
        sleep(2000);
//        WebElement noButton = driver.findElement(By.xpath("//button[text()='No']"));
//
//        // Check if the element is displayed
//        if (noButton.isDisplayed()) {
//            // Click on the "No" button if it is displayed
//            noButton.click();
//        }

        driver.findElement(By.xpath("//input[@id='input_catcode']")).sendKeys("**");
        driver.findElement(By.xpath("//*[@id=\"ui-id-4\"]/li[1]")).click();
        driver.findElement(By.xpath("//*[@id=\"btn-add-bottom\"]")).click();

        Thread.sleep(4000);

        driver.findElement(By.xpath("//*[@id=\"idView Request\"]")).click();
        Thread.sleep(4000);
        driver.switchTo().frame("C1ReqMain");
        Thread.sleep(5000);
        // Assign Account Code
        driver.findElement(By.xpath("//*[@id=\"wrapper\"]/section[4]/form/div[5]/table/tbody/tr[1]/td[5]/p/a[2]/img")).click();

        driver.findElement(By.xpath("//*[@id=\"frmReqCostDist\"]/div[3]/button")).click();

        driver.findElement(By.xpath("//*[@id=\"frmReqCostDist\"]/div[1]/button[1]")).click();

//        driver.findElement(By.xpath("//*[@id=\"wrapper\"]/section[4]/form/div[1]/button[6]")).click();
//        Thread.sleep(2);

        driver.findElement(By.xpath("//*[@title='Submit Request']")).click();
    }


    public void create_request_for_approval() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(12, TimeUnit.SECONDS);

        // Create a request
        WebElement Request_tab = driver.findElement(By.xpath("//a[@title=\"Request\"]"));
        Request_tab.click();
        WebElement Create_new = driver.findElement(By.xpath("//a[@title=\"Create new\"]"));
        Create_new.click();


        driver.switchTo().frame("C1ReqMain");


        driver.findElement(By.xpath("//*[@id=\"wrapper\"]/section[1]/ul/li[4]/a")).click();

//        driver.switchTo().parentFrame();
        driver.switchTo().frame("C1ReqMain");
        driver.findElement(By.xpath("//*[@id=\"OrderQty\"]")).sendKeys("100");
        driver.findElement(By.xpath("//*[@id='UnitPrice']")).sendKeys("50");
        driver.findElement(By.xpath("//input[@id='SupplierPartNum']")).sendKeys("64836493");

        driver.findElement(By.xpath("//input[@id='input_SupplierName']")).sendKeys("Shikha Stage Vendor");
        driver.findElement(By.xpath("//*[@id=\"ui-id-1\"]/li[1]")).click();

        driver.findElement(By.xpath("//button[text()='No']")).click();
//        sleep(3000);

//        WebElement message_alert = driver.findElement(By.xpath("//button[text()='No']"));
//
//        if (message_alert.isDisplayed())
//        {
//            message_alert.click();
//        }
//        else Thread.sleep(3000);


        driver.findElement(By.xpath("//input[@id='input_catcode']")).sendKeys("**");
        driver.findElement(By.xpath("//*[@id=\"ui-id-4\"]/li[1]")).click();
        driver.findElement(By.xpath("//*[@id=\"btn-add-bottom\"]")).click();

        driver.switchTo().parentFrame();
        Thread.sleep(3000);
        driver.switchTo().frame("reqcart");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@name='txtReqName']"));

        // Locate the textbox element
//        WebDriver copyreqnumber = null;
//        WebDriver driver = copyreqnumber;

        // Locate the textbox element
        WebElement textBoxElement = driver.findElement(By.xpath("//*[@name='txtReqName']"));
        // Create an Actions object
        Actions actions = new Actions(driver);
        Thread.sleep(3000);
        // Double click on the textbox
        actions.doubleClick(textBoxElement).perform();
        Thread.sleep(3000);
        // Use keyboard shortcuts to copy the text
        actions.keyDown(Keys.CONTROL).sendKeys("c").keyUp(Keys.CONTROL).perform();
        Thread.sleep(3000);
        // Verify the copied text if needed
        String copiedText = getClipboardText(); // Implement a method to get the text from the clipboard
        System.out.println("Copied Text: " + copiedText);


        Thread.sleep(3000);
        driver.switchTo().parentFrame();
        driver.switchTo().frame("C1ReqMain");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\'idView Request\']")).click();

        Thread.sleep(5000);
        driver.switchTo().frame("C1ReqMain");
        // Assign Account Code
        driver.findElement(By.xpath("//*[@id=\"wrapper\"]/section[4]/form/div[5]/table/tbody/tr[1]/td[5]/p/a[2]/img")).click();

        driver.findElement(By.xpath("//*[@id=\"frmReqCostDist\"]/div[3]/button")).click();

        driver.findElement(By.xpath("//*[@id=\"frmReqCostDist\"]/div[1]/button[1]")).click();

        // Approval preview button

        driver.findElement(By.xpath("//*[@id=\"btn-approvalPreview\"]")).click();


        driver.switchTo().frame("topframe");


        driver.findElement(By.xpath("//*[@id=\"addApprover\"]")).click();

        driver.switchTo().parentFrame();
        driver.switchTo().frame("bodyframe");
        driver.findElement(By.xpath("//*[@id=\"workflowbody\"]/table/tbody/tr/td[2]/table/tbody/tr/td[1]/table/tbody/tr/td/div/div/a/i")).click();

        // Switch to popup window to select approver
        String parentWindowHandle = driver.getWindowHandle();

        Set<String> windowHandles = driver.getWindowHandles();

        for (String handle : windowHandles) {
            if (!handle.equals(parentWindowHandle)) {
                driver.switchTo().window(handle);
                break;
            }
        }

        driver.switchTo().window(parentWindowHandle);


        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"searchText\"]")));


        driver.findElement(By.xpath("//*[@id=\"searchText\"]")).sendKeys("Sukreet Kumar Sinha");
        Thread.sleep(3000);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='ui-id-1']/li")));

        driver.findElement(By.xpath("//*[@id='ui-id-1']/li")).click();


        driver.findElement(By.xpath("//*[@class=\"btn btn-wp selectApp\"]")).click();


        driver.switchTo().frame("C1ReqMain");
        driver.switchTo().frame("topframe");


        driver.findElement(By.xpath("//*[@title='Close']")).click();

        //Switch to frame
        driver.switchTo().frame("C1ReqMain");

        driver.findElement(By.xpath("//*[@title='Submit Request']")).click();
    }

    public void approval_login() throws InterruptedException {
        driver.findElement(By.xpath("//*[@id=\"visibleUname\"]")).sendKeys("sukreetperfect");
        driver.findElement(By.xpath("//*[@id=\"visiblePass\"]")).sendKeys("Password@12");
        sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"login-submit\"]")).click();

    }

    protected String getClipboardText() {
        return null;
    }

    public void from_date() throws InterruptedException {

        driver.findElement(By.xpath("//*[@id=\"filter_fromDate\"]")).click();
        Thread.sleep(2000);

        LocalDate currentDate = LocalDate.now();

        // Find the element representing the current date
        String xpath = String.format("//td[@class='today day']", currentDate.getDayOfMonth());
        WebElement currentDateElement = driver.findElement(By.xpath(xpath));

        // Click on the element to select the current date
        currentDateElement.click();
        Thread.sleep(5000);

    }

    public void to_date() throws InterruptedException {

        driver.findElement(By.xpath("//*[@id=\"filter_toDate\"]")).click();
        Thread.sleep(2000);

        LocalDate currentDate2 = LocalDate.now();

        // Find the element representing the current date
        String xpath2 = String.format("//td[@class='today day']", currentDate2.getDayOfMonth());
        WebElement currentDateElement2 = driver.findElement(By.xpath(xpath2));

        // Click on the element to select the current date
        currentDateElement2.click();
        Thread.sleep(3000);

    }

    public void request_from_date() throws InterruptedException {
        driver.findElement(By.xpath("//*[@name='FromDate']")).click();
        sleep(2000);

        LocalDate currentDate = LocalDate.now();

        // Find the element representing the current date
        String xpath = String.format("//td[@class='today day']", currentDate.getDayOfMonth());
        WebElement currentDateElement = driver.findElement(By.xpath(xpath));

        // Click on the element to select the current date
        currentDateElement.click();
        sleep(5000);
    }

    public void request_to_date() throws InterruptedException {
        driver.findElement(By.xpath("//*[@name='ToDate']")).click();
        sleep(2000);

        LocalDate currentDate2 = LocalDate.now();

        // Find the element representing the current date
        String xpath2 = String.format("//td[@class='today day']", currentDate2.getDayOfMonth());
        WebElement currentDateElement2 = driver.findElement(By.xpath(xpath2));
        currentDateElement2.click();
        sleep(5000);

    }

    @AfterClass(enabled = false)
    public void tearDown() {
        // Close the browser after the test execution is complete
        driver.quit();

//        extent.flush();


    }


}

