package buyer.testcases.registration;

import java.io.IOException;
import java.lang.reflect.Method;

import org.springframework.cglib.core.Local;
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
		ExtentReport.report.endTest(ExtentReport.logger);
	}

	@BeforeMethod
	public void setupBeforeTest() {
		login.clickOnRegisterLink();
	}

	@Test(description = "This test case will register using both FEIN and SSN", enabled = true)
	public void registrationWithFeinAndSsn() throws IOException {
		try {
			//ExtentReport.logger = ExtentReport.report.startTest();

			register.setOrgInfo();
			ExtentReport.logger.log(LogStatus.PASS, "Organization Information entered");

			register.clickContinue();
			ExtentReport.logger.log(LogStatus.PASS, "Continue button clicked");

			register.setContactInfo();
			ExtentReport.logger.log(LogStatus.PASS, "Contact Information Entered");

			register.clickContinue();
			ExtentReport.logger.log(LogStatus.PASS, "Continue button clicked");

			register.clickContinue();
			ExtentReport.logger.log(LogStatus.PASS, "Continue button clicked");

			register.setBuyerOrganization();
			ExtentReport.logger.log(LogStatus.PASS, "Buyer Organization entered");

			register.clickContinue();
			ExtentReport.logger.log(LogStatus.PASS, "Continue button clicked");

			register.setBuyerTermsAndConditions();
			ExtentReport.logger.log(LogStatus.PASS, "Terms and Conditions Accepted");

			register.clickContinue();
			ExtentReport.logger.log(LogStatus.PASS, "Continue button clicked");

			register.setUsernameAndPassword();
			ExtentReport.logger.log(LogStatus.PASS, "Username and Password entered");

			register.clickContinue();
			ExtentReport.logger.log(LogStatus.PASS, "Continue button clicked");

			register.clickContinue();
			ExtentReport.logger.log(LogStatus.PASS, "Continue button clicked");

			Assert.assertEquals(register.VerifyRegistration(), "Documents");
			ExtentReport.logger.log(LogStatus.PASS, "Registration verification done");

			login.logout();

		} catch (Exception e) {
			ExtentReport.logger.log(LogStatus.FAIL, "Test case failed");

			Assert.fail("Test Case Failed");
		}
		ExtentReport.report.endTest(ExtentReport.logger);

	}

	@Test(description = "This test case will register using SSN only", enabled = true)
	public void registrationWithSsn() throws IOException {
		try {
			//ExtentReport.logger = ExtentReport.report.startTest(this.getClass().getEnclosingMethod().getName());

			register.setOrgInfoWithSsnOnly(true);
			ExtentReport.logger.log(LogStatus.PASS, "Organization Information entered");

			register.clickContinue();
			ExtentReport.logger.log(LogStatus.PASS, "Continue button clicked");

			register.setContactInfo();
			ExtentReport.logger.log(LogStatus.PASS, "Contact Information Entered");

			register.clickContinue();
			ExtentReport.logger.log(LogStatus.PASS, "Continue button clicked");

			register.clickContinue();
			ExtentReport.logger.log(LogStatus.PASS, "Continue button clicked");

			register.setBuyerOrganization();
			ExtentReport.logger.log(LogStatus.PASS, "Buyer Organization entered");

			register.clickContinue();
			ExtentReport.logger.log(LogStatus.PASS, "Continue button clicked");

			register.setBuyerTermsAndConditions();
			ExtentReport.logger.log(LogStatus.PASS, "Terms and Conditions Accepted");

			register.clickContinue();
			ExtentReport.logger.log(LogStatus.PASS, "Continue button clicked");

			register.setUsernameAndPassword();
			ExtentReport.logger.log(LogStatus.PASS, "Username and Password entered");

			register.clickContinue();
			ExtentReport.logger.log(LogStatus.PASS, "Continue button clicked");

			register.clickContinue();
			ExtentReport.logger.log(LogStatus.PASS, "Continue button clicked");

			Assert.assertEquals(register.VerifyRegistration(), "Documents");
			ExtentReport.logger.log(LogStatus.PASS, "Registration verification done");

			login.logout();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Test Case Failed");
			ExtentReport.logger.log(LogStatus.FAIL, "Test case failed");

		}
		ExtentReport.report.endTest(ExtentReport.logger);

	}

	@Test(description = "This test case will register using Fein only", enabled = true)
	public void registrationWithFein() throws IOException {
		try {
			//ExtentReport.logger = ExtentReport.report.startTest(this.getClass().getEnclosingMethod().getName());

			register.setOrgInfoWithFeinOnly(true);
			ExtentReport.logger.log(LogStatus.PASS, "Organization Information entered");

			register.clickContinue();
			ExtentReport.logger.log(LogStatus.PASS, "Continue button clicked");

			register.setContactInfo();
			ExtentReport.logger.log(LogStatus.PASS, "Contact Information Entered");

			register.clickContinue();
			ExtentReport.logger.log(LogStatus.PASS, "Continue button clicked");

			register.clickContinue();
			ExtentReport.logger.log(LogStatus.PASS, "Continue button clicked");

			register.setBuyerOrganization();
			register.clickContinue();
			ExtentReport.logger.log(LogStatus.PASS, "Continue button clicked");

			register.setBuyerTermsAndConditions();
			ExtentReport.logger.log(LogStatus.PASS, "Buyer Organization entered");

			register.clickContinue();
			ExtentReport.logger.log(LogStatus.PASS, "Continue button clicked");

			register.setUsernameAndPassword();
			register.clickContinue();
			ExtentReport.logger.log(LogStatus.PASS, "Continue button clicked");

			register.clickContinue();
			ExtentReport.logger.log(LogStatus.PASS, "Continue button clicked");

			Assert.assertEquals(register.VerifyRegistration(), "Documents");
			ExtentReport.logger.log(LogStatus.PASS, "Registration verification done");

			login.logout();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("This test case is failed ");
			ExtentReport.logger.log(LogStatus.FAIL, "Test case failed");

		}
		ExtentReport.report.endTest(ExtentReport.logger);

	}

	@Test(description = "This test case will check the duplicate SSN", dependsOnMethods = {
			"registrationWithSsn" }, enabled = true)
	public void registrationWithDuplicateSsn() throws IOException {
		try {
			//ExtentReport.logger = ExtentReport.report.startTest(this.getClass().getEnclosingMethod().getName());

			register.setOrgInfoWithSsnOnly(false);
			ExtentReport.logger.log(LogStatus.PASS, "Organization Information entered");

			register.clickContinue();
			ExtentReport.logger.log(LogStatus.PASS, "Continue button clicked");

			Assert.assertEquals(register.duplicateSsnCheck(), true, "Same SSN is already present in the system");
			ExtentReport.logger.log(LogStatus.PASS, "Same Ssn cannot be allowed for registration");

			register.navigateToHome();

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Duplicate SSN message is not visible");
			ExtentReport.logger.log(LogStatus.FAIL, "Test case failed");

			register.navigateToHome();

		}
		ExtentReport.report.endTest(ExtentReport.logger);

	}
	/*
	 * @Test(description="This test case will check the duplicate SSN",enabled=true)
	 * public void registrationWithDuplicateDuns() throws IOException { try {
	 * register.setOrgInfoWithSsnOnly(false); register.clickContinue();
	 * Assert.assertEquals(register.duplicateSsnCheck(), true,
	 * "Same SSN is already present in the system"); } catch (Exception e) {
	 * e.printStackTrace(); Assert.fail("Duplicate SSN message is not visible"); } }
	 */

	@Test(description = "This test case will check the duplicate FEIN", dependsOnMethods = {
			"registrationWithFein" }, enabled = true)
	public void registrationWithDuplicateFein() throws IOException {
		try {
			//ExtentReport.logger = ExtentReport.report.startTest(this.getClass().getEnclosingMethod().getName());

			register.setOrgInfoWithFeinOnly(false);
			ExtentReport.logger.log(LogStatus.PASS, "Organization Information entered");

			register.clickContinue();
			ExtentReport.logger.log(LogStatus.PASS, "Continue button clicked");

			Assert.assertEquals(register.duplicateFeinCheck(), true,
					"System does not allow to register as a Headquarters with the same FEIN.");
			ExtentReport.logger.log(LogStatus.PASS, "Same Fein cannot be used for registration");

			register.navigateToHome();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Duplicate Fein message is not visible");
			register.navigateToHome();

		}
		ExtentReport.report.endTest(ExtentReport.logger);

	}

	@AfterClass
	public void tearDown() {

		ExtentReport.report.flush();
		ExtentReport.report.close();
	}
}
