package pageobjects.buyer.orders;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import utilities.common.Browser;
import utilities.common.DatePicker;
import utilities.common.ResourceLoader;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ViewAllOrderPOM {

    private final Browser browser;

    public ViewAllOrderPOM(WebDriver browser) {
        this.browser = (Browser) browser;
        PageFactory.initElements(((Browser) browser).driver, this);
    }

    @FindBy(xpath = "//select[@id = 'FSts']")
    WebElement buyerstatusdropdown;

    @FindBy(xpath = "//select[@id = 'FBUYER']")
    WebElement buyerdropdown;

    @FindBy(xpath = "//select[@id = 'FUser']")
    WebElement requesterdropdown;

    @FindBy(xpath = "//input[@id = 'FBorg']")
    WebElement organizationtxtbox;

    @FindBy(xpath = "//input[@id = 'FPONbr']")
    WebElement ordernumbetxtbox;

    @FindBy(xpath = "//input[@id = 'FPOOrdername']")
    WebElement ordernametxtbox;

    @FindBy(xpath = "//input[@id = 'FPOSupplierName']")
    WebElement suppliertxtbox;

    @FindBy(xpath = "//select[@id = 'FTransmitStatus']")
    WebElement transmissionstatusdropdwon;

    @FindBy(xpath = "//input[@id='FromDate']")
    public WebElement fromdate;

    @FindBy(xpath = "//input[@id='ToDate']")
    public WebElement todate;

    @FindBy(xpath = "//div[(@class='datepicker datepicker-dropdown dropdown-menu datepicker-orient-left datepicker-orient-top') and contains(@style,'display: block')]")
    public WebElement datepicker;

    @FindBy(xpath = "//button[@name='Submit']")
    public WebElement submitbtn;

    @FindBy(xpath = "//button[@name='Reset']")
    public WebElement resetbtn;

    @FindBy(xpath = "//button[@name='Clear Filters']")
    public WebElement clearfilterbtn;

    @FindBy(xpath = "//table[contains(@id,'poTable')]")
    public WebElement resulttable;

    @FindBy(xpath = "//tbody//tr[contains(@role,'row')]")
    public List<WebElement> orderresultrows;

    @FindBy(xpath = "//table//tr[contains(@role,'row')]//td[10]")
    public WebElement orderstatuscol;

    @FindBy(xpath = "//table//tr[contains(@role,'row')]//td[5]")
    public WebElement buyercol;

    @FindBy(xpath = "//table//tr[contains(@role,'row')]//td[4]")
    public WebElement requestercol;

    @FindBy(xpath = "//table//tr[contains(@role,'row')]//td[2]")
    public WebElement organizationcol;

    @FindBy(xpath = "//table//tr[contains(@role,'row')]//td[3]")
    public WebElement ordernum_namecol;

    @FindBy(xpath = "//table//tr[contains(@role,'row')]//td[7]")
    public WebElement suppliercol;

    @FindBy(xpath = "//table//tr[contains(@role,'row')]//td[6]")
    public WebElement ordercreatedatecol;

    public ResourceLoader orderdata = new ResourceLoader("data/order");

    public void filterbyBuyerStatus(String BuyerStatus){

        browser.waitForElementToBeClickable(buyerstatusdropdown);
        Select buyerstatus = new Select(buyerstatusdropdown);
        buyerstatus.selectByVisibleText(BuyerStatus);
        submitbtn.click();
        browser.waitForPageLoad();
        browser.WaitTillElementIsPresent(resulttable);
        if(orderresultrows.size()>0) {
            for (int i = 1; i <= orderresultrows.size(); i++) {
                String actualbuyerstatus = orderstatuscol.getText().trim();
                System.out.println(actualbuyerstatus);
                Assert.assertTrue(actualbuyerstatus.contains(orderdata.getValue("BuyerStatus")));
            }
        }else{
            System.out.println("No Orders found matching the selected filter.");
        }
    }

    public  void filterbyBuyer(String Buyer){

        browser.waitForElementToBeClickable(buyerdropdown);
        Select buyer = new Select(buyerdropdown);
        buyer.selectByVisibleText(Buyer);
        submitbtn.click();
        browser.waitForPageLoad();
        browser.WaitTillElementIsPresent(resulttable);
        if(orderresultrows.size()>0){
            for (int i= 1 ; i<=orderresultrows.size(); i++){
                String actualbuyer = buyercol.getText().trim();
                System.out.println(actualbuyer);
                Assert.assertEquals(actualbuyer, orderdata.getValue("Buyer") );
        }
        }else{
            System.out.println("No Orders found matching the selected filter.");
        }
    }

    public void filterbyRequester(String Requester){

        browser.waitForElementToBeClickable(requesterdropdown);
        Select requester = new Select(requesterdropdown);
        requester.selectByVisibleText(Requester);
        submitbtn.click();
        browser.waitForPageLoad();
        browser.WaitTillElementIsPresent(resulttable);
        if(orderresultrows.size()>0){
        for (int i =1; i<=orderresultrows.size(); i++){
            String actualrequester = requestercol.getText().trim();
            System.out.println(actualrequester);
            Assert.assertTrue(actualrequester.contains(orderdata.getValue("Requester")));
        }
        }else{
            System.out.println("No Orders found matching the selected filter.");
        }
    }

    public void filterbyOrganization(String Organization){

        browser.waitForElementToBeClickable(organizationtxtbox);
        organizationtxtbox.clear();
        organizationtxtbox.sendKeys(Organization);
        submitbtn.click();
        browser.waitForPageLoad();
        browser.WaitTillElementIsPresent(resulttable);
        if(orderresultrows.size()>0){
        for(int i =1; i<=orderresultrows.size(); i++){
            String actualorg = organizationcol.getText().trim();
            System.out.println(actualorg);
            Assert.assertEquals(actualorg, orderdata.getValue("Organization"));
        }
    }else{
        System.out.println("No Orders found matching the selected filter.");
    }
    }

    public void filterbyOrderNumber(String OrderNumber){
        browser.waitForElementToBeClickable(ordernumbetxtbox);
        ordernumbetxtbox.clear();
        ordernumbetxtbox.sendKeys(OrderNumber);
        submitbtn.click();
        browser.waitForPageLoad();
        browser.WaitTillElementIsPresent(resulttable);
        if(orderresultrows.size()>0){
            for(int i =1; i<=orderresultrows.size(); i++){
                String acutalordernum = ordernum_namecol.getText().trim();
                System.out.println(acutalordernum);
                Assert.assertTrue(acutalordernum.contains(ordernumbetxtbox.getText()));
            }
        }else{
            System.out.println("No Orders found matching the selected filter.");
        }
    }


    public void filterbyOrderName(String OrderName){
        browser.waitForElementToBeClickable(ordernametxtbox);
        ordernametxtbox.clear();
        ordernametxtbox.sendKeys(OrderName);
        submitbtn.click();
        browser.waitForPageLoad();
        browser.WaitTillElementIsPresent(resulttable);
        if(orderresultrows.size()>0){
            for(int i =1; i<=orderresultrows.size(); i++){
                String acutalordername = ordernum_namecol.getText().trim();
                System.out.println(acutalordername);
                Assert.assertTrue(acutalordername.contains(ordernametxtbox.getText()));
            }
        }else{
            System.out.println("No Orders found matching the selected filter.");
        }
    }

    public void filterbySupplier(String Supplier){
        browser.waitForElementToBeClickable(suppliertxtbox);
        suppliertxtbox.clear();
        suppliertxtbox.sendKeys(Supplier);
        submitbtn.click();
        browser.waitForPageLoad();
        browser.WaitTillElementIsPresent(resulttable);
        if(orderresultrows.size()>0){
            for(int i =1; i<=orderresultrows.size(); i++){
                String acutalsupplier = suppliercol.getText().trim();
                System.out.println(acutalsupplier);
                Assert.assertTrue(acutalsupplier.contains(suppliertxtbox.getText()));
            }
        }else{
            System.out.println("No Orders found matching the selected filter.");
        }
    }

    public void filterbyTransmissionStatus(String TransmissonStatus){

        browser.waitForElementToBeClickable(transmissionstatusdropdwon);
        Select transmissionstatus = new Select(transmissionstatusdropdwon);
        transmissionstatus.selectByVisibleText(TransmissonStatus);
        submitbtn.click();
        browser.waitForPageLoad();
        browser.WaitTillElementIsPresent(resulttable);
        if(orderresultrows.size()>0) {
            for (int i = 1; i <= orderresultrows.size(); i++) {
                String actualtransmissionstatus = orderstatuscol.getText().trim();
                System.out.println(actualtransmissionstatus);
                Assert.assertTrue(actualtransmissionstatus.contains(orderdata.getValue("TransmissionStatus")));
            }
        }else{
            System.out.println("No Orders found matching the selected filter.");
        }
    }

    public void setFromDate(String from){

        browser.waitForElementToBeClickable(fromdate);
        fromdate.click();
        browser.waitForElementToBeClickable(datepicker);
        fromdate.sendKeys(from);
        ordernametxtbox.click();
    }

    public void setToDate(String to){
        browser.waitForElementToBeClickable(todate);
        todate.click();
        browser.waitForElementToBeClickable(datepicker);
        todate.sendKeys(to);
        ordernametxtbox.click();
    }

    public void createDateAssertion(){
        try {
            browser.waitForPageLoad();
            browser.WaitTillElementIsPresent(resulttable);
            System.out.println(orderresultrows.size());
            Date fromDate = new SimpleDateFormat("MM/dd/yyyy").parse(DatePicker.getPastDate());
            Date toDate = new SimpleDateFormat("MM/dd/yyyy").parse(DatePicker.getCurrentDate());
            for (int i = 1; i <= orderresultrows.size(); i++) {
                String reqcreatedate = ordercreatedatecol.getText();
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
