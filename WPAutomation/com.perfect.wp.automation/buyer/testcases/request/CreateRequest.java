package testcases.request;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.LogStatus;
import pageobjects.generic.HomePage;
import pageobjects.generic.LoginPage;
import pageobjects.generic.RequestNavigation;
import pageobjects.requestPageObjects.CreateRequestPOM;
import pageobjects.requestPageObjects.OffCatalogReqPOM;
import pageobjects.requestPageObjects.RequestNumber;
import pageobjects.requestPageObjects.ViewRequest;
import pageobjects.utils.ExtentReport;
import pageobjects.utils.PCDriver;
import pageobjects.utils.ReadConfig;
import pageobjects.utils.ReadExcelData;

@Listeners(ExtentReport.class)
		
public class CreateRequest extends PCDriver{

	LoginPage login = new LoginPage();
	HomePage home = new HomePage();
	RequestNavigation reqnav = new RequestNavigation();
	CreateRequestPOM req = new CreateRequestPOM();
	OffCatalogReqPOM offcatreq = new OffCatalogReqPOM();
	RequestNumber reqnum = new RequestNumber();
	ViewRequest viewreq = new ViewRequest();
	
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
	
	//@Test(description="User is on create request page or not")
	public void verifycreatereqpage(){
		reqnav.requestdropdown("Create new");
		Assert.assertTrue(getTitle().contains("WebProcure: Request And Workflow"));
	}
		
	/*@Test(description="Goto OffCatalog request tab")
	public void verirfyoffcatalogreqtab() throws InterruptedException{
		reqnav.typesofreqlist("Off-Catalog Request");
		//reqnav.movetoreqframe();
		Assert.assertSame(offcatreq.offcatreqpagetitle,"Off-Catalog Request" );
	}
	*/
	
	/*@Test(description = "This test will field is mandaotry or not")
	public void valuefieldismandatory() throws Exception{
		reqnav.requestdropdown("Create new");
		reqnav.typesofreqlist("Off-Catalog Request");
		
		offcatreq.clickAdd();
		Assert.assertEquals(offcatreq.bootAlertbox(), "Alert");
		offcatreq.acceptalertbox();
		
	}
	*/
	@Test(description="This test will create Off Catalog Request")
	public void createoffcatreq() throws Exception{
		reqnav.requestdropdown("Create new");
		reqnav.typesofreqlist("Off-Catalog Request");
		offcatreq.additemtooffcatreq();
		ReadExcelData.getInstance("Request").updateCellValue("RequestName",reqnum.requestname());
		viewreq.requestsubmission();
	}
	
}
