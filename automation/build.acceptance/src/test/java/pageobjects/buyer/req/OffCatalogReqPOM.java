package pageobjects.buyer.req;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import utilities.common.Browser;

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

	public OffCatalogReqPOM() {
		PageFactory.initElements(Browser.getDriver(), this);
	}

	public void additemtooffcatreq() throws Exception {

		Browser.waitForElementToDisappear(By.id("loadingDiv"));
		Browser.waitForPageLoad();

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
			System.out.println("Checkox is now checked");
		} else {
			retainkeyinfo.click();
		}
	}

	public void setQuantity(String strquantity) {
		Browser.waitForElementToBeClickable(orderquantity);
		orderquantity.sendKeys(strquantity);
		Assert.assertFalse(orderquantity.getAttribute("value").isEmpty());
	}

	public void selectUnit(String selectedunit) throws InterruptedException {
		// new Select(dropdownunit).selectByValue("EA");
		Browser.waitForElementToBeClickable(dropdownunit);
		dropdownunit.click();
		Browser.waitForElementToBeClickable(unittextbox);
		unittextbox.sendKeys(selectedunit);
		for (WebElement unit : unitlist) {
			if (unit.getText().contentEquals(selectedunit)) {
				unit.click();
			}
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

	public void setsupplierpartno(String strsupplierpartno) {
		Browser.waitForElementToBeClickable(supplierpartno);
		supplierpartno.sendKeys(strsupplierpartno);
	}

	public void selectsupplier(String suppliername) throws Exception {
		Browser.waitForElementToBeClickable(vendortextbox);
	
	}

	public void selectmanufacturer(String manufacturername) throws Exception {
		Browser.waitForElementToBeClickable(mfrname);

	}

	public void selectcommoditycode(String commoditycode) throws Exception {
		System.out.println("commodity code:" + commoditycode);

	}

	public void clickAdd() throws Exception {
		Browser.waitForElementToBeClickable(additem);
		additem.click();
		System.out.println("Clicked on Add");
	}

	public String bootAlertbox() throws Exception {

		Thread.sleep(2000);
		String alerttitle = alertboxtitle.getText();
		System.out.println(alerttitle);

		return alerttitle;
	}

	public String bootalertmessage() throws Exception {
		Thread.sleep(2000);
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

}
