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
import java.util.Set;

public class HtmlTableBuilder {

    private String[] colors;

    private static HtmlTableBuilder instance;
    private Controller controller = ControllerImpl.getInstance();

    private HtmlTableBuilder() {
        colors = new String[]{"#8c8888", "#5c5959"};
    }




    public String createServicesHtmlTable(Collection<Service> services,final String id, final String name,
            final String cost,final String status,final String templateId,final String customerId,final String activationDate,
            final String areas) {
        String innerPart = "";

        innerPart +=
                addColumns(new String[]{"","unique id", "name of service","cost of service","status of service show his state",
                        "id of template","id of customer","date when service was activated","areas where status is availabel","",""},
                        addCheckboxImg(),
                        "id"+createButton("↑","ServiceSortUpById")+
                                createButton("↓","ServiceSortDownById")+
                                createInput("filterServiceId",id),
                        "Name"+createButton("↑","ServiceSortDownByName")+
                                createButton("↓","ServiceSortUpByName")+
                        createInput("filterServiceName",name),
                        "Cost"+createButton("↑","ServiceSortDownByCost")+
                                createButton("↓","ServiceSortUpByCost")+
                        createInput("filterServiceCost",cost),
                        "Status"+createButton("↑","ServiceSortDownByStatus")+
                                createButton("↓","ServiceSortUpByStatus")+
                                createInput("filterServiceStatus",status),
                        "TemplateId"+createButton("↑","ServiceSortDownByTemplateId")+
                                createButton("↓","ServiceSortUpByTemplateId")+
                                createInput("filterServiceTemplateId",templateId),
                        "CustomerId"+createButton("↑","ServiceSortDownByCustomerId")+
                                createButton("↓","ServiceSortUpByCustomerId")+
                                createInput("filterServiceCustomerId",customerId),
                        "ActivationDate"+createButton("↑","ServiceSortDownByActivationDate")+
                                createButton("↓","ServiceSortUpByActivationDate")+
                                createInput("filterServiceActivationDate",activationDate)
                        , "Areas"+createButton("↑","ServiceSortDownByAreas")+
                                createButton("↓","ServiceSortUpByAreas")+
                                createInput("filterServiceAreas",areas)
                        ,createButton("filter","filter"),"");

        innerPart += addServicesToHtmlTable(services);

        return build(innerPart);
    }

    public String createTemplatesHtmlTable(Collection<Template> templates,final String id, final String name,
            final String cost,final String description) {
        StringBuilder innerPart = new StringBuilder();

        innerPart.append(
                addColumns(new String[]{"","unique id","name of template","cost of template","description of " +
                                "template","areas where template is available","",""},
                        addCheckboxImg(),
                        "Id"+createButton("↑","TemplateSortDownById")
                                +createButton("↓","TemplateSortUpById")
                                +createInput("filterTemplateId",id)
                        , "Name"+createButton("↑","TemplateSortDownByName")
                                +createButton("↓","TemplateSortUpByName")
                        +createInput("filterTemplateName",name)
                        , "Cost"+createButton("↑","TemplateSortDownByCost")
                                +createButton("↓","TemplateSortUpByCost")
                        +createInput("filterTemplateCost",cost)
                        , "Description"
                                +createButton("↑","TemplateSortDownByDescription")
                                +createButton("↓","TemplateSortUpByDescription")
                                +createInput("filterTemplateDescription",description)
                        , "Areas"
                                +createButton("↑","TemplateSortDownByAreas")
                                +createButton("↓","TemplateSortUpByAreas")
                                +createInput("filterTemplateAreas",description)
                        ,createButton("filter","filter"),"")
        );

        int i = 1;
        for (Template t : templates) {
            i = getNextColorNumber(i);
            innerPart.append(addEmployeeTemplateToHtmlTable(t, colors[i]));
        }

        return build(innerPart.toString());
    }

