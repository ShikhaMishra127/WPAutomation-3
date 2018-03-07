package buyer.pageobjects.requestPageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

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
		Thread.sleep(8000);
		PCDriver.switchToFrameBasedOnFrameName("reqcart");
		// System.out.println(reqcartframe.getAttribute("name"));
		PCDriver.waitForElementToBeClickable(reqname);
		System.out.println("Req name is: "+reqname.getAttribute("value"));
		Assert.assertFalse(reqname.getAttribute("value").isEmpty());
		return reqname.getAttribute("value");
	}
}
