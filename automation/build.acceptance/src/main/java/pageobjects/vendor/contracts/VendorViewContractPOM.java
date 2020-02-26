package pageobjects.vendor.contracts;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageobjects.buyer.contract.ViewContractPOM;
import utilities.common.Browser;

import java.util.Map;

public class VendorViewContractPOM {

    private final Browser browser;

    public VendorViewContractPOM(WebDriver browser) {
        this.browser = (Browser) browser;
        PageFactory.initElements(((Browser) browser).driver, this);
    }

    //////////////////////////////////////////////////////////////////////// CONTRACT SEARCH PAGE

    @FindBy(xpath="//input[@id='contracttitlefilter']")
    public WebElement contractNameEdit;

    @FindBy(xpath="//input[@id='contractnumfilter']")
    public WebElement contractNumberEdit;

    @FindBy(xpath="//button[@id='find']")
    public WebElement submitButton;

    @FindBy(xpath="//table[@id='contractTable']")
    public WebElement contractListTable;

    //////////////////////////////////////////////////////////////////////// CONTRACT SUMMARY PAGE

    @FindBy(xpath="//section[@id='cont-requirement-content']")
    public WebElement summaryContentSection;

    public String GetGeneralInfoElement(String columnname) {

        String xpath = "//td[contains(.,'" + columnname + "')]/following-sibling::*";

        return browser.getSubElement(summaryContentSection, xpath).getText();
    }


    public enum ListColumn implements Browser.HTMLTableColumn
    { BOGUS, CONTRACTNUM, TITLE, ORG, EFFECTIVEDATE, EXPIRATIONDATE, STATUS }

    public Map<Browser.HTMLTableColumn, WebElement> getElementsForContractLine(String contractnum) {

        String rowXPath = "//a[contains(text(),'" + contractnum + "')]/parent::*/parent::*";
        return browser.buildTableMap(contractListTable, rowXPath, VendorViewContractPOM.ListColumn.values());
    }

}
