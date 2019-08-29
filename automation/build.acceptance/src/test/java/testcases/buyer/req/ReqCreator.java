package testcases.buyer.req;

import framework.Request;
import org.openqa.selenium.support.ui.Select;
import pageobjects.buyer.req.NewReqPOM;
import pageobjects.common.BuyerNavBarPOM;
import pageobjects.common.LoginPagePOM;
import utilities.common.Browser;
import utilities.common.ResourceLoader;

public class ReqCreator {

    Request newreq;
    Browser browser;
    ResourceLoader resource;
    BuyerNavBarPOM navbar;
    LoginPagePOM login;
    NewReqPOM req;


    private void setup(Browser inBrowser,  ResourceLoader reqdata) {

        browser = inBrowser;
        resource = reqdata;
        navbar = new BuyerNavBarPOM(browser);
        login = new LoginPagePOM(browser);
        req = new NewReqPOM(browser);

        newreq = new Request();
    }

    public Request CreateRequest(Browser inBrowser,  ResourceLoader reqData) {

        setup(inBrowser, reqData);

        browser.getDriver().get(browser.baseUrl);

        login.loginAsBuyer();

        navigateToNewReq();
        enterOffCatalogData();
        reviewSubmitReq();

        navbar.logout();
        browser.close();

        return newreq;
    }


    private void navigateToNewReq() {

        // Go to Requests > Create New > Off-Catalog Request
        navbar.selectDropDownItem(resource.getValue("navbar_headitem"), resource.getValue("navbar_newreq"));

        // in some EBOs, focus is given to the external catalog content. switch back to req frame
        browser.switchToFrame(req.reqIFrame);
        browser.clickWhenAvailable(req.offCatalogTab);

    }

    private void enterOffCatalogData()  {

        browser.switchToFrame(req.reqIFrame);

        // Fill in all required fields for the Request
        browser.sendKeysWhenAvailable(req.ocOrderQtyEdit, resource.getValue("Quantity"));
        browser.sendKeysWhenAvailable(req.ocUnitPriceEdit, resource.getValue("UnitPrice"));
        browser.sendKeysWhenAvailable(req.ocSupplierPartNoEdit, resource.getValue("SupplierPartNo"));
        browser.sendKeysWhenAvailable(req.ocMfrNameEdit, resource.getValue("Manufacturer"));

        // if "Usage Code" feature enabled, select first item in list
        if (browser.elementExists(req.ocUsageCodeDrop)) {
            req.ocUsageCodeDrop.click();
            new Select(req.ocUsageCodeDrop).selectByIndex(1);
        }

        browser.clickTypeAheadDropdownItem(req.ocVendorNameEdit, req.ocVendorList, resource.getValue("SupplierName"));

        // check "retain key info" radio, so it doesn't ask us later
        if (!req.ocRetainInfoCheck.isSelected()) {
            req.ocRetainInfoCheck.click();
        }

        // add commodity code
        browser.clickTypeAheadDropdownItem(req.ocCommodityEdit, req.ocCommodityList, resource.getValue("CommodityCode"));

        // if a pesky "do you want a contract with that?" pop-up appears, close it
        browser.waitForElementToBeClickable(req.modalNoButton, (long)3);
        if (req.modalDialog.getAttribute("class").contains("modal-open")) {
            req.modalNoButton.click();
        }

        // click "Add" button to add item to req
        browser.ClickWhenClickable(req.ocAddItemButton);
    }

    private void reviewSubmitReq() {

        // click View Request tab
        browser.driver.switchTo().defaultContent();
        browser.switchToFrame(req.reqIFrame);
        browser.clickWhenAvailable(req.viewReqTab);

        // get the name/number of the newly created req
        browser.switchToFrame(req.footerIFrame);
        browser.waitForElementToAppear(req.reqNameEdit);

        // save reqName, reqNumber and reqTotal for other tests
        String reqName = req.reqNameEdit.getAttribute("value");
        newreq.setReqName(reqName);
        newreq.setReqNumber(reqName.substring(reqName.indexOf("/")+1));
        newreq.setReqTotal(resource.getValue("ReqTotal"));

        // click Submit Request button
        browser.driver.switchTo().defaultContent();
        browser.switchToFrame(req.reqIFrame);
        browser.clickWhenAvailable(req.vrSubmitReqButton);

        browser.Log(reqName + " Created");
    }
}
