package pageobjects.generic;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pageobjects.utils.PCDriver;

public class ContractNavigation {

	@FindBy(xpath = "//div[@class='container']//li")
	public WebElement ContractsMenu;

	public ContractNavigation() {
		PageFactory.initElements(PCDriver.getDriver(), this);
	}
	
	public void clickContractsSubMenu(String str) {
		PCDriver.waitForElementToBeClickable(ContractsMenu);
		ContractsMenu.findElement(By.xpath("//a[contains(@title,'"+str+"')]")).click();
	}

	/*public static enum ContractsSubMenu {
		CreateNewContract("Create new Contract"),
		ViewCurrentContracts("View Current Contracts"), 
		ViewArchivedContracts("View Archived Contracts");
		private final String subMenu;

		ContractsSubMenu(String levelCode) {
			this.subMenu = levelCode;
			
		}
		 public String getText() {
	            return subMenu;
	        }
		
	}*/

	  public static enum ContractsSubMenu {
		  CreateNewContract {
	            @Override
	            public String toString() {
	                return "Create new Contract";

	            }
	        },
		  ViewCurrentContracts {
	            @Override
	            public String toString() {
	                return "View Current Contracts";
	            }
	        },
		  ViewArchivedContracts{
	            @Override
	            public String toString() {
	                return "View Archived Contracts";
	            }
	        }
	    }



	

}
