package pageobjects.solicitation;

import java.awt.AWTException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pageobjects.utils.PCDriver;
import pageobjects.utils.ReadConfig;

public class CurrentSolicitationsPage {

	public CurrentSolicitationsPage() {

		PageFactory.initElements(PCDriver.getDriver(), this);
	}

	@FindBy(xpath = "//a[contains(@href,'CurrentBids')]")
	public WebElement lnkActiveSolicitations;

	@FindBy(xpath = "//button[contains(text(),'Add Attachment')]")
	public WebElement btnAddAttachment;

	@FindBy(xpath = "//ul[@class='nav nav-tabs']")
	public WebElement lnkTopNav;

	@FindBy(xpath = "//label[@class='btn btn-wp btn-sm']")
	public WebElement btnFileUpload;

	@FindBy(xpath = "//input[contains(@class,'form-control datepicker')]")
	public List<WebElement> datePicker;

	@FindBy(xpath = "//div[contains(@id,'grp_deliveryDate')]/input")
	public WebElement DeliveryDate;

	@FindBy(xpath = "//td[text()='15']")
	public WebElement selectDate;

	@FindBy(xpath = "//iframe[@class='cke_wysiwyg_frame cke_reset']")
	public WebElement richTextIframe;

	@FindBy(xpath = "//body[@class='cke_editable cke_editable_themed cke_contents_ltr cke_show_borders']")
	public WebElement txtRichText;

	@FindBy(xpath = "//div[@id='sections']//input[@class='form-control']")
	public WebElement txtNormalText;

	@FindBy(xpath = "//select[@class='questdd']")
	public WebElement questionDropDown;

	@FindBy(xpath = "//textarea[@class='quest_input form-control']")
	public WebElement txtTextQuestion;

	@FindBy(xpath = "//td//input[@class='quest_input_checkbox'][@value='Y']")
	public WebElement quesRadioButton;

	@FindBy(xpath = "//a[contains(@href,'checkAll')]")
	public WebElement lnkCheckAll;

	@FindBy(xpath = "//button[contains(text(),'Accept')]")
	public WebElement btnAccept;

	@FindBy(xpath = "//li[contains(@class,'paginate_button')][@tabindex='0']//a")
	public List<WebElement> lnkNext;

	@FindBy(xpath = "//input[@class='form-control'][not(contains(@type,'hidden'))][not(contains(@title,'Date'))]")
	public List<WebElement> lstItems;

	@FindBy(xpath = "//input[contains(@name,'customfield')][not(contains(@type,'hidden'))]")
	public List<WebElement> lstCustomQuestions;

	@FindBy(xpath = "(//div[@class='fileupload-buttonbar']/following-sibling::div/a)[1]")
	public WebElement verifyImport;

	@FindBy(xpath = "//h3")
	public WebElement lblPageTitle;

	@FindBy(xpath = "//button[contains(text(),'Save')]")
	public WebElement btnSaveReview;
	
	@FindBy(xpath="//button[(text()='Save')]")
	public WebElement btnSave;
	
	@FindBy(xpath="//div[contains(@class,'panel-title')]")
	public WebElement lblVerifySubmission;

	public void clickOtherActiveSolicitations() {
		PCDriver.waitForElementToBeClickable(lnkActiveSolicitations);
		lnkActiveSolicitations.click();
	}

	public void selectQuestionReadioButton(String str) {
		PCDriver.waitForElementToBeClickable(quesRadioButton);
		quesRadioButton.findElement(By.xpath("//input[@class='quest_input_checkbox'][@value='" + str + "']")).click();
	}

	public void setNormalText(String str) {
		PCDriver.waitForElementToBeClickable(txtNormalText);
		txtNormalText.sendKeys(str);
	}

	public void setTextInTextQuestion(String str) {
		PCDriver.waitForElementToBeClickable(txtTextQuestion);
		txtTextQuestion.sendKeys(str);
	}

	public void selectDropDownValue() {
		PCDriver.waitForElementToBeClickable(questionDropDown);
		PCDriver.selectFromDropDownByIndex(questionDropDown, 1);

	}

	public void enterTextInRichText() {
		PCDriver.waitForElementToBeClickable(richTextIframe);
		PCDriver.switchToFrame(richTextIframe);
		txtRichText.sendKeys("abc");
		PCDriver.switchToDefaultContent();

	}

	public void clickAddAttachment() {
		PCDriver.waitForElementToBeClickable(btnAddAttachment);
		btnAddAttachment.click();
		PCDriver.switchToWindow("puw_upload");

	}

