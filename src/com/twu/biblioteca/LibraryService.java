package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.List;

class LibraryService {

    Resource findResource(String identifier, List<Resource> resources) {
        for (Resource resource : resources)
            if (resource.getIdentifier().equals(identifier)) return resource;
        return null;
    }

    ArrayList<Resource> getAvailableResources(List<Resource> resources) {
        ArrayList<Resource> availableResources = new ArrayList<>();
        for (Resource resource : resources)
            if (!resource.isCheckedOut()) availableResources.add(resource);
        return availableResources;
    }
}
