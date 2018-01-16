package pageobjects.contract;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pageobjects.utils.PCDriver;

public class createContractPageObject {

	public createContractPageObject() {
		PageFactory.initElements(PCDriver.getDriver(), this);
	}

	@FindBy(id = "contractType")
	public WebElement drpDownContractType;

	@FindBy(id = "title")
	public WebElement txtTitle;

	@FindBy(name = "roundtrip")
	public WebElement chkBoxRoundTrip;

	@FindBy(xpath = "//button[text()='Commodities']")
	public WebElement btnCommodities;

	@FindBy(xpath = "//button[text()='Add Contacts']")
	public WebElement btnAddContacts;

	@FindBy(id = "vendorContactType1")
	public WebElement txtVendorContactType;

	@FindBy(id = "vendorContactFnLn1")
	public WebElement txtContactName;

	@FindBy(id = "vendorContactEmail1")
	public WebElement txtContactEmail;

	@FindBy(id = "vendorContactPhone1")
	public WebElement txtContactPhone;

	@FindBy(xpath = "//button[text()='Search Contractors']")
	public WebElement btnSearchContractors;

	@FindBy(id = "sname_action")
	public WebElement txtSupplierSearch;

	@FindBy(xpath = "//img[contains(@title,'Check Mark Selected')]")
	public WebElement chkMarkSupplier;

	@FindBy(name = "contractPricingType")
	public WebElement drpDownPricingType;

	@FindBy(name = "tempcontractTotalValueCondition")
	public WebElement drpDownTotalValue;

	@FindBy(id = "maxValue")
	public WebElement txtValue;

	@FindBy(id = "award_date")
	public WebElement dateAwardDate;

	@FindBy(xpath = "//div[contains(@class,'datepicker-dropdown')][contains(@style,'block')]")
	public WebElement effectiveDatePicker;

	@FindBy(id = "effective_date")
	public WebElement dateEffectiveDate;

	@FindBy(id = "expiry_date")
	public WebElement dateExpiryDate;

	@FindBy(xpath = "//button[text()='Next']")
	public WebElement btnNext;

	@FindBy(xpath = "//button[text()='Add Field']")
	public WebElement btnAddField;

	@FindBy(xpath = "//div[@class='fieldTitleDiv']")
	public List<WebElement> fieldDiv;

	@FindBy(xpath = "//button[text()='Exit']")
	public WebElement btnExit;

	@FindBy(xpath = "//h3")
	public List<WebElement> lblPageName;

	@FindBy(id = "multicheck")
	public WebElement chkBoxMasterContract;

	@FindBy(xpath = "//input[@name='vendors']")
	public List<WebElement> lstCheckBoxContractors;

	@FindBy(xpath = "//button[contains(text(),'Select')]")
	public WebElement btnSelect;

	public void selectContractType(String str) {
		PCDriver.selectFromDropDownByVisibleText(drpDownContractType, str);
	}

	public void AddContact() {
		PCDriver.waitForElementToBeClickable(btnAddContacts);
		btnAddContacts.click();
		txtVendorContactType.sendKeys("abc");
		txtContactName.sendKeys("abc");
		txtContactEmail.sendKeys("abc@abc.com");
		txtContactPhone.sendKeys("20154350433");
	}

	public void setContractTitle(String str) {
		PCDriver.waitForElementToBeClickable(txtTitle);
		txtTitle.sendKeys(str);
	}

	public void selectRoundTripCheckBox() {
		PCDriver.waitForElementToBeClickable(chkBoxRoundTrip);
		chkBoxRoundTrip.click();
	}

	public void selectMasterContractCheckBox() {
		PCDriver.waitForElementToBeClickable(chkBoxMasterContract);
		chkBoxMasterContract.click();
	}

	public void clickOnCommodities() {
		PCDriver.waitForElementToBeClickable(btnCommodities);
		btnCommodities.click();
	}

	public void clickSearchContractors() {
		PCDriver.waitForElementToBeClickable(btnSearchContractors);
		btnSearchContractors.click();
		PCDriver.switchToWindow("supplier");
		PCDriver.waitForPageLoad();
	}

	public void searchSupplierAndSelect(String str) {
		PCDriver.waitForElementToBeClickable(txtSupplierSearch);
		txtSupplierSearch.sendKeys(str);
	}

	public void clickOnSupplierCheckMark() {
		PCDriver.waitForElementToBeClickable(chkMarkSupplier);
		chkMarkSupplier.click();
		PCDriver.switchToWindow("");
	}

	public void clickOnContractorCheckBox() {
		PCDriver.visibilityOfListLocated(lstCheckBoxContractors);
		PCDriver.waitForElementToBeClickable(btnSelect);
		for (int i = 0; i <= 2; i++) {
			lstCheckBoxContractors.get(i).click();
		}
		btnSelect.click();
		PCDriver.switchToWindow("");
	}

	public void selectPricingType(String strPricingType) {
		PCDriver.selectFromDropDownByVisibleText(drpDownPricingType, strPricingType);

	}

	public void selectTotalValue(String strTotalValue) {
		PCDriver.selectFromDropDownByVisibleText(drpDownTotalValue, strTotalValue);
	}

	public void setUSDValue(String strValue) {
		PCDriver.waitForElementToBeClickable(txtValue);
		txtValue.clear();
		txtValue.sendKeys(strValue);
	}

	public void setAwardDate(String strAwardDate) {
		PCDriver.waitForElementToBeClickable(dateAwardDate);
		dateAwardDate.sendKeys(strAwardDate);

	}

	public void setEffectiveDate(String strEffectiveDate) {
		PCDriver.waitForElementToBeClickable(dateEffectiveDate);
		dateEffectiveDate.click();
		PCDriver.waitForElementToBeClickable(effectiveDatePicker);
		effectiveDatePicker.findElement(By.xpath(".//td[contains(@class,'day')][text()='" + strEffectiveDate + "']"))
				.click();

	}

	public void setExpiryDate(String strAwardDate) {
		PCDriver.waitForElementToBeClickable(dateExpiryDate);
		dateExpiryDate.sendKeys(strAwardDate);

	}

	public void clickNext() {
		PCDriver.waitForPageLoad();
		try {
			PCDriver.visibilityOfListLocated(fieldDiv);
		} catch (Exception e) {

		} finally {
			PCDriver.waitForElementToBeClickable(btnNext);
			btnNext.click();
		}
	}

	public void clickExit() {
		PCDriver.waitForPageLoad();
		PCDriver.waitForElementToBeClickable(btnExit);
		btnExit.click();
	}

	public boolean verifyCurrentContractsPage() {
		PCDriver.waitForPageLoad();
		if (lblPageName.size() > 0) {
			return true;
		} else {
			return false;
		}
	}
}
