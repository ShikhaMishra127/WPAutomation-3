package pageobjects.solicitationPageObjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pageobjects.utils.PCDriver;

public class ViewArchivedPage {

	
	@FindBy(xpath="//a[contains(text(),'Retracted')]")
	public WebElement lnkRetracted;
	
	public ViewArchivedPage() {

		PageFactory.initElements(PCDriver.getDriver(), this);
	}
	
	public void clickRetractedOrCancelleSol() {
		PCDriver.waitForElementToBeClickable(lnkRetracted);
		lnkRetracted.click();
	}
	
	
	
}