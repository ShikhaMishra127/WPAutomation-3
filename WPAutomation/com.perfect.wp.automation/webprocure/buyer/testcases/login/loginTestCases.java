package buyer.testcases.login;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import buyer.pageobjects.solicitationPageObjects.CreateSolicitationPOM;
import commonutils.pageobjects.generic.LoginPage;
import commonutils.pageobjects.utils.ExtentReport;
import commonutils.pageobjects.utils.ReadExcelData;

@Listeners(ExtentReport.class)
public class loginTestCases {
	LoginPage page = new LoginPage();
	CreateSolicitationPOM sol = new CreateSolicitationPOM();

	@Test
	public void wrongPassword() {
		try {
			String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
			page.setUsername(
					ReadExcelData.getInstance("WrongPass").getStringValue("Username") + System.currentTimeMillis());
			page.setPassword(ReadExcelData.getInstance("WrongPass").getStringValue("Password"));
			page.clickOnLogin();
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Assert.assertTrue(page.passwordIncorrect().contains("Your login attempt was not successful"));
		} catch (IOException e) {

		}
	}

	@AfterMethod
	public void tearDownAfterTest() {
		sol.clickHomeButton();
		ExtentReport.logger.log(LogStatus.PASS, "Clicked on Home Button");

	}

	@AfterClass
	public void tearDown() {
		ExtentReport.report.endTest(ExtentReport.logger);

		/*
		 * ExtentReport.report.endTest(ExtentReport.logger);
		 * ExtentReport.report.flush(); ExtentReport.report.close();
		 */

	}
}
