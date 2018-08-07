package buyer.testcases.request;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.LogStatus;
import buyer.pageobjects.requestPageObjects.CreateRequestPOM;
import buyer.pageobjects.requestPageObjects.EditRequestPOM;
import buyer.pageobjects.requestPageObjects.OffCatalogReqPOM;
import buyer.pageobjects.requestPageObjects.RequestNumber;
import buyer.pageobjects.requestPageObjects.RoundTripPOM;
import buyer.pageobjects.requestPageObjects.ViewRequest;
import buyer.pageobjects.solicitationPageObjects.CreateSolicitationPOM;
import commonutils.pageobjects.generic.HomePage;
import commonutils.pageobjects.generic.LoginPage;
import commonutils.pageobjects.generic.RequestNavigation;
import commonutils.pageobjects.utils.ExtentReport;
import commonutils.pageobjects.utils.PCDriver;
import commonutils.pageobjects.utils.ReadConfig;
import commonutils.pageobjects.utils.ReadExcelData;

@Listeners(ExtentReport.class)

public class EditRequest extends PCDriver {

	LoginPage login = new LoginPage();
	CreateSolicitationPOM sol = new CreateSolicitationPOM();
	HomePage home = new HomePage();
	RequestNavigation reqnav = new RequestNavigation();
	CreateRequestPOM req = new CreateRequestPOM();
	OffCatalogReqPOM offcatreq = new OffCatalogReqPOM();
	RoundTripPOM roundtrip = new RoundTripPOM();
	RequestNumber reqnum = new RequestNumber();
	ViewRequest viewreq = new ViewRequest();
	EditRequestPOM editreq = new EditRequestPOM();
	

	@BeforeClass
	public void setup() {
		try {
			ExtentReport.logger = ExtentReport.report.startTest(this.getClass().getSimpleName());
			ExtentReport.logger.log(LogStatus.INFO, "Test Case Started");
			ExtentReport.logger.log(LogStatus.PASS, "Browser Invoked");
			home.cookiesalert();
			login.setUsername(ReadConfig.getInstance().getRequestUsername().toString());
			ExtentReport.logger.log(LogStatus.PASS, "UserName Entered");
			login.setPassword(ReadConfig.getInstance().getRequestPassword().toString());
			ExtentReport.logger.log(LogStatus.PASS, "Password Entered");
			login.clickOnLogin();
			ExtentReport.logger.log(LogStatus.PASS, "Login Button Clicked");
			//home.cookiesalert();
			home.clickIgnoreOnPopUp();
			

		} catch (Exception e) {
			e.printStackTrace();
			e.getMessage();
			Assert.fail();
		}
	}

	@BeforeMethod
	public void setupBeforeTest() {
		//home.movetoSubOrg();
		home.selectTopNavDropDown("Request");
		ExtentReport.logger.log(LogStatus.PASS, "Clicked on Request");

	}
	
	
	@Test(enabled = true, description = "Add item to cart and generate request number" )
	public void additemtocart() throws Exception{
		reqnav.requestdropdown("Create new");
		ExtentReport.logger.log(LogStatus.PASS, "Clicked on Create New");	
		reqnav.typesofreqlist("Off-Catalog Request");
		ExtentReport.logger.log(LogStatus.PASS, "Clicked on Off Catalog Request Tab");
		offcatreq.additemtooffcatreq();
		ExtentReport.logger.log(LogStatus.INFO, "Clicked on Add button");
		ExtentReport.logger.log(LogStatus.PASS, "Item Added to request cart");	
		ReadExcelData.getInstance("EditRequest").updateCellValue("TargetRequest", reqnum.requestname());
		ExtentReport.logger.log(LogStatus.PASS, "Request Number and name generated");
		viewreq.movetoviewreq();
		ExtentReport.logger.log(LogStatus.INFO, "Move to Request Checkout page");
		viewreq.clickclosebtn();
	}
	
	@Test(enabled = true, description = "Edit item quantity" )
	public void editquantity(){
		reqnav.requestdropdown("View All");
		ExtentReport.logger.log(LogStatus.INFO, "Clicked on View");
		editreq.clickonedit();
		editreq.editquantity();
		editreq.updaterequest();
		editreq.assertupdates();
		
	}
	
	@Test(enabled = true, description = "copy items in request")
	public void copyitems(){
		reqnav.requestdropdown("View All");
		ExtentReport.logger.log(LogStatus.INFO, "Clicked on View");
		editreq.clickonedit();
		editreq.copyitems();
		editreq.updaterequest();
	}
	
	
	
	@AfterMethod
	public void tearDownAfterTest() {
		//PCDriver.switchToDefaultContent();
		sol.clickHomeButton();
		ExtentReport.logger.log(LogStatus.PASS, "Clicked on Home Button");

	}	

	//@AfterClass
	public void tearDown() {
		ExtentReport.report.endTest(ExtentReport.logger);
		home.logout();

	}

}
