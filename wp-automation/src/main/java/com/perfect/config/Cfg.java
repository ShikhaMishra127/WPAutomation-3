package com.perfect.config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources("classpath:config.properties")
public interface Cfg extends Config {

    @DefaultValue("")
    String env();

    @DefaultValue("en")
    String language();

    @DefaultValue("chrome")
    String browserName();

    @DefaultValue("10000")
    long timeout();

    @DefaultValue("1920x1080")
    String browserSize();

    @DefaultValue("false")
    boolean headless();

    @DefaultValue("true")
    boolean holdBrowserOpen();

    @DefaultValue("https://internalwpqa.perfect.com")
    String baseURL();
}
