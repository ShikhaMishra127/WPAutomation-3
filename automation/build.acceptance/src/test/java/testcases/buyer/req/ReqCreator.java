package testcases.buyer.req;

import framework.Request;
import org.openqa.selenium.support.ui.Select;
import org.testng.ITestContext;
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


    private void setup(String contractdata, ITestContext testContext) {

        browser = new Browser(testContext);
        resource = new ResourceLoader(contractdata);
        navbar = new BuyerNavBarPOM(browser);
        login = new LoginPagePOM(browser);
        req = new NewReqPOM(browser);

        newreq = new Request();
    }

    public Request CreateRequest(String reqData, ITestContext testContext) {

        setup(reqData, testContext);

        browser.getDriver().get(browser.baseUrl);

        login.loginAsBuyer();

        navigateToNewReq();
        enterOffCatalogData();
        reviewSubmitReq();

       // navbar.logout();
      //  browser.close();

        return newreq;
    }


    private void navigateToNewReq() {

        navbar.selectDropDownItem(resource.getValue("navbar_headitem"), resource.getValue("navbar_subitem"));

        // in some EBOs, focus is given to the external catalog content. switch back to req frame
        browser.switchToFrame(req.reqIFrame);
        browser.clickWhenAvailable(req.offCatalogTab);

    }

    private void enterOffCatalogData() {

        browser.switchToFrame(req.reqIFrame);

        browser.waitForElementToAppear(req.ocOrderQtyEdit);
        req.ocOrderQtyEdit.sendKeys("5000");

        browser.sendKeysWhenAvailable(req.ocOrderQtyEdit, "5000");
        browser.sendKeysWhenAvailable(req.ocUnitPriceEdit, "2.89");
        browser.sendKeysWhenAvailable(req.ocSupplierPartNoEdit, "SPN02122391");
        browser.sendKeysWhenAvailable(req.ocMfrNameEdit, "ABC Corp.");
        browser.InjectJavaScript("arguments[0].value=arguments[1]", req.ocNeedByDateEdit, "08/20/2019");

        // if "Usage Code" feature enabled, select first item in list
        if (browser.elementExists(req.ocUsageCodeDrop)) {
            req.ocUsageCodeDrop.click();
            new Select(req.ocUsageCodeDrop).selectByIndex(1);
        }

        browser.clickTypeAheadDropdownItem(req.ocVendorNameEdit, req.ocVendorList, "Auto");

        // check "retain key info" radio, so it doesn't ask us later
        if (!req.ocRetainInfoCheck.isSelected()) {
            req.ocRetainInfoCheck.click();
        }

        // add commodity code
        browser.clickTypeAheadDropdownItem(req.ocCommodityEdit, req.ocCommodityList, "05240");

        // if a pesky "do you want a contract with that?" pop-up appears, close it
        if (req.modalDialog.getAttribute("class").contains("modal-open")) {
            browser.clickWhenAvailable(req.modalNoButton);
        }

        // click "Add" button to add item to req
        browser.clickWhenAvailable(req.ocAddItemButton);

    }

    private void reviewSubmitReq() {

        browser.clickWhenAvailable(req.viewReqTab);
        System.out.println("Done!");

    }
    /*
    steps:
    login as a buyer
    go to create new req > off-catalog page
    enter data on page
    click on review req
    submit req
     */



}
