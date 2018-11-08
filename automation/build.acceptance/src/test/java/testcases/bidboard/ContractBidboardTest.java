package testcases.bidboard;

import java.io.IOException;

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
    String targetContract;

	
	public ContractBidboardTest() throws IOException {

	}

    @BeforeClass
    public void setup() throws IOException {
 
    	resource = new ResourceLoader("data/bidboard");
    	browser = new Browser();
    	bidboard = new ContractBidboardPOM(browser);
    	
    	targetContract = resource.getValue("contract_number");
    	
    	browser.getDriver().get(browser.contractUrl);
    	
    	
    }
    
    @Test()
    public void ViewContractList() {
    	
    	bidboard.clickHome();
		
    	// Get an initial count of how many contracts returned
    	int currentContracts = bidboard.numberOfContracts();
		Assert.assertTrue("Initial number of contracts not 0 ", (currentContracts > 0) );
		
		// Click Filter reset - verify results for more than just "Active"
		bidboard.clickReset();
		Assert.assertTrue("Contract list larger after reset", (bidboard.numberOfContracts() > currentContracts) );
    }
    
    @Test()
    public void ViewContractSummary() {
 	
    	// Look up one, unique contract
		bidboard.searchContracts(targetContract);
		browser.WaitTillElementIsPresent(bidboard.firstContract);
		
		// verify title, date, supplier and the fact only one contract returned
		Assert.assertTrue("Only target contract returned", (bidboard.numberOfContracts() == 1) );
		Assert.assertTrue("Target contract Title OK", bidboard.firstContract.getText().contains("Automation Test") );
		Assert.assertTrue("Target contract Dates OK", bidboard.firstContract.getText().contains("Nov 5, 2018 - Dec 31, 2025") );
		Assert.assertTrue("Target contract Supplier OK", bidboard.firstContract.getText().contains("AutoSupplier") );
		
		bidboard.viewSummaryPage();
		
		// verify PUBLIC attachments are visible and PRIVATE attachments are NOT
		Assert.assertTrue("Contract contains public attachment", bidboard.summaryAttachments.getText().contains("public"));
		Assert.assertTrue("Contract DOES NOT contain private attachment", !bidboard.summaryAttachments.getText().contains("private"));

		// verify Contract Period dates
		Assert.assertTrue("Contract has Issue Date", bidboard.summaryPeriod.getText().contains("Issue Date:Nov 5, 2018"));
		Assert.assertTrue("Contract has Award Date", bidboard.summaryPeriod.getText().contains("Award Date:Nov 5, 2018"));
		Assert.assertTrue("Contract has Effective Date", bidboard.summaryPeriod.getText().contains("Effective Date:Nov 5, 2018"));
		Assert.assertTrue("Contract has Expiration Date", bidboard.summaryPeriod.getText().contains("Expiration Date:Dec 31, 2025"));
		
		// verify Contract Pricing
		Assert.assertTrue("Contract has Pricing Type", bidboard.summaryPricing.getText().contains("Pricing Type: Fixed Price"));
		Assert.assertTrue("Contract has Total Value", bidboard.summaryPricing.getText().contains("Value: $50,000.00"));
		Assert.assertTrue("Contract has Contract Type", bidboard.summaryPricing.getText().contains("Contract Type: State Contract"));
    }
    
    @AfterClass
    public void teardown() {
    	browser.close();
    }
}