package jsp;

import com.netcracker.students.o3.controller.Controller;
import com.netcracker.students.o3.controller.ControllerImpl;
import com.netcracker.students.o3.model.area.Area;
import com.netcracker.students.o3.model.orders.Order;
import com.netcracker.students.o3.model.services.Service;
import com.netcracker.students.o3.model.services.ServiceStatus;
import com.netcracker.students.o3.model.templates.Template;
import com.netcracker.students.o3.model.users.Customer;
import com.netcracker.students.o3.model.users.Employee;

import java.math.BigInteger;
import java.util.Collection;
import java.util.List;

public class HtmlTableBuilder {

    private String[] colors;

    private static HtmlTableBuilder instance;

    private HtmlTableBuilder() {
        colors = new String[]{"#ff0000", "#808080"};
    }


    public String createCustomerTemplatesTable(Collection<Template> templates) {
        StringBuilder innerPart = new StringBuilder();

        innerPart.append(addColumns("Name" +
                        createButton("↑", "TemplateSortUpByName") +
                        createButton("↓", "TemplateSortDownByName"),
                "Cost" +
                        createButton("↑", "TemplateSortUpByCost") +
                        createButton("↓", "TemplateSortDownByCost"), "Description", ""));

        int i = 1;
        for (Template t : templates) {
            i = getNextColorNumber(i);
            innerPart.append(addCustomerTemplateToTable(t, colors[i]));
        }

        return build(innerPart.toString());
    }

    public String createCustomerServicesTable(Collection<Service> services) {
        StringBuilder innerPart = new StringBuilder();

        innerPart.append(
                addColumns(
                        "Name" + createButton("↑", "ServiceSortUpByName") +
                                createButton("↓", "ServiceSortDownByName"),
                        "Cost" + createButton("↑", "ServiceSortUpByCost") +
                                createButton("↓", "ServiceSortDownByCost"), "Status", "", ""));


        int i = 1;
        for (Service service : services) {
            i = getNextColorNumber(i);
            innerPart.append(addCustomerServiceToTable(service, colors[i]));

        }

        return build(innerPart.toString());
    }



    public String createEmployeeServicesTable(Collection<Service> services) {
        String innerPart = "";

        innerPart +=
                addColumns(
                        "id"+createButton("↑","ServiceSortUpById")+
                                createButton("↓","ServiceSortDownById"),
                        "Name"+createButton("↑","ServiceSortUpByName")+
                                createButton("↓","ServiceSortDownByName"),
                        "Cost"+createButton("↑","ServiceSortUpByCost")+
                                createButton("↓","ServiceSortDownByCost"),
                        "Status", "TemplateId", "UserId",
                        "ActivationDate", "Areas");

        innerPart += addEmployeeServicesToTable(services);

        return build(innerPart);
    }

    public String createEmployeeTemplatesTable(Collection<Template> templates) {
        StringBuilder innerPart = new StringBuilder();

        innerPart.append(
                addColumns("Id"+createButton("↑","TemplateSortUpById")
                                +createButton("↓","TemplateSortDownById")
                        , "Name"+createButton("↑","TemplateSortUpByName")
                                +createButton("↓","TemplateSortDownByName")
                        , "Cost"+createButton("↑","TemplateSortUpByCost")
                                +createButton("↓","TemplateSortDownByCost")
                        , "Description", "Areas","")
        );

        int i = 1;
        for (Template t : templates) {
            i = getNextColorNumber(i);
            innerPart.append(addEmployeeTemplateToTable(t, colors[i]));
        }

        return build(innerPart.toString());
    }

    public String createOrdersTable(Collection<Order> orders) {
        StringBuilder innerPart = new StringBuilder();

        innerPart.append(addColumns(
                        "id"+createButton("↑","OrderSortUpById")
                +createButton("↓","OrderSortDownById")
                ,"TemplateId", "ServiceId", "EmployeeId", "Status", "Action", "CreationDate",""));
        int i = 1;
        for (Order order : orders) {
            i = getNextColorNumber(i);
            innerPart.append(addOrderToTable(order, colors[i]));
        }

        return build(innerPart.toString());
    }

