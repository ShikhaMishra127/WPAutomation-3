package buyer.testcases.Approval;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.time.LocalDate;
import java.util.Set;

import static java.lang.Thread.sleep;

public class Approval {
    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        // Set up the WebDriver instance
        System.setProperty("webdriver.chrome.driver", "D:\\chromedriver_win32 (4)\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();


    }

    @BeforeMethod
    public void testLoginPage() throws InterruptedException {
        // Open the login page
        driver.get("https://webprocure-stage.proactiscloud.com/Login");

        // Perform login
        WebElement usernameField = driver.findElement(By.id("visibleUname"));
        WebElement passwordField = driver.findElement(By.xpath("//input[@id='visiblePass']"));
        WebElement loginButton = driver.findElement(By.id("login-submit"));

        usernameField.sendKeys("smperfect");
        passwordField.sendKeys("Welcome@2");
        loginButton.click();

        sleep(5000);

//         Switch to the pop-up window
        String mainWindowHandle = driver.getWindowHandle();
        for (String handle : driver.getWindowHandles())
        {
            if (!handle.equals(mainWindowHandle))
            {
                driver.switchTo().window(handle);
                break;
            }
        }

        // Find and click the "Ignore" button
        WebElement ignoreButton = driver.findElement(By.xpath("//*[@id=\"session_info_display_modal\"]/div[2]/div/div[3]/button[1]"));
        ignoreButton.click();

        // Switch back to the main window
        driver.switchTo().window(mainWindowHandle);

        sleep(5000);




        // Verify successful login
//        WebElement welcomeMessage = driver.findElement(By.id("welcome-message"));
//        Assert.assertEquals(welcomeMessage.getText(), "Welcome, ShikhaM");
    }

    @Test
    public void Approval_Inbox() throws InterruptedException {


        // Create a request
        WebElement Request_tab = driver.findElement(By.xpath("//a[@title=\"Request\"]"));
        Request_tab.click();
        WebElement Create_new = driver.findElement(By.xpath("//a[@title=\"Create new\"]"));
        Create_new.click();

        sleep(4000);
        driver.switchTo().frame("C1ReqMain");


        driver.findElement(By.xpath("//*[@id=\"wrapper\"]/section[1]/ul/li[4]/a")).click();
        sleep(5000);

//        driver.switchTo().parentFrame();
        driver.switchTo().frame("C1ReqMain");
        driver.findElement(By.xpath("//*[@id=\"OrderQty\"]")).sendKeys("100");
        sleep(2000);
        driver.findElement(By.xpath("//*[@id='UnitPrice']")).sendKeys("50");
        sleep(2000);
        driver.findElement(By.xpath("//input[@id='SupplierPartNum']")).sendKeys("64836493");
        sleep(4000);

        driver.findElement(By.xpath("//input[@id='input_SupplierName']")).sendKeys("Andrew's Dry Cleaners");
        sleep(4000);
        driver.findElement(By.xpath("//*[@id=\"ui-id-1\"]/li[1]")).click();
        sleep(4000);

        driver.findElement(By.xpath("//button[text()='No']")).click();
        sleep(2000);


        driver.findElement(By.xpath("//input[@id='input_catcode']")).sendKeys("**");
        sleep(3000);
        driver.findElement(By.xpath("//*[@id=\"ui-id-3\"]/li[1]")).click();
        sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"btn-add-bottom\"]")).click();
        sleep(2000);

        driver.switchTo().parentFrame();
        driver.switchTo().frame("reqcart");
        driver.findElement(By.xpath("//*[@name='txtReqName']"));

        // Locate the textbox element
//        WebDriver copyreqnumber = null;
//        WebDriver driver = copyreqnumber;

                // Locate the textbox element
                 WebElement textBoxElement = driver.findElement(By.xpath("//*[@name='txtReqName']"));

        // Create an Actions object
        Actions actions = new Actions(driver);

        // Double click on the textbox
        actions.doubleClick(textBoxElement).perform();

        // Use keyboard shortcuts to copy the text
        actions.keyDown(Keys.CONTROL).sendKeys("c").keyUp(Keys.CONTROL).perform();

        // Verify the copied text if needed
        String copiedText = getClipboardText(); // Implement a method to get the text from the clipboard
        System.out.println("Copied Text: " + copiedText);

        driver.switchTo().parentFrame();
        driver.switchTo().frame("C1ReqMain");
        Thread.sleep(4000);

        driver.findElement(By.xpath("//*[@id=\'idView Request\']")).click();
        sleep(4);

        driver.switchTo().frame("C1ReqMain");
        // Assign Account Code
        driver.findElement(By.xpath("//*[@id=\"wrapper\"]/section[4]/form/div[5]/table/tbody/tr[1]/td[5]/p/a[2]/img")).click();
        sleep(3);

        driver.findElement(By.xpath("//*[@id=\"frmReqCostDist\"]/div[3]/button")).click();
        sleep(3);

        driver.findElement(By.xpath("//*[@id=\"frmReqCostDist\"]/div[1]/button[1]")).click();
        sleep(3);

        // Approval preview button

        driver.findElement(By.xpath("//*[@id=\"btn-approvalPreview\"]")).click();
        sleep(3);


        driver.switchTo().frame("topframe");

