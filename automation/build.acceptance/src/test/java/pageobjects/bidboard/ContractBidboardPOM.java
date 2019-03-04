package pageobjects.bidboard;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.common.Browser;

public class ContractBidboardPOM {

	private final Browser browser;
	
	
	private final String BidBoardHeader = "//*[@id='webprocure_public_contract_board']/app-root/contract-board/contract-board-header";
	private final String BidBoardList = "//*[@id='webprocure_public_contract_board']/app-root/contract-board/contract-board-results";
	private final String BidBoardDetail = "//*[@id='webprocure_public_contract_board']/app-root/contract-board/contract-board-detail";

	/**
	 * Constructor called by PageFactory.instantiatePage
	 * @param browser WebDriver (as required by PageFactory) will be cast back to Browser.
	 */
	public ContractBidboardPOM(WebDriver browser) throws IOException {
		this.browser = (Browser)browser;
		PageFactory.initElements(((Browser) browser).driver, this);
		
	}
	@FindBy(xpath = "//*[@id='contract-facets']/contract-board-facet/div/span")
	public WebElement resetFacetLink;
	
	@FindBy(xpath = "//*[@id='search']")
	public WebElement searchBar;
	
	@FindBy(xpath = BidBoardList + "/div/div/div[3]/div[1]/h5")
	public WebElement contractCount;
	
	@FindBy(xpath = BidBoardList + "/div/div/div[3]/div[5]/ul/li[1]")
	public WebElement firstContract;
	
	@FindBy(xpath = BidBoardList + "/div/div/div[3]/div[5]/ul/li[1]/span")
	public WebElement firstContractLink;

	@FindBy(xpath = "//div[@class= 'contractDetail']")
	public WebElement contractdetailspage;

	@FindBy(xpath = BidBoardDetail + "/div[1]/div/div/div[2]/div/div[1]/h5")
	public WebElement summaryHeader;
	
	@FindBy(xpath = "//i[contains(@class, 'material-icons') and text() = 'attachment']/parent::*/parent::*/div/ul")
	public WebElement summaryAttachments;
	
	@FindBy(xpath = "//i[contains(@class, 'material-icons') and text() = 'attach_money']/parent::*/parent::*/div")
	public WebElement summaryPricing;
	
	@FindBy(xpath = "//i[contains(@class, 'material-icons') and text() = 'access_time']/parent::*/parent::*/div")
	public WebElement summaryPeriod;
	
	@FindBy(xpath = "//i[contains(@class, 'material-icons') and text() = 'library_books']/parent::*")
	public WebElement home_button;
	
	@FindBy(xpath = "//i[contains(@class, 'material-icons') and text() = 'chevron_right']/parent::*")
	public WebElement right_chev_button;
	
	@FindBy(xpath = "//i[contains(@class, 'material-icons') and text() = 'last_page']/parent::*")
	public WebElement right_chev_last_button;
	
	@FindBy(xpath = "//i[contains(@class, 'material-icons') and text() = 'chevron_left']/parent::*")
	public WebElement left_chev_button;
	
	@FindBy(xpath = "//i[contains(@class, 'material-icons') and text() = 'first_page']/parent::*")
	public WebElement left_chev_first_button;


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

		browser.waitForElementToBeClickable(searchBar);
		searchBar.sendKeys(searchText);

	}
	
	public void viewSummaryPage() {
		
		firstContractLink.click();
		
		// wait for summary page to load before returning
		try {
			Thread.sleep(browser.defaultWait);

			//browser.visibilityOfElement(contractdetailspage);
			browser.waitForElementToAppear(contractdetailspage);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		browser.WaitTillElementIsPresent(summaryAttachments);
	}
}
