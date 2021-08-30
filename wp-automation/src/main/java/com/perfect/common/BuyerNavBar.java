package com.perfect.common;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class BuyerNavBar {

    SelenideElement topNavigationBar = $x("//ul[contains(@class,'navbar-left')]");


    public void selectDropDownItemByText(String headerItem, String subItem) {

        topNavigationBar.shouldBe(visible);

        String headerXPath = "//ul[contains(@class,'navbar-left')]//*[@title='" + headerItem + "']";
        String subItemXPath = headerXPath + "/following-sibling::*//*[@title='" + subItem + "']";

        //click main element
        $(byXpath(headerXPath)).click();
        //click sub-element
        $(byXpath(subItemXPath)).click();
    }

    public void logout() {
        topNavigationBar.shouldBe(visible);
        $("#userMenu").shouldBe(visible).click();
        $(byTitle("Logout")).shouldBe(visible).click();
        Selenide.confirm();
        topNavigationBar.shouldBe(hidden);
        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();
    }

    public void logoutViewPage() {
        $x("//div[@class='container']/div[@class='row']/div[2]/div[2]")
                .shouldBe(visible)
                .click();
        $(byText("Logout")).shouldBe(visible).click();
        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();
    }
}
