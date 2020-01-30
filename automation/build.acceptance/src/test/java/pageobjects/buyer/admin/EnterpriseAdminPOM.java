package pageobjects.buyer.admin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.common.Browser;

import java.util.List;
import java.util.Map;

public class EnterpriseAdminPOM {

    private final Browser browser;

    public EnterpriseAdminPOM(WebDriver browser) {
        this.browser = (Browser) browser;
        PageFactory.initElements(((Browser) browser).driver, this);
    }

    //////////////////////////////////////////////////////////////////////// COMMON

    @FindBy(xpath = "//iframe[@id='dataframe']")
    public WebElement dataFrame;

    @FindBy(xpath = "//frame[contains(@src,'BorgWorkflowTemplateDisplay')]")
    public WebElement wfFrame;

    @FindBy(xpath="//footer[@id='footerRow']")
    public WebElement footer;


    //////////////////////////////////////////////////////////////////////// ORGANIZATION / ADMIN LEFT FRAME

    @FindBy(xpath = "//iframe[@id='orgframe']")
    public WebElement menuOrgFrame;

    @FindBy(xpath = "//a[contains(@href,'listvieworgs')]")
    public WebElement menuListViewLink;

    @FindBy(xpath = "//div[@id='navigation']")
    public WebElement menuAdminDiv;

    public void SelectFromMenu(String header, String subitem) {

        // make sure the proper org is selected by switching to list-view to find suborg, then clicking on it
        browser.waitForPageLoad();
        browser.switchToFrame(menuOrgFrame);

        if (browser.elementExists(menuListViewLink)) {
            browser.clickWhenAvailable(menuListViewLink);
        }

        By borgxpath = By.xpath("//a[contains(@href,'setOrg')][contains(text(),'Automation')]");

        browser.clickWhenAvailable(borgxpath);

        // swtich back to top frame
        browser.driver.switchTo().defaultContent();

        By headerxpath = By.xpath("//div[@id='navigation']/ul/li/a[contains(@href,'setAction')][contains(text(),'" + header + "')]");
        By subitemxpath = By.xpath("//div[@id='navigation']/ul/li/ul/li/a[contains(@href,'setAction')][contains(text(),'" + subitem + "')]");

        // click the header menu to bring up sub-items, then click sub-item and wait for page to load
        browser.clickWhenAvailable(headerxpath);
        browser.clickWhenAvailable(subitemxpath);
        browser.waitForPageLoad();

    }

    //////////////////////////////////////////////////////////////////////// EDIT GENERAL ORG INFO PAGE

    @FindBy(xpath = "//input[@id='allow_attachment_download_on_bidboard']")
    public WebElement oiBidBoardAllowDownloadCheckbox;

    @FindBy(xpath = "//input[@id='remove_type_dd_bidboard']")
    public WebElement oiBidBoardRemoveTypeFilterCheckbox;

    @FindBy(xpath = "//input[@id='remove_commodity_bidboard']")
    public WebElement oiBidBoardRemoveCommodityFilterCheckbox;

    @FindBy(xpath = "//input[@id='remove_solsummary_print_bidboard']")
    public WebElement oiBidBoardRemovePrintButtonCheckbox;

    @FindBy(xpath = "(//button[contains(@onclick,'SaveOrg')])[1]")
    public WebElement oiSaveButton;

    //////////////////////////////////////////////////////////////////////// REQUEST WORKFLOW PAGE

    @FindBy(xpath = "//input[@name='save']")
    public WebElement wfSaveButton;

    public void wfSetWorkflowCheckbox(String setting, Boolean checked) {

        By checkboxxpath = By.xpath("//pre[text()='" + setting + "']/parent::*/parent::*/td[2]/input");

        browser.waitForElementToAppear(checkboxxpath);
        browser.clickSetCheckbox(checkboxxpath, checked);
    }

    //////////////////////////////////////////////////////////////////////// REQUEST WORKFLOW PAGE

    @FindBy(xpath = "//button[contains(@onclick,'Off-Catalog Approver')]")
    public WebElement raOffCatalogChangeApproverButton;

    @FindBy(xpath = "//select[@id=approverType]")
    public WebElement raApproverTypeDrop;

