package buyer.pageobjects.requestPageObjects;

import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Set;

import org.apache.http.impl.io.SocketOutputBuffer;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import commonutils.pageobjects.utils.ExtentReport;
import commonutils.pageobjects.utils.PCDriver;
import commonutils.pageobjects.utils.ReadExcelData;

public class RoundTripPOM {

	WebDriver vendorsitedriver;

	@FindBy(xpath = "//table[@class ='table table-bordered table-striped dataTable no-footer']//tr/td/a[@class='CatalogListingRow']")
	List<WebElement> supplierlist;

	@FindBy(xpath = "//li[@id='navGroup2']")
	WebElement papercatogaries;
	
	@FindBy(xpath = "//img[@class = 'size75' and @title = 'Copy & Printer Paper']")
	WebElement papersubcat;
		
	@FindBy(xpath = "//a[contains(@class,'refV2 black med_txt js-refinement-link')]")
	List<WebElement> list_refinement_category;

	@FindBy(xpath = "//a[@class='refV2 black med_txt js-refinement-link']")
	List<WebElement> subcatageorieslist;

	String selectedsubcategory = "Colored Paper ";

	@FindBy(xpath = "//input[@id = 'quantityBox0']")
	WebElement selecteditemqty;

	@FindBy(xpath = "//input[@id = 'skuListFormID_INDEX_0' and @title = 'Add to Cart']")
	WebElement addtocartbtn;

	@FindBy(xpath = "//a[@class='btn primary' and text() = 'Check Out']")
	WebElement checkoutbtn;

	public RoundTripPOM() {
		PageFactory.initElements(PCDriver.getDriver(), this);
	}

	public void selecttargetsupplier() {
		PCDriver.waitForElementToDisappear(By.id("loadingDiv"));
		PCDriver.waitForPageLoad();
		try {
			Thread.sleep(10000);
			PCDriver.getDriver().switchTo().defaultContent();
			PCDriver.getDriver().switchTo().frame("C1ReqMain");
			System.out.println(supplierlist.size());
			for (WebElement targetsupplier : supplierlist) {
				if (targetsupplier.getText().contains("Office Depot Inc")) {
					System.out.println(targetsupplier.getText());
					targetsupplier.click();
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public void addroundtripitemtocart() {

		String webprocurewindow = PCDriver.driver.getWindowHandle();
		System.out.println(webprocurewindow);

		Set<String> supplierwindow = PCDriver.driver.getWindowHandles();
		System.out.println(supplierwindow);

		for (String focusonwindow : PCDriver.driver.getWindowHandles()) {
			System.out.println(focusonwindow);
			PCDriver.switchToWindow(focusonwindow);
			PCDriver.driver.manage().window().maximize();
		}
		PCDriver.waitForPageLoad();
		try {
			Thread.sleep(8000);
			PCDriver.hoverOnElement(papercatogaries);
			Thread.sleep(3000);
			By byXpathppr = By.xpath("//img[@class='size75']//following-sibling::span[contains(text(),'Copy & Printer Paper')]");
			WebElement selectedcategories = papercatogaries.findElement(byXpathppr);
			selectedcategories.click();
			System.out.println("selectedcat clicked");
			Thread.sleep(5000);
			for (WebElement subcategory : subcatageorieslist) {
				
				if (subcategory.getText().contains(selectedsubcategory)) {
					System.out.println("subcategory list " +subcategory.getText());
					subcategory.click();
					PCDriver.waitForElementToBeEnable(By.id("skuListFormID_INDEX_0"));
					selecteditemqty.sendKeys("2");
					addtocartbtn.click();
					PCDriver.WaitTillElementIsPresent(checkoutbtn);
					if(PCDriver.getDriver() instanceof PhantomJSDriver)
					{	
					PCDriver.waitForElementToBeClickable(checkoutbtn);
					checkoutbtn.click();
					}
					else{
					JavascriptExecutor executor1 = (JavascriptExecutor)PCDriver.getDriver();
					executor1.executeScript("arguments[0].click();", checkoutbtn);
					}
					System.out.println("old value:" + webprocurewindow.toString());
					

					PCDriver.getDriver().switchTo().window(webprocurewindow);
					System.out.println("The title is:"+PCDriver.getDriver().getTitle());
					PCDriver.visibilityOfListLocated(PCDriver.getDriver().findElements(By.xpath("//a[contains(text(),'Request')]")));					
					break;
					
				}
			}
			
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}