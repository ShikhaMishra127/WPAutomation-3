package testcases;

import java.awt.AWTException;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.LogStatus;
import pageobjects.generic.HomePage;
import pageobjects.generic.LoginPage;
import pageobjects.generic.solicitationNavigation;
import pageobjects.solicitation.CurrentSolicitationsPage;
import pageobjects.solicitation.VendorHomePage;
import pageobjects.solicitationPageObjects.AttachmentPage;
import pageobjects.solicitationPageObjects.CreateSolicitationPOM;
import pageobjects.solicitationPageObjects.EditSolicitationPageObject;
import pageobjects.solicitationPageObjects.HeaderPage;
import pageobjects.solicitationPageObjects.ReviewAwardPage;
import pageobjects.solicitationPageObjects.SummaryPage;
import pageobjects.utils.ExtentReport;
import pageobjects.utils.ReadConfig;

@Listeners(ExtentReport.class)

public class SolicitationResponse {

	LoginPage login = new LoginPage();
	HomePage home = new HomePage();
	CreateSolicitationPOM sol = new CreateSolicitationPOM();
	EditSolicitationPageObject edit = new EditSolicitationPageObject();
	solicitationNavigation solNav = new solicitationNavigation();
	HeaderPage header = new HeaderPage();
	SummaryPage summary = new SummaryPage();
	VendorHomePage vendorhome = new VendorHomePage();
	CurrentSolicitationsPage currentSol = new CurrentSolicitationsPage();
	ReviewAwardPage review = new ReviewAwardPage();
	AttachmentPage attach = new AttachmentPage();

	@BeforeClass
	public void setup() {
		try {
			ExtentReport.logger = ExtentReport.report.startTest(this.getClass().getSimpleName());
			ExtentReport.logger.log(LogStatus.INFO, "Test Case Started");
			ExtentReport.logger.log(LogStatus.PASS, "Browser Invoked");
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

	@Test(description="This test case will create the response for No Line Item Formal Solicitation")
	public void createResponseForFormalSolicitationForNoLineItem() throws AWTException {
		vendorhome.clickOnSolicitation("Formal");
		currentSol.clickOtherActiveSolicitations();
		edit.setSolNumber("RFPC18001228");
		sol.clickSubmit();
		edit.clickOnThreeDots();
		review.ThreeDotsMenu("editResp");
		currentSol.EnterOverviewDetails();
		edit.clickSave();
		currentSol.EnterRequirementDetails();
		edit.clickSave();
		currentSol.EnterQuestionnaireDetails();
		edit.clickSave();
		currentSol.clickTopNavItem("Attach Documents");
		currentSol.clickAddAttachment();
		attach.uploadNewDocument();
		review.clickDone();
		review.clickSubmit();

	}
	
	@Test(description="This test case will create the response for No Line Item InFormal Solicitation")
	public void createResponseForInformalSolicitationForNoLineItem() throws AWTException {
		vendorhome.clickOnSolicitation("Informal");
		currentSol.clickOtherActiveSolicitations();
		edit.setSolNumber("RFPC18001228");
		sol.clickSubmit();
		edit.clickOnThreeDots();
		review.ThreeDotsMenu("editResp");
		currentSol.EnterOverviewDetails();
		edit.clickSave();
		currentSol.EnterRequirementDetails();
		edit.clickSave();
		currentSol.EnterQuestionnaireDetails();
		edit.clickSave();
		currentSol.clickTopNavItem("Attach Documents");
		currentSol.clickAddAttachment();
		attach.uploadNewDocument();
		review.clickDone();
		review.clickSubmit();

	}

	@Test(description="This test case will create the response for Informal Solicitation")
	public void createResponseForInformalSolicitationForLineItem() throws AWTException {
		vendorhome.clickOnSolicitation("Informal");
		currentSol.clickOtherActiveSolicitations();
		edit.setSolNumber("RFP18001235");
		sol.clickSubmit();
		edit.clickOnThreeDots();
		review.ThreeDotsMenu("editResp");
		currentSol.EnterOverviewDetails();
		edit.clickSave();
		currentSol.EnterRequirementDetails();
		edit.clickSave();
		currentSol.EnterQuestionnaireDetails();
		edit.clickSave();
		currentSol.EnterResponseDetails();
		sol.clickSubmit();
		sol.clickSubmit();

	}
	
	@Test(description="This test case will create the response for Formal Solicitation")
	public void createResponseForFormalSolicitationForLineItem() throws AWTException {
		vendorhome.clickOnSolicitation("Formal");
		currentSol.clickOtherActiveSolicitations();
		edit.setSolNumber("RFP18001209");
		sol.clickSubmit();
		edit.clickOnThreeDots();
		review.ThreeDotsMenu("editResp");
		currentSol.EnterOverviewDetails();
		edit.clickSave();
		currentSol.EnterRequirementDetails();
		edit.clickSave();
		currentSol.EnterQuestionnaireDetails();
		edit.clickSave();
		currentSol.EnterResponseDetails();
		sol.clickSubmit();
		sol.clickSubmit();
	}
	
	//@Test(description="This test case will cancel or retract the response")
	public void retractResponseForInformalSolicitation() {
		vendorhome.clickOnSolicitation("Informal");
		edit.setSolNumber("RFPC18001216");
		sol.clickSubmit();
		edit.clickOnThreeDots();
		review.ThreeDotsMenu("deleteNLIResp");
		Assert.assertTrue(currentSol.verifyRetractResponse());
	}
	
	//@Test(description="This test case will cancel or retract the response")
	public void retractResponseForFormalSolicitation() {
		vendorhome.clickOnSolicitation("Formal");
		edit.setSolNumber("RFPC18001216");
		sol.clickSubmit();
		edit.clickOnThreeDots();
		review.ThreeDotsMenu("deleteNLIResp");
		Assert.assertTrue(currentSol.verifyRetractResponse());
	}
}
