package commonutils.pageobjects.utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import com.google.common.io.Files;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ExtentReport implements ITestListener,ISuiteListener {
	public static ExtentReports report;
	public static ExtentTest logger;

	public ExtentReport() {
		report = new ExtentReports(ReadConfig.getInstance().getAutomationReportPath(), true);
		report.loadConfig(new File(System.getProperty("user.dir") + "//ExtentReport.xml"));

	}

	public static String getScreenshot(WebDriver driver, String screenshotName) throws Exception {
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		// after execution, you could see a folder "FailedTestsScreenshots" under src
		// folder
		  //String destination = "tmp/FailedTestsScreenshots/" + screenshotName + dateName
		String destination =   "/tmp/FailedTestsScreenshots/" + screenshotName + dateName
				+ ".png";
		File finalDestination = new File(destination);
		Files.copy(source, finalDestination);
		return destination;
	}

	@Override
	public void onTestStart(ITestResult result) {
		ExtentReport.logger = ExtentReport.report.startTest(result.getName());

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestFailure(ITestResult result) {
		try {
			logger.log(LogStatus.FAIL,
					logger.addScreenCapture(ExtentReport.logger.addScreenCapture(getScreenshot(PCDriver.getDriver(), result.getName()))));
			

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		try {
			logger.log(LogStatus.FAIL,
					logger.addScreenCapture(ExtentReport.logger.addScreenCapture(getScreenshot(PCDriver.getDriver(), result.getName()))));
			

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		try {
			logger.log(LogStatus.FAIL,
					logger.addScreenCapture(ExtentReport.logger.addScreenCapture(getScreenshot(PCDriver.getDriver(), result.getName()))));
			

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFinish(ITestContext context) {
		//PCDriver.getDriver().quit();
		//ExtentReport.report.flush();
		//ExtentReport.report.close();

	}
	
		@Override
	public void onStart(ISuite suite) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ISuite suite) {
		//PCDriver.getDriver().quit();
				
	}

}
