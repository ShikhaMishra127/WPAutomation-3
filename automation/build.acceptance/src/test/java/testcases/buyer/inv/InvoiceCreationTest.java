package testcases.buyer.inv;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageobjects.buyer.invoice.InvoicePom;
import pageobjects.buyer.invoice.ViewinvoicePom;
import pageobjects.common.BuyerNavBarPOM;
import pageobjects.common.LoginPagePOM;
import utilities.common.Browser;

import java.util.concurrent.TimeUnit;

@Listeners
public class InvoiceCreationTest {
	private ViewinvoicePom viewinv;
	private InvoicePom invpom;
	private BuyerNavBarPOM navbar;
	private LoginPagePOM login;
	private Browser browser;

	public InvoiceCreationTest() { }

	@BeforeClass
	public void setup(ITestContext testContext) {
		browser = new Browser(testContext);
		viewinv = new ViewinvoicePom(browser);
		invpom = new InvoicePom(browser);
		navbar = new BuyerNavBarPOM(browser);
		login = new LoginPagePOM(browser);
		browser.getDriver().get(browser.baseUrl);
		login.loginAsBuyer();
		browser.waitForPageLoad();
		navbar.selectTopNavDropDown("Invoice");
	}
	
	/********* Invoice Creation happy flow ***********/
	@Test(priority = 1)
	public void invoiceCreation() {
        invpom.invoiceHeader();
        invpom.additem();
		invpom.attachment();
        invpom.match();
        invpom.invoiceSummary();
	}

	/********* Search Invoice by supplier name ***********/
	@Test(priority =2, enabled=true)
	public void suppliersearch() {

		navbar.selectTopNavDropDown("Invoice");
		viewinv.supplierfilter();
		browser.waitForPageLoad();
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
		}
		Assert.assertTrue(viewinv.supassert1().contains("AutoSupplier"));
	}

	/********* Search by Invoice number***********/
	@Test(priority =3, enabled=true)
		public void invoiceNo() {
		navbar.selectTopNavDropDown("Invoice");	
		viewinv.invNo();
			Assert.assertTrue(viewinv.supassert2().contains("AutoSupplier"));
		}
	
	/********* Supplier sorting filter***********/
	@Test(priority =4, enabled=true)

	public void sortSupName()
	{
		navbar.selectTopNavDropDown("Invoice");
		browser.waitForPageLoad();
		viewinv.supcolheader.click();
		browser.waitForPageLoad();
		Assert.assertTrue(viewinv.supassert2().contains("AutoSupplier"));
	}
	/********* Date filter ***********/
	@Test(priority =5, enabled=true)
	public void datesearch()
	{
		navbar.selectTopNavDropDown("Invoice");
		browser.waitForPageLoad();
		viewinv.date();
		browser.waitForPageLoad();
		Assert.assertTrue(viewinv.supassert2().contains("AutoSupplier"));
	}
	/********* Search invoice by buyer invoice number***********/
	@Test(priority =6, enabled=true)
	public void buyerinvoice()
	{
		navbar.selectTopNavDropDown("Invoice");
		browser.waitForPageLoad();
		viewinv.buyInv();
		Assert.assertTrue(viewinv.supassert2().contains("AutoSupplier"));
	}

	@AfterClass
	public void tearDown() {
		browser.close();
	}
}
