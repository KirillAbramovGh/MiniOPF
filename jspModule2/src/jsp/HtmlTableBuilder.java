package jsp;

import com.netcracker.students.o3.controller.Controller;
import com.netcracker.students.o3.controller.ControllerImpl;
import com.netcracker.students.o3.model.area.Area;
import com.netcracker.students.o3.model.services.Service;
import com.netcracker.students.o3.model.services.ServiceStatus;
import com.netcracker.students.o3.model.templates.Template;

import java.math.BigInteger;
import java.util.Collection;
import java.util.List;

public class HtmlTableBuilder {

    private String cellStart = "<td align='center'>";
    private String cellEnd = "</td>";

    private String rowStart = "<tr>";
    private String rowEnd = "</tr>";
    private String[] colors = new String[]{"#ff0000","#808080"};

    private static HtmlTableBuilder instance;

    private HtmlTableBuilder() {
    }


    /**
     * add columns to table
     *
     * @param columnNames
     */
    private String addColumns(String... columnNames) {
        String result = "";
        result += rowStart;
        for (String columnName : columnNames) {
            result += "<th>" + columnName + "</th>";
        }
        result += rowEnd;
        return result;
    }


    /**
     * add empty row in order to add cells after
     *
     * @return
     */


    /**
     * add cell to row
     *
     * @param cell
     * @return
     */
    private String addCell(String cell) {
        String result = "";
        result += cellStart + cell + cellEnd;
        return result;
    }

    private String addCell(String cell, String color) {
        String startFont = "<font color='" + color + "'>";
        String endFont = "</font>";
        return addCell(startFont + cell + endFont);
    }

    /**
     * add button to table
     *
     * @param type
     * @param value
     * @param name
     */


    private String createButton(String type, String value, String name) {
        String buttonStart = "<input ";
        String buttonEnd = " />";
        return buttonStart + "type='" + type + "' value='" + value + "' name='" + name + "'" + buttonEnd;
    }

    private String createButton(String value, String name) {
        return createButton("submit", value, name);
    }

    private String addServiceToTable(Service service,String color) {
        String result = "<tr bgcolor='"+color+"'>";

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
        return result + rowEnd;
    }


    private String addTemplateToTable(Template template,String color) {
        String result = "<tr bgcolor='"+color+"'>";

        result += addCell(template.getName());
        result += addCell(template.getCost() + "");
        result += addCell(template.getDescription() + getAreasByTemplateId(template.getId()));
        result += addCell(createButton("connect", "connect" + template.getId()));

        return result + rowEnd;
    }

    /**
     * @return result html
     */
    private String build(String innerPart) {
        String start = "<table border='0' width='auto' cellpadding='20'>";
        String end = "</table>";
        return start + innerPart + end;
    }

    /**
     * create table from list of services
     *
     * @param services
     */
    public String createServicesTable(Collection<Service> services) {
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
            innerPart.append(addServiceToTable(service,colors[i]));

        }

        return build(innerPart.toString());
    }

    private int getNextColorNumber(int i){
        if(i==0){
            return 1;
        }
        return 0;
    }
    /**
     * create table from templates
     *
     * @param templates
     */
    public String createTemplatesTable(Collection<Template> templates) {
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
            innerPart.append(addTemplateToTable(t,colors[i]));
        }

        return build(innerPart.toString());
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

    public static HtmlTableBuilder getInstance() {
        if (instance == null) {
            instance = new HtmlTableBuilder();
        }

        return instance;
    }
}
