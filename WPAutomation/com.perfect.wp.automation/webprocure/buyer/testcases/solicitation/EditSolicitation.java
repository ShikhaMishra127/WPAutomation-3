package buyer.testcases.solicitation;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import buyer.pageobjects.solicitationPageObjects.CreateSolicitationPOM;
import buyer.pageobjects.solicitationPageObjects.EditSolicitationPageObject;
import buyer.pageobjects.solicitationPageObjects.SupplierPage;
import commonutils.pageobjects.generic.HomePage;
import commonutils.pageobjects.generic.LoginPage;
import commonutils.pageobjects.utils.ExtentReport;
import commonutils.pageobjects.utils.PCDriver;
import commonutils.pageobjects.utils.ReadConfig;

@Listeners(ExtentReport.class)
public class EditSolicitation extends PCDriver {
	LoginPage login = new LoginPage();
	HomePage home = new HomePage();
	EditSolicitationPageObject edit = new EditSolicitationPageObject();
	CreateSolicitationPOM createSol = new CreateSolicitationPOM();
	SupplierPage supplier = new SupplierPage();

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

	}

	@Test(description = "This test case will edit the informal sol by searching title and submit", enabled = true)
	public void EditInformalSolicitationUsingTitleAndSubmit() throws IOException {
		ExtentReport.logger.log(LogStatus.INFO, "Test Case Started");

		home.clickInformalSolicitationEdit();
		ExtentReport.logger.log(LogStatus.PASS, "Clicked on Informal Solicitation");
		
		edit.clickOnUnissuedSolicitations();
		ExtentReport.logger.log(LogStatus.PASS, "Clicked on Unissued Solicitation");

		// edit.clickOnActiveSolicitations();
		edit.setTitleForSearch(edit.getSolTitle(0));
		ExtentReport.logger.log(LogStatus.PASS, "Entered Solicitation that needs to be edited");

		edit.clickOnFilter();
		ExtentReport.logger.log(LogStatus.PASS, "Clicked Filter Button");

		Assert.assertTrue(edit.verifySearchResultRow());
		ExtentReport.logger.log(LogStatus.PASS, "Verified the searched Solicitation");

		edit.clickOnThreeDotsForNotSubmittedStatus();
		ExtentReport.logger.log(LogStatus.PASS, "Clicked on Three Dots Menu");

		edit.clickEditUnderNotSubmittedThreeDots();
		ExtentReport.logger.log(LogStatus.PASS, "Edit Clicked under the Three Dots Menu");

		edit.checkVendorsAddedWarning();
		ExtentReport.logger.log(LogStatus.PASS, "Vendor Added");

		edit.checkVendorItemWarning();
		ExtentReport.logger.log(LogStatus.PASS, "Line Items Added");

		// edit.clickTopNavItem("Edit Header");
		edit.clickSave();
		ExtentReport.logger.log(LogStatus.PASS, "Save Button Clicked");

		edit.clickReturn();
		ExtentReport.logger.log(LogStatus.PASS, "Return Button Clicked");

		createSol.clickSubmit();
		ExtentReport.logger.log(LogStatus.PASS, "Submit Button Clicked");

		Assert.assertTrue(createSol.verifySuccessMessage().contains("This solicitation has been submitted"));
		ExtentReport.logger.log(LogStatus.PASS, "Solicitation Message : "+createSol.verifySuccessMessage());


	}

	@Test(description = "This test case will edit the formal sol by searching title and submit", enabled = true)
	public void EditFormalSolicitationUsingTitleAndSubmit() throws IOException {
		ExtentReport.logger.log(LogStatus.INFO, "Test Case Started");

		home.clickFormalSolicitationEdit();
		ExtentReport.logger.log(LogStatus.PASS, "Clicked on Formal Solicitation");
		
		edit.clickOnUnissuedSolicitations();
		ExtentReport.logger.log(LogStatus.PASS, "Clicked on Unissued Solicitation");

		edit.setTitleForSearch(edit.getSolTitle(0));
		ExtentReport.logger.log(LogStatus.PASS, "Entered Solicitation that needs to be edited");

		//edit.setTitleForSearch("QA Automation1517305071632"); 
		edit.clickOnFilter();
		ExtentReport.logger.log(LogStatus.PASS, "Clicked Filter Button");

		Assert.assertTrue(edit.verifySearchResultRow());
		ExtentReport.logger.log(LogStatus.PASS, "Verified the searched Solicitation");

		edit.clickOnThreeDotsForNotSubmittedStatus();
		ExtentReport.logger.log(LogStatus.PASS, "Clicked on Three Dots Menu");

		edit.clickEditUnderNotSubmittedThreeDots();
		ExtentReport.logger.log(LogStatus.PASS, "Edit Clicked under the Three Dots Menu");

		edit.checkVendorsAddedWarning();
		ExtentReport.logger.log(LogStatus.PASS, "Vendor Added");

		edit.checkVendorItemWarning();
		ExtentReport.logger.log(LogStatus.PASS, "Line Items Added");

		// edit.clickTopNavItem("Edit Header");
		edit.clickSave();
		ExtentReport.logger.log(LogStatus.PASS, "Save Button Clicked");

		edit.clickReturn();
		ExtentReport.logger.log(LogStatus.PASS, "Return Button Clicked");

		createSol.clickSubmit();
		ExtentReport.logger.log(LogStatus.PASS, "Submit Button Clicked");

		Assert.assertTrue(createSol.verifySuccessMessage().contains("This solicitation has been submitted"));
		ExtentReport.logger.log(LogStatus.PASS, "Solicitation Message : "+createSol.verifySuccessMessage());

	}

	@Test(description = "This test case will create the addendum for Formal Solicitation", enabled = true)
	public void CreateAddendumForFormalSolicitationUsingTitleAndSubmit() throws IOException {
		ExtentReport.logger.log(LogStatus.INFO, "Test Case Started");

		home.clickFormalSolicitationEdit();
		ExtentReport.logger.log(LogStatus.PASS, "Clicked on Formal Solicitation");

		edit.clickOnActiveSolicitations();
		ExtentReport.logger.log(LogStatus.PASS, "Clicked on Active Solicitations");

		edit.clickOnThreeDots();
		ExtentReport.logger.log(LogStatus.PASS, "Clicked on Three Dots Menu");

		edit.clickCreateAddendum();
		ExtentReport.logger.log(LogStatus.PASS, "Clicked on Create Addendum");

		edit.clickTopNavItem("Edit Item Specs");
		ExtentReport.logger.log(LogStatus.PASS, "Clicked on Line Item Edit");

		createSol.AddLineItemsAndVerify("10", "Environmental Services");
		ExtentReport.logger.log(LogStatus.PASS, "Line Items Added");

		edit.clickSave();
		ExtentReport.logger.log(LogStatus.PASS, "Save Button Clicked");

		edit.clickReturn();
		ExtentReport.logger.log(LogStatus.PASS, "Return Button Clicked");

		createSol.clickSubmit();
		ExtentReport.logger.log(LogStatus.PASS, "Submit Button Clicked");

		Assert.assertTrue(edit.verifyAddendumSubmission());
		ExtentReport.logger.log(LogStatus.PASS, "Addendum Submitted successfully");

	}

	@Test(description = "This test case will create the addendum for Informal Solicitation", enabled = true)
	public void CreateAddendumForInFormalSolicitationUsingTitleAndSubmit() throws IOException {
		ExtentReport.logger.log(LogStatus.INFO, "Test Case Started");

		home.clickInformalSolicitationEdit();
		ExtentReport.logger.log(LogStatus.PASS, "Clicked on Informal Solicitation");

		// edit.setTitleForSearch(ReadExcelData.getInstance("Solicitation").getStringValue("Title"));
		// edit.clickOnFilter();
		edit.clickOnActiveSolicitations();
		ExtentReport.logger.log(LogStatus.PASS, "Clicked on Active Solicitations");

		edit.clickOnThreeDots();
		ExtentReport.logger.log(LogStatus.PASS, "Clicked on Three Dots Menu");

		// edit.clickEdit();
		edit.clickCreateAddendum();
		ExtentReport.logger.log(LogStatus.PASS, "Clicked on Create Addendum");

		edit.clickTopNavItem("Edit Item Specs");
		ExtentReport.logger.log(LogStatus.PASS, "Clicked on Line Item Edit");

		createSol.AddLineItemsAndVerify("10", "Environmental Services");
		ExtentReport.logger.log(LogStatus.PASS, "Line Items Added");

		edit.clickSave();
		ExtentReport.logger.log(LogStatus.PASS, "Save Button Clicked");

		edit.clickReturn();
		ExtentReport.logger.log(LogStatus.PASS, "Return Button Clicked");

		createSol.clickSubmit();
		ExtentReport.logger.log(LogStatus.PASS, "Submit Button Clicked");

		Assert.assertTrue(edit.verifyAddendumSubmission());
		ExtentReport.logger.log(LogStatus.PASS, "Addendum Submitted successfully");

	}

	@Test(description = "This test case will edit the Informal Solicitation searching by Sol Number", enabled = true)
	public void EditInformalSolicitationUsingSolNumberAndSubmit() throws IOException {
		ExtentReport.logger.log(LogStatus.INFO, "Test Case Started");

		home.clickInformalSolicitationEdit();
		ExtentReport.logger.log(LogStatus.PASS, "Clicked on Informal Solicitation");
		
		edit.clickOnUnissuedSolicitations();
		ExtentReport.logger.log(LogStatus.PASS, "Clicked on Unissued Solicitation");

		edit.setSolNumber(edit.getSolNumber(0));
		ExtentReport.logger.log(LogStatus.PASS, "Entered Solicitation that needs to be edited");

		edit.clickOnFilter();
		ExtentReport.logger.log(LogStatus.PASS, "Clicked Filter Button");

		Assert.assertTrue(edit.verifySearchResultRow());
		ExtentReport.logger.log(LogStatus.PASS, "Verified the searched Solicitation");

		edit.clickOnThreeDotsForNotSubmittedStatus();
		ExtentReport.logger.log(LogStatus.PASS, "Clicked on Three Dots Menu");

		edit.clickEditUnderNotSubmittedThreeDots();
		ExtentReport.logger.log(LogStatus.PASS, "Edit Clicked under the Three Dots Menu");

		edit.checkVendorsAddedWarning();
		ExtentReport.logger.log(LogStatus.PASS, "Vendor Added");

		edit.checkVendorItemWarning();
		ExtentReport.logger.log(LogStatus.PASS, "Line Items Added");

		// edit.clickTopNavItem("Edit Header");
		edit.clickSave();
		ExtentReport.logger.log(LogStatus.PASS, "Save Button Clicked");

		edit.clickReturn();
		ExtentReport.logger.log(LogStatus.PASS, "Return Button Clicked");

		createSol.clickSubmit();
		ExtentReport.logger.log(LogStatus.PASS, "Submit Button Clicked");

		Assert.assertTrue(createSol.verifySuccessMessage().contains("This solicitation has been submitted"));
		ExtentReport.logger.log(LogStatus.PASS, "Solicitation Message : "+createSol.verifySuccessMessage());


	}

	@Test(description = "This test case will edit the Formal Solicitation searching by Sol Number", enabled = true)
	public void EditFormalSolicitationUsingSolNumberAndSubmit() throws IOException {
		ExtentReport.logger.log(LogStatus.INFO, "Test Case Started");

		home.clickFormalSolicitationEdit();
		ExtentReport.logger.log(LogStatus.PASS, "Clicked on Formal Solicitation");
		
		edit.clickOnUnissuedSolicitations();
		ExtentReport.logger.log(LogStatus.PASS, "Clicked on Unissued Solicitation");

		edit.setSolNumber(edit.getSolNumber(0));
		ExtentReport.logger.log(LogStatus.PASS, "Entered Solicitation that needs to be edited");

		edit.clickOnFilter();
		ExtentReport.logger.log(LogStatus.PASS, "Clicked Filter Button");

		edit.clickOnThreeDotsForNotSubmittedStatus();
		ExtentReport.logger.log(LogStatus.PASS, "Clicked on Three Dots Menu");

		edit.clickEditUnderNotSubmittedThreeDots();
		ExtentReport.logger.log(LogStatus.PASS, "Edit Clicked under the Three Dots Menu");

		edit.checkVendorsAddedWarning();
		ExtentReport.logger.log(LogStatus.PASS, "Vendor Added");

		edit.checkVendorItemWarning();
		ExtentReport.logger.log(LogStatus.PASS, "Line Items Added");

		// edit.clickTopNavItem("Edit Header");
		edit.clickSave();
		ExtentReport.logger.log(LogStatus.PASS, "Save Button Clicked");

		edit.clickReturn();
		ExtentReport.logger.log(LogStatus.PASS, "Return Button Clicked");

		createSol.clickSubmit();
		ExtentReport.logger.log(LogStatus.PASS, "Submit Button Clicked");

		Assert.assertTrue(createSol.verifySuccessMessage().contains("This solicitation has been submitted"));
		ExtentReport.logger.log(LogStatus.PASS, "Solicitation Message : "+createSol.verifySuccessMessage());

	}

	@Test(description = "This test case will check the solicitation history for Formal Solicitation", enabled = false)
	public void VerifySolicitationHistoryForFormalSolicitation() {
		ExtentReport.logger.log(LogStatus.INFO, "Test Case Started");

		home.clickFormalSolicitationEdit();
		ExtentReport.logger.log(LogStatus.PASS, "Clicked on Formal Solicitation");

		edit.clickOnActiveSolicitations();
		ExtentReport.logger.log(LogStatus.PASS, "Clicked on Active Solicitations");

		edit.clickOnThreeDots();
		ExtentReport.logger.log(LogStatus.PASS, "Clicked on Three Dots Menu");

		edit.clickSolHistory();
		ExtentReport.logger.log(LogStatus.PASS, "Clicked on Solicitation History");

		Assert.assertTrue(edit.verifySolHistory());
		ExtentReport.logger.log(LogStatus.PASS, "Solicitation History is Present");

	}

	@Test(description = "This test case will check the solicitation history for Formal Solicitation", enabled = false)
	public void VerifySolicitationHistoryForInformalSolicitation() {
		ExtentReport.logger.log(LogStatus.INFO, "Test Case Started");

		home.clickInformalSolicitationEdit();
		ExtentReport.logger.log(LogStatus.PASS, "Clicked on Informal Solicitation");

		edit.clickOnActiveSolicitations();
		ExtentReport.logger.log(LogStatus.PASS, "Clicked on Active Solicitations");

		edit.clickOnThreeDots();
		ExtentReport.logger.log(LogStatus.PASS, "Clicked on Three Dots Menu");

		edit.clickSolHistory();
		ExtentReport.logger.log(LogStatus.PASS, "Clicked on Solicitation History");

		Assert.assertTrue(edit.verifySolHistory());
		ExtentReport.logger.log(LogStatus.PASS, "Solicitation History is Present");

	}

	@Test(description = "This test case will check the start date search results are within the selected date range for Unissued Formal Solicitation", enabled = true)
	public void checkStartDateFilterForUnissuedFormalSol() {
		ExtentReport.logger.log(LogStatus.INFO, "Test Case Started");

		home.clickFormalSolicitationEdit();
		ExtentReport.logger.log(LogStatus.PASS, "Clicked on Formal Solicitation");
		
		edit.clickOnUnissuedSolicitations();
		ExtentReport.logger.log(LogStatus.PASS, "Clicked on Unissued Solicitation");

		edit.setFromStartDate("03/01/2018");
		ExtentReport.logger.log(LogStatus.PASS, "Entered the From Start Date");

		edit.setToStartDate("03/31/2018");
		ExtentReport.logger.log(LogStatus.PASS, "Entered the To Start Date");

		edit.clickOnFilter();
		ExtentReport.logger.log(LogStatus.PASS, "Clicked Filter Button");

		Assert.assertTrue(edit.VerifyStartDate());
		ExtentReport.logger.log(LogStatus.PASS, "Start Date Results are with in Range");

	}

	@Test(description = "This test case will test the start date filter and check if the results lie in the same range for Unissued Informal Solicitations", enabled = true)
	public void checkStartDateFilterForUnissuedInFormalSol() {
		ExtentReport.logger.log(LogStatus.INFO, "Test Case Started");

		home.clickInformalSolicitationEdit();
		ExtentReport.logger.log(LogStatus.PASS, "Clicked on Informal Solicitation");
		
		edit.clickOnUnissuedSolicitations();
		ExtentReport.logger.log(LogStatus.PASS, "Clicked on Unissued Solicitation");

		edit.setFromStartDate("03/01/2018");
		ExtentReport.logger.log(LogStatus.PASS, "Entered the From Start Date");

		edit.setToStartDate("03/31/2018");
		ExtentReport.logger.log(LogStatus.PASS, "Entered the To Start Date");

		edit.clickOnFilter();
		ExtentReport.logger.log(LogStatus.PASS, "Clicked Filter Button");

		Assert.assertTrue(edit.VerifyStartDate());
		ExtentReport.logger.log(LogStatus.PASS, "Start Date Results are with in Range");

	}

	@Test(description = "This test case will check the end date search results are within the selected date range for Unissued Formal Solicitation", enabled = true)
	public void checkEndDateFilterForUnissuedFormalSol() {
		ExtentReport.logger.log(LogStatus.INFO, "Test Case Started");

		home.clickFormalSolicitationEdit();
		ExtentReport.logger.log(LogStatus.PASS, "Clicked on Formal Solicitation");
		
		edit.clickOnUnissuedSolicitations();
		ExtentReport.logger.log(LogStatus.PASS, "Clicked on Unissued Solicitation");

		edit.setFromEndDate("03/01/2018");
		ExtentReport.logger.log(LogStatus.PASS, "Entered the From End Date");

		edit.setToEndDate("03/31/2018");
		ExtentReport.logger.log(LogStatus.PASS, "Entered the To End Date");

		edit.clickOnFilter();
		ExtentReport.logger.log(LogStatus.PASS, "Clicked Filter Button");

		Assert.assertTrue(edit.VerifyEndDate());
		ExtentReport.logger.log(LogStatus.PASS, "End Date Results are with in Range");

	}

	@Test(description = "This test case will test the end date filter and check if the results lie in the same range for Unissued Informal Solicitations", enabled = true)
	public void checkEndDateFilterForUnissuedInFormalSol() {
		ExtentReport.logger.log(LogStatus.INFO, "Test Case Started");

		home.clickInformalSolicitationEdit();
		ExtentReport.logger.log(LogStatus.PASS, "Clicked on Informal Solicitation");
		
		edit.clickOnUnissuedSolicitations();
		ExtentReport.logger.log(LogStatus.PASS, "Clicked on Unissued Solicitation");

		edit.setFromEndDate("03/01/2018");
		ExtentReport.logger.log(LogStatus.PASS, "Entered the From End Date");

		edit.setToEndDate("03/31/2018");
		ExtentReport.logger.log(LogStatus.PASS, "Entered the To End Date");

		edit.clickOnFilter();
		ExtentReport.logger.log(LogStatus.PASS, "Clicked Filter Button");

		Assert.assertTrue(edit.VerifyEndDate());
		ExtentReport.logger.log(LogStatus.PASS, "End Date Results are with in Range");

	}

	@Test(description = "", enabled = false)
	public void checkStartDateFilterForActiveFormalSol() {
		ExtentReport.logger.log(LogStatus.INFO, "Test Case Started");

		home.clickFormalSolicitationEdit();
		ExtentReport.logger.log(LogStatus.PASS, "Clicked on Formal Solicitation");
		
		edit.clickOnActiveSolicitations();
		ExtentReport.logger.log(LogStatus.PASS, "Clicked on Active Solicitations");

		edit.setFromStartDate("03/01/2018");
		ExtentReport.logger.log(LogStatus.PASS, "Entered the From Start Date");

		edit.setToStartDate("03/31/2018");
		ExtentReport.logger.log(LogStatus.PASS, "Entered the To Start Date");

		edit.clickOnFilter();
		ExtentReport.logger.log(LogStatus.PASS, "Clicked Filter Button");

		Assert.assertTrue(edit.VerifyStartDate());
		ExtentReport.logger.log(LogStatus.PASS, "Start Date Results are with in Range");

		
	}

	@Test(description = "This test case will test the start date filter and check if the results lie in the same range for Active Informal Solicitations", enabled = true)
	public void checkStartDateFilterForActiveInFormalSol() {
		ExtentReport.logger.log(LogStatus.INFO, "Test Case Started");

		home.clickInformalSolicitationEdit();
		ExtentReport.logger.log(LogStatus.PASS, "Clicked on Informal Solicitation");

		edit.clickOnActiveSolicitations();
		ExtentReport.logger.log(LogStatus.PASS, "Clicked on Active Solicitations");

		edit.setFromStartDate("03/01/2018");
		ExtentReport.logger.log(LogStatus.PASS, "Entered the From Start Date");

		edit.setToStartDate("03/31/2018");
		ExtentReport.logger.log(LogStatus.PASS, "Entered the To Start Date");

		edit.clickOnFilter();
		ExtentReport.logger.log(LogStatus.PASS, "Clicked Filter Button");

		Assert.assertTrue(edit.VerifyStartDate());
		ExtentReport.logger.log(LogStatus.PASS, "Start Date Results are with in Range");

	}

	@Test(description = "This test will check the end date filter for Active Formal Solicitation", enabled = true)
	public void checkEndDateFilterForActiveFormalSol() {
		ExtentReport.logger.log(LogStatus.INFO, "Test Case Started");

		home.clickFormalSolicitationEdit();
		ExtentReport.logger.log(LogStatus.PASS, "Clicked on Formal Solicitation");
		
		edit.clickOnActiveSolicitations();
		ExtentReport.logger.log(LogStatus.PASS, "Clicked on Active Solicitations");

		edit.setFromEndDate("03/01/2018");
		ExtentReport.logger.log(LogStatus.PASS, "Entered the From End Date");

		edit.setToEndDate("03/31/2018");
		ExtentReport.logger.log(LogStatus.PASS, "Entered the To End Date");

		edit.clickOnFilter();
		ExtentReport.logger.log(LogStatus.PASS, "Clicked Filter Button");

		Assert.assertTrue(edit.VerifyEndDate());
		ExtentReport.logger.log(LogStatus.PASS, "End Date Results are with in Range");

	}

	@Test(description = "This test case will test the end date filter and check if the results lie in the same range for Active Informal Solicitations", enabled = true)
	public void checkEndDateFilterForActiveInFormalSol() {
		ExtentReport.logger.log(LogStatus.INFO, "Test Case Started");

		home.clickInformalSolicitationEdit();
		ExtentReport.logger.log(LogStatus.PASS, "Clicked on Informal Solicitation");

		edit.clickOnActiveSolicitations();
		ExtentReport.logger.log(LogStatus.PASS, "Clicked on Active Solicitations");

		edit.setFromEndDate("03/01/2018");
		ExtentReport.logger.log(LogStatus.PASS, "Entered the From End Date");

		edit.setToEndDate("03/31/2018");
		ExtentReport.logger.log(LogStatus.PASS, "Entered the To End Date");

		edit.clickOnFilter();
		ExtentReport.logger.log(LogStatus.PASS, "Clicked Filter Button");

		Assert.assertTrue(edit.VerifyEndDate());
		ExtentReport.logger.log(LogStatus.PASS, "End Date Results are with in Range");

	}

	@Test(description = "This test case will check if we close the edit without submitting then the user should be taken back to the Current InFormal Solicitation Page", enabled = true)
	public void closeTheEditForInformalSol() throws IOException {
		ExtentReport.logger.log(LogStatus.INFO, "Test Case Started");

		home.clickInformalSolicitationEdit();
		ExtentReport.logger.log(LogStatus.PASS, "Clicked on Informal Solicitation");

		edit.clickOnUnissuedSolicitations();
		ExtentReport.logger.log(LogStatus.PASS, "Clicked on Unissued Solicitation");
		
		edit.setTitleForSearch(edit.getSolTitle(0));
		ExtentReport.logger.log(LogStatus.PASS, "Entered Solicitation that needs to be edited");

		edit.clickOnFilter();
		ExtentReport.logger.log(LogStatus.PASS, "Clicked Filter Button");

		Assert.assertTrue(edit.verifySearchResultRow());
		ExtentReport.logger.log(LogStatus.PASS, "Verified the searched Solicitation");


		edit.clickOnThreeDots();
		ExtentReport.logger.log(LogStatus.PASS, "Clicked on Three Dots Menu");

		edit.clickEdit();
		ExtentReport.logger.log(LogStatus.PASS, "Clicked Edit under Three Dots Menu");

		edit.clickClose();
		ExtentReport.logger.log(LogStatus.PASS, "Close Button Clicked Successfully");

		Assert.assertTrue(createSol.verifyPageTitle("Current Informal Solicitation"));
		ExtentReport.logger.log(LogStatus.PASS, "Current Informal Solicitations Page displayed as expected");


	}

	@Test(description = "This test case will check if we close the edit without submitting then the user should be taken back to the Current Formal Solicitation Page", enabled = true)
	public void closeTheEditForFormalSol() throws IOException {
		ExtentReport.logger.log(LogStatus.INFO, "Test Case Started");

		home.clickFormalSolicitationEdit();
		ExtentReport.logger.log(LogStatus.PASS, "Clicked on Formal Solicitation");
		
		edit.clickOnUnissuedSolicitations();
		ExtentReport.logger.log(LogStatus.PASS, "Clicked on Unissued Solicitation");
		
		edit.setTitleForSearch(edit.getSolTitle(0));
		ExtentReport.logger.log(LogStatus.PASS, "Entered Solicitation that needs to be edited");

		edit.clickOnFilter();
		ExtentReport.logger.log(LogStatus.PASS, "Clicked Filter Button");

		Assert.assertTrue(edit.verifySearchResultRow());
		ExtentReport.logger.log(LogStatus.PASS, "Verified the searched Solicitation");

		edit.clickOnThreeDotsForNotSubmittedStatus();
		ExtentReport.logger.log(LogStatus.PASS, "Clicked on Three Dots Menu");

		edit.clickEdit();
		ExtentReport.logger.log(LogStatus.PASS, "Clicked Edit under Three Dots Menu");

		edit.clickClose();
		ExtentReport.logger.log(LogStatus.PASS, "Close Button Clicked Successfully");

		Assert.assertTrue(createSol.verifyPageTitle("Current Formal Solicitation"));
		ExtentReport.logger.log(LogStatus.PASS, "Current Formal Solicitations Page displayed as expected");

	}
	/*
	 * @Test public void compareChangesOfAmendment() {
	 * home.clickFormalSolicitationEdit(); edit.clickOnActiveSolicitations();
	 * edit.clickOnThreeDots(); edit.clickSolHistory(); edit.clickItemsToCompare();
	 * Assert.assertTrue(edit.verifyChanges());
	 * 
	 * }
	 */

	@AfterMethod
	public void setupAfterTest() {
		 createSol.clickHomeButton();

	}

	@AfterClass
	public void tearDown() {
		ExtentReport.report.endTest(ExtentReport.logger);
		 home.logout();

	}
}
