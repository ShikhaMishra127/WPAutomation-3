package testcases.buyer.req;

import com.relevantcodes.extentreports.LogStatus;
import java.io.IOException;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageobjects.buyer.req.OffCatalogReqPOM;
import pageobjects.buyer.req.ProcessReqPOM;
import pageobjects.common.BuyerNavBarPOM;
import pageobjects.common.LoginPagePOM;
import utilities.common.Browser;
import utilities.common.ExtentReport;

public class CreateRequest {

    Browser browser;
    LoginPagePOM login;
    BuyerNavBarPOM navbar;
    OffCatalogReqPOM offrequest;
    ProcessReqPOM shoppingcart;
    ExtentReport testreport;

    public CreateRequest() throws IOException {

        browser = new Browser();
        login = new LoginPagePOM(browser);
        navbar = new BuyerNavBarPOM(browser);
        offrequest = new OffCatalogReqPOM(browser);
        shoppingcart = new ProcessReqPOM(browser);
        testreport = new ExtentReport(browser);
    }

    @BeforeClass
    public void setup() throws IOException {

        // before starting our tests, first log into the system as a buyer
            testreport.logger = ExtentReport.report.startTest(this.getClass().getSimpleName());
            testreport.logger.log(LogStatus.INFO, "Test Case Started");
            login.loginAsBuyer();
            testreport.logger.log(LogStatus.PASS, "Logged in Successful");

    }

    @Test()
    public void createOffCatRequest() {

        navbar.selectTopNavDropDown("Request");
        navbar.requestdropdown("Create new");
        Assert.assertTrue(navbar.getTitle().contains("WebProcure: Request And Workflow"));
        navbar.typesofreqlist("Off-Catalog Request");
        offrequest.addItemToOffCatReq();
        shoppingcart.getRequestName();
        shoppingcart.viewcart();
        shoppingcart.submitRequest();
        Assert.assertEquals(shoppingcart.reqConfirmationMsg(), "Request successfully submitted.");
        testreport.logger.log(LogStatus.PASS, "Created off-catalog request successfully");
    }

    @AfterClass
    public void teardown() {
        navbar.logout();
        testreport.report.endTest(ExtentReport.logger);
        testreport.report.flush();
        // login.close();
    }



}

