package buyer.pageobjects.solicitationPageObjects;

import java.awt.AWTException;
import java.io.File;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commonutils.pageobjects.utils.PCDriver;

public class SolicitationImportPage {

	@FindBy(id = "btn-upload-file")
	public WebElement uploadFile;

	@FindBy(xpath = "//input[@class='btn btn-wp']")
	public WebElement btnUpload;
	
	@FindBy(xpath="//a[contains(@href,'SolTemplateDownload')]")
	public WebElement btnDownloadTemplate;

	public SolicitationImportPage() {

		PageFactory.initElements(PCDriver.getDriver(), this);
	}

	public void uploadFile(String strFile) throws AWTException {
		PCDriver.waitForElementToBeClickable(btnUpload);
		btnUpload.click();
		PCDriver.uploadFile(strFile);
		uploadFile.click();
	}
	
	public void clickDownloadTemplateButton() {
		PCDriver.waitForElementToBeClickable(btnDownloadTemplate,Long.valueOf("10"));
		btnDownloadTemplate.click();
		
	}
	
	public boolean isFileDownloaded(String downloadPath, String fileName) {
		boolean flag = false;
	    File dir = new File(downloadPath);
	    File[] dir_contents = dir.listFiles();
	  	    
	    for (int i = 0; i < dir_contents.length; i++) {
	        if (dir_contents[i].getName().equals(fileName))
	            return flag=true;
	            }

	    return flag;
	}

}
