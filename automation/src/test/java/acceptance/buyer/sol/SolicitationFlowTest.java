package acceptance.buyer.sol;

import main.java.framework.Solicitation;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageobjects.bidboard.SolicitationBidboardPOM;
import pageobjects.buyer.common.BuyerNavBarPOM;
import pageobjects.buyer.sol.*;
import pageobjects.common.LoginPagePOM;
import pageobjects.vendor.common.VendorNavBarPOM;
import pageobjects.vendor.sol.VendorSolResponsePOM;
import utilities.common.Browser;
import utilities.common.ResourceLoader;
import utilities.common.TestRailReference;

import java.util.Map;

import static pageobjects.buyer.sol.BuyerViewSolicitationPOM.SolListColumn;
import static pageobjects.buyer.sol.BuyerViewSolicitationPOM.SolListColumn.ACTION;

public class SolicitationFlowTest {

    Solicitation sol;
    ResourceLoader resource;

    @BeforeClass
    public void setup() {

        sol = new Solicitation();
        resource = new ResourceLoader("data/solicitation");
    }

    @Test
    @TestRailReference(id = 3550)
    public void CreateSolicitationTest(ITestContext testContext) {

        Browser browser = new Browser(testContext);
        SolCreator creator = new SolCreator();

        sol = creator.CreateSolicitation(browser, resource);
    }

    @Test(enabled = true, dependsOnMethods = {"CreateSolicitationTest"})
    @TestRailReference(id = 7060)
    public void VendorBidOnSolTest(ITestContext testContext) {

        Browser browser = new Browser(testContext);
        LoginPagePOM login = new LoginPagePOM(browser);
        VendorNavBarPOM navbar = new VendorNavBarPOM(browser);
        VendorSolResponsePOM sol = new VendorSolResponsePOM(browser);

        browser.getDriver().get(browser.baseUrl);

        // log in and go to list of current solicitations
        login.loginAsUser(browser.supplierUsername, browser.supplierPassword);
        navbar.selectNavDropByBuyer(browser.buyerName, "Solicitations","Current");

        // Filter list, using our sol number from previous test
        browser.waitForElementToAppear(sol.viewBidNumberFilterEdit);
        sol.viewBidNumberFilterEdit.sendKeys(this.sol.getSolNumber());
        sol.viewFilterSubmitButton.click();

        // wait for our solicitation to show up. Check every 5 seconds, for at least 5 minutes
        sol.waitForSolToAppear(this.sol.getSolNumber());

        browser.Log("Solicitation '" + this.sol.getSolName() + "' viewed by vendor");

        // get a list of WebElements we can use for our target solicitation (date, name, actions, etc.)
        Map<VendorSolResponsePOM.SolColumn, WebElement> targetSolItem = sol.getWebElementsBySol(this.sol.getSolNumber());

        // make sure target solicitation is Active (so we can bid on it)
        browser.Assert("Verify Sol STATUS OK", targetSolItem.get(VendorSolResponsePOM.SolColumn.STATUS).getText(), "Active");

        // click Add Response from the Action bar (ellipsis icon)
        browser.clickSubElement(targetSolItem.get(VendorSolResponsePOM.SolColumn.ACTION), sol.viewActionButton);
        browser.clickSubElement(targetSolItem.get(VendorSolResponsePOM.SolColumn.ACTION), sol.viewActionAddResponseButton);

        // click OK when it suggests a quote name
        browser.clickWhenAvailable(sol.bidQuoteNameOkButton);

        // go to overview tab and click accept all documents (if not already done)
        browser.clickWhenAvailable(sol.bidOverviewTab);

        if (browser.elementExists(sol.overCheckAllLink)) {
            sol.overCheckAllLink.click();
            sol.overAcceptButton.click();
        }

        // wait for "loading" overlay to disappear, then click Respond tab
        browser.waitForElementToDisappear(By.xpath("//div[@class='blockUI blockOverlay']"));
        browser.clickWhenAvailable(sol.bidRespondTab);
        browser.waitForPageLoad();

        // bid on item and click Save
        browser.waitForElementToAppear(sol.respondPriceEdit);

        sol.respondPriceEdit.sendKeys("34.52");
        sol.respondSaveButton.click();

        // go to review response and click Submit
        browser.clickWhenAvailable(sol.bidReviewTab);
        browser.clickWhenAvailable(sol.reviewSubmitButton);

        // say Yes to No Bid items and finish with bid, by closing it
        browser.clickWhenAvailable(sol.reviewMarkNoBidButton);
        browser.clickWhenAvailable(sol.reviewCloseButton);

        browser.Log("Vendor bid on solicitation " + this.sol.getSolName());

        navbar.vendorLogout();
        browser.close();
    }

