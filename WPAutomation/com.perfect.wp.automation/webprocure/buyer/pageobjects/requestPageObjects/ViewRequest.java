package buyer.pageobjects.requestPageObjects;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commonutils.pageobjects.utils.PCDriver;
import commonutils.pageobjects.utils.ReadExcelData;

public class ViewRequest {

	@FindBy(xpath = "//iframe[@name='C1ReqMain']")
	public List<WebElement> reqframe;

	@FindBy(xpath = "//a[@id='idView Request']")
	public WebElement viewreqtab;

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

	@FindBy(xpath = "//button[text()='Submit Request']")
	public WebElement submitbutton;

	@FindBy(xpath = "//button[text()='Submit']")
	public WebElement submissionconfirmationbtn;

	@FindBy(xpath = "//div[contains(@class,'ui-pnotify')]")
	public WebElement reqsuccessMessage;

	public ViewRequest() {

		PageFactory.initElements(PCDriver.getDriver(), this);
	}

	public void requestsubmission() throws Exception {
		movetoviewreq();
		assignacctcode();
		submitrequest();
		confirmationpage();

	}

	public void movetoviewreq() throws Exception {
		PCDriver.getDriver().switchTo().defaultContent();
		Thread.sleep(5000);
		PCDriver.switchToFrameBasedOnFrameName("C1ReqMain");
		// PCDriver.getDriver().switchTo().frame(reqframe);
		viewreqtab.click();
	}

	public void submitrequest() throws Exception {

		// PCDriver.waitForElementToDisappear(By.id("loadingDiv"));
		PCDriver.waitForPageLoad();
		/*
		 * //System.out.println(reqframe.size()); try { Thread.sleep(10000); } catch
		 * (InterruptedException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 * 
		 * PCDriver.getDriver().switchTo().defaultContent();
		 * PCDriver.switchToFrameBasedOnFrameName("C1ReqMain");
		 * PCDriver.waitForElementToDisappear(By.id("loadingDiv"));
		 */
		PCDriver.waitForElementToBeClickable(submitbutton);
		submitbutton.click();
	}

	public void assignacctcode() throws IOException, Exception {
		PCDriver.waitForElementToDisappear(By.id("loadingDiv"));
		PCDriver.waitForPageLoad();
		// System.out.println(reqframe.size());
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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
		acctcodesavebtn.click();

		PCDriver.waitForPageLoad();
		// PCDriver.getDriver().switchTo().alert();
		Thread.sleep(5000);
		confirmokbtn.click();
		PCDriver.waitForPageLoad();
		distributeevenlybtn.click();
		PCDriver.waitForPageLoad();
		itemallocationsavebtn.click();
	}

	public void setagencycode(String agency) {
		PCDriver.waitForElementToBeClickable(agencycode);
		agencycode.sendKeys(agency);
	}

	public void setorgcode(String org) {
		try {
			Thread.sleep(2000);
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
			Thread.sleep(2000);
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

	public void lineitemdetailsicon() {

		PCDriver.visibilityOfListLocated(lineitemdetailsicon);

	}

	public void successfullsubmissionmsg() {

	}
}
