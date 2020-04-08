package jsp.ejb;

import com.netcracker.students.o3.controller.ControllerImpl;
import com.netcracker.students.o3.controller.searcher.EmployeeSearcher;
import com.netcracker.students.o3.controller.searcher.SearcherArea;
import com.netcracker.students.o3.controller.searcher.SearcherCustomer;
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

import javax.ejb.Stateless;

@Stateless
public class EmployeeSessionBean
{
    public Employee getEmployee(BigInteger employeeId)
    {
        return ControllerImpl.getInstance().getEmployee(employeeId);
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
        SearcherOrders searcherOrders = SearcherOrders.getInstance();
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
        SearcherOrders searcherOrders = SearcherOrders.getInstance();
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
        SearcherService searcherService = SearcherService.getInstance();
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
        SearcherTemplates searcherTemplates = SearcherTemplates.getInstance();
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
        SearcherCustomer searcherCustomer = SearcherCustomer.getInstance();
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
        SearcherArea searcherArea = SearcherArea.getInstance();
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
}
