package buyer.pageobjects.solicitationPageObjects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commonutils.pageobjects.utils.PCDriver;
import commonutils.pageobjects.utils.log4jClass;

public class ViewArchivedPage {

	log4jClass logger=new log4jClass();
	@FindBy(xpath = "//a[contains(text(),'Retracted')]")
	public WebElement lnkRetracted;
	
	@FindBy(xpath="//img[@title='More Actions']")
	public List<WebElement> drpDownThreeDots;

	public ViewArchivedPage() {

		PageFactory.initElements(PCDriver.getDriver(), this);
	}

	public void clickRetractedOrCancelleSol() {
		logger.info("Entered Retracted Or Cancelled Sol Method");
		PCDriver.waitForElementToBeClickable(lnkRetracted);
		lnkRetracted.click();
	

	}
	
	public void clickThreeDots(int i) {
		PCDriver.visibilityOfListLocated(drpDownThreeDots);
		drpDownThreeDots.get(i).click();
	}

}