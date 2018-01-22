package buyer.pageobjects.contract;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commonutils.pageobjects.utils.PCDriver;

public class ContractSummary {

	public ContractSummary() {
		PageFactory.initElements(PCDriver.getDriver(), this);
	}

	@FindBy(xpath = "//button[text()='Submit']")
	public WebElement btnSubmit;

	@FindBy(xpath = "//button[text()='Close']")
	public WebElement btnClose;

	public void clickSubmitOnContractSummary() {
		PCDriver.waitForElementToBeClickable(btnSubmit);
		btnSubmit.click();
	}

	public void clickCloseButton() {
		PCDriver.waitForElementToBeClickable(btnClose);
		btnClose.click();
	}

}
