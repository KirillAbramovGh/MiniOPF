package com.netcracker.students.o3.controller.sorters;

import com.netcracker.students.o3.controller.comparators.service.ComparatorServicesByActivationDate;
import com.netcracker.students.o3.controller.comparators.service.ComparatorServicesByAreas;
import com.netcracker.students.o3.controller.comparators.service.ComparatorServicesByCost;
import com.netcracker.students.o3.controller.comparators.service.ComparatorServicesByCustomerId;
import com.netcracker.students.o3.controller.comparators.service.ComparatorServicesById;
import com.netcracker.students.o3.controller.comparators.service.ComparatorServicesByName;
import com.netcracker.students.o3.controller.comparators.service.ComparatorServicesByStatus;
import com.netcracker.students.o3.controller.comparators.service.ComparatorServicesByTemplateId;
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
        switch (type){
            case ServiceSortUpById:
                return new ComparatorServicesById(true);
            case ServiceSortDownById:
                return new ComparatorServicesById(false);
            case ServiceSortUpByCost:
                return new ComparatorServicesByCost(true);
            case ServiceSortUpByName:
                return new ComparatorServicesByName(true);
            case ServiceSortUpByAreas:
                return new ComparatorServicesByAreas(true);
            case ServiceSortDownByCost:
                return new ComparatorServicesByCost(false);
            case ServiceSortDownByName:
                return  new ComparatorServicesByName(false);
            case ServiceSortUpByStatus:
                return new ComparatorServicesByStatus(true);
            case ServiceSortDownByAreas:
                return new ComparatorServicesByAreas(false);
            case ServiceSortDownByStatus:
                return new ComparatorServicesByStatus(false);
            case ServiceSortUpByCustomerId:
                return new ComparatorServicesByCustomerId(true);
            case ServiceSortUpByTemplateId:
                return new ComparatorServicesByTemplateId(true);
            case ServiceSortDownByCustomerId:
                return new ComparatorServicesByCustomerId(false);
            case ServiceSortDownByTemplateId:
                return new ComparatorServicesByTemplateId(false);
            case ServiceSortUpByActivationDate:
                return new ComparatorServicesByActivationDate(true);
            case ServiceSortDownByActivationDate:
                return new ComparatorServicesByActivationDate(false);
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
