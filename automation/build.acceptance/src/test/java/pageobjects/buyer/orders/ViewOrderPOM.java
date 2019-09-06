package pageobjects.buyer.orders;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.common.Browser;

import java.util.HashMap;
import java.util.Map;

public class ViewOrderPOM {


    private final Browser browser;

    public ViewOrderPOM(WebDriver browser) {
        this.browser = (Browser) browser;
        PageFactory.initElements(((Browser) browser).driver, this);
    }

    //////////////////////////////////////////////////////////////////////// COMMON CONTROLS

    @FindBy(xpath="//select[@id='FSts']")
    public WebElement mainSearchOrderStatusDrop;

    @FindBy(xpath="//input[@id='FPONbr']")
    public WebElement mainSearchOrderNumberEdit;

    @FindBy(xpath="//input[@id='FPOSupplierName']")
    public WebElement mainSearchSupplierEdit;

    @FindBy(xpath="//button[@name='Submit']")
    public WebElement mainSearchSubmitButton;

    @FindBy(xpath="//button[@name='Reset']")
    public WebElement mainSearchResetButton;

    @FindBy(xpath="//table[@id='poTable']")
    public WebElement searchPOResultsTable;


    //////////////////////////////////////////////////////////////////////// HELPFUL METHODS

    public enum POListColumn {BOGUS, DETAILS, ORG, BUYER, ENDDATE, STATUS, ACTION}

    public Map<POListColumn, WebElement> getElementsForPOLine(String ponum) {

        // Look for our row in the filtered list of items
        String xpathrow = "//td[contains(text(),'" + ponum + "')]/parent::*";

        browser.waitForElementToAppear(By.xpath(xpathrow));

        HashMap<POListColumn, WebElement> elements = new HashMap<>();

        // build a list of WebElements that reference each column (name, number, status, etc)
        for (int i = 1; i < POListColumn.values().length; i++) {

            String columnxpath = xpathrow + "/td[" + String.valueOf(i) + "]";

            browser.waitForElementToAppear(By.xpath(columnxpath));
            elements.put(POListColumn.values()[i], searchPOResultsTable.findElement(By.xpath(columnxpath)));
        }

        return elements;
    }
}
