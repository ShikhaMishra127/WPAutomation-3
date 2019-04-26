package testcases.vendor.registration;

import org.junit.Assert;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageobjects.common.LoginPagePOM;
import pageobjects.vendor.common.VendorNavBarPOM;
import pageobjects.vendor.registration.RegStandardPOM;
import utilities.common.Browser;
import utilities.common.ResourceLoader;
import utilities.common.UniqueID;

import java.io.IOException;

public class StandardRegistrationTest {

    Browser browser;
    ResourceLoader resource = new ResourceLoader("data/registration");
    RegStandardPOM reg;
    LoginPagePOM login;
    VendorNavBarPOM vendor;
    String vendorUsername;

    UniqueID vendorNum = new UniqueID(UniqueID.IDType.SSNFEIN);

    public StandardRegistrationTest() throws IOException {
    }

    @BeforeClass
    public void setup() throws IOException {

        browser = new Browser();
        login = new LoginPagePOM(browser);
        reg = new RegStandardPOM(browser);
        vendor = new VendorNavBarPOM(browser);

        browser.getDriver().get(browser.baseUrl);
    }

    @AfterClass
    public void tearDown() {
        browser.close();
    }

    // loads default for company registration data
    public void loadOrgInfo() {

        String companyName = resource.getValue("vendor_base_name") + " " + vendorNum.getNumber();
        reg.orgCompanyName.sendKeys(companyName);
        System.out.printf("Creating %s%n", companyName);

        reg.orgDBAEdit.sendKeys(resource.getValue("vendor_dba")+vendorNum.getNumber());
        reg.orgURLEdit.sendKeys(resource.getValue("vendor_website"));

        new Select(reg.orgEnterpriseTypeDrop).selectByIndex(3);
        new Select(reg.orgStateIncorporatedDrop).selectByVisibleText(resource.getValue("vendor_state"));
        new Select(reg.orgTimeZoneDrop).selectByIndex(5);

        reg.orgParentBranchRadioPARENT.click();

        reg.orgCompanyPhone1.sendKeys(resource.getValue("vendor_phone_pt1"));
        reg.orgCompanyPhone2.sendKeys(resource.getValue("vendor_phone_pt2"));
        reg.orgCompanyPhone3.sendKeys(resource.getValue("vendor_phone_pt3"));

        reg.orgCompanyFax1.sendKeys(resource.getValue("vendor_fax_pt1"));
        reg.orgCompanyFax2.sendKeys(resource.getValue("vendor_fax_pt2"));
        reg.orgCompanyFax3.sendKeys(resource.getValue("vendor_fax_pt3"));

        reg.orgEmailEdit.sendKeys(resource.getValue("vendor_email"));
        browser.InjectJavaScript("arguments[0].value=arguments[1]", reg.orgEmailConfirmEdit, resource.getValue("vendor_email") );

        new Select(reg.orgCountryDrop).selectByVisibleText(resource.getValue("vendor_country"));

        reg.orgAddress1.sendKeys(resource.getValue("vendor_address1"));
        reg.orgAddress2.sendKeys(resource.getValue("vendor_address2"));
        reg.orgAddress3.sendKeys(resource.getValue("vendor_address3"));

        reg.orgCity.sendKeys(resource.getValue("vendor_city"));

        new Select(reg.orgStateDrop).selectByVisibleText(resource.getValue("vendor_state"));

        reg.orgZip1.sendKeys(resource.getValue("vendor_zip"));
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
        Assert.assertTrue("T&C Loaded OK", reg.stepTitle.getText().contains(resource.getValue("vendor_title_step_tc")));

        // initially decline terms and conditions
        reg.declineButton.click();
        Assert.assertTrue("Terms Declined OK", reg.homeBanner.isDisplayed());

        // now go back and accept Terms and Conditions
        reg.startButton.click();
        reg.acceptButton.click();
        Assert.assertTrue("Terms Accepted OK", reg.stepTitle.getText().contains(resource.getValue("vendor_title_step_org")));
    }

    @Test(priority = 3)
    public void DuplicateFEINTest() {

        // load standard vendor info on organization page
        loadOrgInfo();

        // set FEIN to known duplicate value
        UniqueID existingVendor = new UniqueID(resource.getValue("duplicate_ssnfein"));
        reg.orgFein1Edit.sendKeys(existingVendor.getFeinPt1());
        reg.orgFein2Edit.sendKeys(existingVendor.getFeinPt2());

        // click CONTINUE and verify fail message
        reg.continueButton.click();
        reg.getOrgDuplicateCloseButton.click();  // close pesky pop-up

        Assert.assertTrue("Duplicate FEIN message OK", reg.orgErrorMessage.getText().contains(resource.getValue("vendor_error_msg")));

        // reset FEIN
        reg.orgFein1Edit.clear();
        reg.orgFein2Edit.clear();

    }

