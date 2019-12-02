package com.netcracker.students.o3.controller;

import com.netcracker.students.o3.model.Model;
import com.netcracker.students.o3.model.area.Area;
import com.netcracker.students.o3.model.orders.Order;
import com.netcracker.students.o3.model.orders.OrderImpl;
import com.netcracker.students.o3.model.services.Service;
import com.netcracker.students.o3.model.services.ServiceStatus;
import com.netcracker.students.o3.model.templates.Template;
import com.netcracker.students.o3.model.users.Customer;
import com.netcracker.students.o3.model.users.CustomerImpl;
import com.netcracker.students.o3.model.users.Employee;
import com.netcracker.students.o3.model.users.EmployerImpl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;

public class ControllerImpl implements Controller
{
    private final Model model;
    private static Controller instance;

    private ControllerImpl()
    {
        model = Model.getInstance();
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
    public void createCustomer(final Customer customer)
    {

    }

    @Override
    public void createEmployee()
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
    public void createArea(final BigInteger areaId)
    {

    }

    @Override
    public void createOrder(final BigInteger templateId)
    {
        Order order = new OrderImpl(model.getNextId(), templateId);
        model.addOrder(order);
    }

    @Override
    public void createService(final BigInteger serviceId)
    {

    }

    @Override
    public void createTemplate(final BigInteger templateId)
    {

    }

    @Override
    public void createCustomer(final BigInteger customerId)
    {

    }

    @Override
    public void createEmployee(final BigInteger employeeId)
    {

    }

    @Override
    public BigInteger login(final String login, final String password)
    {
        for (Customer customer : model.getCustomers().values())
        {
            if (customer.getLogin().equals(login) && customer.getPassword().equals(password))
            {
                return customer.getId();
            }
        }
        for (Employee employee : model.getEmployers().values())
        {
            if (employee.getLogin().equals(login) && employee.getPassword().equals(password))
            {
                return employee.getId();
            }
        }
        return null;
    }


    @Override
    public BigInteger registerCustomer(final String login, final String password, final String name, final BigInteger areaId)
    {
        for (Customer v : model.getCustomers().values())
        {
            if (v.getLogin().equals(login))
            {

            }
        }
        for (Employee v : model.getEmployers().values())
        {
            if (v.getLogin().equals(login))
            {

            }
        }
        Customer customer = new CustomerImpl(model.getNextId(), name, login, password);
        customer.setAreaId(areaId);
        model.addCustomer(customer);
        return customer.getId();
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
    public boolean checkLogin(final String login)
    {
        for (Customer customer : model.getCustomers().values())
        {
            if (customer.getLogin().equals(login))
            {
                return false;
            }
        }
        return true;
    }

    @Override
    public ArrayList<Service> getSuspendedServices(final BigInteger id)
    {
        ArrayList<Service> services = new ArrayList<>();
        for (BigInteger v : model.getCustomerById(id).getConnectedServicesIds())
        {
            if (model.getServiceById(v).getStatus() == ServiceStatus.Suspended)
            {
                services.add(model.getServiceById(v));
            }
        }
        return services;
    }

    @Override
    public ArrayList<Service> getEnteringServices(final BigInteger id)
    {
        ArrayList<Service> services = new ArrayList<>();
        for (BigInteger v : model.getCustomerById(id).getConnectedServicesIds())
        {
            if (model.getServiceById(v).getStatus() == ServiceStatus.Entering)
            {
                services.add(model.getServiceById(v));
            }
        }
        return services;
    }

    @Override
    public String getCustomerString(final BigInteger id)
    {
        return null;
    }

    @Override
    public ArrayList<Service> getActiveServices(final BigInteger id)
    {
        ArrayList<Service> services = new ArrayList<>();
        for (BigInteger serviceId : model.getCustomerById(id).getConnectedServicesIds())
        {
            services.add(model.getServiceById(serviceId));
        }

        return services;
    }

    @Override
    public ArrayList<Template> getTemplatesByAreaId(final BigInteger areaId)
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
    public ArrayList<Template> getAllTemplates()
    {
        ArrayList<Template> templates = new ArrayList<>();
        for (Template template : model.getTemplates().values())
        {
            templates.add(template);
        }

        return templates;
    }

    @Override
    public ArrayList<Order> getOrdersByCustomerId(final BigInteger customerId)
    {
        return null;
    }

    @Override
    public ArrayList<Order> getOrdersByEmployeeId(final BigInteger employeeId)
    {
        return null;
    }

    @Override
    public BigInteger getCustomerAreaId(final BigInteger customerId)
    {
        return model.getCustomerById(customerId).getAreaId();
    }

    @Override
    public boolean isCustomer(final BigInteger id)
    {
        return model.getCustomerById(id) != null;
    }

    @Override
    public boolean isEmployee(final BigInteger id)
    {
        return model.getEmployeeById(id) != null;
    }

    @Override
    public Customer getCustomer(final BigInteger id)
    {
        return model.getCustomerById(id);
    }

    @Override
    public Employee getEmployee(final BigInteger id)
    {
        return model.getEmployeeById(id);
    }

    @Override
    public ArrayList<Area> getAreas()
    {
        ArrayList<Area> areas = new ArrayList<>();
        for (Area area : model.getAreas().values())
        {
            areas.add(area);
        }
        return areas;
    }

    @Override
    public ArrayList<Template> getTemplates()
    {
        return null;
    }

    @Override
    public ArrayList<Service> getServices()
    {
        return null;
    }

    @Override
    public ArrayList<Customer> getCustomers()
    {
        return null;
    }

    @Override
    public ArrayList<Order> getOrders()
    {
        return null;
    }

    @Override
    public ArrayList<Employee> getEmployes()
    {
        return null;
    }

    @Override
    public Area getArea(final BigInteger id)
    {
        return null;
    }

    @Override
    public Template getTemplate(final BigInteger id)
    {
        return null;
    }

    @Override
    public Service getService(final BigInteger id)
    {
        return null;
    }

    @Override
    public Order getOrder(final BigInteger id)
    {
        return null;
    }

    @Override
    public void putOnBalance(final BigInteger customerId, final BigDecimal money)
    {
        Customer customer = model.getCustomerById(customerId);
        BigDecimal currentMoney = customer.getMoneyBalance();
        customer.setMoneyBalance(currentMoney.add(money));
    }

    public static Controller getInstance()
    {
        if (instance == null)
        {
            instance = new ControllerImpl();
        }

        return instance;
    }
}
