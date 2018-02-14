package buyer.pageobjects.requestPageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import commonutils.pageobjects.utils.ExtentReport;
import commonutils.pageobjects.utils.PCDriver;
import commonutils.pageobjects.utils.ReadExcelData;

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

	//@FindBy(xpath = ".//*[@id='ui-id-1']")
	//public List<WebElement> vendorlist;
	
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

	@FindBy(xpath = "//button[text()='OK']")
	public WebElement okalertbtn;

	public OffCatalogReqPOM() {
		PageFactory.initElements(PCDriver.getDriver(), this);
	}

	public void additemtooffcatreq() throws Exception {

		PCDriver.waitForElementToDisappear(By.id("loadingDiv"));
		PCDriver.waitForPageLoad();

		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		PCDriver.getDriver().switchTo().defaultContent();
		// Thread.sleep(5000);
		// System.out.println(cartFrame.getSize());
		PCDriver.getDriver().switchTo().frame("C1ReqMain");
		// System.out.println(cartFrame.getAttribute("name"));
		checkRetainKeyInfo();
		setQuantity(ReadExcelData.getInstance("Request").getStringValue("Quantity"));
		selectUnit(ReadExcelData.getInstance("Request").getStringValue("unit"));
		setEstimatedUnitPrice(ReadExcelData.getInstance("Request").getStringValue("EstimatedUnitPrice"));
		selectcurrencycode();
		setsupplierpartno(ReadExcelData.getInstance("Request").getStringValue("SupplierPartNumer"));
		selectsupplier(ReadExcelData.getInstance("Request").getStringValue("suppliername"));
		selectmanufacturer(ReadExcelData.getInstance("Request").getStringValue("manufacturername"));
		selectcommoditycode(ReadExcelData.getInstance("Request").getStringValue("commoditycode"));
		// selectcontractnum();
		clickAdd();
	}

	public void checkRetainKeyInfo() {
		PCDriver.waitForElementToBeClickable(retainkeyinfo);
		if (retainkeyinfo.isSelected()) {
			System.out.println("Checkox is now checked");
		} else {
			retainkeyinfo.click();
		}
	}

	public void setQuantity(String strquantity) {
		PCDriver.waitForElementToBeClickable(orderquantity);
		orderquantity.sendKeys(strquantity);
		Assert.assertFalse(orderquantity.getAttribute("value").isEmpty());
	}

	public void selectUnit(String selectedunit) throws InterruptedException {
		// new Select(dropdownunit).selectByValue("EA");
		PCDriver.waitForElementToBeClickable(dropdownunit);
		dropdownunit.click();
		PCDriver.waitForElementToBeClickable(unittextbox);
		unittextbox.sendKeys(selectedunit);
		for (WebElement unit : unitlist) {
			if (unit.getText().contentEquals(selectedunit)) {
				unit.click();
			}
		}
	}

	public void setEstimatedUnitPrice(String strunitprice) {
		PCDriver.waitForElementToBeClickable(unitprice);
		unitprice.sendKeys(strunitprice);
		Assert.assertFalse(unitprice.getAttribute("value").isEmpty());
	}

	public void selectcurrencycode() {
		new Select(dropdowncurrency).selectByValue("USD");
	}

	public void setsupplierpartno(String strsupplierpartno) {
		PCDriver.waitForElementToBeClickable(supplierpartno);
		supplierpartno.sendKeys(strsupplierpartno);
	}

	public void selectsupplier(String suppliername) throws Exception {
		PCDriver.waitForElementToBeClickable(vendortextbox);
		vendortextbox.clear();
		vendortextbox.sendKeys(suppliername);
		PCDriver.visibilityOfListLocated(vendorlist);
		Thread.sleep(5000);
		System.out.println(vendorlist.size());
		for (WebElement vendor : vendorlist) {
			if (vendor.getText().contains(ReadExcelData.getInstance("Request").getStringValue("supplierselected"))) {
				//System.out.println(vendor.getText());
				Assert.assertEquals(vendor.getText(),
						ReadExcelData.getInstance("Request").getStringValue("supplierselected"));

				PCDriver.waitForElementToBeClickable(vendor);
				vendor.click();
				//System.out.println(vendortextbox.getAttribute("value"));
			}
		}
	}

	public void selectmanufacturer(String manufacturername) throws Exception {
		PCDriver.waitForElementToBeClickable(mfrname);
		mfrname.clear();
		mfrname.sendKeys(manufacturername);
		PCDriver.visibilityOfListLocated(mfrlist);
		Thread.sleep(5000);
		//System.out.println(mfrlist.size());
		for (WebElement manufacturer : mfrlist) {
			if (manufacturer.getText()
					.contains(ReadExcelData.getInstance("Request").getStringValue("manufacturerselected"))) {
				// System.out.println(manufacturer.getText());
				Assert.assertEquals(manufacturer.getText(),
						ReadExcelData.getInstance("Request").getStringValue("manufacturerselected"));

				PCDriver.waitForElementToBeClickable(manufacturer);
				manufacturer.click();
				//System.out.println(mfrname.getAttribute("value"));
			}
		}
	}

	public void selectcommoditycode(String commoditycode) throws Exception {
		System.out.println("commodity code:"+commoditycode);
		PCDriver.waitForElementToBeClickable(commoditybox);
		commoditybox.clear();
		commoditybox.sendKeys(commoditycode);
		PCDriver.visibilityOfListLocated(commoditylist);
		Thread.sleep(5000);
		//System.out.println("Size of list:" +commoditylist.size());
		for (WebElement commodity : commoditylist) {
			//System.out.println(commodity.getText());
			if (commodity.getText().contains("15101611")) {
				// System.out.println(commodity.getText());
				Assert.assertEquals(commodity.getText(),
						ReadExcelData.getInstance("Request").getStringValue("selectedcommodity"));
				
				//PCDriver.waitForElementToBeClickable(commodity);
				((JavascriptExecutor)PCDriver.getDriver()).executeScript("window.confirm = function(msg){return false;};");
				commodity.click();
			//System.out.println("choosen commodity: " +commoditybox.getAttribute("value"));
			//Assert.assertEquals(commodity.getText(), commoditybox.getAttribute("value"));
				try {
					//PCDriver.dismissAlert();
				
				} catch (Exception e) {
					System.out.println("No Alert Present");

				}
			}
		}
	}

	public void clickAdd() throws Exception {
		PCDriver.waitForElementToBeClickable(additem);
		additem.click();
		System.out.println("Clicked on Add");
	}

	public String bootAlertbox() throws Exception {
		
		Thread.sleep(2000);
		String alerttitle = alertboxtitle.getText();
		System.out.println(alerttitle);

		return alerttitle;
	}

	public void acceptalertbox() {
		try {
			okalertbtn.click();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
