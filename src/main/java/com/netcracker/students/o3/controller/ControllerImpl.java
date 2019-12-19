package com.netcracker.students.o3.controller;

import com.netcracker.students.o3.Exceptions.IncorrectCredentialsException;
import com.netcracker.students.o3.Exceptions.LoginOccupiedException;
import com.netcracker.students.o3.Exceptions.UnpossibleChangeAreaException;
import com.netcracker.students.o3.Exceptions.WrongInputException;
import com.netcracker.students.o3.model.Model;
import com.netcracker.students.o3.model.area.Area;
import com.netcracker.students.o3.model.orders.Order;
import com.netcracker.students.o3.model.orders.OrderAction;
import com.netcracker.students.o3.model.orders.OrderImpl;
import com.netcracker.students.o3.model.orders.OrderStatus;
import com.netcracker.students.o3.model.serializer.Serializer;
import com.netcracker.students.o3.model.serializer.SerializerImpl;
import com.netcracker.students.o3.model.services.Service;
import com.netcracker.students.o3.model.services.ServiceStatus;
import com.netcracker.students.o3.model.templates.Template;
import com.netcracker.students.o3.model.users.Customer;
import com.netcracker.students.o3.model.users.CustomerImpl;
import com.netcracker.students.o3.model.users.Employee;
import com.netcracker.students.o3.model.users.EmployerImpl;
import com.netcracker.students.o3.model.users.User;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class ControllerImpl implements Controller
{
    private final Model model;
    private static Controller instance;

    private ControllerImpl()
    {
        model = Model.getInstance();
        Serializer serializer = new SerializerImpl();

        try
        {
            serializer.deserializeModel(model);
        }catch (Exception e){
            e.printStackTrace();
        }
    }



    @Override
    public void startOrder(final BigInteger orderId, final BigInteger employeeId)
    {

    }

    @Override
    public void suspendOrder(final BigInteger orderId)
    {

    }

    @Override
    public void stopOrder(final BigInteger orderId)
    {

    }

    @Override
    public void completeOrder(final BigInteger orderId)
    {

    }

    @Override
    public void changeBalance(final BigInteger customerId, final BigDecimal value)
    {

    }

    @Override
    public void deleteArea(final BigInteger areaId)
    {

    }

    @Override
    public void deleteOrder(final BigInteger orderId)
    {

    }

    @Override
    public void deleteService(final BigInteger serviceId)
    {

    }

    @Override
    public void deleteTemplate(final BigInteger templateId)
    {

    }

    @Override
    public void deleteCustomer(final BigInteger customerId)
    {

    }

    @Override
    public void deleteEmployee(final BigInteger employeeId)
    {

    }

    @Override
    public void deepDeleteArea(final BigInteger areaId)
    {

    }

    @Override
    public void deepDeleteOrder(final BigInteger orderId)
    {

    }

    @Override
    public void deepDeleteService(final BigInteger serviceId)
    {

    }

    @Override
    public void deepDeleteTemplate(final BigInteger templateId)
    {

    }

    @Override
    public void deepDeleteCustomer(final BigInteger customerId)
    {

    }

    @Override
    public void deepDeleteEmployee(final BigInteger employeeId)
    {

    }

    @Override
    public BigInteger createCustomer(final String name, final String login, final String password,final BigInteger areaId)
    {
        BigInteger customerId = model.createCustomer(name,login,password,areaId);
        return customerId;
    }

    @Override
    public BigInteger createEmployee(final String name, final String login, final String password)
    {
        BigInteger employeeId = model.createEmployee(name,login,password);
        return employeeId;
    }

    @Override
    public BigInteger createOrder(final BigInteger templateId, final BigInteger serviceId, final BigInteger employeeId,
            final OrderStatus status, final OrderAction action)
    {
        BigInteger orderId = model.createOrder(templateId,serviceId,employeeId,status,action);
        return orderId;
    }

    @Override
    public BigInteger createTemplate(final String name, final BigDecimal cost, final String description)
    {
        BigInteger templateId = model.createTemplate(name,cost,description);
        return templateId;
    }

    @Override
    public BigInteger createService(final BigInteger userId, final BigInteger templateId, final ServiceStatus status,
            final BigDecimal cost)
    {
        BigInteger serviceId = model.createService(userId,templateId,status,cost);
        return serviceId;
    }

    @Override
    public BigInteger createArea(final String name, final String description)
    {
        BigInteger areaId = model.createArea(name,description);
        return areaId;
    }

    @Override
    public User getUserByCredentials(final String login, final String password)
            throws IncorrectCredentialsException
    {
        for (Customer customer : model.getCustomers().values())
        {
            if (customer.getLogin().equals(login) && customer.getPassword().equals(password))
            {
                return customer;
            }
        }

        for (Employee employee : model.getEmployers().values())
        {
            if (employee.getLogin().equals(login) && employee.getPassword().equals(password))
            {
                return employee;
            }
        }
        throw new IncorrectCredentialsException("Неправильный логин или пароль!");
    }


    @Override
    public BigInteger registerCustomer(final String login, final String password, final String name,
            final BigInteger areaId)
    {
        return  createCustomer(name,login,password,areaId);
    }

    @Override
    public BigInteger registerEmployee(final String login, final String password, final String Name)
    {
        Employee employee = new EmployerImpl();
        employee.setId(model.getNextId());
        model.addEmployee(employee);
        return employee.getId();
    }

    @Override
    public boolean checkPassword(final BigInteger id, final String password)
    {
        return model.getCustomerById(id).getPassword().equals(password);
    }

    @Override
    public boolean isLoginExists(final String login)
    {
        return isEmployeeLogin(login) || isCustomerLogin(login);
    }

    @Override
    public List<Service> getSuspendedServices(final BigInteger customerId)
    {
        ArrayList<Service> services = new ArrayList<>();
        for (BigInteger v : model.getCustomerById(customerId).getConnectedServicesIds())
        {
            if (model.getServiceById(v).getStatus() == ServiceStatus.Suspended)
            {
                services.add(model.getServiceById(v));
            }
        }
        return services;
    }

    @Override
    public List<Service> getEnteringServices(final BigInteger customerId)
    {
        ArrayList<Service> services = new ArrayList<>();
        for (BigInteger v : model.getCustomerById(customerId).getConnectedServicesIds())
        {

            if (model.getServiceById(v).getStatus() == ServiceStatus.Entering)
            {
                services.add(model.getServiceById(v));
            }
        }
        return services;
    }


    @Override
    public List<Service> getActiveServices(final BigInteger customerId)
    {
        ArrayList<Service> services = new ArrayList<>();
        for (BigInteger serviceId : model.getCustomerById(customerId).getConnectedServicesIds())
        {
            services.add(model.getServiceById(serviceId));
        }

        return services;
    }

    @Override
    public List<Template> getTemplatesByAreaId(final BigInteger areaId)
    {
        ArrayList<Template> templates = new ArrayList<>();
        for (Template template : model.getTemplates().values())
        {
            if (template.getPossibleAreasId().contains(areaId))
            {
                templates.add(template);
            }
        }

        return templates;
    }

    @Override
    public List<Template> getAllTemplates()
    {
        ArrayList<Template> templates = new ArrayList<>();
        for (Template template : model.getTemplates().values())
        {
            templates.add(template);
        }

        return templates;
    }

    @Override
    public List<Order> getOrdersByCustomerId(final BigInteger customerId)
    {
        return null;
    }

    @Override
    public List<Order> getOrdersByEmployeeId(final BigInteger employeeId)
    {
        return null;
    }

    @Override
    public BigInteger getCustomerAreaId(final BigInteger customerId)
    {
        return model.getCustomerById(customerId).getAreaId();
    }

    @Override
    public boolean isCustomer(final BigInteger userId)
    {
        return model.getCustomerById(userId) != null;
    }

    @Override
    public boolean isEmployee(final BigInteger userId)
    {
        return model.getEmployeeById(userId) != null;
    }

    @Override
    public Customer getCustomer(final BigInteger id)
    {
        return model.getCustomerById(id);
    }

    @Override
    public Employee getEmployee(final BigInteger userId)
    {
        return model.getEmployeeById(userId);
    }

    @Override
    public List<Area> getAreas()
    {
        ArrayList<Area> areas = new ArrayList<>();
        for (Area area : model.getAreas().values())
        {
            areas.add(area);
        }
        return areas;
    }

    @Override
    public List<Template> getTemplates()
    {
        return null;
    }

    @Override
    public List<Service> getServices()
    {
        return null;
    }

    @Override
    public List<Customer> getCustomers()
    {
        return null;
    }

    @Override
    public List<Order> getOrders()
    {
        return null;
    }

    @Override
    public List<Employee> getEmployes()
    {
        return null;
    }

    @Override
    public Area getArea(final BigInteger areaId)
    {
        return model.getAreaById(areaId);
    }

    @Override
    public Template getTemplate(final BigInteger templateId)
    {
        return model.getTemplateById(templateId);
    }

    @Override
    public Service getService(final BigInteger serviceId)
    {
        return model.getServiceById(serviceId);
    }

    @Override
    public Order getOrder(final BigInteger orderId)
    {
        return model.getOrderById(orderId);
    }

    @Override
    public void putOnBalance(final BigInteger customerId, final BigDecimal money)
    {
        Customer customer = model.getCustomerById(customerId);
        BigDecimal currentMoney = customer.getMoneyBalance();
        customer.setMoneyBalance(currentMoney.add(money));
    }

    @Override
    public List<Service> getServicesByAreaId(final BigInteger areaId)
    {
        List<Service> availableServices = new ArrayList<>();
        List<Service> allServices = getServices();

        for(Service service : allServices ){
            Template template = getTemplate(service.getTemplateId());
            if(template.getPossibleAreasId().contains(areaId)){
                availableServices.add(service);
            }
        }

        return availableServices;
    }

    @Override
    public boolean isCustomerLogin(final String login)
    {
        List<Customer> customers = getCustomers();

        for(User user : customers){
            if(user.getLogin().equals(login)){
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean isEmployeeLogin(final String login)
    {
        List<Employee> employees = getEmployes();

        for(User user : employees){
            if(user.getLogin().equals(login)){
                return false;
            }
        }

        return true;
    }

    public static Controller getInstance()
    {
        if (instance == null)
        {
            instance = new ControllerImpl();
        }

        return instance;
    }

    public BigDecimal getBalance(BigInteger customerId){
        System.out.println(getCustomer(customerId));
        return getCustomer(customerId).getMoneyBalance();
    }

    public String getCustomerFio(BigInteger customerId){
        return getCustomer(customerId).getName();
    }

    public String getAreaName(BigInteger customerId){
        return getArea(getCustomer(customerId).getAreaId()).getName();
    }

    public List<Service> getCustomerAvailableServices(BigInteger customerId){
        return getServicesByAreaId(getCustomerAreaId(customerId));
    }

    public void setCustomerName(BigInteger customerId,String name) throws WrongInputException
    {
        if (!name.isEmpty())
        {
            getCustomer(customerId).setName(name);
        }
        else
        {
            throw new WrongInputException("Имя не может быть пустым!");
        }
    }

    public void setUserLogin(BigInteger userId,String login) throws LoginOccupiedException, WrongInputException
    {
        if(!login.isEmpty()){
            if(!isLoginExists(login)){
                getUser(userId).setLogin(login);
            }else {
                throw new LoginOccupiedException("Логин занят");
            }
        }else {
            throw new WrongInputException("Логин не может быть пустым");
        }
    }

    private User getUser(BigInteger userId){
        User user = getCustomer(userId);

        if(user == null){
            user = getEmployee(userId);
        }

        return user;
    }

    public void setUserPassword(BigInteger userId, String password) throws WrongInputException
    {
        if(!password.isEmpty()){
            getUser(userId).setPassword(password);
        }else {
            throw new WrongInputException("Пароль не может быть пустым");
        }
    }

    public void setCustomerArea(BigInteger customerId, BigInteger areaId) throws UnpossibleChangeAreaException
    {
        List<Service> activeAndEnteringServices = getEnteringServices(customerId);
        activeAndEnteringServices.addAll(getActiveServices(customerId));

        for (Service service: activeAndEnteringServices
             )
        {
            Template template = getTemplate(service.getTemplateId());
            if (!template.getPossibleAreasId().contains(areaId))
            {
                throw new UnpossibleChangeAreaException("Не возможно изменить район");
            }
        }

        getCustomer(customerId).setAreaId(areaId);
    }
}
