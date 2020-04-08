package jsp;

import com.netcracker.students.o3.model.area.Area;

import javax.servlet.http.HttpSession;

import jsp.builders.HtmlSelectBuilder;
import jsp.ejb.StartSessionBean;

public class StartWebVisualiser
{

    private static StartWebVisualiser instance;

    private StartWebVisualiser()
    {
    }

    public static StartWebVisualiser getInstance()
    {
        if (instance == null)
        {
            instance = new StartWebVisualiser();
        }
        return instance;
    }

    public String showAreas(StartSessionBean startSessionBean)
    {
        HtmlSelectBuilder selectBuilder = new HtmlSelectBuilder();
        selectBuilder.setNameAttribute("area");

        for (Area area : startSessionBean.getAreas())
        {
            selectBuilder.addElement(area.getName());
        }

        return selectBuilder.built();
    }

    public String showErrorMessage(HttpSession session)
    {
        String error = (String) session.getAttribute("error");
        error = error == null ? "" : error;
        return "<h4 align='center'>" + error + "</h4>";
    }
}

