package com.twu.biblioteca;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// TODO: Print to console using PrintWriter
public class BibliotecaApp {

    private static final Book[] books = new Book[4];

    public static void main(String[] args) throws IOException {
        String[] bookTitles = {"A Game of Thrones", "Nineteen Eighty-Four", "The Metamorphosis", "Wuthering Heights"};
        String[] pubYears = {"1996", "1949", "1915", "1847"};
        String[] authors = {"George R. R. Martin", "George Orwell", "Franz Kafka", "Emily Brontë"};

        for (int i = 0; i < 4; i++)
            books[i] = new Book(bookTitles[i], authors[i], pubYears[i]);

        System.out.println("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!");
        String[] menuOptions = {"[1] List of books", "[2] Quit", "[3] Checkout book", "[4] Return a book"};
        for (String menuOption : menuOptions) System.out.println(menuOption);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str;

        menuOptionLoop:
        while ((str = br.readLine()) != null) {
            switch (str) {
                case "1":
                    for (Book book : books)
                        if (!book.isCheckedOut())
                            System.out.printf("%-30s|%-30s|%-4.4s%n", book.getTitle(), book.getAuthor(),
                                    book.getPubYear());
                    break;
                case "2":
                    break menuOptionLoop;
                case "3":
                    String checkoutTitle = br.readLine();
                    Book checkout = findBook(checkoutTitle);
                    if (checkout == null || checkout.isCheckedOut())
                        System.out.println("Sorry, that book is not available");
                    else {
                        checkout.setCheckedOut(true);
                        System.out.println("Thank you! Enjoy the book");
                    }
                    break;
                case "4":
                    String returnTitle = br.readLine();
                    Book returned = findBook(returnTitle);
                    if (returned != null) returned.setCheckedOut(false);
                    break;
                default:
                    System.out.println("Please select a valid option!");
                    break;
            }
        }

        br.close();
        if (str != null) System.exit(0);
    }

    private static Book findBook(String title) {
        for (Book book : books)
            if (book.getTitle().equals(title))
                return book;
        return null;
    }
}
