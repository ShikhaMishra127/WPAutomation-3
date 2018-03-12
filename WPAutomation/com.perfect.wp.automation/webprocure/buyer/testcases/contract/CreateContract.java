package buyer.testcases.contract;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
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

public class CreateContract {
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
		ExtentReport.logger.log(LogStatus.PASS, "Contracts Menu is clicked");

	}

	@Test(description = "This test case will create a Contract", enabled = true)
	public void CreateContractsByAddingAllItemsFromExistingCatalog() throws IOException, InterruptedException {
		contNav.clickContractsSubMenu(ContractsSubMenu.CreateNewContract.toString());
		ExtentReport.logger.log(LogStatus.PASS, "Create Contracts is clicked");
		contr.setContractTitle(
				ReadExcelData.getInstance("Contract").getStringValue("ContractTitle") + System.currentTimeMillis());
		contr.selectContractType(ReadExcelData.getInstance("Contract").getStringValue("ContractType"));
		ExtentReport.logger.log(LogStatus.PASS, "Contract Type is selected");
		contr.selectRoundTripCheckBox();
		header.clickAndSelectCategory("Live Plant");
		header.clickCloseOnCategoryPopUp();
		contr.clickSearchContractors();
		// contr.searchSupplierAndSelect("a**");
		review.searchSupplier(ReadExcelData.getInstance("Contract").getStringValue("SupplierSearch"));
		contr.clickOnSupplierCheckMark();
		contr.AddContact();
		contr.selectPricingType(ReadExcelData.getInstance("Contract").getStringValue("PricingType"));
		contr.selectTotalValue(ReadExcelData.getInstance("Contract").getStringValue("TotalValue"));
		contr.setUSDValue(ReadExcelData.getInstance("Contract").getStringValue("UsdValue"));
		contr.setAwardDate(ReadExcelData.getInstance("Contract").getStringValue("AwardDate"));
		contr.setEffectiveDate(ReadExcelData.getInstance("Contract").getStringValue("EffectiveDate"));
		contr.setExpiryDate(ReadExcelData.getInstance("Contract").getStringValue("ExpiryDate"));
		createSol.clickOnNextStep();
		contNotific.EnterNotificationDetails();
		contr.clickNext();
		contr.clickNext();
		items.selectPriceCatalogValue();
		items.clickAddAllItems();
		// items.clickAddNewCatalog();
		// items.uploadCatFile("C:\\Users\\Anshul\\Downloads\\11_12_20171211_91330_66589_AwardedItemsCAT.xls");
		// items.uploadPrcFile("C:\\Users\\Anshul\\Downloads\\11_12_20171211_91330_66589_AwardedItemsPRC.xls");
		// items.clickNext();
		contr.clickNext();
		attach.clickUploadDocFromLibrary();
		attach.uploadDocFromLibrary();
		contr.clickNext();
		auth.clickAuthorizeCheckBox();
		auth.clickFinished();
		summary.clickSubmitOnContractSummary();
		Assert.assertTrue(contr.verifyCurrentContractsPage());
	}

	@Test(description = "This test case will create a Contract", enabled = false)
	public void CreateContractsByAddingNewCatalog() throws IOException, InterruptedException {
		contNav.clickContractsSubMenu(ContractsSubMenu.CreateNewContract.toString());
		ExtentReport.logger.log(LogStatus.PASS, "Create Contracts is clicked");
		contr.setContractTitle(
				ReadExcelData.getInstance("Contract").getStringValue("ContractTitle") + System.currentTimeMillis());
		contr.selectContractType(ReadExcelData.getInstance("Contract").getStringValue("ContractType"));
		ExtentReport.logger.log(LogStatus.PASS, "Contract Type is selected");
		contr.selectRoundTripCheckBox();
		header.clickAndSelectCategory("Live Plant");
		header.clickCloseOnCategoryPopUp();
		contr.clickSearchContractors();
		// contr.searchSupplierAndSelect("a**");
		review.searchSupplier(ReadExcelData.getInstance("Contract").getStringValue("SupplierSearch"));
		contr.clickOnSupplierCheckMark();
		contr.AddContact();
		contr.selectPricingType(ReadExcelData.getInstance("Contract").getStringValue("PricingType"));
		contr.selectTotalValue(ReadExcelData.getInstance("Contract").getStringValue("TotalValue"));
		contr.setUSDValue(ReadExcelData.getInstance("Contract").getStringValue("UsdValue"));
		contr.setAwardDate(ReadExcelData.getInstance("Contract").getStringValue("AwardDate"));
		contr.setEffectiveDate(ReadExcelData.getInstance("Contract").getStringValue("EffectiveDate"));
		contr.setExpiryDate(ReadExcelData.getInstance("Contract").getStringValue("ExpiryDate"));
		createSol.clickOnNextStep();
		contNotific.EnterNotificationDetails();
		contr.clickNext();
		contr.clickNext();
		// items.selectPriceCatalogValue();
		// items.clickAddAllItems();
		items.clickAddNewCatalog();
		items.uploadCatFile("C:\\Users\\Anshul\\Downloads\\11_12_20171211_91330_66589_AwardedItemsCAT.xls");
		items.uploadPrcFile("C:\\Users\\Anshul\\Downloads\\11_12_20171211_91330_66589_AwardedItemsPRC.xls");
		items.clickNext();
		contr.clickNext();
		attach.clickUploadDocFromLibrary();
		attach.uploadDocFromLibrary();
		contr.clickNext();
		auth.clickAuthorizeCheckBox();
		auth.clickFinished();
		summary.clickSubmitOnContractSummary();
		Assert.assertTrue(contr.verifyCurrentContractsPage());
	}

	@Test(description = "This test case will create a Master Contract", enabled = false)
	public void CreateMasterContracts() throws IOException, InterruptedException {
		contNav.clickContractsSubMenu(ContractsSubMenu.CreateNewContract.toString());
		contr.selectContractType(ReadExcelData.getInstance("Contract").getStringValue("ContractType"));
		contr.setContractTitle(
				ReadExcelData.getInstance("Contract").getStringValue("ContractTitle") + System.currentTimeMillis());
		contr.selectMasterContractCheckBox();
		header.clickAndSelectCategory("Live Plant");
		header.clickCloseOnCategoryPopUp();
		contr.clickSearchContractors();
		// contr.searchSupplierAndSelect("a**");
		review.searchSupplier(ReadExcelData.getInstance("Contract").getStringValue("SupplierSearch"));
		contr.clickOnContractorCheckBox();
		// contr.AddContact();
		contr.selectPricingType(ReadExcelData.getInstance("Contract").getStringValue("PricingType"));
		contr.selectTotalValue(ReadExcelData.getInstance("Contract").getStringValue("TotalValue"));
		contr.setUSDValue(ReadExcelData.getInstance("Contract").getStringValue("UsdValue"));
		contr.setAwardDate(ReadExcelData.getInstance("Contract").getStringValue("AwardDate"));
		contr.setEffectiveDate(ReadExcelData.getInstance("Contract").getStringValue("EffectiveDate"));
		contr.setExpiryDate(ReadExcelData.getInstance("Contract").getStringValue("ExpiryDate"));
		createSol.clickOnNextStep();
		contNotific.EnterNotificationDetails();
		contr.clickNext();
		contr.clickNext();
		// items.selectPriceCatalogValue();
		// items.clickAddAllItems();
		// items.clickAddNewCatalog();
		// items.uploadCatFile("C:\\Users\\Anshul\\Downloads\\11_12_20171211_91330_66589_AwardedItemsCAT.xls");
		// items.uploadPrcFile("C:\\Users\\Anshul\\Downloads\\11_12_20171211_91330_66589_AwardedItemsPRC.xls");
		// items.clickNext();
		contr.clickNext();
		attach.clickUploadDocFromLibrary();
		attach.uploadDocFromLibrary();
		contr.clickNext();
		auth.clickAuthorizeCheckBox();
		auth.clickFinished();
		summary.clickSubmitOnContractSummary();
		Assert.assertTrue(contr.verifyCurrentContractsPage());
	}

	@Test(description = "This test case will check if Contract Title is Mandatory or not", enabled = false)
	public void verifyTitleIsMandatory() {
		contNav.clickContractsSubMenu(ContractsSubMenu.CreateNewContract.toString());
		Assert.assertEquals(contr.clickNextStep(), "Enter Title");

	}

	@Test(description = "This test case will check if Contracter is Mandatory or not", enabled = false)
	public void verifyContracterIsMandatory() throws IOException {
		contNav.clickContractsSubMenu(ContractsSubMenu.CreateNewContract.toString());
		contr.selectContractType(ReadExcelData.getInstance("Contract").getStringValue("ContractType"));
		contr.setContractTitle(
				ReadExcelData.getInstance("Contract").getStringValue("ContractTitle") + System.currentTimeMillis());
		Assert.assertEquals(contr.clickNextStep(), "Select Contractor");

	}

	@Test(description = "This test case will check if Contract Pricing Type is Mandatory or not", enabled = false)
	public void verifyContractPricingTypeIsMandatory() throws IOException {
		contNav.clickContractsSubMenu(ContractsSubMenu.CreateNewContract.toString());
		contr.selectContractType(ReadExcelData.getInstance("Contract").getStringValue("ContractType"));
		contr.setContractTitle(
				ReadExcelData.getInstance("Contract").getStringValue("ContractTitle") + System.currentTimeMillis());
		contr.clickSearchContractors();
		review.searchSupplier(ReadExcelData.getInstance("Contract").getStringValue("SupplierSearch"));
		contr.clickOnSupplierCheckMark();
		Assert.assertEquals(createSol.clickOnNextStep(), "Select Contract Pricing Type");

	}

	@Test(description = "This test case will check if Contract Total Value is Mandatory or not", enabled = false)
	public void verifyContractTotalValueIsMandatory() throws IOException {
		contNav.clickContractsSubMenu(ContractsSubMenu.CreateNewContract.toString());
		contr.selectContractType(ReadExcelData.getInstance("Contract").getStringValue("ContractType"));
		contr.setContractTitle(
				ReadExcelData.getInstance("Contract").getStringValue("ContractTitle") + System.currentTimeMillis());
		contr.clickSearchContractors();
		review.searchSupplier(ReadExcelData.getInstance("Contract").getStringValue("SupplierSearch"));
		contr.clickOnSupplierCheckMark();
		contr.selectPricingType(ReadExcelData.getInstance("Contract").getStringValue("PricingType"));
		Assert.assertEquals(createSol.clickOnNextStep(), "Select Contract Total Value Condition");

	}

	@Test(description = "This test case will check if Award Date is Mandatory or not", enabled = false)
	public void verifyAwardDateIsMandatory() throws IOException {
		contNav.clickContractsSubMenu(ContractsSubMenu.CreateNewContract.toString());
		contr.selectContractType(ReadExcelData.getInstance("Contract").getStringValue("ContractType"));
		contr.setContractTitle(
				ReadExcelData.getInstance("Contract").getStringValue("ContractTitle") + System.currentTimeMillis());
		contr.clickSearchContractors();
		review.searchSupplier(ReadExcelData.getInstance("Contract").getStringValue("SupplierSearch"));
		contr.clickOnSupplierCheckMark();
		contr.selectPricingType(ReadExcelData.getInstance("Contract").getStringValue("PricingType"));
		contr.selectTotalValue(ReadExcelData.getInstance("Contract").getStringValue("TotalValue"));
		Assert.assertEquals(createSol.clickOnNextStep(), "'Enter award date can't be null'");

	}

	@Test(description = "This test case will check if Effective Date is Mandatory or not", enabled = false)
	public void verifyEffectiveDateIsMandatory() throws IOException {
		contNav.clickContractsSubMenu(ContractsSubMenu.CreateNewContract.toString());
		contr.selectContractType(ReadExcelData.getInstance("Contract").getStringValue("ContractType"));
		contr.setContractTitle(
				ReadExcelData.getInstance("Contract").getStringValue("ContractTitle") + System.currentTimeMillis());
		contr.clickSearchContractors();
		review.searchSupplier(ReadExcelData.getInstance("Contract").getStringValue("SupplierSearch"));
		contr.clickOnSupplierCheckMark();
		contr.selectPricingType(ReadExcelData.getInstance("Contract").getStringValue("PricingType"));
		contr.selectTotalValue(ReadExcelData.getInstance("Contract").getStringValue("TotalValue"));
		contr.setAwardDate(ReadExcelData.getInstance("Contract").getStringValue("AwardDate"));
		Assert.assertEquals(createSol.clickOnNextStep(), "'Enter effective date can't be null.'");

	}

	@Test(description = "This test case will check if Expiry Date is Mandatory or not", enabled = false)
	public void verifyExpiryDateIsMandatory() throws IOException {
		contNav.clickContractsSubMenu(ContractsSubMenu.CreateNewContract.toString());
		contr.selectContractType(ReadExcelData.getInstance("Contract").getStringValue("ContractType"));
		contr.setContractTitle(
				ReadExcelData.getInstance("Contract").getStringValue("ContractTitle") + System.currentTimeMillis());
		contr.clickSearchContractors();
		review.searchSupplier(ReadExcelData.getInstance("Contract").getStringValue("SupplierSearch"));
		contr.clickOnSupplierCheckMark();
		contr.selectPricingType(ReadExcelData.getInstance("Contract").getStringValue("PricingType"));
		contr.selectTotalValue(ReadExcelData.getInstance("Contract").getStringValue("TotalValue"));
		contr.setAwardDate(ReadExcelData.getInstance("Contract").getStringValue("AwardDate"));
		contr.setEffectiveDate(ReadExcelData.getInstance("Contract").getStringValue("EffectiveDate"));
		Assert.assertEquals(createSol.clickOnNextStep(), "'Expiry Date Can not be null.'");

	}

	@Test(description = "This test case will check the working of exit button", enabled = false)
	public void verifyExitContract() {
		contNav.clickContractsSubMenu(ContractsSubMenu.CreateNewContract.toString());
		contr.clickExit();
		Assert.assertTrue(contr.verifyCurrentContractsPage());
	}

	@Test(description = "This test case will close the contract on Contract Summary just before Submitting", enabled = false)
	public void verifyCloseButtonOnContractSubmissionPage() throws IOException, InterruptedException {
		contNav.clickContractsSubMenu(ContractsSubMenu.CreateNewContract.toString());
		contr.selectContractType(ReadExcelData.getInstance("Contract").getStringValue("ContractType"));
		contr.setContractTitle(
				ReadExcelData.getInstance("Contract").getStringValue("ContractTitle") + System.currentTimeMillis());
		contr.selectRoundTripCheckBox();
		header.clickAndSelectCategory("Live Plant");
		header.clickCloseOnCategoryPopUp();
		contr.clickSearchContractors();
		review.searchSupplier(ReadExcelData.getInstance("Contract").getStringValue("SupplierSearch"));
		contr.clickOnSupplierCheckMark();
		contr.AddContact();
		contr.selectPricingType(ReadExcelData.getInstance("Contract").getStringValue("PricingType"));
		contr.selectTotalValue(ReadExcelData.getInstance("Contract").getStringValue("TotalValue"));
		contr.setUSDValue(ReadExcelData.getInstance("Contract").getStringValue("UsdValue"));
		contr.setAwardDate(ReadExcelData.getInstance("Contract").getStringValue("AwardDate"));
		contr.setEffectiveDate(ReadExcelData.getInstance("Contract").getStringValue("EffectiveDate"));
		contr.setExpiryDate(ReadExcelData.getInstance("Contract").getStringValue("ExpiryDate"));
		createSol.clickOnNextStep();
		contNotific.EnterNotificationDetails();
		contr.clickNext();
		contr.clickNext();
		items.selectPriceCatalogValue();
		items.clickAddAllItems();
		contr.clickNext();
		attach.clickUploadDocFromLibrary();
		attach.uploadDocFromLibrary();
		contr.clickNext();
		auth.clickAuthorizeCheckBox();
		auth.clickFinished();
		summary.clickCloseButton();
		Assert.assertTrue(contr.verifyCurrentContractsPage());
	}

	@Test(description = "This test case will check that Expiry Date must always occure after Effective Date", enabled = false)
	public void expiryDateGreaterThanEffectiveDate() throws IOException {
		contNav.clickContractsSubMenu(ContractsSubMenu.CreateNewContract.toString());
		contr.selectContractType(ReadExcelData.getInstance("Contract").getStringValue("ContractType"));
		contr.setContractTitle(
				ReadExcelData.getInstance("Contract").getStringValue("ContractTitle") + System.currentTimeMillis());
		contr.clickSearchContractors();
		review.searchSupplier(ReadExcelData.getInstance("Contract").getStringValue("SupplierSearch"));
		contr.clickOnSupplierCheckMark();
		contr.AddContact();
		contr.selectPricingType(ReadExcelData.getInstance("Contract").getStringValue("PricingType"));
		contr.selectTotalValue(ReadExcelData.getInstance("Contract").getStringValue("TotalValue"));
		contr.setUSDValue(ReadExcelData.getInstance("Contract").getStringValue("UsdValue"));
		contr.setAwardDate(ReadExcelData.getInstance("Contract").getStringValue("AwardDate"));
		contr.setEffectiveDate("21");
		contr.setExpiryDate(ReadExcelData.getInstance("Contract").getStringValue("AwardDate"));
		Assert.assertEquals(createSol.clickOnNextStep(), "Expiry must occur after effective date.");
	}

	@Test(description = "This test case will check Effective Date should always occur after Award Date", enabled = false)
	public void checkEffectiveDateShouldOccurAfterAwardDate() throws IOException {
		contNav.clickContractsSubMenu(ContractsSubMenu.CreateNewContract.toString());
		contr.selectContractType(ReadExcelData.getInstance("Contract").getStringValue("ContractType"));
		contr.setContractTitle(
				ReadExcelData.getInstance("Contract").getStringValue("ContractTitle") + System.currentTimeMillis());
		contr.clickSearchContractors();
		review.searchSupplier(ReadExcelData.getInstance("Contract").getStringValue("SupplierSearch"));
		contr.clickOnSupplierCheckMark();
		contr.AddContact();
		contr.selectPricingType(ReadExcelData.getInstance("Contract").getStringValue("PricingType"));
		contr.selectTotalValue(ReadExcelData.getInstance("Contract").getStringValue("TotalValue"));
		contr.setUSDValue(ReadExcelData.getInstance("Contract").getStringValue("UsdValue"));
		contr.setAwardDate(ReadExcelData.getInstance("Contract").getStringValue("AwardDate"));
		contr.setEffectiveDate("19");
		contr.setExpiryDate(ReadExcelData.getInstance("Contract").getStringValue("AwardDate"));
		Assert.assertEquals(createSol.clickOnNextStep(), "'Effective must occur after award date.'");
	}

	@AfterMethod
	public void tearDownAfterTest() {
		createSol.clickHomeButton();

	}

	@AfterClass
	public void tearDown() {
		ExtentReport.report.endTest(ExtentReport.logger);
		ExtentReport.report.flush();
		ExtentReport.report.close();
	}
}
