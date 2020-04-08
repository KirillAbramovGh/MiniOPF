package jsp.servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jsp.ejb.EmployeeSessionBean;

@WebServlet("/employeeServlet")
public class EmployeeServlet extends HttpServlet
{
    @EJB
    EmployeeSessionBean employeeSessionBean;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        cleanSession(req.getSession());
        req.getSession().setAttribute("nextPage", "/webEmployeeView.jsp");
        for (String key : req.getParameterMap().keySet())
        {
            if (req.getParameterMap().get(key) != null)
            {
                parse(key, req, resp);
            }
        }

        forward((String) req.getSession().getAttribute("nextPage"), req, resp);
    }

    private void parse(String key, HttpServletRequest req, HttpServletResponse resp)
    {
        String[] res = key.split(" ");
        String command = res[0];

        try
        {
            EmployeeCommand.valueOf(command).execute(req, resp, getServletContext(),
                    employeeSessionBean, key);
        }
        catch (Exception e)
        {
            e.printStackTrace();
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

}
