package pageobjects.buyer.sol;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.common.Browser;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class NewSolicitationPOM {

    private final Browser browser;

    public NewSolicitationPOM(WebDriver browser) throws IOException {
        this.browser = (Browser) browser;
        PageFactory.initElements(((Browser) browser).driver, this);
    }



    //////////////////////////////////////////////////////////////////////// COMMON

    @FindBy(xpath = "//ul[@class='nav nav-wizard']//li[@class='active']")
    public WebElement stepTitle;

    @FindBy(xpath = "//button[@onclick='javascript:NextStep();']")
    public WebElement nextButton;

    @FindBy(xpath = "//button[@onclick='javascript:MoveStep(true);']")
    public WebElement requireNextButtonAlt;

    //button[@onclick='javascript:Finished();']


    //////////////////////////////////////////////////////////////////////// HEADER PAGE

    @FindBy(xpath = "//input[@id='bidNumber']")
    public WebElement headBidNumberEdit;

    @FindBy(xpath = "//input[@id='bidTitle']")
    public WebElement headBidTitleEdit;

    @FindBy(xpath = "//select[@id='bidInvitationType']")
    public WebElement headSolPublicTypeDrop;

    @FindBy(xpath = "//select[@id='solType']")
    public WebElement headInvitationTypeDrop;

    @FindBy(xpath = "//input[@id='bidestimatedtotal']")
    public WebElement headEstTotalEdit;

    @FindBy(xpath = "//textarea[@id='bidDesc']")
    public WebElement headDescriptionEdit;

    @FindBy(xpath = "//input[@id='noLineItem']")
    public WebElement headNoLineItemCheckbox;

    @FindBy(xpath = "//button[@id='selectCatButton']")
    public WebElement headSelectCatButton;

    @FindBy(xpath = "//input[@id='solstartdatetime']")
    public WebElement headStartDateEdit;

    @FindBy(xpath = "//input[@id='solenddatetime']")
    public WebElement headEndDateEdit;

    @FindBy(xpath = "//input[@name='collaboration_enable']")
    public WebElement headCollaborationCheckbox;

    @FindBy(xpath = "//input[@id='collabstartdatetime']")
    public WebElement headCollaborationStartDateEdit;

    @FindBy(xpath = "//input[@id='collabenddatetime']")
    public WebElement headCollaborationEndDateEdit;

    //////////////////////////////////////////////////////////////////////// REQUIREMENTS PAGE

    @FindBy(xpath = "//button[@onclick='javascript:MoveStep(true);']")
    public WebElement requireNextButton;

    //////////////////////////////////////////////////////////////////////// QUESTIONNAIRE PAGE

    //////////////////////////////////////////////////////////////////////// ATTACHMENTS PAGE

    @FindBy(xpath = "//button[@onclick='javascript:UploadDocumentFromLibrary();']")
    public WebElement docsUploadFromLibButton;

    @FindBy(xpath = "(//input[@type='checkbox'])[1]")
    public WebElement docsUploadFirstFileCheckbox;

    @FindBy(xpath = "//button[@onclick='javascript:Save();']")
    public WebElement docsUploadSaveButton;

    @FindBy(xpath = "//table[@class='table table-bordered table-striped no-footer']//thead")
    public WebElement docsFileUploadHeader;


    ////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////////////////////////////////////////


    public String solDatePlusMin(int min) {
        return LocalDateTime.now().plusMinutes(min).format(DateTimeFormatter.ofPattern("MM/dd/yyyy, hh:mm a"));
    }

}
