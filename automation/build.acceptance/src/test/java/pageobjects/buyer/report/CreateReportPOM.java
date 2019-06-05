package pageobjects.buyer.report;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.common.Browser;

import java.io.IOException;

public class CreateReportPOM {

    private final Browser browser;

    public CreateReportPOM(WebDriver browser) throws IOException {
        this.browser = (Browser)browser;
        PageFactory.initElements(((Browser) browser).driver, this);

    }

    //// Report List page

    @FindBy(xpath = "//form[@name='frmReports']")
    public WebElement reportList;

    @FindBy(xpath = "//h3")
    public WebElement createReportPageHeader;

    @FindBy(xpath = "//iframe[@id='jasperViewFrame']")
    public WebElement jasperFrame;

    //// Jasper report page

    @FindBy(xpath = "//*[@id='dimensionsTree']")
    public WebElement jasperFieldList;

    @FindBy(xpath = "//*[@id='presentation']")
    public WebElement jasperPreviewButton;

    @FindBy(xpath = "//*[@id='canvas']")
    public WebElement jasperPreviewButton2;

    // WOO! has title of Report
    @FindBy(xpath = "//*[@id='canvas']//div[contains(@class,'title')]")
    public WebElement jasperReportTitle;

    @FindBy(xpath = "//*[@id='explorer']")
    public WebElement jasperPreviewButtonReset;

    ///////////////////////////////////////////////////

    public void selectReportByName(String reportName) {

        String xpath = "//a[contains(text(),'" + reportName + "')]";

        // find report with report string
        browser.waitForElementToAppear(By.xpath(xpath));

        WebElement reportLink = reportList.findElement(By.xpath(xpath));
        browser.clickWhenAvailable(reportLink);

        // Wait for the page to load before leaving
        browser.waitForPageLoad();
    }
}