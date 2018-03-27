package buyer.pageobjects.solicitationPageObjects;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import commonutils.pageobjects.generic.HomePage;
import commonutils.pageobjects.utils.DatePicker;
import commonutils.pageobjects.utils.PCDriver;

public class EditSolicitationPageObject {

	HomePage home = new HomePage();
	SupplierPage supplier = new SupplierPage();
	ItemSpecPage item = new ItemSpecPage();
	CreateSolicitationPOM sol = new CreateSolicitationPOM();

	public EditSolicitationPageObject() {
		PageFactory.initElements(PCDriver.getDriver(), this);

	}

	@FindBy(id = "filter_bidTitle")
	public WebElement txtTitle;

	@FindBy(id = "filter_bidNumber")
	public WebElement txtSolNumber;

	@FindBy(xpath = "//button[contains(text(),'Filter')]")
	public WebElement btnFilter;

	@FindBy(xpath = "//tr[@class='odd']")
	public List<WebElement> searchResultRow;

	@FindBy(xpath = "//img[@class='dropdown-toggle dd-action']")
	public WebElement drpDownThreeDots;

	@FindBy(xpath = "//td[contains(text(),'Not Submitted')]/following-sibling::td//img[@class='dropdown-toggle dd-action']")
	public WebElement drpDownThreeDotsForNotSubmittedStatus;

	@FindBy(xpath = "//a/i[text()='edit']")
	public WebElement lnkEdit;

	@FindBy(xpath = "//td[contains(text(),'Not Submitted')]/following-sibling::td//ul//a/i[text()='edit']")
	public WebElement lnkEditNotSubmitted;

	@FindBy(xpath = "//button[contains(@onclick,'BidVendors')]")
	public WebElement btnEditVendor;

	@FindBy(xpath = "//button[contains(@onclick,'biditems')]")
	public WebElement btnEditItem;

	@FindBy(xpath = "//ul[contains(@class,'pagination pull-right')]")
	public WebElement topNavEdit;

	@FindBy(xpath = "//button[contains(text(),'Save')]")
	public WebElement btnSave;

	@FindBy(xpath = "//button[text()='Return']")
	public WebElement btnReturn;

	@FindBy(xpath = "//a[text()='Active Solicitations']")
	public WebElement lnkActiveSolicitations;

	@FindBy(xpath = "//a[text()='Un-issued Solicitations']")
	public WebElement lnkUnissuedSolicitations;

	@FindBy(xpath = "//a[contains(@href,'javascript:startAddendum')]")
	public WebElement lnkCreateAddendum;

	@FindBy(xpath = "//div[contains(@class,'alert alert-info')]")
	public WebElement lblSuccessMessage;

	@FindBy(xpath = "//a[contains(@href,'SolicitationHistory')]")
	public WebElement lnkSolHistory;

	@FindBy(xpath = "//tr[@role='row']")
	public List<WebElement> lstSolHistoryRow;

	@FindBy(xpath = "//button[text()='Compare Versions']")
	public WebElement btnCompareVersions;

	@FindBy(xpath = "//tr//*[contains(@style,'background: rgb(255, 255, 102) none repeat scroll 0% 0%;')]")
	public List<WebElement> lstChanges;

	@FindBy(xpath = "//h4[text()='Solicitation Version Comparison']")
	public WebElement lblSolComparison;

	@FindBy(name = "filter_from_startDate")
	public WebElement dateStartDateFrom;

	@FindBy(name = "filter_to_startDate")
	public WebElement dateStartDateTo;

	@FindBy(name = "filter_from_endDate")
	public WebElement dateFromEndDate;

	@FindBy(name = "filter_to_endDate")
	public WebElement dateToEndDate;

	@FindBy(xpath = "//td[4]")
	public List<WebElement> lstsearchResultsForStartDate;

	@FindBy(xpath = "//td[5]")
	public List<WebElement> lstsearchResultsForEndDate;

	@FindBy(xpath = "//button[text()='Close']")
	public WebElement btnClose;

	@FindBy(xpath = "//tr/td[contains(text(),'Not Submitted')]/preceding-sibling::td[@class='sorting_1']/a")
	List<WebElement> lstSolNumber;

	@FindBy(xpath = "//tr/td[contains(text(),'Not Submitted')]/preceding-sibling::td[4]")
	List<WebElement> lstSolTitle;

