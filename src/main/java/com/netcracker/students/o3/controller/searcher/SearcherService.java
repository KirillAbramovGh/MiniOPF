package com.netcracker.students.o3.controller.searcher;

import com.netcracker.students.o3.model.services.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * class search templates and services
 */
public class SearcherService {
    private static SearcherService instance;

    private SearcherService() {
    }

    /**
     * search services by cost
     */
    public List<Service> searchServiceByCost(List<Service> services, BigDecimal cost) {
        List<Service> result = new ArrayList<>();

        for (Service service : services) {
            if (isServiceCostEqualsCost(service, cost)) {
                result.add(service);
            }
        }

        return result;
    }

    private boolean isServiceCostEqualsCost(Service service, BigDecimal cost) {
        return Math.abs(service.getCost().doubleValue() - cost.doubleValue()) < 10;
    }

    /**
     * search services by name
     */
    public List<Service> searchServiceByName(List<Service> services, String name) {
        List<Service> result = new ArrayList<>();

        for (Service service : services) {
            if (isServiceNameContainsString(service, name)) {
                result.add(service);
            }
        }

        return result;
    }

    private boolean isServiceNameContainsString(Service service, String name) {
        String serviceName = service.getName().toLowerCase();

        return serviceName.contains(name.toLowerCase());
    }

    /**
     * search services by status
     */
    public List<Service> searchServiceByStatus(List<Service> services, String status) {
        List<Service> result = new ArrayList<>();

        for (Service service : services) {
            if (isServiceStatusNameContainsString(service, status)) {
                result.add(service);
            }
        }

        return result;
    }

    private boolean isServiceStatusNameContainsString(Service service, String status) {
        String serviceStatusName = service.getStatus().toString();
        return serviceStatusName.toLowerCase().contains(status.toLowerCase());
    }

    /**
     * search services by all fields
     */
    public List<Service> searchServices(List<Service> services, String searchField) {
        Set<Service> result = new HashSet<>();

        result.addAll(searchServiceByName(services, searchField));
        result.addAll(searchServiceByStatus(services, searchField));
        result.addAll(searchServiceByCost(services, parseBigDecimal(searchField)));

        return new ArrayList<>(result);
    }


    private BigDecimal parseBigDecimal(String value) {
        try {
            double doubleValue = Double.parseDouble(value);
            return BigDecimal.valueOf(doubleValue);
        } catch (NumberFormatException e) {
            return BigDecimal.ZERO;
        }
    }

    public static SearcherService getInstance() {
        if (instance == null) {
            instance = new SearcherService();
        }

        return instance;
    }
}
