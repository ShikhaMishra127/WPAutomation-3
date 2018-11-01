package testcases.bidboard;

import java.io.IOException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.common.Browser;

public class ContractBidboardTest {

	public ContractBidboardTest() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

    @BeforeClass
    public void setup() throws IOException {
    	
        // TEMPORARY: load a different URL
        Browser browser = new Browser();
    	browser.getDriver().get(browser.contractUrl);
    }
    
    @Test()
    public void ViewContracts() {
    	
    }

}
