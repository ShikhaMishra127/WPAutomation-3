package pageobjects.vendor.sol;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.common.Browser;

import java.util.EnumMap;
import java.util.Map;

public class VendorSolResponsePOM {

    public enum SolColumn { SOLNUM, BORG, TITLE, STARTDATE, ENDDATE, TIMELEFT, STATUS, ACTION }

    private final Browser browser;

    public VendorSolResponsePOM(WebDriver browser) {
        this.browser = (Browser) browser;
        PageFactory.initElements(((Browser) browser).driver, this);
    }

    //////////////////////////////////////////////////////////////////////// COMMON CONTROLS

    @FindBy(xpath="//select[@name='orgId']")
    public WebElement viewOrgFilterDrop;

    @FindBy(xpath="//input[@id='filter_bidTitle']")
    public WebElement viewTitleFilterEdit;

    @FindBy(xpath="//input[@id='filter_bidNumber']")
    public WebElement viewBidNumberFilterEdit;

    @FindBy(xpath="//input[@id='filter_from_startDate']")
    public WebElement viewStartDateFromEdit;

    @FindBy(xpath="//input[@id='filter_to_startDate']")
    public WebElement viewStartDateToEdit;

    @FindBy(xpath="//input[@id='filter_from_endDate']")
    public WebElement viewEndDateFromEdit;

    @FindBy(xpath="//input[@id='filter_to_endDate']")
    public WebElement viewEndDateToEdit;

    @FindBy(xpath="//button[contains(@onclick,'validateForm')]")
    public WebElement viewFilterSubmitButton;

    @FindBy(xpath="//button[contains(@onclick,'resetPage(')]")
    public WebElement viewFilterResetButton;

    @FindBy(xpath="//li[@id='adminChartTab']")
    public WebElement viewMyListTab;

    @FindBy(xpath="//li[@id='userChartTab']")
    public WebElement viewOtherOpportunitiesTab;

    @FindBy(xpath="//table[@id='solTable']")
    public WebElement viewSolResultTable;

    ////////// SOL ITEM ACTION BAR
    public static String viewActionButton = "./span/img";
    public static String viewActionAddResponseButton = "./span/ul/li/a[contains(@href,'addNewResponse')]";
    public static String viewActionPrintButton = "./span/ul/li/a[contains(@href,'VendorBidReport')]";
    public static String viewActionCollaborationButton = "./span/ul/li/a[contains(@href,'collabCenter')]";

    ////////// NEW QUOTE NAME MODAL OVERLAY
    @FindBy(xpath="//input[@class='bootbox-input bootbox-input-text form-control']")
    public WebElement bidQuoteNameEdit;

    @FindBy(xpath="//button[contains(@data-bb-handler,'confirm')]")
    public WebElement bidQuoteNameOkButton;

    @FindBy(xpath="//button[@data-bb-handler='cancel']")
    public WebElement bidQuoteNameCancelButton;

    //////////////////////////////////////////////////////////////////////// NEW BID COMMON CONTROLS

    // !!!! THIS IS INCORRECT AND NEEDS TO BE FIXED.
    // We do not normally use English labels to identify controls. Please put in a JIRA ticket
    // to add IDs to the tabs within Vendor Sol Response
    /*
    @FindBy(xpath="//a[contains(@href,'VendorBidEdit') and not(contains(@href,'questionnaire'))]")
    public WebElement bidOverviewTab;

    @FindBy(xpath="//a[contains(@href,'fields')]")
    public WebElement bidRequirementsTab;

    @FindBy(xpath="//a[contains(@href,'VendorBidEdit') and contains(@href,'questionnaire')]")
    public WebElement bidQuestionnaireTab;

    @FindBy(xpath="//a[contains(@href,'VendorBid/') and not(contains(@href,'window.location'))]")
    public WebElement bidRespondTab;

    @FindBy(xpath="//a[contains(@href,'VendorQuoteReport')]")
    public WebElement bidReviewTab;

    @FindBy(xpath="//a[contains(@href,'VendorQuestionAnswer')]")
    public WebElement bidCollaborateTab;

    @FindBy(xpath="//div[@class='pull-right']/button[contains(@onclick,'VendorBid/')]")
    public WebElement bidOnItemsButton;
    */

    // !!! INCORRECT - SEE ABOVE
    @FindBy(xpath="//a[contains(text(),'Overview')]")
    public WebElement bidOverviewTab;

    @FindBy(xpath="//a[contains(text(),'Requirements')]")
    public WebElement bidRequirementsTab;

    @FindBy(xpath="//a[contains(text(),'Questionnaire')]")
    public WebElement bidQuestionnaireTab;

    @FindBy(xpath="//a[contains(text(),'Respond')]")
    public WebElement bidRespondTab;

    @FindBy(xpath="//a[contains(text(),'Review Response')]")
    public WebElement bidReviewTab;

    @FindBy(xpath="//a[contains(text(),'Collaborate')]")
    public WebElement bidCollaborateTab;

    @FindBy(xpath="//button[contains(text(),'Bid on Items')]")
    public WebElement bidOnItemsButton;


    //////////////////////////////////////////////////////////////////////// OVERVIEW PAGE

    @FindBy(xpath="//a[contains(@href,'checkAll') and (contains(@href,'false'))]")
    public WebElement overUncheckAllLink;

    @FindBy(xpath="//a[contains(@href,'checkAll') and (contains(@href,'true'))]")
    public WebElement overCheckAllLink;

    @FindBy(xpath="//button[@onclick='javascript:acceptDocs();']")
    public WebElement overAcceptButton;

    //////////////////////////////////////////////////////////////////////// RESPONSE PAGE

    @FindBy(xpath="//input[contains(@name,'price')]")
    public WebElement  respondPriceEdit;

    @FindBy(xpath="//div[@class='pull-right']/button[contains(@onclick,'SavePath')]")
    public WebElement respondSaveButton;

    //////////////////////////////////////////////////////////////////////// REVIEW PAGE

    @FindBy(xpath="//button[@onclick='javascript:submitResponse();']")
    public WebElement reviewSubmitButton;

    @FindBy(xpath="//button[@onclick='javascript:noBid();']")
    public WebElement reviewMarkNoBidButton;

    @FindBy(xpath="//button[@onclick='javascript:Done();']")
    public WebElement reviewCloseButton;


    //////////////////////////////////////////////////////////////////////// HELPFUL METHODS


    public Map<SolColumn, WebElement> getWebElementsBySol(String solnum) {

        Map<SolColumn, WebElement> thelist = new EnumMap<>(SolColumn.class);

        // Look for our solicitation row in the filtered list of solicitations
        String xpathrow = "//table[@id='solTable']/tbody/tr/td/b/a[contains(text(),'" + solnum +
                "')]/parent::*/parent::*/parent::*";

        // for each column, add a new WebElement (Name, number, status, actions, etc.)
        for (SolColumn element : SolColumn.values()) {

            String xpathcolumn = xpathrow + "/td[" + (element.ordinal() + 1) + "]";

            browser.waitForElementToAppear(By.xpath(xpathcolumn));
            thelist.put(element, viewSolResultTable.findElement(By.xpath(xpathcolumn)));
        }

        return thelist;
    }

    // Given a solicitation number, search for it in a list of solicitations
    public void waitForSolToAppear(String solnum) {

        String solnum_xpath = "//a[contains(text(),'"+ solnum +"')]";

        browser.waitForElementWithRefresh(solnum_xpath,15, 300);
    }
}
