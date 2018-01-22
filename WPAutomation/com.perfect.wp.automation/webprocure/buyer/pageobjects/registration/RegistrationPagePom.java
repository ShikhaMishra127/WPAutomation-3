package buyer.pageobjects.registration;

import java.io.IOException;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import commonutils.pageobjects.utils.PCDriver;
import commonutils.pageobjects.utils.ReadExcelData;

public class RegistrationPagePom {

	public RegistrationPagePom() {
		PageFactory.initElements(PCDriver.getDriver(), this);
	}

	String sheetName = "Registration";
	/*************************
	 * Object Repo First Page
	 ************************************/

	@FindBy(xpath = "(//img[contains(@title,'Begin')])[1]")
	public WebElement btnBegin;

	/**************************
	 * Object Repo Second Page
	 *********************************/
	@FindBy(xpath = "(//img[@title='Accept'])[1]")
	public WebElement btnAccept;

	@FindBy(xpath = "(//img[@title='Decline'])[1]")
	public WebElement btnDecline;

	/***************************** First Page *************************/

	public void clickBegin() {
		btnBegin.click();

	}

	/**************************************
	 * Second Page
	 *************************************/

	public void clickAccept() {
		btnAccept.click();
	}

	public void clickDecline() {
		btnDecline.click();
	}

	/**************************************
	 * Common Elements
	 *************************************/

	@FindBy(xpath = "(//img[@title='Continue'])[1]")
	public WebElement btnContinue;

	@FindBy(xpath = "(//img[@title='Go Previous'])[1]")
	public WebElement btnPrevious;

	/**************************************
	 * Verification Elements
	 *************************************/

	@FindBy(xpath = "//button[contains(text(),'Got It')]")
	public WebElement btnGotItOnPopup;

	@FindBy(xpath = "//h3[text()='Documents']")
	public WebElement headVerify;

	@FindBy(xpath = "//table[@class='errorTable']//span[contains(text(),'SSN')]")
	public WebElement duplicateSsnCheck;

	public void clickContinue() {
		btnContinue.click();
	}

	public void clickPrevious() {
		btnPrevious.click();
	}

	public void clickGotIt() {
		PCDriver.waitForElementToBeClickable(btnGotItOnPopup);
		btnGotItOnPopup.click();
	}

	public String VerifyRegistration() {
		clickGotIt();
		return headVerify.getText();
	}

	public boolean duplicateSsnCheck() {
		if (duplicateSsnCheck.isDisplayed()) {
			return true;
		} else {
			return false;
		}
	}

