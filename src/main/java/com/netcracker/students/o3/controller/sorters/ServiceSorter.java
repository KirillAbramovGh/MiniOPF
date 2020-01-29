package com.netcracker.students.o3.controller.sorters;

import com.netcracker.students.o3.controller.comparators.service.ComparatorServicesByCost;
import com.netcracker.students.o3.controller.comparators.service.ComparatorServicesById;
import com.netcracker.students.o3.controller.comparators.service.ComparatorServicesByName;
import com.netcracker.students.o3.controller.sorters.SortType.ServiceSortType;
import com.netcracker.students.o3.model.services.Service;

import java.util.Comparator;
import java.util.List;

/**
 * class sort services
 */
public class ServiceSorter {
    private static ServiceSorter instance;

    private ServiceSorter(){}

    private Comparator<Service> defineSortType(ServiceSortType type) {
        if (ServiceSortType.UpByCost.equals(type)) {
            return new ComparatorServicesByCost(true);
        } else if (ServiceSortType.UpByName.equals(type)) {
            return new ComparatorServicesByName(true);
        } else if (ServiceSortType.DownByCost.equals(type)) {
            return new ComparatorServicesByCost(false);
        }else if (ServiceSortType.UpById.equals(type)){
            return new ComparatorServicesById(true);
        }else if (ServiceSortType.DownById.equals(type)){
            return new ComparatorServicesById(false);
        }

        return new ComparatorServicesByName(false);
    }

    /**
     * sort services
     */
    public void sort(List<Service> services, ServiceSortType type) {
        Comparator<Service> serviceComparator = defineSortType(type);
        System.out.println(serviceComparator);
        services.sort(serviceComparator);
    }

    public static ServiceSorter getInstance() {
        if (instance == null) {
            instance = new ServiceSorter();
        }
        return instance;
    }
}
