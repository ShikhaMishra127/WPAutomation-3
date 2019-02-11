package pageobjects.common;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import utilities.common.Browser;

public class BuyerNavBarPOM  {

    private final Browser browser;

    /**
     * Constructor called by PageFactory.instantiatePage
     * @param browser WebDriver (as required by PageFactory) will be cast back to Browser.
     */
	public BuyerNavBarPOM(WebDriver browser) {
	    this.browser = (Browser) browser;
        PageFactory.initElements(((Browser) browser).driver, this);
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

	@FindBy(xpath = "//*[@id='Analytics']")
	public WebElement analyticsdropdown;
	
	@FindBy(xpath = "//li[contains (@class,'paginate_button')]")
	public WebElement typeofreqlist;

	@FindBy(name = "C1ReqMain")
	public WebElement reqiframe;

	@FindBy(xpath = "//a[@title='Create Informal Solicitation']")
	public WebElement informalSolCreate;

			
	// HELPFUL METHODS
	public void clickGotIt() {
		browser.waitForElementToBeClickable(btnGotIt);
		btnGotIt.click();
	}

	public void movetoSubOrg() {
		browser.waitForElementToBeClickable(borgdropdown);
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
		browser.waitForPageLoad();
		try {
					browser.waitForElementToBeClickable(sessionModal);
			browser.waitForElementToBeClickable(btnIgnoreOnPopUp);
			btnIgnoreOnPopUp.click();
		} catch (Exception e) {

		}
		try {
			clickGotIt();
		} catch (Exception e) {

		}
	}
	
	public void cookiesalert(){
		browser.waitForPageLoad();
		try{
			browser.waitForPageLoad();
			browser.waitForElementToBeClickable(cookiessavesetting);
			cookiessavesetting.click();
		}catch(Exception e){
			
		}
	}

	public void selectTopNavDropDown(String navName) {

		browser.waitForElementToBeClickable(topNav);
		topNav.findElement(By.xpath("//a[contains(text(),'" + navName + "')]")).click();
	}
	
	public void logout() {
		browser.waitForPageLoad();
		browser.waitForElementToBeClickable(drpDownUserMenu);
		drpDownUserMenu.click();
		browser.waitForElementToBeClickable(lnkLogout);
		 ((JavascriptExecutor)browser.getDriver()).executeScript("window.confirm = function(msg){return true;}");
		lnkLogout.click();
		browser.waitForPageLoad();
	}
	
	// Request DropDown Menu
	public void requestdropdown(String reqoptions) {
		// browser.waitForElementToBeClickable(requestdropdown);

		requestdropdown.findElement(By.xpath("//li[@class='dropdown open']//a[contains(text(),'" + reqoptions + "')]"))
				.click();

		try {
			browser.getDriver().switchTo().alert().accept();

		} catch (Exception e) {
			System.out.println("No Alert Present");

		} finally {
			browser.waitForPageLoad();
			//browser.switchToFrame(reqiframe);
		}
	}

	/*
	  headerItem - Top-level nav bar menu item
	  subItem - Menu item below the top-level item
    */
	public void selectDropDownItem(String headerItem, String subItem) {

	    String headerXPath = "//ul[contains(@class,'navbar-left')]//*[@title='" + headerItem + "']";
	    String subItemXPath = headerXPath + "/following-sibling::*//*[@title='" + subItem + "']";

	    // click on the nav bar header item
	    WebElement header = topNav.findElement(By.xpath(headerXPath));
	    header.click();

	    // click on the sub-item below the header item
	    WebElement sub = header.findElement(By.xpath(subItemXPath));
	    sub.click();

	    // Wait for the page to load before leaving
        browser.waitForPageLoad();
	}

	// Select type of request which user want to create
	public void typesofreqlist(String requesttype) {

		browser.getDriver().switchTo().defaultContent();
		browser.switchToFrame(reqiframe);
		typeofreqlist.findElement(By.xpath(".//following-sibling::li//a[contains(text(),'" + requesttype + "')]"))
				.click();
		try {
			browser.getDriver().switchTo().alert().accept();

		} catch (Exception e) {
			System.out.println("No Alert Present");

		} finally {
			browser.waitForElementToDisappear(By.id("loadingDiv"));
			browser.waitForPageLoad();

			try {
				Thread.sleep(8000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			browser.getDriver().switchTo().defaultContent();
			browser.waitForElementToDisappear(By.id("loadingDiv"));
		}
	}

	public String getTitle() {
		return browser.getDriver().getTitle();
	}
}