	@FindBy(xpath = "(//div[@class='panel-heading'])[6]/following-sibling::div//span")
	public List<WebElement> lblVendorHeaderWarning;

	@FindBy(xpath = "(//div[@class='panel-heading'])[5]/following-sibling::div/span")
	public List<WebElement> lblVendorItemWarning;

	@FindBy(xpath = "//h4[contains(text(),'Info')]")
	public WebElement lblSubmitPageTitle;

	public String getSolNumber(int i) {
		PCDriver.visibilityOfListLocated(lstSolNumber);
		return lstSolNumber.get(i).getText();
	}

	public String getSolTitle(int i) {
		try {
			PCDriver.visibilityOfListLocated(lstSolTitle);
		} catch (Exception e) {
			System.out.println("List is Empty");
		}
		if (lstSolTitle.size() == 0) {
			PCDriver.getDriver().findElement(By.xpath("//a[@alt='Next Page']")).click();
			getSolNumber(i);
		}
		return lstSolTitle.get(i).getText();
	}

	public void clearSearchedData() {
		PCDriver.waitForElementToBeClickable(dateFromEndDate);
		dateFromEndDate.clear();
		PCDriver.waitForElementToBeClickable(dateToEndDate);

		dateToEndDate.clear();
		PCDriver.waitForElementToBeClickable(dateStartDateFrom);

		dateStartDateFrom.clear();
		PCDriver.waitForElementToBeClickable(dateStartDateTo);

		dateStartDateTo.clear();
		PCDriver.waitForElementToBeClickable(txtSolNumber);

		txtSolNumber.clear();
		PCDriver.waitForElementToBeClickable(btnFilter);

		btnFilter.click();
		PCDriver.waitForPageLoad();
	}

	public void checkVendorsAddedWarning() {
		try {
			PCDriver.waitForPageLoad();
			PCDriver.waitForElementToBeClickable(lblVendorHeaderWarning.get(0), Long.valueOf(String.valueOf(5)));
			if (lblVendorHeaderWarning.size() >= 1) {
				PCDriver.waitForElementToBeClickable(btnEditVendor, Long.valueOf(String.valueOf(5)));
				btnEditVendor.click();
				supplier.CreateNewSupplier("abv");
				PCDriver.switchToWindow("");
				PCDriver.waitForPageLoad();
				clickSave();
				clickReturn();

			}
		} catch (Exception e) {
			System.out.println("Vendors are available");
		}
	}

	public void checkVendorItemWarning() {
		try {
			PCDriver.waitForPageLoad();
			PCDriver.waitForElementToBeClickable(lblVendorItemWarning.get(0), Long.valueOf(String.valueOf(5)));
			if (lblVendorItemWarning.size() >= 1) {
				PCDriver.waitForElementToBeClickable(btnEditItem, Long.valueOf(String.valueOf(5)));
				btnEditItem.click();
				sol.AddLineItemsAndVerify("10", "Electronic Components and Supplies");
			}
		} catch (Exception e) {
			System.out.println("Items are available");
		}
	}

	public void clickClose() {
		PCDriver.waitForElementToBeClickable(btnClose);
		btnClose.click();
	}

	public void setTitleForSearch(String strTitle) throws IOException {
		PCDriver.waitForPageLoad();

		PCDriver.waitForElementToBeClickable(txtTitle);
		txtTitle.sendKeys(strTitle);

	}

	public void setSolNumber(String strNumber) {
		PCDriver.waitForElementToBeClickable(txtSolNumber);
		txtSolNumber.sendKeys(strNumber);
	}

	public void clickOnFilter() {
		PCDriver.waitForElementToBeClickable(btnFilter);
		btnFilter.click();
	}

	public void setToStartDate(String strDate) {
		PCDriver.waitForElementToBeClickable(dateStartDateTo);
		dateStartDateTo.sendKeys(strDate);
		txtTitle.click();

	}

	public void setFromStartDate(String strDate) {
		PCDriver.waitForElementToBeClickable(dateStartDateFrom);
		dateFromEndDate.clear();
		dateToEndDate.clear();
		dateStartDateFrom.clear();
		dateStartDateTo.clear();

		dateStartDateFrom.sendKeys(strDate + Keys.TAB);
		txtTitle.click();

	}

