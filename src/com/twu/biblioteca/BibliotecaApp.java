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
        String[] menuOptions = {"[1] List of books", "[2] Quit", "[3] Checkout book"};
        for (String menuOption : menuOptions) System.out.println(menuOption);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str;

        commandLoop:
        while ((str = br.readLine()) != null) {
            switch (str) {
                case "1":
                    for (Book book : books)
                        if (!book.isCheckedOut())
                            System.out.printf("%-30s|%-30s|%-4.4s%n", book.getTitle(), book.getAuthor(),
                                    book.getPubYear());
                    break;
                case "2":
                    break commandLoop;
                case "3":
                    String checkoutTitle = br.readLine();
                    Book checkout = null;
                    for (Book book : books)
                        if (book.getTitle().equals(checkoutTitle) && !book.isCheckedOut()) {
                            checkout = book;
                            checkout.setCheckedOut();
                            System.out.println("Thank you! Enjoy the book");
                            break;
                        }
                    if (checkout == null) System.out.println("Sorry, that book is not available");
                    break;
                default:
                    System.out.println("Please select a valid option!");
                    break;
            }
        }

        br.close();
        if (str != null) System.exit(0);
    }
}
