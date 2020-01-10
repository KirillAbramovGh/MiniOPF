package com.netcracker.students.o3.controller.sorters;

import com.netcracker.students.o3.controller.comparators.template.ComparatorTemplatesByCost;
import com.netcracker.students.o3.controller.comparators.template.ComparatorTemplatesByName;
import com.netcracker.students.o3.controller.sorters.sortParameters.SortType;
import com.netcracker.students.o3.model.templates.Template;

import java.util.Comparator;
import java.util.List;

public class TemplatesSorter
{
    private Comparator<Template> templateComparator;

    public TemplatesSorter()
    {
        templateComparator = new ComparatorTemplatesByName(true);
    }

    public void defineSortType(SortType type)
    {
        if (SortType.UpByCost.equals(type))
        {
            templateComparator = new ComparatorTemplatesByCost(true);
        }
        else if (SortType.UpByName.equals(type))
        {
            templateComparator = new ComparatorTemplatesByName(true);
        }
        else if (SortType.DownByCost.equals(type))
        {
            templateComparator = new ComparatorTemplatesByCost(false);
        }
        else if (SortType.DownByName.equals(type))
        {
            templateComparator = new ComparatorTemplatesByName(false);
        }
    }

    public void sort(List<Template> services)
    {
        services.sort(templateComparator);
    }
}
