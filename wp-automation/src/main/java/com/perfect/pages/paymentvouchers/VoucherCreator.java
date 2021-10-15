package com.perfect.pages.paymentvouchers;

import com.codeborne.selenide.Selenide;
import com.perfect.common.BuyerNavBar;
import com.perfect.config.Cfg;
import com.perfect.config.ProjectConfig;
import com.perfect.dtos.PaymentVoucher;
import com.perfect.dtos.User;
import com.perfect.pages.loginpages.LoginPage;
import com.perfect.utils.ResourceLoader;
import io.qameta.allure.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import static com.perfect.pages.paymentvouchers.enums.PaymentCreatorEnums.CheckCategory.HARDWARE;
import static com.perfect.pages.paymentvouchers.enums.PaymentCreatorEnums.EFTIndicator.YES;
import static com.perfect.pages.paymentvouchers.enums.PaymentCreatorEnums.PaymentIndicator.HELD;
import static com.perfect.utils.Utils.getRandomNumber;

public class VoucherCreator {

    public static final Logger log = LoggerFactory.getLogger(VoucherCreator.class);

    Cfg config = ProjectConfig.init();

    ResourceLoader resource;
    LoginPage login;
    PaymentVoucher newVoucher;
    PaymentVoucherCreatePage voucher;
    BuyerNavBar navbar;

    public User USER_BUYER = new User(config.buyerUsername(), config.buyerPassword());

    private void setup(ResourceLoader voucherData) {

        resource = voucherData;

        login = new LoginPage();
        newVoucher = new PaymentVoucher();
        voucher = new PaymentVoucherCreatePage();
        navbar = new BuyerNavBar();
    }

    @Step
    public PaymentVoucher CreatePaymentVoucher(ResourceLoader voucherData) {

        setup(voucherData);

        String invoiceNum = getRandomNumber(12);

        Selenide.open("/");

        login.loginAs(USER_BUYER);

        navbar.selectDropDownItemByText(resource.getValue("navbar_headitem"), resource.getValue("navbar_subitem"));
        voucher.checkCreatePaymentVoucherPageIsOpened();

        voucher.setSupplierName(resource.getValue("suppliername"));

        newVoucher.setVoucherNumber(voucher.getVoucherNumber());
        newVoucher.setInvoiceNumber(invoiceNum);

        voucher.setPaymentJustification(resource.getValue("justification"))
                .selectPaymentIndicator(HELD)
                .selectEFTIndicator(YES)
                .selectCheckCategory(HARDWARE);

        ZonedDateTime dateNow = ZonedDateTime.now();
        ZonedDateTime dateTomorrow = dateNow.plusDays(1);
        String inputDateNow = DateTimeFormatter.ofPattern("MM/dd/yyyy").format(dateNow);
        String inputDateTomorrow = DateTimeFormatter.ofPattern("MM/dd/yyyy").format(dateTomorrow);


        voucher.setReceiveDate(inputDateNow)
                .setPostDate(inputDateTomorrow)
                .setIssueDate(inputDateNow)
                .setDueDate(inputDateTomorrow)
                .addItemsToVoucher(resource.getValue("commoditycode"))
                .checkCommodityCode(resource.getValue("commoditycode"))
                .setSupplierInvoiceNumber(invoiceNum)
                .setQuantity(resource.getValue("quantity"))
                .setUnitPrice(resource.getValue("unitprice"))
                .setUnitDropdown(resource.getValue("unit"))
                .setManufacturer(resource.getValue("manufacturer"))
                .setSupplierPartNumber(resource.getValue("manufacturerpartnumber"))
                .setComments(resource.getValue("comments"))
                .addAccountDistribution(resource.getValue("accountdistribution"))
                .clickSubmitButton();

        navbar.logout();

        log.info("Payment voucher {} is created.", newVoucher.getVoucherNumber());

        return newVoucher;
    }

    @Step
    public void removePaymentVoucher(String voucherNumber) {

        InvoiceSearchPage search = new InvoiceSearchPage();


        login = new LoginPage();
        newVoucher = new PaymentVoucher();
        voucher = new PaymentVoucherCreatePage();
        navbar = new BuyerNavBar();

        Selenide.open("/");

        // log in and go to list of current vouchers
        login.loginAs(USER_BUYER);
        //Open search form and submit some search keywords
        navbar.selectDropDownItemByText(resource.getValue("navbar_headitem"), resource.getValue("navbar_subitem"));

        search.checkSearchPageIsOpened()
                .setBuyerNumber(voucherNumber)
                .submitSearch()
                .checkSearchResults("Displaying: 1-1 / 1")
                .clickPaymentActionMenu()
                .clickDeleteAction()
                .checkSearchResults("Displaying: 0-0 / 0");
        navbar.logout();
    }
}
