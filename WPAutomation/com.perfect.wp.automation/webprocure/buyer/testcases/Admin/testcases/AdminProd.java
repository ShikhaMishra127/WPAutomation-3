package buyer.testcases.Admin.testcases;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import commonutils.pageobjects.utils.BaseClassProd;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static ExtentReporters.getScreenShot.capture;
import static commonutils.pageobjects.utils.ExtentReport.report;

public class AdminProd extends BaseClassProd {

    static ExtentTest test;

    @BeforeClass
    public static void startTest() {
        report = new com.relevantcodes.extentreports.ExtentReports(System.getProperty("user.dir") + "\\ExtentReportResults.html");
        test = report.startTest("Admin Testcases");
    }

    @BeforeMethod
    public void login_App() throws InterruptedException {
        Buyer_login();
//        test.log(LogStatus.PASS, "Successfully Login");

    }

    @Test
    public void Edit_general_org_info() throws InterruptedException, IOException {

      try {
          driver.manage().timeouts().implicitlyWait(12, TimeUnit.SECONDS);
          test.log(LogStatus.PASS, "Test case-1 : Edit General Org Info page");

          // Find and click the "Admin" element
          WebElement adminElement = driver.findElement(By.xpath("//*[@title='Admin ']"));
          adminElement.click();
          test.log(LogStatus.PASS, "Go to the Admin tab");

          // Find and click the "Enterprise Administration" element
          WebElement enterpriseAdminElement = driver.findElement(By.xpath("//*[@title='Enterprise Administration']"));
          enterpriseAdminElement.click();


          // Switch to the "orgframe" iframe
          driver.switchTo().frame("orgframe");

          // Find and click the element inside the iframe
          WebElement orgFrameElement = driver.findElement(By.xpath("/html/body/table[2]/tbody/tr/td[2]/a/font"));
          orgFrameElement.click();

          // Switch back to the default content (outside the iframe)
          driver.switchTo().defaultContent();

          // Find and click the first element in the navigation menu
          WebElement firstNavElement = driver.findElement(By.xpath("//*[@id=\"navigation\"]/ul/li[1]/a"));
          firstNavElement.click();

          // Find and click the first sub-element in the navigation menu
          WebElement firstSubNavElement = driver.findElement(By.xpath("//*[@id=\"navigation\"]/ul/li[1]/ul/li[1]/a"));
          firstSubNavElement.click();
          test.log(LogStatus.PASS, "Go to the Edit General Org Info page");

          driver.switchTo().frame("dataframe");
          // Calculate the height of the web page
          JavascriptExecutor js = (JavascriptExecutor) driver;
          long pageHeight = (Long) js.executeScript("return document.documentElement.scrollHeight");

          // Scroll to the middle of the web page
          js.executeScript("window.scrollTo(0, Math.floor(arguments[0] / 2));", pageHeight);

          WebElement removeDocumentElement = driver.findElement(By.xpath("//*[@id=\"remove_document\"]"));
          removeDocumentElement.click();
          test.log(LogStatus.PASS, "Click on the remove document permission");

          // Click on the Save button
          WebElement saveButtonElement = driver.findElement(By.xpath("/html/body/form/table[29]/tbody/tr/td/button[1]"));
          saveButtonElement.click();
          test.log(LogStatus.PASS, "Changes Saved Successfully.");
          Thread.sleep(3000);

          // Applied Assertion
          String edit_genral_page = driver.findElement(By.xpath("/html/body/form/table[1]/tbody/tr[1]/td/a")).getText();
          String expectedTitle = "Edit Info for Perfect City NN";
          Assert.assertEquals(edit_genral_page, expectedTitle, "Page title does not match the expected title.");

          test.log(LogStatus.INFO, test.addScreenCapture(capture(driver)) + "For clarifications - Please refer to the" +
                  " final ScreenShot...!!");
      }
      catch(Exception e) {
          test.log(LogStatus.FAIL, "This Test Case is fail");
          test.log(LogStatus.INFO, test.addScreenCapture(capture(driver)) + "For clarifications - Please refer to the" +
                  " failed test-case ScreenShot...!!");
      }
    }

