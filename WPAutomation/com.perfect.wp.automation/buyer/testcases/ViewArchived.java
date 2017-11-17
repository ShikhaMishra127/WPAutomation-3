package testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import pageobjects.generic.HomePage;
import pageobjects.generic.LoginPage;
import pageobjects.generic.solicitationNavigation;
import pageobjects.solicitationPageObjects.CreateSolicitationPOM;
import pageobjects.solicitationPageObjects.EditSolicitationPageObject;
import pageobjects.solicitationPageObjects.ReviewAwardPage;
import pageobjects.utils.ExtentReport;
import pageobjects.utils.ReadConfig;

@Listeners(ExtentReport.class)

public class ViewArchived {
	LoginPage login = new LoginPage();
	HomePage home = new HomePage();
	EditSolicitationPageObject edit = new EditSolicitationPageObject();
	CreateSolicitationPOM createSol = new CreateSolicitationPOM();
	solicitationNavigation sol = new solicitationNavigation();
	ReviewAwardPage award = new ReviewAwardPage();

	@BeforeClass
	public void setup() {
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

	}

	@Test(description = "This test case will search the Sol via Sol Number and finalize the award")
	public void ReverseAward() {
		home.selectTopNavDropDown("Solicitation");
		sol.informalSolicationsMenu("View Archived");
		edit.setSolNumber("IFBC18001123");
		edit.clickOnFilter();
		edit.clickOnThreeDots();
		award.ThreeDotsMenu("AwardReversal");
		award.enterReason();
		award.setConfirmTextOnFinalizePage("Yes");
		award.clickSubmit();
		Assert.assertTrue(edit.verifyReverseBid());

	}
}