	public void setFromEndDate(String strDate) {
		try {
			dateFromEndDate.clear();
			dateToEndDate.clear();
			dateStartDateFrom.clear();
			dateStartDateTo.clear();

		} catch (Exception e) {

		}
		dateFromEndDate.sendKeys(strDate + Keys.TAB);
		txtTitle.click();
	}

	public void setToEndDate(String strDate) {
		dateToEndDate.sendKeys(strDate + Keys.TAB);
		txtTitle.click();

	}

	public void clickOnThreeDots() {
		PCDriver.waitForElementToBeClickable(drpDownThreeDots);
		drpDownThreeDots.click();
	}

	public void clickOnThreeDotsForNotSubmittedStatus() {
		PCDriver.waitForElementToBeClickable(drpDownThreeDotsForNotSubmittedStatus);
		drpDownThreeDotsForNotSubmittedStatus.click();
	}

	public void clickEdit() {
		try {
			PCDriver.waitForElementToBeClickable(lnkEdit);
			((JavascriptExecutor) PCDriver.getDriver()).executeScript("window.confirm = function(msg){return true;}");
			lnkEdit.click();
			// PCDriver.acceptAlert();
		} catch (Exception e) {
			System.out.println("three Dots Edit button is not visible");
		}
	}

	public void clickEditUnderNotSubmittedThreeDots() {
		try {
			PCDriver.waitForElementToBeClickable(lnkEditNotSubmitted);
			((JavascriptExecutor) PCDriver.getDriver()).executeScript("window.confirm = function(msg){return true;}");
			lnkEditNotSubmitted.click();
			PCDriver.waitForPageLoad();
			// PCDriver.acceptAlert();
		} catch (Exception e) {
			System.out.println("three Dots Edit button is not visible");
		}
	}

	public void clickCreateAddendum() {
		PCDriver.waitForElementToBeClickable(lnkCreateAddendum);
		((JavascriptExecutor) PCDriver.getDriver()).executeScript("window.confirm = function(msg){return true;}");
		lnkCreateAddendum.click();
		// PCDriver.acceptAlert();
	}

	public void clickOnActiveSolicitations() {
		lnkActiveSolicitations.click();
	}

	public void clickOnUnissuedSolicitations() {
		lnkUnissuedSolicitations.click();
	}

	public void clickTopNavItem(String strTabName) {
		PCDriver.waitForElementToBeClickable(topNavEdit);
		topNavEdit.findElement(By.xpath(".//li/a[contains(text(),'" + strTabName + "')]")).click();
	}

	public void clickSave() {
		try {
			PCDriver.waitForElementToBeClickable(btnSave);

			((JavascriptExecutor) PCDriver.getDriver()).executeScript("arguments[0].scrollIntoView(true);", btnSave);

			btnSave.click();
			PCDriver.waitForPageLoad();
		} catch (Exception e) {
			System.out.println("Save button is not visible on page");
		}
	}

	public void clickReturn() {
		try {

			PCDriver.waitForElementToBeClickable(btnReturn);
			btnReturn.click();
			PCDriver.waitForPageLoad();
		} catch (Exception e) {
			System.out.println("Return button is not visible");
		}
	}

	public boolean verifyAddendumSubmission() {
		PCDriver.waitForPageLoad();
		PCDriver.waitForElementToBeClickable(lblSubmitPageTitle);
		PCDriver.waitForElementToBeClickable(lblSuccessMessage);
		System.out.println(lblSuccessMessage.getText());
		if (lblSuccessMessage.getText().contains("This solicitation addendum has been published.")) {
			return true;
		} else {
			return false;
		}
	}

	@SuppressWarnings("finally")
	public boolean verifyEditedSolicitationSubmission() {
		try {
			((JavascriptExecutor) PCDriver.getDriver()).executeScript("window.confirm = function(msg){return true;}");

			// PCDriver.acceptAlert();
		} catch (Exception e) {
			System.out.println("Alert not present");
		} finally {
			PCDriver.waitForPageLoad();
			if (lblSuccessMessage.getText().contains("This solicitation has been submitted")) {
				return true;
			} else {
				return false;
			}
		}
	}

	public boolean verifyFinalizeBid() {
		PCDriver.waitForPageLoad();
		if (lblSuccessMessage.getText().contains("is now in finalized status")) {
			return true;
		} else {
			return false;
		}
	}

