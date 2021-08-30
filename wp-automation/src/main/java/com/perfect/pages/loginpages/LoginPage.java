package com.perfect.pages.loginpages;

import com.perfect.constants.users.Users;

import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage{

    String cookieModal = "#cookieModal";
    public String loginButton = "#login-submit";
    String addLinksButton = "#addlinkbutton";

    //click accept cookies message
    private void clickAcceptCookies() {
       $(cookieModal).shouldBe(visible);
       $(byName("saveCookieSettings")).click();
       $(cookieModal).shouldBe(hidden);
    }

    //fill login with credentials and press login
    private void fillLoginForm(String userName, String password) {

        clickAcceptCookies();

        $("#visibleUname").setValue(userName);
        $("#visiblePass").setValue(password);
        $(loginButton).click();
        $(loginButton).shouldBe(hidden);
        $(addLinksButton).shouldBe(visible);
    }

    //login as predefined users in Users class
    public void loginAs(Users users) {
        String userName = users.getUserName();
        String userPassword = users.getPassword();

        fillLoginForm(userName, userPassword);
    }

    //login as any user, can be useful for debug/specific checks etc
    public void loginByCredentials(String userName, String password) {
        fillLoginForm(userName, password);
    }
}
