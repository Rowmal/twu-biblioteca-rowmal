package com.twu.biblioteca;

import java.util.List;

class Library {

    private final List<Resource> books;
    private final List<Resource> movies;
    private final LibraryService libraryService;

    Library(List<Resource> books, List<Resource> movies, LibraryService libraryService) {
        this.books = books;
        this.movies = movies;
        this.libraryService = libraryService;
    }

    void checkoutResource(String identifier, ResourceType resourceType) {
        Resource resource = libraryService.findResource(identifier, getResourcesByType(resourceType));
        String bookOrMovie = resourceType == ResourceType.BOOK ? "book" : "movie";
        if (resource == null || resource.isCheckedOut())
            System.out.printf("Sorry, that %s is not available%n", bookOrMovie);
        else {
            resource.checkoutResource();
            System.out.printf("Thank you! Enjoy the %s%n", bookOrMovie);
        }
    }

    void returnResource(String identifier, ResourceType resourceType) {
        Resource resource = libraryService.findResource(identifier, getResourcesByType(resourceType));
        String bookOrMovie = resourceType == ResourceType.BOOK ? "book" : "movie";
        if (resource == null || !resource.isCheckedOut())
            System.out.printf("That is not a valid %s to return%n", bookOrMovie);
        else {
            resource.returnResource();
            System.out.printf("Thank you for returning the %s%n", bookOrMovie);
        }
    }

    List<Resource> getResourceList(ResourceType resourceType) {
        return libraryService.getAvailableResources(getResourcesByType(resourceType));
    }

    private List<Resource> getResourcesByType(ResourceType resourceType) {
        if (resourceType == ResourceType.BOOK) return books;
        else return movies;
    }
}
