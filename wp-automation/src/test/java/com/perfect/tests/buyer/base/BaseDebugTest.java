package com.perfect.tests.buyer.base;

import com.codeborne.selenide.testng.TextReport;
import com.perfect.BaseTest;
import com.perfect.utils.RetryAnalyzer;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Listeners(TextReport.class)
public class BaseDebugTest extends BaseTest {

    //any test for debugging purpose can be added here
    //or this file can be deleted later

    @Test
    void configurationTest() {

        System.out.println("Current env: " + config.env());
        System.out.println("Current baseURL: " + config.baseURL());
        System.out.println("Current contract BB url: " + config.contractBB_URL());
        System.out.println("Current browser: " + config.browserName());
        System.out.println("Current language: " + config.language());
        System.out.println("Current user: " + config.buyerUsername());
        System.out.println("Current password: " + config.buyerPassword());
        System.out.println("Current headless mode " + config.headless());

        log.info("some logging using slf4j - language -> {}", config.language());

        assertThat(config.headless()).isEqualTo(true);
        assertThat(config.buyerUsername()).isNotEmpty();
        assertThat(config.buyerPassword()).isNotEmpty();
    }

    //disabled as will always fail
    //example how to use test retry
    //this test will run 2 times - first run will ignore and the second one will fail
    @Test(retryAnalyzer = RetryAnalyzer.class, enabled = false)
    void retryTest() {
        log.info("some dummy retry");
        assertThat(true).isFalse();
    }
}
