package pageobjects.registration;

import java.io.IOException;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pageobjects.generic.LoginPage;
import pageobjects.utils.PCDriver;
import pageobjects.utils.ReadConfig;
import pageobjects.utils.ReadExcelData;

public class WhiteLabelRegistrationPagePom {
	final static String sheetName = "Registration";
	LoginPage login = new LoginPage();

	public WhiteLabelRegistrationPagePom() {

		PCDriver.driver.navigate().to(ReadConfig.getInstance().getWhiteLabelUrl());
		PageFactory.initElements(PCDriver.getDriver(), this);

	}

	@FindBy(xpath = "//tbody")
	public WebElement chkBoxRegistration;

	@FindBy(xpath = "//div[contains(@style,'display')]//img[contains(@src,'gif')]")
	public WebElement imgLoad;

	@FindBy(xpath = "//iframe[@id='whitelabelregframe']")
	public WebElement termsNConditionsFrame;

	@FindBy(xpath = "//input[@id='termsncondition']")
	public WebElement chkBoxTermsAndConditions;

	@FindBy(xpath = "//button[@id='next']")
	public WebElement btnNext;

	/******************* Validation Check *******************/
	@FindBy(xpath = "//label[@class='error' and @for='ssn']")
	public WebElement txtDuplicateSsn;

	@FindBy(xpath = "//label[@class='error' and @for='fein']")
	public WebElement txtDuplicateFein;

	@FindBy(xpath = "//div[@id='confirmmsg']")
	public WebElement checkConfirmMessage;

	@FindBy(xpath = "//a[contains(text(),'Take me to WebProcure Now')]")
	public WebElement btnWebProcureButton;

	public void clickWebProcureButtonAndCheckLogin() throws IOException {
		btnWebProcureButton.click();
		PCDriver.switchToWindow("_new");
		login.setUsername(ReadExcelData.getInstance(sheetName).getStringValue("EmailAddress"));
		login.setPassword(ReadExcelData.getInstance(sheetName).getStringValue("Password"));
		login.clickOnLogin();

	}

	public boolean checkConfirmMessage() {
		PCDriver.waitForElementToBeClickable(checkConfirmMessage);
		if (checkConfirmMessage.isDisplayed()) {
			return true;
		} else {
			return false;
		}
	}

	public boolean checkSsnMessage() {

		if (txtDuplicateSsn.isDisplayed()) {
			return true;
		} else {
			return false;
		}
	}

	public boolean checkFeinMessage() {

		if (txtDuplicateFein.isDisplayed()) {
			return true;
		} else {
			return false;
		}
	}

