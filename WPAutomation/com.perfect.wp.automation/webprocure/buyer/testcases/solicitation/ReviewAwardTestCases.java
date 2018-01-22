package buyer.testcases.solicitation;

import java.io.IOException;
import java.text.ParseException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import buyer.pageobjects.solicitationPageObjects.CreateSolicitationPOM;
import buyer.pageobjects.solicitationPageObjects.EditSolicitationPageObject;
import buyer.pageobjects.solicitationPageObjects.ReviewAwardPage;
import commonutils.pageobjects.generic.HomePage;
import commonutils.pageobjects.generic.LoginPage;
import commonutils.pageobjects.generic.solicitationNavigation;
import commonutils.pageobjects.utils.ExtentReport;
import commonutils.pageobjects.utils.PCDriver;
import commonutils.pageobjects.utils.ReadConfig;

@Listeners(ExtentReport.class)

public class ReviewAwardTestCases extends PCDriver {

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

	// @Test(description="This test case will check the end date filter and verify
	// the search results are correct for Ended Sols in Formal Solicitation")
	public void checkEndDateFilterForFormalEndedSol() throws ParseException {
		home.selectTopNavDropDown("Solicitation");
		sol.formalSolicationsMenu("Review / Award");
		edit.setFromEndDate("11/07/2017");
		edit.setToEndDate("11/09/2017");
		edit.clickOnFilter();
		Assert.assertTrue(award.verifyData());
	}

	// @Test(description="This test case will check the end date filter and verify
	// the search results are correct for Active Sols in InFormal Solicitation")
	public void checkEndDateFilterForInformalEndedSol() throws ParseException {
		home.selectTopNavDropDown("Solicitation");
		sol.informalSolicationsMenu("Review / Award");
		edit.setFromEndDate("11/07/2017");
		edit.setToEndDate("11/09/2017");
		edit.clickOnFilter();
		Assert.assertTrue(award.verifyData());
	}

	// @Test(description="This test case will check the end date filter and verify
	// the search results are correct for Ended Sols in Formal Solicitation")
	public void checkEndDateFilterForFormalActiveSol() throws ParseException {
		home.selectTopNavDropDown("Solicitation");
		sol.formalSolicationsMenu("Review / Award");
		edit.clickOnActiveSolicitations();
		edit.setFromEndDate("11/07/2017");
		edit.setToEndDate("11/09/2017");
		edit.clickOnFilter();
		Assert.assertTrue(award.verifyData());
	}

	// @Test(description="This test case will check the end date filter and verify
	// the search results are correct for Active Sols in InFormal Solicitation")
	public void checkEndDateFilterForInformalActiveSol() throws ParseException {
		home.selectTopNavDropDown("Solicitation");
		sol.informalSolicationsMenu("Review / Award");
		edit.clickOnActiveSolicitations();
		edit.setFromEndDate("11/07/2017");
		edit.setToEndDate("11/09/2017");
		edit.clickOnFilter();
		Assert.assertTrue(award.verifyData());
	}

	// @Test
	public void checkVendorResponse() throws ParseException {
		home.selectTopNavDropDown("Solicitation");
		sol.formalSolicationsMenu("Review / Award");
		edit.setFromEndDate("11/07/2017");
		edit.setToEndDate("11/09/2017");
		edit.clickOnFilter();
		Assert.assertTrue(award.verifyData());
		edit.clickOnThreeDots();
		award.ThreeDotsMenu("AwardStyle");
		award.clickOnPaperResponse();
		award.clickContinue();
		award.searchSupplier("a*");
		award.clickSupplierAndContinue();
	}

	@Test
	public void awardFormalSolicitation() throws ParseException {
		home.selectTopNavDropDown("Solicitation");
		sol.formalSolicationsMenu("Review / Award");
		edit.setFromEndDate("11/10/2017");
		edit.setToEndDate("11/12/2017");
		edit.clickOnFilter();
		Assert.assertTrue(award.verifyData());
		edit.clickOnThreeDots();
		award.ThreeDotsMenu("AwardStyle");
		award.clickOnAwardByItem();
		award.clickContinue();
		award.selectLineItemAndEnterQuantity("10");
		edit.clickSave();
		award.clickDone();
	}

	// @Test
	public void awardInformalSolicitationForActiveSolicitation() throws ParseException {
		home.selectTopNavDropDown("Solicitation");
		sol.informalSolicationsMenu("Review / Award");
		edit.clickOnActiveSolicitations();
		edit.setFromEndDate("11/18/2017");
		edit.setToEndDate("11/18/2017");
		edit.clickOnFilter();
		Assert.assertTrue(award.verifyData());
		award.ThreeDotsMenu("AwardStyle");
		award.clickAwardSummaryPopUp();
		award.clickOnAwardByItem();
		award.clickContinue();
		award.selectLineItemAndEnterQuantity("10");
		edit.clickSave();
		award.clickDone();
	}

