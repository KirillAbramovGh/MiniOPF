package com.netcracker.students.o3.model;

import com.netcracker.students.o3.model.area.AreaImpl;
import com.netcracker.students.o3.model.orders.OrderImpl;
import com.netcracker.students.o3.model.services.ServiceImpl;
import com.netcracker.students.o3.model.templates.TemplateImpl;
import com.netcracker.students.o3.model.users.CustomerImpl;
import com.netcracker.students.o3.model.users.EmployerImpl;

public class ModelJsonFormat
{
    private CustomerImpl[] customers;
    private EmployerImpl[] employees;
    private TemplateImpl[] templates;
    private ServiceImpl[] services;
    private AreaImpl[] areas;
    private OrderImpl[] orders;

    public ModelJsonFormat()
    {
        customers = new CustomerImpl[0];
        employees = new EmployerImpl[0];
        templates = new TemplateImpl[0];
        services = new ServiceImpl[0];
        areas = new AreaImpl[0];
        orders = new OrderImpl[0];
    }

    public CustomerImpl[] getCustomers()
    {
        return customers;
    }

    public void setCustomers(final CustomerImpl[] customers)
    {
        this.customers = customers;
    }

    public EmployerImpl[] getEmployees()
    {
        return employees;
    }

    public void setEmployees(final EmployerImpl[] employees)
    {
        this.employees = employees;
    }

    public TemplateImpl[] getTemplates()
    {
        return templates;
    }

    public void setTemplates(final TemplateImpl[] templates)
    {
        this.templates = templates;
    }

    public ServiceImpl[] getServices()
    {
        return services;
    }

    public void setServices(final ServiceImpl[] services)
    {
        this.services = services;
    }

    public AreaImpl[] getAreas()
    {
        return areas;
    }

    public void setAreas(final AreaImpl[] areas)
    {
        this.areas = areas;
    }

    public OrderImpl[] getOrders()
    {
        return orders;
    }

    public void setOrders(final OrderImpl[] orders)
    {
        this.orders = orders;
    }
}
