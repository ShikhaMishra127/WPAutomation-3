package pageobjects.common;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.common.Browser;

public class BuyerNavBarPOM  {

    private final Browser browser;

	public BuyerNavBarPOM(WebDriver browser) {
	    this.browser = (Browser) browser;
        PageFactory.initElements(((Browser) browser).driver, this);
	}
	
	// PAGE OBJECTS
	@FindBy(id="userMenu")
	public WebElement drpDownUserMenu;
	
	@FindBy(xpath="//a[text()='Logout']")
	public WebElement lnkLogout;

	@FindBy(xpath = "//ul[contains(@class,'navbar-left')]")
	public WebElement topNav;

	// HELPFUL METHODS
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

	public void selectDropDownItem(String headerItem, String subItem) {

	    String headerXPath = "//ul[contains(@class,'navbar-left')]//*[@title='" + headerItem + "']";
	    String subItemXPath = headerXPath + "/following-sibling::*//*[@title='" + subItem + "']";

	    browser.waitForElementToAppear(topNav);

	    // click on the nav bar header item
		browser.clickWhenAvailable(By.xpath(headerXPath));

	    // click on the sub-item below the header item
		browser.clickWhenAvailable(By.xpath(subItemXPath));

	    // Wait for the page to load before leaving
        browser.waitForPageLoad();
	}
	
}
