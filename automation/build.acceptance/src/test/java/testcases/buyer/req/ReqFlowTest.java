package testcases.buyer.req;

import framework.Request;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageobjects.buyer.approval.ApprovalInboxPOM;
import pageobjects.buyer.common.BuyerNavBarPOM;
import pageobjects.buyer.invoice.NewInvoicePOM;
import pageobjects.buyer.invoice.ViewInvoicePOM;
import pageobjects.buyer.orders.ReceiveOrderPOM;
import pageobjects.buyer.orders.ViewOrderPOM;
import pageobjects.buyer.req.NewReqPOM;
import pageobjects.buyer.req.ViewReqPOM;
import pageobjects.common.InvoicePOPicker;
import pageobjects.common.LoginPagePOM;
import pageobjects.vendor.common.VendorNavBarPOM;
import pageobjects.vendor.invoice.VendorNewInvoicePOM;
import pageobjects.vendor.invoice.VendorViewInvoicePOM;
import pageobjects.vendor.orders.VendorOrderViewPOM;
import utilities.common.Browser;
import utilities.common.ResourceLoader;
import utilities.common.TestRailReference;

import java.time.format.DateTimeFormatter;
import java.util.Map;

import static pageobjects.buyer.invoice.ViewInvoicePOM.InvListColumn;
import static pageobjects.buyer.orders.ViewOrderPOM.POListColumn;
import static pageobjects.buyer.req.ViewReqPOM.ReqListColumn;
import static pageobjects.vendor.orders.VendorOrderViewPOM.VendorPOListColumn;
import static pageobjects.vendor.invoice.VendorViewInvoicePOM.VendorInvListColumn;

//@Listeners({TestRailListener.class})

public class ReqFlowTest {

    Request request;
    ResourceLoader resource;

    @BeforeClass
    public void setup() {

        request = new Request();
        resource = new ResourceLoader("data/req");
    }

    @Test
    @TestRailReference(id = 3516)
    public void CreateRequestTest(ITestContext testContext) {

        Browser browser = new Browser(testContext);
        ReqCreator creator = new ReqCreator();

        request = creator.CreateRequest(browser, resource);
    }


    @Test(enabled = true, dependsOnMethods = {"CreateRequestTest"})
    @TestRailReference(id = 3522)
    public void ApproveRequestTest(ITestContext testContext) {

        Browser browser = new Browser(testContext);
        LoginPagePOM login = new LoginPagePOM(browser);
        BuyerNavBarPOM navbar = new BuyerNavBarPOM(browser);
        ApprovalInboxPOM inbox = new ApprovalInboxPOM(browser);

        browser.getDriver().get(browser.baseUrl);

        login.loginAsApprover();

        // Go to Approval > Approval Inbox
        navbar.selectDropDownItem(resource.getValue("navbar_approval"), resource.getValue("navbar_inbox"));

        // Look up document type "Request"
        new Select(inbox.documentTypeDrop).selectByVisibleText(resource.getValue("inbox_doctype"));

        // Enter req number and click Search
        browser.sendKeysWhenAvailable(inbox.documentNumberEdit, request.getReqNumber());
        browser.clickWhenAvailable(inbox.submitButton);

        browser.HardWait(3);

        // Enter comment and click Approve
       browser.sendKeysWhenAvailable(inbox.reqApprovalComment, resource.getValue("inbox_approval_comment"));
       browser.clickWhenAvailable(inbox.approveButton);

       browser.HardWait(3);

       browser.Log(request.getReqName() + " approved in workflow");

        navbar.logout();
        browser.close();
    }

