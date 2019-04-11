package com.twu.biblioteca;

class Book extends Resource {

    private final String title;
    private final String author;
    private final String pubYear;

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

    @Override
    public String getIdentifier() {
        return title;
    }
}
