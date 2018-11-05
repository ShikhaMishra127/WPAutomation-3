package pageobjects.buyer.req;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import utilities.common.Browser;
import utilities.common.ResourceLoader;

import java.util.List;

public class ProcessReqPOM {

    private final Browser browser;

    /**
     * Constructor called by PageFactory.instantiatePage
     * @param browser WebDriver (as required by PageFactory) will be cast back to Browser.
     */
    public ProcessReqPOM(WebDriver browser) {
        this.browser = (Browser) browser;
        PageFactory.initElements(((Browser) browser).driver, this);
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
            browser.waitForPageLoad();
            browser.getDriver().switchTo().defaultContent();
            browser.switchToFrame(reqcartframe);
            browser.waitForElementToBeClickable(reqname);
            if (reqname.getAttribute("value").isEmpty()) {
                Thread.sleep(browser.defaultWait);
            }
            System.out.println("Req name is: " + reqname.getAttribute("value"));
            Assert.assertFalse(reqname.getAttribute("value").isEmpty());
        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    public void viewcart() {

        try {
            browser.getDriver().switchTo().defaultContent();
            browser.switchToFrame(reqframe);
            viewreqtab.click();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void submitRequest() {
        try {
            browser.waitForPageLoad();
            browser.getDriver().switchTo().defaultContent();
            browser.switchToFrame(reqframe);
            browser.waitForElementToBeClickable(submitbutton);
            submitbutton.click();
            browser.waitForPageLoad();
            if (browser.getDriver().getPageSource().contains("Confirmation")) {
                browser.waitForElementToBeClickable(submitbutton);
                submitbutton.click();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String reqConfirmationMsg() {
        browser.waitForElementToDisappear(By.id("loadingDiv"));
        browser.getDriver().switchTo().defaultContent();
        browser.WaitTillElementIsPresent(bootalert);
        browser.waitForElementToBeClickable(bootalert);
        String reqsubmissionmsg = reqconfirmationmessage.getText();
        System.out.println(reqsubmissionmsg);
        return reqsubmissionmsg;
    }
}
