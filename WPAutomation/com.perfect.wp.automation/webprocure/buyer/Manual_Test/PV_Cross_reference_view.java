package buyer.Manual_Test;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import commonutils.pageobjects.utils.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;

import static ExtentReporters.getScreenShot.capture;
import static commonutils.pageobjects.utils.ExtentReport.report;

public class PV_Cross_reference_view extends BaseClass {

    static ExtentTest test;

    @BeforeClass
    public static void startTest() {
        report = new com.relevantcodes.extentreports.ExtentReports(System.getProperty("user.dir") + "\\ExtentReportResults.html");
        test = report.startTest("Payment Voucher TestCase");
    }

    @BeforeMethod
    public void login_App() throws InterruptedException {
        Buyer_login();
        test.log(LogStatus.PASS, "Successfully Login");

    }

    @Test(priority = 1)
    public void search_Payment_Voucher() throws InterruptedException, IOException {

        try {

            test.log(LogStatus.PASS, "Test-Case-1: Search Payment Voucher on cross reference view");

            test.log(LogStatus.PASS, "Create an Payment Voucher");
            Create_Payment_voucher();
            test.log(LogStatus.PASS, "Payment voucher is successfully created");
            // Paste the copied payment voucher in the request number filter
            test.log(LogStatus.PASS, "Searched the created payment voucher in the cross-reference view search box");


            WebElement buyer_no = driver.findElement(By.xpath("//*[@id='crcSearchBox']"));
            // Create an Actions object
            Actions actions2 = new Actions(driver);
            // Click inside the textbox to focus on it
            buyer_no.click();
            // Use keyboard shortcuts to paste the text
            actions2.keyDown(Keys.CONTROL).sendKeys("v").keyUp(Keys.CONTROL).perform();


            driver.findElement(By.xpath("//*[@onclick='crossReferenceSearch()']")).click();
            Thread.sleep(3000);
            driver.findElement(By.xpath("//*[@class='highlight']")).click();

            driver.findElement(By.xpath("//*[@class='pointer text-primary']")).click();

            // Assertion Applied
            String actualTitle = driver.findElement(By.xpath("//*[contains(text(), \" Payment Voucher Summary\")]")).getText();
            String expectedTitle = "Payment Voucher Summary";
            Assert.assertEquals(actualTitle, expectedTitle, "Page title does not match the expected title.");

            test.log(LogStatus.INFO, test.addScreenCapture(capture(driver)) + "For clarifications - Please refer to the" +
                    " final ScreenShot...!!");
        }
        catch(Exception e)
        {
            test.log(LogStatus.FAIL, "This Test Case is fail");
            test.log(LogStatus.INFO, test.addScreenCapture(capture(driver)) + "For clarifications - Please refer to the" +
                    " failed test-case ScreenShot...!!");
        }

    }

    @Test(priority = 2)
    public void Edit_PV_cross_reference_view() throws InterruptedException, IOException {

        try {
            test.log(LogStatus.PASS, "Test-Case-1: Edit Payment Voucher on cross reference view");
            test.log(LogStatus.PASS, "Start creating Payment voucher");

            Edit_Payment_Voucher();

            test.log(LogStatus.PASS, "Search the payment voucher in the cross-reference view search box");
            // Paste the copied payment voucher in the request number filter

            WebElement buyer_no = driver.findElement(By.xpath("//*[@id='crcSearchBox']"));
            // Create an Actions object
            Actions actions2 = new Actions(driver);
            // Click inside the textbox to focus on it
            buyer_no.click();
            // Use keyboard shortcuts to paste the text
            actions2.keyDown(Keys.CONTROL).sendKeys("v").keyUp(Keys.CONTROL).perform();

            driver.findElement(By.xpath("//*[@onclick='crossReferenceSearch()']")).click();
            Thread.sleep(3000);

            test.log(LogStatus.PASS, "Click on the resultant payment voucher number");

            driver.findElement(By.xpath("//*[@class='highlight']")).click();

            test.log(LogStatus.PASS, "Select the Edit from the action menu");
            driver.findElement(By.xpath("//*[@id='dropdownMenuButton']")).click();
            driver.findElement(By.xpath("//*[contains(text(), \"Edit\")]")).click();


            test.log(LogStatus.PASS, "Write some content in the justification description box");
            driver.findElement(By.xpath("//*[@placeholder='Enter justification note']")).sendKeys("...Edit the Payment Voucher..");

            Thread.sleep(3000);

            JavascriptExecutor js1 = (JavascriptExecutor) driver;
//        Scroll down till the bottom of the page
            js1.executeScript("window.scrollBy(0,document.body.scrollHeight)");
            test.log(LogStatus.PASS, "Submit the payment voucher after edit.");
            driver.findElement(By.xpath("//button[normalize-space()='Submit']")).click();

            test.log(LogStatus.PASS, "Submit it");

            driver.findElement(By.xpath("//*[contains(text(), \"Submit\")]")).click();

            WebElement buyer_no1 = driver.findElement(By.xpath("//*[@id='crcSearchBox']"));
            // Create an Actions object
            Actions actions3 = new Actions(driver);
            // Click inside the textbox to focus on it
            buyer_no1.click();
            // Use keyboard shortcuts to paste the text
            actions3.keyDown(Keys.CONTROL).sendKeys("v").keyUp(Keys.CONTROL).perform();

            test.log(LogStatus.PASS, "Verified that the payment voucher comes in 'Approved' status");

            driver.findElement(By.xpath("//*[@onclick='crossReferenceSearch()']")).click();
            Thread.sleep(3000);
            test.log(LogStatus.PASS, "Click on its action menu and select the 'History' option");
            driver.findElement(By.xpath("//*[@class='highlight']")).click();
            test.log(LogStatus.PASS, "Click on its action menu and select the 'History' option");
            driver.findElement(By.xpath("//*[@id='dropdownMenuButton']")).click();

            driver.findElement(By.xpath("//*[contains(text(), \" History\")]")).click();

            test.log(LogStatus.PASS, "Verified that the 'Payment Voucher History' page successfully appeared.");
            // Assertion Applied
            String actualTitle = driver.findElement(By.xpath("//*[contains(text(), \"Approved\")][1]")).getText();
            String expectedTitle = "Approved";
            Assert.assertEquals(actualTitle, expectedTitle, "Page title does not match the expected title.");

            test.log(LogStatus.INFO, test.addScreenCapture(capture(driver)) + "For clarifications - Please refer to the" +
                    " final ScreenShot...!!");
        }
        catch(Exception e)
        {
            test.log(LogStatus.FAIL, "This Test Case is fail");
            test.log(LogStatus.INFO, test.addScreenCapture(capture(driver)) + "For clarifications - Please refer to the" +
                    " failed test-case ScreenShot...!!");
        }

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
