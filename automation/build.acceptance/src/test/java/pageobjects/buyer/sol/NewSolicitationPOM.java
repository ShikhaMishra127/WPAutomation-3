package pageobjects.buyer.sol;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.common.Browser;

import java.io.IOException;

public class NewSolicitationPOM {

    private final Browser browser;

    public NewSolicitationPOM(WebDriver browser) throws IOException {
        this.browser = (Browser)browser;
        PageFactory.initElements(((Browser) browser).driver, this);
    }

    //////////////////////////////////////////////////////////////////////// HEADER PAGE

    @FindBy(xpath = "//li[@id='solc_Header' and @class='active']")
    public WebElement headTitle;

    @FindBy(xpath = "//input[@id='bidNumber']")
    public WebElement headBidNumberEdit;

    @FindBy(xpath = "//input[@id='bidTitle']")
    public WebElement headBidTitleEdit;

    @FindBy(xpath = "//select[@id='bidInvitationType']")
    public WebElement headSolPublicTypeDrop;

    @FindBy(xpath = "//select[@id='solType']")
    public WebElement headInvitationTypeDrop;

    @FindBy(xpath = "//input[@id='bidestimatedtotal']")
    public WebElement headEstTotalEdit;

    @FindBy(xpath = "//textarea[@id='bidDesc']")
    public WebElement headDescriptionEdit;

    @FindBy(xpath = "//input[@id='noLineItem']")
    public WebElement headNoLineItemCheckbox;

    @FindBy(xpath = "//button[@id='selectCatButton']")
    public WebElement headSelectCatButton;}

    @FindBy(xpath = "//input[@id='solstartdatetime']")
    public WebElement headStartDate;

    @FindBy(xpath = "//input[@id='solenddatetime']")
    public WebElement headEndDate;


    /*

    @FindBy(xpath = "")
    public WebElement ;

    @FindBy(xpath = "")
    public WebElement ;

    @FindBy(xpath = "")
    public WebElement ;

    @FindBy(xpath = "")
    public WebElement ;

    @FindBy(xpath = "")
    public WebElement ;

    @FindBy(xpath = "")
    public WebElement ;

    @FindBy(xpath = "")
    public WebElement ;

    @FindBy(xpath = "")
    public WebElement ;




 */