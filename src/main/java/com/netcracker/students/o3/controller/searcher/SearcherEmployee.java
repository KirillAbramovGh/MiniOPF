package com.netcracker.students.o3.controller.searcher;

import com.netcracker.students.o3.model.users.Employee;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SearcherEmployee {
    private static SearcherEmployee instance;

    private SearcherEmployee(){
    }

    public static SearcherEmployee getInstance(){
        if(instance == null){
            instance = new SearcherEmployee();
        }

        return instance;
    }

    public List<Employee> search(String search, String field, Collection<Employee> employees) {
        switch (field){
            case "Id": return searchById(search,employees);
            case "Name": return searchByName(search,employees);
            case "Login": return searchByLogin(search,employees);
        }

        return new ArrayList<>();
    }

    private List<Employee> searchByLogin(String search, Collection<Employee> employees) {
        List<Employee> result = new ArrayList<>();

        for(Employee employee : employees){
            if(employee.getId().toString().equals(search)){
                result.add(employee);
            }
        }

        return result;
    }

    private List<Employee> searchByName(String search, Collection<Employee> employees) {
        List<Employee> result = new ArrayList<>();

        for(Employee employee : employees){
            if(employee.getName().contains(search)){
                result.add(employee);
            }
        }

        return result;
    }

    private List<Employee> searchById(String search, Collection<Employee> employees) {
        List<Employee> result = new ArrayList<>();

        for(Employee employee : employees){
            if(employee.getLogin().contains(search)){
                result.add(employee);
            }
        }

        return result;
    }
}
