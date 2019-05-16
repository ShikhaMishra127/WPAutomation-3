package testcases.buyer.contract;

import framework.Contract;
import org.openqa.selenium.support.ui.Select;
import pageobjects.buyer.contract.NewContractPOM;
import pageobjects.common.BuyerNavBarPOM;
import pageobjects.common.CommodityPickerPOM;
import pageobjects.common.LoginPagePOM;
import utilities.common.Browser;
import utilities.common.ResourceLoader;
import utilities.common.UniqueID;

import java.time.ZonedDateTime;

public class ContractCreator {

    Browser browser;
    ResourceLoader resource;
    BuyerNavBarPOM navbar;
    LoginPagePOM login;
    Contract newcontract;
    NewContractPOM contract;
    CommodityPickerPOM commodity;


    public Contract CreateContract(String contractdata) {

        setup(contractdata);
        browser.getDriver().get(browser.baseUrl);

        login.loginAsBuyer();

        headerStep();
        notificationStep();
        clausesStep();
        catalogItemsStep();
        documentsStep();
        authorizationStep();
        summaryStep();

        navbar.logout();
        browser.close();

        return newcontract;
    }



    private void setup(String contractdata) {

        browser = new Browser();
        resource = new ResourceLoader(contractdata);
        navbar = new BuyerNavBarPOM(browser);
        login = new LoginPagePOM(browser);
        contract = new NewContractPOM(browser);
        commodity = new CommodityPickerPOM(browser);
        newcontract = new Contract();

    }

    private void headerStep() {

        navbar.selectDropDownItem(resource.getValue("navbar_headitem"), resource.getValue("navbar_subitem"));

        UniqueID contractNum = new UniqueID(UniqueID.IDType.DATE);

        String title = "Automated Contract " + contractNum.getNumber();

        newcontract.setContractNumber(contractNum.getNumber());

        contract.headContractTitleEdit.sendKeys(title);
        new Select(contract.headContractVisibilityDrop).selectByValue("Public");
        new Select(contract.headContractTypeDrop).selectByIndex(1);

        String longdesc = "This is a long description for " + title;

        newcontract.setContractLongDesc(longdesc);
        contract.headLongDescEdit.sendKeys(longdesc);

        contract.headerContractNumber.sendKeys(contractNum.getNumber());

        // Add commodities
        contract.headerCommoditiesButton.click();
        commodity.selectCommodityByCode("05200");
        commodity.selectCommodityByCode("06510");
        commodity.selectCommodityByCode("02204");
        commodity.selectCommodityByCode("00500");
        commodity.commodityCloseButton.click();

        // Add contractor
        browser.ClickWhenClickable(contract.headerContractorSearchButton);

        // set focus to pop-up
        String parentWindow = browser.driver.getWindowHandle();
        browser.SwitchToPopUp(parentWindow);

        browser.waitForElementToAppear(contract.headerSupplierSearchEdit);

        contract.headerSupplierSearchEdit.sendKeys("AutoSupplier");
        contract.headerSupplierSearchButton.click();
        browser.ClickWhenClickable(contract.headerSupplierSearchCheck);

        // switch focus back to main window
        browser.switchTo().window(parentWindow);

        // Add pricing information
        new Select(contract.headerPricingTypeDrop).selectByValue("Fixed Price");
        new Select(contract.headerPricingConditionDrop).selectByValue("Estimate");
        browser.InjectJavaScript("arguments[0].value=arguments[1]", contract.headerPricingTotalValueEdit, "1500.00" );

        // Add contract dates

        ZonedDateTime startDate = browser.getDateTimeNowInUsersTimezone();
        ZonedDateTime endDate = browser.getDateTimeNowInUsersTimezone().plusDays(30);

        browser.InjectJavaScript("arguments[0].value=arguments[1]", contract.headerIssueDateEdit, startDate.format(newcontract.inputBoxFormatter) );
        browser.InjectJavaScript("arguments[0].value=arguments[1]", contract.headerAwardDateEdit, startDate.format(newcontract.inputBoxFormatter) );
        browser.InjectJavaScript("arguments[0].value=arguments[1]", contract.headerEffectiveDateEdit, startDate.format(newcontract.inputBoxFormatter) );
        browser.InjectJavaScript("arguments[0].value=arguments[1]", contract.headerExpirationDateEdit, endDate.format(newcontract.inputBoxFormatter) );
        browser.InjectJavaScript("arguments[0].value=arguments[1]", contract.headerProjectedDateEdit, endDate.format(newcontract.inputBoxFormatter) );

        newcontract.setContractDateAward(startDate);
        newcontract.setContractDateEffective(startDate);
        newcontract.setContractDateExpiration(endDate);
        newcontract.setContractDateProjected(endDate);

        browser.ClickWhenClickable(contract.nextStepButton);
    }

    private void notificationStep() {
        browser.ClickWhenClickable(contract.nextStepButton);
    }

    private void clausesStep() {
        browser.ClickWhenClickable(contract.clausesNextButton);
    }

    private void catalogItemsStep() {
        browser.ClickWhenClickable(contract.nextStepButton);
    }

    private void documentsStep() {

        browser.clickWhenAvailable(contract.attachDocFromLibButton);

        // set focus to pop-up
        String parentWindow = browser.driver.getWindowHandle();
        browser.SwitchToPopUp(parentWindow);

        contract.addFileFromLibrary("contract private attachment.txt");
        contract.addFileFromLibrary("contract private Visible to Contractor.txt");
        contract.addFileFromLibrary("contract public attachment.txt");

        contract.attachLibSaveButton.click();

        // switch focus back to main window
        browser.switchTo().window(parentWindow);

        contract.setFileVisibility("contract private attachment.txt", true, false);
        contract.setFileVisibility("contract private Visible to Contractor.txt", true, true);
        contract.setFileVisibility("contract public attachment.txt", false, false);

        browser.ClickWhenClickable(contract.nextStepButton);
    }

    private void authorizationStep() {

        // click the top-level borg for authorization
        browser.clickWhenAvailable(contract.authFirstOrgCheckbox);

        // click add all Organizations button
        //browser.clickWhenAvailable(contract.authSelectAllOrgsButton);

        // click 'Finished'
        browser.ClickWhenClickable(contract.authFinishedButton);
    }

    private void summaryStep() {
        // submit contract to workflow
        browser.clickWhenAvailable(contract.summarySubmitButton);
    }

}