package pageobjects.vendor.registration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.common.Browser;

import java.io.IOException;

public class RegWhiteLabelPOM {

    private final Browser browser;

    public RegWhiteLabelPOM(WebDriver browser) throws IOException {
        this.browser = (Browser)browser;
        PageFactory.initElements(((Browser)browser).driver, this);
    }

    //////////////////////////////////////////////////////////////////////// COMMON

    @FindBy(xpath = "//iframe[@id='whitelabelregframe']")
    public WebElement regFrame;

    @FindBy(xpath = "//button[@id='next']")
    public WebElement nextButton;


    //////////////////////////////////////////////////////////////////////// DOING BUSINESS PAGE

    @FindBy(xpath = "(//span[@class='stepheader'])[3]")
    //@FindBy(xpath = "(//div[contains(@class,'ui-corner-all')]//span[@class='stepheader']")
    public WebElement stepHeader;

    @FindBy(xpath = "//input[@id='termsncondition']")
    public WebElement termsCheckbox;

    @FindBy(xpath = "//div[@class='fieldset-content']/label[@class='error']")
    public WebElement termsErrorMessage;


    //////////////////////////////////////////////////////////////////////// ORGANIZATION INFO PAGE

    @FindBy(xpath = "//div[@id='compInfo']/h2/span")
    public WebElement orgInfoTitle;

    @FindBy(xpath = "//select[@id='country']")
    public WebElement orgCountryDrop;


    @FindBy(xpath = "//input[@id='hq1']")
    public WebElement orgHQRadio;

    @FindBy(xpath = "//input[@id='hq2']")
    public WebElement orgBranchRadio;

    @FindBy(xpath = "//input[@id='fein1']")
    public WebElement orgFeinEdit1;

    @FindBy(xpath = "//input[@id='fein2']")
    public WebElement orgFeinEdit2;

    @FindBy(xpath = "//input[@id='retypefein1']")
    public WebElement orgFeinConfirmEdit1;

    @FindBy(xpath = "//input[@id='retypefein2']")
    public WebElement orgFeinConfirmEdit2;

    @FindBy(xpath = "//label[@id='FeinError']")
    public WebElement orgDuplicateFeinError;

    @FindBy(xpath = "//input[@id='ssn1']")
    public WebElement orgSsnEdit1;

    @FindBy(xpath = "//input[@id='ssn2']")
    public WebElement orgSsnEdit2;

    @FindBy(xpath = "//input[@id='ssn3']")
    public WebElement orgSsnEdit3;

    @FindBy(xpath = "//input[@id='retypessn1']")
    public WebElement orgSsnConfirmEdit1;

    @FindBy(xpath = "//input[@id='retypessn2']")
    public WebElement orgSsnConfirmEdit2;

    @FindBy(xpath = "//input[@id='retypessn3']")
    public WebElement orgSsnConfirmEdit3;

    @FindBy(xpath = "//label[@id='ssnError']")
    public WebElement orgDuplicateSsnError;

    @FindBy(xpath = "//input[@id='duns']")
    public WebElement orgDunsEdit;

    @FindBy(xpath = "//input[@id='confirmduns']")
    public WebElement orgDunsConfirmEdit;

    @FindBy(xpath = "//input[@id='supname']")
    public WebElement orgCompanyName;

    @FindBy(xpath = "//input[@id='Address1']")
    public WebElement orgAddressEdit1;

    @FindBy(xpath = "//input[@id='Address2']")
    public WebElement orgAddressEdit2;

    @FindBy(xpath = "//input[@id='city']")
    public WebElement orgCityEdit;

    @FindBy(xpath = "//select[@id='state']")
    public WebElement orgStateDrop;

    @FindBy(xpath = "//input[@id='zipcode']")
    public WebElement orgZipEdit;

    @FindBy(xpath = "//select[@id='suptype']")
    public WebElement orgBusinessTypeDrop;
}


/*

    @FindBy(xpath = "")
    public WebElement ;

    @FindBy(xpath = "")
    public WebElement ;

    @FindBy(xpath = "")
    public WebElement ;

    @FindBy(xpath = "")
    public WebElement ;

    @FindBy(xpath = "")
    public WebElement ;

    @FindBy(xpath = "")
    public WebElement ;

    @FindBy(xpath = "")
    public WebElement ;

    @FindBy(xpath = "")
    public WebElement ;

    @FindBy(xpath = "")
    public WebElement ;

    @FindBy(xpath = "")
    public WebElement ;

    @FindBy(xpath = "")
    public WebElement ;

 */