package jsp.builders;

import com.netcracker.students.o3.controller.Controller;
import com.netcracker.students.o3.controller.ControllerImpl;
import com.netcracker.students.o3.model.area.Area;
import com.netcracker.students.o3.model.orders.Order;
import com.netcracker.students.o3.model.services.Service;
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





    public String createServicesHtmlTable(Collection<Service> services, final String name,
            final String cost) {
        String innerPart = "";

        innerPart +=
                addColumns(
                        "id"+createButton("↑","ServiceSortUpById")+
                                createButton("↓","ServiceSortDownById"),
                        "Name"+createButton("↑","ServiceSortDownByName")+
                                createButton("↓","ServiceSortUpByName")+
                        createInput("filterServiceName",name),
                        "Cost"+createButton("↑","ServiceSortDownByCost")+
                                createButton("↓","ServiceSortUpByCost")+
                        createInput("filterServiceCost",cost),
                        "Status",
                        "TemplateId",
                        "UserId",
                        "ActivationDate", "Areas","","");

        innerPart += addServicesToHtmlTable(services);

        return build(innerPart);
    }

    public String createTemplatesHtmlTable(Collection<Template> templates, final String name,
            final String cost) {
        StringBuilder innerPart = new StringBuilder();

        innerPart.append(
                addColumns("Id"+createButton("↑","TemplateSortDownById")
                                +createButton("↓","TemplateSortUpById")
                        , "Name"+createButton("↑","TemplateSortDownByName")
                                +createButton("↓","TemplateSortUpByName")
                        +createInput("filterTemplateName",name)
                        , "Cost"+createButton("↑","TemplateSortDownByCost")
                                +createButton("↓","TemplateSortUpByCost")
                        +createInput("filterTemplateCost",cost)
                        , "Description", "Areas","","")
        );

        int i = 1;
        for (Template t : templates) {
            i = getNextColorNumber(i);
            innerPart.append(addEmployeeTemplateToHtmlTable(t, colors[i]));
        }

        return build(innerPart.toString());
    }

    public String createOrdersHtmlTable(Collection<Order> orders, final String templateId, final String serviceId,
            final String eId) {
        StringBuilder innerPart = new StringBuilder();

        innerPart.append(addColumns(
                        "id"+createButton("↑","OrderSortDownById")
                +createButton("↓","OrderSortUpById")
                ,"TemplateId"+createInput("filterOrderTemplateId",templateId),
                "ServiceId"+createInput("filterOrderServiceId",serviceId),
                "EmployeeId"+createInput("filterOrderEmployeeId",eId), "Status", "Action", "CreationDate",""));
        int i = 1;
        for (Order order : orders) {
            i = getNextColorNumber(i);
            innerPart.append(addOrderToHtmlTable(order, colors[i]));
        }

        return build(innerPart.toString());
    }

    public String createCustomersHtmlTable(Collection<Customer> customers, final String name, final String area) {
        StringBuilder innerPart = new StringBuilder();

        innerPart.append(
                addColumns(
                        "Id"+createButton("↑","CustomerSortDownById")+
                                createButton("↓","CustomerSortUpById")
                        , "Name"+createButton("↑","CustomerSortDownByName")+
                                createButton("↓","CustomerSortUpByName")
                        +createInput("filterCustomerName",name)
                        , "Login"+createButton("↑","CustomerSortDownByLogin")+
                                createButton("↓","CustomerSortUpByLogin")
                        , "Password", "Area"+createInput("filterCustomerArea",area),
                        "Balance"+createButton("↑","CustomerSortDownByBalance")+
                                createButton("↓","CustomerSortUpByBalance")
                        , "ConnectedServices","","")
        );

        int i = 1;
        for (Customer customer : customers) {
            i = getNextColorNumber(i);
            innerPart.append(addCustomerToHtmlTable(customer, colors[i]));
        }

        return build(innerPart.toString());
    }

    public String createEmployeesHtmlTable(Collection<Employee> employees, final String name) {
        StringBuilder innerPart = new StringBuilder();

        innerPart.append(
                addColumns(
                        "Id"+createButton("↑","EmployeeSortDownById")+
                                createButton("↓","EmployeeSortUpById")
                        , "Name"+createButton("↑","EmployeeSortDownByName")+
                                createButton("↓","EmployeeSortUpByName")
                        +createInput("filterEmployeeName",name)
                        , "Login"+createButton("↑","EmployeeSortDownByLogin")+
                                createButton("↓","EmployeeSortUpByLogin")
                        , "Password","","")
        );

        int i = 1;
        for (Employee employee : employees) {
            i = getNextColorNumber(i);
            innerPart.append(addEmployeeToHtmlTable(employee, colors[i]));
        }

        return build(innerPart.toString());
    }

    public String createAreasHtmlTable(Collection<Area> areas, final String name) {
        StringBuilder innerPart = new StringBuilder();

        innerPart.append(
                addColumns("Id"+createButton("↑","AreaSortDownById")+
                                createButton("↓","AreaSortUpById")
                        , "Name"+createButton("↑","AreaSortDownByName")+
                                createButton("↓","AreaSortUpByName")
                        +createInput("filterAreaName",name)
                        , "Description"+createButton("↑","AreaSortDownByDescription")+
                                createButton("↓","AreaSortUpByDescription")
                        ,"","")
        );

        int i = 1;
        for (Area area : areas) {
            i = getNextColorNumber(i);
            innerPart.append(addAreaToHtmlTable(area, colors[i]));
        }

        return build(innerPart.toString());
    }






    private String addServicesToHtmlTable(Collection<Service> services) {
        StringBuilder innerPart = new StringBuilder();
        int i = 1;
        for (Service service : services) {
            i = getNextColorNumber(i);
            innerPart.append(addEmployeeServiceToHtmlTable(service, colors[i]));
        }
        return innerPart.toString();
    }




    private String addOrderToHtmlTable(Order order, String color) {
        String result = "<tr bgcolor='" + color + "'>";

        result += addCell("<a href='http://localhost:8080/jspModule_war_exploded/JSONVisual.jsp?entityId="+order.getId()+
                "' target=\"_blank\">"+order.getId() + "</a>");
        result += addCell(order.getTemplateId() + "");
        result += addCell(order.getServiceId() + "");
        result += addCell(order.getEmployeeId() + "");
        result += addCell(order.getStatus() + "");
        result += addCell(order.getAction() + "");
        result += addCell(order.getCreationDate() + "");

        String buttons = "";
        switch (order.getStatus()){
            case Entering:buttons+=createButton("Start","startOrder",order.getId().toString());break;
            case Suspended:
                buttons+=createButton("BackToEntering","cancelOrder",order.getId().toString());
                buttons+=createButton("Resume","resumeOrder",order.getId().toString());break;
            case Completed:break;
            case Processing:
                buttons+=createButton("BackToEntering","cancelOrder",order.getId().toString());
                buttons+=createButton("Complete","completeOrder",order.getId().toString());break;
        }
        buttons+= createButton("Delete","deleteOrder",order.getId().toString())+
                createButton("Update","updateOrder",order.getId().toString());
        result += addCell(buttons);

        return result + "</tr>";
    }

    private String addEmployeeServiceToHtmlTable(Service service, String color) {
        String result = "<tr bgcolor='" + color + "'>";

        result += addCell("<a href='http://localhost:8080/jspModule_war_exploded/JSONVisual.jsp?entityId="+service.getId()+"' target=\"_blank\">"+service.getId() + "</a>");
        result += addCell(controller.getServiceName(service.getId()));
        result += addCell(service.getCost() + "");
        result += addCell(service.getStatus() + "");
        result += addCell(service.getTemplateId() + "");
        result += addCell(service.getUserId() + "");
        result += addCell(service.getActivationDate() + "");
        result += addCell(getAreasByTemplateId(service.getTemplateId()));
        result += addCell(createButton("Delete","deleteService",service.getId().toString()));
        result += addCell(createButton("Update","updateService",service.getId().toString()));


        return result + "</tr>";
    }

    private String addEmployeeTemplateToHtmlTable(Template template, String color) {
        String result = "<tr bgcolor='" + color + "'>";

        result += addCell("<a href='http://localhost:8080/jspModule_war_exploded/JSONVisual.jsp?entityId="+template.getId()+"' target=\"_blank\">"+template.getId() + "</a>");
        result += addCell(template.getName() + "");
        result += addCell(template.getCost() + "");
        result += addCell(template.getDescription() + "");
        result += addCell(getAreasByTemplateId(template.getId()));
        result +=addCell(createButton("Delete","deleteTemplate",template.getId().toString()));
        result += addCell(createButton("Update","updateTemplate",template.getId().toString()));

        return result + "</tr>";
    }

    private String addCustomerToHtmlTable(Customer customer, String color) {
        String result = "<tr bgcolor='" + color + "'>";

        result += addCell("<a href='http://localhost:8080/jspModule_war_exploded/JSONVisual.jsp?entityId="+customer.getId()+"' target=\"_blank\">"+customer.getId() + "</a>");
        result += addCell(customer.getName() + "");
        result += addCell(customer.getLogin());
        result += addCell(customer.getPassword());
        result += addCell(customer.getAreaId() + "");
        result += addCell(customer.getMoneyBalance() + "");
        result += addCell(
                getCustomerConnectedServiceIds(customer)
        );
        result+=addCell(createButton("Delete","deleteCustomer",customer.getId().toString()));
        result+=addCell(createButton("Update","updateCustomer",customer.getId().toString()));


        return result + "</tr>";
    }

    private String addEmployeeToHtmlTable(Employee employee, String color) {
        String result = "<tr bgcolor='" + color + "'>";

        result += addCell("<a href='http://localhost:8080/jspModule_war_exploded/JSONVisual.jsp?entityId="+employee.getId()+"' target=\"_blank\">"+employee.getId() + "</a>");
        result += addCell(employee.getName() + "");
        result += addCell(employee.getLogin());
        result += addCell(employee.getPassword());
        result+=addCell(createButton("Delete","deleteEmployee",employee.getId().toString()));
        result+=addCell(createButton("Update","updateEmployee",employee.getId().toString()));

        return result + "</tr>";
    }

    private String addAreaToHtmlTable(Area area, String color) {
        String result = "<tr bgcolor='" + color + "'>";

        result += addCell("<a href='http://localhost:8080/jspModule_war_exploded/JSONVisual.jsp?entityId="+area.getId()+
                "' target=\"_blank\">"+area.getId() + "</a>");
        result += addCell(area.getName() + "");
        result += addCell(area.getDescription());
        result += addCell(createButton("Delete","deleteArea",area.getId().toString()));
        result += addCell(createButton("Update","updateArea",area.getId().toString()));

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

    private String createInput(String name){
        return "<input type=\"text\" name=\""+name+"\">";
    }

    private String createInput(String name,String value){
        if (value == null){
            value = "";
        }
        return "<input type=\"text\" name=\""+name+"\" value=\""+value+"\">";
    }
    public static HtmlTableBuilder getInstance() {
        if (instance == null) {
            instance = new HtmlTableBuilder();
        }

        return instance;
    }
}