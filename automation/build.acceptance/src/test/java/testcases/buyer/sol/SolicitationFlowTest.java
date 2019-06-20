package testcases.buyer.sol;


import junit.framework.Assert;
import main.java.framework.Solicitation;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageobjects.bidboard.SolicitationBidboardPOM;
import pageobjects.common.LoginPagePOM;
import pageobjects.vendor.common.VendorNavBarPOM;
import pageobjects.vendor.sol.VendorSolResponsePOM;
import utilities.common.Browser;
import utilities.common.TestRail;

import java.io.IOException;
import java.util.Map;

public class SolicitationFlowTest {

    Solicitation sol;
    TestRail tRail;

    @BeforeClass
    public void setup() throws IOException {
        sol = new Solicitation();
        tRail = new TestRail();
    }

    @Test
    public void CreateSolicitationTest() {

        // create a solicitation and come back with the sol number
        SolCreator creator = new SolCreator();
        sol = creator.CreateSolicitation("data/solicitation");

        tRail.UpdateTestcase("3550", TestRail.Status.PASSED, sol.getSolName() + " Created");
    }

    @Test(dependsOnMethods = {"CreateSolicitationTest"})
    public void VendorBidOnSolTest() {

        Browser browser = new Browser();
        LoginPagePOM login = new LoginPagePOM(browser);
        VendorNavBarPOM navbar = new VendorNavBarPOM(browser);
        VendorSolResponsePOM sol = new VendorSolResponsePOM(browser);

        browser.getDriver().get(browser.baseUrl);

        // log in and go to list of current solicitations
        login.loginAsUser("autosupplier","Automation123!");
        navbar.selectNavSolItemByBuyer("Perfect City", "Current");

        // Filter list, using our sol number from previous test
        browser.waitForElementToAppear(sol.viewBidNumberFilterEdit);
        sol.viewBidNumberFilterEdit.sendKeys(this.sol.getSolNumber());
        sol.viewFilterSubmitButton.click();

        // wait for our solicitation to show up. Check every 5 seconds, for at least 5 minutes
       sol.waitForSolToAppear(this.sol.getSolNumber());

        tRail.UpdateTestcase("3551", TestRail.Status.PASSED, this.sol.getSolName() + " Viewed");

        // get a list of WebElements we can use for our target solicitation (date, name, actions, etc.)
        Map<VendorSolResponsePOM.SolColumn, WebElement> targetSolItem = sol.getWebElementsBySol(this.sol.getSolNumber());

        // make sure target solicitation is Active (so we can bid on it)
        Assert.assertTrue("Sol STATUS OK",  targetSolItem.get(VendorSolResponsePOM.SolColumn.STATUS).getText().contains("Active"));

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

        navbar.vendorLogout();
        browser.close();

        tRail.UpdateTestcase("7060", TestRail.Status.PASSED, "Vendor bid on sol " + this.sol.getSolName());

    }

    @Test(dependsOnMethods = {"CreateSolicitationTest"})
    public void IndexSolicitationTest() {
        Browser browser = new Browser();
        SolicitationBidboardPOM board = new SolicitationBidboardPOM(browser);

        browser.getDriver().get(browser.solicitationUrl);

        sol.dumpSolInfo();

        board.waitForSol(sol.getSolNumber());

        WebElement ourline = board.getSolLineItem(sol.getSolNumber());

        browser.getSubElement(ourline, board.itemTitle).click();

        browser.waitForElementToAppear(board.summaryTitle);

        Assert.assertTrue("Sol Name OK", board.summaryTitle.getText().contains(sol.getSolName()));
        Assert.assertTrue("Sol Number OK", board.summaryInfo.getText().contains(sol.getSolNumber()));
        Assert.assertTrue("Sol Long Description OK", board.summaryLongDesc.getText().contains(sol.getSolLongDesc()));

        tRail.UpdateTestcase("11003", TestRail.Status.PASSED, sol.getSolName() + " confirmed on Bid Board");

        browser.close();

    }


}
