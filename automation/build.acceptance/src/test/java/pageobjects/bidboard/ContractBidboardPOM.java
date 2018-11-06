package pageobjects.bidboard;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.common.Browser;

public class ContractBidboardPOM {

	private final Browser browser;
	
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
	
	@FindBy(xpath = BidBoardDetail + "/div[1]/div/div/div[2]/div/div[1]/h5")
	public WebElement summaryHeader;
	
	@FindBy(xpath = BidBoardDetail + "/div[1]/div/div/div[2]/div/div[14]/h2/i/parent::*/parent::*/div/ul")
	public WebElement summaryAttachments;

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
	
	public void searchContracts(String searchText) {

		browser.waitForElementToBeClickable(searchBar);
		searchBar.sendKeys(searchText);

	}
}
