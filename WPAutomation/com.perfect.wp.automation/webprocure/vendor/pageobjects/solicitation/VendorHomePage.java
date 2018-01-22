package vendor.pageobjects.solicitation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commonutils.pageobjects.utils.PCDriver;

public class VendorHomePage {

	public VendorHomePage() {

		PageFactory.initElements(PCDriver.getDriver(), this);
	}

	@FindBy(xpath = "//tr/td[text()='State of Missouri']")
	public WebElement tblSolState;

	public void clickOnSolicitation(String str) {
		PCDriver.waitForElementToBeClickable(tblSolState);

		if (str.contains("Formal")) {
			tblSolState.findElement(By.xpath("./following-sibling::td[1]")).click();
		} else {
			tblSolState.findElement(By.xpath("./following-sibling::td[2]")).click();
		}
	}
}
