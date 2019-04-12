package com.twu.biblioteca;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

// TODO: Print to console using PrintWriter
// TODO: Utilise collections framework
class BibliotecaApp {

    private final Library library;
    private final ArrayList<User> users = new ArrayList<>();
    private final String[] menuOptions = {"[1] List of books", "[2] Quit", "[3] Checkout book",
            "[4] Return a book", "[5] List of movies", "[6] Checkout movie", "[7] Show main menu",
            "[8] User information"};

    private BibliotecaApp(Library library) {
        this.library = library;
        this.users.add(new User("123-4567", "password", "rowmal", "rowmal@protonmail.com", "0400000000"));
    }

    private void run() throws IOException {
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
                    if (menuOption.equals("3")) library.checkoutResource(title, ResourceType.BOOK);
                    else library.returnResource(title, ResourceType.BOOK);
                    break;
                case "5":
                    printMovieList();
                    break;
                case "6":
                    String name = br.readLine();
                    library.checkoutResource(name, ResourceType.MOVIE);
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

    public static void main(String[] args) throws IOException {
        ArrayList<Resource> books = new ArrayList<>();
        ArrayList<Resource> movies = new ArrayList<>();
        LibraryService libraryService = new LibraryService();
        books.add(new Book("A Game of Thrones", "George R. R. Martin", "1996"));
        books.add(new Book("Nineteen Eighty-Four", "George Orwell", "1949"));
        books.add(new Book("The Metamorphosis", "Franz Kafka", "1915"));
        books.add(new Book("Wuthering Heights", "Emily Brontë", "1847"));
        movies.add(new Movie("Ghostbusters", "1984", "Ivan Reitman", "9"));
        movies.add(new Movie("Event Horizon", "1997", "Paul W. S. Anderson", "3"));
        movies.add(new Movie("The Witch", "2015", "Robert Eggers", "8"));
        movies.add(new Movie("Under the Skin", "2013", "Jonathan Glazer", "7"));
        Library library = new Library(books, movies, libraryService);

        BibliotecaApp bibliotecaApp = new BibliotecaApp(library);
        bibliotecaApp.run();
    }

    private void printBookList() {
        for (Resource resource : library.getResourceList(ResourceType.BOOK)) {
            Book book = (Book) resource;
            if (!book.isCheckedOut())
                System.out.printf("%-30s|%-30s|%-30s%n", book.getTitle(), book.getAuthor(), book.getPubYear());
        }
    }

    private void printMovieList() {
        for (Resource resource : library.getResourceList(ResourceType.MOVIE)) {
            Movie movie = (Movie) resource;
            if (!movie.isCheckedOut())
                System.out.printf("%-30s|%-30s|%-30s|%-30s%n", movie.getName(), movie.getDirector(), movie.getYear(),
                        movie.getRating());
        }
    }

    private void printMenu(boolean loggedIn) {
        for (String menuOption : menuOptions) {
            if (menuOption.equals("[8] User information") && !loggedIn) continue;
            System.out.println(menuOption);
        }
    }

    private static void printUserInfo(User user) {
        System.out.printf("%-30s|%-30s|%-30s%n", user.getName(), user.getEmail(), user.getPhoneNum());
    }


    private User findUser(String libNum) {
        for (User user : users)
            if (user.getLibNum().equals(libNum)) return user;
        return null;
    }
}