    @Test(enabled = true, dependsOnMethods = {"ApproveRequestTest"})
    @TestRailReference(id = 3518)
    public void ViewRequestTest(ITestContext testContext) {

        Browser browser = new Browser(testContext);
        LoginPagePOM login = new LoginPagePOM(browser);
        BuyerNavBarPOM navbar = new BuyerNavBarPOM(browser);
        ViewReqPOM view = new ViewReqPOM(browser);

        navigateToReq(browser, login, navbar, view);

        Map<ViewReqPOM.ReqListColumn, WebElement> reqLine = view.getElementsForReqLine(request.getReqName());

        // make sure req number and status are ok
        browser.Assert("Verify req number", reqLine.get(ReqListColumn.REQNUM).getText(), request.getReqNumber());
        browser.Assert("Verify req status", reqLine.get(ReqListColumn.STATUS).getText(), resource.getValue("request_status"));

        // expand req data and get PO number generated (For further tests)
        browser.clickSubElement(reqLine.get(ReqListColumn.EXPAND), view.riDownArrow);
        browser.waitForElementToAppear(view.riPONumber);

        request.setReqPONumber(view.riPONumber.getText().trim());  // PO number has a trailing space, dammit!

        // make sure total cost is ok
//        browser.Assert("Verify req total", browser.getSubElement(view.reqTable, view.riReqTotal).getText(), request.getReqTotal()));

        browser.Log("PO " + request.getReqPONumber() + " created");
        browser.Log(request.getReqName() + " viewed as a buyer");

        navbar.logout();
        browser.close();
    }

    @Test(enabled = false, dependsOnMethods = {"CreateRequestTest"})
    @TestRailReference(id = 3519)
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
    @TestRailReference(id = 3520)
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
        browser.Assert("Verify Print Request Name", view.printReqName.getText(), request.getReqName());
        browser.Assert("Verify Print Request Number", view.printReqNumber.getText(), request.getReqNumber());
//        browser.Assert("Verify Request Total", view.printReqBody.getText(), request.getReqTotal()));

        // close pop-up and return to parent window
        browser.ClosePopUp(parentWindow);

        browser.Log("Viewed printed request #" + request.getReqNumber());