	public void setOrgInfo() throws IOException {
		try {
			OrganizationInfo OrgInfo = new OrganizationInfo();
			clickBegin();
			clickAccept();
			OrgInfo.setCompanyName(ReadExcelData.getInstance(sheetName).getStringValue("CompanyName"));
			OrgInfo.setFein(ReadExcelData.getInstance(sheetName).getStringValue("FEIN"));
			OrgInfo.setSSN(ReadExcelData.getInstance(sheetName).getStringValue("SSN"));
			OrgInfo.selectEnterpriseType(ReadExcelData.getInstance(sheetName).getStringValue("EnterpriseType"));
			OrgInfo.setOfficeType(ReadExcelData.getInstance(sheetName).getStringValue("OfficeType"));
			OrgInfo.setTimeZone(ReadExcelData.getInstance(sheetName).getStringValue("TimeZone"));
			OrgInfo.setContactPhone(ReadExcelData.getInstance(sheetName).getStringValue("CompanyPhoneNumber"));
			OrgInfo.setFaxNumber(ReadExcelData.getInstance(sheetName).getStringValue("CompanyFaxNumber"));
			OrgInfo.setEmailAdress(ReadExcelData.getInstance(sheetName).getStringValue("EmailAddress"));
			OrgInfo.setConfirmEmailAdress(ReadExcelData.getInstance(sheetName).getStringValue("EmailAddress"));
			OrgInfo.setAddress(ReadExcelData.getInstance(sheetName).getStringValue("Address1"));
			OrgInfo.setCity(ReadExcelData.getInstance(sheetName).getStringValue("City"));
			OrgInfo.setState(ReadExcelData.getInstance(sheetName).getStringValue("State"));
			OrgInfo.setPostalCode(ReadExcelData.getInstance(sheetName).getStringValue("PostalCode"));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setOrgInfoWithFeinOnly() throws IOException {
		try {
			OrganizationInfo OrgInfo = new OrganizationInfo();
			clickBegin();
			clickAccept();
			OrgInfo.setCompanyName(ReadExcelData.getInstance(sheetName).getStringValue("CompanyName"));
			OrgInfo.setFein(ReadExcelData.getInstance(sheetName).getStringValue("FEINOnly"));
			OrgInfo.selectEnterpriseType(ReadExcelData.getInstance(sheetName).getStringValue("EnterpriseType"));
			OrgInfo.setOfficeType(ReadExcelData.getInstance(sheetName).getStringValue("OfficeType"));
			OrgInfo.setTimeZone(ReadExcelData.getInstance(sheetName).getStringValue("TimeZone"));
			OrgInfo.setContactPhone(ReadExcelData.getInstance(sheetName).getStringValue("CompanyPhoneNumber"));
			OrgInfo.setFaxNumber(ReadExcelData.getInstance(sheetName).getStringValue("CompanyFaxNumber"));
			OrgInfo.setEmailAdress(ReadExcelData.getInstance(sheetName).getStringValue("EmailAddress"));
			OrgInfo.setConfirmEmailAdress(ReadExcelData.getInstance(sheetName).getStringValue("EmailAddress"));
			OrgInfo.setAddress(ReadExcelData.getInstance(sheetName).getStringValue("Address1"));
			OrgInfo.setCity(ReadExcelData.getInstance(sheetName).getStringValue("City"));
			OrgInfo.setState(ReadExcelData.getInstance(sheetName).getStringValue("State"));
			OrgInfo.setPostalCode(ReadExcelData.getInstance(sheetName).getStringValue("PostalCode"));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setOrgInfoWithSsnOnly() throws IOException {
		try {
			OrganizationInfo OrgInfo = new OrganizationInfo();
			clickBegin();
			clickAccept();
			OrgInfo.setCompanyName(ReadExcelData.getInstance(sheetName).getStringValue("CompanyName"));
			OrgInfo.setSSN(ReadExcelData.getInstance(sheetName).getStringValue("SSNOnly"));
			OrgInfo.selectEnterpriseType(ReadExcelData.getInstance(sheetName).getStringValue("EnterpriseType"));
			OrgInfo.setOfficeType(ReadExcelData.getInstance(sheetName).getStringValue("OfficeType"));
			OrgInfo.setTimeZone(ReadExcelData.getInstance(sheetName).getStringValue("TimeZone"));
			OrgInfo.setContactPhone(ReadExcelData.getInstance(sheetName).getStringValue("CompanyPhoneNumber"));
			OrgInfo.setFaxNumber(ReadExcelData.getInstance(sheetName).getStringValue("CompanyFaxNumber"));
			OrgInfo.setEmailAdress(ReadExcelData.getInstance(sheetName).getStringValue("EmailAddress"));
			OrgInfo.setConfirmEmailAdress(ReadExcelData.getInstance(sheetName).getStringValue("EmailAddress"));
			OrgInfo.setAddress(ReadExcelData.getInstance(sheetName).getStringValue("Address1"));
			OrgInfo.setCity(ReadExcelData.getInstance(sheetName).getStringValue("City"));
			OrgInfo.setState(ReadExcelData.getInstance(sheetName).getStringValue("State"));
			OrgInfo.setPostalCode(ReadExcelData.getInstance(sheetName).getStringValue("PostalCode"));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setContactInfo() {

		ContactInfo contact = new ContactInfo();
		try {
			contact.setFirstName(ReadExcelData.getInstance(sheetName).getStringValue("FirstName"));
			contact.setLastName(ReadExcelData.getInstance(sheetName).getStringValue("LastName"));
			contact.checkBoxMainContact();
			contact.checkBoxPoContact();
			contact.checkBoxPoAdress();
			contact.checkBoxSolContact();
			contact.checkBoxSolAdress();
			contact.checkBoxRemitContact();
			contact.checkBoxRemitAdress();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void setBuyerOrganization() {
		BuyingOrganization buyer = new BuyingOrganization();
		try {
			buyer.selectBuyingOrganization(ReadExcelData.getInstance(sheetName).getStringValue("BuyerOrg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setBuyerTermsAndConditions() {
		BuyerTermsAndConditions buyerTerms = new BuyerTermsAndConditions();
		try {
			buyerTerms.selectTermsAndConditions(ReadExcelData.getInstance(sheetName).getStringValue("SelectTerms"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setUsernameAndPassword() {
		SelectUserNameAndPassword select = new SelectUserNameAndPassword();
		try {
			Random r = new Random();
			char c = (char) (r.nextInt(26) + 'a');

			select.setUsername(ReadExcelData.getInstance(sheetName).getStringValue("Username") + c);
			select.setPassword(ReadExcelData.getInstance(sheetName).getStringValue("Password"));
			select.setConfirmPassword(ReadExcelData.getInstance(sheetName).getStringValue("Password"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private class OrganizationInfo {

		public OrganizationInfo() {
			PageFactory.initElements(PCDriver.getDriver(), this);

		}

		/**************************
		 * Object Repo Third Page
		 *********************************/

		@FindBy(xpath = "//input[@name='orgInfoCmd.name']")
		public WebElement txtOrgName;

		@FindBy(xpath = "//input[@name='orgInfoCmd.doingBusinessAs']")
		public WebElement txtDoingBussAs;

		@FindBy(xpath = "//input[@name='orgInfoCmd.homeUrl']")
		public WebElement txtCompWebsite;

		@FindBy(xpath = "//input[@name='orgInfoCmd.duns']")
		public WebElement txtDunesNumber;

		@FindBy(xpath = "//input[@name='orgInfoCmd.fedtaxid1']")
		public WebElement txtFeinId;

		@FindBy(xpath = "//input[@name='orgInfoCmd.ssn1']")
		public WebElement txtSsn;

		@FindBy(xpath = "//select[@name='orgInfoCmd.enterpriseTypeId']")
		public WebElement drpDownEnterpriseType;

		@FindBy(xpath = "//td[@valign='top']")
		public WebElement radioOfficeType;

		@FindBy(xpath = "//select[@name='orgInfoCmd.defaultTz']")
		public WebElement drpDownTimeZone;

		@FindBy(xpath = "//td[@valign='top']")
		public WebElement txtContactPhone;

		@FindBy(xpath = "//td[@valign='top']")
		public WebElement txtFaxNumber;

		@FindBy(name = "orgInfoCmd.email")
		public WebElement txtEmailAdress;

		@FindBy(name = "orgInfoCmd.confirmEmail")
		public WebElement txtConfirmEmailAdress;

		@FindBy(name = "orgInfoCmd.addr1")
		public WebElement txtAdress;

		@FindBy(name = "orgInfoCmd.city")
		public WebElement txtCity;

		@FindBy(name = "orgInfoCmd.state")
		public WebElement drpDownState;

		@FindBy(xpath = "//td[@valign='top']")
		public WebElement txtPostalCode;

		/**************************************
		 * Third Page
		 *************************************/

		public void setCompanyName(String strCompName) {
			txtOrgName.sendKeys(strCompName);
		}

		public void setDoingBusinessAs(String strDbaName) {
			txtDoingBussAs.sendKeys(strDbaName);
		}

		public void setCompanyWebsite(String strCompWebsite) {
			txtCompWebsite.sendKeys(strCompWebsite);

		}

		public void setDunsNumber(String strDunesNumber) {
			PCDriver.waitForElementToBeClickable(txtDunesNumber);
			txtDunesNumber.sendKeys(strDunesNumber);
		}

		public void setFein(String strFein) {
			PCDriver.waitForElementToBeClickable(txtFeinId);
			txtFeinId.sendKeys(strFein);
		}

		public void setSSN(String strSsn) {
			PCDriver.waitForElementToBeClickable(txtSsn);
			txtSsn.sendKeys(strSsn);
		}

		public void selectEnterpriseType(String value) {
			PCDriver.waitForElementToBeClickable(drpDownEnterpriseType);
			drpDownEnterpriseType.click();
			drpDownEnterpriseType.findElement(By.xpath("//option[text()='" + value + "']")).click();

		}

		public void setOfficeType(String strOfficeType) {
			PCDriver.waitForElementToBeClickable(radioOfficeType);

			radioOfficeType
					.findElement(By.xpath("//text()[contains(.,'" + strOfficeType + "')]/preceding-sibling::input[1]"))
					.click();

			if (strOfficeType.equals("Branch")) {
				try {
					setDunsNumber(ReadExcelData.getInstance(sheetName).getStringValue("DunsNumber"));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		public void setTimeZone(String strTimeZone) {
			PCDriver.waitForElementToBeClickable(drpDownTimeZone);

			new Select(drpDownTimeZone).selectByVisibleText(strTimeZone);
		}

		public void setContactPhone(String strNumber) {
			PCDriver.waitForElementToBeClickable(txtContactPhone);

			for (int i = 1; i < 5; i++) {

				if (i == 1) {
					txtContactPhone.findElement(By.xpath("//input[@name='orgInfoCmd.phone" + i + "']"))
							.sendKeys(strNumber.substring(0, 4));
				} else if (i == 2) {
					txtContactPhone.findElement(By.xpath("//input[@name='orgInfoCmd.phone" + i + "']"))
							.sendKeys(strNumber.substring(4, 7));
				} else if (i == 3) {
					txtContactPhone.findElement(By.xpath("//input[@name='orgInfoCmd.phone" + i + "']"))
							.sendKeys(strNumber.substring(7, 11));

				} else if (i == 4 && strNumber.length() > 11) {
					txtContactPhone.findElement(By.xpath("//input[@name='orgInfoCmd.phone" + i + "']"))
							.sendKeys(strNumber.substring(11, strNumber.length()));

				}
			}
		}

		public void setFaxNumber(String strNumber) {
			PCDriver.waitForElementToBeClickable(txtFaxNumber);

			for (int i = 1; i < 4; i++) {

				if (i == 1) {
					txtFaxNumber.findElement(By.xpath("//input[@name='orgInfoCmd.fax" + i + "']"))
							.sendKeys(strNumber.substring(0, 5));
				} else if (i == 2) {
					txtFaxNumber.findElement(By.xpath("//input[@name='orgInfoCmd.fax" + i + "']"))
							.sendKeys(strNumber.substring(5, 8));
				} else if (i == 3) {
					txtFaxNumber.findElement(By.xpath("//input[@name='orgInfoCmd.fax" + i + "']"))
							.sendKeys(strNumber.substring(8, strNumber.length()));

				}
			}
		}

		public void setEmailAdress(String strEmail) {
			PCDriver.waitForElementToBeClickable(txtEmailAdress);

			txtEmailAdress.sendKeys(strEmail + Keys.TAB);
		}

		public void setConfirmEmailAdress(String strConfirmEmail) {
			PCDriver.waitForElementToBeClickable(txtConfirmEmailAdress);
			txtConfirmEmailAdress.sendKeys(strConfirmEmail);
		}

		public void setAddress(String strAdress) {
			PCDriver.waitForElementToBeClickable(txtAdress);

			txtAdress.sendKeys(strAdress);
		}

		public void setCity(String strCity) {
			PCDriver.waitForElementToBeClickable(txtCity);

			txtCity.sendKeys(strCity);
		}

		public void setState(String strState) {
			PCDriver.waitForElementToBeClickable(drpDownState);

			new Select(drpDownState).selectByVisibleText(strState);

		}

		public void setPostalCode(String strPostalCode) {
			PCDriver.waitForElementToBeClickable(txtPostalCode);

			for (int i = 1; i < 3; i++) {

				if (i == 1) {
					txtPostalCode.findElement(By.xpath("//input[@name='orgInfoCmd.zip" + i + "']"))
							.sendKeys(strPostalCode.substring(1, 6));
				} else if (i == 2 && strPostalCode.length() > 6) {
					txtFaxNumber.findElement(By.xpath("//input[@name='orgInfoCmd.fax" + i + "']"))
							.sendKeys(strPostalCode.substring(7, strPostalCode.length()));
				}
			}
		}

	}

	private class ContactInfo {

		@FindBy(name = "mainContactInfoCmd.fname")
		public WebElement txtFirstName;

		@FindBy(name = "mainContactInfoCmd.lname")
		public WebElement txtLastName;

		@FindBy(name = "mainContactInfoSameAsOrgInfo")
		public WebElement chkBoxMainContact;

		@FindBy(name = "poContactSameAsLocalContact")
		public WebElement chkBoxPoContact;

		@FindBy(name = "poAddressSameAsLocalAddress")
		public WebElement chkBoxPoAdress;

		@FindBy(name = "solContactSameAsLocalContact")
		public WebElement chkBoxSolContact;

		@FindBy(name = "solAddressSameAsLocalAddress")
		public WebElement chkBoxSolAdress;

		@FindBy(name = "remitContactSameAsLocalContact")
		public WebElement chkBoxRemitContact;

		@FindBy(name = "remitAddressSameAsLocalAddress")
		public WebElement chkBoxRemitAdress;

		public ContactInfo() {
			PageFactory.initElements(PCDriver.getDriver(), this);

		}

		public void setFirstName(String strFirstName) {
			txtFirstName.sendKeys(strFirstName);
		}

		public void setLastName(String strLastName) {
			txtLastName.sendKeys(strLastName);
		}

		public void checkBoxMainContact() {
			chkBoxMainContact.click();
		}

		public void checkBoxPoContact() {
			chkBoxPoContact.click();
		}

		public void checkBoxPoAdress() {
			chkBoxPoAdress.click();
		}

		public void checkBoxSolContact() {
			chkBoxSolContact.click();
		}

		public void checkBoxSolAdress() {
			chkBoxSolAdress.click();
		}

		public void checkBoxRemitContact() {
			chkBoxRemitContact.click();
		}

		public void checkBoxRemitAdress() {
			chkBoxRemitAdress.click();
		}

	}

	private class BuyingOrganization {

		public BuyingOrganization() {
			PageFactory.initElements(PCDriver.getDriver(), this);
		}

		@FindBy(xpath = "//tbody")
		WebElement chkBoxBuyingOrg;

		public void selectBuyingOrganization(String strOrgState) {
			chkBoxBuyingOrg.findElement(By.xpath(
					"//following-sibling::td[contains(text(),'" + strOrgState + "')]/preceding-sibling::td/input"))
					.click();
		}
	}

	private class BuyerTermsAndConditions {

		@FindBy(xpath = "//td")
		public WebElement radioBoxTermsConditons;

		public BuyerTermsAndConditions() {
			PageFactory.initElements(PCDriver.getDriver(), this);

		}

		public void selectTermsAndConditions(String strType) {
			radioBoxTermsConditons
					.findElement(By.xpath("//text()[contains(.,'" + strType + "')]/preceding-sibling::input[1]"))
					.click();
		}
	}

	private class SelectUserNameAndPassword {

		@FindBy(name = "userName")
		private WebElement txtUsername;

		@FindBy(name = "password")
		private WebElement txtPassword;

		@FindBy(name = "confirmPassword")
		private WebElement txtConfirmPassword;

		public SelectUserNameAndPassword() {
			PageFactory.initElements(PCDriver.getDriver(), this);

		}

		private void setUsername(String strUsername) {
			txtUsername.sendKeys(strUsername);
		}

		private void setPassword(String strPassword) {
			txtPassword.sendKeys(strPassword);
		}

		private void setConfirmPassword(String strConfirmPassword) {
			txtConfirmPassword.sendKeys(strConfirmPassword);
		}
	}

}