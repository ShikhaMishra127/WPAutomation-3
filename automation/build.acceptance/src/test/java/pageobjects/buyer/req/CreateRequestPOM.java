package pageobjects.buyer.req;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import org.openqa.selenium.support.PageFactory;
import utilities.common.Browser;


public class CreateRequestPOM {

	private final Browser browser;

	/**
	 * Constructor called by PageFactory.instantiatePage
	 * @param browser WebDriver (as required by PageFactory) will be cast back to Browser.
	 */
	public CreateRequestPOM(WebDriver browser) throws IOException {
		this.browser = (Browser)browser;
		PageFactory.initElements(((Browser) browser).driver, this);
	}

	@FindBy(xpath = "//[@id='idCatalog']")
	public WebElement catalogreq;

	@FindBy(xpath = "//[@id='idRoundTrip']")
	public WebElement roundtripreq;

	@FindBy(xpath = "//[@id='idFavorites']")
	public WebElement favouritereq;

	@FindBy(xpath = "//[@id='idOff-Catalog Request']")
	public WebElement offcatalogreq;

	@FindBy(xpath = "//[id='idTemplates']")
	public WebElement templates;

	@FindBy(xpath = "//[id='idView Request']")
	public WebElement viewrequest;

	public void clickoffcatlogtab() {
		browser.waitForElementToBeClickable(offcatalogreq);
		offcatalogreq.click();
	}
}
