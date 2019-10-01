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

    public String iiDownArrow = "./a[contains(@href,'getPos')]";
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


    public enum InvListColumn { BOGUS, EXPAND, ORG, BUYERNUM, SUPPLIERNUM, TOTAL, POSTDATE, SUPPLIER, STATUS, ACTION }

    public Map<InvListColumn, WebElement> getElementsForInvLine(String invnumber) {

        // Look for our req row in the filtered list of invoices
        String xpathrow = "//td[contains(text(),'" + invnumber + "')]/parent::*";
        browser.waitForElementToAppear(By.xpath(xpathrow));

        HashMap<InvListColumn, WebElement> elements = new HashMap<>();

        // build a list of WebElements that reference each column (name, number, status, etc)
        for (int i = 1; i < ViewReqPOM.ReqListColumn.values().length; i++) {

            String columnxpath = xpathrow + "/td[" + String.valueOf(i) +  "]";

            browser.waitForElementToAppear(By.xpath(columnxpath));
            elements.put(InvListColumn.values()[i], invTable.findElement(By.xpath(columnxpath)));
        }

        return elements;
    }


}
