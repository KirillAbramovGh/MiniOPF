package com.netcracker.students.o3.controller.searcher;

import com.netcracker.students.o3.model.services.Service;

import java.util.*;

/**
 * class search templates and services
 */
public class SearcherService extends Searcher<Service> {
    private static SearcherService instance;

    private SearcherService() {
    }

    /**
     * search services by cost
     */
    public List<Service> searchServiceByCost(Collection<Service> services, String cost) {
        List<Service> result = new ArrayList<>();

        for (Service service : services) {
            if (isCostInDiapason(service.getCost(), cost, 60)
                    || checkRegExp(cost, service.getCost().toString())
            ) {
                result.add(service);
            }
        }

        return result;
    }


    /**
     * search services by name
     */
    public List<Service> searchServiceByName(Collection<Service> services, String search) {
        List<Service> result = new ArrayList<>();

        String name;
        for (Service service : services) {
            name = service.getName();
            if (name.contains(search) || checkRegExp(search, name)) {
                result.add(service);
            }
        }

        return result;
    }

    /**
     * search services by status
     */
    public List<Service> searchServiceByStatus(Collection<Service> services, String search) {
        List<Service> result = new ArrayList<>();

        String status;
        for (Service service : services) {
            status = service.getStatus().toString().toLowerCase();
            if (status.contains(search) || checkRegExp(search, status)) {
                result.add(service);
            }
        }

        return result;
    }


    /**
     * search services by all fields
     */
    public List<Service> searchServicesByAllEntities(List<Service> services, String searchField) {
        Set<Service> result = new HashSet<>();

        result.addAll(searchServiceByName(services, searchField));
        result.addAll(searchServiceByStatus(services, searchField));
        result.addAll(searchServiceByCost(services, searchField));

        return new ArrayList<>(result);
    }

    public static SearcherService getInstance() {
        if (instance == null) {
            instance = new SearcherService();
        }

        return instance;
    }

    public List<Service> search(String search, String field, Collection<Service> services) {
        switch (field) {
            case "Id":
                return searchServiceById(search, services);
            case "Name":
                return searchServiceByName(services, search);
            case "Cost":
                return searchServiceByCost(services, search);
            case "Status":
                return searchServiceByStatus(services, search);
            case "TemplateId":
                return searchServiceByTemplateId(search, services);
            case "UserId":
                return searchServiceByUserId(search, services);
            case "Areas":
                return searchServiceByArea(search, services);
        }

        return new ArrayList<>();
    }

    private List<Service> searchServiceByArea(String search, Collection<Service> services) {
        List<Service> result = new ArrayList<>();

        for (Service service : services) {
            if (checkArea(search, getServiceArea(service))) {
                result.add(service);
            }
        }

        return result;
    }


    private List<Service> searchServiceByUserId(String search, Collection<Service> services) {
        List<Service> result = new ArrayList<>();

        String userId;
        for (Service service : services) {
            userId = service.getUserId().toString();
            if (userId.contains(search) || checkRegExp(search, userId)) {
                result.add(service);
            }
        }

        return result;
    }

    private List<Service> searchServiceByTemplateId(String search, Collection<Service> services) {
        List<Service> result = new ArrayList<>();

        String templateId;
        for (Service service : services) {
            templateId = service.getTemplateId().toString();
            if (templateId.contains(search) || checkRegExp(search, templateId)) {
                result.add(service);
            }
        }

        return result;
    }

    private List<Service> searchServiceById(String search, Collection<Service> services) {
        List<Service> result = new ArrayList<>();

        String id;
        for (Service service : services) {
            id = service.getId().toString();
            if (id.equals(search) || checkRegExp(search, id)) {
                result.add(service);
            }
        }

        return result;
    }
}
