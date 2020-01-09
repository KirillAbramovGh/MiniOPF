package jsp;

import com.netcracker.students.o3.controller.ControllerImpl;
import com.netcracker.students.o3.model.services.Service;
import com.netcracker.students.o3.model.services.ServiceStatus;
import com.netcracker.students.o3.model.templates.Template;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class HtmlTableBuilder
{
    private String start = "<table border='1' width='auto' cellpadding='20'>";
    private String end = "</table>";

    private String cellStart = "<td>";
    private String cellEnd = "</td>";

    private String rowStart = "<tr>";
    private String rowEnd = "</tr>";

    private String buttonStart = "<input ";
    private String buttonEnd = " />";

    private List<String> columns;

    private String innerPart = "";

    public HtmlTableBuilder()
    {
        columns = new ArrayList<>();
    }

    public void addColumns(String... columnNames)
    {
        columns.addAll(Arrays.asList(columnNames));

        innerPart += rowStart;
        for (String columnName : columnNames)
        {
            innerPart += "<th>" + columnName + "</th>";
        }
        innerPart += rowEnd;
    }

    public void addRow(String... cells)
    {
        innerPart += rowStart;
        for (String cell : cells)
        {
            innerPart += cellStart + cell + cellEnd;
        }
        innerPart += rowEnd;
    }

    public HtmlTableBuilder addRow()
    {
        innerPart += rowStart;
        return this;
    }

    public HtmlTableBuilder addCell(String cell)
    {
        innerPart += cellStart + cell + cellEnd;
        return this;
    }

    public HtmlTableBuilder addButton(String type, String value, String name)
    {
        innerPart +=
                cellStart + buttonStart + "type='" + type + "' value='" + value + "' name='" + name + "'" + buttonEnd +
                        cellEnd;
        return this;
    }

    public HtmlTableBuilder addButton(String value, String name)
    {
        addButton("submit", value, name);
        return this;
    }

    public HtmlTableBuilder builtRow()
    {
        innerPart += rowEnd;
        return this;
    }

    public String built()
    {
        String resultHtml = start + innerPart + end;
        return resultHtml;
    }

    public void createServicesTable(Collection<Service> services)
    {

        addColumns("id", "Name", "Cost", "Status", "disconnect", "suspend/resume");

        String id;
        String name;
        String cost;
        String status;

        int i = 1;
        for (Service service : services)
        {
            id = i + "";
            name = ControllerImpl.getInstance().getService(service.getId()).getName();
            cost = service.getCost() + "";
            status = service.getStatus() + "";

            addRow().addCell(id).addCell(name).addCell(cost).addCell(status);

            if (service.getStatus() == ServiceStatus.Entering)
            {
                addButton("cancel", "disconnect" + service.getId());
                addCell("");
            }
            else if (service.getStatus() == ServiceStatus.Active)
            {
                addButton("disconnect", "disconnect" + service.getId());
                addButton("suspend", "suspend" + service.getId());
            }
            else if (service.getStatus() == ServiceStatus.Suspended)
            {
                addButton("disconnect", "disconnect" + service.getId());
                addButton("resume", "suspend" + service.getId());
            }
            builtRow();
            i++;
        }
    }

    public void createTemplatesTable(Collection<Template> templates){
        addColumns("id", "name", "cost", "description", "connect");

        int i = 1;
        for (Template t : templates)
        {
            addRow().addCell(i + "").addCell(t.getName()).addCell(t.getCost() + "")
                    .addCell(t.getDescription())
                    .addButton("connect", "connect" + t.getId()).builtRow();
            i++;
        }

    }
}
