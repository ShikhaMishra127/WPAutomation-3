package com.perfect.pages.paymentvouchers;

import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class InvoiceSearchPage {

    SelenideElement submitButton = $x("//button[@onclick='javascript:submitPage()']");

    SelenideElement resetButton = $x("//button[@onclick='javascript:resetPage()']");

    SelenideElement buyerNumberInput = $x("//input[@id='binvoicenum']");

    SelenideElement supplierInvoiceNumberInput = $x("//label[contains(text(),'Supplier No.')]//..//input");

    SelenideElement searchedItemActionMenu = $x("//table[@id='invTable']//tr/td[9]//button");

    SelenideElement searchedElementDeleteButton = $x("//table[@id='invTable']//tr/td[9]//ul//a[contains(text(), 'Delete')]");

    SelenideElement searchedElementEditButton = $x("//table[@id='invTable']//tr/td[9]//ul//a[contains(text(), 'Edit')]");

    SelenideElement searchedElementViewButton = $x("//table[@id='invTable']//tr/td[9]//ul//a[contains(text(), 'View')]");

    SelenideElement confirmDeletePayment = $x("//button[@data-bb-handler='confirm']");

    public InvoiceSearchPage clickSubmitButton() {
        submitButton.click();
        return this;
    }

    public InvoiceSearchPage clickResetButton() {
        resetButton.click();
        return this;
    }

    public InvoiceSearchPage waitForSearchFormIsLoaded() {
        submitButton.shouldBe(visible, Duration.ofSeconds(3));
        resetButton.shouldBe(visible);
        return this;
    }


    public InvoiceSearchPage checkSearchPageIsOpened() {
        SelenideElement searchPageTitle = $x("//*[@id='page-title']//span[contains(text(), 'Invoice/Credit/Payment Voucher List')]");
        searchPageTitle.shouldBe(visible);
        return this;
    }

    public InvoiceSearchPage setBuyerNumber(String voucherNumber) {
        buyerNumberInput.setValue(voucherNumber);
        return this;
    }

    public InvoiceSearchPage setSupplierInvoiceNumber(String invoiceNumber) {
        supplierInvoiceNumberInput.setValue(invoiceNumber);
        return this;
    }

    public InvoiceSearchPage submitSearch() {
        submitButton.click();
        return this;
    }

    public InvoiceSearchPage checkSearchResults(String search) {
        SelenideElement searchResultCount = $x("//*[@id='solTable_info']");
        String resultedCode = searchResultCount.shouldBe(visible).getText();
        assertThat(resultedCode).isEqualTo(search);
        return this;
    }

    public InvoiceSearchPage clickPaymentActionMenu() {
        searchedItemActionMenu.click();
        return this;
    }

    public InvoiceSearchPage clickDeleteAction() {
        searchedElementDeleteButton.click();
        confirmDeletePayment.click();
        return this;
    }

    public String getInfoFromSearchTableByColumnName(String columnName) {
        String cellValue;
        SelenideElement column;

        switch (columnName) {
            case "organization":
                column = $x("//table[@id='invTable']//tr/td[2]");
                column.shouldBe(visible);
                cellValue = column.getText();
                break;
            case "buyer":
                column = $x("//table[@id='invTable']//tr/td[3]");
                column.shouldBe(visible);
                cellValue = column.getText();
                break;
            case "supplierno":
                column = $x("//table[@id='invTable']//tr/td[4]");
                column.shouldBe(visible);
                cellValue = column.getText();
                break;
            case "totalusd":
                column = $x("//table[@id='invTable']//tr/td[5]");
                column.shouldBe(visible);
                cellValue = column.getText();
                break;
            case "supplier":
                column = $x("//table[@id='invTable']//tr/td[7]");
                column.shouldBe(visible);
                cellValue = column.getText();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + columnName);
        }
        return cellValue;
    }

    public InvoiceSearchPage clickViewAction() {
        searchedElementViewButton.shouldBe(visible).click();
        return this;
    }
}
