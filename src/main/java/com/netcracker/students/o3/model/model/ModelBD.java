package com.netcracker.students.o3.model.model;

import com.netcracker.students.o3.model.area.Area;
import com.netcracker.students.o3.model.model.Model;
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
import java.util.Map;

public class ModelBD implements Model
{
    @Override
    public void setOrders(final Map<BigInteger, Order> orders)
    {

    }

    @Override
    public void setTemplates(final Map<BigInteger, Template> templates)
    {

    }

    @Override
    public void setServices(final Map<BigInteger, Service> services)
    {

    }

    @Override
    public void setCustomers(final Map<BigInteger, Customer> customers)
    {

    }

    @Override
    public void setEmployers(final Map<BigInteger, Employee> employers)
    {

    }

    @Override
    public void setAreas(final Map<BigInteger, Area> areas)
    {

    }

    @Override
    public BigInteger getLastId()
    {
        return null;
    }

    @Override
    public void setLastId(final BigInteger lastId)
    {

    }

    @Override
    public BigInteger getNextId()
    {
        return null;
    }

    @Override
    public BigInteger createCustomer(final String name, final String login, final String password,
            final BigInteger areaId)
    {
        return null;
    }

    @Override
    public BigInteger createEmployee(final String name, final String login, final String password)
    {
        return null;
    }

    @Override
    public BigInteger createOrder(final BigInteger templateId, final BigInteger serviceId, final OrderStatus status,
            final OrderAction action)
    {
        return null;
    }

    @Override
    public BigInteger createTemplate(final String name, final BigDecimal cost, final String description)
    {
        return null;
    }

    @Override
    public BigInteger createService(final BigInteger userId, final BigInteger templateId, final ServiceStatus status)
    {
        return null;
    }

    @Override
    public BigInteger createArea(final String name, final String description)
    {
        return null;
    }

    @Override
    public Map<BigInteger, Order> getOrders()
    {
        return null;
    }

    @Override
    public Map<BigInteger, Template> getTemplates()
    {
        return null;
    }

    @Override
    public Map<BigInteger, Service> getServices()
    {
        return null;
    }

    @Override
    public Map<BigInteger, Customer> getCustomers()
    {
        return null;
    }

    @Override
    public Map<BigInteger, Employee> getEmployees()
    {
        return null;
    }

    @Override
    public Map<BigInteger, Area> getAreas()
    {
        return null;
    }

    @Override
    public Order getOrderById(final BigInteger id)
    {
        return null;
    }

    @Override
    public Template getTemplateById(final BigInteger id)
    {
        return null;
    }

    @Override
    public Service getServiceById(final BigInteger id)
    {
        return null;
    }

    @Override
    public Customer getCustomerById(final BigInteger id)
    {
        return null;
    }

    @Override
    public Employee getEmployeeById(final BigInteger id)
    {
        return null;
    }

    @Override
    public Area getAreaById(final BigInteger id)
    {
        return null;
    }

    @Override
    public void addOrder(final Order order)
    {

    }

    @Override
    public void addService(final Service service)
    {

    }

    @Override
    public void addTemplate(final Template template)
    {

    }

    @Override
    public void addCustomer(final Customer customer)
    {

    }

    @Override
    public void addEmployee(final Employee employee)
    {

    }

    @Override
    public void addArea(final Area area)
    {

    }

    @Override
    public void deleteOrderById(final BigInteger id)
    {

    }

    @Override
    public void deleteTemplateById(final BigInteger id)
    {

    }

    @Override
    public void deleteServiceById(final BigInteger id)
    {

    }

    @Override
    public void deleteCustomerById(final BigInteger id)
    {

    }

    @Override
    public void deleteEmployeeById(final BigInteger id)
    {

    }

    @Override
    public void deleteAreaById(final BigInteger id)
    {

    }

    @Override
    public void setOrder(final Order order)
    {

    }

    @Override
    public void setTemplate(final Template template)
    {

    }

    @Override
    public void setService(final Service service)
    {

    }

    @Override
    public void setCustomer(final Customer customer)
    {

    }

    @Override
    public void setEmployee(final Employee employee)
    {

    }

    @Override
    public void setArea(final Area area)
    {

    }
}
