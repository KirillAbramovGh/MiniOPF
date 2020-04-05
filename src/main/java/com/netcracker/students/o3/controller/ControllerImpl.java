package com.netcracker.students.o3.controller;

import com.netcracker.students.o3.Exceptions.IncorrectCredentialsException;
import com.netcracker.students.o3.Exceptions.LoginOccupiedException;
import com.netcracker.students.o3.Exceptions.WrongInputException;
import com.netcracker.students.o3.controller.sorters.ServiceSorter;
import com.netcracker.students.o3.controller.sorters.SortType.ServiceSortType;
import com.netcracker.students.o3.model.area.Area;
import com.netcracker.students.o3.model.model.Model;
import com.netcracker.students.o3.model.model.ModelDb;
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
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class ControllerImpl implements Controller
{
    private final Model model;
    private static ControllerImpl instance;
    private final ServiceSorter serviceSorter;

    private ControllerImpl()
    {
        model = ModelDb.getInstance();
        serviceSorter = ServiceSorter.getInstance();
        takeMoney(new Timer());
    }

    private ControllerImpl(Model model)
    {
        this.model = model;
        serviceSorter = ServiceSorter.getInstance();
       // takeMoney(new Timer());
    }


    @Override
    public void startOrder(final BigInteger orderId, final BigInteger employeeId)
    {
        Order order = getOrder(orderId);
        order.setEmployeeId(employeeId);
        order.setStatus(OrderStatus.Processing);
        Service service = getService(order.getServiceId());
        service.setStatus(ServiceStatus.Provisioning);
        model.setOrder(order);
        model.setService(service);
    }

    @Override
    public void suspendOrder(final BigInteger orderId)
    {
        Order order = getOrder(orderId);
        order.setEmployeeId(BigInteger.ZERO);
        order.setStatus(OrderStatus.Entering);
        Service service = getService(order.getServiceId());
        switch (order.getAction()){
            case Suspend: service.setStatus(ServiceStatus.Active);break;
            case Resume:
            case Disconnect:
                service.setStatus(ServiceStatus.Suspended);break;
            case New: service.setStatus(ServiceStatus.Planned);break;
        }
        model.setOrder(order);
        model.setService(service);
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
        model.deleteArea(areaId);
    }

    @Override
    public void deleteOrder(final BigInteger orderId)
    {
        model.deleteOrder(orderId);
    }

    @Override
    public void deleteService(final BigInteger serviceId)
    {
        model.deleteService(serviceId);
    }

    @Override
    public void deleteTemplate(final BigInteger templateId)
    {
        model.deleteTemplate(templateId);
    }

    @Override
    public void deleteCustomer(final BigInteger customerId)
    {
        model.deleteCustomer(customerId);
    }

    @Override
    public void deleteEmployee(final BigInteger employeeId)
    {
        model.deleteEmployee(employeeId);
    }

    @Override
    public void deepDeleteArea(final BigInteger areaId)
    {
        List<Template> templates = getTemplatesByAreaId(areaId);
        for(Template template : templates){
            List<BigInteger> possibleAreas = template.getPossibleAreasId();
            possibleAreas.remove(areaId);
            template.setPossibleAreasId(possibleAreas);
            model.setTemplate(template);
        }

        List<Customer> customers = getCustomers();
        for(Customer customer : customers){
            if(customer.getArea().equals(areaId)){
                deepDeleteCustomer(customer.getId());
            }
        }
        model.deleteArea(areaId);
    }

    @Override
    public void deepDeleteOrder(final BigInteger orderId)
    {
        Order order = getOrder(orderId);
        Service service = getService(order.getServiceId());
        deepDeleteService(service.getId());

        model.deleteOrder(orderId);
    }

    @Override
    public void deepDeleteService(final BigInteger serviceId)
    {
        List<Order> orders = getOrders();

        for(Order order : orders){
            if (order.getServiceId().equals(serviceId) && !order.getStatus().equals(OrderStatus.Completed)){
                model.deleteOrder(order.getId());
            }
        }

        model.deleteService(serviceId);
    }

    @Override
    public void deepDeleteTemplate(final BigInteger templateId)
    {
        List<Service> services = getServices();

        for(Service service : services){
            if(service.getTemplateId().equals(templateId)){
                deepDeleteService(service.getId());
            }
        }

        model.deleteTemplate(templateId);
    }

    @Override
    public void deepDeleteCustomer(final BigInteger customerId)
    {
        List<Service> services = getCustomerServices(customerId);
        for(Service service : services){
            deepDeleteService(service.getId());
        }
        model.deleteCustomer(customerId);
    }

    @Override
    public void deepDeleteEmployee(final BigInteger employeeId)
    {
        List<Order> orders = getOrdersByEmployeeId(employeeId);
        for(Order order : orders){
            deepDeleteOrder(order.getId());
        }

        model.deleteEmployee(employeeId);
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
    public Area getAreaByName(final String name)
    {
        return model.getAreaByName(name);
    }

    @Override
    public Customer getCustomerByLogin(final String login)
    {
        return model.getCustomerByLogin(login);
    }

    @Override
    public Employee getEmployeeByLogin(final String login)
    {
        return model.getEmployeeByLogin(login);
    }

    @Override
    public List<Order> getOrdersByTemplateId(final BigInteger templateId)
    {
        return model.getOrdersByTemplateId(templateId);
    }

    @Override
    public List<Order> getOrdersByServiceId(final BigInteger serviceId)
    {
        return model.getOrdersByServiceId(serviceId);
    }

    @Override
    public List<Order> getOrdersByStatus(final OrderStatus status)
    {
        return model.getOrdersByStatus(status);
    }

    @Override
    public List<Order> getOrdersByAction(final OrderAction action)
    {
        return model.getOrdersByAction(action);
    }

    @Override
    public List<Service> getServicesByUserId(final BigInteger userId)
    {
        return model.getServicesByUserId(userId);
    }

    @Override
    public List<Service> getServicesByTemplateId(final BigInteger templateId)
    {
        return model.getServicesByTemplateId(templateId);
    }

    @Override
    public List<Service> getServicesByStatus(final ServiceStatus status)
    {
        return model.getServicesByStatus(status);
    }

    @Override
    public List<Service> getServicesByStatusAndCustomerId(final BigInteger userId, final ServiceStatus status)
    {
        return model.getServicesByStatusAndCustomerId(userId,status);
    }

    @Override
    public Template getTemplateByName(final String name)
    {
        return model.getTemplateByName(name);
    }

    @Override
    public Object getEntity(final BigInteger entityId)
    {
        Object result = getArea(entityId);
        if(result == null){
            result = getTemplate(entityId);
        }
        if(result == null){
            result = getEmployee(entityId);
        }
        if(result == null){
            result = getCustomer(entityId);
        }
        if(result == null){
            result = getService(entityId);
        }
        if(result == null){
            result = getOrder(entityId);
        }

        return result;
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
    public boolean checkCustomerPassword(final BigInteger customerId, final String password)
    {
        return model.getCustomer(customerId).getPassword().equals(password);
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
    public List<Service> getPlannedServices(final BigInteger customerId)
    {
        return model.getServicesByStatusAndCustomerId(customerId,ServiceStatus.Planned);
    }


    @Override
    public List<Service> getActiveServices(final BigInteger customerId)
    {
        return model.getServicesByStatusAndCustomerId(customerId,ServiceStatus.Active);
    }

    @Override
    public List<Service> getPlannedAndActiveServices(final BigInteger customerId)
    {
        List<Service> services = new ArrayList<>(getPlannedServices(customerId));
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
        return model.getCustomer(customerId).getArea();
    }

    @Override
    public boolean isCustomer(final BigInteger userId)
    {
        return model.getCustomer(userId) != null;
    }

    @Override
    public boolean isEmployee(final BigInteger userId)
    {
        return model.getEmployee(userId) != null;
    }

    @Override
    public Customer getCustomer(final BigInteger id)
    {
        return model.getCustomer(id);
    }

    @Override
    public Employee getEmployee(final BigInteger userId)
    {
        return model.getEmployee(userId);
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
        return model.getArea(areaId);
    }

    @Override
    public Template getTemplate(final BigInteger templateId)
    {
        return model.getTemplate(templateId);
    }

    @Override
    public Service getService(final BigInteger serviceId)
    {
        return model.getService(serviceId);
    }

    @Override
    public Order getOrder(final BigInteger orderId)
    {
        return model.getOrder(orderId);
    }

    @Override
    public void putOnBalance(final BigInteger customerId, final BigDecimal money)
    {
        Customer customer = model.getCustomer(customerId);
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

    public static ControllerImpl getInstance(Model model)
    {
        if (instance == null)
        {
            instance = new ControllerImpl(model);
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
        return getArea(getCustomer(customerId).getArea()).getName();
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
        customer.setArea(areaId);
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
    public void process(final BigInteger serviceId)
    {
        Service service = getService(serviceId);
        service.setStatus(ServiceStatus.Provisioning);
    }

    @Override
    public void backToPlanned(final BigInteger serviceId)
    {
        Service service = getService(serviceId);
        service.setStatus(ServiceStatus.Planned);
    }

    @Override
    public void activate(final BigInteger serviceId)
    {
        Service service = getService(serviceId);
        service.setStatus(ServiceStatus.Active);
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
        for(Service service : getPlannedActiveSuspendedProvisioningService(customerId)){
            if(service.getTemplateId().equals(templateId)){
                return;
            }
        }
        Template template = getTemplate(templateId);
        if(customer.getMoneyBalance().doubleValue()>=template.getCost().doubleValue())
        {
            customer.setMoneyBalance(BigDecimal.valueOf(
                    customer.getMoneyBalance().doubleValue() - template.getCost().doubleValue())
            );

            Service service = createService(customerId, templateId, ServiceStatus.Planned);
            Order order = createOrder(templateId, service.getId(), OrderStatus.Entering, OrderAction.New);
            customer.addConnectedServiceId(service.getId());
            model.setCustomer(customer);
        }
       // completeOrder(order,service);
    }

    @Override
    public void disconnectService(final BigInteger serviceId)
    {
        Service service = getService(serviceId);
        Order order =
                createOrder(service.getTemplateId(), serviceId, OrderStatus.Entering, OrderAction.Disconnect);
        service.setStatus(ServiceStatus.Provisioning);
        model.setService(service);
       // completeOrder(order,service);
    }


    @Override
    public List<Service> getCustomerServices(final BigInteger customerId)
    {
        return model.getServicesByUserId(customerId);
    }

    @Override
    public List<Area> getAvailableAreas(final BigInteger customerId)
    {
        List<Service> services = getPlannedAndActiveServices(customerId);
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
        Service service = getService(order.getServiceId());
        service.setStatus(ServiceStatus.Provisioning);
        model.setService(service);
        model.setOrder(order);
    }

    @Override
    public List<Service> getPlannedActiveSuspendedProvisioningService(BigInteger customerId)
    {
        List<Service> services = getPlannedAndActiveServices(customerId);
        services.addAll(getSuspendedServices(customerId));
        services.addAll(getProvisioningServices(customerId));
        return services;
    }

    private List<Service> getProvisioningServices(BigInteger customerId){
        return model.getServicesByStatusAndCustomerId(customerId,ServiceStatus.Provisioning);
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


    public void takeMoney(Timer timer){
        timer.schedule(new TimerTask()
        {
            @Override
            public void run()
            {
               for(Customer customer : getCustomers()){
                   List<Service> services = getActiveServices(customer.getId());
                   System.out.println("Take money from customer");
                   serviceSorter.sort(services,ServiceSortType.UpByCost);
                   for(Service service : services){
                       if(customer.getMoneyBalance().compareTo(service.getCost())>-1){
                           customer.setMoneyBalance(
                                   BigDecimal.valueOf(
                                           customer.getMoneyBalance().doubleValue()-service.getCost().doubleValue()));
                       }
                       else {
                           service.setStatus(ServiceStatus.Suspended);
                           setService(service);
                       }
                   }
                   setCustomer(customer);
               }
            }
        },new Date(),10_000_000);
    }
}
