package com.PerfectCommerce.TestCases;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.PerfectCommerce.utils.BrowserUtils;
import com.PerfectCommerce.utils.ExtentReport;
import com.relevantcodes.extentreports.LogStatus;

public class HomePageTest {
	//HomePage home=new HomePage();
	BrowserUtils utils=new BrowserUtils();
	ExtentReport extent=new ExtentReport(this.getClass().getName());

	@BeforeClass
	@Parameters({"browser"})

	public void setup(String browser) {
		utils.init(browser);
		extent.logger.log(LogStatus.INFO, "Anshul");
		//log.info("Browser Invoked");

	}
	
	@Test

	public void openHomepage() {
		System.out.println("Passed");
	}
	
	@AfterClass
	public void tearDown() {
		extent.report.endTest(extent.logger);
		extent.report.flush();
	
	}
}
