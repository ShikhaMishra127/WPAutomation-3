package com.perfect;

import com.codeborne.selenide.Configuration;
import com.github.javafaker.Faker;
import com.perfect.config.Cfg;
import com.perfect.config.ProjectConfig;
import org.testng.annotations.BeforeSuite;

public class BaseTest {

    Faker faker = new Faker();

    @BeforeSuite
    public void setup() {
        Cfg config = ProjectConfig.init();
        Configuration.browser = config.browserName();
        Configuration.baseUrl = config.baseURL();
        Configuration.browserSize = config.browserSize();
        Configuration.headless = config.headless();
        Configuration.holdBrowserOpen = config.holdBrowserOpen();
    }

    //current not in use, probably need to be moved in another place
    protected  <T> T at(Class<T> aClass) {
        try {
            return aClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}