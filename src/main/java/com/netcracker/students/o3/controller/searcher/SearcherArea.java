package com.netcracker.students.o3.controller.searcher;

import com.netcracker.students.o3.model.area.Area;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SearcherArea extends Searcher<Area> {
    private static SearcherArea instance;

    private SearcherArea() {
    }

    public static SearcherArea getInstance() {
        if (instance == null) {
            instance = new SearcherArea();
        }

        return instance;
    }

    private List<Area> searchById(String search, Collection<Area> areas) {
        List<Area> result = new ArrayList<>();

        String id;
        for (Area area : areas) {
            id = area.getId().toString();
            if (id.equals(search) || checkRegExp(search, id)) {
                result.add(area);
            }
        }

        return result;
    }

    private List<Area> searchByName(String search, Collection<Area> areas) {
        List<Area> result = new ArrayList<>();

        String name;
        for (Area area : areas) {
            name = area.getName();
            if (name.contains(search) || checkRegExp(search, name)) {
                result.add(area);
            }
        }

        return result;
    }

    private List<Area> searchByDescription(String search, Collection<Area> areas) {
        List<Area> result = new ArrayList<>();

        String description;
        for (Area area : areas) {
            description = area.getDescription();
            if (description.contains(search) || checkRegExp(search, description)) {
                result.add(area);
            }
        }

        return result;
    }


    public List<Area> search(String search, String field, Collection<Area> areas) {
        switch (field) {
            case "Id":
                return searchById(search, areas);
            case "Name":
                return searchByName(search, areas);
            case "Description":
                return searchByDescription(search, areas);
        }

        return new ArrayList<>();
    }
}
