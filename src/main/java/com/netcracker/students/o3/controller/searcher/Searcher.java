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

public class Searcher
{

    public List<Template> searchTemplatesByArea(List<Template> templates, String areaName)
    {
        List<Template> results = new ArrayList<>();

        for (Template template : templates)
        {
            for (BigInteger id : template.getPossibleAreasId())
            {
                Area area = ControllerImpl.getInstance().getArea(id);
                if (area.getName().contains(areaName))
                {
                    results.add(template);
                }
            }
        }
        return results;
    }

    public List<Template> searchTemplatesByCost(List<Template> templates, BigDecimal cost)
    {
        List<Template> result = new ArrayList<>();

        for (Template template : templates)
        {
            if (Math.abs(template.getCost().doubleValue() - cost.doubleValue()) < 10)
            {
                result.add(template);
            }
        }

        return result;
    }

    public List<Template> searchTemplatesByName(List<Template> templates, String name)
    {
        List<Template> result = new ArrayList<>();

        for (Template template : templates)
        {
            if (template.getName().toLowerCase().contains(name.toLowerCase()))
            {
                result.add(template);
            }
        }

        return result;
    }

    public List<Template> searchTemplatesByDescription(List<Template> templates, String description)
    {
        List<Template> result = new ArrayList<>();

        for (Template template : templates)
        {
            if (template.getDescription().toLowerCase().contains(description.toLowerCase()))
            {
                result.add(template);
            }
        }

        return result;
    }

    public List<Service> searchServiceByCost(List<Service> services, BigDecimal cost)
    {
        List<Service> result = new ArrayList<>();

        for (Service service : services)
        {
            if (Math.abs(service.getCost().doubleValue() - cost.doubleValue()) < 10)
            {
                result.add(service);
            }
        }

        return result;
    }

    public List<Service> searchServiceByName(List<Service> services, String name)
    {
        List<Service> result = new ArrayList<>();

        for (Service service : services)
        {
            if (service.getName().toLowerCase().contains(name.toLowerCase()))
            {
                result.add(service);
            }
        }

        return result;
    }

    public List<Service> searchServiceByDescription(List<Service> services, String description)
    {
        List<Service> result = new ArrayList<>();

        for (Service service : services)
        {
            if (service.getName().toLowerCase().contains(description.toLowerCase()))
            {
                result.add(service);
            }
        }

        return result;
    }

    public List<Service> searchServiceByStatus(List<Service> services, String status)
    {

        List<Service> result = new ArrayList<>();

        for (Service service : services)
        {
            if (service.getStatus().toString().toLowerCase().contains(status.toLowerCase()))
            {
                result.add(service);
            }
        }

        return result;
    }

    public Set<Service> searchServices(List<Service> services, String searchField)
    {
        Set<Service> result = new HashSet<>();

        result.addAll(searchServiceByName(services, searchField));
        result.addAll(searchServiceByDescription(services, searchField));
        result.addAll(searchServiceByStatus(services, searchField));
        result.addAll(searchServiceByCost(services, parseBigDecimal(searchField)));

        return result;
    }

    public Set<Template> searchTemplates(List<Template> templates, String searchField)
    {
        Set<Template> result = new HashSet<>();

        result.addAll(searchTemplatesByName(templates, searchField));
        result.addAll(searchTemplatesByDescription(templates, searchField));
        result.addAll(searchTemplatesByArea(templates, searchField));
        result.addAll(searchTemplatesByCost(templates, parseBigDecimal(searchField)));

        return result;
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
}
