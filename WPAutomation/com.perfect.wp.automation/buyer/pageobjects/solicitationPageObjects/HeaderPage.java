package pageobjects.solicitationPageObjects;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import pageobjects.utils.PCDriver;
import pageobjects.utils.ReadExcelData;

public class HeaderPage {
	@FindBy(id = "bidNumber")
	public WebElement txtSolNumber;

	@FindBy(id = "bidTitle")
	public WebElement txtTitle;

	@FindBy(id = "solType")
	public WebElement drpDownSolType;

	@FindBy(id = "bidestimatedtotal")
	public WebElement txtEstimatedTotValue;

	@FindBy(id = "bidDesc")
	public WebElement txtDesc;

	@FindBy(name = "bidInvitationType")
	public WebElement drpDownInvitationType;

	@FindBy(xpath = "//button[text()='Close']")
	public WebElement btnCloseOnCategoryPopUp;

	@FindBy(id = "noLineItem")
	public WebElement chkBoxNoLineItem;

	@FindBy(id = "selectCatButton")
	public WebElement btnCat;

	@FindBy(id = "deliveryInfo")
	public WebElement txtDeliveryInfo;

	@FindBy(xpath = "//div[@aria-hidden='false']//ul[contains(@class,'ui-fancytree')]/li")
	public WebElement chkBoxCategory;

	@FindBy(id = "solstartdatetime")
	public WebElement solStartDate;

	@FindBy(id = "solenddatetime")
	public WebElement solEndDate;

	@FindBy(id = "partstartdatetime")
	public WebElement partStartDate;

	@FindBy(id = "partenddatetime")
	public WebElement partEndDate;

	@FindBy(id = "collaboration_enable")
	public WebElement chkBoxColab;

	@FindBy(id = "collabstartdatetime")
	public WebElement collabStartDate;

	@FindBy(id = "collabenddatetime")
	public WebElement collabEndDate;

	@FindBy(xpath = "//button[text()='Add New Section']")
	public WebElement btnAddField;

	@FindBy(xpath = "//div[contains(@class,'fieldTitleDiv input-group')]")
	public WebElement fieldTitle;

	@FindBy(className = "input-group")
	public WebElement drpDownfieldType;

	@FindBy(xpath = "//div[@class='xdsoft_datetimepicker xdsoft_noselect ' and contains(@style,'block')]")
	public WebElement datePicker;

	@FindBy(xpath = "//button[text()='Add New Section']")
	public WebElement btnAddSection;

	@FindBy(xpath = "//section[@id='sol-header-content']//div[@class='checkbox']/label//input[@name='suborg_invite']")
	public WebElement chkBoxSubOrgInvite;

	@FindBy(xpath = "//input[@name='collaboration_enable']")
	public WebElement chkBoxCollabDuration;

	public HeaderPage() {

		PageFactory.initElements(PCDriver.getDriver(), this);
	}

	public void enterHeaderDetails(boolean lineitem) throws IOException, InterruptedException {
		setTitle(ReadExcelData.getInstance("Solicitation").getStringValue("Title"));
		setSolType();
		selectInvitationType("Private");
		setEstimatedTotalValue(ReadExcelData.getInstance("Solicitation").getStringValue("EstimatedTotalValue"));
		setDescription("abc");
		if (lineitem == false) {
			selectNoLineItemCheckBox();
			clickAndSelectCategory("Apparel");
			clickCloseOnCategoryPopUp();
		}
		addField();

	}

	public void waitForAddFieldToDisplay() {
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		PCDriver.waitForElementToBeClickable(btnAddField);

	}

	public void setSolStartDate(String strDate) {
		solStartDate.clear();

		PCDriver.waitForElementToBeClickable(datePicker);
		PCDriver.waitForElementToBeClickable(solStartDate);
		solStartDate.sendKeys(strDate);

	}

	public void setPartStartDate(String strDate) {
		partStartDate.clear();

		PCDriver.waitForElementToBeClickable(datePicker);
		PCDriver.waitForElementToBeClickable(partStartDate);
		partStartDate.sendKeys(strDate);

	}

