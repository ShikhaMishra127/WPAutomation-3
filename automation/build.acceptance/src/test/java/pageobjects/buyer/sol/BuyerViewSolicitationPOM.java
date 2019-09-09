package pageobjects.buyer.sol;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.common.Browser;

import java.util.HashMap;
import java.util.Map;

public class BuyerViewSolicitationPOM {

    private final Browser browser;

    public BuyerViewSolicitationPOM(WebDriver browser) {
        this.browser = (Browser) browser;
        PageFactory.initElements(((Browser) browser).driver, this);
    }

    //////////////////////////////////////////////////////////////////////// COMMON CONTROLS

    @FindBy(xpath="//select[@id='filter-organization']")
    public WebElement searchOrgDrop;

    @FindBy(xpath="//input[@id='filter_bidTitle']")
    public WebElement searchSolTitleEdit;

    @FindBy(xpath="//input[@id='filter_bidNumber']")
    public WebElement searchSolNumberEdit;

    @FindBy(xpath="//select[@id='filter-user']")
    public WebElement searchUserDrop;

    @FindBy(xpath="//input[@id='filter_from_endDate']")
    public WebElement searchEndDateFromEdit;

    @FindBy(xpath = "//input[@id='filter_to_endDate']")
    public WebElement searchEndDateToEdit;

    @FindBy(xpath = "//li[@id='userChartTab']")
    public WebElement searchEndedSolsTab;

    @FindBy(xpath = "//li[@id='adminChartTab']")
    public WebElement searchActiveSolsTab;

    @FindBy(xpath = "//button[contains(@onclick,'validateForm')]")
    public WebElement searchUpdateFilterButton;

    @FindBy(xpath = "//table[@id='solTable']")
    public WebElement searchSolResultsTable;

    ///// Sol View Action Bar sub-items
    public String actionEllipsis = "./span/img[contains(@src,'24px.svg')]";
    public String actionAddendum = "./span/ul/li/a[contains(@href,'javascript:startAddendum')]";
    public String actionEvaluate = "./span/ul/li/a[contains(@href,'AwardStyle')]";
    public String actionFinalize = "./span/ul/li/a[contains(@href,'javascript:Finalize')]";
    public String actionAwardRpt = "./span/ul/li/a[contains(@href,'AwardReport')]";
    public String actionNotify = "./span/ul/li/a[contains(@href,'BidVendorNotice')]";
    public String actionPrint = "./span/ul/li/a[contains(@href,'javascript:BMS_PopupWindow')]";
    // yes, there are many more. feel free to put them in as needed.

    //////////////////////////////////////////////////////////////////////// HELPFUL METHODS

    public enum SolListColumn {BOGUS, SOLNUM, TITLE, BUYER, ENDDATE, STATUS, ACTION}

    public Map<SolListColumn, WebElement> getElementsForSolLine(String solnum) {

        // Look for our row in the filtered list of items
        String xpathrow = "//table[@id='solTable']/tbody/tr/td/a[contains(text(),'" + solnum + "')]/parent::*/parent::*";

        browser.waitForElementToAppear(By.xpath(xpathrow));

        HashMap<SolListColumn, WebElement> elements = new HashMap<>();

        // build a list of WebElements that reference each column (name, number, status, etc)
        for (int i = 1; i < SolListColumn.values().length; i++) {

            String columnxpath = xpathrow + "/td[" + String.valueOf(i) + "]";

            browser.waitForElementToAppear(By.xpath(columnxpath));
            elements.put(SolListColumn.values()[i], searchSolResultsTable.findElement(By.xpath(columnxpath)));
        }

        return elements;
    }
}
