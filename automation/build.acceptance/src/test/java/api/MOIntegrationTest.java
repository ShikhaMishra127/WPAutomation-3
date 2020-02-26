package api;

import framework.Request;
import org.openqa.selenium.WebElement;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageobjects.buyer.common.BuyerNavBarPOM;
import pageobjects.buyer.req.ReqCreator;
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

    @Test
    public void CreateRequestTest(ITestContext testContext) {

        Browser browser = new Browser(testContext);
        ReqCreator creator = new ReqCreator(browser, resource);

        // Go to create a Request > Fill in all required fields and submit the Request
        request = creator.CreateRequest();
        browser.Log(request.toString());
    }

    @Test(enabled = true, dependsOnMethods = "CreateRequestTest")
    @TestRailReference(id = 5559)
    public void RequestResponseTest(ITestContext testContext) {

        Browser browser = new Browser(testContext);
        LoginPagePOM login = new LoginPagePOM(browser);
        BuyerNavBarPOM navbar = new BuyerNavBarPOM(browser);
        ViewReqPOM view = new ViewReqPOM(browser);

        // log in and go to view target request
        browser.getDriver().get(browser.baseUrl);
        login.loginAsUser(resource.getValue("req_buyer_username"), resource.getValue("req_buyer_password"));
        navbar.selectDropDownItem(resource.getValue("navbar_reqheaditem"), resource.getValue("navbar_viewreq"));

        browser.sendKeysWhenAvailable(view.filterReqNumEdit, request.getReqNumber());
        browser.clickWhenAvailable(view.filterSubmitButton);

        Map<ViewReqPOM.ReqListColumn, WebElement> reqLine = view.getElementsForReqLine(request.getReqName());
        request.setReqStatus(reqLine.get(ReqListColumn.STATUS).getText());

        // Request status set to Financial System Pending
        browser.Assert("Verify New Request Status", request.getReqStatus(), "Financial System Pending");

        // Send ERP Response for Request to REJECT and send
        ERPInput reqObj = new ERPInput(request.getReqNumber(), "REQUEST", "0");
        reqObj.Send();

        browser.sendKeysWhenAvailable(view.filterReqNumEdit, request.getReqNumber());
        browser.clickWhenAvailable(view.filterSubmitButton);

        reqLine = view.getElementsForReqLine(request.getReqName());

        // The Request status set to Rejected
        browser.Assert("Verify REJECT Status",
                reqLine.get(ReqListColumn.STATUS).getText(), "Financial System Status : Rejected");

        // click on Action bar, then edit the rejected Req
        browser.clickSubElement(reqLine.get(ReqListColumn.ACTION), view.riEllipsis);
        browser.clickSubElement(reqLine.get(ReqListColumn.ACTION), view.riEdit);

        // resubmit the rejected Req, so that it is once again in Financial System Pending
        ReqCreator edit = new ReqCreator(browser, resource);
        edit.reviewSubmitReq();

        // Send ERP Response for Request to ACCEPT and send
        reqObj.Replace("success", "1");
        reqObj.Send();

        browser.sendKeysWhenAvailable(view.filterReqNumEdit, request.getReqNumber());
        browser.clickWhenAvailable(view.filterSubmitButton);

        reqLine = view.getElementsForReqLine(request.getReqName());

        // The Request status set to PO Created / Accepted
        browser.Assert("Verify ACCEPT Status",
                reqLine.get(ReqListColumn.STATUS).getText(), "Financial System Status : Accepted");
        browser.Assert("Verify PO Created Status",
                reqLine.get(ReqListColumn.STATUS).getText(), "PO Created");

        navbar.logout();
        browser.close();
    }
}
