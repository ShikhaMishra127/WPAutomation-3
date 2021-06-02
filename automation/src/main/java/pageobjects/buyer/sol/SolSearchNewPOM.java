package pageobjects.buyer.sol;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.common.Browser;

import java.util.List;

public class SolSearchNewPOM {

    private final Browser browser;

    public SolSearchNewPOM(WebDriver browser) {
        this.browser = (Browser) browser;
        PageFactory.initElements(((Browser) browser).driver, this);
    }

    @FindBy(xpath="//button[contains(text(),'Search')]")
    public WebElement searchSolButton;

    @FindBy(xpath = "//button[contains(text(),'Reset')]")
    public WebElement resetButton;

    @FindBy(xpath = "//*[@id='input-search-box']")
    public WebElement searchInput;

    String searchAmount = "//div[@class='row search-result']";

    String firstSearchResult = "//div[@class='row search-result']/div/a";

    String noResultFound = "//div[@class='text-center text-warning']/h3";


    public void checkThatSearchButtonDisplayed() {
        browser.waitForElementToAppear(searchSolButton);
        browser.Assert("Search button is displayed", browser.elementExists(searchSolButton));
    }
    public void checkThatResetButtonDisplayed() {
        browser.waitForElementToAppear(resetButton);
        browser.Assert("Search button is displayed", browser.elementExists(resetButton));
    }

    public SolSearchNewPOM pressSearchButton() {
        browser.clickWhenAvailable(searchSolButton);
        return this;
    }

    public SolSearchNewPOM waitForSomeResultsIsDisplayed() {
        browser.waitForElementToAppear(By.xpath(searchAmount));
        return this;
    }

    public SolSearchNewPOM pressResetButton() {
        browser.clickWhenAvailable(resetButton);
        return this;
    }

    public int getSearchResultAmount() {
        browser.waitForPageLoad();
        browser.waitForElementToAppear(By.xpath(searchAmount));
        List<WebElement> searchResult = browser.findElements(By.xpath(searchAmount));
        return searchResult.size();
    }

    public SolSearchNewPOM enterSearchKeyword(String keyword) {
        browser.sendKeysWhenAvailable(searchInput, keyword);
        return this;
    }

    public SolSearchNewPOM checkFirstSearchResult(String solName) {
        browser.waitForPageLoad();
        //find number of solicitation to merge it with name
        List<WebElement> searchListSolNumber = browser.findElements(By.xpath("//div[@class='row search-result']//span[@class='text-primary']/b"));
        String firstNumber = searchListSolNumber.get(0).getText();
        //get name of first search name that is displayed in list
        List<WebElement> searchList = browser.findElements(By.xpath("//span[@class='text-primary']"));
        WebElement firstSearchResult = searchList.get(0);
        String searchRequest = firstSearchResult.getText();
        //merge sol number with name to match it on a page
        String modifiedString = firstNumber + " | " +solName;
        browser.AssertEquals("Check first name search result", searchRequest, modifiedString);
        return this;
    }

    public SolSearchNewPOM clickFirstSearchResult() {
        browser.waitForElementToAppear(By.xpath(firstSearchResult));
        browser.findElement(By.xpath(firstSearchResult)).click();
        return this;
    }

    public String checkMessageNoResultFound() {
        browser.waitForPageLoad();
        browser.waitForElementToAppear(By.xpath(noResultFound));
        return browser.findElement(By.xpath(noResultFound)).getText();
    }
}
