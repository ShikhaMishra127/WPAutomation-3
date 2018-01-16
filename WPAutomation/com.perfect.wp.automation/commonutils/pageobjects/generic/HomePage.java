package pageobjects.generic;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

import pageobjects.utils.PCDriver;

public class HomePage {
	solicitationNavigation nav=new solicitationNavigation();

	public HomePage() {
		PageFactory.initElements(PCDriver.getDriver(), this);
	}

	@FindBy(xpath="(//div[@class='modal-content'])[2]")
	public WebElement sessionModal;
	
	@FindBy(xpath = "//button[text()='Ignore']")
	public WebElement btnIgnoreOnPopUp;

	@FindBy(xpath = "//ul[contains(@class,'navbar-left')]")
	public WebElement topNav;
	
	@FindBy(xpath="//li[contains(text(),'Informal Solicitations')]/following-sibling::li")
	public WebElement informalSolMenu;
	
	@FindBy(xpath="//button[contains(text(),'Got It')]")
	public WebElement btnGotIt;
	
	public void clickGotIt() {
		PCDriver.waitForElementToBeClickable(btnGotIt);
		btnGotIt.click();
	}

	public void clickInformalSolicitationEdit() {
		selectTopNavDropDown("Solicitation");
		nav.informalSolicationsMenu("View Current");
	}
	
	public void clickFormalSolicitationEdit() {
		selectTopNavDropDown("Solicitation");
		nav.formalSolicationsMenu("View Current");
	}
	public void clickIgnoreOnPopUp() {
		PCDriver.waitForPageLoad();
		PCDriver.waitForElementToBeClickable(sessionModal);
		try {
			PCDriver.waitForElementToBeClickable(btnIgnoreOnPopUp);
		btnIgnoreOnPopUp.click();
		}
		catch(Exception e) {
			
		}try {
		clickGotIt();
		}
		catch(Exception e) {
			
		}
	}

	public void selectTopNavDropDown(String navName) {
		PCDriver.waitForElementToBeClickable(topNav);
		topNav.findElement(By.xpath("//a[contains(text(),'" + navName + "')]")).click();
	}
}
