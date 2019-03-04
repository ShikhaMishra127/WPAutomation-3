package pageobjects.buyer.invoice;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import utilities.common.Browser;
import utilities.common.ResourceLoader;

public class InvoicePom{

	private final Browser browser;
		
	public InvoicePom(WebDriver browser) {
		this.browser = (Browser) browser;
		PageFactory.initElements(((Browser) browser).driver, this);
	}

	/******** Header Information Page ************/	
	
	@FindBy(xpath = "//a[contains(@title,'Create New')]")
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

	@FindBy(xpath = "//span[contains(@id,'search_concept')]")
	public WebElement datetype;
	
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
	
	@FindBy(xpath = "//table[@id='invTable']//tbody/tr[1]//td[contains(text(),'AutoSupplier')]")
	public WebElement supplierName;

	@FindBy(xpath = "//*[@id=\"invTable\"]/tbody/tr[1]/td[7]")
	public WebElement pawn;

	@FindBy(xpath = "//input[contains(@class,'select2-search__field')]")
	public WebElement inputcat;

	@FindBy(xpath = "//ul[contains(@id,'select2-19-results')]")
	public List<WebElement> selectcat;

	@FindBy(xpath = "//select[@id='assetIndicator']")
	public WebElement Asset;

	@FindBy(xpath = "//button[@class='btn btn-wp']")
	public WebElement nxtbtn;

	/********* Purchase Order Selection Page ***********/
	@FindBy(xpath = "//input[contains(@id,'ponum_filter')]")
	public WebElement search;

	@FindBy(xpath = "//button[contains(@onclick,'submitSearch')]")
	public WebElement searchbtn;

	@FindBy(xpath = "(//button[@class='btn btn-wp'])[1]")
	public WebElement Findpo;

	@FindBy(xpath = "//i[contains(@class,'hand-pointer')]")
	public List<WebElement> drop;

	@FindBy(xpath = "(//input[contains(@name,'polines')])[1]")
	public WebElement item1;

	@FindBy(xpath = "(//input[contains(@name,'polines')])[2]")
	public WebElement item2;

	@FindBy(xpath = "//input[contains(@class,'checkall')]")
	public WebElement checkall;

	@FindBy(xpath = "//button[@class='btn btn-wp savebutton']")
	public WebElement savebtn;

	@FindBy(xpath = "//input[contains(@id,'invoiceQty')]")
	public List<WebElement> quantity;

	@FindBy(xpath = "//input[contains(@name,'invoiceQty_1961_2')]")
	public WebElement quantity1;

	@FindBy(xpath = "(//button[contains(@onclick,'submitPage')])[2]")
	public WebElement nxt;

	@FindBy(xpath = "(//input[contains(@onkeypress,'searchItem')])[1]")
	public WebElement fa;

	@FindBy(xpath = "(//span[contains(@class,'hand-pointer')])[1]")
	public WebElement faSearch;

	@FindBy(xpath = "/html/body/div[5]/div[2]/div/div[1]/div/div/section[1]/h3")
	public WebElement title;
	
	@FindBy(xpath = "//section/h3[contains(text(),'Select Item Category')]")
	public WebElement titlefa;

	/********* Attachment Page ***********/
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

	/********* Invoice Matching Page ***********/
	@FindBy(xpath = "//button[contains(@onclick,'matchAll()')]")
	public WebElement matchall;

	@FindBy(xpath = "//button[contains(@onclick,'showSummaryPage')]")
	public WebElement nxtclick2;

	@FindBy(xpath = "(//button[contains(@onclick,'matchInvoice')])[1]")
	public WebElement match;

	/********* Summary Page***********/
	@FindBy(xpath = "(//button[@class='btn btn-wp'])[4]")
	public WebElement submit;

	@FindBy(xpath = "//iframe")
	public WebElement frame;

	@FindBy(xpath = "//input[@id='file1']")
	public WebElement uploadFile;

