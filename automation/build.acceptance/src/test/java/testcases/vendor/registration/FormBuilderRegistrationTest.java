package testcases.vendor.registration;

import junit.framework.Assert;
import org.openqa.selenium.By;
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
    String registrationURL = "";

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
    //    @Test(enabled = true, dependsOnMethods = {"CreateRegistrationTest"})
    @TestRailReference(id = 12468)
    public void RegisterSupplierTest(ITestContext testContext) {

        Browser browser = new Browser(testContext);
        RegFormBuilderPOM form = new RegFormBuilderPOM(browser);
        CommodityPickerPOM picker = new CommodityPickerPOM(browser);

        UniqueID supplierID = new UniqueID(UniqueID.IDType.SSNFEIN);
        String supplierName = "Test Supplier " + supplierID.getNumber();

        // DEL ME - Assume that we've set the registration URL in the previous test
        registrationURL = "https://internalwpqa.perfect.com/wp-form-builder/#/form/bff3fb33-1a25-4817-a9b2-040bf3f15b07";

        browser.getDriver().get(registrationURL);

        // Fill out Company Info page
        browser.sendKeysWhenAvailable(form.supplierNameEdit, supplierName);
        new Select(form.countryDrop).selectByValue("US");
        new Select(form.businessTypeDrop).selectByVisibleText("Individual/Sole Proprietor");

        browser.sendKeysWhenAvailable(browser.getSubElement(form.feinSection, form.feinPt1), supplierID.getFeinPt1());
        browser.sendKeysWhenAvailable(browser.getSubElement(form.feinSection, form.feinPt2), supplierID.getFeinPt2());
        browser.sendKeysWhenAvailable(browser.getSubElement(form.feinSection, form.feinConfirmPt1), supplierID.getFeinPt1());
        browser.sendKeysWhenAvailable(browser.getSubElement(form.feinSection, form.feinConfirmPt2), supplierID.getFeinPt2());

        browser.sendKeysWhenAvailable(form.compAddrLine1Edit, "1 Mayberry Place");
        browser.sendKeysWhenAvailable(form.compAddrLine2Edit, "Suite 120");
        browser.sendKeysWhenAvailable(form.compAddrCityEdit, "Williamsburg");
        new Select(form.compAddrStateDrop).selectByValue("VA");
        browser.sendKeysWhenAvailable(form.compAddrZipEdit, "23606");

        // Automatically click "here" if error message appears, "We could not find this address."
        form.VerifyIfInvalidAddress();

        browser.waitForPageLoad();

        // Fill out User Info page
        browser.sendKeysWhenAvailable(form.userFirstNameEdit, "Andrew");
        browser.sendKeysWhenAvailable(form.userLastNameEdit, "Lipinski");
        browser.sendKeysWhenAvailable(form.userJobTitleEdit, "Lord Master QA");
        browser.sendKeysWhenAvailable(form.userPhoneEdit, "8005551212");
        browser.sendKeysWhenAvailable(form.userFaxEdit, "8664443334");
        browser.sendKeysWhenAvailable(form.userEmailAddressEdit, "andrew.comenzo@proactis.com");
        browser.sendKeysWhenAvailable(form.userUsernameEdit, supplierID.getNumber());
        browser.sendKeysWhenAvailable(form.userPasswordEdit, "Xxxxxxx1!");
        browser.sendKeysWhenAvailable(form.userConfirmPasswordEdit, "Xxxxxxx1!");

        browser.clickWhenAvailable(form.nextButton);

        // Fill out Commodity Info page
        browser.waitForElementToAppear(form.commFrame);
        browser.switchToFrame(form.commFrame);

        picker.addCodes("05200,18030,06505");

        browser.switchBackToTopFrame();

        // Finish Registration
        browser.clickWhenAvailable(form.submitButton);

        browser.waitForElementToAppear(form.summarySupplierName);

        browser.Assert("Verify New Supplier Name", form.summarySupplierName.getText(), supplierName);

        browser.Log("Supplier (" + supplierName +") created using FormBuilder");
    }
}
