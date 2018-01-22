package buyer.pageobjects.contract;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commonutils.pageobjects.utils.PCDriver;

public class ContractAuthorization {

	public ContractAuthorization() {
		PageFactory.initElements(PCDriver.getDriver(), this);
	}

	@FindBy(id = "authorize_head_org")
	public WebElement chkBoxAuthorize;

	@FindBy(xpath = "//button[text()='Finished']")
	public WebElement btnFinished;

	public void clickAuthorizeCheckBox() {
		PCDriver.waitForElementToBeClickable(chkBoxAuthorize);
		chkBoxAuthorize.click();
	}

	public void clickFinished() {
		PCDriver.waitForElementToBeClickable(btnFinished);
		btnFinished.click();
	}

}
