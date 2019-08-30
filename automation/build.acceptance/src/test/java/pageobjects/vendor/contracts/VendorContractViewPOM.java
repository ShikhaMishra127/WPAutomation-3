package pageobjects.vendor.contracts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utilities.common.Browser;
import org.openqa.selenium.support.PageFactory;
import utilities.common.ResourceLoader;
import pageobjects.vendor.common.VendorNavBarPOM;
import org.openqa.selenium.support.FindBy;


public class VendorContractViewPOM {

    ResourceLoader resource = new ResourceLoader("data/contract");
    private final Browser browser;
    public VendorContractViewPOM(WebDriver browser) {

        this.browser = (Browser) browser;
        PageFactory.initElements(((Browser) browser).driver, this);
    }

    public void selectNavContractByBuyer(String buyername, String subitem) {

        WebElement header = topNav.findElement(By.xpath("//ul[contains(@class,'navbar-left')]//*[@title='Contracts']"));
        browser.clickWhenAvailable(header);

        WebElement menulink = topNav.findElement(By.xpath("//li[@class='dropdown open']//li[contains(text(),'"+ buyername +"')]"));
        menulink.findElement(By.xpath("./following-sibling::*/a[contains(text(),'"+ subitem +"')]")).click();

    }

    String contractNumber = resource.getValue("contract_number");

    @FindBy(xpath ="//span[@id='select2-deptfilter-container']")
    public WebElement ctDepartmentList;

    @FindBy(xpath = "//input[@id='contracttitlefilter']")
    public WebElement ctTitleSearchField;

    @FindBy(xpath = "//input[@id='contractnumfilter']")
    public WebElement ctNumberSearchField;

    @FindBy(xpath = "//span[@id='select2-statusfilter-container']")
    public WebElement ctDocumentList;

    @FindBy(xpath = "//button[@id='find']")
    public WebElement ctSubmitButton;

    @FindBy(xpath = "//button[@id='reset']")
    public WebElement ctResetButton;

    @FindBy(xpath = "//th[contains(text(),'Title')]")
    public WebElement ctTitle;

    @FindBy(xpath = "//th[contains(text(),'Organization')]")
    public WebElement ctOrg;

    @FindBy(xpath = "//th[contains(text(),'Effective')]")
    public WebElement ctEffectiveDate;

    @FindBy(xpath ="//th[contains(text(),'Expiration')]")
    public WebElement ctExpirationDate;

    @FindBy(xpath = "//ul[contains(@class,'navbar-left')]")
    public WebElement topNav;

}
