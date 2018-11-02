package pageobjects.bidboard;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import utilities.common.Browser;

public class ContractBidboardPOM {

	private final Browser browser;

	/**
	 * Constructor called by PageFactory.instantiatePage
	 * @param browser WebDriver (as required by PageFactory) will be cast back to Browser.
	 */
	public ContractBidboardPOM(WebDriver browser) throws IOException {
		this.browser = (Browser)browser;
	}
}
