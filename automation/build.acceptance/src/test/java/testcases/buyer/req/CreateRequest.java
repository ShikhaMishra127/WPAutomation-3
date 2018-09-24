package testcases.buyer.req;

import java.io.IOException;
import java.util.Locale;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageobjects.buyer.req.OffCatalogReqPOM;
import pageobjects.common.BuyerNavBarPOM;
import pageobjects.common.LoginPagePOM;
import utilities.common.Browser;
import utilities.common.ResourceLoader;

public class CreateRequest {

	public CreateRequest() throws IOException {
		super();
	}


	LoginPagePOM login = new LoginPagePOM();
	BuyerNavBarPOM navbar = new BuyerNavBarPOM();
	OffCatalogReqPOM request = new OffCatalogReqPOM();

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
	public void clickNewReq() {
		navbar.selectTopNavDropDown("Request");
		navbar.requestdropdown("Create new");
		
		Assert.assertTrue(navbar.getTitle().contains("WebProcure: Request And Workflow"));
		
		navbar.typesofreqlist("Off-Catalog Request");
		request.addItemToOffCatReq();
		request.movetoviewreq();
	}

}

