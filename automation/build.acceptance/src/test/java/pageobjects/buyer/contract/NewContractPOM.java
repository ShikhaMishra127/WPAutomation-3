package pageobjects.buyer.contract;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.common.Browser;

public class NewContractPOM {

    private final Browser browser;

    public NewContractPOM(WebDriver browser) {
        this.browser = (Browser) browser;
        PageFactory.initElements(((Browser) browser).driver, this);
    }

    //////////////////////////////////////////////////////////////////////// COMMON CONTROLS

    @FindBy(xpath="//button[contains(@onclick,'javascript:nextStep()')]")
    public WebElement nextStepButton;

    @FindBy(xpath="//button[contains(@onclick,'javascript:MoveStep(false)')]")
    public WebElement previousStepButton;


    //////////////////////////////////////////////////////////////////////// HEADER PAGE

    @FindBy(xpath="//select[@id='contractType']")
    public WebElement headContractTypeDrop;

    @FindBy(xpath="//select[@id='contractAccessType']")
    public WebElement headContractVisibilityDrop;

    @FindBy(xpath="//input[@name='title']")
    public WebElement headContractTitleEdit;

    @FindBy(xpath="//textarea[@id='description']")
    public WebElement headLongDescEdit;

    @FindBy(xpath="//input[@name='contractNumber']")
    public WebElement headerContractNumber;

    @FindBy(xpath="//input[@id='multicheck']")
    public WebElement headerMasterCheck;

    @FindBy(xpath="//input[@id='dontdispatch']")
    public WebElement headerDoNotDispatchCheck;

    @FindBy(xpath="//input[@name='roundtrip']")
    public WebElement headerRoundtripCheck;

    @FindBy(xpath="//input[@id='pcard']")
    public WebElement headerPCardCheck;

    @FindBy(xpath="//button[@id='selectCatButton']")
    public WebElement headerCommoditiesButton;

    @FindBy(xpath="//button[@id='contractbtnslt']")
    public WebElement headerContractorSearchButton;

    @FindBy(xpath="//input[@id='sname_action']")
    public WebElement headerSupplierSearchEdit;

    @FindBy(xpath="//button[contains(@onclick,'javascript:submitSearch()')]")
    public WebElement headerSupplierSearchButton;

    @FindBy(xpath="//img[contains(@src,'selectcheckmark.gif')]")
    public WebElement headerSupplierSearchCheck;

    @FindBy(xpath="//input[@id='vendorName']")
    public WebElement headerContractorNameEdit;

    @FindBy(xpath="//select[@id='contractPricingType']")
    public WebElement headerPricingTypeDrop;

    @FindBy(xpath="//select[@id='tempcontractTotalValueCondition']")
    public WebElement headerPricingConditionDrop;

    @FindBy(xpath="//input[@id='maxValue']")
    public WebElement headerPricingTotalValueEdit;

    @FindBy(xpath="//input[@id='issue_date']")
    public WebElement headerIssueDateEdit;

    @FindBy(xpath="//input[@id='award_date']")
    public WebElement headerAwardDateEdit;

    @FindBy(xpath="//input[@id='effective_date']")
    public WebElement headerEffectiveDateEdit;

    @FindBy(xpath="//input[@id='expiry_date']")
    public WebElement headerExpirationDateEdit;

    @FindBy(xpath="//input[@id='projectedFinalExpDate']")
    public WebElement headerProjectedDateEdit;

    @FindBy(xpath="//div[@id='distribpanel']/div/button")
    public WebElement headDistributorButton;

    @FindBy(xpath="//div[@id='removeButton']")
    public WebElement headPopupCloseButton;




    //////////////////////////////////////////////////////////////////////// NOTIFICATIONS PAGE



    //////////////////////////////////////////////////////////////////////// CLAUSES PAGE

    @FindBy(xpath="//button[contains(@class,'addNewSection')]")
    public WebElement clausesAddSectionButton;

    @FindBy(xpath="//button[@onclick='javascript:MoveStep(true)']")
    public WebElement clausesNextButton;

    //////////////////////////////////////////////////////////////////////// CATALOG ITEMS PAGE

    //////////////////////////////////////////////////////////////////////// ATTACHMENTS PAGE

    ////////// add files from library dialog
    @FindBy(xpath="//form[@name='theform']")
    public WebElement attachLibResult;

    @FindBy(xpath="//button[contains(@onclick,'submit')]")
    public WebElement attachLibSaveButton;

    ////////// contract documents page
    @FindBy(xpath="//tbody")
    public WebElement attachContainer;


    @FindBy(xpath="//button[@type='button'][contains(@onclick,'docfromlib')]")
    public WebElement attachDocFromLibButton;

    ////////// contract document sub-elements
    String attachLineVisibleToContractorCheckbox = "./td/span/label/input[@type='checkbox']";
    String attachLinePrivateToggle="./td/div/div[@class='bootstrap-switch-container']";

    ////////// helper methods

    public void addFilesFromLibrary(String contract_attachments) {

        String[] files = contract_attachments.split(",");

        for (String file: files) {

            String filename = file.split("\\|")[0];

            String xpath = "//form[@name='theform']/table/tbody/tr/td/a[contains(text(),'" + filename + "')]/parent::*/parent::*/td/input[@type='checkbox']";

            // if we find the file in the list of uploaded files, click on its checkbox. Otherwise fail.
            if (browser.elementExists(By.xpath(xpath))) {
                attachLibResult.findElement(By.xpath(xpath)).click();
            } else {
                System.out.printf("Cannot located file '%s' in contract file library.%n", filename);
            }
        }
    }

    public void setFileVisibility(String contract_attachments) {

        String[] files = contract_attachments.split(",");

        for (String file : files) {

            String[] attribute = file.split("\\|");

            String filename = attribute[0];
            boolean markPrivate = Boolean.valueOf(attribute[1]);
            boolean markVisible = Boolean.valueOf(attribute[2]);

            String xpath = "//a[contains(.,'" + filename + "')]/parent::*/parent::*";

            // search for line containing document
            browser.waitForElementToAppear(By.xpath(xpath));
            WebElement ourline = attachContainer.findElement(By.xpath(xpath));

            if (!markPrivate) {
                // click toggle switch
                browser.clickSubElement(ourline, attachLinePrivateToggle);

            } else if (markVisible) {
                // click visible to contractor checkbox
                browser.clickSubElement(ourline, attachLineVisibleToContractorCheckbox);
            }

        }
    }

    //////////////////////////////////////////////////////////////////////// AUTHORIZATION PAGE

    @FindBy(xpath="//table[@id='orgtable']")
    public WebElement authOrgTable;

    @FindBy(xpath="//table[@id='orgtable']/tbody/tr/td/input[@type='checkbox'][1]")
    public WebElement authFirstOrgCheckbox;

    @FindBy(xpath="//button[contains(@onclick,'javascript:selectAll')]")
    public WebElement authSelectAllOrgsButton;

    @FindBy(xpath="//button[contains(@onclick,'javascript:nextStep')]")
    public WebElement authFinishedButton;


    //////////////////////////////////////////////////////////////////////// SUMMARY PAGE

    @FindBy(xpath="//button[contains(@onclick,'submitContract();')]")
    public WebElement summarySubmitButton;



}