package pageobjects.solicitationPageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pageobjects.generic.ssnAndFeinGenerator;
import pageobjects.utils.PCDriver;
import pageobjects.utils.ReadExcelData;

public class CreateSolicitationPOM {
	SupplierPage supplier = new SupplierPage();
	AttachmentPage attach = new AttachmentPage();
	QuestionnairePage ques = new QuestionnairePage();

	public CreateSolicitationPOM() {

		PageFactory.initElements(PCDriver.getDriver(), this);
	}

	@FindBy(xpath = "//button[text()='Next Step']")
	public WebElement btnNextStep;

	@FindBy(xpath = "//button[text()='Close']")
	public WebElement btnCloseOnCategoryPopUp;

	@FindBy(xpath = "//button[text()='Submit']")
	public WebElement btnSubmit;

	@FindBy(xpath = "//button[text()='Create New Item']")
	public WebElement btnCreateNewItem;

	@FindBy(xpath = "//span[@class='fa fa-home fa-lg']")
	public WebElement btnHome;

	@FindBy(xpath = "//div[@class='alert alert-info']/text()")
	public WebElement txtSubmitMessage;

	public void clickHomeButton() {
		PCDriver.waitForElementToBeClickable(btnHome);
		btnHome.click();
	}

	public void EnterHeaderDetails() throws Exception {
		HeaderPage header = new HeaderPage();
		header.setTitle(ReadExcelData.getInstance("Solicitation").getStringValue("Title"));
		header.setSolType();
		header.setEstimatedTotalValue(ReadExcelData.getInstance("Solicitation").getStringValue("EstimatedTotalValue"));
		header.selectNoLineItemCheckBox();
		header.clickAndSelectCategory("Apparel");
		header.clickCloseOnCategoryPopUp();
	}

	public void waitForDivToAppearInReqPage() {
		RequirementsPage req = new RequirementsPage();
		req.waitForDivToAppear();
	}

	public void EnterQuestionnaire() {
		ques.verifyQuestionPage();
		ques.EnterQuestionDetails();
	}

	public void uploadDocFromLibrary() {
		AttachmentPage attach = new AttachmentPage();
		attach.clickUploadDocFromLibrary();
		attach.uploadDocFromLibrary();
	}

	public void uploadNewDocument() {
		attach.clickAddNewDoc();
		attach.uploadNewDocument();
	}

	public void createLineItem(int index, String strItem) {
		itemSpecPage item = new itemSpecPage();
		item.CreateNewItem(index, strItem);

	}

	public boolean AddLineItemsAndVerify() {
		itemSpecPage item = new itemSpecPage();

		item.enterQuantity("10");
		return item.verifyItemSpec();

	}

	public void CreateSupplier() {
		supplier.AcceptSupplierAlert();
		try {
			supplier.CreateNewSupplier("abc");
			supplier.clickNewSupplier();
			supplier.setSupplierName("Apple1");
			supplier.setEnterpriceType("C Corporation");
			supplier.setDoingBusinessAs("abc");
			supplier.setMedicalOrLegalServiceProvider();
			supplier.setStateIncorporated("Alaska");
			supplier.setFirstName("qwe");
			supplier.setLasttName("tyu");
			supplier.setCompanyPhoneNumber("848392925764");
			supplier.setEmailAddress("abc@gmail.com");
			supplier.setConfirmEmailAddress("abc@gmail.com");
			supplier.setCompanyFaxNumber("848383948382");
			supplier.setCountry("United States");
			supplier.setAddress("abcdlf");
			supplier.setCity("Alaska");
			supplier.setState("Alaska");
			supplier.setOtherRegion("Alaska");
			supplier.setZipCode("12347");
			supplier.setFeinNumber(ssnAndFeinGenerator.FeinGenerator());
			supplier.setSsnNumber(ssnAndFeinGenerator.generateSSN());
			supplier.setDunsNumber(ssnAndFeinGenerator.dunsNumberGenerator());
			supplier.setExternalSupplierId("453434343434342");
			supplier.clickSave();
			supplier.clickClose();
		} catch (Exception e) {
			PCDriver.switchToWindow("");
		}
	}

