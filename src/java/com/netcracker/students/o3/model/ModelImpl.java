package com.netcracker.students.o3.model;

import com.netcracker.students.o3.Area.Area;
import com.netcracker.students.o3.Area.AreaImpl;
import com.netcracker.students.o3.orders.Order;
import com.netcracker.students.o3.orders.OrderImpl;
import com.netcracker.students.o3.services.Service;
import com.netcracker.students.o3.services.ServiceImpl;
import com.netcracker.students.o3.templates.Template;
import com.netcracker.students.o3.templates.TemplateImpl;
import com.netcracker.students.o3.users.Customer;
import com.netcracker.students.o3.users.Employer;
import com.netcracker.students.o3.users.User;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.HashMap;

public class ModelImpl
{
    private static ModelImpl instance;

    private HashMap<BigInteger, Order> orders;
    private HashMap<BigInteger, Template> templates;
    private HashMap<BigInteger, Service> services;
    private HashMap<BigInteger, User> customers;
    private HashMap<BigInteger, User> employers;
    private HashMap<BigInteger, Area> areas;

    private File ordersFile;
    private File templatesFile;
    private File servicesFile;
    private File customerFile;
    private File areasFile;
    private File employeeFile;


    private ModelImpl() throws IOException
    {
        ordersFile = new File("orders.json");
        templatesFile = new File("templates.json");
        servicesFile = new File("services.json");
        customerFile = new File("customer.json");
        employeeFile = new File("employee.json");
        areasFile = new File("areas.json");
        deserialize();
    }
    public synchronized void serialize() throws IOException
    {
        Serializer serializer = new Serializer();
        serializer.serializeList(orders,ordersFile);
        serializer.serializeList(templates,templatesFile);
        serializer.serializeList(services,servicesFile);
        serializer.serializeList(employers,employeeFile);
        serializer.serializeList(customers,customerFile);
        serializer.serializeList(areas,areasFile);
    }

    public  synchronized void deserialize() throws IOException
    {
        Serializer serializer = new Serializer();
        orders = serializer.deserializeList(ordersFile,OrderImpl.class);
        templates = serializer.deserializeList(templatesFile,TemplateImpl.class);
        services = serializer.deserializeList(servicesFile,ServiceImpl.class);
        customers = serializer.deserializeList(customerFile,Customer.class);
        employers = serializer.deserializeList(employeeFile, Employer.class);
        areas = serializer.deserializeList(areasFile, AreaImpl.class);
    }

    public  synchronized HashMap<BigInteger, Order> getListOfOrders()
    {
        return orders;
    }

    public synchronized  HashMap<BigInteger, Template> getListOfTemplates()
    {
        return templates;
    }

    public synchronized  HashMap<BigInteger, Service> getListOfServices()
    {
        return services;
    }

    public synchronized HashMap<BigInteger,User> getListOfCustomers(){return customers;}
    public synchronized HashMap<BigInteger,User> getListOfEmployers(){return employers;}

    public synchronized  HashMap<BigInteger, Area> getListOfAreas()
    {
        return areas;
    }

    public synchronized  Order getOrderById(BigInteger id){
        return orders.get(id);
    }

    public  synchronized Template getTemplateById(BigInteger id){
        return templates.get(id);
    }

    public synchronized  Service getServiceById(BigInteger id){
        return services.get(id);
    }

    public synchronized  User getUserById(BigInteger id){
       User user = customers.get(id);
       if(user!=null){
           return user;
       }
       return employers.get(id);
    }
    public synchronized  Area getAreaById(BigInteger id){
        return areas.get(id);
    }

    public synchronized  void addOrder(Order order){
            orders.put(order.getId(),order);
    }

    public synchronized  void addService(Service service){
            services.put(service.getId(),service);

    }

    public synchronized  void addTemplate(Template template){
            templates.put(template.getId(),template);
    }

    public synchronized  void addUser(User user){
        if(user instanceof Customer){
            customers.put(user.getId(),user);
        }else{
            employers.put(user.getId(),user);
        }
    }

    public  synchronized void addArea(Area area){
        if(!orders.containsKey(area.getId())){
            areas.put(area.getId(),area);
        }

    }

    public synchronized  void deleteOrderById(BigInteger id){
        orders.remove(id);
    }

    public synchronized  void deleteTemplateById(BigInteger id){
        templates.remove(id);
    }
    public synchronized  void deleteServiceById(BigInteger id){
        services.remove(id);
    }
    public synchronized  void deleteUserById(BigInteger id){
        employers.remove(id);
        customers.remove(id);
    }
    public synchronized  void deleteAreaById(BigInteger id){
        areas.remove(id);
    }

    public synchronized  void setOrder(Order order){
        orders.put(order.getId(),order);
    }

    public synchronized  void setTemplate(Template template){
        templates.put(template.getId(),template);
    }

    public synchronized  void setService(Service service){
        services.put(service.getId(),service);
    }

    public synchronized  void setUser(User user){
        if(user instanceof Customer){
            customers.put(user.getId(),user);
        }else{
            employers.put(user.getId(),user);
        }

    }

    public synchronized  void setArea(Area area){
        areas.put(area.getId(),area);
    }

    public synchronized static ModelImpl getInstance() throws IOException
    {
        if(instance==null){
            instance = new ModelImpl();
        }
        return instance;
    }

}
