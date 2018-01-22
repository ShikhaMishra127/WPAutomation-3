package buyer.pageobjects.solicitationPageObjects;

import java.awt.AWTException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commonutils.pageobjects.utils.PCDriver;

public class SolicitationImportPage {

	@FindBy(id = "btn-upload-file")
	public WebElement uploadFile;

	@FindBy(xpath = "//input[@class='btn btn-wp']")
	public WebElement btnUpload;

	public SolicitationImportPage() {

		PageFactory.initElements(PCDriver.getDriver(), this);
	}

	public void uploadFile(String strFile) throws AWTException {
		PCDriver.waitForElementToBeClickable(btnUpload);
		btnUpload.click();
		PCDriver.uploadFile(strFile);
		uploadFile.click();
	}

}
