package buyer.testcases.registration;

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
		idaho= new IdahowhitelabelPageObjects();
	}
	
	
	/************Test scripts*****************/
	
	
	@Test(description="This test case will register supplier using SSN and FEIN",enabled=false)

	public void registerUsingSsnFein() {
		ExtentReport.logger.log(LogStatus.INFO, "Test Case Started");

		idaho.firstfintestcase();
		Assert.assertTrue(idaho.verify());
		ExtentReport.logger.log(LogStatus.PASS, "Register Using Ssn Done");

	}

	

	@Test(description="This test case will register supplier using SSN and FEIN",/*dependsOnMethods= {"RegisterWithFein"},*/enabled=true)
	public void CheckDuplicateFein() {
		ExtentReport.logger.log(LogStatus.INFO, "Test Case Started");

		idaho.samefintestcase();

		ExtentReport.logger.log(LogStatus.PASS, "Not able to proceed with Duplicate Fein");

	}

	@Test(description="This test case will register supplier using FEIN",enabled=false)
	public void RegisterWithFein() {
		idaho.Differentfintestcase();
		//Assert.assertTrue(idaho.verify());


	}
	@Test(description="This test case will register supplier using Ssn",enabled=false)
	public void RegisterUsingSsn() {
		idaho.firstssntestcase(true);

	}
	
	@Test(description="This test case will check Duplicate Ssn",dependsOnMethods = {
	"RegisterUsingSsn" },enabled=false)
	public void DuplicateSsnCheck() {
		idaho.firstssntestcase(false);
		Assert.assertTrue(idaho.verifyDuplicateSsn());

	}
	
	@Test(description="This test case will check Duplicate Ssn",dependsOnMethods = {
	"RegisterUsingSsn" },enabled=false)
	public void DuplicateUsernameCheck() {
		idaho.DuplicateUsernameCheck();
		//Assert.assertTrue(idaho.verifyDuplicateSsn());

	}
	
	@AfterClass
	public void tearDown() {
		ExtentReport.report.flush();
	}
}