	public void selectDateField() {
		PCDriver.waitForElementToDisappear(By.xpath("//div[@class='blockUI blockOverlay']"));

		if (datePicker.size() != 0) {
			datePicker.get(0).click();
			PCDriver.waitForElementToBeClickable(selectDate);
			selectDate.click();
		}
	}

	public void clickTopNavItem(String strTabName) {
		PCDriver.waitForElementToDisappear(By.xpath("//div[@class='blockUI blockOverlay']"));
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		PCDriver.waitForElementToBeClickable(lnkTopNav);
		PCDriver.waitForElementToDisappear(By.xpath("//div[@class='blockUI blockOverlay']"));
		lnkTopNav.findElement(By.xpath(".//a[text()='" + strTabName + "']")).click();
	}

	public void uploadFileInRequirementTab(String str) throws AWTException {
		PCDriver.waitForElementToDisappear(By.xpath("//div[@class='blockUI blockOverlay']"));
		PCDriver.waitForElementToBeClickable(btnFileUpload);
		btnFileUpload.click();
		PCDriver.uploadFile(str);
		PCDriver.waitForElementToBeClickable(verifyImport);
	}

	public void EnterOverviewDetails() throws AWTException {
		try {
			PCDriver.waitForElementToDisappear(By.xpath("//div[@class='blockUI blockOverlay']"));

			try {
				PCDriver.waitForElementToBeClickable(lnkCheckAll);
				lnkCheckAll.click();
				btnAccept.click();

			} catch (Exception e) {
				System.out.println("Check All link is not present");
			} finally {
				PCDriver.waitForPageLoad();
				PCDriver.waitForElementToDisappear(By.xpath("//div[@class='blockUI blockOverlay']"));

				enterTextInRichText();
				uploadFileInRequirementTab(ReadConfig.getInstance().getExcelPath());
				selectDateField();
				setNormalText("abc");
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	public void EnterRequirementDetails() throws AWTException {
		try {
			clickTopNavItem("Requirements");
			enterTextInRichText();
			setNormalText("abc");

			selectDateField();
			uploadFileInRequirementTab(ReadConfig.getInstance().getExcelPath());
		} catch (Exception e) {
			System.out.println("Entered Details in Requirements Tab");
		}
	}

	public void EnterQuestionnaireDetails() throws AWTException {
		try {
			PCDriver.waitForElementToDisappear(By.xpath("//div[@class='blockUI blockOverlay']"));

			clickTopNavItem("Questionnaire");
			uploadFileInRequirementTab(ReadConfig.getInstance().getExcelPath());
			selectDropDownValue();
			setTextInTextQuestion("abc");
			selectQuestionReadioButton("Y");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Entered Details in Questionnaire Tab");
		}
	}

	public void EnterResponseDetails() {
		try {
			clickTopNavItem("Respond");
			System.out.println("Size is:"+lnkNext.size());
			for (int i = 0; i < lnkNext.size(); i++) {
				PCDriver.waitForElementToBeClickable(lnkNext.get(i));
				lnkNext.get(i).click();

				PCDriver.waitForPageLoad();
				PCDriver.waitForElementToBeClickable(lstItems.get(0));
				System.out.println("items size is:"+lstItems.size());
				for (int z = 0; z < lstItems.size()-1; z++) {
					try {
						lstItems.get(z).clear();
						lstItems.get(z).sendKeys("10");
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				DeliveryDate.click();
				PCDriver.waitForElementToBeClickable(selectDate);
				selectDate.click();
				try {
				for (int j = 0; j < lstCustomQuestions.size(); j++) {
					lstCustomQuestions.get(j).clear();
					lstCustomQuestions.get(j).sendKeys("abc");
				}
				}catch(Exception e) {
					e.printStackTrace();

					System.out.println("Questions are not available on page");
				}

				try {
					PCDriver.waitForElementToBeClickable(btnSaveReview);
					btnSave.click();
					//PCDriver.waitForElementToDisappear(By.xpath("//button[(text()='Save')]"));
					//lnkNext.get(lnkNext.size() - i - 1).click();

					//PCDriver.acceptAlert();
				} catch (Exception e) {
					e.printStackTrace();

					System.out.println("No Alert Present");
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean verifyRetractResponse() {
		PCDriver.acceptAlert();
		PCDriver.waitForElementToBeClickable(lblPageTitle);
		if (lblPageTitle.getText().contains("Current Solicitations")) {
			return true;
		} else {
			return false;
		}

	}
	
	public boolean verifySubmission() {
		PCDriver.waitForPageLoad();
		PCDriver.waitForElementToBeClickable(lblVerifySubmission);
		if(lblVerifySubmission.getText().contains("Submission confirmation email sent")) {
			return true;
		
		}else {
			return false;
		}
	}
}
