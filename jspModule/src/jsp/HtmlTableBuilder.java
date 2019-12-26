package jsp;

import java.util.ArrayList;
import java.util.Arrays;
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

    public HtmlTableBuilder(){
        columns = new ArrayList<>();
    }

    public void addColumns(String... columnNames){
        columns.addAll(Arrays.asList(columnNames));

        innerPart+=rowStart;
        for(String columnName : columnNames){
            innerPart+="<th>"+columnName+"</th>";
        }
        innerPart+=rowEnd;
    }

    public void addRow(String... cells){
        innerPart+=rowStart;
        for(String cell : cells){
            innerPart+=cellStart+cell+cellEnd;
        }
        innerPart+=rowEnd;
    }

    public HtmlTableBuilder addRow(){
        innerPart+=rowStart;
        return this;
    }

    public HtmlTableBuilder addCell(String cell){
        innerPart+=cellStart+cell+cellEnd;
        return this;
    }

    public HtmlTableBuilder addButton(String type,String value,String name){
        innerPart+=cellStart+buttonStart+"type='"+type+"' value='"+value+"' name='"+name+"'"+buttonEnd+cellEnd;
        return this;
    }

    public HtmlTableBuilder addButton(String value,String name){
        addButton("submit",value,name);
        return this;
    }

    public HtmlTableBuilder builtRow(){
        innerPart+=rowEnd;
        return this;
    }

    public String buitlt(){
        String resultHtml = start+innerPart+end;
        return resultHtml;
    }

}
