package buyer.pageobjects.invoice;

import java.awt.AWTException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import commonutils.pageobjects.utils.PCDriver;
import commonutils.pageobjects.utils.ReadConfig;
import commonutils.pageobjects.utils.ReadExcelData;

public class GenerateInvoice {
	public GenerateInvoice() {

		PageFactory.initElements(PCDriver.getDriver(), this);
	}

	/******** HeaderInformation ************/
	@FindBy(xpath = "/html/body/nav/div/div/div[2]/ul/li[5]/ul/li[1]/a")
	public WebElement createnew;

	@FindBy(xpath = "//input[@id='autocomplete_supplierSrch']")
	public WebElement supplier;

	@FindBy(xpath = "//button[@id='_pendo-close-guide_']")
	public WebElement pendu;

	@FindBy(xpath = "//ul[contains(@style,'block')]//li[@class='ui-menu-item']/a")
	public List<WebElement> select;

	@FindBy(xpath = "//input[@id='invoicenum']")
	public WebElement invoiceno;

	@FindBy(xpath = "//select[@id='payindicator']")
	public WebElement payindicator;

	@FindBy(xpath = "//input[@id='receive_date']")
	public WebElement receivedate;

	@FindBy(xpath = "//input[@id='post_date']")
	public WebElement postdate;

	@FindBy(xpath = "//input[@id='issue_date']")
	public WebElement issuedate;

	@FindBy(xpath = "//input[@id='due_date']")
	public WebElement duedate;

	@FindBy(xpath = "//input[@id='memo']")
	public WebElement memo;

	@FindBy(id = "_pendo-close-guide_")
	public WebElement pindu;

	@FindBy(id = "eft")
	public WebElement eft;

	@FindBy(xpath = "//*[@id=\"cont-requirement-content\"]/form/div[3]/div[3]/div/div[1]/span/span[1]/span")
	public WebElement cat;

	@FindBy(xpath = "//input[contains(@class,'select2-search__field')]")
	public WebElement inputcat;

	@FindBy(xpath = "//ul[contains(@id,'select2-19-results')]")
	public List<WebElement> selectcat;

	@FindBy(xpath = "//select[@id='assetIndicator']")
	public WebElement Asset;

	@FindBy(xpath = "//button[@class='btn btn-wp']")
	public WebElement nxtbtn;

	/********* InvoiceItem ***********/
	@FindBy(xpath = "(//button[@class='btn btn-wp'])[1]")
	public WebElement Findpo;

	@FindBy(xpath = "//i[contains(@class,'hand-pointer')]")
	public List<WebElement> drop;

	@FindBy(xpath = "(//input[contains(@name,'polines')])[1]")
	public WebElement item1;

	@FindBy(xpath = "(//input[contains(@name,'polines')])[2]")
	public WebElement item2;

	@FindBy(xpath = "//button[@class='btn btn-wp savebutton']")
	public WebElement savebtn;

	@FindBy(xpath = "//input[contains(@id,'invoiceQty')]")
	public List<WebElement> quantity;

	@FindBy(xpath = "//input[contains(@name,'invoiceQty_1961_2')]")
	public WebElement quantity1;

	@FindBy(xpath = "(//button[contains(@onclick,'submitPage')])[2]")
	public WebElement nxt;

	/********* Attachment ***********/
	@FindBy(xpath = "//button[@id='adddocid']")
	public WebElement adddoc;

	@FindBy(xpath = "(//span[contains(@class,'btn btn-wp btn-file')])[1]")
	public WebElement browse;

	@FindBy(xpath = "//input[@id='filetext1']")
	public WebElement name;

	@FindBy(xpath = "(//textarea[contains(@class,'filePurposeClass form-control')])[1]")
	public WebElement purpose;

	@FindBy(xpath = "//button[@id='uploadButton']")
	public WebElement uploadfile;

	@FindBy(xpath = "(//button[@class='btn btn-wp'])[3]")
	public WebElement nxtclick;

	/********* Invoice Matching ***********/
	@FindBy(xpath = "//button[contains(@onclick,'matchAll()')]")
	public WebElement matchall;

	@FindBy(xpath = "(//button[@class='btn btn-wp'])[3]")
	public WebElement nxtclick2;

	/********* Summary ***********/
	@FindBy(xpath = "(//button[@class='btn btn-wp'])[4]")
	public WebElement submit;

	@FindBy(xpath = "//iframe")
	public WebElement frame;
	
	@FindBy(xpath="//input[@id='file1']")
	public WebElement uploadFile;
	
	@FindBy(xpath="//h3[text()='Invoice List']")
	public WebElement lblInvoiceListPage;

