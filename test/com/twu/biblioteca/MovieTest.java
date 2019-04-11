package com.twu.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;

import java.io.*;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

public class MovieTest {
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
    public void moviesShouldBeListedWhenOptionSelected() throws IOException {
        String[] expectedNames = {"Ghostbusters", "Event Horizon", "The Witch", "Under the Skin"};
        String[] expectedYears = {"1984", "1997", "2015", "2013"};
        String[] expectedDirectors = {"Ivan Reitman", "Paul W. S. Anderson", "Robert Eggers", "Jonathan Glazer"};
        String[] expectedRatings = {"9", "3", "8", "7"};
        String input = "5\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        BibliotecaApp.main(null);

        String outputStr = output.toString();
        for (int i = 0; i < 4; i++) {
            assertThat(outputStr, containsString(expectedNames[i]));
            assertThat(outputStr, containsString(expectedYears[i]));
            assertThat(outputStr, containsString(expectedDirectors[i]));
            assertThat(outputStr, containsString(expectedRatings[i]));
        }
    }

    @Test
    public void successCheckOutMessageShouldDisplayWhenMovieCheckedOut() throws IOException {
        String checkout = "Ghostbusters";
        String expectedMessage = "Thank you! Enjoy the movie";
        String input = "6\n" + checkout + "\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        BibliotecaApp.main(null);

        assertThat(output.toString(), containsString(expectedMessage));
    }

    @Test
    public void failCheckOutMessageShouldDisplayWhenMovieNotInBiblioteca() throws IOException {
        String checkout = "The Shining";
        String expectedMessage = "Sorry, that movie is not available";
        String input = "6\n" + checkout + "\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        BibliotecaApp.main(null);

        assertThat(output.toString(), containsString(expectedMessage));
    }

    @Test
    public void failCheckoutMessageShouldDisplayWhenMovieAlreadyCheckedOut() throws IOException {
        String checkout = "Ghostbusters";
        String expectedMessage = "Sorry, that movie is not available";
        String input = "6\n" + checkout + "\n" + "6\n" + checkout + "\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        BibliotecaApp.main(null);

        assertThat(output.toString(), containsString(expectedMessage));
    }

    @Test
    public void checkedOutMoviesShouldNotBeListed() throws IOException {
        String checkout = "Ghostbusters";
        String input = "6\n" + checkout + "\n" + "5\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        BibliotecaApp.main(null);

        assertThat(output.toString(), not(containsString(checkout)));

    }
}