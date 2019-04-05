package com.twu.biblioteca;

class User {

    private final String libNum;
    private final String password;
    private final String name;
    private final String email;
    private final String phoneNum;

    User(String libNum, String password, String name, String email, String phoneNum) {
        this.libNum = libNum;
        this.password = password;
        this.name = name;
        this.email = email;
        this.phoneNum = phoneNum;
    }

    String getLibNum() {
        return libNum;
    }

    String getPassword() {
        return password;
    }

    String getName() {
        return name;
    }

    String getEmail() {
        return email;
    }

    String getPhoneNum() {
        return phoneNum;
    }
}
