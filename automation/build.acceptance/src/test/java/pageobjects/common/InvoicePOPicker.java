package pageobjects.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.common.Browser;

public class InvoicePOPicker {
    private final Browser browser;

    public InvoicePOPicker(WebDriver browser) {
        this.browser = (Browser)browser;
        PageFactory.initElements(((Browser) browser).driver, this);
    }

    ////////////////////////////////////////////////////////////////////////

   //@FindBy(xpath = "//iframe[contains(@src,'InvoiceCreate')]")
    @FindBy(xpath = "(//iFrame)[1]")
    public WebElement iFrame;

    @FindBy(xpath = "//input[@id='ponum_filter']")
    public WebElement lookupOrderNumberEdit;

    @FindBy(xpath = "//button[contains(@onclick,'javascript:submitSearch')]")
    public WebElement lookupSearchButton;

    @FindBy(xpath = "//section[@id='cont-search']")
    public WebElement lookupResults;

    @FindBy(xpath = "//table/tbody/tr/th/input[@class='checkall']")
    public WebElement POIncludeAllCheck;

    @FindBy(xpath = "//button[contains(@onclick,'submitPO')]")
    public WebElement addPOItemsButton;

    ////////////////////////////////////////////////////////////////////////

    /**
     * Routine will enter a PO number as a search parameter and add all the line items
     * contained within that PO into an invoice
     *
     * @param ponum - The PO number containing all the items we want to add
     */
    public void addAllPOItems(String ponum) {
        browser.waitForPageLoad();

        // wait for the PO search page to load. Apparently this is a big problem
        browser.waitForElementToBeClickable(lookupOrderNumberEdit,(long)5);
        browser.switchToFrame(iFrame);
        browser.waitForPageLoad();

        // enter target PO and click Search
        browser.sendKeysWhenAvailable(lookupOrderNumberEdit, ponum);
        browser.clickWhenAvailable(lookupSearchButton);

        // when you find the PO, click the drop-arrow to the left to expand
        try { Thread.sleep(3000); } catch (InterruptedException e) { e.printStackTrace(); }

        String arrowxpath = "//table/tbody/tr/td/a[contains(text(),'" + ponum + "')]/parent::*/preceding-sibling::*//i[contains(@class,'hand-pointer')]";
        WebElement element = browser.driver.findElement(By.xpath(arrowxpath));

        browser.clickWhenAvailable(element);

        // then include all po items and click Add
        browser.clickWhenAvailable(POIncludeAllCheck);
        browser.clickWhenAvailable(addPOItemsButton);

        browser.waitForPageLoad();
    }

}
