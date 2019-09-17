package testcases.buyer.req;

import framework.Request;
import junit.framework.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageobjects.buyer.orders.ReceiveOrderPOM;
import pageobjects.buyer.orders.ViewOrderPOM;
import pageobjects.buyer.req.NewReqPOM;
import pageobjects.buyer.req.ViewReqPOM;
import pageobjects.common.BuyerNavBarPOM;
import pageobjects.common.LoginPagePOM;
import utilities.common.Browser;
import utilities.common.ResourceLoader;
import utilities.common.TestRailListener;
import utilities.common.TestRailReference;

import java.time.format.DateTimeFormatter;
import java.util.Map;

import static pageobjects.buyer.orders.ViewOrderPOM.POListColumn;
import static pageobjects.buyer.req.ViewReqPOM.ReqListColumn;

@Listeners({TestRailListener.class})

public class ReqFlowTest {

    Request request;
    ResourceLoader resource;

    @BeforeClass
    public void setup() {

        request = new Request();
        resource = new ResourceLoader("data/req");
    }

    @Test
    @TestRailReference(id = 3597)
    public void CreateRequestTest(ITestContext testContext) {

        Browser browser = new Browser(testContext);
        ReqCreator creator = new ReqCreator();

        request = creator.CreateRequest(browser, resource);
    }

    @Test(enabled = true, dependsOnMethods = {"CreateRequestTest"})
    @TestRailReference(id = 3597)
    public void ViewRequestTest(ITestContext testContext) {

        Browser browser = new Browser(testContext);
        LoginPagePOM login = new LoginPagePOM(browser);
        BuyerNavBarPOM navbar = new BuyerNavBarPOM(browser);
        ViewReqPOM view = new ViewReqPOM(browser);

        navigateToReq(browser, login, navbar, view);

        Map<ViewReqPOM.ReqListColumn, WebElement> reqLine = view.getElementsForReqLine(request.getReqName());

        // make sure req number and status are ok
        Assert.assertTrue("Verify req number", reqLine.get(ReqListColumn.REQNUM).getText().contains(request.getReqNumber()));
        Assert.assertTrue("Verify req status", reqLine.get(ReqListColumn.STATUS).getText().contains("PO Created"));

        // expand req data and get PO number generated (For further tests)
        browser.clickSubElement(reqLine.get(ReqListColumn.EXPAND), view.riDownArrow);
        browser.waitForElementToAppear(view.riPONumber);
        request.setReqPONumber(view.riPONumber.getText());

        // make sure total cost is ok
//        Assert.assertTrue("Verify req total", browser.getSubElement(view.reqTable, view.riReqTotal).getText().contains(request.getReqTotal()));

        browser.Log("PO " + request.getReqPONumber() + " created");
        browser.Log(request.getReqName() + " viewed as a buyer");

        navbar.logout();
        browser.close();
    }

    @Test(enabled = true, dependsOnMethods = {"CreateRequestTest"})
    @TestRailReference(id = 3597)
    public void CopyRequestTest(ITestContext testContext) {

        Browser browser = new Browser(testContext);
        LoginPagePOM login = new LoginPagePOM(browser);
        BuyerNavBarPOM navbar = new BuyerNavBarPOM(browser);
        ViewReqPOM view = new ViewReqPOM(browser);
        NewReqPOM req = new NewReqPOM(browser);

        navigateToReq(browser, login, navbar, view);

        Map<ViewReqPOM.ReqListColumn, WebElement> reqLine = view.getElementsForReqLine(request.getReqName());

        // click on Action bar, then Copy Req item
        browser.clickSubElement(reqLine.get(ReqListColumn.ACTION), view.riEllipsis);
        browser.clickSubElement(reqLine.get(ReqListColumn.ACTION), view.riActionCopyReq);
/*
        THIS CODE WOULD WORK GREAT - IF THE FOOTER WASN'T MISSING WHEN IN AUTOMATION! unblock when working

        // wait for newly copied req to appear, then get the new req number
        browser.waitForElementToAppear(req.footerIFrame);
        browser.switchToFrame(req.footerIFrame);
        browser.waitForElementToAppear(req.reqNameEdit);

        String reqNum = req.reqNameEdit.getAttribute("value");
        reqNum = reqNum.substring(reqNum.indexOf("/")+1);
 */
        // go back to the main content area and click the Close button
        browser.driver.switchTo().defaultContent();
        browser.switchToFrame(req.reqIFrame);
        browser.clickWhenAvailable(req.vrCloseReqButton);
/*
        // go back to the list of reqs, find the newly copied one, and DELETE IT
        browser.sendKeysWhenAvailable(view.filterReqNumEdit, reqNum);
        browser.clickWhenAvailable(view.filterSubmitButton);

        browser.clickSubElement(reqLine.get(ACTION), view.riEllipsis);
        browser.clickSubElement(reqLine.get(ACTION), view.riActionDelReq);
*/
        browser.Log("Viewed copied request #" + request.getReqNumber());

        navbar.logout();
        browser.close();
    }

