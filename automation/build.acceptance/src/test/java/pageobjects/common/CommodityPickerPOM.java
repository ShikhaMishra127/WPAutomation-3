package pageobjects.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.common.Browser;


public class CommodityPickerPOM {

    private final Browser browser;

    public CommodityPickerPOM(WebDriver browser) {
        this.browser = (Browser)browser;
        PageFactory.initElements(((Browser) browser).driver, this);
    }

    //////////////////////////////////////////////////////////////////////// COMMODITY CODES PAGE

    @FindBy(xpath = "//input[@id='searchTxt']")
    public WebElement commoditySearchEdit;

    @FindBy(xpath = "//button[@id='searchCats']")
    public WebElement commoditySearchButton;

    @FindBy(xpath = "//button[@id='clearsearch']")
    public WebElement commoditySearchClearButton;

    @FindBy(xpath = "//div[@id='catTreeDiv']")
    public WebElement commoditySearchResults;

    @FindBy(xpath = "//div[@id='select_categories']//div[@class='modal-footer']/button")
    public WebElement commodityCloseButton;

    ////////////////////////////////////////////////////////////////////////

    public void selectCommodityByCode(String code) {

        String checkboxpath = "//span[contains(@class, 'fancytree-title') and contains(text(), '" + code + "')]/preceding-sibling::*[1]";

        browser.waitForElementToAppear(commoditySearchEdit);

        // search by code
        commoditySearchClearButton.click();
        commoditySearchEdit.sendKeys(code);
        commoditySearchButton.click();

        // wait for tree to be updated with results
        browser.waitForElementToAppear(By.xpath(checkboxpath));

        // find the checkbox of the found code
        WebElement checkbox = commoditySearchResults.findElement(By.xpath(checkboxpath));

        // click the correct code (if overlay is there, wait for it to leave before clicking checkbox
        browser.waitForElementToDisappear(By.xpath("//div[contains(@class,'blockOverlay')]"));
        browser.waitForElementToBeClickable(checkbox);
        checkbox.click();
    }

}

