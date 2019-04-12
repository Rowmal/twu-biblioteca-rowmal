package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class LibraryTest {

    private Library library;
    private LibraryService mockedLibraryService;
    private List<Resource> books;
    private final Resource book = new Book("A Game of Thrones", "George R. R. Martin", "1996");
    private final String bookIdentifier = book.getIdentifier();


    @Before
    public void setup() {
        books = new ArrayList<>();
        books.add(book);
        mockedLibraryService = mock(LibraryService.class);
        library = new Library(books, new ArrayList<>(), mockedLibraryService);
    }

    @Test
    public void checkoutResourceShouldCallFindResourceWithCorrectArgs() {
        library.checkoutResource(bookIdentifier, ResourceType.BOOK);
        verify(mockedLibraryService).findResource(bookIdentifier, books);
    }

    @Test
    public void resourceShouldBeCheckedOutAfterCheckoutResourceCalled() {
        when(mockedLibraryService.findResource(anyString(), anyList())).thenReturn(book);
        library.checkoutResource(bookIdentifier, ResourceType.BOOK);
        assertTrue(book.isCheckedOut());
    }

    @Test
    public void returnResourceShouldCallFindResourceWithCorrectArgs() {
        library.returnResource(bookIdentifier, ResourceType.BOOK);
        verify(mockedLibraryService).findResource(bookIdentifier, books);
    }

    @Test
    public void resourceShouldNotBeCheckedOutAfterReturnResourceCalled() {
        when(mockedLibraryService.findResource(anyString(), anyList())).thenReturn(book);
        library.returnResource(bookIdentifier, ResourceType.BOOK);
        assertFalse(book.isCheckedOut());
    }

    @Test
    public void getResourceListShouldCallGetAvailableResourcesWithCorrectArgs() {
        library.getResourceList(ResourceType.BOOK);
        verify(mockedLibraryService).getAvailableResources(books);
    }
}
