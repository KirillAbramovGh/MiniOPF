package jsp.servlets;

import java.io.IOException;
import java.math.BigInteger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jsp.EmployeeWebOperations;

@WebServlet("/employeeServlet")
public class EmployeeServlet extends HttpServlet
{
    private EmployeeWebOperations employeeWebOperations = EmployeeWebOperations.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        BigInteger id = (BigInteger) req.getSession().getAttribute("id");
        employeeWebOperations.start(id);
        cleanSession(req.getSession());
        for (String key : req.getParameterMap().keySet())
        {
            if (req.getParameterMap().get(key) != null)
            {
                parse(key, req, resp);
            }
        }

        forward("/webEmployeeView.jsp", req, resp);
    }

    private void parse(String key, HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException
    {
        String[] res = key.split(" ");
        String command = res[0];

        try
        {
            EmployeeCommand.valueOf(command).execute(req, resp, getServletContext(),
                    employeeWebOperations, key);
        }
        catch (Exception e){

        }
    }

    private void forward(String path, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        ServletContext servletContext = getServletContext();
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(path);

        requestDispatcher.forward(request, response);
    }

    private void cleanSession(HttpSession session)
    {
        session.setAttribute("sortOrders", null);
        session.setAttribute("sortEmployees", null);
        session.setAttribute("sortCustomers", null);
        session.setAttribute("sortAreas", null);
        session.setAttribute("sortTemplates", null);
        session.setAttribute("sortServices", null);
    }

    private void setAttribute(String key, Object value, HttpServletRequest request)
    {
        request.getSession().setAttribute(key, value);
    }
}
