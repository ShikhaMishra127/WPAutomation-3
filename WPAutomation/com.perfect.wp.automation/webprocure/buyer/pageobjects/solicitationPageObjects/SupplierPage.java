package buyer.pageobjects.solicitationPageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import commonutils.pageobjects.utils.PCDriver;

public class SupplierPage {
	@FindBy(xpath = "//button[text()='Create New Supplier']")
	public WebElement btnCreateSupplier;

	@FindBy(xpath = "//input[@name='sname']")
	public WebElement txtSName;

	@FindBy(xpath = "//button[text()='Submit']")
	public WebElement btnSubmit;
	// puw_CreateNewVendor
	@FindBy(id = "vendorname")
	public WebElement txtSupplierName;

	@FindBy(id = "enterprise")
	public WebElement drpDownEnterprise;

	@FindBy(id = "dba")
	public WebElement txtDoingBusiness;

	@FindBy(id = "med_legal_sp")
	public WebElement chkBoxMedicalOrLegal;

	@FindBy(id = "state_incorp")
	public WebElement drpDownState;

	@FindBy(id = "fname")
	public WebElement txtFName;

	@FindBy(id = "lname")
	public WebElement txtLName;

	@FindBy(name = "phone_areacode")
	public WebElement txtPhoneAreaCode;

	@FindBy(name = "phone_exchange")
	public WebElement txtPhoneExchange;

	@FindBy(name = "phone_number")
	public WebElement txtPhoneNumber;

	@FindBy(id = "email")
	public WebElement txtEmail;

	@FindBy(id = "email_confirm")
	public WebElement txtConfirmEmail;

	@FindBy(name = "fax_areacode")
	public WebElement txtFaxAreaCode;

	@FindBy(name = "fax_exchange")
	public WebElement txtFaxExchange;

	@FindBy(name = "fax_number")
	public WebElement txtFaxNumber;

	@FindBy(id = "country")
	public WebElement drpDownCountry;

	@FindBy(id = "addr1")
	public WebElement txtAddress1;

	@FindBy(id = "city")
	public WebElement txtCity;

	@FindBy(id = "cntry")
	public WebElement txtCounty;

	@FindBy(id = "select_state")
	public WebElement drpDownStateSupplier;

	@FindBy(id = "otherregion")
	public WebElement txtOtherRegion;

	@FindBy(id = "zip")
	public WebElement txtZipCode;

	@FindBy(name = "fein1")
	public WebElement txtFein1;

	@FindBy(name = "fein2")
	public WebElement txtFein2;

	@FindBy(name = "ssn1")
	public WebElement txtSsn1;

	@FindBy(name = "ssn2")
	public WebElement txtSsn2;

	@FindBy(name = "ssn3")
	public WebElement txtSsn3;

	@FindBy(id = "duns")
	public WebElement txtDuns;

	@FindBy(id = "extSupId")
	public WebElement txtExtSupplierId;

	@FindBy(id = "tofind")
	public WebElement txtSearchLineItem;

	@FindBy(xpath = "//a[text()='Selected Suppliers']")
	public WebElement lnkSelectedSupplier;

	@FindBy(xpath = "//button[text()='Close']")
	public WebElement btnClose;

	@FindBy(xpath = "//td/input")
	public List<WebElement> lstSearchResults;

	/******************************** New Search **********************************/

	@FindBy(xpath = "//button[text()='New Search']")
	public WebElement btnNewSearch;

	@FindBy(id = "sreg")
	public WebElement drpDownSupplierRegion;

	@FindBy(id = "shasselcat")
	public WebElement drpDownCommodityCategory;

	@FindBy(id = "sbuyercreated")
	public WebElement drpDownSupplierbuyerCreated;

	@FindBy(id = "stermsaccepted")
	public WebElement drpDownBuyerTAC;

	@FindBy(xpath = "//button[text()='Search']")
	public WebElement btnSearch;

	@FindBy(xpath = "//a[@title='Advance page']")
	public List<WebElement> btnNext;

	@FindBy(xpath = "//button[contains(text(),'Save')]|//button[text()='Select']")
	public WebElement btnSave;

	@FindBy(xpath = "//div[text()='Supplier Search Results']")
	public WebElement elementSuplierSearchResult;

	@FindBy(xpath = "//td/input")
	public WebElement chkBoxCheck;

	public SupplierPage() {

		PageFactory.initElements(PCDriver.getDriver(), this);
	}

	public void AcceptSupplierAlert() {
		try {
			new WebDriverWait(PCDriver.getDriver(), 30).ignoring(NoAlertPresentException.class)
					.until(ExpectedConditions.alertIsPresent());
			PCDriver.getDriver().switchTo().alert().accept();
		} catch (Exception e) {
			System.out.println("No Supplier Alert Present");
		}

	}

	public void clickNewSupplier() {
		PCDriver.waitForElementToBeClickable(btnCreateSupplier);
		btnCreateSupplier.click();

	}

	public void CreateNewSupplier(String strSupplierName) {
		PCDriver.waitForPageLoad();

		clickNewSupplier();

		PCDriver.switchToWindow("puw_CreateNewVendor");
		PCDriver.waitForPageLoad();

		txtSName.sendKeys(strSupplierName);
		btnSubmit.click();
		selectSearchedSuppliers();

	}

	public void searchSupplier(String strSupplierName) {
		lnkSelectedSupplier.click();
		PCDriver.waitForElementToBeClickable(btnNewSearch);
		btnNewSearch.click();
		PCDriver.waitForElementToBeClickable(txtSName);
		txtSName.sendKeys(strSupplierName);
		System.out.println("Hi Anshul this side");
		btnSearch.click();

	}

