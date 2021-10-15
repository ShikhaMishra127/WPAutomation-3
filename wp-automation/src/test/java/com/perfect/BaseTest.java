package com.perfect;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.github.javafaker.Faker;
import com.perfect.config.Cfg;
import com.perfect.config.ProjectConfig;
import com.perfect.dtos.User;
import io.qameta.allure.selenide.AllureSelenide;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeSuite;

public class BaseTest {

    public static final Logger log = LoggerFactory.getLogger(BaseTest.class);

    public Cfg config = ProjectConfig.init();

    Faker faker = new Faker();

    public User USER_BUYER = new User(config.buyerUsername(), config.buyerPassword());

    @BeforeSuite
    public void setup() {
        Configuration.browser = config.browserName();
        Configuration.baseUrl = config.baseURL();
        Configuration.browserSize = config.browserSize();
        Configuration.headless = config.headless();
        Configuration.holdBrowserOpen = config.holdBrowserOpen();

        //add AllureReport Selenide test listener
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(false));
    }
}