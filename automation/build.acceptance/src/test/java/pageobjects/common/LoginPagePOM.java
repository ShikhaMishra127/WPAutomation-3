package pageobjects.common;

import java.io.IOException;
import java.util.Locale;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.common.Browser;
import utilities.common.ResourceLoader;

public class LoginPagePOM extends Browser {
	
	public LoginPagePOM() throws IOException {
		super();
		PageFactory.initElements(Browser.getDriver(), this);
	}

	@FindBy(xpath = "//button[contains(@id,'saveCookieSettings')]")
	public WebElement okcookie;

	@FindBy(id="visibleUname")
	public WebElement txtUsername;

	@FindBy(id="visiblePass")
	public WebElement txtPassword;

	@FindBy(id = "login-submit")
	public WebElement btnLogin;

	@FindBy(xpath = "//a[text()='Register Here']")
	public WebElement lnkRegister;

	@FindBy(xpath = "//a[@id='userMenu']")
	public WebElement logoutMenu;

	@FindBy(xpath = "//a[text()='Logout']")
	public WebElement lnkLogout;

	@FindBy(xpath = "//div[contains(@class,'error alert alert-danger')]")
	public WebElement alert;

	public void setUsername(String str) {
		Browser.waitForElementToBeClickable(txtUsername);
		txtUsername.sendKeys(str);
	}

	public void handleCookie() {
		try {
			Browser.waitForElementToBeClickable(okcookie);
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
		Browser.waitForElementToBeClickable(lnkRegister);
		lnkRegister.click();
	}

}
