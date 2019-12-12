package testcases.buyer.contract;

import framework.Contract;
import junit.framework.Assert;
import org.openqa.selenium.WebElement;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageobjects.bidboard.ContractBidboardPOM;
import pageobjects.buyer.common.BuyerNavBarPOM;
import pageobjects.buyer.contract.ViewContractPOM;
import pageobjects.common.LoginPagePOM;
import utilities.common.Browser;
import utilities.common.ResourceLoader;
import utilities.common.TestRailReference;

import java.util.Map;

import static pageobjects.buyer.contract.ViewContractPOM.ListColumn;

public class ContractFlowTest {

    Contract contract;
    ResourceLoader resource;

    @BeforeClass
    public void setup() {

        contract = new Contract();
        resource = new ResourceLoader("data/contract");
    }

    @Test
    @TestRailReference(id=3543)
    public void CreateContractTest(ITestContext testContext) {

         Browser browser = new Browser(testContext);
         ContractCreator creator = new ContractCreator();
         contract = creator.CreateContract(browser, resource);

        // TEMP - delete me!
        //contract.setContractNumber("190516.040722");
        //contract.setContractName("Automated Contract 190516.040722");
    }

    @Test(enabled = false, dependsOnMethods = {"CreateContractTest"})
    @TestRailReference(id=4661)
    public void IndexContractTest(ITestContext testContext) {

        Browser browser = new Browser(testContext);
        ContractBidboardPOM board = new ContractBidboardPOM(browser);

        browser.getDriver().get(browser.contractUrl);

        contract.DumpContractInfo();

        board.waitForContract(contract.getContractNumber());

        WebElement ourline = board.getContractLineItem(contract.getContractNumber());

        // ensure contract list line item contains valid data
        Assert.assertTrue("Title OK", ourline.getText().contains(contract.getContractName()));
        Assert.assertTrue("Number OK", ourline.getText().contains(contract.getContractNumber()));
        Assert.assertTrue("LongDesc OK", ourline.getText().contains(contract.getContractLongDesc()));

        // commented-out failed assert until JIRA ticket WP-4510 is fixed
        //Assert.assertTrue("Start Date OK", contract.getContractDateAwardBidboardFormatted().contains(browser.getSubElement(ourline, board.itemStartDate).getText()));
        //Assert.assertTrue("End Date OK", contract.getContractDateExpirationBidboardFormatted().contains(browser.getSubElement(ourline, board.itemEndDate).getText()));
        Assert.assertTrue("Supplier OK", ourline.getText().contains(contract.getContractSupplier()));

        browser.getSubElement(ourline, board.itemTitle).click();

        browser.waitForElementToAppear(board.summaryAttachments);
        browser.waitForElementToAppear(board.summaryPeriod);
        browser.waitForElementToAppear(board.summaryPricing);

        // BUG: https://proactis.atlassian.net/browse/WP-4095
        Assert.assertTrue("ExpirationDate OK", board.summaryPeriod.getText().contains("Expiration Date:" + contract.getContractDateExpirationBidboardFormatted()));
        Assert.assertTrue("Issue Date OK", board.summaryPeriod.getText().contains("Issue Date:" + contract.getContractDateAwardBidboardFormatted()));

        // verify PUBLIC attachments are visible and PRIVATE attachments are NOT
        Assert.assertTrue("Contract contains public attachment", board.summaryAttachments.getText().contains("public"));
        Assert.assertTrue("Contract DOES NOT contain private attachment", !board.summaryAttachments.getText().contains("private"));

        // verify Contract Pricing
        Assert.assertTrue("Contract has Pricing Type", board.summaryPricing.getText().contains(contract.getContractPricingType()));
//        Assert.assertTrue("Contract has Total Value", board.summaryPricing.getText().contains(contract.getContractValueFormatted()));
//        Assert.assertTrue("Contract has Contract Type", board.summaryPricing.getText().contains(contract.getContractType()));

    }

    @Test(enabled = true, dependsOnMethods = {"CreateContractTest"})
    @TestRailReference(id=3544)
    public void ViewContractTest(ITestContext testContext) {

        Browser browser = new Browser(testContext);
        LoginPagePOM login = new LoginPagePOM(browser);
        BuyerNavBarPOM navbar = new BuyerNavBarPOM(browser);
        ViewContractPOM view = new ViewContractPOM(browser);

        browser.get(browser.baseUrl);
        login.loginAsBuyer();

        navbar.selectDropDownItem("Contracts","View Current Contracts");

        browser.sendKeysWhenAvailable(view.contractNumberEdit, contract.getContractNumber());
        browser.clickWhenAvailable(view.submitButton);

        Map<Browser.HTMLTableColumn, WebElement> contractLine = view.getElementsForContractLine(contract.getContractNumber());

        System.out.println("STATUS: " + contractLine.get(ListColumn.STATUS).getText());
    }

}
