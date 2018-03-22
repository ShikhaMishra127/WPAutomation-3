package buyer.testcases.solicitation;

import java.text.ParseException;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.LogStatus;
import buyer.pageobjects.solicitationPageObjects.CreateSolicitationPOM;
import buyer.pageobjects.solicitationPageObjects.EditSolicitationPageObject;
import buyer.pageobjects.solicitationPageObjects.ReviewAwardPage;
import buyer.pageobjects.solicitationPageObjects.ViewArchivedPage;
import commonutils.pageobjects.generic.HomePage;
import commonutils.pageobjects.generic.LoginPage;
import commonutils.pageobjects.generic.solicitationNavigation;
import commonutils.pageobjects.utils.ExtentReport;
import commonutils.pageobjects.utils.PCDriver;
import commonutils.pageobjects.utils.ReadConfig;

@Listeners(ExtentReport.class)

public class ViewArchived extends PCDriver {
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
		ExtentReport.logger.log(LogStatus.PASS, "Browser Invoked");
		login.setUsername(ReadConfig.getInstance().getUserName().toString());
		ExtentReport.logger.log(LogStatus.PASS, "UserName Entered");
		login.setPassword(ReadConfig.getInstance().getPassword().toString());
		ExtentReport.logger.log(LogStatus.PASS, "Password Entered");
		login.clickOnLogin();
		ExtentReport.logger.log(LogStatus.PASS, "Login Button Clicked");
		home.clickIgnoreOnPopUp();
		ExtentReport.logger.log(LogStatus.PASS, "Ignored the Popup");

	}

	@Test(description = "This test case will search the Sol via Sol Number and reverse the award", enabled = true)
	public void ReverseAwardForInformalSolicitation() {
		ExtentReport.logger.log(LogStatus.INFO, "Test Case Started");

		home.selectTopNavDropDown("Solicitation");
		ExtentReport.logger.log(LogStatus.PASS, "Solicitation menu Clicked");

		sol.informalSolicationsMenu("View Archived");
		ExtentReport.logger.log(LogStatus.PASS, "Informal Solicitation View Archived Clicked");

		// edit.setSolNumber("IFBC18001123");
		// edit.clickOnFilter();
		view.clickThreeDots(0);
		ExtentReport.logger.log(LogStatus.PASS, "Clicked on Three Dots Menu");

		award.ThreeDotsMenu("AwardReversal");
		ExtentReport.logger.log(LogStatus.PASS, "Clicked Award Reversal under Three Dots");

		award.enterReason();
		ExtentReport.logger.log(LogStatus.PASS, "Reason for Reversal Entered");

		award.setConfirmTextOnFinalizePage("Yes");
		ExtentReport.logger.log(LogStatus.PASS, "Yes Text Entered");

		award.clickSubmit();
		ExtentReport.logger.log(LogStatus.PASS, "Submit Button Clicked");

		Assert.assertTrue(edit.verifyReverseBid());
		ExtentReport.logger.log(LogStatus.PASS, "Reverse Bid message is displayed :" + edit.verifyReverseBid());

	}

	@Test(description = "This test case will search the Sol via Sol Number and reverse the award", enabled = true)
	public void ReverseAwardForFormalSolicitation() {
		home.selectTopNavDropDown("Solicitation");
		ExtentReport.logger.log(LogStatus.PASS, "Solicitation menu Clicked");

		sol.formalSolicationsMenu("View Archived");
		ExtentReport.logger.log(LogStatus.PASS, "Formal Solicitation View Archived Clicked");

		// edit.setSolNumber("IFBC18001123");
		// edit.clickOnFilter();
		view.clickThreeDots(0);
		ExtentReport.logger.log(LogStatus.PASS, "Clicked on Three Dots Menu");

		award.ThreeDotsMenu("AwardReversal");

		ExtentReport.logger.log(LogStatus.PASS, "Clicked Award Reversal under Three Dots");

		award.enterReason();
		ExtentReport.logger.log(LogStatus.PASS, "Reason for Reversal Entered");

		award.setConfirmTextOnFinalizePage("Yes");
		ExtentReport.logger.log(LogStatus.PASS, "Yes Text Entered");

		award.clickSubmit();
		ExtentReport.logger.log(LogStatus.PASS, "Submit Button Clicked");

		Assert.assertTrue(edit.verifyReverseBid());
		ExtentReport.logger.log(LogStatus.PASS, "Reverse Bid message is displayed :" + edit.verifyReverseBid());

	}

	@Test(description = "This test case will check the end date filter for finalized Informal Solicitation", enabled = true)
	public void checkEndDateFilterForFinalizedInFormalSol() throws ParseException {
		home.selectTopNavDropDown("Solicitation");
		ExtentReport.logger.log(LogStatus.PASS, "Solicitation menu Clicked");

		sol.formalSolicationsMenu("View Archived");
		ExtentReport.logger.log(LogStatus.PASS, "Informal Solicitation View Archived Clicked");

		edit.setFromEndDate("05/31/2017");
		ExtentReport.logger.log(LogStatus.PASS, "From EndDate Entered");

		edit.setToEndDate("11/09/2017");
		ExtentReport.logger.log(LogStatus.PASS, "To EndDate Entered");

		edit.clickOnFilter();
		ExtentReport.logger.log(LogStatus.PASS, "Clicked on Filter Button");

		Assert.assertTrue(edit.VerifyEndDate());
		ExtentReport.logger.log(LogStatus.PASS,
				"Search Data Verified and the results are with in the date end date range");

	}

	@Test(description = "This test case will check the end date filter for finalized Formal Solicitation", enabled = true)
	public void checkEndDateFilterForFinalizedFormalSol() throws ParseException {
		home.selectTopNavDropDown("Solicitation");
		ExtentReport.logger.log(LogStatus.PASS, "Solicitation menu Clicked");

		sol.formalSolicationsMenu("View Archived");
		ExtentReport.logger.log(LogStatus.PASS, "Formal Solicitation View Archived Clicked");

		edit.setFromEndDate("05/31/2017");
		ExtentReport.logger.log(LogStatus.PASS, "From EndDate Entered");

		edit.setToEndDate("11/09/2017");
		ExtentReport.logger.log(LogStatus.PASS, "To EndDate Entered");

		edit.clickOnFilter();
		ExtentReport.logger.log(LogStatus.PASS, "Clicked on Filter Button");

		Assert.assertTrue(edit.VerifyEndDate());
		ExtentReport.logger.log(LogStatus.PASS,
				"Search Data Verified and the results are with in the date end date range");

	}

	@Test(description = "This test case will check start date filter for Finalized Informal Solicitation", enabled = true)
	public void checkStartDateFilterForFinalizedInFormalSol() {
		home.selectTopNavDropDown("Solicitation");
		ExtentReport.logger.log(LogStatus.PASS, "Solicitation menu Clicked");

		sol.formalSolicationsMenu("View Archived");
		ExtentReport.logger.log(LogStatus.PASS, "Informal Solicitation View Archived Clicked");

		edit.setFromStartDate("11/08/2017");
		ExtentReport.logger.log(LogStatus.PASS, "From StartDate Entered");

		edit.setToStartDate("11/09/2017");
		ExtentReport.logger.log(LogStatus.PASS, "To StartDate Entered");

		edit.clickOnFilter();
		ExtentReport.logger.log(LogStatus.PASS, "Clicked on Filter Button");

		Assert.assertTrue(edit.VerifyStartDate());
		ExtentReport.logger.log(LogStatus.PASS,
				"Search Data Verified and the results are with in the date start date range");

	}

	@Test(description = "This test case will check start date filter for Finalized Formal Solicitation", enabled = true)
	public void checkStartDateFilterForFinalizedFormalSol() {
		home.selectTopNavDropDown("Solicitation");
		ExtentReport.logger.log(LogStatus.PASS, "Solicitation menu Clicked");

		sol.formalSolicationsMenu("View Archived");
		ExtentReport.logger.log(LogStatus.PASS, "Formal Solicitation View Archived Clicked");

		edit.setFromStartDate("11/08/2017");
		ExtentReport.logger.log(LogStatus.PASS, "From StartDate Entered");

		edit.setToStartDate("11/09/2017");
		ExtentReport.logger.log(LogStatus.PASS, "To StartDate Entered");

		edit.clickOnFilter();
		ExtentReport.logger.log(LogStatus.PASS, "Clicked on Filter Button");

		Assert.assertTrue(edit.VerifyStartDate());
		ExtentReport.logger.log(LogStatus.PASS,
				"Search Data Verified and the results are with in the date start date range");

	}

	@Test(description = "This test case will check the end date filter for retracted Formal Solicitation", enabled = true)
	public void checkEndDateFilterForRetractedSolFormalSol() throws ParseException {
		home.selectTopNavDropDown("Solicitation");
		ExtentReport.logger.log(LogStatus.PASS, "Solicitation menu Clicked");

		sol.formalSolicationsMenu("View Archived");
		ExtentReport.logger.log(LogStatus.PASS, "Formal Solicitation View Archived Clicked");

		view.clickRetractedOrCancelleSol();
		ExtentReport.logger.log(LogStatus.PASS, "Clicked on Retracted/Cancelled Solicitation");

		edit.setFromEndDate("05/31/2017");
		ExtentReport.logger.log(LogStatus.PASS, "From EndDate Entered");

		edit.setToEndDate("11/09/2017");
		ExtentReport.logger.log(LogStatus.PASS, "To EndDate Entered");

		edit.clickOnFilter();
		ExtentReport.logger.log(LogStatus.PASS, "Clicked on Filter Button");

		Assert.assertTrue(edit.VerifyEndDate());

	}

	@Test(description = "This test case will check start date filter for retracted Formal Solicitation", enabled = true)
	public void checkStartDateFilterForRetractedSolFormalSol() {
		home.selectTopNavDropDown("Solicitation");
		ExtentReport.logger.log(LogStatus.PASS, "Solicitation menu Clicked");

		sol.formalSolicationsMenu("View Archived");
		ExtentReport.logger.log(LogStatus.PASS, "Formal Solicitation View Archived Clicked");

		view.clickRetractedOrCancelleSol();
		ExtentReport.logger.log(LogStatus.PASS, "Clicked on Retracted/Cancelled Solicitation");

		edit.setFromStartDate("06/01/2017");
		ExtentReport.logger.log(LogStatus.PASS, "From StartDate Entered");

		edit.setToStartDate("11/09/2017");
		ExtentReport.logger.log(LogStatus.PASS, "To StartDate Entered");

		edit.clickOnFilter();
		ExtentReport.logger.log(LogStatus.PASS, "Clicked on Filter Button");

		Assert.assertTrue(edit.VerifyStartDate());
	}

	@Test(description = "This test case will check the end date filter of Finalized Informal Solicitation", enabled = true)
	public void checkEndDateFilterOfFinalizedSolInInformalSol() throws ParseException {
		home.selectTopNavDropDown("Solicitation");
		ExtentReport.logger.log(LogStatus.PASS, "Solicitation menu Clicked");

		sol.informalSolicationsMenu("View Archived");
		ExtentReport.logger.log(LogStatus.PASS, "Informal Solicitation View Archived Clicked");

		view.clickRetractedOrCancelleSol();
		ExtentReport.logger.log(LogStatus.PASS, "Clicked on Retracted/Cancelled Solicitation");

		edit.setFromEndDate("05/31/2017");
		ExtentReport.logger.log(LogStatus.PASS, "From EndDate Entered");

		edit.setToEndDate("11/09/2017");
		ExtentReport.logger.log(LogStatus.PASS, "To EndDate Entered");

		edit.clickOnFilter();
		ExtentReport.logger.log(LogStatus.PASS, "Clicked on Filter Button");

		Assert.assertTrue(edit.VerifyEndDate());

	}

	@Test(description = "This test case will check the start dete fiter for Finalized Informal Solicitation", enabled = true)
	public void checkStartDateFilterOfFinalizedSolInInformalSol() {
		home.selectTopNavDropDown("Solicitation");
		ExtentReport.logger.log(LogStatus.PASS, "Solicitation menu Clicked");

		sol.informalSolicationsMenu("View Archived");
		ExtentReport.logger.log(LogStatus.PASS, "Informal Solicitation View Archived Clicked");

		edit.setFromStartDate("06/27/2017");
		ExtentReport.logger.log(LogStatus.PASS, "From StartDate Entered");

		edit.setToStartDate("11/09/2017");
		ExtentReport.logger.log(LogStatus.PASS, "To StartDate Entered");

		edit.clickOnFilter();
		ExtentReport.logger.log(LogStatus.PASS, "Clicked on Filter Button");

		Assert.assertTrue(edit.VerifyStartDate());
	}

	@Test(description = "This test case will enter the end dates and check the date range of the filter for Retracted Informal Sol", enabled = true)
	public void checkEndDateFilterOfRetractedSolInInformalSol() throws ParseException {
		home.selectTopNavDropDown("Solicitation");
		ExtentReport.logger.log(LogStatus.PASS, "Solicitation menu Clicked");

		sol.informalSolicationsMenu("View Archived");
		ExtentReport.logger.log(LogStatus.PASS, "Informal Solicitation View Archived Clicked");

		view.clickRetractedOrCancelleSol();
		ExtentReport.logger.log(LogStatus.PASS, "Clicked on Retracted/Cancelled Solicitation");

		edit.setFromEndDate("05/31/2017");
		ExtentReport.logger.log(LogStatus.PASS, "From EndDate Entered");

		edit.setToEndDate("11/09/2017");
		ExtentReport.logger.log(LogStatus.PASS, "To EndDate Entered");

		edit.clickOnFilter();
		ExtentReport.logger.log(LogStatus.PASS, "Clicked on Filter Button");

		Assert.assertTrue(edit.VerifyEndDate());

	}

	@Test(description = "This test case will enter the start dates and check the date range of the filter for Retracted Informal Sol", enabled = true)
	public void checkStartDateFilterOfRetractedSolInInformalSol() {
		home.selectTopNavDropDown("Solicitation");
		ExtentReport.logger.log(LogStatus.PASS, "Solicitation menu Clicked");

		sol.informalSolicationsMenu("View Archived");
		ExtentReport.logger.log(LogStatus.PASS, "Informal Solicitation View Archived Clicked");

		view.clickRetractedOrCancelleSol();
		ExtentReport.logger.log(LogStatus.PASS, "Clicked on Retracted/Cancelled Solicitation");

		edit.setFromStartDate("06/27/2017");
		ExtentReport.logger.log(LogStatus.PASS, "From StartDate Entered");

		edit.setToStartDate("11/09/2017");
		ExtentReport.logger.log(LogStatus.PASS, "To StartDate Entered");

		edit.clickOnFilter();
		ExtentReport.logger.log(LogStatus.PASS, "Clicked on Filter Button");

		Assert.assertTrue(edit.VerifyStartDate());
	}

	@AfterMethod
	public void setupAfterTest() {
		createSol.clickHomeButton();
		ExtentReport.logger.log(LogStatus.PASS, "Home Button Clicked");

	}

	@AfterClass
	public void tearDown() {
		ExtentReport.report.endTest(ExtentReport.logger);
		home.logout();

	}
}
