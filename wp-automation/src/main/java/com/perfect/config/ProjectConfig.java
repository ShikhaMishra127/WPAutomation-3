package com.perfect.config;

import org.aeonbits.owner.ConfigFactory;

public class ProjectConfig {
    public static Cfg init(){
        return ConfigFactory.create(Cfg.class, System.getProperties());
    }
}
