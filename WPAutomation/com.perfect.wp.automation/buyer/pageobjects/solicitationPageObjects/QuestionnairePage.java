package pageobjects.solicitationPageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pageobjects.utils.PCDriver;

public class QuestionnairePage {

	/******************** Questionnaire Page ********************************/
	@FindBy(xpath = "//h3[contains(text(),'Questionnaire')]")
	public WebElement verifyQuestionnairePage;

	@FindBy(id = "addsection")
	public WebElement btnAddSection;

	@FindBy(id = "sectiontitle_1")
	public WebElement txtSectionTitle;

	@FindBy(id = "sectiondesc_1")
	public WebElement txtSectionDesc;

	@FindBy(name = "add_question")
	public WebElement btnAddQues;

	@FindBy(name = "quest_text_1")
	public WebElement txtQuesText;

	@FindBy(name = "required_1")
	public WebElement chkBoxRequired;

	@FindBy(name = "enable_scoring")
	public WebElement chkBoxEnableScore;

	@FindBy(id = "sectionweight_1")
	public WebElement txtSectionWeight;

	public QuestionnairePage() {

		PageFactory.initElements(PCDriver.getDriver(), this);
	}

	public void verifyQuestionPage() {
		PCDriver.waitForElementToBeClickable(verifyQuestionnairePage);
	}

	public void EnterQuestionDetails() {
		PCDriver.waitForPageLoad();
		PCDriver.waitForElementToBeClickable(btnAddSection);
		chkBoxEnableScore.click();
		btnAddSection.click();
		PCDriver.waitForElementToBeClickable(txtSectionTitle);
		txtSectionTitle.sendKeys("abcd");
		txtSectionDesc.sendKeys("abcd");
		btnAddQues.click();
		PCDriver.waitForElementToBeClickable(chkBoxRequired);
		chkBoxRequired.click();
		txtQuesText.sendKeys("abc");
		txtSectionWeight.clear();
		txtSectionWeight.sendKeys("100");

	}
}
