package com.netcracker.students.o3.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.netcracker.students.o3.model.area.Area;
import com.netcracker.students.o3.model.area.AreaImpl;
import com.netcracker.students.o3.model.orders.Order;
import com.netcracker.students.o3.model.orders.OrderAction;
import com.netcracker.students.o3.model.orders.OrderImpl;
import com.netcracker.students.o3.model.orders.OrderStatus;
import com.netcracker.students.o3.model.services.Service;
import com.netcracker.students.o3.model.services.ServiceImpl;
import com.netcracker.students.o3.model.services.ServiceStatus;
import com.netcracker.students.o3.model.templates.Template;
import com.netcracker.students.o3.model.templates.TemplateImpl;
import com.netcracker.students.o3.model.users.Customer;
import com.netcracker.students.o3.model.users.CustomerImpl;
import com.netcracker.students.o3.model.users.Employee;
import com.netcracker.students.o3.model.users.EmployerImpl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class Model
{
    private static Model instance;

    @JsonDeserialize(as = HashMap.class,keyAs = BigInteger.class,contentAs = OrderImpl.class)
    private Map<BigInteger, Order> orders;
    @JsonDeserialize(as = HashMap.class,keyAs=BigInteger.class,contentAs = TemplateImpl.class)
    private Map<BigInteger, Template> templates;
    @JsonDeserialize(as = HashMap.class,keyAs=BigInteger.class,contentAs = ServiceImpl.class)
    private Map<BigInteger, Service> services;
    @JsonDeserialize(as = HashMap.class,keyAs=BigInteger.class,contentAs = CustomerImpl.class)
    private Map<BigInteger, Customer> customers;
    @JsonDeserialize(as = HashMap.class,keyAs=BigInteger.class,contentAs = EmployerImpl.class)
    private Map<BigInteger, Employee> employers;
    @JsonDeserialize(as = HashMap.class,keyAs=BigInteger.class,contentAs = AreaImpl.class)
    private Map<BigInteger, Area> areas;
    private BigInteger lastId;


    public void setOrders(final Map<BigInteger, Order> orders)
    {
        this.orders = orders;
    }

    public void setTemplates(final Map<BigInteger, Template> templates)
    {
        this.templates = templates;
    }

    public void setServices(final Map<BigInteger, Service> services)
    {
        this.services = services;
    }

    public void setCustomers(final Map<BigInteger, Customer> customers)
    {
        this.customers = customers;
    }

    public void setEmployers(final Map<BigInteger, Employee> employers)
    {
        this.employers = employers;
    }

    public void setAreas(final Map<BigInteger, Area> areas)
    {
        this.areas = areas;
    }



    public static Model getInstance()
    {
        if (instance == null)
        {
            instance = new Model();
        }
        return instance;
    }

    private Model()
    {
        orders = new HashMap<>();
        templates = new HashMap<>();
        services = new HashMap<>();
        customers = new HashMap<>();
        employers = new HashMap<>();
        areas = new HashMap<>();
        lastId = BigInteger.ZERO;
    }


    public BigInteger getLastId()
    {
        return lastId;
    }


    public void setLastId(final BigInteger lastId)
    {
        this.lastId = lastId;
    }

    @JsonIgnore
    public BigInteger getNextId(){
        lastId = lastId.add(BigInteger.ONE);
        return lastId;
    }



    public BigInteger createCustomer(String name,String login,String password){
        Customer newCustomer = new CustomerImpl(getNextId(),name,login,password);
        BigInteger customerId = newCustomer.getId();

        addCustomer(newCustomer);
        return customerId;
    }

    public BigInteger createEmployee(String name,String login,String password){
        Employee newEmployee = new EmployerImpl(getNextId(),name,login,password);
        BigInteger employeeId = newEmployee.getId();

        addEmployee(newEmployee);
        return employeeId;
    }

    public BigInteger createOrder(BigInteger templateId,BigInteger serviceId,BigInteger employeeId,
            OrderStatus status, OrderAction action){
        Order newOrder = new OrderImpl(getNextId(),templateId,serviceId,employeeId,status,action);
        BigInteger orderId = newOrder.getId();

        addOrder(newOrder);
        return orderId;
    }

    public BigInteger createTemplate(String name, BigDecimal cost,String description){
        Template newTemplate = new TemplateImpl(getNextId(),name,cost,description);
        BigInteger templateId = newTemplate.getId();

        addTemplate(newTemplate);
        return templateId;
    }

    public BigInteger createService(BigInteger userId,BigInteger templateId, ServiceStatus status,BigDecimal cost){
        Service newService = new ServiceImpl(getNextId(),userId,templateId,status,cost);
        BigInteger serviceId = newService.getId();

        addService(newService);
        return serviceId;
    }

    public BigInteger createArea(String name,String description){
        Area newArea = new AreaImpl(getNextId(),name,description);
        BigInteger areaId = newArea.getId();

        addArea(newArea);
        return areaId;
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


}
