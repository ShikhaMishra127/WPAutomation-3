package pageobjects.buyer.orders;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.common.Browser;

import java.util.HashMap;
import java.util.Map;

public class ViewOrderPOM {


    private final Browser browser;

    public ViewOrderPOM(WebDriver browser) {
        this.browser = (Browser) browser;
        PageFactory.initElements(((Browser) browser).driver, this);
    }

    //////////////////////////////////////////////////////////////////////// COMMON CONTROLS

    @FindBy(xpath="//select[@id='FSts']")
    public WebElement mainSearchOrderStatusDrop;

    @FindBy(xpath="//input[@id='FPONbr']")
    public WebElement mainSearchOrderNumberEdit;

    @FindBy(xpath="//input[@id='FPOSupplierName']")
    public WebElement mainSearchSupplierEdit;

    @FindBy(xpath="//button[@name='Submit']")
    public WebElement mainSearchSubmitButton;

    @FindBy(xpath="//button[@name='Reset']")
    public WebElement mainSearchResetButton;

    @FindBy(xpath="//table[@id='poTable']")
    public WebElement searchPOResultsTable;

    ////////// POItem Sub-Elements
    public String piActionCopyToReq = "./span/ul/li/a[contains(@href,'Copy')]";
    public String piActionPOHistory = "./span/ul/li/a[contains(@href,'PORevision')]";
    public String piActionPOPrint = "./span/ul/li/a[contains(@href,'Print')]";
    public String piActionSOPrint = "./span/ul/li/a[contains(@href,'SuppPrint')]";
    public String piActionReceive = "./span/ul/li/a[contains(@href,'ReceiveReceipt')]";
    public String piReceiptHistory = "./span/ul/li/a[contains(@href,'ReceiptHistory')]";
    public String piActionCOCreate = "./span/ul/li/a[contains(@href,'CreateChangeOrder')]";
    public String piActionWFMap = "./span/ul/li/a[contains(@href,'ReqApproval')]";
    public String piActionPOCancel = "./span/ul/li/a[contains(@href,'CancelOrder')]";
    public String piActionPODetails = "./span/ul/li/a[contains(@href,'ViewPODetails')]";

    // PO History Sub-Elements
    @FindBy(xpath="//span[contains(@class,'dropdown dd-span')]")
    public WebElement phEllipsis;

    public String phHistory = "./ul/li/a[contains(@href,'POHistory')]";
    public String phPrint = "./ul/li/a[contains(@href,'Print')]";
    public String phApproval = "./ul/li/a[contains(@href,'ReqApproval')]";

    @FindBy(xpath="//form[@name='frmPOHistLst']")
    public WebElement phHistoryList;

    public String getReqNumberFromPOHistory(String ponumber) {

        String xpath = "//form[@name='frmPOHistLst']/div/table/tbody/tr/th[contains(.,'PO " + ponumber + " has failed Financial System validation')]";
        String[] history;

        browser.waitForElementToAppear(By.xpath(xpath));
        WebElement element = browser.findElement(By.xpath(xpath));
        history = element.getText().split("request document|has been automatically generated");

        return history[1].trim();
    }

    //////////////////////////////////////////////////////////////////////// HELPFUL METHODS

    public enum POListColumn {BOGUS, ARROW, ORG, ORDER, REQUESTER, BUYER, DATE, SUPPLIER, CONTRACT, TOTAL, STATUS, ACTION}

    public Map<POListColumn, WebElement> getElementsForPOLine(String ponum) {

        // Look for our row in the filtered list of items
        String xpathrow = "//td[contains(text(),'" + ponum.trim() + "')]/parent::*";

        browser.waitForElementToAppear(By.xpath(xpathrow));

        HashMap<POListColumn, WebElement> elements = new HashMap<>();

        // build a list of WebElements that reference each column (name, number, status, etc)
        for (int i = 1; i < POListColumn.values().length; i++) {

            String columnxpath = xpathrow + "/td[" + String.valueOf(i) + "]";

            browser.waitForElementToAppear(By.xpath(columnxpath));
            elements.put(POListColumn.values()[i], searchPOResultsTable.findElement(By.xpath(columnxpath)));
        }

        return elements;
    }
}
