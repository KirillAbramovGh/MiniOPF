package com.netcracker.students.o3.model.model;

import com.netcracker.students.o3.model.area.Area;
import com.netcracker.students.o3.model.area.AreaImpl;
import com.netcracker.students.o3.model.dao.AbstractDao;
import com.netcracker.students.o3.model.dao.AreaDao;
import com.netcracker.students.o3.model.dao.CustomerDao;
import com.netcracker.students.o3.model.dao.EmployeeDao;
import com.netcracker.students.o3.model.dao.LastIdDao;
import com.netcracker.students.o3.model.dao.OrderDao;
import com.netcracker.students.o3.model.dao.ServiceDao;
import com.netcracker.students.o3.model.dao.TemplateDao;
import com.netcracker.students.o3.model.model.Model;
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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModelBD implements Model
{
    private AbstractDao<Order> orderDao;
    private AbstractDao<Template> templateDao;
    private AbstractDao<Service> serviceDao;
    private AbstractDao<Customer> customerDao;
    private AbstractDao<Employee> employeeDao;
    private AbstractDao<Area> areaDao;
    private LastIdDao lastIdDao;

    private static ModelBD instance;

    private ModelBD(){
        orderDao = new OrderDao();
        templateDao = new TemplateDao();
        serviceDao = new ServiceDao();
        customerDao = new CustomerDao();
        employeeDao = new EmployeeDao();
        areaDao = new AreaDao();
        lastIdDao = new LastIdDao();
    }

    @Override
    public void setOrders(final Map<BigInteger, Order> orders)
    {
        for(Order order : orders.values()){
            try
            {
                orderDao.create(order);
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void setTemplates(final Map<BigInteger, Template> templates)
    {
        for(Template template : templates.values()){
            try
            {
                templateDao.create(template);
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void setServices(final Map<BigInteger, Service> services)
    {
        for(Service service : services.values()){
            try
            {
                serviceDao.create(service);
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void setCustomers(final Map<BigInteger, Customer> customers)
    {
        for(Customer customer : customers.values()){
            try
            {
                customerDao.create(customer);
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void setEmployees(final Map<BigInteger, Employee> employees)
    {
        for(Employee employee : employees.values()){
            try
            {
                employeeDao.create(employee);
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void setAreas(final Map<BigInteger, Area> areas)
    {
        for(Area area : areas.values()){
            try
            {
                areaDao.create(area);
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
    }

    @Override
    public BigInteger getLastId()
    {
        try
        {
            return lastIdDao.getLastId();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void setLastId(final BigInteger lastId)
    {
        try
        {
            lastIdDao.setLastId(lastId);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

    }

    @Override
    public BigInteger getNextId()
    {
        try
        {
            BigInteger lastId = lastIdDao.getLastId();
            BigInteger newValue = lastId.add(BigInteger.ONE);
            lastIdDao.setLastId(newValue);
            return lastIdDao.getLastId();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Customer createCustomer(final String name, final String login, final String password,
            final BigInteger areaId)
    {
        Customer newCustomer = new CustomerImpl(getNextId(), name, login, password, areaId);
        addCustomer(newCustomer);

        return newCustomer;
    }

    @Override
    public Employee createEmployee(final String name, final String login, final String password)

    {
        Employee newEmployee = new EmployerImpl(getNextId(), name, login, password);
        addEmployee(newEmployee);

        return newEmployee;
    }

    @Override
    public Order createOrder(final BigInteger templateId, final BigInteger serviceId, final OrderStatus status,
            final OrderAction action)
    {
        Order newOrder = new OrderImpl(getNextId(), templateId, serviceId, status, action);
        newOrder.setCreationDate(new Date());
        addOrder(newOrder);

        return newOrder;
    }

    @Override
    public Template createTemplate(final String name, final BigDecimal cost, final String description)

    {
        Template newTemplate = new TemplateImpl(getNextId(), name, cost, description);
        addTemplate(newTemplate);

        return newTemplate;
    }

    @Override
    public Service createService(final BigInteger userId, final BigInteger templateId, final ServiceStatus status)

    {
        Service newService = new ServiceImpl(getNextId(), userId, templateId, status);
        addService(newService);

        return newService;
    }

    @Override
    public Area createArea(final String name, final String description)

    {
        Area newArea = new AreaImpl(getNextId(), name, description);
        addArea(newArea);

        return newArea;
    }

    @Override
    public Map<BigInteger, Order> getOrders()
    {
        Map<BigInteger,Order> orderMap = new HashMap<>();
        try
        {
            for(Order order : orderDao.getAll()){
                if(order!=null && order.getId()!=null)
                {
                    orderMap.put(order.getId(), order);
                }
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return orderMap;
    }

    @Override
    public Map<BigInteger, Template> getTemplates()
    {
        Map<BigInteger,Template> templateMap = new HashMap<>();
        try
        {
            for(Template template : templateDao.getAll()){
                if(template!=null && template.getName()!=null)
                {
                    templateMap.put(template.getId(), template);
                }
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return templateMap;
    }

    @Override
    public Map<BigInteger, Service> getServices()
    {
        Map<BigInteger,Service> serviceMap = new HashMap<>();
        try
        {
            for(Service service : serviceDao.getAll()){
                if(service!=null && service.getStatus()!=null)
                {
                    serviceMap.put(service.getId(), service);
                }
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return serviceMap;
    }

    @Override
    public Map<BigInteger, Customer> getCustomers()
    {
        Map<BigInteger,Customer> serviceMap = new HashMap<>();
        try
        {
            for(Customer customer : customerDao.getAll()){
                if(customer!=null && customer.getName()!=null)
                {
                    serviceMap.put(customer.getId(), customer);
                }
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return serviceMap;
    }

    @Override
    public Map<BigInteger, Employee> getEmployees()
    {
        Map<BigInteger,Employee> employeeMap = new HashMap<>();
        try
        {
            for(Employee employee : employeeDao.getAll()){
                if(employee!=null && employee.getName()!=null)
                {
                    employeeMap.put(employee.getId(), employee);
                }
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return employeeMap;
    }

    @Override
    public Map<BigInteger, Area> getAreas()
    {
        Map<BigInteger,Area> areaMap = new HashMap<>();
        try
        {
            for(Area area : areaDao.getAll()){
                if(area!=null && area.getName()!=null)
                {
                    areaMap.put(area.getId(), area);
                }
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return areaMap;
    }

    @Override
    public Order getOrderById(final BigInteger id)
    {
        try
        {
            Order order = orderDao.getEntityById(id);
            if(order!=null && order.getServiceId() == null){
                order = null;
            }
            return order;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Template getTemplateById(final BigInteger id)
    {
        try
        {
            Template template = templateDao.getEntityById(id);
            if(template!=null && template.getName()==null){
                template = null;
            }
            return template;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Service getServiceById(final BigInteger id)
    {
        try
        {
            Service service = serviceDao.getEntityById(id);
            if(service!=null && service.getStatus()==null){
                service = null;
            }
            return service;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Customer getCustomerById(final BigInteger id)
    {
        try
        {
            Customer customer = customerDao.getEntityById(id);
            if(customer!=null && customer.getName()==null){
                customer = null;
            }
            return customer;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Employee getEmployeeById(final BigInteger id)
    {
        try
        {
            Employee employee = employeeDao.getEntityById(id);
            if(employee!=null && employee.getName()==null){
                employee = null;
            }

            return employee;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Area getAreaById(final BigInteger id)
    {
        try
        {
            Area area = areaDao.getEntityById(id);
            if(area!=null && area.getName()==null){
                area = null;
            }
            return area;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void addOrder(final Order order)
    {
        try
        {
            orderDao.create(order);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void addService(final Service service)
    {
        try
        {
            serviceDao.create(service);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void addTemplate(final Template template)
    {
        try
        {
            templateDao.create(template);
        }

        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void addCustomer(final Customer customer)
    {
        try
        {
            customerDao.create(customer);
        }

        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void addEmployee(final Employee employee)
    {
        try
        {
            employeeDao.create(employee);
        }

        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void addArea(final Area area)
    {
        try
        {
            areaDao.create(area);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteOrderById(final BigInteger id)
    {
        try
        {
            orderDao.delete(id);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteTemplateById(final BigInteger id)
    {
        try
        {
            templateDao.delete(id);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteServiceById(final BigInteger id)
    {
        try
        {
            serviceDao.delete(id);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCustomerById(final BigInteger id)
    {
        try
        {
            customerDao.delete(id);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteEmployeeById(final BigInteger id)
    {
        try
        {
            employeeDao.delete(id);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAreaById(final BigInteger id)
    {
        try
        {
            areaDao.delete(id);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void setOrder(final Order order)
    {
        try
        {
            orderDao.update(order);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

    }

    @Override
    public void setTemplate(final Template template)
    {
        try
        {
            templateDao.update(template);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

    }

    @Override
    public void setService(final Service service)
    {
        try
        {
            serviceDao.update(service);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

    }

    @Override
    public void setCustomer(final Customer customer)
    {
        try
        {
            customerDao.update(customer);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

    }

    @Override
    public void setEmployee(final Employee employee)
    {
        try
        {
            employeeDao.update(employee);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

    }

    @Override
    public void setArea(final Area area)
    {
        try
        {
            areaDao.update(area);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

    }

    public Area getAreaByName(final String name){
        try
        {
            return ((AreaDao)areaDao).getAreaByName(name);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return null;
    }

    public Customer getCustomerByLogin(final String login){
        try
        {
            return ((CustomerDao)customerDao).getCustomerByLogin(login);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public Employee getEmployeeByLogin(final String login){
        try
        {
            return ((EmployeeDao)employeeDao).getEmployeeByLogin(login);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public List<Order> getOrdersByTemplateId(final BigInteger templateId){
        try
        {
            return ((OrderDao)orderDao).getOrdersByTemplateId(templateId);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public List<Order> getOrdersByServiceId(final BigInteger serviceId){
        try
        {
            return ((OrderDao)orderDao).getOrdersByServiceId(serviceId);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public List<Order> getOrdersByEmployeeId(final BigInteger employeeId){
        try
        {
            return ((OrderDao)orderDao).getOrdersByEmployeeId(employeeId);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public List<Order> getOrdersByStatus(final OrderStatus status){
        try
        {
            return ((OrderDao)orderDao).getOrdersByStatus(status);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public List<Order> getOrdersByAction(final OrderAction action){
        try
        {
            return ((OrderDao)orderDao).getOrdersByAction(action);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public List<Service> getServicesByUserId(BigInteger userId){
        try
        {
            return ((ServiceDao)serviceDao).getServicesByUserId(userId);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public List<Service> getServicesByTemplateId(BigInteger templateId){
        try
        {
            return ((ServiceDao)serviceDao).getServicesByTemplateId(templateId);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public List<Service> getServicesByStatus(ServiceStatus status){
        try
        {
            return ((ServiceDao)serviceDao).getServicesByStatus(status);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public List<Service> getServicesByStatusAndCustomerId(BigInteger userId,ServiceStatus status){
        try
        {
            return ((ServiceDao)serviceDao).getServicesByStatusAndCustomerId(userId,status);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public List<Template> getTemplatesByAreaId(BigInteger areaId){
        try
        {
            return ((TemplateDao)templateDao).getTemplatesByAreaId(areaId);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public Template getTemplateByName(String name){
        try
        {
            return ((TemplateDao)templateDao).getTemplateByName(name);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }


    public static ModelBD getInstance(){
        if(instance == null){
            instance = new ModelBD();
        }

        return instance;
    }
}
