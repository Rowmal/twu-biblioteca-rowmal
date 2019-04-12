package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class LibraryServiceTest {

    private LibraryService libraryService;
    private Resource resource;
    private List<Resource> resources;

    @Before
    public void setUp() {
        resource = new Book("A Game of Thrones", "George R. R. Martin", "1996");
        resources = new ArrayList<>();
        resources.add(resource);
        libraryService = new LibraryService();
    }

    @Test
    public void findResourceShouldReturnCorrectResource() {
        Resource found = libraryService.findResource(resource.getIdentifier(), resources);
        assertThat(found, is(equalTo(resource)));
    }

    @Test
    public void findResourceShouldReturnNullIfResourceNotInLibrary() {
        String invalidIdentifier = "The Wind in the Willows";
        Resource notFound = libraryService.findResource(invalidIdentifier, resources);
        assertNull(notFound);
    }

    @Test
    public void getAvailableResourcesShouldReturnResourcesFromList() {
        ArrayList<Resource> availableResources = libraryService.getAvailableResources(resources);
        assertThat(availableResources, hasItem(resource));
    }

    @Test
    public void getAvailableResourcesShouldNotReturnCheckedOutResources() {
        resource.checkoutResource();
        ArrayList<Resource> availableResources = libraryService.getAvailableResources(resources);
        assertThat(availableResources, not(hasItem(resource)));
    }
}
