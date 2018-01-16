package pageobjects.solicitationPageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
	HeaderPage header = new HeaderPage();
	RequirementsPage req = new RequirementsPage();
	ItemSpecPage item = new ItemSpecPage();




	public CreateSolicitationPOM() {

		PageFactory.initElements(PCDriver.getDriver(), this);
	}

	@FindBy(xpath = "//button[text()='Next Step']")
	public WebElement btnNextStep;

	@FindBy(xpath = "//button[text()='Close']")
	public WebElement btnCloseOnCategoryPopUp;

	@FindBy(xpath = "//button[contains(text(),'Submit')]")
	public WebElement btnSubmit;

	@FindBy(xpath = "//i[@class='fa fa-home fa-lg']")
	public WebElement btnHome;

	@FindBy(xpath = "//div[@class='alert alert-info']")
	public WebElement txtSubmitMessage;
	
	@FindBy(xpath="//button[text()='Exit']")
	public WebElement btnExit;
	
	
	
	@FindBy(xpath="//section[@id='page-title']/h3")
	public WebElement verifyPage;
	
	@FindBy(xpath="(//div[@class='panel-body'])[3]")
	public WebElement addInfoSection;
	
	@FindBy(xpath="//h4[contains(text(),'Info') and not(contains(text(),'Header'))]")
	public WebElement pageSubmit;
	
	@FindBy(xpath= "//div[@class='alert alert-danger']")
	public WebElement verifyGroupNameMessage;
	
	public boolean  verifyGroupNameNotEmpty() {
		PCDriver.waitForElementToBeClickable(verifyGroupNameMessage);
		System.out.println(verifyGroupNameMessage.getText());
		if(verifyGroupNameMessage.getText().contains("The group name is required.")) {
			PCDriver.switchToWindow("");
			return true;
		}else {
			PCDriver.switchToWindow("");

			return false;
		}
	}


	public void clickHomeButton() {
		PCDriver.waitForElementToBeClickable(btnHome);
		btnHome.click();
	}
	
	public void waitForAddInfoSection() {
		PCDriver.waitForElementToBeClickable(addInfoSection);
	}
	
	public boolean verifyPageTitle(String str) {
		try {
		PCDriver.acceptAlert();
		}
		catch(Exception e) {
			System.out.println("No alert Present");
		}
		PCDriver.waitForElementToBeClickable(verifyPage);
		if(verifyPage.getText().contains(str)) {
			return true;
		}else {
			return false;
			
		}
	}

	public void EnterHeaderDetails() throws Exception {
		header.setTitle(ReadExcelData.getInstance("Solicitation").getStringValue("Title"));
		header.setSolType();
		header.setEstimatedTotalValue(ReadExcelData.getInstance("Solicitation").getStringValue("EstimatedTotalValue"));
		header.selectNoLineItemCheckBox();
		header.clickAndSelectCategory("Apparel");
		header.clickCloseOnCategoryPopUp();
	
	}

	public void waitForDivToAppearInReqPage() {
		try {
		req.waitForDivToAppear();
		Thread.sleep(4000);
		}
		catch(Exception e) {
			
		}
	}

	public void EnterQuestionnaire() {
		ques.verifyQuestionPage();
		ques.EnterQuestionDetails();
	}

	public void uploadDocFromLibrary() {
		
		attach.clickUploadDocFromLibrary();
		attach.uploadDocFromLibrary();
	}

	public void uploadNewDocument() {
		attach.clickAddNewDoc();
		attach.uploadNewDocument();
	}

	public void createLineItem(int index, String strItem) {
		item.CreateNewItem(index, strItem);

	}

	public boolean AddLineItemsAndVerify(String quant,String strCategory) {

		item.enterQuantity(quant,strCategory);
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
			supplier.setCompanyPhoneNumber("8483929257");
			supplier.setEmailAddress("abc@gmail.com");
			supplier.setConfirmEmailAddress("abc@gmail.com");
			supplier.setCompanyFaxNumber("8483839483");
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
			System.out.println("Submit button or alert not present");
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

	

	public String verifySuccessMessage() {
		PCDriver.waitForElementToBeClickable(pageSubmit);
		return txtSubmitMessage.getText();
	}
	
	public void clickExit() {
		waitForAddInfoSection();
		PCDriver.waitForElementToBeClickable(btnExit);
		((JavascriptExecutor) PCDriver.getDriver()).executeScript("arguments[0].scrollIntoView(true);",
				btnExit);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		btnExit.click();
	}

	public void createNewGroup(String str) {
		item.CreateGroup(str);
		PCDriver.waitForPageLoad();
	}
	
	public void switchOnAdjustmentHandler() {
		PCDriver.waitForPageLoad();
		item.clickOnAdjustmentHandler();
	}
	public void waitForItemSpecLibraryLinkToDisappear() {
		PCDriver.switchToWindow("");
		try {
		PCDriver.waitForElementToDisappear(By.xpath("//a[text()='Item Spec Library']"));
		}
		catch(Exception e) {
			System.out.println("Item Spec Library did not disapper");
		}
	}
}