    public String createCustomersTable(Collection<Customer> customers) {
        StringBuilder innerPart = new StringBuilder();

        innerPart.append(
                addColumns(
                        "Id"+createButton("↑","CustomerSortUpById")+
                                createButton("↓","CustomerSortDownById")
                        , "Name"+createButton("↑","CustomerSortUpByName")+
                                createButton("↓","CustomerSortDownByName")
                        , "Login"+createButton("↑","CustomerSortUpByLogin")+
                                createButton("↓","CustomerSortDownByLogin")
                        , "Password", "Area",
                        "Balance"+createButton("↑","CustomerSortUpByBalance")+
                                createButton("↓","CustomerSortDownByBalance")
                        , "ConnectedServices","")
        );

        int i = 1;
        for (Customer customer : customers) {
            i = getNextColorNumber(i);
            innerPart.append(addCustomerToTable(customer, colors[i]));
        }

        return build(innerPart.toString());
    }

    public String createEmployeesTable(Collection<Employee> employees) {
        StringBuilder innerPart = new StringBuilder();

        innerPart.append(
                addColumns(
                        "Id"+createButton("↑","EmployeeSortUpById")+
                                createButton("↓","EmployeeSortDownById")
                        , "Name"+createButton("↑","EmployeeSortUpByName")+
                                createButton("↓","EmployeeSortDownByName")
                        , "Login"+createButton("↑","EmployeeSortUpByLogin")+
                                createButton("↓","EmployeeSortDownByLogin")
                        , "Password","")
        );

        int i = 1;
        for (Employee employee : employees) {
            i = getNextColorNumber(i);
            innerPart.append(addEmployeeToTable(employee, colors[i]));
        }

        return build(innerPart.toString());
    }

    public String createAreasTable(Collection<Area> areas) {
        StringBuilder innerPart = new StringBuilder();

        innerPart.append(
                addColumns("Id"+createButton("↑","AreaSortUpById")+
                                createButton("↓","AreaSortDownById")
                        , "Name"+createButton("↑","AreaSortUpByName")+
                                createButton("↓","AreaSortDownByName")
                        , "Description"+createButton("↑","AreaSortUpByDescription")+
                                createButton("↓","AreaSortDownByDescription")
                        ,"")
        );

        int i = 1;
        for (Area area : areas) {
            i = getNextColorNumber(i);
            innerPart.append(addAreaToTable(area, colors[i]));
        }

        return build(innerPart.toString());
    }




    private String addEmployeeServicesToTable(Collection<Service> services) {
        StringBuilder innerPart = new StringBuilder();
        int i = 1;
        for (Service service : services) {
            i = getNextColorNumber(i);
            innerPart.append(addEmployeeServiceToTable(service, colors[i]));
        }
        return innerPart.toString();
    }

    private String addCustomerServiceToTable(Service service, String color) {
        String result = "<tr bgcolor='" + color + "'>";

        String name = service.getName();
        String cost = service.getCost() + "";
        String status = service.getStatus() + "";

        result += addCell(name + getAreasByTemplateId(service.getTemplateId()));
        result += addCell(cost);

        if (service.getStatus() == ServiceStatus.Entering) {
            result += addCell(status, "yellow");
            result += addCell(createButton("cancel", "disconnect" + service.getId()));
            result += addCell("");
        } else if (service.getStatus() == ServiceStatus.Active) {
            result += addCell(status, "green");
            result += addCell(createButton("disconnect", "disconnect" + service.getId()));
            result += addCell(createButton("suspend", "suspend" + service.getId()));
        } else if (service.getStatus() == ServiceStatus.Suspended) {
            result += addCell(status, "red");
            result += addCell(createButton("disconnect", "disconnect" + service.getId()));
            result += addCell(createButton("resume", "suspend" + service.getId()));
        }
        return result + "</tr>";
    }

    private String addCustomerTemplateToTable(Template template, String color) {
        String result = "<tr bgcolor='" + color + "'>";

        result += addCell(template.getName());
        result += addCell(template.getCost() + "");
        result += addCell(template.getDescription() + getAreasByTemplateId(template.getId()));
        result += addCell(createButton("connect", "connect" + template.getId()));

        return result + "</tr>";
    }

    private String addOrderToTable(Order order, String color) {
        String result = "<tr bgcolor='" + color + "'>";

        result += addCell(order.getId() + "");
        result += addCell(order.getTemplateId() + "");
        result += addCell(order.getServiceId() + "");
        result += addCell(order.getEmployeeId() + "");
        result += addCell(order.getStatus() + "");
        result += addCell(order.getAction() + "");
        result += addCell(order.getCreationDate() + "");

        switch (order.getStatus()){
            case Entering:result+=addCell(createButton("Start","startOrder"+order.getId()));break;
            case Suspended:result+=addCell(createButton("Resume","resumeOrder"+order.getId()));break;
            case Completed:result+=addCell("");break;
            case Processing:result+=addCell(createButton("Complete","completeOrder"+order.getId()));break;
        }

        return result + "</tr>";
    }

