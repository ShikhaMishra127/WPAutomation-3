package buyer.pageobjects.requestPageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commonutils.pageobjects.utils.PCDriver;

public class RequestNumber {

	@FindBy(xpath = "//iframe[@name='reqcart']")
	public WebElement reqcartframe;
	@FindBy(xpath = "//input[@name='txtReqName']")
	public WebElement reqname;

	public RequestNumber() {

		PageFactory.initElements(PCDriver.getDriver(), this);
	}

	public String requestname() throws Exception {

		PCDriver.waitForPageLoad();

		PCDriver.getDriver().switchTo().defaultContent();
		// PCDriver.switchToDefaultWindow();
		Thread.sleep(10000);
		PCDriver.switchToFrameBasedOnFrameName("reqcart");
		// System.out.println(reqcartframe.getAttribute("name"));
		System.out.println(reqname.getAttribute("value"));
		return reqname.getAttribute("value");
	}
}
