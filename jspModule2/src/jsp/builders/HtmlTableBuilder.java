package jsp.builders;

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
    private Controller controller = ControllerImpl.getInstance();

    private HtmlTableBuilder() {
        colors = new String[]{"#8c8888", "#5c5959"};
    }


    public String createCustomerTemplatesTable(Collection<Template> templates) {
        StringBuilder innerPart = new StringBuilder();

        innerPart.append(addColumns("Name" +
                        createButton("↑", "TemplateSortDownByName") +
                        createButton("↓", "TemplateSortUpByName"),
                "Cost" +
                        createButton("↑", "TemplateSortDownByCost") +
                        createButton("↓", "TemplateSortUpByCost"), "Description", ""));

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
                        "Name" + createButton("↑", "ServiceSortDownByName") +
                                createButton("↓", "ServiceSortUpByName"),
                        "Cost" + createButton("↑", "ServiceSortDownByCost") +
                                createButton("↓", "ServiceSortUpByCost"), "Status", "", ""));


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
                        "Name"+createButton("↑","ServiceSortDownByName")+
                                createButton("↓","ServiceSortUpByName"),
                        "Cost"+createButton("↑","ServiceSortDownByCost")+
                                createButton("↓","ServiceSortUpByCost"),
                        "Status", "TemplateId", "UserId",
                        "ActivationDate", "Areas");

        innerPart += addEmployeeServicesToTable(services);

        return build(innerPart);
    }

    public String createEmployeeTemplatesTable(Collection<Template> templates) {
        StringBuilder innerPart = new StringBuilder();

        innerPart.append(
                addColumns("Id"+createButton("↑","TemplateSortDownById")
                                +createButton("↓","TemplateSortUpById")
                        , "Name"+createButton("↑","TemplateSortDownByName")
                                +createButton("↓","TemplateSortUpByName")
                        , "Cost"+createButton("↑","TemplateSortDownByCost")
                                +createButton("↓","TemplateSortUpByCost")
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
                        "id"+createButton("↑","OrderSortDownById")
                +createButton("↓","OrderSortUpById")
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
                        "Id"+createButton("↑","CustomerSortDownById")+
                                createButton("↓","CustomerSortUpById")
                        , "Name"+createButton("↑","CustomerSortDownByName")+
                                createButton("↓","CustomerSortUpByName")
                        , "Login"+createButton("↑","CustomerSortDownByLogin")+
                                createButton("↓","CustomerSortUpByLogin")
                        , "Password", "Area",
                        "Balance"+createButton("↑","CustomerSortDownByBalance")+
                                createButton("↓","CustomerSortUpByBalance")
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
                        "Id"+createButton("↑","EmployeeSortDownById")+
                                createButton("↓","EmployeeSortUpById")
                        , "Name"+createButton("↑","EmployeeSortDownByName")+
                                createButton("↓","EmployeeSortUpByName")
                        , "Login"+createButton("↑","EmployeeSortDownByLogin")+
                                createButton("↓","EmployeeSortUpByLogin")
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
                addColumns("Id"+createButton("↑","AreaSortDownById")+
                                createButton("↓","AreaSortUpById")
                        , "Name"+createButton("↑","AreaSortDownByName")+
                                createButton("↓","AreaSortUpByName")
                        , "Description"+createButton("↑","AreaSortDownByDescription")+
                                createButton("↓","AreaSortUpByDescription")
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

        String name = controller.getServiceName(service.getId());
        String cost = service.getCost() + "";
        String status = service.getStatus() + "";

        result += addCell(name + getAreasByTemplateId(service.getTemplateId()));
        result += addCell(cost);

        if (service.getStatus() == ServiceStatus.Entering) {
            result += addCell(status, "yellow");
            result += addCell(createButton("cancel", "disconnect" , service.getId().toString()));
            result += addCell("");
        } else if (service.getStatus() == ServiceStatus.Active) {
            result += addCell(status, "green");
            result += addCell(createButton("disconnect", "disconnect" , service.getId().toString()));
            result += addCell(createButton("suspend", "suspend" , service.getId().toString()));
        } else if (service.getStatus() == ServiceStatus.Suspended) {
            result += addCell(status, "red");
            result += addCell(createButton("disconnect", "disconnect" , service.getId().toString()));
            result += addCell(createButton("resume", "resume" , service.getId().toString()));
        }
        return result + "</tr>";
    }

    private String addCustomerTemplateToTable(Template template, String color) {
        String result = "<tr bgcolor='" + color + "'>";

        result += addCell(template.getName());
        result += addCell(template.getCost() + "");
        result += addCell(template.getDescription() + getAreasByTemplateId(template.getId()));
        result += addCell(createButton("connect", "connect" , template.getId().toString()));

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
            case Entering:result+=addCell(createButton("Start","startOrder",order.getId().toString()));break;
            case Suspended:result+=addCell(createButton("Resume","resumeOrder",order.getId().toString()));break;
            case Completed:result+=addCell("");break;
            case Processing:result+=addCell(createButton("Complete","completeOrder",order.getId().toString()));break;
        }

        return result + "</tr>";
    }

    private String addEmployeeServiceToTable(Service service, String color) {
        String result = "<tr bgcolor='" + color + "'>";

        result += addCell(service.getId() + "");
        result += addCell(controller.getServiceName(service.getId()));
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
        result +=addCell(createButton("Delete","deleteTemplate",template.getId().toString()));

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
        result+=addCell(createButton("Delete","deleteCustomer",customer.getId().toString()));

        return result + "</tr>";
    }

    private String addEmployeeToTable(Employee employee, String color) {
        String result = "<tr bgcolor='" + color + "'>";

        result += addCell(employee.getId() + "");
        result += addCell(employee.getName() + "");
        result += addCell(employee.getLogin());
        result += addCell(employee.getPassword());
        result+=addCell(createButton("Delete","deleteEmployee",employee.getId().toString()));

        return result + "</tr>";
    }

    private String addAreaToTable(Area area, String color) {
        String result = "<tr bgcolor='" + color + "'>";

        result += addCell(area.getId() + "");
        result += addCell(area.getName() + "");
        result += addCell(area.getDescription());
        result += addCell(createButton("Delete","deleteArea",area.getId().toString()));

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

    private String createButton(String value, String name,String id) {
        return createButton(value,name+" "+id);
    }


    private String build(String innerPart) {
        String start = "<table border='0' width='auto' cellpadding='20' class='table'>";
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