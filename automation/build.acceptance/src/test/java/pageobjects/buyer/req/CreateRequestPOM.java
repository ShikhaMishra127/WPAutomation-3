package pageobjects.buyer.req;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import utilities.common.Browser;


public class CreateRequestPOM {

	private final Browser browser;

	public CreateRequestPOM(Browser browser) throws IOException {
		this.browser = browser;
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
