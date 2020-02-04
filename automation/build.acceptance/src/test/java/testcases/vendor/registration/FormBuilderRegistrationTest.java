package testcases.vendor.registration;

import org.testng.ITestContext;
import org.testng.annotations.Test;
import pageobjects.buyer.admin.FormBuilderAdminPOM;
import pageobjects.buyer.admin.InternalAdminPOM;
import pageobjects.common.LoginPagePOM;
import utilities.common.Browser;
import utilities.common.ResourceLoader;
import utilities.common.TestRailReference;

public class FormBuilderRegistrationTest {

    ResourceLoader resource = new ResourceLoader("data/formbuilder");;

    @Test
    @TestRailReference(id = 12469)
    public void CreateRegistrationTest(ITestContext testContext) {

        Browser browser = new Browser(testContext);
        LoginPagePOM login = new LoginPagePOM(browser);
        InternalAdminPOM admin = new InternalAdminPOM(browser);
        FormBuilderAdminPOM form = new FormBuilderAdminPOM(browser);

        // go to default URL and log in as a supplier
        browser.getDriver().get(browser.baseUrl);
        login.loginAsUser("acomenzo", "Sewers1!");

        // that's a lot of clicks to get to FB, ugh!
        browser.clickWhenAvailable(admin.formBuilderLink);
        browser.clickWhenAvailable(admin.welcomeFBButton);
        browser.clickWhenAvailable(form.formBuilderTab);
        browser.clickWhenAvailable(form.startFBButton);

        browser.HardWait(3);
        form.CreateOrUseProject("Automation", "BOOM");
    }

}