	public void searchSupplier() {
		try {
			PCDriver.acceptAlert();
		} catch (Exception e) {
			System.out.println("Alert not present");
		}
		SupplierPage supplier = new SupplierPage();
		supplier.searchSupplier("apple");
		supplier.selectSearchedSuppliers();

	}

	public String clickOnNextStep() {
		PCDriver.waitForPageLoad();
		PCDriver.waitForElementToBeClickable(btnNextStep);
		btnNextStep.click();
		String msg = null;

		try {
			// msg=(String) ((JavascriptExecutor)PCDriver.getDriver()).executeScript("return
			// window.alert.getText;");
			msg = PCDriver.getDriver().switchTo().alert().getText();
			PCDriver.getDriver().switchTo().alert().accept();
			// ((JavascriptExecutor)PCDriver.getDriver()).executeScript("window.confirm =
			// function(){return true;}");

		} catch (Exception e) {

		}
		return msg;
	}

	public void clickSubmit() {
		try {
			PCDriver.waitForElementToBeClickable(btnSubmit);
			btnSubmit.click();
			PCDriver.getDriver().switchTo().alert().accept();
		} catch (Exception e) {
		}
	}

	private class RequirementsPage {
		@FindBy(xpath = "//div[@class='fieldTextDiv']")
		public WebElement reqDiv;

		public RequirementsPage() {

			PageFactory.initElements(PCDriver.getDriver(), this);
		}

		public void waitForDivToAppear() {
			PCDriver.waitForElementToBeClickable(reqDiv);
		}

	}

	private class AttachmentPage {
		@FindBy(xpath = "//button[text()='Upload Documents from Library']")
		public WebElement btnUploadDocFromLibrary;

		@FindBy(xpath = "//button[text()='Add new document']")
		public WebElement btnAddNewDoc;

		@FindBy(xpath = "//input[@type='checkbox']")
		List<WebElement> lstCheckBox;

		@FindBy(xpath = "//button[text()='Save']")
		public WebElement btnSave;

		@FindBy(xpath = "//span[@class='btn btn-wp btn-file']")
		public List<WebElement> btnBrowse;

		@FindBy(xpath = "//button[text()='Upload']")
		public WebElement btnUpload;

		public AttachmentPage() {

			PageFactory.initElements(PCDriver.getDriver(), this);
		}

		public void clickUploadDocFromLibrary() {
			PCDriver.waitForElementToBeClickable(btnUploadDocFromLibrary);
			btnUploadDocFromLibrary.click();
			PCDriver.waitForPageLoad();

		}

		public void clickAddNewDoc() {
			PCDriver.waitForElementToBeClickable(btnAddNewDoc);
			btnAddNewDoc.click();
			PCDriver.waitForPageLoad();
		}

		public void uploadNewDocument() {
			PCDriver.switchToWindow("puw_upload");
			PCDriver.waitForPageLoad();

			for (int i = 2; i < 5; i++) {
				btnBrowse.get(i - 2).findElement(By.xpath(".//input[@name='file" + i + "']"))
						.sendKeys("D:\\WebProcureData.xlsx");
			}
			btnUpload.click();
			PCDriver.switchToWindow("");

		}

		public void uploadDocFromLibrary() {
			PCDriver.switchToWindow("puw_librdoc");
			PCDriver.waitForPageLoad();

			for (int i = 0; i < 6; i++) {
				lstCheckBox.get(i).click();
			}
			btnSave.click();
			PCDriver.switchToWindow("");

		}
	}

	private class itemSpecPage {
		/************************* Page Objects Create Item ********************/
		@FindBy(xpath = "//a[text()='Item Spec Library']")
		public WebElement itemSpecLink;

		@FindBy(name = "name")
		public WebElement txtItemSpecName;

		@FindBy(name = "sic")
		public WebElement txtItemSpecNumber;

		@FindBy(name = "mnftr_name")
		public WebElement txtManufacturerName;

		@FindBy(name = "qty")
		public WebElement txtQuantity;

		@FindBy(xpath = "//button[contains(text(),'Save')]")
		public WebElement btnSave;

		@FindBy(xpath = "//table[@id='topCatTable']")
		public WebElement lnkCategory;

		@FindBy(xpath = "//table[@id='cattable']")
		public WebElement lineItemTab;

