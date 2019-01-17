package testcases.buyer.inv;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import pageobjects.buyer.invoice.InvoicePom;
import pageobjects.buyer.invoice.ViewinvoicePom;
import pageobjects.common.BuyerNavBarPOM;
import pageobjects.common.LoginPagePOM;
import utilities.common.Browser;
//import utilities.common.ExtentReport;
//import utilities.common.HomePagebtn;

@Listeners//(ExtentReport.class)
public class InvoiceCreation {
	Browser browser;
	ViewinvoicePom viewinv;
	InvoicePom invpom;
	BuyerNavBarPOM navbar;
	// log4jClass log=new log4jClass();
	//ViewinvoicePom view = new ViewinvoicePom();
	LoginPagePOM login;
	

	public InvoiceCreation() throws IOException {

	}
	


	@BeforeClass
	public void setup() {
		try {
			browser = new Browser();
			viewinv = new ViewinvoicePom(browser);
			invpom = new InvoicePom(browser);
			navbar = new BuyerNavBarPOM(browser);
			// log4jClass log=new log4jClass();
			//ViewinvoicePom view = new ViewinvoicePom();
			login = new LoginPagePOM(browser);
			// log.info("Before Class entered");
			//ExtentReport.logger = ExtentReport.report.startTest(this.getClass().getSimpleName());
			//ExtentReport.logger.log(LogStatus.INFO, "Test Case Started");
			//ExtentReport.logger.log(LogStatus.PASS, "Browser Invoked");
			browser.getDriver().get(browser.baseUrl);;
			login.loginAsBuyer();
			browser.waitForPageLoad();
			//homerun.clickIgnoreOnPopUp();
			navbar.selectTopNavDropDown("Invoice");
			//ExtentReport.logger.log(LogStatus.PASS, "Login Button Clicked");
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
	@AfterClass
	public void tearDown() {

		//ExtentReport.report.endTest(ExtentReport.logger);

		/*
		 * ExtentReport.report.endTest(ExtentReport.logger);
		 * ExtentReport.report.flush(); ExtentReport.report.close();
		 */
		navbar.logout();
		browser.close();
		
	}
	/********* Invoice Creation happy flow ***********/
	@Test(priority = 1, enabled=true)
	public void invoiceCreation() {
		try {
        invpom.invoiceHeader();
        invpom.additem();
		invpom.attachment();
        invpom.match();
        invpom.invoiceSummary();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test(priority =2, enabled=true)
	public void supsearch() {
		navbar.selectTopNavDropDown("Invoice");
		viewinv.supinv();
		browser.waitForPageLoad();
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
		}
		Assert.assertTrue(viewinv.supassert1().contains("AutoSupplier"));
	}
	@Test(priority =3, enabled=true)
		public void invoiceSearch() {
		navbar.selectTopNavDropDown("Invoice");	
		viewinv.invNo();
			Assert.assertTrue(viewinv.supassert2().contains("AutoSupplier"));
		}
	@Test(priority =4, enabled=true)
	public void sortSupName()
	{
		navbar.selectTopNavDropDown("Invoice");
		browser.waitForPageLoad();
		viewinv.supcolheader.click();
		browser.waitForPageLoad();
		Assert.assertTrue(viewinv.supassert2().contains("AutoSupplier"));
	}
}
