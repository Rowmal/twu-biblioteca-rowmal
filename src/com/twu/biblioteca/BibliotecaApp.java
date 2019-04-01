package com.twu.biblioteca;

public class BibliotecaApp {

    public static void main(String[] args) {
        System.out.println("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!");
        String[] libraryBooks = { "A Game of Thrones", "Nineteen Eighty-Four", "The Metamorphosis" };
        String[] pubYears = { "1996", "1949", "1915" };
        String[] authors = { "George R. R. Martin", "George Orwell", "Franz Kafka" };
        for (int i = 0; i < 3; i++)
            System.out.printf("%-30s|%-30s|%-4.4s%n", libraryBooks[i], authors[i], pubYears[i]);
    }
}
