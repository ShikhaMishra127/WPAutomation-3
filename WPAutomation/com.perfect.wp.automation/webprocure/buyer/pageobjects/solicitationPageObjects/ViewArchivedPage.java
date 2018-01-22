package buyer.pageobjects.solicitationPageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commonutils.pageobjects.utils.PCDriver;

public class ViewArchivedPage {

	@FindBy(xpath = "//a[contains(text(),'Retracted')]")
	public WebElement lnkRetracted;

	public ViewArchivedPage() {

		PageFactory.initElements(PCDriver.getDriver(), this);
	}

	public void clickRetractedOrCancelleSol() {
		PCDriver.waitForElementToBeClickable(lnkRetracted);
		lnkRetracted.click();
	}

}