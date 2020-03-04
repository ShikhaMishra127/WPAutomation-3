package api;

import framework.Request;
import org.openqa.selenium.WebElement;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageobjects.buyer.common.BuyerNavBarPOM;
import pageobjects.buyer.orders.ViewOrderPOM;
import pageobjects.buyer.req.ReqCreator;
import pageobjects.buyer.req.ViewReqPOM;
import pageobjects.common.LoginPagePOM;
import utilities.common.Browser;
import utilities.common.ResourceLoader;
import utilities.common.TestRailReference;
import utilities.restbuilder.ERPInput;

import java.util.Map;

import static pageobjects.buyer.orders.ViewOrderPOM.POListColumn;
import static pageobjects.buyer.req.ViewReqPOM.ReqListColumn;

public class MOIntegrationTest {

    Request request;
    Request newrequest;
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
        browser.Assert("Verify New Request Status", request.getReqStatus(), resource.getValue("erp_pending"));

        // Send ERP Response for Request to REJECT and send
        ERPInput reqObj = new ERPInput(request.getReqNumber(), "REQUEST", "0");
        reqObj.Send();

        browser.sendKeysWhenAvailable(view.filterReqNumEdit, request.getReqNumber());
        browser.clickWhenAvailable(view.filterSubmitButton);

        reqLine = view.getElementsForReqLine(request.getReqName());

        // The Request status set to Rejected
        browser.Assert("Verify REJECT Status", reqLine.get(ReqListColumn.STATUS).getText(), resource.getValue("erp_req_rejected"));

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
        browser.Assert("Verify ACCEPT Status", reqLine.get(ReqListColumn.STATUS).getText(), resource.getValue("erp_req_accepted"));
        browser.Assert("Verify PO Created Status", reqLine.get(ReqListColumn.STATUS).getText(), resource.getValue("erp_req_pocreated"));

        // look up PO number in summary and save for later
        browser.clickSubElement(reqLine.get(ReqListColumn.EXPAND), view.riDownArrow);
        browser.waitForElementToAppear(view.riPONumber);

        request.setReqPONumber(view.riPONumber.getText().trim());

        navbar.logout();
        browser.close();
    }

    @Test(enabled = true, dependsOnMethods = "RequestResponseTest")
    @TestRailReference(id = 5560)
    public void POResponseTest(ITestContext testContext) {

        Browser browser = new Browser(testContext);
        LoginPagePOM login = new LoginPagePOM(browser);
        BuyerNavBarPOM navbar = new BuyerNavBarPOM(browser);
        ViewReqPOM reqview = new ViewReqPOM(browser);
        ViewOrderPOM poview = new ViewOrderPOM(browser);

        // log in and go to view target PO
        browser.getDriver().get(browser.baseUrl);
        login.loginAsUser(resource.getValue("req_buyer_username"), resource.getValue("req_buyer_password"));

        // Go to Requests > Order > View All
        navbar.selectDropDownItem(resource.getValue("navbar_poheaditem"), resource.getValue("navbar_viewpo"));

        // search for target PO
        browser.sendKeysWhenAvailable(poview.mainSearchOrderNumberEdit, request.getReqPONumber());
        browser.clickWhenAvailable(poview.mainSearchSubmitButton);

        Map<ViewOrderPOM.POListColumn, WebElement> poLine = poview.getElementsForPOLine(request.getReqPONumber());

        // verify PO is created and ready to be received
        String status = poLine.get(POListColumn.STATUS).getText();
        System.out.println("Status: " + status);

        // Verify PO Status set to Financial System Pending
        browser.Assert("Verify PO Approved", status, resource.getValue("erp_pending"));

        // Send ERP Response for Request to REJECT and send
        ERPInput POObj = new ERPInput(request.getReqPONumber(), "PO", "0");
        POObj.Send();

        browser.sendKeysWhenAvailable(poview.mainSearchOrderNumberEdit, request.getReqPONumber());
        browser.clickWhenAvailable(poview.mainSearchSubmitButton);

        poLine = poview.getElementsForPOLine(request.getReqPONumber());

        // Verify PO Status set to Financial System Rejected
        browser.Assert("Verify PO Rejected", poLine.get(POListColumn.STATUS).getText(), resource.getValue("erp_po_rejected"));

        poLine = poview.getElementsForPOLine(request.getReqPONumber());

        // a new req is generated after failure. Get the new ReqNumber so we can continue
        // req number is buried in the PO history
        browser.clickWhenAvailable(poLine.get(POListColumn.ACTION));
        browser.clickSubElement(poLine.get(POListColumn.ACTION), poview.piActionPOHistory);

        browser.clickWhenAvailable(poview.phEllipsis);
        browser.clickSubElement(poview.phEllipsis, poview.phHistory);

        // create storage for new request info
        newrequest = new Request();
        newrequest.setReqNumber(poview.getReqNumberFromPOHistory(request.getReqPONumber()));

        // look up the req that was created on PO failure
        navbar.selectDropDownItem(resource.getValue("navbar_reqheaditem"), "View All Requests");

        browser.sendKeysWhenAvailable(reqview.filterReqNumEdit, newrequest.getReqNumber());
        browser.clickWhenAvailable(reqview.filterSubmitButton);

        Map<ViewReqPOM.ReqListColumn, WebElement> reqLine = reqview.getElementsForReqLine(newrequest.getReqNumber());

        request.setReqStatus(reqLine.get(ReqListColumn.STATUS).getText());

        // click on Action bar, edit the newly created Req and submit
        browser.clickSubElement(reqLine.get(ReqListColumn.ACTION), reqview.riEllipsis);
        browser.clickSubElement(reqLine.get(ReqListColumn.ACTION), reqview.riEdit);

        ReqCreator edit = new ReqCreator(browser, resource);
        edit.reviewSubmitReq();

        // Send ERP Response for Request to ACCEPT and send
        ERPInput reqObj = new ERPInput(newrequest.getReqNumber(), "REQUEST", "1");
        reqObj.Send();

        browser.clickWhenAvailable(reqview.filterSubmitButton);
        reqLine = reqview.getElementsForReqLine(newrequest.getReqNumber());

        // expand req data and get PO number generated (For further tests)
        browser.clickSubElement(reqLine.get(ReqListColumn.EXPAND), reqview.riDownArrow);
        browser.waitForElementToAppear(reqview.riPONumber);

        newrequest.setReqPONumber(reqview.riPONumber.getText().trim());

        // Find the newly created PO and send an ERP response OF Accepted
        navbar.selectDropDownItem(resource.getValue("navbar_poheaditem"), "View All Orders");

        ERPInput newPOObj = new ERPInput(newrequest.getReqPONumber(), "PO", "1");
        newPOObj.Send();

        // Search for target PO and verify PO Status set to Financial System Accepted
        browser.sendKeysWhenAvailable(poview.mainSearchOrderNumberEdit, newrequest.getReqPONumber());
        browser.clickWhenAvailable(poview.mainSearchSubmitButton);

        poLine = poview.getElementsForPOLine(newrequest.getReqPONumber());

        browser.Assert("Verify new PO Accepted", poLine.get(POListColumn.STATUS).getText(), resource.getValue("erp_po_accepted"));

        navbar.logout();
        browser.close();
    }

}
