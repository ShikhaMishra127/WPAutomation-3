package pageobjects.buyer.invoice;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.common.Browser;

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
    
}
