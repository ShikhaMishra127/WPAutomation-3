package buyer.pageobjects.invoice;

import java.io.Closeable;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.ClickAction;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.gargoylesoftware.htmlunit.javascript.host.Window;

import commonutils.pageobjects.utils.PCDriver;
import commonutils.pageobjects.utils.ReadExcelData;

public class Viewinvoice {
	public Viewinvoice() {
		PageFactory.initElements(PCDriver.getDriver(), this);
	}

	@FindBy(xpath = "//input[contains(@id,'supplierSrch')]")
	public WebElement supsrch;

	@FindBy(xpath = "//input[contains(@name,'binvoicenum')]")
	public WebElement binvnum;

	@FindBy(xpath = "(//input[contains(@id,'invoicenum')])[2]")
	public WebElement invnum;

	@FindBy(xpath = "//input[contains(@name,'requisitioner')]")
	public WebElement req;

	@FindBy(xpath = "//span[contains(@id,'search_concept')]")
	public WebElement datetype;

	@FindBy(xpath = "//*[@id=\"filter\"]/form/fieldset/div[2]/div[1]/div/div[1]/div/div[1]/ul/li[2]/a")
	public WebElement postdate;

	@FindBy(xpath = "//input[contains(@name,'fromdate')]")
	public WebElement from;

	@FindBy(xpath = "//input[contains(@name,'todate')]")
	public WebElement to;

	@FindBy(xpath = "//select[contains(@id,'invstat')]")
	public WebElement invcstatus;

	@FindBy(xpath = "(//button[contains(@class,'btn btn-wp')])[1]")
	public WebElement filter;

	@FindBy(xpath = "(//button[contains(@class,'btn btn-wp')])[2]")
	public WebElement reset;

	@FindBy(xpath = "(//ul[contains(@class,'dropdown-menu-right')])[1]")
	public List<WebElement> actiondrop;

	@FindBy(xpath = "(//a[contains(@title,'View All')])[3]")
	public WebElement viewall;

	@FindBy(xpath = "/html/body/div[1]/section[4]/div[2]/table/tbody/tr[1]/td[7]")
	public WebElement Airplan;

	@FindBy(xpath = "//*[@id=\"invTable\"]/tbody/tr[1]/td[7]")
	public WebElement pawn;
	
	@FindBy(xpath="//td")
	public WebElement actions;
	
	@FindBy(xpath="//tr")
	public WebElement selectaction;
	
	@FindBy(xpath="//tr")
	public WebElement no;
	
	@FindBy(xpath="//*[@id=\"invTable\"]/tbody/tr[1]")
	public WebElement wholeinv;

	@FindBy(xpath= "//*[@id=\"invTable\"]/tbody/tr[1]")
	public WebElement fetchInv;
	
	@FindBy(xpath="//b/u[contains(text(),'Workflow Map')]")
	public WebElement workflow;
	
	@FindBy(xpath="/html/body/table/tbody/tr/td[2]/table[3]/tbody/tr[2]/td[2]/button")
	public WebElement close;

	@FindBy(xpath="(//img[contains(@title,'Expand')])[1]")
	public WebElement expandinv;
	
	@FindBy(xpath="(//font[contains(@class,'ReportSubHeader')])[1]")
	public WebElement posumm;
	
	@FindBy(xpath="//a[contains(text(),'Group by Orders')]")
	public WebElement groupbyorder;
	
