package com.netcracker.students.o3.controller.sorters;

import com.netcracker.students.o3.controller.comparators.template.ComparatorTemplatesByCost;
import com.netcracker.students.o3.controller.comparators.template.ComparatorTemplatesByName;
import com.netcracker.students.o3.model.templates.Template;

import java.util.Comparator;
import java.util.List;

/**
 * class sort templates
 */
public class TemplatesSorter {
    /**
     * comparator which compare templates
     */
    private static TemplatesSorter instance;

    private TemplatesSorter() {
    }

    /**
     * define type of sorting
     *
     * @param type
     */
    private Comparator<Template> defineSortType(SortType type) {
        switch (type) {
            case DownByName:
                return new ComparatorTemplatesByName(false);
            case DownByCost:
                return new ComparatorTemplatesByCost(false);
            case UpByName:
                return new ComparatorTemplatesByName(true);
            case UpByCost:
                return new ComparatorTemplatesByCost(true);
        }
        return new ComparatorTemplatesByName(false);
    }

    /**
     * sort services
     *
     * @param services
     */
    public void sort(List<Template> services, SortType type) {
        if(type!=null) {
            Comparator<Template> templateComparator = defineSortType(type);
            services.sort(templateComparator);
        }
    }

    public static TemplatesSorter getInstance(){
        if(instance==null){
            instance = new TemplatesSorter();
        }
        return instance;
    }
}
