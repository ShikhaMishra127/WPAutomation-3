package testcases;

import java.io.IOException;

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
	HomePage home=new HomePage();
	EditSolicitationPageObject edit=new EditSolicitationPageObject();
	CreateSolicitationPOM createSol=new CreateSolicitationPOM();
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
	//@Test
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
	
	
	@Test
	public void EditFormalSolicitationUsingTitleAndSubmit() throws IOException {
		home.clickFormalSolicitationEdit();
	edit.setTitleForSearch(ReadExcelData.getInstance("Solicitation").getStringValue("Title"));	
	edit.clickOnFilter();
	edit.clickOnThreeDots();
	edit.clickEdit();
	edit.clickTopNavItem("Edit Header");
	edit.clickSave();
	edit.clickReturn();
	createSol.clickSubmit();
	
	}
}
