package com.twu.biblioteca;

public class BibliotecaApp {

    public static void main(String[] args) {
        System.out.println("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!");
        String[] libraryBooks = { "A Game of Thrones", "Nineteen Eighty-Four", "The Metamorphosis" };
        for (String book : libraryBooks) System.out.println(book);
    }
}
