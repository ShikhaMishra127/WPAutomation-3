package pageobjects.buyer.admin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.common.Browser;

public class EnterpriseAdminPOM {

    private final Browser browser;

    public EnterpriseAdminPOM(WebDriver browser) {
        this.browser = (Browser) browser;
        PageFactory.initElements(((Browser) browser).driver, this);
    }

    ////////////////////////////////////////////////////////////////////////

    @FindBy(xpath="//iframe[@id='orgframe']")
    public WebElement menuOrgFrame;

    @FindBy(xpath="//a[contains(@href,'listvieworgs')]")
    public WebElement menuListViewLink;



    @FindBy(xpath="//div[@id='navigation']")
    public WebElement menuAdminDiv;

    public void SelectFromMenu(String header, String subitem) {

        // make sure the proper org is selected by switching to list-view to find suborg, then clicking on it
        browser.waitForPageLoad();
        browser.switchToFrame(menuOrgFrame);

        if (browser.elementExists(menuListViewLink)) {
            browser.clickWhenAvailable(menuListViewLink);
        }

        By borgxpath = By.xpath("//a[contains(@href,'setOrg')][contains(text(),'Automation')]");

        browser.clickWhenAvailable(borgxpath);

        // swtich back to top frame
        browser.driver.switchTo().defaultContent();

        By headerxpath = By.xpath("//div[@id='navigation']/ul/li/a[contains(@href,'setAction')][contains(text(),'" + header + "')]");
        By subitemxpath = By.xpath("//div[@id='navigation']/ul/li/ul/li/a[contains(@href,'setAction')][contains(text(),'" + subitem + "')]");

        // click the header menu to bring up sub-items, then click sub-item and wait for page to load
        browser.clickWhenAvailable(headerxpath);
        browser.clickWhenAvailable(subitemxpath);
        browser.waitForPageLoad();

    }


}
