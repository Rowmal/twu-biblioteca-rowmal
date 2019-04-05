package com.twu.biblioteca;

class User {

    private final String libNum;
    private final String password;

    User(String libNum, String password) {
        this.libNum = libNum;
        this.password = password;
    }

    String getLibNum() {
        return libNum;
    }

    String getPassword() {
        return password;
    }
}
