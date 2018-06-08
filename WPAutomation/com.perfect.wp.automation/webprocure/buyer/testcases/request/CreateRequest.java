package buyer.testcases.request;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.LogStatus;
import buyer.pageobjects.requestPageObjects.CreateRequestPOM;
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

public class CreateRequest extends PCDriver {

	LoginPage login = new LoginPage();
	CreateSolicitationPOM sol = new CreateSolicitationPOM();
	HomePage home = new HomePage();
	RequestNavigation reqnav = new RequestNavigation();
	CreateRequestPOM req = new CreateRequestPOM();
	OffCatalogReqPOM offcatreq = new OffCatalogReqPOM();
	RoundTripPOM roundtrip = new RoundTripPOM();
	RequestNumber reqnum = new RequestNumber();
	ViewRequest viewreq = new ViewRequest();

	@BeforeClass
	public void setup() {
		try {
			ExtentReport.logger = ExtentReport.report.startTest(this.getClass().getSimpleName());
			ExtentReport.logger.log(LogStatus.INFO, "Test Case Started");
			ExtentReport.logger.log(LogStatus.PASS, "Browser Invoked");
			login.setUsername(ReadConfig.getInstance().getRequestUsername().toString());
			ExtentReport.logger.log(LogStatus.PASS, "UserName Entered");
			login.setPassword(ReadConfig.getInstance().getRequestPassword().toString());
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
		//home.movetoSubOrg();
		home.selectTopNavDropDown("Request");
		ExtentReport.logger.log(LogStatus.PASS, "Clicked on Request");

	}

	// @Test(description="User is on create request page or not")
	public void verifycreatereqpage() {
		reqnav.requestdropdown("Create new");
		Assert.assertTrue(getTitle().contains("WebProcure: Request And Workflow"));
	}
	
	@Test(priority=1, enabled = true, description = "This test will check quantity field is mandatory")
	public void quantityismandatory() throws Exception{
		
		reqnav.requestdropdown("Create new");
		ExtentReport.logger.log(LogStatus.PASS, "Clicked on Create New");	
		reqnav.typesofreqlist("Off-Catalog Request");
		ExtentReport.logger.log(LogStatus.PASS, "Clicked on Off Catalog Request Tab");
		offcatreq.donotenterquantity();
		Assert.assertEquals(offcatreq.bootalertmessage(),"The value in the Quantity field is an invalid input. Please enter a number that is of 15 or fewer digits.");
		ExtentReport.logger.log(LogStatus.PASS, "Quantity Alert successfully generated");
		offcatreq.acceptalertbox();
	
	}
	
	@Test(priority=2, enabled = true, description = "This test will check unit price field is mandatory")
	public void unitpriceismandatory() throws Exception{
		
			reqnav.requestdropdown("Create new");
			ExtentReport.logger.log(LogStatus.PASS, "Clicked on Create New");	
			reqnav.typesofreqlist("Off-Catalog Request");
			ExtentReport.logger.log(LogStatus.PASS, "Clicked on Off Catalog Request Tab");
			offcatreq.donotenterunitprice();
			Assert.assertEquals(offcatreq.bootalertmessage(),"Estimated Unit Price is a required field");
			ExtentReport.logger.log(LogStatus.PASS, "Unit price Alert successfully generated");
			offcatreq.acceptalertbox();	
			
	}
	
	@Test(priority=3, enabled = true, description = "This test will commodity code field is mandatory")
	public void commodityismandatory() throws Exception{
		
		reqnav.requestdropdown("Create new");
		ExtentReport.logger.log(LogStatus.PASS, "Clicked on Create New");	
		reqnav.typesofreqlist("Off-Catalog Request");
		ExtentReport.logger.log(LogStatus.PASS, "Clicked on Off Catalog Request Tab");
		offcatreq.donotentercommoditycode();
		if(PCDriver.getDriver() instanceof PhantomJSDriver){
			((JavascriptExecutor)PCDriver.getDriver()).executeScript("window.confirm = function(msg){return true;}");
		}else{
		System.out.println(PCDriver.driver.switchTo().alert().getText());
		Assert.assertEquals(PCDriver.getDriver().switchTo().alert().getText(),"You have not assigned a commodity code for this item.You can not continue without assigning a commodity code.");
		PCDriver.acceptAlert();
		}
	}

	@Test(priority=4, enabled = true, description = "This test will create a Off Catalog Request")
	public void createoffcatreq() throws Exception {
		
		reqnav.requestdropdown("Create new");
		ExtentReport.logger.log(LogStatus.PASS, "Clicked on Create New");	
		reqnav.typesofreqlist("Off-Catalog Request");
		ExtentReport.logger.log(LogStatus.PASS, "Clicked on Off Catalog Request Tab");
		offcatreq.additemtooffcatreq();
		ExtentReport.logger.log(LogStatus.INFO, "Clicked on Add button");
		ExtentReport.logger.log(LogStatus.PASS, "Item Added to request cart");
		ReadExcelData.getInstance("Request").updateCellValue("RequestName", reqnum.requestname());
		ExtentReport.logger.log(LogStatus.PASS, "Request Number and name generated");
		viewreq.movetoviewreq();
		ExtentReport.logger.log(LogStatus.INFO, "Move to Request Checkout page");
		viewreq.attachmenttab(ReadExcelData.getInstance("processreqtabs").getStringValue("filename"));
		ExtentReport.logger.log(LogStatus.INFO, "Attachment added at Requisition level");
		viewreq.justificationtab();
		ExtentReport.logger.log(LogStatus.INFO, "Request Justification entered");
		viewreq.buyercontacttab();
		ExtentReport.logger.log(LogStatus.INFO, "Buyer Contact Selected");
		viewreq.assignacctcode();
		ExtentReport.logger.log(LogStatus.INFO, "Account Code Assigned");
		viewreq.lineitemattachment(ReadExcelData.getInstance("LineItemAttachment").getStringValue("attachmentname"));
		ExtentReport.logger.log(LogStatus.INFO, "Line item attachment attached");
		viewreq.submitrequest();
		ExtentReport.logger.log(LogStatus.INFO, "Clicked on Submit button");
		viewreq.confirmationpage();
		ExtentReport.logger.log(LogStatus.INFO, "Clicked on confirmation page submit button");
		Assert.assertEquals(viewreq.successfullsubmissionmsg(), "Request successfully submitted.");
		ExtentReport.logger.log(LogStatus.PASS, "Request Successfully Submitted");	
	}

	
	@Test(priority=5, enabled=true, description = "This test will create a roundtrip Request")
	public void roundtripreq() throws Exception {
		
		reqnav.requestdropdown("Create new");
		ExtentReport.logger.log(LogStatus.PASS, "Clicked on Create New");
		reqnav.typesofreqlist("RoundTrip");
		ExtentReport.logger.log(LogStatus.PASS, "Clicked on Off Catalog Request Tab");
		roundtrip.selecttargetsupplier();
		ExtentReport.logger.log(LogStatus.PASS, "Target Supplier selected and clicked");
		roundtrip.addroundtripitemtocart();
		ExtentReport.logger.log(LogStatus.PASS, "add item to cart");
		ReadExcelData.getInstance("Request").updateCellValue("RequestName", reqnum.requestname());
		ExtentReport.logger.log(LogStatus.PASS, "Request Number and name generated");
		//viewreq.movetoviewreq();
		//ExtentReport.logger.log(LogStatus.INFO, "Move to Request Checkout page");
		viewreq.attachmenttab(ReadExcelData.getInstance("processreqtabs").getStringValue("filename"));
		ExtentReport.logger.log(LogStatus.INFO, "Attachment added at Requisition level");
		viewreq.justificationtab();
		ExtentReport.logger.log(LogStatus.INFO, "Request Justification entered");
		viewreq.buyercontacttab();
		ExtentReport.logger.log(LogStatus.INFO, "Buyer Contact Selected");
		viewreq.assignacctcode();
		ExtentReport.logger.log(LogStatus.INFO, "Account Code Assigned");
		viewreq.lineitemattachment(ReadExcelData.getInstance("LineItemAttachment").getStringValue("attachmentname"));
		ExtentReport.logger.log(LogStatus.INFO, "Line item attachment attached");
		viewreq.submitrequest();
		ExtentReport.logger.log(LogStatus.INFO, "Clicked on Submit button");
		viewreq.confirmationpage();
		ExtentReport.logger.log(LogStatus.INFO, "Clicked on confirmation page submit button");
		Assert.assertEquals(viewreq.successfullsubmissionmsg(), "Request successfully submitted.");
		ExtentReport.logger.log(LogStatus.PASS, "Request Successfully Submitted");

	}
	
	@Test(priority=6, enabled=true, description = "This test will create multityperreq")
	public void multitypereq() throws Exception {
		
		reqnav.requestdropdown("Create new");
		ExtentReport.logger.log(LogStatus.PASS, "Clicked on Create New");
		reqnav.typesofreqlist("Off-Catalog Request");
		ExtentReport.logger.log(LogStatus.PASS, "Clicked on Off Catalog Request Tab");
		offcatreq.additemtooffcatreq();
		ExtentReport.logger.log(LogStatus.INFO, "Clicked on Add button");
		ExtentReport.logger.log(LogStatus.PASS, "Item Added to request cart");
		
		reqnav.typesofreqlist("RoundTrip");
		ExtentReport.logger.log(LogStatus.PASS, "Clicked on Off Catalog Request Tab");
		roundtrip.selecttargetsupplier();
		ExtentReport.logger.log(LogStatus.PASS, "Target Supplier selected and clicked");
		roundtrip.addroundtripitemtocart();
		ExtentReport.logger.log(LogStatus.PASS, "rountrip item added to cart");
		
		ReadExcelData.getInstance("Request").updateCellValue("RequestName", reqnum.requestname());
		ExtentReport.logger.log(LogStatus.PASS, "Request Number and name generated");
		//viewreq.movetoviewreq();
		//ExtentReport.logger.log(LogStatus.INFO, "Move to Request Checkout page");
		viewreq.attachmenttab(ReadExcelData.getInstance("processreqtabs").getStringValue("filename"));
		ExtentReport.logger.log(LogStatus.INFO, "Attachment added at Requisition level");
		viewreq.justificationtab();
		ExtentReport.logger.log(LogStatus.INFO, "Request Justification entered");
		viewreq.buyercontacttab();
		ExtentReport.logger.log(LogStatus.INFO, "Buyer Contact Selected");
		viewreq.assignacctcode();
		ExtentReport.logger.log(LogStatus.INFO, "Account Code Assigned");
		viewreq.lineitemattachment(ReadExcelData.getInstance("LineItemAttachment").getStringValue("attachmentname"));
		ExtentReport.logger.log(LogStatus.INFO, "Line item attachment attached");
		viewreq.submitrequest();
		ExtentReport.logger.log(LogStatus.INFO, "Clicked on Submit button");
		viewreq.confirmationpage();
		ExtentReport.logger.log(LogStatus.INFO, "Clicked on confirmation page submit button");
		Assert.assertEquals(viewreq.successfullsubmissionmsg(), "Request successfully submitted.");
		ExtentReport.logger.log(LogStatus.PASS, "Request Successfully Submitted");

	}
	@Test(enabled=true, description = "This test will check empty req checkout page")
	public void emptyreqchecoutpage() throws Exception{
		reqnav.requestdropdown("Create new");
		ExtentReport.logger.log(LogStatus.PASS, "Clicked on Create New");
		reqnav.typesofreqlist("View Request");
		ExtentReport.logger.log(LogStatus.PASS, "Clicked on View Request Tab");
		Assert.assertEquals(viewreq.blankreqcart(), "Request contains no line items.");
		
	}

	@AfterMethod
	public void tearDownAfterTest() {
		//PCDriver.switchToDefaultContent();
		sol.clickHomeButton();
		ExtentReport.logger.log(LogStatus.PASS, "Clicked on Home Button");

	}	

	@AfterClass
	public void tearDown() {
		ExtentReport.report.endTest(ExtentReport.logger);
		home.logout();

	}
	
}