		@FindBy(xpath = "//button[text()='Add Items']")
		public WebElement btnAddItems;

		@FindBy(xpath = "//li[((contains(@class,'paginate-button next')))]//child::a[@class='nextButton']")
		public WebElement btnNextArrow;

		@FindBy(xpath = "//div[contains(@class,'ui-pnotify')]")
		public WebElement successMessage;

		@FindBy(xpath = "//div[@class='ui-pnotify-closer']")
		public WebElement successMessageClose;

		@FindBy(xpath = "//li[contains(@class,'paginate-button next pagination-btn-disabled')]")
		List<WebElement> checkNextButtonDisabled;

		@FindBy(xpath = "//a[text()='Browse Catalog']")
		public WebElement lnkBrowseCatalog;

		@FindBy(xpath = "//a[text()='Selected Item Specs']")
		public WebElement lnkSelectedSpecs;

		@FindBy(xpath = "(//tbody/tr/td[@align='center'])[1]/input")
		public List<WebElement> lstSelectedspes;

		/************************* Page Objects Add Item ************************/

		@FindBy(xpath = "//tbody//td[4]/input[contains(@class,'form-control')]")
		public List<WebElement> lstLineItems;

		public void enterQuantity(String quant) {
			clickItemSpecLibrary();
			newItemCategory("Apparel");
			PCDriver.waitForElementToBeClickable(lineItemTab);

			while (checkNextButtonDisabled.size() == 0) {
				for (int i = 0; i < lstLineItems.size(); i++) {
					try {
						PCDriver.waitForElementToBeClickable(lstLineItems.get(i));

						lstLineItems.get(i).sendKeys(quant);
					} catch (Exception e) {

					}
				}
				btnAddItems.click();
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				PCDriver.hoverOnElement(successMessage);
				PCDriver.waitForElementToBeClickable(successMessageClose);
				successMessageClose.click();
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				try {
					if (checkNextButtonDisabled.size() == 0) {
						PCDriver.waitForElementToBeClickable(btnNextArrow);
						btnNextArrow.click();
						Thread.sleep(2000);
					} else {
						break;
					}
				} catch (Exception e) {

				}
			}
		}

		public boolean verifyItemSpec() {
			lnkSelectedSpecs.click();
			if (lstSelectedspes.size() != 0) {
				return true;
			} else {
				return false;
			}
		}

		public itemSpecPage() {

			PageFactory.initElements(PCDriver.getDriver(), this);
		}

		public void newItemCategory(String strItemName) {
			PCDriver.waitForElementToBeClickable(
					lnkCategory.findElement(By.xpath("//a[contains(@data-catname,'" + strItemName + "')]")));
			lnkCategory.findElement(By.xpath("//a[contains(@data-catname,'" + strItemName + "')]")).click();
		}

		public void clickCreateNewItem() {
			PCDriver.waitForElementToBeClickable(btnCreateNewItem);
			btnCreateNewItem.click();
		}

		public void clickItemSpecLibrary() {
			PCDriver.waitForElementToBeClickable(itemSpecLink);
			PCDriver.waitForPageLoad();
			itemSpecLink.click();
			PCDriver.waitForPageLoad();
		}

		public void CreateNewItem(int index, String strItem) {
			clickItemSpecLibrary();

			newItemCategory(strItem);
			for (int i = 0; i < 2; i++) {
				clickCreateNewItem();
				PCDriver.switchToWindow("puw_Item_Add");
				PCDriver.waitForElementToBeClickable(txtItemSpecName);
				txtItemSpecName.sendKeys("abc" + System.currentTimeMillis());
				txtItemSpecNumber.sendKeys("100");
				txtManufacturerName.sendKeys("abcd");
				txtQuantity.sendKeys("10");
				btnSave.click();
				try {
					PCDriver.dismissAlert();
				} catch (Exception e) {
					System.out.println("No Alert Present");
				}
				PCDriver.switchToWindow("");
				PCDriver.waitForPageLoad();

			}
			lnkBrowseCatalog.click();

		}

	}

	public String verifySuccessMessage() {
		PCDriver.waitForPageLoad();
		return txtSubmitMessage.getText();
	}

}
