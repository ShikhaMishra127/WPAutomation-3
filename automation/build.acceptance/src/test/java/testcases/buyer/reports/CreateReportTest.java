package testcases.buyer.reports;

import org.junit.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageobjects.buyer.report.CreateReportPOM;
import pageobjects.buyer.common.BuyerNavBarPOM;
import pageobjects.common.LoginPagePOM;
import utilities.common.Browser;
import utilities.common.ResourceLoader;
import utilities.common.TestRailReference;

public class CreateReportTest {

    Browser browser;
    ResourceLoader resource;
    CreateReportPOM reports;
    LoginPagePOM login;
    BuyerNavBarPOM navbar;
    String reportName;

    public CreateReportTest() {

    }

    @BeforeClass
    public void setup(ITestContext testContext) {

        resource = new ResourceLoader("data/report");
        browser = new Browser(testContext);
        reports = new CreateReportPOM(browser);
        login = new LoginPagePOM(browser);
        navbar = new BuyerNavBarPOM(browser);

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
    @TestRailReference(id=3523)
    public void GoToReportTest() {

        // Navigate to Execute Reports
        navbar.selectDropDownItem("Analytics", "Create Report");

        // verify the page title
        browser.Assert("Verify Report Page Header", reports.createReportPageHeader.getText(), "Create Reports");

        // get a report from the list
        reports.selectReportByName( reportName );

        // set focus to Jasper frame to continue test
        browser.switchToFrame(reports.jasperFrame);
    }

    @Test(priority = 2)
    @TestRailReference(id=3523)
    public void CreateReportPageTest() {

        // wait until the report loads
        WebDriverWait wait = new WebDriverWait(browser.getDriver(), 10);
        wait.until(ExpectedConditions.textToBePresentInElement(reports.jasperReportTitle, reportName));

        browser.waitForElementToAppear(reports.jasperReportTitle);

        // Assert report was loaded into content area and preview button available
        browser.Assert("Verify Jasper Preview button", reports.jasperPreviewButton.getText(), "Preview");
        browser.Assert("Verify Report Title", reports.jasperReportTitle.getText(), reportName);

        // click on the preview button for fun
        reports.jasperPreviewButton.click();

        //step out of iFrame
        browser.switchTo().parentFrame();

        browser.Log("Verified report '"+ reportName + "' runs.");
    }
}
