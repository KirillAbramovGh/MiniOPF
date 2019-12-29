package jsp;

public class HtmlSelectBuilder
{
    private String nameAttribute = "";
    private String innerPart = "";
    private String elementStart = "<option>";
    private String elementEnd = "</option>";

    public void setNameAttribute(final String nameAttribute)
    {
        this.nameAttribute = nameAttribute;
        innerPart+=getStart();
    }

    private String getStart(){
        return "<select name='"+nameAttribute+"'>";
    }

    public void addElement(String nameElement){
        innerPart+=elementStart+nameElement+elementEnd;
    }

    public String built(){
        innerPart+="</select>";
        return innerPart;
    }
}
