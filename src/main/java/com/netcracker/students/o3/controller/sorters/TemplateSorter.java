package com.netcracker.students.o3.controller.sorters;

import com.netcracker.students.o3.controller.comparators.template.ComparatorTemplatesByCost;
import com.netcracker.students.o3.controller.comparators.template.ComparatorTemplatesById;
import com.netcracker.students.o3.controller.comparators.template.ComparatorTemplatesByName;
import com.netcracker.students.o3.controller.sorters.SortType.TemplateSortType;
import com.netcracker.students.o3.model.templates.Template;

import java.util.Comparator;
import java.util.List;

/**
 * class sort templates
 */
public class TemplateSorter {
    /**
     * comparator which compare templates
     */
    private static TemplateSorter instance;

    private TemplateSorter() {
    }

    /**
     * define type of sorting
     *
     * @param type
     */
    private Comparator<Template> defineSortType(TemplateSortType type) {
        switch (type) {
            case DownByName:
                return new ComparatorTemplatesByName(false);
            case DownByCost:
                return new ComparatorTemplatesByCost(false);
            case UpByName:
                return new ComparatorTemplatesByName(true);
            case UpByCost:
                return new ComparatorTemplatesByCost(true);
            case UpById:
                return new ComparatorTemplatesById(true);
            case DownById:
                return new ComparatorTemplatesById(false);
        }
        return new ComparatorTemplatesByName(false);
    }

    /**
     * sort services
     *
     * @param templates
     */
    public void sort(List<Template> templates, TemplateSortType type) {
        if(type!=null) {
            Comparator<Template> templateComparator = defineSortType(type);
            templates.sort(templateComparator);
        }
    }

    public static TemplateSorter getInstance(){
        if(instance==null){
            instance = new TemplateSorter();
        }
        return instance;
    }
}
