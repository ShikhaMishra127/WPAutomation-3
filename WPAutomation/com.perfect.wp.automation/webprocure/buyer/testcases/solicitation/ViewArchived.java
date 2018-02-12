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

	@Test(description = "This test case will search the Sol via Sol Number and reverse the award", enabled = true)
	public void ReverseAwardForInformalSolicitation() {
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

	@Test(description = "This test case will search the Sol via Sol Number and reverse the award", enabled = true)
	public void ReverseAwardForFormalSolicitation() {
		home.selectTopNavDropDown("Solicitation");
		sol.formalSolicationsMenu("View Archived");
		edit.setSolNumber("IFBC18001123");
		edit.clickOnFilter();
		edit.clickOnThreeDots();
		award.ThreeDotsMenu("AwardReversal");
		award.enterReason();
		award.setConfirmTextOnFinalizePage("Yes");
		award.clickSubmit();
		Assert.assertTrue(edit.verifyReverseBid());

	}

	@Test(description = "This test case will check the end date filter for finalized Informal Solicitation", enabled = true)
	public void checkEndDateFilterForFinalizedInFormalSol() throws ParseException {
		home.selectTopNavDropDown("Solicitation");
		sol.formalSolicationsMenu("View Archived");
		edit.setFromEndDate("05/31/2017");
		edit.setToEndDate("11/09/2017");
		edit.clickOnFilter();
		Assert.assertTrue(edit.VerifyEndDate());

	}

	@Test(description = "This test case will check the end date filter for finalized Formal Solicitation", enabled = true)
	public void checkEndDateFilterForFinalizedFormalSol() throws ParseException {
		home.selectTopNavDropDown("Solicitation");
		sol.formalSolicationsMenu("View Archived");
		edit.setFromEndDate("05/31/2017");
		edit.setToEndDate("11/09/2017");
		edit.clickOnFilter();
		Assert.assertTrue(edit.VerifyEndDate());

	}

	@Test(description = "This test case will check start date filter for Finalized Informal Solicitation", enabled = true)
	public void checkStartDateFilterForFinalizedInFormalSol() {
		home.selectTopNavDropDown("Solicitation");
		sol.formalSolicationsMenu("View Archived");
		edit.setFromStartDate("11/08/2017");
		edit.setToStartDate("11/09/2017");
		edit.clickOnFilter();
		Assert.assertTrue(edit.VerifyStartDate());
	}

	@Test(description = "This test case will check start date filter for Finalized Formal Solicitation")
	public void checkStartDateFilterForFinalizedFormalSol() {
		home.selectTopNavDropDown("Solicitation");
		sol.formalSolicationsMenu("View Archived");
		edit.setFromStartDate("11/08/2017");
		edit.setToStartDate("11/09/2017");
		edit.clickOnFilter();
		Assert.assertTrue(edit.VerifyStartDate());
	}

	@Test(description = "This test case will check the end date filter for retracted Informal Solicitation", enabled = true)
	public void checkEndDateFilterForRetractedSolInFomalSol() throws ParseException {
		home.selectTopNavDropDown("Solicitation");
		sol.formalSolicationsMenu("View Archived");
		view.clickRetractedOrCancelleSol();
		edit.setFromEndDate("05/31/2017");
		edit.setToEndDate("11/09/2017");
		edit.clickOnFilter();
		Assert.assertTrue(edit.VerifyEndDate());

	}

	@Test(description = "This test case will check start date filter for retracted informal Solicitation", enabled = true)
	public void checkStartDateFilterForRetractedSolInFomalSol() {
		home.selectTopNavDropDown("Solicitation");
		sol.formalSolicationsMenu("View Archived");
		view.clickRetractedOrCancelleSol();
		edit.setFromStartDate("11/08/2017");
		edit.setToStartDate("11/09/2017");
		edit.clickOnFilter();
		Assert.assertTrue(edit.VerifyStartDate());
	}

	@Test(description = "This test case will check the end date filter of Finalized Informal Solicitation", enabled = true)
	public void checkEndDateFilterOfFinalizedSolInInformalSol() throws ParseException {
		home.selectTopNavDropDown("Solicitation");
		sol.informalSolicationsMenu("View Archived");
		view.clickRetractedOrCancelleSol();
		edit.setFromEndDate("05/31/2017");
		edit.setToEndDate("11/09/2017");
		edit.clickOnFilter();
		Assert.assertTrue(edit.VerifyEndDate());

	}

	@Test(description = "This test case will check the start dete fiter for Finalized Informal Solicitation", enabled = true)
	public void checkStartDateFilterOfFinalizedSolInInformalSol() {
		home.selectTopNavDropDown("Solicitation");
		sol.informalSolicationsMenu("View Archived");
		edit.setFromStartDate("06/27/2017");
		edit.setToStartDate("11/09/2017");
		edit.clickOnFilter();
		Assert.assertTrue(edit.VerifyStartDate());
	}

	@Test(description = "This test case will enter the end dates and check the date range of the filter for Retracted Informal Sol", enabled = true)
	public void checkEndDateFilterOfRetractedSolInInformalSol() throws ParseException {
		home.selectTopNavDropDown("Solicitation");
		sol.informalSolicationsMenu("View Archived");
		view.clickRetractedOrCancelleSol();
		edit.setFromEndDate("05/31/2017");
		edit.setToEndDate("11/09/2017");
		edit.clickOnFilter();
		Assert.assertTrue(edit.VerifyEndDate());

	}

	@Test(description = "This test case will enter the start dates and check the date range of the filter for Retracted Informal Sol", enabled = true)
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

	@AfterClass
	public void tearDown() {
		ExtentReport.report.endTest(ExtentReport.logger);
		ExtentReport.report.flush();
		ExtentReport.report.close();

	}
}
