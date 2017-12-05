package pageobjects.solicitationPageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pageobjects.utils.PCDriver;

public class AttachmentPage {
	@FindBy(xpath = "//button[text()='Upload Documents from Library']")
	public WebElement btnUploadDocFromLibrary;

	@FindBy(xpath = "//button[text()='Add new document']")
	public WebElement btnAddNewDoc;

	@FindBy(xpath = "//input[@type='checkbox']")
	List<WebElement> lstCheckBox;

	@FindBy(xpath = "//button[text()='Save']")
	public WebElement btnSave;

	@FindBy(xpath = "//span[@class='btn btn-wp btn-file']")
	public List<WebElement> btnBrowse;

	@FindBy(xpath = "//button[text()='Upload']")
	public WebElement btnUpload;
	

	public AttachmentPage() {

		PageFactory.initElements(PCDriver.getDriver(), this);
	}

	public void clickUploadDocFromLibrary() {
		PCDriver.waitForElementToBeClickable(btnUploadDocFromLibrary);
		btnUploadDocFromLibrary.click();
		PCDriver.waitForPageLoad();

	}

	public void clickAddNewDoc() {
		PCDriver.waitForElementToBeClickable(btnAddNewDoc);
		btnAddNewDoc.click();
		PCDriver.waitForPageLoad();
	}

	public void uploadNewDocument() {
		PCDriver.switchToWindow("puw_upload");
		PCDriver.waitForPageLoad();

		for (int i = 2; i < 5; i++) {
			btnBrowse.get(i - 2).findElement(By.xpath(".//input[@name='file" + i + "']"))
					.sendKeys("D:\\WebProcureData.xlsx");
		}
		btnUpload.click();
		PCDriver.switchToWindow("");

	}

	public void uploadDocFromLibrary() {
		PCDriver.switchToWindow("puw_librdoc");
		PCDriver.waitForPageLoad();

		for (int i = 0; i < 6; i++) {
			lstCheckBox.get(i).click();
		}
		btnSave.click();
		PCDriver.switchToWindow("");

	}
}
