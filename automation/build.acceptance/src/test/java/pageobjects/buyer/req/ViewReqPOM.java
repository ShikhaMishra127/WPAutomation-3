package pageobjects.buyer.req;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.common.Browser;

import java.util.HashMap;
import java.util.Map;

public class ViewReqPOM {

    private final Browser browser;

    public enum ReqListColumn { BOGUS, EXPAND, ORG, REQNUM, REQNAME, REQUESTER, BUYER, CREATEDATE, STATUS, ACTION }

    public ViewReqPOM(WebDriver browser) {
        this.browser = (Browser) browser;
        PageFactory.initElements(((Browser) browser).driver, this);
    }

    //////////////////////////////////////////////////////////////////////// COMMON CONTROLS

    //////////////////////////////////////////////////////////////////////// TRACK REQUESTS PAGE

    @FindBy(xpath = "//select[@id='FSts']")
    public WebElement filterStatusDrop;

    @FindBy(xpath = "//input[@id='FShoppingCartNum']")
    public WebElement filterReqNumEdit;

    @FindBy(xpath = "//input[@id='FShoppingCartName']")
    public WebElement filterReqNameEdit;

    @FindBy(xpath = "//select[@id='FUser']")
    public WebElement filterRequesterDrop;

    @FindBy(xpath = "//input[@id='FBorg']")
    public WebElement filterBorgEdit;

    @FindBy(xpath = "//select[@id='FBUYER']")
    public WebElement filterContactDrop;

    @FindBy(xpath = "//input[@id='FromDate']")
    public WebElement filterFromDateEdit;

    @FindBy(xpath = "//input[@id='ToDate']")
    public WebElement filterToDateEdit;

    @FindBy(xpath = "//button[@name='Submit']")
    public WebElement filterSubmitButton;

    @FindBy(xpath = "//button[@name='Reset']")
    public WebElement filterResetButton;

    @FindBy(xpath = "//button[@name='Clear Filters']")
    public WebElement filterClearButton;

    @FindBy(xpath="//table[@id='reqTable']")
    public WebElement reqTable;

    //////////////////////////////////////////////////////////////////////// REQUISITION ITEM DETAILS

    ////////// ReqItem top-row Sub-Elements
    public String riDownArrow = "./a[contains(@href, 'ExpandReq')]";
    public String riEllipsis = "./span/button";
    public String riActionCopyReq = "./span/ul/li/a[contains(@href,'javascript:ReqCopy')]";
    public String riActionHistory = "./span/ul/li/a[contains(@href,'javascript:ReqHistory')]";
    public String riActionApprovalMap = "./span/ul/li/a[contains(@href,'javascript:ReqApproval')]";
    public String riActionPrint = "./span/ul/li/a[contains(@href,'javascript:ReqPrint')]";
    public String riReqTotal = "./tbody/tr[3]";

    ////////// ReqItem Expanded detail Elements
    @FindBy(xpath="//img[contains(@src,'person')]/parent::*/parent::*/parent::*/preceding-sibling::*/font")
    public WebElement riPONumber;

    @FindBy(xpath="//img[contains(@src,'person')]/parent::*/parent::*/parent::*/font")
    public WebElement riSupplierName;

    @FindBy(xpath="//img[@data-toggle='dropdown']/parent::*/parent::*/preceding-sibling::*/font[1]")
    public WebElement riHeaderData;

    //////////////////////////////////////////////////////////////////////// HELPER METHODS

    public Map<ReqListColumn, WebElement> getElementsForReqLine(String reqname) {

        // Look for our req row in the filtered list of requisitions
        String xpathrow = "//td[contains(text(),'" + reqname + "')]/parent::*";
        browser.waitForElementToAppear(By.xpath(xpathrow));

        HashMap<ReqListColumn, WebElement> elements = new HashMap<>();

        // build a list of WebElements that reference each column (name, number, status, etc)
        for (int i = 1; i < ReqListColumn.values().length; i++) {

            String columnxpath = xpathrow + "/td[" + String.valueOf(i) +  "]";

            browser.waitForElementToAppear(By.xpath(columnxpath));
            elements.put(ReqListColumn.values()[i], reqTable.findElement(By.xpath(columnxpath)));
        }

        return elements;
    }

}
