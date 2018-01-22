package commonutils.pageobjects.generic;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commonutils.pageobjects.utils.PCDriver;

public class LoginPage extends PCDriver {

	public LoginPage() {
		PageFactory.initElements(PCDriver.getDriver(), this);
	}

	@FindBy(xpath = "//input[contains(@placeholder,'Username')]")
	// @FindBy(id="visibleUname")
	public WebElement txtUsername;

	@FindBy(xpath = "//input[@placeholder='Password']")
	public WebElement txtPassword;

	@FindBy(id = "login-submit")
	public WebElement btnLogin;

	@FindBy(xpath = "//a[text()='Register Here']")
	public WebElement lnkRegister;

	@FindBy(xpath = "//a[@id='userMenu']")
	public WebElement logoutMenu;

	@FindBy(xpath = "//a[text()='Logout']")
	public WebElement lnkLogout;

	public void setUsername(String str) {
		PCDriver.waitForElementToBeClickable(txtUsername);
		txtUsername.sendKeys(str);
	}

	public void setPassword(String str) {
		txtPassword.sendKeys(str);
	}

	public void clickOnLogin() {
		btnLogin.click();

	}

	public void clickOnRegisterLink() {
		PCDriver.waitForElementToBeClickable(lnkRegister);
		lnkRegister.click();
	}

	public void logout() {
		logoutMenu.click();
		lnkLogout.click();
		PCDriver.getDriver().switchTo().alert().accept();
	}

}
