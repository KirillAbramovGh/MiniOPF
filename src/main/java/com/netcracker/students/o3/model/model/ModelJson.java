package com.netcracker.students.o3.model.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.netcracker.students.o3.model.area.Area;
import com.netcracker.students.o3.model.area.AreaImpl;
import com.netcracker.students.o3.model.orders.Order;
import com.netcracker.students.o3.model.orders.OrderAction;
import com.netcracker.students.o3.model.orders.OrderImpl;
import com.netcracker.students.o3.model.orders.OrderStatus;
import com.netcracker.students.o3.model.serializer.Serializer;
import com.netcracker.students.o3.model.serializer.SerializerImpl;
import com.netcracker.students.o3.model.services.Service;
import com.netcracker.students.o3.model.services.ServiceImpl;
import com.netcracker.students.o3.model.services.ServiceStatus;
import com.netcracker.students.o3.model.templates.Template;
import com.netcracker.students.o3.model.templates.TemplateImpl;
import com.netcracker.students.o3.model.users.Customer;
import com.netcracker.students.o3.model.users.CustomerImpl;
import com.netcracker.students.o3.model.users.Employee;
import com.netcracker.students.o3.model.users.EmployerImpl;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * GRUD class for entities
 * */
public class ModelJson implements Model
{
    private static ModelJson instance;

    @JsonDeserialize(as = HashMap.class,keyAs = BigInteger.class,contentAs = OrderImpl.class)
    private Map<BigInteger, Order> orders;
    @JsonDeserialize(as = HashMap.class,keyAs=BigInteger.class,contentAs = TemplateImpl.class)
    private Map<BigInteger, Template> templates;
    @JsonDeserialize(as = HashMap.class,keyAs=BigInteger.class,contentAs = ServiceImpl.class)
    private Map<BigInteger, Service> services;
    @JsonDeserialize(as = HashMap.class,keyAs=BigInteger.class,contentAs = CustomerImpl.class)
    private Map<BigInteger, Customer> customers;
    @JsonDeserialize(as = HashMap.class,keyAs=BigInteger.class,contentAs = EmployerImpl.class)
    private Map<BigInteger, Employee> employees;
    @JsonDeserialize(as = HashMap.class,keyAs=BigInteger.class,contentAs = AreaImpl.class)
    private Map<BigInteger, Area> areas;

    /**
     * last entity id
     * */
    private BigInteger lastId;


    /**
     * method initialize model
     * */
    private ModelJson()
    {
        orders = new HashMap<>();
        templates = new HashMap<>();
        services = new HashMap<>();
        customers = new HashMap<>();
        employees = new HashMap<>();
        areas = new HashMap<>();
        lastId = BigInteger.ZERO;
    }

    /**
     * Methods which set Map of entities
     * */
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

    public void setEmployees(final Map<BigInteger, Employee> employees)
    {
        this.employees = employees;
    }

    public void setAreas(final Map<BigInteger, Area> areas)
    {
        this.areas = areas;
    }



    /**
     * return set and return instance
     * @return instance
     * */
    public static ModelJson getInstance()
    {
        if (instance == null)
        {
            instance = new ModelJson();
        }
        return instance;
    }



    /**
     * @return lastId
     * */
    public BigInteger getLastId()
    {
        return lastId;
    }


    public void setLastId(final BigInteger lastId)
    {
        this.lastId = lastId;
    }


    /**
     * method increment lastId and return
     * @return lastId
     * */
    @JsonIgnore
    public BigInteger getNextId(){
        synchronized (lastId)
        {
            lastId = lastId.add(BigInteger.ONE);
            return lastId;
        }
    }


/**
 *methods which create entities by credentials
 * @return id of created entity
 * */
    public Customer createCustomer(String name,String login,String password,BigInteger areaId){
        synchronized (customers)
        {
            Customer newCustomer = new CustomerImpl(getNextId(), name, login, password, areaId);
            addCustomer(newCustomer);
            return newCustomer;
        }
    }

    public Employee createEmployee(String name,String login,String password){
        synchronized (employees)
        {
            Employee newEmployee = new EmployerImpl(getNextId(), name, login, password);
            addEmployee(newEmployee);

            return newEmployee;
        }
    }

    public Order createOrder(BigInteger templateId,BigInteger serviceId,
            OrderStatus status, OrderAction action){
        synchronized (orders)
        {
            Order newOrder = new OrderImpl(getNextId(), templateId, serviceId, status, action);
            newOrder.setCreationDate(new Date());
            addOrder(newOrder);
            return newOrder;
        }
    }

    public Template createTemplate(String name, BigDecimal cost,String description){
        synchronized (templates)
        {
            Template newTemplate = new TemplateImpl(getNextId(), name, cost, description);
            BigInteger templateId = newTemplate.getId();

            addTemplate(newTemplate);
            return newTemplate;
        }
    }

    public Service createService(BigInteger userId,BigInteger templateId, ServiceStatus status){
        synchronized (services)
        {
            Service newService = new ServiceImpl(getNextId(), userId, templateId, status);
            addService(newService);
            return newService;
        }
    }

    public Area createArea(String name,String description){
        synchronized (areas)
        {
            Area newArea = new AreaImpl(getNextId(), name, description);
            addArea(newArea);
            return newArea;
        }
    }


