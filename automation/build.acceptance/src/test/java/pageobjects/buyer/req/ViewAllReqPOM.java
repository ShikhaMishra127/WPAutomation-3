package pageobjects.buyer.req;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import utilities.common.Browser;
import utilities.common.DatePicker;
import utilities.common.ResourceLoader;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Set;

public class ViewAllReqPOM {
    public ViewAllReqPOM() {
        PageFactory.initElements(Browser.getDriver(), this);
    }

    @FindBy(xpath = "//select[@id='FSts']")
    public WebElement requeststatusdropdown;

    @FindBy(xpath = "//select[@id='FUser']")
    public WebElement requesterdropdown;

    @FindBy(xpath = "//input[@id='FShoppingCartNum']")
    public WebElement requestnumbersearchbox;

    @FindBy(xpath = "//input[@id='FShoppingCartName']")
    public WebElement requestnamesearchox;

    @FindBy(xpath = "//input[@id='FBorg']")
    public WebElement organizationsearchox;

    @FindBy(xpath = "//select[@id='FBUYER']")
    public WebElement buyercontactdropdown;

    @FindBy(xpath = "//div[(@class='datepicker datepicker-dropdown dropdown-menu datepicker-orient-left datepicker-orient-top') and contains(@style,'display: block')]")
    public WebElement datepicker;

    @FindBy(xpath = "//input[@id='FromDate']")
    public WebElement fromdate;

    @FindBy(xpath = "//input[@id='ToDate']")
    public WebElement todate;

    @FindBy(xpath = "//button[@name='Submit']")
    public WebElement submitbtn;

    @FindBy(xpath = "//table[contains(@id,'reqTable')]")
    public WebElement resulttable;

    @FindBy(xpath = "//tbody//tr[contains(@role,'row')]")
    public List<WebElement> reqresultrows;

    @FindBy(xpath = "//table//tr[contains(@role,'row')]//td[8]")
    public WebElement reqstatuscol;

    @FindBy(xpath = "//table//tr[contains(@role,'row')]//td[5]")
    public WebElement requestercol;

    @FindBy(xpath = "//table//tr[contains(@role,'row')]//td[4]")
    public WebElement reqnamecol;

    @FindBy(xpath = "//table//tr[contains(@role,'row')]//td[3]")
    public WebElement reqnumbercol;

    @FindBy(xpath = "//table//tr[contains(@role,'row')]//td[6]")
    public WebElement buyercontactcol;

    @FindBy(xpath = "//table//tr[contains(@role,'row')]//td[7]")
    public WebElement createdatecol;

    @FindBy(xpath = "//button[@name='Reset']")
    public WebElement resetbtn;

    @FindBy(xpath = "//button[@name='Clear Filters']")
    public WebElement clearfilterbtn;

    @FindBy(xpath = "//img[contains(@title,'More Actions')]")
    public List<WebElement> actiondropdown;

    @FindBy(xpath = "//ul[contains(@class,'dropdown-menu dropdown-menu-right')]//li")
    public List<WebElement> actionlist;

    @FindBy(xpath = "//tr[@class='ReportHeader']")
    public WebElement reqprintheader;



    public ResourceLoader reqdata = new ResourceLoader("data/requisition");


    public void filterByStatus(String RequestStatus){
        Browser.waitForElementToBeClickable(requeststatusdropdown);
        Select reqstatus = new Select(requeststatusdropdown);
        reqstatus.selectByVisibleText(RequestStatus);
        submitbtn.click();
        Browser.waitForPageLoad();
        Browser.WaitTillElementIsPresent(resulttable);
        for (int i = 1; i <= reqresultrows.size(); i++) {
            String actualreqstatus = reqstatuscol.getText().trim();
            Assert.assertEquals(actualreqstatus, reqdata.getValue("RequestStatus"));

        }
    }

    public void filterByRequester(String Requester){
        Browser.waitForElementToBeClickable(requesterdropdown);
        Select requestername = new Select(requesterdropdown);
        requestername.selectByVisibleText(Requester);
        submitbtn.click();
        Browser.waitForPageLoad();
        Browser.WaitTillElementIsPresent(resulttable);
        for (int i = 1; i <= reqresultrows.size(); i++) {
            String actualrequester = requestercol.getText().trim();
            Assert.assertEquals(actualrequester, reqdata.getValue("Requester"));
        }
    }

    public void filterByRequestName(String RequestName) {
        Browser.waitForElementToBeClickable(requestnamesearchox);
        requestnamesearchox.sendKeys(RequestName);
        submitbtn.click();
        Browser.waitForPageLoad();
        Browser.WaitTillElementIsPresent(resulttable);
        for (int i = 1; i <= reqresultrows.size(); i++) {
            String actualreqname = reqnamecol.getText().trim();
            Assert.assertEquals(actualreqname, requestnamesearchox.getAttribute("value"));
        }
    }

