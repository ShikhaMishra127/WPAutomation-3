package buyer.testcases.vendor;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import commonutils.pageobjects.utils.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

import static ExtentReporters.getScreenShot.capture;
import static commonutils.pageobjects.utils.ExtentReport.report;

public class VendorsStage extends BaseClass {
//    private WebDriver driver;

    static ExtentTest test;

    @BeforeClass
    public static void startTest() {
        report = new com.relevantcodes.extentreports.ExtentReports(System.getProperty("user.dir") + "\\ExtentReportResults.html");
        test = report.startTest("Vendor Invoice Test Cases");
    }

    @BeforeMethod
    public void login_App() throws InterruptedException {
        vendors_login();
        test.log(LogStatus.PASS, "Successfully Login");

    }


    @Test(priority = 1)
    public void vendor_invoice() throws InterruptedException, IOException {


       try {
           driver.manage().timeouts().implicitlyWait(12, TimeUnit.SECONDS);

           test.log(LogStatus.PASS, "Test Case-1 : Create Vendor Invoice");

           WebElement invoice = driver.findElement(By.xpath("//*[@title='Invoice']"));
           invoice.click();
           WebElement Create_invoice = driver.findElement(By.xpath("//*[@title='Create New'][1]"));
           Create_invoice.click();
           test.log(LogStatus.PASS, "Come to the Vendor Create Invoice page");

           //Invoice creation- Header Information
           // Get the current date and time
           LocalDateTime currentDateTime = LocalDateTime.now();

           // Format the date and time as per your requirement
           DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
           String formattedDateTime = currentDateTime.format(formatter);


           By textBoxLocator = By.xpath("//*[@name='invoicenum']");
           WebElement Invoice_Number = driver.findElement(textBoxLocator);
           String trimmedDateTime = formattedDateTime.replace(" ", "")
                   .replace(":", "")
                   .replace("-", "");

           Invoice_Number.sendKeys(trimmedDateTime);
           Thread.sleep(4000);

           // Click on issue date
           WebElement Issue_date = driver.findElement(By.xpath("//*[@name='issue_date']"));
           Issue_date.click();

           LocalDate currentDate = LocalDate.now();
           // Find the element representing the current date
           String xpath = String.format("//td[@class='today day']", currentDate.getDayOfMonth());
           WebElement currentDateElement = driver.findElement(By.xpath(xpath));

           // Click on the element to select the current date
           currentDateElement.click();
           Thread.sleep(5000);

           // Click on Due date

           currentDate = LocalDate.now();
           LocalDate targetDate = currentDate.plusDays(2);
           formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
           String formattedTargetDate = targetDate.format(formatter);

           By inputFieldLocator = By.xpath("//*[@name='due_date']");
           WebElement inputField = driver.findElement(inputFieldLocator);
           inputField.clear();
           test.log(LogStatus.PASS, "Fill all the necessary details on the header information page");

           // Enter the formatted target date in the input field
           inputField.sendKeys(formattedTargetDate);
           Thread.sleep(3000);

           driver.findElement(By.xpath("//*[@id=\"wrapper\"]/section[3]/form/div[1]/div[1]/button[1]")).click();
           Thread.sleep(3000);

           String iframeSrc = "/vendorinv/findpo.do?eboID=33&amp;bmsHOID=62798&amp;invoiceid=&amp;&amp;pos=";
           WebElement iframeElement = driver.findElement(By.xpath("/html/body/div[6]/div[2]/div/div[2]/div/iframe"));

           // Switch to the iframe
           driver.switchTo().frame(iframeElement);

           test.log(LogStatus.PASS, "Select the PO");

           // Switch to the iframe
           driver.findElement(By.xpath("//*[@id=\"cont-search\"]/div[2]/div/table/tbody/tr[1]/td[1]/i")).click();
           driver.findElement(By.xpath("/html/body/div[1]/form/section[2]/div[2]/div/table/tbody/tr[2]/td[2]/div/table/tbody/tr[1]/th[1]/input")).click();
           driver.findElement(By.xpath("//*[@id=\"cont-search\"]/div[3]/div/button")).click();
           driver.switchTo().defaultContent();
           driver.findElement(By.xpath("/html/body/div[1]/section[3]/form/div[2]/div[2]/div/table/tbody/tr[1]/td[2]/input")).sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.BACK_SPACE);
           driver.findElement(By.xpath("/html/body/div[1]/section[3]/form/div[2]/div[2]/div/table/tbody/tr[1]/td[2]/input")).sendKeys("1");

           test.log(LogStatus.PASS, "Enter the invoice quantity");

           WebElement Next_btn = driver.findElement(By.xpath("//button[normalize-space()='Next']"));
           Next_btn.click();

           WebElement save_btn = driver.findElement(By.xpath("//*[@id=\"save\"]"));
           save_btn.click();
           Thread.sleep(2000);
           driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/div[2]/button[2]")).click();
           Thread.sleep(3000);

           //  Scroll to the bottom of the page
           JavascriptExecutor js = (JavascriptExecutor) driver;
           js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

           Thread.sleep(3000);
           WebElement submit_btn = driver.findElement(By.xpath("//*[@id='btnsubmit']"));
           submit_btn.click();
           test.log(LogStatus.PASS, "Successfully submitted the invoice on the summary page");

           driver.findElement(By.xpath("/html/body/div[7]/div[2]/div/div[2]/button[2]")).click();

           //Applied Assertion
           WebElement invoice_alert = driver.findElement(By.xpath("//*[@id=\"closemodal\"]"));
           invoice_alert.getText();
           test.log(LogStatus.PASS, "Click on the Close the confirmation alert");
           Thread.sleep(2000);
           driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[1]")).getText();
           Assert.assertEquals(invoice_alert.getText(), "Close");



           Thread.sleep(3000);
           test.log(LogStatus.INFO, test.addScreenCapture(capture(driver)) + "For clarifications - Please refer to the" +
                   " final ScreenShot...!!");
       }
       catch (Exception e){
           test.log(LogStatus.FAIL, "This Test Case is fail");
           test.log(LogStatus.INFO, test.addScreenCapture(capture(driver)) + "For clarifications - Please refer to the" +
                   " failed test-case ScreenShot...!!");
       }
     }

     @Test(priority = 2)
     public void Vendor_ViewAll_Invoice() throws IOException {
         try {
             driver.manage().timeouts().implicitlyWait(12, TimeUnit.SECONDS);


             test.log(LogStatus.PASS, "Test Case-2 : Vendor ViewAll Invoices");

             WebElement invoice = driver.findElement(By.xpath("//*[@title='Invoice']"));
             invoice.click();
             WebElement view_all = driver.findElement(By.xpath("//a[normalize-space()='View All']"));
             view_all.click();
             test.log(LogStatus.PASS, "Go to the Invoice View All Page");

             Select select_org = new Select(driver.findElement(By.xpath("//*[@id=\"borg_filter\"]")));
             select_org.selectByValue("2");

             Select select_status = new Select(driver.findElement(By.xpath("//*[@id=\"filter_invstat\"]")));
             select_status.selectByVisibleText("Submitted");
             test.log(LogStatus.PASS, "Applied 'Select Org' and 'Status filter'");

             WebElement Apply_Filter = driver.findElement(By.xpath("//button[normalize-space()='Apply Filter']"));
             Apply_Filter.click();
             test.log(LogStatus.PASS, "Applied 'Select Org' and 'Status filter'");

             WebElement Buyer_invoice_sort = driver.findElement(By.xpath("//th[@aria-label='Buyer Invoice/CM No.: " +
                     "activate to sort column']"));
             Buyer_invoice_sort.click();

             WebElement Post_date = driver.findElement(By.xpath("//th[@aria-label='Date: activate to sort column']"));
             Post_date.click();
             test.log(LogStatus.PASS, "Clicked on the 'Buyer Invoice number' and 'Post date' columns for sorting");

             //*[@id="page-title"]/div/div/h3

             // Applied Assertion
             String edit_user_page = driver.findElement(By.xpath("//*[@id=\"page-title\"]/div/div/h3")).getText();
             String expectedTitle = "Invoice/Credit List";
             Assert.assertEquals(edit_user_page, expectedTitle, "Page title does not match the expected title.");

             Thread.sleep(2000);
             test.log(LogStatus.INFO, test.addScreenCapture(capture(driver)) + "For clarifications - Please refer to the" +
                     " final ScreenShot...!!");

         }
         catch(Exception e){
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

