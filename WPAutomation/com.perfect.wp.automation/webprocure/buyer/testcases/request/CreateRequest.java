package buyer.testcases.request;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import buyer.pageobjects.requestPageObjects.CreateRequestPOM;
import buyer.pageobjects.requestPageObjects.OffCatalogReqPOM;
import buyer.pageobjects.solicitationPageObjects.CreateSolicitationPOM;
import buyer.pageobjects.requestPageObjects.RequestNumber;
import buyer.pageobjects.requestPageObjects.ViewRequest;
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
	HomePage home = new HomePage();
	RequestNavigation reqnav = new RequestNavigation();
	CreateRequestPOM req = new CreateRequestPOM();
	OffCatalogReqPOM offcatreq = new OffCatalogReqPOM();
	RequestNumber reqnum = new RequestNumber();
	ViewRequest viewreq = new ViewRequest();
	CreateSolicitationPOM sol=new CreateSolicitationPOM();


	@BeforeClass
	public void setup() {
		try {
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
		home.movetoSubOrg();
		home.selectTopNavDropDown("Request");
	}

	// @Test(description="User is on create request page or not")
	public void verifycreatereqpage() {
		reqnav.requestdropdown("Create new");
		Assert.assertTrue(getTitle().contains("WebProcure: Request And Workflow"));
	}

	/*
	 * @Test(description="Goto OffCatalog request tab") public void
	 * verirfyoffcatalogreqtab() throws InterruptedException{
	 * reqnav.typesofreqlist("Off-Catalog Request"); //reqnav.movetoreqframe();
	 * Assert.assertSame(offcatreq.offcatreqpagetitle,"Off-Catalog Request" ); }
	 */

	/*
<<<<<<< Updated upstream:WPAutomation/com.perfect.wp.automation/webprocure/buyer/testcases/request/CreateRequest.java
	 * @Test(description = "This test will field is mandaotry or not") public void
	 * valuefieldismandatory() throws Exception{
	 * reqnav.requestdropdown("Create new");
	 * reqnav.typesofreqlist("Off-Catalog Request");
	 * 
	 * offcatreq.clickAdd(); Assert.assertEquals(offcatreq.bootAlertbox(), "Alert");
	 * offcatreq.acceptalertbox();
=======
	 * @Test(description = "This test will field is mandaotry or not") public
	 * void valuefieldismandatory() throws Exception{ reqnav.requestdropdown(
	 * "Create new"); reqnav.typesofreqlist("Off-Catalog Request");
	 * 
	 * offcatreq.clickAdd(); Assert.assertEquals(offcatreq.bootAlertbox(),
	 * "Alert"); offcatreq.acceptalertbox();
>>>>>>> Stashed changes:WPAutomation/com.perfect.wp.automation/buyer/testcases/request/CreateRequest.java
	 * C:\Users\Sunal\Documents\RequestAttachment\VendorReport_Quote_RFQ18000151.txt
	 * 
	 * }
	 */
	@Test(description = "This test will create Off Catalog Request",enabled = true)
	public void createoffcatreq() throws Exception {
		reqnav.requestdropdown("Create new");
		reqnav.typesofreqlist("Off-Catalog Request");
		offcatreq.additemtooffcatreq();
		ReadExcelData.getInstance("Request").updateCellValue("RequestName", reqnum.requestname());
		// viewreq.attachmenttab(ReadExcelData.getInstance("Attachments").getStringValue("filename"));
		//viewreq.requestsubmission();
		viewreq.movetoviewreq();
		viewreq.attachmenttab(ReadExcelData.getInstance("processreqtabs").getStringValue("filename"));
		viewreq.justificationtab();
		viewreq.buyercontacttab();
		viewreq.assignacctcode();
		viewreq.lineitemattachment(ReadExcelData.getInstance("LineItemAttachment").getStringValue("attachmentname"));
		viewreq.submitrequest();
		viewreq.confirmationpage();
		Assert.assertEquals(viewreq.successfullsubmissionmsg(), "Request successfully submitted.");
	}
	
	@AfterMethod
	public void tearDownAfterTest() {
		sol.clickHomeButton();
	}

	@AfterClass
	public void tearDown() {
		ExtentReport.report.endTest(ExtentReport.logger);
		home.logout();

	}

}
