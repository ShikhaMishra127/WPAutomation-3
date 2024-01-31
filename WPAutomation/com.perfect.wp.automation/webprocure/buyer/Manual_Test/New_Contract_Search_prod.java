package buyer.Manual_Test;

import com.relevantcodes.extentreports.LogStatus;
import commonutils.pageobjects.utils.BaseClassProd;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static ExtentReporters.getScreenShot.capture;
import static commonutils.pageobjects.utils.ExtentReport.report;

public class New_Contract_Search_prod extends BaseClassProd {

    @BeforeClass
    public static void startTest() {
        report = new com.relevantcodes.extentreports.ExtentReports(System.getProperty("user.dir") + "\\ExtentReportResults.html");
        test = report.startTest("Contracts Audit Testcases");
    }

    @BeforeMethod
    public void login_App() throws InterruptedException {
        Buyer_login();
        test.log(LogStatus.PASS, "Successfully Login");

    }

    @Test
    public void new_contract_search_prod() throws InterruptedException, IOException {

        try {
            driver.manage().timeouts().implicitlyWait(12, TimeUnit.SECONDS);
            test.log(LogStatus.PASS, "Start creating contract");
            create_contract_prod();

            test.log(LogStatus.PASS, "contract is successfully get created");
            // Go to contract search screen

            WebElement contract_module = driver.findElement(By.xpath("//a[@title='Contracts']"));
            contract_module.click();

            test.log(LogStatus.PASS, "Go to the new contract search page and search the created contract.");
//
//        // Go to Contract search page.
            WebElement contract_search = driver.findElement(By.xpath("//a[text()='Contract Search ']"));
            contract_search.click();

            //Paste the copied contract number

            WebElement contract_searchbox = driver.findElement(By.xpath("//*[@id=\"input-search-box\"]"));
            // Create an Actions object
            Actions actions2 = new Actions(driver);
            // Click inside the textbox to focus on it
            contract_searchbox.click();
            // Use keyboard shortcuts to paste the text
            actions2.keyDown(Keys.CONTROL).sendKeys("v").keyUp(Keys.CONTROL).perform();


            Thread.sleep(2000);

            // Click on the Search button
            WebElement search_btn = driver.findElement(By.xpath("//*[@id=\"wrapper\"]/div[1]/div[1]/div/div/div[4]/div[4]/button[1]"));
            search_btn.click();
            Thread.sleep(2000);


            test.log(LogStatus.PASS, "Click on the searched contract action menu and go to its contract history page.");

            driver.findElement(By.xpath("//*[@id=\"search-results\"]/div[2]/div/div[2]/div[3]/div[1]/div/button/i")).click();
            driver.findElement(By.xpath("//*[@id=\"search-results\"]/div[2]/div/div[2]/div[3]/div[1]/div/ul/li[8]/a")).click();

            Thread.sleep(2000);

            test.log(LogStatus.PASS, "Contract Audit record page successfully appears.");
            test.log(LogStatus.INFO, test.addScreenCapture(capture(driver)) + "For clarifications - Please refer to the" +
                    " final ScreenShot...!!");

            //Return to the contract new search page

            driver.findElement(By.xpath("//*[@id=\"cont-requirement-content\"]/form/div[2]/div/div/button")).click();

            // Reset All applied filters
            test.log(LogStatus.PASS, "Reset All the applied filters and select some new filters like status, contract" +
                    " " + "type and commodities.");


            // Reset All
            driver.findElement(By.xpath("//*[@id=\"wrapper\"]/div[1]/div[1]/div/div/div[4]/div[4]/button[2]")).click();
            Thread.sleep(2000);
            // Make Contract Admin to 'All'
            Select Contract_Admin = new Select(driver.findElement(By.xpath("//*[@id=\"ca\"]/select")));
            Contract_Admin.selectByVisibleText("All");
            Thread.sleep(2000);
            // Make Organization to 'Perfect City NN'
            Select organization = new Select(driver.findElement(By.xpath("//*[@id=\"co\"]/select")));
            organization.selectByVisibleText("All");
            Thread.sleep(2000);
            // Make Authorized Organization to 'All'
            Select Authorized_organization = new Select(driver.findElement(By.xpath("//*[@id=\"ao\"]/select")));
            Authorized_organization.selectByVisibleText("All");
            Thread.sleep(2000);


            // Click on the Relevance (Apply sorting)
             driver.findElement(By.xpath("//*[@id=\"search-results\"]/div[2]/div/div[1]/div[3]/div/button")).click();

             driver.findElement(By.xpath("//*[@id=\"search-results\"]/div[2]/div/div[1]/div[3]/div/ul/li[4]/a")).click();
            Thread.sleep(2000);

            driver.findElement(By.xpath("//*[@id=\"search-results\"]/div[2]/div/div[1]/div[3]/div/button")).click();

            driver.findElement(By.xpath("//*[@id=\"search-results\"]/div[2]/div/div[1]/div[3]/div/ul/li[6]/a")).click();
            Thread.sleep(2000);

            driver.findElement(By.xpath("//*[@id=\"search-results\"]/div[2]/div/div[1]/div[3]/div/button")).click();

            driver.findElement(By.xpath("//*[@id=\"search-results\"]/div[2]/div/div[1]/div[3]/div/ul/li[9]/a")).click();
            Thread.sleep(2000);

            driver.findElement(By.xpath("//*[@id=\"search-results\"]/div[2]/div/div[1]/div[3]/div/button")).click();

            driver.findElement(By.xpath("//*[@id=\"search-results\"]/div[2]/div/div[1]/div[3]/div/ul/li[12]/a")).click();
            Thread.sleep(2000);



            // Select Status
            driver.findElement(By.xpath("//*[@id=\"status-1\"]")).click();

            Thread.sleep(2000);

            // Select Commodities
            driver.findElement(By.xpath("//*[@id=\"commodity-0\"]")).click();
            Thread.sleep(2000);
            driver.findElement(By.xpath("//*[@id=\"status-2\"]")).click();
            Thread.sleep(2000);

            test.log(LogStatus.PASS, "Click on the Apply button and verify that the all the applied filters should be" +
                    " successfully get implemented");

            // Click on the Apply button
            driver.findElement(By.xpath("//*[@id=\"search-results\"]/div[1]/div[2]/div/button")).click();

            Thread.sleep(2000);
            test.log(LogStatus.INFO, test.addScreenCapture(capture(driver)) + "For clarifications - Please refer to the" +
                    " final ScreenShot...!!");


        } catch (Exception e) {
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