	public void clickRegistrationCheckBox(String strStateName) {

		PCDriver.waitForElementToBeClickable(chkBoxRegistration);
		chkBoxRegistration.findElement(By.xpath("//button[contains(@onclick,'" + strStateName.toUpperCase()
				+ "')][contains(@onclick,'whitelabelreg')]")).click();
		try {
			Thread.sleep(8000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void AcceptTermsAndConditions() {
		PCDriver.switchToFrame(termsNConditionsFrame);
		PCDriver.waitForElementToBeClickable(chkBoxTermsAndConditions);
		PCDriver.executeScript(chkBoxTermsAndConditions);
		PCDriver.waitForElementToBeClickable(btnNext);
		PCDriver.executeScript(btnNext);

	}

	public void clickNext() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JavascriptExecutor executor = (JavascriptExecutor) PCDriver.getDriver();
		executor.executeScript("arguments[0].click();", btnNext);
	}

	public void setOrgInfoWithFeinForMissouri() throws IOException, InterruptedException {
		OrganizationInfo org = new OrganizationInfo();
		org.setFeinNumber();
		org.setConfirmFeinNumberForMissouri(ReadExcelData.getInstance(sheetName).getStringValue("FEIN"));
		org.setLegalName(ReadExcelData.getInstance(sheetName).getStringValue("CompanyName"));
		org.setAddress(ReadExcelData.getInstance(sheetName).getStringValue("Address1"));
		org.setCity(ReadExcelData.getInstance(sheetName).getStringValue("City"));
		org.setPostalCode(ReadExcelData.getInstance(sheetName).getStringValue("PostalCode"));
		org.selectBusinessType(ReadExcelData.getInstance(sheetName).getStringValue("EnterpriseType"));
	}

	public void setOrgInfoWithSsnForMissouri() throws IOException, InterruptedException {
		OrganizationInfo org = new OrganizationInfo();
		org.setSsnNumberForMissouri(ReadExcelData.getInstance(sheetName).getStringValue("SSN"));
		org.setConfirmSsnNumber(ReadExcelData.getInstance(sheetName).getStringValue("SSN"));
		org.setLegalName(ReadExcelData.getInstance(sheetName).getStringValue("CompanyName"));
		org.setAddress(ReadExcelData.getInstance(sheetName).getStringValue("Address1"));
		org.setCity(ReadExcelData.getInstance(sheetName).getStringValue("City"));
		org.setPostalCode(ReadExcelData.getInstance(sheetName).getStringValue("PostalCode"));
		org.selectBusinessType(ReadExcelData.getInstance(sheetName).getStringValue("EnterpriseType"));
	}

	public void setOrgContactInfo(String stateName) throws IOException {
		OrganizationContactInfo orgContact = new OrganizationContactInfo();
		orgContact.setFirstName(ReadExcelData.getInstance(sheetName).getStringValue("FirstName"));
		orgContact.setLastName(ReadExcelData.getInstance(sheetName).getStringValue("LastName"));
		Random r = new Random();
		char c = (char) (r.nextInt(26) + 'a');
		orgContact.setEmailAddress(ReadExcelData.getInstance(sheetName).getStringValue("EmailAddress") + c);
		if (stateName.equals("Missouri")) {
			orgContact.setUserName(ReadExcelData.getInstance(sheetName).getStringValue("Username"));
			orgContact.setFaxNumber(ReadExcelData.getInstance(sheetName).getStringValue("CompanyFaxNumber"));
			orgContact.setPhoneNumber(ReadExcelData.getInstance(sheetName).getStringValue("CompanyPhoneNumber"));
		} else {
			orgContact.setPhoneNumber("3278" + Keys.TAB + "3334441");
			orgContact.setFaxNumber("3531" + Keys.TAB + "5344512");

		}
		orgContact.setPassword(ReadExcelData.getInstance(sheetName).getStringValue("Password"));
		orgContact.setRetypePassword(ReadExcelData.getInstance(sheetName).getStringValue("Password"));
	}

	public void setCompanyInfoWithSSnForIdaho() throws Exception {
		OrganizationInfo org = new OrganizationInfo();
		org.setLegalName(ReadExcelData.getInstance(sheetName).getStringValue("CompanyName"));
		org.setAddress(ReadExcelData.getInstance(sheetName).getStringValue("Address1"));
		org.setCity(ReadExcelData.getInstance(sheetName).getStringValue("City"));
		org.selectState(ReadExcelData.getInstance(sheetName).getStringValue("State"));
		org.setPostalCode(ReadExcelData.getInstance(sheetName).getStringValue("PostalCode"));
		org.setSsnNumber();
		// org.setFeinNumber(ReadExcelData.getInstance(sheetName).getStringValue("FEIN"));
		org.selectBusinessType(ReadExcelData.getInstance(sheetName).getStringValue("EnterpriseType"));

	}

	public void setCompanyInfoWithFeinForIdaho() throws Exception {
		OrganizationInfo org = new OrganizationInfo();
		org.setLegalName(ReadExcelData.getInstance(sheetName).getStringValue("CompanyName"));
		org.setAddress(ReadExcelData.getInstance(sheetName).getStringValue("Address1"));
		org.setCity(ReadExcelData.getInstance(sheetName).getStringValue("City"));
		org.selectState(ReadExcelData.getInstance(sheetName).getStringValue("State"));
		org.setPostalCode(ReadExcelData.getInstance(sheetName).getStringValue("PostalCode"));
		org.setFeinNumber();
		// org.setFeinNumber(ReadExcelData.getInstance(sheetName).getStringValue("FEIN"));
		org.selectBusinessType(ReadExcelData.getInstance(sheetName).getStringValue("EnterpriseType"));

	}

	public void setCategory(String stateName) throws IOException {
		SelectCommodity select = new SelectCommodity();
		if (stateName.equals("Missouri")) {
			select.selectCategoryForMissouri(ReadExcelData.getInstance(sheetName).getStringValue("Category"));
		} else {
			select.selectCategoryForIdaho("Grinding");
		}
	}

	private class OrganizationInfo {

		public OrganizationInfo() {
			PageFactory.initElements(PCDriver.getDriver(), this);

		}

		@FindBy(id = "fein1")
		public WebElement txtFienNumber1;

		@FindBy(id = "fein2")
		public WebElement txtFienNumber2;

		@FindBy(id = "retypefein1")
		public WebElement txtConfirmFein1;

		@FindBy(id = "retypefein2")
		public WebElement txtConfirmFein2;

		@FindBy(id = "ssn1")
		public WebElement txtSsn;

		@FindBy(id = "retypessn1")
		public WebElement txtRetrySsn;

		@FindBy(id = "supname")
		public WebElement txtLegalName;

		@FindBy(id = "Address1")
		public WebElement txtAddress;

		@FindBy(xpath = "//select[@id='state']")
		public WebElement drpDownState;

		@FindBy(id = "city")
		public WebElement txtCity;

		@FindBy(id = "zipcode")
		public WebElement txtPostalCode;

		@FindBy(id = "suptype")
		public WebElement drpDownBusinessType;

		@FindBy(xpath = "//select[@id='suptype']")
		public WebElement selectBussinessType;

		@FindBy(xpath = "//a[@id='disableCompValidation']")
		public WebElement disableValidation;

		public void setFeinNumber() throws IOException {
			PCDriver.waitForElementToBeClickable(txtFienNumber1);
			long timeSeed = System.nanoTime(); // to get the current date time value

			double randSeed = Math.random() * 1000; // random number generation

			long midSeed = (long) (timeSeed * randSeed); // mixing up the time and
															// rand number.

			// variable timeSeed
			// will be unique

			// variable rand will
			// ensure no relation
			// between the numbers

			String s = midSeed + "";
			String subStr = s.substring(0, 9);

			System.out.println("Value is:" + subStr);
			ReadExcelData.getInstance(sheetName).updateCellValue("FEIN", subStr);
			txtFienNumber1.sendKeys(subStr.substring(0, 2));
			txtFienNumber2.sendKeys(subStr.substring(2, subStr.length()));

		}

	/*	public void setFeinNumberForMissouri(String str) throws IOException {
			PCDriver.waitForElementToBeClickable(txtFienNumber1);
			txtFienNumber1.sendKeys(str.substring(0, 2));
			txtFienNumber2.sendKeys(str.substring(2, str.length()));

		}*/

		public void setConfirmFeinNumber() {
			PCDriver.waitForElementToBeClickable(txtConfirmFein1);
			long timeSeed = System.nanoTime(); // to get the current date time value

			double randSeed = Math.random() * 1000; // random number generation

			long midSeed = (long) (timeSeed * randSeed); // mixing up the time and
															// rand number.

			// variable timeSeed
			// will be unique

			// variable rand will
			// ensure no relation
			// between the numbers

			String s = midSeed + "";
			String subStr = s.substring(0, 9);

			int finalSeed = Integer.parseInt(subStr); // integer value
			txtConfirmFein1.sendKeys(String.valueOf(finalSeed));
		}

		public void setSsnNumber() {
			PCDriver.waitForElementToBeClickable(txtSsn);
			long timeSeed = System.nanoTime(); // to get the current date time value

			double randSeed = Math.random() * 1000; // random number generation

			long midSeed = (long) (timeSeed * randSeed); // mixing up the time and
															// rand number.

			// variable timeSeed
			// will be unique

			// variable rand will
			// ensure no relation
			// between the numbers

			String s = midSeed + "";
			String subStr = s.substring(0, 9);

			int finalSeed = Integer.parseInt(subStr); // integer value
			txtSsn.sendKeys(String.valueOf(finalSeed));
		}

		public void setConfirmFeinNumberForMissouri(String str) {
			PCDriver.waitForElementToBeClickable(txtConfirmFein1);
			txtConfirmFein1.sendKeys(str.substring(0, 2));
			txtConfirmFein2.sendKeys(str.substring(2, str.length()));
		}

		public void setSsnNumberForMissouri(String strSsnNumber) {
			txtSsn.sendKeys(strSsnNumber);
		}

		public void setConfirmSsnNumber(String SsnNumber) {
			txtRetrySsn.sendKeys(SsnNumber);
		}

		public void setLegalName(String txtLegal) {
			PCDriver.waitForElementToBeClickable(txtLegalName);
			txtLegalName.sendKeys(txtLegal);
		}

		public void setAddress(String txtAdd) {
			txtAddress.sendKeys(txtAdd);
		}

		public void selectState(String strStateName) {
			drpDownState.click();
			drpDownState.findElement(By.xpath("//option[text()='" + strStateName + "']")).click();
		}

		public void setCity(String strCity) {
			txtCity.sendKeys(strCity);
		}

		public void setPostalCode(String strPostalCode) throws InterruptedException {
			txtPostalCode.sendKeys(strPostalCode);

		}

		public void selectBusinessType(String strBussinessName) throws InterruptedException {
			drpDownBusinessType.click();
			selectBussinessType.findElement(By.xpath("//option[contains(text(),'" + strBussinessName + "')]")).click();
			Thread.sleep(2000);
			try {
				PCDriver.executeScript(disableValidation);
			} catch (Exception e) {

			}
		}
	}

	private class OrganizationContactInfo {
		@FindBy(id = "fname")
		public WebElement txtFirstName;

		@FindBy(id = "lname")
		public WebElement txtLastName;

		@FindBy(id = "myphone")
		public WebElement txtPhoneNumber;

		@FindBy(id = "myfax")
		public WebElement txtFaxNumber;

		@FindBy(id = "username")
		public WebElement txtEmail;

		@FindBy(id = "username1")
		public WebElement txtUsername;

		@FindBy(id = "password")
		public WebElement txtPassword;

		@FindBy(id = "retypePassword")
		public WebElement txtRetypePassword;

		public OrganizationContactInfo() {
			PageFactory.initElements(PCDriver.getDriver(), this);
		}

		public void setFirstName(String strFirstName) {
			PCDriver.waitForElementToBeClickable(txtFirstName);
			txtFirstName.sendKeys(strFirstName);
		}

		public void setLastName(String strLastName) {
			txtLastName.sendKeys(strLastName);
		}

		public void setPhoneNumber(String strPhoneNumber) {
			txtPhoneNumber.sendKeys(strPhoneNumber);
		}

		public void setFaxNumber(String strFaxNumber) {
			txtFaxNumber.sendKeys(strFaxNumber);
		}

		public void setEmailAddress(String strEmail) {
			PCDriver.waitForElementToBeClickable(txtEmail);
			txtEmail.sendKeys(strEmail);
		}

		public void setUserName(String strUsername) {
			Random r = new Random();
			char c = (char) (r.nextInt(26) + 'a');

			txtUsername.sendKeys(strUsername + c);
		}

		public void setPassword(String strPassword) {
			txtPassword.sendKeys(strPassword);
		}

		public void setRetypePassword(String strRetypePassword) {
			txtRetypePassword.sendKeys(strRetypePassword);
		}

	}

	private class SelectCommodity {

		@FindBy(xpath = "//li")
		public WebElement chkBoxCategory;

		@FindBy(xpath = "//tbody//td[contains(text(),'Grinding')]/preceding-sibling::td/input[@id='6569']")
		public WebElement categoryIdaho;

		public SelectCommodity() {
			PageFactory.initElements(PCDriver.getDriver(), this);
		}

		public void selectCategoryForMissouri(String strCategoryName) {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			chkBoxCategory
					.findElement(
							By.xpath("(//span[contains(text(),'" + strCategoryName + "')]/preceding-sibling::span)[2]"))
					.click();
		}

		public void selectCategoryForIdaho(String strCategoryName) {
			PCDriver.waitForElementToBeClickable(categoryIdaho);
			categoryIdaho
					.findElement(By.xpath(
							"//td[contains(text(),'" + strCategoryName + "')]/preceding-sibling::td/input[@id='6569']"))
					.click();
		}
	}

}
