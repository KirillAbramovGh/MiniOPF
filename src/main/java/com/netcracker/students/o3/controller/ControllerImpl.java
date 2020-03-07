package com.netcracker.students.o3.controller;

import com.netcracker.students.o3.Exceptions.IncorrectCredentialsException;
import com.netcracker.students.o3.Exceptions.LoginOccupiedException;
import com.netcracker.students.o3.Exceptions.WrongInputException;
import com.netcracker.students.o3.model.area.Area;
import com.netcracker.students.o3.model.model.Model;
import com.netcracker.students.o3.model.model.ModelBD;
import com.netcracker.students.o3.model.orders.Order;
import com.netcracker.students.o3.model.orders.OrderAction;
import com.netcracker.students.o3.model.orders.OrderStatus;
import com.netcracker.students.o3.model.services.Service;
import com.netcracker.students.o3.model.services.ServiceStatus;
import com.netcracker.students.o3.model.templates.Template;
import com.netcracker.students.o3.model.users.Customer;
import com.netcracker.students.o3.model.users.Employee;
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
        model = ModelBD.getInstance();
    }


    @Override
    public void startOrder(final BigInteger orderId, final BigInteger employeeId)
    {
        Order order = getOrder(orderId);
        order.setEmployeeId(employeeId);
        order.setStatus(OrderStatus.Processing);
        model.setOrder(order);
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
        Order order = getOrder(orderId);
        Service service = getService(order.getServiceId());
        completeOrder(order,service);
    }

    @Override
    public void completeOrder(final BigInteger orderId,final Service service)
    {
        Order order = getOrder(orderId);
       completeOrder(order,service);
    }

    @Override
    public void completeOrder(final Order order,final Service service)
    {
        switch (order.getAction())
        {
            case New:
            case Resume:
                service.setStatus(ServiceStatus.Active);
                break;
            case Suspend:
                service.setStatus(ServiceStatus.Suspended);
                break;
            case Disconnect:
                service.setStatus(ServiceStatus.Disconnected);
                break;
        }
        order.setStatus(OrderStatus.Completed);
        model.setOrder(order);
        model.setService(service);
    }


    @Override
    public void deleteArea(final BigInteger areaId)
    {
        model.deleteAreaById(areaId);
    }

    @Override
    public void deleteOrder(final BigInteger orderId)
    {
        model.deleteOrderById(orderId);
    }

    @Override
    public void deleteService(final BigInteger serviceId)
    {
        model.deleteServiceById(serviceId);
    }

    @Override
    public void deleteTemplate(final BigInteger templateId)
    {
        model.deleteTemplateById(templateId);
    }

    @Override
    public void deleteCustomer(final BigInteger customerId)
    {
        model.deleteCustomerById(customerId);
    }

    @Override
    public void deleteEmployee(final BigInteger employeeId)
    {
        model.deleteEmployeeById(employeeId);
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
    public Customer createCustomer(final String name, final String login, final String password,
            final BigInteger areaId)
    {
        return model.createCustomer(name, login, password, areaId);
    }

    @Override
    public Employee createEmployee(final String name, final String login, final String password)

    {
        return model.createEmployee(name, login, password);
    }

    @Override
    public Order createOrder(final BigInteger templateId, final BigInteger serviceId,
            final OrderStatus status, final OrderAction action)

    {
        return model.createOrder(templateId, serviceId, status, action);
    }

    @Override
    public Template createTemplate(final String name, final BigDecimal cost, final String description)

    {
        return model.createTemplate(name, cost, description);
    }

    @Override
    public Service createService(final BigInteger userId, final BigInteger templateId, final ServiceStatus status)

    {
        return model.createService(userId, templateId, status);
    }

    @Override
    public Area createArea(final String name, final String description)

    {
        return model.createArea(name, description);
    }

    @Override
    public BigInteger getUserIdByCredentials(final String login, final String password)
            throws IncorrectCredentialsException
    {
        Customer customer = model.getCustomerByLogin(login);
        if(customer != null && customer.getPassword().equals(password)){
            return customer.getId();
        }
        Employee employee = model.getEmployeeByLogin(login);
        if(employee != null && employee.getPassword().equals(password)){
            return employee.getId();
        }

        throw new IncorrectCredentialsException("Неправильный логин или пароль!");
    }

    public List<Employee> getEmployees()
    {
        return new ArrayList<>(model.getEmployees().values());
    }

    @Override
    public void setCustomer(final Customer customer)
    {
        model.setCustomer(customer);
    }

    @Override
    public void setOrder(final Order order)
    {
        model.setOrder(order);
    }

    @Override
    public void setTemplate(final Template template)
    {
        model.setTemplate(template);
    }

    @Override
    public void setService(final Service service)
    {
        model.setService(service);
    }

    @Override
    public void setArea(final Area area)
    {
        model.setArea(area);
    }


    @Override
    public Customer registerCustomer(final String login, final String password, final String name,
            final BigInteger areaId)
            throws LoginOccupiedException
    {
        if (!isLoginExists(login))
        {
            return createCustomer(name, login, password, areaId);
        }
        else
        {
            throw new LoginOccupiedException("Login occupied");
        }
    }

    @Override
    public Employee registerEmployee(final String login, final String password, final String Name)
            throws LoginOccupiedException
    {
        if (!isLoginExists(login))
        {
            return createEmployee(Name, login, password);
        }

        throw new LoginOccupiedException("Login occupied");
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
        return model.getServicesByStatusAndCustomerId(customerId,ServiceStatus.Suspended);
    }

    @Override
    public List<Service> getEnteringServices(final BigInteger customerId)
    {
        return model.getServicesByStatusAndCustomerId(customerId,ServiceStatus.Entering);
    }


    @Override
    public List<Service> getActiveServices(final BigInteger customerId)
    {
        return model.getServicesByStatusAndCustomerId(customerId,ServiceStatus.Active);
    }

    @Override
    public List<Service> getEnteringAndActiveServices(final BigInteger customerId)
    {
        List<Service> services = new ArrayList<>(getEnteringServices(customerId));
        services.addAll(getActiveServices(customerId));
        return services;
    }


    @Override
    public List<Template> getTemplatesByAreaId(final BigInteger areaId)
    {
        return model.getTemplatesByAreaId(areaId);
    }



    @Override
    public List<Order> getOrdersByEmployeeId(final BigInteger employeeId)
    {
        return model.getOrdersByEmployeeId(employeeId);
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
        return new ArrayList<>(model.getAreas().values());
    }

    @Override
    public List<Template> getTemplates()
    {
        return new ArrayList<>(model.getTemplates().values());
    }

    @Override
    public List<Service> getServices()
    {
        return new ArrayList<>(model.getServices().values());
    }

    @Override
    public List<Customer> getCustomers()
    {
        return new ArrayList<>(model.getCustomers().values());
    }

    @Override
    public List<Order> getOrders()
    {
        return new ArrayList<>(model.getOrders().values());
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

        model.setCustomer(customer);
    }



    @Override
    public boolean isCustomerLogin(final String login)
    {
        return model.getCustomerByLogin(login)!=null;
    }

    @Override
    public boolean isEmployeeLogin(final String login)
    {
        return model.getEmployeeByLogin(login)!=null;
    }

    public static Controller getInstance()
    {
        if (instance == null)
        {
            instance = new ControllerImpl();
        }

        return instance;
    }

    public BigDecimal getBalance(BigInteger customerId)
    {
        return getCustomer(customerId).getMoneyBalance();
    }

    public String getCustomerFio(BigInteger customerId)
    {
        return getCustomer(customerId).getName();
    }

    public String getAreaName(BigInteger customerId)
    {
        return getArea(getCustomer(customerId).getAreaId()).getName();
    }

    public List<Template> getCustomerAvailableTemplates(BigInteger customerId)
    {
        return getTemplatesByAreaId(getCustomerAreaId(customerId));
    }

    public void setCustomerName(BigInteger customerId, String name) throws WrongInputException
    {
        if (!name.isEmpty())
        {
            Customer customer =getCustomer(customerId);
            customer.setName(name);
            model.setCustomer(customer);
        }
        else
        {
            throw new WrongInputException("Имя не может быть пустым!");
        }
    }

    public void setUserLogin(BigInteger userId, String login) throws LoginOccupiedException, WrongInputException
    {
        if (!login.isEmpty())
        {
            if (!isLoginExists(login))
            {
                User user = getUser(userId);
                user.setLogin(login);
                if (isCustomer(userId))
                {
                    model.setCustomer((Customer) user);
                }
                else if (isEmployee(userId))
                {
                    model.setEmployee((Employee)user);
                }
            }
            else
            {
                if (!getUser(userId).getLogin().equals(login))
                {
                    throw new LoginOccupiedException("Логин занят");
                }
            }
        }
        else
        {
            throw new WrongInputException("Логин не может быть пустым");
        }
    }

    private User getUser(BigInteger userId)
    {
        User user = getCustomer(userId);

        if (user == null)
        {
            user = getEmployee(userId);
        }

        return user;
    }

    public void setUserPassword(BigInteger userId, String password) throws WrongInputException
    {
        if (!password.isEmpty())
        {
            User user = getUser(userId);
            user.setPassword(password);
            if (isCustomer(userId))
            {
                model.setCustomer((Customer) user);
            }
            else if (isEmployee(userId))
            {
                model.setEmployee((Employee) user);
            }
        }
        else
        {
            throw new WrongInputException("Пароль не может быть пустым");
        }
    }

    public void setCustomerArea(BigInteger customerId, BigInteger areaId)
    {
        disconnectImpossibleServices(customerId, areaId);
        Customer customer = getCustomer(customerId);
        customer.setAreaId(areaId);
        model.setCustomer(customer);
    }

    @Override
    public void setEmployee(final Employee employee)
    {
        model.setEmployee(employee);
    }

    private void disconnectImpossibleServices(BigInteger customerId, BigInteger areaId)
    {
        Template template;
        for (Service service : getCustomerServices(customerId))
        {
            template = getTemplate(service.getTemplateId());
            if (!template.getPossibleAreasId().contains(areaId))
            {
                disconnectService(service.getId());
            }
        }
    }




    @Override
    public void suspendService(final BigInteger serviceId)
    {
        Service service = getService(serviceId);
        Order order = createOrder(service.getTemplateId(), serviceId, OrderStatus.Entering, OrderAction.Suspend);
        completeOrder(order,service);
    }

    @Override
    public void resumeService(final BigInteger serviceId)
    {
        Service service = getService(serviceId);
        Order order = createOrder(service.getTemplateId(), serviceId, OrderStatus.Entering, OrderAction.Resume);
        completeOrder(order,service);
    }

    @Override
    public void connectService(final BigInteger customerId, final BigInteger templateId)
    {
        Customer customer = getCustomer(customerId);
        for(Service service : getEnteringActiveSuspendedService(customerId)){
            if(service.getTemplateId().equals(templateId)){
                return;
            }
        }
        Service service = createService(customerId, templateId, ServiceStatus.Entering);
        Order order = createOrder(templateId, service.getId(), OrderStatus.Entering, OrderAction.New);
        customer.addConnectedServiceId(service.getId());
        model.setCustomer(customer);

        completeOrder(order,service);
    }

    @Override
    public void disconnectService(final BigInteger serviceId)
    {
        Service service = getService(serviceId);
        Order order =
                createOrder(service.getTemplateId(), serviceId, OrderStatus.Entering, OrderAction.Disconnect);
        completeOrder(order,service);
    }


    @Override
    public List<Service> getCustomerServices(final BigInteger customerId)
    {
        return model.getServicesByUserId(customerId);
    }

    @Override
    public List<Area> getAvailableAreas(final BigInteger customerId)
    {
        List<Service> services = getEnteringAndActiveServices(customerId);
        List<Area> availableAreas = new ArrayList<>();

        if (services.size() > 0)
        {
            availableAreas.add(getArea(getCustomerAreaId(customerId)));
            for (Service service : services)
            {
                Template template = getTemplate(service.getTemplateId());
                for (BigInteger areaId : template.getPossibleAreasId())
                {
                    if (!availableAreas.contains(getArea(areaId)))
                    {
                        availableAreas.add(getArea(areaId));
                    }
                }
            }
        }
        else
        {
            availableAreas.addAll(getAreas());
        }

        return availableAreas;
    }

    @Override
    public void resumeOrder(BigInteger orderId)
    {
        Order order = getOrder(orderId);
        order.setStatus(OrderStatus.Processing);
        model.setOrder(order);
    }

    @Override
    public List<Service> getEnteringActiveSuspendedService(BigInteger customerId)
    {
        List<Service> services = getEnteringAndActiveServices(customerId);
        services.addAll(getSuspendedServices(customerId));
        return services;
    }

    @Override
    public String getServiceName(final BigInteger serviceId)
    {
        return getTemplate(getService(serviceId).getTemplateId()).getName();
    }

    @Override
    public String getServiceDescription(final BigInteger serviceId)
    {
        return getTemplate(getService(serviceId).getTemplateId()).getDescription();

    }


}
