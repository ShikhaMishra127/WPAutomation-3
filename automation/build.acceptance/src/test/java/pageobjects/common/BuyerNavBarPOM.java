package pageobjects.common;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.common.Browser;

public class BuyerNavBarPOM extends Browser {

	public BuyerNavBarPOM() throws IOException {
		super();
		PageFactory.initElements(Browser.getDriver(), this);
	}
	
	// PAGE OBJECTS
	
	@FindBy(xpath = "//a[@id='orgMenu']")
	public WebElement borgdropdown;

	@FindBy(xpath = "//*[contains(@href,'/switchorg.do?oid=114428')]")
	public WebElement selectedsuborg;
	
	@FindBy(id="userMenu")
	public WebElement drpDownUserMenu;
	
	@FindBy(xpath="//a[text()='Logout']")
	public WebElement lnkLogout;

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
	
	@FindBy(xpath = "//button[@id = 'saveCookieSettings']")
	public WebElement cookiessavesetting;
	
	@FindBy(xpath = "(//button[contains(text(),'�')])[1]")
	public WebElement enhancementsalert;
	
	@FindBy(xpath = "//a[@title='Request']")
	public WebElement requestdropdown;
	
	@FindBy(xpath = "//li[contains (@class,'paginate_button')]")
	public WebElement typeofreqlist;

	@FindBy(name = "C1ReqMain")
	public WebElement reqiframe;
			
	// HELPFUL METHODS
	public void clickGotIt() {
		Browser.waitForElementToBeClickable(btnGotIt);
		btnGotIt.click();
	}

	public void movetoSubOrg() {
		Browser.waitForElementToBeClickable(borgdropdown);
		borgdropdown.click();
		selectedsuborg.click();
	}

	public void clickInformalSolicitationEdit() {
		selectTopNavDropDown("Solicitation");
//		nav.informalSolicationsMenu("View Current");
	}

	public void clickFormalSolicitationEdit() {
		selectTopNavDropDown("Solicitation");
//		nav.formalSolicationsMenu("View Current");
	}

	public void clickIgnoreOnPopUp() {
		Browser.waitForPageLoad();
		try {
					Browser.waitForElementToBeClickable(sessionModal);
			Browser.waitForElementToBeClickable(btnIgnoreOnPopUp);
			btnIgnoreOnPopUp.click();
		} catch (Exception e) {

		}
		try {
			clickGotIt();
		} catch (Exception e) {

		}
	}
	
	public void cookiesalert(){
		Browser.waitForPageLoad();
		try{
			Browser.waitForPageLoad();
			Browser.waitForElementToBeClickable(cookiessavesetting);
			cookiessavesetting.click();
		}catch(Exception e){
			
		}
	}

	public void selectTopNavDropDown(String navName) {
		Browser.waitForElementToBeClickable(topNav);
		topNav.findElement(By.xpath("//a[contains(text(),'" + navName + "')]")).click();
	}
	
	public void logout() {
		Browser.waitForPageLoad();
		Browser.waitForElementToBeClickable(drpDownUserMenu);
		drpDownUserMenu.click();
		Browser.waitForElementToBeClickable(lnkLogout);
		 ((JavascriptExecutor)Browser.getDriver()).executeScript("window.confirm = function(msg){return true;}");
		lnkLogout.click();
		Browser.waitForPageLoad();
	}
	
	// Request DropDown Menu
	public void requestdropdown(String reqoptions) {
		// Browser.waitForElementToBeClickable(requestdropdown);

		requestdropdown.findElement(By.xpath("//li[@class='dropdown open']//a[contains(text(),'" + reqoptions + "')]"))
				.click();

		try {
			Browser.getDriver().switchTo().alert().accept();

		} catch (Exception e) {
			System.out.println("No Alert Present");

		} finally {
			Browser.waitForPageLoad();
			Browser.switchToFrame(reqiframe);
		}
	}

	// Select type of request which user want to create
	public void typesofreqlist(String requesttype) {

		typeofreqlist.findElement(By.xpath(".//following-sibling::li//a[contains(text(),'" + requesttype + "')]"))
				.click();
		try {
			Browser.getDriver().switchTo().alert().accept();

		} catch (Exception e) {
			System.out.println("No Alert Present");

		} finally {
			Browser.waitForElementToDisappear(By.id("loadingDiv"));
			Browser.waitForPageLoad();

			try {
				Thread.sleep(8000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			Browser.getDriver().switchTo().defaultContent();
			Browser.waitForElementToDisappear(By.id("loadingDiv"));
		}
	}
}
