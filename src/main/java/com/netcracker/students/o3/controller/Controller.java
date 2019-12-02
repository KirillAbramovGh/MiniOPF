package com.netcracker.students.o3.controller;

import com.netcracker.students.o3.model.area.Area;
import com.netcracker.students.o3.model.orders.Order;
import com.netcracker.students.o3.model.services.Service;
import com.netcracker.students.o3.model.templates.Template;
import com.netcracker.students.o3.model.users.Customer;
import com.netcracker.students.o3.model.users.Employee;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public interface Controller
{
    void startOrder(BigInteger orderId, BigInteger employeeId);

    void suspendOrder(BigInteger orderId);

    //трется employeeId и ордер возвращается в Entering
    void stopOrder(BigInteger orderId);

    void completeOrder(BigInteger orderId);

    //Value положительный - прибавить n рублей на счет пользователя. Отрицательный - списать средства
    void changeBalance(BigInteger customerId, BigDecimal value);

    void createCustomer(Customer customer);

    void createEmployee(/*set of parameters*/);

    //удаляет только непривязанные к другим объектам сущности
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
    void createArea(BigInteger areaId);

    void createOrder(BigInteger orderId);

    void createService(BigInteger serviceId);

    void createTemplate(BigInteger templateId);

    void createCustomer(BigInteger customerId);

    void createEmployee(BigInteger employeeId);

    BigInteger login(String login, String password);

    BigInteger register(String login, String password, String Name, BigInteger areaId);

    boolean checkPassword(BigInteger id, String password);

    boolean checkLogin(String login);

    ArrayList<Service> getSuspendedServices(BigInteger id);

    ArrayList<Service> getEnteringServices(BigInteger id);

    String getCustomerString(BigInteger id);

    ArrayList<Service> getActiveServices(BigInteger id);

    ArrayList<Template> getTemplatesByAreaId(BigInteger areaId);

    ArrayList<Template> getAllTemplates();

    ArrayList<Order> getOrdersByCustomerId(BigInteger customerId);

    ArrayList<Order> getOrdersByEmployeeId(BigInteger employeeId);

    BigInteger getCustomerAreaId(BigInteger customerId);

    boolean isCustomer(BigInteger id);

    boolean isEmployee(BigInteger id);

    Customer getCustomer(BigInteger id);

    Employee getEmployee(BigInteger id);

    List<Area> getAreas();

    List<Template> getTemplates();

    List<Service> getServices();

    List<Customer> getCustomers();

    List<Order> getOrders();

    List<Employee> getEmployes();


    Area getArea(BigInteger id);

    Template getTemplate(BigInteger id);

    Service getService(BigInteger id);

    Order getOrder(BigInteger id);

    void putOnBalance(BigInteger customerId, BigDecimal money);

    // В контроллере должны лежать функции и кастомера, и работника

    //дублирование методов модели с проверкой delete() и set() методов на дурака
}
