package testcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import pageobjects.generic.LoginPage;
import pageobjects.registration.WhiteLabelRegistrationPagePom;
import pageobjects.utils.ExtentReport;
import pageobjects.utils.PCDriver;

@Listeners(ExtentReport.class)

public class WhiteLabelRegistrationTestCases {

	LoginPage login = new LoginPage();
	WhiteLabelRegistrationPagePom whiteLabelregister;

	@BeforeClass
	public void setup() {
		ExtentReport.logger = ExtentReport.report.startTest(this.getClass().getSimpleName());
		ExtentReport.logger.log(LogStatus.INFO, "Test Case Started");
		ExtentReport.logger.log(LogStatus.PASS, "Browser Invoked");

	}

	@BeforeMethod()
	public void setupBeforeTest() {
		whiteLabelregister = new WhiteLabelRegistrationPagePom();

	}

	@Test(description = "This test case will register using Fein for Missouri")
	public void RegistrationWithFeinForMissouri() throws IOException, InterruptedException {
		try {
			whiteLabelregister.clickRegistrationCheckBox("Missouri");
			ExtentReport.logger.log(LogStatus.PASS, "Missouri Check Box Clicked");
			whiteLabelregister.AcceptTermsAndConditions();
			ExtentReport.logger.log(LogStatus.PASS, "Terms and Conditions Accepted");
			whiteLabelregister.setOrgInfoWithFeinForMissouri();
			ExtentReport.logger.log(LogStatus.PASS, "Organization Information Entered");
			whiteLabelregister.clickNext();
			ExtentReport.logger.log(LogStatus.PASS, "Next Button Clicked");
			whiteLabelregister.setOrgContactInfo("Missouri");
			ExtentReport.logger.log(LogStatus.PASS, "Contact Information Entered");
			whiteLabelregister.clickNext();
			ExtentReport.logger.log(LogStatus.PASS, "Next Button Clicked");
			whiteLabelregister.clickNext();
			whiteLabelregister.setCategory("Missouri");
			ExtentReport.logger.log(LogStatus.PASS, "Category Selected");
			whiteLabelregister.clickNext();
			ExtentReport.logger.log(LogStatus.PASS, "Registration Completed");
			Assert.assertEquals(whiteLabelregister.checkConfirmMessage(), true, "Registration success");
			whiteLabelregister.clickWebProcureButtonAndCheckLogin();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}

	}

	 @Test(description = "This test case checks the Duplicate Fein", dependsOnMethods = { "RegistrationWithFeinForMissouri" })
	public void DuplicateFeinCheck() throws IOException, InterruptedException {
		try {
			whiteLabelregister.clickRegistrationCheckBox("Missouri");
			ExtentReport.logger.log(LogStatus.PASS, "Missouri Check Box Clicked");
			whiteLabelregister.AcceptTermsAndConditions();
			ExtentReport.logger.log(LogStatus.PASS, "Terms and Conditions Accepted");
			whiteLabelregister.setOrgInfoWithFeinForMissouri();
			ExtentReport.logger.log(LogStatus.PASS, "Organization Information Entered");
			whiteLabelregister.clickNext();
			ExtentReport.logger.log(LogStatus.PASS, "Next Button Clicked");
			Assert.assertEquals(whiteLabelregister.checkFeinMessage(), true, "Fein already exists");
		} catch (Exception e) {
			e.printStackTrace();

			Assert.fail();
		}
	}

	 @Test(description = "This test case will register using Ssn for Missouri")
	public void RegistrationWithSsnForMissouri() throws Exception {
		try {
			whiteLabelregister.clickRegistrationCheckBox("Missouri");
			ExtentReport.logger.log(LogStatus.PASS, "Missouri Check Box Clicked");
			whiteLabelregister.AcceptTermsAndConditions();
			ExtentReport.logger.log(LogStatus.PASS, "Terms and Conditions Accepted");
			whiteLabelregister.setOrgInfoWithSsnForMissouri();
			ExtentReport.logger.log(LogStatus.PASS, "Organization Information Entered");
			whiteLabelregister.clickNext();
			ExtentReport.logger.log(LogStatus.PASS, "Next Button Clicked");
			whiteLabelregister.setOrgContactInfo("Missouri");
			ExtentReport.logger.log(LogStatus.PASS, "Contact Information Entered");
			whiteLabelregister.clickNext();
			ExtentReport.logger.log(LogStatus.PASS, "Next Button Clicked");
			whiteLabelregister.clickNext();
			whiteLabelregister.setCategory("Missouri");
			ExtentReport.logger.log(LogStatus.PASS, "Category Selected");
			whiteLabelregister.clickNext();
			ExtentReport.logger.log(LogStatus.PASS, "Registration Completed");
			Assert.assertEquals(whiteLabelregister.checkConfirmMessage(), true, "Registration success");
		} catch (Exception e) {
			e.printStackTrace();

			Assert.fail();
		}

	}

