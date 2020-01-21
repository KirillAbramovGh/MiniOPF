package com.netcracker.students.o3.controller.searcher;

import com.netcracker.students.o3.controller.ControllerImpl;
import com.netcracker.students.o3.model.area.Area;
import com.netcracker.students.o3.model.services.Service;
import com.netcracker.students.o3.model.templates.Template;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * class search templates and services
 */
public class SearcherTemplates
{
    private static SearcherTemplates instance;

    private SearcherTemplates(){}

    /**
     * search templates by area name
     */
    public List<Template> searchTemplatesByArea(List<Template> templates, String areaName)
    {
        List<Template> results = new ArrayList<>();

        for (Template template : templates)
        {
            for (BigInteger id : template.getPossibleAreasId())
            {
                Area area = ControllerImpl.getInstance().getArea(id);
                if (isTemplateAreaNameContainsString(area, areaName))
                {
                    results.add(template);
                }
            }
        }
        return results;
    }

    private boolean isTemplateAreaNameContainsString(Area area, String areaName)
    {
        return area.getName().toLowerCase().contains(areaName.toLowerCase());
    }

    /**
     * search templates by cost
     */
    public List<Template> searchTemplatesByCost(List<Template> templates, BigDecimal cost)
    {
        List<Template> result = new ArrayList<>();

        for (Template template : templates)
        {
            if (isTemplateCostEqualsCost(template, cost))
            {
                result.add(template);
            }
        }

        return result;
    }

    private boolean isTemplateCostEqualsCost(Template template, BigDecimal cost)
    {
        return Math.abs(template.getCost().doubleValue() - cost.doubleValue()) < 10;
    }

    /**
     * search templates by name
     */
    public List<Template> searchTemplatesByName(List<Template> templates, String name)
    {
        List<Template> result = new ArrayList<>();

        for (Template template : templates)
        {
            if (isTemplateNameContainsString(template, name))
            {
                result.add(template);
            }
        }

        return result;
    }

    private boolean isTemplateNameContainsString(Template template, String name)
    {
        return template.getName().toLowerCase().contains(name.toLowerCase());
    }

    /**
     * search templates by description
     */
    public List<Template> searchTemplatesByDescription(List<Template> templates, String description)
    {
        List<Template> result = new ArrayList<>();

        for (Template template : templates)
        {
            if (isTemplateDescriptionContainsString(template, description))
            {
                result.add(template);
            }
        }

        return result;
    }


    private boolean isTemplateDescriptionContainsString(Template template, String description)
    {
        return template.getDescription().toLowerCase().contains(description.toLowerCase());
    }



    /**
     * search templates by name,area,cost fields
     */
    public List<Template> searchTemplates(List<Template> templates, String searchField)
    {
        Set<Template> result = new HashSet<>();

        result.addAll(searchTemplatesByName(templates, searchField));
        result.addAll(searchTemplatesByArea(templates, searchField));
        result.addAll(searchTemplatesByCost(templates, parseBigDecimal(searchField)));

        return new ArrayList<>(result);
    }

    private BigDecimal parseBigDecimal(String value)
    {
        try
        {
            double doubleValue = Double.parseDouble(value);
            return BigDecimal.valueOf(doubleValue);
        }
        catch (NumberFormatException e)
        {
            return BigDecimal.ZERO;
        }
    }

    public static SearcherTemplates getInstance(){
        if(instance==null){
            instance = new SearcherTemplates();
        }

        return instance;
    }
}