    /**
     * methods return map of entities
     * @return map of entities
     * */
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

    public Map<BigInteger, Employee> getEmployees()
    {
        return employees;
    }

    public Map<BigInteger, Area> getAreas()
    {
        return areas;
    }



    /**
     * methods which return entity by id
     * @return entity
     * */
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
        return employees.get(id);
    }

    public Area getAreaById(BigInteger id)
    {
        return areas.get(id);
    }



    /**
     * methods which add entity to model
     * */
    public void addOrder(Order order)
    {
        synchronized (orders)
        {
            orders.put(order.getId(), order);
        }
        synchronized (this){
            onDataChange();
        }
    }

    public void addService(Service service)
    {
        synchronized (services)
        {
            services.put(service.getId(), service);
        }
        synchronized (this){
            onDataChange();
        }
    }

    public void addTemplate(Template template)
    {
        synchronized (templates)
        {
            templates.put(template.getId(), template);
        }
        synchronized (this){
            onDataChange();
        }
    }

    public void addCustomer(Customer customer)
    {
        synchronized (customers)
        {
            customers.put(customer.getId(), customer);
        }
        synchronized (this){
            onDataChange();
        }
    }

    public void addEmployee(Employee employee)
    {
        synchronized (employees)
        {
            employees.put(employee.getId(), employee);
        }
        synchronized (this){
            onDataChange();
        }
    }

    public void addArea(Area area)
    {
            synchronized (areas)
            {
                areas.put(area.getId(), area);
            }
        synchronized (this){
            onDataChange();
        }
    }


    /**
     * delete entity by id
     * */
    public void deleteOrderById(BigInteger id)
    {
        synchronized (orders)
        {
            orders.remove(id);
        }
            onDataChange();
    }

    public void deleteTemplateById(BigInteger id)
    {
        synchronized (templates)
        {
            templates.remove(id);
        }
            onDataChange();
    }

    public void deleteServiceById(BigInteger id)
    {
        synchronized (services)
        {
            services.remove(id);
        }
            onDataChange();
    }

    public void deleteCustomerById(BigInteger id)
    {
        synchronized (customers)
        {
            customers.remove(id);
        }
            onDataChange();
    }

    public void deleteEmployeeById(BigInteger id)
    {
        synchronized (employees)
        {
            employees.remove(id);
        }
            onDataChange();
    }

    public void deleteAreaById(BigInteger id)
    {
        synchronized (areas)
        {
            areas.remove(id);
        }
            onDataChange();
    }


    /**
     * methods update entities
     * */
    public void setOrder(Order order)
    {
        synchronized (orders)
        {
            orders.put(order.getId(), order);
        }
            onDataChange();
    }

    public void setTemplate(Template template)
    {
        synchronized (templates)
        {
            templates.put(template.getId(), template);
        }
            onDataChange();
    }

    public void setService(Service service)
    {
        synchronized (services)
        {
            services.put(service.getId(), service);
        }
            onDataChange();
    }

    public void setCustomer(Customer customer)
    {
        synchronized (customers)
        {
            customers.put(customer.getId(), customer);
        }
            onDataChange();
    }

    public void setEmployee(Employee employee)
    {
        synchronized (employees)
        {
            employees.put(employee.getId(), employee);
        }
            onDataChange();
    }

    public void setArea(Area area)
    {
        synchronized (areas){
        areas.put(area.getId(), area);
        }
            onDataChange();
    }

    @Override
    public Area getAreaByName(final String name)
    {
        return null;
    }

    @Override
    public Customer getCustomerByLogin(final String login)
    {
        return null;
    }

    @Override
    public Employee getEmployeeByLogin(final String login)
    {
        return null;
    }

    @Override
    public List<Order> getOrdersByTemplateId(final BigInteger templateId)
    {
        return null;
    }

    @Override
    public List<Order> getOrdersByServiceId(final BigInteger serviceId)
    {
        return null;
    }

    @Override
    public List<Order> getOrdersByEmployeeId(final BigInteger employeeId)
    {
        return null;
    }

    @Override
    public List<Order> getOrdersByStatus(final OrderStatus status)
    {
        return null;
    }

    @Override
    public List<Order> getOrdersByAction(final OrderAction action)
    {
        return null;
    }

    @Override
    public List<Service> getServicesByUserId(final BigInteger userId)
    {
        return null;
    }

    @Override
    public List<Service> getServicesByTemplateId(final BigInteger templateId)
    {
        return null;
    }

    @Override
    public List<Service> getServicesByStatus(final ServiceStatus status)
    {
        return null;
    }

    @Override
    public List<Service> getServicesByStatusAndCustomerId(final BigInteger userId, final ServiceStatus status)
    {
        return null;
    }

    @Override
    public List<Template> getTemplatesByAreaId(final BigInteger areaId)
    {
        return null;
    }

    @Override
    public Template getTemplateByName(final String name)
    {
        return null;
    }


    /**
     * on data change save model
     * */
    private void onDataChange(){
        synchronized (this)
        {
            Serializer serializer = new SerializerImpl();
            try
            {
                serializer.serializeModel(this);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}
