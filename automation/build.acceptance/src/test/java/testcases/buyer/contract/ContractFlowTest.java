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

/*
        DateTimeFormatter inputBoxFormatter = DateTimeFormatter.ofPattern("LLL d, yyyy");
        Browser browser = new Browser();
        contract.setContractNumber("190509.015550");
        contract.setContractName("Automated Contract 190509.015550");
        contract.setContractLongDesc("This is a long description for Automated Contract 190509.015550");
        contract.setContractSupplier("AutoSupplier");
        contract.setContractTotalValue("1,500.00");

        contract.setContractDateAward(ZonedDateTime.of(2019, 05, 9, 02, 00, 0, 0, ZoneId.systemDefault()));
        contract.setContractDateEffective(browser.getDateTimeNowInUsersTimezone());
        contract.setContractDateExpiration(ZonedDateTime.of(2019, 06, 8, 02, 00, 0, 0, ZoneId.systemDefault()));
        contract.setContractDateProjected(browser.getDateTimeNowInUsersTimezone());
*/
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

        Assert.assertTrue("Start Date OK", contract.getContractDateAwardBidboardFormatted().contains(browser.getSubElement(ourline, board.itemStartDate).getText()));
        Assert.assertTrue("End Date OK", contract.getContractDateExpirationBidboardFormatted().contains(browser.getSubElement(ourline, board.itemEndDate).getText()));

        Assert.assertTrue("Supplier OK", ourline.getText().contains(contract.getContractSupplier()));

        browser.getSubElement(ourline, board.itemTitle).click();

        // verify summary detail screen contains valid data

        browser.waitForElementToAppear(board.summaryPeriod);

        Assert.assertTrue("IssueDate OK", board.summaryPeriod.getText().contains("Issue Date:" + contract.getContractDateAwardBidboardFormatted()));

        // BUG: https://proactis.atlassian.net/browse/WP-4095
        //Assert.assertTrue("ExpirationDate OK", board.summaryPeriod.getText().contains("Expiration Date:" + contract.getContractDateExpirationBidboardFormatted()));

        Assert.assertTrue("Pricing OK", board.summaryPricing.getText().contains(contract.getContractTotalValue()));

    }

}
