package pageobjects.buyer.contract;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.common.Browser;

import java.util.Map;

public class ViewContractPOM {

    private final Browser browser;

    public ViewContractPOM(WebDriver browser) {
        this.browser = (Browser) browser;
        PageFactory.initElements(((Browser) browser).driver, this);
    }

    //////////////////////////////////////////////////////////////////////// SEARCH PAGE

    @FindBy(xpath="//input[@id='contractNumber']")
    public WebElement contractNumberEdit;

    @FindBy(xpath="//button[contains(@onclick,'searchPage')]")
    public WebElement submitButton;

    @FindBy(xpath="//table[@id='contTable']")
    public WebElement contractListTable;

    //////////////////////////////////////////////////////////////////////// SUMMARY PAGE

    @FindBy(xpath="(//table[@class='overViewTable'])[1]//td[contains(.,'Access')]/following-sibling::*")
    public WebElement summaryPrivacyLabel;

    @FindBy(xpath="(//table[@class='overViewTable'])[1]")
    public WebElement summaryGeneralInfoSection;


    public WebElement GetGeneralInfoElement(String columnname) {

        String xpath = "//td[contains(.,'" + columnname + "')]/following-sibling::*";

        return browser.getSubElement(summaryGeneralInfoSection, xpath);
    }

    public enum ListColumn implements Browser.HTMLTableColumn
    { BOGUS, CONTRACTNUM, TITLE, CONTRACTOR, ORG, BUYER, EFFECTIVEDATE, EXPIRATIONDATE, STATUS, ACTION }

    public Map<Browser.HTMLTableColumn, WebElement> getElementsForContractLine(String contractnum) {

        String rowXPath = "//a[contains(text(),'" + contractnum + "')]/parent::*/parent::*";
        return browser.buildTableMap(contractListTable, rowXPath, ListColumn.values());
    }

}
