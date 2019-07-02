package testcases.buyer.reports;

import org.junit.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageobjects.buyer.report.CreateReportPOM;
import pageobjects.common.BuyerNavBarPOM;
import pageobjects.common.LoginPagePOM;
import utilities.common.Browser;
import utilities.common.ResourceLoader;
import utilities.common.TestRail;

import java.io.IOException;

public class CreateReportTest {

    Browser browser;
    ResourceLoader resource;
    CreateReportPOM reports;
    LoginPagePOM login;
    BuyerNavBarPOM navbar;
    TestRail tRail;
    String reportName;

    public CreateReportTest() throws IOException {

    }

    @BeforeClass
    public void setup() throws IOException {

        resource = new ResourceLoader("data/report");
        browser = new Browser();
        reports = new CreateReportPOM(browser);
        login = new LoginPagePOM(browser);
        navbar = new BuyerNavBarPOM(browser);
        tRail = new TestRail();

        reportName = resource.getValue("create_report_name");

        browser.getDriver().get(browser.baseUrl);

        login.loginAsBuyer();
    }

    @AfterClass
    public void tearDown() {
        navbar.logout();
        browser.close();
    }

    @Test(priority = 1)
    public void GoToReportTest() {

        // Navigate to Execute Reports
        navbar.selectDropDownItem("Analytics", "Create Report");

        // verify the page title
        Assert.assertTrue("Report Page Header OK", reports.createReportPageHeader.getText().contains("Create Reports"));

        // get a report from the list
        reports.selectReportByName( reportName );

        // set focus to Jasper frame to continue test
        browser.switchToFrame(reports.jasperFrame);
    }

    @Test(priority = 2)
    public void CreateReportPageTest() {

        // wait until the report loads
        WebDriverWait wait = new WebDriverWait(browser.getDriver(), 10);
        wait.until(ExpectedConditions.textToBePresentInElement(reports.jasperReportTitle, reportName));

        // Assert report was loaded into content area and preview button available
        Assert.assertTrue("Jasper Preview button OK", reports.jasperPreviewButton.getText().contains("Preview"));
        Assert.assertTrue("Report Title OK", reports.jasperReportTitle.getText().contains(reportName));

        // click on the preview button for fun
        reports.jasperPreviewButton.click();

        //step out of iFrame
        browser.switchTo().parentFrame();

        tRail.UpdateTestcase("3599", TestRail.Status.PASSED, "Verified report "+reportName+ "runs.");

    }
}
