package buyer.pageobjects.solicitationPageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commonutils.pageobjects.utils.PCDriver;

public class ItemSpecPage {

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

	@FindBy(xpath = "//button[text()='Create New Item']")
	public WebElement btnCreateNewItem;

	@FindBy(xpath = "//button[text()='Create Group']")
	public WebElement btnCreateGroup;

	@FindBy(id = "name")
	public WebElement txtGroupName;

	@FindBy(xpath = "//span[contains(@class,'bootstrap-switch-handle-off bootstrap-switch-warning')]")
	public WebElement handlerAdjustment;
	
	@FindBy(xpath="//tbody/tr[@align='left']/td[text()='0']")
	public WebElement lblquantity;

	/************************* Page Objects Add Item ************************/

	@FindBy(xpath = "//tbody//td[4]/input[contains(@class,'form-control')]")
	public List<WebElement> lstLineItems;

	public void enterQuantity(String quant, String strCategory) {
		clickItemSpecLibrary();
		newItemCategory(strCategory);
		PCDriver.waitForElementToBeClickable(lineItemTab);

		while (checkNextButtonDisabled.size() == 0) {
			for (int i = 0; i < lstLineItems.size(); i++) {
				try {
					PCDriver.WaitTillElementIsPresent(lblquantity);
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
		PCDriver.waitForElementToBeClickable(lnkSelectedSpecs);
		lnkSelectedSpecs.click();
		PCDriver.waitForPageLoad();
		PCDriver.visibilityOfListLocated(lstSelectedspes);
		if (lstSelectedspes.size() != 0) {
			return true;
		} else {
			return false;
		}
	}

	public ItemSpecPage() {

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
		//PCDriver.waitForPageLoad();
		itemSpecLink.click();
		PCDriver.waitForPageLoad();
	}

	public void CreateNewItem(int index, String strItem) {
		clickItemSpecLibrary();

		newItemCategory(strItem);
		for (int i = 0; i < index; i++) {
			clickCreateNewItem();
			PCDriver.switchToWindow("puw_Item_Add");
			PCDriver.waitForElementToBeClickable(txtItemSpecName);
			txtItemSpecName.sendKeys("abc" + System.currentTimeMillis());
			txtItemSpecNumber.sendKeys("100");
			txtManufacturerName.sendKeys("abcd");
			txtQuantity.sendKeys("10");
			try {
				((JavascriptExecutor)PCDriver.getDriver()).executeScript("window.confirm = function(msg){return false;};");
				System.out.println("Alert is present");

				//PCDriver.dismissAlert();
			} catch (Exception e) {
				System.out.println("No Alert Present");
			}
			btnSave.click();
			
			PCDriver.switchToWindow("");
			PCDriver.waitForPageLoad();

		}
		lnkBrowseCatalog.click();

	}

	public void CreateGroup(String str) {
		PCDriver.waitForElementToBeClickable(btnCreateGroup);
		btnCreateGroup.click();
		PCDriver.switchToWindow("puw_Group");
		PCDriver.waitForPageLoad();
		txtGroupName.sendKeys(str);
		btnSave.click();

	}

	public void clickOnAdjustmentHandler() {
		PCDriver.waitForElementToBeClickable(handlerAdjustment);
		handlerAdjustment.click();
	}

}
