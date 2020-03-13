package jsp;

import com.netcracker.students.o3.controller.Controller;
import com.netcracker.students.o3.controller.ControllerImpl;
import com.netcracker.students.o3.model.area.Area;

import java.util.List;

import javax.servlet.http.HttpSession;

import jsp.builders.HtmlSelectBuilder;

public class StartWebOperations
{

    private static StartWebOperations instance;

    private StartWebOperations()
    {
    }

    public static StartWebOperations getInstance()
    {
        if (instance == null)
        {
            instance = new StartWebOperations();
        }
        return instance;
    }

    public String showAreas()
    {
        Controller controller = ControllerImpl.getInstance();
        List<Area> areas = controller.getAreas();
        HtmlSelectBuilder selectBuilder = new HtmlSelectBuilder();
        selectBuilder.setNameAttribute("area");

        for (Area area : areas)
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