    @Test(enabled = true, dependsOnMethods = {"CreateRequestTest"})
    @TestRailReference(id = 3597)
    public void PrintRequestTest(ITestContext testContext) {

        Browser browser = new Browser(testContext);
        LoginPagePOM login = new LoginPagePOM(browser);
        BuyerNavBarPOM navbar = new BuyerNavBarPOM(browser);
        ViewReqPOM view = new ViewReqPOM(browser);

        navigateToReq(browser, login, navbar, view);

        Map<ViewReqPOM.ReqListColumn, WebElement> reqLine = view.getElementsForReqLine(request.getReqName());

        // click on Action bar, then Copy Req item
        browser.clickSubElement(reqLine.get(ReqListColumn.ACTION), view.riEllipsis);
        browser.clickSubElement(reqLine.get(ReqListColumn.ACTION), view.riActionPrint);

        // Wait for popup and switch focus
        browser.waitForPopUpToOpen();

        // set focus to report details
        String parentWindow = browser.driver.getWindowHandle();
        browser.SwitchToPopUp(parentWindow);

        browser.waitForElementToAppear(view.printReqHeader);

        // verify information on printed req
        Assert.assertTrue("Verify Print Request Name", view.printReqName.getText().contains(request.getReqName()));
        Assert.assertTrue("Verify Print Request Number", view.printReqNumber.getText().contains(request.getReqNumber()));
//        Assert.assertTrue("Verify Request Total", view.printReqBody.getText().contains(request.getReqTotal()));

        // close pop-up and return to parent window
        browser.ClosePopUp(parentWindow);

        browser.Log("Viewed printed request #" + request.getReqNumber());

        navbar.logout();
        browser.close();
    }

    @Test(enabled = true, dependsOnMethods = {"CreateRequestTest"})
    @TestRailReference(id = 3597)
    public void ViewRequestHistoryTest(ITestContext testContext) {

        Browser browser = new Browser(testContext);
        LoginPagePOM login = new LoginPagePOM(browser);
        BuyerNavBarPOM navbar = new BuyerNavBarPOM(browser);
        ViewReqPOM view = new ViewReqPOM(browser);

        navigateToReq(browser, login, navbar, view);

        Map<ViewReqPOM.ReqListColumn, WebElement> reqLine = view.getElementsForReqLine(request.getReqName());

        // click on Action bar, then Copy Req item
        browser.clickSubElement(reqLine.get(ReqListColumn.ACTION), view.riEllipsis);
        browser.clickSubElement(reqLine.get(ReqListColumn.ACTION), view.riActionHistory);

        browser.waitForElementToAppear(view.historyReqName);

        // verify information in req history
        Assert.assertTrue("Verify History Request Name", view.historyReqName.getText().contains(request.getReqName()));
        Assert.assertTrue("Verify History Request Number", view.historyReqNumber.getText().contains(request.getReqNumber()));

        // BUG WP-5071 - Req History does not show req total
        // Assert.assertTrue("Verify History Request Total", view.historyReqTotal.getText().contains(request.getReqTotal()));

        browser.Log("Viewed req history for #" + request.getReqNumber());

        navbar.logout();
        browser.close();
    }


