package jsp;

import com.netcracker.students.o3.controller.ControllerImpl;
import com.netcracker.students.o3.model.services.Service;
import com.netcracker.students.o3.model.services.ServiceStatus;
import com.netcracker.students.o3.model.templates.Template;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class HtmlTableBuilder {
    private String start = "<table border='0' width='auto' cellpadding='20'>";
    private String end = "</table>";

    private String cellStart = "<td align='center'>";
    private String cellEnd = "</td>";

    private String rowStart = "<tr>";
    private String rowEnd = "</tr>";

    private String buttonStart = "<input ";
    private String buttonEnd = " />";

    private String currentColor = "red";

    private List<String> columns;

    private String innerPart = "";

    public HtmlTableBuilder() {
        columns = new ArrayList<>();
    }

    /**
     * add columns to table
     *
     * @param columnNames
     */
    public void addColumns(String... columnNames) {
        columns.addAll(Arrays.asList(columnNames));

        innerPart += rowStart;
        for (String columnName : columnNames) {
            innerPart += "<th>" + columnName + "</th>";
        }
        innerPart += rowEnd;
    }


    /**
     * add empty row in order to add cells after
     *
     * @return
     */
    public HtmlTableBuilder addRow() {
        innerPart += rowStart;
        return this;
    }

    /**
     * add cell to row
     *
     * @param cell
     * @return
     */
    public HtmlTableBuilder addCell(String cell) {
        innerPart += cellStart + cell + cellEnd;
        return this;
    }

    /**
     * add button to table
     *
     * @param type
     * @param value
     * @param name
     * @return
     */
    public HtmlTableBuilder addButton(String type, String value, String name) {
        innerPart +=
                cellStart + buttonStart + "type='" + type + "' value='" + value + "' name='" + name + "'" + buttonEnd +
                        cellEnd;
        return this;
    }

    public HtmlTableBuilder addButton(String value, String name) {
        addButton("submit", value, name);
        return this;
    }

    /**
     * mean end of row
     *
     * @return
     */
    public HtmlTableBuilder builtRow() {
        innerPart += rowEnd;
        return this;
    }

    /**
     * @return result html
     */
    public String build() {
        String resultHtml = start + innerPart + end;
        return resultHtml;
    }

    /**
     * create table from list of services
     *
     * @param services
     */
    public void createServicesTable(Collection<Service> services) {

        String btnUpName = "<input type='submit' name='ServiceSortUpByName' value='↑'>";
        String btnDownName = "<input type='submit' name='ServiceSortDownByName' value='↓'>";
        String btnUpCost = "<input type='submit' name='ServiceSortUpByCost' value='↑'>";
        String btnDownCost = "<input type='submit' name='ServiceSortDownByCost' value='↓'>";

        addColumns("Name"+btnUpName+btnDownName, "Cost"+btnUpCost+btnDownCost, "Status", "", "");

        String name;
        String cost;
        String status;

        for (Service service : services) {
            name = ControllerImpl.getInstance().getService(service.getId()).getName();
            cost = service.getCost() + "";
            status = service.getStatus() + "";

            addRow().addCell(name).addCell(cost).addCell(status);

            if (service.getStatus() == ServiceStatus.Entering) {
                addButton("cancel", "disconnect" + service.getId());
                addCell("");
            } else if (service.getStatus() == ServiceStatus.Active) {
                addButton("disconnect", "disconnect" + service.getId());
                addButton("suspend", "suspend" + service.getId());
            } else if (service.getStatus() == ServiceStatus.Suspended) {
                addButton("disconnect", "disconnect" + service.getId());
                addButton("resume", "suspend" + service.getId());
            }
            builtRow();
        }
    }

    /**
     * create table from templates
     *
     * @param templates
     */
    public void createTemplatesTable(Collection<Template> templates) {
        String btnUpName = "<input type='submit' name='TemplateSortUpByName' value='↑'>";
        String btnDownName = "<input type='submit' name='TemplateSortDownByName' value='↓'>";
        String btnUpCost = "<input type='submit' name='TemplateSortUpByCost' value='↑'>";
        String btnDownCost = "<input type='submit' name='TemplateSortDownByCost' value='↓'>";

        addColumns("Name"+btnUpName+btnDownName, "Cost"+btnUpCost+btnDownCost, "Description", "");

        for (Template t : templates) {
            addRow().addCell(t.getName()).addCell(t.getCost() + "")
                    .addCell(t.getDescription())
                    .addButton("connect", "connect" + t.getId()).builtRow();
        }

    }
}
