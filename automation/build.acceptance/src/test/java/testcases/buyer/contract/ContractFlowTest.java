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

import java.time.ZoneId;
import java.time.ZonedDateTime;
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
    @TestRailReference(id = 3543)
    public void CreateContractTest(ITestContext testContext) {

        Browser browser = new Browser(testContext);
        ContractCreator creator = new ContractCreator();
        contract = creator.CreateContract(browser, resource);

    }

    @Test(enabled = true, dependsOnMethods = {"CreateContractTest"})
    @TestRailReference(id = 4661)
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

        Assert.assertTrue("Start Date OK", contract.formatDate(contract.getContractDateAward(), contract.bidboardFormatter).contains(browser.getSubElement(ourline, board.itemStartDate).getText()));
        Assert.assertTrue("End Date OK", contract.formatDate(contract.getContractDateExpiration(), contract.bidboardFormatter).contains(browser.getSubElement(ourline, board.itemEndDate).getText()));

        Assert.assertTrue("Supplier OK", ourline.getText().contains(contract.getContractSupplier()));

        browser.getSubElement(ourline, board.itemTitle).click();

        browser.waitForElementToAppear(board.summaryAttachments);
        browser.waitForElementToAppear(board.summaryPeriod);
        browser.waitForElementToAppear(board.summaryPricing);

        Assert.assertTrue("ExpirationDate OK", board.summaryPeriod.getText().contains("Expiration Date:" + contract.formatDate(contract.getContractDateExpiration(), contract.bidboardFormatter)));
        Assert.assertTrue("Issue Date OK", board.summaryPeriod.getText().contains("Issue Date:" + contract.formatDate(contract.getContractDateAward(), contract.bidboardFormatter)));

        // verify PUBLIC attachments are visible and PRIVATE attachments are NOT
        Assert.assertTrue("Contract contains public attachment", board.summaryAttachments.getText().contains("public"));
        Assert.assertTrue("Contract DOES NOT contain private attachment", !board.summaryAttachments.getText().contains("private"));

        // verify Contract Pricing
        Assert.assertTrue("Contract has Pricing Type", board.summaryPricing.getText().contains(contract.getContractPricingType()));
//        Assert.assertTrue("Contract has Total Value", board.summaryPricing.getText().contains(contract.getContractValueFormatted()));
//        Assert.assertTrue("Contract has Contract Type", board.summaryPricing.getText().contains(contract.getContractType()));

        browser.Log("Verified contract " + contract.getContractName() + " indexed on Contract Board");

        browser.close();
    }

    @Test(enabled = true, dependsOnMethods = {"CreateContractTest"})
    @TestRailReference(id = 3544)
    public void ViewContractTest(ITestContext testContext) {

        Browser browser = new Browser(testContext);
        LoginPagePOM login = new LoginPagePOM(browser);
        BuyerNavBarPOM navbar = new BuyerNavBarPOM(browser);
        ViewContractPOM view = new ViewContractPOM(browser);

        browser.get(browser.baseUrl);
        login.loginAsBuyer();

        // Go to Contracts > View Current > Configure a search criteria
        navbar.selectDropDownItem("Contracts", "View Current Contracts");

        // Search for target contract and click on contract number to get summary screen
        browser.sendKeysWhenAvailable(view.contractNumberEdit, contract.getContractNumber());
        browser.clickWhenAvailable(view.submitButton);

        Map<Browser.HTMLTableColumn, WebElement> contractLine = view.getElementsForContractLine(contract.getContractNumber());

        browser.clickSubElement(contractLine.get(ListColumn.CONTRACTNUM), "./a");

        // view summary and make sure data is correct
        Assert.assertTrue("Verify Privacy Setting", view.GetGeneralInfoElement("Access").getText().contains(contract.getContractVisibility()));
        Assert.assertTrue("Verify Contract Title", view.GetGeneralInfoElement("Title").getText().contains(contract.getContractName()));
        Assert.assertTrue("Verify Contract Issue Date", view.GetGeneralInfoElement("Issue Date").getText().contains(contract.formatDate(contract.getContractDateEffective(), contract.summaryFormatter)));

        browser.Log("Verified contract " + contract.getContractName() + " details on summary page");

        // close up and log out
        navbar.logout();
        browser.close();
    }

}
