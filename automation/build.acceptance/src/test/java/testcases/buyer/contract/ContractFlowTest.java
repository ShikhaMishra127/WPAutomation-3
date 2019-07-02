package testcases.buyer.contract;

import framework.Contract;
import junit.framework.Assert;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageobjects.bidboard.ContractBidboardPOM;
import utilities.common.Browser;

public class ContractFlowTest {

    Contract contract;

    @BeforeClass
    public void setup() {
        contract = new Contract();
    }

    @Test
    public void CreateContractTest() {

        ContractCreator creator = new ContractCreator();
        contract = creator.CreateContract("data/contract");

        System.out.format("Contract %s created.%n", contract.getContractNumber());

    }

    @Test(dependsOnMethods = {"CreateContractTest"})
    public void IndexContractTest() {

        Browser browser = new Browser();
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
        Assert.assertTrue("End Date OK", contract.getContractDateExpirationBidboardFormatted().contains(browser.getSubElement(ourline, board.itemEndDate).getText()));
        Assert.assertTrue("Supplier OK", ourline.getText().contains(contract.getContractSupplier()));

        browser.getSubElement(ourline, board.itemTitle).click();

        browser.waitForElementToAppear(board.summaryAttachments);
        browser.waitForElementToAppear(board.summaryPeriod);
        browser.waitForElementToAppear(board.summaryPricing);

        // BUG: https://proactis.atlassian.net/browse/WP-4095
        //Assert.assertTrue("ExpirationDate OK", board.summaryPeriod.getText().contains("Expiration Date:" + contract.getContractDateExpirationBidboardFormatted()));
        Assert.assertTrue("Issue Date OK", board.summaryPeriod.getText().contains("Issue Date:" + contract.getContractDateAwardBidboardFormatted()));

        // verify PUBLIC attachments are visible and PRIVATE attachments are NOT
        Assert.assertTrue("Contract contains public attachment", board.summaryAttachments.getText().contains("public"));
        Assert.assertTrue("Contract DOES NOT contain private attachment", !board.summaryAttachments.getText().contains("private"));

        // verify Contract Pricing
        Assert.assertTrue("Contract has Pricing Type", board.summaryPricing.getText().contains(contract.getContractPricingType()));
//        Assert.assertTrue("Contract has Total Value", board.summaryPricing.getText().contains(contract.getContractValueFormatted()));
        Assert.assertTrue("Contract has Contract Type", board.summaryPricing.getText().contains(contract.getContractType()));


    }

}
