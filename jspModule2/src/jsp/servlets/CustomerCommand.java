package jsp.servlets;

import com.netcracker.students.o3.Exceptions.UnpossibleChangeAreaException;
import com.netcracker.students.o3.Exceptions.WrongInputException;
import com.netcracker.students.o3.controller.ControllerImpl;
import com.netcracker.students.o3.controller.sorters.SortType.ServiceSortType;
import com.netcracker.students.o3.controller.sorters.SortType.TemplateSortType;
import com.netcracker.students.o3.model.area.Area;

import java.io.IOException;
import java.math.BigInteger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp.CustomerWebOperations;

public enum CustomerCommand
{

    disconnect
            {
                public void execute(HttpServletRequest req, final HttpServletResponse resp,
                        final ServletContext context, CustomerWebOperations customerWebOperations,
                        final String key)
                {
                    BigInteger id = getIdFromKey(key);
                    customerWebOperations.disconnectService(id);
                }
            },

    suspend
            {
                public void execute(HttpServletRequest req, final HttpServletResponse resp,
                        final ServletContext context, CustomerWebOperations customerWebOperations,
                        final String key)
                {
                    BigInteger id = getIdFromKey(key);
                    customerWebOperations.suspendService(id);
                }
            },

    resume
            {
                public void execute(HttpServletRequest req, final HttpServletResponse resp,
                        final ServletContext context, CustomerWebOperations customerWebOperations,
                        final String key)
                {
                    BigInteger id = getIdFromKey(key);
                    customerWebOperations.resumeService(id);
                }
            },

    connect
            {
                public void execute(HttpServletRequest req, final HttpServletResponse resp,
                        final ServletContext context, CustomerWebOperations customerWebOperations,
                        final String key)
                {
                    BigInteger id = getIdFromKey(key);
                    customerWebOperations.connectService(id);
                }
            },

    change
            {
                public void execute(HttpServletRequest req, final HttpServletResponse resp,
                        final ServletContext context, CustomerWebOperations customerWebOperations,
                        final String key) throws WrongInputException, UnpossibleChangeAreaException
                {
                    changeSettings(req,customerWebOperations);
                }
            },

    searchButton
            {
                public void execute(HttpServletRequest req, final HttpServletResponse resp,
                        final ServletContext context, CustomerWebOperations customerWebOperations,
                        final String key)
                {
                    setAttribute("searchField", req.getParameter("searchField"), req);
                }
            },

    ServiceSortUpByName
            {
                public void execute(HttpServletRequest req, final HttpServletResponse resp,
                        final ServletContext context, CustomerWebOperations customerWebOperations,
                        final String key)
                {
                    setAttribute("sortServices", ServiceSortType.UpByName, req);
                }
            },

    ServiceSortDownByName
            {
                public void execute(HttpServletRequest req, final HttpServletResponse resp,
                        final ServletContext context, CustomerWebOperations customerWebOperations,
                        final String key)
                {
                    setAttribute("sortServices", ServiceSortType.DownByName, req);
                }
            },

    ServiceSortUpByCost
            {
                public void execute(HttpServletRequest req, final HttpServletResponse resp,
                        final ServletContext context, CustomerWebOperations customerWebOperations,
                        final String key)
                {
                    setAttribute("sortServices", ServiceSortType.UpByCost, req);
                }
            },

    ServiceSortDownByCost
            {
                public void execute(HttpServletRequest req, final HttpServletResponse resp,
                        final ServletContext context, CustomerWebOperations customerWebOperations,
                        final String key)
                {
                    setAttribute("sortServices", ServiceSortType.DownByCost, req);
                }
            },

    TemplateSortUpByName
            {
                public void execute(HttpServletRequest req, final HttpServletResponse resp,
                        final ServletContext context, CustomerWebOperations customerWebOperations,
                        final String key)
                {
                    setAttribute("sortTemplates", TemplateSortType.UpByName, req);
                }
            },

    TemplateSortDownByName
            {
                public void execute(HttpServletRequest req, final HttpServletResponse resp,
                        final ServletContext context, CustomerWebOperations customerWebOperations,
                        final String key)
                {
                    setAttribute("sortTemplates", TemplateSortType.DownByName, req);
                }
            },

    TemplateSortUpByCost
            {
                public void execute(HttpServletRequest req, final HttpServletResponse resp,
                        final ServletContext context, CustomerWebOperations customerWebOperations,
                        final String key)
                {
                    setAttribute("sortTemplates", TemplateSortType.UpByCost, req);
                }
            },

    TemplateSortDownByCost
            {
                public void execute(HttpServletRequest req, final HttpServletResponse resp,
                        final ServletContext context, CustomerWebOperations customerWebOperations,
                        final String key)
                {
                    setAttribute("sortTemplates", TemplateSortType.DownByCost, req);
                }
            },

    out
            {
                public void execute(HttpServletRequest req, final HttpServletResponse resp,
                        final ServletContext context, CustomerWebOperations customerWebOperations,
                        final String key) throws ServletException, IOException
                {
                    forward("/startView.jsp",context, req, resp);
                }
            };

    public void execute(HttpServletRequest req, final HttpServletResponse resp,
            final ServletContext context, CustomerWebOperations customerWebOperations, final String key)
            throws IOException, ServletException
    {
        System.out.println("standard");
    }

    private static BigInteger getIdFromKey(String key){
        String[] res = key.split(" ");
        long longValue = Long.parseLong(res[1]);
        return BigInteger.valueOf(longValue);
    }

    private static void changeSettings(final HttpServletRequest req, final CustomerWebOperations customerWebOperations) throws WrongInputException, UnpossibleChangeAreaException
    {
        String name = req.getParameter("fio");
        String password = req.getParameter("password");
        BigInteger area = BigInteger.valueOf(Long.parseLong(req.getParameter("area")));

        Area newArea = ControllerImpl.getInstance().getArea(area);


        customerWebOperations.changeName(name);
        customerWebOperations.changePassword(password);
        customerWebOperations.changeArea(newArea);
    }

    private static void setAttribute(String key, Object value, HttpServletRequest request)
    {
        request.getSession().setAttribute(key, value);
    }

    private static void forward(String path,ServletContext context, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        RequestDispatcher requestDispatcher = context.getRequestDispatcher(path);

        requestDispatcher.forward(request, response);
    }
}

