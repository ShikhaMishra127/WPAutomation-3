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
        topUsername.click();  // click on user's name to drop list
        item.click();
    }

    public void selectNavDropDownItem(String headerItem, String subItem) {

        String headerXPath = "//ul[contains(@class,'navbar-left')]//*[@title='" + headerItem + "']";
        String subItemXPath = headerXPath + "/following-sibling::*//*[@title='" + subItem + "']";

        // click on the nav bar header item
        WebElement header = topNav.findElement(By.xpath(headerXPath));
        header.click();

        // click on the sub-item below the header item
        WebElement sub = header.findElement(By.xpath(subItemXPath));
        sub.click();

        // Wait for the page to load before leaving
        browser.waitForPageLoad();
    }

    // Specifically for Solicitation menu item - selects list item by buyer company name (ex. "Perfect City", "Current Solicitations")
    public void selectNavSolItemByBuyer(String buyername, String subitem) {

        WebElement header = topNav.findElement(By.xpath("//ul[contains(@class,'navbar-left')]//*[@title='Solicitations']"));
        browser.clickWhenAvailable(header);

        WebElement menulink = topNav.findElement(By.xpath("//li[@class='dropdown open']//li[contains(text(),'"+ buyername +"')]"));
        menulink.findElement(By.xpath("./following-sibling::*/a[contains(text(),'"+ subitem +"')]")).click();

    }

    public void vendorLogout() {

        browser.waitForPageLoad();

        // change alert so that we confirm logout
        ((JavascriptExecutor)browser.getDriver()).executeScript("window.confirm = function(msg){return true;}");
        selectUserDropDownItem("Logout");
    }
}
