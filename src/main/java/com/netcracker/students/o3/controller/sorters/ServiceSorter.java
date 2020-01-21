package com.netcracker.students.o3.controller.sorters;

import com.netcracker.students.o3.controller.comparators.service.ComparatorServiceByCost;
import com.netcracker.students.o3.controller.comparators.service.ComparatorServiceByName;
import com.netcracker.students.o3.model.services.Service;

import java.util.Comparator;
import java.util.List;

/**
 * class sort services
 */
public class ServiceSorter {
    private static ServiceSorter instance;

    private ServiceSorter(){}

    private Comparator<Service> defineSortType(SortType type) {
        if (SortType.UpByCost.equals(type)) {
            return new ComparatorServiceByCost(true);
        } else if (SortType.UpByName.equals(type)) {
            return new ComparatorServiceByName(true);
        } else if (SortType.DownByCost.equals(type)) {
            return new ComparatorServiceByCost(false);
        }

        return new ComparatorServiceByName(false);
    }

    /**
     * sort services
     */
    public void sort(List<Service> services, SortType type) {
        Comparator<Service> serviceComparator = defineSortType(type);
        services.sort(serviceComparator);
    }

    public static ServiceSorter getInstance() {
        if (instance == null) {
            instance = new ServiceSorter();
        }
        return instance;
    }
}