	/********* Receive Date Alert ***********/
	/*@FindBy(xpath = "//button[contains(@data-bb-handler,'ok')]")
	public WebElement okbtn;
	
	@FindBy(xpath = "//button[text()='OK']")
	public WebElement ok;

	@FindBy(xpath = "//div[contains(@class,'bootbox-body')]")
	public WebElement Alertmsg;

	@FindBy(xpath = "//button[contains(@data-bb-handler,'confirm')]")
	public WebElement yesbtn;
	
	@FindBy(xpath = "//button[contains(@class,'adddoc')]")
	public WebElement nobtn;

	@FindBy(xpath = "//button[contains(@class,'button close')]")
	public WebElement closebtn;

	@FindBy(xpath = "//span[contains(@id,'invsuppname')]")
	public WebElement invsuppname;*/

	/********* Assert pages ***********/
	@FindBy(xpath = "//*[@id=\"page-title\"]/h3")
	public WebElement POdata;
	
	  public ResourceLoader invdata = new ResourceLoader("data/Invoice");

    //// Adding data in Header Page
	public void invoiceHeader() {
		
			System.out.println("Entered in Invoice Header");
			createnew.click();
			browser.waitForPageLoad();
			try {
				TimeUnit.SECONDS.sleep(10);
			} catch (InterruptedException e1) {
			}
			suppliername(invdata.getValue("SupplierName"));
			browser.waitForElementToBeClickable(invoiceno);
			invoiceno.sendKeys(invdata.getValue("InvoiceNo")+ System.currentTimeMillis());
			//ReadExcelData.getInstance("Headerinfo").updateCellValue("supplier Invoice no",invoiceno.getAttribute("value"));
			browser.waitForElementToBeClickable(receivedate);
			receivedate.sendKeys(invdata.getValue("ReceiveDate"));
			postdate.sendKeys(invdata.getValue("PostDate"));
			issuedate.sendKeys(invdata.getValue("IssueDate"));
			duedate.sendKeys(invdata.getValue("DueDate"));
			memo.sendKeys(invdata.getValue("Memo"));
			browser.waitForElementToBeClickable(eft);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
			browser.selectFromDropDownByVisibleText(eft,
					invdata.getValue("EFT"));
			browser.waitForPageLoad();
			nxtbtn.click();
		}
		
	//// Adding item details for invoice creation
	public void additem()
	{
		Findpo.click();
		browser.switchToFrame(frame);
		browser.waitForElementToBeClickable(search);
		search.clear();
		search.sendKeys(invdata.getValue("PO"));
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		searchbtn.click();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e2) {
		}
		browser.visibilityOfListLocated(drop);
		drop.get(0).click();
		browser.waitForElementToBeClickable(checkall);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
		}
		checkall.click();
		browser.waitForElementToBeClickable(savebtn);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		savebtn.click();

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		browser.visibilityOfListLocated(quantity);
		quantity.get(0).click();
		quantity.get(0).sendKeys(Keys.chord(Keys.CONTROL, "a"));
		quantity.get(0).sendKeys(Keys.BACK_SPACE);
		quantity.get(0).sendKeys(invdata.getValue("Quantityofitem"));

		browser.waitForElementToBeClickable(nxt);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		nxt.click();
	}
	
    //// Add attachment
	public void attachment() {
		browser.waitForPageLoad();
		nxtclick.click();
	}
    //// Matching receipt 
	public void match() {
		browser.waitForPageLoad();
		browser.waitForElementToBeClickable(matchall);
		matchall.click();
		nxtclick2.click();

	}
    //// Summary Page
	public void invoiceSummary() {
		browser.waitForPageLoad();
		((JavascriptExecutor) browser.getDriver()).executeScript("arguments[0].scrollIntoView(true);", submit);
		submit.click(); 
	}

	public void suppliername(String Supname)
	{
		browser.waitForElementToBeClickable(supplier);
		supplier.sendKeys(Supname);

		browser.visibilityOfListLocated(select);
		System.out.println("Select list visible");

		for (WebElement selectsupplier : select) {
			browser.waitForElementToBeClickable(selectsupplier);
			System.out.println("Supplier Name" + selectsupplier.getText());
			Assert.assertTrue(selectsupplier.getText()
					.contains(Supname));
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
			}
			selectsupplier.click();
	}
	}
	
	public String suppassert1() {
		System.out.println(supplierName.getText());
		return supplierName.getText();
	}

	public String suppassert2() {
		System.out.println(pawn.getText());
		return pawn.getText();
	}
	

	

}
