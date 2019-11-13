package testcases.buyer.admin;

import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageobjects.buyer.admin.EnterpriseAdminPOM;
import pageobjects.buyer.common.BuyerNavBarPOM;
import pageobjects.common.LoginPagePOM;
import utilities.common.Browser;
import utilities.common.TestRailReference;

public class EnterpriseAdminTest {

    Browser browser;

    LoginPagePOM login;
    BuyerNavBarPOM navbar;
    EnterpriseAdminPOM admin;

    EnterpriseAdminTest() { }

    @BeforeClass
    public void setup(ITestContext testContext) {

        browser = new Browser(testContext);
        login = new LoginPagePOM(browser);
        navbar = new BuyerNavBarPOM(browser);
        admin = new EnterpriseAdminPOM(browser);

        browser.getDriver().get(browser.baseUrl);
        login.loginAsBuyer();

        navbar.selectDropDownItem("Admin ", "Enterprise Administration");
    }

    @AfterClass
    public void tearDown() {
        navbar.logout();
        browser.close();
    }

    @Test(priority = 1)
    @TestRailReference(id=3599)
    public void GeneralOrgInfoTest() {

        admin.SelectFromMenu("Organization Information", "Edit General Org Info");

    }

}
