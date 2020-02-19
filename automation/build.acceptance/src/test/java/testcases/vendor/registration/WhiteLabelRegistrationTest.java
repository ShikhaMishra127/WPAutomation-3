package testcases.vendor.registration;

import org.openqa.selenium.support.ui.Select;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageobjects.common.CommodityPickerPOM;
import pageobjects.common.LoginPagePOM;
import pageobjects.vendor.common.VendorNavBarPOM;
import pageobjects.vendor.common.VendorProfileVerificationPOM;
import pageobjects.vendor.registration.RegWhiteLabelPOM;
import utilities.common.Browser;
import utilities.common.ResourceLoader;
import utilities.common.TestRailReference;
import utilities.common.UniqueID;

public class WhiteLabelRegistrationTest {

    Browser browser;
    ResourceLoader resource = new ResourceLoader("data/registration");
    RegWhiteLabelPOM reg;
    LoginPagePOM login;
    VendorProfileVerificationPOM profile;
    VendorNavBarPOM vendor;
    CommodityPickerPOM commodity;

    String vendor_name;
    String vendor_fullname;
    String vendor_username;
    String vendor_fullphonenum;
    String vendor_fullfaxnum;

    UniqueID vendorNum = new UniqueID(UniqueID.IDType.SSNFEIN);

    @BeforeClass
    public void setup(ITestContext testContext) {

        browser = new Browser(testContext);
        String wlBaseURL = resource.getValue("wl_baseurl");
        reg = new RegWhiteLabelPOM(browser);
        login = new LoginPagePOM(browser);
        profile = new VendorProfileVerificationPOM(browser);
        vendor = new VendorNavBarPOM(browser);
        commodity = new CommodityPickerPOM(browser);

        // username is always the FEIN/SSN number we generated for the test
        vendor_username = vendorNum.getNumber();

        vendor_fullphonenum=resource.getValue("vendor_phone_pt1") + resource.getValue("vendor_phone_pt2") +
                resource.getValue("vendor_phone_pt3");

        vendor_fullfaxnum = resource.getValue("vendor_fax_pt1") + resource.getValue("vendor_fax_pt2") +
                resource.getValue("vendor_fax_pt3");

        vendor_fullname = resource.getValue("vendor_firstname") + " " + resource.getValue("vendor_lastname");

        browser.getDriver().get(wlBaseURL);

    }

    @Test(priority = 1)
    public void BannerTest() {

        browser.switchToFrame(reg.regFrame);
        browser.waitForElementToAppear(reg.stepHeader);
        browser.Assert("Doing Business with MO banner OK", reg.stepHeader.getText(), resource.getValue("vendor_wl_title_step_tcs"));

    }

    @Test(priority = 2)
    public void TsAndCsTest() {

        // initially decline terms and conditions
        browser.UncheckCheckbox(reg.termsCheckbox);
        browser.clickWhenAvailable(reg.nextButton);
        browser.waitForElementToAppear(reg.termsErrorMessage);
        browser.Assert("Terms Declined OK", reg.termsErrorMessage.isDisplayed());

        // now accept T&Cs and continue
        browser.CheckCheckbox(reg.termsCheckbox);
        browser.clickWhenAvailable(reg.nextButton);

        browser.waitForElementToAppear(reg.orgInfoTitle);
        browser.Assert("Org Info banner OK", reg.orgInfoTitle.getText(), resource.getValue("vendor_wl_title_step_org"));
    }

    @Test(priority = 3)
    public void DuplicateFEINTest() {

        // set FEIN to known duplicate value
        UniqueID existingVendor = new UniqueID(resource.getValue("duplicate_ssnfein"));

        browser.clickWhenAvailable(reg.orgFeinEdit1);
        browser.sendKeysWhenAvailable(reg.orgFeinEdit1, existingVendor.getFeinPt1());
        browser.sendKeysWhenAvailable(reg.orgFeinEdit2, existingVendor.getFeinPt2());
        browser.clickWhenAvailable(reg.orgFeinConfirmEdit1);

        browser.waitForElementToAppear(reg.orgDuplicateFeinError);
        browser.Assert("Duplicate FEIN message OK", reg.orgDuplicateFeinError.isDisplayed());

        // reset FEIN
        reg.orgFeinEdit1.clear();
        reg.orgFeinEdit2.clear();
    }

