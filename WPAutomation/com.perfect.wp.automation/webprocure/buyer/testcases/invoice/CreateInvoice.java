package buyer.testcases.invoice;

import org.testng.Assert;
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
			ExtentReport.logger.log(LogStatus.INFO, "Test Case Started");
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
	 @AfterMethod
	 public void setupAfterTest() {
	  sol.clickHomeButton();
	 }

	@Test(priority = 1)
	public void invoiceCreation() {
        
		voice.invoiceHeader();
		voice.additem();
		voice.attachment();
		voice.match();
		voice.invoiceSummary();
	}

	@Test(priority = 2)
	public void receiveDateAlert() {

		voice.receiveDate();
		Assert.assertEquals(voice.getAlert(), "Receive Date must be on or after the Invoice Issue Date");
		voice.okbtn.click();
	}

	@Test(priority = 3)
	public void eftIndicatorAlert() {
		voice.mandatoryEFT();
		Assert.assertTrue(voice.getAlert().contains("Please enter/select data for following required fields:"));
		voice.okbtn.click();
	}

	@Test(priority = 4)
	public void sameInvoiceNo() {
		voice.updateValue();
		Assert.assertTrue(voice.getAlert()
				.contains("The invoice number entered is already in use. Please enter different invoice number"));
		voice.okbtn.click();
	}

	@Test(priority = 5)
	public void attachementAlert() {
		voice.invoiceHeader();
		voice.additem();
		voice.attachementalert();
		Assert.assertTrue(voice.attachAlert().contains("Please select a file"));
	}

	@Test(priority = 6)
	public void matchinvoice() {
		voice.invoiceHeader();
		voice.additem();
		voice.attachment();
		voice.match.click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
		}
		Assert.assertTrue(voice.getAlert().contains("There are no receivers available for matching"));
	}

	@Test(priority = 7)
	public void invoiceNoMissing() {
		voice.invoiceNo();
		Assert.assertTrue(voice.getAlert().contains("Please enter/select data for following required fields:"));
		voice.okbtn.click();
	}

	@Test(priority = 8)
	public void diffSupplier() {
		voice.posearch();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
		}
		Assert.assertTrue(
				voice.getAlert().contains("We found a PO with that number associated to a different supplier"));
	}

	@Test(priority = 9)
	public void changeSupplier() {
		voice.posearch();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
		}
		Assert.assertTrue(
				voice.getAlert().contains("We found a PO with that number associated to a different supplier"));
		voice.yesbtn.click();
		PCDriver.getDriver().switchTo().defaultContent();
		voice.closebtn.click();
		PCDriver.getDriver().switchTo().defaultContent();
		Assert.assertTrue(voice.suppname().contains("Pawn Shop"));
		}
	@Test(priority=10)
	public void enterquantity()
	{
		voice.invoiceHeader();
		voice.zeroquantity();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			}
		Assert.assertTrue(voice.getAlert().contains("Please enter positive numeric value for Invoice Quantity"));
	}
	
}
