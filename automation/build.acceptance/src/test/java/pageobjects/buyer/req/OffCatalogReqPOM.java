package pageobjects.buyer.req;

import java.util.List;
import java.util.Locale;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import utilities.common.Browser;
import utilities.common.ResourceLoader;

public class OffCatalogReqPOM {
	
	@FindBy(xpath = "//*[@id='page-title']/h3")
	public WebElement offcatreqpagetitle;

	@FindBy(xpath = "//button[text()='Add']")
	public WebElement additem;

	@FindBy(xpath = "//button[test()='Reset']")
	public WebElement reset;

	@FindBy(xpath = "//input[@name = 'chkAddToFavorites']")
	public WebElement addtofavorites;

	@FindBy(xpath = "//input[@name = 'chkRetainKeyInfo' and not(contains(@type,'hidden'))]")
	public WebElement retainkeyinfo;

	@FindBy(xpath = "//*[@id='OrderQty']")
	public WebElement orderquantity;

	@FindBy(xpath = "//*[@id='select2-selUOMID-container']")
	public WebElement dropdownunit;

	@FindBy(xpath = "//input[@class='select2-search__field']")
	public WebElement unittextbox;

	@FindBy(xpath = "//ul[@class='select2-results__options']")
	public List<WebElement> unitlist;

	@FindBy(xpath = "//*[@id='UnitPrice']")
	public WebElement unitprice;

	@FindBy(xpath = "//*[@id='selCurrencyCode']")
	public WebElement dropdowncurrency;

	@FindBy(xpath = "//*[@id='SupplierPartNum']")
	public WebElement supplierpartno;

	@FindBy(xpath = "//*[@id='input_SupplierName']")
	public WebElement selectsupplier;

	@FindBy(xpath = "//*[@id='input_SupplierName']")
	public WebElement vendortextbox;

	@FindBy(xpath = "//ul[@class='ui-autocomplete ui-front ui-menu ui-widget ui-widget-content ui-corner-all'][not(contains(@style,'none'))]//a")
	public List<WebElement> vendorlist;

	@FindBy(xpath = "//*[@id='input_MfrName']")
	public WebElement mfrname;

	@FindBy(xpath = "//ul[@class='ui-autocomplete ui-front ui-menu ui-widget ui-widget-content ui-corner-all'][not(contains(@style,'none'))]//a")
	public List<WebElement> mfrlist;

	@FindBy(xpath = "//*[@id='dateNeedBy']")
	public WebElement dateneedby;

	@FindBy(xpath = "//*[@id='input_catcode']")
	public WebElement commoditybox;

	@FindBy(xpath = "//ul[@class='ui-autocomplete ui-front ui-menu ui-widget ui-widget-content ui-corner-all'][not(contains(@style,'none'))]//a")
	public List<WebElement> commoditylist;

	@FindBy(xpath = "//*[@id='input_contractNum']")
	public WebElement selectcontract;

	@FindBy(xpath = "//td/iframe[@name='C1ReqMain']")
	public WebElement cartFrame;

	@FindBy(xpath = "//*[@class='modal-dialog']")
	public WebElement alertbox;

	@FindBy(xpath = "//*[@class='modal-title']")
	public WebElement alertboxtitle;

	@FindBy(xpath = "//div[@class='bootbox-body']")
	public WebElement alterboxmessage;
	
	@FindBy(xpath = "//div[contains(text(),'One or more contracts are available for the selected commodity. Would you like to select a contract as well?')]")
	public WebElement contractalert;
	
	@FindBy(xpath = "//button[@type='button' and contains(text(),'No')]")
	public WebElement btnNO;
	
	@FindBy(xpath = "//button[@type='button' and contains(text(),'Yes')]")
	public WebElement btnYES;

	@FindBy(xpath = "//button[text()='OK']")
	public WebElement okalertbtn;

	public ResourceLoader reqdata = new ResourceLoader("data/requisition"); 

	public OffCatalogReqPOM() {
		PageFactory.initElements(Browser.getDriver(), this);
	}

