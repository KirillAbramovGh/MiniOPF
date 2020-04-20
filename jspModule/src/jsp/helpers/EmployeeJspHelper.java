package jsp.helpers;

import com.netcracker.students.o3.controller.sorters.SortType.AreaSortType;
import com.netcracker.students.o3.controller.sorters.SortType.CustomerSortType;
import com.netcracker.students.o3.controller.sorters.SortType.EmployeeSortType;
import com.netcracker.students.o3.controller.sorters.SortType.OrderSortType;
import com.netcracker.students.o3.controller.sorters.SortType.ServiceSortType;
import com.netcracker.students.o3.controller.sorters.SortType.TemplateSortType;
import com.netcracker.students.o3.model.Entity;
import com.netcracker.students.o3.model.area.Area;
import com.netcracker.students.o3.model.dto.DtoTransformer;
import com.netcracker.students.o3.model.dto.HtmlParser;
import com.netcracker.students.o3.model.orders.Order;
import com.netcracker.students.o3.model.orders.OrderImpl;
import com.netcracker.students.o3.model.services.Service;
import com.netcracker.students.o3.model.services.ServiceImpl;
import com.netcracker.students.o3.model.templates.Template;
import com.netcracker.students.o3.model.templates.TemplateImpl;
import com.netcracker.students.o3.model.users.Customer;
import com.netcracker.students.o3.model.users.Employee;

import java.math.BigInteger;
import java.util.List;

import jsp.builders.HtmlCheckboxBuilder;
import jsp.builders.HtmlTableBuilder;
import jsp.sessionBeans.EmployeeSessionBean;

public class EmployeeJspHelper
{
    private static EmployeeJspHelper instance;

    private HtmlTableBuilder htmlTableBuilder;


    private EmployeeJspHelper()
    {
        htmlTableBuilder = HtmlTableBuilder.getInstance();
    }

    public static EmployeeJspHelper getInstance()
    {
        if (instance == null)
        {
            instance = new EmployeeJspHelper();
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

    public String checkBocksEntities(EmployeeSessionBean sessionBean,String type){
        HtmlCheckboxBuilder checkboxBuilder = HtmlCheckboxBuilder.getInstance();
        String result = "error";
        switch (type){
            case "area":
                result = checkboxBuilder.makeCheckboxesFromAreas(sessionBean.getAreas()); break;
            case "customer":
                result = checkboxBuilder.makeCheckboxesFromCustomers(sessionBean.getCustomers()); break;
            case "template":
                result = checkboxBuilder.makeCheckboxesFromTemplates(sessionBean.getTemplates()); break;
            case "service":
                result = checkboxBuilder.makeCheckboxesFromServices(sessionBean.getServices()); break;
            case "employee":
                result = checkboxBuilder.makeCheckboxesFromEmployees(sessionBean.getEmployees()); break;
            case "order":
                result = checkboxBuilder.makeCheckboxesFromOrders(sessionBean.getOrders()); break;
        }

        return result;
    }

    public String transformAreasToHtml(List<Area> areas){
        StringBuilder res = new StringBuilder("</br>");
        DtoTransformer transformer = new DtoTransformer();
        HtmlParser parser = new HtmlParser();
       for(Area area : areas){
           res.append(parser.parseToHtml(transformer.transform(area))).append(",</br>");
       }
       return res.toString();
    }

    public String transformCustomersToHtml(List<Customer> customers){
        StringBuilder res = new StringBuilder("</br>");
        DtoTransformer transformer = new DtoTransformer();
        HtmlParser parser = new HtmlParser();
        for(Customer customer : customers){
            res.append(parser.parseToHtml(transformer.transform(customer))).append(",</br>");
        }
        return res.toString();
    }

    public String transformTemplatesToHtml(List<Template> templates){
        StringBuilder res = new StringBuilder("</br>");
        DtoTransformer transformer = new DtoTransformer();
        HtmlParser parser = new HtmlParser();
        for(Template template : templates){
            res.append(parser.parseToHtml(transformer.transform(template))).append(",</br>");
        }
        return res.toString();
    }

    public String transformEmployeesToHtml(List<Employee> employees){
        StringBuilder res = new StringBuilder("</br>");
        DtoTransformer transformer = new DtoTransformer();
        HtmlParser parser = new HtmlParser();
        for(Employee employee : employees){
            res.append(parser.parseToHtml(transformer.transform(employee))).append(",</br>");
        }
        return res.toString();
    }

    public String transformServicesToHtml(List<Service> services){
        StringBuilder res = new StringBuilder("</br>");
        DtoTransformer transformer = new DtoTransformer();
        HtmlParser parser = new HtmlParser();
        for(Service service : services){
            res.append(parser.parseToHtml(transformer.transform(service))).append(",</br>");

        }
        return res.toString();
    }

    public String transformOrdersToHtml(List<Order> orders){
        StringBuilder res = new StringBuilder("</br>");
        DtoTransformer transformer = new DtoTransformer();
        HtmlParser parser = new HtmlParser();
        for(Order order : orders){
            res.append(parser.parseToHtml(transformer.transform(order))).append(",</br>");
        }
        return res.toString();
    }

    public String getEntitiesEditForm(String type){
        switch (type){
            case "customer": return getCustomersEditForm();
            case "employee": return getEmployeesEditForm();
            case "template": return getTemplatesEditForm();
            case "service": return getServicesEditForm();
            case "order": return getOrdersEditForm();
            case "area": return getAreasEditForm();
        }
        return "";
    }

    public String getCustomersEditForm(){
        String res = "";

        res+= "<p>name " + createInput("name")+"</p>";
        res+= "<p>login " + createInput("login")+"</p>";
        res+= "<p>password " + createInput("password")+"</p>";
        res+= "<p>balance " + createInput("balance")+"</p>";

        return res;
    }

    public String getEmployeesEditForm(){
        String res = "";

        res+= "<p>name " + createInput("name")+"</p>";
        res+= "<p>login " + createInput("login")+"</p>";
        res+= "<p>password " + createInput("password")+"</p>";

        return res;
    }

    public String getAreasEditForm(){
        String res = "";

        res+= "<p>name " + createInput("name")+"</p>";
        res+= "<p>description " + createInput("description")+"</p>";

        return res;
    }

    public String getTemplatesEditForm(){
        String res = "";

        res+= "<p>name " + createInput("name")+"</p>";
        res+= "<p>description " + createInput("description")+"</p>";
        res+= "<p>cost " + createInput("cost")+"</p>";

        return res;
    }

    public String getServicesEditForm(){
        String res = "";
        res+= "<p>status " + "<select name=\"status\">" +
                "                <option>Planned</option>" +
                "                <option>Active</option>" +
                "                <option>Processing</option>" +
                "                <option>Disconnected</option>" +
                "                <option>Suspended</option>" +
                "            </select>"+"</p>";
        return res;
    }

    public String getOrdersEditForm(){
        String res = "";
        res+= "<p>action " + "<select name=\"action\">" +
                "            <option>New</option>" +
                "            <option>Disconnect</option>" +
                "            <option>Resume</option>" +
                "            <option>Suspend</option>" +
                "        </select>"+"</p>";
        res+= "<p>status " + "<select name=\"status\">" +
                "            <option>Entering</option>" +
                "            <option>Active</option>" +
                "            <option>Processing</option>" +
                "            <option>Disconnected</option>" +
                "        </select>"+"</p>";
        return res;
    }


    private String createInput(String name){
        return "<input type=\"text\" name=\""+name+"\" >";
    }
}
