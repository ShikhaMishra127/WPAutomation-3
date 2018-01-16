package pageobjects.contract;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import pageobjects.utils.PCDriver;

public class CatalogItems {

	public CatalogItems() {
		PageFactory.initElements(PCDriver.getDriver(), this);
	}

	@FindBy(xpath = "//button[contains(text(),'Add New Catalog')]")
	public WebElement btnAddNewCatalog;

	@FindBy(id = "catFile")
	public WebElement uploadCatFile;

	@FindBy(id = "prcFile")
	public WebElement uploadPrcFile;

	@FindBy(xpath = "//iframe[@id='formWin']")
	public WebElement catalogFrame;

	@FindBy(xpath = "//button[contains(text(),'Next')]")
	public WebElement btnNext;

	@FindBy(xpath = "//button[contains(text(),'Browse Catalog')]")
	public WebElement btnBrowseCatalog;

	@FindBy(xpath = "//button[contains(text(),'Add All Items')]")
	public WebElement btnAddAllItems;

	@FindBy(id = "pricecatalog")
	public WebElement drpDownPricCatalog;

	public void clickAddNewCatalog() {
		PCDriver.waitForElementToBeClickable(btnAddNewCatalog);
		btnAddNewCatalog.click();
	}

	public void uploadCatFile(String str) {
		try {
			Thread.sleep(7000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PCDriver.switchToFrame(catalogFrame);
		// PCDriver.waitForElementToBeClickable(uploadCatFile);
		// uploadCatFile.click();
		uploadCatFile.sendKeys(str);
	}

	public void uploadPrcFile(String str) {
		uploadPrcFile.sendKeys(str);
	}

	public void clickNext() {
		PCDriver.waitForElementToBeClickable(btnNext);
		btnNext.click();
		PCDriver.acceptAlert();
	}

	public void clickAddAllItems() {
		PCDriver.waitForElementToBeClickable(btnAddAllItems);
		btnAddAllItems.click();
	}

	public void clickBrowseCatalog() {
		PCDriver.waitForElementToBeClickable(btnBrowseCatalog);
		btnBrowseCatalog.click();

	}

	public void selectPriceCatalogValue() {
		PCDriver.waitForElementToBeClickable(drpDownPricCatalog);
		new Select(drpDownPricCatalog).selectByIndex(1);
	}
}
