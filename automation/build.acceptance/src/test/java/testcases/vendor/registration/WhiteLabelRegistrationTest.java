package testcases.vendor.registration;

import org.junit.Assert;
import org.openqa.selenium.support.ui.Select;
import pageobjects.common.LoginPagePOM;
import pageobjects.vendor.common.VendorNavBarPOM;
import pageobjects.vendor.registration.RegWhiteLabelPOM;
import utilities.common.Browser;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.common.ResourceLoader;
import utilities.common.ssnFein;

import java.io.IOException;
import java.nio.channels.SelectableChannel;

public class WhiteLabelRegistrationTest {

    Browser browser;
    ResourceLoader resource = new ResourceLoader("data/registration");
    RegWhiteLabelPOM reg;
    LoginPagePOM login;
    VendorNavBarPOM vendor;

    String vendor_username;
    String vendor_fullphonenum;
    String vendor_fullfaxnum;

    ssnFein vendorNum = new ssnFein();

    @BeforeClass
    public void setup() throws IOException {

        browser = new Browser();
        String baseURL = resource.getValue("wl_baseurl");
        reg = new RegWhiteLabelPOM(browser);
        login = new LoginPagePOM(browser);
        vendor = new VendorNavBarPOM(browser);

        // username is always the FEIN/SSN number we generated for the test
        vendor_username = vendorNum.getNumber();

        vendor_fullphonenum=resource.getValue("vendor_phone_pt1") + resource.getValue("vendor_phone_pt2") +
                resource.getValue("vendor_phone_pt3");

        vendor_fullfaxnum=resource.getValue("vendor_fax_pt1") + resource.getValue("vendor_fax_pt2") +
                resource.getValue("vendor_fax_pt3");

       browser.getDriver().get(baseURL);

    }

    @Test(priority = 1)
    public void startTest() {

        browser.switchToFrame(reg.regFrame);
        browser.waitForPageLoad();
        Assert.assertTrue("Doing Business with MO banner OK", reg.stepHeader.getText().contains(resource.getValue("vendor_wl_title_step_tcs")));

    }

    @Test(priority = 2)
    public void TsAndCsTest() {

        // initially decline terms and conditions
        browser.UncheckCheckbox(reg.termsCheckbox);
        reg.nextButton.click();
        Assert.assertTrue("Terms Declined OK", reg.termsErrorMessage.isDisplayed());

        // now accept T&Cs and continue
        browser.CheckCheckbox(reg.termsCheckbox);
        reg.nextButton.click();

        browser.waitForElementToAppear(reg.orgInfoTitle);
        Assert.assertTrue("Org Info banner OK", reg.orgInfoTitle.getText().contains(resource.getValue("vendor_wl_title_step_org")));
    }

    @Test(priority = 3)
    public void DuplicateFEINTest() {

        // set FEIN to known duplicate value
        ssnFein existingVendor = new ssnFein(resource.getValue("duplicate_ssnfein"));

        reg.orgFeinEdit1.click();
        reg.orgFeinEdit1.sendKeys(existingVendor.getFeinPt1());
        reg.orgFeinEdit2.sendKeys(existingVendor.getFeinPt2());
        reg.orgFeinConfirmEdit1.click();

        Assert.assertTrue("Duplicate FEIN message OK", reg.orgDuplicateFeinError.isDisplayed());

        // reset FEIN
        reg.orgFeinEdit1.clear();
        reg.orgFeinEdit2.clear();
    }

    @Test(priority = 4)
    public void DuplicateSsnTest() {

        // set SSN to known duplicate value
        ssnFein existingVendor = new ssnFein(resource.getValue("duplicate_ssnfein"));

        reg.orgSsnEdit1.click();
        reg.orgSsnEdit1.sendKeys(existingVendor.getSSNPt1());
        reg.orgSsnEdit2.sendKeys(existingVendor.getSSNPt2());
        reg.orgSsnEdit3.sendKeys(existingVendor.getSSNPt3());
        reg.orgSsnConfirmEdit1.click();

        Assert.assertTrue("Duplicate SSN message OK", reg.orgDuplicateSsnError.isDisplayed());

        //reset SSN
        reg.orgSsnEdit1.clear();
        reg.orgSsnEdit2.clear();
        reg.orgSsnEdit3.clear();
    }

