package com.netcracker.students.o3.controller;

import com.netcracker.students.o3.Exceptions.IncorrectCredentialsException;
import com.netcracker.students.o3.Exceptions.LoginOccupiedException;
import com.netcracker.students.o3.Exceptions.WrongInputException;
import com.netcracker.students.o3.model.Model;
import com.netcracker.students.o3.model.area.Area;
import com.netcracker.students.o3.model.orders.Order;
import com.netcracker.students.o3.model.orders.OrderAction;
import com.netcracker.students.o3.model.orders.OrderStatus;
import com.netcracker.students.o3.model.serializer.Serializer;
import com.netcracker.students.o3.model.serializer.SerializerImpl;
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

public class ControllerImpl implements Controller {
    private final Model model;
    private static Controller instance;

    private ControllerImpl() {
        model = Model.getInstance();
        Serializer serializer = new SerializerImpl();

        try {
            serializer.deserializeModel(model);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void disconnectService(final BigInteger customerId, final BigInteger serviceId) {
        Service service = getService(serviceId);

        BigInteger orderId = createOrder(service.getTemplateId(), serviceId, OrderStatus.Entering, OrderAction.Disconnect);
        startOrder(orderId, null);
        completeOrder(orderId);

        model.setService(model.getServiceById(serviceId));
    }

    @Override
    public void resumeService(BigInteger serviceId) {
        Service service = getService(serviceId);
        service.setStatus(ServiceStatus.Active);
        service.setActivationDate(new Date());
    }

    @Override
    public void suspendService(BigInteger serviceId) {
        Service service = getService(serviceId);
        service.setStatus(ServiceStatus.Suspended);
    }

    @Override
    public void cancelService(BigInteger customerId, BigInteger serviceId) {
        disconnectService(customerId, serviceId);
    }

    @Override
    public void completeService(BigInteger serviceId) {
        Service service = getService(serviceId);
        service.setStatus(ServiceStatus.Active);
        service.setActivationDate(new Date());
    }

    @Override
    public void startOrder(final BigInteger orderId, final BigInteger employeeId) {
        Order order = getOrder(orderId);
        order.setEmployeeId(employeeId);
        order.setStatus(OrderStatus.Processing);
    }

    @Override
    public void suspendOrder(final BigInteger orderId) {

    }

    @Override
    public void stopOrder(final BigInteger orderId) {

    }


    @Override
    public void completeOrder(final BigInteger orderId) {
        Order order = getOrder(orderId);
        Service service = getService(order.getServiceId());

        switch (order.getAction()) {
            case New:
            case Resume:
                completeService(service.getId());
                break;
            case Suspend:
                suspendService(service.getId());
                break;
            case Disconnect:
                disconnectService(service.getUserId(), service.getId());
                break;
        }

        order.setStatus(OrderStatus.Completed);
    }

    @Override
    public void changeBalance(final BigInteger customerId, final BigDecimal value) {

    }

    @Override
    public void deleteArea(final BigInteger areaId) {
        model.deleteAreaById(areaId);
    }

    @Override
    public void deleteOrder(final BigInteger orderId) {

    }

    @Override
    public void deleteService(final BigInteger serviceId) {

    }

    @Override
    public void deleteTemplate(final BigInteger templateId) {
        model.deleteTemplateById(templateId);
    }

    @Override
    public void deleteCustomer(final BigInteger customerId) {
        model.deleteCustomerById(customerId);
    }

    @Override
    public void deleteEmployee(final BigInteger employeeId) {
        model.deleteEmployeeById(employeeId);
    }

    @Override
    public void deepDeleteArea(final BigInteger areaId) {

    }

    @Override
    public void deepDeleteOrder(final BigInteger orderId) {

    }

    @Override
    public void deepDeleteService(final BigInteger serviceId) {

    }

    @Override
    public void deepDeleteTemplate(final BigInteger templateId) {

    }

    @Override
    public void deepDeleteCustomer(final BigInteger customerId) {

    }

    @Override
    public void deepDeleteEmployee(final BigInteger employeeId) {

    }

    @Override
    public BigInteger createCustomer(final String name, final String login, final String password,
                                     final BigInteger areaId) {
        return model.createCustomer(name, login, password, areaId);
    }

    @Override
    public BigInteger createEmployee(final String name, final String login, final String password) {
        return model.createEmployee(name, login, password);
    }

    @Override
    public BigInteger createOrder(final BigInteger templateId, final BigInteger serviceId,
                                  final OrderStatus status, final OrderAction action) {
        return model.createOrder(templateId, serviceId, status, action);
    }

    @Override
    public BigInteger createTemplate(final String name, final BigDecimal cost, final String description) {
        return model.createTemplate(name, cost, description);
    }

    @Override
    public BigInteger createService(final BigInteger userId, final BigInteger templateId, final ServiceStatus status) {
        BigInteger serviceId = model.createService(userId, templateId, status);
        model.createOrder(templateId, serviceId, OrderStatus.Entering, OrderAction.New);
        return serviceId;
    }

    @Override
    public BigInteger createArea(final String name, final String description) {
        return model.createArea(name, description);
    }

    @Override
    public BigInteger getUserIdByCredentials(final String login, final String password)
            throws IncorrectCredentialsException {
        for (Customer customer : model.getCustomers().values()) {
            if (customer.getLogin().equals(login) && customer.getPassword().equals(password)) {
                return customer.getId();
            }
        }

        for (Employee employee : model.getEmployers().values()) {
            if (employee.getLogin().equals(login) && employee.getPassword().equals(password)) {
                System.out.println(employee);
                return employee.getId();
            }
        }
        throw new IncorrectCredentialsException("Неправильный логин или пароль!");
    }


    @Override
    public BigInteger registerCustomer(final String login, final String password, final String name,
                                       final BigInteger areaId) throws LoginOccupiedException {
        if (!isLoginExists(login)) {
            return createCustomer(name, login, password, areaId);
        } else {
            throw new LoginOccupiedException("Login occupied");
        }
    }

    @Override
    public BigInteger registerEmployee(final String login, final String password, final String Name) throws LoginOccupiedException {
        if (!isLoginExists(login)) {
            return model.createEmployee(Name, login, password);
        }

        throw new LoginOccupiedException("Login occupied");
    }

    @Override
    public boolean checkPassword(final BigInteger id, final String password) {
        return model.getCustomerById(id).getPassword().equals(password);
    }

    @Override
    public boolean isLoginExists(final String login) {

        return isEmployeeLogin(login) || isCustomerLogin(login);
    }

    @Override
    public List<Service> getSuspendedServices(final BigInteger customerId) {
        ArrayList<Service> services = new ArrayList<>();
        for (BigInteger v : model.getCustomerById(customerId).getConnectedServicesIds()) {
            if (model.getServiceById(v).getStatus().equals(ServiceStatus.Suspended)) {
                services.add(model.getServiceById(v));
            }
        }
        return services;
    }

    @Override
    public List<Service> getEnteringServices(final BigInteger customerId) {
        ArrayList<Service> services = new ArrayList<>();
        for (BigInteger v : model.getCustomerById(customerId).getConnectedServicesIds()) {

            if (model.getServiceById(v).getStatus().equals(ServiceStatus.Entering)) {
                services.add(model.getServiceById(v));
            }
        }
        return services;
    }


    @Override
    public List<Service> getActiveServices(final BigInteger customerId) {
        ArrayList<Service> services = new ArrayList<>();
        for (BigInteger serviceId : model.getCustomerById(customerId).getConnectedServicesIds()) {
            if (model.getServiceById(serviceId).getStatus() == ServiceStatus.Active) {
                services.add(model.getServiceById(serviceId));
            }
        }

        return services;
    }

    @Override
    public List<Service> getEnteringAndActiveServices(final BigInteger customerId) {
        List<Service> services = new ArrayList<>(getEnteringServices(customerId));
        services.addAll(getActiveServices(customerId));
        return services;
    }


    @Override
    public List<Template> getTemplatesByAreaId(final BigInteger areaId) {
        ArrayList<Template> templates = new ArrayList<>();
        for (Template template : model.getTemplates().values()) {
            if (template.getPossibleAreasId().contains(areaId)) {
                templates.add(template);
            }
        }

        return templates;
    }

    @Override
    public List<Template> getAllTemplates() {

        return new ArrayList<>(model.getTemplates().values());
    }

    @Override
    public List<Order> getOrdersByCustomerId(final BigInteger customerId) {
        return null;
    }

    @Override
    public List<Order> getOrdersByEmployeeId(final BigInteger employeeId) {
        List<Order> orders = getOrders();
        List<Order> result = new ArrayList<>();
        for (Order order : orders) {
            if (employeeId.equals(order.getEmployeeId())) {
                result.add(order);
            }
        }
        return result;
    }

    @Override
    public BigInteger getCustomerAreaId(final BigInteger customerId) {
        return model.getCustomerById(customerId).getAreaId();
    }

    @Override
    public boolean isCustomer(final BigInteger userId) {
        return model.getCustomerById(userId) != null;
    }

    @Override
    public boolean isEmployee(final BigInteger userId) {
        return model.getEmployeeById(userId) != null;
    }

    @Override
    public Customer getCustomer(final BigInteger id) {
        return model.getCustomerById(id);
    }

    @Override
    public Employee getEmployee(final BigInteger userId) {
        return model.getEmployeeById(userId);
    }

    @Override
    public List<Area> getAreas() {
        return new ArrayList<>(model.getAreas().values());
    }

    @Override
    public List<Template> getTemplates() {
        return new ArrayList<>(model.getTemplates().values());
    }

    @Override
    public List<Service> getServices() {
        return new ArrayList<>(model.getServices().values());
    }

    @Override
    public List<Customer> getCustomers() {
        return new ArrayList<>(model.getCustomers().values());
    }

    @Override
    public List<Order> getOrders() {
        return new ArrayList<>(model.getOrders().values());
    }

    @Override
    public List<Employee> getEmployers() {
        return new ArrayList<>(model.getEmployers().values());
    }

    @Override
    public Area getArea(final BigInteger areaId) {
        return model.getAreaById(areaId);
    }

    @Override
    public Template getTemplate(final BigInteger templateId) {
        return model.getTemplateById(templateId);
    }

    @Override
    public Service getService(final BigInteger serviceId) {
        return model.getServiceById(serviceId);
    }

    @Override
    public Order getOrder(final BigInteger orderId) {
        return model.getOrderById(orderId);
    }

    @Override
    public void putOnBalance(final BigInteger customerId, final BigDecimal money) {
        Customer customer = model.getCustomerById(customerId);
        BigDecimal currentMoney = customer.getMoneyBalance();
        customer.setMoneyBalance(currentMoney.add(money));

        model.setCustomer(customer);
    }

    @Override
    public List<Service> getServicesByAreaId(final BigInteger areaId) {
        List<Service> availableServices = new ArrayList<>();
        List<Service> allServices = getServices();

        for (Service service : allServices) {
            Template template = getTemplate(service.getTemplateId());
            if (template.getPossibleAreasId().contains(areaId)) {
                availableServices.add(service);
            }
        }

        return availableServices;
    }

    @Override
    public boolean isCustomerLogin(final String login) {
        List<Customer> customers = getCustomers();

        for (User user : customers) {
            if (user.getLogin().equals(login)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean isEmployeeLogin(final String login) {
        List<Employee> employees = this.getEmployers();

        for (User user : employees) {
            if (user.getLogin().equals(login)) {
                return true;
            }
        }

        return false;
    }

    public static Controller getInstance() {
        if (instance == null) {
            instance = new ControllerImpl();
        }

        return instance;
    }

    public BigDecimal getBalance(BigInteger customerId) {
        return getCustomer(customerId).getMoneyBalance();
    }

    public String getCustomerFio(BigInteger customerId) {
        return getCustomer(customerId).getName();
    }

    public String getAreaName(BigInteger customerId) {
        return getArea(getCustomer(customerId).getAreaId()).getName();
    }

    public List<Template> getCustomerAvailableTemplates(BigInteger customerId) {
        return getTemplatesByAreaId(getCustomerAreaId(customerId));
    }

    public void setCustomerName(BigInteger customerId, String name) throws WrongInputException {
        if (!name.isEmpty()) {
            getCustomer(customerId).setName(name);
            model.setCustomer(model.getCustomerById(customerId));
        } else {
            throw new WrongInputException("Имя не может быть пустым!");
        }
    }

    public void setUserLogin(BigInteger userId, String login) throws LoginOccupiedException, WrongInputException {
        if (!login.isEmpty()) {
            if (!isLoginExists(login)) {
                getUser(userId).setLogin(login);
                if (isCustomer(userId)) {
                    model.setCustomer(model.getCustomerById(userId));
                } else if (isEmployee(userId)) {
                    model.setEmployee(model.getEmployeeById(userId));
                }
            } else {
                if (!getUser(userId).getLogin().equals(login)) {
                    throw new LoginOccupiedException("Логин занят");
                }
            }
        } else {
            throw new WrongInputException("Логин не может быть пустым");
        }
    }

    private User getUser(BigInteger userId) {
        User user = getCustomer(userId);

        if (user == null) {
            user = getEmployee(userId);
        }

        return user;
    }

    public void setUserPassword(BigInteger userId, String password) throws WrongInputException {
        if (!password.isEmpty()) {
            getUser(userId).setPassword(password);
            if (isCustomer(userId)) {
                model.setCustomer(model.getCustomerById(userId));
            } else if (isEmployee(userId)) {
                model.setEmployee(model.getEmployeeById(userId));
            }
        } else {
            throw new WrongInputException("Пароль не может быть пустым");
        }
    }

    public void setCustomerArea(BigInteger customerId, BigInteger areaId) {
        cancelOrSuspendImpossibleServices(customerId, areaId);
        getCustomer(customerId).setAreaId(areaId);
        model.setCustomer(model.getCustomerById(customerId));
    }

    private void cancelOrSuspendImpossibleServices(BigInteger customerId, BigInteger areaId) {
        Template template;
        for (Service service : getCustomerServices(customerId)) {
            template = getTemplate(service.getTemplateId());
            if (!template.getPossibleAreasId().contains(areaId)) {
                disconnectEnteringOrSuspendActiveService(service.getId(), customerId);
            }
        }
    }

    private void disconnectEnteringOrSuspendActiveService(BigInteger serviceId, BigInteger customerId) {
        Service service = getService(serviceId);

        if (service.getStatus().equals(ServiceStatus.Active)) {
            suspendService(customerId, service.getId());
        } else if (service.getStatus().equals(ServiceStatus.Entering)) {
            disconnectService(customerId, service.getId());
        }
    }

    @Override
    public void suspendOrResumeService(final BigInteger customerId, final BigInteger serviceId) {
        if (getService(serviceId).getStatus() == ServiceStatus.Active) {
            suspendService(customerId, serviceId);
        } else if (getService(serviceId).getStatus() == ServiceStatus.Suspended) {
            resumeService(customerId, serviceId);
        }
    }

    @Override
    public void suspendService(final BigInteger customerId, final BigInteger serviceId) {
        Service service = getService(serviceId);
        BigInteger orderId = createOrder(service.getTemplateId(), serviceId, OrderStatus.Entering, OrderAction.Suspend);

        completeOrder(orderId);

        model.setService(model.getServiceById(serviceId));
    }

    @Override
    public void resumeService(final BigInteger customerId, final BigInteger serviceId) {
        Service service = getService(serviceId);
        BigInteger orderId = createOrder(service.getTemplateId(), serviceId, OrderStatus.Entering, OrderAction.Resume);
        model.setService(model.getServiceById(serviceId));
    }

    @Override
    public void connectService(final BigInteger customerId, final BigInteger templateId) {
        BigInteger serviceId = createService(customerId, templateId, ServiceStatus.Entering);
        getCustomer(customerId).getConnectedServicesIds().add(serviceId);
        model.setService(model.getServiceById(serviceId));
        model.setCustomer(model.getCustomerById(customerId));
    }

    @Override
    public List<Service> getCustomerServices(final BigInteger customerId) {
        Customer customer = model.getCustomerById(customerId);
        List<Service> result = new ArrayList<>();

        for (BigInteger serviceId : customer.getConnectedServicesIds()) {
            result.add(model.getServiceById(serviceId));
        }

        return result;
    }

    @Override
    public List<Area> getAvailableAreas(final BigInteger customerId) {
        List<Service> services = getEnteringAndActiveServices(customerId);
        List<Area> availableAreas = new ArrayList<>();

        if (services.size() > 0) {
            availableAreas.add(getArea(getCustomerAreaId(customerId)));
            for (Service service : services) {
                Template template = getTemplate(service.getTemplateId());
                for (BigInteger areaId : template.getPossibleAreasId()) {
                    if (!availableAreas.contains(getArea(areaId))) {
                        availableAreas.add(getArea(areaId));
                    }
                }
            }
        } else {
            availableAreas.addAll(getAreas());
        }

        return availableAreas;
    }

    @Override
    public void resumeOrder(BigInteger orderId) {
        Order order = getOrder(orderId);
        order.setStatus(OrderStatus.Processing);
    }


}
