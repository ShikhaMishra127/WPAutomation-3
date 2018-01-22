package buyer.testcases.contract;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import buyer.pageobjects.contract.AttachmentPageContract;
import buyer.pageobjects.contract.CatalogItems;
import buyer.pageobjects.contract.ContractAuthorization;
import buyer.pageobjects.contract.ContractNotificationsPage;
import buyer.pageobjects.contract.ContractSummary;
import buyer.pageobjects.contract.EditContractPageObject;
import buyer.pageobjects.contract.EditContractPageObject.ContractsThreeDotsMenu;
import buyer.pageobjects.contract.EditContractPageObject.EditContractTopNav;
import buyer.pageobjects.contract.createContractPageObject;
import buyer.pageobjects.solicitationPageObjects.CreateSolicitationPOM;
import buyer.pageobjects.solicitationPageObjects.EditSolicitationPageObject;
import buyer.pageobjects.solicitationPageObjects.HeaderPage;
import buyer.pageobjects.solicitationPageObjects.ReviewAwardPage;
import commonutils.pageobjects.generic.ContractNavigation;
import commonutils.pageobjects.generic.ContractNavigation.ContractsSubMenu;
import commonutils.pageobjects.generic.HomePage;
import commonutils.pageobjects.generic.LoginPage;
import commonutils.pageobjects.utils.ExtentReport;
import commonutils.pageobjects.utils.ReadConfig;
import commonutils.pageobjects.utils.ReadExcelData;

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
	EditContractPageObject editContract = new EditContractPageObject();

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
	public void testEdit() throws IOException {
		contNav.clickContractsSubMenu(ContractsSubMenu.ViewCurrentContracts.toString());
		editContract.setContractTitle(ReadExcelData.getInstance("Contract").getStringValue("ContractTitle"));
		createSol.clickSubmit();
		edit.clickOnThreeDots();
		editContract.clickThreeDotItem(ContractsThreeDotsMenu.EditContract.toString());
		edit.clickTopNavItem(EditContractTopNav.EditHeader.toString());
		editContract.addNewSectionAndField();
		editContract.clickSave();

	}
}
