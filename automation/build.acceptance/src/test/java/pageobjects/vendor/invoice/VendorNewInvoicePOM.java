package pageobjects.vendor.invoice;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.common.Browser;

public class VendorNewInvoicePOM {

    private final Browser browser;

    public VendorNewInvoicePOM(WebDriver browser) {
        this.browser = (Browser) browser;
        PageFactory.initElements(((Browser) browser).driver, this);
    }


    //////////////////////////////////////////////////////////////////////// COMMON CONTROLS

    @FindBy(xpath="(//button[contains(@onclick,'submitPage')])[1]")
    public WebElement nextButton;

    //////////////////////////////////////////////////////////////////////// INVOICE HEADER PAGE

    @FindBy(xpath="//input[@id='invoicenum']")
    public WebElement headerInvoiceNumberEdit;

    @FindBy(xpath="//input[@id='issue_date']")
    public WebElement headerIssueDate;

    @FindBy(xpath="//input[@id='due_date']")
    public WebElement headerDueDate;

    @FindBy(xpath="//textarea[@id='invoicecomments']")
    public WebElement headerInvoiceComments;

    @FindBy(xpath="//button[contains(@onclick,'findPO')]")
    public WebElement headerFindPOButton;

    @FindBy(xpath="//button[contains(@onclick,'submitPage')]")
    public WebElement headerNextButton;

    @FindBy(xpath="//input[@id='invoiceAmt_freight']")
    public WebElement headerFreightAmtEdit;

    @FindBy(xpath="//textarea[@id='invoicecomments_freight']")
    public WebElement headerFreightCommentEdit;

    ////////// Invoice items main page (post PO selection)

    @FindBy(xpath="//input[contains(@id,'invoiceQty')]")
    public WebElement itemsInvoiceQtyEdit;

    @FindBy(xpath="//textarea[not(contains(@name,'freight'))][contains(@name,'invoicecomments')]")
    public WebElement itemsCommentEdit;

    @FindBy(xpath="//tbody/tr/td/div/input[@name='invoiceAmt_freight']")
    public WebElement itemsFreightEdit;

    @FindBy(xpath="//textarea[contains(@name,'invoicecomments_freight')]")
    public WebElement itemsFreightCommentEdit;

    @FindBy(xpath="//button[contains(@onclick,'javascript:submitPage')]")
    public WebElement itemsNextButton;

    //////////////////////////////////////////////////////////////////////// ATTACHMENTS TAB

    @FindBy(xpath="//button[@id='save']")
    public WebElement attachNextButton;

    @FindBy(xpath="//div[@class='modal-dialog']")
    public WebElement attachConfirmationModal;

    @FindBy(xpath="//button[contains(@data-bb-handler,'confirm')]")
    public WebElement attachYesContinueButton;


    //////////////////////////////////////////////////////////////////////// SUMMARY TAB

    @FindBy(xpath="//table[@class='overViewTable']")
    public WebElement summaryOverViewText;

    @FindBy(xpath="//a[contains(@href,'viewPO')]")
    public WebElement summaryPONumberText;

    @FindBy(xpath="//td[@id='misccharges']")
    public WebElement summaryMiscChargesText;

    @FindBy(xpath="//button[@id='btnsubmit']")
    public WebElement summarySubmitInvoiceButton;

    @FindBy(xpath="//div[@class='modal-dialog']")
    public WebElement summaryConfirmationModal;

    @FindBy(xpath="//button[contains(@data-bb-handler,'confirm')]")
    public WebElement summaryYesContinueButton;

    @FindBy(xpath="//button[@id='closemodal']")
    public WebElement summaryClosePostSubmitButton;

}
