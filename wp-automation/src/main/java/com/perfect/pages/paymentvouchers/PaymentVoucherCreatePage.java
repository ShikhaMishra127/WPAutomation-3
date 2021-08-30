package com.perfect.pages.paymentvouchers;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.perfect.pages.paymentvouchers.enums.PaymentCreatorEnums.CheckCategory;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$x;
import static com.perfect.pages.paymentvouchers.enums.PaymentCreatorEnums.CheckCategory.*;
import static com.perfect.pages.paymentvouchers.enums.PaymentCreatorEnums.EFTIndicator;
import static com.perfect.pages.paymentvouchers.enums.PaymentCreatorEnums.EFTIndicator.NO;
import static com.perfect.pages.paymentvouchers.enums.PaymentCreatorEnums.EFTIndicator.YES;
import static com.perfect.pages.paymentvouchers.enums.PaymentCreatorEnums.PaymentIndicator;
import static com.perfect.pages.paymentvouchers.enums.PaymentCreatorEnums.PaymentIndicator.*;
import static org.assertj.core.api.Assertions.*;

public class PaymentVoucherCreatePage {



    SelenideElement supplierNameInput = $x("//input[@aria-label='Supplier']");

    SelenideElement supplierDropDown = $x("//typeahead-container[@role='listbox']");

    SelenideElement supplierButton = $x("//typeahead-container[@role='listbox']/button");

    SelenideElement justificationInput = $x("//textarea[@rows='3']");

    //payment indicator dropdown
    SelenideElement paymentIndicatorDropdown = $x("//label[contains(text(),'Payment Indicator')]//..//button");

    //EFT indicator dropdown
    SelenideElement EFTIndicatorDropdown = $x("//label[contains(text(),'EFT Indicator')]//..//button");

    //Category dropdown
    SelenideElement checkCategoryDropdown = $x("//label[contains(text(),'Check Category')]//..//button");

    //common elements for Payment Indicator/EFT/Category dropdowns
    SelenideElement dropdownSearchInput = $x("//input[@name='search-text']");
    SelenideElement searchedItem = $x("//ul[@class='available-items']/li");

    //Payment inputs
    SelenideElement receiveDateInput = $x("//input[@placeholder='Enter Receive Date']");
    SelenideElement postDateInput = $x("//input[@placeholder='Enter Post Date']");
    SelenideElement issueDateInput = $x("//input[@placeholder='Enter Issue Date']");
    SelenideElement dueDateInput = $x("//input[@placeholder='Enter Due Date']");

    //Add items to payment & other related locators: modal window, etc
    SelenideElement addItemsButton = $x("//button[contains(text(),'Add Items')]");
    SelenideElement modalWindow = $x("//div[@class='modal-content']");
    SelenideElement contractCheckbox = $x("//label[@for='contractNumberEnable']");
    SelenideElement commoditySearchInput = $x("//div[@class='modal-content']//input[@type='text']");
    SelenideElement commodityCheckBox = $x("//table//input[@type='checkbox']");
    SelenideElement modalDoneButton = $x("//wp-modal-footer//button");

    SelenideElement supplierInvoiceNumberInput = $x("//label[contains(text(),'Supplier Invoice No.')]//..//input");
    SelenideElement quantityInput = $x("//label[contains(text(),'Quantity')]//..//input");
    SelenideElement unitPriceInput =$x("//label[contains(text(),'Unit Price')]//..//input");

    SelenideElement unitDropdown = $x("//label[contains(text(),'Unit')]//..//button");

    SelenideElement manufacturerInput = $x("//label[contains(text(),'Manufacturer')]//..//input");
    SelenideElement supplierPartNumberInput = $x("//label[contains(text(),'Supplier Part Number')]//..//input");
    SelenideElement commentsInput = $x("//label[contains(text(),'Comments')]//..//textarea");

    SelenideElement submitButton = $x("//button[contains(text(), 'Submit')]");

    //account distribution selectors
    SelenideElement addAccountDistributionButton =  $x("//button[contains(text(), 'Add Account Distribution')]");
    SelenideElement addAccountCodesModalTitle = $x("//h4[contains(text(), 'Add Account Codes')]");
    SelenideElement costCenterInput = $x("//input[@typeaheadoptionfield='costCenter.description']");
    SelenideElement firstSearchResultCostCenter = $x("//typeahead-container//button");
    SelenideElement distributeEvenlyButton = $x("//wp-modal-content//button[contains(text(), 'Distribute Evenly')]");
    SelenideElement accountDistributionSaveButton = $x("//div[@class='modal-content']//button[contains(text(), 'Save')]");


    public PaymentVoucherCreatePage checkCreatePaymentVoucherPageIsOpened() {
        SelenideElement voucherCreateFormTitle = $x("//span[contains(text(),'Create Payment Voucher')]");
        voucherCreateFormTitle.shouldBe(visible).shouldHave(text("Create Payment Voucher"));
        return this;
    }

    public PaymentVoucherCreatePage setSupplierName(String suppliername) {
        supplierNameInput.setValue(suppliername);
        supplierDropDown.shouldBe(visible);
        supplierButton.click();
        return this;
    }

    public String getVoucherNumber() {
        SelenideElement voucherNumberInput = $x("//label[contains(text(),'Buyer Payment Voucher No.')]/..//input[@type='text']");
        return voucherNumberInput.getAttribute("value");
    }

