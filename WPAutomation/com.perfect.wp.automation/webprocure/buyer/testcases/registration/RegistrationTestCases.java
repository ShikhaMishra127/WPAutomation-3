package buyer.testcases.registration;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import buyer.pageobjects.registration.RegistrationPagePom;
import commonutils.pageobjects.generic.LoginPage;
import commonutils.pageobjects.utils.ExtentReport;

@Listeners(ExtentReport.class)

public class RegistrationTestCases {
	LoginPage login = new LoginPage();
	RegistrationPagePom register = new RegistrationPagePom();

	@BeforeClass
	public void setup() {
		ExtentReport.logger = ExtentReport.report.startTest(this.getClass().getSimpleName());
		ExtentReport.logger.log(LogStatus.INFO, "Test Case Started");
		ExtentReport.logger.log(LogStatus.PASS, "Browser Invoked");
	}

	@BeforeMethod
	public void setupBeforeTest() {
		login.clickOnRegisterLink();
	}

	@Test(description = "This test case will register using both FEIN and SSN",enabled=true)
	public void registrationWithFeinAndSsn() throws IOException {
		try {
			register.setOrgInfo();
			register.clickContinue();
			register.setContactInfo();
			register.clickContinue();
			register.clickContinue();
			register.setBuyerOrganization();
			register.clickContinue();
			register.setBuyerTermsAndConditions();
			register.clickContinue();
			register.setUsernameAndPassword();
			register.clickContinue();
			register.clickContinue();
			Assert.assertEquals(register.VerifyRegistration(), "Documents");
			login.logout();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Test Case Failed");
		}
	}

	@Test(description = "This test case will register using SSN only",enabled=true)
	public void registrationWithSsn() throws IOException {
		try {
			register.setOrgInfoWithSsnOnly(true);
			register.clickContinue();
			register.setContactInfo();
			register.clickContinue();
			register.clickContinue();
			register.setBuyerOrganization();
			register.clickContinue();
			register.setBuyerTermsAndConditions();
			register.clickContinue();
			register.setUsernameAndPassword();
			register.clickContinue();
			register.clickContinue();
			Assert.assertEquals(register.VerifyRegistration(), "Documents");
			login.logout();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Test Case Failed");
		}
	}

	@Test(description = "This test case will register using Fein only",enabled=true)
	public void registrationWithFein() throws IOException {
		try {
			register.setOrgInfoWithFeinOnly(true);
			register.clickContinue();
			register.setContactInfo();
			register.clickContinue();
			register.clickContinue();
			register.setBuyerOrganization();
			register.clickContinue();
			register.setBuyerTermsAndConditions();
			register.clickContinue();
			register.setUsernameAndPassword();
			register.clickContinue();
			register.clickContinue();
			Assert.assertEquals(register.VerifyRegistration(), "Documents");
			login.logout();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("This test case is failed ");
		}
	}

	@Test(description = "This test case will check the duplicate SSN", dependsOnMethods = { "registrationWithSsn" },enabled=true)
	public void registrationWithDuplicateSsn() throws IOException {
		try {
			register.setOrgInfoWithSsnOnly(false);
			register.clickContinue();
			Assert.assertEquals(register.duplicateSsnCheck(), true, "Same SSN is already present in the system");
			register.navigateToHome();

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Duplicate SSN message is not visible");
			register.navigateToHome();

		}
	}
/*
	 @Test(description="This test case will check the duplicate SSN",enabled=true)
	public void registrationWithDuplicateDuns() throws IOException {
		try {
			register.setOrgInfoWithSsnOnly(false);
			register.clickContinue();
			Assert.assertEquals(register.duplicateSsnCheck(), true, "Same SSN is already present in the system");
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Duplicate SSN message is not visible");
		}
	}*/
	 
	 @Test(description="This test case will check the duplicate FEIN",dependsOnMethods = { "registrationWithFein" },enabled=true)
		public void registrationWithDuplicateFein() throws IOException {
			try {
				register.setOrgInfoWithFeinOnly(false);
				register.clickContinue();
				Assert.assertEquals(register.duplicateFeinCheck(), true, "System does not allow to register as a Headquarters with the same FEIN.");
				register.navigateToHome();
			} catch (Exception e) {
				e.printStackTrace();
				Assert.fail("Duplicate Fein message is not visible");
				register.navigateToHome();

			}
		}

	@AfterClass
	public void tearDown() {
		ExtentReport.report.endTest(ExtentReport.logger);
		ExtentReport.report.flush();
		ExtentReport.report.close();
	}
}
