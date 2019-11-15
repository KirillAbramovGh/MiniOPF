package com.netcracker.students.o3.model;

import com.netcracker.students.o3.model.area.Area;
import com.netcracker.students.o3.model.orders.Order;
import com.netcracker.students.o3.model.services.Service;
import com.netcracker.students.o3.model.templates.Template;
import com.netcracker.students.o3.model.users.Customer;
import com.netcracker.students.o3.model.users.Employee;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class Model
{
    private static Model instance;

    private final Map<BigInteger, Order> orders;
    private final Map<BigInteger, Template> templates;
    private final Map<BigInteger, Service> services;
    private final Map<BigInteger, Customer> customers;
    private final Map<BigInteger, Employee> employers;
    private final Map<BigInteger, Area> areas;

    private Model()
    {
        orders = new HashMap<>();
        templates = new HashMap<>();
        services = new HashMap<>();
        customers = new HashMap<>();
        employers = new HashMap<>();
        areas = new HashMap<>();
    }

    public Map<BigInteger, Order> getOrders()
    {
        return orders;
    }

    public Map<BigInteger, Template> getTemplates()
    {
        return templates;
    }

    public Map<BigInteger, Service> getServices()
    {
        return services;
    }

    public Map<BigInteger, Customer> getCustomers()
    {
        return customers;
    }

    public Map<BigInteger, Employee> getEmployers()
    {
        return employers;
    }

    public Map<BigInteger, Area> getAreas()
    {
        return areas;
    }

    public Order getOrderById(BigInteger id)
    {
        return orders.get(id);
    }

    public Template getTemplateById(BigInteger id)
    {
        return templates.get(id);
    }

    public Service getServiceById(BigInteger id)
    {
        return services.get(id);
    }

    public Customer getCustomerById(BigInteger id)
    {
        return customers.get(id);
    }

    public Employee getEmployeeById(BigInteger id)
    {
        return employers.get(id);
    }

    public Area getAreaById(BigInteger id)
    {
        return areas.get(id);
    }

    public void addOrder(Order order)
    {
        orders.put(order.getId(), order);
    }

    public void addService(Service service)
    {
        services.put(service.getId(), service);

    }

    public void addTemplate(Template template)
    {
        templates.put(template.getId(), template);
    }

    public void addCustomer(Customer customer)
    {
        customers.put(customer.getId(), customer);
    }

    public void addEmployee(Employee employee)
    {
        employers.put(employee.getId(), employee);
    }

    public void addArea(Area area)
    {
        if (!orders.containsKey(area.getId()))
        {
            areas.put(area.getId(), area);
        }

    }

    public void deleteOrderById(BigInteger id)
    {
        orders.remove(id);
    }

    public void deleteTemplateById(BigInteger id)
    {
        templates.remove(id);
    }

    public void deleteServiceById(BigInteger id)
    {
        services.remove(id);
    }

    public void deleteCustomerById(BigInteger id)
    {
        customers.remove(id);
    }

    public void deleteEmployeeById(BigInteger id)
    {
        employers.remove(id);
    }

    public void deleteAreaById(BigInteger id)
    {
        areas.remove(id);
    }

    public void setOrder(Order order)
    {
        orders.put(order.getId(), order);
    }

    public void setTemplate(Template template)
    {
        templates.put(template.getId(), template);
    }

    public void setService(Service service)
    {
        services.put(service.getId(), service);
    }

    public void setCustomer(Customer user)
    {
        customers.put(user.getId(), user);
    }

    public void setEmployee(Employee employee)
    {
        employers.put(employee.getId(), employee);
    }

    public void setArea(Area area)
    {
        areas.put(area.getId(), area);
    }

    public static Model getInstance()
    {
        if (instance == null)
        {
            instance = new Model();
        }
        return instance;
    }

}
