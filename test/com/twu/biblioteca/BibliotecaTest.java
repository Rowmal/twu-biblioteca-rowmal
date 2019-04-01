package com.twu.biblioteca;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import java.io.PrintStream;
import java.io.ByteArrayOutputStream;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.hamcrest.CoreMatchers.containsString;

public class BibliotecaTest {

    private PrintStream systemOut;
    private final ByteArrayOutputStream actualOut = new ByteArrayOutputStream();

    @Before
    public void mockSystemOut() {
        systemOut = System.out;
        System.setOut(new PrintStream(actualOut));
    }

    @After
    public void revertStreams() {
        System.out.close();
        System.setOut(systemOut);
    }

    @Test
    public void welcomeMessageShouldBeDisplayedWhenApplicationStarts() {
        BibliotecaApp.main(null);
        String expectedWelcome = "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!\n";
        assertThat(actualOut.toString(), startsWith(expectedWelcome));
    }

    @Test
    public void booksShouldBeListedAfterWelcomeMessage() {
        BibliotecaApp.main(null);
        String expectedBookList = "A Game of Thrones\nNineteen Eighty-Four\nThe Metamorphosis\n";
        assertThat(actualOut.toString(), containsString(expectedBookList));
    }
}
