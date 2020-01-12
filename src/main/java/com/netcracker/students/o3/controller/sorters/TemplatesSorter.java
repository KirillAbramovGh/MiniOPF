package com.netcracker.students.o3.controller.sorters;

import com.netcracker.students.o3.controller.comparators.template.ComparatorTemplatesByCost;
import com.netcracker.students.o3.controller.comparators.template.ComparatorTemplatesByName;
import com.netcracker.students.o3.model.templates.Template;

import java.util.Comparator;
import java.util.List;

/**
 * class sort templates
 */
public class TemplatesSorter
{
    /**
     * comparator which compare templates
     */
    private Comparator<Template> templateComparator;

    public TemplatesSorter()
    {
        templateComparator = new ComparatorTemplatesByName(true);
    }

    /**
     * define type of sorting
     * @param type
     */
    public void defineSortType(SortType type)
    {
        switch (type){
            case DownByName:templateComparator = new ComparatorTemplatesByName(false);break;
            case DownByCost:templateComparator = new ComparatorTemplatesByCost(false);break;
            case UpByName:templateComparator = new ComparatorTemplatesByName(true);break;
            case UpByCost:templateComparator = new ComparatorTemplatesByCost(true);break;
        }
    }

    /**
     * sort services
     * @param services
     */
    public void sort(List<Template> services)
    {
        services.sort(templateComparator);
    }
}
