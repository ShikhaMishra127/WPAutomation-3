package buyer.pageobjects.requestPageObjects;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import commonutils.pageobjects.utils.PCDriver;
import commonutils.pageobjects.utils.ReadExcelData;

public class ViewRequest {

	@FindBy(xpath = "//iframe[@name='C1ReqMain']")
	public List<WebElement> reqframe;

	@FindBy(xpath = "//a[@id='idView Request']")
	public WebElement viewreqtab;

	@FindBy(xpath = "//div[@class='btn-group']")
	public WebElement  tabgroups;
	
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
	//(//div[contains(@class,'selectize-input') and contains(@class,'has_items') and contains(@class,'input-active')])
	
	@FindBy(xpath = "//div[@class='selectize-input items full has-options has-items focus dropdown-active input-active']/input")
	public WebElement defaultbuyercontact;
	
	@FindBy(xpath = "//div[contains(@class,'option')]")
	public List<WebElement> buyercontactlist;

	@FindBy(xpath = "//img[@title='Line Item Details']")
	public List<WebElement> lineitemdetailsicon;

	@FindBy(xpath = "//img[@title='Assign Account Distribution']")
	public WebElement acctcodeicon;

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

	@FindBy(xpath = "//button[text()='Submit']")
	public WebElement submissionconfirmationbtn;

	@FindBy(xpath = "//div[contains(@class,'ui-pnotify')]")
	public WebElement reqsuccessAlert;

	@FindBy(xpath = "//div[contains(@class,'ui-pnotify-text')]")
	public WebElement reqsuccessMessage;

	public ViewRequest() {

		PageFactory.initElements(PCDriver.getDriver(), this);
	}

	public void requestsubmission() throws Exception {
		movetoviewreq();
		attachmenttab(ReadExcelData.getInstance("Attachments").getStringValue("filename"));
		assignacctcode();
		submitrequest();
		confirmationpage();

	}

	public void movetoviewreq() throws Exception {
		Thread.sleep(25000);
		PCDriver.getDriver().switchTo().defaultContent();
		Thread.sleep(5000);
		PCDriver.switchToFrameBasedOnFrameName("C1ReqMain");
		// PCDriver.getDriver().switchTo().frame(reqframe);
		viewreqtab.click();
	}



