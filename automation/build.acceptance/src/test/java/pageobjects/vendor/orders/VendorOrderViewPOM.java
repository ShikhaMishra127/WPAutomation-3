package pageobjects.vendor.orders;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.common.Browser;

import java.util.HashMap;
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

    ////////////////////////////////////////////////////////////////////////

    @FindBy(xpath="//table[@id='poTable']")
    public WebElement poTable;


    public enum POListColumn { BOGUS, NUMBER, ORG, DATE, TOTAL, STATUS }

    public Map<POListColumn, WebElement> getElementsForPOLine(String searchString) {

        // Look for our row in the filtered list of results
        String xpathrow = "//td/a[contains(text(),'" + searchString + "')]/parent::*/parent::*";
        browser.waitForElementToAppear(By.xpath(xpathrow));

        HashMap<POListColumn, WebElement> elements = new HashMap<>();

        // build a list of WebElements that reference each column (name, number, status, etc)
        for (int i = 1; i < POListColumn.values().length; i++) {

            String columnxpath = xpathrow + "/td[" + String.valueOf(i) +  "]";

            browser.waitForElementToAppear(By.xpath(columnxpath));
            elements.put(POListColumn.values()[i], poTable.findElement(By.xpath(columnxpath)));
        }

        return elements;
    }

}
