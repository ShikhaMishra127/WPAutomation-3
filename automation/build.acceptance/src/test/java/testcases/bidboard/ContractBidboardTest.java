package testcases.bidboard;

import java.io.IOException;

import org.junit.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageobjects.bidboard.ContractBidboardPOM;
import utilities.common.Browser;

public class ContractBidboardTest {

    Browser browser;
	ContractBidboardPOM bidboard;
	
	public ContractBidboardTest() throws IOException {

	}

    @BeforeClass
    public void setup() throws IOException {
 
    	browser = new Browser();
    	bidboard = new ContractBidboardPOM(browser);
    	
    	browser.getDriver().get(browser.contractUrl);
    }
    
    @Test()
    public void ViewContracts() {
    	
		int currentContracts = bidboard.numberOfContracts();
		Assert.assertTrue( currentContracts > 0 );
		
		bidboard.clickReset();
		Assert.assertTrue( bidboard.numberOfContracts() > currentContracts );

		bidboard.searchContracts("ACN001");
		Assert.assertTrue("Only target contract returned", bidboard.numberOfContracts() == 1);
		
		Assert.assertTrue("Contract contains public attachment", bidboard.summaryAttachments.toString().contains("public"));
		Assert.assertTrue("Contract DOES NOT contain private attachment", !bidboard.summaryAttachments.toString().contains("private"));

    }
    
    @AfterClass
    public void teardown() {
    	browser.close();
    }
}
