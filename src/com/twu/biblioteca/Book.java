package com.twu.biblioteca;

class Book {

    private boolean checkedOut = false;
    private final String title;
    private final String author;
    private final String pubYear;

    Book(String title, String author, String pubYear) {
        this.title = title;
        this.author = author;
        this.pubYear = pubYear;
    }

    boolean isCheckedOut() {
        return checkedOut;
    }

    void setCheckedOut() {
        this.checkedOut = true;
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
}