    @Test(enabled = true, dependsOnMethods = {"VendorBidOnSolTest"})
    @TestRailReference(id = 3551)
    public void VendorViewSolTest() {
        // If we bid on Solicitation, we obviously viewed it first. Each method should have only *one* test case
    }

    @Test(enabled = true, dependsOnMethods = {"CreateSolicitationTest"})
    @TestRailReference(id = 11003)
    public void IndexSolicitationTest(ITestContext testContext) {
        Browser browser = new Browser(testContext);
        SolicitationBidboardPOM board = new SolicitationBidboardPOM(browser);

        browser.getDriver().get(browser.solicitationUrl);

        sol.dumpSolInfo();

        board.waitForSol(sol.getSolNumber());

        WebElement ourline = board.getSolLineItem(sol.getSolNumber());

        browser.getSubElement(ourline, board.itemTitle).click();

        browser.waitForElementToAppear(board.summaryTitle);

        browser.Assert("Verify Sol Name", board.summaryTitle.getText(), sol.getSolName());
        browser.Assert("Verify Sol Number", board.summaryInfo.getText(), sol.getSolNumber());
        browser.Assert("Verify Sol Long Description", board.summaryLongDesc.getText(), sol.getSolLongDesc());

        browser.Log(sol.getSolName() + " confirmed on Bid Board");
        browser.close();

    }

    @Test(enabled = true, dependsOnMethods = {"VendorBidOnSolTest"})
    @TestRailReference(id = 3552)
    public void AwardSolTest(ITestContext testContext) {

        Browser browser = new Browser(testContext);
        LoginPagePOM login = new LoginPagePOM(browser);
        BuyerNavBarPOM navbar = new BuyerNavBarPOM(browser);
        BuyerViewSolicitationPOM view = new BuyerViewSolicitationPOM(browser);
        BuyerAwardSolicitationPOM award = new BuyerAwardSolicitationPOM(browser);

        browser.getDriver().get(browser.baseUrl);

        login.loginAsBuyer();

        // Navigate to Solicitations > Review/Award Solicitations > Active Sols
        navbar.selectDropDownItem("Solicitations", "Award Informal Solicitation");

        // TEMP -remove when done
        browser.clickWhenAvailable(view.searchActiveSolsTab);
        browser.sendKeysWhenAvailable(view.searchSolNumberEdit, sol.getSolNumber());
        browser.clickWhenAvailable(view.searchUpdateFilterButton);

        Map<SolListColumn, WebElement> solLine = view.getElementsForSolLine(sol.getSolNumber());

        browser.clickSubElement(solLine.get(ACTION), view.actionEllipsis);
        browser.clickSubElement(solLine.get(ACTION), view.actionEvaluate);

        // if that list of bidders is in the way, minimize it
        browser.waitForElementToAppear(award.mainFooterArea);
        if (browser.elementExists(award.mainFooterCollapseButton)) {
            award.mainFooterCollapseButton.click();
        }

        // click Award All to One option
        browser.clickWhenAvailable(award.mainAwardAllToOneRadio);
        browser.clickWhenAvailable(award.mainContinueButton);

        // click on checkbox for target vendor and award
        award.ClickAwardSupplierCheckbox(browser.supplierName);

        // wait for the stupid pop-up to appear, if there is one
        browser.waitForElementToBeClickable(award.allPopupContinueButton);

        if (browser.elementExists(award.allPopupContinueButton)) {
            browser.clickWhenAvailable(award.allPopupContinueButton);  // pop-up sometimes appears, warning of partial bid
        }

        // wait for overlay with "Saving" spinner to disappear before continuing
        browser.waitForElementToBeClickable(award.allDoneButton);

        browser.clickWhenAvailable(award.allDoneButton);
        browser.clickWhenAvailable(award.summaryDoneButton);

        browser.Log(sol.getSolName() + " awarded.");

        // while back on the sol list page, click Finalize sol on action bar
        solLine = view.getElementsForSolLine(sol.getSolNumber());

        browser.clickSubElement(solLine.get(ACTION), view.actionEllipsis);
        browser.clickSubElement(solLine.get(ACTION), view.actionFinalize);

        // type 'YES' to confirm award and click Submit
        browser.sendKeysWhenAvailable(award.finalizeConfirmEdit, "YES");
        browser.clickWhenAvailable(award.finalizeSubmitButton);

        // when asked to Create Req, click Close
        browser.clickWhenAvailable(award.finalizeCloseButton);

        browser.Log(sol.getSolName() + " finalized.");

        navbar.logout();
        browser.close();
    }