    @Test(priority = 5)
    public void OrgInfoPageTest() {

        browser.waitForElementToAppear(reg.orgInfoTitle);
        Assert.assertTrue("Organization Info banner OK", reg.orgInfoTitle.getText().contains(resource.getValue("vendor_wl_title_step_org")));

        reg.orgFeinEdit1.sendKeys(vendorNum.getFeinPt1());
        reg.orgFeinEdit2.sendKeys(vendorNum.getFeinPt2());
        reg.orgFeinConfirmEdit1.sendKeys(vendorNum.getFeinPt1());
        reg.orgFeinConfirmEdit2.sendKeys(vendorNum.getFeinPt2());

        //reg.orgCompanyName.sendKeys("Automated Supplier " + vendorNum.getNumber());
        reg.orgCompanyName.sendKeys(resource.getValue("vendor_base_name")+" "+vendorNum.getNumber());
        reg.orgAddressEdit1.sendKeys(resource.getValue("vendor_address1"));
        reg.orgAddressEdit2.sendKeys(resource.getValue("vendor_address2"));
        reg.orgCityEdit.sendKeys(resource.getValue("vendor_city"));
        new Select(reg.orgStateDrop).selectByVisibleText(resource.getValue("vendor_state"));
        reg.orgZipEdit.sendKeys(resource.getValue("vendor_zip"));
        new Select(reg.orgBusinessTypeDrop).selectByVisibleText(resource.getValue("vendor_wl_business_type"));

        browser.ClickWhenClickable(reg.orgValidateAddressLink);

        browser.ClickWhenClickable(reg.orgDiversitySectionRadio);
        browser.ClickWhenClickable(reg.orgDiversityWbeRadio);
        browser.ClickWhenClickable(reg.orgDiversityDbeCheckbox);

        browser.ClickWhenClickable(reg.orgEmergencySectionRadio);

        reg.orgEmergencyNameEdit.sendKeys(resource.getValue("vendor_firstname") + " " + resource.getValue("vendor_lastname"));
        reg.orgEmergencyEmailEdit.sendKeys(resource.getValue("vendor_email"));
        reg.orgEmergencyEmailConfirmEdit.sendKeys(resource.getValue("vendor_email"));

        reg.orgEmergencyPhoneEdit.sendKeys(vendor_fullphonenum);
        reg.orgEmergencyPhoneConfirmEdit.sendKeys(vendor_fullphonenum);

        reg.nextButton.click();

    }

    @Test(priority = 6)
    public void ContactInfoPageTest() {

        browser.waitForElementToAppear(reg.contactInfoTitle);
        Assert.assertTrue("Contact Info banner OK", reg.contactInfoTitle.getText().contains(resource.getValue("vendor_wl_title_step_con")));

        reg.contactFirstNameEdit.sendKeys("Zelda");
        reg.contactLastNameEdit.sendKeys("Lipinski");
        reg.contactJobEdit.sendKeys("Automated User");
        reg.contactPhoneEdit.sendKeys(vendor_fullphonenum);
        reg.contactPhoneConfirmEdit.sendKeys(vendor_fullphonenum);
        reg.contactFaxEdit.sendKeys(vendor_fullfaxnum);
        reg.contactFaxConfirmEdit.sendKeys(vendor_fullfaxnum);
        reg.contactEmailEdit.sendKeys(resource.getValue("vendor_email"));
        reg.contactEmailConfirmEdit.sendKeys(resource.getValue("vendor_email"));
        reg.contactUsernameEdit.sendKeys(vendor_username);
        reg.contactPasswordEdit.sendKeys(resource.getValue("vendor_password"));
        reg.contactPasswordConfirmEdit.sendKeys(resource.getValue("vendor_password"));

        reg.nextButton.click();

    }

    @Test(priority = 7)
    public void PaymentTypePageTest() {

        browser.waitForElementToAppear(reg.paymentInfoTitle);
        Assert.assertTrue("Payment Type banner OK", reg.paymentInfoTitle.getText().contains(resource.getValue("vendor_wl_title_step_pay")));

        browser.ClickWhenClickable(reg.nextButton);

    }

    @Test(priority = 8)
    public void CommodityPageTest() {

        browser.waitForElementToAppear(reg.commodityInfoTitle);
        Assert.assertTrue("Commodity banner OK", reg.commodityInfoTitle.getText().contains(resource.getValue("vendor_wl_title_step_com")));

        // Add a list of comma-separated commodity codes to profile
        String[] values = resource.getValue("vendor_wl_commodity_codes").split(",");

        // for each code, search for and then add code to vendor's profile
        for (String code : values) {
            reg.selectCommodityByCode(code);
        }

        // submit registration
        reg.nextButton.click();
    }

    @Test(priority = 9)
    public void RegistrationCompleteTest() {

        browser.waitForElementToAppear(reg.finalTakeMeToWPButton);

        Assert.assertTrue("Username displayed OK", reg.finalUsername.getText().contains(vendor_username));

        // click button to take us to the Webprocure Login page
        reg.finalTakeMeToWPButton.click();

    }

    @Test(priority = 10)
    public void ConfirmVendorLogin() {

        browser.waitForElementToAppear(login.btnLogin);
        login.loginAsUser(vendor_username, resource.getValue("vendor_password"));

        browser.waitForElementToAppear(vendor.topNav);

        // If logged in properly, the username should be the top menu item
        String FullName = (resource.getValue("vendor_firstname") + " " + resource.getValue("vendor_lastname"));
        Assert.assertTrue("Vendor logged in OK", vendor.topUsername.getText().contains(FullName));

        vendor.vendorLogout();

    }
}
