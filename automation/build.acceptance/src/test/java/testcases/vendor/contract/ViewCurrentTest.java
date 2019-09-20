package testcases.vendor.contract;

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
    public void setup() {
        browser = new Browser();
        login = new LoginPagePOM(browser);
        navbar = new VendorNavBarPOM(browser);
        ctNavBar = new VendorContractViewPOM(browser);

        // before starting our tests, first log into the system as a vendor
        browser.getDriver().get(browser.baseUrl);
        login.loginAsUser("autosupplier", "Automation123!");
    }
    @Test()
    public void viewCurrentContracts() {
        ctNavBar.selectNavContractByBuyer("Perfect City", "View Contracts");
    }
}
