package buyer.Manual_Test;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import commonutils.pageobjects.utils.BaseClassProd;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static ExtentReporters.getScreenShot.capture;
import static commonutils.pageobjects.utils.ExtentReport.report;

public class Payment_Voucher_Prod extends BaseClassProd {

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

    @Test(priority=1)
    public void create_PV() throws InterruptedException, IOException{

        try {
            test.log(LogStatus.PASS, "Test Case-1 : Create Payment Voucher");
            driver.manage().timeouts().implicitlyWait(12, TimeUnit.SECONDS);
            test.log(LogStatus.PASS, "Start Creating payment vouchers by following all the steps.");
            create_payment_voucher_prod();

            test.log(LogStatus.PASS, "payment voucher is successfully created");

            test.log(LogStatus.PASS, "Creating payment voucher test case is  successfully get tested out");
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

    @Test(priority=2)
    public void Edit_PV() throws InterruptedException, IOException{

        try {

            driver.manage().timeouts().implicitlyWait(12, TimeUnit.SECONDS);
            test.log(LogStatus.PASS, "Test Case-2 : Edit Payment Voucher");

            test.log(LogStatus.PASS, "Start Creating new payment voucher by following all the steps.");
            Edit_Payment_Voucher_prod();

            test.log(LogStatus.PASS, "Payment voucher is successfully get created and comes in 'Payment voucher " +
                    "created' status");

            test.log(LogStatus.PASS, "Go to the created payment voucher and Edit it.");
            driver.findElement(By.xpath("//*[@id=\"invTable\"]/tbody/tr[1]/td[9]/span/button/img")).click();

            driver.findElement(By.xpath("//span[@class='dropdown dd-span open']//li[3]//a[1]")).click();


            driver.findElement(By.xpath("//textarea[@placeholder='Enter justification note']")).sendKeys("...Now..Edit " +
                    "the " +
                    "payment voucher is in progress");


            JavascriptExecutor js1 = (JavascriptExecutor) driver;
//        Scroll down till the bottom of the page
            js1.executeScript("window.scrollBy(0,document.body.scrollHeight)");
            test.log(LogStatus.PASS, "Submit the payment voucher after edit.");
            driver.findElement(By.xpath("//button[normalize-space()='Submit']")).click();

            test.log(LogStatus.PASS, "Editing payment voucher test case is  successfully get tested out");
            Thread.sleep(3000);
            test.log(LogStatus.INFO, test.addScreenCapture(capture(driver)) + "For clarifications - Please refer to the" +
                    " final ScreenShot...!!");

        }
        catch(Exception e){
            test.log(LogStatus.FAIL, "This Test Case is fail");
            test.log(LogStatus.INFO, test.addScreenCapture(capture(driver)) + "For clarifications - Please refer to the" +
                    " failed test-case ScreenShot...!!");
        }

    }

    @Test(priority = 3)
    public void Delete_payment_voucher() throws InterruptedException, IOException {
        try {
            test.log(LogStatus.PASS, "Test Case-3 : Delete Payment Voucher");
            driver.manage().timeouts().implicitlyWait(12, TimeUnit.SECONDS);

            test.log(LogStatus.PASS, "Go to the Invoice View All page");

            WebElement Invoice_tab = driver.findElement(By.xpath("//a[@title='Invoice']"));
            Invoice_tab.click();

            WebElement Invoice_viewAll = driver.findElement(By.xpath("//a[@href='/POListing/city/perfect']"));
            Invoice_viewAll.click();

            test.log(LogStatus.PASS, "Select 'payment voucher' as a document type and status as a payment voucher " +
                    "created");

            driver.findElement(By.xpath("//*[@id=\"filter\"]/form/fieldset/div[3]/div/div/button[2]")).click();

            Select select_doc_type = new Select(driver.findElement(By.xpath("//select[@name='doctype_filter']")));
            select_doc_type.selectByVisibleText("Payment Vouchers");

            Select select_status = new Select(driver.findElement(By.xpath("//*[@id=\"filter_invstat\"]")));
            select_status.selectByVisibleText("Payment Voucher Created");

            test.log(LogStatus.PASS, "Click on the Apply filter button.");

            driver.findElement(By.xpath("//button[normalize-space()='Apply Filter']")).click();
            Thread.sleep(2000);
            test.log(LogStatus.PASS, "Go to the targeted payment voucher and click on its action menu and select the " +
                    "delete action from there.");
            Thread.sleep(2000);
            driver.findElement(By.xpath("//*[@id=\"invTable\"]/tbody/tr[1]/td[9]/span/button/img")).click();
            driver.findElement(By.xpath("//span[@class='dropdown dd-span open']//li[5]//a[1]")).click();

            driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/div[2]/button[2]")).click();

            test.log(LogStatus.PASS, "Payment Voucher is successfully get deleted.");
            test.log(LogStatus.INFO, test.addScreenCapture(capture(driver)) + "For clarifications - Please refer to the" +
                    " final ScreenShot...!!");

        } catch (IOException e)
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
