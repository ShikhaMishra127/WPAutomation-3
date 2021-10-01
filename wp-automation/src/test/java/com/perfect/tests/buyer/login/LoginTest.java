package com.perfect.tests.buyer.login;
import com.codeborne.selenide.Selenide;
import com.perfect.BaseTest;
import com.perfect.common.BuyerNavBar;
import com.perfect.pages.loginpages.LoginPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    LoginPage loginPage = new LoginPage();
    BuyerNavBar buyerNavBar = new BuyerNavBar();

    @BeforeMethod
    public void openLoginPage() {
        Selenide.open("/");
    }

    @Test
    public void loginAsBuyer() {
        loginPage.loginAs(USER_BUYER);
    }

    @AfterMethod
    public void logout() {
        buyerNavBar.logout();
    }
}
