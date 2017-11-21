package testcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import pageobjects.generic.HomePage;
import pageobjects.generic.LoginPage;
import pageobjects.solicitationPageObjects.CreateSolicitationPOM;
import pageobjects.solicitationPageObjects.EditSolicitationPageObject;
import pageobjects.utils.ExtentReport;
import pageobjects.utils.ReadConfig;
import pageobjects.utils.ReadExcelData;

@Listeners(ExtentReport.class)
public class EditSolicitation {
	LoginPage login = new LoginPage();
	HomePage home = new HomePage();
	EditSolicitationPageObject edit = new EditSolicitationPageObject();
	CreateSolicitationPOM createSol = new CreateSolicitationPOM();

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

	// @Test
	public void EditInformalSolicitationUsingTitleAndSubmit() throws IOException {
		home.clickInformalSolicitationEdit();
		edit.setTitleForSearch(ReadExcelData.getInstance("Solicitation").getStringValue("Title"));
		edit.clickOnFilter();
		edit.clickOnThreeDots();
		edit.clickEdit();
		edit.clickTopNavItem("Edit Header");
		edit.clickSave();
		edit.clickReturn();
		createSol.clickSubmit();

	}

	// @Test
	public void EditInformalSolicitationUsingSolNumberAndSubmit() throws IOException {
		home.clickInformalSolicitationEdit();
		edit.setSolNumber(ReadExcelData.getInstance("Solicitation").getStringValue("Title"));
		edit.clickOnFilter();
		edit.clickOnThreeDots();
		edit.clickEdit();
		edit.clickTopNavItem("Edit Header");
		edit.clickSave();
		edit.clickReturn();
		createSol.clickSubmit();

	}

	// @Test(description = "This test case will create the addendum")
	public void EditFormalSolicitationUsingTitleAndSubmit() throws IOException {
		home.clickFormalSolicitationEdit();
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

	// @Test
	public void VerifySolicitationHistory() {
		home.clickFormalSolicitationEdit();
		edit.clickOnActiveSolicitations();
		edit.clickOnThreeDots();
		edit.clickSolHistory();
		Assert.assertTrue(edit.verifySolHistory());
	}

	// @Test
	public void checkStartDateFilterForUnissuedFormalSol() {
		home.clickFormalSolicitationEdit();
		edit.setFromStartDate("11/08/2017");
		edit.setToStartDate("11/09/2017");
		edit.clickOnFilter();
		Assert.assertTrue(edit.VerifyStartDate());
	}

	@Test
	public void checkEndDateFilterForUnissuedFormalSol() {
		home.clickFormalSolicitationEdit();
		edit.setFromEndDate("11/01/2017");
		edit.setToEndDate("11/09/2017");
		edit.clickOnFilter();
		Assert.assertTrue(edit.VerifyEndDate());
	}

	// @Test
	public void checkStartDateFilterForActiveFormalSol() {
		home.clickFormalSolicitationEdit();
		edit.setFromStartDate("11/08/2017");
		edit.setToStartDate("11/09/2017");
		edit.clickOnFilter();
		Assert.assertTrue(edit.VerifyStartDate());
	}

	@Test
	public void checkEndDateFilterForActiveFormalSol() {
		home.clickFormalSolicitationEdit();
		edit.setFromEndDate("11/01/2017");
		edit.setToEndDate("11/09/2017");
		edit.clickOnFilter();
		Assert.assertTrue(edit.VerifyEndDate());
	}

	@Test(description = "This test case will test the start date filter and check if the results lie in the same range for Unissued Informal Solicitations")
	public void checkStartDateFilterForUnissuedInFormalSol() {
		home.clickInformalSolicitationEdit();
		edit.setFromStartDate("11/08/2017");
		edit.setToStartDate("11/09/2017");
		edit.clickOnFilter();
		Assert.assertTrue(edit.VerifyStartDate());
	}

	@Test(description = "This test case will test the end date filter and check if the results lie in the same range for Unissued Informal Solicitations")
	public void checkEndDateFilterForUnissuedInFormalSol() {
		home.clickInformalSolicitationEdit();
		edit.setFromEndDate("11/01/2017");
		edit.setToEndDate("11/09/2017");
		edit.clickOnFilter();
		Assert.assertTrue(edit.VerifyEndDate());
	}

	@Test(description = "This test case will test the start date filter and check if the results lie in the same range for Active Informal Solicitations")
	public void checkStartDateFilterForActiveInFormalSol() {
		home.clickInformalSolicitationEdit();
		edit.clickOnActiveSolicitations();

		edit.setFromStartDate("11/08/2017");
		edit.setToStartDate("11/09/2017");
		edit.clickOnFilter();
		Assert.assertTrue(edit.VerifyStartDate());
	}

	@Test(description = "This test case will test the end date filter and check if the results lie in the same range for Active Informal Solicitations")
	public void checkEndDateFilterForActiveInFormalSol() {
		home.clickInformalSolicitationEdit();
		edit.clickOnActiveSolicitations();
		edit.setFromEndDate("11/01/2017");
		edit.setToEndDate("11/09/2017");
		edit.clickOnFilter();
		Assert.assertTrue(edit.VerifyEndDate());
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
}
