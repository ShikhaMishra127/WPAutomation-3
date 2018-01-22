package commonutils.pageobjects.generic;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commonutils.pageobjects.utils.PCDriver;

public class RequestNavigation {

	public RequestNavigation() {
		PageFactory.initElements(PCDriver.getDriver(), this);
	}

	@FindBy(xpath = "//a[@title='Request']")
	public WebElement requestdropdown;

	@FindBy(name = "C1ReqMain")
	public WebElement reqiframe;

	@FindBy(xpath = "//li[contains (@class,'paginate_button')]")
	public WebElement typeofreqlist;

	// Request DropDown Menu
	public void requestdropdown(String reqoptions) {
		// PCDriver.waitForElementToBeClickable(requestdropdown);

		requestdropdown.findElement(By.xpath("//li[@class='dropdown open']//a[contains(text(),'" + reqoptions + "')]"))
				.click();

		try {
			PCDriver.getDriver().switchTo().alert().accept();

		} catch (Exception e) {
			System.out.println("No Alert Present");

		} finally {
			PCDriver.waitForPageLoad();
			PCDriver.switchToFrame(reqiframe);
		}
	}

	// Select type of request which user want to create
	public void typesofreqlist(String requesttype) throws Exception {

		typeofreqlist.findElement(By.xpath(".//following-sibling::li//a[contains(text(),'" + requesttype + "')]"))
				.click();
		try {
			PCDriver.getDriver().switchTo().alert().accept();

		} catch (Exception e) {
			System.out.println("No Alert Present");

		} finally {
			PCDriver.waitForElementToDisappear(By.id("loadingDiv"));
			PCDriver.waitForPageLoad();

			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			PCDriver.getDriver().switchTo().defaultContent();
			PCDriver.waitForElementToDisappear(By.id("loadingDiv"));
			// Thread.sleep(5000);
			// System.out.println(cartFrame.getSize());
			PCDriver.getDriver().switchTo().frame("C1ReqMain");
			// System.out.println(cartFrame.getAttribute("name"));
		}
	}

}
