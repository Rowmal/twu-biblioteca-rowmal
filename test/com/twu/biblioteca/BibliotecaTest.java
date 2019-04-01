package com.twu.biblioteca;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.hamcrest.CoreMatchers.containsString;

public class BibliotecaTest {

    private InputStream systemIn;
    private PrintStream systemOut;
    private final ByteArrayOutputStream output = new ByteArrayOutputStream();

    @Before
    public void mockSystemStreams() {
        systemIn = System.in;
        systemOut = System.out;
        System.setOut(new PrintStream(output));
    }

    @After
    public void revertStreams() {
        System.setIn(systemIn);
        System.setOut(systemOut);
        output.reset();
    }

    @Test
    public void welcomeMessageShouldDisplayWhenApplicationStarts() throws IOException {
        String expectedWelcome = "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!\n";
        String input = "1\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        BibliotecaApp.main(null);

        assertThat(output.toString(), startsWith(expectedWelcome));
    }

    @Test
    public void mainMenuShouldDisplayAfterWelcomeMessage() throws IOException {
        String expectedMainMenu = "[1] List of books\n";
        String input = "1\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        BibliotecaApp.main(null);

        assertThat(output.toString(), containsString(expectedMainMenu));
    }

    @Test
    public void booksShouldBeListedAfterWelcomeMessage() throws IOException {
        String[] expectedBookList = { "A Game of Thrones", "Nineteen Eighty-Four", "The Metamorphosis" };
        String input = "1\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        BibliotecaApp.main(null);

        String outputStr = output.toString();
        for (int i = 0; i < 3; i++) {
            assertThat(outputStr, containsString(expectedBookList[i]));
        }
    }

    @Test
    public void authorAndPublicationYearShouldBeListed() throws IOException {
        String[] expectedPubYears = { "1996", "1949", "1915" };
        String[] expectedAuthors = { "George R. R. Martin", "George Orwell", "Franz Kafka" };
        String input = "1\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        BibliotecaApp.main(null);

        String outputStr = output.toString();
        for (int i = 0; i < 3; i++) {
            assertThat(outputStr, containsString(expectedPubYears[i]));
            assertThat(outputStr, containsString(expectedAuthors[i]));
        }
    }
}
