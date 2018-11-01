package testcases.bidboard;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import utilities.common.Browser;
import utilities.common.ExtentReport;

public class ContractBidboardTest {

	public ContractBidboardTest() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

    @BeforeClass
    public void setup() {
    	
        // TEMPORARY: load a different URL
    	Browser.getDriver().get(Browser.contractUrl);
    }
    
    @Test()
    public void ViewContracts() {
    	
    }

}
