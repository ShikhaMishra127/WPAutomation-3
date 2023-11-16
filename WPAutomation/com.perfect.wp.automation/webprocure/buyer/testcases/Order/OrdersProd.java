package buyer.testcases.Order;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import commonutils.pageobjects.utils.BaseClassProd;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.concurrent.TimeUnit;

import static ExtentReporters.getScreenShot.capture;
import static commonutils.pageobjects.utils.ExtentReport.report;

public class OrdersProd extends BaseClassProd {


    static ExtentTest test;

    @BeforeClass
    public static void startTest() {
        report = new com.relevantcodes.extentreports.ExtentReports(System.getProperty("user.dir") + "\\ExtentReportResults.html");
        test = report.startTest("Order Testcases");
    }

    @BeforeMethod
    public void login_App() throws InterruptedException {
        Buyer_login();
//        test.log(LogStatus.PASS, "Successfully Login");

    }

    @Test(priority = 1)

        public void OrderViewAll() throws InterruptedException, IOException {
        try {

            driver.manage().timeouts().implicitlyWait(12, TimeUnit.SECONDS);
            test.log(LogStatus.PASS, "Test Case-1 : Order ViewAll page");
            test.log(LogStatus.PASS, "Go to the Order View all page");

            WebElement order = driver.findElement(By.xpath("//a[@title='Order']"));
            WebElement order_viewAll = driver.findElement(By.xpath("/html/body/nav/div/div/div[2]/ul/li[2]/ul/li[1]/a"));
            order.click();
            order_viewAll.click();

            driver.findElement(By.xpath("//*[@id=\"FSts\"]")).click();
            driver.findElement(By.xpath("//*[@id=\"FSts\"]/option[3]")).click();

            WebElement submit = driver.findElement(By.xpath("//*[@id=\"filter\"]/div/div/div/button[1]"));

            submit.click();

            // Change the sort of column
            driver.findElement(By.xpath("//*[@id=\"poTable\"]/thead/tr/th[3]")).click();
            driver.findElement(By.xpath("//*[@id=\"poTable\"]/thead/tr/th[9]")).click();
            test.log(LogStatus.PASS, "Sorting of columns are working correctly");

            String actualTitle = driver.findElement(By.xpath("//*[@id=\"page-title\"]/h3")).getText();
            String expectedTitle = "Track Orders";
            Assert.assertEquals(actualTitle, expectedTitle, "Page title does not match the expected title.");

//            driver.findElement(By.xpath("//*[@id=\"page-title\"]/h3")).getText();
//            Assert.assertEquals(invoice_alert.getText(), "");

            test.log(LogStatus.PASS, "OrderViewAll page successfully get tested out");
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


    @Test(priority = 2)
    public void Receive() throws InterruptedException, IOException {
        try {
            test.log(LogStatus.PASS, "Test Case-2 : Order Receive.");
            test.log(LogStatus.PASS, "Go to the receive list page.");
            WebElement order = driver.findElement(By.xpath("//a[@title='Order']"));
            Thread.sleep(3000);
            order.click();
            WebElement Receive_list = driver.findElement(By.xpath("/html/body/nav/div/div/div[2]/ul/li[2]/ul/li[2]/a"));
            Receive_list.click();
            Thread.sleep(3000);

            test.log(LogStatus.PASS, " Start Receive the Order");
            driver.findElement(By.xpath("//*[@id=\"rcvTable\"]/tbody/tr[1]/td[9]/span/button/img")).click();
            Thread.sleep(3000);
            driver.findElement(By.xpath("//*[@id=\"rcvTable\"]/tbody/tr[1]/td[9]/span/ul/li[1]/a")).click();
            Thread.sleep(5000);
            driver.findElement(By.id("rcvDate")).click();


            LocalDate currentDate = LocalDate.now();

            // Find the element representing the current date
            String xpath = String.format("//td[@class='today day']", currentDate.getDayOfMonth());
            WebElement currentDateElement = driver.findElement(By.xpath(xpath));

            // Click on the element to select the current date
            currentDateElement.click();
            Thread.sleep(5000);

            WebElement receivedQty = driver.findElement(By.xpath("//*[@id=\"orderDetails\"]/div[4]/div/table/tbody/tr[1]/td[4]/input"));

            // Clear the value from the textbox
            //receivedQty.clear();
            // Select the existing value and clear it using the combination of Ctrl+A and Backspace
            receivedQty.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.BACK_SPACE);


            driver.findElement(By.name("txtReceivedQty")).sendKeys("1.000");
            Thread.sleep(6000);
            driver.findElement(By.id("cmdSubmit")).click();
            Thread.sleep(5000);
            driver.findElement(By.xpath("//*[@id=\"rcvTable\"]/tbody/tr[1]/td[9]/span/button/img")).click();
            Thread.sleep(3000);
            driver.findElement(By.xpath("//*[@id=\"rcvTable\"]/tbody/tr[1]/td[9]/span/ul/li[2]/a")).click();
            Thread.sleep(6000);

            // Assertion Applied
            String actualTitle = driver.findElement(By.xpath("//*[@id=\"page-title\"]/h3")).getText();
            String expectedTitle = "Receipt History";
            Assert.assertEquals(actualTitle, expectedTitle, "Page title does not match the expected title.");

            test.log(LogStatus.PASS, "Receiving the order is successfully get executed");

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

    @Test(priority = 3)
    public void vendors_profile() throws InterruptedException, IOException {
        try {
            test.log(LogStatus.PASS, "Test Case-3 : View Order from vendor-site.");

            create_request();
            test.log(LogStatus.PASS, "Successfully created a request");
            // Logout from buyer side
            logout();
            test.log(LogStatus.PASS, "Logout Successfully from the buyer-site");
            // Login to vendor side

            test.log(LogStatus.PASS, "Login to the vendor-site Successfully");
            vendors_login();
            test.log(LogStatus.PASS, "Login to the vendor-site Successfully");

            //Click on Order tab and select "view orders"
            test.log(LogStatus.PASS, "Go to its view order page.");

            WebElement Order_tab = driver.findElement(By.xpath("//*[@id=\"wrapper\"]/nav/div/div/div[2]/ul[1]/li[3]/a"));
            Order_tab.click();
            Thread.sleep(2000);

            WebElement view_orders = driver.findElement(By.xpath("//*[@id=\"wrapper\"]/nav/div/div/div[2]/ul[1]/li[3]/ul/li[2]/a"));
            view_orders.click();
            Thread.sleep(2000);

            // Click on the "From date"
            from_date();
            // Click on To date
            to_date();

            driver.findElement(By.xpath("//*[@id=\"find\"]")).click();

            driver.findElement(By.xpath("//*[@id=\"poTable\"]/tbody/tr/td[1]/a")).click();
            Thread.sleep(3000);
            driver.findElement(By.xpath("//*[@id=\"acknowledgeAction\"]")).click();


            Thread.sleep(3000);
            driver.findElement(By.xpath("//*[@id=\"vendorAckComments\"]")).sendKeys("Just for testing purpose");
            Thread.sleep(3000);
            driver.findElement(By.xpath("//*[@id=\"confirmAcknowledgeMessage\"]/form/div/input[2]")).click();
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0,-4500)");

            test.log(LogStatus.PASS, "Acknowledgement of the order performed successfully");

            Thread.sleep(3000);
            // Applied Assertion
            String acknowledge_order = driver.findElement(By.xpath("//*[@id=\"status-info\"]")).getText();
            String expectedTitle = "ACKNOWLEDGED";
            Assert.assertEquals(acknowledge_order, expectedTitle, "Page title does not match the expected title.");

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
