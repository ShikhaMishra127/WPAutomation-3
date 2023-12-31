package commonutils.pageobjects.generic;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commonutils.pageobjects.utils.PCDriver;

public class solicitationNavigation {

	public solicitationNavigation() {
		PageFactory.initElements(PCDriver.getDriver(), this);

	}

	@FindBy(xpath = "//li[contains(text(),'Informal Solicitations')]")
	public WebElement informalSolMenu;

	@FindBy(xpath = "//li[contains(text(),'Formal Solicitations')]")
	public WebElement formalSolMenu;

	/*********************************
	 * Informal Solicitation DropDown Menu
	 ******************************************************/
	public void informalSolicationsMenu(String menuItem) {
		PCDriver.waitForPageLoad();
		PCDriver.waitForElementToBeClickable(informalSolMenu);

		informalSolMenu.findElement(By.xpath(".//following-sibling::li//a[contains(text(),'" + menuItem + "')]"))
				.click();

		try {
			PCDriver.getDriver().switchTo().alert().accept();

		} catch (Exception e) {
			System.out.println("No Alert Present");

		} finally {
			PCDriver.waitForPageLoad();

		}
	}

	public void formalSolicationsMenu(String menuItem) {
		PCDriver.waitForPageLoad();
		PCDriver.waitForElementToBeClickable(formalSolMenu);
		((JavascriptExecutor)PCDriver.getDriver()).executeScript("window.confirm = function(msg){return true;}");

		formalSolMenu.findElement(By.xpath(".//following-sibling::li//a[contains(text(),'" + menuItem + "')]")).click();
		try {
			//PCDriver.getDriver().switchTo().alert().accept();
		} catch (Exception e) {
			System.out.println("No Alert Present");
		}
	}

}
