package testcases.buyer.sol;

import main.java.framework.Solicitation;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import pageobjects.buyer.sol.NewSolicitationPOM;
import pageobjects.common.BuyerNavBarPOM;
import pageobjects.common.CommodityPickerPOM;
import pageobjects.common.LoginPagePOM;
import utilities.common.Browser;
import utilities.common.ResourceLoader;
import utilities.common.UniqueID;

public class SolCreator {

    Browser browser;
    ResourceLoader resource;
    CommodityPickerPOM commodity;
    BuyerNavBarPOM navbar;
    LoginPagePOM login;
    NewSolicitationPOM sol;
    Solicitation newsol;

    SolCreator() {   }


    public Solicitation CreateSolicitation(String soldata) {

        setup(soldata);
        browser.getDriver().get(browser.baseUrl);
        login.loginAsBuyer();

        HeaderStep();
        RequirementsAndQuestionnaireStep();
        AttachmentsStep();
        ItemSpecStep();
        SupplierSelectStep();
        SolicitationSummaryStep();

        navbar.logout();
        browser.close();

        return newsol;
    }

    private void setup(String soldata) {

        browser = new Browser();
        resource = new ResourceLoader(soldata);
        commodity = new CommodityPickerPOM(browser);
        navbar = new BuyerNavBarPOM(browser);
        login = new LoginPagePOM(browser);
        sol = new NewSolicitationPOM(browser);
        newsol = new Solicitation();

    }

    private void HeaderStep() {

        navbar.selectDropDownItem(resource.getValue("navbar_headitem"), resource.getValue("navbar_subitem") );

        UniqueID solNum = new UniqueID(UniqueID.IDType.DATE);
        newsol.setSolNumber(solNum.getNumber());

        String solName = resource.getValue("solname") + " " + solNum.getNumber();
        newsol.setSolName(solName);

        Assert.assertTrue("Solicitation Step HEADER OK", sol.stepTitle.getText().contains(resource.getValue("solstep_header")));

        sol.headBidTitleEdit.sendKeys(solName);
        sol.headBidNumberEdit.clear();
        sol.headBidNumberEdit.sendKeys(solNum.getNumber());
        sol.headDescriptionEdit.sendKeys(resource.getValue("sollongdesc") + " " + solName );

        new Select(sol.headSolPublicTypeDrop).selectByValue(resource.getValue("solprivate"));
        new Select(sol.headInvitationTypeDrop).selectByIndex(1);

        sol.headEstTotalEdit.sendKeys( resource.getValue("solesttotal") );

        sol.headSelectCatButton.click();

        browser.waitForElementToAppear(commodity.commoditySearchButton);

        // Add a list of comma-separated commodity codes to add to sol header
        String[] values = resource.getValue("solheadcommodities").split(",");

        // for each code, search for and then add code to solicitation header
        for (String code : values) {
            commodity.selectCommodityByCode(code);
        }

        commodity.commodityCloseButton.click();

        // wait until we load the page after picking commodities
        browser.waitForElementToAppear(sol.headStartDateEdit);

        // set our Solicitation start date to 5 minutes from NOW
        String solStartDate = sol.solDatePlusMin(Integer.valueOf(resource.getValue("solminutestowait")));
        browser.InjectJavaScript("arguments[0].value=arguments[1]", sol.headStartDateEdit, solStartDate);
        newsol.setSolStartDate(solStartDate);

        // set Collaboration dates if not already set
        if (!sol.headCollaborationCheckbox.isSelected()) {
            sol.headCollaborationCheckbox.click();
        }

        // set Collaboration date to 6 minutes from now (must be after sol start)
        browser.InjectJavaScript("arguments[0].value=arguments[1]", sol.headCollaborationStartDateEdit, sol.solDatePlusMin((Integer.valueOf(resource.getValue("solminutestowait"))+1) ));

        sol.nextButton.click();

    }

    private void RequirementsAndQuestionnaireStep() {

        Assert.assertTrue("Solicitation Step REQUIREMENTS OK", sol.stepTitle.getText().contains(resource.getValue("solstep_requirements")));
        browser.clickWhenAvailable(sol.requireNextButton);

        Assert.assertTrue("Solicitation Step QUESTIONNAIRE OK", sol.stepTitle.getText().contains(resource.getValue("solstep_questionnaire")));
        browser.clickWhenAvailable(sol.nextButton);
    }

    private void AttachmentsStep() {

        Assert.assertTrue("Solicitation Step ATTACHMENTS OK", sol.stepTitle.getText().contains(resource.getValue("solstep_attachments")));

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

    private void ItemSpecStep() {

        Assert.assertTrue("Solicitation Step ITEM SPECS OK", sol.stepTitle.getText().contains(resource.getValue("solstep_itemspecs")));

        // Add two groups to the solicitation, verify groups appear in drop-down
        sol.itemCreateGroup(resource.getValue("solgroupname1"));
        sol.itemCreateGroup(resource.getValue("solgroupname2"));

        Assert.assertTrue("Group DropDown Exists OK", sol.itemGroupDropDown.isEnabled() );

        // add two items from resources. I know it's a terrible implementation - please improve
        String[] item1 = resource.getValue("solitem1").split(",");
        String[] item2 = resource.getValue("solitem2").split(",");

        sol.itemCreateItem( item1[0],item1[1],item1[2],item1[3],item1[4],item1[5],item1[6],item1[7] );
        sol.itemCreateItem( item2[0],item2[1],item2[2],item2[3],item2[4],item2[5],item2[6],item2[7] );

        sol.itemPageNextButton.click();

    }

    private void SupplierSelectStep() {

        Assert.assertTrue("Solicitation Step SELECT SUPPLIERS OK", sol.stepTitle.getText().contains(resource.getValue("solstep_suppliers")));

        // zip between tabs to clear out pre-selected commodities in search
        browser.clickWhenAvailable(sol.supplierSelectedTab);
        browser.clickWhenAvailable(sol.supplierSearchButton);
        browser.clickWhenAvailable(sol.supplierSelectedTab);
        browser.clickWhenAvailable(sol.supplierSearchButton);

        sol.supplierSearchName.sendKeys(resource.getValue("solsuppliername"));

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

    private void SolicitationSummaryStep() {

        Assert.assertTrue("Solicitation Step SUMMARY OK", sol.stepTitle.getText().contains(resource.getValue("solstep_summary")));

        // click on Submit Solicitation button
        browser.clickWhenAvailable(sol.summarySubmitButton);

        // click OK on alert asking to create solicitation
        Alert alert = browser.switchTo().alert();
        alert.accept();

        // click OK button after sol submitted to return to sol list screen
        browser.clickWhenAvailable(sol.summaryOKAfterSubmitButton);

    }
}
