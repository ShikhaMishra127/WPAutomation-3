package pageobjects.vendor.orders;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.common.Browser;

public class VendorOrderViewPOM {

    private final Browser browser;

    public VendorOrderViewPOM(WebDriver browser) {
        this.browser = (Browser) browser;
        PageFactory.initElements(((Browser) browser).driver, this);
    }

    ////////////////////////////////////////////////////////////////////////

    @FindBy(xpath="//span[contains(@id,'statusfilter')]")
    public WebElement orderStatusFilterDrop;

    @FindBy(xpath="//button[@id='find']")
    public WebElement orderFilterListButton;

    ////////////////////////////////////////////////////////////////////////






}