	public void supinv() {
		try {
            
			viewall.click();
			PCDriver.waitForPageLoad();
			supsrch.sendKeys(ReadExcelData.getInstance("ViewInv").getStringValue("Supplier"));
			PCDriver.waitForElementToBeClickable(filter);
			filter.click();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public void chooseaction(String str)
	{
		actions.findElement(By.xpath("//span[contains(@class,'open')]/child::ul/li/a[contains(text(),'"+str+"')]")).click();
		
	}
    public void clickaction(String str)
    {
    	selectaction.findElement(By.xpath(""
    			+ "(//td[contains(text(),'"+str+"')]/following-sibling::td//img)[1]")).click();
    }
    public void searchInv(String str)
    {
    	no.findElement(By.xpath("(//td[contains(text(),'"+str+"')]/following-sibling::td//img)[2]")).click();
    }
    
	public void buyInv() {
		try {
			PCDriver.waitForPageLoad();

			PCDriver.waitForElementToBeClickable(viewall);
			viewall.click();
			reset.click();
			binvnum.sendKeys(ReadExcelData.getInstance("ViewInv").getStringValue("BuyerInvoice"));
			filter.click();
			
		} catch (IOException e) {

		}
	}

	public void invNo() { 
		try {
			PCDriver.waitForElementToBeClickable(viewall);
			viewall.click();
			reset.click();
			invnum.sendKeys(ReadExcelData.getInstance("ViewInv").getStringValue("Invoice No."));
			PCDriver.waitForPageLoad();
			filter.click();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void requester() {
		try {
			viewall.click();
			reset.click();
			req.sendKeys(ReadExcelData.getInstance("ViewInv").getStringValue("Requester"));
			filter.click();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void date() {
		try {
			viewall.click();
			reset.click();
			datetype.click();
			postdate.click();
			from.sendKeys(ReadExcelData.getInstance("ViewInv").getStringValue("FromDate"));
			to.sendKeys(ReadExcelData.getInstance("ViewInv").getStringValue("ToDate"));
			filter.click();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void selectStatus() {
		viewall.click();
		reset.click();
		try {
			PCDriver.selectFromDropDownByVisibleText(invcstatus,
					(ReadExcelData.getInstance("ViewInv").getStringValue("Status")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		filter.click();
	}
	
	public void viewInv()
	{
		viewall.click();
		reset.click();
		clickaction("Not Matched");
		chooseaction("View Invoice");
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
		}
	}
	public void approvalMap()
	{
		viewall.click();
		reset.click();
		clickaction("Submitted For Payment");
		chooseaction("Approval Map");
		nxtwindow();
	}
	public void nxtwindow()
	{   
		PCDriver.waitForPageLoad();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
		}
		PCDriver.switchToWindow("winnis1");
		PCDriver.waitForPageLoad();
		Assert.assertTrue(workflow.getText().equals("Workflow Map"));
		PCDriver.waitForElementToBeClickable(close);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
		}
		close.click();
		PCDriver.switchToWindow("");
    }
	public void history()
	{
		viewall.click();
		reset.click();
		PCDriver.waitForPageLoad();
		clickaction("Not Matched");
		chooseaction("View Invoice History");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
		}	
	}

	public void expandInv()
	{
		viewall.click();
		reset.click();
		PCDriver.waitForPageLoad();
		((JavascriptExecutor) PCDriver.getDriver()).executeScript("arguments[0].scrollIntoView(true);",
				expandinv);
		expandinv.click();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
		}
		clickaction("Complete");
		PCDriver.waitForPageLoad();
		chooseaction("View Order Details");
		poDetail();
	}
	public void poDetail()
	{
		PCDriver.waitForPageLoad();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
		}
		PCDriver.switchToWindow("PopUp");
		PCDriver.waitForPageLoad();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
		}
		System.out.println(posumm.getText());
		Assert.assertTrue(posumm.getText().equals("Purchase Order Summary"));
		PCDriver.getDriver().close();
		PCDriver.switchToWindow("");
	}
	
	public void groupby()
	{
		clickaction("Complete");
		PCDriver.waitForPageLoad();
		chooseaction("View Order Details");
		poDetail();
	}
	public void cancel()
	{
		System.out.println(wholeinv.getText());
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
		}
		clickaction("Rejected");
		chooseaction("Invoice Cancel");
		System.out.println(fetchInv.getText());
		PCDriver.waitForPageLoad();
		
	}
	public String supassert1() {
		System.out.println(Airplan.getText());
		return Airplan.getText();
	}

	public String supassert2() {
		System.out.println(pawn.getText());
		return pawn.getText();
	}

	public String supassert3() {
		return pawn.getText();
	}
	

}
