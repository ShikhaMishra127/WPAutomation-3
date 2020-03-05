package pageobjects.buyer.sol;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.common.Browser;

public class BuyerAwardSolicitationPOM {

    private final Browser browser;

    public BuyerAwardSolicitationPOM(WebDriver browser) {
        this.browser = (Browser) browser;
        PageFactory.initElements(((Browser) browser).driver, this);
    }

    //////////////////////////////////////////////////////////////////////// COMMON CONTROLS


    //////////////////////////////////////////////////////////////////////// SOL EVALUATE RESPONSES MAIN PAGE

    @FindBy(xpath="//input[@id='paper']")
    public WebElement mainPaperResponseRadio;

    @FindBy(xpath="//input[@id='item']")
    public WebElement mainAwardByItemRadio;

    @FindBy(xpath="//input[@id='group']")
    public WebElement mainAwardByGroupRadio;

    @FindBy(xpath="//input[@id='vendor']")
    public WebElement mainAwardAllToOneRadio;

    @FindBy(xpath="//button[contains(@onclick,'javascript:JumpPage')]")
    public WebElement mainContinueButton;

    @FindBy(xpath="//button[contains(@onlclick,'BidTabReport')]")
    public WebElement mainExportBidTabButton;

    @FindBy(xpath="//div[@id='showhide']")
    public WebElement mainFooterArea;

    @FindBy(xpath="//img[contains(@src,'collapse')]")
    public WebElement mainFooterCollapseButton;


    //////////////////////////////////////////////////////////////////////// SOL AWARD ALL FOR ONE PAGE

    @FindBy(xpath="//button[@data-bb-handler='confirm']")
    public WebElement allPopupContinueButton;

    @FindBy(xpath="//button[contains(@onclick,'javascript:Award')]")
    public WebElement allDoneButton;

    @FindBy(xpath="//button[contains(@onclick,'javascript:Cancel')]")
    public WebElement allCloseButton;

    //////////////////////////////////////////////////////////////////////// POST AWARD SUMMARY PAGE

    @FindBy(xpath="//button[contains(@onclick,'AwardBid')]")
    public WebElement summaryDoneButton;

    @FindBy(xpath="//button[contains(@onclick,'Download')]")
    public WebElement summaryDownloadButton;

    //////////////////////////////////////////////////////////////////////// FINALIZE PAGE

    @FindBy(xpath="//input[@name='p']")
    public WebElement finalizeConfirmEdit;

    @FindBy(xpath="//button[contains(@onclick,'javascript:checkAnswer')]")
    public WebElement finalizeSubmitButton;

    @FindBy(xpath="//button[contains(@onclick,'CreateReq')]")
    public WebElement finalizeCreateReqButton;

    @FindBy(xpath="//button[contains(@onclick,'AwardBid')]")
    public WebElement finalizeCloseButton;


    //////////////////////////////////////////////////////////////////////// HELPER METHODS

    public void ClickAwardSupplierCheckbox(String supplier) {

        String xpath = "//table/tbody/tr/td/a[contains(text(),'" + supplier + "')]/parent::*/parent::*/td/input";

        browser.waitForElementToAppear(By.xpath(xpath));
        WebElement element = browser.driver.findElement(By.xpath(xpath));

        // if the checkbox for our supplier is unchecked, check it
            if (!element.isSelected()) {
            element.click();
        }
    }

}
