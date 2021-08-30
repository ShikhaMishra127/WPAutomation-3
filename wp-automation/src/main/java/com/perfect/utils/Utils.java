package com.perfect.utils;

import com.github.javafaker.Faker;

public class Utils {

    static Faker faker = new Faker();

    public static String getRandomNumber(int count) {
        return faker.number().digits(count);
    }
}
