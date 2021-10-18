package com.perfect.utils;

public interface At {

    //interface allow us to check some elements from different page object
    //be sure that element is marked as public
    default <T> T at(Class<T> aClass) {
        try {
            return aClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
