package pageobjects.utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.google.common.io.Files;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ExtentReport implements ITestListener {
	public static ExtentReports report;
	public static ExtentTest logger;

	public ExtentReport() {
		report = new ExtentReports("D://WebProcureAutomation.html", true);
		report.loadConfig(new File(System.getProperty("user.dir") + "//ExtentReport.xml"));

	}

	public static String getScreenhot(WebDriver driver, String screenshotName) throws Exception {
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		// after execution, you could see a folder "FailedTestsScreenshots" under src
		// folder
		String destination = System.getProperty("user.dir") + "/FailedTestsScreenshots/" + screenshotName + dateName
				+ ".png";
		File finalDestination = new File(destination);
		Files.copy(source, finalDestination);
		return destination;
	}

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestFailure(ITestResult result) {
		try {
			logger.log(LogStatus.FAIL,
					logger.addScreenCapture(ExtentReport.getScreenhot(PCDriver.getDriver(), result.getName())));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		try {
			logger.log(LogStatus.FAIL,
					logger.addScreenCapture(ExtentReport.getScreenhot(PCDriver.getDriver(), result.getName())));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		try {
			logger.log(LogStatus.FAIL,
					logger.addScreenCapture(ExtentReport.getScreenhot(PCDriver.getDriver(), result.getName())));

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
		// TODO Auto-generated method stub

	}

}
