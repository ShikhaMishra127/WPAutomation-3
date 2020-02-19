package testcases.buyer.contract;

import framework.Contract;
import org.openqa.selenium.support.ui.Select;
import pageobjects.buyer.contract.NewContractPOM;
import pageobjects.buyer.common.BuyerNavBarPOM;
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


    private void setup(Browser inBrowser, ResourceLoader contractdata) {

        browser = inBrowser;
        resource = contractdata;
        navbar = new BuyerNavBarPOM(browser);
        login = new LoginPagePOM(browser);
        contract = new NewContractPOM(browser);
        commodity = new CommodityPickerPOM(browser);

        newcontract = new Contract();
    }

    public Contract CreateContract(Browser inBrowser, ResourceLoader contractData) {

        setup(inBrowser, contractData);

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


    private void headerStep() {

        navbar.selectDropDownItem(resource.getValue("navbar_headitem"), resource.getValue("navbar_subitem_create"));

        UniqueID contractNum = new UniqueID(UniqueID.IDType.DATE);

        String title = resource.getValue("contract_name") + " " + contractNum.getNumber();
        String longdesc = resource.getValue("contract_longdesc") + " " + title;

        // fill our contract object with values from .properties and ID we just generated
        newcontract.setContractNumber(contractNum.getNumber());
        newcontract.setContractName(title);
        newcontract.setContractLongDesc(longdesc);
        newcontract.setVisibility(resource.getValue("contract_visibility"));
        newcontract.setContractType(resource.getValue("contract_type"));
        newcontract.setContractSupplier(browser.supplierName);
        newcontract.setContractPricingType(resource.getValue("contract_pricingtype"));
        newcontract.setContractTotalValue(resource.getValue("contract_totalvalue"));
        newcontract.setContractValueFormatted(resource.getValue("contract_totalvalueformatted"));

        browser.sendKeysWhenAvailable(contract.headContractTitleEdit, title);

        new Select(contract.headContractTypeDrop).selectByIndex(1);
        new Select(contract.headContractVisibilityDrop).selectByVisibleText(newcontract.getContractVisibility());

        browser.sendKeysWhenAvailable(contract.headLongDescEdit, longdesc);

        // if a ridiculous pop-up comes up, telling us the contract is public, close it before continuing
        if (browser.elementExists(contract.headPopupCloseButton)) {
            contract.headPopupCloseButton.click();
        }

        browser.sendKeysWhenAvailable(contract.headerContractNumber, contractNum.getNumber());

        // Add a list of comma-separated commodity codes to add to contract
        browser.clickWhenAvailable(contract.headerCommoditiesButton);
        commodity.addCodes(resource.getValue("contract_commodities"));

        // Add contractor
        browser.clickWhenAvailable(contract.headerContractorSearchButton);

        // set focus to pop-up
        String parentWindow = browser.driver.getWindowHandle();
        browser.SwitchToPopUp(parentWindow);

        browser.waitForElementToAppear(contract.headerSupplierSearchEdit);

        contract.headerSupplierSearchEdit.sendKeys(newcontract.getContractSupplier());
        contract.headerSupplierSearchButton.click();
        browser.clickWhenAvailable(contract.headerSupplierSearchCheck);

        // switch focus back to main window
        browser.switchTo().window(parentWindow);

        // Add pricing information
        new Select(contract.headerPricingTypeDrop).selectByValue(newcontract.getContractPricingType());
        new Select(contract.headerPricingConditionDrop).selectByValue(resource.getValue("contract_pricingcondition"));
        browser.InjectJavaScript("arguments[0].value=arguments[1]", contract.headerPricingTotalValueEdit, newcontract.getContractTotalValue() );


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

        browser.clickWhenAvailable(contract.nextStepButton);
    }

    private void notificationStep() {
        browser.clickWhenAvailable(contract.nextStepButton);
    }

    private void clausesStep() {
        browser.clickWhenAvailable(contract.clausesNextButton);
    }

    private void catalogItemsStep() {
        browser.clickWhenAvailable(contract.nextStepButton);
    }

    private void documentsStep() {

        browser.clickWhenAvailable(contract.attachDocFromLibButton);

        // set focus to pop-up
        String parentWindow = browser.driver.getWindowHandle();
        browser.SwitchToPopUp(parentWindow);

        contract.addFilesFromLibrary(resource.getValue("contract_attachments"));

        browser.clickWhenAvailable(contract.attachLibSaveButton);

        // switch focus back to main window
        browser.switchTo().window(parentWindow);

        contract.setFileVisibility(resource.getValue("contract_attachments"));

        browser.clickWhenAvailable(contract.nextStepButton);
    }

    private void authorizationStep() {

        // click the top-level borg for authorization
        browser.clickWhenAvailable(contract.authFirstOrgCheckbox);

        // click add all Organizations button
        //browser.clickWhenAvailable(contract.authSelectAllOrgsButton);

        // click 'Finished'
        browser.clickWhenAvailable(contract.authFinishedButton);
    }

    private void summaryStep() {
        // submit contract to workflow
        browser.clickWhenAvailable(contract.summarySubmitButton);

        browser.Log("Contract '" + newcontract.getContractNumber() + "' created");
    }

}