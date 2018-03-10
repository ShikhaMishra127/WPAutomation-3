package buyer.testcases.solicitation;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import buyer.pageobjects.solicitationPageObjects.CreateSolicitationPOM;
import buyer.pageobjects.solicitationPageObjects.HeaderPage;
import buyer.pageobjects.solicitationPageObjects.ItemSpecPage;
import buyer.pageobjects.solicitationPageObjects.SummaryPage;
import commonutils.pageobjects.generic.HomePage;
import commonutils.pageobjects.generic.LoginPage;
import commonutils.pageobjects.generic.solicitationNavigation;
import commonutils.pageobjects.utils.DatePicker;
import commonutils.pageobjects.utils.ExtentReport;
import commonutils.pageobjects.utils.PCDriver;
import commonutils.pageobjects.utils.ReadConfig;
import commonutils.pageobjects.utils.ReadExcelData;

@Listeners(ExtentReport.class)

public class CreateSolicitation extends PCDriver {
	// log4jClass log=new log4jClass();
	LoginPage login = new LoginPage();
	HomePage home = new HomePage();
	CreateSolicitationPOM sol = new CreateSolicitationPOM();
	solicitationNavigation solNav = new solicitationNavigation();
	HeaderPage header = new HeaderPage();
	SummaryPage summary = new SummaryPage();
	ItemSpecPage item = new ItemSpecPage();

