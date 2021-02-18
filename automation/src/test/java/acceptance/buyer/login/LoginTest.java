package acceptance.buyer.login;

import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageobjects.buyer.common.BuyerNavBarPOM;
import pageobjects.common.LoginPagePOM;
import pageobjects.vendor.common.VendorNavBarPOM;
import utilities.common.Browser;
import utilities.common.ResourceLoader;
import utilities.common.TestRailReference;

public class LoginTest {

    Browser browser;
    ResourceLoader resource;

    LoginPagePOM login;
    VendorNavBarPOM vendorNavBar;
    BuyerNavBarPOM buyerNavBar;

    LoginTest() {
    }

    @BeforeClass
    public void setup(ITestContext testContext) {

        browser = new Browser(testContext);
        resource = new ResourceLoader("data/admin");

        login = new LoginPagePOM(browser);
        vendorNavBar = new VendorNavBarPOM(browser);
        buyerNavBar = new BuyerNavBarPOM(browser);

    }

    @AfterTest
    public void teardown(ITestContext testContext) {
        browser.close();
    }

    @Test
    @TestRailReference(id = 3553)
    public void BuyerLoginTest() {

        browser.getDriver().get(browser.baseUrl);

        login.loginAsBuyer();
        browser.Log("BUYER user logged IN ");
    }

    @Test
    @TestRailReference(id = 3554)
    public void BuyerLogoutTest() {
        buyerNavBar.logout();
        browser.Log("BUYER user logged OUT ");
    }

    @Test
    @TestRailReference(id = 3553)
    public void VendorLoginTest(ITestContext testContext) {

        browser.getDriver().get(browser.baseUrl);

        login.loginAsSupplier();
        browser.Log("Vendor user logged IN ");
    }

    @Test
    @TestRailReference(id = 3554)
    public void VendorLogoutTest(ITestContext testContext) {
        vendorNavBar.vendorLogout();
        browser.Log("Vendor user logged OUT ");
    }

}