    @Test(priority = 4)
    public void DuplicateSSNTest() {

        // set SSN to known duplicate value
        UniqueID existingVendor = new UniqueID(resource.getValue("duplicate_ssnfein"));
        reg.orgSsn1Edit.sendKeys(existingVendor.getSSNPt1());
        reg.orgSsn2Edit.sendKeys(existingVendor.getSSNPt2());
        reg.orgSsn3Edit.sendKeys(existingVendor.getSSNPt3());

        // click CONTINUE and verify fail message
        reg.continueButton.click();

        Assert.assertTrue("Duplicate SSN message OK", reg.orgErrorMessage.getText().contains(resource.getValue("vendor_error_msg")));

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

        Assert.assertTrue("Org Info Accepted OK", reg.stepTitle.getText().contains(resource.getValue("vendor_title_step_con")));

    }

    @Test(priority = 6)
    public void ContactInformationTest() {

        new Select(reg.contactTitleDrop).selectByVisibleText(resource.getValue("vendor_title"));
        reg.contactFirstNameEdit.sendKeys(resource.getValue("vendor_firstname"));
        reg.contactLastNameEdit.sendKeys(resource.getValue("vendor_lastname"));

        // auto-copy all fields from main contact info
        reg.contactSameAsOrgCheckbox.click();
        reg.contactPOContactSameAsCheckbox.click();
        reg.contactPOAddressSameAsCheckbox.click();
        reg.contactSolContactSameAsCheckbox.click();
        reg.contactSolAddressSameAsCheckbox.click();
        reg.contactRemitContactSameAsCheckbox.click();
        reg.contactRemitAddressSameAsCheckbox.click();

        // click Continue after all fields populated
        reg.continueButton.click();

        Assert.assertTrue("Contact Info Accepted OK", reg.stepTitle.getText().contains(resource.getValue("vendor_title_step_dem")));
    }

    @Test(priority = 7)
    public void DemographicInfoTest() {

        // select minority assignments
        reg.demoSmallOwnedCheckbox.click();
        reg.demoVeteranOwnedCheckbox.click();
        reg.demoWomenOwnedCheckbox.click();
        new Select(reg.demoMinorityDrop).selectByVisibleText(resource.getValue("vendor_minority"));

        // click Continue after all fields populated
        reg.continueButton.click();

        Assert.assertTrue("Demographic Info Accepted OK", reg.stepTitle.getText().contains(resource.getValue("vendor_title_step_ebo")));
    }

    @Test(priority = 8)
    public void SelectBuyerTest() {

        // add new supplier to Perfect City
        reg.buyerPerfectCityCheckbox.click();

        // click Continue after all fields populated
        reg.continueButton.click();

        Assert.assertTrue("Select target buyer OK", reg.stepTitle.getText().contains(resource.getValue("vendor_title_step_btc")));

        reg.buyerTandCAcceptRadio.click();
        reg.continueButton.click();

        Assert.assertTrue("Accept buyer T&Cs OK", reg.stepTitle.getText().contains(resource.getValue("vendor_title_step_usr")));

    }

    @Test(priority = 9)
    public void SelectUsernameTest() {

        // username is unique and set to our SSN/FEIN number for this test
        vendorUsername = vendorNum.getNumber();
        reg.userNameEdit.sendKeys(vendorUsername);

        // enter password and confirmation
        reg.userPasswordEdit.sendKeys(resource.getValue("vendor_password"));
        reg.userPasswordConfirmEdit.sendKeys(resource.getValue("vendor_password"));

        // click Continue after all fields populated
        reg.continueButton.click();

        Assert.assertTrue("Username/Password created OK", reg.stepTitle.getText().contains(resource.getValue("vendor_title_step_fin")));

        // click Continue after summary page shown
        reg.continueButton.click();

        // AT THIS POINT you should be logged in as the vendor
        // Wait for login username/password fields
        browser.waitForElementToAppear(login.txtUsername);
        login.txtUsername.clear();
    }

    @Test(priority = 10)
    public void ConfirmVendorLogin() {

        // log in as the new vendor. Username is our SSN/FEIN number
        vendorUsername = vendorNum.getNumber();
        login.loginAsUser(vendorUsername, resource.getValue("vendor_password"));

        browser.waitForElementToAppear(vendor.topNav);

        // If logged in properly, the username should be the top menu item
        String FullName = (resource.getValue("vendor_firstname") + " " + resource.getValue("vendor_lastname"));
        Assert.assertTrue("Vendor logged in OK", vendor.topUsername.getText().contains(FullName));

        vendor.vendorLogout();

    }

}
