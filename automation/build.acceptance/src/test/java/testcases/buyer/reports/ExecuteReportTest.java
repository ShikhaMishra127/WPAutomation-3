package testcases.buyer.reports;

import org.junit.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageobjects.buyer.report.ExecuteReportPOM;
import pageobjects.common.BuyerNavBarPOM;
import pageobjects.common.LoginPagePOM;
import utilities.common.Browser;
import utilities.common.ResourceLoader;
import utilities.common.TestRailListener;
import utilities.common.TestRailReference;

@Listeners({TestRailListener.class})

public class ExecuteReportTest {

    Browser browser;
    ExecuteReportPOM reports;
    ResourceLoader resource;
    LoginPagePOM login;
    BuyerNavBarPOM navbar;
    private String reportName;
    private String reportTitle;
    private String reportFromDate;
    private String reportToDate;
    private String reportSection;

    public ExecuteReportTest() {

    }

    @BeforeClass
    public void setup(ITestContext testContext) {

        resource = new ResourceLoader("data/report");
        browser = new Browser(testContext);
        reports = new ExecuteReportPOM(browser);
        login = new LoginPagePOM(browser);
        navbar = new BuyerNavBarPOM(browser);

        reportSection = resource.getValue("report_section");
        reportName = resource.getValue("report_name");
        reportTitle = resource.getValue("report_title");
        reportFromDate = resource.getValue("report_fromDate");
        reportToDate = resource.getValue("report_toDate");

        browser.getDriver().get(browser.baseUrl);

        login.loginAsBuyer();
    }

    @AfterClass
    public void tearDown() {
        browser.close();
    }

    @Test(priority = 1)
    @TestRailReference(id=3606)
    public void GoToReportTest() {
        browser.Log("Buyer Logged in OK");

        // Navigate to Execute Reports
        navbar.selectDropDownItem("Analytics", "Execute Reports");

        // Select report specified in report.properties
        reports.selectReportByName(reportSection, reportName);

        // verify the report title
        Assert.assertTrue("Verify Report Title Header", reports.reportParameterHeader.getText().contains(reportName));

    }

    @Test(priority = 2)
    public void SetReportParametersTest() {

        // Add the report title to edit box
        reports.reportTitleEdit.sendKeys(reportTitle);

        // check "Select all Organizations" if it hasn't been checked already
        if (!reports.reportAllBorgsCheckbox.isSelected()) {
            reports.reportAllBorgsCheckbox.click();
        }

        // Add report "From" and "To" dates
        browser.InjectJavaScript("arguments[0].value=arguments[1]", reports.reportFromDate, reportFromDate);
        browser.InjectJavaScript("arguments[0].value=arguments[1]", reports.reportToDate, reportToDate);

        // check "Select all Statuses" if it hasn't been checked already
        if (!reports.reportAllStatusCheckbox.isSelected()) {
            reports.reportAllStatusCheckbox.click();
        }

        // launch report
        reports.submitButton.click();
    }

    @Test(priority = 3)
    @TestRailReference(id=3599)
    public void ReviewHTMLReportTest() {
        //Wait for popup and switch focus
        browser.waitForPopUpToOpen();

        // set focus to report details
        String parentWindow = browser.driver.getWindowHandle();
        browser.SwitchToPopUp(parentWindow);
        browser.waitForElementToAppear(reports.HTMLReportHeader);

        // verify the HTML pop-up report title
        Assert.assertTrue("Verify Report Pop-up Name Header", reports.HTMLReportHeader.getText().contains(reportName.toUpperCase()) );
        Assert.assertTrue("Verify Report Pop-up Title Header", reports.HTMLReportSubHeader.getText().contains(reportTitle) );

        browser.Log("Verified report '"+ reportName + "' runs.");

        // close pop-up and return to parent window
        browser.ClosePopUp(parentWindow);
      
        browser.clickWhenAvailable(reports.backButton);
    }

    @Test(priority = 4)
    @TestRailReference(id=3607)
    public void LogOutTest() {
        navbar.logout();
        browser.Log("Buyer Logged out OK");
    }
}
