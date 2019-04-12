package com.twu.biblioteca;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return title.equals(book.title) &&
                author.equals(book.author) &&
                pubYear.equals(book.pubYear);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author, pubYear);
    }
}
