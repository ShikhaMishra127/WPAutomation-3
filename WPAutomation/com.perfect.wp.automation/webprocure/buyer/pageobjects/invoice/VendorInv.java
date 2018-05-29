package buyer.pageobjects.invoice;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import commonutils.pageobjects.utils.PCDriver;
import commonutils.pageobjects.utils.ReadExcelData;

public class VendorInv {
	public VendorInv() {
		PageFactory.initElements(PCDriver.getDriver(), this);
	}

	@FindBy(xpath = "(//a[contains(@title,'Create New')])[2]")
	public WebElement createNewMiss;

	@FindBy(xpath = "//input[@id='invoicenum']")
	public WebElement invoiceno;

	@FindBy(xpath = "//input[@id='issue_date']")
	public WebElement issuedate;

	@FindBy(xpath = "//input[@id='due_date']")
	public WebElement duedate;

	@FindBy(xpath = "//div[contains(@class,'bootstrap-switch-off')]")
	public WebElement finalinv;

	@FindBy(xpath = "(//button[@class='btn btn-wp'])[1]")
	public WebElement Findpo;

	@FindBy(xpath = "//input[contains(@id,'ponum_filter')]")
	public WebElement search;

	@FindBy(xpath = "//button[contains(@onclick,'submitSearch')]")
	public WebElement searchbtn;

	@FindBy(xpath = "//iframe")
	public WebElement frame;

	@FindBy(xpath = "//i[contains(@class,'hand-pointer')]")
	public List<WebElement> drop;

	@FindBy(xpath = "//input[contains(@class,'checkall')]")
	public WebElement checkall;

	@FindBy(xpath = "//button[@class='btn btn-wp savebutton']")
	public WebElement savebtn;

	@FindBy(xpath = "//input[contains(@id,'invoiceQty')]")
	public List<WebElement> quantity;

	@FindBy(xpath = "//input[contains(@name,'invoiceQty_1961_2')]")
	public WebElement quantity1;

	@FindBy(xpath = "//button[contains(@onclick,'submitPage')]")
	public WebElement nxt;

	@FindBy(xpath = "//button[contains(@id,'save')]")
	public WebElement save;

	@FindBy(xpath = "//button[contains(@data-bb-handler,'confirm')]")
	public WebElement confirm;

	@FindBy(xpath = "//button[contains(@id,'btnsubmit')]")
	public WebElement submitbtn;

	@FindBy(xpath = "//button[contains(@id,'closemodal')]")
	public WebElement close;

	@FindBy(xpath = "(//*[contains(.,'Yes')])[8]")
	public WebElement text;
	
	public void venInvHead() {
		try {
			System.out.println("Entered in Invoice Header");
			PCDriver.waitForPageLoad();
			createNewMiss.click();
			PCDriver.waitForElementToBeClickable(invoiceno);
			invoiceno.sendKeys(
					ReadExcelData.getInstance("VinvHeader").getStringValue("Invoice No") + System.currentTimeMillis());
			issuedate.sendKeys(ReadExcelData.getInstance("VinvHeader").getStringValue("Issue Date"));
			duedate.sendKeys(ReadExcelData.getInstance("VinvHeader").getStringValue("Due Date"));

		} catch (IOException e) {

		}
	}

	public void findPO() {
		try {

			Findpo.click();
			PCDriver.switchToFrame(frame);
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e1) {
			}
			PCDriver.waitForPageLoad();
			PCDriver.waitForElementToBeClickable(search);
			search.clear();
			search.sendKeys(ReadExcelData.getInstance("VinvHeader").getStringValue("PO"));
			PCDriver.waitForElementToBeClickable(searchbtn);
			searchbtn.click();
			PCDriver.visibilityOfListLocated(drop);
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
			}
			drop.get(0).click();
			PCDriver.waitForElementToBeClickable(checkall);
			checkall.click();
			PCDriver.waitForElementToBeClickable(savebtn);
			savebtn.click();
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
			quantity.get(0).sendKeys(ReadExcelData.getInstance("VinvHeader").getStringValue("Quantityofitem"));
			PCDriver.visibilityOfListLocated(quantity);
			quantity.get(1).click();
			quantity.get(1).sendKeys(Keys.chord(Keys.CONTROL, "a"));
			quantity.get(1).sendKeys(Keys.BACK_SPACE);
			quantity.get(1).sendKeys(ReadExcelData.getInstance("VinvHeader").getStringValue("Quantityofitem1"));
			PCDriver.waitForElementToBeClickable(nxt);
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			nxt.click();
		} catch (IOException e) {

		}
	}

	public void attach() {
		PCDriver.waitForElementToBeClickable(save);
		save.click();
		PCDriver.waitForElementToBeClickable(confirm);
		confirm.click();
	}

	public void summary() {
		
		PCDriver.waitForElementToBeClickable(submitbtn);
		submitbtn.click();
		PCDriver.waitForElementToBeClickable(confirm);
		confirm.click();
		PCDriver.waitForElementToBeClickable(close);
		close.click();
	}

	public void finvoice() {
		try {

			Findpo.click();
			PCDriver.switchToFrame(frame);
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e1) {
			}
			PCDriver.waitForElementToBeClickable(search);
			search.clear();
			search.sendKeys(ReadExcelData.getInstance("VinvHeader").getStringValue("PO"));
			PCDriver.waitForElementToBeClickable(searchbtn);
			searchbtn.click();
			PCDriver.visibilityOfListLocated(drop);
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
			}
			drop.get(0).click();
			PCDriver.waitForElementToBeClickable(checkall);
			checkall.click();
			PCDriver.waitForElementToBeClickable(savebtn);
			savebtn.click();
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
			quantity.get(0).sendKeys(ReadExcelData.getInstance("VinvHeader").getStringValue("Quantityofitem"));
			PCDriver.visibilityOfListLocated(quantity);
			quantity.get(1).click();
			quantity.get(1).sendKeys(Keys.chord(Keys.CONTROL, "a"));
			quantity.get(1).sendKeys(Keys.BACK_SPACE);
			quantity.get(1).sendKeys(ReadExcelData.getInstance("VinvHeader").getStringValue("Quantityofitem1"));
			 PCDriver.waitForElementToBeClickable(finalinv);
				finalinv.click();
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			PCDriver.waitForElementToBeClickable(nxt);
			nxt.click();
		} catch (IOException e) {

		}
	}
	public String position()
	{
		PCDriver.waitForPageLoad();
		System.out.println(text.getText());
		return text.getText();
	}
	
	public void ponumber()
	{
		try {
		System.out.println("Entered in Invoice Header");
		PCDriver.waitForPageLoad();
		createNewMiss.click();
		PCDriver.waitForElementToBeClickable(invoiceno);
		invoiceno.sendKeys(
				ReadExcelData.getInstance("VinvHeader").getStringValue("Invoice No") + System.currentTimeMillis());
		issuedate.sendKeys(ReadExcelData.getInstance("VinvHeader").getStringValue("Issue Date"));
		duedate.sendKeys(ReadExcelData.getInstance("VinvHeader").getStringValue("Due Date"));
		nxt.click();
	} catch (IOException e) {

	}
	}
	

}
