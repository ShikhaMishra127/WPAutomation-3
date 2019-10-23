package pageobjects.vendor.orders;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.common.Browser;

import java.util.Map;

public class VendorOrderViewPOM {

    private final Browser browser;

    public VendorOrderViewPOM(WebDriver browser) {
        this.browser = (Browser) browser;
        PageFactory.initElements(((Browser) browser).driver, this);
    }

    ////////////////////////////////////////////////////////////////////////

    @FindBy(xpath="//span[contains(@id,'statusfilter')]")
    public WebElement orderStatusFilterDrop;

    @FindBy(xpath="//input[@id='ponumfilter']")
    public WebElement orderNumberEdit;

    @FindBy(xpath="//button[@id='find']")
    public WebElement orderFilterListButton;

    @FindBy(xpath="//button[contains(@onclick, 'cancelPath')]")
    public WebElement orderCloseButton;



    ////////////////////////////////////////////////////////////////////////

    @FindBy(xpath="//section[@id='page-title']//h3")
    public WebElement summaryOrderTitle;

    @FindBy(xpath="//button[@id='acknowledgeAction']")
    public WebElement summaryAcknowledgeButton;

    @FindBy(xpath="//textarea[@id='vendorAckComments']")
    public WebElement summaryCommentsEdit;

    @FindBy(xpath="//input[contains(@onclick,'acknowledge')]")
    public WebElement summaryCommentsContinueButton;


    ////////////////////////////////////////////////////////////////////////



    @FindBy(xpath="//table[@id='poTable']")
    public WebElement poTable;


    public enum VendorPOListColumn implements Browser.HTMLTableColumn { BOGUS, NUMBER, ORG, DATE, TOTAL, STATUS }

    public Map<Browser.HTMLTableColumn, WebElement> getElementsForPOLine(String searchString) {

        String rowXPath = "//td/a[contains(text(),'" + searchString + "')]/parent::*/parent::*";

        return browser.buildTableMap(poTable, rowXPath, VendorPOListColumn.values());
    }
}
