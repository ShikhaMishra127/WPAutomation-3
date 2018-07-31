package buyer.pageobjects.invoice;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
	
	@FindBy(xpath = "(//a[contains(@title,'View All')])[2]")
	public WebElement viewAllmiss;

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
	public WebElement addPO;

	@FindBy(xpath = "//input[contains(@id,'invoiceQty')]")
	public List<WebElement> quantity;

	@FindBy(xpath = "//input[contains(@name,'invoiceQty_1961_2')]")
	public WebElement quantity1;

	@FindBy(xpath = "//button[contains(@onclick,'submitPage')]")
	public WebElement nxt;

	@FindBy(xpath = "//button[contains(@id,'save')]")
	public WebElement next;

	@FindBy(xpath = "//button[contains(@data-bb-handler,'confirm')]")
	public WebElement confirm;

	@FindBy(xpath = "//button[contains(@id,'btnsubmit')]")
	public WebElement submitbtn;

	@FindBy(xpath = "//button[contains(@id,'closemodal')]")
	public WebElement close;
	
	@FindBy(xpath = "//button[contains(@data-bb-handler,'ok')]")
	public WebElement ok;
	
	@FindBy(xpath = "//div[contains(@class,'bootbox-body')]")
	public WebElement Alertmsg;

	@FindBy(xpath = "(//*[contains(.,'Yes')])[8]")
	public WebElement text;
	
	@FindBy(xpath = "//button[contains(@class,'close')]")
	public WebElement closewindow;
	
	@FindBy(xpath = "//button[contains(@id,'btnclose')]")
	public WebElement closebtn;
	
	/********* View All Invoice ***********/
	
	@FindBy(xpath = "//select[contains(@id,'invstat')]")
	public WebElement invcstatus;
	
	@FindBy(xpath = "(//button[contains(@class,'btn btn-wp')])[1]")
	public WebElement filter;
	
	@FindBy(xpath="//tr")
	public WebElement selectaction;
	
	@FindBy(xpath="//td")
	public WebElement actions;
	
	@FindBy(xpath="//a[contains(@title,'Edit Header')]")
	public WebElement editheader;
	
	@FindBy(xpath="//a[contains(@title,'Summary')]")
	public WebElement summary;
	
	@FindBy(xpath="//button[contains(text(),'Submit')]")
	public WebElement submit;
	
	@FindBy(xpath="//button[contains(text(),'Save')]")
	public WebElement savebtn;
	
	public void venInvHead() {
		try {
			System.out.println("Entered in Invoice Header");
			createNewMiss.click();
			PCDriver.waitForElementToBeClickable(invoiceno);
			invoiceno.sendKeys(
					ReadExcelData.getInstance("VinvHeader").getStringValue("Invoice No") + System.currentTimeMillis());
			issuedate.sendKeys(ReadExcelData.getInstance("VinvHeader").getStringValue("Issue Date"));
			duedate.sendKeys(ReadExcelData.getInstance("VinvHeader").getStringValue("Due Date"));

		} catch (IOException e) {

		}
	}

	public void invViewHead()
	{
		viewAllmiss.click();
		PCDriver.waitForPageLoad();
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
			try {
				Thread.sleep(4000);
			} catch (InterruptedException e) {
			}
			drop.get(0).click();
			PCDriver.waitForElementToBeClickable(checkall);
			checkall.click();
			PCDriver.waitForElementToBeClickable(addPO);
			addPO.click();
			PCDriver.switchToDefaultContent();
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e) {

		}
		}
	
		public void invDetails()
			{
				try {
			PCDriver.visibilityOfListLocated(quantity);
			quantity.get(0).click();
			quantity.get(0).sendKeys(Keys.chord(Keys.CONTROL, "a"));
			quantity.get(0).sendKeys(Keys.BACK_SPACE);
			quantity.get(0).sendKeys(ReadExcelData.getInstance("VinvHeader").getStringValue("Quantityofitem"));
			PCDriver.visibilityOfListLocated(quantity);
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
	        
		public void noitemPO()
		{
			Findpo.click();
			PCDriver.switchToFrame(frame);
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e1) {
			}
			PCDriver.waitForPageLoad();
			PCDriver.waitForElementToBeClickable(search);
			search.clear();
			try {
				search.sendKeys(ReadExcelData.getInstance("VinvHeader").getStringValue("PO"));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			PCDriver.waitForElementToBeClickable(searchbtn);
			searchbtn.click();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
			PCDriver.waitForElementToBeClickable(addPO);
			addPO.click();
			

		}
	public void attach() {
		PCDriver.waitForPageLoad();
		PCDriver.waitForElementToBeClickable(next);
		next.click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
		}
		confirm.click();
		
	}

	public void summary() {
		PCDriver.waitForPageLoad();
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
			PCDriver.waitForElementToBeClickable(addPO);
			addPO.click();
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
	public String getAlert()
	{
		System.out.println(Alertmsg.getText());
		return Alertmsg.getText();
	}
	public void emptyPO()
	{
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
		}
		Assert.assertTrue(getAlert().contains("Please select at least one PO line"));
		ok.click();
		PCDriver.switchToDefaultContent();
		closewindow.click();
		PCDriver.switchToDefaultContent();
	}
	
	/********* ViewAll Invoice  ***********/
	
	
	public void clickaction(String str)
    {
    	selectaction.findElement(By.xpath(""
    			+ "(//td[contains(text(),'"+str+"')]/following-sibling::td//img)[1]")).click();
    }
	public void chooseaction(String str)
	{
		actions.findElement(By.xpath("//span[contains(@class,'open')]/child::ul/li/a[contains(text(),'"+str+"')]")).click();
		
	}
	public void activeinv()
	{
		try {
			PCDriver.selectFromDropDownByVisibleText(invcstatus,(ReadExcelData.getInstance("VendorView").getStringValue("Status")));
			filter.click();
			clickaction("Created");
			chooseaction("View Invoice");
		} catch (IOException e) {
			
		}
	}
	public void editinvoice()
	{
	try {
		PCDriver.selectFromDropDownByVisibleText(invcstatus,(ReadExcelData.getInstance("VendorView").getStringValue("Status")));
		filter.click();
		clickaction("Created");
		chooseaction("Edit Invoice");
		editheader.click();
		PCDriver.waitForPageLoad();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
		}
		quantity.get(0).click();
		quantity.get(0).sendKeys(Keys.chord(Keys.CONTROL, "a"));
		quantity.get(0).sendKeys(Keys.BACK_SPACE);
		quantity.get(0).sendKeys(ReadExcelData.getInstance("VendorView").getStringValue("Edit value"));
		savebtn.click();
		PCDriver.waitForPageLoad();
		summary.click();
		PCDriver.waitForElementToBeClickable(submit);
		submit.click();
		} catch (IOException e) {
			
		}
		
	}
	
	
}