//        driver.findElement(By.xpath("//*[@title='Close'] ")).click();

        driver.findElement(By.xpath("//*[@id=\"addApprover\"]")).click();
        sleep(3);

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


        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"searchText\"]")));



        driver.findElement(By.xpath("//*[@id=\"searchText\"]")).sendKeys("Sukreet Kumar Sinha");
        sleep(5);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='ui-id-1']/li")));

        driver.findElement(By.xpath("//*[@id='ui-id-1']/li")).click();


        sleep(3);
        driver.findElement(By.xpath("//*[@class=\"btn btn-wp selectApp\"]")).click();
        sleep(5000);


        driver.switchTo().frame("C1ReqMain");
        driver.switchTo().frame("topframe");


        driver.findElement(By.xpath("//*[@title='Close']")).click();
        sleep(3);

        //Switch to frame
        driver.switchTo().frame("C1ReqMain");

        driver.findElement(By.xpath("//*[@title='Submit Request']")).click();
        sleep(2);

        WebElement Req_Tab= driver.findElement(By.xpath("//*[@title='Request']"));
        WebElement Req_viewAll= driver.findElement(By.xpath("//*[@title='View All Requests']"));

        Req_Tab.click();
        Req_viewAll.click();
        sleep(3000);


        // Search for the Calender

        // Click on the "From date"

        driver.findElement(By.xpath("//*[@name='FromDate']")).click();
        sleep(2000);

        LocalDate currentDate = LocalDate.now();

        // Find the element representing the current date
        String xpath = String.format("//td[@class='today day']", currentDate.getDayOfMonth());
        WebElement currentDateElement = driver.findElement(By.xpath(xpath));

        // Click on the element to select the current date
        currentDateElement.click();
        sleep(5000);

        // Click on "To date"

        driver.findElement(By.xpath("//*[@name='ToDate']")).click();
        sleep(2000);

        LocalDate currentDate2 = LocalDate.now();

        // Find the element representing the current date
        String xpath2 = String.format("//td[@class='today day']", currentDate2.getDayOfMonth());
        WebElement currentDateElement2 = driver.findElement(By.xpath(xpath));
         currentDateElement2.click();
        sleep(5000);

        driver.findElement(By.xpath("//*[@name='Submit']")).click();

        // Logout from the buyer side
        driver.findElement(By.xpath("//*[@id=\"userMenu\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"userMenuList\"]/li[4]/a")).click();
        driver.switchTo().alert().accept();
        sleep(5000);


        // Login with the Approver's credentials

        driver.findElement(By.xpath("//*[@id=\"visibleUname\"]")).sendKeys("aXc001" );
        driver.findElement(By.xpath("//*[@id=\"visiblePass\"]")).sendKeys("Welcome@12");
        driver.findElement(By.xpath("//*[@id=\"login-submit\"]")).click();

        sleep(4000);
//        Switch to the pop-up window
        String mainWindowHandle = driver.getWindowHandle();
        for (String handle : driver.getWindowHandles())
        {
            if (!handle.equals(mainWindowHandle))
            {
                driver.switchTo().window(handle);
                break;
            }
        }

        // Find and click the "Ignore" button
        WebElement ignoreButton = driver.findElement(By.xpath("//*[@id=\"session_info_display_modal\"]/div[2]/div/div[3]/button[1]"));
        ignoreButton.click();

        // Switch back to the main window
        driver.switchTo().window(mainWindowHandle);

        sleep(5000);

        WebElement Approval_tab = driver.findElement(By.xpath("//*[@title='Approval']"));
        Approval_tab.click();
        WebElement Approval_Ibx = driver.findElement(By.xpath("//*[@title='Approval Inbox']"));
        Approval_Ibx.click();

        sleep(4000);


        driver.findElement(By.xpath("//*[@name='FDocType']")).click();
        // Locate the dropdown element
        WebElement dropdownElement = driver.findElement(By.xpath("//*[@name='FDocType']"));

        // Create a Select object
        Select dropdown = new Select(dropdownElement);

        // Select an option by visible text
        dropdown.selectByVisibleText("Request");

        // Paste the copied reqnumber in the request number filter

        WebElement Req_Numbertextbox = driver.findElement(By.xpath("//*[@id='FDocumentNumber']"));

        // Create an Actions object
        Actions actions2 = new Actions(driver);

        // Click inside the textbox to focus on it
        Req_Numbertextbox.click();

        // Use keyboard shortcuts to paste the text
        actions2.keyDown(Keys.CONTROL).sendKeys("v").keyUp(Keys.CONTROL).perform();


        driver.findElement(By.xpath("//*[@title='Submit']")).click();
        sleep(3000);

        WebElement approveButton = driver.findElement(By.xpath("//*[@title='Approve']"));
        approveButton.click();
        sleep(4000);// driver.findElement(By.xpath("//button[text()='Approve'][1]"));
    }

    private String getClipboardText() {
        return null;
    }


    @AfterMethod(enabled = false)
    public void tearDownAfterTest() {
//		Quit the Order

    }

    @AfterClass(enabled = false)
    public void tearDown() {
        // Quit the WebDriver instance
        driver.quit();
    }
}
