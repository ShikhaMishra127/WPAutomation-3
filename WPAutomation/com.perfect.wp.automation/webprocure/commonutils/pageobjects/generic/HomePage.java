package commonutils.pageobjects.generic;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commonutils.pageobjects.utils.PCDriver;

public class HomePage {

	@FindBy(xpath = "//a[@id='orgMenu']")
	public WebElement borgdropdown;

	@FindBy(xpath = "//*[contains(@href,'/switchorg.do?oid=114428')]")
	public WebElement selectedsuborg;
	
	@FindBy(id="userMenu")
	public WebElement drpDownUserMenu;
	
	@FindBy(xpath="//a[text()='Logout']")
	public WebElement lnkLogout;

	solicitationNavigation nav = new solicitationNavigation();

	public HomePage() {
		PageFactory.initElements(PCDriver.getDriver(), this);
	}

	@FindBy(xpath = "(//div[@class='modal-content'])[2]")
	public WebElement sessionModal;

	@FindBy(xpath = "//button[text()='Ignore']")
	public WebElement btnIgnoreOnPopUp;

	@FindBy(xpath = "//ul[contains(@class,'navbar-left')]")
	public WebElement topNav;

	@FindBy(xpath = "//li[contains(text(),'Informal Solicitations')]/following-sibling::li")
	public WebElement informalSolMenu;

	@FindBy(xpath = "//button[contains(text(),'Got It')]")
	public WebElement btnGotIt;

	public void clickGotIt() {
		PCDriver.waitForElementToBeClickable(btnGotIt);
		btnGotIt.click();
	}

	public void movetoSubOrg() {
		PCDriver.waitForElementToBeClickable(borgdropdown);
		borgdropdown.click();
		selectedsuborg.click();
	}

	public void clickInformalSolicitationEdit() {
		selectTopNavDropDown("Solicitation");
		nav.informalSolicationsMenu("View Current");
	}

	public void clickFormalSolicitationEdit() {
		selectTopNavDropDown("Solicitation");
		nav.formalSolicationsMenu("View Current");
	}

	public void clickIgnoreOnPopUp() {
		PCDriver.waitForPageLoad();
		try {
					PCDriver.waitForElementToBeClickable(sessionModal);
			PCDriver.waitForElementToBeClickable(btnIgnoreOnPopUp);
			btnIgnoreOnPopUp.click();
		} catch (Exception e) {

		}
		try {
			clickGotIt();
		} catch (Exception e) {

		}
	}

	public void selectTopNavDropDown(String navName) {
		PCDriver.waitForElementToBeClickable(topNav);
		topNav.findElement(By.xpath("//a[contains(text(),'" + navName + "')]")).click();
	}
	
	public void logout() {
		PCDriver.waitForPageLoad();
		PCDriver.waitForElementToBeClickable(drpDownUserMenu);
		drpDownUserMenu.click();
		PCDriver.waitForElementToBeClickable(lnkLogout);
		 ((JavascriptExecutor)PCDriver.getDriver()).executeScript("window.confirm = function(msg){return true;}");
		lnkLogout.click();
		PCDriver.waitForPageLoad();
	}
}
