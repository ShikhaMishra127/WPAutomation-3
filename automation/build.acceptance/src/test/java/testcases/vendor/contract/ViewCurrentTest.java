package testcases.vendor.contract;

import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageobjects.vendor.common.VendorNavBarPOM;
import pageobjects.vendor.contracts.VendorContractViewPOM;
import pageobjects.common.LoginPagePOM;
import utilities.common.Browser;


public class ViewCurrentTest {

    Browser browser;
    LoginPagePOM login;
    VendorNavBarPOM navbar;
    VendorContractViewPOM ctNavBar;

    @BeforeClass
    public void setup(ITestContext testContext) {
        browser = new Browser(testContext);
        login = new LoginPagePOM(browser);
        navbar = new VendorNavBarPOM(browser);
        ctNavBar = new VendorContractViewPOM(browser);

        // before starting our tests, first log into the system as a vendor
        browser.getDriver().get(browser.baseUrl);
        login.loginAsUser(browser.supplierUsername, browser.supplierPassword);
    }
    @Test()
    public void viewCurrentContracts() {
        ctNavBar.selectNavContractByBuyer(browser.buyerName, "View Contracts");

        navbar.vendorLogout();
        browser.close();
    }
}
