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
	
	
	@Test(description="This test case will register supplier using SSN and FEIN",enabled=true)

	public void registerUsingSsnFein() {
		idaho.firstfintestcase();
		Assert.assertTrue(idaho.verify());

	}

	

	@Test(description="This test case will register supplier using SSN and FEIN",dependsOnMethods= {"RegisterWithFein"},enabled=true)
	public void CheckDuplicateFein() {
		idaho.samefintestcase();

		
	}

	@Test(description="This test case will register supplier using FEIN",enabled=true)
	public void RegisterWithFein() {
		idaho.Differentfintestcase();
		//Assert.assertTrue(idaho.verify());


	}
	@Test(description="This test case will register supplier using Ssn",enabled=true)
	public void RegisterUsingSsn() {
		idaho.firstssntestcase(true);

	}
	
	@Test(description="This test case will check Duplicate Ssn",dependsOnMethods = {
	"RegisterUsingSsn" },enabled=true)
	public void DuplicateSsnCheck() {
		idaho.firstssntestcase(false);
		Assert.assertTrue(idaho.verifyDuplicateSsn());

	}
	
	@Test(description="This test case will check Duplicate Ssn",dependsOnMethods = {
	"RegisterUsingSsn" },enabled=true)
	public void DuplicateUsernameCheck() {
		idaho.DuplicateUsernameCheck();
		//Assert.assertTrue(idaho.verifyDuplicateSsn());

	}
	
	@AfterClass
	public void tearDown() {
		ExtentReport.report.flush();
	}
}
