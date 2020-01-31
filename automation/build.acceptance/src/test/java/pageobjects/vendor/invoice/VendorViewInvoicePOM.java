package pageobjects.vendor.invoice;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.common.Browser;

import java.util.Map;

public class VendorViewInvoicePOM {

    private final Browser browser;

    public VendorViewInvoicePOM(WebDriver browser) {
        this.browser = (Browser) browser;
        PageFactory.initElements(((Browser) browser).driver, this);
    }


    //////////////////////////////////////////////////////////////////////// VENDOR SEARCH INVOICE PAGE

    @FindBy(xpath="//input[@id='ponumfilter']")
    public WebElement searchPONumberEdit;


    @FindBy(xpath="//input[@id='invoicenum']")
    public WebElement searchInvoiceNumberEdit;

    @FindBy(xpath="//button[contains(@onclick,'submitPage')]")
    public WebElement searchSubmitButton;

    @FindBy(xpath="//table[@id='invTable']")
    public WebElement invoiceResultTable;

    public String submenuViewIcon = "./span/ul/li/a[contains(@href,'viewinvoice')]";


    public enum VendorInvListColumn implements Browser.HTMLTableColumn {BOGUS, EXPAND, ORG, BUYERINVOICE, SUPPLIERINVOICE, TOTAL, POSTDATE, STATUS, ACTION}

    public Map<Browser.HTMLTableColumn, WebElement> getElementsForInvoiceLine(String invoiceNumber) {
        String rowXPath = "//td[contains(text(),'"+ invoiceNumber +"')]/parent::*";
        return browser.buildTableMap(invoiceResultTable, rowXPath, VendorInvListColumn.values());
    }

    //////////////////////////////////////////////////////////////////////// VENDOR INVOICE SUMMARY PAGE

    @FindBy(xpath="//td[@id='issuedate']")
    public WebElement summaryIssueDate;

    @FindBy(xpath="//td[@id='invnumber']")
    public WebElement summaryInvoiceNumber;

    @FindBy(xpath="//td[@id='misccharges']")
    public WebElement summaryMiscFreightAmount;

    @FindBy(xpath="//td[@id='misccomments']")
    public WebElement summaryMiscFreightComments;

    @FindBy(xpath="//button[@id='btnclose']")
    public WebElement summaryCloseButton;

}
