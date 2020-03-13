package jsp;

import com.netcracker.students.o3.controller.Controller;
import com.netcracker.students.o3.controller.ControllerImpl;
import com.netcracker.students.o3.controller.searcher.SearcherArea;
import com.netcracker.students.o3.controller.searcher.SearcherCustomer;
import com.netcracker.students.o3.controller.searcher.EmployeeSearcher;
import com.netcracker.students.o3.controller.searcher.SearcherOrders;
import com.netcracker.students.o3.controller.searcher.SearcherService;
import com.netcracker.students.o3.controller.searcher.SearcherTemplates;
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
import com.netcracker.students.o3.model.area.Area;
import com.netcracker.students.o3.model.orders.Order;
import com.netcracker.students.o3.model.services.Service;
import com.netcracker.students.o3.model.templates.Template;
import com.netcracker.students.o3.model.users.Customer;
import com.netcracker.students.o3.model.users.Employee;

import java.math.BigInteger;
import java.util.List;

import jsp.builders.HtmlTableBuilder;

public class EmployeeWebOperations
{
    private static EmployeeWebOperations instance;

    private Controller controller;
    private HtmlTableBuilder tableBuilder;
    private SearcherService searcherService;
    private SearcherTemplates searcherTemplates;
    private SearcherCustomer searcherCustomer;
    private SearcherOrders searcherOrders;
    private SearcherArea searcherArea;
    private EmployeeSearcher employeeSearcher;


    private EmployeeWebOperations()
    {
        controller = ControllerImpl.getInstance();
        tableBuilder = HtmlTableBuilder.getInstance();
        searcherService = SearcherService.getInstance();
        searcherArea = SearcherArea.getInstance();
        searcherOrders = SearcherOrders.getInstance();
        searcherTemplates = SearcherTemplates.getInstance();
        searcherCustomer = SearcherCustomer.getInstance();
        employeeSearcher = EmployeeSearcher.getInstance();
    }

    public static EmployeeWebOperations getInstance()
    {
        if (instance == null)
        {
            instance = new EmployeeWebOperations();
        }
        return instance;
    }

    public Employee getEmployee(BigInteger employeeId)
    {
        return controller.getEmployee(employeeId);
    }

    public void changeNameAndPassword(String name, String password,BigInteger employeeId)
    {
        Employee employee = getEmployee(employeeId);
        boolean isChanged = false;
        if (!employee.getName().equals(name))
        {
            changeName(name, employee);
            isChanged = true;
        }
        if (!employee.getPassword().equals(password))
        {
            changePassword(password, employee);
            isChanged = true;
        }
        if(isChanged){
            controller.setEmployee(employee);
        }
    }

    private void changeName(String name, Employee employee)
    {
        if (isNotNullOrEmpty(name))
        {
            employee.setName(name);
        }
    }

    private void changePassword(String password, Employee employee)
    {
        if (isNotNullOrEmpty(password))
        {
            employee.setPassword(password);
        }
    }

    public String showEmployeeOrders(String search, String field, OrderSortType sortOrders,
            String templateId,String serviceId,BigInteger eId)
    {
        List<Order> orders = getEmployeeOrders(eId);
        if (isNotNullOrEmpty(search))
        {
            orders = searcherOrders.search(search, field, orders);
        }
        if(isNotNullOrEmpty(templateId)){
            orders = searcherOrders.search(templateId,"TemplateId",orders);
        }
        if(isNotNullOrEmpty(serviceId)){
            orders = searcherOrders.search(serviceId,"ServiceId",orders);
        }

        if (sortOrders != null)
        {
            OrderSorter.getInstance().sort(orders, sortOrders);
        }

        return tableBuilder.createOrdersTable(orders);
    }

    public String showAllOrders(String search, String field, OrderSortType sortOrders,
            String templateId,String serviceId,String employeeId)
    {
        List<Order> orders = controller.getOrders();
        if (isNotNullOrEmpty(search))
        {
            orders = searcherOrders.search(search, field, orders);
        }
        if(isNotNullOrEmpty(templateId)){
            orders = searcherOrders.search(templateId,"TemplateId",orders);
        }
        if(isNotNullOrEmpty(serviceId)){
            orders = searcherOrders.search(serviceId,"ServiceId",orders);
        }
        if(isNotNullOrEmpty(employeeId)){
            orders = searcherOrders.search(employeeId,"EmployeeId",orders);
        }

        if (sortOrders != null)
        {
            OrderSorter.getInstance().sort(orders, sortOrders);
        }

        return tableBuilder.createOrdersTable(orders);
    }

    public String showAllServices(String search, String field, ServiceSortType sortService,
            String name,String cost)
    {
        List<Service> services = controller.getServices();
        if (isNotNullOrEmpty(search))
        {
            services = searcherService.search(search, field, services);
        }

        if(isNotNullOrEmpty(name)){
            services = searcherService.search(name,"Name",services);
        }
        if(isNotNullOrEmpty(cost)){
            services = searcherService.search(cost,"Cost",services);
        }

        if (sortService != null)
        {
            ServiceSorter.getInstance().sort(services, sortService);
        }
        return tableBuilder.createEmployeeServicesTable(services);
    }

