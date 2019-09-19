package pageobjects.buyer.invoice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.common.Browser;

import java.util.List;

public class NewInvoicePOM {

    private final Browser browser;

    public NewInvoicePOM(WebDriver browser) {
        this.browser = (Browser) browser;
        PageFactory.initElements(((Browser) browser).driver, this);
    }

    //////////////////////////////////////////////////////////////////////// COMMON CONTROLS

    //////////////////////////////////////////////////////////////////////// NEW INVOICE HEADER TAB

    @FindBy(xpath = "//form[@name='theform']/section/div/div/input")
    public WebElement headBuyerInvoiceNumberEdit;

    @FindBy(xpath = "//input[contains(@id,'supplierSrch')]")
    public WebElement headSupplierSearchEdit;

    @FindBy(xpath = "//ul[contains(@class,'ui-autocomplete')]//a")
    public List<WebElement> headSupplierSearchList;

    @FindBy(xpath = "//input[@id='invoicenum']")
    public WebElement headSupplierInvoiceNumberEdit;

    @FindBy(xpath = "//input[@id='receive_date']")
    public WebElement headReceiveDateEdit;

    @FindBy(xpath = "//input[@id='post_date']")
    public WebElement headPostDateEdit;

    @FindBy(xpath = "//input[@id='issue_date']")
    public WebElement headIssueDateEdit;

    @FindBy(xpath = "//input[@id='due_date']")
    public WebElement headDueDateEdit;

    @FindBy(xpath = "//select[@id='eft']")
    public WebElement headEFTDrop;

    @FindBy(xpath = "//button[contains(@onclick,'javascript:submitPage')]")
    public WebElement headNextButton;

    //////////////////////////////////////////////////////////////////////// NEW INVOICE ITEMS TAB

    @FindBy(xpath = "//button[contains(@onclick,'javascript:poSrchPopUp')][1]")
    public WebElement itemsPOSearchButton;

    @FindBy(xpath = "//button[contains(@onclick,'javascript:invoiceAllPO')][1]")
    public WebElement itemsInvoiceAllButton;

    @FindBy(xpath = "//button[contains(@onclick,'javascript:previousStep')][1]")
    public WebElement itemsPreviousButton;

    @FindBy(xpath = "//button[contains(@onclick,'javascript:submitPage')][1]")
    public WebElement itemsNextButton;

    ////////// PO Lookup dialog
    @FindBy(xpath = "//iframe[contains(@src,'InvoiceCreate')]")
    public WebElement itemsIFrame;

    @FindBy(xpath = "//input[@id='ponum_filter']")
    public WebElement itemsLookupOrderNumberEdit;

    @FindBy(xpath = "//button[contains(@onclick,'javascript:submitSearch')]")
    public WebElement itemsLookupSearchButton;

    @FindBy(xpath = "//section[@id='cont-search']")
    public WebElement itemsLookupResults;

    @FindBy(xpath = "//table/tbody/tr/th/input[@class='checkall']")
    public WebElement itemsPOIncludeAllCheck;

    @FindBy(xpath = "//button[contains(@onclick,'submitPO')]")
    public WebElement itemsAddPOItemsButton;

    public void clickPOExpand(String poNumber) {
        String arrowxpath = "//table/tbody/tr/td/a[contains(text(),'" + poNumber + "')]/parent::*/preceding-sibling::*";
        browser.clickWhenAvailable(By.xpath(arrowxpath));
    }
}