    @Test
    public void Edit_user() throws InterruptedException, IOException {
        try {
            driver.manage().timeouts().implicitlyWait(12, TimeUnit.SECONDS);
            test.log(LogStatus.PASS, "Test case-2 : Edit User");

            // Find and click the "Admin" element
            WebElement adminElement = driver.findElement(By.xpath("//*[@title='Admin ']"));
            adminElement.click();
            test.log(LogStatus.PASS, "Go to the Admin tab.");

            // Find and click the "Enterprise Administration" element
            WebElement enterpriseAdminElement = driver.findElement(By.xpath("//*[@title='Enterprise Administration']"));
            enterpriseAdminElement.click();

            // Switch to the "orgframe" iframe
            driver.switchTo().frame("orgframe");

            // Find and click the element inside the iframe
            WebElement orgFrameElement = driver.findElement(By.xpath("/html/body/table[2]/tbody/tr/td[2]/a/font"));
            orgFrameElement.click();

            // Switch back to the default content (outside the iframe)
            driver.switchTo().defaultContent();

            WebElement thirdNavElement = driver.findElement(By.xpath("//*[@id=\"navigation\"]/ul/li[3]/a"));
            thirdNavElement.click();

            // Find and click on the first sub-element in the third navigation menu
            WebElement firstSubNavElement = driver.findElement(By.xpath("//*[@id=\"navigation\"]/ul/li[3]/ul/li[1]/a"));
            firstSubNavElement.click();
            test.log(LogStatus.PASS, "Go to the Edit user page.");

            driver.switchTo().frame("dataframe");

            Thread.sleep(3000);
            // Find and enter text in the element with name "un"
            WebElement unElement = driver.findElement(By.xpath("//*[@name='un']"));
            unElement.sendKeys("smperfect");

            // Find and click the element with value "Find"
            WebElement findButtonElement = driver.findElement(By.xpath("//*[@value='Find']"));
            findButtonElement.click();
            test.log(LogStatus.PASS, "Enter the User details(Username/Email/Lastname)");

            // Find and click the element with title "Edit"
            WebElement editElement = driver.findElement(By.xpath("//*[@title='Edit']"));
            editElement.click();
            test.log(LogStatus.PASS, "Click on the Edit Icon.");

            //  Scroll to the bottom of the page
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

            Thread.sleep(3000);
            WebElement adminPermission = driver.findElement(By.xpath("//*[@name='priv_921']"));
            adminPermission.click();

            // Logging the step in the Extent report
            test.log(LogStatus.PASS, "Click on the 'Admin for the TierLINK (Manage TierLINK configuration for each EBO' permission.");

            // Find and click on the button (assuming it's Save or Apply changes)
            WebElement buttonElement = driver.findElement(By.xpath("/html/body/form/table[3]/tbody/tr/td/button[2]"));
            buttonElement.click();
            test.log(LogStatus.PASS, "Changes Saved Successfully.");

            // Applied Assertion
            String edit_user_page = driver.findElement(By.xpath("/html/body/form/table[1]/tbody/tr[1]/td/a")).getText();
            String expectedTitle = "Shikha M";
            Assert.assertEquals(edit_user_page, expectedTitle, "Page title does not match the expected title.");

            test.log(LogStatus.INFO, test.addScreenCapture(capture(driver)) + "For clarifications - Please refer to the" +
                    " final ScreenShot...!!");
        }
        catch (Exception e){
            test.log(LogStatus.FAIL, "This Test Case is fail");
            test.log(LogStatus.INFO, test.addScreenCapture(capture(driver)) + "For clarifications - Please refer to the" +
                    " failed test-case ScreenShot...!!");
        }
    }

    @Test
    public void View_Vendor() throws InterruptedException, IOException {

        try {
            driver.manage().timeouts().implicitlyWait(12, TimeUnit.SECONDS);

            test.log(LogStatus.PASS, "Test case-3 : View Vendor");

            WebElement adminElement = driver.findElement(By.xpath("//*[@title='Admin ']"));
            adminElement.click();
            test.log(LogStatus.PASS, "Go to the Admin tab.");

            // Find and click the "Enterprise Administration" element
            WebElement enterpriseAdminElement = driver.findElement(By.xpath("//*[@title='Enterprise Administration']"));
            enterpriseAdminElement.click();

            // Switch to the "orgframe" iframe
            driver.switchTo().frame("orgframe");

            // Find and click the element inside the iframe
            WebElement orgFrameElement = driver.findElement(By.xpath("/html/body/table[2]/tbody/tr/td[2]/a/font"));
            orgFrameElement.click();

            // Switch back to the default content (outside the iframe)
            driver.switchTo().defaultContent();

            // Click on the fourth element in the navigation menu
            WebElement suppliers = driver.findElement(By.xpath("//*[@id=\"navigation\"]/ul/li[4]/a"));
            suppliers.click();

            // Click on the first sub-element in the fourth navigation menu
            WebElement edit_supplierpage = driver.findElement(By.xpath("//*[@id=\"navigation\"]/ul/li[4]/ul/li[1]/a"));
            edit_supplierpage.click();
            test.log(LogStatus.PASS, "Go to the Edit Supplier page.");

            // Switch to the "dataframe" iframe
            driver.switchTo().frame("dataframe");

            // Find the element with name "sname" and enter text
            WebElement snameElement = driver.findElement(By.xpath("//*[@name='sname']"));
            snameElement.sendKeys("Shikha Stage Vendor");

            // Locate the dropdown element
            WebElement dropdownElement = driver.findElement(By.id("svas"));

            // Create a Select object with the dropdown element
            Select dropdown = new Select(dropdownElement);

            // Select the value by visible text
            dropdown.selectByVisibleText("Approved");

            // Locate the dropdown element
            WebElement country = driver.findElement(By.id("country"));

            // Create a Select object with the dropdown element
            Select country_dropdown = new Select(country);

            // Select the value by visible text
            country_dropdown.selectByVisibleText("India");
            test.log(LogStatus.PASS, "Enter all the vendor details.");

            WebElement search_btn = driver.findElement(By.xpath("//*[@id='searchbtn']"));
            search_btn.click();
            test.log(LogStatus.PASS, "Click on the Search button");
            Thread.sleep(3000);

            // Applied Assertion
            String edit_user_page = driver.findElement(By.xpath("//*[@id=\"mainPage\"]/form/table[1]/tbody/tr[1]/td")).getText();
            String expectedTitle = "Supplier Search Result for Perfect City NN";
            Assert.assertEquals(edit_user_page, expectedTitle, "Page title does not match the expected title.");

            test.log(LogStatus.PASS, "Successfully got the desired search result.");
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
        logout();
    }

    @AfterClass
    public static void endTest() {
        report.endTest(test);
        report.flush();
    }
}

