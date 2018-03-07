package buyer.testcases.registration;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import buyer.pageobjects.registration.AltecRegistrationPagePom;
import commonutils.pageobjects.generic.HomePage;
import commonutils.pageobjects.generic.LoginPage;
import commonutils.pageobjects.utils.ExtentReport;
@Listeners(ExtentReport.class)
public class AltecRegistrationTestCases {
	LoginPage login = new LoginPage();
	AltecRegistrationPagePom altecregister;
	HomePage home=new HomePage();

	@BeforeClass
	public void setup() {
		ExtentReport.logger = ExtentReport.report.startTest(this.getClass().getSimpleName());
		ExtentReport.logger.log(LogStatus.INFO, "Test Case Started");
		ExtentReport.logger.log(LogStatus.PASS, "Browser Invoked");

	}

	@BeforeMethod()
	public void setupBeforeTest() {
		altecregister = new AltecRegistrationPagePom();

	}
	
	@Test
	public void AltecRegistration(){
		
	}
	
	@AfterClass
	public void tearDown(){
		ExtentReport.report.endTest(ExtentReport.logger);
		//home.logout();
	}

}
