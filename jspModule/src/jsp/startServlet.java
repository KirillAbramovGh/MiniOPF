package jsp;

import com.netcracker.students.o3.Exceptions.IncorrectCredentialsException;
import com.netcracker.students.o3.Exceptions.LoginOccupiedException;
import com.netcracker.students.o3.controller.Controller;
import com.netcracker.students.o3.controller.ControllerImpl;
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

@WebServlet("/start")
public class startServlet extends HttpServlet
{
    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp)
            throws ServletException, IOException
    {
        if (req.getParameter("loginUser") != null)
        {
            BigInteger id = login(req);
            if (id != null)
            {
                req.getSession().setAttribute("id", id);
                forward("/webCustomerView.jsp", req, resp);
            }else {
                forward("/startView.jsp",req,resp);
            }
        }
        else if (req.getParameter("regCustomer") != null)
        {
            BigInteger id = regCustomer(req);
            if (id != null)
            {
                req.getSession().setAttribute("id",id);
                forward("/webCustomerView.jsp", req, resp);
            }else {
                forward("/startView.jsp",req,resp);
            }
        }
        else if (req.getParameter("reqAdmin") != null)
        {
            BigInteger id = regEmployee(req);
            if (id != null)
            {
                req.getSession().setAttribute("id",id);

            }else {
                forward("/startView.jsp",req,resp);
            }
        }


    }

    private void forward(String path, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        ServletContext servletContext = getServletContext();
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(path);

        requestDispatcher.forward(request, response);
    }

    private BigInteger regEmployee(final HttpServletRequest req)
    {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String name = req.getParameter("fio");

        BigInteger employeeId = null;
        if (!login.isEmpty() && !password.isEmpty() && !name.isEmpty())
        {
            employeeId = ControllerImpl.getInstance().registerEmployee(login, password, name);
        }

        return employeeId;
    }

    private BigInteger login(HttpServletRequest req)
    {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        BigInteger userId = null;
        try
        {
            userId = ControllerImpl.getInstance().getUserIdByCredentials(login, password);
        }
        catch (IncorrectCredentialsException e)
        {

        }

        return userId;
    }

    private BigInteger regCustomer(HttpServletRequest req)
    {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String name = req.getParameter("fio");
        String area = req.getParameter("area");


        Controller controller = ControllerImpl.getInstance();
        BigInteger areaId = null;

        for (Area a : controller.getAreas())
        {
            if (a.getName().equals(area))
            {
                areaId = a.getId();
            }
        }


        BigInteger userId = null;
        if (!login.isEmpty() && !password.isEmpty() && !name.isEmpty())
        {
            try
            {
                userId = controller.registerCustomer(login, password, name, areaId);
            }
            catch (LoginOccupiedException e)
            {
            }
        }

        return userId;
    }
}
