package buyer.testcases.invoice;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import buyer.pageobjects.invoice.GenerateInvoice;
import buyer.pageobjects.invoice.VendorInv;
import buyer.pageobjects.invoice.Viewinvoice;
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
public class Vinvoicetest {
	GenerateInvoice voice = new GenerateInvoice();

	// log4jClass log=new log4jClass();
	Viewinvoice viewall = new Viewinvoice();
	LoginPage login = new LoginPage();
	HomePage home = new HomePage();
	CreateSolicitationPOM sol = new CreateSolicitationPOM();
	solicitationNavigation solNav = new solicitationNavigation();
	HeaderPage header = new HeaderPage();
	SummaryPage summary = new SummaryPage();
	VendorInv vinvoice =new VendorInv();

	@BeforeClass
	public void setup() {
		try {
			// log.info("Before Class entered");
			ExtentReport.logger = ExtentReport.report.startTest(this.getClass().getSimpleName());
			ExtentReport.logger.log(LogStatus.INFO, "Test Case Started");
			ExtentReport.logger.log(LogStatus.PASS, "Browser Invoked");
			login.handleCookie();
			login.setUsername(ReadConfig.getInstance().getVendorUserName().toString());
			ExtentReport.logger.log(LogStatus.PASS, "UserName Entered");
			login.setPassword(ReadConfig.getInstance().getVendorPassword().toString());
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
		sol.clickvendorHomeButton();
	}
	@Test(priority = 1, enabled=true)
	public void invoiceFlow()
	{
		PCDriver.waitForPageLoad();
		vinvoice.venInvHead();
		vinvoice.findPO();
		vinvoice.invDetails();
		vinvoice.attach();
		vinvoice.summary();
	}
	@Test(priority = 2, enabled=true)
	public void invcreation()
	{
		PCDriver.waitForPageLoad();
		vinvoice.venInvHead();
		vinvoice.findPO();
		vinvoice.invDetails();
		vinvoice.attach();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
		}
		PCDriver.waitForPageLoad();
		((JavascriptExecutor) PCDriver.getDriver()).executeScript("arguments[0].scrollIntoView(true);",vinvoice.closebtn);
		vinvoice.closebtn.click();
	}
	//@Test(priority = 2, enabled=false)
	public void finalInvoice()
	{
		vinvoice.venInvHead();
		vinvoice.finvoice();
		vinvoice.attach();
		
		Assert.assertTrue(vinvoice.position().contains("Yes"));
		
	}
	@Test(priority = 3, enabled=true)
	public void poRequired()
	{
		vinvoice.venInvHead();
		vinvoice.nxt.click();
		PCDriver.waitForPageLoad();
		System.out.println(voice.getAlert());
		PCDriver.waitForPageLoad();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
		}
		Assert.assertTrue(voice.getAlert().contains("No PO Item selected")); 
		vinvoice.ok.click();
	}
	@Test(priority=4, enabled=true)
	public void enterquantity() {
		vinvoice.venInvHead();
		vinvoice.findPO();
		//voice.zeroquantity();
		PCDriver.waitForElementToBeClickable(vinvoice.nxt);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e1) {
		}
		vinvoice.nxt.click();
		PCDriver.waitForPageLoad();
		System.out.println(voice.getAlert());
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
		}
		Assert.assertTrue(voice.getAlert().contains("Please enter positive numeric value for Invoice Quantity"));
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
		}
		PCDriver.waitForElementToBeClickable(voice.ok);
		voice.ok.click();
		
	}
	@Test(priority=5, enabled=true)
	public void poAssert()
	{
		vinvoice.venInvHead();
		vinvoice.noitemPO();
		PCDriver.waitForPageLoad();
		vinvoice.emptyPO();
		
	}
	@Test(priority=6, enabled=true)
	public void poSummary()
	{
	    vinvoice.invViewHead();
		vinvoice.activeinv();
	}
	
	@Test(priority=7, enabled=true)
	public void invEdit()
	{
	    vinvoice.invViewHead();
		vinvoice.editinvoice();
	}

}
