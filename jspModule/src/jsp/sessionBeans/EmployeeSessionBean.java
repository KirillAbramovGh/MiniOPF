package jsp.sessionBeans;

import com.netcracker.students.o3.controller.Controller;
import com.netcracker.students.o3.controller.ControllerImpl;
import com.netcracker.students.o3.controller.searcher.EmployeeSearcher;
import com.netcracker.students.o3.controller.searcher.AreaSearcher;
import com.netcracker.students.o3.controller.searcher.CustomerSearcher;
import com.netcracker.students.o3.controller.searcher.OrdersSearcher;
import com.netcracker.students.o3.controller.searcher.ServiceSearcher;
import com.netcracker.students.o3.controller.searcher.TemplatesSearcher;
import com.netcracker.students.o3.controller.sorters.AreaSorter;
import com.netcracker.students.o3.controller.sorters.CustomerSorter;
import com.netcracker.students.o3.controller.sorters.EmployeeSorter;
import com.netcracker.students.o3.controller.sorters.OrderSorter;
import com.netcracker.students.o3.controller.sorters.ServiceSorter;
import com.netcracker.students.o3.controller.sorters.SortType.AreaSortType;
import com.netcracker.students.o3.controller.sorters.SortType.CustomerSortType;
import com.netcracker.students.o3.controller.sorters.SortType.EmployeeSortType;
import com.netcracker.students.o3.controller.sorters.SortType.OrderSortType;
import com.netcracker.students.o3.controller.sorters.SortType.ServiceSortType;
import com.netcracker.students.o3.controller.sorters.SortType.TemplateSortType;
import com.netcracker.students.o3.controller.sorters.TemplateSorter;
import com.netcracker.students.o3.model.Entity;
import com.netcracker.students.o3.model.orders.OrderAction;
import com.netcracker.students.o3.model.orders.OrderStatus;
import com.netcracker.students.o3.model.serialization.JsonEntitiesStorage;
import com.netcracker.students.o3.model.area.Area;
import com.netcracker.students.o3.model.orders.Order;
import com.netcracker.students.o3.model.services.Service;
import com.netcracker.students.o3.model.services.ServiceStatus;
import com.netcracker.students.o3.model.templates.Template;
import com.netcracker.students.o3.model.users.Customer;
import com.netcracker.students.o3.model.users.Employee;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.Stateless;

@Stateless
public class EmployeeSessionBean
{
    public Employee getEmployee(BigInteger employeeId)
    {
        return ControllerImpl.getInstance().getEmployee(employeeId);
    }

    public Entity getEntity(BigInteger id){
        return ControllerImpl.getInstance().getEntity(id);
    }
    public Customer getCustomer(BigInteger customerId){
        return ControllerImpl.getInstance().getCustomer(customerId);
    }
    public Template getTemplate(BigInteger templateId){
        return ControllerImpl.getInstance().getTemplate(templateId);
    }
    public Service getService(BigInteger serviceId){
        return ControllerImpl.getInstance().getService(serviceId);
    }
    public Order getOrder(BigInteger orderId){
        return ControllerImpl.getInstance().getOrder(orderId);
    }
    public Area getArea(BigInteger areaId){
        return ControllerImpl.getInstance().getArea(areaId);
    }
    public void changeNameAndPassword(String name, String password, BigInteger employeeId)
    {
        Employee employee = ControllerImpl.getInstance().getEmployee(employeeId);

        employee.setName(name);
        employee.setPassword(password);

        ControllerImpl.getInstance().setEmployee(employee);
    }

    public List<Order> getFilteredOrdersByEmployeeId(String search, String field, OrderSortType sortOrders,
            String templateId, String serviceId, BigInteger eId)
    {
        OrdersSearcher searcherOrders = OrdersSearcher.getInstance();
        List<Order> orders = ControllerImpl.getInstance().getOrdersByEmployeeId(eId);

        if (isNotNullOrEmpty(search))
        {
            orders = searcherOrders.search(search, field, orders);
        }
        if (isNotNullOrEmpty(templateId))
        {
            orders = searcherOrders.search(templateId, "TemplateId", orders);
        }
        if (isNotNullOrEmpty(serviceId))
        {
            orders = searcherOrders.search(serviceId, "ServiceId", orders);
        }

        if (sortOrders != null)
        {
            OrderSorter.getInstance().sort(orders, sortOrders);
        }

        return orders;
    }