        navbar.logout();
        browser.close();
    }

    @Test(enabled = true, dependsOnMethods = {"CreateRequestTest"})
    @TestRailReference(id = 3521)
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
        browser.Assert("Verify History Request Name", view.historyReqName.getText(), request.getReqName());
        browser.Assert("Verify History Request Number", view.historyReqNumber.getText(), request.getReqNumber());

        // BUG WP-5071 - Req History does not show req total
        // browser.Assert("Verify History Request Total", view.historyReqTotal.getText(), request.getReqTotal());

        browser.Log("Viewed req history for #" + request.getReqNumber());

        navbar.logout();
        browser.close();
    }

    @Test(enabled = true, dependsOnMethods = {"ViewRequestTest"}) // ViewRequestTest records the PO number we need to receive
    @TestRailReference(id = 3546)
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
    @TestRailReference(id = 3545)
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
        browser.Assert("Verify PO Approved", status, resource.getValue("po_status"));
        browser.Assert("Verify PO Transmitted", status, resource.getValue("po_buyer_transmission"));

        // back on the PO list, click Receipt History and confirm receipt submitted
        browser.clickWhenAvailable(poLine.get(POListColumn.ACTION));
        browser.clickSubElement(poLine.get(POListColumn.ACTION), view.piReceiptHistory);

        browser.waitForElementToAppear(receive.rhReceiptNumber);
        request.setReceiptNumber(receive.rhReceiptNumber.getText());

        browser.clickWhenAvailable(receive.rhCloseButton);

        navbar.logout();
        browser.close();
    }

    @Test(enabled = true, dependsOnMethods = {"ReceiveOrderTest"})
    @TestRailReference(id = 3541)
    public void CreateInvoiceTest(ITestContext testContext) {

        Browser browser = new Browser(testContext);
        LoginPagePOM login = new LoginPagePOM(browser);
        BuyerNavBarPOM navbar = new BuyerNavBarPOM(browser);
        NewInvoicePOM invoice = new NewInvoicePOM(browser);
        InvoicePOPicker poPicker = new InvoicePOPicker(browser);

        // go to default URL and log in as a buyer
        browser.getDriver().get(browser.baseUrl);
        login.loginAsBuyer();

        // Go to Invoices > Create New
        navbar.selectDropDownItem(resource.getValue("navbar_invheaditem"), resource.getValue("navbar_newinv"));

        // save the invoice number for later
        browser.waitForElementToAppear(invoice.headBuyerInvoiceNumberEdit);
        request.setBuyerInvoiceNumber(invoice.headBuyerInvoiceNumberEdit.getAttribute("value"));

        // set the supplier's invoice number to buyer's + "S"
        browser.sendKeysWhenAvailable(invoice.headSupplierInvoiceNumberEdit, request.getBuyerInvoiceNumber() + "S");

        // select supplier and EFT
        browser.clickTypeAheadDropdownItem(invoice.headSupplierSearchEdit, invoice.headSupplierSearchList, request.getReqSupplierName());
        new Select(invoice.headEFTDrop).selectByValue("No");

        // set required dates
        String todaysDate = browser.getDateTimeNowInUsersTimezone().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));

        browser.sendKeysWhenAvailable(invoice.headReceiveDateEdit, todaysDate);
        browser.sendKeysWhenAvailable(invoice.headPostDateEdit, todaysDate);
        browser.sendKeysWhenAvailable(invoice.headIssueDateEdit, todaysDate);
        browser.sendKeysWhenAvailable(invoice.headDueDateEdit, todaysDate);

        // done with this page, go to next
        browser.clickWhenAvailable(invoice.headNextButton);

        // Look up target PO from the list of available POs for supplier
        browser.clickWhenAvailable(invoice.itemsPOSearchButton);

        // add all items contained within target PO to invoice
        poPicker.addAllPOItems(request.getReqPONumber());

        // back on the main invoice page, invoice one item, add comments and go to next step
        browser.sendKeysWhenAvailable(invoice.itemsInvoiceQtyEdit, resource.getValue("invoice_qty"));
        browser.sendKeysWhenAvailable(invoice.itemsCommentEdit, resource.getValue("invoice_comment"));
        browser.sendKeysWhenAvailable(invoice.itemsFreightEdit, resource.getValue("invoice_freight"));
        browser.sendKeysWhenAvailable(invoice.itemsFreightCommentEdit, resource.getValue("invoice_freightcomment"));
        browser.clickWhenAvailable(invoice.NextButton);

        // keep going after reaching the Attachments tab (future expanded test)
        browser.clickWhenAvailable(invoice.NextButton);

        // Click Match All for items we invoiced, then go to summary page
        browser.clickWhenAvailable(invoice.matchMatchAllButton);
        browser.clickWhenAvailable(invoice.matchNextButton);

        // Verify info on summary page and submit invoice
        browser.Assert("Verify Buyer Invoice #", invoice.summaryHeadDetails.getText(), request.getBuyerInvoiceNumber());

        browser.clickWhenAvailable(invoice.summarySubmitInvoice);

        browser.Log("Invoice " + request.getBuyerInvoiceNumber() + " created.");

        navbar.logout();
        browser.close();
    }

    @Test(enabled = true, dependsOnMethods = {"CreateInvoiceTest"})
    @TestRailReference(id = 3542)
    public void ViewInvoiceTest(ITestContext testContext) {

        Browser browser = new Browser(testContext);
        LoginPagePOM login = new LoginPagePOM(browser);
        BuyerNavBarPOM navbar = new BuyerNavBarPOM(browser);
        ViewInvoicePOM view = new ViewInvoicePOM(browser);

        // go to default URL and log in as a buyer
        browser.getDriver().get(browser.baseUrl);
        login.loginAsBuyer();

        // Go to Invoices > View All
        navbar.selectDropDownItem(resource.getValue("navbar_invheaditem"), resource.getValue("navbar_viewinv"));

        // Look up the target invoice
        browser.sendKeysWhenAvailable(view.mainBuyerInvoiceNumberFilterEdit, request.getBuyerInvoiceNumber());
        browser.clickWhenAvailable(view.mainApplyFilterButton);

        // Click the down-arrow on the target invoice to bring up details
        Map<Browser.HTMLTableColumn, WebElement> invLine = view.getElementsForInvLine(request.getBuyerInvoiceNumber()+"S");
        browser.clickSubElement(invLine.get(InvListColumn.EXPAND), view.iiDownArrow);

        // Verify PO associated with invoice exists
        browser.Assert("Verify PO attached to Invoice", view.ExpandedPOExists(request.getReqPONumber()));

        browser.Log("PO " + request.getReqPONumber() + " attached to invoice " + request.getBuyerInvoiceNumber() + ".");

        navbar.logout();
        browser.close();

    }

    @Test(enabled = true, dependsOnMethods = {"ViewOrdersTest"})
    @TestRailReference(id = 13416)
    public void VendorViewPOTest(ITestContext testContext) {

        Browser browser = new Browser(testContext);
        LoginPagePOM login = new LoginPagePOM(browser);
        VendorNavBarPOM navbar = new VendorNavBarPOM(browser);
        VendorOrderViewPOM vendorpo = new VendorOrderViewPOM(browser);

        // go to default URL and log in as a supplier
        browser.getDriver().get(browser.baseUrl);
        login.loginAsSupplier();

        navbar.selectNavDropByBuyer(browser.buyerName, resource.getValue("navbar_vendor_po"), resource.getValue("navbar_vendor_view"));

        // look up target PO and click on PO Number to get a summary page
        browser.sendKeysWhenAvailable(vendorpo.orderNumberEdit, request.getReqPONumber());
        browser.clickWhenAvailable(vendorpo.orderFilterListButton);

        Map<Browser.HTMLTableColumn, WebElement> poLine = vendorpo.getElementsForPOLine(request.getReqPONumber());

        browser.clickSubElement(poLine.get(VendorPOListColumn.NUMBER), "./a");

        // verify PO summary is the one we're looking for
        browser.waitForElementToAppear(vendorpo.summaryOrderTitle);
        browser.Assert("Verify PO title matches", vendorpo.summaryOrderTitle.getText(), request.getReqPONumber());

        // click Acknowledge button, fill in comments and submit
        browser.clickWhenAvailable(vendorpo.summaryAcknowledgeButton);
        browser.sendKeysWhenAvailable(vendorpo.summaryCommentsEdit, resource.getValue("vendor_comment"));
        vendorpo.summaryCommentsContinueButton.click();
        browser.clickWhenAvailable(vendorpo.orderCloseButton);

        // look up order again and verify status has changed to "ACKNOWLEDGED"
        browser.sendKeysWhenAvailable(vendorpo.orderNumberEdit, request.getReqPONumber());
        browser.clickWhenAvailable(vendorpo.orderFilterListButton);

        poLine = vendorpo.getElementsForPOLine(request.getReqPONumber());

        browser.Assert("Verify PO status is 'ACKNOWLEDGED'",
                poLine.get(VendorPOListColumn.STATUS).getText(), resource.getValue("po_vendor_transmission"));

        browser.Log("PO " + request.getReqPONumber() + " viewed and acknowledged by vendor");

        navbar.vendorLogout();
        browser.close();
    }

    @Test(enabled = true, dependsOnMethods = {"ReceiveOrderTest"})
    @TestRailReference(id = 3539)
    public void VendorCreateInvoiceTest(ITestContext testContext) {

        Browser browser = new Browser(testContext);
        LoginPagePOM login = new LoginPagePOM(browser);
        VendorNavBarPOM navbar = new VendorNavBarPOM(browser);
        VendorNewInvoicePOM invoice = new VendorNewInvoicePOM(browser);
        InvoicePOPicker poPicker = new InvoicePOPicker(browser);

        // go to default URL and log in as a supplier
        browser.getDriver().get(browser.baseUrl);
        login.loginAsSupplier();

        // go to Create new Invoice
        navbar.selectNavDropByBuyer(browser.buyerName, resource.getValue("navbar_invheaditem"), resource.getValue("navbar_newinv"));

        String vendorInvoiceNumber = request.getBuyerInvoiceNumber()+"V";

        request.setSupplierInvoiceNumber( vendorInvoiceNumber );

        // fill out header page
        browser.sendKeysWhenAvailable(invoice.headerInvoiceNumberEdit, vendorInvoiceNumber);
        browser.sendKeysWhenAvailable(invoice.headerInvoiceComments, resource.getValue("vendor_comment"));

        String todaysDate = browser.getDateTimeNowInUsersTimezone().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));

        browser.sendKeysWhenAvailable(invoice.headerIssueDate, todaysDate);
        browser.sendKeysWhenAvailable(invoice.headerDueDate, todaysDate);

        // add all items in target PO
        browser.clickWhenAvailable(invoice.headerFindPOButton);
        poPicker.addAllPOItems(request.getReqPONumber());

        // invoice one item, add comments and go to next step
        browser.sendKeysWhenAvailable(invoice.itemsInvoiceQtyEdit, resource.getValue("invoice_qty"));
        browser.sendKeysWhenAvailable(invoice.itemsCommentEdit, resource.getValue("invoice_comment"));
        browser.sendKeysWhenAvailable(invoice.itemsFreightEdit, resource.getValue("invoice_freight"));
        browser.sendKeysWhenAvailable(invoice.itemsFreightCommentEdit, resource.getValue("invoice_freightcomment"));
        browser.clickWhenAvailable(invoice.itemsNextButton);

        browser.HardWait(3);

        // keep going after reaching the Attachments tab - modal asking to continue
        browser.clickWhenAvailable(invoice.attachNextButton);
        browser.waitForElementToAppear(invoice.attachConfirmationModal);
        browser.clickWhenAvailable(invoice.attachYesContinueButton);

        // Verify information on Summary page
        browser.waitForPageLoad();
        browser.waitForElementToAppear(invoice.summaryOverViewText);

        browser.Assert("Invoice Number OK", invoice.summaryOverViewText.getText(), vendorInvoiceNumber);
        browser.Assert("PO Number OK", invoice.summaryPONumberText.getText(), request.getReqPONumber());
        browser.Assert("Freight Charge OK", invoice.summaryMiscChargesText.getText(), resource.getValue("invoice_freight"));

        // submit invoice
        browser.clickWhenAvailable(invoice.summarySubmitInvoiceButton);

        // keep going after sumbitting invoice - more pesky modals asking if it's ok to submit, then close
        browser.clickWhenAvailable(invoice.summaryYesContinueButton);
        browser.clickWhenAvailable(invoice.summaryClosePostSubmitButton);

        browser.Log("Invoice " + vendorInvoiceNumber + " created by vendor");

        navbar.vendorLogout();
        browser.close();

    }

    @Test(enabled = true, dependsOnMethods = {"VendorCreateInvoiceTest"})
    @TestRailReference(id = 3540)
    public void VendorViewInvoiceTest(ITestContext testContext) {

        Browser browser = new Browser(testContext);
        LoginPagePOM login = new LoginPagePOM(browser);
        VendorNavBarPOM navbar = new VendorNavBarPOM(browser);
        VendorViewInvoicePOM view = new VendorViewInvoicePOM(browser);

        // go to default URL and log in as a supplier
        browser.getDriver().get(browser.baseUrl);
        login.loginAsSupplier();

        String targetInvoiceNumber = request.getSupplierInvoiceNumber();

        // go to View Invoices and search for target supplier invoice
        navbar.selectNavDropByBuyer(browser.buyerName, resource.getValue("navbar_invheaditem"), resource.getValue("navbar_viewinv"));

        browser.sendKeysWhenAvailable(view.searchInvoiceNumberEdit, targetInvoiceNumber);
        browser.clickWhenAvailable(view.searchSubmitButton);

        // click on action icon, then view invoice (to bring up invoice summary)
        Map<Browser.HTMLTableColumn,WebElement> invLine = view.getElementsForInvoiceLine(targetInvoiceNumber);

        browser.clickWhenAvailable(invLine.get(VendorInvListColumn.ACTION));
        browser.clickSubElement(invLine.get(VendorInvListColumn.ACTION), view.submenuViewIcon);

        browser.waitForElementToAppear(view.summaryInvoiceNumber);

        // verify some lines on the invoice summary page
        browser.Assert("Verify Invoice Number", view.summaryInvoiceNumber.getText(), targetInvoiceNumber);
       // browser.Assert("Verify Misc Charges", view.summaryMiscFreightAmount.getText(), request.getSupplierMiscCharges());
       // browser.Assert("Verify Misc Charge Comments", view.summaryMiscFreightComments.getText(), request.getSupplierMiscChargeComments());

        browser.clickWhenAvailable(view.summaryCloseButton);

        browser.Log("Summary for invoice " + targetInvoiceNumber + " viewed by vendor");

        navbar.vendorLogout();
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

