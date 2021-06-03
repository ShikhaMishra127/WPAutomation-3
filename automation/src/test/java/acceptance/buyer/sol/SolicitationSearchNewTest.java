package acceptance.buyer.sol;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageobjects.buyer.common.BuyerNavBarPOM;
import pageobjects.buyer.sol.SolSearchNewPOM;
import pageobjects.buyer.sol.SolViewPOM;
import pageobjects.common.LoginPagePOM;
import utilities.common.Browser;
import utilities.common.TestRailReference;

public class SolicitationSearchNewTest {

    @Test
    @TestRailReference(id = 118665)
    public void openNewSearchPage (ITestContext testContext) {
        Browser browser = new Browser(testContext);
        LoginPagePOM login = new LoginPagePOM(browser);
        BuyerNavBarPOM navbar = new BuyerNavBarPOM(browser);
        SolSearchNewPOM search = new SolSearchNewPOM(browser);

        browser.getDriver().get(browser.baseUrl);

        // log in and go to list of current solicitations
        login.loginAsBuyer();
        //Open search form and check that is search button is displayed
        navbar.selectDropDownItem("Solicitations", "Solicitation Search");
        search.checkThatSearchButtonDisplayed();
        search.checkThatResetButtonDisplayed();

        String searchText = search.searchSolButton.getText();
        browser.AssertEquals("Check that Search button has proper text: ", searchText, "Search");
        String resetButton = search.resetButton.getText();
        browser.AssertEquals("Check that Reset button has proper text: ", resetButton, "Reset");
        int searchAmount = search.getSearchResultAmount();
        browser.AssertEquals("Check that amount of results is displayed", searchAmount, 10);

        browser.close();
    }

    @DataProvider(name = "solicitation-provider")
    public Object[][] solicitations(){
        return new Object[][] {
                //this data provider contains next data
                //first string is search keyword, as we can search by number and by name
                //and the second is name - we should get sol name even we searching by number
                {"Automation 123456789", "Automation 123456789"},
                {"21001667", "Automation 123456789"}
        };
    }

    @DataProvider(name = "filters-provider")
    public Object[][] filters(){
        return new Object[][] {
                //this data provider contains next data
                //first string is search keyword
                //and the second is filter name that we need to check in filters block
                //third one - filter type for filter selector
                {"21001667", "Formal Solicit...", "formal"},
                {"190920.032047", "Informal Solic...", "informal"}
        };
    }

    @Test(dataProvider = "solicitation-provider")
    @TestRailReference(id = 118665)
    public void searchForSolName(ITestContext testContext, String searchKeyword, String expectedSearchResult) {
        Browser browser = new Browser(testContext);
        LoginPagePOM login = new LoginPagePOM(browser);
        BuyerNavBarPOM navbar = new BuyerNavBarPOM(browser);
        SolSearchNewPOM search = new SolSearchNewPOM(browser);

        browser.getDriver().get(browser.baseUrl);

        // log in and go to list of current solicitations
        login.loginAsBuyer();
        //Open search form and submit some search keywords
        navbar.selectDropDownItem("Solicitations", "Solicitation Search");

        search.enterSearchKeyword(searchKeyword)
                .pressSearchButton()
                .waitForSomeResultsIsDisplayed()
                .checkFirstSearchResult(expectedSearchResult)
                .pressResetButton();
        int searchAmount = search.getSearchResultAmount();
        Assert.assertEquals(searchAmount, 10);

        browser.close();
    }

    @Test
    @TestRailReference(id = 118665)
    public void searchNoResultsIsFound(ITestContext testContext) {
        Browser browser = new Browser(testContext);
        LoginPagePOM login = new LoginPagePOM(browser);
        BuyerNavBarPOM navbar = new BuyerNavBarPOM(browser);
        SolSearchNewPOM search = new SolSearchNewPOM(browser);

        browser.getDriver().get(browser.baseUrl);

        // log in and go to list of current solicitations
        login.loginAsBuyer();
        //Open search form and submit some search keywords
        navbar.selectDropDownItem("Solicitations", "Solicitation Search");

        search.enterSearchKeyword("2100166721001667")
                .pressSearchButton();

        //check that message about no results is displayed
        String noResultFoundMessage = search.checkMessageNoResultFound();
        browser.AssertEquals("Check that no results message is displayed ", noResultFoundMessage, "Oops! No results found.");

        browser.close();
    }

    @Test
    @TestRailReference(id = 118665)
    public void openAndViewSolicitation(ITestContext testContext) {
        Browser browser = new Browser(testContext);
        LoginPagePOM login = new LoginPagePOM(browser);
        BuyerNavBarPOM navbar = new BuyerNavBarPOM(browser);
        SolSearchNewPOM search = new SolSearchNewPOM(browser);
        SolViewPOM view = new SolViewPOM(browser);

        browser.getDriver().get(browser.baseUrl);

        // log in and go to list of current solicitations
        login.loginAsBuyer();
        //Open search form and submit some search keywords
        navbar.selectDropDownItem("Solicitations", "Solicitation Search");

        search.enterSearchKeyword("Automation 123456789")
                .pressSearchButton()
                .waitForSomeResultsIsDisplayed()
                .checkFirstSearchResult("Automation 123456789")
                .clickFirstSearchResult();
        view.checkSolSummary("Edit Formal Solicitation View : 21001667 - Automation 123456789 (Formal)")
                .checkSolTitle("Automation 123456789")
                .checkDescription("Some test description")
                .checkJustification("Some test justification");

        browser.close();
    }

    @Test(dataProvider = "filters-provider")
    @TestRailReference(id = 118665)
    public void checkSearchFilters(ITestContext testContext, String solNumber, String filterName, String filter) {
        Browser browser = new Browser(testContext);
        LoginPagePOM login = new LoginPagePOM(browser);
        BuyerNavBarPOM navbar = new BuyerNavBarPOM(browser);
        SolSearchNewPOM search = new SolSearchNewPOM(browser);

        browser.getDriver().get(browser.baseUrl);

        // log in and go to list of current solicitations
        login.loginAsBuyer();
        //Open search form and submit some search keywords
        navbar.selectDropDownItem("Solicitations", "Solicitation Search");

        search.enterSearchKeyword(solNumber)
                .pressSearchButton()
                .waitForSomeResultsIsDisplayed()
                .checkSelectedFiltersText("No filters are applied yet.")
                .selectFilter(filter)
                .waitForSomeResultsIsDisplayed()
                .checkSelectedFiltersText(filterName)
                .waitForSomeResultsIsDisplayed();

        int searchResult = search.getSearchResultAmount();
        browser.AssertEquals("Check that amount of results is displayed", searchResult, 1);

        search.clearAllFilters()
                .checkSelectedFiltersText("No filters are applied yet.")
                .waitForSomeResultsIsDisplayed();

        browser.close();
    }
}
