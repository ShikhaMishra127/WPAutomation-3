package testcases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.LogStatus;
import pageobjects.generic.HomePage;
import pageobjects.generic.LoginPage;
import pageobjects.generic.solicitationNavigation;
import pageobjects.solicitationPageObjects.CreateSolicitationPOM;
import pageobjects.solicitationPageObjects.HeaderPage;
import pageobjects.solicitationPageObjects.SummaryPage;
import pageobjects.utils.DatePicker;
import pageobjects.utils.ExtentReport;
import pageobjects.utils.PCDriver;
import pageobjects.utils.ReadConfig;
import pageobjects.utils.ReadExcelData;

@Listeners(ExtentReport.class)

public class CreateSolicitation extends PCDriver {
	LoginPage login = new LoginPage();
	HomePage home = new HomePage();
	CreateSolicitationPOM sol = new CreateSolicitationPOM();
	solicitationNavigation solNav = new solicitationNavigation();
	HeaderPage header = new HeaderPage();
	SummaryPage summary = new SummaryPage();

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
		home.selectTopNavDropDown("Solicitations");

	}

	@Test(description = "This test case will check if Solicitation title is mandatory or not for Informal Solicitation")
	public void verifyTitleIsMandateForInformalSol() {
		solNav.informalSolicationsMenu("Create");
		header.waitForAddFieldToDisplay();
		sol.clickOnNextStep();
		Assert.assertEquals(sol.clickOnNextStep(), "Please enter a title for this quote");
	}

	@Test(description = "This test case will check if Solicitation title is mandatory or not for Informal Solicitation")
	public void verifyTitleIsMandateForFormalSol() {
		solNav.informalSolicationsMenu("Create");
		header.waitForAddFieldToDisplay();
		Assert.assertEquals(sol.clickOnNextStep(), "Please enter a title for this bid");
	}

	@Test(description = "This test case will check if past solicitation start date is allowed or not")
	public void verifyPastSolStartDateForInformalSol() {
		solNav.informalSolicationsMenu("Create");
		header.setSolStartDate(DatePicker.getPastDate());
		Assert.assertEquals(sol.clickOnNextStep(),
				"The Informal Solicitation Duration Start Date and Time must be after the current time.");

	}

	@Test(description = "This test case will check if past solicitation start date is allowed or not")
	public void verifyPastSolStartDateForFormalSol() {
		solNav.formalSolicationsMenu("Create");
		header.setSolStartDate(DatePicker.getPastDate());
		Assert.assertEquals(sol.clickOnNextStep(),
				"The Informal Solicitation Duration Start Date and Time must be after the current time.");

	}

	@Test(description = "This test case will check if future solicitation start date is allowed or not for Informal Solicitation")
	public void verifyFutureSolStartDateForInformalSol() {
		solNav.informalSolicationsMenu("Create");
		header.setSolStartDate(DatePicker.getFutureDate());
		Assert.assertEquals(sol.clickOnNextStep(), "Please enter a title for this quote");

	}

	@Test(description = "This test case will check if future solicitation start date is allowed or not for Informal Solicitation")
	public void verifyFutureSolStartDateForFormalSol() {
		solNav.formalSolicationsMenu("Create");
		header.setSolStartDate(DatePicker.getFutureDate());
		Assert.assertEquals(sol.clickOnNextStep(), "Please enter a title for this quote");

	}

	@Test(description = "This test case checks that Participation End Date should be before the InFormal Solicitation Duration Start Date.")
	public void verifyPartEndDateBeforSolStartDateForFormalSol() {
		solNav.informalSolicationsMenu("Create");
		header.setPartEndDate(DatePicker.getCustomDate(header.getSolStartDate(), -100));
		Assert.assertEquals(sol.clickOnNextStep(),
				"The Participation End Date should be before the InFormal Solicitation Duration Start Date.");
	}

	@Test(description = "This test case checks that Participation End Date should be before the Formal Solicitation Duration Start Date.")
	public void verifyPartEndDateBeforSolStartDateForInFormalSol() {
		solNav.informalSolicationsMenu("Create");
		header.setPartEndDate(DatePicker.getCustomDate(header.getSolStartDate(), -100));
		Assert.assertEquals(sol.clickOnNextStep(),
				"The Participation End Date should be before the InFormal Solicitation Duration Start Date.");
	}

	@Test(description = "This test case checks that Participation End Date should be before the Formal Solicitation Duration Start Date.")
	public void verifyCollabEndDateAfterCurrentTimeForInFormalSol() {
		solNav.informalSolicationsMenu("Create");
		header.setCollabEndDate(DatePicker.getCustomDate(header.getSolStartDate(), 2));
		Assert.assertEquals(sol.clickOnNextStep(),
				"The Collaboration End Date and Time need to be after the current time.");
	}

	@Test(description = "This test case checks that Participation End Date should be before the Formal Solicitation Duration Start Date.")
	public void verifyCollabEndDateAfterCurrentTimeForFormalSol() {
		solNav.formalSolicationsMenu("Create");
		header.setCollabEndDate(DatePicker.getCustomDate(header.getSolStartDate(), 2));
		Assert.assertEquals(sol.clickOnNextStep(),
				"The Collaboration End Date and Time need to be after the current time.");
	}

	@Test(description = "This test case checks that Participation End Date should be before the Formal Solicitation Duration Start Date.")
	public void verifyCollabStartDateAfterCurrentTimeForFormalSol() {
		solNav.formalSolicationsMenu("Create");
		header.setCollabStartDate(DatePicker.getCustomDate(header.getSolStartDate(), 2));
		Assert.assertEquals(sol.clickOnNextStep(),
				"The Collaboration Start Date and Time need to be after the current time.");
	}

	@Test(description = "This test case checks that Participation End Date should be before the Formal Solicitation Duration Start Date.")
	public void verifyCollabStartDateAfterCurrentTimeForInFormalSol() {
		solNav.informalSolicationsMenu("Create");
		header.setCollabStartDate(DatePicker.getCustomDate(header.getSolStartDate(), 2));
		Assert.assertEquals(sol.clickOnNextStep(),
				"The Collaboration Start Date and Time need to be after the current time.");
	}

	@Test(description = "This test case checks that Participation End Date should be before the Formal Solicitation Duration Start Date.")
	public void verifyCollabStartDateAfterSolStartDateForFormalSol() {
		solNav.formalSolicationsMenu("Create");
		header.setCollabStartDate(DatePicker.getCustomDate(header.getSolStartDate(), 1));
		Assert.assertEquals(sol.clickOnNextStep(),
				"The Collaboration Start Date should be after the Formal Solicitation Duration Start Date.");
	}

	@Test(description = "This test case checks that Participation End Date should be before the Formal Solicitation Duration Start Date.")
	public void verifyCollabStartDateAfterSolStartDateForInFormalSol() {
		solNav.informalSolicationsMenu("Create");
		header.setCollabStartDate(DatePicker.getCustomDate(header.getSolStartDate(), 1));
		Assert.assertEquals(sol.clickOnNextStep(),
				"The Collaboration Start Date should be after the Formal Solicitation Duration Start Date.");
	}

	@Test(description = "This test case will create the NoLineType Solicitation")
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
			Assert.assertEquals(sol.verifySuccessMessage(), "This solicitation has been submitted");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test(description = "This test case will create the NoLineType Solicitation")
	public void formalSolicitationCreationWithNoLineItem() {
		try {
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
			Assert.assertEquals(sol.verifySuccessMessage(), "This solicitation has been submitted");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test(description = "This test case will create the Solicitation by creating new line item and searching for an existing supplier and adding it")
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
		sol.createLineItem(10, "Apparel");
		// sol.createLineItem(10,"Cleaning");
		sol.clickOnNextStep();
		sol.searchSupplier();
		// sol.CreateSupplier();
		sol.clickOnNextStep();
		Assert.assertEquals(summary.verifyListIsPresentInSummaryForSupplier(), true);
		Assert.assertEquals(summary.verifyListIsPresentInSummaryForItem(), true);
		sol.clickSubmit();
		Assert.assertEquals(sol.verifySuccessMessage(), "This solicitation has been submitted");

	}

	@Test(description = "This test case will create the Solicitation by creating new line item and searching for a supplier and adding it")
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
		sol.createLineItem(10, "Apparel");
		// sol.createLineItem(10,"Cleaning");
		sol.clickOnNextStep();
		sol.searchSupplier();
		// sol.CreateSupplier();
		sol.clickOnNextStep();
		Assert.assertEquals(summary.verifyListIsPresentInSummaryForSupplier(), true);
		Assert.assertEquals(summary.verifyListIsPresentInSummaryForItem(), true);
		sol.clickSubmit();
		Assert.assertEquals(sol.verifySuccessMessage(), "This solicitation has been submitted");

	}

	@Test(description = "This test case will create the Solicitation by adding new line item and creating new supplier")
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
		Assert.assertEquals(sol.AddLineItemsAndVerify("10", "Apparel"), true, "lines items are added");
		sol.clickOnNextStep();
		sol.CreateSupplier();
		// sol.searchSupplier();
		sol.clickOnNextStep();
		Assert.assertEquals(summary.verifyListIsPresentInSummaryForSupplier(), true);
		Assert.assertEquals(summary.verifyListIsPresentInSummaryForItem(), true);
		sol.clickSubmit();
		Assert.assertEquals(sol.verifySuccessMessage(), "This solicitation has been submitted");
	}

	@Test(description = "This test case will create the Solicitation by adding new line item and creating new supplier")
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
		Assert.assertEquals(sol.AddLineItemsAndVerify("10", "Apparel"), true, "lines items are added");
		sol.clickOnNextStep();
		sol.CreateSupplier();
		// sol.searchSupplier();
		sol.clickOnNextStep();
		Assert.assertEquals(summary.verifyListIsPresentInSummaryForSupplier(), true);
		Assert.assertEquals(summary.verifyListIsPresentInSummaryForItem(), true);
		sol.clickSubmit();
		Assert.assertEquals(sol.verifySuccessMessage(), "This solicitation has been submitted");

	}

	@Test(description = "This test case will create a new Informal Solicitation by adding a new group")
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
		Assert.assertEquals(sol.AddLineItemsAndVerify("10", "Apparel"), true, "lines items are added");
		sol.switchOnAdjustmentHandler();
		sol.clickOnNextStep();
		sol.CreateSupplier();
		// sol.searchSupplier();
		sol.clickOnNextStep();
		Assert.assertEquals(summary.verifyListIsPresentInSummaryForSupplier(), true);
		Assert.assertEquals(summary.verifyListIsPresentInSummaryForItem(), true);
		sol.clickSubmit();
		Assert.assertEquals(sol.verifySuccessMessage(), "This solicitation has been submitted");

	}

	@Test(description = "This test case will create a new Formal Solicitation by adding a new group")
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
		Assert.assertEquals(sol.AddLineItemsAndVerify("10", "Building and Construction Machinery and Accessories"),
				true, "lines items are added");
		sol.switchOnAdjustmentHandler();
		sol.clickOnNextStep();
		sol.CreateSupplier();
		// sol.searchSupplier();
		sol.clickOnNextStep();
		Assert.assertEquals(summary.verifyListIsPresentInSummaryForSupplier(), true);
		Assert.assertEquals(summary.verifyListIsPresentInSummaryForItem(), true);
		sol.clickSubmit();

	}

	@Test(description = "This test case will check when creating a new group the group name cannot be empty for Informal Solicitation")
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

	@Test(description = "This test case will check when creating a new group the group name cannot be empty for Formal Solicitation")
	public void checkGroupNameCannotBeEmptyForFormalSolicitation() throws Exception {

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
	}

	/// @Test(description = "This test case will check the exiting before Creation
	/// of Sol should take the user to Current Formal Sol Page")
	public void exitSolicitationBeforeCreationForFormalSolicitation() {
		solNav.formalSolicationsMenu("Create");
		sol.clickExit();
		Assert.assertTrue(sol.verifyPageTitle("Current Formal Solicitation"));
	}

	@Test(description = "This test case will check the exiting before Creation of Sol should take the user to Current InFormal Sol Page")
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
		ExtentReport.report.flush();
		ExtentReport.report.close();
		PCDriver.getDriver().quit();

	}
}
