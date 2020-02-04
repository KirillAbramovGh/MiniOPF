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
                execute(key, req, resp);
            }
        }
        forward("/webCustomerView.jsp", req, resp);
    }


    private void execute(String key, HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException
    {
        String[] res = key.split(" ");
        String command = res[0];

        ServletContext context = getServletContext();
        try
        {
            CustomerCommand.valueOf(command).execute(req, resp, context, customerWebOperations, key);
        }
        finally
        {
            forward("/webCustomerView.jsp",req,resp);
        }
    }

    private void forward(String path, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher(path);

        requestDispatcher.forward(request, response);
    }


}
