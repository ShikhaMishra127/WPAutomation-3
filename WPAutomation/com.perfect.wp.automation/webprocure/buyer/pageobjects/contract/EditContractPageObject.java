package buyer.pageobjects.contract;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import commonutils.pageobjects.utils.PCDriver;

public class EditContractPageObject {

	public EditContractPageObject() {
		PageFactory.initElements(PCDriver.getDriver(), this);
	}

	@FindBy(id = "vendorName")
	public WebElement txtVendorName;

	@FindBy(id = "contractTitle")
	public WebElement txtContractTitle;

	@FindBy(id = "contractNumber")
	public WebElement txtContractNumber;

	@FindBy(xpath = "//span[@class='dropdown dd-span open']//ul[contains(@class,'dropdown-menu dropdown-menu-right')]")
	public WebElement threeDotsItems;

	@FindBy(xpath = "//button[contains(text(),'Add New Section')]")
	public WebElement btnAddNewSection;

	@FindBy(xpath = "//button[text()='Add Field']")
	public WebElement btnAddField;

	@FindBy(xpath = "//div[contains(@class,'fieldTitleDiv input-group')]")
	public WebElement fieldTitle;

	@FindBy(className = "input-group")
	public WebElement drpDownfieldType;

	@FindBy(xpath = "//button[contains(text(),'Save')]")
	public WebElement btnSave;

	public void setVendorName(String str) {
		PCDriver.waitForElementToBeClickable(txtVendorName);
		txtVendorName.sendKeys(str);
	}

	public void setContractTitle(String str) {
		PCDriver.waitForElementToBeClickable(txtContractTitle);
		txtContractTitle.sendKeys(str);
	}

	public void setContractNumber(String strContractNumber) {
		PCDriver.waitForElementToBeClickable(txtContractNumber);
		txtContractNumber.sendKeys(strContractNumber);
	}

	public void clickThreeDotItem(String strItemName) {
		PCDriver.waitForElementToBeClickable(threeDotsItems);
		threeDotsItems.findElement(By.xpath(".//li/a[contains(text(),'" + strItemName + "')]")).click();
	}

	public void addNewSectionAndField() {
		PCDriver.waitForElementToBeClickable(btnAddNewSection, Long.parseLong(String.valueOf(10)));
		// btnAddNewSection.click();
		btnAddField.click();

		for (int i = 2; i < 6; i++) {

			try {

				btnAddField.click();
			} catch (Exception e) {
				System.out.println("Section not present");
			}
			PCDriver.waitForElementToBeClickable(fieldTitle);

			fieldTitle.findElement(By.xpath("//input[contains(@id,'fieldTitle_" + i + "_25')]")).sendKeys("" + i);
			new Select(drpDownfieldType.findElement(By.xpath("//select[contains(@id,'fieldType_" + i + "_25')]")))
					.selectByIndex(i);
			// int x = i - 1;
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (PCDriver.getDriver()
					.findElements(By.xpath("(//iframe[@class='cke_wysiwyg_frame cke_reset'])[" + i + "]"))
					.size() != 0) {
				// WebElement
				// ele=PCDriver.getDriver().findElement(By.xpath("//iframe[@class='cke_wysiwyg_frame
				// cke_reset']"));
				PCDriver.switchToFrame(PCDriver.getDriver()
						.findElement(By.xpath("(//iframe[@class='cke_wysiwyg_frame cke_reset'])[" + i + "]")));

				PCDriver.getDriver()
						.findElement(By.xpath(
								"//body[@class='cke_editable cke_editable_themed cke_contents_ltr cke_show_borders']"))
						.sendKeys("abc");
				PCDriver.getDriver().switchTo().defaultContent();
			}

		}
	}

	public void clickSave() {
		PCDriver.waitForElementToBeClickable(btnSave, Long.parseLong(String.valueOf(5)));
		btnSave.click();
	}

	public static enum ContractsThreeDotsMenu {
		EditContract {
			@Override
			public String toString() {
				return "Edit";

			}
		},
		CopyContract {
			@Override
			public String toString() {
				return "Copy";
			}
		},
		PrintContract {
			@Override
			public String toString() {
				return "Print";
			}
		},

		DeleteContract {
			@Override
			public String toString() {
				return "Delete";
			}
		},
		ContractHistory {
			@Override
			public String toString() {
				return "View Archived Contracts";
			}
		},

		ContractRelease {
			@Override
			public String toString() {
				return "View Archived Contracts";
			}
		}
	}

	public static enum EditContractTopNav {
		EditHeader {
			@Override
			public String toString() {
				return "Edit Header";

			}
		},
		EditNotification {
			@Override
			public String toString() {
				return "Edit Notification";
			}
		},
		EditClauses {
			@Override
			public String toString() {
				return "Edit Clauses";
			}
		},

		EditItems {
			@Override
			public String toString() {
				return "Edit Items";
			}
		},
		ContractHistory {
			@Override
			public String toString() {
				return "View Archived Contracts";
			}
		},

		ContractRelease {
			@Override
			public String toString() {
				return "View Archived Contracts";
			}
		}
	}

}
