package pageobjects.bidboard;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.common.Browser;

public class SolicitationBidboardPOM {

    private final Browser browser;

    public SolicitationBidboardPOM(WebDriver browser) {
        this.browser = (Browser)browser;
        PageFactory.initElements(((Browser) browser).driver, this);
    }

    //////////////////////////////////////////////////////////////////////// SOL BIDBOARD LIST PAGE

    @FindBy(xpath="//div[@id='bid-facets']")
    public WebElement listFacets;

    @FindBy(xpath="//div[@id='bid-facets']/app-bid-board-facets//div//span[@class='text-small blue-text']")
    public WebElement listClearFilterLink;

    @FindBy(xpath="//input[@id='search']")
    public WebElement listSearchEdit;

    @FindBy(xpath="//ul[@class='collection']")
    public WebElement listSolResults;

    ////////// Solicitation line item sub-elements
    public String itemDescription ="//span[@class='contract-desc']";
    public String itemOrganization =  "//span[@class='text-small grey-text']";
    public String itemStartEndDate = "//span[contains(@class,'text-small teal-text')]";
    public String itemTitle = "//span[@class='title']";

    //////////////////////////////////////////////////////////////////////// SOL BIDBOARD SUMMARY PAGE

    @FindBy(xpath="//div[@class='col m7']/h5/b")
    public WebElement summaryTitle;

    @FindBy(xpath="//div[@class='col m7']//p")
    public WebElement summaryLongDesc;

    // all important information in one block of text
    @FindBy(xpath="//div[@class='col m7']")
    public WebElement summaryInfo;

    @FindBy(xpath="//div[@class='row']//div//div[@class='col m12']")
    public WebElement summaryCategories;

    @FindBy(xpath="//div[@class='col m12']//app-bid-items")
    public WebElement summaryBidItems;

    @FindBy(xpath="//a[@class='pointer']")
    public WebElement summaryAttachments;


    //////////////////////////////////////////////////////////////////////// HELPFUIL METHODS


    public void waitForSol(String solnum) {

        browser.waitForElementToAppear(listSearchEdit);
        listSearchEdit.sendKeys(solnum);

        String xpath = "//span[@class='title teal-text'][contains(text(),'"+ solnum +"')]";

        browser.waitForElementWithRefresh(xpath, 15, 300);

    }

    // function that takes solnumber and returns webelement for line-item in sol bidboard (all elements)
    public WebElement getSolLineItem(String solnum) {

        String xpath = "//span[@class='title teal-text'][contains(text(),'"+ solnum +"')]/parent::*/parent::*/parent::*";

        browser.waitForElementToAppear(By.xpath(xpath));

        return listSolResults.findElement(By.xpath(xpath));
    }

}

