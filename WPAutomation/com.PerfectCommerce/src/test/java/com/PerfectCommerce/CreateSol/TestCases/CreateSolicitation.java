package com.PerfectCommerce.CreateSol.TestCases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.PerfectCommerce.CreateSol.Pom.CreateSolicitationPOM;
import com.PerfectCommerce.generic.HomePage;
import com.PerfectCommerce.generic.LoginPage;
import com.PerfectCommerce.utils.ExtentReport;
import com.PerfectCommerce.utils.PCDriver;
import com.PerfectCommerce.utils.ReadConfig;
import com.relevantcodes.extentreports.LogStatus;

public class CreateSolicitation extends PCDriver {
	ExtentReport extent = new ExtentReport();
	LoginPage login = new LoginPage();
	HomePage home = new HomePage();
	CreateSolicitationPOM sol = new CreateSolicitationPOM();

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
			home.selectTopNavDropDown("Solicitations");
			home.informalSolicationsMenu("Create");

		} catch (Exception e) {
			Assert.fail();
		}

	}

	@Test(description = "This test case will create the NoLineType Solication", testName = "Sol_Creation_NoLineType")
	public void openHomepage() {
		try {
			sol.setTitle("QA Automation");
			sol.setSolType();
			sol.setEstimatedTotalValue("1000");
			sol.selectNoLineItemCheckBox();
			sol.clickAndSelectCategory("Apparel");
			sol.clickCloseOnCategoryPopUp();
			sol.clickOnNextStep();
			sol.clickOnNextStep();
			sol.clickOnNextStep();
			sol.clickOnNextStep();
			sol.clickOnNextStep();
			sol.clickOnNextStep();


		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@AfterClass
	public void tearDown() {

		ExtentReport.report.endTest(ExtentReport.logger);
		ExtentReport.report.flush();
		ExtentReport.report.close();
		//driver.quit();

	}
}
