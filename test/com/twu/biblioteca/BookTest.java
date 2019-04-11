package com.twu.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

public class BookTest {

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
    public void revertSystemStreams() {
        System.setIn(systemIn);
        System.setOut(systemOut);
        output.reset();
    }

    // Book checkouts
    @Test
    public void successCheckOutMessageShouldDisplayWhenBookCheckedOut() throws IOException {
        String checkout = "A Game of Thrones";
        String expectedMessage = "Thank you! Enjoy the book";
        String input = "3\n" + checkout + "\n" + "123-4567\n" + "password\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        BibliotecaApp.main(null);

        assertThat(output.toString(), containsString(expectedMessage));
    }

    @Test
    public void failCheckOutMessageShouldDisplayWhenBookNotInBiblioteca() throws IOException {
        String checkout = "The Wind in the Willows";
        String expectedMessage = "Sorry, that book is not available";
        String input = "3\n" + checkout + "\n" + "123-4567\n" + "password\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        BibliotecaApp.main(null);

        assertThat(output.toString(), containsString(expectedMessage));
    }

    @Test
    public void failCheckoutMessageShouldDisplayWhenBookAlreadyCheckedOut() throws IOException {
        String checkout = "A Game of Thrones";
        String expectedMessage = "Sorry, that book is not available";
        String input = "3\n" + checkout + "\n" + "123-4567\n" + "password\n" + "3\n" + checkout + "\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        BibliotecaApp.main(null);

        assertThat(output.toString(), containsString(expectedMessage));
    }

    @Test
    public void checkedOutBooksShouldNotBeListed() throws IOException {
        String checkout = "A Game of Thrones";
        String input = "3\n" + checkout + "\n" + "123-4567\n" + "password\n" + "1\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        BibliotecaApp.main(null);

        assertThat(output.toString(), not(containsString(checkout)));
    }

    // Book returns
    @Test
    public void successReturnMessageShouldDisplayWhenBookReturned() throws IOException {
        String checkout = "A Game of Thrones";
        String expectedMessage = "Thank you for returning the book";
        String input = "3\n" + checkout + "\n" + "123-4567\n" + "password\n" + "4\n" + checkout + "\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        BibliotecaApp.main(null);

        assertThat(output.toString(), containsString(expectedMessage));
    }

    @Test
    public void failReturnMessageShouldDisplayWhenBookNotInBiblioteca() throws IOException {
        String checkout = "The Wind in the Willows";
        String expectedMessage = "That is not a valid book to return";
        String input = "4\n" + checkout + "\n" + "123-4567\n" + "password\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        BibliotecaApp.main(null);

        assertThat(output.toString(), containsString(expectedMessage));
    }

    @Test
    public void failReturnMessageShouldDisplayWhenBookAlreadyReturned() throws IOException {
        String checkout = "A Game of Thrones";
        String expectedMessage = "That is not a valid book to return";
        String input = "4\n" + checkout + "\n" + "123-4567\n" + "password\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        BibliotecaApp.main(null);

        assertThat(output.toString(), containsString(expectedMessage));
    }

    @Test
    public void returnedBooksShouldBeListed() throws IOException {
        String checkout = "A Game of Thrones";
        String input = "3\n" + checkout + "\n" + "123-4567\n" + "password\n" + "4\n" + checkout + "\n" + "1\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        BibliotecaApp.main(null);

        assertThat(output.toString(), containsString(checkout));
    }
}