	// @Test
	public void awardInformalSolicitationForEndedSolicitation() {
		home.selectTopNavDropDown("Solicitation");
		sol.informalSolicationsMenu("Review / Award");

	}

	// @Test
	public void awardByGroupForInformalSolicitaton() throws IOException {
		home.selectTopNavDropDown("Solicitation");
		sol.informalSolicationsMenu("Review / Award");
		edit.clickOnActiveSolicitations();
		edit.setSolNumber("IFBC18001116");
		edit.clickOnFilter();
		award.ThreeDotsMenu("AwardStyle");
		award.clickAwardSummaryPopUp();
		award.clickAwardByGroup();
		award.clickContinue();
		award.clickGroupsAndAddItems();
	}

	// @Test
	public void VerifyCheckBoxForawardAllToOneForInformalSolicitation() {

		home.selectTopNavDropDown("Solicitation");
		sol.informalSolicationsMenu("Review / Award");
		edit.clickOnActiveSolicitations();
		edit.setSolNumber("IFBC18001106");
		edit.clickOnFilter();
		award.ThreeDotsMenu("AwardStyle");

		award.clickAwardSummaryPopUp();
		award.clickAwardAllToOneForBid();
		award.clickContinue();
		award.clickOnToggleBox();
		Assert.assertEquals(award.verifyCheckBox(), true);
	}

	// @Test
	public void VerifyCancelButtonForawardAllToOneForInformalSolicitation() {

		home.selectTopNavDropDown("Solicitation");
		sol.informalSolicationsMenu("Review / Award");
		edit.clickOnActiveSolicitations();
		edit.setSolNumber("IFBC18001106");
		edit.clickOnFilter();
		award.ThreeDotsMenu("AwardStyle");
		award.clickAwardSummaryPopUp();
		award.clickAwardAllToOneForBid();
		award.clickContinue();
		award.checkLineItem();
		award.clickCancel();
		Assert.assertEquals(award.verifyAwardAllToOnePage(), true);

	}

	// @Test
	public void awardAllToOneForInformalSolicitation() {

		home.selectTopNavDropDown("Solicitation");
		sol.informalSolicationsMenu("Review / Award");
		edit.clickOnActiveSolicitations();
		edit.setSolNumber("IFBC18001106");
		edit.clickOnFilter();
		edit.clickOnThreeDots();
		award.ThreeDotsMenu("AwardStyle");
		award.clickAwardSummaryPopUp();
		award.clickAwardAllToOneForBid();
		award.clickContinue();
		award.checkLineItem();
		award.clickContinue();
		award.clickDone();
		// award.clickCancel();
		// Assert.assertEquals(award.verifyAwardAllToOnePage(), true);

	}

	// @Test
	public void VerifyCloseForAwardAllToOneForInformalSolicitation() {

		home.selectTopNavDropDown("Solicitation");
		sol.informalSolicationsMenu("Review / Award");
		edit.clickOnActiveSolicitations();
		edit.setSolNumber("IFBC18001116");
		edit.clickOnFilter();
		edit.clickOnThreeDots();
		award.ThreeDotsMenu("AwardStyle");
		award.clickAwardSummaryPopUp();
		award.clickAwardAllToOneForBid();
		award.clickContinue();
		award.clickClose();
		Assert.assertEquals(award.verifyEvaluateAndAwardPage(), true);
	}

	// @Test
	public void CreateNewRound() throws Exception {
		home.selectTopNavDropDown("Solicitation");
		sol.informalSolicationsMenu("Review / Award");
		edit.setTitleForSearch("QA Automation1509714194973");
		edit.clickOnFilter();
		edit.clickOnThreeDots();
		award.ThreeDotsMenu("createNewRound");
		award.selectSuppliersOnPopup();
		award.clickSubmit();
		award.enterReason();
		edit.clickTopNavItem("Edit Header");
		edit.clickSave();
		edit.clickReturn();
		award.clickSubmit();
		Assert.assertTrue(edit.verifyEditedSolicitationSubmission());
	}