	public void invoiceHeader() {
		try {
			System.out.println("Entered Invoice Header");
			String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
			createnew.click();
			supplier.sendKeys(ReadExcelData.getInstance("Headerinfo").getStringValue("Supplier"));
			supplier.clear();
			try {
				PCDriver.waitForElementToBeClickable(pendu);
				pendu.click();
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Popup not present");
			}
			supplier.sendKeys(ReadExcelData.getInstance("Headerinfo").getStringValue("Supplier"));

			PCDriver.visibilityOfListLocated(select);
			for (WebElement selectsupplier : select) {
				PCDriver.waitForElementToBeClickable(selectsupplier);
				Assert.assertTrue(selectsupplier.getText()
						.contains(ReadExcelData.getInstance("Headerinfo").getStringValue("Supplier")));
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				selectsupplier.click();
			}
			PCDriver.waitForElementToBeClickable(invoiceno);
			invoiceno.sendKeys(
					ReadExcelData.getInstance("Headerinfo").getStringValue("Invoice No") + System.currentTimeMillis());
			PCDriver.selectFromDropDownByVisibleText(payindicator,
					(ReadExcelData.getInstance("Headerinfo").getStringValue("Pay Indicator")));
			receivedate.sendKeys(ReadExcelData.getInstance("Headerinfo").getStringValue("Receive Date"));
			postdate.sendKeys(ReadExcelData.getInstance("Headerinfo").getStringValue("Post Date"));
			issuedate.sendKeys(ReadExcelData.getInstance("Headerinfo").getStringValue("Issue Date"));
			duedate.sendKeys(ReadExcelData.getInstance("Headerinfo").getStringValue("Due Date"));
			memo.sendKeys(ReadExcelData.getInstance("Headerinfo").getStringValue("Memo"));
			PCDriver.waitForElementToBeClickable(eft);
			PCDriver.selectFromDropDownByVisibleText(eft,
					(ReadExcelData.getInstance("Headerinfo").getStringValue("EFT")));
			cat.click();
			inputcat.sendKeys(ReadExcelData.getInstance("Headerinfo").getStringValue("Check Cat"));
			PCDriver.visibilityOfListLocated(selectcat);
			for (WebElement list : selectcat) {
				System.out.println(list.getText());
				Assert.assertTrue(
						list.getText().contains(ReadExcelData.getInstance("Headerinfo").getStringValue("Check Cat")));
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				list.click();
			}
			PCDriver.selectFromDropDownByVisibleText(Asset,
					(ReadExcelData.getInstance("Headerinfo").getStringValue("Fixed Asset")));
			nxtbtn.click();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Exit Invoice Header");

	}

	public void additem() {
		try {
			System.out.println("Entered Add Item");
			PCDriver.waitForPageLoad();
			String expectedtitle = "PO/Line Data";
			String actualtitle = PCDriver.getDriver().getTitle();
			System.out.println(actualtitle+"This is");
			Assert.assertTrue(actualtitle.contains(expectedtitle));
			PCDriver.waitForElementToBeClickable(Findpo);
			Findpo.click();
			PCDriver.switchToFrame(frame);
			PCDriver.visibilityOfListLocated(drop);
			System.out.println("Drop size is "+ drop.size());
			drop.get(0).click();
			PCDriver.waitForElementToBeClickable(item1);
			try {
				Thread.sleep(4000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			item1.click();
			PCDriver.waitForElementToBeClickable(item2);
			item2.click();
			PCDriver.waitForElementToBeClickable(savebtn);
			savebtn.click();
			//PCDriver.waitForElementToDisappear(By.xpath("//button[@class='btn btn-wp savebutton']"));
			PCDriver.switchToDefaultContent();
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			PCDriver.visibilityOfListLocated(quantity);
			quantity.get(0).click();
			quantity.get(0).sendKeys(Keys.chord(Keys.CONTROL, "a"));
			quantity.get(0).sendKeys(Keys.BACK_SPACE);
			quantity.get(0).sendKeys(ReadExcelData.getInstance("Attachment").getStringValue("Quantityofitem"));
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			PCDriver.visibilityOfListLocated(quantity);
			quantity.get(1).click();
			quantity.get(1).sendKeys(Keys.chord(Keys.CONTROL, "a"));
			quantity.get(1).sendKeys(Keys.BACK_SPACE);
			quantity.get(1).sendKeys(ReadExcelData.getInstance("Attachment").getStringValue("Quantityofitem1"));
			nxt.click();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Exit Add Item");

	}

	public void attachment() {
		try {
			System.out.println("Entered Attachment Method");

			PCDriver.waitForPageLoad();
			String expectedtitle = "WebProcure: Upload Documents";
			String actualtitle = PCDriver.getDriver().getTitle();
			Assert.assertTrue(actualtitle.contains(expectedtitle));
			adddoc.click();
			//PCDriver.waitForElementToBeClickable(browse);
			//browse.click();
			try {
				//PCDriver.uploadFile(ReadExcelData.getInstance("Attachment").getStringValue("Filename"));
			uploadFile.sendKeys(ReadExcelData.getInstance("Attachment").getStringValue("Filename"));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			purpose.sendKeys(ReadExcelData.getInstance("Attachment").getStringValue("Justification"));

			uploadfile.click();
			PCDriver.waitForElementToBeClickable(nxtclick);
			try {
				Thread.sleep(20000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			nxtclick.click();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Exit Attachment Method");

	}

	public void match() {
		System.out.println("Entered Match Method");

		String expectedtitle = "WebProcure: Invoice Item Matching";
		String actualtitle = PCDriver.getDriver().getTitle();
		Assert.assertTrue(actualtitle.contains(expectedtitle));
		PCDriver.waitForElementToBeClickable(matchall);
		matchall.click();
		nxtclick2.click();
		System.out.println("Exit Match Method");

	}
 
	
	public void invoiceSummary()
	{   
		System.out.println("Entered invoiceSummary Method");

		String expectedtitle = "WebProcure: Invoice Summary";
		String actualtitle = PCDriver.getDriver().getTitle();
		Assert.assertTrue(actualtitle.contains(expectedtitle));
		((JavascriptExecutor) PCDriver.getDriver()).executeScript("arguments[0].scrollIntoView(true);", submit);
		submit.click();
		System.out.println("Exit invoiceSummary Method");

	}
	public void createinvoice() {

		invoiceHeader();
		additem();
		attachment();
		match();
		invoiceSummary();
	}
	
	public boolean verifyPageAfterInvoiceSubmission() {
		PCDriver.waitForElementToBeClickable(lblInvoiceListPage);
		
		if(lblInvoiceListPage.getText().contains("Invoice List")) {
			return true;
		}
		else {
			return false;
		}
	}

}
