package testcases.bidboard;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageobjects.bidboard.ContractBidboardPOM;
import utilities.common.Browser;
import utilities.common.ResourceLoader;

public class ContractBidboardTest {

    Browser browser;
	ContractBidboardPOM bidboard;
    ResourceLoader resource;

	public ContractBidboardTest() throws IOException {

	}

    @BeforeClass
    public void setup() throws IOException {
 
    	resource = new ResourceLoader("data/bidboard");
    	browser = new Browser();
    	bidboard = new ContractBidboardPOM(browser);

    	browser.getDriver().get(browser.contractUrl);

    }
    
    @Test()
    public void ViewContractList() throws Exception {
    	
    	bidboard.clickHome();
		browser.WaitTillElementIsPresent(bidboard.firstContract);
		
    	// Get an initial count of how many contracts returned
    	int currentContracts = bidboard.numberOfContracts();
		Assert.assertTrue("Initial number of contracts not 0 ", (currentContracts > 0) );
		
		// Click Filter reset - verify results for more than just "Active"
		bidboard.clickReset();
		browser.waitForPageLoad();
		Assert.assertTrue("Contract list larger after reset", (bidboard.numberOfContracts() > currentContracts) );
    }
    
    @Test()
    public void ViewContractSummary() throws Exception {
 	
    	// Look up one, unique contract
		bidboard.searchContracts(resource.getValue("contract_number"));
		browser.WaitTillElementIsPresent(bidboard.firstContract);
		TimeUnit.SECONDS.sleep(browser.defaultWait);
		
		// verify title, date, supplier and the fact only one contract returned
		Assert.assertTrue("Only the target contract returned", (bidboard.numberOfContracts() == 1) );
		Assert.assertTrue("Target contract Title OK", bidboard.firstContract.getText().contains(resource.getValue("contract_title")) );
		Assert.assertTrue("Target contract Dates OK", bidboard.firstContract.getText().contains(resource.getValue("contract_dates")) );
		Assert.assertTrue("Target contract Supplier OK", bidboard.firstContract.getText().contains(resource.getValue("contract_suppliername")) );

		//resource.getValue("contract_")
		bidboard.viewSummaryPage();
		
		// verify PUBLIC attachments are visible and PRIVATE attachments are NOT
		Assert.assertTrue("Contract contains public attachment", bidboard.summaryAttachments.getText().contains("public"));
		Assert.assertTrue("Contract DOES NOT contain private attachment", !bidboard.summaryAttachments.getText().contains("private"));

		// verify Contract Period dates
		Assert.assertTrue("Contract has Issue Date", bidboard.summaryPeriod.getText().contains(resource.getValue("contract_issueddate")));
		Assert.assertTrue("Contract has Award Date", bidboard.summaryPeriod.getText().contains(resource.getValue("contract_awarddate")));
		Assert.assertTrue("Contract has Effective Date", bidboard.summaryPeriod.getText().contains(resource.getValue("contract_effectivedate")));
		Assert.assertTrue("Contract has Expiration Date", bidboard.summaryPeriod.getText().contains(resource.getValue("contract_expireddate")));

		// verify Contract Pricing
		Assert.assertTrue("Contract has Pricing Type", bidboard.summaryPricing.getText().contains(resource.getValue("contract_pricingtype")));
		Assert.assertTrue("Contract has Total Value", bidboard.summaryPricing.getText().contains(resource.getValue("contract_totalvalue")));
		Assert.assertTrue("Contract has Contract Type", bidboard.summaryPricing.getText().contains(resource.getValue("contract_type")));
    }

	@AfterClass
    public void teardown() {

		browser.close();
    }
}
