package buyer.pageobjects.requestPageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commonutils.pageobjects.utils.PCDriver;

public class CreateRequestPOM {

	public CreateRequestPOM() {
		PageFactory.initElements(PCDriver.getDriver(), this);
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
		PCDriver.waitForElementToBeClickable(offcatalogreq);
		offcatalogreq.click();
	}
}
