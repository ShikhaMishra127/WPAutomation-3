package buyer.pageobjects.registration;

import java.io.IOException;
import java.util.ResourceBundle;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.IFactoryAnnotation;
import org.testng.annotations.Test;

import buyer.testcases.registration.IdahoRegistrationTestCases;
import commonutils.pageobjects.utils.PCDriver;
import commonutils.pageobjects.utils.ReadConfig;
import commonutils.pageobjects.utils.ReadExcelData;
import commonutils.pageobjects.utils.ssnAndFeinGenerator;

public class IdahowhitelabelPageObjects {

	/********** Idaho Supplier Registration Page ************/

	@FindBy(xpath = "//input[@id='termsncondition']")
	public WebElement iagreechkbox;

	@FindBy(xpath = "//button[@id='next']/span")
	public WebElement nextpage;

	@FindBy(id = "confirmmsg")
	public WebElement msg;

	/********** Supplier Signup ************/

	@FindBy(id = "whitelabelregframe")
	public WebElement iframeWhite;

	@FindBy(name = "supname")
	public WebElement companyname;

	@FindBy(id = "dba")
	public WebElement Busname;

	@FindBy(id = "Address1")
	public WebElement adrs1;

	@FindBy(id = "Address2")
	public WebElement adrs2;

	@FindBy(id = "city")
	public WebElement city1;

	@FindBy(id = "state")
	public WebElement state;

	@FindBy(id = "zipcode")
	public WebElement zip;

	@FindBy(id = "country")
	public WebElement country;

	@FindBy(id = "hq1")
	public WebElement parentcmp;

	@FindBy(id = "hq2")
	public WebElement branch;

	@FindBy(id = "fein1")
	public WebElement fein1;

	@FindBy(id = "fein2")
	public WebElement fein2;

	@FindBy(id = "ssn1")
	public WebElement securityno1;

	@FindBy(id = "ssn2")
	public WebElement securityno2;

	@FindBy(id = "ssn3")
	public WebElement securityno3;

	@FindBy(id = "suptype")
	public WebElement Busstype;

	@FindBy(id = "duns")
	public WebElement dunsno;

	@FindBy(id = "ppf")
	public WebElement chk1;

	@FindBy(id = "fs")
	public WebElement chk2;

	@FindBy(id = "sdb")
	public WebElement chk3;

	@FindBy(id = "ebo")
	public WebElement chk4;

	@FindBy(id = "uc")
	public WebElement chk5;

	@FindBy(id = "vet")
	public WebElement chk6;

	@FindBy(id = "dv")
	public WebElement chk7;

	@FindBy(id = "vv")
	public WebElement chk8;

	@FindBy(id = "ms")
	public WebElement chk9;

	@FindBy(id = "divclassification")
	public WebElement drpDownClassification;

	@FindBy(id = "mbeacertifnum")
	public WebElement certno;

	@FindBy(id = "mbeacertifdate")
	public WebElement certdate;

	@FindBy(id = "emvendor1")
	public WebElement emergencyven1;

	@FindBy(name = "emvendor")
	public WebElement emergencyven2;

	@FindBy(id = "next")
	public WebElement nextpage1;

	/********** Supplier Personal Information ************/

	@FindBy(id = "fname")
	public WebElement firstname;

	@FindBy(id = "lname")
	public WebElement lastname;

	@FindBy(id = "username")
	public WebElement email;

	@FindBy(xpath = "//input[@id='password']")
	public WebElement pwd;

	@FindBy(id = "retypePassword")
	public WebElement retypepwd;

	@FindBy(id = "myphone")
	public WebElement phno1;

	@FindBy(id = "phareaCode")
	public WebElement phno2;

	@FindBy(id = "phoneNumber")
	public WebElement phno3;

	@FindBy(id = "next")
	public WebElement nextpage2;

	/********** Select Solicitation ************/

	@FindBy(id = "6566")
	public WebElement Sandblasting;

	@FindBy(id = "6568")
	public WebElement Tumbling;

	@FindBy(id = "6570")
	public WebElement Pumice;

	@FindBy(id = "next")
	public WebElement nextpage3;

	@FindBy(xpath = "//label[@id='ssnerror']")
	public WebElement ssnCheck;

