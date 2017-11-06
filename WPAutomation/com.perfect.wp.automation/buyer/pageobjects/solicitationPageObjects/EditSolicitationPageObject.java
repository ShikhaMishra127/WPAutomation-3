package pageobjects.solicitationPageObjects;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pageobjects.generic.HomePage;
import pageobjects.utils.PCDriver;

public class EditSolicitationPageObject {

	HomePage home = new HomePage();

	public EditSolicitationPageObject() {
		PageFactory.initElements(PCDriver.getDriver(), this);

	}

	@FindBy(id = "filter_bidTitle")
	public WebElement txtTitle;

	@FindBy(xpath = "//button[contains(text(),'Filter')]")
	public WebElement btnFilter;

	@FindBy(xpath = "//img[@class='dropdown-toggle dd-action']")
	public WebElement drpDownThreeDots;

	@FindBy(xpath = "//a/i[text()='edit']")
	public WebElement lnkEdit;

	@FindBy(xpath = "//ul[contains(@class,'pagination pull-right')]")
	public WebElement topNavEdit;

	@FindBy(xpath = "//button[text()='Save']")
	public WebElement btnSave;

	@FindBy(xpath = "//button[text()='Return']")
	public WebElement btnReturn;

	public void setTitleForSearch(String strTitle) throws IOException {
		PCDriver.waitForElementToBeClickable(txtTitle);
		txtTitle.sendKeys(strTitle);
	}

	public void clickOnFilter() {
		btnFilter.click();
	}

	public void clickOnThreeDots() {
		PCDriver.waitForElementToBeClickable(drpDownThreeDots);
		drpDownThreeDots.click();
	}

	public void clickEdit() {
		try {
			PCDriver.waitForElementToBeClickable(lnkEdit);
			lnkEdit.click();
			PCDriver.acceptAlert();
		} catch (Exception e) {

		}
	}

	public void clickTopNavItem(String strTabName) {
		PCDriver.waitForElementToBeClickable(topNavEdit);
		topNavEdit.findElement(By.xpath(".//li/a[contains(text(),'" + strTabName + "')]")).click();
	}

	public void clickSave() {
		btnSave.click();
	}

	public void clickReturn() {
		btnReturn.click();
	}
}