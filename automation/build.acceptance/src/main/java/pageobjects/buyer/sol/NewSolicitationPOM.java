package pageobjects.buyer.sol;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import utilities.common.Browser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class NewSolicitationPOM {

    private final Browser browser;
    public String TargetSolNum;

    public NewSolicitationPOM(WebDriver browser) {
        this.browser = (Browser) browser;
        PageFactory.initElements(((Browser) browser).driver, this);
    }



    //////////////////////////////////////////////////////////////////////// COMMON CONTROLS

    @FindBy(xpath = "//ul[@class='nav nav-wizard']//li[@class='active']")
    public WebElement stepTitle;

    @FindBy(xpath = "//button[@onclick='javascript:NextStep();']")
    public WebElement nextButton;


    //////////////////////////////////////////////////////////////////////// HEADER PAGE

    @FindBy(xpath = "//input[@id='bidNumber']")
    public WebElement headBidNumberEdit;

    @FindBy(xpath = "//input[@id='bidTitle']")
    public WebElement headBidTitleEdit;

    @FindBy(xpath = "//select[@id='bidInvitationType']")
    public WebElement headSolPublicTypeDrop;

    @FindBy(xpath = "//select[@id='solType']")
    public WebElement headInvitationTypeDrop;

    @FindBy(xpath = "//input[@id='bidestimatedtotal']")
    public WebElement headEstTotalEdit;

    @FindBy(xpath = "//textarea[@id='bidDesc']")
    public WebElement headDescriptionEdit;

    @FindBy(xpath = "//input[@id='noLineItem']")
    public WebElement headNoLineItemCheckbox;

    @FindBy(xpath = "//button[@id='selectCatButton']")
    public WebElement headSelectCatButton;

    @FindBy(xpath = "//input[@id='solstartdatetime']")
    public WebElement headStartDateEdit;

    @FindBy(xpath = "//input[@id='solenddatetime']")
    public WebElement headEndDateEdit;

    @FindBy(xpath = "//input[@name='collaboration_enable']")
    public WebElement headCollaborationCheckbox;

    @FindBy(xpath = "//input[@id='collabstartdatetime']")
    public WebElement headCollaborationStartDateEdit;

    @FindBy(xpath = "//input[@id='collabenddatetime']")
    public WebElement headCollaborationEndDateEdit;

    //////////////////////////////////////////////////////////////////////// REQUIREMENTS PAGE

    @FindBy(xpath = "//button[@onclick='javascript:MoveStep(true);']")
    public WebElement requireNextButton;

    //////////////////////////////////////////////////////////////////////// QUESTIONNAIRE PAGE


    //////////////////////////////////////////////////////////////////////// ATTACHMENTS PAGE

    @FindBy(xpath = "//button[@onclick='javascript:UploadDocumentFromLibrary();']")
    public WebElement docsUploadFromLibButton;

    @FindBy(xpath = "(//input[@type='checkbox'])[1]")
    public WebElement docsUploadFirstFileCheckbox;

    @FindBy(xpath="(//input[@type='checkbox'])[1]/parent::*/parent::*/td[3]/a")
    public WebElement docsUploadFirstFilenameText;

    @FindBy(xpath = "//button[@onclick='javascript:Save();']")
    public WebElement docsUploadSaveButton;

    @FindBy(xpath = "//table[@class='table table-bordered table-striped no-footer']//thead")
    public WebElement docsFileUploadHeader;


    //////////////////////////////////////////////////////////////////////// ITEM SPEC PAGE


    @FindBy(xpath = "//button[@onclick='javascript:AddNewGroup();']")
    public WebElement itemAddGroupButton;

    @FindBy(xpath = "//input[@name='name']")
    public WebElement itemGroupNameEdit;

    @FindBy(xpath = "//button[@onclick='javascript:document.theform.submit();']")
    public WebElement itemGroupSaveButton;

    @FindBy(xpath = "//select[@name='groupid']")
    public WebElement itemGroupDropDown;

    @FindBy(xpath = "//button[@onclick='javascript:AddNewItem();']")
    public WebElement itemCreateItemButton;

    @FindBy(xpath="//button[@onclick='javascript:Finished();']")
    public WebElement itemPageNextButton;


    /////// New Item pop-up

    @FindBy(xpath = "//input[@id='name']")
    public WebElement itemNewNameEdit;

    @FindBy(xpath = "//input[@id='sic']")
    public WebElement itemNewSpecNumEdit;

    @FindBy(xpath = "//input[@id='mnftr_name']")
    public WebElement itemNewMfrNameEdit;

    @FindBy(xpath = "//input[@id='mnftr_prt_no']")
    public WebElement itemNewMfrPartNoEdit;

    @FindBy(xpath = "//textarea[@id='description']")
    public WebElement itemNewDescriptionEdit;

    @FindBy(xpath = "//input[@id='qty']")
    public WebElement itemNewQtyEdit;

    @FindBy(xpath = "//select[@id='groupid']")
    public WebElement itemNewGroupDropDown;

    @FindBy(xpath = "(//button[@type='button'])[1]")
    public WebElement itemNewCommodityButton;

    @FindBy(xpath = "//button[@onclick='javascript:SaveItem(document.theform);']")
    public WebElement itemNewSaveItemButton;

    /////// Commodity Search Pop-up (inside new item pop-up)

    @FindBy(xpath = "//input[@name='name_parm']")
    public WebElement itemNewCommoditySearchEdit;

    @FindBy(xpath = "//button[@id='search']")
    public WebElement itemNewCommoditySearchButton;

    @FindBy(xpath = "//span[@class='highlight']")
    public WebElement itemNewCommoditySearchResultLink;

    //////////////////////////////////////////////////////////////////////// SUPPLIERS PAGE

    @FindBy(xpath = "//li[@id='selectedSuppliersTab']/a")
    public WebElement supplierSelectedTab;

    @FindBy(xpath = "//button[@onclick='javascript:searchAgain();']")
    public WebElement supplierSearchButton;

    @FindBy(xpath="//input[@id='sname']")
    public WebElement supplierSearchName;

    @FindBy(xpath="//button[@onclick='javascript:validate_form();']")
    public WebElement supplierLookupButton;

    @FindBy(xpath="//input[@title='select']")
    public WebElement supplier;

    @FindBy(xpath="//table[@id='searchResult']")
    public WebElement supplierSearchResultTable;


    //////////////////////////////////////////////////////////////////////// SUMMARY PAGE

    @FindBy(xpath="//span[@class='dateDisplay dateSeq']/br")
    public WebElement summaryStartDate;

    @FindBy(xpath="//span[@class='dateDisplay']/br")
    public WebElement summaryEndDate;

    @FindBy(xpath="//button[contains(@onclick,'SubmitWorkFlow')]")
    public WebElement summarySubmitButton;

    @FindBy(xpath="//button[@class='btn btn-wp']")
    public WebElement summaryOKAfterSubmitButton;


    //////////////////////////////////////////////////////////////////////// HELPFUL METHODS

    public void itemCreateGroup(String groupname) {

        itemAddGroupButton.click();

        // set focus to pop-up
        String parentWindow = browser.driver.getWindowHandle();
        browser.SwitchToPopUp(parentWindow);

        // Add group name and click Save
        browser.waitForElementToAppear(itemGroupNameEdit);
        itemGroupNameEdit.sendKeys(groupname);
        itemGroupSaveButton.click();

        // switch focus back to main window
        browser.switchTo().window(parentWindow);

    }

    public void itemCreateItem(String name,
                               String specnum,
                               String mfrname,
                               String mfrpartnum,
                               String longdesc,
                               String commodity,
                               String qty,
                               String group
    ) {

        browser.clickWhenAvailable(itemCreateItemButton);

        // set focus to pop-up
        String parentWindow = browser.driver.getWindowHandle();
        browser.SwitchToPopUp(parentWindow);

        itemNewNameEdit.sendKeys(name);
        itemNewSpecNumEdit.sendKeys(specnum);
        itemNewMfrNameEdit.sendKeys(mfrname);
        itemNewMfrPartNoEdit.sendKeys(mfrpartnum);
        itemNewDescriptionEdit.sendKeys(longdesc);
        itemNewQtyEdit.sendKeys(qty);

        new Select(itemNewGroupDropDown).selectByVisibleText(group);

        itemNewCommodityButton.click();

        // set focus to 2nd gen pop-up (commodity code picker)
        String newItemWindow = browser.driver.getWindowHandle();
        browser.SwitchToWindow("SelectCat");

        // search for commodity code and click on it
        itemNewCommoditySearchEdit.sendKeys(commodity);
        itemNewCommoditySearchButton.click();

        browser.clickWhenAvailable(itemNewCommoditySearchResultLink);

        // go back to add item screen and save item
        browser.switchTo().window(newItemWindow);

        browser.clickWhenAvailable(itemNewSaveItemButton);

        // click cancel on alert asking to create another item
        Alert alert = browser.switchTo().alert();
        alert.dismiss();

        // set focus back to main window before leaving
        browser.switchTo().window(parentWindow);
    }

}
