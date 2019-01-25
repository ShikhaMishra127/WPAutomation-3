package testcases.buyer.reports;

import org.junit.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageobjects.buyer.report.ExecuteReportPOM;
import pageobjects.common.BuyerNavBarPOM;
import pageobjects.common.LoginPagePOM;
import utilities.common.Browser;
import utilities.common.ResourceLoader;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

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

    public ExecuteReportTest() throws IOException {

    }

    @BeforeClass
    public void setup() throws IOException {

        resource = new ResourceLoader("data/report");
        browser = new Browser();
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
        navbar.logout();
        browser.close();
    }

    @Test(priority = 1)
    public void GoToReportTest() {

        // Navigate to Execute Reports
        navbar.selectdropdownitem("Analytics", "Execute Reports");

        // Select report specified in report.properties
        reports.selectReportByName(reportSection, reportName);

        // verify the report title
        Assert.assertTrue("Report Title Header OK", reports.reportParameterHeader.getText().contains(reportName));
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
    public void ReviewHTMLReportTest() {

        // set focus to report details
        String parentWindow = browser.driver.getWindowHandle();
        browser.SwitchToPopUp(parentWindow);
        browser.waitForElementToAppear(reports.HTMLReportHeader);
        // verify the HTML pop-up report title
        Assert.assertTrue("Report Pop-up Name Header OK", reports.HTMLReportHeader.getText().contains(reportName.toUpperCase()) );
        Assert.assertTrue("Report Pop-up Title Header OK", reports.HTMLReportSubHeader.getText().contains(reportTitle) );

        // close pop-up and return to parent window
        browser.ClosePopUp(parentWindow);

        reports.backButton.click();
    }
}