    private String addEmployeeServiceToTable(Service service, String color) {
        String result = "<tr bgcolor='" + color + "'>";

        result += addCell(service.getId() + "");
        result += addCell(service.getName());
        result += addCell(service.getCost() + "");
        result += addCell(service.getStatus() + "");
        result += addCell(service.getTemplateId() + "");
        result += addCell(service.getUserId() + "");
        result += addCell(service.getActivationDate() + "");
        result += addCell(getAreasByTemplateId(service.getTemplateId()));

        return result + "</tr>";
    }

    private String addEmployeeTemplateToTable(Template template, String color) {
        String result = "<tr bgcolor='" + color + "'>";

        result += addCell(template.getId() + "");
        result += addCell(template.getName() + "");
        result += addCell(template.getCost() + "");
        result += addCell(template.getDescription() + "");
        result += addCell(getAreasByTemplateId(template.getId()));
        result +=addCell(createButton("Delete","deleteTemplate"+template.getId()));

        return result + "</tr>";
    }

    private String addCustomerToTable(Customer customer, String color) {
        String result = "<tr bgcolor='" + color + "'>";

        result += addCell(customer.getId() + "");
        result += addCell(customer.getName() + "");
        result += addCell(customer.getLogin());
        result += addCell(customer.getPassword());
        result += addCell(customer.getAreaId() + "");
        result += addCell(customer.getMoneyBalance() + "");
        result += addCell(
                getCustomerConnectedServiceIds(customer)
        );
        result+=addCell(createButton("Delete","deleteCustomer"+customer.getId()));

        return result + "</tr>";
    }

    private String addEmployeeToTable(Employee employee, String color) {
        String result = "<tr bgcolor='" + color + "'>";

        result += addCell(employee.getId() + "");
        result += addCell(employee.getName() + "");
        result += addCell(employee.getLogin());
        result += addCell(employee.getPassword());
        result+=addCell(createButton("Delete","deleteEmployee"+employee.getId()));

        return result + "</tr>";
    }

    private String addAreaToTable(Area area, String color) {
        String result = "<tr bgcolor='" + color + "'>";

        result += addCell(area.getId() + "");
        result += addCell(area.getName() + "");
        result += addCell(area.getDescription());
        result += addCell(createButton("Delete","deleteArea"+area.getId()));

        return result + "</tr>";
    }


    private String addColumns(String... columnNames) {
        StringBuilder result = new StringBuilder();
        String rowStart = "<tr>";
        result.append(rowStart);
        for (String columnName : columnNames) {
            result.append("<th>").append(columnName).append("</th>");
        }
        result.append("</tr>");
        return result.toString();
    }

    private String addCell(String cell) {
        String result = "";
        String cellStart = "<td align='center'>";
        String cellEnd = "</td>";
        result += cellStart + cell + cellEnd;
        return result;
    }

    private String addCell(String cell, String color) {
        String startFont = "<font color='" + color + "'>";
        String endFont = "</font>";
        return addCell(startFont + cell + endFont);
    }


    private String createButton(String value, String name) {
        String buttonStart = "<input ";
        String buttonEnd = " />";
        return buttonStart + "type='" + "submit" + "' value='" + value + "' name='" + name + "'" + buttonEnd;
    }


    private String build(String innerPart) {
        String start = "<table border='0' width='auto' cellpadding='20'>";
        String end = "</table>";
        return start + innerPart + end;
    }


    private int getNextColorNumber(int i) {
        if (i == 0) {
            return 1;
        }
        return 0;
    }


    private String getAreasByTemplateId(BigInteger templateId) {
        StringBuilder result = new StringBuilder();

        Controller controller = ControllerImpl.getInstance();
        Template template = controller.getTemplate(templateId);

        List<BigInteger> areasId = template.getPossibleAreasId();
        Area area;
        for (BigInteger areaId : areasId) {
            area = controller.getArea(areaId);
            result.append(" ").append(area.getName());
        }

        return result.toString();
    }

    private String getCustomerConnectedServiceIds(Customer customer) {
        StringBuilder result = new StringBuilder();
        for (BigInteger id : customer.getConnectedServicesIds()) {
            result.append(id).append(",");
        }
        return result.toString();
    }

    public static HtmlTableBuilder getInstance() {
        if (instance == null) {
            instance = new HtmlTableBuilder();
        }

        return instance;
    }
}