	/************ Test case1 *****************/
	public void companydetails() {
		try {
			// Thread.sleep(5000);
			PCDriver.waitForElementToBeClickable(companyname);
			companyname.sendKeys(ReadExcelData.getInstance("Test1").getStringValue("Company Name"));
			PCDriver.waitForElementToBeClickable(Busname);
			Busname.sendKeys(ReadExcelData.getInstance("Test1").getStringValue("Doing Business"));
			PCDriver.waitForElementToBeClickable(adrs1);
			adrs1.sendKeys(ReadExcelData.getInstance("Test1").getStringValue("Company Address1"));
			PCDriver.waitForElementToBeClickable(adrs2);
			adrs2.sendKeys(ReadExcelData.getInstance("Test1").getStringValue("Company Address2"));
			PCDriver.waitForElementToBeClickable(city1);
			city1.sendKeys(ReadExcelData.getInstance("Test1").getStringValue("City"));

			PCDriver.waitForElementToBeClickable(state);
			PCDriver.selectFromDropDownByVisibleText(state,
					(ReadExcelData.getInstance("Test1").getStringValue("State")));
			PCDriver.waitForElementToBeClickable(zip);
			zip.sendKeys(ReadExcelData.getInstance("Test1").getStringValue("Zip Code"));

			PCDriver.selectFromDropDownByVisibleText(country,
					(ReadExcelData.getInstance("Test1").getStringValue("Country")));
			PCDriver.waitForElementToBeClickable(branch);
			branch.click();
			PCDriver.waitForElementToBeClickable(fein1);
			fein1.sendKeys(ReadExcelData.getInstance("Test1").getStringValue("Employer Id No1"));
			PCDriver.waitForElementToBeClickable(fein2);
			fein2.sendKeys(ReadExcelData.getInstance("Test1").getStringValue("Employer Id No2"));
			PCDriver.waitForElementToBeClickable(securityno1);
			securityno1.sendKeys(ReadExcelData.getInstance("Test1").getStringValue("Security No1"));
			PCDriver.waitForElementToBeClickable(securityno2);
			securityno2.sendKeys(ReadExcelData.getInstance("Test1").getStringValue("Security No2"));
			PCDriver.waitForElementToBeClickable(securityno3);
			securityno3.sendKeys(ReadExcelData.getInstance("Test1").getStringValue("Security No3"));
			PCDriver.waitForElementToBeClickable(Busstype);
			PCDriver.selectFromDropDownByVisibleText(Busstype,
					(ReadExcelData.getInstance("Test1").getStringValue("TypeofBuss")));
			PCDriver.waitForElementToBeClickable(dunsno);
			dunsno.sendKeys(ReadExcelData.getInstance("Test1").getStringValue("DUNS"));
			PCDriver.waitForElementToBeClickable(chk1);
			chk1.click();
			System.out.println("The Title is:" + PCDriver.getDriver().getTitle());
			Thread.sleep(10000);
			// try {
			// PCDriver.switchToDefaultContent();
			// PCDriver.switchToFrameBasedOnFrameName("whitelabelregframe");
			// } catch (Exception e) {
			// e.printStackTrace();
			// }
			PCDriver.waitForElementToBeClickable(nextpage);
			PCDriver.waitForElementToBeClickable(nextpage, Long.valueOf("10"));
			((JavascriptExecutor) PCDriver.getDriver()).executeScript("arguments[0].scrollIntoView(true);", nextpage);

			Thread.sleep(10000);
			nextpage.click();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void Suppliercontactdetail(boolean check) {
		try {
			PCDriver.waitForPageLoad();
			String expectedTitle = "Test Harness";
			String actualTitle = PCDriver.getDriver().getTitle();
			Assert.assertEquals(actualTitle, expectedTitle);
			Thread.sleep(5000);
			PCDriver.waitForElementToBeClickable(firstname);
			firstname.sendKeys(ReadExcelData.getInstance("Test1.1").getStringValue("FirstName"));
			lastname.sendKeys(ReadExcelData.getInstance("Test1.1").getStringValue("LastName"));
			if (check == true) {

				ReadExcelData.getInstance("Test2.1").updateCellValue("Email", "");
				ReadExcelData.getInstance("Test2.1").updateCellValue("Email",
						"starbucks" + System.currentTimeMillis() + "@gmail.com");
				email.sendKeys(ReadExcelData.getInstance("Test2.1").getStringValue("Email"));
			}
			pwd.sendKeys(ReadExcelData.getInstance("Test1.1").getStringValue("Password"));
			retypepwd.sendKeys(ReadExcelData.getInstance("Test1.1").getStringValue("RetypePassword"));
			phno1.sendKeys(ReadExcelData.getInstance("Test1.1").getStringValue("PhnNo1"));
			phno2.sendKeys(ReadExcelData.getInstance("Test1.1").getStringValue("PhnNo2"));
			phno3.sendKeys(ReadExcelData.getInstance("Test1.1").getStringValue("PhnNo3"));
			PCDriver.waitForElementToBeClickable(nextpage2, Long.valueOf("10"));
			nextpage2.click();

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public boolean verify() {
		PCDriver.waitForElementToBeClickable(msg);
		if (msg.getText().contains("Registration Confirmation")) {
			return true;
		} else {
			return false;
		}
	}

	/************ Test case2 *****************/

	public void companydetailstestscript2() {

		try {
			// Thread.sleep(5000);
			PCDriver.waitForElementToBeClickable(companyname);
			companyname.sendKeys(ReadExcelData.getInstance("Test2").getStringValue("Company Name"));
			PCDriver.waitForElementToBeClickable(Busname);
			Busname.sendKeys(ReadExcelData.getInstance("Test2").getStringValue("Doing Business"));
			PCDriver.waitForElementToBeClickable(adrs1);
			adrs1.sendKeys(ReadExcelData.getInstance("Test2").getStringValue("Company Address1"));
			PCDriver.waitForElementToBeClickable(adrs2);
			adrs2.sendKeys(ReadExcelData.getInstance("Test2").getStringValue("Company Address2"));
			PCDriver.waitForElementToBeClickable(city1);
			city1.sendKeys(ReadExcelData.getInstance("Test2").getStringValue("City"));

			PCDriver.waitForElementToBeClickable(state);
			PCDriver.selectFromDropDownByVisibleText(state,
					(ReadExcelData.getInstance("Test2").getStringValue("State")));
			PCDriver.waitForElementToBeClickable(zip);
			zip.sendKeys(ReadExcelData.getInstance("Test2").getStringValue("Zip Code"));

			PCDriver.selectFromDropDownByVisibleText(country,
					(ReadExcelData.getInstance("Test2").getStringValue("Country")));
			PCDriver.waitForElementToBeClickable(branch);
			branch.click();
			PCDriver.waitForElementToBeClickable(fein1);
			fein1.sendKeys(ReadExcelData.getInstance("Test2").getStringValue("Employer Id No1"));
			PCDriver.waitForElementToBeClickable(fein2);
			fein2.sendKeys(ReadExcelData.getInstance("Test2").getStringValue("Employer Id No2"));
			PCDriver.waitForElementToBeClickable(securityno1);
			securityno1.sendKeys(ReadExcelData.getInstance("Test2").getStringValue("Security No1"));
			PCDriver.waitForElementToBeClickable(securityno2);
			securityno2.sendKeys(ReadExcelData.getInstance("Test2").getStringValue("Security No2"));
			PCDriver.waitForElementToBeClickable(securityno3);
			securityno3.sendKeys(ReadExcelData.getInstance("Test2").getStringValue("Security No3"));
			PCDriver.waitForElementToBeClickable(Busstype);
			PCDriver.selectFromDropDownByVisibleText(Busstype,
					(ReadExcelData.getInstance("Test2").getStringValue("TypeofBuss")));
			PCDriver.waitForElementToBeClickable(dunsno);
			dunsno.sendKeys(ReadExcelData.getInstance("Test2").getStringValue("DUNS"));
			PCDriver.waitForElementToBeClickable(chk1);
			chk1.click();
			System.out.println("The Title is:" + PCDriver.getDriver().getTitle());
			Thread.sleep(10000);

			PCDriver.waitForElementToBeClickable(nextpage, Long.valueOf("10"));
			((JavascriptExecutor) PCDriver.getDriver()).executeScript("arguments[0].scrollIntoView(true);", nextpage);
			PCDriver.waitForElementToBeClickable(nextpage);
			Thread.sleep(15000);
			nextpage.click();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void SuppliercontactdetailTestscript2(boolean check) {
		try {
			PCDriver.waitForPageLoad();
			String expectedTitle = "Test Harness";
			String actualTitle = PCDriver.getDriver().getTitle();
			Assert.assertEquals(actualTitle, expectedTitle);
			Thread.sleep(5000);
			PCDriver.waitForElementToBeClickable(firstname);
			firstname.sendKeys(ReadExcelData.getInstance("Test2.1").getStringValue("FirstName"));
			lastname.sendKeys(ReadExcelData.getInstance("Test2.1").getStringValue("LastName"));
			if (check == true) {
				ReadExcelData.getInstance("Test2.1").updateCellValue("Email", "");
				ReadExcelData.getInstance("Test2.1").updateCellValue("Email",
						"starbucks" + System.currentTimeMillis() + "@gmail.com");
				email.sendKeys(ReadExcelData.getInstance("Test2.1").getStringValue("Email"));
			} else {
				email.sendKeys(ReadExcelData.getInstance("Test2.1").getStringValue("Email"));
			}

			PCDriver.waitForElementToBeClickable(pwd);
			Thread.sleep(8000);
			pwd.sendKeys(ReadExcelData.getInstance("Test2.1").getStringValue("Password"));
			retypepwd.sendKeys(ReadExcelData.getInstance("Test2.1").getStringValue("RetypePassword"));
			PCDriver.waitForElementToBeClickable(phno1);
			phno1.sendKeys(ReadExcelData.getInstance("Test2.1").getStringValue("PhnNo1"));
			((JavascriptExecutor)PCDriver.getDriver()).executeScript("arguments[0].scrollIntoView();", phno2);
			Thread.sleep(4000);
			PCDriver.waitForElementToBeClickable(phno2);
			phno2.sendKeys(ReadExcelData.getInstance("Test2.1").getStringValue("PhnNo2"));
			PCDriver.waitForElementToBeClickable(phno3);

			phno3.sendKeys(ReadExcelData.getInstance("Test2.1").getStringValue("PhnNo3"));
			PCDriver.waitForElementToBeClickable(nextpage2, Long.valueOf("10"));
			nextpage2.click();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/************ Test case3 *****************/
	public void companydetailstestscript3() {
		try {
			PCDriver.waitForElementToBeClickable(companyname);
			companyname.sendKeys(ReadExcelData.getInstance("Test3").getStringValue("Company Name"));
			PCDriver.waitForElementToBeClickable(Busname);
			Busname.sendKeys(ReadExcelData.getInstance("Test3").getStringValue("Doing Business"));
			PCDriver.waitForElementToBeClickable(adrs1);
			adrs1.sendKeys(ReadExcelData.getInstance("Test3").getStringValue("Company Address1"));
			PCDriver.waitForElementToBeClickable(adrs2);
			adrs2.sendKeys(ReadExcelData.getInstance("Test3").getStringValue("Company Address2"));
			PCDriver.waitForElementToBeClickable(city1);
			city1.sendKeys(ReadExcelData.getInstance("Test3").getStringValue("City"));

			PCDriver.waitForElementToBeClickable(state);
			PCDriver.selectFromDropDownByVisibleText(state,
					(ReadExcelData.getInstance("Test3").getStringValue("State")));
			PCDriver.waitForElementToBeClickable(zip);
			zip.sendKeys(ReadExcelData.getInstance("Test3").getStringValue("Zip Code"));

			PCDriver.selectFromDropDownByVisibleText(country,
					(ReadExcelData.getInstance("Test3").getStringValue("Country")));
			PCDriver.waitForElementToBeClickable(branch);
			branch.click();
			PCDriver.waitForElementToBeClickable(fein1);
			fein1.sendKeys(ReadExcelData.getInstance("Test3").getStringValue("Employer Id No1"));
			PCDriver.waitForElementToBeClickable(fein2);
			fein2.sendKeys(ReadExcelData.getInstance("Test3").getStringValue("Employer Id No2"));
			PCDriver.waitForElementToBeClickable(securityno1);
			securityno1.sendKeys(ReadExcelData.getInstance("Test3").getStringValue("Security No1"));
			PCDriver.waitForElementToBeClickable(securityno2);
			securityno2.sendKeys(ReadExcelData.getInstance("Test3").getStringValue("Security No2"));
			PCDriver.waitForElementToBeClickable(securityno3);
			securityno3.sendKeys(ReadExcelData.getInstance("Test3").getStringValue("Security No3"));
			PCDriver.waitForElementToBeClickable(Busstype);
			PCDriver.selectFromDropDownByVisibleText(Busstype,
					(ReadExcelData.getInstance("Test3").getStringValue("TypeofBuss")));
			PCDriver.waitForElementToBeClickable(dunsno);
			dunsno.sendKeys(ReadExcelData.getInstance("Test3").getStringValue("DUNS"));
			PCDriver.waitForElementToBeClickable(chk1);
			chk1.click();
			System.out.println("The Title is:" + PCDriver.getDriver().getTitle());
			Thread.sleep(10000);
			PCDriver.waitForElementToBeClickable(nextpage, Long.valueOf("10"));
			((JavascriptExecutor) PCDriver.getDriver()).executeScript("arguments[0].scrollIntoView(true);", nextpage);
			PCDriver.waitForElementToBeClickable(nextpage);
			Thread.sleep(15000);
			nextpage.click();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void SuppliercontactdetailTestscript3() {
		try {
			PCDriver.waitForPageLoad();
			String expectedTitle = "Test Harness";
			String actualTitle = PCDriver.getDriver().getTitle();
			Assert.assertEquals(actualTitle, expectedTitle);
			Thread.sleep(5000);
			PCDriver.waitForElementToBeClickable(firstname);
			firstname.sendKeys(ReadExcelData.getInstance("Test3.1").getStringValue("FirstName"));
			lastname.sendKeys(ReadExcelData.getInstance("Test3.1").getStringValue("LastName"));
			email.sendKeys(ReadExcelData.getInstance("Test3.1").getStringValue("Email") + System.currentTimeMillis()
					+ "@gmail.com");
			pwd.sendKeys(ReadExcelData.getInstance("Test3.1").getStringValue("Password"));
			retypepwd.sendKeys(ReadExcelData.getInstance("Test3.1").getStringValue("RetypePassword"));
			phno1.sendKeys(ReadExcelData.getInstance("Test3.1").getStringValue("PhnNo1"));
			phno2.sendKeys(ReadExcelData.getInstance("Test3.1").getStringValue("PhnNo2"));
			phno3.sendKeys(ReadExcelData.getInstance("Test3.1").getStringValue("PhnNo3"));
			PCDriver.waitForElementToBeClickable(nextpage2, Long.valueOf("10"));
			nextpage2.click();

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/************ Test case4 *****************/
	public void companydetailstestscript4(boolean check) {

		try {
			// Thread.sleep(5000);
			PCDriver.waitForElementToBeClickable(companyname);
			companyname.sendKeys(ReadExcelData.getInstance("Test4").getStringValue("Company Name"));
			PCDriver.waitForElementToBeClickable(Busname);
			Busname.sendKeys(ReadExcelData.getInstance("Test4").getStringValue("Doing Business"));
			PCDriver.waitForElementToBeClickable(adrs1);
			adrs1.sendKeys(ReadExcelData.getInstance("Test4").getStringValue("Company Address1"));
			PCDriver.waitForElementToBeClickable(adrs2);
			adrs2.sendKeys(ReadExcelData.getInstance("Test4").getStringValue("Company Address2"));
			PCDriver.waitForElementToBeClickable(city1);
			city1.sendKeys(ReadExcelData.getInstance("Test4").getStringValue("City"));

			PCDriver.waitForElementToBeClickable(state);
			PCDriver.selectFromDropDownByVisibleText(state,
					(ReadExcelData.getInstance("Test4").getStringValue("State")));
			PCDriver.waitForElementToBeClickable(zip);
			zip.sendKeys(ReadExcelData.getInstance("Test4").getStringValue("Zip Code"));

			PCDriver.selectFromDropDownByVisibleText(country,
					(ReadExcelData.getInstance("Test4").getStringValue("Country")));
			PCDriver.waitForElementToBeClickable(branch);
			branch.click();
			ReadExcelData.getInstance("Registration").updateCellValue("FEIN", ssnAndFeinGenerator.FeinGenerator());

			PCDriver.waitForElementToBeClickable(fein1);
			fein1.sendKeys(ReadExcelData.getInstance("Registration").getStringValue("FEIN").substring(0, 2));
			PCDriver.waitForElementToBeClickable(fein2);
			fein2.sendKeys(ReadExcelData.getInstance("Registration").getStringValue("FEIN").substring(2));
			if (check == true) {
				PCDriver.waitForElementToBeClickable(securityno1);
				ReadExcelData.getInstance("Registration").updateCellValue("SSN", ssnAndFeinGenerator.generateSSN());
				securityno1.sendKeys(ReadExcelData.getInstance("Registration").getStringValue("SSN").substring(0, 3));
				PCDriver.waitForElementToBeClickable(securityno2);
				securityno2.sendKeys(ReadExcelData.getInstance("Registration").getStringValue("SSN").substring(3, 5));
				PCDriver.waitForElementToBeClickable(securityno3);
				securityno3.sendKeys(ReadExcelData.getInstance("Registration").getStringValue("SSN").substring(5));
			} else {
				PCDriver.waitForElementToBeClickable(securityno1);
				securityno1.sendKeys(ReadExcelData.getInstance("Registration").getStringValue("SSN").substring(0, 3));
				PCDriver.waitForElementToBeClickable(securityno2);
				securityno2.sendKeys(ReadExcelData.getInstance("Registration").getStringValue("SSN").substring(3, 5));
				PCDriver.waitForElementToBeClickable(securityno3);
				securityno3.sendKeys(ReadExcelData.getInstance("Registration").getStringValue("SSN").substring(5));
			}
			PCDriver.waitForElementToBeClickable(Busstype);
			PCDriver.selectFromDropDownByVisibleText(Busstype,
					(ReadExcelData.getInstance("Test4").getStringValue("TypeofBuss")));
			PCDriver.waitForElementToBeClickable(dunsno);
			dunsno.sendKeys(ReadExcelData.getInstance("Test4").getStringValue("DUNS"));
			PCDriver.waitForElementToBeClickable(chk1);
			chk1.click();
			System.out.println("The Title is:" + PCDriver.getDriver().getTitle());
			Thread.sleep(10000);
			PCDriver.waitForElementToBeClickable(nextpage, Long.valueOf("10"));
			((JavascriptExecutor) PCDriver.getDriver()).executeScript("arguments[0].scrollIntoView(true);", nextpage);
			PCDriver.waitForElementToBeClickable(nextpage);
			Thread.sleep(15000);
			nextpage.click();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void SuppliercontactdetailTestscript4(boolean check) {
		try {
			PCDriver.waitForPageLoad();
			String expectedTitle = "Test Harness";
			String actualTitle = PCDriver.getDriver().getTitle();
			Assert.assertEquals(actualTitle, expectedTitle);
			Thread.sleep(5000);
			PCDriver.waitForElementToBeClickable(firstname);
			firstname.sendKeys(ReadExcelData.getInstance("Test4.1").getStringValue("FirstName"));
			lastname.sendKeys(ReadExcelData.getInstance("Test4.1").getStringValue("LastName"));
			if (check == true) {
				ReadExcelData.getInstance("Test4.1").updateCellValue("Email", "");

				ReadExcelData.getInstance("Test4.1").updateCellValue("Email",
						ReadExcelData.getInstance("Test4.1").getStringValue("Email") + System.currentTimeMillis()
								+ "@gmail.com");
				email.sendKeys(ReadExcelData.getInstance("Test4.1").getStringValue("Email"));

			} else {
				email.sendKeys(ReadExcelData.getInstance("Test4.1").getStringValue("Email"));
			}

			pwd.sendKeys(ReadExcelData.getInstance("Test4.1").getStringValue("Password"));
			retypepwd.sendKeys(ReadExcelData.getInstance("Test4.1").getStringValue("RetypePassword"));
			phno1.sendKeys(ReadExcelData.getInstance("Test4.1").getStringValue("PhnNo1"));
			phno2.sendKeys(ReadExcelData.getInstance("Test4.1").getStringValue("PhnNo2"));
			phno3.sendKeys(ReadExcelData.getInstance("Test4.1").getStringValue("PhnNo3"));
			PCDriver.waitForElementToBeClickable(nextpage2, Long.valueOf("10"));
			nextpage2.click();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void SelectSol() {
		PCDriver.waitForPageLoad();
		PCDriver.waitForElementToBeClickable(Tumbling);

		Tumbling.click();
		Pumice.click();

		PCDriver.waitForElementToBeClickable(nextpage3);
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		((JavascriptExecutor) PCDriver.getDriver()).executeScript("arguments[0].scrollIntoView(true);", nextpage3);
		nextpage3.click();
	}

	/*********** Idaho Driver ********/

	public class Supplierinfo {
		public Supplierinfo() {
			PCDriver.driver.navigate().to(ReadConfig.getInstance().getIdahoRegistration());
			PageFactory.initElements(PCDriver.getDriver(), this);
		}

		public void setDropDown() {
			PCDriver.waitForElementToBeClickable(drpDownClassification);
			PCDriver.selectFromDropDownByVisibleText(drpDownClassification, "Hispanic or Latino American");

		}
	}

	public class Supplierdetails {
		public Supplierdetails() {
			PCDriver.driver.navigate().to(ReadConfig.getInstance().getIdahoRegistration());
			PageFactory.initElements(PCDriver.getDriver(), this);
		}
	}

	public class Supplierinfo2 {
		public Supplierinfo2() {
			PCDriver.driver.navigate().to(ReadConfig.getInstance().getIdahoRegistration());
			PageFactory.initElements(PCDriver.getDriver(), this);
		}
	}

	public class Supplierdetails2 {
		public Supplierdetails2() {
			PCDriver.driver.navigate().to(ReadConfig.getInstance().getIdahoRegistration());
			PageFactory.initElements(PCDriver.getDriver(), this);
		}
	}

	public class listofSol {
		public listofSol() {
			PCDriver.driver.navigate().to(ReadConfig.getInstance().getIdahoRegistration());
			PageFactory.initElements(PCDriver.getDriver(), this);
		}
	}

	/*********** WhiteLabel Driver ********/
	public IdahowhitelabelPageObjects() {

		PCDriver.driver.navigate().to(ReadConfig.getInstance().getIdahoRegistration());
		PageFactory.initElements(PCDriver.getDriver(), this);
	}

	public void clickidahosupplierregistrationbtn() {

		try {
			Thread.sleep(10000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PCDriver.getDriver().switchTo().defaultContent();
		PCDriver.getDriver().switchTo().frame(0);

		PCDriver.waitForElementToBeClickable(iagreechkbox);
		iagreechkbox.click();
		PCDriver.waitForElementToBeClickable(nextpage);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		nextpage.click();
		PCDriver.waitForPageLoad();
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void firstfintestcase() {
		clickidahosupplierregistrationbtn();
		companydetails();
		Suppliercontactdetail(true);
		SelectSol();
		PCDriver.waitForPageLoad();

	}

	public void samefintestcase() {
		// PCDriver.waitForPageLoad();
		clickidahosupplierregistrationbtn();
		companydetailstestscript2();
		SuppliercontactdetailTestscript2(false);
		SelectSol();

	}

	public void Differentfintestcase() {

		// PCDriver.waitForPageLoad();
		clickidahosupplierregistrationbtn();
		companydetailstestscript3();
		SuppliercontactdetailTestscript3();
		SelectSol();
		Assert.assertTrue(verify());

	}

	public void firstssntestcase(boolean check) {
		if (check == true) {
			clickidahosupplierregistrationbtn();
			companydetailstestscript4(check);
			SuppliercontactdetailTestscript4(true);
			SelectSol();
			Assert.assertTrue(verify());
		} else {
			clickidahosupplierregistrationbtn();
			companydetailstestscript4(check);
		}
	}

	public void DuplicateUsernameCheck() {
		clickidahosupplierregistrationbtn();
		companydetailstestscript4(true);
		SuppliercontactdetailTestscript4(false);

	}

	public boolean verifyDuplicateSsn() {
		if (ssnCheck.getText().contains("A company has already been registered with this SSN in our system")) {
			return true;
		} else {
			return false;
		}
	}
}
