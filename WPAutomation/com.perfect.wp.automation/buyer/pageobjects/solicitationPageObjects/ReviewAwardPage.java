package pageobjects.solicitationPageObjects;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pageobjects.utils.PCDriver;

public class ReviewAwardPage {
	EditSolicitationPageObject edit = new EditSolicitationPageObject();

	public ReviewAwardPage() {
		PageFactory.initElements(PCDriver.getDriver(), this);

	}

	@FindBy(xpath = "//ul[@class='dropdown-menu dropdown-menu-right']")
	public WebElement lnkThreeDotsMenu;

	@FindBy(id = "paper")
	public WebElement radioButtonPaper;

	@FindBy(id = "fields")
	public WebElement radioButtonReviewRequirements;

	@FindBy(id = "docs")
	public WebElement radioButtonReviewVendorDocs;

	@FindBy(id = "item")
	public WebElement radioButtonAward;

	@FindBy(xpath = "//button[text()='Continue']")
	public WebElement btnContinue;

	@FindBy(id = "sname")
	public WebElement txtSName;

	@FindBy(xpath = "//button[text()='Search']")
	public WebElement btnSearch;

	@FindBy(xpath = "//input[@name='vendorid']")
	public WebElement lstRadioButton;

	@FindBy(xpath = "//td/input[@type='checkbox']")
	public WebElement chkBoxLineItem;

	@FindBy(xpath = "//button[contains(text(),'Done')]")
	public WebElement btnDone;

	@FindBy(xpath = "//img[@alt='Collapse']")
	public WebElement popupAwardSummary;

	@FindBy(xpath = "//img[contains(@title,'some') or contains(@title,'none')]/following-sibling::a")
	public List<WebElement> lstGroupItems;

	@FindBy(xpath = "//button[text()='Cancel'][@class='btn btn-default']")
	public WebElement btnCancel;

	@FindBy(id = "group")
	public WebElement radioButonAwardGroup;

	@FindBy(id = "vendor")
	public WebElement radioButtonAwardAllToOne;

	@FindBy(xpath = "//span[@class='bootstrap-switch-handle-on bootstrap-switch-success']")
	public WebElement awardToggleBox;

	@FindBy(xpath = "//h3[contains(text(),'Award All to One')]")
	public List<WebElement> pageAwardAllToOne;

	@FindBy(xpath = "//h3[contains(text(),'Evaluate and Award')]")
	public List<WebElement> pageEvaluateAndAward;

	@FindBy(xpath = "//button[text()='Close']")
	public WebElement btnClose;
	
	/******************************************
	 * Create New Round
	 ********************************************/
	

	@FindBy(xpath="//td/input[not(contains(@id,'selectall'))]")
	public List<WebElement> lstSupplierOnPopup;
	
	@FindBy(xpath="//div[contains(@class,'modal-dialog modal-lg')]")
	public WebElement modalDialog;
	
	@FindBy(xpath="//button[contains(text(),'Submit')]")
	public WebElement btnSubmit;
	
	@FindBy(xpath="//iframe")
	public WebElement iframeNewRound;
	
	@FindBy(xpath="(//div[@class='modal-dialog'])[2]")
	public WebElement modalDialogRoundReason;
	
	@FindBy(xpath="//textarea")
	public WebElement txtReason;
	
	@FindBy(xpath="//button[text()='Ok']")
	public WebElement btnOk;
	
	@FindBy(xpath="//button[contains(text(),'Create New Round')]")
	public WebElement btnCreateNewRound;
	
	@FindBy(name = "p")
	public WebElement txConfirmText;

	public void clickOnPaperResponse() {
		PCDriver.waitForElementToBeClickable(radioButtonPaper);
		radioButtonPaper.click();
	}

	public void clickAwardSummaryPopUp() {
		PCDriver.waitForElementToBeClickable(popupAwardSummary);
		popupAwardSummary.click();
	}

	public void checkLineItem() {
		PCDriver.waitForElementToBeClickable(chkBoxLineItem);
		chkBoxLineItem.click();

	}


	public void clickCancel() {
		PCDriver.waitForElementToBeClickable(btnCancel);
		btnCancel.click();
	}
	
	public void clickCreateNewRoundButton() {
		PCDriver.waitForElementToBeClickable(btnCreateNewRound);
		btnCreateNewRound.click();
	}

	public void clickOnToggleBox() {
		PCDriver.waitForElementToBeClickable(awardToggleBox);
		awardToggleBox.click();
	}

