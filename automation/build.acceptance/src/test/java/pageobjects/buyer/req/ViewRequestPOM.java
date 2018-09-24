package pageobjects.buyer.req;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.common.Browser;

public class ViewRequestPOM {


	@FindBy(xpath = "//iframe[@name='C1ReqMain']")
	public WebElement reqframe;

	@FindBy(xpath = "//a[@id='idView Request']")
	public WebElement viewreqtab;
	
	@FindBy(xpath="//div[@class='row alert alert-warning']")
	public WebElement blankreqcartmsg;

	@FindBy(xpath = "//div[@class='btn-group']")
	public WebElement tabgroups;

	@FindBy(xpath = "//button[text()='attachments']")
	public WebElement attachmenttab;

	@FindBy(xpath = "//h3[text()='Attachments']")
	public WebElement attachmenttabtitle;

	@FindBy(xpath = "//button[@name='btnNew']")
	public WebElement newattachmentbtn;

	@FindBy(xpath = "//span[@class='input-group-btn']")
	public WebElement browsebtn;

	@FindBy(xpath = "//textarea[@id='Purpose']")
	public WebElement attachmentpurpose;

	@FindBy(xpath = "//button[@name='btnSave']")
	public WebElement savebtn;

	@FindBy(xpath = "//button[@name='btnCancel']")
	public WebElement cancelbtn;

	@FindBy(xpath = "//button[@name='btnClose']")
	public WebElement attachmentclosebtn;

	@FindBy(xpath = "//button[@name='btnDeleteAll']")
	public WebElement deleteallbtn;

	@FindBy(xpath = "//button[text()='justification']")
	public WebElement justificationtab;

	@FindBy(xpath = "//h3[text()='Justification']")
	public WebElement justificationtitle;

	@FindBy(xpath = "//textarea[@name='Justification']")
	public WebElement justificationtextarea;

	@FindBy(xpath = "//button[text()='approval preview']")
	public WebElement approvalpreviewtab;

	@FindBy(xpath = "//button[text()='buyer contact']")
	public WebElement buyercontacttab;

	@FindBy(xpath = "//h3[text()='Buyer Contact']")
	public WebElement buyercontacttitle;

	@FindBy(xpath = "//div[contains(@class,'selectize-input')]")
	public WebElement buyercontactdropdown;
	// (//div[contains(@class,'selectize-input') and
	// contains(@class,'has_items') and contains(@class,'input-active')])

	@FindBy(xpath = "//div[@class='selectize-input items full has-options has-items focus dropdown-active input-active']/input")
	public WebElement defaultbuyercontact;

	@FindBy(xpath = "//div[contains(@class,'option')]")
	public List<WebElement> buyercontactlist;
	
	@FindBy(xpath = "//table[@class='table table-bordered table-striped dataTable no-footer']")
	public List<WebElement> reqcheckoutpagetables;
	
	@FindBy(xpath = "//img[@title='Line Item Details']")
	public List<WebElement> lineitemdetailsicon;

	@FindBy(xpath = "//img[@title='Assign Account Distribution']")
	public List<WebElement> acctcodeicon;

	@FindBy(xpath = "//button[@name='AccountAssignment']")
	public WebElement addacctcodebtn;

	@FindBy(xpath = "//input[@id='input_1']")
	public WebElement agencycode;

	@FindBy(xpath = "//input[@id='input_2']")
	public WebElement orgcode;

	@FindBy(xpath = "//input[@id='input_4']")
	public WebElement suborgcode;

	@FindBy(xpath = "//input[@id='input_5']")
	public WebElement fundcode;

	@FindBy(xpath = "//input[@id='input_6']")
	public WebElement appropcode;

	@FindBy(xpath = "//input[@id='input_7']")
	public WebElement objectcode;

	@FindBy(xpath = "//button[@name='btnSave']")
	public WebElement acctcodesavebtn;

	@FindBy(xpath = "//button[text()='OK']")
	public WebElement confirmokbtn;

	@FindBy(xpath = "//button[@name='DistributeEvenly']")
	public WebElement distributeevenlybtn;

	@FindBy(xpath = "//button[text()='Save']")
	public WebElement itemallocationsavebtn;

	@FindBy(xpath = "//img[@title='Line Item Attachment']")
	public WebElement lineitemattachmenticon;

	@FindBy(xpath = "//button[text()='Submit Request']")
	public WebElement submitbutton;
	
	@FindBy(xpath = "//div[@class='bootbox modal fade bootbox-confirm in']")
	public WebElement itemtypemsgalert;

	@FindBy(xpath = "//button[text()='Submit']")
	public WebElement submissionconfirmationbtn;

	@FindBy(xpath = "//div[contains(@class,'ui-pnotify')]")
	public WebElement reqsuccessAlert;

	@FindBy(xpath = "//div[contains(@class,'ui-pnotify-text')]")
	public WebElement reqsuccessMessage;
	
	@FindBy(xpath="//tbody")
	public WebElement actionItem;

	public void ViewRequest() {
		PageFactory.initElements(Browser.getDriver(), this);
	}

	public void requestsubmission() throws Exception {
		movetoviewreq();
		submitrequest();
		confirmationpage();
	}

	public void movetoviewreq() throws Exception {
		Thread.sleep(25000);
		Browser.getDriver().switchTo().defaultContent();
		Thread.sleep(5000);
		Browser.switchToFrameBasedOnFrameName("C1ReqMain");
		// Browser.getDriver().switchTo().frame(reqframe);
		viewreqtab.click();
	}
	
	public String blankreqcart() throws Exception{
		Browser.waitForElementToDisappear(By.id("loadingDiv"));
		Browser.switchToDefaultContent();
		Thread.sleep(5000);
		Browser.switchToFrameBasedOnFrameName("C1ReqMain");
		String blankcartmsg = blankreqcartmsg.getText();
		System.out.println(blankcartmsg);
		return blankcartmsg;
	}

	public void submitrequest() throws Exception {
		System.out.println("Entered Submit Request");
		// Browser.waitForElementToDisappear(By.id("loadingDiv"));
		Browser.waitForPageLoad();
		Browser.waitForElementToBeClickable(submitbutton);
		submitbutton.click();
		Thread.sleep(10000);
		//Browser.getDriver().switchTo().frame("C1ReqMain");
		//if(itemtypemsgalert.isDisplayed()){
		Browser.WaitTillElementIsPresent(itemtypemsgalert);
		//if(itemtypemsgalert.isEnabled()){
			confirmokbtn.click();
		//}
	}

	public void confirmationpage() {
		Browser.getDriver().switchTo().defaultContent();
		try {
			Thread.sleep(5000);
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	
		Browser.waitForElementToBeClickable(submissionconfirmationbtn);
		submissionconfirmationbtn.click();
	}

	public String successfullsubmissionmsg() {
		Browser.waitForElementToDisappear(By.id("loadingDiv"));
		Browser.WaitTillElementIsPresent(reqsuccessAlert);
		Browser.waitForElementToBeClickable(reqsuccessAlert);
		String reqsubmsg = reqsuccessMessage.getText();
		System.out.println(reqsubmsg);
		return reqsubmsg;
	}

	public void clickclosebtn() {
		// TODO Auto-generated method stub
	}
}
