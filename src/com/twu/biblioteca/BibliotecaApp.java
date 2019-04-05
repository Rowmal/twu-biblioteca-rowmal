package com.twu.biblioteca;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// TODO: Print to console using PrintWriter
// TODO: Utilise collections framework
public class BibliotecaApp {

    private static final Book[] books = new Book[4];
    private static final Movie[] movies = new Movie[4];
    private static final User[] users = new User[1];
    private static final String[] menuOptions = {"[1] List of books", "[2] Quit", "[3] Checkout book",
            "[4] Return a book", "[5] List of movies", "[6] Checkout movie", "[7] Show main menu",
            "[8] User information"};

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
        users[0] = new User("123-4567", "password", "rowmal", "rowmal@protonmail.com",
                "0400000000");

        System.out.println("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!");

        printMenu(false);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String menuOption;
        boolean loggedIn = false;
        User loggedInUser = null;

        menuOptionLoop:
        while ((menuOption = br.readLine()) != null) {
            switch (menuOption) {
                case "1":
                    printBookList();
                    break;
                case "2":
                    break menuOptionLoop;
                case "3":
                case "4":
                    String title = br.readLine();
                    if (!loggedIn) {
                        System.out.println("Please enter your library number and password");
                        String libNum = br.readLine();
                        String password = br.readLine();
                        User user = findUser(libNum);
                        if (user == null || !user.getPassword().equals(password)) break;
                        loggedIn = true;
                        loggedInUser = user;
                    }
                    if (menuOption.equals("3")) checkoutBook(title);
                    else returnBook(title);
                    break;
                case "5":
                    printMovieList();
                    break;
                case "6":
                    String name = br.readLine();
                    checkoutMovie(name);
                    break;
                case "7":
                    printMenu(loggedIn);
                    break;
                case "8":
                    if (loggedIn) {
                        printUserInfo(loggedInUser);
                        break;
                    }
                default:
                    System.out.println("Please select a valid option!");
                    break;
            }
        }

        br.close();
        if (menuOption != null) System.exit(0);
    }

    private static void printBookList() {
        for (Book book : books)
            if (!book.isCheckedOut())
                System.out.printf("%-30s|%-30s|%-30s%n", book.getTitle(), book.getAuthor(), book.getPubYear());
    }

    private static void printMovieList() {
        for (Movie movie : movies)
            if (!movie.isCheckedOut())
                System.out.printf("%-30s|%-30s|%-30s|%-30s%n", movie.getName(), movie.getDirector(), movie.getYear(),
                        movie.getRating());
    }

    private static void printMenu(boolean loggedIn) {
        for (String menuOption : menuOptions) {
            if (menuOption.equals("[8] User information") && !loggedIn) continue;
            System.out.println(menuOption);
        }
    }

    private static void printUserInfo(User user) {
        System.out.printf("%-30s|%-30s|%-30s%n", user.getName(), user.getEmail(), user.getPhoneNum());
    }

    private static Book findBook(String title) {
        for (Book book : books)
            if (book.getTitle().equals(title)) return book;
        return null;
    }

    private static Movie findMovie(String name) {
        for (Movie movie : movies)
            if (movie.getName().equals(name)) return movie;
        return null;
    }

    private static User findUser(String libNum) {
        for (User user : users)
            if (user.getLibNum().equals(libNum)) return user;
        return null;
    }

    private static void checkoutBook(String title) {
        Book book = findBook(title);
        if (book == null || book.isCheckedOut()) System.out.println("Sorry, that book is not available");
        else {
            book.setCheckedOut(true);
            System.out.println("Thank you! Enjoy the book");
        }
    }

    private static void returnBook(String title) {
        Book returned = findBook(title);
        if (returned == null || !returned.isCheckedOut()) System.out.println("That is not a valid book to return");
        else {
            returned.setCheckedOut(false);
            System.out.println("Thank you for returning the book");
        }
    }

    private static void checkoutMovie(String name) {
        Movie movie = findMovie(name);
        if (movie == null || movie.isCheckedOut()) System.out.println("Sorry, that movie is not available");
        else {
            movie.setCheckedOut(true);
            System.out.println("Thank you! Enjoy the movie");
        }
    }
}
