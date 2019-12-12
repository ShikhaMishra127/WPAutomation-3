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

    @FindBy(xpath = "(//div[@id='bottonButtons']//button[contains(@onclick,'submitPage')])[1]")
    public WebElement NextButton;

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

    @FindBy(xpath = "(//button[contains(@onclick,'submitPage')])[1]")
    public WebElement headNextButton;

    //////////////////////////////////////////////////////////////////////// NEW INVOICE ITEMS TAB

    @FindBy(xpath = "(//button[contains(@onclick,'poSrchPopUp')])[1]")
    public WebElement itemsPOSearchButton;

    @FindBy(xpath = "(//button[contains(@onclick,'invoiceAllPO')])[1]")
    public WebElement itemsInvoiceAllButton;

    @FindBy(xpath = "(//button[contains(@onclick,'previousStep')])[1]")
    public WebElement itemsPreviousButton;

    @FindBy(xpath = "(//button[contains(@onclick,'submitPage')])[1]")
    public WebElement itemsNextButton;

    ////////// PO Lookup dialog
    @FindBy(xpath = "//iframe[contains(@src,'InvoiceCreate')]")
    public WebElement itemsIFrame;

    @FindBy(xpath = "//input[@id='ponum_filter']")
    public WebElement itemsLookupOrderNumberEdit;

    @FindBy(xpath = "(//button[contains(@onclick,'submitSearch')])[1]")
    public WebElement itemsLookupSearchButton;

    @FindBy(xpath = "//section[@id='cont-search']")
    public WebElement itemsLookupResults;

    @FindBy(xpath = "//table/tbody/tr/th/input[@class='checkall']")
    public WebElement itemsPOIncludeAllCheck;

    @FindBy(xpath = "//button[contains(@onclick,'submitPO')]")
    public WebElement itemsAddPOItemsButton;

    public void clickPOExpand(String poNumber) {

        // sleep for a little - we need to wait for onclick event handler to be loaded before clicking icon
        browser.HardWait(3);

        String arrowxpath = "//table/tbody/tr/td/a[contains(text(),'" + poNumber + "')]/parent::*/preceding-sibling::*//i[contains(@class,'hand-pointer')]";
        WebElement element = browser.driver.findElement(By.xpath(arrowxpath));

        browser.clickWhenAvailable(element);
    }

    ////////// Invoice items main page (post PO selection)

    @FindBy(xpath="//input[contains(@id,'invoiceQty')]")
    public WebElement itemsInvoiceQtyEdit;

    @FindBy(xpath="//textarea[not(contains(@name,'freight'))][contains(@name,'invoicecomments')]")
    public WebElement itemsCommentEdit;

    @FindBy(xpath="//tbody/tr/td/div/input[@name='invoiceAmt_freight']")
    public WebElement itemsFreightEdit;

    @FindBy(xpath="//textarea[contains(@name,'invoicecomments_freight')]")
    public WebElement itemsFreightCommentEdit;


    //////////////////////////////////////////////////////////////////////// MATCHING TAB

    @FindBy(xpath="//button[contains(@onclick,'matchAll')]")
    public WebElement matchMatchAllButton;

    @FindBy(xpath="//button[contains(@onclick,'showSummaryPage')]")
    public WebElement matchNextButton;

    //////////////////////////////////////////////////////////////////////// MATCHING TAB

    @FindBy(xpath="//table[@id='details']/tbody/tr//td[2]")
    public WebElement summaryHeadDetails;

    @FindBy(xpath = "(//button[contains(@onclick,'submitPage')])[1]")
    public WebElement summarySubmitInvoice;

}
