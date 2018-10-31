package pageobjects.bidboard;

import java.io.IOException;

import org.openqa.selenium.support.PageFactory;

import utilities.common.Browser;

public class ContractBidboardPOM {

	public ContractBidboardPOM() throws IOException {
		PageFactory.initElements(Browser.getDriver(), this);
	}
}
