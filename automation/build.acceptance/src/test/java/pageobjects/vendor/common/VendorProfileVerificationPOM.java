package pageobjects.vendor.common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.common.Browser;

import java.io.IOException;

public class VendorProfileVerificationPOM {

    private final Browser browser;

    public VendorProfileVerificationPOM(WebDriver browser) throws IOException {
        this.browser = (Browser)browser;
        PageFactory.initElements(((Browser)browser).driver, this);
    }

    ///// PAGE OBJECTS
    @FindBy(xpath = "//button[@id='okBtn']")
    public WebElement okButton;

    @FindBy(xpath = "//button[@id='editBtn']")
    public WebElement editButton;

    @FindBy(xpath = "//label[contains(text(),'Company Name')]/parent::*/following-sibling::*/p")
    public WebElement companyName;

    @FindBy(xpath = "//label[contains(text(),'First Name')]/parent::*/following-sibling::*/p")
    public WebElement firstName;

    @FindBy(xpath = "//label[contains(text(),'Last Name')]/parent::*/following-sibling::*/p")
    public WebElement lastName;

    @FindBy(xpath = "//label[contains(text(),'UserName')]/parent::*/following-sibling::*/p")
    public WebElement username;

    @FindBy(xpath = "//label[contains(text(),'Type of Business')]/parent::*/following-sibling::*/p")
    public WebElement typeOfBusiness;

    @FindBy(xpath = "//label[contains(text(),'FEIN')]/parent::*/following-sibling::*/p")
    public WebElement fein;

    @FindBy(xpath = "//label[contains(text(),'SSN')]/parent::*/following-sibling::*/p")
    public WebElement ssn;

    @FindBy(xpath = "//input[@id='fein1']")
    public WebElement fein1Edit;

    @FindBy(xpath = "//input[@id='fein2']")
    public WebElement fein2Edit;

    @FindBy(xpath = "//input[@id='fein1Again']")
    public WebElement fein1ConfirmEdit;

    @FindBy(xpath = "//input[@id='fein2Again']")
    public WebElement fein2ConfirmEdit;

    @FindBy(xpath = "//input[@id='ssn1']")
    public WebElement ssn1Edit;

    @FindBy(xpath = "//input[@id='ssn2']")
    public WebElement ssn2Edit;

    @FindBy(xpath = "//input[@id='ssn3']")
    public WebElement ssn3Edit;

    @FindBy(xpath = "//input[@id='ssn1Again']")
    public WebElement ssn1ConfirmEdit;

    @FindBy(xpath = "//input[@id='ssn2Again']")
    public WebElement ssn2ConfirmEdit;

    @FindBy(xpath = "//input[@id='ssn3Again']")
    public WebElement ssn3ConfirmEdit;

    @FindBy(xpath = "//input[@id='hq']")
    public WebElement hqRadio;

    @FindBy(xpath = "//input[@id='branch']")
    public WebElement branchRadio;

    @FindBy(xpath = "//input[@id='duns']")
    public WebElement dunsEdit;

    @FindBy(xpath = "//button[@id='updateBtn']")
    public WebElement updateButton;

    @FindBy(xpath = "//button[@id='cancelBtn']")
    public WebElement cancelButton;
}
