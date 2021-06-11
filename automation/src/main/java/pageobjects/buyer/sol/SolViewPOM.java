package pageobjects.buyer.sol;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import utilities.common.Browser;

public class SolViewPOM {
    private final Browser browser;

    public SolViewPOM(WebDriver browser) {
        this.browser = (Browser) browser;
        PageFactory.initElements(((Browser) browser).driver, this);
    }
    //TODO Probably this block need to be rewrited with method that accept ENUMS and work as case/switch

    public SolViewPOM checkSolSummary(String summary) {
        String summaryLocator = "//*[@id='page-title']//..//h3";

        browser.waitForElementToAppear(By.xpath(summaryLocator));
        String summaryText = browser.findElement(By.xpath(summaryLocator)).getText();
        browser.AssertEquals("Check that proper summary is displayed: ", summary, summaryText);
        return this;
    }

    public SolViewPOM checkSolTitle(String title) {
        String titleLocator = "//*[@id='overViewTable']/tbody/tr[4]/td[2]";

        browser.waitForElementToAppear(By.xpath(titleLocator));
        String titleText = browser.findElement(By.xpath(titleLocator)).getText().trim();
        browser.AssertEquals("Check that proper title is displayed: ", title, titleText);
        return this;
    }

    public SolViewPOM checkDescription(String description) {
        String descriptionLocator = "//*[contains(text(),'"+description+"')]";

        browser.waitForElementToAppear(By.xpath(descriptionLocator));
        String descriptionText = browser.findElement(By.xpath(descriptionLocator)).getText().trim();
        browser.AssertEquals("Check that proper description is displayed: ", description, descriptionText);
        return this;
    }

    public SolViewPOM checkJustification(String justification) {
        String justificationLocator = "//*[contains(text(),'"+justification+"')]";

        browser.waitForElementToAppear(By.xpath(justificationLocator));
        String justificationText = browser.findElement(By.xpath(justificationLocator)).getText().trim();
        browser.AssertEquals("Check that proper justification is displayed: ", justification, justificationText);
        return this;
    }
}
