package buyer.Manual_Test;

import com.relevantcodes.extentreports.LogStatus;
import commonutils.pageobjects.utils.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static commonutils.pageobjects.utils.ExtentReport.report;

public class Contracts_Audits extends BaseClass {



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

    @Test(priority = 1)
    public void contract_audit() throws InterruptedException, IOException{
        driver.manage().timeouts().implicitlyWait(12, TimeUnit.SECONDS);
        create_contract();


        WebElement contract_searchbox = driver.findElement(By.xpath("//*[@id='contractNumber']"));
        // Create an Actions object
        Actions actions2 = new Actions(driver);
        // Click inside the textbox to focus on it
        contract_searchbox.click();
        // Use keyboard shortcuts to paste the text
        actions2.keyDown(Keys.CONTROL).sendKeys("v").keyUp(Keys.CONTROL).perform();

        driver.findElement(By.xpath("//*[contains(text(),'Submit')][1]"));

        driver.findElement(By.xpath("//*[@id=\"contTable\"]/tbody/tr/td[9]/span/button/img")).click();

        driver.findElement(By.xpath("//*[@id=\"contTable\"]/tbody/tr[1]/td[9]/span/ul/li[8]/a")).click();


    }
}
