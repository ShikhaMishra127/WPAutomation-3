package testcases.buyer.reports;

import org.junit.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageobjects.buyer.report.ExecuteReportPOM;
import pageobjects.common.BuyerNavBarPOM;
import pageobjects.common.LoginPagePOM;
import utilities.common.Browser;
import utilities.common.ResourceLoader;

import java.io.IOException;

public class RunReportTest {

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

    public RunReportTest() throws IOException {

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


    @Test()
    public void ExecuteReportTest() {

        // Navigate to Execute Reports
        navbar.selectdropdownitem("Analytics","Execute Reports");

        // Select report specified in report.properties
        reports.selectReportByName(reportSection, reportName);

        // verify the report title
        Assert.assertTrue("Report Title Header OK", reports.reportParameterHeader.getText().contains(reportName) );

        // Add the report title to edit box
        reports.reportTitleEdit.sendKeys("Automation - " + reportTitle);

        // check "Select all Organizations" if it hasn't been checked already
        if (!reports.reportAllBorgsCheckbox.isSelected()) {
            reports.reportAllBorgsCheckbox.click();
        }

        // Add report "From" and "To" dates
        browser.InjectJavaScript("arguments[0].value=arguments[1]", reports.reportFromDate, reportFromDate );
        browser.InjectJavaScript("arguments[0].value=arguments[1]", reports.reportToDate, reportToDate );

        // check "Select all Statuses" if it hasn't been checked already
        if (!reports.reportAllStatusCheckbox.isSelected()) {
            reports.reportAllStatusCheckbox.click();
        }

        // launch report
        reports.submitButton.click();
    }

    @Test()
    public void ViewExecutedReportTest() {

    }
}