	public void attachmenttab(String filename) throws Exception {

		PCDriver.waitForElementToDisappear(By.id("loadingDiv"));
		PCDriver.switchToDefaultContent();
		Thread.sleep(5000);
		PCDriver.switchToFrameBasedOnFrameName("C1ReqMain");
		PCDriver.waitForElementToBeClickable(attachmenttab);
		attachmenttab.click();
		try {
			PCDriver.WaitTillElementIsPresent(attachmenttabtitle);
			PCDriver.waitForElementToBeClickable(newattachmentbtn);
			newattachmentbtn.click();
			//browsebtn.click();
			//PCDriver.uploadFile(filename);
			browsebtn.findElement(By.xpath("//input[@name='FileNamewithPath']")).sendKeys("/opt/Automation/VendorReport_Quote_RFQ18000151.txt");
			attachmentpurpose.sendKeys(ReadExcelData.getInstance("processreqtabs").getStringValue("attachment_purpose"));
			savebtn.click();
			PCDriver.switchToDefaultContent();
			Thread.sleep(6000);
			PCDriver.switchToFrameBasedOnFrameName("C1ReqMain");
			PCDriver.WaitTillElementIsPresent(attachmentclosebtn);
			attachmentclosebtn.click();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void justificationtab() throws Exception{
		PCDriver.waitForElementToDisappear(By.id("loadingDiv"));
		PCDriver.switchToDefaultContent();
		Thread.sleep(5000);
		PCDriver.switchToFrameBasedOnFrameName("C1ReqMain");
		PCDriver.waitForElementToBeClickable(justificationtab);
		justificationtab.click();
		try{
			PCDriver.WaitTillElementIsPresent(justificationtitle);
			justificationtextarea.sendKeys(ReadExcelData.getInstance("processreqtabs").getStringValue("justification"));
			savebtn.click();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void buyercontacttab() throws InterruptedException{
				System.out.println("Entered Buyer Contact");

		PCDriver.waitForElementToDisappear(By.id("loadingDiv"));
		PCDriver.switchToDefaultContent();
		Thread.sleep(5000);
		PCDriver.switchToFrameBasedOnFrameName("C1ReqMain");
		PCDriver.waitForElementToBeClickable(buyercontacttab);
		buyercontacttab.click();
		try{
			PCDriver.WaitTillElementIsPresent(buyercontacttitle);
			//PCDriver.waitForElementToBeClickable(buyercontactdropdown);
			Thread.sleep(2000);
			buyercontactdropdown.click();
			/*			Thread.sleep(2000);

			PCDriver.waitForElementToBeClickable(buyercontactdropdown.findElement(By.xpath("./input")));
						Thread.sleep(2000);

			buyercontactdropdown.sendKeys(Keys.BACK_SPACE);
						Thread.sleep(2000);

			buyercontactdropdown.findElement(By.xpath("./input")).click();
						Thread.sleep(2000);

			buyercontactdropdown.findElement(By.xpath("./input")).sendKeys("Manisha Insys");
			
			Thread.sleep(4000);
			buyercontactlist.size();
			System.out.println(buyercontactlist.size());
			if(buyercontactlist.size()>0){
				for(WebElement buyercontact: buyercontactlist){
					//System.out.println(buyercontact.getText());
					String selectedcontact = "Manisha Insys";
					if(buyercontact.getText().contains(selectedcontact)){
						System.out.println(buyercontact.getText());
						Assert.assertEquals(selectedcontact, buyercontact.getText());
						buyercontact.click();
					}
				}
			}
			*/
			savebtn.click();
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println("Exit Buyer Contact");

	}

	public void assignacctcode() throws IOException, Exception {
				System.out.println("Entered Assign Contact");

		PCDriver.waitForElementToDisappear(By.id("loadingDiv"));
		PCDriver.waitForPageLoad();
		// System.out.println(reqframe.size());
		PCDriver.getDriver().switchTo().defaultContent();
		PCDriver.switchToFrameBasedOnFrameName("C1ReqMain");
		PCDriver.waitForElementToDisappear(By.id("loadingDiv"));
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PCDriver.waitForElementToBeClickable(acctcodeicon);
		acctcodeicon.click();
		PCDriver.waitForPageLoad();
		PCDriver.waitForElementToBeClickable(addacctcodebtn);
		addacctcodebtn.click();
		PCDriver.waitForPageLoad();
		setagencycode(ReadExcelData.getInstance("AccountCode").getStringValue("Agency"));
		setorgcode(ReadExcelData.getInstance("AccountCode").getStringValue("Org"));
		setsuborgcode(ReadExcelData.getInstance("AccountCode").getStringValue("SubOrg"));
		setfundcode(ReadExcelData.getInstance("AccountCode").getStringValue("Fund"));
		setappropcode(ReadExcelData.getInstance("AccountCode").getStringValue("Approp"));
		setobjectcode(ReadExcelData.getInstance("AccountCode").getStringValue("Object"));
		PCDriver.waitForElementToBeClickable(acctcodesavebtn);
		acctcodesavebtn.click();
						System.out.println("Hey Submit Button Clicked");

		PCDriver.waitForPageLoad();
		// PCDriver.getDriver().switchTo().alert();
		Thread.sleep(5000);
		confirmokbtn.click();
		PCDriver.waitForPageLoad();
		distributeevenlybtn.click();
		PCDriver.waitForPageLoad();
		itemallocationsavebtn.click();
				System.out.println("Exit Assign Contact");

	}

	public void setagencycode(String agency) {
		PCDriver.waitForElementToBeClickable(agencycode);
		agencycode.sendKeys(agency);
	}

	public void setorgcode(String org) {
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PCDriver.waitForElementToBeClickable(orgcode);
		orgcode.sendKeys(org);
	}

	public void setsuborgcode(String suborg) {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PCDriver.waitForElementToBeClickable(suborgcode);
		suborgcode.sendKeys(suborg);
	}

	public void setfundcode(String fund) {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PCDriver.waitForElementToBeClickable(fundcode);
		fundcode.sendKeys(fund);
	}

	public void setappropcode(String approp) {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PCDriver.waitForElementToBeClickable(appropcode);
		appropcode.sendKeys(approp);
	}

	public void setobjectcode(String object) {
		 try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PCDriver.waitForElementToBeClickable(objectcode);
		objectcode.sendKeys(object);
	}
	
	public void lineitemattachment(String attachmentname){
				System.out.println("Entered Line Item Attachment");

		PCDriver.waitForElementToDisappear(By.id("loadingDiv"));
		PCDriver.waitForPageLoad();
		// System.out.println(reqframe.size());
		PCDriver.getDriver().switchTo().defaultContent();
		PCDriver.switchToFrameBasedOnFrameName("C1ReqMain");
		PCDriver.waitForElementToDisappear(By.id("loadingDiv"));
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PCDriver.waitForElementToBeClickable(lineitemattachmenticon);
		lineitemattachmenticon.click();
		PCDriver.waitForPageLoad();
		try {
			PCDriver.WaitTillElementIsPresent(attachmenttabtitle);
			PCDriver.waitForElementToBeClickable(newattachmentbtn);
			newattachmentbtn.click();
			//browsebtn.click();
			//PCDriver.uploadFile(attachmentname);
			browsebtn.findElement(By.xpath("//input[@name='FileNamewithPath']")).sendKeys("/opt/Automation/WeeklyAchievementReport_20170918.doc");
			attachmentpurpose.sendKeys(ReadExcelData.getInstance("LineItemAttachment").getStringValue("attachmentpurpose"));
			savebtn.click();
			PCDriver.switchToDefaultContent();
			Thread.sleep(5000);
			PCDriver.switchToFrameBasedOnFrameName("C1ReqMain");
			PCDriver.WaitTillElementIsPresent(attachmentclosebtn);
			attachmentclosebtn.click();

		} catch (Exception e) {
			e.printStackTrace();
		}
				System.out.println("Exit Line Item Attachment");

	}
	
	public void submitrequest() throws Exception {
		System.out.println("Entered Submit Request");

		// PCDriver.waitForElementToDisappear(By.id("loadingDiv"));
		PCDriver.waitForPageLoad();
		PCDriver.waitForElementToBeClickable(submitbutton);
		submitbutton.click();
				System.out.println("Exit Submit Request");

	}

	public void confirmationpage() {
		PCDriver.getDriver().switchTo().defaultContent();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// PCDriver.switchToFrameBasedOnFrameName("C1ReqMain");
		PCDriver.waitForElementToBeClickable(submissionconfirmationbtn);
		submissionconfirmationbtn.click();
	}


	public String successfullsubmissionmsg() {
		PCDriver.waitForElementToDisappear(By.id("loadingDiv"));
		PCDriver.WaitTillElementIsPresent(reqsuccessAlert);
		PCDriver.waitForElementToBeClickable(reqsuccessAlert);
		String reqsubmsg = reqsuccessMessage.getText();
		System.out.println(reqsubmsg);
		return reqsubmsg;
	}
}
