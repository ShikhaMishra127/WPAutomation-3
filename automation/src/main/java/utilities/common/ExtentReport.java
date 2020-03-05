package utilities.common;

import com.google.common.io.Files;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.*;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReport implements ITestListener,ISuiteListener {
    ResourceLoader environment = new ResourceLoader("qa");
    public static ExtentReports report;
    public static ExtentTest logger;

    private final Browser browser;

    public ExtentReport(WebDriver browser) {
        this.browser = (Browser) browser;
        report = new ExtentReports(environment.getValue("report_path"));
        report.loadConfig(new File(System.getProperty("user.dir") + "//ExtentReport.xml"));

    }

    public static String getScreenshot(WebDriver driver, String screenshotName) throws Exception {
        String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        // after execution, you could see a folder "FailedTestsScreenshots" under src
        // folder
        //String destination = "tmp/FailedTestsScreenshots/" + screenshotName + dateName
        String destination =   System.getProperty("user.dir")+"//failedtestscreenshots//" + screenshotName + dateName
                + ".png";
        File finalDestination = new File(destination);
        Files.copy(source, finalDestination);
        return destination;
    }

    @Override
    public void onTestStart(ITestResult result) {
        logger = ExtentReport.report.startTest(result.getName());

    }

    @Override
    public void onTestSuccess(ITestResult result) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onTestFailure(ITestResult result) {
        try {
            logger.log(LogStatus.FAIL,
                    logger.addScreenCapture(ExtentReport.logger.addScreenCapture(getScreenshot(browser.getDriver(), result.getName()))));


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        try {
            logger.log(LogStatus.FAIL,
                    logger.addScreenCapture(ExtentReport.logger.addScreenCapture(getScreenshot(browser.getDriver(), result.getName()))));


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        try {
            logger.log(LogStatus.FAIL,
                    logger.addScreenCapture(ExtentReport.logger.addScreenCapture(getScreenshot(browser.getDriver(), result.getName()))));


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
