package com.twu.biblioteca;

abstract class Resource {

    private boolean checkedOut = false;

    abstract public String getIdentifier();

    void checkoutResource() {
        checkedOut = true;
    }

    void returnResource() {
        checkedOut = false;
    }

    boolean isCheckedOut() {
        return checkedOut;
    }
}
