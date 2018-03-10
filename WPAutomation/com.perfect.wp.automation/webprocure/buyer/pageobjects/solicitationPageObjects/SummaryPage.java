package buyer.pageobjects.solicitationPageObjects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commonutils.pageobjects.utils.PCDriver;

public class SummaryPage {

	public SummaryPage() {

		PageFactory.initElements(PCDriver.getDriver(), this);
	}

	@FindBy(xpath = "//span/parent::div/preceding-sibling::div//h4[contains(text(),'Supplier')]")
	public List<WebElement> lstsupplierColumn;

	@FindBy(xpath = "//span/parent::div/preceding-sibling::div//h4[contains(text(),'Item')]")
	public List<WebElement> lstItemColumn;

	public boolean verifyListIsPresentInSummaryForSupplier() {
		PCDriver.waitForPageLoad();
		if (lstsupplierColumn.size() != 0) {
			return false;

		} else {
			return true;
		}
	}

	public boolean verifyListIsPresentInSummaryForItem() {
		if (lstItemColumn.size() != 0) {
			return false;

		} else {
			return true;
		}
	}
}
