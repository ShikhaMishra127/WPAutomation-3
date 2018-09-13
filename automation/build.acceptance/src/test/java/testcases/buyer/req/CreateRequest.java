package testcases.buyer.req;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageobjects.common.BuyerNavBarPOM;
import pageobjects.common.LoginPagePOM;
import utilities.common.Browser;

public class CreateRequest {

	public CreateRequest() throws IOException {
		super();
	}

	LoginPagePOM login = new LoginPagePOM();
	BuyerNavBarPOM navbar = new BuyerNavBarPOM();

	@BeforeClass
	public void setup() {
		// before starting our tests, first log into the system as a buyer
		login.setUsername(Browser.buyerUsername);
		login.setPassword(Browser.buyerPassword);
		login.clickOnLogin();
	}
	
	@AfterClass
	public void teardown() {
		//navbar.logout();
		login.close();
	}
	
	@Test
	public void verifyHomePage() {
		Assert.assertTrue(login.getTitle().equalsIgnoreCase("WebProcure: My WebProcure"));
	}
	
	@Test
	public void clickNewReq() {
		navbar.selectTopNavDropDown("Request");
		navbar.requestdropdown("Create new");
		navbar.typesofreqlist("Off-Catalog Request");
		
		//Assert.assertTrue(login.getTitle().equalsIgnoreCase("WebProcure: Request And Workflow"));
	}
}

