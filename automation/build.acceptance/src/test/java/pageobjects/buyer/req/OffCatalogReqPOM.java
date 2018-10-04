package pageobjects.buyer.req;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import utilities.common.Browser;
import utilities.common.ResourceLoader;

import java.util.List;

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

    //	@FindBy(xpath = "//div[contains(text(),'One or more contracts are available for the selected commodity. Would you like to select a contract as well?')]")
    @FindBy(xpath = "//body[contains(@onload,'DisplayMsg()')]")
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
            checkRetainKeyInfo();

            // enter new req data
            setQuantity(reqdata.getValue("Quantity"));
            setEstimatedUnitPrice(reqdata.getValue("UnitPrice"));
            setSupplierPartNo(reqdata.getValue("SupplierPartNo"));
            setSupplierName(reqdata.getValue("SupplierName"));
            setManufacturer(reqdata.getValue("Manufacturer"));
            setCommodityCode(reqdata.getValue("SearchCommodityCode"), reqdata.getValue("CommodityCode"));

            //skip contract alert if appears
            if (contractalert.getAttribute("class").contains("modal-open")) {
                btnNO.click();
            }
            clickAdd();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void checkRetainKeyInfo() {
        Browser.WaitTillElementIsPresent(retainkeyinfo);
        if (retainkeyinfo.isSelected()) {
            System.out.println("Retain Key Info checkbox is already checked");
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
        vendortextbox.sendKeys("**");
        Browser.visibilityOfListLocated(vendorlist);
        for (WebElement vendor : vendorlist) {
            if (vendor.getText().contains(suppliername)) {
                Assert.assertEquals(vendor.getText(), suppliername);
                Browser.waitForElementToBeClickable(vendor);
                vendor.click();
            }
        }

    }

    public void setManufacturer(String manufacturername) throws Exception {
        Browser.waitForElementToBeClickable(mfrname);
        mfrname.clear();
        mfrname.sendKeys(manufacturername);
    }

    public void setCommodityCode(String searchcommoditycode, String commoditycode) throws Exception {
        Browser.waitForElementToBeClickable(commoditybox);
        commoditybox.sendKeys(searchcommoditycode);
        Browser.visibilityOfListLocated(commoditylist);

        for (WebElement commodity : commoditylist) {

            if (commodity.getText().contains(commoditycode)) {

                Assert.assertEquals(commodity.getText(), commoditycode);
                Browser.waitForElementToBeClickable(commodity);
                //((JavascriptExecutor) Browser.getDriver()).executeScript("window.confirm = function(msg){return false;};");
                commodity.click();
            }
        }
    }

    public void clickAdd() throws Exception {

        Browser.waitForElementToBeClickable(additem);
        additem.click();
       // System.out.println("Clicked on Add Button");
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

}
