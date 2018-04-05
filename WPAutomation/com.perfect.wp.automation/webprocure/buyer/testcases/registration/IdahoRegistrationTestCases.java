package buyer.testcases.registration;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import buyer.pageobjects.registration.IdahowhitelabelPageObjects;
import commonutils.pageobjects.utils.ExtentReport;

@Listeners(ExtentReport.class)
public class IdahoRegistrationTestCases {

	IdahowhitelabelPageObjects idaho;

	@BeforeClass
	public void setup() {
		ExtentReport.logger = ExtentReport.report.startTest(this.getClass().getSimpleName());
		ExtentReport.logger.log(LogStatus.INFO, "Test Case Started");
		ExtentReport.logger.log(LogStatus.PASS, "Browser Invoked");
	}

	@BeforeMethod
	public void geturl() {
		idaho = new IdahowhitelabelPageObjects();
	}

	/************ Test scripts *****************/

	@Test(description = "This test case will register supplier using SSN and FEIN", enabled = true)

	public void registerUsingSsnFein() {
		ExtentReport.logger.log(LogStatus.INFO, "Test Case Started");

		idaho.clickidahosupplierregistrationbtn();
		ExtentReport.logger.log(LogStatus.PASS, "Terms And Conditions Accepted");

		idaho.companydetails(true);
		ExtentReport.logger.log(LogStatus.PASS, "Company Details Entered");

		idaho.Suppliercontactdetail(true);
		ExtentReport.logger.log(LogStatus.PASS, "Supplier Contact Details Entered");

		idaho.SelectSol();
		ExtentReport.logger.log(LogStatus.PASS, "Commodities Selected");

		Assert.assertTrue(idaho.verify());
		ExtentReport.logger.log(LogStatus.PASS, "Register Using Ssn Done");

	}

	@Test(description = "This test case will register supplier using FEIN", enabled = true)
	public void RegisterWithFein() {
		// idaho.Differentfintestcase(true);
		ExtentReport.logger.log(LogStatus.INFO, "Test Case Started");

		idaho.clickidahosupplierregistrationbtn();
		ExtentReport.logger.log(LogStatus.PASS, "Terms And Conditions Accepted");

		idaho.companydetailstestscript3(true);
		ExtentReport.logger.log(LogStatus.PASS, "Company Details Entered");
		idaho.SuppliercontactdetailTestscript3();
		ExtentReport.logger.log(LogStatus.PASS, "Supplier Contact Details Entered");

		idaho.SelectSol();
		ExtentReport.logger.log(LogStatus.PASS, "Commodities Selected");

		Assert.assertTrue(idaho.verify());
		ExtentReport.logger.log(LogStatus.PASS, "Registration Completed");

	}

	@Test(description = "This test case will check if user is able to register using Duplicate Fein", dependsOnMethods = {
			"RegisterWithFein" }, enabled = true)
	public void DuplicateFeinCheck() throws IOException {
		ExtentReport.logger.log(LogStatus.INFO, "Test Case Started");
		idaho.clickidahosupplierregistrationbtn();

		ExtentReport.logger.log(LogStatus.PASS, "Terms And Conditions Accepted");

		idaho.companydetailstestscript3(false);
		ExtentReport.logger.log(LogStatus.PASS, "Company Details Entered");
		// idaho.Differentfintestcase(false);
		Assert.assertTrue(idaho.verifyDuplicateFein());
		ExtentReport.logger.log(LogStatus.PASS, "Not able to proceed with Duplicate Fein");

	}

	@Test(description = "This test case will register supplier using Ssn", enabled = true)
	public void RegisterUsingSsn() {
		ExtentReport.logger.log(LogStatus.INFO, "Test Case Started");

		idaho.clickidahosupplierregistrationbtn();
		idaho.companydetailstestscript4(true);
		ExtentReport.logger.log(LogStatus.PASS, "Company Details Entered");

		idaho.SuppliercontactdetailTestscript4(true);
		ExtentReport.logger.log(LogStatus.PASS, "Supplier Contact Details Entered");

		idaho.SelectSol();
		ExtentReport.logger.log(LogStatus.PASS, "Commodities Selected");

		Assert.assertTrue(idaho.verify());
		ExtentReport.logger.log(LogStatus.PASS, "Registration Completed");
		// idaho.firstssntestcase(true);

	}

	@Test(description = "This test case will check Duplicate Ssn", dependsOnMethods = {
			"RegisterUsingSsn" }, enabled = true)
	public void DuplicateSsnCheck() {
		ExtentReport.logger.log(LogStatus.INFO, "Test Case Started");

		idaho.clickidahosupplierregistrationbtn();
		idaho.companydetailstestscript4(false);
		// idaho.firstssntestcase(false);
		Assert.assertTrue(idaho.verifyDuplicateSsn());

	}

	@Test(description = "This test case will check Duplicate Ssn", dependsOnMethods = {
			"RegisterUsingSsn" }, enabled = true)
	public void DuplicateUsernameCheck() throws IOException {
		ExtentReport.logger.log(LogStatus.INFO, "Test Case Started");

		// idaho.samefintestcase();
		idaho.clickidahosupplierregistrationbtn();
		ExtentReport.logger.log(LogStatus.PASS, "Terms And Conditions Accepted");

		idaho.companydetailstestscript2(false);
		ExtentReport.logger.log(LogStatus.PASS, "Company Details Entered");

		idaho.SuppliercontactdetailTestscript2(false);
		ExtentReport.logger.log(LogStatus.PASS, "Supplier Contact Details Entered");

		idaho.SelectSol();
		ExtentReport.logger.log(LogStatus.PASS, "Commodities Selected");
		Assert.assertTrue(idaho.verifyDuplicateUsername());

	}

	@AfterClass
	public void tearDown() {
		ExtentReport.report.flush();
	}
}
