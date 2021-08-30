package com.perfect.tests.buyer.paymentvoucher;

import com.codeborne.selenide.Selenide;
import com.perfect.BaseTest;
import com.perfect.common.BuyerNavBar;
import com.perfect.constants.users.Users;
import com.perfect.dtos.PaymentVoucher;
import com.perfect.pages.loginpages.LoginPage;
import com.perfect.pages.paymentvouchers.InvoiceSearchPage;
import com.perfect.pages.paymentvouchers.PaymentVoucherView;
import com.perfect.pages.paymentvouchers.VoucherCreator;
import com.perfect.utils.ResourceLoader;
import org.testng.annotations.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PaymentVoucherTest extends BaseTest {

    InvoiceSearchPage searchPage = new InvoiceSearchPage();

    LoginPage loginPage = new LoginPage();
    BuyerNavBar buyerNavBar = new BuyerNavBar();
    VoucherCreator voucherCreator = new VoucherCreator();
    PaymentVoucher voucher = new PaymentVoucher();
    PaymentVoucherView voucherView = new PaymentVoucherView();
    ResourceLoader resource;

    @BeforeClass
    public void createPaymentVoucher() {
        resource = new ResourceLoader("data/voucher");
        voucher = voucherCreator.CreatePaymentVoucher(resource);
    }

    @BeforeMethod
    public void openMainPageAndLogin() {
        Selenide.open("/");
    }

    @Test
    void searchCreatedVoucherBuyer() {
        loginPage.loginAs(Users.BUYER);
        buyerNavBar.selectDropDownItemByText(resource.getValue("navbar_headitem"), resource.getValue("navbar_subitem_search"));
        searchPage.waitForSearchFormIsLoaded();

        searchPage.checkSearchPageIsOpened()
                .setBuyerNumber(voucher.getVoucherNumber())
                .submitSearch()
                .checkSearchResults("Displaying: 1-1 / 1");
        String displayedBuyer = searchPage.getInfoFromSearchTableByColumnName("buyer");
        assertThat(displayedBuyer).contains(voucher.getVoucherNumber());
    }

    @Test
    void searchCreatedVoucherSupplier() {
        loginPage.loginAs(Users.BUYER);
        buyerNavBar.selectDropDownItemByText(resource.getValue("navbar_headitem"), resource.getValue("navbar_subitem_search"));
        searchPage.waitForSearchFormIsLoaded();

        searchPage.checkSearchPageIsOpened()
                .setSupplierInvoiceNumber(voucher.getInvoiceNumber())
                .submitSearch()
                .checkSearchResults("Displaying: 1-1 / 1");
        String displayedBuyer = searchPage.getInfoFromSearchTableByColumnName("supplierno");
        assertThat(displayedBuyer).contains(voucher.getInvoiceNumber());
    }

    @Test
    void viewPaymentVoucher() {
        loginPage.loginAs(Users.BUYER);
        buyerNavBar.selectDropDownItemByText(resource.getValue("navbar_headitem"), resource.getValue("navbar_subitem_search"));
        searchPage.waitForSearchFormIsLoaded();

        searchPage.checkSearchPageIsOpened()
                .setBuyerNumber(voucher.getVoucherNumber())
                .submitSearch()
                .checkSearchResults("Displaying: 1-1 / 1")
                .clickPaymentActionMenu()
                .clickViewAction();

        voucherView.checkPageTitle("Payment Voucher Summary")
                .checkCommodityCode(resource.getValue("commoditycode"))
                .checkJustification(resource.getValue("justification"));
    }


    @AfterMethod
    public void logout() {
        buyerNavBar.logoutViewPage();
    }

    //disabled due not possible to remove voucher
    @AfterClass(enabled = false)
    public void removeVoucher() {
        voucherCreator.removePaymentVoucher(voucher.getVoucherNumber());
    }
}