    @Test(enabled = true, dependsOnMethods = {"CreateRequestTest"})
    @TestRailReference(id = 3604)
    public void ReceiveOrderTest(ITestContext testContext) {

        Browser browser = new Browser(testContext);
        LoginPagePOM login = new LoginPagePOM(browser);
        BuyerNavBarPOM navbar = new BuyerNavBarPOM(browser);
        ViewOrderPOM view = new ViewOrderPOM(browser);
        ReceiveOrderPOM receive = new ReceiveOrderPOM(browser);

        // go to the target PO
        navigateToPO(browser, login, navbar, view);
        Map<ViewOrderPOM.POListColumn, WebElement> poLine = view.getElementsForPOLine(request.getReqPONumber());

        // click Receive on action bar
        browser.clickWhenAvailable(poLine.get(POListColumn.ACTION));
        browser.clickSubElement(poLine.get(POListColumn.ACTION), view.piActionReceive);

        // fill-in fields for receipt and click Submit
        browser.clickWhenAvailable(receive.ReceiveAllButton);
        browser.sendKeysWhenAvailable(receive.CarrierEdit, resource.getValue("receipt_carrier"));
        browser.sendKeysWhenAvailable(receive.FreightEdit, resource.getValue("receipt_freight"));
        browser.sendKeysWhenAvailable(receive.CartonCountEdit, resource.getValue("receipt_cartoncount"));
        browser.sendKeysWhenAvailable(receive.PackingSlipEdit, resource.getValue("receipt_packingslip"));
        String todaysDate = browser.getDateTimeNowInUsersTimezone().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        browser.InjectJavaScript("arguments[0].value=arguments[1]", receive.DateReceivedEdit, todaysDate);
        new Select(receive.ItemStatusDrop).selectByIndex(1);
        browser.clickWhenAvailable(receive.SubmitButton);

        navbar.logout();
        browser.close();
    }

    @Test(enabled = true, dependsOnMethods = {"ReceiveOrderTest"})
    @TestRailReference(id = 3604)
    public void ViewOrdersTest(ITestContext testContext) {

        Browser browser = new Browser(testContext);
        LoginPagePOM login = new LoginPagePOM(browser);
        BuyerNavBarPOM navbar = new BuyerNavBarPOM(browser);
        ViewOrderPOM view = new ViewOrderPOM(browser);
        ReceiveOrderPOM receive = new ReceiveOrderPOM(browser);

        // go to the target PO
        navigateToPO(browser, login, navbar, view);
        Map<ViewOrderPOM.POListColumn, WebElement> poLine = view.getElementsForPOLine(request.getReqPONumber());

        // verify PO is created and ready to be received
        String status = poLine.get(POListColumn.STATUS).getText();
        Assert.assertTrue("Verify PO Approved", status.contains("Approved"));
        Assert.assertTrue("Verify PO Transmitted", status.contains("Sent to Email"));

        // back on the PO list, click Receipt History and confirm receipt submitted
        browser.clickWhenAvailable(poLine.get(POListColumn.ACTION));
        browser.clickSubElement(poLine.get(POListColumn.ACTION), view.piReceiptHistory);

        browser.waitForElementToAppear(receive.rhReceiptNumber);
        request.setReceiptNumber(receive.rhReceiptNumber.getText());

        browser.clickWhenAvailable(receive.rhCloseButton);

        navbar.logout();
        browser.close();
    }

    //////////////////////////////////////////////////////////////////////// HELPER METHODS

    private void navigateToPO(Browser browser, LoginPagePOM login, BuyerNavBarPOM navbar, ViewOrderPOM view) {
        // go to default URL and log in as a buyer
        browser.getDriver().get(browser.baseUrl);
        login.loginAsBuyer();

        // Go to Requests > Order > View All
        navbar.selectDropDownItem(resource.getValue("navbar_poheaditem"), resource.getValue("navbar_viewpo"));

        // search for target PO
        browser.sendKeysWhenAvailable(view.mainSearchOrderNumberEdit, request.getReqPONumber());
        browser.clickWhenAvailable(view.mainSearchSubmitButton);
    }

    private void navigateToReq(Browser browser, LoginPagePOM login, BuyerNavBarPOM navbar, ViewReqPOM view) {
        browser.getDriver().get(browser.baseUrl);

        login.loginAsBuyer();

        // Go to Requests > Create New > Off-Catalog Request
        navbar.selectDropDownItem(resource.getValue("navbar_reqheaditem"), resource.getValue("navbar_viewreq"));

        browser.sendKeysWhenAvailable(view.filterReqNumEdit, request.getReqNumber());
        browser.clickWhenAvailable(view.filterSubmitButton);
    }
}
