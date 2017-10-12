package com.PerfectCommerce.CreateSol.Pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.PerfectCommerce.utils.PCDriver;

public class CreateSolicitationPOM {

	public CreateSolicitationPOM() {

		PageFactory.initElements(PCDriver.getDriver(), this);
	}

	@FindBy(id="bidTitle")
	public WebElement txtTitle;
	
	@FindBy(id="solType")
	public WebElement drpDownSolType;
	
	@FindBy(id="bidestimatedtotal")
	public WebElement txtEstimatedTotValue;
	
	@FindBy(id="noLineItem")
	public WebElement chkBoxNoLineItem;
	
	@FindBy(id="selectCatButton")
	public WebElement btnCat;
	
	@FindBy(xpath="//div[@aria-hidden='false']//ul[contains(@class,'ui-fancytree')]/li")
	public WebElement chkBoxCategory;
	
	@FindBy(xpath="//button[text()='Next Step']")
	public WebElement btnNextStep;
	
	@FindBy(xpath="//button[text()='Close']")
	public WebElement btnCloseOnCategoryPopUp;
	
	
/***************************Create Sol First Page***************************/

	
	
	
	
	 /**
	  * 
	  * @param strTitle
	  */
	public void setTitle(String strTitle) {
	txtTitle.sendKeys(strTitle);	
		
	}
	
	public void setSolType() {
		new Select(drpDownSolType).selectByIndex(1);
	}
	
	public void setEstimatedTotalValue(String strValue) {
		txtEstimatedTotValue.sendKeys(strValue);
	}
	
	public void selectNoLineItemCheckBox() {
		chkBoxNoLineItem.click();
	}
	
	public void clickAndSelectCategory(String CategoryName) throws InterruptedException {
		btnCat.click();
		if(chkBoxCategory.isDisplayed()) {
		PCDriver.waitForElementToBeClickable(chkBoxCategory);
		Thread.sleep(3000);
		chkBoxCategory.findElement(By.xpath("//span[contains(text(),'"+CategoryName+"')]//preceding-sibling::span[contains(@class,'fancytree-checkbox')]")).click();
		}
	}
	
	public void clickCloseOnCategoryPopUp() {
		PCDriver.waitForElementToBeClickable(btnCloseOnCategoryPopUp);
		btnCloseOnCategoryPopUp.click();
	}
	
	public void clickOnNextStep() {
		PCDriver.waitForElementToBeClickable(btnNextStep);
		btnNextStep.click();
		PCDriver.waitForPageLoad();
	}
	
}
