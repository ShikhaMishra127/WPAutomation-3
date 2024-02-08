package buyer.Manual_Test;

import com.relevantcodes.extentreports.LogStatus;
import commonutils.pageobjects.utils.BaseClassProd;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static ExtentReporters.getScreenShot.capture;
import static commonutils.pageobjects.utils.ExtentReport.report;

public class Contracts_audit_prod extends BaseClassProd {

    @BeforeClass
    public static void startTest() {
        report = new com.relevantcodes.extentreports.ExtentReports(System.getProperty("user.dir") + "\\ExtentReportResults.html");
        test = report.startTest("Contracts Audit Testcases");

        String reportFilePath = "C:\\Users\\Shikha\\Desktop\\Test Reports\\Prod Reports\\24.1 Reports";
    }

    @BeforeMethod
    public void login_App() throws InterruptedException {
        Buyer_login();
        test.log(LogStatus.PASS, "Successfully Login");

    }
    @Test(priority = 1)
    public void contract_audit() throws InterruptedException, IOException {
        try {
            driver.manage().timeouts().implicitlyWait(12, TimeUnit.SECONDS);

            create_contract_Prod();

            test.log(LogStatus.PASS, "Created a contract");

            WebElement contract_searchbox = driver.findElement(By.xpath("//*[@id='contractNumber']"));
            // Create an Actions object
            Actions actions2 = new Actions(driver);
            // Click inside the textbox to focus on it
            contract_searchbox.click();
            // Use keyboard shortcuts to paste the text
            actions2.keyDown(Keys.CONTROL).sendKeys("v").keyUp(Keys.CONTROL).perform();

            test.log(LogStatus.PASS, "Go to its classic search page and search the created contract by contract number");

            Thread.sleep(2000);
            driver.findElement(By.xpath("//*[contains(text(),'Submit')][1]")).click();
            Thread.sleep(2000);
            driver.findElement(By.xpath("//*[@id=\"contTable\"]/tbody/tr/td[9]/span/button/img")).click();
            test.log(LogStatus.PASS, "Go to the contract History page to view the contract audits.");

            Thread.sleep(2000);
            driver.findElement(By.xpath("//*[@id=\"contTable\"]/tbody/tr[1]/td[9]/span/ul/li[8]/a")).click();
            Thread.sleep(2000);
            test.log(LogStatus.INFO, test.addScreenCapture(capture(driver)) + "For clarifications - Please refer to the" +
                    " final ScreenShot...!!");
        }
        catch (Exception e){
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


