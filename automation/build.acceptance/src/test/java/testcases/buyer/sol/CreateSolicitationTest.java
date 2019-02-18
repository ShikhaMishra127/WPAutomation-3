package testcases.buyer.sol;

//import com.sun.java.swing.ui.CommonMenuBar;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageobjects.buyer.sol.NewSolicitationPOM;
import pageobjects.common.BuyerNavBarPOM;
import pageobjects.common.CommodityPickerPOM;
import pageobjects.common.LoginPagePOM;
import utilities.common.Browser;
import utilities.common.ResourceLoader;
import utilities.common.UniqueID;

import java.io.IOException;

public class CreateSolicitationTest {

    Browser browser;
    ResourceLoader resource;
    CommodityPickerPOM commodity;
    BuyerNavBarPOM navbar;
    LoginPagePOM login;
    NewSolicitationPOM sol;

    CreateSolicitationTest() throws IOException {   }

    @BeforeClass
    public void setup() throws IOException {

        browser = new Browser();
        resource = new ResourceLoader("data/solicitation");
        commodity = new CommodityPickerPOM(browser);
        navbar = new BuyerNavBarPOM(browser);
        login = new LoginPagePOM(browser);
        sol = new NewSolicitationPOM(browser);

        browser.getDriver().get(browser.baseUrl);

        login.loginAsBuyer();
    }

    @Test(priority = 1)
    public void HeaderStepTest() {

        navbar.selectDropDownItem("Solicitations", "Create Informal Solicitation" );

        UniqueID solNum = new UniqueID(UniqueID.IDType.DATE);
        String solName = "Automated Sol " + solNum.getNumber();

        Assert.assertTrue("Solicitation Step HEADER OK", sol.stepTitle.getText().contains("Header"));

        sol.headBidTitleEdit.sendKeys(solName);
        sol.headBidNumberEdit.clear();
        sol.headBidNumberEdit.sendKeys(solNum.getNumber());
        sol.headDescriptionEdit.sendKeys("This is a long description for " + solName );

        new Select(sol.headSolPublicTypeDrop).selectByValue("Y");
        new Select(sol.headInvitationTypeDrop).selectByIndex(1);

        sol.headEstTotalEdit.sendKeys("1500.00");

        sol.headSelectCatButton.click();

        browser.waitForElementToAppear(commodity.commoditySearchButton);

        commodity.selectCommodityByCode("05240");
        commodity.selectCommodityByCode("44505");

        commodity.commodityCloseButton.click();

        // wait until we load the page after picking commodities
        browser.waitForElementToAppear(sol.headStartDateEdit);

        // set our Solicitation start date to 5 minutes from NOW
        browser.InjectJavaScript("arguments[0].value=arguments[1]", sol.headStartDateEdit, sol.solDatePlusMin(5));

        // set Collaboration dates if not already set
        if (!sol.headCollaborationCheckbox.isSelected()) {
            sol.headCollaborationCheckbox.click();
        }

        // set Collaboration date to 6 minutes from now (must be after sol start)
        browser.InjectJavaScript("arguments[0].value=arguments[1]", sol.headCollaborationStartDateEdit, sol.solDatePlusMin(6));

        sol.nextButton.click();

    }

    @Test(priority = 2)
    public void RequirementsStepTest() {

        Assert.assertTrue("Solicitation Step REQUIREMENTS OK", sol.stepTitle.getText().contains("Requirements"));

        browser.clickWhenAvailable(sol.requireNextButton);

    }

    @Test(priority = 3)
    public void QuestionnaireStepTest() {

        Assert.assertTrue("Solicitation Step QUESTIONNAIRE OK", sol.stepTitle.getText().contains("Questionnaire"));

        browser.clickWhenAvailable(sol.nextButton);

    }

    @Test(priority = 4)
    public void AttachmentsStepTest() {

        Assert.assertTrue("Solicitation Step ATTACHMENTS OK", sol.stepTitle.getText().contains("Attachments"));

        // go to the Upload From Document library
        browser.clickWhenAvailable(sol.docsUploadFromLibButton);

        // set focus to pop-up
        String parentWindow = browser.driver.getWindowHandle();
        browser.SwitchToPopUp(parentWindow);

        // click on the 1st file in the list (assume that there is at least one file)
        browser.clickWhenAvailable(sol.docsUploadFirstFileCheckbox);
        sol.docsUploadSaveButton.click();

        // switch focus back to main window
        browser.switchTo().window(parentWindow);

        // wait for header from list of attached files to appear
        browser.waitForElementToAppear(sol.docsFileUploadHeader);

        sol.nextButton.click();

    }

    @Test(priority = 5)
    public void ItemSpecStepTest() {

        Assert.assertTrue("Solicitation Step ITEM SPECS OK", sol.stepTitle.getText().contains("Item Specs"));

        // Add two groups to the solicitation, verify groups appear in drop-down
        sol.itemCreateGroup("Goods");
        sol.itemCreateGroup("Services");

        Assert.assertTrue("Group DropDown Exists OK", sol.itemGroupDropDown.isEnabled() );

        sol.itemCreateItem(
                "Snow Plow Services",
                "SP0001",
                "ABC Corp.",
                "MPN239222",
                "This is a long description for snow plow services. Includes de-icing and removal of snow.",
                "05240",
                "4",
                "Services"
        );
        sol.itemCreateItem(
                "Flex Heliobolts",
                "AT0001",
                "ABC Corp.",
                "MPN239222",
                "This is a long description for the heliobolt item",
                "44505",
                "2",
                "Goods"
        );

        sol.itemPageNextButton.click();

    }

    @Test(priority = 6)
    public void SupplierSelectStepTest() {

        Assert.assertTrue("Solicitation Step SELECT SUPPLIERS OK", sol.stepTitle.getText().contains("Suppliers"));

        // zip between tabs to clear out pre-selected commodities in search
        browser.clickWhenAvailable(sol.supplierSelectedTab);
        browser.clickWhenAvailable(sol.supplierSearchButton);
        browser.clickWhenAvailable(sol.supplierSelectedTab);
        browser.clickWhenAvailable(sol.supplierSearchButton);

        sol.supplierSearchName.sendKeys("AutoSupplier");

        sol.supplierLookupButton.click();

        String checkboxxpath = "//a[contains(text(),'" +
                "AutoSupplier" +
                "')]/parent::*/parent::*/parent::*/parent::*/parent::*/preceding-sibling::*/input[@title='select']";

        // wait for supplier results page with our checkbox
        browser.waitForElementToAppear(By.xpath(checkboxxpath));

        // find the checkbox of the found code
        WebElement checkbox = sol.supplierSearchResultTable.findElement(By.xpath(checkboxxpath));

        // click the correct code
        browser.clickWhenAvailable(checkbox);

        sol.nextButton.click();

    }

    @Test(priority = 7)
    public void SupplierSummaryStepTest() {

        Assert.assertTrue("Solicitation Step SUMMARY OK", sol.stepTitle.getText().contains("Summary"));

        // click on Submit Solicitation button
        browser.clickWhenAvailable(sol.summarySubmitButton);

        // click OK on alert asking to create solicitation
        Alert alert = browser.switchTo().alert();
        alert.accept();

        // click OK button after sol submitted to return to sol list screen
        browser.clickWhenAvailable(sol.summaryOKAfterSubmitButton);

    }
}
