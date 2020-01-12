package com.netcracker.students.o3.controller.sorters;

import com.netcracker.students.o3.controller.comparators.service.ComparatorServiceByCost;
import com.netcracker.students.o3.controller.comparators.service.ComparatorServiceByName;
import com.netcracker.students.o3.model.services.Service;

import java.util.Comparator;
import java.util.List;

/**
 * class sort services
 */
public class ServiceSorter
{
    /**
     * comparator which compare service
     */
    private Comparator<Service> serviceComparator;

    public ServiceSorter()
    {
        serviceComparator = new ComparatorServiceByName(true);
    }

    /**
     * define sort type
     */
    public void defineSortType(SortType type)
    {
        if (SortType.UpByCost.equals(type))
        {
            serviceComparator = new ComparatorServiceByCost(true);
        }
        else if (SortType.UpByName.equals(type))
        {
            serviceComparator = new ComparatorServiceByName(true);
        }
        else if (SortType.DownByCost.equals(type))
        {
            serviceComparator = new ComparatorServiceByCost(false);
        }
        else if (SortType.DownByName.equals(type))
        {
            serviceComparator = new ComparatorServiceByName(false);
        }
    }

    /**
     * sort services
     */
    public void sort(List<Service> services)
    {
        services.sort(serviceComparator);
    }
}
