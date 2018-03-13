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
import commonutils.pageobjects.utils.PCDriver;
import commonutils.pageobjects.utils.ReadConfig;

@Listeners(ExtentReport.class)
public class IdahoRegistrationTestCases {

	IdahowhitelabelPageObjects idaho = new IdahowhitelabelPageObjects();
	
	@BeforeClass
	public void setup() {
		ExtentReport.logger = ExtentReport.report.startTest(this.getClass().getSimpleName());
		ExtentReport.logger.log(LogStatus.INFO, "Test Case Started");
		ExtentReport.logger.log(LogStatus.PASS, "Browser Invoked");
	}
	
	/************Test scripts*****************/
	
	@Test(description="This test case will register supplier using SSN and FEIN",enabled=true)

	public void registerUsingSsnFein() {
		idaho.firstfintestcase();
		Assert.assertTrue(idaho.verify());
	}

	@BeforeMethod
	public void geturl() {
		PCDriver.getDriver().navigate().to(ReadConfig.getInstance().getIdahoRegistration());
	}

	@Test(description="This test case will register supplier using SSN and FEIN",enabled=true)
	public void CheckDuplicateFein() {
		idaho.samefintestcase();
		
	}

	@Test(description="This test case will register supplier using SSN and FEIN",enabled=true)
	public void RegisterWithFein() {
		idaho.Differentfintestcase();
		//Assert.assertTrue(idaho.verify());

	}
	@Test(description="This test case will register supplier using SSN and FEIN",enabled=true)
	public void RegisterUsingSsn() {
		idaho.firstssntestcase();
	}
	
	@AfterClass
	public void tearDown() {
		ExtentReport.report.endTest(ExtentReport.logger);
		ExtentReport.report.flush();
		ExtentReport.report.close();
	}
}
