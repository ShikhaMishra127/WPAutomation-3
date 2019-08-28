package testcases.buyer.req;

import framework.Request;
import junit.framework.Assert;
import org.openqa.selenium.WebElement;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageobjects.buyer.req.ViewReqPOM;
import pageobjects.common.BuyerNavBarPOM;
import pageobjects.common.LoginPagePOM;
import utilities.common.Browser;
import utilities.common.ResourceLoader;
import utilities.common.TestRailListener;
import utilities.common.TestRailReference;

import java.util.Map;

import static pageobjects.buyer.req.ViewReqPOM.ReqListColumn.*;

@Listeners({TestRailListener.class})

public class ReqFlowTest {

    Request request;
    ResourceLoader resource;

    @BeforeClass
    public void setup() {
        request = new Request();
    }


    @Test
    @TestRailReference(id=3597)
    public void CreateRequestTest(ITestContext testContext) {

        ReqCreator creator = new ReqCreator();
        request = creator.CreateRequest("data/req", testContext);
    }

    @Test(dependsOnMethods = {"CreateRequestTest"})
    @TestRailReference(id=3597)
    public void ViewRequestTest(ITestContext testContext) {

        Browser browser = new Browser(testContext);
        LoginPagePOM login = new LoginPagePOM(browser);
        BuyerNavBarPOM navbar = new BuyerNavBarPOM(browser);
        ViewReqPOM view = new ViewReqPOM(browser);

        resource = new ResourceLoader("data/req");
        browser.getDriver().get(browser.baseUrl);

        login.loginAsBuyer();

        // Go to Requests > Create New > Off-Catalog Request
        navbar.selectDropDownItem(resource.getValue("navbar_headitem"), resource.getValue("navbar_viewreq"));

        browser.sendKeysWhenAvailable(view.filterReqNumEdit, request.getReqNumber());
        browser.clickWhenAvailable(view.filterSubmitButton);

        Map<ViewReqPOM.ReqListColumn, WebElement> reqLine = view.getElementsForReqLine(request.getReqName());

        // make sure req number and status are ok
        Assert.assertTrue("Verify req number", reqLine.get(REQNUM).getText().contains(request.getReqNumber()));
        Assert.assertTrue("Verify req status", reqLine.get(STATUS).getText().contains("PO Created"));

        // expand req data and get PO number generated (For further tests)
        browser.clickSubElement(reqLine.get(EXPAND), view.riDownArrow);
        browser.waitForElementToAppear(view.riPONumber);
        request.setReqPONumber(view.riPONumber.getText());

        // make sure total cost is ok
        Assert.assertTrue("Verify req total", browser.getSubElement(view.reqTable, view.riReqTotal).getText().contains(request.getReqTotal()));

        browser.Log("PO " + request.getReqPONumber() + " created");
        browser.Log(request.getReqName() + " viewed as a buyer");

        navbar.logout();
        browser.close();
    }
}