    @Test(dependsOnMethods = {"CreateSolicitationTest"})
    @TestRailReference(id = 118665)
    public void searchForSolName(ITestContext testContext) {
        Browser browser = new Browser(testContext);
        LoginPagePOM login = new LoginPagePOM(browser);
        BuyerNavBarPOM navbar = new BuyerNavBarPOM(browser);
        SolSearchNewPOM search = new SolSearchNewPOM(browser);

        browser.getDriver().get(browser.baseUrl);

        String solName = sol.getSolName();

        // log in and go to list of current solicitations
        login.loginAsBuyer();
        //Open search form and submit some search keywords
        navbar.selectDropDownItem("Solicitations", "Solicitation Search");

        search.enterSearchKeyword(solName)
                .pressSearchButton()
                .waitForSomeResultsIsDisplayed()
                .checkFirstSearchResult(solName)
                .pressResetButton();
        int searchAmount = search.getSearchResultAmount();
        Assert.assertEquals(searchAmount, 10);

        browser.close();
    }

    @Test(dependsOnMethods = {"CreateSolicitationTest"})
    @TestRailReference(id = 118665)
    public void searchForSolNumber(ITestContext testContext) {
        Browser browser = new Browser(testContext);
        LoginPagePOM login = new LoginPagePOM(browser);
        BuyerNavBarPOM navbar = new BuyerNavBarPOM(browser);
        SolSearchNewPOM search = new SolSearchNewPOM(browser);

        browser.getDriver().get(browser.baseUrl);

        String solName = sol.getSolName();
        String solNumber = sol.getSolNumber();

        // log in and go to list of current solicitations
        login.loginAsBuyer();
        //Open search form and submit some search keywords
        navbar.selectDropDownItem("Solicitations", "Solicitation Search");

        search.enterSearchKeyword(solNumber)
                .pressSearchButton()
                .waitForSomeResultsIsDisplayed()
                .checkFirstSearchResult(solName)
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

        search.waitForSomeResultsIsDisplayed()
                .enterSearchKeyword("2100166721001667");

        //check that message about no results is displayed
        String noResultFoundMessage = search.checkMessageNoResultFound();
        browser.AssertEquals("Check that no results message is displayed ", noResultFoundMessage, "Oops! No results found.");

        browser.close();
    }

    @Test(dependsOnMethods = {"CreateSolicitationTest"})
    @TestRailReference(id = 118665)
    public void openAndViewSolicitation(ITestContext testContext) {
        Browser browser = new Browser(testContext);
        LoginPagePOM login = new LoginPagePOM(browser);
        BuyerNavBarPOM navbar = new BuyerNavBarPOM(browser);
        SolSearchNewPOM search = new SolSearchNewPOM(browser);
        SolViewPOM view = new SolViewPOM(browser);

        browser.getDriver().get(browser.baseUrl);

        String solName = sol.getSolName();
        String solNumber = sol.getSolNumber();

        // log in and go to list of current solicitations
        login.loginAsBuyer();
        //Open search form and submit some search keywords
        navbar.selectDropDownItem("Solicitations", "Solicitation Search");

        search.enterSearchKeyword(solName)
                .pressSearchButton()
                .waitForSomeResultsIsDisplayed()
                .checkFirstSearchResult(solName)
                .clickFirstSearchResult();
        view.checkSolSummary("Edit Informal Solicitation View : "+solNumber+" - "+solName+" (Informal)")
                .checkSolTitle(solName)
                .checkDescription("This is a long description for "+solName+"");

        browser.close();
    }

    @Test(dependsOnMethods = {"CreateSolicitationTest"})
    @TestRailReference(id = 118665)
    public void checkSearchFilters(ITestContext testContext) {
        Browser browser = new Browser(testContext);
        LoginPagePOM login = new LoginPagePOM(browser);
        BuyerNavBarPOM navbar = new BuyerNavBarPOM(browser);
        SolSearchNewPOM search = new SolSearchNewPOM(browser);

        browser.getDriver().get(browser.baseUrl);

        String solName = sol.getSolName();
        String solNumber = sol.getSolNumber();

        // log in and go to list of current solicitations
        login.loginAsBuyer();
        //Open search form and submit some search keywords
        navbar.selectDropDownItem("Solicitations", "Solicitation Search");

        search.enterSearchKeyword(solName)
                .pressSearchButton()
                .waitForSomeResultsIsDisplayed()
                .checkSelectedFiltersText("No filters are applied yet.")
                .selectFilter("informal")
                .waitForSomeResultsIsDisplayed()
                .checkSelectedFiltersText("Informal Solic...")
                .waitForSomeResultsIsDisplayed();

        int searchResult = search.getSearchResultAmount();
        browser.AssertEquals("Check that amount of results is displayed", searchResult, 10);

        search.clearAllFilters()
                .checkSelectedFiltersText("No filters are applied yet.")
                .waitForSomeResultsIsDisplayed();

        browser.close();
    }
}
