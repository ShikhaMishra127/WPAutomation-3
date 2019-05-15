package pageobjects.bidboard;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.common.Browser;

import java.time.format.DateTimeFormatter;

public class ContractBidboardPOM {

	private final Browser browser;

	private final String BidBoardHeader = "//*[@id='webprocure_public_contract_board']/app-root/contract-board/contract-board-header";
	private final String BidBoardList = "//*[@id='webprocure_public_contract_board']/app-root/contract-board/contract-board-results";
	private final String BidBoardDetail = "//*[@id='webprocure_public_contract_board']/app-root/contract-board/contract-board-detail";

	/**
	 * Constructor called by PageFactory.instantiatePage
	 * @param browser WebDriver (as required by PageFactory) will be cast back to Browser.
	 */
	public ContractBidboardPOM(WebDriver browser) {
		this.browser = (Browser)browser;
		PageFactory.initElements(((Browser) browser).driver, this);
		
	}

	private DateTimeFormatter inputBoxFormatter = DateTimeFormatter.ofPattern("LLL d, yyyy");

	//////////////////////////////////////////////////////////////////////// CONTRACT BOARD LIST PAGE

	@FindBy(xpath="//a[@class='brand-logo']")
	public WebElement searchBarHomeButton;

	@FindBy(xpath = "//*[@id='search']")
	public WebElement searchBarEdit;

	@FindBy(xpath = "//*[@id='contract-facets']/contract-board-facet/div/span")
	public WebElement resetFacetLink;


	@FindBy(xpath="//ul[@class='collection']")
	public WebElement listOfContractResults;

	////////// Contract line item sub-elements
	public String itemTitle = "//span[@class='title']";
	public String itemDescription ="//span[@class='contract-desc']";
	public String itemVendor = "//span[contains(@class,'vendor-name')]";
	public String itemStartDate = "//span[contains(@title,'Start Date')]";
	public String itemEndDate = "//span[contains(@title,'End Date')]";

	
	@FindBy(xpath = BidBoardList + "/div/div/div[3]/div[1]/h5")
	public WebElement contractCount;
	
	@FindBy(xpath = BidBoardList + "/div/div/div[3]/div[5]/ul/li[1]")
	public WebElement firstContract;
	
	@FindBy(xpath = BidBoardList + "/div/div/div[3]/div[5]/ul/li[1]/span")
	public WebElement firstContractLink;

	//////////////////////////////////////////////////////////////////////// CONTRACT BOARD SUMMARY PAGE

	@FindBy(xpath = "//div[@class= 'contractDetail']")
	public WebElement contractdetailspage;
	
	@FindBy(xpath = "//i[@class='material-icons'][contains(.,'attachment')]/parent::*/parent::*/div/ul")
	public WebElement summaryAttachments;

	@FindBy(xpath = "//i[@class='material-icons'][contains(.,'attach_money')]/parent::*/parent::*/div")
	public WebElement summaryPricing;

	@FindBy(xpath = "//i[@class='material-icons'][contains(.,'access_time')]/parent::*/parent::*/div")
	public WebElement summaryPeriod;

	@FindBy(xpath = "//i[@class='material-icons'][contains(.,'library_books')]/parent::*")
	public WebElement home_button;

	@FindBy(xpath = "//i[@class='material-icons'][contains(.,'chevron_right')]/parent::*")
	public WebElement right_chev_button;

	@FindBy(xpath = "//i[@class='material-icons'][contains(.,'last_page')]/parent::*")
	public WebElement right_chev_last_button;

	@FindBy(xpath = "//i[@class='material-icons'][contains(.,'chevron_left')]/parent::*")
	public WebElement left_chev_button;

	@FindBy(xpath = "//i[@class='material-icons'][contains(.,'first_page')]/parent::*")
	public WebElement left_chev_first_button;

	//////////////////////////////////////////////////////////////////////// HELPFUL METHODS

	public int numberOfContracts() {
		int numberFound = 0;
		// sleep for a little - assume we are waiting for page to reload contract result count
		try { Thread.sleep(browser.defaultWait); } catch (InterruptedException e) { e.printStackTrace(); }

		if (contractCount.getClass() != null) {
			String numberReturned = contractCount.getText().split(" ")[0];
			numberFound = Integer.parseInt(numberReturned);
		}

		return numberFound;
	}
	
	public void clickReset() {
		browser.waitForElementToBeClickable(resetFacetLink);
		resetFacetLink.click();
	}
	
	public void clickHome() {
		browser.waitForElementToBeClickable(home_button);
		home_button.click();
	}
	
	public void searchContracts(String searchText) {

		clickReset();

		browser.waitForElementToBeClickable(searchBarEdit);
		searchBarEdit.sendKeys(searchText);

	}

	public void waitForContract(String contractNumber) {

		browser.clickWhenAvailable(searchBarHomeButton);

		browser.waitForElementToAppear(searchBarEdit);
		searchBarEdit.sendKeys(contractNumber);

		String xpath = "//span[@class='title teal-text'][contains(text(),'"+ contractNumber +"')]";

		browser.waitForElementWithRefresh(xpath, 15, 300);

	}

	// function that takes contract number and returns webelement for line-item in contract board (all elements)
	public WebElement getContractLineItem(String contractNum) {

		String xpath = "//span[@class='title teal-text'][contains(text(),'"+ contractNum +"')]/parent::*/parent::*/parent::*";

		browser.waitForElementToAppear(By.xpath(xpath));

		return listOfContractResults.findElement(By.xpath(xpath));
	}
}

