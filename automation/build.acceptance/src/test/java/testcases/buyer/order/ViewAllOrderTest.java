package testcases.buyer.order;

import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageobjects.buyer.orders.ViewAllOrderPOM;
import pageobjects.buyer.req.ProcessReqPOM;
import pageobjects.common.BuyerNavBarPOM;
import pageobjects.common.LoginPagePOM;
import utilities.common.Browser;
import utilities.common.DatePicker;
import utilities.common.ExtentReport;
import utilities.common.ResourceLoader;

import java.io.IOException;

public class ViewAllOrderTest {

    public ResourceLoader orderdata = new ResourceLoader("data/order");
    Browser browser;
    LoginPagePOM login;
    BuyerNavBarPOM navbar;
    ViewAllOrderPOM viewall;
    ProcessReqPOM shoppingcart;
    ExtentReport testreport;

    public ViewAllOrderTest() { }

    @BeforeClass
    public void setup() {
        browser = new Browser();
        login = new LoginPagePOM(browser);
        navbar = new BuyerNavBarPOM(browser);
        viewall = new ViewAllOrderPOM(browser);
        shoppingcart = new ProcessReqPOM(browser);
        testreport = new ExtentReport(browser);
        // before starting our tests, first log into the system as a buyer

            testreport.logger = ExtentReport.report.startTest(this.getClass().getSimpleName());
            testreport.logger.log(LogStatus.INFO, "Test Case Started");
            browser.getDriver().get(browser.baseUrl);
            login.loginAsBuyer();
            testreport.logger.log(LogStatus.PASS, "Logged in Successful");

    }


    @Test
    public void viewallOrder() {
        browser.waitForPageLoad();
        navbar.selectTopNavDropDown("Order");
        navbar.requestdropdown("View All");
        testreport.logger.log(LogStatus.PASS, "Redirected to View All Req Successful");
    }


    @Test(priority = 1)
    public void byBuyerStatus() {

        viewall.filterbyBuyerStatus(orderdata.getValue("BuyerStatus"));
        viewall.clickReset();
        testreport.logger.log(LogStatus.PASS, "Filtered By Buyer Status Successfull");
    }

    @Test(priority = 2)
    public void byBuyer() {
        viewall.filterbyBuyer(orderdata.getValue("Buyer"));
        viewall.clickReset();
    }

    @Test(priority = 3)
    public void byRequester() {
        viewall.filterbyRequester(orderdata.getValue("Requester"));
        viewall.clickReset();
    }

    @Test(priority = 4)
    public void byOrganization() {
        viewall.filterbyOrganization(orderdata.getValue("Organization"));
        viewall.clickReset();

    }

    @Test(priority = 5)
    public void byOrderNumber() {
        viewall.filterbyOrderNumber(orderdata.getValue("OrderNumber"));
        viewall.clickReset();
    }

    @Test(priority = 6)
    public void byOrderName() {
        viewall.filterbyOrderName(orderdata.getValue("OrderName"));
        viewall.clickReset();
    }

    @Test(priority = 7)
    public void bySupplier() {
        viewall.filterbySupplier(orderdata.getValue("Supplier"));
        viewall.clickReset();
    }

    @Test(priority = 8)
    public void byTransmissionStatus() {
        viewall.filterbyTransmissionStatus(orderdata.getValue("TransmissionStatus"));
        viewall.clickReset();
    }

    @Test(priority = 9)
    public void byDate() {
        viewall.setFromDate(DatePicker.getPastDate());
        viewall.setToDate(DatePicker.getCurrentDate());
        viewall.clickSubmit();
        viewall.createDateAssertion();
        viewall.clickReset();
        testreport.logger.log(LogStatus.PASS, "Filtered By Date Successfull");
    }


    @AfterClass
    public void teardown() {
        navbar.logout();
        testreport.report.endTest(ExtentReport.logger);
        testreport.report.flush();
        browser.close();
    }
}
