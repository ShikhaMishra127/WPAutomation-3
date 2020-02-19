package testcases.buyer.contract;

import framework.Contract;
import org.openqa.selenium.WebElement;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageobjects.bidboard.ContractBidboardPOM;
import pageobjects.buyer.common.BuyerNavBarPOM;
import pageobjects.buyer.contract.ViewContractPOM;
import pageobjects.common.LoginPagePOM;
import pageobjects.vendor.common.VendorNavBarPOM;
import pageobjects.vendor.contracts.VendorViewContractPOM;
import utilities.common.Browser;
import utilities.common.ResourceLoader;
import utilities.common.TestRailReference;

import java.util.Map;

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
        browser.Assert("Verify Contract Title", ourline.getText(), contract.getContractName());
        browser.Assert("Verify Contract Number", ourline.getText(), contract.getContractNumber());
        browser.Assert("Verify Contract Long Description", ourline.getText(), contract.getContractLongDesc());

        browser.Assert("Verify Contract Start Date",
                contract.formatDate(contract.getContractDateAward(), contract.bidboardFormatter), browser.getSubElement(ourline, board.itemStartDate).getText());
        browser.Assert("Verify Contract End Date",
                contract.formatDate(contract.getContractDateExpiration(), contract.bidboardFormatter), browser.getSubElement(ourline, board.itemEndDate).getText());

        browser.Assert("Verify Contract Supplier Name", ourline.getText(), contract.getContractSupplier());

        browser.getSubElement(ourline, board.itemTitle).click();

        browser.waitForElementToAppear(board.summaryAttachments);
        browser.waitForElementToAppear(board.summaryPeriod);
        browser.waitForElementToAppear(board.summaryPricing);

        browser.Assert("Verify Contract Expiration Date",
                board.summaryPeriod.getText(), "Expiration Date:" + contract.formatDate(contract.getContractDateExpiration(), contract.bidboardFormatter));
        browser.Assert("Verify Contract Issue Date",
                board.summaryPeriod.getText(), "Issue Date:" + contract.formatDate(contract.getContractDateAward(), contract.bidboardFormatter));

        // verify PUBLIC attachments are visible and PRIVATE attachments are NOT
        browser.Assert("Contract contains public attachment", board.summaryAttachments.getText(), "public");
        browser.Assert("Contract DOES NOT contain private attachment", !board.summaryAttachments.getText().contains("private"));

        // verify Contract Pricing
        browser.Assert("Contract has Pricing Type", board.summaryPricing.getText(), contract.getContractPricingType());
//        browser.Assert("Contract has Total Value", board.summaryPricing.getText(), contract.getContractValueFormatted()));
//        browser.Assert("Contract has Contract Type", board.summaryPricing.getText(), contract.getContractType()));

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
        navbar.selectDropDownItem(resource.getValue("navbar_headitem"), resource.getValue("navbar_subitem_view"));

        // Search for target contract and click on contract number to get summary screen
        browser.sendKeysWhenAvailable(view.contractNumberEdit, contract.getContractNumber());
        browser.clickWhenAvailable(view.submitButton);

        Map<Browser.HTMLTableColumn, WebElement> contractLine = view.getElementsForContractLine(contract.getContractNumber());

        browser.clickSubElement(contractLine.get(ViewContractPOM.ListColumn.CONTRACTNUM), "./a");

        // view summary and make sure data is correct
        browser.Assert("Verify Privacy Setting",
                view.GetGeneralInfoElement(resource.getValue("summary_access")), contract.getContractVisibility());
        browser.Assert("Verify Contract Title",
                view.GetGeneralInfoElement(resource.getValue("summary_title")), contract.getContractName());

        browser.Log("Verified contract " + contract.getContractName() + " details on summary page");

        // close up and log out
        navbar.logout();
        browser.close();
    }

    @Test(enabled = true, dependsOnMethods = {"CreateContractTest"})
    @TestRailReference(id = 12467)
    public void VendorViewContractTest(ITestContext testContext) {

        Browser browser = new Browser(testContext);
        LoginPagePOM login = new LoginPagePOM(browser);
        VendorNavBarPOM navbar = new VendorNavBarPOM(browser);
        VendorViewContractPOM view = new VendorViewContractPOM(browser);

        browser.get(browser.baseUrl);
        login.loginAsSupplier();

        navbar.selectNavDropByBuyer(browser.buyerName, resource.getValue("navbar_headitem"), resource.getValue("navbar_vendor_view"));

        // go to Contracts > View Contracts and look up target contract, then click number to get summary screen
        browser.sendKeysWhenAvailable(view.contractNumberEdit, contract.getContractNumber());
        browser.clickWhenAvailable(view.submitButton);

        Map<Browser.HTMLTableColumn, WebElement> contractLine = view.getElementsForContractLine(contract.getContractNumber());
        browser.clickSubElement(contractLine.get(VendorViewContractPOM.ListColumn.CONTRACTNUM), "./a");

        // view summary and make sure data is correct
        browser.Assert("Verify Contract Title",
                view.GetGeneralInfoElement(resource.getValue("summary_title")), contract.getContractName());

        browser.Log("Verified contract " + contract.getContractName() + " details on vendor summary page");

        // close up and log out
        navbar.vendorLogout();
        browser.close();

    }
}
