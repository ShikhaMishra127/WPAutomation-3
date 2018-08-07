package buyer.pageobjects.requestPageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commonutils.pageobjects.utils.PCDriver;

public class CatalogReqPOM {

	@FindBy(xpath = "//button[@type = 'submit']")
	public WebElement searchbutton;
	
	@FindBy(xpath = "//button[@type = 'button' and (contains(@onclick,'javascript:ResetAllFields()'))]")
	public WebElement newsearchbutton;
	
	@FindBy(xpath = "//button[@type = 'button' and (contains(@data-target,'#filter-panel'))]")
	public WebElement advancesearchbutton;
	
	@FindBy(xpath = "//input[contains(@id,'partnum')]")
	public WebElement filter_partnum;
	
	@FindBy(xpath = "//select[contains(@id,'TXT_SUPPLIER')]")
	public WebElement filter_supplierdropdown;
	
	@FindBy(xpath = "//input[contains(@id,'manufacturer')]")
	public WebElement filter_manufacturer;
	
	@FindBy(xpath = "//input[contains(@id,'contractnum')]")
	public WebElement filter_contractnumbber;
	
	@FindBy(xpath = "//div[contains(@class,'grid-group-item')]")
	public  List<WebElement> category_list;
	
	@FindBy(xpath = "//button[contains(@id,'grid-view')]" )
	public WebElement gridview;
	
	public String itemid;
	
	@FindBy(xpath = ("//input[contains(@class,'form-control item-quantity') and contains(@type,'text') and contains(@id,'+itemid')]"))
	public WebElement quantity_box;
	
	@FindBy(xpath = "//button[@class = 'btn btn-success btn-item-add']")
	public WebElement additem_btn;
	
	
	
	@FindBy(xpath = "//input[contains(@id,'keyword')]")
	public WebElement searchbox;
	
	public CatalogReqPOM() {
		// TODO Auto-generated constructor stub
		PageFactory.initElements(PCDriver.getDriver(), this);
	}
	
	public void addcatalogitem() throws Exception{
		PCDriver.waitForElementToDisappear(By.id("loadingDiv"));
		PCDriver.waitForPageLoad();

		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}

		//PCDriver.getDriver().switchTo().defaultContent();;
		//PCDriver.getDriver().switchTo().frame("C1ReqMain");
		PCDriver.visibilityOfListLocated(category_list);
		for(WebElement catalogcategory:category_list){
			String categoryname = catalogcategory.getText();
			System.out.println(categoryname);
			if(categoryname.equalsIgnoreCase("Apparel and Luggage and Personal Care Products")){	
				catalogcategory.click();
				Thread.sleep(10000);
				PCDriver.switchToDefaultContent();
				PCDriver.switchToFrameBasedOnFrameName("C1ReqMain");
				gridview.click();
				PCDriver.visibilityOfListLocated(category_list);
				for(WebElement itemname:category_list){
					String items = itemname.getText();
					if(items.equalsIgnoreCase("Catalog Line Item Edit Test 1")){
						itemid = itemname.getAttribute("id");
						System.out.println(itemid);
						PCDriver.waitForElementToBeEnable(By.xpath("//input[contains(@class,'form-control item-quantity') and contains(@type,'text') and contains(@id,'+itemid')]"));
						quantity_box.sendKeys("20");
					}
							
				}
				
			}
		}
	}

}
