package buyer.pageobjects.requestPageObjects;

import java.util.List;

import org.apache.log4j.varia.ReloadingPropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import commonutils.pageobjects.utils.PCDriver;
import commonutils.pageobjects.utils.ReadConfig;
import commonutils.pageobjects.utils.ReadExcelData;

public class EditRequestPOM {

	public EditRequestPOM() {

		PageFactory.initElements(PCDriver.getDriver(), this);
	}

	@FindBy(xpath = "//input[@id='FShoppingCartName']")
	public WebElement requestnametxtbox;

	@FindBy(xpath = "//input[@id='FShoppingCartNum']")
	public WebElement requestumtxtbox;

	@FindBy(xpath = "//input[@id='FBorg']")
	public WebElement organizationtxtbox;

	@FindBy(xpath = "//select[@id='FUser']")
	public WebElement requesterdropdown;

	@FindBy(xpath = "//button[@title='Submit']")
	public WebElement submitbtn;

	@FindBy(xpath = "//table[contains(@id,'reqTable')]")
	public WebElement resulttable;

	@FindBy(xpath = "//table//tr[contains(@role,'row')]")
	public List<WebElement> reqresultrows;

	@FindBy(xpath = "//table//tr[contains(@role,'row')]//td[4]")
	public WebElement reqnamecol;

	@FindBy(xpath = "//img[contains(@title,'More Actions')]")
	public List<WebElement> actiondropdown;

	@FindBy(xpath = "//ul[contains(@class,'dropdown-menu dropdown-menu-right')]//li")
	public List<WebElement> actionlist;

	@FindBy(xpath = "//input[contains(@id,'quantity')]")
	public List<WebElement> editqty;
	
	@FindBy(xpath = "//img[contains(@title,'Copy Line Item')]")
	public List<WebElement> copylineitemicon;
	
	@FindBy(xpath = "//span[@class='CartInfo'][1]")
	public WebElement itemcount;
	
	@FindBy(xpath = "//img[contains(@title,'Edit Off-Catalog Item')]")
	public List<WebElement> editoffcatlogitemicon;

	@FindBy(xpath = "//button[contains(@title,'Update Request')]")
	public WebElement btnupdatereq;

	@FindBy(xpath = "//button[contains(@title,'Close')]")
	public WebElement btnclose;

	@FindBy(xpath = "//button[contains(@title,'Delete All')]")
	public WebElement btndeleteall;

	@FindBy(xpath = "//button[contains(@title,'Submit Request')]")
	public WebElement btnsubmitreq;

	public void clickonedit() {
		try {
			PCDriver.waitForPageLoad();
			PCDriver.waitForElementToBeEnable(By.id("FShoppingCartName"));
			requestnametxtbox.sendKeys(ReadExcelData.getInstance("EditRequest").getStringValue("TargetRequest"));
			submitbtn.click();
			PCDriver.waitForPageLoad();
			PCDriver.WaitTillElementIsPresent(resulttable);
			for (int i = 1; i <= reqresultrows.size(); i++) {
				String requestname = reqnamecol.getText();
				if (requestname.contains(ReadExcelData.getInstance("EditRequest").getStringValue("TargetRequest"))) {
					for (WebElement actions : actiondropdown) {
						actions.click();
						// actiondropdown.click();
						PCDriver.visibilityOfListLocated(actionlist);
						for (WebElement editaction : actionlist) {
							System.out.println(editaction.getText());
							if (editaction.getText().contains("Edit")) {
								editaction.click();
								
							}
							break;
						}
					}

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	

	public void editquantity() {

		try {
			PCDriver.waitForPageLoad();
			PCDriver.waitForElementToDisappear(By.id("loadingDiv"));
			if(PCDriver.getDriver().getTitle().contains("WebProcure: Request And Workflow")){
				PCDriver.switchToDefaultContent();
				//Thread.sleep(8000);
				PCDriver.switchToFrameBasedOnFrameName("C1ReqMain");
				for(WebElement quantityfield:editqty){
				quantityfield.clear();
				System.out.print("field cleared");
				quantityfield.sendKeys(ReadExcelData.getInstance("EditRequest").getStringValue("EditQuantity"));
				}		
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void copyitems(){
		try{
			PCDriver.waitForElementToDisappear(By.id("loadingDiv"));
			PCDriver.switchToDefaultContent();
			Thread.sleep(10000);
			PCDriver.switchToFrameBasedOnFrameName("C1ReqMain");
			PCDriver.visibilityOfListLocated(copylineitemicon);
			System.out.println(copylineitemicon.size());
			for(WebElement copyitem : copylineitemicon){
				PCDriver.waitForElementToBeClickable(copyitem);
				copyitem.click();
				break;
			}
			Thread.sleep(10000);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	
	public void assertupdates(){
		try{
			PCDriver.waitForElementToDisappear(By.id("loadingDiv"));
			PCDriver.switchToDefaultContent();
			Thread.sleep(10000);
			PCDriver.switchToFrameBasedOnFrameName("C1ReqMain");
			for(WebElement updatedqty : editqty){
			Assert.assertEquals(updatedqty.getText(), ReadExcelData.getInstance("EditRequest").getStringValue("EditQuantity"));
			}
			}catch(Exception e){
				e.printStackTrace();
			}
	}
	
	public void updaterequest(){
		PCDriver.waitForElementToBeClickable(btnupdatereq);
		btnupdatereq.click();
	}
	
	public void submitrequest(){
		PCDriver.waitForElementToBeClickable(btnsubmitreq);
		btnsubmitreq.click();
	}
}