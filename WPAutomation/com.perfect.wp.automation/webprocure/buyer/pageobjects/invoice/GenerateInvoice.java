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
	VendorInv vinvoice =new VendorInv();
	public GenerateInvoice() {

		PageFactory.initElements(PCDriver.getDriver(), this);
	}

	/******** HeaderInformation ************/
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

	@FindBy(xpath = "//button[contains(@onclick,'showSummaryPage')]")
	public WebElement nxtclick2;

	@FindBy(xpath = "(//button[contains(@onclick,'matchInvoice')])[1]")
	public WebElement match;

	/********* Summary ***********/
	@FindBy(xpath = "(//button[@class='btn btn-wp'])[4]")
	public WebElement submit;

	@FindBy(xpath = "//iframe")
	public WebElement frame;

	@FindBy(xpath = "//input[@id='file1']")
	public WebElement uploadFile;

	/********* Receive Date Alert ***********/
	@FindBy(xpath = "//button[contains(@data-bb-handler,'ok')]")
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
	public WebElement invsuppname;

	/********* Attachment Alert ***********/
	@FindBy(xpath = "(//label[contains(@class,'error')])[1]")
	public WebElement attach;

	/********* Assert pages ***********/
	@FindBy(xpath = "//*[@id=\"page-title\"]/h3")
	public WebElement POdata;

	/********* Invoice Creation ***********/

	public void invoiceHeader() {
		try {
			System.out.println("Entered in Invoice Header");
			createnew.click();
			supplier.sendKeys(ReadExcelData.getInstance("Headerinfo").getStringValue("Supplier"));
			supplier.clear();
			try {
				PCDriver.waitForElementToBeClickable(pendu);
				pendu.click();
			} catch (Exception e) {
				System.out.println("Popup not present");
			}
			supplier.sendKeys(ReadExcelData.getInstance("Headerinfo").getStringValue("Supplier"));
			System.out.println("Supplier");

			PCDriver.visibilityOfListLocated(select);
			System.out.println("Select list visible");

			for (WebElement selectsupplier : select) {
				PCDriver.waitForElementToBeClickable(selectsupplier);
				System.out.println("Supplier Name" + selectsupplier.getText());
				Assert.assertTrue(selectsupplier.getText()
						.contains(ReadExcelData.getInstance("Headerinfo").getStringValue("Supplier")));
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
				}
				selectsupplier.click();
			}
			PCDriver.waitForElementToBeClickable(invoiceno);
			invoiceno.sendKeys(
					ReadExcelData.getInstance("Headerinfo").getStringValue("Invoice No") + System.currentTimeMillis());
			ReadExcelData.getInstance("Headerinfo").updateCellValue("supplier Invoice no",
					invoiceno.getAttribute("value"));
			PCDriver.waitForElementToBeClickable(receivedate);
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
			String expectedtitle = "PO/Line Data";
			String actualtitle = PCDriver.getDriver().getTitle();
			Assert.assertTrue(actualtitle.contains(expectedtitle));
			Findpo.click();
			PCDriver.switchToFrame(frame);
			PCDriver.waitForElementToBeClickable(search);
			search.clear();
			search.sendKeys(ReadExcelData.getInstance("Attachment").getStringValue("Item"));
			searchbtn.click();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e2) {
			}
			PCDriver.visibilityOfListLocated(drop);
			drop.get(0).click();
			PCDriver.waitForElementToBeClickable(checkall);
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e1) {
			}
			checkall.click();
			PCDriver.waitForElementToBeClickable(savebtn);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
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
			PCDriver.visibilityOfListLocated(quantity);
			quantity.get(1).click();
			quantity.get(1).sendKeys(Keys.chord(Keys.CONTROL, "a"));
			quantity.get(1).sendKeys(Keys.BACK_SPACE);
			quantity.get(1).sendKeys(ReadExcelData.getInstance("Attachment").getStringValue("Quantityofitem1"));
			PCDriver.waitForElementToBeClickable(nxt);
			nxt.click();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void attachment() {
		try {
			PCDriver.waitForPageLoad();
			String expectedtitle = "WebProcure: Upload Documents";
			String actualtitle = PCDriver.getDriver().getTitle();
			Assert.assertTrue(actualtitle.contains(expectedtitle));
			adddoc.click();
			// PCDriver.waitForElementToBeClickable(browse);
			// browse.click();
			try {
				// PCDriver.uploadFile(ReadExcelData.getInstance("Attachment").getStringValue("Filename"));
				uploadFile.sendKeys(ReadExcelData.getInstance("Attachment").getStringValue("Filename"));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			purpose.sendKeys(ReadExcelData.getInstance("Attachment").getStringValue("Justification"));

			uploadfile.click();
			PCDriver.waitForElementToBeClickable(nxtclick);
			try {
				Thread.sleep(7000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			nxtclick.click();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void match() {
		String expectedtitle = "WebProcure: Invoice Item Matching";
		String actualtitle = PCDriver.getDriver().getTitle();
		Assert.assertTrue(actualtitle.contains(expectedtitle));
		PCDriver.waitForElementToBeClickable(matchall);
		matchall.click();
		nxtclick2.click();

	}

	public void invoiceSummary() {
		String expectedtitle = "WebProcure: Invoice Summary";
		String actualtitle = PCDriver.getDriver().getTitle();
		Assert.assertTrue(actualtitle.contains(expectedtitle));
		((JavascriptExecutor) PCDriver.getDriver()).executeScript("arguments[0].scrollIntoView(true);", submit);
		submit.click();
	}

	/********* Receive Date Alert Message ***********/

	public void receiveDate() {
		try {
			String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
			PCDriver.waitForElementToBeClickable(createnew);
			createnew.click();
			supplier.sendKeys(ReadExcelData.getInstance("DateAlert").getStringValue("Supplier"));
			supplier.clear();
			try {
				PCDriver.waitForElementToBeClickable(pendu);
				pendu.click();
			} catch (Exception e) {
				System.out.println("Popup not present");
			}
			supplier.sendKeys(ReadExcelData.getInstance("DateAlert").getStringValue("Supplier"));

			PCDriver.visibilityOfListLocated(select);
			for (WebElement selectsupplier : select) {
				PCDriver.waitForElementToBeClickable(selectsupplier);
				Assert.assertTrue(selectsupplier.getText()
						.contains(ReadExcelData.getInstance("DateAlert").getStringValue("Supplier")));
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
				}
				selectsupplier.click();
			}

			PCDriver.waitForElementToBeClickable(invoiceno);
			invoiceno.sendKeys(
					ReadExcelData.getInstance("DateAlert").getStringValue("Invoice No") + System.currentTimeMillis());
			PCDriver.selectFromDropDownByVisibleText(payindicator,
					(ReadExcelData.getInstance("DateAlert").getStringValue("Pay Indicator")));
			receivedate.sendKeys(ReadExcelData.getInstance("DateAlert").getStringValue("Receive Date"));
			postdate.sendKeys(ReadExcelData.getInstance("DateAlert").getStringValue("Post Date"));
			issuedate.sendKeys(ReadExcelData.getInstance("DateAlert").getStringValue("Issue Date"));
			duedate.sendKeys(ReadExcelData.getInstance("DateAlert").getStringValue("Due Date"));
			memo.sendKeys(ReadExcelData.getInstance("DateAlert").getStringValue("Memo"));
			PCDriver.waitForElementToBeClickable(eft);
			PCDriver.selectFromDropDownByVisibleText(eft,
					(ReadExcelData.getInstance("DateAlert").getStringValue("EFT")));
			nxtbtn.click();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
			}
		} catch (IOException e) {

		}
	}

	/********* EFT Mandatory Alert ***********/

	public void mandatoryEFT() {
		try {
			String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
			PCDriver.waitForElementToBeClickable(createnew);
			createnew.click();
			supplier.sendKeys(ReadExcelData.getInstance("DateAlert").getStringValue("Supplier"));
			supplier.clear();
			try {
				PCDriver.waitForElementToBeClickable(pendu);
				pendu.click();
			} catch (Exception e) {
				System.out.println("Popup not present");
			}
			supplier.sendKeys(ReadExcelData.getInstance("DateAlert").getStringValue("Supplier"));

			PCDriver.visibilityOfListLocated(select);
			for (WebElement selectsupplier : select) {
				PCDriver.waitForElementToBeClickable(selectsupplier);
				Assert.assertTrue(selectsupplier.getText()
						.contains(ReadExcelData.getInstance("DateAlert").getStringValue("Supplier")));
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
				}
				selectsupplier.click();
			}
			PCDriver.waitForElementToBeClickable(invoiceno);
			invoiceno.sendKeys(
					ReadExcelData.getInstance("DateAlert").getStringValue("Invoice No") + System.currentTimeMillis());
			PCDriver.selectFromDropDownByVisibleText(payindicator,
					(ReadExcelData.getInstance("DateAlert").getStringValue("Pay Indicator")));
			receivedate.sendKeys(ReadExcelData.getInstance("DateAlert").getStringValue("Receive Date"));
			postdate.sendKeys(ReadExcelData.getInstance("DateAlert").getStringValue("Post Date"));
			issuedate.sendKeys(ReadExcelData.getInstance("DateAlert").getStringValue("Issue Date"));
			duedate.sendKeys(ReadExcelData.getInstance("DateAlert").getStringValue("Due Date"));
			memo.sendKeys(ReadExcelData.getInstance("DateAlert").getStringValue("Memo"));
			nxtbtn.click();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/********* Invoice No. Already Registered ***********/

	public void updateValue() {
		try {
			createnew.click();
			supplier.sendKeys(ReadExcelData.getInstance("Headerinfo").getStringValue("Supplier"));
			supplier.clear();
			try {
				PCDriver.waitForElementToBeClickable(pendu);
				pendu.click();
			} catch (Exception e) {
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
				}
				selectsupplier.click();
			}
			PCDriver.waitForElementToBeClickable(invoiceno);
			invoiceno.sendKeys(ReadExcelData.getInstance("Headerinfo").getStringValue("supplier Invoice no"));
			receivedate.sendKeys(ReadExcelData.getInstance("Headerinfo").getStringValue("Receive Date"));
			postdate.sendKeys(ReadExcelData.getInstance("Headerinfo").getStringValue("Post Date"));
			issuedate.sendKeys(ReadExcelData.getInstance("Headerinfo").getStringValue("Issue Date"));
			duedate.sendKeys(ReadExcelData.getInstance("Headerinfo").getStringValue("Due Date"));
			PCDriver.waitForElementToBeClickable(eft);
			PCDriver.selectFromDropDownByVisibleText(eft,
					(ReadExcelData.getInstance("Headerinfo").getStringValue("EFT")));
			nxtbtn.click();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
			}
		} catch (IOException e) {

		}

	}

	/********* Invoice NO. Missing ***********/

	public void invoiceNo() {
		try {
			createnew.click();
			supplier.sendKeys(ReadExcelData.getInstance("Headerinfo").getStringValue("Supplier"));
			supplier.clear();
			try {
				PCDriver.waitForElementToBeClickable(pendu);
				pendu.click();
			} catch (Exception e) {
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
				}
				selectsupplier.click();
			}
			receivedate.sendKeys(ReadExcelData.getInstance("Headerinfo").getStringValue("Receive Date"));
			postdate.sendKeys(ReadExcelData.getInstance("Headerinfo").getStringValue("Post Date"));
			issuedate.sendKeys(ReadExcelData.getInstance("Headerinfo").getStringValue("Issue Date"));
			duedate.sendKeys(ReadExcelData.getInstance("Headerinfo").getStringValue("Due Date"));
			PCDriver.waitForElementToBeClickable(eft);
			PCDriver.selectFromDropDownByVisibleText(eft,
					(ReadExcelData.getInstance("Headerinfo").getStringValue("EFT")));
			nxtbtn.click();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
			}
		} catch (IOException e) {

		}

	}

	/********* PO Search Error Message ***********/
	public void posearch() {
		try {
			String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
			PCDriver.waitForPageLoad();
			createnew.click();
			supplier.sendKeys(ReadExcelData.getInstance("DiffSupplier").getStringValue("Supplier"));
			supplier.clear();
			try {
				PCDriver.waitForElementToBeClickable(pendu);
				pendu.click();
			} catch (Exception e) {
				System.out.println("Popup not present");
			}
			supplier.sendKeys(ReadExcelData.getInstance("DiffSupplier").getStringValue("Supplier"));

			PCDriver.visibilityOfListLocated(select);
			for (WebElement selectsupplier : select) {
				PCDriver.waitForElementToBeClickable(selectsupplier);
				System.out.println("Supplier Name" + selectsupplier.getText());
				Assert.assertTrue(selectsupplier.getText()
						.contains(ReadExcelData.getInstance("DiffSupplier").getStringValue("Supplier")));
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
				}
				selectsupplier.click();
			}
			PCDriver.waitForElementToBeClickable(invoiceno);
			invoiceno.sendKeys(ReadExcelData.getInstance("DiffSupplier").getStringValue("Invoice No")
					+ System.currentTimeMillis());
			receivedate.sendKeys(ReadExcelData.getInstance("DiffSupplier").getStringValue("Receive Date"));
			postdate.sendKeys(ReadExcelData.getInstance("DiffSupplier").getStringValue("Post Date"));
			issuedate.sendKeys(ReadExcelData.getInstance("DiffSupplier").getStringValue("Issue Date"));
			duedate.sendKeys(ReadExcelData.getInstance("DiffSupplier").getStringValue("Due Date"));

			PCDriver.waitForElementToBeClickable(eft);
			PCDriver.selectFromDropDownByVisibleText(eft,
					(ReadExcelData.getInstance("DiffSupplier").getStringValue("EFT")));
			nxtbtn.click();
			Findpo.click();
			PCDriver.switchToFrame(frame);
			PCDriver.waitForElementToBeClickable(search);
			search.clear();
			search.sendKeys(ReadExcelData.getInstance("DiffSupplier").getStringValue("PO"));
			searchbtn.click();
		} catch (IOException e) {

		}
	}

	public void zeroquantity() {
		try {
			Findpo.click();
			PCDriver.switchToFrame(frame);
			PCDriver.waitForElementToBeClickable(search);
			search.clear();
			search.sendKeys(ReadExcelData.getInstance("Attachment").getStringValue("PO1"));
			searchbtn.click();
			PCDriver.visibilityOfListLocated(drop);
			drop.get(0).click();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			checkall.click();
			savebtn.click();
			//PCDriver.waitForElementToDisappear(By.xpath("//button[@class='btn btn-wp savebutton']"));
			PCDriver.switchToDefaultContent();
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				PCDriver.waitForElementToBeClickable(nxt);
				nxt.click();
			}catch (Exception e) {

			}
			try {
				PCDriver.waitForElementToBeClickable(vinvoice.nxt);
				vinvoice.nxt.click();
			} catch (Exception e) {

			}
			

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void searchfa() {
		try {
			Findpo.click();
			PCDriver.switchToFrame(frame);
			PCDriver.waitForElementToBeClickable(search);
			search.clear();
			search.sendKeys(ReadExcelData.getInstance("Attachment").getStringValue("PO"));
			searchbtn.click();
			PCDriver.visibilityOfListLocated(drop);
			drop.get(0).click();
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e1) {
			}
			checkall.click();
			savebtn.click();
			PCDriver.switchToDefaultContent();
			PCDriver.waitForElementToBeClickable(fa);
			fa.sendKeys(ReadExcelData.getInstance("Attachment").getStringValue("fainput"));
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			PCDriver.waitForElementToBeClickable(faSearch);
			faSearch.click();
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
			}
			Assert.assertTrue(fixCodeSearch().contains("Select Item Category"));
			PCDriver.waitForElementToBeClickable(closebtn);
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
			}
			closebtn.click();
			} catch (IOException e) {
			
		}
	}

	/********* Alert Message Box ***********/

	public String getAlert() {
		System.out.println(Alertmsg.getText());
		return Alertmsg.getText();
	}

	/********* Attachment Error Message ***********/

	public void attachementalert() {
		adddoc.click();
		nxtclick.click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
		}
	}

	public String attachAlert() {
		System.out.println(attach.getText());
		return attach.getText();
	}

	public String suppname() {
		return invsuppname.getText();
	}

	public String fixCodeSearch() {
		System.out.println(titlefa.getText());
		return titlefa.getText();
	}

	public String poSelect() {
		return POdata.getText();
	}

}