    public void filterByRequestNumber(String RequestNumber){
        Browser.waitForElementToBeClickable(requestnumbersearchbox);
        requestnumbersearchbox.sendKeys(RequestNumber);
        submitbtn.click();
        Browser.waitForPageLoad();
        Browser.waitForElementToDisappear(By.id("loadingDiv"));
        Browser.WaitTillElementIsPresent(resulttable);
        for (int i = 1; i <= reqresultrows.size(); i++) {
            String actualrequestnumber = reqnumbercol.getText().trim();
            Assert.assertEquals(actualrequestnumber, requestnumbersearchbox.getAttribute("value"));
        }
    }

     public void filteByBuyerContact(String BuyerContact){
        Browser.waitForElementToBeClickable(buyercontactdropdown);
        Select buyercontact = new Select(buyercontactdropdown);
        buyercontact.selectByVisibleText(BuyerContact);
        submitbtn.click();
        Browser.waitForPageLoad();
        Browser.WaitTillElementIsPresent(resulttable);
         for (int i = 1; i <= reqresultrows.size(); i++) {
             String actualbuyercontact = buyercontactcol.getText().trim();
             Assert.assertEquals(actualbuyercontact, reqdata.getValue("BuyerContact"));
         }
     }

     public void setFromDate(String from){

        Browser.waitForElementToBeClickable(fromdate);
        fromdate.click();
        Browser.waitForElementToBeClickable(datepicker);
        fromdate.sendKeys(from);
        requestnamesearchox.click();
     }

     public void setToDate(String to){
         Browser.waitForElementToBeClickable(todate);
         todate.click();
         Browser.waitForElementToBeClickable(datepicker);
         todate.sendKeys(to);
         requestnamesearchox.click();
     }

     public void createDateAssertion(){
        try {
            Browser.waitForPageLoad();
            Browser.WaitTillElementIsPresent(resulttable);
            System.out.println(reqresultrows.size());
            Date fromDate = new SimpleDateFormat("MM/dd/yyyy").parse(DatePicker.getPastDate());
            Date toDate = new SimpleDateFormat("MM/dd/yyyy").parse(DatePicker.getCurrentDate());
            for (int i = 1; i <= reqresultrows.size(); i++) {
                String reqcreatedate = buyercontactcol.getText().trim();
                Date createdDate = new SimpleDateFormat("MM/dd/yyyy").parse(reqcreatedate);
                System.out.println(createdDate);
                Assert.assertTrue(fromDate.before(createdDate));
                Assert.assertFalse(toDate.after(createdDate));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void copyRequest(){
        filterByRequestNumber(reqdata.getValue("RequestNumber"));
        Browser.waitForPageLoad();
        try {
            Browser.waitForElementToDisappear(By.id("loadingDiv"));
            // System.out.println(actiondropdown.size());
            for (WebElement action : actiondropdown) {
                action.click();
                Thread.sleep(Browser.defaultWait);
                Browser.visibilityOfListLocated(actionlist);
                for (WebElement copyaction : actionlist) {
                    if (copyaction.getText().contains("Copy to Request")) {
                        Thread.sleep(Browser.defaultWait);
                        copyaction.click();
                        Browser.waitForElementToDisappear(By.id("loadingDiv"));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void reqPrint(){
        filterByRequestNumber(reqdata.getValue("RequestNumber"));
        Browser.waitForPageLoad();
        try {
            Browser.waitForElementToDisappear(By.id("loadingDiv"));
            // System.out.println(actiondropdown.size());
            for (WebElement action : actiondropdown) {
                action.click();
                Thread.sleep(Browser.defaultWait);
                Browser.visibilityOfListLocated(actionlist);
                for (WebElement copyaction : actionlist) {
                    if (copyaction.getText().contains("Print")) {
                        Thread.sleep(Browser.defaultWait);
                        copyaction.click();
                        Browser.waitForElementToDisappear(By.id("loadingDiv"));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void validatePrint(){

        String viewallreqwindow = Browser.driver.getWindowHandle();
        // System.out.println(viewallreqwindow);

        Set<String> printwindow = Browser.driver.getWindowHandles();
        //System.out.println(printwindow);

        for (String printpriviewwindow: Browser.driver.getWindowHandles()) {
            //System.out.println(printpriviewwindow);
            Browser.driver.switchTo().window(printpriviewwindow);
            Browser.driver.manage().window().maximize();
        }
        Browser.waitForPageLoad();
        System.out.println(reqprintheader.getText());
        Assert.assertEquals(reqprintheader.getText(),"Request");
    }

    public void clickSubmit(){
        Browser.waitForElementToBeClickable(submitbtn);
        submitbtn.click();
    }

    public void clickReset(){
        Browser.waitForElementToBeClickable(resetbtn);
        resetbtn.click();
        Browser.waitForPageLoad();
    }

}