    public List<Order> getFilteredOrders(String search, String field, OrderSortType sortOrders,
            String templateId, String serviceId, String employeeId)
    {
        OrdersSearcher searcherOrders = OrdersSearcher.getInstance();
        List<Order> orders = ControllerImpl.getInstance().getOrders();
        if (isNotNullOrEmpty(search))
        {
            orders = searcherOrders.search(search, field, orders);
        }
        if (isNotNullOrEmpty(templateId))
        {
            orders = searcherOrders.search(templateId, "TemplateId", orders);
        }
        if (isNotNullOrEmpty(serviceId))
        {
            orders = searcherOrders.search(serviceId, "ServiceId", orders);
        }
        if (isNotNullOrEmpty(employeeId))
        {
            orders = searcherOrders.search(employeeId, "EmployeeId", orders);
        }

        if (sortOrders != null)
        {
            OrderSorter.getInstance().sort(orders, sortOrders);
        }

        return orders;
    }

    public List<Service> getFilteredServices(String search, String field, ServiceSortType sortService,
            String name, String cost)
    {
        ServiceSearcher searcherService = ServiceSearcher.getInstance();
        List<Service> services = ControllerImpl.getInstance().getServices();
        if (isNotNullOrEmpty(search))
        {
            services = searcherService.search(search, field, services);
        }

        if (isNotNullOrEmpty(name))
        {
            services = searcherService.search(name, "Name", services);
        }
        if (isNotNullOrEmpty(cost))
        {
            services = searcherService.search(cost, "Cost", services);
        }

        if (sortService != null)
        {
            ServiceSorter.getInstance().sort(services, sortService);
        }
        return services;
    }

    public List<Template> getFilteredTemplates(String search, String field, TemplateSortType sortTemplates,
            String name, String cost)
    {
        TemplatesSearcher searcherTemplates = TemplatesSearcher.getInstance();
        List<Template> templates = ControllerImpl.getInstance().getTemplates();
        if (isNotNullOrEmpty(search))
        {
            templates = searcherTemplates.search(search, field, templates);
        }
        if (isNotNullOrEmpty(name))
        {
            templates = searcherTemplates.search(name, "Name", templates);
        }
        if (isNotNullOrEmpty(cost))
        {
            templates = searcherTemplates.search(cost, "Cost", templates);
        }

        if (sortTemplates != null)
        {
            TemplateSorter.getInstance().sort(templates, sortTemplates);
        }
        return templates;
    }

    public List<Customer> getFilteredCustomers(String search, String field, CustomerSortType sortCustomers,
            String name, String area)
    {
        CustomerSearcher searcherCustomer = CustomerSearcher.getInstance();
        List<Customer> customers = ControllerImpl.getInstance().getCustomers();
        if (isNotNullOrEmpty(search))
        {
            customers = searcherCustomer.search(search, field, customers);
        }
        if (isNotNullOrEmpty(name))
        {
            customers = searcherCustomer.search(name, "Name", customers);
        }
        if (isNotNullOrEmpty(area))
        {
            customers = searcherCustomer.search(area, "Area", customers);
        }

        if (sortCustomers != null)
        {
            CustomerSorter.getInstance().sort(customers, sortCustomers);
        }
        return customers;
    }


    public List<Employee> getFilteredEmployees(String searchValue, String searchField, EmployeeSortType sortEmployees,
            String name)
    {
        EmployeeSearcher employeeSearcher = EmployeeSearcher.getInstance();
        List<Employee> employees = ControllerImpl.getInstance().getEmployees();
        if (isNotNullOrEmpty(searchValue))
        {
            employees = employeeSearcher.search(searchValue, searchField, employees);
        }
        if (isNotNullOrEmpty(name))
        {
            employees = employeeSearcher.search(name, "Name", employees);
        }

        if (sortEmployees != null)
        {
            EmployeeSorter.getInstance().sort(employees, sortEmployees);
        }
        return employees;
    }

    public List<Area> getFilteredAreas(String search, String field, AreaSortType sortAreas,
            String name)
    {
        AreaSearcher searcherArea = AreaSearcher.getInstance();
        List<Area> areas = ControllerImpl.getInstance().getAreas();
        if (isNotNullOrEmpty(search))
        {
            areas = searcherArea.search(search, field, areas);
        }
        if (isNotNullOrEmpty(name))
        {
            areas = searcherArea.search(name, "Name", areas);
        }

        if (sortAreas != null)
        {
            AreaSorter.getInstance().sort(areas, sortAreas);
        }
        return areas;
    }


