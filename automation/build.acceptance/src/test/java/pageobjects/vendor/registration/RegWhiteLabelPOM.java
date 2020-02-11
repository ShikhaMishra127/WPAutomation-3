package pageobjects.vendor.registration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.common.Browser;

public class RegWhiteLabelPOM {

    private final Browser browser;

    public RegWhiteLabelPOM(WebDriver browser) {
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

    @FindBy(xpath = "//a[@id='disableCompValidation']")
    public WebElement orgValidateAddressLink;

    @FindBy(xpath = "//select[@id='suptype']")
    public WebElement orgBusinessTypeDrop;

    @FindBy(xpath = "//input[@id='divnow']")
    public WebElement orgDiversitySectionRadio;

    @FindBy(xpath = "//input[@id='wbe']")
    public WebElement orgDiversityWbeRadio;

    @FindBy(xpath = "//input[@id='mbe']")
    public WebElement orgDiversityMbeRadio;

    @FindBy(xpath = "//input[@id='dbe']")
    public WebElement orgDiversityDbeCheckbox;

    @FindBy(xpath = "//input[@id='emvendor2']")
    public WebElement orgEmergencySectionRadio;

    @FindBy(xpath = "//input[@id='emcontactname']")
    public WebElement orgEmergencyNameEdit;

    @FindBy(xpath = "//input[@id='emcontactemail']")
    public WebElement orgEmergencyEmailEdit;

    @FindBy(xpath = "//input[@id='verifyemcontactemail']")
    public WebElement orgEmergencyEmailConfirmEdit;

    @FindBy(xpath = "//input[@id='emphone']")
    public WebElement orgEmergencyPhoneEdit;

    @FindBy(xpath = "//input[@id='verifyemphone']")
    public WebElement orgEmergencyPhoneConfirmEdit;


    //////////////////////////////////////////////////////////////////////// CONTACT INFORMATION PAGE

    @FindBy(xpath = "//div[@id='contactinfo']/h2/span")
    public WebElement contactInfoTitle;

    @FindBy(xpath = "//select[@id='salutation']")
    public WebElement contactSalutationDrop;

    @FindBy(xpath = "//input[@id='fname']")
    public WebElement contactFirstNameEdit;

    @FindBy(xpath = "//input[@id='lname']")
    public WebElement contactLastNameEdit;

    @FindBy(xpath = "//input[@id='jobtitle']")
    public WebElement contactJobEdit;

    @FindBy(xpath = "//input[@id='myphone']")
    public WebElement contactPhoneEdit;

    @FindBy(xpath = "//input[@id='verifymyphone']")
    public WebElement contactPhoneConfirmEdit;

    @FindBy(xpath = "//input[@id='myfax']")
    public WebElement contactFaxEdit;

    @FindBy(xpath = "//input[@id='verifymyfax']")
    public WebElement contactFaxConfirmEdit;

    @FindBy(xpath = "//input[@id='username']")
    public WebElement contactEmailEdit;

    @FindBy(xpath = "//input[@id='verifyusername']")
    public WebElement contactEmailConfirmEdit;

    @FindBy(xpath = "//input[@id='username1']")
    public WebElement contactUsernameEdit;

    @FindBy(xpath = "//input[@id='password']")
    public WebElement contactPasswordEdit;

    @FindBy(xpath = "//input[@id='retypePassword']")
    public WebElement contactPasswordConfirmEdit;


    //////////////////////////////////////////////////////////////////////// PAYMENT INFORMATION PAGE

    @FindBy(xpath = "//div[@id='paymentTypeSelection']/h2/span")
    public WebElement paymentInfoTitle;

    @FindBy(xpath = "//tr[@id='paymentType_Row_1']//input[@type='checkbox']")
    public WebElement paymentActionFirstCheckbox;


    //////////////////////////////////////////////////////////////////////// COMMODITY CODES PAGE

    @FindBy(xpath = "//div[@id='categories']/h2/span")
    public WebElement commodityInfoTitle;

    @FindBy(xpath = "//input[@id='searchTxt']")
    public WebElement commoditySearchEdit;

    @FindBy(xpath = "//button[@id='searchCats']")
    public WebElement commoditySearchButton;

    @FindBy(xpath = "//button[@id='clearsearch']")
    public WebElement commoditySearchClearButton;

    @FindBy(xpath = "//div[@id='catTreeDiv']")
    public WebElement commoditySearchResults;

    //////////////////////////////////////////////////////////////////////// REGISTRATION COMPLETE PAGE

    @FindBy(xpath = "//div[@id='confirmmsg']//p[contains(text(),'Your username')]/b")
    public WebElement finalUsername;

    @FindBy(xpath = "//a[@class='sexybutton sexylarge sexysimple customblue']")
    public WebElement finalTakeMeToWPButton;

}
