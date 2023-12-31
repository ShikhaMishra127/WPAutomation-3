package pageobjects.vendor.common;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.common.Browser;

public class VendorNavBarPOM {

    private final Browser browser;

    public VendorNavBarPOM(WebDriver browser) {
        this.browser = (Browser)browser;
        PageFactory.initElements(((Browser)browser).driver, this);
    }

    //////////////////////////////////////////////////////////////////////// PAGE OBJECTS
    @FindBy(xpath = "//ul[contains(@class,'navbar-left')]")
    public WebElement topNav;

    @FindBy(xpath = "//ul[@id='userMenuList']")
    public WebElement topUserList;

    @FindBy(xpath = "//a[@id='userMenu']")
    public WebElement topUsername;

    //////////////////////////////////////////////////////////////////////// HELPER METHODS

    public void selectUserDropDownItem(String menuItem) {

        String itemXPath = "//li/a[text()='" + menuItem + "']";
        WebElement item = topUserList.findElement(By.xpath(itemXPath));

        // click on the user menu item
        browser.clickWhenAvailable(topUsername);  // click on user's name to drop list
        browser.clickWhenAvailable(item);
    }

    // selects list item by buyer company name (ex. "Perfect City", "Solicitations", "Current Solicitations")
    public void selectNavDropByBuyer(String buyername, String item, String subitem) {

        WebElement header = topNav.findElement(By.xpath("//a[@title='"+ item +"']"));
        browser.clickWhenAvailable(header);

        WebElement buyerheader = topNav.findElement(By.xpath("//li[@class='dropdown open']//li[contains(text(),'"+ buyername +"')]"));
        String actiontoclick = "./following-sibling::*/a[contains(text(),'"+ subitem +"')]";

        browser.clickSubElement(buyerheader, actiontoclick);

        // Wait for the page to load before leaving
        browser.waitForPageLoad();
    }

    public void vendorLogout() {

        browser.waitForPageLoad();

        // change alert so that we confirm logout
        ((JavascriptExecutor)browser.getDriver()).executeScript("window.confirm = function(msg){return true;}");
        selectUserDropDownItem("Logout");
    }
}
