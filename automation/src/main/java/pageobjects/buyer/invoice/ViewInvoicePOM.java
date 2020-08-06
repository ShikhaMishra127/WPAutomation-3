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


    @FindBy(xpath="//table[contains(@id,'potable')]")
    public WebElement mainExpandPOTable;

    public String iiDownArrow = "./a[contains(@href,'getItems')]";
    public String iiEllipsis = "./span/button";
    public String iiViewInvoice = "./span/ul/li/a[contains(@href,'&mode=view')]";
    public String iiEditInvoice = "./span/ul/li/a[contains(@href,'&mode=edit')]";
    public String iiPrintInvoice = "./span/ul/li/a[contains(@href,'InvoicePrint')]";
    public String iiCancelInvoice = "./span/ul/li/a[contains(@href,'InvoiceCancel')]";
    public String iiInvoiceHistory = "./span/ul/li/a[contains(@href,'InvoiceHistory')]";
    public String iiSupplierNotify = "./span/ul/li/a[contains(@href,'supplierNotification')]";

    public boolean ExpandedPOExists(String ponumber) {

        browser.waitForElementToAppear(mainExpandPOTable);

        String xpath = "//tbody/tr/td[contains(text(),'"+ ponumber +"')]";

        return browser.elementExists(By.xpath(xpath));
    }


    public enum InvListColumn implements Browser.HTMLTableColumn { BOGUS, EXPAND, ORG, BUYERNUM, SUPPLIERNUM, TOTAL, POSTDATE, SUPPLIER, STATUS, ACTION }

    public Map<Browser.HTMLTableColumn, WebElement> getElementsForInvLine(String invnumber) {
        String rowXPath = "//td[contains(text(),'" + invnumber + "')]/parent::*";
        return browser.buildTableMap(invTable, rowXPath, InvListColumn.values());
    }
}
