package pageobjects.buyer.orders;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.common.Browser;

public class ReceiveOrderPOM {

    private final Browser browser;

    public ReceiveOrderPOM(WebDriver browser) {
        this.browser = (Browser) browser;
        PageFactory.initElements(((Browser) browser).driver, this);
    }

    //////////////////////////////////////////////////////////////////////// COMMON CONTROLS

    @FindBy(xpath="//table[@class='overViewTable']/tbody/tr[1]/td[2]")
    public WebElement PONumberText;

    @FindBy(xpath="//button[@id='cmdSubmit']")
    public WebElement SubmitButton;

    @FindBy(xpath="//button[@name='cmdReceiveAll']")
    public WebElement ReceiveAllButton;

    @FindBy(xpath="//button[@name='cmdCancel']")
    public WebElement CancelButton;

    @FindBy(xpath="//input[@id='rcvDate']")
    public WebElement DateReceivedEdit;

    @FindBy(xpath="//input[@name='txtCarrier']")
    public WebElement CarrierEdit;

    @FindBy(xpath="//input[@name='txtFreightBill']")
    public WebElement FreightEdit;

    @FindBy(xpath="//input[@name='txtCartonCnt']")
    public WebElement CartonCountEdit;

    @FindBy(xpath="//input[@name='txtPackingSlip']")
    public WebElement PackingSlipEdit;

    @FindBy(xpath="//textarea[@name='txtHdrComments']")
    public WebElement HeaderCommentsEdit;

    @FindBy(xpath="//input[@name='txtReceivedQty']")
    public WebElement QuantityEdit;

    @FindBy(xpath="//input[@name='txtPackingSlipQty']")
    public WebElement PackingQuantityEdit;

    @FindBy(xpath="//select[@name='cboReceiptItemStatus']")
    public WebElement ItemStatusDrop;

    @FindBy(xpath="//textarea[@name='txtItemComments']")
    public WebElement ItemCommentsEdit;

    @FindBy(xpath="//input[@id='fileupload']")
    public WebElement AttachmentUploadButton;

}