    public String createOrdersHtmlTable(Collection<Order> orders,final String id, final String templateId, final String serviceId,
            final String eId,final String status,final String action,final String creationDate) {
        StringBuilder innerPart = new StringBuilder();

        innerPart.append(addColumns(new String[]{"","unique id","id of template",
                "id of service","id of employee","status show state of order",
                "action show what need to do","date of creation",""},
                addCheckboxImg(),
                 "id"
                 +createButton("↑","OrderSortDownById")
                 +createButton("↓","OrderSortUpById")
                 +createInput("filterOrderId",id)
                ,"TemplateId"
                        +createButton("↑","OrderSortDownByTemplateId")
                        +createButton("↓","OrderSortUpByTemplateId")
                        +createInput("filterOrderTemplateId",templateId),
                "ServiceId"
                        +createButton("↑","OrderSortDownByServiceId")
                        +createButton("↓","OrderSortUpByServiceId")
                        +createInput("filterOrderServiceId",serviceId),
                "EmployeeId"
                        +createButton("↑","OrderSortDownByEmployeeId")
                        +createButton("↓","OrderSortUpByEmployeeId")
                        +createInput("filterOrderEmployeeId",eId),
                "Status"
                        +createButton("↑","OrderSortDownByStatus")
                        +createButton("↓","OrderSortUpByStatus")
                        +createInput("filterOrderStatus",status)
                , "Action"
                        +createButton("↑","OrderSortDownByAction")
                        +createButton("↓","OrderSortUpByAction")
                        +createInput("filterOrderAction",action)
                , "CreationDate"
                        +createButton("↑","OrderSortDownByCreationDate")
                        +createButton("↓","OrderSortUpByCreationDate")
                        +createInput("filterOrderCreationDate",creationDate)
                ,
                createButton("filter","filter")));
        int i = 1;
        for (Order order : orders) {
            i = getNextColorNumber(i);
            innerPart.append(addOrderToHtmlTable(order, colors[i]));
        }

        return build(innerPart.toString());
    }

    public String createCustomersHtmlTable(Collection<Customer> customers,final String id, final String name, final String area,
            final String login,final String password,final String balance,final String connectedServices) {
        StringBuilder innerPart = new StringBuilder();

        innerPart.append(
                addColumns(new String[]{"","unique id","name of customer","login of customer",
                        "password of customer","area of customer","balance of customer","list of connected ids","",""},
                        addCheckboxImg(),
                        "Id"
                                +createButton("↑","CustomerSortDownById")+
                                createButton("↓","CustomerSortUpById")
                                +createInput("filterCustomerId",id)
                        , "Name"
                                +createButton("↑","CustomerSortDownByName")
                                +createButton("↓","CustomerSortUpByName")
                                +createInput("filterCustomerName",name)
                        , "Login"
                                +createButton("↑","CustomerSortDownByLogin")
                                +createButton("↓","CustomerSortUpByLogin")
                                +createInput("filterCustomerLogin",login)
                        , "Password"
                                +createButton("↑","CustomerSortDownByPassword")
                                +createButton("↓","CustomerSortUpByPassword")
                                +createInput("filterCustomerLogin",password)
                        , "Area"
                                +createButton("↑","CustomerSortDownByArea")
                                +createButton("↓","CustomerSortUpByArea")
                                +createInput("filterCustomerArea",area),
                        "Balance"
                                +createButton("↑","CustomerSortDownByBalance")
                                +createButton("↓","CustomerSortUpByBalance")
                                +createInput("filterCustomerBalance",balance)
                        , "ConnectedServices"
                                +createButton("↑","CustomerSortDownByConnectedServices")
                                +createButton("↓","CustomerSortUpByConnectedServices")
                                +createInput("filterCustomerConnectedServices",connectedServices)
                        ,createButton("filter","filter"),"")
        );

        int i = 1;
        for (Customer customer : customers) {
            i = getNextColorNumber(i);
            innerPart.append(addCustomerToHtmlTable(customer, colors[i]));
        }

        return build(innerPart.toString());
    }

    public String createEmployeesHtmlTable(Collection<Employee> employees,final String id, final String name,
            final String login,final String password) {
        StringBuilder innerPart = new StringBuilder();

        innerPart.append(
                addColumns(new String[]{"","unique id","name of employee","login of employee",
                        "login of employee","password of employee","",""},
                        addCheckboxImg(),
                        "Id"
                                +createButton("↑","EmployeeSortDownById")
                                +createButton("↓","EmployeeSortUpById")
                                +createInput("filterEmployeeId",id)
                        , "Name"
                                +createButton("↑","EmployeeSortDownByName")
                                +createButton("↓","EmployeeSortUpByName")
                                +createInput("filterEmployeeName",name)
                        , "Login"
                                +createButton("↑","EmployeeSortDownByLogin")
                                +createButton("↓","EmployeeSortUpByLogin")
                                +createInput("filterEmployeeLogin",login)
                        , "Password"
                                +createButton("↑","EmployeeSortDownByPassword")
                                +createButton("↓","EmployeeSortUpByPassword")
                                +createInput("filterEmployeePassword",password)
                        ,createButton("filter","filter"),"")
        );

        int i = 1;
        for (Employee employee : employees) {
            i = getNextColorNumber(i);
            innerPart.append(addEmployeeToHtmlTable(employee, colors[i]));
        }

        return build(innerPart.toString());
    }

