package testcases.buyer.req;

import com.relevantcodes.extentreports.LogStatus;
import java.io.IOException;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageobjects.buyer.req.ProcessReqPOM;
import pageobjects.buyer.req.ViewAllReqPOM;
import pageobjects.common.BuyerNavBarPOM;
import pageobjects.common.LoginPagePOM;
import utilities.common.Browser;
import utilities.common.DatePicker;
import utilities.common.ExtentReport;
import utilities.common.ResourceLoader;

public class ViewAllReqTC {

    public ViewAllReqTC() throws IOException {
        super();
    }

    Browser browser = new Browser();
    LoginPagePOM login = PageFactory.initElements(browser, LoginPagePOM.class);
    BuyerNavBarPOM navbar = PageFactory.initElements(browser, BuyerNavBarPOM.class);
    ViewAllReqPOM  viewall = PageFactory.initElements(browser, ViewAllReqPOM.class);
    ProcessReqPOM shoppingcart = PageFactory.initElements(browser, ProcessReqPOM.class);
    ExtentReport testreport = PageFactory.initElements(browser, ExtentReport.class);


    public ResourceLoader reqdata = new ResourceLoader("data/requisition");

    @BeforeClass
    public void setup() {
        // before starting our tests, first log into the system as a buyer
        try{
            testreport.logger = ExtentReport.report.startTest(this.getClass().getSimpleName());
            testreport.logger.log(LogStatus.INFO, "Test Case Started");
            login.loginAsBuyer();
            testreport.logger.log(LogStatus.PASS, "Logged in Successful");
        }
        catch (Exception e){
            e.printStackTrace();
            e.getMessage();
            Assert.fail();
        }
    }

    @Test
    public void viewallReq(){
        navbar.selectTopNavDropDown("Request");
        navbar.requestdropdown("View All");
        testreport.logger.log(LogStatus.PASS, "Redirected to View All Req Successful");
    }

    @Test(priority = 1, enabled = true)
    public void byStatus(){

        viewall.filterByStatus(reqdata.getValue("RequestStatus"));
        viewall.clickReset();
        testreport.logger.log(LogStatus.PASS, "Filtered By Status Successfull");
    }

    @Test(priority = 2, enabled = true)
    public void byRequester() {

        viewall.filterByRequester(reqdata.getValue("Requester"));
        viewall.clickReset();
        testreport.logger.log(LogStatus.PASS, "Filtered By Requester Successfull");
    }

    @Test(priority = 3, enabled = true)
    void byRequestNumber(){

        viewall.filterByRequestNumber(reqdata.getValue("RequestNumber"));
        viewall.clickReset();
        testreport.logger.log(LogStatus.PASS, "Filtered By RequestNumber Successfull");
    }

    @Test(priority = 4, enabled = true)
    public void byRequestName(){

        viewall.filterByRequestName(reqdata.getValue("RequestName"));
        viewall.clickReset();
        testreport.logger.log(LogStatus.PASS, "Filtered By RequestName Successfull");
    }

    @Test(priority = 5, enabled = true)
    public void byBuyerContact(){

        viewall.filteByBuyerContact(reqdata.getValue("BuyerContact"));
        viewall.clickReset();
        testreport.logger.log(LogStatus.PASS, "Filtered By BuyerContact Successfull");
    }

    @Test(priority = 6, enabled = true)
    public void byDate(){
        viewall.setFromDate(DatePicker.getPastDate());
        viewall.setToDate(DatePicker.getCurrentDate());
        viewall.clickSubmit();
        viewall.createDateAssertion();
        viewall.clickReset();
        testreport.logger.log(LogStatus.PASS, "Filtered By Date Successfull");
    }

    @Test(priority = 7, enabled = true)
    public void copyRequestTC(){
        viewall.copyRequest();
        Assert.assertEquals(shoppingcart.reqConfirmationMsg(), "New Request created.");
        shoppingcart.submitRequest();
        Assert.assertEquals(shoppingcart.reqConfirmationMsg(), "Request successfully submitted.");
        testreport.logger.log(LogStatus.PASS, "Request copied and created Successful");
    }

    @Test(priority = 8, enabled = false)
    public void printRequestTC(){
        viewall.reqPrint();
        viewall.validatePrint();
    }

    @AfterClass
    public void teardown() {
        navbar.logout();
        testreport.report.endTest(ExtentReport.logger);
        testreport.report.flush();
        //login.close();
    }

}
