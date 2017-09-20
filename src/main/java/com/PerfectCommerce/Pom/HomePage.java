package com.PerfectCommerce.Pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.PerfectCommerce.utils.PCDriver;

public class HomePage extends PCDriver {

	
	@FindBy(xpath="//input[@placeholder='Username or Email']")
	WebElement txtUsername;
	
	
	
	public void setUsername(String str) {
		txtUsername.sendKeys(str);
		
		
	}
	
	
}
