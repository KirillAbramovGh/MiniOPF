package com.netcracker.students.o3.controller.searcher;

import com.netcracker.students.o3.controller.ControllerImpl;
import com.netcracker.students.o3.model.area.Area;
import com.netcracker.students.o3.model.templates.Template;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

/**
 * class search templates and services
 */
public class SearcherTemplates extends Searcher<Template> {
    private static SearcherTemplates instance;

    private SearcherTemplates() {
    }

    /**
     * search templates by area name
     */
    public List<Template> searchTemplatesByArea(Collection<Template> templates, String areaName) {
        List<Template> results = new ArrayList<>();

        for (Template template : templates) {
            for (BigInteger id : template.getPossibleAreasId()) {
                Area area = ControllerImpl.getInstance().getArea(id);
                if (area.getName().contains(areaName) || checkRegExp(areaName, area.getName())) {
                    results.add(template);
                }
            }
        }
        return results;
    }

    /**
     * search templates by cost
     */
    public List<Template> searchTemplatesByCost(Collection<Template> templates, String search) {
        List<Template> result = new ArrayList<>();

        BigDecimal cost;
        for (Template template : templates) {
            cost = template.getCost();
            if (isCostInDiapason(cost, search, 50) || checkRegExp(search, cost.toString())) {
                result.add(template);
            }
        }

        return result;
    }


    /**
     * search templates by name
     */
    public List<Template> searchTemplatesByName(Collection<Template> templates, String search) {
        List<Template> result = new ArrayList<>();

        String name;
        for (Template template : templates) {
            name = template.getName();
            if (name.contains(search) || checkRegExp(search, name)) {
                result.add(template);
            }
        }

        return result;
    }


    /**
     * search templates by description
     */
    public List<Template> searchTemplatesByDescription(Collection<Template> templates, String search) {
        List<Template> result = new ArrayList<>();

        String description;
        for (Template template : templates) {
            description = template.getDescription().toLowerCase();
            if (description.contains(search) || checkRegExp(search, description)) {
                result.add(template);
            }
        }

        return result;
    }


    public static SearcherTemplates getInstance() {
        if (instance == null) {
            instance = new SearcherTemplates();
        }

        return instance;
    }

    public List<Template> search(String search, String field, Collection<Template> templates) {
        switch (field) {
            case "Id":
                return searchTemplatesById(search, templates);
            case "Name":
                return searchTemplatesByName(templates, search);
            case "Description":
                return searchTemplatesByDescription(templates, search);
            case "Cost":
                return searchTemplatesByCost(templates, search);
            case "Area":
                return searchTemplatesByArea(templates, search);
            case "all":
                Set<Template> res = new HashSet<>(searchTemplatesById(search, templates));

                res.addAll(searchTemplatesByName(templates, search));
                res.addAll(searchTemplatesByDescription(templates, search));
                res.addAll(searchTemplatesByCost(templates, search));
                res.addAll(searchTemplatesByArea(templates, search));

                return new ArrayList<>(res);
        }
        return new ArrayList<>();
    }

    private List<Template> searchTemplatesById(String search, Collection<Template> templates) {
        List<Template> result = new ArrayList<>();

        String id;
        for (Template template : templates) {
            id = template.getId().toString();
            if (id.equals(search) || checkRegExp(search, id)) {
                result.add(template);
            }
        }

        return result;
    }

    public List<Template> searchTemplatesByAllFields(Collection<Template> templates, String req) {
        Set<Template> result = new HashSet<>();

        templates.addAll(searchTemplatesByArea(templates, req));
        templates.addAll(searchTemplatesByName(templates, req));
        templates.addAll(searchTemplatesByDescription(templates, req));
        templates.addAll(searchTemplatesByCost(templates, req));

        return new ArrayList<>(result);
    }
}
