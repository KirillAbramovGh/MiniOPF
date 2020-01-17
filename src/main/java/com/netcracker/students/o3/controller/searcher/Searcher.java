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
public class Searcher
{
    private static Searcher instance;

    private Searcher(){}

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
     * search services by cost
     */
    public List<Service> searchServiceByCost(List<Service> services, BigDecimal cost)
    {
        List<Service> result = new ArrayList<>();

        for (Service service : services)
        {
            if (isServiceCostEqualsCost(service, cost))
            {
                result.add(service);
            }
        }

        return result;
    }

    private boolean isServiceCostEqualsCost(Service service, BigDecimal cost)
    {
        return Math.abs(service.getCost().doubleValue() - cost.doubleValue()) < 10;
    }

    /**
     * search services by name
     */
    public List<Service> searchServiceByName(List<Service> services, String name)
    {
        List<Service> result = new ArrayList<>();

        for (Service service : services)
        {
            if (isServiceNameContainsString(service, name))
            {
                result.add(service);
            }
        }

        return result;
    }

    private boolean isServiceNameContainsString(Service service, String name)
    {
        String serviceName = service.getName().toLowerCase();

        return serviceName.contains(name.toLowerCase());
    }

    /**
     * search services by status
     */
    public List<Service> searchServiceByStatus(List<Service> services, String status)
    {
        List<Service> result = new ArrayList<>();

        for (Service service : services)
        {
            if (isServiceStatusNameContainsString(service, status))
            {
                result.add(service);
            }
        }

        return result;
    }

    private boolean isServiceStatusNameContainsString(Service service, String status)
    {
        String serviceStatusName = service.getStatus().toString();
        return serviceStatusName.toLowerCase().contains(status.toLowerCase());
    }

    /**
     * search services by all fields
     */
    public List<Service> searchServices(List<Service> services, String searchField)
    {
        Set<Service> result = new HashSet<>();

        result.addAll(searchServiceByName(services, searchField));
        result.addAll(searchServiceByStatus(services, searchField));
        result.addAll(searchServiceByCost(services, parseBigDecimal(searchField)));

        return new ArrayList<>(result);
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

    public static Searcher getInstance(){
        if(instance==null){
            instance = new Searcher();
        }

        return instance;
    }
}
