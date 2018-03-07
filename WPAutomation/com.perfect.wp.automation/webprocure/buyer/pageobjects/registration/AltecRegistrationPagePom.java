package buyer.pageobjects.registration;

import org.openqa.selenium.support.PageFactory;

import commonutils.pageobjects.generic.LoginPage;
import commonutils.pageobjects.utils.PCDriver;
import commonutils.pageobjects.utils.ReadConfig;

public class AltecRegistrationPagePom {
	final static String sheetName = "Registration";
	LoginPage login = new LoginPage();

	public AltecRegistrationPagePom() {

		PCDriver.driver.navigate().to(ReadConfig.getInstance().getAltecRegistrationUrl());
		PageFactory.initElements(PCDriver.getDriver(), this);

	}

}