    @FindBy(xpath = "//input[contains(@onkeyup,'searchApprover')]")
    public WebElement raApproverNameEdit;

    @FindBy(xpath = "//ul[@id='ui-id-1']")
    public List<WebElement> raApproverList;

    @FindBy(xpath = "//button[@data-bb-handler='main']")
    public WebElement raSelectButton;

    @FindBy(xpath = "//button[contains(@onclick,'saveApprovers')]")
    public WebElement raSaveAllButton;

    //////////////////////////////////////////////////////////////////////// EDIT SUPPLIERS PAGE

    @FindBy(xpath = "//input[@id='sname']")
    public WebElement esSearchName;

    @FindBy(xpath = "//input[@id='searchbtn']")
    public WebElement esSearchButton;

    /*
        public enum InvListColumn implements Browser.HTMLTableColumn { BOGUS, EXPAND, ORG, BUYERNUM, SUPPLIERNUM, TOTAL, POSTDATE, SUPPLIER, STATUS, ACTION }

        public Map<Browser.HTMLTableColumn, WebElement> getElementsForInvLine(String invnumber) {
            String rowXPath = "//td[contains(text(),'" + invnumber + "')]/parent::*";
            return browser.buildTableMap(invTable, rowXPath, InvListColumn.values());
        }
     */
    @FindBy(xpath = "//tbody//table")
    public WebElement esSupplierResultTable;

    public enum SupplierListColumn implements Browser.HTMLTableColumn {BOGUS, NAME, DBA, REL, ADDRESS, HQ, MBE, WBE, SB, VOB, STATUS, ACTION}

    public Map<Browser.HTMLTableColumn, WebElement> getElementsForSupplierLine(String suppliername) {
        String rowXPath = "//a[contains(@href,'vendorInfo')][contains(text(),'" + suppliername + "')]/parent::*/parent::*";
        return browser.buildTableMap(esSupplierResultTable, rowXPath, SupplierListColumn.values());
    }

    public String esActionEdit = "./a/img[contains(@src,'edit.gif')]/parent::*";
    public String esActionDel = "./a/img[contains(@src,'delete.gif')]/parent::*";
    public String esActionAudit = "./a/img[contains(@src,'footprint.gif')]/parent::*";

    ///// supplier general info page

    @FindBy(xpath="//input[@type='radio'][@value='2']")
    public WebElement esApprovedRadio;

    @FindBy(xpath="//input[@type='radio'][@value='1']")
    public WebElement esPendingRadio;

    @FindBy(xpath="//input[@type='radio'][@value='3']")
    public WebElement esDeclinedRadio;

    @FindBy(xpath="//button[contains(@onclick,'saveSupplier')]")
    public WebElement esSaveButton;

    //////////////////////////////////////////////////////////////////////// EDIT USERS PAGE

    ///// user search page

    @FindBy(xpath="//input[@name='un']")
    public WebElement euUsernameEdit;

    @FindBy(xpath="//input[@name='em']")
    public WebElement euEmailEdit;

    @FindBy(xpath="//input[@name='ln']")
    public WebElement euLastNameEdit;

    @FindBy(xpath="//input[contains(@onclick,'finduserid')]")
    public WebElement euFindButton;

    public void euClickEditIconForUser(String username) {

        String xpath = "//td[contains(text(),'" + username + "')]/parent::*/td/a/img[contains(@src,'edit')]/parent::*";

        browser.clickWhenAvailable(By.xpath(xpath));
    }

    ///// user information page

    @FindBy(xpath="(//td[@class='WELCOME'])[1]/parent::*/parent::*")
    public WebElement euUserInfoTable;

    @FindBy(xpath="(//td[@class='WELCOME'])[1]/parent::*/parent::*//span[@class='badge']")
    public WebElement euUserStatus;

    @FindBy(xpath="//button[@name='onclick']")
    public WebElement euSelectAllButton;

    @FindBy(xpath="(//button[contains(@onclick,'SaveUser')])[1]")
    public WebElement euSaveUserButton;

    @FindBy(xpath="(//button[contains(@onclick,'CloseUser')])[1]")
    public WebElement euCloseButton;

}
