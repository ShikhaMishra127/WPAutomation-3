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

	@Test(description = "This test case will edit the informal sol by searching title and submit", enabled = true)
	public void EditInformalSolicitationUsingTitleAndSubmit() throws IOException {
		home.clickInformalSolicitationEdit();
		// edit.clickOnActiveSolicitations();
		edit.setTitleForSearch(edit.getSolTitle(0));
		edit.clickOnFilter();
		Assert.assertTrue(edit.verifySearchResultRow());
		edit.clickOnThreeDots();
		edit.clickEdit();
		edit.checkVendorsAddedWarning();
		edit.checkVendorItemWarning();
		// edit.clickTopNavItem("Edit Header");
		edit.clickSave();
		edit.clickReturn();
		createSol.clickSubmit();
		Assert.assertTrue(createSol.verifySuccessMessage().contains("This solicitation has been submitted"));

	}

	@Test(description = "This test case will edit the formal sol by searching title and submit", enabled = true)
	public void EditFormalSolicitationUsingTitleAndSubmit() throws IOException {
		home.clickFormalSolicitationEdit();
		edit.setTitleForSearch("QA Automation1517306442148");
		edit.clickOnFilter();
		Assert.assertTrue(edit.verifySearchResultRow());
		edit.clickOnThreeDotsForNotSubmittedStatus();
		edit.clickEditUnderNotSubmittedThreeDots();
		edit.checkVendorsAddedWarning();
		edit.checkVendorItemWarning();
		// edit.clickTopNavItem("Edit Header");
		edit.clickSave();
		edit.clickReturn();
		createSol.clickSubmit();
		Assert.assertTrue(createSol.verifySuccessMessage().contains("This solicitation has been submitted"));
	}

	@Test(description = "This test case will create the addendum for Formal Solicitation", enabled = true)
	public void CreateAddendumForFormalSolicitationUsingTitleAndSubmit() throws IOException {
		home.clickFormalSolicitationEdit();
		edit.clickOnActiveSolicitations();
		edit.clickOnThreeDots();
		edit.clickCreateAddendum();
		edit.clickTopNavItem("Edit Item Specs");
		createSol.AddLineItemsAndVerify("10", "Environmental Services");
		edit.clickSave();
		edit.clickReturn();
		createSol.clickSubmit();
		Assert.assertTrue(edit.verifyAddendumSubmission());
	}

	@Test(description = "This test case will create the addendum for Informal Solicitation", enabled = true)
	public void CreateAddendumForInFormalSolicitationUsingTitleAndSubmit() throws IOException {
		home.clickInformalSolicitationEdit();
		// edit.setTitleForSearch(ReadExcelData.getInstance("Solicitation").getStringValue("Title"));
		// edit.clickOnFilter();
		edit.clickOnActiveSolicitations();
		edit.clickOnThreeDots();
		// edit.clickEdit();
		edit.clickCreateAddendum();
		edit.clickTopNavItem("Edit Item Specs");
		createSol.AddLineItemsAndVerify("10", "Environmental Services");
		edit.clickSave();
		edit.clickReturn();
		createSol.clickSubmit();
		Assert.assertTrue(edit.verifyAddendumSubmission());
	}

	@Test(description = "This test case will edit the Informal Solicitation searching by Sol Number", enabled = true)
	public void EditInformalSolicitationUsingSolNumberAndSubmit() throws IOException {
		home.clickInformalSolicitationEdit();
		edit.setSolNumber(edit.getSolNumber(0));
		edit.clickOnFilter();
		Assert.assertTrue(edit.verifySearchResultRow());
		edit.clickOnThreeDotsForNotSubmittedStatus();
		edit.clickEditUnderNotSubmittedThreeDots();
		edit.checkVendorsAddedWarning();
		edit.checkVendorItemWarning();
		// edit.clickTopNavItem("Edit Header");
		edit.clickSave();
		edit.clickReturn();
		createSol.clickSubmit();
		Assert.assertTrue(createSol.verifySuccessMessage().contains("This solicitation has been submitted"));

	}

	@Test(description = "This test case will edit the Formal Solicitation searching by Sol Number", enabled = true)
	public void EditFormalSolicitationUsingSolNumberAndSubmit() throws IOException {
		home.clickFormalSolicitationEdit();
		edit.setSolNumber(edit.getSolNumber(0));
		edit.clickOnFilter();
		edit.clickOnThreeDotsForNotSubmittedStatus();
		edit.clickEditUnderNotSubmittedThreeDots();
		edit.checkVendorsAddedWarning();
		edit.checkVendorItemWarning();
		// edit.clickTopNavItem("Edit Header");
		edit.clickSave();
		edit.clickReturn();
		createSol.clickSubmit();

	}

	@Test(description = "This test case will check the solicitation history for Formal Solicitation", enabled = true)
	public void VerifySolicitationHistoryForFormalSolicitation() {
		home.clickFormalSolicitationEdit();
		edit.clickOnActiveSolicitations();
		edit.clickOnThreeDots();
		edit.clickSolHistory();
		Assert.assertTrue(edit.verifySolHistory());
	}

	@Test(description = "This test case will check the solicitation history for Formal Solicitation", enabled = true)
	public void VerifySolicitationHistoryForInformalSolicitation() {
		home.clickInformalSolicitationEdit();
		edit.clickOnActiveSolicitations();
		edit.clickOnThreeDots();
		edit.clickSolHistory();
		Assert.assertTrue(edit.verifySolHistory());
	}

	@Test(description = "This test case will check the start date search results are within the selected date range for Unissued Formal Solicitation", enabled = false)
	public void checkStartDateFilterForUnissuedFormalSol() {
		home.clickFormalSolicitationEdit();
		edit.setFromStartDate("11/08/2017");
		edit.setToStartDate("11/09/2017");
		edit.clickOnFilter();
		Assert.assertTrue(edit.VerifyStartDate());
	}

	@Test(description = "This test case will test the start date filter and check if the results lie in the same range for Unissued Informal Solicitations", enabled = false)
	public void checkStartDateFilterForUnissuedInFormalSol() {
		home.clickInformalSolicitationEdit();
		edit.setFromStartDate("11/08/2017");
		edit.setToStartDate("11/09/2017");
		edit.clickOnFilter();
		Assert.assertTrue(edit.VerifyStartDate());
	}

	@Test(description = "This test case will check the end date search results are within the selected date range for Unissued Formal Solicitation", enabled = false)
	public void checkEndDateFilterForUnissuedFormalSol() {
		home.clickFormalSolicitationEdit();
		edit.setFromEndDate("11/01/2017");
		edit.setToEndDate("11/09/2017");
		edit.clickOnFilter();
		Assert.assertTrue(edit.VerifyEndDate());
	}

	@Test(description = "This test case will test the end date filter and check if the results lie in the same range for Unissued Informal Solicitations", enabled = false)
	public void checkEndDateFilterForUnissuedInFormalSol() {
		home.clickInformalSolicitationEdit();
		edit.setFromEndDate("11/01/2017");
		edit.setToEndDate("11/09/2017");
		edit.clickOnFilter();
		Assert.assertTrue(edit.VerifyEndDate());
	}

	@Test(description = "", enabled = false)
	public void checkStartDateFilterForActiveFormalSol() {
		home.clickFormalSolicitationEdit();
		edit.setFromStartDate("11/08/2017");
		edit.setToStartDate("11/09/2017");
		edit.clickOnFilter();
		Assert.assertTrue(edit.VerifyStartDate());
	}

	@Test(description = "This test case will test the start date filter and check if the results lie in the same range for Active Informal Solicitations", enabled = false)
	public void checkStartDateFilterForActiveInFormalSol() {
		home.clickInformalSolicitationEdit();
		edit.clickOnActiveSolicitations();
		edit.setFromStartDate("11/08/2017");
		edit.setToStartDate("11/09/2017");
		edit.clickOnFilter();
		Assert.assertTrue(edit.VerifyStartDate());
	}

	@Test(description = "", enabled = false)
	public void checkEndDateFilterForActiveFormalSol() {
		home.clickFormalSolicitationEdit();
		edit.setFromEndDate("11/01/2017");
		edit.setToEndDate("11/09/2017");
		edit.clickOnFilter();
		Assert.assertTrue(edit.VerifyEndDate());
	}

	@Test(description = "This test case will test the end date filter and check if the results lie in the same range for Active Informal Solicitations", enabled = false)
	public void checkEndDateFilterForActiveInFormalSol() {
		home.clickInformalSolicitationEdit();
		edit.clickOnActiveSolicitations();
		edit.setFromEndDate("11/01/2017");
		edit.setToEndDate("11/09/2017");
		edit.clickOnFilter();
		Assert.assertTrue(edit.VerifyEndDate());
	}

	@Test(description = "This test case will check if we close the edit without submitting then the user should be taken back to the Current InFormal Solicitation Page", enabled = false)
	public void closeTheEditForInformalSol() {
		home.clickInformalSolicitationEdit();
		edit.clickOnThreeDots();
		edit.clickEdit();
		edit.clickClose();
		Assert.assertTrue(createSol.verifyPageTitle("Current Informal Solicitation"));

	}

	@Test(description = "This test case will check if we close the edit without submitting then the user should be taken back to the Current Formal Solicitation Page", enabled = false)
	public void closeTheEditForFormalSol() {
		home.clickFormalSolicitationEdit();
		edit.clickOnThreeDots();
		edit.clickEdit();
		edit.clickClose();
		Assert.assertTrue(createSol.verifyPageTitle("Current Formal Solicitation"));
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
		// createSol.clickHomeButton();

	}

	@AfterClass
	public void tearDown() {
		ExtentReport.report.endTest(ExtentReport.logger);
		ExtentReport.report.flush();
		ExtentReport.report.close();
		// PCDriver.getDriver().quit();

	}
}