    public String showAllTemplates(String search, String field, TemplateSortType sortTemplates,
            String name,String cost)
    {
        List<Template> templates = controller.getTemplates();
        if (isNotNullOrEmpty(search))
        {
            templates = searcherTemplates.search(search, field, templates);
        }
        if(isNotNullOrEmpty(name)){
            templates = searcherTemplates.search(name,"Name",templates);
        }
        if(isNotNullOrEmpty(cost)){
            templates = searcherTemplates.search(cost,"Cost",templates);
        }

        if (sortTemplates != null)
        {
            TemplateSorter.getInstance().sort(templates, sortTemplates);
        }
        return tableBuilder.createEmployeeTemplatesTable(templates);
    }

    public String showAllCustomers(String search, String field, CustomerSortType sortCustomers,
            String name,String area)
    {
        List<Customer> customers = controller.getCustomers();
        if (isNotNullOrEmpty(search))
        {
            customers = searcherCustomer.search(search, field, customers);
        }
        if(isNotNullOrEmpty(name)){
            customers = searcherCustomer.search(name,"Name",customers);
        }
        if(isNotNullOrEmpty(area)){
            customers = searcherCustomer.search(area,"Area",customers);
        }

        if (sortCustomers != null)
        {
            CustomerSorter.getInstance().sort(customers, sortCustomers);
        }
        return tableBuilder.createCustomersTable(customers);
    }


    public String showAllEmployees(String searchValue, String searchField, EmployeeSortType sortEmployees,
            String name)
    {
        List<Employee> employees = controller.getEmployees();
        if (isNotNullOrEmpty(searchValue))
        {
            employees = employeeSearcher.search(searchValue, searchField, employees);
        }
        if(isNotNullOrEmpty(name)){
            employees = employeeSearcher.search(name,"Name",employees);
        }

        if (sortEmployees != null)
        {
            EmployeeSorter.getInstance().sort(employees, sortEmployees);
        }
        return tableBuilder.createEmployeesTable(employees);
    }

    public String showAllAreas(String search, String field, AreaSortType sortAreas,
            String name)
    {
        List<Area> areas = controller.getAreas();
        if (isNotNullOrEmpty(search))
        {
            areas = searcherArea.search(search, field, areas);
        }
        if(isNotNullOrEmpty(name)){
            areas = searcherArea.search(name, "Name",areas);
        }

        if (sortAreas != null)
        {
            AreaSorter.getInstance().sort(areas, sortAreas);
        }
        return tableBuilder.createAreasTable(areas);
    }





    public void startOrder(BigInteger orderId,BigInteger employeeId)
    {
        Order order = controller.getOrder(orderId);
        if(order.getEmployeeId().longValue() == 0 || order.getEmployeeId().equals(employeeId))
        {
            controller.startOrder(orderId, employeeId);
        }
    }

    private List<Order> getEmployeeOrders(BigInteger employeeId)
    {
        return controller.getOrdersByEmployeeId(employeeId);
    }

    private boolean isNotNullOrEmpty(String value)
    {
        return value != null && !value.replaceAll(" ", "").isEmpty();
    }

    public void resumeOrder(BigInteger orderId)
    {
        controller.resumeOrder(orderId);
    }

    public void completeOrder(BigInteger orderId)
    {
        controller.completeOrder(orderId);
    }

    public void deleteTemplate(BigInteger templateId)
    {
        controller.deleteTemplate(templateId);
    }

    public void deleteCustomer(BigInteger customerId)
    {
        controller.deleteCustomer(customerId);
    }

    public void deleteEmployee(BigInteger employeeId)
    {
        controller.deleteEmployee(employeeId);
    }

    public void deleteArea(BigInteger areaId)
    {
        controller.deleteArea(areaId);
    }

    public String showAll(String search, String... entities)
    {
        String result = "";

        for (String entity : entities)
        {
            if (entity != null)
            {
                switch (entity)
                {
                    case "Services":
                        result += "<h2>Services</h2><div class='table'>" + showAllServices(search, "all", null,"","") +
                                "</div>"; break;
                    case "Templates":
                        result += "<h2>Templates</h2><div class='table'>" + showAllTemplates(search, "all", null,null,null) +
                                "</div>"; break;
                    case "Orders":
                        result += "<h2>Orders</h2><div class='table'>" + showAllOrders(search, "all", null,"","","") +
                                "</div>"; break;
                    case "Customers":
                        result += "<h2>Customers</h2><div class='table'>" + showAllCustomers(search, "all", null,"","") +
                                "</div>"; break;
                    case "Areas":
                        result += "<h2>Areas</h2><div class='table'>" + showAllAreas(search, "all", null,"") +
                                "</div>"; break;
                    case "Employees":
                        result += "<h2>Employees</h2><div class='table'>" + showAllEmployees(search, "all", null,"") +
                                "</div>"; break;
                }
            }
        }

        return result;
    }

    public void deleteOrder(final BigInteger id)
    {
        controller.deleteOrder(id);
    }

    public void deleteService(final BigInteger id)
    {
        controller.deleteService(id);
    }
}
