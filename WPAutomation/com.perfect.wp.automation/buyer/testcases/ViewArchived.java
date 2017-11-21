package testcases;

import java.text.ParseException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
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
import pageobjects.solicitationPageObjects.ViewArchivedPage;
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
	ViewArchivedPage view = new ViewArchivedPage();

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

	// @Test(description = "This test case will search the Sol via Sol Number and
	// reverse the award")
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

		@Test
	public void checkEndDateFilterForFinalizedSolInFormalSol() throws ParseException {
		home.selectTopNavDropDown("Solicitation");
		sol.formalSolicationsMenu("View Archived");
		edit.setFromEndDate("05/31/2017");
		edit.setToEndDate("11/09/2017");
		edit.clickOnFilter();
		Assert.assertTrue(edit.VerifyEndDate());

	}

	@Test
	public void checkStartDateFilterForFinalizedSolInFormalSol() {
		home.selectTopNavDropDown("Solicitation");
		sol.formalSolicationsMenu("View Archived");
		edit.setFromStartDate("11/08/2017");
		edit.setToStartDate("11/09/2017");
		edit.clickOnFilter();
		Assert.assertTrue(edit.VerifyStartDate());
	}
	
	 @Test
		public void checkEndDateFilterForRetractedSolInFomalSol() throws ParseException {
			home.selectTopNavDropDown("Solicitation");
			sol.formalSolicationsMenu("View Archived");
			view.clickRetractedOrCancelleSol();

			edit.setFromEndDate("05/31/2017");
			edit.setToEndDate("11/09/2017");
			edit.clickOnFilter();
			Assert.assertTrue(edit.VerifyEndDate());

		}

		@Test
		public void checkStartDateFilterForRetractedSolInFomalSol() {
			home.selectTopNavDropDown("Solicitation");
			sol.formalSolicationsMenu("View Archived");
			view.clickRetractedOrCancelleSol();

			edit.setFromStartDate("11/08/2017");
			edit.setToStartDate("11/09/2017");
			edit.clickOnFilter();
			Assert.assertTrue(edit.VerifyStartDate());
		}
	
	
	 @Test
	public void checkEndDateFilterOfFinalizedSolInInformalSol() throws ParseException {
		home.selectTopNavDropDown("Solicitation");
		sol.informalSolicationsMenu("View Archived");
		view.clickRetractedOrCancelleSol();
		edit.setFromEndDate("05/31/2017");
		edit.setToEndDate("11/09/2017");
		edit.clickOnFilter();
		Assert.assertTrue(edit.VerifyEndDate());

	}

	@Test
	public void checkStartDateFilterOfFinalizedSolInInformalSol() {
		home.selectTopNavDropDown("Solicitation");
		sol.informalSolicationsMenu("View Archived");
		edit.setFromStartDate("06/27/2017");
		edit.setToStartDate("11/09/2017");
		edit.clickOnFilter();
		Assert.assertTrue(edit.VerifyStartDate());
	}
	
		@Test(description="This test case will enter the end dates and check the date range of the filter for Retracted Informal Sol")
		public void checkEndDateFilterOfRetractedSolInInformalSol() throws ParseException {
			home.selectTopNavDropDown("Solicitation");
			sol.informalSolicationsMenu("View Archived");
			view.clickRetractedOrCancelleSol();
			edit.setFromEndDate("05/31/2017");
			edit.setToEndDate("11/09/2017");
			edit.clickOnFilter();
			Assert.assertTrue(edit.VerifyEndDate());

		}

		@Test(description="This test case will enter the start dates and check the date range of the filter for Retracted Informal Sol")
		public void checkStartDateFilterOfRetractedSolInInformalSol() {
			home.selectTopNavDropDown("Solicitation");
			sol.informalSolicationsMenu("View Archived");
			view.clickRetractedOrCancelleSol();
			edit.setFromStartDate("06/27/2017");
			edit.setToStartDate("11/09/2017");
			edit.clickOnFilter();
			Assert.assertTrue(edit.VerifyStartDate());
		}
		
		@AfterMethod
		public void setupAfterTest() {
			createSol.clickHomeButton();

		}
}
