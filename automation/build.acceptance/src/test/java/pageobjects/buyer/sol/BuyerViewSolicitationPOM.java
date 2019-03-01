package pageobjects.buyer.sol;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.common.Browser;

import java.util.ArrayList;

public class BuyerViewSolicitationPOM {

    private final Browser browser;

    public enum SolListElement { SOLNUM, TITLE, BUYER, ENDDATE, STATUS, ACTION }

    public BuyerViewSolicitationPOM(WebDriver browser) {
        this.browser = (Browser) browser;
        PageFactory.initElements(((Browser) browser).driver, this);
    }

    //////////////////////////////////////////////////////////////////////// COMMON CONTROLS

    @FindBy(xpath="//select[@id='filter-organization']")
    public WebElement searchOrgDrop;

    @FindBy(xpath="//input[@id='filter_bidTitle']")
    public WebElement searchSolTitleEdit;

    @FindBy(xpath="//input[@id='filter_bidNumber']")
    public WebElement searchSolNumberEdit;

    @FindBy(xpath="//select[@id='filter-user']")
    public WebElement searchUserDrop;

    @FindBy(xpath="//input[@id='filter_from_endDate']")
    public WebElement searchEndDateFromEdit;

    @FindBy(xpath="//input[@id='filter_to_endDate']")
    public WebElement searchEndDateToEdit;

    @FindBy(xpath="//li[@id='userChartTab']")
    public WebElement searchEndedSolsTab;

    @FindBy(xpath="//li[@id='adminChartTab']")
    public WebElement searchActiveSolsTab;


    @FindBy(xpath="//button[contains(@onclick,'validateForm')]")
    public WebElement searchUpdateFilterButton ;

    @FindBy(xpath="//table[@id='solTable']")
    public WebElement searchSolResultsTable;



    //////////////////////////////////////////////////////////////////////// HELPFUL METHODS

    public ArrayList<WebElement> getElementsForSol(String solnum) {

        // Look for our solicitation row in the filtered list of solicitations
        String xpathrow = "//table[@id='solTable']/tbody/tr/td/a[contains(text(),'" + solnum + "')]/parent::*/parent::*";
        browser.waitForElementToAppear(By.xpath(xpathrow));

        ArrayList<WebElement> elements = new ArrayList<WebElement>();

        // build a list of WebElements that reference each column (name, number, status, etc)
        for (int i = 1; i < SolListElement.values().length; i++) {

            String columnxpath = xpathrow + "/td[" + String.valueOf(i) +  "]";

            browser.waitForElementToAppear(By.xpath(columnxpath));
            elements.add(searchSolResultsTable.findElement(By.xpath(columnxpath)));
        }

        return elements;
    }

}