	public void setPartEndDate(String strDate) {
		PCDriver.waitForElementToBeClickable(btnAddField);
		chkBoxSubOrgInvite.click();
		partEndDate.clear();
		PCDriver.waitForElementToBeClickable(datePicker);
		partEndDate.sendKeys(strDate);
	}

	public void setCollabStartDate(String srtDate) {
		PCDriver.waitForElementToBeClickable(btnAddField);
		chkBoxColab.click();
		collabStartDate.clear();
		PCDriver.waitForElementToBeClickable(datePicker);
		collabStartDate.sendKeys(srtDate);

	}

	public void setCollabEndDate(String srtDate) {
		PCDriver.waitForElementToBeClickable(btnAddField);
		chkBoxColab.click();
		collabEndDate.clear();
		PCDriver.waitForElementToBeClickable(datePicker);
		collabEndDate.sendKeys(srtDate);

	}

	public String getSolStartDate() {
		return solStartDate.getAttribute("value");
	}

	public String getSolEndDate() {
		return solEndDate.getAttribute("value");
	}

	public String getCollabStartDate() {
		return collabStartDate.getAttribute("value");
	}

	public String getCollabEndDate() {
		return collabEndDate.getAttribute("value");
	}

	public String getPartStartDate() {
		return partStartDate.getAttribute("value");
	}

	public String getPartEndDate() {
		return partEndDate.getAttribute("value");
	}

	public void setTitle(String strTitle) {
		PCDriver.waitForElementToBeClickable(txtTitle);
		txtTitle.sendKeys(strTitle);
	}

	public void setSolType() {
		new Select(drpDownSolType).selectByIndex(1);
	}

	public void setDescription(String strDesc) {
		txtDesc.sendKeys(strDesc);
	}

	public void setEstimatedTotalValue(String strValue) {
		txtEstimatedTotValue.sendKeys(strValue);
	}

	public void selectInvitationType(String strType) {
		new Select(drpDownInvitationType).selectByVisibleText("Private");

	}

	public void selectNoLineItemCheckBox() {
		chkBoxNoLineItem.click();
	}

	public void clickAndSelectCategory(String CategoryName) throws InterruptedException {
		btnCat.click();
		PCDriver.waitForElementToBeClickable(chkBoxCategory);
		if (chkBoxCategory.isDisplayed()) {
			PCDriver.waitForElementToBeEnable(By.xpath("//li//span[contains(text(),'" + CategoryName
					+ "')]//preceding-sibling::span[contains(@class,'fancytree-checkbox')]"));
			chkBoxCategory.findElement(By.xpath("//li//span[contains(text(),'" + CategoryName
					+ "')]//preceding-sibling::span[contains(@class,'fancytree-checkbox')]")).click();
		}
	}

	public void clickCloseOnCategoryPopUp() {
		PCDriver.waitForElementToBeClickable(btnCloseOnCategoryPopUp);
		btnCloseOnCategoryPopUp.click();
	}

	public void addField() {
		for (int i = 2; i < 6; i++) {
			PCDriver.waitForElementToBeClickable(btnAddField);
			btnAddField.click();
			PCDriver.waitForElementToBeClickable(fieldTitle);

			fieldTitle.findElement(By.xpath("//input[contains(@id,'fieldTitle_" + i + "_24')]")).sendKeys("" + i);
			new Select(drpDownfieldType.findElement(By.xpath("//select[contains(@id,'fieldType_" + i + "_24')]")))
					.selectByIndex(i - 1);
			int x = i - 1;
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (PCDriver.getDriver()
					.findElements(By.xpath("(//iframe[@class='cke_wysiwyg_frame cke_reset'])[" + x + "]"))
					.size() != 0) {
				// WebElement
				// ele=PCDriver.getDriver().findElement(By.xpath("//iframe[@class='cke_wysiwyg_frame
				// cke_reset']"));
				PCDriver.switchToFrame(PCDriver.getDriver()
						.findElement(By.xpath("(//iframe[@class='cke_wysiwyg_frame cke_reset'])[" + x + "]")));

				PCDriver.getDriver()
						.findElement(By.xpath(
								"//body[@class='cke_editable cke_editable_themed cke_contents_ltr cke_show_borders']"))
						.sendKeys("abc");
				PCDriver.getDriver().switchTo().defaultContent();
			}
		}
	}

}
