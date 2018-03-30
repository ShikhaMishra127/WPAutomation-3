package buyer.testcases.invoice;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import buyer.pageobjects.invoice.GenerateInvoice;
import buyer.pageobjects.solicitationPageObjects.CreateSolicitationPOM;
import buyer.pageobjects.solicitationPageObjects.HeaderPage;
import buyer.pageobjects.solicitationPageObjects.SummaryPage;
import commonutils.pageobjects.generic.HomePage;
import commonutils.pageobjects.generic.LoginPage;
import commonutils.pageobjects.generic.solicitationNavigation;
import commonutils.pageobjects.utils.ExtentReport;
import commonutils.pageobjects.utils.PCDriver;
import commonutils.pageobjects.utils.ReadConfig;

@Listeners(ExtentReport.class)
public class CreateInvoice extends PCDriver {
	GenerateInvoice voice = new GenerateInvoice();

	// log4jClass log=new log4jClass();
	LoginPage login = new LoginPage();
	HomePage home = new HomePage();
	CreateSolicitationPOM sol = new CreateSolicitationPOM();
	solicitationNavigation solNav = new solicitationNavigation();
	HeaderPage header = new HeaderPage();
	SummaryPage summary = new SummaryPage();

	@BeforeClass
	public void setup() {
		try {
			// log.info("Before Class entered");
			ExtentReport.logger = ExtentReport.report.startTest(this.getClass().getSimpleName());
			ExtentReport.logger.log(LogStatus.PASS, "Browser Invoked");
			login.setUsername(ReadConfig.getInstance().getUserName().toString());
			ExtentReport.logger.log(LogStatus.PASS, "UserName Entered");
			login.setPassword(ReadConfig.getInstance().getPassword().toString());
			ExtentReport.logger.log(LogStatus.PASS, "Password Entered");
			login.clickOnLogin();
			ExtentReport.logger.log(LogStatus.PASS, "Login Button Clicked");
			home.clickIgnoreOnPopUp();

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

	@BeforeMethod
	public void setupBeforeTest() {
		home.selectTopNavDropDown("Invoice");
	}

	@Test(description = "This test case will create the Invoice", enabled = true)
	public void createInvoice() {
		ExtentReport.logger.log(LogStatus.INFO, "Test Case Started");

		voice.invoiceHeader();
		ExtentReport.logger.log(LogStatus.PASS, "Header Details Entered");

		voice.additem();
		ExtentReport.logger.log(LogStatus.PASS, "Items are added");

		voice.attachment();
		ExtentReport.logger.log(LogStatus.PASS, "Attachment Added");

		voice.match();
		ExtentReport.logger.log(LogStatus.PASS, "Item Matched");

		voice.invoiceSummary();
		ExtentReport.logger.log(LogStatus.PASS, "Invoice Summary Displayed");

		voice.verifyPageAfterInvoiceSubmission();
		ExtentReport.logger.log(LogStatus.PASS, "Invoice Submission Verified");

	}

	@AfterMethod
	public void tearDownAfterTest() {
		sol.clickHomeButton();
		ExtentReport.logger.log(LogStatus.PASS, "Clicked on Home Button");

	}

	@AfterClass
	public void tearDown() {
		ExtentReport.report.endTest(ExtentReport.logger);
		ExtentReport.report.flush();

	}
}
