package pageobjects.buyer.report;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.common.Browser;

import java.io.IOException;

public class ExecuteReportPOM {
    private final Browser browser;

    public ExecuteReportPOM(WebDriver browser) throws IOException {
        this.browser = (Browser)browser;
        PageFactory.initElements(((Browser) browser).driver, this);
    }

    @FindBy(xpath = "//table[@class='table table-bordered table-striped no-footer']//tbody")
    public WebElement reportList;

    public void selectReportByName(String headerName, String reportName) {

        // find group of reports by header string
        WebElement reportHeaderLink = reportList.findElement(By.xpath("//*[text()[contains(.,'"+headerName+"')]]/a"));
        reportHeaderLink.click();

        // find report with report string
        WebElement reportLink = reportList.findElement(By.xpath("//a[contains(text(),'"+reportName+"')]"));
        reportLink.click();

        // Wait for the page to load before leaving
        browser.waitForPageLoad();
    }
}
