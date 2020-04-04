package jsp;

import com.netcracker.students.o3.controller.sorters.SortType.AreaSortType;
import com.netcracker.students.o3.controller.sorters.SortType.CustomerSortType;
import com.netcracker.students.o3.controller.sorters.SortType.EmployeeSortType;
import com.netcracker.students.o3.controller.sorters.SortType.OrderSortType;
import com.netcracker.students.o3.controller.sorters.SortType.ServiceSortType;
import com.netcracker.students.o3.controller.sorters.SortType.TemplateSortType;
import com.netcracker.students.o3.model.area.Area;
import com.netcracker.students.o3.model.orders.Order;
import com.netcracker.students.o3.model.services.Service;
import com.netcracker.students.o3.model.templates.Template;
import com.netcracker.students.o3.model.users.Customer;
import com.netcracker.students.o3.model.users.Employee;

import java.math.BigInteger;
import java.util.List;

import jsp.builders.HtmlTableBuilder;
import jsp.ejb.EmployeeEJB;

public class EmployeeWebVisualiser
{
    private static EmployeeWebVisualiser instance;

    private HtmlTableBuilder htmlTableBuilder;


    private EmployeeWebVisualiser()
    {
        htmlTableBuilder = HtmlTableBuilder.getInstance();
    }

    public static EmployeeWebVisualiser getInstance()
    {
        if (instance == null)
        {
            instance = new EmployeeWebVisualiser();
        }
        return instance;
    }

    public String showFilteredOrdersByEmployeeId(String search, String field, OrderSortType sortOrders,
            String templateId, String serviceId, BigInteger eId, EmployeeEJB employeeEJB)
    {
        List<Order> orders = employeeEJB.getFilteredOrdersByEmployeeId(search, field, sortOrders,
                templateId, serviceId, eId);
        return htmlTableBuilder.createOrdersHtmlTable(orders, templateId, serviceId, eId.toString());
    }

    public String showFilteredOrders(String search, String field, OrderSortType sortOrders,
            String templateId, String serviceId, String employeeId, EmployeeEJB employeeEJB)
    {
        List<Order> orders = employeeEJB.getFilteredOrders(search, field, sortOrders,
                templateId, serviceId, employeeId);
        return htmlTableBuilder.createOrdersHtmlTable(orders, templateId, serviceId, employeeId);
    }

    public String showFilteredServices(String search, String field, ServiceSortType sortService,
            String name, String cost, EmployeeEJB employeeEJB)
    {
        List<Service> services = employeeEJB.getFilteredServices(search, field, sortService,
                name, cost);
        return htmlTableBuilder.createServicesHtmlTable(services, name, cost);
    }

    public String showFilteredTemplates(String search, String field, TemplateSortType sortTemplates,
            String name, String cost, EmployeeEJB employeeEJB)
    {
        List<Template> templates = employeeEJB.getFilteredTemplates(search, field, sortTemplates, name,
                cost);
        return htmlTableBuilder.createTemplatesHtmlTable(templates, name, cost);
    }

    public String showFilteredCustomers(String search, String field, CustomerSortType sortCustomers,
            String name, String area, EmployeeEJB employeeEJB)
    {
        List<Customer> customers = employeeEJB.getFilteredCustomers(search, field, sortCustomers,
                name, area);
        return htmlTableBuilder.createCustomersHtmlTable(customers, name, area);
    }


    public String showFilteredEmployees(String searchValue, String searchField, EmployeeSortType sortEmployees,
            String name, EmployeeEJB employeeEJB)
    {
        List<Employee> employees = employeeEJB.getFilteredEmployees(searchValue, searchField, sortEmployees,
                name);
        return htmlTableBuilder.createEmployeesHtmlTable(employees, name);
    }

    public String showFilteredAreas(String search, String field, AreaSortType sortAreas,
            String name, EmployeeEJB employeeEJB)
    {
        List<Area> areas = employeeEJB.getFilteredAreas(search, field, sortAreas,
                name);
        return htmlTableBuilder.createAreasHtmlTable(areas, name);
    }

    public String showAll(String search, EmployeeEJB employeeEJB, String... entities)
    {
        StringBuilder result = new StringBuilder();

        for (String entity : entities)
        {
            if (entity != null)
            {
                switch (entity)
                {
                    case "Services":
                        result.append("<h2>Services</h2><div class='table'>")
                                .append(showFilteredServices(search, "all", null, "", "", employeeEJB))
                                .append("</div>"); break;
                    case "Templates":
                        result.append("<h2>Templates</h2><div class='table'>")
                                .append(showFilteredTemplates(search, "all", null, null, null, employeeEJB))
                                .append("</div>"); break;
                    case "Orders":
                        result.append("<h2>Orders</h2><div class='table'>")
                                .append(showFilteredOrders(search, "all", null, "", "", "", employeeEJB))
                                .append("</div>"); break;
                    case "Customers":
                        result.append("<h2>Customers</h2><div class='table'>")
                                .append(showFilteredCustomers(search, "all", null, "", "", employeeEJB))
                                .append("</div>"); break;
                    case "Areas":
                        result.append("<h2>Areas</h2><div class='table'>")
                                .append(showFilteredAreas(search, "all", null, "", employeeEJB)).append("</div>"); break;
                    case "Employees":
                        result.append("<h2>Employees</h2><div class='table'>")
                                .append(showFilteredEmployees(search, "all", null, "", employeeEJB)).append("</div>"); break;
                }
            }
        }

        return result.toString();
    }
}