	// @Test(description="This test case will create new round from Award All To One
	// For Bid Page")
	public void createNewRoundFromAwardAllToOneForBidPage() {
		home.selectTopNavDropDown("Solicitation");
		sol.informalSolicationsMenu("Review / Award");
		edit.setSolNumber("IFBC18001123");
		edit.clickOnFilter();
		edit.clickOnThreeDots();
		award.ThreeDotsMenu("AwardStyle");
		award.clickAwardSummaryPopUp();
		award.clickAwardAllToOneForBid();
		award.clickContinue();
		award.clickCreateNewRoundButton();
		award.selectSuppliersOnPopup();
		award.clickSubmit();
		award.enterReason();
		edit.clickTopNavItem("Edit Header");
		edit.clickSave();
		edit.clickReturn();
		award.clickSubmit();
		Assert.assertTrue(edit.verifyEditedSolicitationSubmission());
	}

	// @Test(description = "This test case will create new round from Award All To
	// One For Bid Page")
	public void createNewRoundFromAwardByGroupPage() {
		home.selectTopNavDropDown("Solicitation");
		sol.informalSolicationsMenu("Review / Award");
		edit.setSolNumber("IFBC18001123");
		edit.clickOnFilter();
		edit.clickOnThreeDots();
		award.ThreeDotsMenu("AwardStyle");
		award.clickAwardSummaryPopUp();
		award.clickAwardByGroup();
		award.clickContinue();
		award.clickCreateNewRoundButton();
		award.selectSuppliersOnPopup();
		award.clickSubmit();
		award.enterReason();
		edit.clickTopNavItem("Edit Header");
		edit.clickSave();
		edit.clickReturn();
		award.clickSubmit();
		Assert.assertTrue(edit.verifyEditedSolicitationSubmission());
	}

	// @Test(description = "This test case will search the Sol via Sol Number and
	// finalize the award")
	public void FinalizeAwardForInformalSolicitation() {
		home.selectTopNavDropDown("Solicitation");
		sol.informalSolicationsMenu("Review / Award");
		edit.setSolNumber("IFBC18001123");
		edit.clickOnFilter();
		edit.clickOnThreeDots();
		award.ThreeDotsMenu("Finalize");
		award.setConfirmTextOnFinalizePage("Yes");
		award.clickSubmit();
		Assert.assertTrue(edit.verifyFinalizeBid());

	}

	// @Test(description = "This test case will search the Sol via Sol Number and
	// finalize the award")
	public void FinalizeAwardForFormalSolicitation() {
		home.selectTopNavDropDown("Solicitation");
		sol.formalSolicationsMenu("Review / Award");
		edit.setSolNumber("IFBC18001123");
		edit.clickOnFilter();
		edit.clickOnThreeDots();
		award.ThreeDotsMenu("Finalize");
		award.setConfirmTextOnFinalizePage("Yes");
		award.clickSubmit();
		Assert.assertTrue(edit.verifyFinalizeBid());

	}

	// @Test(description = "This test case will search the Sol via Sol Number and
	// finalize the award")
	public void ReverseAward() {
		home.selectTopNavDropDown("Solicitation");
		sol.informalSolicationsMenu("Review / Award");
		edit.setSolNumber("IFBC18001123");
		edit.clickOnFilter();
		edit.clickOnThreeDots();
		award.ThreeDotsMenu("Finalize");
		award.setConfirmTextOnFinalizePage("Yes");
		award.clickSubmit();
		Assert.assertTrue(edit.verifyFinalizeBid());

	}

	// @Test(description="This test case will copy the informal Solicitation")
	public void CopySolicitationForInformalSol() {
		home.selectTopNavDropDown("Solicitation");
		sol.informalSolicationsMenu("Review / Award");
		edit.setSolNumber("IFBC18001123");
		edit.clickOnFilter();
		edit.clickOnThreeDots();
		award.ThreeDotsMenu("QuoteCreate");
		createSol.clickOnNextStep();
		createSol.clickOnNextStep();
		createSol.clickOnNextStep();
		createSol.clickOnNextStep();
		createSol.clickOnNextStep();
		createSol.clickOnNextStep();
		createSol.clickOnNextStep();
		createSol.clickSubmit();
		Assert.assertEquals(createSol.verifySuccessMessage(), "This solicitation has been submitted");

	}

	// @Test(description="This test case will copy the formal solicitation")
	public void CopySolicitationForFormalSol() {
		home.selectTopNavDropDown("Solicitation");
		sol.formalSolicationsMenu("Review / Award");
		edit.setSolNumber("IFBC18001123");
		edit.clickOnFilter();
		edit.clickOnThreeDots();
		award.ThreeDotsMenu("QuoteCreate");
		createSol.clickOnNextStep();
		createSol.clickOnNextStep();
		createSol.clickOnNextStep();
		createSol.clickOnNextStep();
		createSol.clickOnNextStep();
		Assert.assertEquals(createSol.verifySuccessMessage(), "This solicitation has been submitted");

	}

	@AfterMethod
	public void setupAfterTest() {
		// createSol.clickHomeButton();
	}
}
