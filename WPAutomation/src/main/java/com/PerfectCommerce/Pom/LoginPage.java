package com.PerfectCommerce.Pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.PerfectCommerce.utils.PCDriver;


public class LoginPage  {

 
	 public LoginPage(WebDriver driver) {
		 PageFactory.initElements(driver, this);
	 }

	
	@FindBy(xpath="//input[contains(@placeholder,'Username')]")
	public WebElement txtUsername;
	
	
	
	public void setUsername(String str) {
		//PageFactory.initElements(utils.driver HomePage.class);
		txtUsername.sendKeys(str);

		
		
	}
	
	
}
