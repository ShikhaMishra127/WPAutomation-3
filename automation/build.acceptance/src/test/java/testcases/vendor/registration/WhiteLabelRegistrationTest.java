package testcases.vendor.registration;

import org.junit.Assert;
import org.openqa.selenium.support.ui.Select;
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
    ResourceLoader resource = new ResourceLoader("data/wl-registration");
    RegWhiteLabelPOM reg;

    ssnFein vendorNum = new ssnFein();

    @BeforeClass
    public void setup() throws IOException {

        browser = new Browser();
        String baseURL = resource.getValue("baseurl");
        reg = new RegWhiteLabelPOM(browser);

        browser.getDriver().get(baseURL);

    }

    @Test(priority = 1)
    public void startTest() {

        browser.switchToFrame(reg.regFrame);
        browser.waitForPageLoad();
        Assert.assertTrue("Doing Business with MO banner OK", reg.stepHeader.getText().contains("Doing Business"));
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
        Assert.assertTrue("Org Info banner OK", reg.orgInfoTitle.getText().contains("Organization Information"));
    }

    @Test(priority = 3)
    public void DuplicateFEINTest() {

        // set FEIN to known duplicate value
        reg.orgFeinEdit1.click();
        reg.orgFeinEdit1.sendKeys("11");
        reg.orgFeinEdit2.sendKeys("1111111");
        reg.orgFeinConfirmEdit1.click();

        Assert.assertTrue("Duplicate FEIN message OK", reg.orgDuplicateFeinError.isDisplayed());

        // reset FEIN
        reg.orgFeinEdit1.clear();
        reg.orgFeinEdit2.clear();
    }

    @Test(priority = 4)
    public void DuplicateSsnTest() {

        // set SSN to known duplicate value
        reg.orgSsnEdit1.click();
        reg.orgSsnEdit1.sendKeys("111");
        reg.orgSsnEdit2.sendKeys("11");
        reg.orgSsnEdit3.sendKeys("1111");
        reg.orgSsnConfirmEdit1.click();

        Assert.assertTrue("Duplicate SSN message OK", reg.orgDuplicateSsnError.isDisplayed());

        //reset SSN
        reg.orgSsnEdit1.clear();
        reg.orgSsnEdit2.clear();
        reg.orgSsnEdit3.clear();
    }

    @Test(priority = 5)
    public void OrgInfoPageTest() {
/*
        reg.orgFeinEdit1.sendKeys(vendorNum.getFeinPt1());
        reg.orgFeinEdit2.sendKeys(vendorNum.getFeinPt2());
        reg.orgFeinConfirmEdit1.sendKeys(vendorNum.getFeinPt1());
        reg.orgFeinConfirmEdit2.sendKeys(vendorNum.getFeinPt2());
        */
        browser.waitForElementToAppear(reg.orgInfoTitle);
        Assert.assertTrue("Organization Info banner OK", reg.orgInfoTitle.getText().contains("Organization Information"));

        reg.orgFeinEdit1.sendKeys("01");
        reg.orgFeinEdit2.sendKeys("2120190");
        reg.orgFeinConfirmEdit1.sendKeys("01");
        reg.orgFeinConfirmEdit2.sendKeys("2120190");

        //reg.orgCompanyName.sendKeys("Automated Supplier " + vendorNum.getNumber());
        reg.orgCompanyName.sendKeys("Automated Supplier 012120190");
        reg.orgAddressEdit1.sendKeys("123 Industrial Pkwy");
        reg.orgAddressEdit2.sendKeys("Suite 200");
        reg.orgCityEdit.sendKeys("Newport News");
        new Select(reg.orgStateDrop).selectByVisibleText("Virginia");
        reg.orgZipEdit.sendKeys("23606");
        new Select(reg.orgBusinessTypeDrop).selectByVisibleText("Individual/Sole Proprietor");

        browser.ClickWhenClickable(reg.orgDiversitySectionRadio);
        browser.ClickWhenClickable(reg.orgDiversityWbeRadio);
        browser.ClickWhenClickable(reg.orgDiversityDbeCheckbox);

        browser.ClickWhenClickable(reg.orgEmergencySectionRadio);
        reg.orgEmergencyNameEdit.sendKeys("Andrew Comenzo");
        reg.orgEmergencyPhoneEdit.sendKeys("8005551212");
        reg.orgEmergencyPhoneConfirmEdit.sendKeys("8005551212");
        reg.orgEmergencyEmailEdit.sendKeys("andrew.comenzo@perfect.com");
        reg.orgEmergencyEmailConfirmEdit.sendKeys("andrew.comenzo@perfect.com");

        browser.ClickWhenClickable(reg.orgValidateAddressLink);
        reg.nextButton.click();


    }

    @Test(priority = 6)
    public void ContactInfoPageTest() {

        browser.waitForElementToAppear(reg.contactInfoTitle);
        Assert.assertTrue("Contact Info banner OK", reg.contactInfoTitle.getText().contains("Organization Contact Information"));

        reg.contactFirstNameEdit.sendKeys("Zelda");
        reg.contactLastNameEdit.sendKeys("Lipinski");
        reg.contactJobEdit.sendKeys("Automated User");
        reg.contactPhoneEdit.sendKeys("1234567890");
        reg.contactPhoneConfirmEdit.sendKeys("1234567890");
        reg.contactFaxEdit.sendKeys("1234567890");
        reg.contactFaxConfirmEdit.sendKeys("1234567890");
        reg.contactEmailEdit.sendKeys("andrew.comenzo@perfect.com");
        reg.contactEmailConfirmEdit.sendKeys("andrew.comenzo@perfect.com");
        reg.contactUsernameEdit.sendKeys("012120190");
        reg.contactPasswordEdit.sendKeys("Xxxxxx1!");
        reg.contactPasswordConfirmEdit.sendKeys("Xxxxxx1!");

        reg.nextButton.click();

    }

    @Test(priority = 7)
    public void PaymentTypePageTest() {

        browser.waitForElementToAppear(reg.paymentInfoTitle);
        Assert.assertTrue("Payment Type banner OK", reg.paymentInfoTitle.getText().contains("Organization Payment Information"));

        browser.ClickWhenClickable(reg.nextButton);

    }

    @Test(priority = 8)
    public void CommodityPageTest() {

        browser.waitForElementToAppear(reg.commodityInfoTitle);
        Assert.assertTrue("Commodity banner OK", reg.commodityInfoTitle.getText().contains("Select Commodity/Service Codes"));

        // Add commodity codes to profile (level 2 & 4)
        reg.selectCommodityByCode("51290000");
        reg.selectCommodityByCode("77111503");

        // submit registration
        //reg.nextButton.click();
    }

}
