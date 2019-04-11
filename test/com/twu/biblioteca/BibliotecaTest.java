package com.twu.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;

import java.io.*;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

// TODO: Utilise System Rules' System.out and System.in rules
// TODO: Make output string testing more fine-grained
// TODO: Organise string input sequences
public class BibliotecaTest {

    private InputStream systemIn;
    private PrintStream systemOut;
    private final ByteArrayOutputStream output = new ByteArrayOutputStream();

    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();

    @Before
    public void mockSystemStreams() {
        systemIn = System.in;
        systemOut = System.out;
        System.setOut(new PrintStream(output));
    }

    @After
    public void revertSystemStreams() {
        System.setIn(systemIn);
        System.setOut(systemOut);
        output.reset();
    }

    // Menu
    @Test
    public void welcomeMessageShouldDisplayWhenBibliotecaStarts() throws IOException {
        String expectedMessage = "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!\n";
        String input = "\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        BibliotecaApp.main(null);

        assertThat(output.toString(), startsWith(expectedMessage));
    }

    @Test
    public void mainMenuShouldDisplayWhenBibliotecaStarts() throws IOException {
        String[] expectedMenuOptions = {"[1] List of books", "[2] Quit", "[3] Checkout book", "[4] Return a book",
                "[5] List of movies", "[6] Checkout movie", "[7] Show main menu"};
        String input = "\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        BibliotecaApp.main(null);

        String outputStr = output.toString();
        for (String expectedMenuOption : expectedMenuOptions)
            assertThat(outputStr, containsString(expectedMenuOption));
    }

    @Test
    public void mainMenuShouldDisplayWhenOptionSelected() throws IOException {
        String expectedMessage = "[1] List of books\n[2] Quit\n[3] Checkout book\n[4] Return a book\n" +
                "[5] List of movies\n[6] Checkout movie\n[7] Show main menu\n";
        String input = "\n" + "7\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        BibliotecaApp.main(null);

        assertThat(output.toString(), endsWith(expectedMessage));
    }

    @Test
    public void booksShouldBeListedWhenOptionSelected() throws IOException {
        String[] expectedTitles = {"A Game of Thrones", "Nineteen Eighty-Four", "The Metamorphosis",
                "Wuthering Heights"};
        String[] expectedPubYears = {"1996", "1949", "1915", "1847"};
        String[] expectedAuthors = {"George R. R. Martin", "George Orwell", "Franz Kafka", "Emily Brontë"};
        String input = "1\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        BibliotecaApp.main(null);

        String outputStr = output.toString();
        for (int i = 0; i < 4; i++) {
            assertThat(outputStr, containsString(expectedTitles[i]));
            assertThat(outputStr, containsString(expectedAuthors[i]));
            assertThat(outputStr, containsString(expectedPubYears[i]));
        }
    }

    @Test
    public void invalidOptionMessageShouldDisplayWhenSelectedOptionNotInMenu() throws IOException {
        String expectedMessage = "Please select a valid option!";
        String input = "😂\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        BibliotecaApp.main(null);

        assertThat(output.toString(), containsString(expectedMessage));
    }

    @Test
    public void bibliotecaShouldQuitWhenOptionSelected() throws IOException {
        String input = "2\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        exit.expectSystemExitWithStatus(0);

        BibliotecaApp.main(null);
    }

    @Test
    public void userInfoMenuOptionShouldDisplayWhenUserLoggedIn() throws IOException {
        String checkout = "A Game of Thrones";
        String expectedMessage = "[1] List of books\n[2] Quit\n[3] Checkout book\n[4] Return a book\n" +
                "[5] List of movies\n[6] Checkout movie\n[7] Show main menu\n[8] User information\n";
        String input = "3\n" + checkout + "\n" + "123-4567\n" + "password\n" + "7\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        BibliotecaApp.main(null);

        assertThat(output.toString(), endsWith(expectedMessage));
    }

    @Test
    public void userInfoShouldDisplayWhenOptionSelected() throws IOException {
        String checkout = "A Game of Thrones";
        String[] expectedUserInfo = {"rowmal", "rowmal@protonmail.com", "0400000000"};
        String input = "3\n" + checkout + "\n" + "123-4567\n" + "password\n" + "8\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        BibliotecaApp.main(null);

        String outputStr = output.toString();
        for (String expected : expectedUserInfo)
            assertThat(outputStr, containsString(expected));
    }

    @Test
    public void userInfoShouldNotDisplayWhenUserNotLoggedIn() throws IOException {
        String[] expectedUserInfo = {"rowmal", "rowmal@protonmail.com", "0400000000"};
        String input = "8\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        BibliotecaApp.main(null);

        String outputStr = output.toString();
        for (String expected : expectedUserInfo)
            assertThat(outputStr, not(containsString(expected)));
    }

    // User
    @Test
    public void userLoginPromptShouldDisplayWhenUserNotLoggedIn() throws IOException {
        String checkout = "A Game of Thrones";
        String expectedMessage = "Please enter your library number and password";
        String input = "3\n" + checkout + "\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        BibliotecaApp.main(null);

        assertThat(output.toString(), containsString(expectedMessage));
    }
}
