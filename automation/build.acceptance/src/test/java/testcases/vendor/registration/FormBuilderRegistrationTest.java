package testcases.vendor.registration;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import pageobjects.buyer.admin.FormBuilderAdminPOM;
import pageobjects.buyer.admin.InternalAdminPOM;
import pageobjects.common.CommodityPickerPOM;
import pageobjects.common.LoginPagePOM;
import pageobjects.vendor.registration.RegFormBuilderPOM;
import utilities.common.Browser;
import utilities.common.ResourceLoader;
import utilities.common.TestRailReference;
import utilities.common.UniqueID;

public class FormBuilderRegistrationTest {

    ResourceLoader resource = new ResourceLoader("data/formbuilder");

    @Test(enabled = false)
    @TestRailReference(id = 12469)
    public void CreateRegistrationTest(ITestContext testContext) {

        /*
        TEST HAS BEEN ABANDONED - until we get enhancement WP-5635 implemented
        (delete this test if it does not look like anyone will do it)
         */
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

        form.RemoveExistingPages();
        browser.clickWhenAvailable(form.editAddNewPageButton);
        browser.sendKeysWhenAvailable(form.editNewPageNameEdit, "SPOOFY");
        browser.clickWhenAvailable(form.editNewPageCreateButton);

        form.OpenFormBuilderEditor("SPOOFY");
        browser.clickWhenAvailable(form.editShowJSONCheckbox);

        Actions actions = new Actions(browser.driver);

        actions.dragAndDrop(browser.getSubElement(form.dragLayouts, form.dragDiv), form.dropArea).build().perform();
        actions.dragAndDrop(browser.getSubElement(form.dragWebProcure, "./app-fein"), form.dropArea).build().perform();

    }

    @Test(enabled = true)
    @TestRailReference(id = 12468)
    public void RegisterSupplierTest(ITestContext testContext) {

        Browser browser = new Browser(testContext);
        RegFormBuilderPOM form = new RegFormBuilderPOM(browser);
        CommodityPickerPOM picker = new CommodityPickerPOM(browser);

        // build a unique supplier name (used for SSN/FEIN as well)
        UniqueID supplierID = new UniqueID(UniqueID.IDType.SSNFEIN);
        String supplierName = resource.getValue("supplier_name") + " " + supplierID.getNumber();

        // Go to FormBuilder registration page
        browser.getDriver().get(browser.formbuilderUrl);

        // Fill out Company Info page
        browser.sendKeysWhenAvailable(form.supplierNameEdit, supplierName);
        new Select(form.countryDrop).selectByValue(resource.getValue("country_code"));
        new Select(form.businessTypeDrop).selectByVisibleText(resource.getValue("business_type"));

        browser.sendKeysWhenAvailable(browser.getSubElement(form.feinSection, form.feinPt1), supplierID.getFeinPt1());
        browser.sendKeysWhenAvailable(browser.getSubElement(form.feinSection, form.feinPt2), supplierID.getFeinPt2());
        browser.sendKeysWhenAvailable(browser.getSubElement(form.feinSection, form.feinConfirmPt1), supplierID.getFeinPt1());
        browser.sendKeysWhenAvailable(browser.getSubElement(form.feinSection, form.feinConfirmPt2), supplierID.getFeinPt2());

        browser.sendKeysWhenAvailable(form.compAddrLine1Edit, resource.getValue("addr1"));
        browser.sendKeysWhenAvailable(form.compAddrLine2Edit, resource.getValue("addr2"));
        browser.sendKeysWhenAvailable(form.compAddrCityEdit, resource.getValue("city"));
        new Select(form.compAddrStateDrop).selectByValue(resource.getValue("state_code"));
        browser.sendKeysWhenAvailable(form.compAddrZipEdit, resource.getValue("zipcode"));

        // Automatically click "here" if error message appears, "We could not find this address."
        form.VerifyIfInvalidAddress();

        browser.waitForPageLoad();

        // Fill out User Info page
        browser.sendKeysWhenAvailable(form.userFirstNameEdit, resource.getValue("first_name"));
        browser.sendKeysWhenAvailable(form.userLastNameEdit, resource.getValue("last_name"));
        browser.sendKeysWhenAvailable(form.userJobTitleEdit, resource.getValue("job_title"));
        browser.sendKeysWhenAvailable(form.userPhoneEdit, resource.getValue("phone_number"));
        browser.sendKeysWhenAvailable(form.userFaxEdit, resource.getValue("fax_number"));
        browser.sendKeysWhenAvailable(form.userEmailAddressEdit, resource.getValue("email"));
        browser.sendKeysWhenAvailable(form.userUsernameEdit, supplierID.getNumber());
        browser.sendKeysWhenAvailable(form.userPasswordEdit, resource.getValue("password"));
        browser.sendKeysWhenAvailable(form.userConfirmPasswordEdit, resource.getValue("password"));

        browser.clickWhenAvailable(form.nextButton);

        // Fill out Commodity Info page
        browser.waitForElementToAppear(form.commFrame);
        browser.switchToFrame(form.commFrame);

        picker.addCodes(resource.getValue("commodity_codes"));

        browser.switchBackToTopFrame();

        // Finish Registration
        browser.clickWhenAvailable(form.submitButton);

        // Verify summary page contains our new supplier
        browser.waitForElementToAppear(form.summarySupplierName);

        browser.Assert("Verify New Supplier Name", form.summarySupplierName.getText(), supplierName);
        browser.Log("Supplier (" + supplierName +") created using FormBuilder");

        browser.close();
    }
}
