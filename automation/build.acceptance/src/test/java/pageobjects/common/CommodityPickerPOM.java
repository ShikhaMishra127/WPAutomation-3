package pageobjects.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.common.Browser;

import java.io.IOException;

public class CommodityPickerPOM {

    private final Browser browser;

    public CommodityPickerPOM(WebDriver browser) throws IOException {
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

    /////

    public void selectCommodityByCode(String code) {

        String checkboxpath = "//span[contains(@class, 'fancytree-title') and contains(text(), '" + code + "')]/preceding-sibling::*[1]";

        // search by code
        commoditySearchClearButton.click();
        commoditySearchEdit.sendKeys(code);
        commoditySearchButton.click();

        // wait for tree to be updated with results
        browser.waitForElementToAppear(By.xpath(checkboxpath));

        // find the checkbox of the found code
        WebElement checkbox = commoditySearchResults.findElement(By.xpath(checkboxpath));

        // click the correct code
        browser.waitForElementToBeClickable(checkbox);
        checkbox.click();
    }

}

