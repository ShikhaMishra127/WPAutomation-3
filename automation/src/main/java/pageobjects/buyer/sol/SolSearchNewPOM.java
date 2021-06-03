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

    @FindBy(xpath = "//label[contains(text(),'Formal Solicitations')]")
    public WebElement formalFilterCheckBox;

    @FindBy(xpath = "//label[contains(text(),'Informal Solicitations')]")
    public WebElement informalFilterCheckBox;

    @FindBy(xpath = "//button[contains(text(),'Clear All')]")
    public WebElement clearAllFiltersButton;

    //crappy duplications cause one method accept WebElement and the second one accepts By
    String selectedFilters = "//*[@id='applied-filter-box']//..//span";
    @FindBy(xpath = "//*[@id='applied-filter-box']//..//span")
    public WebElement selectedFiltersDuplicate;

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

    //TODO probably better to rewrite using ENUMS
    public SolSearchNewPOM selectFilter(String filter) {

        switch (filter) {
            case "formal":
                browser.ScrollElementIntoView(formalFilterCheckBox);
                browser.clickWhenAvailable(formalFilterCheckBox);
                break;
            case "informal":
                browser.ScrollElementIntoView(informalFilterCheckBox);
                browser.clickWhenAvailable(informalFilterCheckBox);
                break;
        }
        return this;
    }

    public SolSearchNewPOM clearAllFilters() {
        browser.clickWhenAvailable(clearAllFiltersButton);
        return this;
    }

    public SolSearchNewPOM checkSelectedFiltersText(String activeFilters) {
        browser.waitForElementToContainText(selectedFiltersDuplicate, activeFilters);
        String filters =  browser.findElement(By.xpath(selectedFilters)).getText().trim();
        browser.Assert("Check that selected filters is active ", filters, activeFilters);
        return this;
    }
}
