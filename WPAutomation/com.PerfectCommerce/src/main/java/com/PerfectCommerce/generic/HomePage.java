package com.PerfectCommerce.generic;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.PerfectCommerce.utils.PCDriver;

public class HomePage {

	public HomePage() {
		PageFactory.initElements(PCDriver.getDriver(), this);

	}

	@FindBy(xpath = "//button[text()='Ignore']")
	public WebElement btnIgnoreOnPopUp;

	@FindBy(xpath = "//ul[contains(@class,'navbar-left')]")
	public WebElement topNav;
	
	@FindBy(xpath="//li[contains(text(),'Informal Solicitations')]/following-sibling::li")
	public WebElement informalSolMenu;

	public void clickIgnoreOnPopUp() {
		PCDriver.waitForPageLoad();

		btnIgnoreOnPopUp.click();
	}

	public void selectTopNavDropDown(String navName) {
		PCDriver.waitForElementToBeClickable(topNav);
		topNav.findElement(By.xpath(".//a[contains(text(),'" + navName + "')]")).click();

	}

	/*********************************
	 * Informal Solicitation DropDown Menu
	 ******************************************************/
	public void informalSolicationsMenu(String menuItem) {
		PCDriver.waitForElementToBeClickable(informalSolMenu);
		informalSolMenu.findElement(By.xpath(".//a[text()='"+menuItem+"']")).click();
	}


}
