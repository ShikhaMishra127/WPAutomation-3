package testcases.vendor.registration;

import org.junit.Assert;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageobjects.common.LoginPagePOM;
import pageobjects.vendor.registration.RegStandardPOM;
import utilities.common.Browser;
import utilities.common.ResourceLoader;
import utilities.common.ssnFein;

import java.io.IOException;

public class StandardRegistrationTest {

    Browser browser;
    ResourceLoader resource = new ResourceLoader("data/registration");
    RegStandardPOM reg;
    LoginPagePOM login;
    ssnFein vendorNum = new ssnFein();

    public StandardRegistrationTest() throws IOException {
    }

    @BeforeClass
    public void setup() throws IOException {

        browser = new Browser();
        login = new LoginPagePOM(browser);
        reg = new RegStandardPOM(browser);

        browser.getDriver().get(browser.baseUrl);
    }

    @AfterClass
    public void tearDown() {
     //   browser.close();
    }

    @Test(priority = 1)
    public void LoginPageTest() {

        // Click the standard registration link on the login page
        login.lnkRegister.click();
        browser.waitForPageLoad();

        Assert.assertTrue("Welcome banner OK", reg.homeBanner.isDisplayed());

    }

    @Test(priority = 2)
    public void TsAndCsTest() {

        // Start the registration by clicking Begin
        reg.startButton.click();

        // go to Terms & Conditions page
        Assert.assertTrue("T&C Loaded OK", reg.stepTitle.getText().contains("WebProcure Terms and Conditions"));

        // initially decline terms and conditions
        reg.declineButton.click();
        Assert.assertTrue("Terms Declined OK", reg.homeBanner.isDisplayed());

        // now go back and accept Terms and Conditions
        reg.startButton.click();
        reg.acceptButton.click();
        Assert.assertTrue("Terms Accepted OK", reg.stepTitle.getText().contains("Organization Information"));
    }

    @Test(priority = 3)
    public void DuplicateFEINTest() {

        // load standard vendor info on organization page
        loadOrgInfo();

        // set FEIN to known duplicate value
        ssnFein existingVendor = new ssnFein(resource.getValue("duplicate_ssnfein"));
        reg.orgFein1Edit.sendKeys(existingVendor.getFeinPt1());
        reg.orgFein2Edit.sendKeys(existingVendor.getFeinPt2());

        // click CONTINUE and verify fail message
        reg.continueButton.click();
        reg.getOrgDuplicateCloseButton.click();  // close pesky pop-up

        Assert.assertTrue("Duplicate FEIN message OK", reg.orgErrorMessage.getText().contains("already exists in the system"));

        // reset FEIN
        reg.orgFein1Edit.clear();
        reg.orgFein2Edit.clear();

    }

    @Test(priority = 4)
    public void DuplicateSSNTest() {

        // set SSN to known duplicate value
        ssnFein existingVendor = new ssnFein(resource.getValue("duplicate_ssnfein"));
        reg.orgSsn1Edit.sendKeys(existingVendor.getSSNPt1());
        reg.orgSsn2Edit.sendKeys(existingVendor.getSSNPt2());
        reg.orgSsn3Edit.sendKeys(existingVendor.getSSNPt3());

        // click CONTINUE and verify fail message
        reg.continueButton.click();

        Assert.assertTrue("Duplicate SSN message OK", reg.orgErrorMessage.getText().contains("already exists in the system"));

        // reset SSN
        reg.orgSsn1Edit.clear();
        reg.orgSsn2Edit.clear();
        reg.orgSsn3Edit.clear();

    }

    @Test(priority = 5)
    public void ValidOrgInfoTest() {

        // enter valid SSN and click Continue
        reg.orgSsn1Edit.sendKeys(vendorNum.getSSNPt1());
        reg.orgSsn2Edit.sendKeys(vendorNum.getSSNPt2());
        reg.orgSsn3Edit.sendKeys(vendorNum.getSSNPt3());

        reg.continueButton.click();

        Assert.assertTrue("Org Info Accepted OK", reg.stepTitle.getText().contains("Contact Information"));

    }

    // loads default for company registration data
    public void loadOrgInfo() {
        reg.orgCompanyName.sendKeys(resource.getValue("vendor_base_name")+" "+vendorNum.getNumber());
        reg.orgDBAEdit.sendKeys(resource.getValue("vendor_dba")+vendorNum.getNumber());
        reg.orgURLEdit.sendKeys(resource.getValue("vendor_website"));

        new Select(reg.orgEnterpriseTypeDrop).selectByIndex(3);
        new Select(reg.orgStateIncorporatedDrop).selectByVisibleText("Virginia");
        new Select(reg.orgTimeZoneDrop).selectByIndex(5);

        reg.orgParentBranchRadioPARENT.click();

        reg.orgCompanyPhone1.sendKeys("800");
        reg.orgCompanyPhone2.sendKeys("555");
        reg.orgCompanyPhone3.sendKeys("1212");

        reg.orgCompanyFax1.sendKeys("800");
        reg.orgCompanyFax2.sendKeys("777");
        reg.orgCompanyFax3.sendKeys("3434");

        reg.orgEmailEdit.sendKeys("andrew.comenzo@perfect.com");
        browser.InjectJavaScript("arguments[0].value=arguments[1]", reg.orgEmailConfirmEdit, "andrew.comenzo@perfect.com" );

        new Select(reg.orgCountryDrop).selectByVisibleText("United States");

        reg.orgAddress1.sendKeys("PO Box 22321");
        reg.orgAddress2.sendKeys("1 Bayport Way");
        reg.orgAddress3.sendKeys("Suite 120");

        reg.orgCity.sendKeys("Newport News");

        new Select(reg.orgStateDrop).selectByVisibleText("Virginia");

        reg.orgZip1.sendKeys("23606");
    }
}
