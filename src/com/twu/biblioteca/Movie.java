package com.twu.biblioteca;

import java.util.Objects;

class Movie extends Resource {

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

    @Override
    public String getIdentifier() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return name.equals(movie.name) &&
                year.equals(movie.year) &&
                director.equals(movie.director) &&
                rating.equals(movie.rating);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, year, director, rating);
    }
}
