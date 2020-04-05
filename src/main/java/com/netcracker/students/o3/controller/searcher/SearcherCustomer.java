package com.netcracker.students.o3.controller.searcher;

import com.netcracker.students.o3.controller.ControllerImpl;
import com.netcracker.students.o3.model.users.Customer;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

public class SearcherCustomer extends Searcher<Customer> {
    private static SearcherCustomer instance;

    private SearcherCustomer() {
    }

    public static SearcherCustomer getInstance() {
        if (instance == null) {
            instance = new SearcherCustomer();
        }

        return instance;
    }

    public List<Customer> search(String search, String field, Collection<Customer> customers) {
        switch (field) {
            case "Id":
                return searchById(search, customers);
            case "Name":
                return searchByName(search, customers);
            case "Login":
                return searchByLogin(search, customers);
            case "Balance":
                return searchByBalance(search, customers);
            case "Area":
                return searchByArea(search, customers);
            case "ConnectedServices":
                return searchByConnectedServices(search, customers);
            case "all":
                Set<Customer> res = new HashSet<>(searchById(search, customers));

                res.addAll(searchByName(search, customers));
                res.addAll(searchByLogin(search, customers));
                res.addAll(searchByBalance(search, customers));
                res.addAll(searchByArea(search, customers));
                res.addAll(searchByConnectedServices(search, customers));

                return new ArrayList<>(res);
        }

        return new ArrayList<>();
    }

    private List<Customer> searchByConnectedServices(String search, Collection<Customer> customers) {
        List<Customer> result = new ArrayList<>();

        for (Customer customer : customers) {
            for (BigInteger serviceId : customer.getConnectedServices()) {
                if (checkService(search, serviceId)) {
                    result.add(customer);
                }
            }
        }

        return result;
    }

    private boolean checkService(String search, BigInteger serviceId) {
        return serviceId.toString().equals(search) ||
                checkRegExp(search, serviceId.toString()) ||
                ControllerImpl.getInstance().getServiceName(serviceId).contains(search);
    }

    private List<Customer> searchByArea(String search, Collection<Customer> customers) {
        List<Customer> result = new ArrayList<>();

        for (Customer customer : customers) {
            if (checkArea(search, customer.getArea())) {
                result.add(customer);
            }
        }

        return result;
    }


    private List<Customer> searchByBalance(String search, Collection<Customer> customers) {
        List<Customer> result = new ArrayList<>();

        BigDecimal balance;
        for (Customer customer : customers) {
            balance = customer.getMoneyBalance();
            if (isCostInDiapason(balance, search, 50) || checkRegExp(search, balance.toString())) {
                result.add(customer);
            }
        }

        return result;
    }

    private List<Customer> searchByLogin(String search, Collection<Customer> customers) {
        List<Customer> result = new ArrayList<>();

        String login;
        for (Customer customer : customers) {
            login = customer.getLogin();
            if (customer.getLogin().contains(search) || checkRegExp(search, login)) {
                result.add(customer);
            }
        }

        return result;
    }

    private List<Customer> searchByName(String search, Collection<Customer> customers) {
        List<Customer> result = new ArrayList<>();

        String name;
        for (Customer customer : customers) {
            name = customer.getName();
            if (name.contains(search) || checkRegExp(search, name)) {
                result.add(customer);
            }
        }

        return result;
    }

    private List<Customer> searchById(String search, Collection<Customer> customers) {
        List<Customer> result = new ArrayList<>();

        String id;
        for (Customer customer : customers) {
            id = customer.getId().toString();
            if (id.equals(search)) {
                result.add(customer);
            }
        }

        return result;
    }

}