	public boolean verifyReverseBid() {
		PCDriver.waitForElementToBeClickable(lblSuccessMessage);
		if (lblSuccessMessage.findElement(By.xpath("./h5")).getText()
				.contains("has been reversed and it is again listed under")) {
			return true;
		} else {
			return false;
		}
	}

	public void clickSolHistory() {
		PCDriver.waitForElementToBeClickable(lnkSolHistory);
		lnkSolHistory.click();
	}

	public boolean verifySolHistory() {
		PCDriver.waitForPageLoad();
		if (lstSolHistoryRow.size() > 2) {
			return true;
		} else {
			return false;
		}
	}

	public void clickItemsToCompare() {
		PCDriver.waitForPageLoad();

		lstSolHistoryRow.get(1).findElement(By.xpath(".//input[@name='addendumIds']")).click();
		lstSolHistoryRow.get(2).findElement(By.xpath(".//input[@name='addendumIds']")).click();
		((JavascriptExecutor) PCDriver.getDriver()).executeScript("window.confirm = function(msg){return true;}");

		btnCompareVersions.click();
		// PCDriver.acceptAlert();
	}

	public boolean VerifyStartDate(List<WebElement> ele) throws ParseException {
		String[] str = new String[ele.size()];
		int count = 0;
		for (int i = 0; i < ele.size(); i++) {
			str = ele.get(i).getText().split("at");
			// SimpleDateFormat simple=new SimpleDateFormat("MM/dd/yyyy");
			// System.out.println(str[0].trim());
			// Date date1=simple.parse(dateStartDateTo.getAttribute("value"));

			// SimpleDateFormat simple1=new SimpleDateFormat("MMMM dd,yyyy");

			// Date date2=simple1.parse(str[0].trim().toString());
			// simple1=simple;
			// date2=simple1.parse(simple1.format(date2));
			// if(date1.compareTo(date2)<0) {
			if (DatePicker.checkDateRangeOnly(str[0].trim().toString(), dateStartDateFrom.getAttribute("value"),
					dateStartDateTo.getAttribute("value")) == true) {
				count++;
			}

		}
		System.out.println("Count is" + count + "size is:" + ele.size());
		if (count == ele.size()) {
			return true;
		} else {
			return false;
		}
	}

	public boolean VerifyEndDate(List<WebElement> ele) throws ParseException {
		String[] str = new String[ele.size()];
		int count = 0;
		for (int i = 0; i < ele.size(); i++) {
			str = ele.get(i).getText().split("at");
			System.out.println("Date is:" + str[0].trim());
			if (DatePicker.checkDateRangeOnly(str[0].trim().toString(), dateFromEndDate.getAttribute("value"),
					dateToEndDate.getAttribute("value")) == true) {
				count++;
			}

		}
		if (count == ele.size()) {
			return true;
		} else {
			return false;
		}
	}

	public boolean VerifyEndDateTime(List<WebElement> ele) throws ParseException {
		String[] str = new String[ele.size()];
		int count = 0;
		for (int i = 0; i < ele.size(); i++) {
			str = ele.get(i).getText().split("at");
			System.out.println("Date is:" + str[0].trim());
			if (DatePicker.checkDateRangeOnly(str[0].trim().toString(), dateFromEndDate.getAttribute("value"),
					dateToEndDate.getAttribute("value")) == true) {
				count++;
			}

		}
		if (count == ele.size()) {
			return true;
		} else {
			return false;
		}
	}

	public boolean VerifyStartDate() {
		try {
			return VerifyStartDate(lstsearchResultsForStartDate);
		} catch (ParseException e) {
			e.printStackTrace();
			return false;

		}
	}

	public boolean VerifyEndDate() {
		try {
			return VerifyEndDate(lstsearchResultsForEndDate);
		} catch (ParseException e) {
			e.printStackTrace();
			return false;

		}
	}

	public boolean verifyChanges() {
		PCDriver.waitForPageLoad();
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		PCDriver.waitForElementToBeClickable(lblSolComparison);
		PCDriver.waitForPageLoad();
		if (lstChanges.size() > 2) {
			return true;
		} else {
			return false;
		}
	}

	public boolean verifySearchResultRow() {
		PCDriver.waitForPageLoad();
		if (searchResultRow.size() != 0) {
			return true;
		} else {
			return false;
		}
	}

}