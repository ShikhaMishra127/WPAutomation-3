package pageobjects.buyer.req;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.common.Browser;

import java.util.List;

public class NewReqPOM {

    private final Browser browser;

    public NewReqPOM(WebDriver browser) {
        this.browser = (Browser) browser;
        PageFactory.initElements(((Browser) browser).driver, this);
    }

    //////////////////////////////////////////////////////////////////////// COMMON CONTROLS

    // handle to entire req container
    @FindBy(name = "C1ReqMain")
    public WebElement reqIFrame;

    // tab elements above new req container
    @FindBy(xpath = "//li[contains(@class,'paginate_button')]/a[contains(@href,'javascript:void')]")
    public WebElement catalogTab;

    @FindBy(xpath = "//a[@id='idRoundTrip']")
    public WebElement roundTripTab;

    @FindBy(xpath = "//a[@id='idFavorites']")
    public WebElement favoritesTab;

    @FindBy(xpath = "//a[@id='idOff-Catalog Request']")
    public WebElement offCatalogTab;

    @FindBy(xpath = "//a[@id='idTemplates']")
    public WebElement templatesTab;

    @FindBy(xpath = "//a[@id='idView Request']")
    public WebElement viewReqTab;

    // any modal dialogs that pop-up when creating a req
    @FindBy(xpath = "//body[contains(@onload,'DisplayMsg()')]")
    public WebElement modalDialog;

    @FindBy(xpath="//button[@data-bb-handler='cancel']")
    public WebElement modalNoButton;

    //////////////////////////////////////////////////////////////////////// NEW REQ FOOTER

    @FindBy(name = "reqcart")
    public WebElement footerIFrame;

    @FindBy(xpath="//input[@id='txtReqName']")
    public WebElement reqNameEdit;

    @FindBy(xpath="(//span[@class='CartInfo'])[2]")
    public WebElement footerItemCount;


    //////////////////////////////////////////////////////////////////////// OFF CATALOG PAGE

    @FindBy(xpath = "//input[@id='OrderQty']")
    public WebElement ocOrderQtyEdit;

    @FindBy(xpath = "//input[@id='UnitPrice']")
    public WebElement ocUnitPriceEdit;

    @FindBy(xpath = "//input[@id='SupplierPartNum']")
    public WebElement ocSupplierPartNoEdit;

    @FindBy(xpath = "//input[@id='input_MfrName']")
    public WebElement ocMfrNameEdit;

    @FindBy(xpath="//div[@id='grp_needbydate']//input[@id='dateNeedBy']")
    public WebElement ocNeedByDateEdit;

    @FindBy(xpath="//input[@id='input_catcode']")
    public WebElement ocCommodityEdit;


    @FindBy(xpath="//button[@data-bb-handler='cancel']")
    public WebElement ocNoContractConfirmButton;

    @FindBy(xpath = "//ul[contains(@class,'ui-autocomplete')]/li/a/div/div")
    public List<WebElement> ocCommodityList;

    @FindBy(xpath="//div[@id='input_CommodityDescc']")
    public WebElement ocCommodityDescEdit;

    @FindBy(xpath="//span[@class='select2-selection select2-selection--single']")
    public WebElement ocUOMDrop;

    @FindBy(xpath="//select[@id='selCurrencyCode']")
    public WebElement ocCurrencyDrop;

    @FindBy(xpath="//input[@id='BuyerPartNum']")
    public WebElement ocBuyerPartNoEdit;

    @FindBy(xpath="//input[@id='input_SupplierName']")
    public WebElement ocVendorNameEdit;

    @FindBy(xpath = "//ul[contains(@class,'ui-autocomplete')]/li/div")
    public List<WebElement> ocVendorList;

    @FindBy(xpath="//span[@id='btnSuppSrch']")
    public WebElement ocVendorSearchButton;

    @FindBy(xpath="//select[@id='cboUsageCode']")
    public WebElement ocUsageCodeDrop;

    @FindBy(xpath="//input[@id='chk-retain-top']")
    public WebElement ocRetainInfoCheck;

    @FindBy(xpath="//button[@id='btn-add-top']")
    public WebElement ocAddItemButton;

    @FindBy(xpath="//input[@id='contractNum_Toggle']/parent::*")
    public WebElement ocAssociateContractCheck;

    @FindBy(xpath="//span[contains(@href,'resetCommoditySrch')]")
    public WebElement ocResetCommoditySearchButton;

    
    //////////////////////////////////////////////////////////////////////// VIEW REQUEST PAGE

    @FindBy(xpath="//button[contains(@onclick,'ReqSubmit')]")
    public WebElement vrSubmitReqButton;

    @FindBy(xpath="//button[contains(@onclick,'OrderSubmit')]")
    public WebElement vrConfirmSubmitReqButton;

    @FindBy(xpath="//button[@name='btnClose']")
    public WebElement vrCloseReqButton;

}
