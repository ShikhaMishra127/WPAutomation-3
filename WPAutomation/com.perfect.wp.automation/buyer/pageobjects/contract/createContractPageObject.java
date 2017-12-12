package pageobjects.contract;

import org.openqa.selenium.support.PageFactory;

import pageobjects.utils.PCDriver;

public class createContractPageObject {

	
	
	public createContractPageObject() {
		PageFactory.initElements(PCDriver.getDriver(),this );
	}
}
