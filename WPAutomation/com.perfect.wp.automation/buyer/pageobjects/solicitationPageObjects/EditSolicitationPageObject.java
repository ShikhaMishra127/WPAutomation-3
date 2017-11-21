package pageobjects.solicitationPageObjects;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageobjects.generic.HomePage;
import pageobjects.utils.DatePicker;
import pageobjects.utils.PCDriver;

public class EditSolicitationPageObject {

	HomePage home = new HomePage();

	public EditSolicitationPageObject() {
		PageFactory.initElements(PCDriver.getDriver(), this);

	}

	@FindBy(id = "filter_bidTitle")
	public WebElement txtTitle;

	@FindBy(id = "filter_bidNumber")
	public WebElement txtSolNumber;

	@FindBy(xpath = "//button[contains(text(),'Filter')]")
	public WebElement btnFilter;

	@FindBy(xpath = "//img[@class='dropdown-toggle dd-action']")
	public WebElement drpDownThreeDots;

	@FindBy(xpath = "//a/i[text()='edit']")
	public WebElement lnkEdit;

	@FindBy(xpath = "//ul[contains(@class,'pagination pull-right')]")
	public WebElement topNavEdit;

	@FindBy(xpath = "//button[contains(text(),'Save')]")
	public WebElement btnSave;

	@FindBy(xpath = "//button[text()='Return']")
	public WebElement btnReturn;

	@FindBy(xpath = "//a[text()='Active Solicitations']")
	public WebElement lnkActiveSolicitations;

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

	public void setTitleForSearch(String strTitle) throws IOException {
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
		dateStartDateFrom.click();
	}

	public void setFromStartDate(String strDate) {
		PCDriver.waitForElementToBeClickable(dateStartDateTo);
		dateStartDateFrom.sendKeys(strDate + Keys.TAB);
	}

	public void setFromEndDate(String strDate) {
		dateFromEndDate.sendKeys(strDate + Keys.TAB);
	}

	public void setToEndDate(String strDate) {
		dateToEndDate.sendKeys(strDate + Keys.TAB);
	}

	public void clickOnThreeDots() {
		PCDriver.waitForElementToBeClickable(drpDownThreeDots);
		drpDownThreeDots.click();
	}

	public void clickEdit() {
		try {
			PCDriver.waitForElementToBeClickable(lnkEdit);
			lnkEdit.click();
			PCDriver.acceptAlert();
		} catch (Exception e) {

		}
	}

	public void clickCreateAddendum() {
		PCDriver.waitForElementToBeClickable(lnkCreateAddendum);
		lnkCreateAddendum.click();
		PCDriver.acceptAlert();
	}

	public void clickOnActiveSolicitations() {
		lnkActiveSolicitations.click();
	}

	public void clickTopNavItem(String strTabName) {
		PCDriver.waitForElementToBeClickable(topNavEdit);
		topNavEdit.findElement(By.xpath(".//li/a[contains(text(),'" + strTabName + "')]")).click();
	}

	public void clickSave() {
		PCDriver.waitForElementToBeClickable(btnSave);

		((JavascriptExecutor) PCDriver.getDriver()).executeScript("arguments[0].scrollIntoView(true);", btnSave);

		btnSave.click();
	}

	public void clickReturn() {
		PCDriver.waitForElementToBeClickable(btnReturn);
		btnReturn.click();
	}

	public boolean verifyAddendumSubmission() {
		PCDriver.waitForPageLoad();
		if (lblSuccessMessage.getText().contains("This solicitation addendum has been published")) {
			return true;
		} else {
			return false;
		}
	}

	@SuppressWarnings("finally")
	public boolean verifyEditedSolicitationSubmission() {
		try {
			PCDriver.acceptAlert();
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
		btnCompareVersions.click();
		PCDriver.acceptAlert();
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
}