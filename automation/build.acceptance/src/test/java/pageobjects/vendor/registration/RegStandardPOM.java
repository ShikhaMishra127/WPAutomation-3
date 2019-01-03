package pageobjects.vendor.registration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.common.Browser;

import java.io.IOException;

public class RegStandardPOM {

    private final Browser browser;

    public RegStandardPOM(WebDriver browser) throws IOException {
        this.browser = (Browser)browser;
        PageFactory.initElements(((Browser)browser).driver, this);
    }

    //////////////////////////////////////////////////////////////////////// HOME PAGE
    @FindBy(xpath = "//h1[contains(text(),'Begin Your Registration')]")
    public WebElement homeBanner;

    @FindBy(xpath = "//img[contains(@name,'Image71')]")
    public WebElement startButton;

    //////////////////////////////////////////////////////////////////////// Shared Elements
    @FindBy(xpath = "//span[@class='regtop']")
    public WebElement stepTitle;

    @FindBy(xpath = "//img[contains(@title,'Continue')]")
    public WebElement continueButton;

    @FindBy(xpath = "//img[contains(@title,'Go Previous')]")
    public WebElement previousButton;

    //////////////////////////////////////////////////////////////////////// REGISTRATION STEP 1
    @FindBy(xpath = "//img[contains(@title,'Decline')]")
    public WebElement declineButton;

    @FindBy(xpath = "//img[contains(@title,'Accept')]")
    public WebElement acceptButton;

    //////////////////////////////////////////////////////////////////////// REGISTRATION STEP 2

    @FindBy(xpath = "//input[@name='orgInfoCmd.name']")
    public WebElement orgCompanyName;


    @FindBy(xpath = "//input[@name='orgInfoCmd.doingBusinessAs']")
    public WebElement orgDBAEdit;

    @FindBy(xpath = "//input[@name='orgInfoCmd.homeUrl']")
    public WebElement orgURLEdit;

    @FindBy(xpath = "//input[@name='orgInfoCmd.duns']")
    public WebElement orgDUNSEdit;

    @FindBy(xpath = "//input[@name='orgInfoCmd.fedtaxid1']")
    public WebElement orgFein1Edit;

    @FindBy(xpath = "//input[@name='orgInfoCmd.fedtaxid2']")
    public WebElement orgFein2Edit;

    @FindBy(xpath = "//input[@name='orgInfoCmd.ssn1']")
    public WebElement orgSsn1Edit;

    @FindBy(xpath = "//input[@name='orgInfoCmd.ssn2']")
    public WebElement orgSsn2Edit;

    @FindBy(xpath = "//input[@name='orgInfoCmd.ssn3']")
    public WebElement orgSsn3Edit;

    @FindBy(xpath = "//select[@name='orgInfoCmd.enterpriseTypeId']")
    public WebElement orgEnterpriseTypeDrop;

    @FindBy(xpath = "//select[@name='orgInfoCmd.stateIncorp']")
    public WebElement orgStateIncorporatedDrop;

    @FindBy(xpath = "//input[@name='orgInfoCmd.hq' and @value='Y']")
    public WebElement orgParentBranchRadioPARENT;

    @FindBy(xpath = "//input[@name='orgInfoCmd.hq' and @value='N']")
    public WebElement orgParentBranchRadioBRANCH;

    @FindBy(xpath = "//input[@name='orgInfoCmd.hqduns']")
    public WebElement orgDunsHQEdit;

    @FindBy(xpath = "//select[@name='orgInfoCmd.defaultTz']")
    public WebElement orgTimeZoneDrop;

    @FindBy(xpath = "//input[@name='orgInfoCmd.phone1']")
    public WebElement orgCompanyPhone1;

    @FindBy(xpath = "//input[@name='orgInfoCmd.phone2']")
    public WebElement orgCompanyPhone2;

    @FindBy(xpath = "//input[@name='orgInfoCmd.phone3']")
    public WebElement orgCompanyPhone3;

    @FindBy(xpath = "//input[@name='orgInfoCmd.fax1']")
    public WebElement orgCompanyFax1;

    @FindBy(xpath = "//input[@name='orgInfoCmd.fax2']")
    public WebElement orgCompanyFax2;

    @FindBy(xpath = "//input[@name='orgInfoCmd.fax3']")
    public WebElement orgCompanyFax3;

    @FindBy(xpath = "//input[@name='orgInfoCmd.email']")
    public WebElement orgEmailEdit ;

    @FindBy(xpath = "//input[@name='orgInfoCmd.confirmEmail']")
    public WebElement orgEmailConfirmEdit;

    @FindBy(xpath = "//select[@name='orgInfoCmd.country']")
    public WebElement orgCountryDrop;

    @FindBy(xpath = "//input[@name='orgInfoCmd.addr1']")
    public WebElement orgAddress1;

    @FindBy(xpath = "//input[@name='orgInfoCmd.addr2']")
    public WebElement orgAddress2;

    @FindBy(xpath = "//input[@name='orgInfoCmd.addr3']")
    public WebElement orgAddress3;

    @FindBy(xpath = "//input[@name='orgInfoCmd.city']")
    public WebElement orgCity;

    @FindBy(xpath = "//select[@name='orgInfoCmd.state']")
    public WebElement orgStateDrop;

    @FindBy(xpath = "//input[@name='orgInfoCmd.zip1']")
    public WebElement orgZip1;

    @FindBy(xpath = "//input[@name='orgInfoCmd.zip2']")
    public WebElement orgZip2;

    @FindBy(xpath = "//span[contains(text(),'Close')]")
    public WebElement getOrgDuplicateCloseButton;

    @FindBy(xpath = "//table[@class='errorTable']/tbody/tr/td/ol/li/span")
    public WebElement orgErrorMessage;

}
