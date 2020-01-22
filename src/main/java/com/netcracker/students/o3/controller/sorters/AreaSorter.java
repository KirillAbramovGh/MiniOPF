package com.netcracker.students.o3.controller.sorters;

import com.netcracker.students.o3.controller.comparators.area.ComparatorAreasByDescription;
import com.netcracker.students.o3.controller.comparators.area.ComparatorAreasById;
import com.netcracker.students.o3.controller.comparators.area.ComparatorAreasByName;
import com.netcracker.students.o3.controller.sorters.SortType.AreaSortType;
import com.netcracker.students.o3.model.area.Area;
import com.netcracker.students.o3.model.users.Customer;

import java.util.Comparator;
import java.util.List;

public class AreaSorter {
    /**
     * comparator which compare templates
     */
    private static AreaSorter instance;

    private AreaSorter() {
    }

    /**
     * define type of sorting
     *
     * @param type
     */
    private Comparator<Area> defineSortType(AreaSortType type) {
        switch (type) {
            case DownById:
                return new ComparatorAreasById(false);
            case UpById:
                return new ComparatorAreasById(true);
            case UpByName:
                return new ComparatorAreasByName(true);
            case DownByName:
                return new ComparatorAreasByName(false);
            case UpByDescription:
                return new ComparatorAreasByDescription(true);
            case DownByDescription:
                return new ComparatorAreasByDescription(false);
        }
        return new ComparatorAreasById(true);
    }

    /**
     * sort services
     *
     * @param services
     */
    public void sort(List<Area> services, AreaSortType type) {
        if (type != null) {
            Comparator<Area> templateComparator = defineSortType(type);
            services.sort(templateComparator);
        }
    }

    public static AreaSorter getInstance() {
        if (instance == null) {
            instance = new AreaSorter();
        }
        return instance;
    }
}
