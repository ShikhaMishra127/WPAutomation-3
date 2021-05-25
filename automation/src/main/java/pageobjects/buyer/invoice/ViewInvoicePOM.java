package pageobjects.buyer.invoice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageobjects.buyer.req.ViewReqPOM;
import utilities.common.Browser;

import java.util.HashMap;
import java.util.Map;

public class ViewInvoicePOM {

    private final Browser browser;

    public ViewInvoicePOM(WebDriver browser) {
        this.browser = (Browser) browser;
        PageFactory.initElements(((Browser) browser).driver, this);
    }

    //////////////////////////////////////////////////////////////////////// COMMON CONTROLS

    //////////////////////////////////////////////////////////////////////// INVOICE SEARCH PAGE

    @FindBy(xpath="//input[@id='autocomplete_supplierSrch']")
    public WebElement mainSupplierFilterEdit;

    @FindBy(xpath="//input[@id='ponum_filter']")
    public WebElement mainPONumberFilterEdit;

    @FindBy(xpath="//input[@id='binvoicenum']")
    public WebElement mainBuyerInvoiceNumberFilterEdit;

    @FindBy(xpath="//button[contains(@onclick,'submitPage')]")
    public WebElement mainApplyFilterButton;

    @FindBy(xpath="//table[@id='invTable']")
    public WebElement invTable;


    //////////////////////////////////////////////////////////////////////// INVOICE LINE CONTROLS

    public String iiDownArrow = "./a[contains(@href,'getItems')]";
    public String iiEllipsis = "./span/button";
    public String iiViewInvoice = "./span/ul/li/a[contains(@href,'&mode=view')]";
    public String iiEditInvoice = "./span/ul/li/a[contains(@href,'&mode=edit')]";
    public String iiPrintInvoice = "./span/ul/li/a[contains(@href,'InvoicePrint')]";
    public String iiCancelInvoice = "./span/ul/li/a[contains(@href,'InvoiceCancel')]";
    public String iiInvoiceHistory = "./span/ul/li/a[contains(@href,'InvoiceHistory')]";
    public String iiSupplierNotify = "./span/ul/li/a[contains(@href,'supplierNotification')]";

    public boolean ExpandedPOExists(WebElement invLine, String ponumber) {

        // get the line for our invoice and find po info on next line down
        browser.waitForElementToAppear(invLine);
        WebElement poLineInfo = browser.getSubElement(invLine,"./parent::*/following-sibling::*/td[2]");

        // return whether the PO number is in the line info
        return ponumber.contains(poLineInfo.getText());
    }


    public enum InvListColumn implements Browser.HTMLTableColumn { BOGUS, EXPAND, ORG, BUYERNUM, SUPPLIERNUM, TOTAL, POSTDATE, SUPPLIER, STATUS, ACTION }

    public Map<Browser.HTMLTableColumn, WebElement> getElementsForInvLine(String invnumber) {
        String rowXPath = "//td[contains(text(),'" + invnumber + "')]/parent::*";
        return browser.buildTableMap(invTable, rowXPath, InvListColumn.values());
    }
    public String getPONumberFromTable(String poNumber) {
        browser.waitForElementToAppear(By.xpath("//a[contains(text(),'"+poNumber+"')]"));
        String poNumberFromPage = browser.findElement(By.xpath("//a[contains(text(),'"+poNumber+"')]")).getText();
        return poNumber;
    }
}
