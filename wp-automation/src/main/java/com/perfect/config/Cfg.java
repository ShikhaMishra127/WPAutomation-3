package com.perfect.config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources("classpath:config.properties")
public interface Cfg extends Config {

    @DefaultValue("qa")
    String env();

    @DefaultValue("en")
    @Key("${env}.language")
    String language();

    @DefaultValue("chrome")
    @Key("${env}.browser")
    String browserName();

    @DefaultValue("10000")
    long timeout();

    @DefaultValue("1920x1080")
    String browserSize();

    @DefaultValue("true")
    boolean headless();

    @DefaultValue("false")
    boolean holdBrowserOpen();

    @Key("${env}.baseURL")
    String baseURL();

    @Key("${env}.contractBB_URL")
    String contractBB_URL();

    @Key("${env}.buyerUsername")
    String buyerUsername();

    @Key("${env}.buyerPassword")
    String buyerPassword();
}
