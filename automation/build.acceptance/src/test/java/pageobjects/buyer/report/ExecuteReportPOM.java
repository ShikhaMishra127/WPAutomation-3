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

    //// Report List page

    @FindBy(xpath = "//table[@class='table table-bordered table-striped no-footer']//tbody")
    public WebElement reportList;

    // Report Parameters page
    @FindBy (xpath="//h3")
    public WebElement reportParameterHeader;

    @FindBy (xpath="//input[@id='txtReportTitle']")
    public WebElement reportTitleEdit;

    @FindBy (xpath="//input[@id='selectAllBorg']")
    public WebElement reportAllBorgsCheckbox;

    @FindBy (xpath="//input[@id='selectAll']")
    public WebElement reportAllStatusCheckbox;

    @FindBy (xpath="//input[@id='id_CalfromBeginDate']")
    public WebElement reportFromDate;

    @FindBy (xpath="//input[@id='id_CaltoEndDate']")
    public WebElement reportToDate;

    @FindBy (xpath="//button[@title='Submit']")
    public WebElement submitButton;

    @FindBy (xpath="//button[@title='Back']")
    public WebElement backButton;

    //// pop-up HTML report

    @FindBy (xpath="//tr[@class='ReportHeader']/td/font")
    public WebElement HTMLReportHeader;

    @FindBy (xpath="//td[@class='ReportSubheader']")
    public WebElement HTMLReportSubHeader;


    ///////////////////////////////////////////////////

    public void selectReportByName(String headerName, String reportName) {

        // find group of reports by header string (triangle icon)
        WebElement reportHeaderLink = reportList.findElement(By.xpath("//*[text()[contains(.,'"+headerName+"')]]/a"));
        reportHeaderLink.click();

        // find report with report string
        WebElement reportLink = reportList.findElement(By.xpath("//a[contains(text(),'"+reportName+"')]"));
        reportLink.click();

        // Wait for the page to load before leaving
        browser.waitForPageLoad();
    }
}