	public void selectSearchedSuppliers() {

		PCDriver.waitForPageLoad();
		try {
			PCDriver.waitForElementToBeClickable(elementSuplierSearchResult);
		} catch (Exception e) {
			System.out.println("Supplier Search Results text is not present");
		}
		while (btnNext.size() != 0) {
			PCDriver.waitForPageLoad();
			for (int i = 0; i < lstSearchResults.size(); i++) {
				PCDriver.waitForElementToDisappear(By.xpath("//td/input[@checked='']"));
				// PCDriver.waitForElementToBeClickable(chkBoxCheck);
				PCDriver.visibilityOfListLocated(lstSearchResults);
				lstSearchResults.get(i).click();
			}
			try {
				btnSave.click();
				PCDriver.acceptAlert();
			} catch (Exception e) {

			} finally {
				PCDriver.waitForElementToBeClickable(btnNext.get(0));
				btnNext.get(0).click();
			}
		}
	}

	public void setSupplierName(String strSupplierName) {
		PCDriver.waitForElementToBeClickable(txtSupplierName);
		txtSupplierName.clear();
		txtSupplierName.sendKeys(strSupplierName);
	}

	public void setEnterpriceType(String strEnterPriceType) {
		PCDriver.waitForElementToBeClickable(drpDownEnterprise);
		// drpDownEnterprise.click();
		// drpDownEnterprise.findElement(By.xpath(".//option[contains(text(),'" +
		// strEnterPriceType + "')]")).click();
		new Select(drpDownEnterprise).selectByIndex(2);
	}

	public void setDoingBusinessAs(String strDoingBusinessAs) {
		txtDoingBusiness.sendKeys(strDoingBusinessAs);
	}

	public void setMedicalOrLegalServiceProvider() {
		chkBoxMedicalOrLegal.click();
	}

	public void setStateIncorporated(String strStateInc) {
		drpDownState.click();
		drpDownState.findElement(By.xpath(".//option[contains(text(),'" + strStateInc + "')]")).click();
	}

	public void setFirstName(String strFirstName) {
		txtFName.sendKeys(strFirstName);

	}

	public void setLasttName(String strLastName) {
		txtLName.sendKeys(strLastName);

	}

	public void setCompanyPhoneNumber(String strCompanyNumber) {
		txtPhoneAreaCode.sendKeys(strCompanyNumber);
		// txtPhoneExchange.sendKeys(strCompanyNumber.substring(4, 7));
		// txtPhoneNumber.sendKeys(strCompanyNumber.substring(7,
		// strCompanyNumber.length()));

	}

	public void setEmailAddress(String strCompanyEmail) {
		txtEmail.sendKeys(strCompanyEmail);
		PCDriver.waitForElementToBeClickable(txtFaxAreaCode);
		txtFaxAreaCode.click();

	}

	public void setConfirmEmailAddress(String strCompanyConfirmEmail) {
		PCDriver.waitForElementToBeClickable(txtConfirmEmail);
		txtConfirmEmail.sendKeys(strCompanyConfirmEmail);

	}

	public void setCompanyFaxNumber(String strCompanyFaxNumber) {
		txtFaxAreaCode.sendKeys(strCompanyFaxNumber);
		// txtFaxExchange.sendKeys(strCompanyFaxNumber.substring(4, 7));
		// txtFaxNumber.sendKeys(strCompanyFaxNumber.substring(7,
		// strCompanyFaxNumber.length()));
	}

	public void setCountry(String strCountry) {
		// drpDownCountry.click();
		// drpDownCountry.findElement(By.xpath(".//option[contains(text(),'" +
		// strCountry + "')]")).click();
		new Select(drpDownCountry).selectByVisibleText("United States");
	}

	public void setAddress(String strAddress) {
		txtAddress1.sendKeys(strAddress);

	}

	public void setCity(String strCity) {
		txtCity.sendKeys(strCity);

	}

	public void setCounty(String strCounty) {
		txtCounty.sendKeys(strCounty);

	}

	public void setState(String strState) {
		// drpDownStateSupplier.click();
		// drpDownStateSupplier.findElement(By.xpath(".//option[contains(text(),'" +
		// strState + "')]"));
		new Select(drpDownStateSupplier).selectByVisibleText("Alaska");
	}

	public void setOtherRegion(String strRegion) {
		txtOtherRegion.sendKeys(strRegion);

	}

	public void setZipCode(String strZipCode) {
		txtZipCode.sendKeys(strZipCode);

	}

	public void setFeinNumber(String strFein) {
		txtFein1.sendKeys(strFein.substring(0, 2));
		txtFein2.sendKeys(strFein.substring(2, strFein.length()));

	}

	public void setSsnNumber(String strSsn) {
		txtSsn1.sendKeys(strSsn.substring(0, 3));
		txtSsn2.sendKeys(strSsn.substring(3, 5));
		txtSsn3.sendKeys(strSsn.substring(5, strSsn.length()));

	}

	public void setDunsNumber(String strDuns) {
		txtDuns.sendKeys(strDuns);
	}

	public void setExternalSupplierId(String strExtSupplierId) {
		txtExtSupplierId.sendKeys(strExtSupplierId);
	}

	public void clickSave() {
		btnSave.click();
		PCDriver.getDriver().switchTo().alert().accept();
	}

	public void clickClose() {
		PCDriver.waitForElementToBeClickable(btnClose);
		btnClose.click();
		PCDriver.switchToWindow("");
		try {
			Thread.sleep(4000);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
