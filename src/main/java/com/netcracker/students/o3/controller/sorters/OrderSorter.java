package com.netcracker.students.o3.controller.sorters;

import com.netcracker.students.o3.controller.comparators.order.ComparatorOrdersById;
import com.netcracker.students.o3.controller.sorters.SortType.OrderSortType;
import com.netcracker.students.o3.model.orders.Order;

import java.util.Comparator;
import java.util.List;

public class OrderSorter {
    /**
     * comparator which compare templates
     */
    private static OrderSorter instance;

    private OrderSorter() {
    }

    /**
     * define type of sorting
     *
     * @param type
     */
    private Comparator<Order> defineSortType(OrderSortType type) {
        switch (type) {
            case DownById:
                return new ComparatorOrdersById(false);
            case UpById:
                return new ComparatorOrdersById(true);
        }
        return new ComparatorOrdersById(true);
    }

    /**
     * sort services
     *
     * @param services
     */
    public void sort(List<Order> services, OrderSortType type) {
        if (type != null) {
            Comparator<Order> templateComparator = defineSortType(type);
            services.sort(templateComparator);
        }
    }

    public static OrderSorter getInstance() {
        if (instance == null) {
            instance = new OrderSorter();
        }
        return instance;
    }
}