	public void addItemToOffCatReq() {

		try {
			// Navigate to correct portion of new req screen
			Browser.waitForElementToDisappear(By.id("loadingDiv"));
			Browser.waitForPageLoad();
			Browser.getDriver().switchTo().defaultContent();
			Browser.getDriver().switchTo().frame("C1ReqMain");

			// enter new req data
			setQuantity(reqdata.getValue("Quantity"));
			setEstimatedUnitPrice(reqdata.getValue("UnitPrice"));
			setSupplierPartNo(reqdata.getValue("SupplierPartNo"));
			setSupplierName(reqdata.getValue("SupplierName"));
			setManufacturer(reqdata.getValue("Manufacturer"));
			setCommodityCode(reqdata.getValue("CommodityCode"));

			clickAdd();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

/*
		checkRetainKeyInfo();
 */
	}
	

	public void donotenterquantity() throws Exception {
		Browser.waitForElementToDisappear(By.id("loadingDiv"));
		Browser.waitForPageLoad();

	}

	public void donotenterunitprice() throws Exception {
		Browser.waitForElementToDisappear(By.id("loadingDiv"));
		Browser.waitForPageLoad();

	}

	public void donotentercommoditycode() throws InterruptedException, Exception {
		Browser.waitForElementToDisappear(By.id("loadingDiv"));
		Browser.waitForPageLoad();

	}

	public void checkRetainKeyInfo() {
		
		Browser.WaitTillElementIsPresent(retainkeyinfo);
		if (retainkeyinfo.isSelected()) {
		} else {
			retainkeyinfo.click();
		}
	}

	public void setQuantity(String strquantity) {
		Browser.waitForElementToBeClickable(orderquantity);
		orderquantity.sendKeys(strquantity);
		Assert.assertFalse(orderquantity.getAttribute("value").isEmpty());
	}

	public void setUnitPrice(String selectedunit) {
		// new Select(dropdownunit).selectByValue("EA");
	
		try {
			Browser.waitForElementToBeClickable(dropdownunit);
			dropdownunit.click();
			Browser.waitForElementToBeClickable(unittextbox);
			unittextbox.sendKeys(selectedunit);
			for (WebElement unit : unitlist) {
				if (unit.getText().contentEquals(selectedunit)) {
					unit.click();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setEstimatedUnitPrice(String strunitprice) {
		Browser.waitForElementToBeClickable(unitprice);
		unitprice.sendKeys(strunitprice);
		Assert.assertFalse(unitprice.getAttribute("value").isEmpty());
	}

	public void selectcurrencycode() {
		new Select(dropdowncurrency).selectByValue("USD");
	}

	public void setSupplierPartNo(String strsupplierpartno) {
		Browser.waitForElementToBeClickable(supplierpartno);
		supplierpartno.sendKeys(strsupplierpartno);
	}

	public void setSupplierName(String suppliername) throws Exception {
		Browser.waitForElementToBeClickable(vendortextbox);
		vendortextbox.clear();
		vendortextbox.sendKeys(suppliername);
		Browser.visibilityOfListLocated(vendorlist);
	
	}

	public void setManufacturer(String manufacturername) throws Exception {
		Browser.waitForElementToBeClickable(mfrname);
		mfrname.clear();
		mfrname.sendKeys(manufacturername);
	}

	public void setCommodityCode(String commoditycode) throws Exception {
		System.out.println("commodity code:" + commoditycode);
		
		Browser.waitForElementToBeClickable(commoditybox);
		commoditybox.sendKeys(commoditycode);
	}

	public void clickAdd() throws Exception {
		
		// skip contract pop-up if shown
		if(contractalert.isDisplayed()) {
			btnNO.click();
		}
		
		Browser.waitForElementToBeClickable(additem);
		additem.click();
		System.out.println("Clicked on Add button");
	}

	public String bootAlertbox() throws Exception {

		Thread.sleep(Browser.defaultWait);
		String alerttitle = alertboxtitle.getText();
		System.out.println(alerttitle);

		return alerttitle;
	}

	public String bootalertmessage() throws Exception {
		Thread.sleep(Browser.defaultWait);
		String alertmessage = alterboxmessage.getText();
		System.out.println(alertmessage);

		return alertmessage;
	}

	public void acceptalertbox() {
		try {
			okalertbtn.click();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void movetoviewreq() throws Exception {
		Thread.sleep((Browser.defaultWait)*2);
		Browser.getDriver().switchTo().defaultContent();
		Thread.sleep(Browser.defaultWait);
		//Browser.switchToFrameBasedOnFrameName("C1ReqMain");
		// PCDriver.getDriver().switchTo().frame(reqframe);
		//viewreqtab.click();
	}

}
