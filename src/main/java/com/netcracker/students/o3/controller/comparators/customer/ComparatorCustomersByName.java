package com.netcracker.students.o3.controller.comparators.customer;

import com.netcracker.students.o3.model.users.Customer;

import java.util.Comparator;

public class ComparatorCustomersByName implements Comparator<Customer> {
    /**
     * define sort up or down
     */
    private boolean isUp;

    public ComparatorCustomersByName(boolean isUp) {
        this.isUp = isUp;
    }

    @Override
    public int compare(final Customer o1, final Customer o2) {
        int res = o1.getName().compareTo(o2.getName());

        if (isUp) return res;
        else return (-1) * res;
    }
}
