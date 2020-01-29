package jsp.servlets;

import com.netcracker.students.o3.controller.sorters.SortType.ServiceSortType;
import com.netcracker.students.o3.controller.sorters.SortType.TemplateSortType;
import com.netcracker.students.o3.model.area.Area;

import java.io.IOException;
import java.math.BigInteger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp.CustomerWebOperations;


@WebServlet("/customerServlet")
public class CustomerServlet extends HttpServlet
{
    CustomerWebOperations customerWebOperations = CustomerWebOperations.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        BigInteger id = (BigInteger) req.getSession().getAttribute("id");
        customerWebOperations.start(id);


        for (String key : req.getParameterMap().keySet())
        {
            if (req.getParameterMap().get(key) != null)
            {
                String potentialCommand = key.split(" ")[0];
                //todo: сделать через паттерн Command. Один раз вызовем switch case, а потом просто ConcreteCommand
                // .execute()
                //                if (isItCommand(key)) {
                execute(key, req, resp);
                //                    break;
                //                }
            }
        }
    }

    //    private boolean isItCommand(String s) {
    //        return
    //    }


    private void execute(String key, HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException
    {
        String[] res = key.split(" ");
        String command = res[0];
        BigInteger id = null;
        if (res.length > 1)
        {
            id = BigInteger.valueOf(Long.parseLong(res[1]));
        }

        switch (command)
        {
            case "disconnect":
                customerWebOperations.disconnectService(id);
                break;
            case "suspend":
                customerWebOperations.suspendService(id); break;
            case "resume":
                customerWebOperations.resumeService(id); break;
            case "connect":
                customerWebOperations.connectService(id);
                break;
            case "change":
                String name = req.getParameter("fio");
                String password = req.getParameter("password");
                String area = req.getParameter("area");

                Area newArea = null;

                for (Area a : customerWebOperations.getAreas())
                {
                    if (a.getName().equals(area))
                    {
                        newArea = a;
                        break;
                    }
                }

                customerWebOperations.changeName(name);
                customerWebOperations.changePassword(password);
                customerWebOperations.changeArea(newArea);
                break;
            case "searchButton":
                setAttribute("searchField", req.getParameter("searchField"), req);
                break;
            case "ServiceSortUpByName":
                setAttribute("sortServices", ServiceSortType.UpByName, req);
                break;
            case "ServiceSortDownByName":
                setAttribute("sortServices", ServiceSortType.DownByName, req);
                break;
            case "ServiceSortUpByCost":
                setAttribute("sortServices", ServiceSortType.UpByCost, req);
                break;
            case "ServiceSortDownByCost":
                setAttribute("sortServices", ServiceSortType.DownByCost, req);
                break;
            case "TemplateSortUpByName":
                setAttribute("sortTemplates", TemplateSortType.UpByName, req);
                break;
            case "TemplateSortDownByName":
                setAttribute("sortTemplates", TemplateSortType.DownByName, req);
                break;
            case "TemplateSortUpByCost":
                setAttribute("sortTemplates", TemplateSortType.UpByCost, req);
                break;
            case "TemplateSortDownByCost":
                setAttribute("sortTemplates", TemplateSortType.DownByCost, req);
                break;
            case "out":
                forward("/startView.jsp", req, resp);
                break;
        }

    }

    private void forward(String path, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        ServletContext servletContext = getServletContext();
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(path);

        requestDispatcher.forward(request, response);
    }

    private void setAttribute(String key, Object value, HttpServletRequest request)
    {
        request.getSession().setAttribute(key, value);
    }
}