    @Test(priority = 4)
    public void DuplicateSsnTest() {

        // set SSN to known duplicate value
        UniqueID existingVendor = new UniqueID(resource.getValue("duplicate_ssnfein"));

        browser.clickWhenAvailable(reg.orgSsnEdit1);
        browser.sendKeysWhenAvailable(reg.orgSsnEdit1, existingVendor.getSSNPt1());
        browser.sendKeysWhenAvailable(reg.orgSsnEdit2, existingVendor.getSSNPt2());
        browser.sendKeysWhenAvailable(reg.orgSsnEdit3, existingVendor.getSSNPt3());
        browser.clickWhenAvailable(reg.orgSsnConfirmEdit1);

        browser.waitForElementToAppear(reg.orgDuplicateSsnError);
        browser.Assert("Duplicate SSN message OK", reg.orgDuplicateSsnError.isDisplayed());

        //reset SSN
        reg.orgSsnEdit1.clear();
        reg.orgSsnEdit2.clear();
        reg.orgSsnEdit3.clear();
    }

    @Test(priority = 5)
    public void OrgInfoPageTest() {

        browser.waitForElementToAppear(reg.orgInfoTitle);
        browser.Assert("Organization Info banner OK", reg.orgInfoTitle.getText(), resource.getValue("vendor_wl_title_step_org"));

        browser.sendKeysWhenAvailable(reg.orgFeinEdit1, vendorNum.getFeinPt1());
        browser.sendKeysWhenAvailable(reg.orgFeinEdit2, vendorNum.getFeinPt2());
        browser.sendKeysWhenAvailable(reg.orgFeinConfirmEdit1, vendorNum.getFeinPt1());
        browser.sendKeysWhenAvailable(reg.orgFeinConfirmEdit2, vendorNum.getFeinPt2());

        vendor_name = resource.getValue("vendor_base_name") + " " + vendorNum.getNumber();
        browser.sendKeysWhenAvailable(reg.orgCompanyName, vendor_name);
        browser.sendKeysWhenAvailable(reg.orgAddressEdit1, resource.getValue("vendor_address1"));
        browser.sendKeysWhenAvailable(reg.orgAddressEdit2, resource.getValue("vendor_address2"));
        browser.sendKeysWhenAvailable(reg.orgCityEdit, resource.getValue("vendor_city"));
        new Select(reg.orgStateDrop).selectByVisibleText(resource.getValue("vendor_state"));
        browser.sendKeysWhenAvailable(reg.orgZipEdit, resource.getValue("vendor_zip"));
        new Select(reg.orgBusinessTypeDrop).selectByVisibleText(resource.getValue("vendor_wl_business_type"));

        browser.clickWhenAvailable(reg.orgValidateAddressLink);
        browser.clickWhenAvailable(reg.orgDiversitySectionRadio);
        browser.clickWhenAvailable(reg.orgDiversityWbeRadio);
        browser.clickWhenAvailable(reg.orgDiversityDbeCheckbox);
        browser.clickWhenAvailable(reg.orgEmergencySectionRadio);

        browser.sendKeysWhenAvailable(reg.orgEmergencyNameEdit, vendor_fullname);
        browser.sendKeysWhenAvailable(reg.orgEmergencyEmailEdit, resource.getValue("vendor_email"));
        browser.sendKeysWhenAvailable(reg.orgEmergencyEmailConfirmEdit, resource.getValue("vendor_email"));

        browser.sendKeysWhenAvailable(reg.orgEmergencyPhoneEdit, vendor_fullphonenum);
        browser.sendKeysWhenAvailable(reg.orgEmergencyPhoneConfirmEdit, vendor_fullphonenum);

        browser.clickWhenAvailable(reg.nextButton);

    }

