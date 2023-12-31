package buyer.pageobjects.solicitationPageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commonutils.pageobjects.utils.PCDriver;
import commonutils.pageobjects.utils.ReadExcelData;
import commonutils.pageobjects.utils.ssnAndFeinGenerator;

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

	@FindBy(xpath = "//span[@class='fa fa-home fa-lg']")
	public WebElement btnHome;
	
	@FindBy(xpath = "//i[@class='fa fa-home fa-lg']")
	public WebElement vbtnHome;

	@FindBy(xpath = "//div[@class='alert alert-info']")
	public WebElement txtSubmitMessage;

	@FindBy(xpath = "//button[text()='Exit']")
	public WebElement btnExit;

	@FindBy(xpath = "//section[@id='page-title']/h3")
	public WebElement verifyPage;

	@FindBy(xpath = "(//div[@class='panel-body'])[3]")
	public WebElement addInfoSection;

	@FindBy(xpath = "//div[@class='bootbox modal fade bootbox-alert in']")
	public WebElement popUpAdjustment;

	@FindBy(xpath = "//h4[contains(text(),'Info') and not(contains(text(),'Header'))]")
	public WebElement pageSubmit;

	@FindBy(xpath = "//div[@class='alert alert-danger']")
	public WebElement verifyGroupNameMessage;

	public boolean verifyGroupNameNotEmpty() {
		PCDriver.waitForElementToBeClickable(verifyGroupNameMessage);
		System.out.println(verifyGroupNameMessage.getText());
		if (verifyGroupNameMessage.getText().contains("The group name is required.")) {
			PCDriver.switchToWindow("");
			return true;
		} else {
			PCDriver.switchToWindow("");

			return false;
		}
	}

	public void clickHomeButton() {
		PCDriver.switchToDefaultContent();
		PCDriver.waitForElementToBeClickable(btnHome);
		((JavascriptExecutor)PCDriver.getDriver()).executeScript("window.confirm = function(msg){return true;}");

		btnHome.click();
	}
	public void clickvendorHomeButton() {
		PCDriver.switchToDefaultContent();
		PCDriver.waitForElementToBeClickable(vbtnHome);
		((JavascriptExecutor)PCDriver.getDriver()).executeScript("window.confirm = function(msg){return true;}");

		vbtnHome.click();
	}

	public void waitForAddInfoSection() {
		try {
		PCDriver.waitForElementToBeClickable(addInfoSection);
		}
		catch(Exception e) {
			System.out.println("AddInfo Section is not displayed");
		}
	}

	public boolean verifyPageTitle(String str) {
		try {
			PCDriver.acceptAlert();
		} catch (Exception e) {
			System.out.println("No alert Present");
		}
		PCDriver.waitForElementToBeClickable(verifyPage);
		if (verifyPage.getText().contains(str)) {
			return true;
		} else {
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
		} catch (Exception e) {

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
		PCDriver.waitForPageLoad();
		item.CreateNewItem(index, strItem);

	}

	public boolean AddLineItemsAndVerify(String quant, String strCategory) {

		item.enterQuantity(quant, strCategory);
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
			((JavascriptExecutor) PCDriver.getDriver()).executeScript("window.confirm =function(){return true;}");
			// PCDriver.acceptAlert();
		} catch (Exception e) {
			System.out.println("Alert not present");
		}
		supplier.searchSupplier("apple");
		supplier.selectSearchedSuppliers();

	}

	public String clickOnNextStep() {
		PCDriver.waitForPageLoad();
		PCDriver.waitForElementToBeClickable(btnNextStep);
		btnNextStep.click();
		String msg = null;
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			supplier.AcceptSupplierAlert();

			// msg=(String) ((JavascriptExecutor)PCDriver.getDriver()).executeScript("return
			// window.alert.getText;");
			// msg = PCDriver.getDriver().switchTo().alert().getText();
			// PCDriver.getDriver().switchTo().alert().accept();
			// ((JavascriptExecutor)PCDriver.getDriver()).executeScript("window.confirm =
			// function(){return true;}");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}

	public void clickSubmit() {
		try {
			// Thread.sleep(10000);
			PCDriver.waitForPageLoad();
			// WebElement
			// ele=PCDriver.getDriver().findElement(By.cssSelector("button.btn:nth-child(4)"));

			PCDriver.waitForElementToBeClickable(btnSubmit);
			/*
			 * Actions builder = new Actions(PCDriver.getDriver());
			 * builder.moveToElement(ele).click(ele); builder.perform();
			 */
			((JavascriptExecutor) PCDriver.getDriver())
					.executeScript("window.confirm = function(msg) { return true; }");

			btnSubmit.click();

			System.out.println("Submit button is clicked");
			// PCDriver.waitForElementToDisappear(By.xpath("//button[contains(text(),'Submit')]"));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Submit button not clicked");
		}
		try {
			// PCDriver.getDriver().switchTo().alert().accept();

			System.out.println("Alert is present");

		} catch (Exception e) {
			System.out.println("Alert not present");
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
		PCDriver.waitForPageLoad();
		PCDriver.waitForElementToBeClickable(pageSubmit);
		PCDriver.waitForElementToBeClickable(txtSubmitMessage);
		System.out.println(txtSubmitMessage.getText());
		return txtSubmitMessage.getText();
	}

	public void clickExit() {
		waitForAddInfoSection();
		PCDriver.waitForElementToBeClickable(btnExit);
		((JavascriptExecutor) PCDriver.getDriver()).executeScript("arguments[0].scrollIntoView(true);", btnExit);
		((JavascriptExecutor) PCDriver.getDriver()).executeScript("window.confirm = function(msg) { return true; }");

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		btnExit.click();
	}

	public void createNewGroup(String str) {
		item.CreateGroup(str);
		// PCDriver.waitForPageLoad();
	}

	public void switchOnAdjustmentHandler() {
		PCDriver.waitForPageLoad();
		item.clickOnAdjustmentHandler();
		try {
			PCDriver.waitForElementToBeClickable(popUpAdjustment, Long.valueOf(String.valueOf(15)));
			PCDriver.getDriver().findElement(By.xpath("//button[contains(text(),'OK')]")).click();
		} catch (Exception e) {
			System.out.println("Adjustment Popup is not opened");
		}
	}

	public void waitForItemSpecLibraryLinkToDisappear() {
		PCDriver.switchToWindow("");
		try {
			PCDriver.waitForElementToDisappear(By.xpath("//a[text()='Item Spec Library']"));
		} catch (Exception e) {
			System.out.println("Item Spec Library did not disapper");
		}
	}
}
