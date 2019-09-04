package testcases.buyer.sol;

import junit.framework.Assert;
import main.java.framework.Solicitation;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pageobjects.bidboard.SolicitationBidboardPOM;
import pageobjects.buyer.sol.BuyerAwardSolicitationPOM;
import pageobjects.buyer.sol.BuyerViewSolicitationPOM;
import pageobjects.common.BuyerNavBarPOM;
import pageobjects.common.LoginPagePOM;
import pageobjects.vendor.common.VendorNavBarPOM;
import pageobjects.vendor.sol.VendorSolResponsePOM;
import utilities.common.Browser;
import utilities.common.ResourceLoader;
import utilities.common.TestRailListener;
import utilities.common.TestRailReference;

import java.util.Map;

import static pageobjects.buyer.sol.BuyerViewSolicitationPOM.SolListColumn;
import static pageobjects.buyer.sol.BuyerViewSolicitationPOM.SolListColumn.ACTION;

@Listeners({TestRailListener.class})

public class SolicitationFlowTest {

    Solicitation sol;
    ResourceLoader resource;

    @BeforeClass
    public void setup() {

        sol = new Solicitation();
        resource = new ResourceLoader("data/solicitation");
    }

    @Test
    @TestRailReference(id=3608)
    public void CreateSolicitationTest(ITestContext testContext) {

        Browser browser = new Browser(testContext);
        SolCreator creator = new SolCreator();

        // create a solicitation and come back with the sol number

        // TEMP - preload sol with static sol data - DELETE ME AFTER IMPLEMENTING
        browser.close();
        sol.setSolNumber("568");
        sol.setSolName("TestSol");

 //       sol = creator.CreateSolicitation(browser, resource);

    }

    @Test(enabled = false, dependsOnMethods = {"CreateSolicitationTest"})
    @TestRailReference(id=3605)
    public void VendorBidOnSolTest(ITestContext testContext) {

        Browser browser = new Browser(testContext);
        LoginPagePOM login = new LoginPagePOM(browser);
        VendorNavBarPOM navbar = new VendorNavBarPOM(browser);
        VendorSolResponsePOM sol = new VendorSolResponsePOM(browser);

        browser.getDriver().get(browser.baseUrl);

        // log in and go to list of current solicitations
        login.loginAsUser(browser.supplierUsername, browser.supplierPassword);
        navbar.selectNavSolItemByBuyer("Perfect City", "Current");

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
        Assert.assertTrue("Verify Sol STATUS OK",  targetSolItem.get(VendorSolResponsePOM.SolColumn.STATUS).getText().contains("Active"));

        // click Add Response from the Action bar (ellipsis icon)
        browser.clickSubElement(targetSolItem.get(VendorSolResponsePOM.SolColumn.ACTION), sol.viewActionButton);
        browser.clickSubElement(targetSolItem.get(VendorSolResponsePOM.SolColumn.ACTION), sol.viewActionAddResponseButton );

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

    @Test(enabled = false, dependsOnMethods = {"VendorBidOnSolTest"})
    @TestRailReference(id=3609)
    public void VendorViewSolTest() {
        // If we bid on Solicitation, we obviously viewed it first. Each method should have only *one* test case
    }

    @Test(enabled = false, dependsOnMethods = {"CreateSolicitationTest"})
    @TestRailReference(id=5870)
    public void IndexSolicitationTest(ITestContext testContext) {
        Browser browser = new Browser(testContext);
        SolicitationBidboardPOM board = new SolicitationBidboardPOM(browser);

        browser.getDriver().get(browser.solicitationUrl);

        sol.dumpSolInfo();

        board.waitForSol(sol.getSolNumber());

        WebElement ourline = board.getSolLineItem(sol.getSolNumber());

        browser.getSubElement(ourline, board.itemTitle).click();

        browser.waitForElementToAppear(board.summaryTitle);

        Assert.assertTrue("Verify Sol Name", board.summaryTitle.getText().contains(sol.getSolName()));
        Assert.assertTrue("Verify Sol Number", board.summaryInfo.getText().contains(sol.getSolNumber()));
        Assert.assertTrue("Verify Sol Long Description", board.summaryLongDesc.getText().contains(sol.getSolLongDesc()));

        browser.Log(sol.getSolName() + " confirmed on Bid Board");
        browser.close();

    }

    //   @Test(dependsOnMethods = {"VendorBidOnSolTest"})
    @Test(dependsOnMethods = {"CreateSolicitationTest"})
    @TestRailReference(id = 3610)
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
        browser.clickWhenAvailable(view.searchActiveSolsTab);

        Map<SolListColumn, WebElement> solLine = view.getElementsForSolLine(sol.getSolNumber());

        browser.clickWhenAvailable(solLine.get(ACTION));
        browser.clickSubElement(solLine.get(ACTION), view.actionEvaluate);

        // if that list of bidders is in the way, minimize it
        browser.waitForElementToAppear(award.mainFooterArea);
        if (browser.elementExists(award.mainFooterCollapseButton)) {
            award.mainFooterCollapseButton.click();
        }

        browser.clickWhenAvailable(award.mainAwardAllToOneRadio);
        browser.clickWhenAvailable(award.mainContinueButton);

        award.ClickAwardSupplierCheckbox(resource.getValue("solsuppliername"));

        if(browser.elementExists(award.allPopupContinueButton)) {
            browser.clickWhenAvailable(award.allPopupContinueButton);
        }

        browser.clickWhenAvailable(award.allDoneButton);

        //navbar.logout();
       //browser.close();

    }


}
