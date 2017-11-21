package pageobjects.solicitationPageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pageobjects.utils.PCDriver;

public class SolicitationImportPage {

	
	@FindBy(id="fileUploader")
	public WebElement uploadFile;
	
	@FindBy(xpath="//button[@class='btn btn-wp']")
	public WebElement btnUpload;
	
	public SolicitationImportPage() {

		PageFactory.initElements(PCDriver.getDriver(), this);
	}
	
	public void uploadFile(String strFile) {
		PCDriver.waitForElementToBeClickable(uploadFile);
		uploadFile.click();
		uploadFile.sendKeys(strFile);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		btnUpload.click();
	}
	
}
