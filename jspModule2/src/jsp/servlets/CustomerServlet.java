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


    private void execute(String command, HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException
    {
        String[] splitCommand = command.split(" ");
        String commandName = splitCommand[0];

        ServletContext context = getServletContext();
        try
        {
            CustomerCommand.valueOf(commandName).execute(req, resp, context, customerWebOperations, command);
        }catch (IllegalArgumentException ignore){ }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private void forward(String path, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher(path);

        requestDispatcher.forward(request, response);
    }


}
