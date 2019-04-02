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

    @Test
    public void welcomeMessageShouldDisplayWhenBibliotecaStarts() throws IOException {
        String expectedWelcome = "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!\n";
        String input = "1\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        BibliotecaApp.main(null);

        assertThat(output.toString(), startsWith(expectedWelcome));
    }

    @Test
    public void mainMenuShouldDisplayAfterWelcomeMessage() throws IOException {
        String[] expectedMenuOptions = {"[1] List of books", "[2] Quit"};
        String input = "1\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        BibliotecaApp.main(null);

        for (String expectedMenuOption : expectedMenuOptions)
            assertThat(output.toString(), containsString(expectedMenuOption));
    }

    @Test
    public void booksShouldBeListedWhenOptionSelected() throws IOException {
        String[] expectedBookTitles = {"A Game of Thrones", "Nineteen Eighty-Four", "The Metamorphosis",
                "Wuthering Heights"};
        String input = "1\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        BibliotecaApp.main(null);

        String outputStr = output.toString();
        for (int i = 0; i < 4; i++) {
            assertThat(outputStr, containsString(expectedBookTitles[i]));
        }
    }

    @Test
    public void authorAndPublicationYearShouldBeListedWithBooks() throws IOException {
        String[] expectedPubYears = {"1996", "1949", "1915", "1847"};
        String[] expectedAuthors = {"George R. R. Martin", "George Orwell", "Franz Kafka", "Emily Brontë"};
        String input = "1\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        BibliotecaApp.main(null);

        String outputStr = output.toString();
        for (int i = 0; i < 4; i++) {
            assertThat(outputStr, containsString(expectedPubYears[i]));
            assertThat(outputStr, containsString(expectedAuthors[i]));
        }
    }

    @Test
    public void invalidOptionMessageShouldDisplay() throws IOException {
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
    public void successMessageShouldDisplayWhenBookCheckedOut() throws IOException {
        String checkout = "A Game of Thrones";
        String expectedMessage = "Thank you! Enjoy the book";
        String input = "3\n" + checkout + "\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        BibliotecaApp.main(null);

        assertThat(output.toString(), containsString(expectedMessage));
    }

    @Test
    public void unavailableMessageShouldDisplayWhenBookNotInBiblioteca() throws IOException {
        String unavailableBook = "The Wind in the Willows";
        String expectedMessage = "Sorry, that book is not available";
        String input = "3\n" + unavailableBook + "\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        BibliotecaApp.main(null);

        assertThat(output.toString(), containsString(expectedMessage));
    }

    @Test
    public void unavailableMessageShouldDisplayWhenBookAlreadyCheckedOut() throws IOException {
        String checkout = "A Game of Thrones";
        String expectedMessage = "Sorry, that book is not available";
        String input = "3\n" + checkout + "\n" + "3\n" + checkout + "\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        BibliotecaApp.main(null);

        assertThat(output.toString(), containsString(expectedMessage));
    }

    @Test
    public void checkedOutBooksShouldNotBeListed() throws IOException {
        String checkout = "A Game of Thrones";
        String input = "3\n" + checkout + "\n" + "1\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        BibliotecaApp.main(null);

        assertThat(output.toString(), not(containsString(checkout)));
    }

    @Test
    public void returnedBooksShouldBeListed() throws IOException {
        String checkout = "A Game of Thrones";
        String input = "3\n" + checkout + "\n" + "4\n" + checkout + "\n" + "1\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        BibliotecaApp.main(null);

        assertThat(output.toString(), containsString(checkout));
    }
}
