package pageobjects.vendor.registration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.common.Browser;

public class RegFormBuilderPOM {

    private final Browser browser;

    public RegFormBuilderPOM(WebDriver browser) {
        this.browser = (Browser)browser;
        PageFactory.initElements(((Browser)browser).driver, this);
    }

    //////////////////////////////////////////////////////////////////////// COMMON

    @FindBy(xpath="//app-multi-page-processor/div/div/form/div/button/i[contains(@class,'arrow-right')]/parent::*")
    public WebElement nextButton;

    @FindBy(xpath="(//app-multi-page-processor/div/div/form/div/button/i[contains(@class,'save')])[1]")
    public WebElement saveButton;

    @FindBy(xpath="(//app-multi-page-processor/div/div/form/div/button/i[contains(@class,'save')])[2]")
    public WebElement submitButton;


    //////////////////////////////////////////////////////////////////////// COMPANY INFO PAGE
    @FindBy(xpath="//app-country-dropdown//select[@name='country']")
    public WebElement countryDrop;

    @FindBy(xpath="//app-name-of-entity//input[@id='supname']")
    public WebElement supplierNameEdit;

    @FindBy(xpath="//app-dba-branch-name//input[@id='dba']")
    public WebElement dbaEdit;

    @FindBy(xpath="//app-business-type//select")
    public WebElement businessTypeDrop;

    @FindBy(xpath="//app-fein/base-element/div/element-structure/div/div")
    public WebElement feinSection;

    public String feinPt1 = "./input[1]";
    public String feinPt2 = "./input[2]";
    public String feinConfirmPt1 = "./input[3]";
    public String feinConfirmPt2 = "./input[4]";

    @FindBy(xpath="(//app-vendor-address/base-element/div/element-structure/div/div/div)[1]/input")
    public WebElement compAddrLine1Edit;

    @FindBy(xpath="(//app-vendor-address/base-element/div/element-structure/div/div/div)[3]/input")
    public WebElement compAddrLine2Edit;

    @FindBy(xpath="(//app-vendor-address/base-element/div/element-structure/div/div/div)[2]/input")
    public WebElement compAddrCityEdit;

    @FindBy(xpath="(//app-vendor-address/base-element/div/element-structure/div/div/div)[4]/select")
    public WebElement compAddrStateDrop;

    @FindBy(xpath="(//app-vendor-address/base-element/div/element-structure/div/div/div)[5]//input")
    public WebElement compAddrZipEdit;

    public void VerifyIfInvalidAddress() {

        String xpath = "//span/a[contains(.,'here')]";

        browser.clickWhenAvailable(nextButton);
        browser.HardWait(4);

        if (browser.elementExists(By.xpath(xpath))) {
            browser.clickWhenAvailable(By.xpath(xpath));
            browser.clickWhenAvailable(nextButton);
        }
    }

    //////////////////////////////////////////////////////////////////////// USER INFO PAGE

    @FindBy(xpath="//app-main-contact/base-element/div/element-structure/div/div/div/select")
    public WebElement userSalutationDrop;

    @FindBy(xpath="(//app-main-contact/base-element/div/element-structure/div/div/div/input)[1]")
    public WebElement userFirstNameEdit;

    @FindBy(xpath="(//app-main-contact/base-element/div/element-structure/div/div/div/input)[2]")
    public WebElement userLastNameEdit;

    @FindBy(xpath="(//app-main-contact/base-element/div/element-structure/div/div/div/input)[3]")
    public WebElement userJobTitleEdit;

    @FindBy(xpath="//app-main-contact/base-element/div/element-structure/div/div/div/div/div/input")
    public WebElement userPhoneEdit;

    @FindBy(xpath="//app-main-contact/base-element/div/element-structure/div/div/div/div/input")
    public WebElement userFaxEdit;

    @FindBy(xpath="(//app-username-password/base-element/div/element-structure/div/div/div/div/input)[1]")
    public WebElement userEmailAddressEdit;

    @FindBy(xpath="(//app-username-password/base-element/div/element-structure/div/div/div/div/input)[2]")
    public WebElement userUsernameEdit;

    @FindBy(xpath="//app-username-password/base-element/div/element-structure/div/div[2]/div[1]/div/input")
    public WebElement userPasswordEdit;

    @FindBy(xpath="//app-username-password/base-element/div/element-structure/div/div[2]/div[2]/div/input")
    public WebElement userConfirmPasswordEdit;

    //////////////////////////////////////////////////////////////////////// COMMODITY INFO PAGE

    @FindBy(xpath="//app-commodities/base-element/div/element-structure/div/iframe")
    public WebElement commFrame;

    //////////////////////////////////////////////////////////////////////// SUMMARY PAGE

    @FindBy(xpath="//strong[1]")
    public WebElement summarySupplierName;


}
