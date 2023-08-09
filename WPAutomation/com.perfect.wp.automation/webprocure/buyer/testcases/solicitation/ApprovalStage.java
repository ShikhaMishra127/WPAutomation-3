package buyer.testcases.solicitation;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import commonutils.pageobjects.utils.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static ExtentReporters.getScreenShot.capture;
import static commonutils.pageobjects.utils.ExtentReport.report;
import static java.lang.Thread.sleep;

public class ApprovalStage extends BaseClass {
//    private WebDriver driver;

    static ExtentTest test;

    @BeforeClass
    public static void startTest() {
        report = new com.relevantcodes.extentreports.ExtentReports(System.getProperty("user.dir") + "\\ExtentReportResults.html");
        test = report.startTest("Approval Inbox");
    }

    @BeforeMethod
    public void login_App() throws InterruptedException {
        Buyer_login();
        test.log(LogStatus.PASS, "Successfully Login");

    }

    @Test
    public void Approval_Inbox() throws InterruptedException, IOException
    {


        try {
            // Create a request
            driver.manage().timeouts().implicitlyWait(12, TimeUnit.SECONDS);
            test.log(LogStatus.PASS, "Test Case-1: Approval Inbox");
            create_request_for_approval();
            test.log(LogStatus.PASS, "Request created");

            // Go to Request tab and View All

            WebElement Req_Tab = driver.findElement(By.xpath("//*[@title='Request']"));
            WebElement Req_viewAll = driver.findElement(By.xpath("//*[@title='View All Requests']"));
            Req_Tab.click();
            Req_viewAll.click();
            sleep(3000);

            // Search for the Calender
            request_from_date(); // Click on the "From date"
            request_to_date();   // Click on "To date"

           WebElement filter_submit_btn= driver.findElement(By.xpath("//*[@name='Submit']"));
           filter_submit_btn.click();
           test.log(LogStatus.PASS, "Request created successfully can be review on the view all page");
           // Logout from the buyer side
             logout();
            test.log(LogStatus.PASS, "Logout from the buyer site");

            // Login with the Approver's credentials
            approval_login();
            test.log(LogStatus.PASS, "Login into approver's site");

            sleep(4000);
//        Switch to the pop-up window
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

            sleep(5000);

            WebElement Approval_tab = driver.findElement(By.xpath("//*[@title='Approval']"));
            Approval_tab.click();
            WebElement Approval_Ibx = driver.findElement(By.xpath("//*[@title='Approval Inbox']"));
            Approval_Ibx.click();
            test.log(LogStatus.PASS, "Come to the Approval Inbox page");

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

           WebElement submit_btn = driver.findElement(By.xpath("//*[@title='Submit']"));
           submit_btn.click();
           sleep(3000);

            WebElement approveButton = driver.findElement(By.xpath("//*[@title='Approve']"));
            approveButton.click();
            test.log(LogStatus.PASS, "Successfully approved the created request document");

            sleep(4000);// driver.findElement(By.xpath("//button[text()='Approve'][1]"));

            //*[@id="page-title"]/h3
            String approval_inbox_title = driver.findElement(By.xpath("//*[@id=\"page-title\"]/h3")).getText();
            String expectedTitle = "Document Approval Inbox";
            Assert.assertEquals(approval_inbox_title, expectedTitle, "Page title does not match the expected title.");


            test.log(LogStatus.INFO, test.addScreenCapture(capture(driver)) + "For clarifications - Please refer to the" +
                    " final ScreenShot...!!");
        }
        catch (Exception e)
        {
            test.log(LogStatus.FAIL, "This Test Case is fail");
            test.log(LogStatus.INFO, test.addScreenCapture(capture(driver)) + "For clarifications - Please refer to the" +
                    " failed test-case ScreenShot...!!");
        }
    }


    public String getClipboardText() {
        return null;
    }


    @AfterMethod(enabled = false)
    public void tearDownAfterTest() {
        //Quit the Order

    }

    @AfterClass
    public static void endTest() {
        report.endTest(test);
        report.flush();
    }
}
