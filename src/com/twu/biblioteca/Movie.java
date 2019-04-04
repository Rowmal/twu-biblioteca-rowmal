package com.twu.biblioteca;

class Movie {

    private final String name;
    private final String year;
    private final String director;
    private final String rating;

    Movie(String name, String year, String director, String rating) {
        this.name = name;
        this.year = year;
        this.director = director;
        this.rating = rating;
    }

    String getName() {
        return name;
    }

    String getYear() {
        return year;
    }

    String getDirector() {
        return director;
    }

    String getRating() {
        return rating;
    }
}