    public void startOrder(BigInteger orderId, BigInteger employeeId)
    {
        Order order = ControllerImpl.getInstance().getOrder(orderId);
        if (order.getEmployee().getId().longValue() == 0 || order.getEmployee().getId().equals(employeeId))
        {
            ControllerImpl.getInstance().startOrder(orderId, order.getEmployee());
        }
    }

    private boolean isNotNullOrEmpty(String value)
    {
        return value != null && !value.replaceAll(" ", "").isEmpty();
    }

    public void resumeOrder(BigInteger orderId)
    {
        ControllerImpl.getInstance().resumeOrder(orderId);
    }

    public void completeOrder(BigInteger orderId)
    {
        ControllerImpl.getInstance().completeOrder(orderId);
    }

    public void deleteTemplate(BigInteger templateId)
    {
        ControllerImpl.getInstance().deepDeleteTemplate(templateId);
    }

    public void deleteCustomer(BigInteger customerId)
    {
        ControllerImpl.getInstance().deepDeleteCustomer(customerId);
    }

    public void deleteEmployee(BigInteger employeeId)
    {
        ControllerImpl.getInstance().deepDeleteEmployee(employeeId);
    }

    public void deleteArea(BigInteger areaId)
    {
        ControllerImpl.getInstance().deepDeleteArea(areaId);
    }

    public void deleteOrder(final BigInteger id)
    {
        ControllerImpl.getInstance().deepDeleteOrder(id);
    }

    public void deleteService(final BigInteger id)
    {
        ControllerImpl.getInstance().deepDeleteService(id);
    }


    public List<Area> getAreas(){
        return ControllerImpl.getInstance().getAreas();
    }

    public List<Customer> getCustomers(){
        return ControllerImpl.getInstance().getCustomers();
    }

    public List<Employee> getEmployees(){
        return ControllerImpl.getInstance().getEmployees();
    }

    public List<Template> getTemplates(){
        return ControllerImpl.getInstance().getTemplates();
    }

    public List<Service> getServices(){
        return ControllerImpl.getInstance().getServices();
    }

    public List<Order> getOrders(){
        return ControllerImpl.getInstance().getOrders();
    }


    public List<Order> getOrders(List<BigInteger> ids){
        List<Order> orders = new ArrayList<>();
        Controller controller = ControllerImpl.getInstance();

        for(BigInteger id : ids){
            orders.add(controller.getOrder(id));
        }
        return orders;
    }

    public List<Service> getServices(List<BigInteger> ids){
        List<Service> services = new ArrayList<>();
        Controller controller = ControllerImpl.getInstance();

        for(BigInteger id : ids){
            services.add(controller.getService(id));
        }
        return services;
    }

    public List<Template> getTemplates(List<BigInteger> ids){
        List<Template> templates = new ArrayList<>();
        Controller controller = ControllerImpl.getInstance();

        for(BigInteger id : ids){
            templates.add(controller.getTemplate(id));
        }
        return templates;
    }

    public List<Customer> getCustomers(List<BigInteger> ids){
        List<Customer> orders = new ArrayList<>();
        Controller controller = ControllerImpl.getInstance();

        for(BigInteger id : ids){
            orders.add(controller.getCustomer(id));
        }
        return orders;
    }

    public List<Employee> getEmployees(List<BigInteger> ids){
        List<Employee> employees = new ArrayList<>();
        Controller controller = ControllerImpl.getInstance();

        for(BigInteger id : ids){
            employees.add(controller.getEmployee(id));
        }
        return employees;
    }

    public List<Area> getAreas(List<BigInteger> ids){
        List<Area> areas = new ArrayList<>();
        Controller controller = ControllerImpl.getInstance();

        for(BigInteger id : ids){
            areas.add(controller.getArea(id));
        }
        return areas;
    }

    public void importEntities(boolean isIgnored){
        JsonEntitiesStorage jsonEntitiesStorage = JsonEntitiesStorage.getInstance();
        jsonEntitiesStorage.importFromFile();

        ControllerImpl.getInstance().importEntities(jsonEntitiesStorage,isIgnored);
    }

