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
import jsp.ejb.EmployeeSessionBean;

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
            String templateId, String serviceId, BigInteger eId, EmployeeSessionBean employeeSessionBean)
    {
        List<Order> orders = employeeSessionBean.getFilteredOrdersByEmployeeId(search, field, sortOrders,
                templateId, serviceId, eId);
        return htmlTableBuilder.createOrdersHtmlTable(orders, templateId, serviceId, eId.toString());
    }

    public String showFilteredOrders(String search, String field, OrderSortType sortOrders,
            String templateId, String serviceId, String employeeId, EmployeeSessionBean employeeSessionBean)
    {
        List<Order> orders = employeeSessionBean.getFilteredOrders(search, field, sortOrders,
                templateId, serviceId, employeeId);
        return htmlTableBuilder.createOrdersHtmlTable(orders, templateId, serviceId, employeeId);
    }

    public String showFilteredServices(String search, String field, ServiceSortType sortService,
            String name, String cost, EmployeeSessionBean employeeSessionBean)
    {
        List<Service> services = employeeSessionBean.getFilteredServices(search, field, sortService,
                name, cost);
        return htmlTableBuilder.createServicesHtmlTable(services, name, cost);
    }

    public String showFilteredTemplates(String search, String field, TemplateSortType sortTemplates,
            String name, String cost, EmployeeSessionBean employeeSessionBean)
    {
        List<Template> templates = employeeSessionBean.getFilteredTemplates(search, field, sortTemplates, name,
                cost);
        return htmlTableBuilder.createTemplatesHtmlTable(templates, name, cost);
    }

    public String showFilteredCustomers(String search, String field, CustomerSortType sortCustomers,
            String name, String area, EmployeeSessionBean employeeSessionBean)
    {
        List<Customer> customers = employeeSessionBean.getFilteredCustomers(search, field, sortCustomers,
                name, area);
        return htmlTableBuilder.createCustomersHtmlTable(customers, name, area);
    }


    public String showFilteredEmployees(String searchValue, String searchField, EmployeeSortType sortEmployees,
            String name, EmployeeSessionBean employeeSessionBean)
    {
        List<Employee> employees = employeeSessionBean.getFilteredEmployees(searchValue, searchField, sortEmployees,
                name);
        return htmlTableBuilder.createEmployeesHtmlTable(employees, name);
    }

    public String showFilteredAreas(String search, String field, AreaSortType sortAreas,
            String name, EmployeeSessionBean employeeSessionBean)
    {
        List<Area> areas = employeeSessionBean.getFilteredAreas(search, field, sortAreas,
                name);
        return htmlTableBuilder.createAreasHtmlTable(areas, name);
    }

    public String showAll(String search, EmployeeSessionBean employeeSessionBean, String... entities)
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
                                .append(showFilteredServices(search, "all", null, "", "", employeeSessionBean))
                                .append("</div>"); break;
                    case "Templates":
                        result.append("<h2>Templates</h2><div class='table'>")
                                .append(showFilteredTemplates(search, "all", null, null, null, employeeSessionBean))
                                .append("</div>"); break;
                    case "Orders":
                        result.append("<h2>Orders</h2><div class='table'>")
                                .append(showFilteredOrders(search, "all", null, "", "", "", employeeSessionBean))
                                .append("</div>"); break;
                    case "Customers":
                        result.append("<h2>Customers</h2><div class='table'>")
                                .append(showFilteredCustomers(search, "all", null, "", "", employeeSessionBean))
                                .append("</div>"); break;
                    case "Areas":
                        result.append("<h2>Areas</h2><div class='table'>")
                                .append(showFilteredAreas(search, "all", null, "", employeeSessionBean)).append("</div>"); break;
                    case "Employees":
                        result.append("<h2>Employees</h2><div class='table'>")
                                .append(showFilteredEmployees(search, "all", null, "", employeeSessionBean)).append("</div>"); break;
                }
            }
        }

        return result.toString();
    }
}
