package api;

import framework.Request;
import org.openqa.selenium.WebElement;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageobjects.buyer.common.BuyerNavBarPOM;
import pageobjects.buyer.req.ViewReqPOM;
import pageobjects.common.LoginPagePOM;
import utilities.common.Browser;
import utilities.common.ResourceLoader;
import utilities.common.TestRailReference;
import utilities.restbuilder.ERPInput;

import java.util.Map;

import static pageobjects.buyer.req.ViewReqPOM.ReqListColumn;

public class MOIntegrationTest {

    Request request;
    ResourceLoader resource;

    @BeforeClass
    public void setup() {

        request = new Request();
        resource = new ResourceLoader("data/api");
    }
/* TEMP - REMOVE ME
    @Test
    public void CreateRequestTest(ITestContext testContext) {

        Browser browser = new Browser(testContext);
        ReqCreator creator = new ReqCreator();

        request = creator.CreateRequest(browser, resource);
                browser.Log(request.toString());
    }
*/

    @Test
    @TestRailReference(id = 5559)
//    @Test(enabled = true, dependsOnMethods = {"CreateRequestTest"})
    public void RejectRequestTest(ITestContext testContext) {

        Browser browser = new Browser(testContext);
        LoginPagePOM login = new LoginPagePOM(browser);
        BuyerNavBarPOM navbar = new BuyerNavBarPOM(browser);
        ViewReqPOM view = new ViewReqPOM(browser);

        request.setReqNumber("402-7");
        request.setReqName("Automation Lipinski/402-7");

        browser.getDriver().get(browser.baseUrl);

        login.loginAsUser(resource.getValue("req_buyer_username"), resource.getValue("req_buyer_password"));

        navbar.selectDropDownItem(resource.getValue("navbar_reqheaditem"), resource.getValue("navbar_viewreq"));

        browser.sendKeysWhenAvailable(view.filterReqNumEdit, request.getReqNumber());
        browser.clickWhenAvailable(view.filterSubmitButton);

        Map<ViewReqPOM.ReqListColumn, WebElement> reqLine = view.getElementsForReqLine(request.getReqName());
        request.setReqStatus(reqLine.get(ReqListColumn.STATUS).getText());

        browser.Log("NEWLY CREATED: " + reqLine.get(ReqListColumn.STATUS).getText());
       // browser.Assert("Verify Initial Status", request.getReqStatus(), "Financial System Pending" );

        ERPInput reqObj = new ERPInput( request.getReqNumber(), "REQUEST", "0");
        reqObj.Send();

        browser.sendKeysWhenAvailable(view.filterReqNumEdit, request.getReqNumber());
        browser.clickWhenAvailable(view.filterSubmitButton);

        reqLine = view.getElementsForReqLine(request.getReqName());

        browser.Log("AFTER REJECT: " + reqLine.get(ReqListColumn.STATUS).getText());


    }
}