    public void setFieldsOfEntities(Set<BigInteger> idsOfEntities,String type, Map<String,String[]> params){
        switch (type){
            case "customer": setFieldsOfCustomers(idsOfEntities,params);break;
            case "employee": setFieldsOfEmployees(idsOfEntities,params);break;
            case "template": setFieldsOfTemplates(idsOfEntities,params);break;
            case "service": setFieldsOfServices(idsOfEntities,params);break;
            case "order": setFieldsOfOrders(idsOfEntities,params);break;
            case "area": setFieldsOfAreas(idsOfEntities,params);break;
        }
    }

    public void setFieldsOfCustomers(Set<BigInteger> idsOfCustomers, Map<String,String[]> params){
        Controller controller = ControllerImpl.getInstance();
        for(BigInteger id : idsOfCustomers){
            Customer customer = controller.getCustomer(id);
            String name = params.get("name")[0];
            String login = params.get("login")[0];
            String password = params.get("password")[0];
            String balance = params.get("balance")[0];
            if(!"".equals(name)){
                customer.setName(name+customer.getId());
            }
            if(!"".equals(login)){
                customer.setLogin(login+customer.getId());
            }
            if(!"".equals(password)){
                customer.setPassword(password+customer.getId());
            }
            if(!"".equals(balance)){
                customer.setMoneyBalance(BigDecimal.valueOf(Long.parseLong(balance)));
            }
            controller.setCustomer(customer);
        }
    }
    public void setFieldsOfEmployees(Set<BigInteger> idsOfEmployees, Map<String,String[]> params){
        Controller controller = ControllerImpl.getInstance();
        for(BigInteger id : idsOfEmployees){
            Employee employee = controller.getEmployee(id);
            String name = params.get("name")[0];
            String login = params.get("login")[0];
            String password = params.get("password")[0];
            if(!"".equals(name)){
                employee.setName(name+employee.getId());
            }
            if(!"".equals(login)){
                employee.setLogin(login+employee.getId());
            }
            if(!"".equals(password)){
                employee.setPassword(password+employee.getId());
            }
            controller.setEmployee(employee);
        }
    }
    public void setFieldsOfTemplates(Set<BigInteger> idsOfTemplates, Map<String,String[]> params){
        Controller controller = ControllerImpl.getInstance();
        for(BigInteger id : idsOfTemplates){
            Template template = controller.getTemplate(id);
            String name = params.get("name")[0];
            String description = params.get("description")[0];
            String cost = params.get("cost")[0];
            if(!"".equals(name)){
                template.setName(name+template.getId());
            }
            if(!"".equals(description)){
                template.setDescription(description);
            }
            if(!"".equals(cost)){
                template.setCost(BigDecimal.valueOf(Long.parseLong(cost)));
            }
            controller.setTemplate(template);
        }
    }
    public void setFieldsOfServices(Set<BigInteger> idsOfServices, Map<String,String[]> params){
        Controller controller = ControllerImpl.getInstance();
        for(BigInteger id : idsOfServices){
            Service service = controller.getService(id);
            String status = params.get("status")[0];
            if(!"".equals(status)){
                service.setStatus(ServiceStatus.valueOf(status));
            }
            controller.setService(service);
        }
    }
    public void setFieldsOfOrders(Set<BigInteger> idsOfOrders, Map<String,String[]> params){
        Controller controller = ControllerImpl.getInstance();
        for(BigInteger id : idsOfOrders){
            Order order = controller.getOrder(id);
            String status = params.get("status")[0];
            String action = params.get("action")[0];
            if(!"".equals(status)){
                order.setStatus(OrderStatus.valueOf(status));
            }
            if(!"".equals(action)){
                order.setAction(OrderAction.valueOf(status));
            }
            controller.setOrder(order);
        }
    }
    public void setFieldsOfAreas(Set<BigInteger> idsOfAreas, Map<String,String[]> params){
        Controller controller = ControllerImpl.getInstance();
        for(BigInteger id : idsOfAreas){
            Area area = controller.getArea(id);
            String name = params.get("name")[0];
            String description = params.get("description")[0];
            if(!"".equals(name)){
                area.setName(name+area.getId());
            }
            if(!"".equals(description)){
                area.setDescription(description);
            }
            controller.setArea(area);
        }
    }
}