	 @Test(description = "This test case check Duplicate Ssn for Missouri", dependsOnMethods = {"RegistrationWithSsnForMissouri" })
	public void DuplicateSsnCheck() throws Exception {
		try {
			whiteLabelregister.clickRegistrationCheckBox("Missouri");
			ExtentReport.logger.log(LogStatus.PASS, "Missouri Check Box Clicked");
			whiteLabelregister.AcceptTermsAndConditions();
			ExtentReport.logger.log(LogStatus.PASS, "Terms and Conditions Accepted");
			whiteLabelregister.setOrgInfoWithSsnForMissouri();
			ExtentReport.logger.log(LogStatus.PASS, "Organization Information Entered");
			Assert.assertEquals(whiteLabelregister.checkSsnMessage(), true, "SSn is already Present");
		} catch (Exception e) {
			e.printStackTrace();

			Assert.fail();
		}
	}

	 @Test(description = "This test case will register using Fein for Idaho")
	public void RegistrationWithFeinForIdaho() throws Exception {
		try {
			whiteLabelregister.clickRegistrationCheckBox("Idaho");
			ExtentReport.logger.log(LogStatus.PASS, "Idaho Check Box Clicked");
			whiteLabelregister.AcceptTermsAndConditions();
			ExtentReport.logger.log(LogStatus.PASS, "Terms and Conditions Accepted");
			whiteLabelregister.setCompanyInfoWithFeinForIdaho();
			ExtentReport.logger.log(LogStatus.PASS, "Company Information Entered");
			whiteLabelregister.clickNext();
			ExtentReport.logger.log(LogStatus.PASS, "Next Button Clicked");
			whiteLabelregister.setOrgContactInfo("Idaho");
			ExtentReport.logger.log(LogStatus.PASS, "Contact Information Entered");
			whiteLabelregister.clickNext();
			ExtentReport.logger.log(LogStatus.PASS, "Next Button Clicked");
			whiteLabelregister.setCategory("Idaho");
			ExtentReport.logger.log(LogStatus.PASS, "Category Selected");
			whiteLabelregister.clickNext();
			ExtentReport.logger.log(LogStatus.PASS, "Next Button Clicked");
			Assert.assertEquals(whiteLabelregister.checkConfirmMessage(), true, "Registration success");
		} catch (Exception e) {
			e.printStackTrace();

			Assert.fail();
		}

	}

	 @Test(description = "This test case will register using Ssn for Idaho")
	public void RegistrationWithSsnForIdaho() throws Exception {
		try {
			whiteLabelregister.clickRegistrationCheckBox("Idaho");
			ExtentReport.logger.log(LogStatus.PASS, "Idaho Check Box Clicked");
			whiteLabelregister.AcceptTermsAndConditions();
			ExtentReport.logger.log(LogStatus.PASS, "Terms and Conditions Accepted");
			whiteLabelregister.setCompanyInfoWithSSnForIdaho();
			ExtentReport.logger.log(LogStatus.PASS, "Company Information Entered");
			whiteLabelregister.clickNext();
			ExtentReport.logger.log(LogStatus.PASS, "Next Button Clicked");
			whiteLabelregister.setOrgContactInfo("Idaho");
			ExtentReport.logger.log(LogStatus.PASS, "Contact Information Entered");
			whiteLabelregister.clickNext();
			ExtentReport.logger.log(LogStatus.PASS, "Next Button Clicked");
			whiteLabelregister.setCategory("Idaho");
			ExtentReport.logger.log(LogStatus.PASS, "Category Selected");
			whiteLabelregister.clickNext();
			ExtentReport.logger.log(LogStatus.PASS, "Next Button Clicked");
			Assert.assertEquals(whiteLabelregister.checkConfirmMessage(), true, "Registration success");
		} catch (Exception e) {
			e.printStackTrace();

			Assert.fail();
		}

	}

	 @Test(dependsOnMethods="RegistrationWithFeinForIdaho")
	public void DuplicateSSnCheckForIdaho() throws Exception {
		whiteLabelregister.clickRegistrationCheckBox("Idaho");
		whiteLabelregister.AcceptTermsAndConditions();
		whiteLabelregister.setCompanyInfoWithSSnForIdaho();
		whiteLabelregister.clickNext();

	}

	@AfterClass
	public void tearDown() {
		ExtentReport.report.endTest(ExtentReport.logger);
		ExtentReport.report.flush();
		ExtentReport.report.close();
		 PCDriver.getDriver().quit();
	}
}
