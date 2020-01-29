package com.netcracker.students.o3.controller.searcher;

import com.netcracker.students.o3.model.orders.Order;

import java.util.*;

public class SearcherOrders extends Searcher<Order> {
    private static SearcherOrders instance;

    private SearcherOrders() {
    }

    public static SearcherOrders getInstance() {
        if (instance == null) {
            instance = new SearcherOrders();
        }

        return instance;
    }

    public List<Order> search(String search, String field, Collection<Order> orders) {
        switch (field) {
            case "Id":
                return searchOrderById(search, orders);
            case "TemplateId":
                return searchOrderByTemplateId(search, orders);
            case "ServiceId":
                return searchOrderByServiceId(search, orders);
            case "EmployeeId":
                return searchOrderByEmployeeId(search, orders);
            case "Status":
                return searchOrderByStatus(search, orders);
            case "Action":
                return searchOrderByAction(search, orders);
            case "all":
                Set<Order> res = new HashSet<>(searchOrderById(search, orders));

                res.addAll(searchOrderByTemplateId(search, orders));
                res.addAll(searchOrderByServiceId(search, orders));
                res.addAll(searchOrderByEmployeeId(search, orders));
                res.addAll(searchOrderByStatus(search, orders));
                res.addAll(searchOrderByAction(search, orders));

                return new ArrayList<>(res);
        }

        return new ArrayList<>();
    }

    private List<Order> searchOrderByAction(String search, Collection<Order> orders) {
        List<Order> result = new ArrayList<>();

        String action;
        for (Order order : orders) {
            action = order.getAction().toString();
            if (action.contains(search) || checkRegExp(search, action)) {
                result.add(order);
            }
        }

        return result;
    }

    private List<Order> searchOrderByStatus(String search, Collection<Order> orders) {
        List<Order> result = new ArrayList<>();

        String status;
        for (Order order : orders) {
            status = order.getStatus().toString();
            if (status.contains(search) || checkRegExp(search, status)) {
                result.add(order);
            }
        }

        return result;
    }

    private List<Order> searchOrderByEmployeeId(String search, Collection<Order> orders) {
        List<Order> result = new ArrayList<>();

        String employeeId;
        for (Order order : orders) {
            employeeId = order.getEmployeeId().toString();
            if (employeeId.contains(search) || checkRegExp(search, employeeId)) {
                result.add(order);
            }
        }

        return result;
    }

    private List<Order> searchOrderByServiceId(String search, Collection<Order> orders) {
        List<Order> result = new ArrayList<>();

        String serviceId;
        for (Order order : orders) {
            serviceId = order.getServiceId().toString();
            if (serviceId.contains(search) || checkRegExp(search, serviceId)) {
                result.add(order);
            }
        }

        return result;
    }

    private List<Order> searchOrderByTemplateId(String search, Collection<Order> orders) {
        List<Order> result = new ArrayList<>();

        String templateId;
        for (Order order : orders) {
            templateId = order.getTemplateId().toString();
            if (templateId.contains(search) || checkRegExp(search, templateId)) {
                result.add(order);
            }
        }

        return result;
    }

    private List<Order> searchOrderById(String search, Collection<Order> orders) {
        List<Order> result = new ArrayList<>();

        String id;
        for (Order order : orders) {
            id = order.getId().toString();
            if (id.equals(search) || checkRegExp(search, id)) {
                result.add(order);
            }
        }

        return result;
    }


}
