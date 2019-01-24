package pageobjects.buyer.req;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import utilities.common.Browser;
import utilities.common.DatePicker;
import utilities.common.ResourceLoader;
import java.util.concurrent.TimeUnit;

public class ViewAllReqPOM {

    private final Browser browser;

    /**
     * Constructor called by PageFactory.instantiatePage
     * @param browser WebDriver (as required by PageFactory) will be cast back to Browser.
     */
    public ViewAllReqPOM(WebDriver browser) {
        this.browser = (Browser) browser;
        PageFactory.initElements(((Browser) browser).driver, this);
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

    @FindBy(xpath = "//font[@class = 'ReportFieldName']")
    public List<WebElement> printfieldnames;

    @FindBy(xpath = "//font[@class = 'ReportFieldResult']")
    public List<WebElement> printfieldvalues;

    @FindBy(xpath = "//li[@id='reqHistTab']")
    public WebElement historytab;

    @FindBy(xpath = "//li[@id='reqWFHistTab']")
    public WebElement historywftab;

    @FindBy(xpath = "//button[@title='Close']")
    public WebElement historyclosebtn;


    public ResourceLoader reqdata = new ResourceLoader("data/requisition");


    public void filterByStatus(String RequestStatus){
        browser.waitForElementToBeClickable(requeststatusdropdown);
        Select reqstatus = new Select(requeststatusdropdown);
        reqstatus.selectByVisibleText(RequestStatus);
        submitbtn.click();
        browser.waitForPageLoad();
        browser.WaitTillElementIsPresent(resulttable);
        for (int i = 1; i <= reqresultrows.size(); i++) {
            String actualreqstatus = reqstatuscol.getText().trim();
            Assert.assertEquals(actualreqstatus, reqdata.getValue("RequestStatus"));

        }
    }

    public void filterByRequester(String Requester){
        browser.waitForElementToBeClickable(requesterdropdown);
        Select requestername = new Select(requesterdropdown);
        requestername.selectByVisibleText(Requester);
        submitbtn.click();
        browser.waitForPageLoad();
        browser.WaitTillElementIsPresent(resulttable);
        for (int i = 1; i <= reqresultrows.size(); i++) {
            String actualrequester = requestercol.getText().trim();
            Assert.assertEquals(actualrequester, reqdata.getValue("Requester"));
        }
    }

    public void filterByRequestName(String RequestName) {
        browser.waitForElementToBeClickable(requestnamesearchox);
        requestnamesearchox.sendKeys(RequestName);
        submitbtn.click();
        browser.waitForPageLoad();
        browser.WaitTillElementIsPresent(resulttable);
        for (int i = 1; i <= reqresultrows.size(); i++) {
            String actualreqname = reqnamecol.getText().trim();
            Assert.assertEquals(actualreqname, requestnamesearchox.getAttribute("value"));
        }
    }

    public void filterByRequestNumber(String RequestNumber){
        browser.waitForElementToBeClickable(requestnumbersearchbox);
        requestnumbersearchbox.sendKeys(RequestNumber);
        submitbtn.click();
        browser.waitForPageLoad();
        browser.waitForElementToDisappear(By.id("loadingDiv"));
        browser.WaitTillElementIsPresent(resulttable);
        for (int i = 1; i <= reqresultrows.size(); i++) {
            String actualrequestnumber = reqnumbercol.getText().trim();
            Assert.assertEquals(actualrequestnumber, requestnumbersearchbox.getAttribute("value"));
        }
    }

     public void filteByBuyerContact(String BuyerContact){
        browser.waitForElementToBeClickable(buyercontactdropdown);
        Select buyercontact = new Select(buyercontactdropdown);
        buyercontact.selectByVisibleText(BuyerContact);
        submitbtn.click();
        browser.waitForPageLoad();
        browser.WaitTillElementIsPresent(resulttable);
         for (int i = 1; i <= reqresultrows.size(); i++) {
             String actualbuyercontact = buyercontactcol.getText().trim();
             Assert.assertEquals(actualbuyercontact, reqdata.getValue("BuyerContact"));
         }
     }

     public void setFromDate(String from){

        browser.waitForElementToBeClickable(fromdate);
        fromdate.click();
        browser.waitForElementToBeClickable(datepicker);
        fromdate.sendKeys(from);
        requestnamesearchox.click();
     }

     public void setToDate(String to){
         browser.waitForElementToBeClickable(todate);
         todate.click();
         browser.waitForElementToBeClickable(datepicker);
         todate.sendKeys(to);
         requestnamesearchox.click();
     }

     public void createDateAssertion(){
        try {
            browser.waitForPageLoad();
            browser.WaitTillElementIsPresent(resulttable);
            System.out.println(reqresultrows.size());
            Date fromDate = new SimpleDateFormat("MM/dd/yyyy").parse(DatePicker.getPastDate());
            Date toDate = new SimpleDateFormat("MM/dd/yyyy").parse(DatePicker.getCurrentDate());
            for (int i = 1; i <= reqresultrows.size(); i++) {
                String reqcreatedate = createdatecol.getText();
                System.out.println(reqcreatedate);
                Date createdDate = new SimpleDateFormat("MMMM dd, yyyy").parse(reqcreatedate);
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
        browser.waitForPageLoad();
        try {
            browser.waitForElementToDisappear(By.id("loadingDiv"));
            // System.out.println(actiondropdown.size());
            for (WebElement action : actiondropdown) {
                action.click();
                Thread.sleep(browser.defaultWait);
                browser.visibilityOfListLocated(actionlist);
                for (WebElement copyaction : actionlist) {
                    if (copyaction.getText().contains("Copy to Request")) {
                        TimeUnit.MILLISECONDS.sleep(browser.defaultWait);
                        //Thread.sleep(Browser.defaultWait);
                        copyaction.click();
                        browser.waitForElementToDisappear(By.id("loadingDiv"));
                        break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void reqPrint(){
        filterByRequestNumber(reqdata.getValue("RequestNumber"));
        browser.waitForPageLoad();
        try {
            browser.waitForElementToDisappear(By.id("loadingDiv"));
            // System.out.println(actiondropdown.size());
            for (WebElement action : actiondropdown) {
                action.click();
                TimeUnit.MILLISECONDS.sleep(browser.defaultWait);
                //Thread.sleep(Browser.defaultWait);
                browser.visibilityOfListLocated(actionlist);
                for (WebElement reqprinticon : actionlist) {
                    if (reqprinticon.getText().contains("Print")) {
                        //Thread.sleep(Browser.defaultWait);
                        TimeUnit.MILLISECONDS.sleep(browser.defaultWait);
                        reqprinticon.click();
                        browser.waitForElementToDisappear(By.id("loadingDiv"));
                        break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void validatePrint(){

        String viewallreqwindow = browser.driver.getWindowHandle();
        // System.out.println(viewallreqwindow);

        Set<String> printwindow = browser.driver.getWindowHandles();
        //System.out.println(printwindow);

        for (String printpriviewwindow: browser.driver.getWindowHandles()) {
            //System.out.println(printpriviewwindow);
            browser.driver.switchTo().window(printpriviewwindow);
            browser.driver.manage().window().maximize();
        }

        browser.waitForPageLoad();
        System.out.println(reqprintheader.getText());
        Assert.assertEquals(reqprintheader.getText(),"Request");
        int i= 0;
        for(WebElement fieldname : printfieldnames){
           // System.out.println(fieldname.getText());
            if(fieldname.getText().contains("Number:")){
             //   System.out.println(printfieldvalues.get(i).getText());
                Assert.assertEquals(printfieldvalues.get(i).getText(),reqdata.getValue("RequestNumber"));
                break;
            }
            i++;
        }
        browser.driver.close();
        browser.driver.switchTo().window(viewallreqwindow);
    }

    public void reqHistory(){
        filterByRequestNumber(reqdata.getValue("RequestNumber"));
        browser.waitForPageLoad();
        try {
            browser.waitForElementToDisappear(By.id("loadingDiv"));
            // System.out.println(actiondropdown.size());
            for (WebElement action : actiondropdown) {
                action.click();
                //Thread.sleep(Browser.defaultWait);
                TimeUnit.MILLISECONDS.sleep(browser.defaultWait);
                browser.visibilityOfListLocated(actionlist);
                for (WebElement reqhistoryicon : actionlist) {
                    if (reqhistoryicon.getText().contains("View Request History")) {
                        //Thread.sleep(Browser.defaultWait);
                        TimeUnit.MILLISECONDS.sleep(browser.defaultWait);
                        reqhistoryicon.click();
                        browser.waitForElementToDisappear(By.id("loadingDiv"));
                        break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void verifyreqHistory(){
        //browser.visibilityOfElement(historytab);
        Assert.assertTrue(browser.driver.getPageSource().contains("Request History Log"));
        browser.waitForElementToBeClickable(historywftab);
        historywftab.click();
        Assert.assertTrue(browser.driver.getPageSource().contains("Workflow History Log"));
        historyclosebtn.click();
    }


    public void clickSubmit(){
        browser.waitForElementToBeClickable(submitbtn);
        submitbtn.click();
    }

    public void clickReset(){
        browser.waitForElementToBeClickable(resetbtn);
        resetbtn.click();
        browser.waitForPageLoad();
    }

}
