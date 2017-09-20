package com.PerfectCommerce.utils;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class PCDriver implements WebDriver {
	WebDriver driver=null;

	public PCDriver() {
		PageFactory.initElements(driver, this.getClass());
	}

	public void close() {
		driver.close();
		
	}
	
	public void sendKeys(String str) {
		
	}

	public WebElement findElement(By arg0) {
		return null;
	}

	public List<WebElement> findElements(By arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public void get(String arg0) {
		// TODO Auto-generated method stub
		
	}

	public String getCurrentUrl() {
		// TODO Auto-generated method stub
		return driver.getCurrentUrl();
	}

	public String getPageSource() {
		
		return driver.getPageSource();
	}

	public String getTitle() {
		
		return driver.getTitle();
	}

	public String getWindowHandle() {
		// TODO Auto-generated method stub
		return null;
	}

	public Set<String> getWindowHandles() {
		
		return driver.getWindowHandles();
	}

	public Options manage() {
		// TODO Auto-generated method stub
		return null;
	}

	public Navigation navigate() {
	
		return null;
	}

	public void quit() {
		driver.quit();
	}

	public TargetLocator switchTo() {
		
		return null;
	}

}
