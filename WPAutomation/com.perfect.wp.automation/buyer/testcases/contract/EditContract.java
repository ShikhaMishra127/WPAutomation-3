package testcases.contract;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import pageobjects.contract.AttachmentPageContract;
import pageobjects.contract.CatalogItems;
import pageobjects.contract.ContractAuthorization;
import pageobjects.contract.ContractNotificationsPage;
import pageobjects.contract.ContractSummary;
import pageobjects.contract.createContractPageObject;
import pageobjects.generic.ContractNavigation;
import pageobjects.generic.HomePage;
import pageobjects.generic.LoginPage;
import pageobjects.generic.ContractNavigation.ContractsSubMenu;
import pageobjects.solicitationPageObjects.CreateSolicitationPOM;
import pageobjects.solicitationPageObjects.EditSolicitationPageObject;
import pageobjects.solicitationPageObjects.HeaderPage;
import pageobjects.solicitationPageObjects.ReviewAwardPage;
import pageobjects.utils.ExtentReport;
import pageobjects.utils.ReadConfig;

@Listeners(ExtentReport.class)
public class EditContract {
	LoginPage login = new LoginPage();
	HomePage home = new HomePage();
	EditSolicitationPageObject edit = new EditSolicitationPageObject();
	CreateSolicitationPOM createSol = new CreateSolicitationPOM();
	ContractNavigation contNav = new ContractNavigation();
	createContractPageObject contr = new createContractPageObject();
	HeaderPage header = new HeaderPage();
	ReviewAwardPage review = new ReviewAwardPage();
	ContractNotificationsPage contNotific = new ContractNotificationsPage();
	CatalogItems items = new CatalogItems();
	AttachmentPageContract attach = new AttachmentPageContract();
	ContractAuthorization auth = new ContractAuthorization();
	ContractSummary summary = new ContractSummary();

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
		home.selectTopNavDropDown("Contracts");

	}
	
	@Test
	public void testEdit() {
		contNav.clickContractsSubMenu(ContractsSubMenu.ViewCurrentContracts.toString());

	}
}
