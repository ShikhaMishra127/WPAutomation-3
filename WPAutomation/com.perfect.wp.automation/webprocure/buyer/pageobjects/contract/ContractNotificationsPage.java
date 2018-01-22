package buyer.pageobjects.contract;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commonutils.pageobjects.utils.PCDriver;
import commonutils.pageobjects.utils.ReadExcelData;

public class ContractNotificationsPage {

	String tabName = "Contract";

	public ContractNotificationsPage() {
		PageFactory.initElements(PCDriver.getDriver(), this);
	}

	@FindBy(id = "amendmentAgencyEmail")
	public WebElement txtAgencyEmail;

	@FindBy(id = "amendmentEmail")
	public WebElement txtAmendmentEmail;

	@FindBy(name = "triggerValue1")
	public WebElement txtTotalValueTrigger;

	@FindBy(name = "triggerValue2")
	public WebElement txtEffectiveDateTrigger;

	@FindBy(name = "triggerValue3")
	public WebElement txtExpirationDateTrigger;

	@FindBy(name = "triggerValue5")
	public WebElement txtInsurAndCertiExpirationTrigger;

	@FindBy(name = "agencyEmail1")
	public WebElement txtAgencyEmail1;

	@FindBy(name = "agencyEmail2")
	public WebElement txtAgencyEmail2;

	@FindBy(name = "agencyEmail3")
	public WebElement txtAgencyEmail3;

	@FindBy(name = "agencyEmail5")
	public WebElement txtAgencyEmail5;

	@FindBy(xpath = "//button[text()='Add Notification from Library']")
	public WebElement btnAddNotification;

	@FindBy(xpath = "//input[@value='Next']")
	public WebElement btnNext;

	@FindBy(xpath = "//input[@value='Add to Contract']")
	public WebElement btnAddToContract;

	@FindBy(id = "contractorEmail_1")
	public WebElement txtContractorEmail;

	public void EnterNotificationDetails() throws IOException {
		setAgencyEmail(ReadExcelData.getInstance(tabName).getStringValue("EmailId"));
		setAmendmentEmail(ReadExcelData.getInstance(tabName).getStringValue("EmailId"));

		setTotalValueTrigger(ReadExcelData.getInstance(tabName).getStringValue("TotalValueTrigger"));
		setEffectiveDateTrigger(ReadExcelData.getInstance(tabName).getStringValue("EffectiveDateTrigger"));
		setExpirationDateTrigger(ReadExcelData.getInstance(tabName).getStringValue("ExpirationDateTrigger"));
		setAgencyEmailForTrigger(ReadExcelData.getInstance(tabName).getStringValue("EmailId"));
		setInsuranceAndCertificationExpirationTrigger(
				ReadExcelData.getInstance(tabName).getStringValue("InsuranceAndCertificationExpirationTrigger"));
		AddCustomNotification();
		try {
			setContractorEmail(ReadExcelData.getInstance(tabName).getStringValue("ContractorEmail"));
		} catch (Exception e) {
			System.out.println("Contractor Email Not Present");
		}
	}

	public void setAgencyEmail(String strEmail) {
		PCDriver.waitForPageLoad();
		PCDriver.waitForElementToBeClickable(txtAgencyEmail);
		txtAgencyEmail.clear();
		txtAgencyEmail.sendKeys(strEmail);
	}

	public void setAmendmentEmail(String strAmendEmail) {
		PCDriver.waitForElementToBeClickable(txtAmendmentEmail);
		txtAmendmentEmail.sendKeys(strAmendEmail);
	}

	public void setTotalValueTrigger(String strTotalValue) {
		PCDriver.waitForElementToBeClickable(txtTotalValueTrigger);
		txtTotalValueTrigger.sendKeys(strTotalValue);
	}

	public void setEffectiveDateTrigger(String strEffectiveDate) {
		PCDriver.waitForElementToBeClickable(txtEffectiveDateTrigger);
		txtEffectiveDateTrigger.sendKeys(strEffectiveDate);
	}

	public void setExpirationDateTrigger(String strExpirationDate) {
		PCDriver.waitForElementToBeClickable(txtExpirationDateTrigger);
		txtExpirationDateTrigger.sendKeys(strExpirationDate);
	}

	public void setAgencyEmailForTrigger(String strAgencyName) {
		PCDriver.waitForElementToBeClickable(txtAgencyEmail1);
		txtAgencyEmail1.clear();
		txtAgencyEmail1.sendKeys(strAgencyName);
		txtAgencyEmail2.clear();
		txtAgencyEmail2.sendKeys(strAgencyName);
		txtAgencyEmail3.clear();
		txtAgencyEmail3.sendKeys(strAgencyName);
		txtAgencyEmail5.clear();
		txtAgencyEmail5.sendKeys(strAgencyName);
	}

	public void setInsuranceAndCertificationExpirationTrigger(String strInsurAndCertiExpTrigger) {
		PCDriver.waitForElementToBeClickable(txtInsurAndCertiExpirationTrigger);
		txtInsurAndCertiExpirationTrigger.sendKeys(strInsurAndCertiExpTrigger);
	}

	public void AddCustomNotification() {
		PCDriver.waitForElementToBeClickable(btnAddNotification);
		btnAddNotification.click();
		PCDriver.waitForElementToBeClickable(btnNext);
		btnNext.click();
		PCDriver.waitForElementToBeClickable(btnAddToContract);
		btnAddToContract.click();
		PCDriver.waitForElementToDisappear(By.xpath("//input[@value='Add to Contract']"));

	}

	public void setContractorEmail(String strContractorEmail) {
		PCDriver.waitForElementToBeClickable(txtContractorEmail);
		txtContractorEmail.clear();
		txtContractorEmail.sendKeys(strContractorEmail);
	}
}