	public boolean verifyCheckBox() {
		if (PCDriver.getDriver().findElements(By.xpath("//td/input[@type='checkbox']")).size() == 0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean verifyAwardAllToOnePage() {
		if (pageAwardAllToOne.size() == 0) {
			return false;
		} else {
			return true;
		}
	}

	public boolean verifyEvaluateAndAwardPage() {
		PCDriver.waitForPageLoad();
		System.out.println("size is:" + pageEvaluateAndAward.size());
		if (pageEvaluateAndAward.size() != 0) {
			return true;
		} else {
			return false;
		}
	}

	public void clickClose() {
		try {
			PCDriver.acceptAlert();
		} catch (Exception e) {

		}
		PCDriver.waitForElementToBeClickable(btnClose);
		btnClose.click();
	}

	public void selectLineItemAndEnterQuantity(String strQauntity) {
		PCDriver.waitForElementToDisappear(By.xpath("//div[@id='saveMessage']"));
		PCDriver.waitForElementToBeClickable(chkBoxLineItem);
		((JavascriptExecutor) PCDriver.getDriver()).executeScript("arguments[0].scrollIntoView(true);", chkBoxLineItem);

		PCDriver.waitForElementToBeClickable(chkBoxLineItem);
		chkBoxLineItem.click();
		PCDriver.waitForElementToDisappear(By.xpath("//div[@id='saveMessage']"));
		try {
			chkBoxLineItem.findElement(By.xpath("./parent::td/following-sibling::td//div/input")).clear();
			chkBoxLineItem.findElement(By.xpath("./parent::td/following-sibling::td//div/input")).sendKeys(strQauntity);
		} catch (Exception e) {
			// td/input[@type='checkbox']
		}
	}

	public void clickOnAwardByItem() {
		PCDriver.waitForElementToBeClickable(radioButtonAward);
		radioButtonAward.click();
	}

	public void clickAwardByGroup() {
		PCDriver.waitForElementToBeClickable(radioButonAwardGroup);
		((JavascriptExecutor) PCDriver.getDriver()).executeScript("arguments[0].scrollIntoView(true);",
				radioButonAwardGroup);
		radioButonAwardGroup.click();
	}

	public void clickAwardAllToOneForBid() {
		PCDriver.waitForElementToBeClickable(radioButtonAwardAllToOne);
		((JavascriptExecutor) PCDriver.getDriver()).executeScript("arguments[0].scrollIntoView(true);",
				radioButtonAwardAllToOne);

		radioButtonAwardAllToOne.click();

	}

	public void clickDone() {
		PCDriver.waitForElementToDisappear(By.xpath("//div[@id='saveMessage']"));

		PCDriver.waitForElementToBeClickable(btnDone);
		btnDone.click();
	}

	public void searchSupplier(String strSupplier) {
		PCDriver.waitForElementToBeClickable(txtSName);
		txtSName.sendKeys(strSupplier);
		btnSearch.click();
	}

	public void clickSupplierAndContinue() {
		PCDriver.waitForElementToBeClickable(lstRadioButton);
		lstRadioButton.click();
		btnContinue.click();
	}

	public void clickGroupsAndAddItems() {
		PCDriver.waitForPageLoad();
		for (int i = 0; i < lstGroupItems.size(); i++) {
			try {
				lstGroupItems.get(i).click();
				PCDriver.acceptAlert();

			} catch (Exception e) {

			} finally {
				checkLineItem();
				try {
					PCDriver.acceptAlert();
				} catch (Exception e) {

				}
				clickDone();

			}
		}
	}

	public void clickContinue() {
		PCDriver.waitForElementToBeClickable(btnContinue);
		((JavascriptExecutor) PCDriver.getDriver()).executeScript("arguments[0].scrollIntoView(true);", btnContinue);
		btnContinue.click();

	}
	
	public void selectSuppliersOnPopup() {
		PCDriver.waitForElementToBeClickable(modalDialog);
		for(int i=0;i<5;i++) {
			PCDriver.waitForElementToBeClickable(lstSupplierOnPopup.get(i));
			lstSupplierOnPopup.get(i).click();
		}
	}

	public void ThreeDotsMenu(String str) {
		PCDriver.waitForElementToBeClickable(lnkThreeDotsMenu);
		lnkThreeDotsMenu.findElement(By.xpath("//a[contains(@href,'"+str+"')]")).click();
	}

	public boolean verifyData() throws ParseException {
		List<WebElement> lstEndDate = new ArrayList<WebElement>();
		for (int i = 0; i < edit.lstsearchResultsForEndDate.size(); i++) {
			lstEndDate.add(edit.lstsearchResultsForEndDate.get(i).findElement(By.xpath("./preceding-sibling::td[1]")));

		}

		return edit.VerifyEndDateTime(lstEndDate);

	}
	
	public void setConfirmTextOnFinalizePage(String strText) {
		PCDriver.waitForElementToBeClickable(txConfirmText);
		txConfirmText.sendKeys(strText);
	}

	public void clickSubmit() {
		PCDriver.waitForElementToBeClickable(btnSubmit);
		btnSubmit.click();
	}
	
	public void enterReason() {
		try {
		PCDriver.waitForElementToBeClickable(modalDialogRoundReason);
		PCDriver.switchToFrame(iframeNewRound);
		PCDriver.waitForElementToBeClickable(txtReason);
		}
		catch(Exception e) {
			System.out.println("No Element present");
		}
		finally {
		txtReason.sendKeys("ablkddddddddd");
		}
		try {
		btnOk.click();
		PCDriver.switchToDefaultWindow();
		}
		catch(Exception e) {
			System.out.println("Ok Button not present");
		}
	}

	
}
