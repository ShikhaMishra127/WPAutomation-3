package testcases.buyer.sol;


import main.java.framework.Solicitation;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageobjects.common.LoginPagePOM;
import pageobjects.vendor.common.VendorNavBarPOM;
import pageobjects.vendor.sol.VendorSolResponsePOM;
import utilities.common.Browser;

import java.io.IOException;
import java.util.Map;

public class SolicitationFlowTest {

    Solicitation sol;

    @BeforeClass
    public void setup() throws IOException {
        sol = new Solicitation();
    }

    @Test(priority = 1)
    public void CreateSolicitationTest() {

        // create a solicitation and come back with the sol number
        SolCreator creator = new SolCreator();
        sol = creator.CreateSolicitation("data/solicitation");
    }

    @Test(priority = 2)
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

       // get a list of WebElements we can use for our target solicitation (date, name, actions, etc.)
        Map<VendorSolResponsePOM.SolColumn,WebElement> targetSolItem = sol.getWebElementsBySol(this.sol.getSolNumber());

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
    }

}
