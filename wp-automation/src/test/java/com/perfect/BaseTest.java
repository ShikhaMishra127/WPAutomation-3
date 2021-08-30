package com.perfect;

import com.codeborne.selenide.Configuration;
import com.github.javafaker.Faker;
import org.testng.annotations.BeforeSuite;

public class BaseTest {

    Faker faker = new Faker();

    //temporary solution, need to be refactored to accept different ENVs
    @BeforeSuite
    public void setup() {
        Configuration.browser = "chrome";
        Configuration.baseUrl = "https://internalwpqa.perfect.com/";
        Configuration.browserSize = "1920x1080";
        Configuration.headless = false;
        Configuration.holdBrowserOpen = false;
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