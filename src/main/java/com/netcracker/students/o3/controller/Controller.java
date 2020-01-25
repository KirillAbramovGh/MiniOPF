package com.netcracker.students.o3.controller;

import com.netcracker.students.o3.Exceptions.IncorrectCredentialsException;
import com.netcracker.students.o3.Exceptions.LoginOccupiedException;
import com.netcracker.students.o3.Exceptions.UnpossibleChangeAreaException;
import com.netcracker.students.o3.Exceptions.WrongInputException;
import com.netcracker.students.o3.model.area.Area;
import com.netcracker.students.o3.model.orders.Order;
import com.netcracker.students.o3.model.orders.OrderAction;
import com.netcracker.students.o3.model.orders.OrderStatus;
import com.netcracker.students.o3.model.services.Service;
import com.netcracker.students.o3.model.services.ServiceStatus;
import com.netcracker.students.o3.model.templates.Template;
import com.netcracker.students.o3.model.users.Customer;
import com.netcracker.students.o3.model.users.Employee;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

/**
 * class contains business logic of program
 */
public interface Controller
{
    /**
     * disconnect service with serviceId
     * @param serviceId - id of service to be disconnected
     */
    void disconnectService(BigInteger serviceId);

    void resumeService(BigInteger serviceId);

    void suspendService(BigInteger serviceId);



    /**
     * start execution of order
     * @return
     */
    void startOrder(BigInteger orderId, BigInteger employeeId);

    /**
     * suspend execution of order
     */
    void suspendOrder(BigInteger orderId);

    /**
     * stop execution of order
     */
    void stopOrder(BigInteger orderId);

    /**
     * complete order
     */
    void completeOrder(BigInteger orderId);

    /**
     * change customer balance with customerId on value
     */
    void changeBalance(BigInteger customerId, BigDecimal value);

    /**
     * delete of entities
     */
    void deleteArea(BigInteger areaId);

    void deleteOrder(BigInteger orderId);

    void deleteService(BigInteger serviceId);

    void deleteTemplate(BigInteger templateId);

    void deleteCustomer(BigInteger customerId);

    void deleteEmployee(BigInteger employeeId);

    /**
     * delete entities with linked entities
     */
    void deepDeleteArea(BigInteger areaId);

    void deepDeleteOrder(BigInteger orderId);

    void deepDeleteService(BigInteger serviceId);

    void deepDeleteTemplate(BigInteger templateId);

    void deepDeleteCustomer(BigInteger customerId);

    void deepDeleteEmployee(BigInteger employeeId);


    /**
     * create entities
     */
    BigInteger createCustomer(String name, String login, String password, BigInteger areaId);

    BigInteger createEmployee(String name, String login, String password);

    BigInteger createOrder(BigInteger templateId, BigInteger serviceId,
            OrderStatus status, OrderAction action);

    BigInteger createTemplate(String name, BigDecimal cost, String description);

    BigInteger createService(BigInteger userId, BigInteger templateId, ServiceStatus status);

    BigInteger createArea(String name, String description);


    /**
     * @return userId by login and password
     */
    BigInteger getUserIdByCredentials(String login, String password) throws IncorrectCredentialsException;

    /**
     * register new Customer
     */
    BigInteger registerCustomer(String login, String password, String Name, BigInteger areaId)
            throws LoginOccupiedException;

    /**
     * register new Employee
     */
    BigInteger registerEmployee(String login, String password, String Name) throws LoginOccupiedException;

    /**
     * check equals password and user password
     */
    boolean checkPassword(BigInteger id, String password);

    /**
     * check existing of login
     */
    boolean isLoginExists(String login);

    /**
     * @return suspended services of customer
     */
    List<Service> getSuspendedServices(BigInteger customerId);

    /**
     * @return entering services of customer
     */
    List<Service> getEnteringServices(BigInteger customerId);

    /**
     * @return active services of customer
     */
    List<Service> getActiveServices(BigInteger customerId);

    /**
     * @return entering and active services of customer
     */
    List<Service> getEnteringAndActiveServices(BigInteger customerId);

    /**
     * @return templates which available to area
     */
    List<Template> getTemplatesByAreaId(BigInteger areaId);

    /**
     * @return templates
     */
    List<Template> getAllTemplates();

    /**
     * @return customer orders
     */
    List<Order> getOrdersByCustomerId(BigInteger customerId);

    /**
     * @return employee orders
     */
    List<Order> getOrdersByEmployeeId(BigInteger employeeId);

    /**
     * @return customer area id
     */
    BigInteger getCustomerAreaId(BigInteger customerId);

    /**
     * check user on customer
     */
    boolean isCustomer(BigInteger userId);

    /**
     * check user on employee
     */
    boolean isEmployee(BigInteger userId);


    /**
     * get list of entities
     */
    List<Area> getAreas();

    List<Template> getTemplates();

    List<Service> getServices();

    List<Customer> getCustomers();

    List<Order> getOrders();

    List<Employee> getEmployers();

    /**
     * get entity by id
     */
    Customer getCustomer(BigInteger userId);

    Employee getEmployee(BigInteger userId);

    Area getArea(BigInteger areaId);

    Template getTemplate(BigInteger templateId);

    Service getService(BigInteger serviceId);

    Order getOrder(BigInteger orderId);

    /**
     * put money on balance
     */
    void putOnBalance(BigInteger customerId, BigDecimal money);

    /**
     * @return services available on area id
     */
    List<Service> getServicesByAreaId(BigInteger areaId);

    /**
     * check user by login
     */
    boolean isCustomerLogin(String login);

    boolean isEmployeeLogin(String login);

    /**
     * @return customer balance
     */
    BigDecimal getBalance(BigInteger customerId);

    /**
     * @return customer name
     */
    String getCustomerFio(BigInteger customerId);

    /**
     * @return area name
     */
    String getAreaName(BigInteger customerId);

    /**
     * @return customer available templates
     */
    List<Template> getCustomerAvailableTemplates(BigInteger customerId);

    /**
     * set customer credentials
     */
    void setCustomerName(BigInteger customerId, String name) throws WrongInputException;

    void setUserLogin(BigInteger userId, String login) throws LoginOccupiedException, WrongInputException;

    void setUserPassword(BigInteger userId, String password) throws WrongInputException;

    void setCustomerArea(BigInteger customerId, BigInteger areaId) throws UnpossibleChangeAreaException;


    /**
     * suspend or resume service
     */
    void suspendOrResumeService(BigInteger customerId, BigInteger serviceId);


    /**
     * connect service
     */
    void connectService(BigInteger customerId, BigInteger serviceId);

    /**
     * @return list of customers service
     */
    List<Service> getCustomerServices(BigInteger customerId);

    /**
     * @return areas available to customer
     */
    List<Area> getAvailableAreas(BigInteger customerId);

    void resumeOrder(BigInteger orderId);
}
