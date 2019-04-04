package com.twu.biblioteca;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// TODO: Print to console using PrintWriter
public class BibliotecaApp {

    private static final Book[] books = new Book[4];
    private static final Movie[] movies = new Movie[4];

    public static void main(String[] args) throws IOException {
        String[] titles = {"A Game of Thrones", "Nineteen Eighty-Four", "The Metamorphosis", "Wuthering Heights"};
        String[] pubYears = {"1996", "1949", "1915", "1847"};
        String[] authors = {"George R. R. Martin", "George Orwell", "Franz Kafka", "Emily Brontë"};
        String[] names = {"Ghostbusters", "Event Horizon", "The Witch", "Under the Skin"};
        String[] years = {"1984", "1997", "2015", "2013"};
        String[] directors = {"Ivan Reitman", "Paul W. S. Anderson", "Robert Eggers", "Jonathan Glazer"};
        String[] ratings = {"9", "3", "8", "7"};
        for (int i = 0; i < 4; i++) {
            books[i] = new Book(titles[i], authors[i], pubYears[i]);
            movies[i] = new Movie(names[i], years[i], directors[i], ratings[i]);
        }

        System.out.println("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!");

        String[] menuOptions = {"[1] List of books", "[2] Quit", "[3] Checkout book", "[4] Return a book",
                "[5] List of movies", "[6] Checkout movie"};
        for (String menuOption : menuOptions) System.out.println(menuOption);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String menuOption;

        menuOptionLoop:
        while ((menuOption = br.readLine()) != null) {
            switch (menuOption) {
                case "1":
                    for (Book book : books)
                        if (!book.isCheckedOut())
                            System.out.printf("%-30s|%-30s|%-30s%n", book.getTitle(), book.getAuthor(),
                                    book.getPubYear());
                    break;
                case "2":
                    break menuOptionLoop;
                case "3":
                    String title = br.readLine();
                    Book book = findBook(title);
                    if (book == null || book.isCheckedOut())
                        System.out.println("Sorry, that book is not available");
                    else {
                        book.setCheckedOut(true);
                        System.out.println("Thank you! Enjoy the book");
                    }
                    break;
                case "4":
                    String returnTitle = br.readLine();
                    Book returned = findBook(returnTitle);
                    if (returned == null || !returned.isCheckedOut())
                        System.out.println("That is not a valid book to return");
                    else {
                        returned.setCheckedOut(false);
                        System.out.println("Thank you for returning the book");
                    }
                    break;
                case "5":
                    for (Movie movie : movies)
                        if (!movie.isCheckedOut())
                            System.out.printf("%-30s|%-30s|%-30s|%-30s%n", movie.getName(), movie.getDirector(),
                                    movie.getYear(), movie.getRating());
                    break;
                case "6":
                    String checkoutName = br.readLine();
                    Movie movie = findMovie(checkoutName);
                    if (movie == null || movie.isCheckedOut())
                        System.out.println("Sorry, that movie is not available");
                    else {
                        movie.setCheckedOut(true);
                        System.out.println("Thank you! Enjoy the movie");
                    }
                    break;
                default:
                    System.out.println("Please select a valid option!");
                    break;
            }
        }

        br.close();
        if (menuOption != null) System.exit(0);
    }

    private static Book findBook(String title) {
        for (Book book : books)
            if (book.getTitle().equals(title))
                return book;
        return null;
    }

    private static Movie findMovie(String name) {
        for (Movie movie : movies)
            if (movie.getName().equals(name))
                return movie;
        return null;
    }
}
