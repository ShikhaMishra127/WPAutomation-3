package pageobjects.buyer.admin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import utilities.common.Browser;

public class FormBuilderAdminPOM {

    private final Browser browser;

    public FormBuilderAdminPOM(WebDriver browser) {
        this.browser = (Browser) browser;
        PageFactory.initElements(((Browser) browser).driver, this);
    }

    //////////////////////////////////////////////////////////////////////// HOME PAGE

    @FindBy(xpath="(//a[@routerlink='/home'])[2]")
    public WebElement homeTab;

    @FindBy(xpath="//a[@routerlink='/form-builder']")
    public WebElement formBuilderTab;

    @FindBy(xpath="//a[@routerlink='/customfields']")
    public WebElement customFieldsTab;

    @FindBy(xpath="//a[@routerlink='/customvalidation']")
    public WebElement customValidationsTab;

    @FindBy(xpath="//button[@routerlink='/projects']")
    public WebElement startFBButton;

    @FindBy(xpath="//span[@class='fa fa-plus']")
    public WebElement createNewCategoryButton;

    @FindBy(xpath="//input[@id='new-cat-name']")
    public WebElement createProjectNameEdit;

    @FindBy(xpath="//button[@class='btn btn-info btn-block '][contains(.,'Add')]")
    public WebElement createCategoryAddButton;

    ////////// PROJECT LIST PAGE
    @FindBy(xpath = "//button[@class='btn btn-primary'][contains(.,'Create New Project')]")
    public WebElement createNewProjectButton;

    String openProjectLink = "./a[@class='btn btn-link btn-sm'][contains(.,'Open Project')]";

    ////////// CREATE NEW PROJECT PAGE
    @FindBy(xpath="//input[@placeholder='Enter form name']")
    public WebElement newProjectName;

    @FindBy(xpath="//select[@name='customerId']")
    public WebElement newProjectCustomerDropDown;

    @FindBy(xpath="//textarea[@name='description']")
    public WebElement newProjectDescEdit;

    @FindBy(xpath="//button[@type='submit']")
    public WebElement newProjectSubmitButton;

    public void CreateOrUseProject(String categoryname, String projectname) {

        String categoryxpath = "//div[@class='col-xs-3 pm0 h100']/div/div/ul/li/a[contains(text(),'" + categoryname + "')]";
        String projectxpath = "//label[@class='text-capitalize'][contains(.,'" + projectname + "')]/parent::*/parent::*";

        // if category (CATEGORYNAME) does not exist, create it
        if (!browser.elementExists(By.xpath(categoryxpath))) {
            browser.clickWhenAvailable(createNewCategoryButton);
            browser.sendKeysWhenAvailable(createProjectNameEdit, categoryname);
            browser.clickWhenAvailable(createCategoryAddButton);
        }

        // click on category for available projects
        WebElement categoryLink = browser.findElement(By.xpath(categoryxpath));
        browser.clickWhenAvailable(categoryLink);

        browser.waitForElementToAppear(By.xpath(projectxpath));

        // If the FormBuilder project (PROJECTNAME) inside category doesn't exist, create it
        if (!browser.elementExists(By.xpath(projectxpath))) {
            browser.clickWhenAvailable(createNewProjectButton);

            browser.sendKeysWhenAvailable(newProjectName, projectname);
            new Select(newProjectCustomerDropDown).selectByVisibleText(" " + browser.buyerName);
            browser.sendKeysWhenAvailable(newProjectDescEdit, "This is an automated FormBuilder Project. Be Amazed.");
            browser.clickWhenAvailable(newProjectSubmitButton);
            browser.confirmAlert();
        }

        // click "Open Project" for our current FormBuilder project
        WebElement projectLink = browser.findElement(By.xpath(projectxpath));
        browser.clickSubElement(projectLink, openProjectLink);
    }

    //////////////////////////////////////////////////////////////////////// EDITOR PAGE

    @FindBy(xpath="//button[contains(.,'Add New Page')]")
    public WebElement editAddNewPageButton;

    @FindBy(xpath="(//input[@name='name'])[2]")
    public WebElement editNewPageNameEdit;

    @FindBy(xpath="//button[contains(.,'Create')]")
    public WebElement editNewPageCreateButton;

    @FindBy(xpath="//table[@class='table table-bordered table-striped']/tbody/tr/td")
    public WebElement editPagesTable;

    @FindBy(xpath="//label[contains(.,'Show JSON')]/input")
    public WebElement editShowJSONCheckbox;


    public void RemoveExistingPages() {

        String xpathDelIcons = "(//a/i[@class='fa fa-trash'])";

        browser.HardWait(2);

        // go through all "Delete" page icons until there are no pages left
        int i = browser.findElements(By.xpath(xpathDelIcons)).size();
        while (i > 0) {
            browser.clickWhenAvailable(By.xpath(xpathDelIcons + "[" + i + "]"));
            browser.confirmAlert();
            i--;
        }
    }

    public void OpenFormBuilderEditor(String projectname) {

        String xpath = "//a[contains(.,'" + projectname + "')]/parent::*/following-sibling::*//i[contains(@class,'fa fa-wrench')]//parent::*";
        browser.clickWhenAvailable(By.xpath(xpath));
    }










}