	@BeforeClass
	public void setup() {
		try {
			// log.info("Before Class entered");
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
		home.selectTopNavDropDown("Solicitations");

	}

	@Test(description = "This test case will check if Solicitation title is mandatory or not for Informal Solicitation", enabled = false)
	public void verifyTitleIsMandateForInformalSol() {
		solNav.informalSolicationsMenu("Create");
		header.waitForAddFieldToDisplay();
		sol.clickOnNextStep();
		Assert.assertEquals(sol.clickOnNextStep(), "Please enter a title for this quote");
	}

	@Test(description = "This test case will check if Solicitation title is mandatory or not for Informal Solicitation", enabled = false)
	public void verifyTitleIsMandateForFormalSol() {
		solNav.informalSolicationsMenu("Create");
		header.waitForAddFieldToDisplay();
		Assert.assertEquals(sol.clickOnNextStep(), "Please enter a title for this bid");
	}

	@Test(description = "This test case will check if past solicitation start date is allowed or not", enabled = false)
	public void verifyPastSolStartDateForInformalSol() {
		solNav.informalSolicationsMenu("Create");
		header.setSolStartDate(DatePicker.getPastDate());
		Assert.assertEquals(sol.clickOnNextStep(),
				"The Informal Solicitation Duration Start Date and Time must be after the current time.");

	}

	@Test(description = "This test case will check if past solicitation start date is allowed or not", enabled = false)
	public void verifyPastSolStartDateForFormalSol() {
		solNav.formalSolicationsMenu("Create");
		header.setSolStartDate(DatePicker.getPastDate());
		Assert.assertEquals(sol.clickOnNextStep(),
				"The Informal Solicitation Duration Start Date and Time must be after the current time.");

	}

	@Test(description = "This test case will check if future solicitation start date is allowed or not for Informal Solicitation", enabled = false)
	public void verifyFutureSolStartDateForInformalSol() {
		solNav.informalSolicationsMenu("Create");
		header.setSolStartDate(DatePicker.getFutureDate());
		Assert.assertEquals(sol.clickOnNextStep(), "Please enter a title for this quote");

	}

	@Test(description = "This test case will check if future solicitation start date is allowed or not for Informal Solicitation", enabled = false)
	public void verifyFutureSolStartDateForFormalSol() {
		solNav.formalSolicationsMenu("Create");
		header.setSolStartDate(DatePicker.getFutureDate());
		Assert.assertEquals(sol.clickOnNextStep(), "Please enter a title for this quote");

	}

	@Test(description = "This test case checks that Participation End Date should be before the InFormal Solicitation Duration Start Date.", enabled = false)
	public void verifyPartEndDateBeforSolStartDateForFormalSol() {
		solNav.informalSolicationsMenu("Create");
		header.setPartEndDate(DatePicker.getCustomDate(header.getSolStartDate(), -100));
		Assert.assertEquals(sol.clickOnNextStep(),
				"The Participation End Date should be before the InFormal Solicitation Duration Start Date.");
	}

	@Test(description = "This test case checks that Participation End Date should be before the Formal Solicitation Duration Start Date.", enabled = false)
	public void verifyPartEndDateBeforSolStartDateForInFormalSol() {
		solNav.informalSolicationsMenu("Create");
		header.setPartEndDate(DatePicker.getCustomDate(header.getSolStartDate(), -100));
		Assert.assertEquals(sol.clickOnNextStep(),
				"The Participation End Date should be before the InFormal Solicitation Duration Start Date.");
	}

	@Test(description = "This test case checks that Participation End Date should be before the Formal Solicitation Duration Start Date.", enabled = false)
	public void verifyCollabEndDateAfterCurrentTimeForInFormalSol() {
		solNav.informalSolicationsMenu("Create");
		header.setCollabEndDate(DatePicker.getCustomDate(header.getSolStartDate(), 2));
		Assert.assertEquals(sol.clickOnNextStep(),
				"The Collaboration End Date and Time need to be after the current time.");
	}

	@Test(description = "This test case checks that Participation End Date should be before the Formal Solicitation Duration Start Date.", enabled = false)
	public void verifyCollabEndDateAfterCurrentTimeForFormalSol() {
		solNav.formalSolicationsMenu("Create");
		header.setCollabEndDate(DatePicker.getCustomDate(header.getSolStartDate(), 2));
		Assert.assertEquals(sol.clickOnNextStep(),
				"The Collaboration End Date and Time need to be after the current time.");
	}

	@Test(description = "This test case checks that Participation End Date should be before the Formal Solicitation Duration Start Date.", enabled = false)
	public void verifyCollabStartDateAfterCurrentTimeForFormalSol() {
		solNav.formalSolicationsMenu("Create");
		header.setCollabStartDate(DatePicker.getCustomDate(header.getSolStartDate(), 2));
		Assert.assertEquals(sol.clickOnNextStep(),
				"The Collaboration Start Date and Time need to be after the current time.");
	}

	@Test(description = "This test case checks that Participation End Date should be before the Formal Solicitation Duration Start Date.", enabled = false)
	public void verifyCollabStartDateAfterCurrentTimeForInFormalSol() {
		solNav.informalSolicationsMenu("Create");
		header.setCollabStartDate(DatePicker.getCustomDate(header.getSolStartDate(), 2));
		Assert.assertEquals(sol.clickOnNextStep(),
				"The Collaboration Start Date and Time need to be after the current time.");
	}

	@Test(description = "This test case checks that Participation End Date should be before the Formal Solicitation Duration Start Date.", enabled = false)
	public void verifyCollabStartDateAfterSolStartDateForFormalSol() {
		solNav.formalSolicationsMenu("Create");
		header.setCollabStartDate(DatePicker.getCustomDate(header.getSolStartDate(), 1));
		Assert.assertEquals(sol.clickOnNextStep(),
				"The Collaboration Start Date should be after the Formal Solicitation Duration Start Date.");
	}

	@Test(description = "This test case checks that Participation End Date should be before the Formal Solicitation Duration Start Date.", enabled = false)
	public void verifyCollabStartDateAfterSolStartDateForInFormalSol() {
		solNav.informalSolicationsMenu("Create");
		header.setCollabStartDate(DatePicker.getCustomDate(header.getSolStartDate(), 1));
		Assert.assertEquals(sol.clickOnNextStep(),
				"The Collaboration Start Date should be after the Formal Solicitation Duration Start Date.");
	}

	@Test(description = "This test case will create the NoLineType Solicitation", enabled = true)
	public void informalSolicitationCreationWithNoLineItem() {
		try {
			solNav.informalSolicationsMenu("Create");
			ReadExcelData.getInstance("Solicitation").updateCellValue("Title",
					"QA Automation" + System.currentTimeMillis());
			header.enterHeaderDetails(false);
			sol.clickOnNextStep();
			sol.waitForDivToAppearInReqPage();
			sol.clickOnNextStep();
			sol.EnterQuestionnaire();
			sol.clickOnNextStep();
			sol.clickOnNextStep();
			sol.clickOnNextStep();
			sol.CreateSupplier();
			sol.clickOnNextStep();
			sol.clickSubmit();
			Assert.assertEquals(sol.verifySuccessMessage(),
					"This solicitation has been submitted to pre-issue workflow for approval.");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test(description = "This test case will create the NoLineType Solicitation", enabled = true)
	public void formalSolicitationCreationWithNoLineItem() throws IOException, InterruptedException {

		solNav.formalSolicationsMenu("Create");
		ReadExcelData.getInstance("Solicitation").updateCellValue("Title",
				"QA Automation" + System.currentTimeMillis());
		header.enterHeaderDetails(false);
		sol.clickOnNextStep();
		sol.waitForDivToAppearInReqPage();
		sol.clickOnNextStep();
		sol.EnterQuestionnaire();
		sol.clickOnNextStep();
		sol.clickOnNextStep();
		sol.clickOnNextStep();
		sol.CreateSupplier();
		sol.clickOnNextStep();
		sol.clickSubmit();
		Assert.assertEquals(sol.verifySuccessMessage(),
				"This solicitation has been submitted to pre-issue workflow for approval.");

	}

	@Test(description = "This test case will create the Solicitation by creating new line item and searching for an existing supplier and adding it", enabled = false)
	public void formalSolicitationByCreatingLineItem() throws Exception {
		solNav.formalSolicationsMenu("Create");
		ReadExcelData.getInstance("Solicitation").updateCellValue("Title",
				"QA Automation" + System.currentTimeMillis());
		header.enterHeaderDetails(true);
		sol.clickOnNextStep();
		sol.waitForDivToAppearInReqPage();
		sol.clickOnNextStep();
		sol.EnterQuestionnaire();
		sol.clickOnNextStep();
		// sol.uploadNewDocument();
		sol.clickOnNextStep();
		sol.createLineItem(2, "Apparel");
		Assert.assertTrue(item.verifyItemSpec());
		// sol.createLineItem(10,"Cleaning");
		sol.clickOnNextStep();
		sol.searchSupplier();
		// sol.CreateSupplier();
		sol.clickOnNextStep();
		Assert.assertEquals(summary.verifyListIsPresentInSummaryForSupplier(), true);
		Assert.assertEquals(summary.verifyListIsPresentInSummaryForItem(), true);
		sol.clickSubmit();
		Assert.assertEquals(sol.verifySuccessMessage(),
				"This solicitation has been submitted to pre-issue workflow for approval.");

	}

	@Test(description="This test case will create the Solicitation by creating new line item and searching for a supplier and adding it",enabled=false)	
	public void inFormalSolicitationByCreatingLineItem() throws Exception {
		solNav.informalSolicationsMenu("Create");
		ReadExcelData.getInstance("Solicitation").updateCellValue("Title",
				"QA Automation" + System.currentTimeMillis());
		header.enterHeaderDetails(true);
		sol.clickOnNextStep();
		sol.waitForDivToAppearInReqPage();
		sol.clickOnNextStep();
		sol.EnterQuestionnaire();
		sol.clickOnNextStep();
		// sol.uploadNewDocument();
		sol.clickOnNextStep();
		sol.createLineItem(10, "Cleaning");
		// sol.createLineItem(10,"Cleaning");
		sol.clickOnNextStep();
		sol.searchSupplier();
		// sol.CreateSupplier();
		sol.clickOnNextStep();
		Assert.assertEquals(summary.verifyListIsPresentInSummaryForSupplier(), true);
		Assert.assertEquals(summary.verifyListIsPresentInSummaryForItem(), true);
		sol.clickSubmit();
		Assert.assertEquals(sol.verifySuccessMessage(),
				"This solicitation has been submitted to pre-issue workflow for approval.");

	}

	@Test(description = "This test case will create the Solicitation by adding new line item and creating new supplier", enabled = true)
	public void formalSolicitationByAddingLineItem() throws Exception {
		solNav.formalSolicationsMenu("Create");
		ReadExcelData.getInstance("Solicitation").updateCellValue("Title",
				"QA Automation" + System.currentTimeMillis());
		header.enterHeaderDetails(true);
		sol.clickOnNextStep();
		sol.waitForDivToAppearInReqPage();
		sol.clickOnNextStep();
		sol.EnterQuestionnaire();
		sol.clickOnNextStep();
		sol.clickOnNextStep();
		Assert.assertEquals(sol.AddLineItemsAndVerify("10", "Cleaning"), true, "lines items are added");
		sol.clickOnNextStep();
		sol.CreateSupplier();
		// sol.searchSupplier();
		sol.clickOnNextStep();
		Assert.assertEquals(summary.verifyListIsPresentInSummaryForSupplier(), true);
		Assert.assertEquals(summary.verifyListIsPresentInSummaryForItem(), true);
		sol.clickSubmit();
		Assert.assertEquals(sol.verifySuccessMessage(),
				"This solicitation has been submitted to pre-issue workflow for approval.");
	}

	@Test(description = "This test case will create the Solicitation by adding new line item and creating new supplier", enabled = true)
	public void inFormalSolicitationByAddingLineItem() throws Exception {
		solNav.informalSolicationsMenu("Create");
		ReadExcelData.getInstance("Solicitation").updateCellValue("Title",
				"QA Automation" + System.currentTimeMillis());
		header.enterHeaderDetails(true);
		sol.clickOnNextStep();
		sol.waitForDivToAppearInReqPage();
		sol.clickOnNextStep();
		sol.EnterQuestionnaire();
		sol.clickOnNextStep();
		sol.clickOnNextStep();
		Assert.assertEquals(sol.AddLineItemsAndVerify("10", "Cleaning"), true, "lines items are added");
		sol.clickOnNextStep();
		sol.CreateSupplier();
		// sol.searchSupplier();
		sol.clickOnNextStep();
		Assert.assertEquals(summary.verifyListIsPresentInSummaryForSupplier(), true);
		Assert.assertEquals(summary.verifyListIsPresentInSummaryForItem(), true);
		sol.clickSubmit();
		Assert.assertEquals(sol.verifySuccessMessage(),
				"This solicitation has been submitted to pre-issue workflow for approval.");

	}

	@Test(description = "This test case will create a new Informal Solicitation by adding a new group", enabled = true)
	public void inFormalSolicitationByAddingGroupsWithLineItem() throws Exception {
		solNav.informalSolicationsMenu("Create");
		ReadExcelData.getInstance("Solicitation").updateCellValue("Title",
				"QA Automation" + System.currentTimeMillis());
		header.enterHeaderDetails(true);
		sol.clickOnNextStep();
		sol.waitForDivToAppearInReqPage();
		sol.clickOnNextStep();
		sol.EnterQuestionnaire();
		sol.clickOnNextStep();
		sol.clickOnNextStep();
		sol.createNewGroup("Testing");
		sol.waitForItemSpecLibraryLinkToDisappear();
		Assert.assertEquals(sol.AddLineItemsAndVerify("10", "Cleaning"), true, "lines items are added");
		sol.switchOnAdjustmentHandler();
		sol.clickOnNextStep();
		sol.CreateSupplier();
		// sol.searchSupplier();
		sol.clickOnNextStep();
		Assert.assertEquals(summary.verifyListIsPresentInSummaryForSupplier(), true);
		Assert.assertEquals(summary.verifyListIsPresentInSummaryForItem(), true);
		sol.clickSubmit();
		Assert.assertEquals(sol.verifySuccessMessage(),
				"This solicitation has been submitted to pre-issue workflow for approval.");

	}

	@Test(description = "This test case will create a new Formal Solicitation by adding a new group", enabled = true)
	public void formalSolicitationByAddingGroupsWithLineItem() throws Exception {
		solNav.formalSolicationsMenu("Create");
		ReadExcelData.getInstance("Solicitation").updateCellValue("Title",
				"QA Automation" + System.currentTimeMillis());
		header.enterHeaderDetails(true);
		sol.clickOnNextStep();
		sol.waitForDivToAppearInReqPage();
		sol.clickOnNextStep();
		sol.EnterQuestionnaire();
		sol.clickOnNextStep();
		sol.clickOnNextStep();
		sol.createNewGroup("Testing");
		sol.waitForItemSpecLibraryLinkToDisappear();
		Assert.assertEquals(sol.AddLineItemsAndVerify("10", "Electronic Components and Supplies"), true,
				"lines items are added");
		sol.switchOnAdjustmentHandler();
		sol.clickOnNextStep();
		sol.CreateSupplier();
		// sol.searchSupplier();
		sol.clickOnNextStep();
		Assert.assertEquals(summary.verifyListIsPresentInSummaryForSupplier(), true);
		Assert.assertEquals(summary.verifyListIsPresentInSummaryForItem(), true);
		sol.clickSubmit();

	}

	@Test(description = "This test case will check when creating a new group the group name cannot be empty for Informal Solicitation", enabled = true)
	public void checkGroupNameCannotBeEmptyForInformalSolicitation() throws Exception {

		solNav.informalSolicationsMenu("Create");
		ReadExcelData.getInstance("Solicitation").updateCellValue("Title",
				"QA Automation" + System.currentTimeMillis());
		header.enterHeaderDetails(true);
		sol.clickOnNextStep();
		sol.waitForDivToAppearInReqPage();
		sol.clickOnNextStep();
		sol.EnterQuestionnaire();
		sol.clickOnNextStep();
		sol.clickOnNextStep();
		sol.createNewGroup("");
		Assert.assertTrue(sol.verifyGroupNameNotEmpty());
	}

	@Test(description = "This test case will check when creating a new group the group name cannot be empty for Formal Solicitation", enabled = true)
	public void checkGroupNameCannotBeEmptyForFormalSolicitation() throws Exception {
		try {
			solNav.formalSolicationsMenu("Create");
			ReadExcelData.getInstance("Solicitation").updateCellValue("Title",
					"QA Automation" + System.currentTimeMillis());
			header.enterHeaderDetails(true);
			sol.clickOnNextStep();
			sol.waitForDivToAppearInReqPage();
			sol.clickOnNextStep();
			sol.EnterQuestionnaire();
			sol.clickOnNextStep();
			sol.clickOnNextStep();
			sol.createNewGroup("");
			Assert.assertTrue(sol.verifyGroupNameNotEmpty());
		} catch (Exception e) {
			Assert.fail();
		}
	}

	@Test(description = "This test case will check the exiting before Creation of Sol should take the user to Current Formal Sol Page", enabled = true)
	public void exitSolicitationBeforeCreationForFormalSolicitation() {
		solNav.formalSolicationsMenu("Create");
		sol.clickExit();
		Assert.assertTrue(sol.verifyPageTitle("Current Formal Solicitation"));
	}

	@Test(description = "This test case will check the exiting before Creation of Sol should take the user to Current InFormal Sol Page", enabled = true)
	public void exitSolicitationBeforeCreationForInFormalSolicitation() {
		solNav.informalSolicationsMenu("Create");
		sol.clickExit();
		Assert.assertTrue(sol.verifyPageTitle("Current Informal Solicitation"));
	}

	@AfterMethod
	public void tearDownAfterTest() {
		sol.clickHomeButton();
	}

	@AfterClass
	public void tearDown() {
		ExtentReport.report.endTest(ExtentReport.logger);

		/*
		 * ExtentReport.report.endTest(ExtentReport.logger);
		 * ExtentReport.report.flush(); ExtentReport.report.close();
		 */
		home.logout();

	}
}
