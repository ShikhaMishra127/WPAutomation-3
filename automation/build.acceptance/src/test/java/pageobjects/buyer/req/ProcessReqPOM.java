package pageobjects.buyer.req;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import utilities.common.Browser;
import utilities.common.ResourceLoader;

import java.util.List;

public class ProcessReqPOM {


    public ProcessReqPOM() {
        PageFactory.initElements(Browser.getDriver(), this);
    }

    @FindBy(xpath = "//iframe")
    public List<WebElement> frames;

    @FindBy(xpath = "//iframe[@name='C1ReqMain']")
    public WebElement reqframe;

    @FindBy(xpath = "//iframe[@name='reqcart']")
    public WebElement reqcartframe;

    @FindBy(xpath = "//input[@name='txtReqName']")
    public WebElement reqname;

    @FindBy(xpath = "//a[@id='idView Request']")
    public WebElement viewreqtab;

    @FindBy(xpath = "//button[@name ='btnSubmit']")
    public WebElement submitbutton;

    @FindBy(xpath = "//div[contains(@class,'ui-pnotify')]")
    public WebElement bootalert;

    @FindBy(xpath = "//div[contains(@class,'ui-pnotify-text')]")
    public WebElement reqconfirmationmessage;

    public ResourceLoader reqdata = new ResourceLoader("data/requisition");


    public void getRequestName() {
        try {
            Browser.waitForPageLoad();
            Browser.getDriver().switchTo().defaultContent();
            Browser.switchToFrame(reqcartframe);
            Browser.waitForElementToBeClickable(reqname);
            if (reqname.getAttribute("value").isEmpty()) {
                Thread.sleep(Browser.defaultWait);
            }
            System.out.println("Req name is: " + reqname.getAttribute("value"));
            Assert.assertFalse(reqname.getAttribute("value").isEmpty());
        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    public void viewcart() {

        try {
            Browser.getDriver().switchTo().defaultContent();
            Browser.switchToFrame(reqframe);
            viewreqtab.click();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void submitRequest() {
        try {
            Browser.waitForPageLoad();
            Browser.getDriver().switchTo().defaultContent();
            Browser.switchToFrame(reqframe);
            Browser.waitForElementToBeClickable(submitbutton);
            submitbutton.click();
            Browser.waitForPageLoad();
            if (Browser.getDriver().getPageSource().contains("Confirmation")) {
                Browser.waitForElementToBeClickable(submitbutton);
                submitbutton.click();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String reqConfirmationMsg() {
        Browser.waitForElementToDisappear(By.id("loadingDiv"));
        Browser.getDriver().switchTo().defaultContent();
        Browser.WaitTillElementIsPresent(bootalert);
        Browser.waitForElementToBeClickable(bootalert);
        String reqsubmissionmsg = reqconfirmationmessage.getText();
        System.out.println(reqsubmissionmsg);
        return reqsubmissionmsg;
    }
}
