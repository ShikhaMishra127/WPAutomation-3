package pageobjects.buyer.common;

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
	
	@FindBy(xpath="//a[contains(@title,'Logout')]")
	public WebElement lnkLogout;

	@FindBy(xpath = "//ul[contains(@class,'navbar-left')]")
	public WebElement topNav;

	@FindBy(xpath="(//a[contains(@class,'navbar-brand')]/span)[1]")
	public WebElement homePageButton;


	// HELPFUL METHODS
	public void logout() {
		browser.waitForPageLoad();
		browser.clickWhenAvailable(drpDownUserMenu);

		 ((JavascriptExecutor)browser.getDriver()).executeScript("window.confirm = function(msg){return true;}");

		 browser.clickWhenAvailable(lnkLogout);

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
