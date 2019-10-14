package pageobjects.buyer.approval;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.common.Browser;

public class ApprovalInboxPOM {

    private final Browser browser;


    public ApprovalInboxPOM(WebDriver browser) {
        this.browser = (Browser) browser;
        PageFactory.initElements(((Browser) browser).driver, this);
    }

    ////////////////////////////////////////////////////////////////////////

    @FindBy(xpath="//select[@id='FDocType']")
    public WebElement documentTypeDrop;

    @FindBy(xpath="//input[@id='FDocumentNumber']")
    public WebElement documentNumberEdit;

    @FindBy(xpath="//button[@name='Submit']")
    public WebElement submitButton;

    @FindBy(xpath="//input[@id='select-all-docs']")
    public WebElement selectAllCheckbox;

    @FindBy(xpath="(//button[contains(@onclick,'approveAllSelectedDocs()')])[1]")
    public WebElement approveSelectedButton;

    @FindBy(xpath="//button[@name='Reset']")
    public WebElement resetButton;

    @FindBy(xpath="//table[@id='approvalTable']")
    public WebElement approvalTable;

    @FindBy(xpath="//textarea")
    public WebElement reqApprovalComment;

    @FindBy(xpath="//button[@name='Approve']")
    public WebElement approveButton;

}
