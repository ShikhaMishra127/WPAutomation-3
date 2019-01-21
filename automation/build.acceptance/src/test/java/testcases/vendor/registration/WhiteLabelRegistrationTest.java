package testcases.vendor.registration;

import org.junit.Assert;
import pageobjects.vendor.registration.RegWhiteLabelPOM;
import utilities.common.Browser;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.common.ResourceLoader;
import utilities.common.ssnFein;

import java.io.IOException;

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
        //regFrame
        browser.switchToFrame(reg.regFrame);
        browser.waitForPageLoad();
        System.out.format("TITLE: %s%n", reg.stepHeader.getText());
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
    public void ValidOrgInfoTest() {

        reg.orgFeinEdit1.sendKeys(vendorNum.getFeinPt1());
        reg.orgFeinEdit2.sendKeys(vendorNum.getFeinPt2());
        reg.orgFeinConfirmEdit1.sendKeys(vendorNum.getFeinPt1());
        reg.orgFeinConfirmEdit2.sendKeys(vendorNum.getFeinPt2());



    }


    }