    public String createAreasHtmlTable(Collection<Area> areas,final String id, final String name,final String description) {
        StringBuilder innerPart = new StringBuilder();

        innerPart.append(
                addColumns(new String[]{"","unique id","area name","area description","",""},
                        addCheckboxImg(),
                        "Id"
                                +createButton("↑","AreaSortDownById")
                                +createButton("↓","AreaSortUpById")
                                +createInput("filterAreaId",id)
                        , "Name"
                                +createButton("↑","AreaSortDownByName")
                                +createButton("↓","AreaSortUpByName")
                                +createInput("filterAreaName",name)
                        , "Description"
                                +createButton("↑","AreaSortDownByDescription")
                                +createButton("↓","AreaSortUpByDescription")
                                +createInput("filterAreaDescription",description)
                        ,createButton("filter","filter"),"")
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

        result += addCell(createCheckbox("orderChecked "+order.getId(),
                "orderChecked "+order.getId()));
        result += addCell(addId(order.getId()));
        result += addCell(addId(order.getTemplate().getId()));
        result += addCell(addId(order.getService().getId()));
        if(order.getEmployee()!=null)
        {
            result += addCell(addId(order.getEmployee().getId()));
        }else {
            result += addCell(addId(BigInteger.ZERO));
        }
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

        result += addCell(createCheckbox("serviceChecked "+service.getId(),
                "serviceChecked "+service.getId()));
        result += addCell(addId(service.getId()));
        result += addCell(controller.getServiceName(service.getId()));
        result += addCell(service.templateGetCost() + "");
        result += addCell(service.getStatus() + "");
        result += addCell(addId(service.getTemplate().getId()));
        result += addCell(addId(service.getCustomer().getId()));
        result += addCell(service.getActivationDate() + "");
        result += addCell(getAreasByTemplateId(service.getTemplate().getId()));
        result += addCell(createButton("Delete","deleteService",service.getId().toString()));
        result += addCell(createButton("Update","updateService",service.getId().toString()));


        return result + "</tr>";
    }

    private String addEmployeeTemplateToHtmlTable(Template template, String color) {
        String result = "<tr bgcolor='" + color + "'>";

        result += addCell(createCheckbox("templateChecked "+template.getId(),
                "templateChecked "+template.getId()));
        result += addCell(addId(template.getId()));
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

        result += addCell(createCheckbox("customerChecked "+customer.getId(),
                "customerChecked "+customer.getId()));
        result += addCell(addId(customer.getId()));
        result += addCell(customer.getName() + "");
        result += addCell(customer.getLogin());
        result += addCell(customer.getPassword());
        result += addCell(addId(customer.getArea().getId()));
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

        result += addCell(createCheckbox("employeeChecked "+employee.getId(),
                "employeeChecked "+employee.getId()));
        result += addCell(addId(employee.getId()));
        result += addCell(employee.getName() + "");
        result += addCell(employee.getLogin());
        result += addCell(employee.getPassword());
        result+=addCell(createButton("Delete","deleteEmployee",employee.getId().toString()));
        result+=addCell(createButton("Update","updateEmployee",employee.getId().toString()));

        return result + "</tr>";
    }

    private String addAreaToHtmlTable(Area area, String color) {
        String result = "<tr bgcolor='" + color + "'>";

        result += addCell(createCheckbox("areaChecked "+area.getId(),
                "areaChecked "+area.getId()));
        result += addCell(addId(area.getId()));
        result += addCell(area.getName() + "");
        result += addCell(area.getDescription());
        result += addCell(createButton("Delete","deleteArea",area.getId().toString()));
        result += addCell(createButton("Update","updateArea",area.getId().toString()));

        return result + "</tr>";
    }


    private String addColumns(String[] titles,String... columnNames) {
        StringBuilder result = new StringBuilder();
        String rowStart = "<tr class='tableHeader'>";
        result.append(rowStart);
        int i = 0;
        for (String columnName : columnNames) {
            result.append("<th title='"+titles[i]+"'>").append(columnName).append("</th>");
            i++;
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
        String start = "<table cellpadding='10' class='table'>";
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

        List<Area> areas = template.getPossibleAreas();

        for (Area area : areas) {
            result.append(" ").append(area.getName());
        }

        return result.toString();
    }

    private String getCustomerConnectedServiceIds(Customer customer) {
        StringBuilder result = new StringBuilder();
        Set<Service> services = customer.getConnectedServices();
        int i = 0;
        for (Service service : services) {
            if(i!=services.size()-1)
            {
                result.append(addId(service.getId())).append(",");
            }else {
                result.append(addId(service.getId()));
            }
            i++;
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

    private String createCheckbox(String name,String value){
        return  "<input type=\"checkbox\" name=\""+name+"\" value=\""+value+"\">";
    }

    private String addId(BigInteger id){
        String start = "<a href='http://localhost:8080/jspModule_war_exploded/JSONVisual.jsp?entityId=";
        String mid = "' target=\"_blank\">";
        String close = "</a>";

        return start+id+mid+id+close;
    }
    public static HtmlTableBuilder getInstance() {
        if (instance == null) {
            instance = new HtmlTableBuilder();
        }

        return instance;
    }

    public String addCheckboxImg(){
        return "<img src='resources/checkbox.jpg'/>";
    }
}