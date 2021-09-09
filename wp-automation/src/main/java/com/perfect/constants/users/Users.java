package com.perfect.constants.users;

public enum Users {
    BUYER("autobuyer", "Automation123!"),
    //INCORRECT_BUYER("autobuyerlol" ,"incorrect_password"),
    SUPPLIER("autosupplier", "Automation123!");

    private final String userName;
    private final String password;

    Users(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}
