package testcases.buyer.req;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageobjects.buyer.req.OffCatalogReqPOM;
import pageobjects.buyer.req.ProcessReqPOM;
import pageobjects.common.BuyerNavBarPOM;
import pageobjects.common.LoginPagePOM;
import utilities.common.Browser;

import java.io.IOException;

public class CreateRequest {

    public CreateRequest() throws IOException {
        super();
    }


    LoginPagePOM login = new LoginPagePOM();
    BuyerNavBarPOM navbar = new BuyerNavBarPOM();
    OffCatalogReqPOM offrequest = new OffCatalogReqPOM();
    ProcessReqPOM shoppingcart = new ProcessReqPOM();

    @BeforeClass
    public void setup() {
        // before starting our tests, first log into the system as a buyer
        login.handleCookie();
        login.setUsername(Browser.buyerUsername);
        login.setPassword(Browser.buyerPassword);
        login.clickOnLogin();
    }

    @AfterClass
    public void teardown() {
        //navbar.logout();
        login.close();
    }

    @Test()
    public void clickNewReq() {

        navbar.selectTopNavDropDown("Request");
        navbar.requestdropdown("Create new");
        Assert.assertTrue(navbar.getTitle().contains("WebProcure: Request And Workflow"));
        navbar.typesofreqlist("Off-Catalog Request");
        offrequest.addItemToOffCatReq();
        shoppingcart.printRequestName();
        shoppingcart.viewcart();
        shoppingcart.submitRequest();
        Assert.assertEquals(shoppingcart.reqConfirmationMsg(), "Request successfully submitted.");
    }

}

