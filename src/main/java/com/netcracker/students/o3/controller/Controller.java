package com.netcracker.students.o3.controller;

import com.netcracker.students.o3.Exceptions.IncorrectCredentialsException;
import com.netcracker.students.o3.Exceptions.LoginOccupiedException;
import com.netcracker.students.o3.Exceptions.UnpossibleChangeAreaException;
import com.netcracker.students.o3.Exceptions.WrongInputException;
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
import com.netcracker.students.o3.model.users.User;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

public interface Controller
{
    void startOrder(BigInteger orderId, BigInteger employeeId);

    void suspendOrder(BigInteger orderId);

    void stopOrder(BigInteger orderId);

    void completeOrder(BigInteger orderId);

    void changeBalance(BigInteger customerId, BigDecimal value);

    void deleteArea(BigInteger areaId);

    void deleteOrder(BigInteger orderId);

    void deleteService(BigInteger serviceId);

    void deleteTemplate(BigInteger templateId);

    void deleteCustomer(BigInteger customerId);

    void deleteEmployee(BigInteger employeeId);

    //удаляет сущность вместе со связанными объектами
    void deepDeleteArea(BigInteger areaId);

    void deepDeleteOrder(BigInteger orderId);

    void deepDeleteService(BigInteger serviceId);

    void deepDeleteTemplate(BigInteger templateId);

    void deepDeleteCustomer(BigInteger customerId);

    void deepDeleteEmployee(BigInteger employeeId);

    //создает сущности

     BigInteger createCustomer(String name,String login,String password,BigInteger areaId);

     BigInteger createEmployee(String name,String login,String password);

     BigInteger createOrder(BigInteger templateId,BigInteger serviceId,BigInteger employeeId,
            OrderStatus status, OrderAction action);

     BigInteger createTemplate(String name, BigDecimal cost,String description);

     BigInteger createService(BigInteger userId,BigInteger templateId, ServiceStatus status,BigDecimal cost);

     BigInteger createArea(String name,String description);



    User getUserByCredentials(String login, String password) throws IncorrectCredentialsException;

    BigInteger registerCustomer(String login, String password, String Name, BigInteger areaId);

    BigInteger registerEmployee(String login, String password, String Name);

    boolean checkPassword(BigInteger id, String password);

    boolean isLoginExists(String login);

    List<Service> getSuspendedServices(BigInteger customerId);

    List<Service> getEnteringServices(BigInteger customerId);


    List<Service> getActiveServices(BigInteger customerId);

    List<Template> getTemplatesByAreaId(BigInteger areaId);

    List<Template> getAllTemplates();

    List<Order> getOrdersByCustomerId(BigInteger customerId);

    List<Order> getOrdersByEmployeeId(BigInteger employeeId);

    BigInteger getCustomerAreaId(BigInteger customerId);

    boolean isCustomer(BigInteger userId);

    boolean isEmployee(BigInteger userId);

    Customer getCustomer(BigInteger userId);

    Employee getEmployee(BigInteger userId);

    List<Area> getAreas();

    List<Template> getTemplates();

    List<Service> getServices();

    List<Customer> getCustomers();

    List<Order> getOrders();

    List<Employee> getEmployes();


    Area getArea(BigInteger areaId);

    Template getTemplate(BigInteger templateId);

    Service getService(BigInteger serviceId);

    Order getOrder(BigInteger orderId);

    void putOnBalance(BigInteger customerId, BigDecimal money);

    List<Service> getServicesByAreaId(BigInteger areaId);

    boolean isCustomerLogin(String login);

    boolean isEmployeeLogin(String login);

    BigDecimal getBalance(BigInteger customerId);

    String getCustomerFio(BigInteger customerId);

    String getAreaName(BigInteger customerId);

    List<Service> getCustomerAvailableServices(BigInteger customerId);

    void setCustomerName(BigInteger customerId, String name) throws WrongInputException;

    void setUserLogin(BigInteger userId, String login) throws LoginOccupiedException, WrongInputException;

    void setUserPassword(BigInteger userId, String password) throws WrongInputException;

    void setCustomerArea(BigInteger customerId, BigInteger areaId) throws UnpossibleChangeAreaException;

}
