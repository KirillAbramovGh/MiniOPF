package com.netcracker.students.o3.controller.sorters;

import com.netcracker.students.o3.controller.comparators.order.ComparatorOrdersByAction;
import com.netcracker.students.o3.controller.comparators.order.ComparatorOrdersByCreationDate;
import com.netcracker.students.o3.controller.comparators.order.ComparatorOrdersByEmployeeId;
import com.netcracker.students.o3.controller.comparators.order.ComparatorOrdersById;
import com.netcracker.students.o3.controller.comparators.order.ComparatorOrdersByServiceId;
import com.netcracker.students.o3.controller.comparators.order.ComparatorOrdersByStatus;
import com.netcracker.students.o3.controller.comparators.order.ComparatorOrdersByTemplateId;
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
            case OrderSortDownById:
                return new ComparatorOrdersById(false);
            case OrderSortUpById:
                return new ComparatorOrdersById(true);
            case OrderSortUpByAction:
                return new ComparatorOrdersByAction(true);
            case OrderSortUpByStatus:
                return new ComparatorOrdersByStatus(true);
            case OrderSortDownByAction:
                return new ComparatorOrdersByAction(false);
            case OrderSortDownByStatus:
                return new ComparatorOrdersByStatus(false);
            case OrderSortUpByServiceId:
                return new ComparatorOrdersByServiceId(true);
            case OrderSortUpByEmployeeId:
                return new ComparatorOrdersByEmployeeId(true);
            case OrderSortUpByTemplateId:
                return new ComparatorOrdersByTemplateId(true);
            case OrderSortDownByServiceId:
                return new ComparatorOrdersByServiceId(false);
            case OrderSortDownByEmployeeId:
                return new ComparatorOrdersByEmployeeId(false);
            case OrderSortDownByTemplateId:
                return new ComparatorOrdersByTemplateId(false);
            case OrderSortUpByCreationDate:
                return new ComparatorOrdersByCreationDate(true);
            case OrderSortDownByCreationDate:
                return new ComparatorOrdersByCreationDate(false);
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