    public PaymentVoucherCreatePage setPaymentJustification(String justification) {
        justificationInput.setValue(justification);
        return this;
    }

    public PaymentVoucherCreatePage selectPaymentIndicator(PaymentIndicator indicator) {

        switch (indicator) {
            case HELD:
                paymentIndicatorDropdown.click();
                dropdownSearchInput.setValue(HELD.getValue());
                searchedItem.click();
                break;
            case COMBINED:
                paymentIndicatorDropdown.click();
                dropdownSearchInput.setValue(COMBINED.getValue());
                searchedItem.click();
                break;
            case SINGLE:
                paymentIndicatorDropdown.click();
                dropdownSearchInput.setValue(SINGLE.getValue());
                searchedItem.click();
                break;
        }
        return this;
    }

    public PaymentVoucherCreatePage selectEFTIndicator(EFTIndicator indicator) {

        switch (indicator) {
            case YES:
                EFTIndicatorDropdown.click();
                dropdownSearchInput.setValue(YES.getValue());
                searchedItem.click();
                break;
            case NO:
                EFTIndicatorDropdown.click();
                dropdownSearchInput.setValue(NO.getValue());
                searchedItem.click();
        }
        return this;
    }

    public PaymentVoucherCreatePage selectCheckCategory(CheckCategory category) {
        switch (category) {
            case HARDWARE:
                checkCategoryDropdown.click();
                dropdownSearchInput.setValue(HARDWARE.getValue());
                searchedItem.click();
                break;
            case LEGAL:
                checkCategoryDropdown.click();
                dropdownSearchInput.setValue(LEGAL.getValue());
                searchedItem.click();
                break;
            case RENT:
                checkCategoryDropdown.click();
                dropdownSearchInput.setValue(RENT.getValue());
                searchedItem.click();
                break;
            case SERVICES:
                checkCategoryDropdown.click();
                dropdownSearchInput.setValue(SERVICES.getValue());
                searchedItem.click();
                break;
            case PETTYCASH:
                checkCategoryDropdown.click();
                dropdownSearchInput.setValue(PETTYCASH.getValue());
                searchedItem.click();
            case MISC:
                checkCategoryDropdown.click();
                dropdownSearchInput.setValue(MISC.getValue());
                searchedItem.click();
                break;
        }
        return this;
    }

    public PaymentVoucherCreatePage setReceiveDate(String inputDateNow) {
        receiveDateInput.setValue(inputDateNow);
        return this;
    }

    public PaymentVoucherCreatePage setPostDate(String inputDateTomorrow) {
        postDateInput.setValue(inputDateTomorrow);
        return this;
    }

    public PaymentVoucherCreatePage setIssueDate(String inputDateNow) {
        issueDateInput.setValue(inputDateNow);
        return this;
    }

    public PaymentVoucherCreatePage setDueDate(String inputDateTomorrow) {
        dueDateInput.setValue(inputDateTomorrow);
        return this;
    }

    public PaymentVoucherCreatePage addItemsToVoucher(String commodityCode) {
        addItemsButton.click();
        modalWindow.shouldBe(visible);
        contractCheckbox.click();
        commoditySearchInput.setValue(commodityCode);
        commodityCheckBox.shouldBe(visible);
        commodityCheckBox.click();
        modalDoneButton.click();
        modalDoneButton.shouldBe(hidden);
        return this;
    }

    public PaymentVoucherCreatePage checkCommodityCode(String commodityCode) {
        SelenideElement commoditySelector = $x("//span[contains(text(),'"+commodityCode+"')]");
        String resultedCode = commoditySelector.getText();
        assertThat(resultedCode).contains(commodityCode);
        return this;
    }

    public PaymentVoucherCreatePage setSupplierInvoiceNumber(String invoiceNum) {
        supplierInvoiceNumberInput.setValue(invoiceNum);
        return this;
    }

    public PaymentVoucherCreatePage setQuantity(String quantity) {
        quantityInput.setValue(quantity);
        return this;
    }

    public PaymentVoucherCreatePage setUnitPrice(String unitPrice) {
        unitPriceInput.setValue(unitPrice);
        return this;
    }

    public PaymentVoucherCreatePage setUnitDropdown(String unit) {
        unitDropdown.click();
        dropdownSearchInput.setValue(unit);
        searchedItem.click();
        return this;
    }

    public PaymentVoucherCreatePage setManufacturer(String manufacturer) {
        manufacturerInput.setValue(manufacturer);
        return this;
    }

    public PaymentVoucherCreatePage setSupplierPartNumber(String partNumber) {
        supplierPartNumberInput.setValue(partNumber);
        return this;
    }

    public PaymentVoucherCreatePage setComments(String comments) {
        commentsInput.setValue(comments);
        return this;
    }

    public PaymentVoucherCreatePage addAccountDistribution(String costCenter) {
        addAccountDistributionButton.scrollTo().click();
        addAccountCodesModalTitle.shouldBe(visible);
        costCenterInput.setValue(costCenter);
        firstSearchResultCostCenter.shouldBe(visible).click();
        Selenide.sleep(2000);
        distributeEvenlyButton.click();
        accountDistributionSaveButton.click();
        return this;
    }

    public PaymentVoucherCreatePage clickSubmitButton() {
        submitButton.scrollTo().click();
        submitButton.shouldBe(hidden);
        return this;
    }
}
