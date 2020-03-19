package pageobjects.buyer.admin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.common.Browser;

public class InternalAdminPOM {

    private final Browser browser;

    public InternalAdminPOM(WebDriver browser) {
        this.browser = (Browser) browser;
        PageFactory.initElements(((Browser) browser).driver, this);
    }

    //////////////////////////////////////////////////////////////////////// HOME PAGE


    @FindBy(xpath="//a[contains(.,'Org Search Popup')]")
    public WebElement orgSearchLink;

    @FindBy(xpath="//a[contains(text(),'User Search Popup')]")
    public WebElement userSearchLink;

    @FindBy(xpath="//a[@href='/wp-form-builder']")
    public WebElement formBuilderLink;

    @FindBy(xpath="//b[contains(text(),'Log Out')]")
    public WebElement logoutLink;

    @FindBy(xpath="//button[@routerlink='/home']")
    public WebElement welcomeFBButton;



}
