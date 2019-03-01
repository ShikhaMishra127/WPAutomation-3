package pageobjects.buyer.invoice;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import utilities.common.Browser;
import utilities.common.ResourceLoader;

public class ViewinvoicePom {
	
	private final Browser browser;
	
	public ViewinvoicePom(WebDriver browser) {
		this.browser = (Browser) browser;
		PageFactory.initElements(((Browser) browser).driver, this);
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

	@FindBy(xpath = "//a[contains(text(),'Issue Date')]")
	public WebElement issuedate;

	@FindBy(xpath = "//input[contains(@name,'fromdate')]")
	public WebElement from;

	@FindBy(xpath = "//input[contains(@name,'todate')]")
	public WebElement to;

	@FindBy(xpath = "//select[contains(@id,'invstat')]")
	public WebElement invcstatus;

	@FindBy(xpath = "(//button[contains(@class,'btn btn-wp')])[1]")
	public WebElement filter;
	
	@FindBy(xpath = "//*[@id=\"page-title\"]/h3")
	public WebElement POdata;

	@FindBy(xpath = "(//button[contains(@class,'btn btn-wp')])[2]")
	public WebElement reset;

	@FindBy(xpath = "(//ul[contains(@class,'dropdown-menu-right')])[1]")
	public List<WebElement> actiondrop;

	@FindBy(xpath = "(//a[contains(@title,'View All')])[3]")
	public WebElement viewall;

	@FindBy(xpath = "/html/body/div[1]/section[4]/div[2]/table/tbody/tr[1]/td[7]")
	public WebElement Airplan;

	@FindBy(xpath = "//table//tbody//tr[1]//td[7]")
	public WebElement vendorname;
	
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
	
	@FindBy(xpath="//a[contains(@title,'Edit Items')]")
	public WebElement edititems;
	
	@FindBy(xpath="//a[contains(@title,'Summary')]")
	public WebElement summary;
	
	@FindBy(xpath = "//input[contains(@id,'invoiceQty')]")
	public List<WebElement> quantity;
	
	@FindBy(xpath = "(//button[contains(text(),'Save')])[2]")
	public List<WebElement> save;
	
	@FindBy(xpath = "//button[contains(text(),'Submit')]")
	public WebElement submit;
	
	@FindBy(xpath = "(//th[contains(text(),'Supplier')])[2]")
	public WebElement supcolheader;
	
	public ResourceLoader invdata = new ResourceLoader("data/Invoice");
	
	
	public void supinv() {
		viewall.click();
		browser.waitForPageLoad();
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
		}
		supsrch.sendKeys(invdata.getValue("Supplier"));
		browser.waitForElementToBeClickable(filter);
		filter.click();

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
		browser.waitForPageLoad();

		browser.waitForElementToBeClickable(viewall);
		viewall.click();
		reset.click();
		binvnum.sendKeys(invdata.getValue("BuyerInvoice"));
		filter.click();
	}

	public void invNo() { 
		browser.waitForElementToBeClickable(viewall);
		viewall.click();
		reset.click();
		invnum.sendKeys(invdata.getValue("InvoiceNo."));
		browser.waitForPageLoad();
		filter.click();
	}
	public String supassert1() {
		System.out.println(Airplan.getText());
		return Airplan.getText();
	}
	public void sortSupplier()
	{
		supcolheader.click();
		browser.waitForPageLoad();
		Assert.assertTrue(supassert2().contains("AutoSupplier"));
	}
	public void date() {
		viewall.click();
		reset.click();
		browser.waitForPageLoad();
		datetype.click();
		issuedate.click();
		from.sendKeys(invdata.getValue("Fromdate"));
		to.sendKeys(invdata.getValue("Todate"));
		filter.click();
		
	}
	public String supassert2() {
		try {
			TimeUnit.SECONDS.sleep(10);
		} catch (InterruptedException e1) {
		}
		return vendorname.getText();
	}
	
	public String poSelect() {
		return POdata.getText();
	}
}
