package buyer.testcases.solicitation;

import java.awt.AWTException;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import buyer.pageobjects.solicitationPageObjects.CreateSolicitationPOM;
import buyer.pageobjects.solicitationPageObjects.EditSolicitationPageObject;
import buyer.pageobjects.solicitationPageObjects.ReviewAwardPage;
import buyer.pageobjects.solicitationPageObjects.SolicitationImportPage;
import commonutils.pageobjects.generic.HomePage;
import commonutils.pageobjects.generic.LoginPage;
import commonutils.pageobjects.generic.solicitationNavigation;
import commonutils.pageobjects.utils.ExtentReport;
import commonutils.pageobjects.utils.PCDriver;
import commonutils.pageobjects.utils.ReadConfig;

@Listeners(ExtentReport.class)

public class SolicitationImport extends PCDriver {

	LoginPage login = new LoginPage();
	HomePage home = new HomePage();
	EditSolicitationPageObject edit = new EditSolicitationPageObject();
	CreateSolicitationPOM createSol = new CreateSolicitationPOM();
	solicitationNavigation sol = new solicitationNavigation();
	ReviewAwardPage award = new ReviewAwardPage();
	SolicitationImportPage solimport = new SolicitationImportPage();

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

	@Test
	public void importSolicitationTemplate() throws AWTException {
		home.selectTopNavDropDown("Solicitation");
		sol.informalSolicationsMenu("Import");
		solimport.uploadFile(ReadConfig.getInstance().getExcelPath());
	}

	@Test
	public void DownloadSolicitationTemplate() throws AWTException {
		home.selectTopNavDropDown("Solicitation");
		// sol.informalSolicationsMenu("Import");
		// solimport.uploadFile(ReadConfig.getInstance().getExcelPath());
	}

	@AfterMethod
	public void setupAfterTest() {
		createSol.clickHomeButton();
		home.logout();

	}
}