    @Test(priority = 6)
    public void ContactInfoPageTest() {

        browser.waitForElementToAppear(reg.contactInfoTitle);
        browser.Assert("Contact Info banner OK", reg.contactInfoTitle.getText(), resource.getValue("vendor_wl_title_step_con"));

        browser.waitForPageLoad();

        browser.sendKeysWhenAvailable(reg.contactFirstNameEdit, resource.getValue("vendor_firstname"));
        browser.sendKeysWhenAvailable(reg.contactLastNameEdit, resource.getValue("vendor_lastname"));
        browser.sendKeysWhenAvailable(reg.contactJobEdit, resource.getValue("vendor_base_name"));
        browser.sendKeysWhenAvailable(reg.contactPhoneEdit, vendor_fullphonenum);
        browser.sendKeysWhenAvailable(reg.contactPhoneConfirmEdit, vendor_fullphonenum);
        browser.sendKeysWhenAvailable(reg.contactFaxEdit, vendor_fullfaxnum);
        browser.sendKeysWhenAvailable(reg.contactFaxConfirmEdit, vendor_fullfaxnum);
        browser.sendKeysWhenAvailable(reg.contactEmailEdit, resource.getValue("vendor_email"));
        browser.sendKeysWhenAvailable(reg.contactEmailConfirmEdit, resource.getValue("vendor_email"));
        browser.sendKeysWhenAvailable(reg.contactUsernameEdit, vendor_username);
        browser.sendKeysWhenAvailable(reg.contactPasswordEdit, resource.getValue("vendor_password"));
        browser.sendKeysWhenAvailable(reg.contactPasswordConfirmEdit, resource.getValue("vendor_password"));

        browser.clickWhenAvailable(reg.nextButton);

    }

    @Test(priority = 7)
    public void PaymentTypePageTest() {

        browser.waitForElementToAppear(reg.paymentInfoTitle);
        browser.Assert("Payment Type banner OK", reg.paymentInfoTitle.getText(), resource.getValue("vendor_wl_title_step_pay"));

        browser.clickWhenAvailable(reg.nextButton);

    }

    @Test(priority = 8)
    public void CommodityPageTest() {

        browser.waitForElementToAppear(reg.commodityInfoTitle);
        browser.Assert("Commodity banner OK", reg.commodityInfoTitle.getText(), resource.getValue("vendor_wl_title_step_com"));

        // Add a list of comma-separated commodity codes to profile
        commodity.addCodes(resource.getValue("vendor_wl_commodity_codes"));

        // submit registration
        browser.clickWhenAvailable(reg.nextButton);
    }

    @Test(priority = 9)
    @TestRailReference(id = 3598)
    public void RegistrationCompleteTest() {

        browser.waitForElementToAppear(reg.finalTakeMeToWPButton);

        browser.Assert("Username displayed OK", reg.finalUsername.getText(), vendor_username);

        browser.Log("Supplier " + vendor_name + " created.");
        browser.Log("Supplier username is " + vendor_username);
    }

    @Test(priority = 10)
    @TestRailReference(id = 3598)
    public void ConfirmVendorLogin() {

        browser.get(browser.baseUrl);
        browser.waitForElementToAppear(login.btnLogin);
        login.loginAsUser(vendor_username, resource.getValue("vendor_password"));

        // First-time vendors must accept terms and conditions before logging in
        browser.clickWhenAvailable(login.vendorAcceptTermsButton);

        // First time vendors also get a confirmation page for FEIN, etc.
        browser.clickWhenAvailable(profile.okButton);

        // If logged in properly, the username should be the top menu item
        browser.waitForElementToAppear(vendor.topNav);

        browser.Assert("Vendor logged in OK", vendor.topUsername.getText(), vendor_fullname);

        browser.Log("Vendor logged into supplier portal.");

        vendor.vendorLogout();
        browser.close();

    }

}
