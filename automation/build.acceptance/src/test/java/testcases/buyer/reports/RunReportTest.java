package testcases.buyer.reports;


import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageobjects.bidboard.ContractBidboardPOM;
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


    public RunReportTest() throws IOException {

    }

    @BeforeClass
    public void setup() throws IOException {

        resource = new ResourceLoader("data/report");
        browser = new Browser();
        reports = new ExecuteReportPOM(browser);
        login = new LoginPagePOM(browser);
        navbar = new BuyerNavBarPOM(browser);

        browser.getDriver().get(browser.baseUrl);

        login.loginAsBuyer();
    }


    @Test()
    public void GoToExecuteReportsTest() {

        navbar.selectdropdownitem("Analytics","Execute Reports");
        reports.selectReportByName("Purchase Orders", "Purchase Orders By Organization");
    }

}
