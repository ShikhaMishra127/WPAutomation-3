package pageobjects.common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.common.Browser;
import utilities.common.OracleQuery;

public class LoginPagePOM {

    private Browser browser;

    public LoginPagePOM(WebDriver browser) {
        this.browser = (Browser) browser;
        PageFactory.initElements(((Browser) browser).driver, this);
		//normalizePassword(Browser.buyerUsername);
	}

	private void normalizePassword(String username) {

		OracleQuery db = new OracleQuery();
		String query =	"UPDATE ORG_PERSON " +
						"SET USERPWD = 'xa4BWbmm+g1ZeMFQKHL0jxFciXfDJ+9je30oN1QOiI3D0roJWe/Koq/yb7XB2UwPqBEV3fQlO1tdfTCE+QLDfyOYCPVMk+wz5N+k1TyhmEZZHyqQlmc4F3IOkxRJ/PH9', " +
						"PWD_CHANGE_DATE = to_date('123120', 'MMDDYY'), " +
						"USERPWD_SALT = 'd+SMnnlTS/abHFVjioec4Q==', " +
						"ISLOCKEDOUT = 0 " +
						"WHERE USERNAME = '" + username +"'";
		db.Connect();
		db.executeUpdate(query);
		db.Close();
	}


    @FindBy(xpath = "//button[contains(@id,'saveCookieSettings')]")
    public WebElement okcookie;

    @FindBy(id = "visibleUname")
    public WebElement txtUsername;

    @FindBy(id = "visiblePass")
    public WebElement txtPassword;

    @FindBy(id = "login-submit")
    public WebElement btnLogin;

    @FindBy(xpath = "//a[contains(@href,'/supplierReg')]")
    public WebElement lnkRegister;

    @FindBy(xpath = "//a[@id='userMenu']")
    public WebElement logoutMenu;

    @FindBy(xpath = "//a[text()='Logout']")
    public WebElement lnkLogout;

    @FindBy(xpath = "//div[contains(@class,'error alert alert-danger')]")
    public WebElement alert;

    @FindBy(xpath = "(//img[@src='/images/registration/arrow_accept.gif'])[1]")
    public WebElement vendorAcceptTermsButton;

    @FindBy(xpath = "(//img[@src='/images/registration/arrow_decline.gif'])[1]")
    public WebElement vendorDeclineTermsButton;

    @FindBy(xpath="//footer[@id='footerRow']")  // both buyer an supplier have the same footer
    public WebElement homePageFooter;



    //////////////////////////////////////////////////////////////////////// "WHAT'S NEW" FEATURE

    @FindBy(xpath="//div[contains(@class,'modal-body')]")
    public WebElement whatsnewModal;

    @FindBy(xpath="//input[@id='featureShowCheckbox']")
    public WebElement whatsnewShowAtLoginCheckbox;

    @FindBy(xpath="//button[contains(@onclick,'closeFeaturePopup')]")
    public WebElement whatsnewGotItButton;

    private void checkWhatsNew() {

        browser.waitForElementToAppear(homePageFooter);
        browser.waitForElementToBeClickable(whatsnewGotItButton, (long)3);

        // If that ridiculous "What's New" modal shows up, check box and close
        if (browser.elementExists(whatsnewGotItButton)) {
            browser.clickWhenAvailable(whatsnewShowAtLoginCheckbox);
            browser.clickWhenAvailable(whatsnewGotItButton);
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////

    public void setUsername(String str) {
        browser.waitForElementToBeClickable(txtUsername);
        txtUsername.sendKeys(str);
    }

    public void handleCookie() {
        try {
            browser.waitForElementToBeClickable(okcookie);
            okcookie.click();
        } catch (Exception e) {
            System.out.println("Popup not present");
        }
    }

    public void setPassword(String str) {
        txtPassword.sendKeys(str);
    }

    public void clickOnLogin() {
        btnLogin.click();

    }

    public void clickOnRegisterLink() {
        browser.waitForElementToBeClickable(lnkRegister);
        lnkRegister.click();
    }

    public void loginAsUser(String username, String password) {
        handleCookie();
        setUsername(username);
        setPassword(password);
        clickOnLogin();
        checkWhatsNew();

        browser.waitForPageLoad();

    }

    public void loginAsBuyer() {
        loginAsUser(browser.buyerUsername, browser.buyerPassword);
    }

    public void loginAsApprover() {
        loginAsUser(browser.approverUsername, browser.approverPassword);
    }

}
