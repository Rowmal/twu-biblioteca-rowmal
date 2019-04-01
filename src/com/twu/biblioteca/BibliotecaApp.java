package com.twu.biblioteca;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BibliotecaApp {

    public static void main(String[] args) throws IOException {
        // TODO: Print to console using PrintWriter
        String[] bookTitles = {"A Game of Thrones", "Nineteen Eighty-Four", "The Metamorphosis", "Wuthering Heights"};
        String[] pubYears = {"1996", "1949", "1915", "1847"};
        String[] authors = {"George R. R. Martin", "George Orwell", "Franz Kafka", "Emily Brontë"};

        System.out.println("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!");
        System.out.println("[1] List of books");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str;
        while ((str = br.readLine()) != null) {
            if (str.equals("1")) {
                for (int i = 0; i < 4; i++)
                    System.out.printf("%-30s|%-30s|%-4.4s%n", bookTitles[i], authors[i], pubYears[i]);
            } else System.out.println("Please select a valid option!");
        }
        br.close();
    }
}
