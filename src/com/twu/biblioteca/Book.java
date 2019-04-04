package com.twu.biblioteca;

class Book {

    private final String title;
    private final String author;
    private final String pubYear;
    private boolean checkedOut = false;

    Book(String title, String author, String pubYear) {
        this.title = title;
        this.author = author;
        this.pubYear = pubYear;
    }

    String getPubYear() {
        return pubYear;
    }

    String getAuthor() {
        return author;
    }

    String getTitle() {
        return title;
    }

    void setCheckedOut(boolean checkedOut) {
        this.checkedOut = checkedOut;
    }

    boolean isCheckedOut() {
        return checkedOut;
    }
}
