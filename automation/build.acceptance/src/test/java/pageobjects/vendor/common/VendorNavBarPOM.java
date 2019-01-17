package pageobjects.vendor.common;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.common.Browser;

import java.io.IOException;

public class VendorNavBarPOM {

    private final Browser browser;

    public VendorNavBarPOM(WebDriver browser) throws IOException {
        this.browser = (Browser)browser;
        PageFactory.initElements(((Browser)browser).driver, this);
    }

    ///// PAGE OBJECTS
    @FindBy(xpath = "//ul[contains(@class,'navbar-left')]")
    public WebElement topNav;

    @FindBy(xpath = "//ul[@id='userMenuList']")
    public WebElement topUserList;

    @FindBy(xpath = "//a[@id='userMenu']")
    public WebElement topUsername;


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

    public void vendorLogout() {

        browser.waitForPageLoad();

        // change alert so that we confirm logout
        ((JavascriptExecutor)browser.getDriver()).executeScript("window.confirm = function(msg){return true;}");
        selectUserDropDownItem("Logout");
    }
}
