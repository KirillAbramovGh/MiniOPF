package com.netcracker.students.o3.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.netcracker.students.o3.model.area.Area;
import com.netcracker.students.o3.model.area.AreaImpl;
import com.netcracker.students.o3.model.orders.Order;
import com.netcracker.students.o3.model.orders.OrderImpl;
import com.netcracker.students.o3.model.services.Service;
import com.netcracker.students.o3.model.services.ServiceImpl;
import com.netcracker.students.o3.model.templates.Template;
import com.netcracker.students.o3.model.templates.TemplateImpl;
import com.netcracker.students.o3.model.users.Customer;
import com.netcracker.students.o3.model.users.CustomerImpl;
import com.netcracker.students.o3.model.users.Employee;
import com.netcracker.students.o3.model.users.EmployerImpl;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class Model
{
    private static Model instance;

    @JsonDeserialize(as = HashMap.class,keyAs = BigInteger.class,contentAs = OrderImpl.class)
    private final Map<BigInteger, Order> orders;
    @JsonDeserialize(as = HashMap.class,keyAs=BigInteger.class,contentAs = TemplateImpl.class)
    private final Map<BigInteger, Template> templates;
    @JsonDeserialize(as = HashMap.class,keyAs=BigInteger.class,contentAs = ServiceImpl.class)
    private final Map<BigInteger, Service> services;
    @JsonDeserialize(as = HashMap.class,keyAs=BigInteger.class,contentAs = CustomerImpl.class)
    private final Map<BigInteger, Customer> customers;
    @JsonDeserialize(as = HashMap.class,keyAs=BigInteger.class,contentAs = EmployerImpl.class)
    private final Map<BigInteger, Employee> employers;
    @JsonDeserialize(as = HashMap.class,keyAs=BigInteger.class,contentAs = AreaImpl.class)
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
        synchronized (orders)
        {
            orders.put(order.getId(), order);
        }
    }

    public void addService(Service service)
    {
        synchronized (services)
        {
            services.put(service.getId(), service);
        }
    }

    public void addTemplate(Template template)
    {
        synchronized (templates)
        {
            templates.put(template.getId(), template);
        }
    }

    public void addCustomer(Customer customer)
    {
        synchronized (customers)
        {
            customers.put(customer.getId(), customer);
        }
    }

    public void addEmployee(Employee employee)
    {
        synchronized (employers)
        {
            employers.put(employee.getId(), employee);
        }
    }

    public void addArea(Area area)
    {
            synchronized (areas)
            {
                areas.put(area.getId(), area);
            }
    }

    public void deleteOrderById(BigInteger id)
    {
        synchronized (orders)
        {
            orders.remove(id);
        }
    }

    public void deleteTemplateById(BigInteger id)
    {
        synchronized (templates)
        {
            templates.remove(id);
        }
    }

    public void deleteServiceById(BigInteger id)
    {
        synchronized (services)
        {
            services.remove(id);
        }
    }

    public void deleteCustomerById(BigInteger id)
    {
        synchronized (customers)
        {
            customers.remove(id);
        }
    }

    public void deleteEmployeeById(BigInteger id)
    {
        synchronized (employers)
        {
            employers.remove(id);
        }
    }

    public void deleteAreaById(BigInteger id)
    {
        synchronized (areas)
        {
            areas.remove(id);
        }
    }

    public void setOrder(Order order)
    {
        synchronized (orders)
        {
            orders.put(order.getId(), order);
        }
    }

    public void setTemplate(Template template)
    {
        synchronized (templates)
        {
            templates.put(template.getId(), template);
        }
    }

    public void setService(Service service)
    {
        synchronized (services)
        {
            services.put(service.getId(), service);
        }
    }

    public void setCustomer(Customer user)
    {
        synchronized (customers)
        {
            customers.put(user.getId(), user);
        }
    }

    public void setEmployee(Employee employee)
    {
        synchronized (employers)
        {
            employers.put(employee.getId(), employee);
        }
    }

    public void setArea(Area area)
    {
        synchronized (areas){
        areas.put(area.getId(), area);
        }
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
