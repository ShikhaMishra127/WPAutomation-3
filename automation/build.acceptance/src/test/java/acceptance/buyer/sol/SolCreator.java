package acceptance.buyer.sol;

import main.java.framework.Solicitation;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import pageobjects.buyer.sol.NewSolicitationPOM;
import pageobjects.buyer.common.BuyerNavBarPOM;
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

    private void setup(Browser inBrowser, ResourceLoader soldata) {

        browser = inBrowser;
        resource = soldata;

        commodity = new CommodityPickerPOM(browser);
        navbar = new BuyerNavBarPOM(browser);
        login = new LoginPagePOM(browser);
        sol = new NewSolicitationPOM(browser);

        newsol = new Solicitation();
    }

    public Solicitation CreateSolicitation(Browser inBrowser, ResourceLoader soldata) {

        setup(inBrowser, soldata);

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


    private void HeaderStep() {

        navbar.selectDropDownItem(resource.getValue("navbar_headitem"), resource.getValue("navbar_subitem") );

        UniqueID solNum = new UniqueID(UniqueID.IDType.DATE);
        newsol.setSolNumber(solNum.getNumber());

        String solName = resource.getValue("solname") + " " + solNum.getNumber();
        newsol.setSolName(solName);

        browser.Assert("Verify Solicitation Step HEADER", sol.stepTitle.getText(), resource.getValue("solstep_header"));

        sol.headBidTitleEdit.sendKeys(solName);
        sol.headBidNumberEdit.clear();
        sol.headBidNumberEdit.sendKeys(solNum.getNumber());

        String longdesc = resource.getValue("sollongdesc") + " " + solName;
        sol.headDescriptionEdit.sendKeys(longdesc);
        newsol.setSolLongDesc(longdesc);

        new Select(sol.headSolPublicTypeDrop).selectByValue(resource.getValue("solprivate"));
        new Select(sol.headInvitationTypeDrop).selectByIndex(1);

        sol.headEstTotalEdit.sendKeys( resource.getValue("solesttotal") );

        sol.headSelectCatButton.click();

        // Add a list of comma-separated commodity codes to add to sol header
        commodity.addCodes(resource.getValue("solheadcommodities"));

        // wait until we load the page after picking commodities
        browser.waitForElementToAppear(sol.headStartDateEdit);

        // set our Solicitation start date to (solminutestowait) minutes from NOW and lasting (solminutesduration) minutes
        int offset = Integer.valueOf(resource.getValue("solminutestowait"));
        int duration = Integer.valueOf(resource.getValue("solminutestowait")) + Integer.valueOf(resource.getValue("solminutesduration"));

        // set dates for both the UI and the Solicitation object
        newsol.setSolStartDate(browser.getDateTimeNowInUsersTimezone().plusMinutes(offset));
        newsol.setSolEndDate(browser.getDateTimeNowInUsersTimezone().plusMinutes(duration));

        // inject dates into edit boxes
        browser.InjectJavaScript("arguments[0].value=arguments[1]", sol.headStartDateEdit, newsol.getSolStartDateFormatted());
        browser.InjectJavaScript("arguments[0].value=arguments[1]", sol.headEndDateEdit, newsol.getSolEndDateFormatted());

        // set Collaboration dates if not already set
        if (!sol.headCollaborationCheckbox.isSelected()) {
            browser.clickWhenAvailable(sol.headCollaborationCheckbox);
        }

        // set Collaboration date to (solminutestowait + 1) minutes from now and -1 minutes from end
        newsol.setCollaborationStartDate(browser.getDateTimeNowInUsersTimezone().plusMinutes(offset + 1));
        newsol.setCollaborationEndDate(browser.getDateTimeNowInUsersTimezone().plusMinutes(duration - 1));

        browser.InjectJavaScript("arguments[0].value=arguments[1]", sol.headCollaborationStartDateEdit, newsol.getCollaborationStartDateFormatted());
        browser.InjectJavaScript("arguments[0].value=arguments[1]", sol.headCollaborationEndDateEdit, newsol.getCollaborationEndDateFormatted());

        sol.nextButton.click();

    }

    private void RequirementsAndQuestionnaireStep() {

        browser.Assert("Verify Solicitation Step REQUIREMENTS", sol.stepTitle.getText(), resource.getValue("solstep_requirements"));
        browser.clickWhenAvailable(sol.requireNextButton);

        browser.Assert("Verify Solicitation Step QUESTIONNAIRE", sol.stepTitle.getText(), resource.getValue("solstep_questionnaire"));
        browser.clickWhenAvailable(sol.nextButton);
    }

    private void AttachmentsStep() {

        browser.Assert("Verify Solicitation Step ATTACHMENTS", sol.stepTitle.getText(), resource.getValue("solstep_attachments"));

        // go to the Upload From Document library
        browser.clickWhenAvailable(sol.docsUploadFromLibButton);

        // set focus to pop-up
        String parentWindow = browser.driver.getWindowHandle();
        browser.SwitchToPopUp(parentWindow);

        // click on the 1st file in the list (assume that there is at least one file)
        browser.clickWhenAvailable(sol.docsUploadFirstFileCheckbox);

        // add filename to solicitation object for later testing
        newsol.setSolAttachment(sol.docsUploadFirstFilenameText.getText());
        sol.docsUploadSaveButton.click();

        // switch focus back to main window
        browser.switchTo().window(parentWindow);

        // wait for header from list of attached files to appear
        browser.waitForElementToAppear(sol.docsFileUploadHeader);

        sol.nextButton.click();

    }

    private void ItemSpecStep() {

        browser.Assert("Verify Solicitation Step ITEM SPECS", sol.stepTitle.getText(), resource.getValue("solstep_itemspecs"));

        // Add two groups to the solicitation, verify groups appear in drop-down
        sol.itemCreateGroup(resource.getValue("solgroupname1"));
        sol.itemCreateGroup(resource.getValue("solgroupname2"));

        browser.Assert("Verify Group DropDown Exists", sol.itemGroupDropDown.isEnabled() );

        // add two items from resources. I know it's a terrible implementation - please improve
        String[] item1 = resource.getValue("solitem1").split(",");
        String[] item2 = resource.getValue("solitem2").split(",");

        sol.itemCreateItem( item1[0],item1[1],item1[2],item1[3],item1[4],item1[5],item1[6],item1[7] );
        sol.itemCreateItem( item2[0],item2[1],item2[2],item2[3],item2[4],item2[5],item2[6],item2[7] );

        sol.itemPageNextButton.click();

    }

    private void SupplierSelectStep() {

        browser.Assert("Verify Solicitation Step SELECT SUPPLIERS", sol.stepTitle.getText(), resource.getValue("solstep_suppliers"));

        // zip between tabs to clear out pre-selected commodities in search
        browser.clickWhenAvailable(sol.supplierSelectedTab);
        browser.clickWhenAvailable(sol.supplierSearchButton);
        browser.clickWhenAvailable(sol.supplierSelectedTab);
        browser.clickWhenAvailable(sol.supplierSearchButton);

        sol.supplierSearchName.sendKeys(browser.supplierName);

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

        browser.Assert("Verify Solicitation Step SUMMARY", sol.stepTitle.getText(), resource.getValue("solstep_summary"));

        // click on Submit Solicitation button
        browser.clickWhenAvailable(sol.summarySubmitButton);

        // click OK on alert asking to create solicitation
        Alert alert = browser.switchTo().alert();
        alert.accept();

        // click OK button after sol submitted to return to sol list screen
        browser.clickWhenAvailable(sol.summaryOKAfterSubmitButton);

        browser.Log("Solicitation '" + newsol.getSolName() + "' created");
    }
}
