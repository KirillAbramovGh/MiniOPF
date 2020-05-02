package com.netcracker.students.o3.controller.sorters;

import com.netcracker.students.o3.controller.comparators.customer.ComparatorCustomersByArea;
import com.netcracker.students.o3.controller.comparators.customer.ComparatorCustomersByBalance;
import com.netcracker.students.o3.controller.comparators.customer.ComparatorCustomersByConnectedServices;
import com.netcracker.students.o3.controller.comparators.customer.ComparatorCustomersById;
import com.netcracker.students.o3.controller.comparators.customer.ComparatorCustomersByLogin;
import com.netcracker.students.o3.controller.comparators.customer.ComparatorCustomersByName;
import com.netcracker.students.o3.controller.comparators.customer.ComparatorCustomersByPassword;
import com.netcracker.students.o3.controller.sorters.SortType.CustomerSortType;
import com.netcracker.students.o3.model.users.Customer;

import java.util.Comparator;
import java.util.List;

public class CustomerSorter {
    /**
     * comparator which compare templates
     */
    private static CustomerSorter instance;

    private CustomerSorter() {
    }

    /**
     * define type of sorting
     *
     * @param type
     */
    private Comparator<Customer> defineSortType(CustomerSortType type) {
        switch (type) {
            case CustomerSortDownById:
                return new ComparatorCustomersById(false);
            case CustomerSortUpById:
                return new ComparatorCustomersById(true);
            case CustomerSortUpByName:
                return new ComparatorCustomersByName(true);
            case CustomerSortDownByName:
                return new ComparatorCustomersByName(false);
            case CustomerSortUpByLogin:
                return new ComparatorCustomersByLogin(true);
            case CustomerSortDownByLogin:
                return new ComparatorCustomersByLogin(false);
            case CustomerSortUpByBalance:
                return new ComparatorCustomersByBalance(true);
            case CustomerSortDownByBalance:
                return new ComparatorCustomersByBalance(false);
            case CustomerSortUpByArea:
                return new ComparatorCustomersByArea(true);
            case CustomerSortDownByArea:
                return new ComparatorCustomersByArea(false);
            case CustomerSortUpByPassword:
                return new ComparatorCustomersByPassword(true);
            case CustomerSortDownByPassword:
                return new ComparatorCustomersByPassword(false);
            case CustomerSortUpByConnectedServices:
                return new ComparatorCustomersByConnectedServices(true);
            case CustomerSortDownByConnectedServices:
                return new ComparatorCustomersByConnectedServices(false);
        }
        return new ComparatorCustomersById(true);
    }

    /**
     * sort services
     *
     * @param services
     */
    public void sort(List<Customer> services, CustomerSortType type) {
        if (type != null) {
            Comparator<Customer> templateComparator = defineSortType(type);
            services.sort(templateComparator);
        }
    }

    public static CustomerSorter getInstance() {
        if (instance == null) {
            instance = new CustomerSorter();
        }
        return instance;
    }
}
